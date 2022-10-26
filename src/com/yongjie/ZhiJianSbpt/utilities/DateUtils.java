package com.yongjie.ZhiJianSbpt.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期格式转换
 *
 * @author zws
 * @Date 2020年4月14日 星期二
 */
public class DateUtils {
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERNS = "yyyy-MM-dd";
    /**
     * 时间格式(yyyy-M-d)
     */
    public final static String DATE_PATTERN = "yyyy-M-d";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /* 时间格式 yyyy */
    public final static String DATE_TIME_YEAR = "yyyy";
    /* 时间格式 MM 04 */
    public final static String DATE_TIME_MONTHS = "MM";
    /* 时间格式 MM 4 */
    public final static String DATE_TIME_MONTH = "M";
    /* 时间格式 day 04 */
    public final static String DATE_TIME_DAYS = "dd";
    /* 时间格式 day 4 */
    public final static String DATE_TIME_DAY = "d";

    /**
     * 返回年
     *
     * @param date
     */
    public static String formatYear(Date date) {
        return format(date, DATE_TIME_YEAR);
    }

    /**
     * 返回年 integer
     */
    public static Integer formatYear() {
        String yearStr = formatYear(new Date());
        return Integer.parseInt(yearStr);
    }

    /**
     * 返回月
     *
     * @param date
     */
    public static String formatMonth(Date date) {
        return formatMonth(date, false);
    }

    /**
     * 返回月
     *
     * @param date
     */
    public static Integer formatMonth() {
        String monthStr = formatMonth(new Date());
        return Integer.parseInt(monthStr);
    }

    /**
     * 获取季度
     *
     * @return
     */
    public static Integer getQuarter() {
        Integer month = formatMonth() - 1;
        int key = month / 3;
        switch (key) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
        }
        return 0;
    }

    public static String formatMonth(Date date, Boolean b) {
        if (b) {
            return format(date, DATE_TIME_MONTHS);
        } else {
            return format(date, DATE_TIME_MONTH);
        }
    }

    /**
     * 返回日
     *
     * @param date
     */
    public static String formatDay(Date date) {
        return formatDay(date, false);
    }

    public static String formatDay(Date date, Boolean b) {
        if (b) {
            return format(date, DATE_TIME_DAYS);
        } else {
            return format(date, DATE_TIME_DAY);
        }
    }

    /**
     * 默认 yyyy-MM-dd
     *
     * @param date
     */
    public static String format(Date date) {
        return format(date, false);
    }

    /**
     * yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String format(String date) {
        return format(date, null);
    }

    /**
     * 自定义格式
     *
     * @param date
     * @param sdf
     * @return
     */
    public static String ApiFormat(Date date) {
        if (StringUtil.isNullOrEmpty(date)) {
            return null;
        }
        return format(date, DATE_TIME_PATTERN);
    }

    public static void main(String[] args) {
        String apiFormat = ApiFormat(new Date());
        System.out.println(apiFormat);
    }

    public static String format(String date, String sdf) {
        if (StringUtil.isNullOrEmpty(date)) {
            return null;
        }
        return format(parse(date, sdf));
    }

    public static String format() {
        return format(new Date());
    }

    /**
     * @param date
     * @param b
     * @return
     */
    public static String format(Date date, Boolean b) {
        if (b) {
            return format(date, DATE_PATTERN);
        } else {
            return format(date, DATE_PATTERNS);
        }
    }

    public static Date parse(String date) {
        return parse(date, null);
    }

    public static Date parse(String date, String sdf) {
        if (StringUtil.isNullOrEmpty(date)) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat(!StringUtil.isNullOrEmpty(sdf) ? sdf : DATE_PATTERNS);
        try {
            return df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据自定义日期返回对应的格式
     *
     * @param date    日期
     * @param pattern 要转换的格式
     * @return 返回要转换的格式
     */
    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }
}