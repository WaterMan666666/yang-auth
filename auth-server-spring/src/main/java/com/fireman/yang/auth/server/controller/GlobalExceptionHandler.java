package com.fireman.yang.auth.server.controller;

import com.fireman.yang.auth.core.common.enums.ReturnCode;
import com.fireman.yang.auth.core.exception.AuthenticateException;
import com.fireman.yang.auth.core.exception.ParameterErrorException;
import com.fireman.yang.auth.core.web.utils.json.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName GlobalExceptionHandler
 * @Author TD
 * @Date 2019/1/16 11:52
 * @Description 异常统一拦截
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 参数类型错误
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value= ParameterErrorException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public HttpEntity<String> parameterErrorExceptionHandler(ParameterErrorException e) {
        String msg = e.getMsg();
        log.error("====参数异常：{}===",msg);
        Map<String,String> map = new HashMap<>(2);
        map.put("msg",msg);
        return returnMessage(JsonUtils.toJsonString(map));
    }

    /**
     * 未授权类型错误
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value= AuthenticateException.class)
    public HttpEntity<String> authenticateExceptionHandler(HttpServletResponse response, AuthenticateException e) {
        String msg = e.getMsg();
        log.error("====签名异常：{}===",msg);
        Map<String,String> map = new HashMap<>(2);
        map.put("msg",msg);
        response.setStatus(ReturnCode.UNAUTHORIZED.getCode());
        return returnMessage(JsonUtils.toJsonString(map));
    }




    /**
     * 所有异常报错
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value= Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public HttpEntity<String> allExceptionHandler(HttpServletResponse response, Exception e) {
        String msg = "系统错误";
        log.error("====系统错误===",e);
        Map<String,String> map = new HashMap<>(2);
        map.put("msg",msg);
        response.setStatus(ReturnCode.SYSTEM_ERROR.getCode());
        return returnMessage(JsonUtils.toJsonString(map));
    }


    private HttpEntity<String> returnMessage(String content){
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type","application/json");
        header.add("Charset","UTF-8");
        return new HttpEntity<>(content,header);
    }


}
