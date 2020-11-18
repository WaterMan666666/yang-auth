package com.fireman.yang.auth.core.web.utils.https;

import com.fireman.yang.auth.core.exception.SystemErrorException;
import com.fireman.yang.auth.core.web.utils.json.JsonUtils;
import okhttp3.*;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @ClassName HttpUtils
 * @Author TD
 * @Date 2019/1/9 17:44
 * @Description OKHttp3为基础的http调用工具，支持证书验证
 */
public class HttpUtils {

    private static OkHttpClient client = null;

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType XML
            = MediaType.parse("application/xml; charset=utf-8");
    public static final MediaType FROM
            = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");

    static {
        client = new OkHttpClient
                .Builder()
                //目前证书都忽略
                .sslSocketFactory(createSSLSocketFactory(),new TrustAllCerts())
                .hostnameVerifier(new TrustAllHostnameVerifier())
                .readTimeout(50, TimeUnit.SECONDS)
                .connectTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool())
                .build();
        client.dispatcher().setMaxRequests(400);
        client.dispatcher().setMaxRequestsPerHost(20);
    }

    /**
     *  Post调用
     * @param url
     * @param params 发送参数
     * @return string
     */
    public static HttpEntity doPostForm ( String url,  Map<String, String> params, Map<String, String> headersParams) {
        FormBody.Builder builder = new FormBody.Builder();
        if(params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .headers(setHeaders(headersParams))
                .build();
        try {
            Response response = client.newCall(request).execute();
            return new HttpEntity(response.code(),response.body().string());
        } catch (Exception e) {
            throw  new SystemErrorException("调用Http异常", e);
        }
    }

    /**
     *  Post调用
     * @param type  类型
     * @param url
     * @param content 发送内容
     * @return string
     */
    public static HttpEntity doPost (MediaType type, String url, String content, Map<String, String> headersParams) {
        RequestBody body = RequestBody.create(type, content);
        Request request = new Request.Builder()
                .url(url)
                .headers(setHeaders(headersParams))
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            return new HttpEntity(response.code(),response.body().string());
        } catch (Exception e) {
            throw  new SystemErrorException("调用Http异常", e);
        }
    }

    /**
     *  Put调用
     * @param type  类型
     * @param url
     * @param content 发送内容
     * @return string
     */
    public static HttpEntity doPut (MediaType type, String url, String content, Map<String, String> headersParams) {
        RequestBody body = RequestBody.create(type, content);
        Request request = new Request.Builder()
                .url(url)
                .headers(setHeaders(headersParams))
                .put(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            return new HttpEntity(response.code(),response.body().string());
        } catch (Exception e) {
            throw  new SystemErrorException("调用Http异常", e);
        }
    }

    /**
     *  Put调用
     * @param url
     * @param params 发送参数
     * @return string
     */
    public static HttpEntity doPutForm ( String url,  Map<String, String> params, Map<String, String> headersParams) {
        FormBody.Builder builder = new FormBody.Builder();
        if(params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .put(formBody)
                .headers(setHeaders(headersParams))
                .build();
        try {
            Response response = client.newCall(request).execute();
            return new HttpEntity(response.code(),response.body().string());
        } catch (Exception e) {
            throw  new SystemErrorException("调用Http异常", e);
        }
    }
    /**
     *  Delete调用
     * @param type  类型
     * @param url
     * @param content 发送内容
     * @return string
     */
    public static HttpEntity doDelete (MediaType type, String url, String content, Map<String, String> headersParams){
        RequestBody body = RequestBody.create(type, content);
        Request request = new Request.Builder()
                .url(url)
                .headers(setHeaders(headersParams))
                .delete(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            return new HttpEntity(response.code(),response.body().string());
        } catch (Exception e) {
            throw  new SystemErrorException("调用Http异常", e);
        }
    }

    /**
     *  Delete调用
     * @param url
     * @param params 发送参数
     * @return string
     */
    public static HttpEntity doDeleteForm ( String url,  Map<String, String> params, Map<String, String> headersParams) {
        FormBody.Builder builder = new FormBody.Builder();
        if(params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .delete(formBody)
                .headers(setHeaders(headersParams))
                .build();
        try {
            Response response = client.newCall(request).execute();
            return new HttpEntity(response.code(),response.body().string());
        } catch (Exception e) {
            throw  new SystemErrorException("调用Http异常", e);
        }
    }



    /**
     *  Put调用
     * @param type  类型
     * @param url
     * @param params 发送内容
     * @return string
     */
    public static HttpEntity doPut (MediaType type, String url, Map<String, String> params,  Map<String, String> headersParams){
        if(FROM.toString().equals(type.toString())){
            return HttpUtils.doPutForm( url, params, headersParams );
        }
        return HttpUtils.doPut( type, url, JsonUtils.toJsonString(params), headersParams);
    }

    /**
     *  Post调用
     * @param type  类型
     * @param url
     * @param params 发送内容
     * @return string
     */
    public static HttpEntity doDelete (MediaType type, String url, Map<String, String> params,  Map<String, String> headersParams){
        if(FROM.toString().equals(type.toString())){
            return HttpUtils.doDeleteForm( url, params, headersParams );
        }
        return HttpUtils.doDelete( type, url, JsonUtils.toJsonString(params), headersParams);
    }

    /**
     *  Post调用
     * @param type  类型
     * @param url
     * @param params 发送内容
     * @return string
     */
    public static HttpEntity doPost (MediaType type, String url, Map<String, String> params,  Map<String, String> headersParams) {
        if(FROM.toString().equals(type.toString())){
            return HttpUtils.doPostForm( url, params, headersParams );
        }
        return HttpUtils.doPost( type, url, JsonUtils.toJsonString(params), headersParams);
    }

    /**
     *  Post调用
     * @param type  类型
     * @param url
     * @param content 发送内容
     * @return string
     */
    public static HttpEntity doPost (MediaType type, String url, String content) {
       return HttpUtils.doPost(type,url,content,null);
    }

    /**
     *  Get调用
     * @param url
     * @param params 发送内容
     * @return string
     */
    public static HttpEntity doGet (String url , Map<String,String> params, Map<String, String> headersParams) {
        HttpUrl httpUrl = HttpUrl.parse(url);
        HttpUrl.Builder builder = httpUrl.newBuilder();
        if(params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.addQueryParameter(entry.getKey(), entry.getValue());
            }
        }
        httpUrl = builder.build();
        Request request = new Request.Builder()
                .url(httpUrl)
                .headers(setHeaders(headersParams))
                .get()
                .build();
        try {
            Response response = client.newCall(request).execute();
            return new HttpEntity(response.code(),response.body().string());
        } catch (Exception e) {
            throw  new SystemErrorException("调用Http异常", e);
        }
    }


    /**
     *  Get异步调用
     * @param url
     * @param params 发送内容
     * @return string
     */
    public static void doGetAsyn (String url ,  Map<String,String> headersParams, Map<String,String> params, Consumer<HttpEntity> consumer) {
        HttpUrl httpUrl = HttpUrl.parse(url);
        HttpUrl.Builder builder = httpUrl.newBuilder();
        for(Map.Entry<String,String> entry : params.entrySet()){
            builder.addQueryParameter(entry.getKey(),entry.getValue());
        }
        httpUrl = builder.build();
        Request request = new Request.Builder()
                .url(httpUrl)
                .headers(setHeaders(headersParams))
                .get()
                .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    throw  new SystemErrorException("调用Http异常", e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    HttpEntity httpEntity = new HttpEntity(response.code(), response.body().string());
                    consumer.accept(httpEntity);
                }
            });
    }

    /**
     * HTTPS  --- 证书处理
     */
    private static class TrustAllCerts implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}

        @Override
        public X509Certificate[] getAcceptedIssuers() {return new X509Certificate[0];}
    }

    /**
     * HTTPS  --- 证书处理
     */
    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    /**
     * HTTPS  --- 证书处理
     */
    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null,  new TrustManager[] { new TrustAllCerts() }, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }
        return ssfFactory;
    }

    /**
     * 设置请求头
     * @return
     */
    private static Headers setHeaders(Map<String, String> headersParams){

        Headers.Builder headersbuilder=new Headers.Builder();
        if(headersParams != null) {
            Iterator<String> iterator = headersParams.keySet().iterator();
            String key = null;
            while (iterator.hasNext()) {
                key = iterator.next();
                headersbuilder.add(key, headersParams.get(key));
            }
        }
        Headers headers=headersbuilder.build();
        return headers;
    }

}
