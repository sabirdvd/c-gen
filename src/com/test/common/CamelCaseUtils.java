package com.test.common;


/**
 * Created by shuisheng on 2018/2/5.
 *
 * 以下方法只能简单用，很多bug
 */
public class CamelCaseUtils {

    private static final char SEPARATOR = '_';

    /**
     * 骆驼格式  -> 下划线格式
     *
     * @param s
     * @return
     */
    public static String toUngerScoreCase(String s) {
        if (isBlank(s)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();

        boolean upperCase = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            boolean nextUperCase = true;
            if (i < (s.length() - 1)) {
                nextUperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if ((i > 0) && Character.isUpperCase(c)) {

                if (!upperCase || !nextUperCase) {
                    sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }

    /**
     * 下划线格式 -> 骆驼格式
     *
     * @param s
     * @return
     */
    public static String toCamelCase(String s) {
        if (isBlank(s)) {
            return null;
        }

        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 下划线命名字符串格式转换为骆驼法则格式 首字母大写
     *
     * @param s
     * @return
     */
    public static String toCamelAndFirstLetterCase(String s) {
        if (isBlank(s)) {
            return null;
        }
        s = toCamelCase(s);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    public static boolean isBlank(String s) {
        if (s == null || s.trim().length() == 0) {
            return true;
        }

        return false;
    }


    public static void main(String[] args) {
        String s1 = "add_big_thing";
        System.out.println(toCamelCase(s1));
        System.out.println(toCamelAndFirstLetterCase(s1));
        System.out.println(toUngerScoreCase(s1));

        System.out.println("----------------");
        String s2 = "addBigThing";
        System.out.println(toCamelCase(s2));
        System.out.println(toUngerScoreCase(s2));
        System.out.println(toCamelAndFirstLetterCase(s2));
    }
}
