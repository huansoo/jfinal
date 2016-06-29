package com.jfsnpm.jfsnpm.core.util;

/**
 * Created by yangchuanhuan on 16/6/1.
 */
public class StringUtils {

    public static boolean isNullString(String str){
        if(null == str || "".equals(str)){
            return true;
        }
        if("".equals(str.trim()) || "NULL".equals(str.trim())){
            return true;
        }
        return false;
    }
}
