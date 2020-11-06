package com.fireman.yang.auth.core.common.constants;

/**
 * @ClassName AuthConstants
 * @Author TD
 * @Date 2019/1/16 14:55
 * @Description 授权常量
 */
public class AuthConstants {

    /** Token Cookie 授权标识 */
    public static final String AUTHORIZATION = "Authorization";
    public static final String AUTHORIZATION_PERMISSION = "AuthorizationPermission";
    public static final String BEARER = "Bearer ";


    public static final String SESSION_TYPE_TOKEN = "TOKEN";
    public static final String SESSION_TYPE_COOKIE = "COOKIE";

    /** 授权模式：授权码 */
    public static final String GRANT_TYPE_CODE = "authorization_code";

    /** Redis图片验证码标识 */
    public static final String UCENTER = "UCENTER";

    public static final String AUTH = "AUTH";

    public static final String SESSION = "SESSION";

    public static final String ONLINE_USER = "ONLINE_USER";

    public static final String AUTH_CODE = "AUTH_CODE";

    public static final String ACCESS_TOKEN = "ACCESS_TOKEN";

    /** 用户标识 */
    public static final String USIGN = "usign";

    /** 客户端关键字：ID */
    public static final String AUTH_ID = "auth_id";
    /** 客户端关键字：用户 */
    public static final String AUTH_USER = "auth_user";
    /** 客户端关键字：角色 */
    public static final String AUTH_ROLE = "user_roles";
    /** 客户端关键字：用户菜单 */
    public static final String AUTH_MENU = "user_menus";
    /** 客户端关键字：AccessToken */
    public static final String AUTH_TOKEN = "access_token";

    /** 默认session时间：30分钟 */
    public static final Integer SESSION_EXPIRE_DEFAULT = 30 * 60;
}