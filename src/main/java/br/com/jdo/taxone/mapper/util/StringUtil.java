package br.com.jdo.taxone.mapper.util;

public class StringUtil {

    public static String putPercent(String name) {
        if (name != null) {
            return "%" + name + "%";
        }
        return null;
    }

}
