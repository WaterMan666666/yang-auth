package com.fireman.yang.auth.core.web.utils.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName JsonUtils
 * @Author TD
 * @Date 2019/1/10 10:12
 * @Description Json处理工具
 */
public class JsonUtils {

    public static ObjectMapper objectMapper ;
    public static ObjectMapper objectMapperSensitivity ;


    static {
        //Mapper创建
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class,new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addSerializer(LocalDate.class,new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addDeserializer(LocalDateTime.class,new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addDeserializer(LocalDate.class,new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        objectMapper.registerModule(javaTimeModule);
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    }


    /**
     * 对象转Json格式字符串
     * @return
     */
    public static String toJsonString(Object pojo){

        try {
            return objectMapper.writeValueAsString(pojo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json格式字符串转对象
     * @param json
     * @param c
     * @return
     */
    public static<T> T jsonToObject(String json,Class<T> c){

        T t = null;
        try {
            t = objectMapper.readValue(json, c);
        } catch (IOException e){
            throw new IllegalArgumentException("参数格式有误");
        }
        return t;
    }

    /**
     * json格式字符串转对象
     * @param json
     * @param c
     * @return
     */
    public static<T> List<T> jsonToArray(String json,Class<T> c){
        JavaType javaType = objectMapper.getTypeFactory()
                .constructParametricType(ArrayList.class, c);
        List<T> t = null;
        try {
            t = objectMapper.readValue(json, javaType);
        } catch (IOException e){
            throw new IllegalArgumentException("参数格式有误");
        }
        return t;
    }



}
