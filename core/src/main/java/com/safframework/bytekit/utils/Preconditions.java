package com.safframework.bytekit.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Tony Shen on 15/11/12.
 */
public class Preconditions {

    /**
     *  可以判断任何一个对象是否为空,包括List Map String 复杂对象等等,
     *  只能判断对象,而不能判断基本数据类型
     *  Preconditions.isBlank("")  true
     *  Preconditions.isBlank(" ")  true
     *  Preconditions.isBlank(null)  true
     *  Preconditions.isBlank("null")  false
     * @param t
     * @param <T>
     * @return
     */
    public static <T> boolean isBlank(T t) {

        if (t==null) {
            return true;
        }


        if (t instanceof List) {
            if (((List) t).size()==0) {
                return true;
            }
        } else if (t instanceof Map) {
            if (((Map) t).size()==0) {
                return true;
            }
        } else if (t instanceof Set) {
            if (((Set) t).size()==0) {
                return true;
            }
        } else if (t instanceof Object []) {
            if (((Object[]) t).length==0) {
                return true;
            }
        } else if (t instanceof String) {

            String str = (String)t;
            if (str.length()==0) return true;

            str = str.trim();
            if (str.length()==0) return true;
        }

        return false;
    }

    public static <T> boolean isNotBlank(T t) {
        return !isBlank(t);
    }

    public static boolean isNotBlanks(Object... objects) {

        if (objects==null) {
            return false;
        }

        for (Object obj:objects) {
            if (isBlank(obj)) {
                return false;
            }
        }

        return true;
    }
}