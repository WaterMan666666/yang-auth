package com.fireman.yang.auth.core.server.support;

import com.fireman.yang.auth.core.User;
import com.fireman.yang.auth.core.login.PasswordToken;
import com.fireman.yang.auth.core.server.dto.AppClientDTO;
import com.fireman.yang.auth.core.server.service.AuthServerService;
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
 * @Date: 2020/11/17
 * @Description:
 */
public class LocalAuthServerServiceImpl implements AuthServerService {

    private static final Logger log = LoggerFactory.getLogger(LocalAuthServerServiceImpl.class);

    private String basePath;

    private Map<String, User> userMap = new HashMap<>();

    private Map<String, AppClientDTO> appClientMap = new HashMap<>();

    public LocalAuthServerServiceImpl(String basePath) {
        this.basePath = basePath;
        init();
    }

    @Override
    public AppClientDTO getAppClient(String clientId) {
        AppClientDTO appClientDTO = appClientMap.get(clientId);
        AppClientDTO result = new AppClientDTO();
        try {
            BeanUtils.copyProperties(result, appClientDTO);
        } catch (InvocationTargetException |IllegalAccessException  e) {
            log.error(" User bean copy error! ");
            return null;
        }
        return result;
    }

    @Override
    public User authenticate(PasswordToken passwordToken) {
        String username = passwordToken.getUsername();
        User user = userMap.get(username);
        String password = user.getPassword();
        if(passwordToken.getToken().equals(password)){
            User authenticate = new User();
            try {
                BeanUtils.copyProperties(authenticate, user);
            } catch (InvocationTargetException |IllegalAccessException  e) {
                log.error(" User bean copy error! ");
                return null;
            }
            authenticate.setPassword(null);
            return authenticate;
        }
        return null;
    }
    public void init(){
        List users = init("user.json", User.class);
        if(!CollectionUtils.isEmpty(users)){
            userMap.clear();
            for (Object object : users){
                if(object instanceof User) {
                    User user = (User)object;
                    userMap.put(user.getUsername(), user);
                }
            }
            log.info("User info update!");
        }
        List clients = init("client.json", AppClientDTO.class);
        if(!CollectionUtils.isEmpty(users)){
            appClientMap.clear();
            for (Object object : clients){
                if(object instanceof AppClientDTO) {
                    AppClientDTO appClientDTO = (AppClientDTO)object;
                    appClientMap.put(appClientDTO.getClientId(), appClientDTO);
                }
            }
            log.info("AppClient info update!");
        }
    }

    public List init(String fileName, Class clazz){
        String readJsonFile = null;
        if(StringUtils.isBlank(basePath)) {
            URI resource = getFileUri(fileName);
            if(resource == null){
                log.info("can not find {}, you can add it", fileName);
                return null;
            }
            readJsonFile = readJsonFile(resource);
        }else{
            readJsonFile = readJsonFile(basePath, fileName);
        }
        return JsonUtils.jsonToArray(readJsonFile, clazz);
    }
    public static String readJsonFile(String basePath, String fileName) {
        File jsonFile = new File(basePath, fileName);
        return  readJsonFile(jsonFile);
    }
    public static String readJsonFile(URI uri) {
        File jsonFile = new File(uri);
        return  readJsonFile(jsonFile);
    }
    public static String readJsonFile(File jsonFile) {
        String jsonStr = "";
        try {
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
    private URI getFileUri(String fileName){
        URL resource = this.getClass().getClassLoader().getResource(fileName);
        if(resource == null){
            log.info("can not find {}, you can add it", fileName);
            return null;
        }
        try {
            return resource.toURI();
        } catch (URISyntaxException e) {
            log.error("error read josn", e);
        }
        return null;
    }

}
