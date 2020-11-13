package com.fireman.yang.auth.core.common;

import com.fireman.yang.auth.core.common.constants.AuthConstants;
import com.fireman.yang.auth.core.web.utils.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tongdong
 * @Date: 2020/11/9
 * @Description:
 */
public class ThreadContext {

    private static final Logger log = LoggerFactory.getLogger(ThreadContext.class);

    private static final ThreadLocal<Map<Object, Object>> resources = new ThreadContext.InheritableThreadLocalMap();

    private static final class InheritableThreadLocalMap<T extends Map<Object, Object>> extends InheritableThreadLocal<Map<Object, Object>> {
        private InheritableThreadLocalMap() {
        }

        @Override
        protected Map<Object, Object> childValue(Map<Object, Object> parentValue) {
            return parentValue != null ? (Map)((HashMap)parentValue).clone() : null;
        }
    }

    protected static void setResources(Map<Object, Object> newResources) {
        if (!CollectionUtils.isEmpty(newResources)) {
            ensureResourcesInitialized();
            resources.get().clear();
            resources.get().putAll(newResources);
        }
    }

    private static void ensureResourcesInitialized() {
        if (resources.get() == null) {
            resources.set(new HashMap(8));
        }
    }

    public static Object get(Object key) {
        Object value = getValue(key);
        return value;
    }

    public static void put(Object key, Object value) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        } else if (value == null) {
            remove(key);
        } else {
            ensureResourcesInitialized();
            resources.get().put(key, value);
            if (log.isTraceEnabled()) {
                log.trace("ThreadContext set info: {}, {}", key , value);
            }
        }
    }

    private static Object getValue(Object key) {
        Map<Object, Object> perThreadResources = resources.get();
        return perThreadResources != null ? perThreadResources.get(key) : null;
    }

    public static Object remove(Object key) {
        Map<Object, Object> perThreadResources = resources.get();
        Object value = perThreadResources != null ? perThreadResources.remove(key) : null;
        return value;
    }

    public static void remove() {
        resources.remove();
    }


    public static HttpServletResponse getResponse(){
        Object object = ThreadContext.get(AuthConstants.AUTH_HTTP_RESPONSE);
        if(object != null && object instanceof HttpServletResponse) {
            HttpServletResponse response = (HttpServletResponse) object;
            return response;
        }
        return null;
    }

    public static HttpServletRequest getRequest(){
        Object object = ThreadContext.get(AuthConstants.AUTH_HTTP_REQUEST);
        if(object != null && object instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) object;
            return request;
        }
        return null;
    }
}
