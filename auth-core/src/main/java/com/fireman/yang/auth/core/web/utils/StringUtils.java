package com.fireman.yang.auth.core.web.utils;

import java.util.*;

/**
 * @author tongdong
 * @Date: 2020/7/1
 * @Description:
 */
public class StringUtils {

    public static boolean hasText(String str) {
        if (!hasLength(str)) {
            return false;
        } else {
            int strLen = str.length();

            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return true;
                }
            }

            return false;
        }
    }

    public static boolean hasLength(String str) {
        return str != null && str.length() > 0;
    }

    public static boolean startsWithIgnoreCase(String str, String prefix) {
        if (str != null && prefix != null) {
            if (str.startsWith(prefix)) {
                return true;
            } else if (str.length() < prefix.length()) {
                return false;
            } else {
                String lcStr = str.substring(0, prefix.length()).toLowerCase();
                String lcPrefix = prefix.toLowerCase();
                return lcStr.equals(lcPrefix);
            }
        } else {
            return false;
        }
    }

    public static String[] tokenizeToStringArray(String str, String delimiters) {
        return tokenizeToStringArray(str, delimiters, true, true);
    }
    public static String[] tokenizeToStringArray(String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens) {
        if (str == null) {
            return null;
        } else {
            StringTokenizer st = new StringTokenizer(str, delimiters);
            ArrayList tokens = new ArrayList();

            while(true) {
                String token;
                do {
                    if (!st.hasMoreTokens()) {
                        return toStringArray(tokens);
                    }

                    token = st.nextToken();
                    if (trimTokens) {
                        token = token.trim();
                    }
                } while(ignoreEmptyTokens && token.length() <= 0);

                tokens.add(token);
            }
        }
    }

    public static String[] toStringArray(Collection collection) {
        return collection == null ? null : (String[])((String[])collection.toArray(new String[collection.size()]));
    }

    public static boolean isNotBlank(String key) {
        return !isBlank(key);
    }

    public static boolean isBlank(String key) {
        return key == null || "".equals(key.trim());
    }


    public static String collectionToCommaDelimitedString(Collection<?> coll) {
        return collectionToDelimitedString(coll, ",");
    }

    public static String collectionToDelimitedString(Collection<?> coll, String delim) {
        return collectionToDelimitedString(coll, delim, "", "");
    }

    public static String collectionToDelimitedString( Collection<?> coll, String delim, String prefix, String suffix) {
        if (coll != null && !coll.isEmpty()) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            Iterator it = coll.iterator();

            while(it.hasNext()) {
                sb.append(prefix).append(it.next()).append(suffix);
                if (it.hasNext()) {
                    sb.append(delim);
                }
            }

            return sb.toString();
        }
    }

    public static String toCommaDelimitedString(List<String> strings) {
        StringBuilder builder = new StringBuilder();
        for (Iterator<String> it = strings.iterator(); it.hasNext(); ) {
            String val = it.next();
            builder.append(val);
            if (it.hasNext()) {
                builder.append(", ");
            }
        }
        return builder.toString();
    }

    public static List<String> getValuesAsList(String string) {
        List<String> values = new ArrayList<>();
        values.add(string);
        if (values != null) {
            List<String> result = new ArrayList<>();
            for (String value : values) {
                if (value != null) {
                    String[] tokens = StringUtils.tokenizeToStringArray(value, ",");
                    for (String token : tokens) {
                        result.add(token);
                    }
                }
            }
            return result;
        }
        return Collections.emptyList();
    }
}
