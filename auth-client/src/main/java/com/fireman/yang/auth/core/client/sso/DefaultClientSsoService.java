package com.fireman.yang.auth.core.client.sso;

import com.fireman.yang.auth.core.User;
import com.fireman.yang.auth.core.client.config.AuthClientConfig;
import com.fireman.yang.auth.core.common.constants.AuthConstants;
import com.fireman.yang.auth.core.common.enums.GrantType;
import com.fireman.yang.auth.core.common.enums.ReturnCode;
import com.fireman.yang.auth.core.exception.AuthenticateException;
import com.fireman.yang.auth.core.exception.ParameterErrorException;
import com.fireman.yang.auth.core.exception.SystemErrorException;
import com.fireman.yang.auth.core.login.AuthorizeCodeToken;
import com.fireman.yang.auth.core.session.AccessToken;
import com.fireman.yang.auth.core.sso.ClientSsoService;
import com.fireman.yang.auth.core.web.utils.https.HttpEntity;
import com.fireman.yang.auth.core.web.utils.https.HttpUtils;
import com.fireman.yang.auth.core.web.utils.json.JsonUtils;
import okhttp3.HttpUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tongdong
 * @Date: 2020/11/18
 * @Description:
 */
public class DefaultClientSsoService implements ClientSsoService {

    private static final Logger log = LoggerFactory.getLogger(DefaultClientSsoService.class);

    private AuthClientConfig config;

    public DefaultClientSsoService(AuthClientConfig config) {
        this.config = config;
    }

    @Override
    public AccessToken authenticate(AuthorizeCodeToken token) {
        Map<String, String> params = new HashMap<>(8);
        params.put("grant_type", GrantType.authorization_code.name());
        params.put("code", token.getCode());
        params.put("client_id", config.getClientId());
        params.put("client_secret", config.getClientSecret());
        HttpUrl httpUrl = HttpUrl.parse(config.getAuthDomain());
        HttpUrl.Builder builder = httpUrl.newBuilder();
        builder.addEncodedPathSegment(config.getAuthTokenUri());
        HttpUrl build = builder.build();
        HttpEntity httpEntity = HttpUtils.doPost(HttpUtils.FROM, build.toString(), params, null);
        checkReturnCode(httpEntity);
        //成功获取
        String content = httpEntity.getContent();
        AccessToken accessToken = JsonUtils.jsonToObject(content, AccessToken.class);
        return accessToken;
    }

    @Override
    public User getUserInfo(AccessToken token) {
        HttpUrl httpUrl = HttpUrl.parse(config.getAuthDomain());
        HttpUrl.Builder builder = httpUrl.newBuilder();
        builder.addEncodedPathSegment("oauth");
        builder.addEncodedPathSegment("user");
        HttpUrl build = builder.build();
        Map<String, String> header = new HashMap<>(2);
        header.put(AuthConstants.AUTHORIZATION, AuthConstants.BEARER + token.getAccessToken());
        HttpEntity httpEntity = HttpUtils.doGet(build.toString(), null, header);
        checkReturnCode(httpEntity);
        //成功获取
        String content = httpEntity.getContent();
        User user = JsonUtils.jsonToObject(content, User.class);
        return user;
    }


    /**
     * 返回码验证
     */
    private void checkReturnCode (HttpEntity entity){
        int code = entity.getCode();
        ReturnCode match = ReturnCode.toEnum(code);
        if(match == null){
            log.error("调用接口返回码：{}", code );
            throw new SystemErrorException("调用授权返回码异常:" + code);
        }
        Map map = null;
        switch (match) {
            case OK :
                break;
            case BAD_REQUEST :
                throw new ParameterErrorException("参数错误");
            case UNAUTHORIZED :
                map = JsonUtils.jsonToObject(entity.getContent(), Map.class);
                throw new AuthenticateException(String.valueOf(map.get("msg")));
            case SYSTEM_ERROR :
                map = JsonUtils.jsonToObject(entity.getContent(), Map.class);
                throw new SystemErrorException(String.valueOf(map.get("msg")));
            default:
                throw new SystemErrorException("系统异常");
        }
    }
}
