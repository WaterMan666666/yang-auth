package com.fireman.yang.auth.core.web.utils.https;


/**
 * @ClassName HttpEntity
 * @Author TD
 * @Date 2019/1/23 14:01
 * @Description Http传输对象
 */
public class HttpEntity {

    /** Http返回码 */
    private int code;
    /** Http返回正文 */
    private String content;


    public HttpEntity(int code, String content) {
        this.code = code;
        this.content = content;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
