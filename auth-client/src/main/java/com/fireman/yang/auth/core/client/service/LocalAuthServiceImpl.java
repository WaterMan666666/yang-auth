package com.fireman.yang.auth.core.client.service;

import com.fireman.yang.auth.core.User;
import com.fireman.yang.auth.core.login.PasswordToken;
import com.fireman.yang.auth.core.service.AuthService;
import com.fireman.yang.auth.core.web.utils.CollectionUtils;
import com.fireman.yang.auth.core.web.utils.StringUtils;
import com.fireman.yang.auth.core.web.utils.json.JsonUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tongdong
 * @Date: 2020/11/6
 * @Description:
 */
public class LocalAuthServiceImpl implements AuthService {

    private static final Logger log = LoggerFactory.getLogger(LocalAuthServiceImpl.class);

    private String userPath;

    private Map<String, User> map = new HashMap<>();

    public LocalAuthServiceImpl(String userPath) {
        this.userPath = userPath;
        init();
    }

    public void init(){
        if(StringUtils.isBlank(userPath)) {
            URL resource = this.getClass().getClassLoader().getResource("user.json");
            if(resource == null){
                log.info("can not find user.json, you can add it");
                return;
            }
            try {
                URI uri = resource.toURI();
                userPath = resource.toURI().getPath();
            } catch (URISyntaxException e) {
                log.error("error read josn", e);
            }
        }
        String readJsonFile = readJsonFile(userPath);
        List<User> users = JsonUtils.jsonToArray(readJsonFile, User.class);
        if(!CollectionUtils.isEmpty(users)){
            map.clear();
            for (User user : users){
                map.put(user.getUsername(), user);
            }
            log.info("user info update!");
        }
    }

    @Override
    public User authenticate(PasswordToken passwordToken) {
        String username = passwordToken.getUsername();
        User user = map.get(username);
        String password = user.getPassword();
        if(passwordToken.getToken().equals(password)){
            User authenticate = new User();
            try {
                BeanUtils.copyProperties(authenticate, user);
            } catch (InvocationTargetException |IllegalAccessException  e) {
                log.error(" User bean copy error! ");
//                throw e;
                return null;
            }
            authenticate.setPassword(null);
            return authenticate;
        }
        return null;
    }

    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
