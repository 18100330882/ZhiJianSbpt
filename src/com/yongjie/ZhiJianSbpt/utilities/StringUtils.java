package com.yongjie.ZhiJianSbpt.utilities;

import java.util.*;

public class StringUtils {
    public static boolean isNullOrEmpty(Object obj) {
        return obj == null || "".equals(obj.toString());
    }

    public static boolean isNullOrEmptyZf(Object obj) {
        return obj == null || "".equals(obj.toString()) || "null".equals(obj.toString());
    }

    public static String toString(Object obj) {
        if (obj == null) return "null";
        return obj.toString();
    }

    public static String join(Collection s, String delimiter) {
        StringBuffer buffer = new StringBuffer();
        Iterator iter = s.iterator();
        while (iter.hasNext()) {
            buffer.append(iter.next());
            if (iter.hasNext()) {
                buffer.append(delimiter);
            }
        }
        return buffer.toString();
    }

    //将首字母变成小写
    public static String changeFirstCharToLowercase(String name) {
        String result = name.substring(3);
        result = result.substring(0, 1).replace(result.charAt(0), (char) (result.charAt(0) + 32)) + result.substring(1);
        return result;
    }

    //全都大写
    public static String changeFirstCharToLowercase2(String name) {
        String result = name.substring(3);
        result = result.substring(0, 1).replace(result.charAt(0), (char) (result.charAt(0) + 32)) + result.substring(1);
        return result.toUpperCase();
    }

    /**
     * 将传递进来的字符串中的小写字母转换为大写形式
     * 并将其中的全角字符转换为半角形式
     *
     * @param str 要转换的字符
     * @return 转换后的字符
     * @author cyk
     */
    public static String getUppercaseAndHalfwidthStr(String str) {
        if (isNullOrEmpty(str)) return str;

        String newStr;    // 最终返回的字符串
        //首先将字符串转换为大写形式；
        newStr = str.toUpperCase();

        //建立全角字符与半角字符的映射
        Map<String, String> map = new HashMap<String, String>();
        //存储全角与半角的映射,如有新的可要替换的字符，直接加到此处
        map.put("，", ",");
        map.put("。", ".");
        map.put("‘", "'");
        map.put("“", "\"");
        map.put("；", ";	");
        map.put("：", ":");
        map.put("！", "!");
        map.put("￥", "$");
        map.put("？", "?");
        map.put("【", "[");
        map.put("】", "]");
        map.put("（", "(");
        map.put("）", ")");

        //遍历全角字符，如果字符串中存在全角字符，使用对应的半角字符替换
        Set<String> keySet = map.keySet();
        for (String fullWidth : keySet) {
            //如果字符串中包含当前的全角符号，替换
            if (newStr.contains(fullWidth)) newStr.replace(fullWidth, map.get(fullWidth));
        }
        return newStr;
    }

    /**
     * 将字符串的编码格式转换为utf-8
     *
     * @param str
     * @return Name = new
     */
    public static String toUTF8(String str) {
        if (isNullOrEmpty(str)) {
            return "";
        }
        try {
            str = java.net.URLEncoder.encode(str, "utf-8");
            return str;
        } catch (Exception exception1) {
        }
        return str;
    }

    /**
     * 判断对象是否为空
     * 如果是空返回 True
     * 否则返回     False
     */
    public static Boolean isEmpty(Object obj) {
        if (obj == null || "null".equals(obj)) return true;
        String str = obj.toString().trim();
        return "".equals(str);
    }

    /**
     * 替换字符串中可能造成SQL注入的字符，
     *
     * @param oldStr
     * @param useEmpty true: 使用空串替换    false：不使用空串而是使用中文对应字符替换
     * @return 原字符替换过风险字符的副本
     */
    public static String replaceSQLInjectChar(String oldStr, boolean useEmpty) {
        if (isNullOrEmptyZf(oldStr)) return oldStr;

        String result;
        // 将风险字符替换为空
        if (useEmpty) {
            result = oldStr.replace("-", "").replace(";", "").replace("'", "").replace("\\", "");
        } else {    // 使用中文对应字符替换
            result = oldStr.replace("-", "_");
            result = result.replace(";", "；");
            result = result.replace("'", "‘");
            result = result.replace("\\", "/");
        }

        return result;
    }

    /**
     * 小写 数字转大写
     * 仅对 100 以内适用
     * num : 要转换的数字  例如  23 > 二十三
     */
    public static String getNum(Integer num) throws Exception {
        String str = "";
        if (num != null) {
            String strNum = num.toString();
            int numLen = strNum.length();
            if (numLen == 1 || num == 10) {
                str = intNumToStrNum(num);
            } else if (num > 10 && num < 20) {
                str = "十";
                char charAt = strNum.charAt(1);
                str += intNumToStrNum(Integer.parseInt(String.valueOf(charAt)));
            } else {
                for (int i = 0; i < numLen; i++) {
                    char charAt = strNum.charAt(i);
                    int intNum = Integer.parseInt(String.valueOf(charAt));
                    if (intNum != 0 && i != 0) {
                        str += "十";
                    }
                    str += intNumToStrNum(intNum);
                }
            }
        }
        return str;
    }

    /**
     * 大写数字数组
     */
    public static String intNumToStrNum(int num) {
        String[] str = {"十", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十"};
        return str[num];
    }
}

