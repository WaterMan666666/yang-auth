package com.fireman.yang.auth.core.web.utils;

import java.util.Collection;
import java.util.Map;

/**
 * @author tongdong
 * @Date: 2020/11/10
 * @Description:
 */
public class CollectionUtils {

    public static boolean isEmpty(Collection c) {
        return c == null || c.isEmpty();
    }

    public static boolean isEmpty(Map m) {
        return m == null || m.isEmpty();
    }

    public static int size(Collection c) {
        return c != null ? c.size() : 0;
    }

    public static int size(Map m) {
        return m != null ? m.size() : 0;
    }
}
