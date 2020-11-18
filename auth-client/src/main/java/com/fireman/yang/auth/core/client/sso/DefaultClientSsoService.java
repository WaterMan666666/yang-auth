package com.fireman.yang.auth.core.client.sso;

import com.fireman.yang.auth.core.User;
import com.fireman.yang.auth.core.client.config.AuthClientConfig;
import com.fireman.yang.auth.core.common.constants.AuthConstants;
import com.fireman.yang.auth.core.common.enums.GrantType;
import com.fireman.yang.auth.core.login.AuthorizeCodeToken;
import com.fireman.yang.auth.core.sso.AccessToken;
import com.fireman.yang.auth.core.sso.ClientSsoService;
import com.fireman.yang.auth.core.web.utils.StringUtils;
import com.fireman.yang.auth.core.web.utils.https.HttpEntity;
import com.fireman.yang.auth.core.web.utils.https.HttpUtils;
import okhttp3.HttpUrl;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tongdong
 * @Date: 2020/11/18
 * @Description:
 */
public class DefaultClientSsoService implements ClientSsoService {

    private AuthClientConfig config;

    public DefaultClientSsoService(AuthClientConfig config) {
        this.config = config;
    }

    @Override
    public AccessToken authenticate(AuthorizeCodeToken token) {
        Map<String, String> params = new HashMap<>(8);
        params.put("grant_type", GrantType.authorization_code.name());
        params.put("code", token.getCode());
        params.put("redirect_uri", config.getLoginUri());
        params.put("client_id", config.getClientId());
        params.put("client_secret", config.getClientSecret());
        HttpUrl httpUrl = HttpUrl.parse(config.getAuthDomain());
        HttpUrl.Builder builder = httpUrl.newBuilder();
        builder.addEncodedPathSegment(config.getAuthTokenUri());
        HttpUrl build = builder.build();
        HttpEntity httpEntity = HttpUtils.doPost(HttpUtils.FROM, build.toString(), params, null);
        return null;
    }

    @Override
    public User getUserInfo(AccessToken token) {
        return null;
    }
}
