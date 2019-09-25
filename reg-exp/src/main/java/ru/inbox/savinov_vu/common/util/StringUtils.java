package ru.inbox.savinov_vu.common.util;

import java.util.Objects;



public class StringUtils {

    public static boolean isNullOrEmpty(String s) {
        return Objects.isNull(s) || "null".equals(s) || s.isEmpty();
    }


    public static boolean nonNullOrEmpty(String s) {
        return !isNullOrEmpty(s);
    }


    public static String getEmptyIfNull(String s) {
        if (isNullOrEmpty(s)) {
            return "";
        }
        return s;
    }


    public static Integer convertStringIdToIntegerId(String s) {
        if (isNullOrEmpty(s)) {
            return null;
        } else {
            return Integer.valueOf(s);
        }
    }

}
