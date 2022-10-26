package com.yongjie.ZhiJianSbpt.utilities;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class HashmapAndEntityTransfer {

    /**
     * 将对象转成hashmap形式。
     *
     * @param object
     * @return hashmap
     */
    public static HashMap entityTransferToHashmap(Object object) {
        HashMap record = new HashMap<>();
        Class<? extends Object> class1 = object.getClass();// 得到实际类
        Field[] fields = class1.getDeclaredFields();// 获取除了继承之外的字段
        for (int i = 0, size = fields.length; i < size; i++) {
            fields[i].setAccessible(true);// 设置可访问
            Object value;
            try {
                value = fields[i].get(object);
                // System.out.println(fields[i].getName()+" "+value);
                record.put(fields[i].getName(), value);
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return record;
    }

    /**
     * 将对象转成hashmap形式。
     *
     * @param object
     * @return hashmap
     */
    public static HashMap entityTransferToHashmap2(Object object) {
        HashMap record = new HashMap<>();
        Class<? extends Object> class1 = object.getClass();// 得到实际类
        Field[] fields = class1.getDeclaredFields();// 获取除了继承之外的字段
        for (int i = 0, size = fields.length; i < size; i++) {
            fields[i].setAccessible(true);// 设置可访问
            Object value;
            try {
                value = fields[i].get(object);
                // System.out.println(fields[i].getName()+" "+value);
                record.put(fields[i].getName().toUpperCase(), value);
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return record;
    }

    /**
     * 将hashMap数据赋值到对象中，并返回对象
     *
     * @param object
     * @return 返回赋值后的对象。
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public static Object hashmapTransferToEntity(Object object, HashMap row)
            throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        @SuppressWarnings("rawtypes")
        Class class1 = object.getClass();
        Method[] methods = class1.getDeclaredMethods();
        for (int i = 0, size = methods.length; i < size; i++) {
            // 那么只选择setter方法。
            if (methods[i].getName().startsWith("set")) {
                String name1 = methods[i].getName();
                Class class2 = methods[i].getParameterTypes()[0];// set方法只有一个参数，所以只获取第一个参数
                // 获取set方法对应的字段名称。

                name1 = StringUtil.changeFirstCharToLowercase(name1);
                if (class2.getSimpleName().contains("long") || class2.getSimpleName().contains("Long")) {
                    // 进行判空操作
                    if (!StringUtil.isNullOrEmpty(row.get(name1))) {
                        methods[i].invoke(object, Long.parseLong(row.get(name1).toString()));
                    }
                } else if (class2.getSimpleName().contains("Double") || class2.getSimpleName().contains("double")) {
                    // 进行判空操作
                    if (!StringUtil.isNullOrEmpty(row.get(name1))) {
                        methods[i].invoke(object, Double.parseDouble(row.get(name1).toString()));
                    }
                } else if (class2.getSimpleName().contains("String")) {
                    if (!StringUtil.isNullOrEmpty(row.get(name1))) {
                        methods[i].invoke(object, row.get(name1).toString());
                    }
                } else if (class2.getSimpleName().contains("Date")) {// model层需要是java.sql.Date
                    if (!StringUtil.isNullOrEmpty(row.get(name1))) {
                        if (row.get(name1).toString().indexOf("CST") > 0) {
                            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
                            Date date;
                            try {
                                date = sdf.parse(row.get(name1).toString());
                                java.sql.Date date2 = new java.sql.Date(date.getTime());
                                methods[i].invoke(object, date2);
                            } catch (ParseException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        } else if (row.get(name1).toString().indexOf("GMT+08:00") > 0) {//如果是格林威治时间。
                            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM ddHH:mm:ss 'GMT+08:00' yyyy", Locale.US);
                            Date date;
                            try {
                                date = sdf.parse(row.get(name1).toString());
                                java.sql.Date date2 = new java.sql.Date(date.getTime());
                                methods[i].invoke(object, date2);
                            } catch (ParseException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                        /*
                         * 将字符串转换为日期格式
                         */
                        else if ((row.get(name1).toString().indexOf("GMT") > 0) && (row.get(name1).toString().indexOf("GMT+") == -1)) {
                            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM ddHH:mm:ss 'GMT' yyyy", Locale.US);
                            Date date;
                            try {
                                date = sdf.parse(row.get(name1).toString());
                                java.sql.Date date2 = new java.sql.Date(date.getTime());
                                methods[i].invoke(object, date2);
                            } catch (ParseException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        } else if (row.get(name1).toString().indexOf(":") > 0) {//如果是utc，即本地时间
                            SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            try {
                                Date d = ss.parse(row.get(name1).toString());
                                methods[i].invoke(object, new java.sql.Date(d.getTime()));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        } else {
                            SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd");
                            try {
                                Date d = ss.parse(row.get(name1).toString());
                                methods[i].invoke(object, new java.sql.Date(d.getTime()));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else if (class2.getSimpleName().contains("int") || class2.getSimpleName().contains("Integer")) {// Integer和int
                    if (!StringUtil.isNullOrEmpty(row.get(name1))) {
                        methods[i].invoke(object, Integer.parseInt(row.get(name1).toString()));
                    }
                } else if (class2.getSimpleName().contains("float") || class2.getSimpleName().contains("Float")) {// Float和float
                    if (!StringUtil.isNullOrEmpty(row.get(name1))) {
                        methods[i].invoke(object, Float.parseFloat(row.get(name1).toString()));
                    }
                } else if (class2.getSimpleName().contains("byte") || class2.getSimpleName().contains("Byte")) {// Byte和byte
                    if (class2.getSimpleName().equals("byte[]") && (!StringUtil.isNullOrEmpty(row.get(name1)))) {//二进制流文件
                        methods[i].invoke(object, ObjectUtil.objectToByteArray(row.get(name1)));
                    } else if (!StringUtil.isNullOrEmpty(row.get(name1))) {
                        methods[i].invoke(object, Byte.parseByte(row.get(name1).toString()));
                    } else {
                    }
                } else if (class2.getSimpleName().contains("short") || class2.getSimpleName().contains("Short")) {
                    if (!StringUtil.isNullOrEmpty(row.get(name1))) {
                        methods[i].invoke(object, Short.parseShort(row.get(name1).toString()));
                    }
                } else if (class2.getSimpleName().contains("BigDecimal")) {// BigDecimal类型
                    if (!StringUtil.isNullOrEmpty(row.get(name1))) {
                        methods[i].invoke(object, BigDecimal.valueOf(Double.parseDouble(row.get(name1).toString())));
                    }
                } else if (class2.getSimpleName().contains("boolean") || class2.getSimpleName().contains("Boolean")) {//布尔类型
                    if (!StringUtil.isNullOrEmpty(row.get(name1))) {
                        methods[i].invoke(object, Boolean.parseBoolean(row.get(name1).toString()));
                    }
                } else {
                    methods[i].invoke(object, row.get(name1) == null ? "" : row.get(name1).toString());
                }
            } else
                continue;
        }
        return object;
    }

    /**
     * 将hashMap数据赋值到对象中，并返回对象
     *
     * @param object
     * @return 返回赋值后的对象。
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public static Object hashmapTransferToEntity2(Object object, HashMap row)
            throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        @SuppressWarnings("rawtypes")
        Class class1 = object.getClass();
        Method[] methods = class1.getDeclaredMethods();
        for (int i = 0, size = methods.length; i < size; i++) {
            // 那么只选择setter方法。
            if (methods[i].getName().startsWith("set")) {
                String name1 = methods[i].getName();
                Class class2 = methods[i].getParameterTypes()[0];// set方法只有一个参数，所以只获取第一个参数
                // 获取set方法对应的字段名称。

                name1 = StringUtil.changeFirstCharToLowercase2(name1);
                if (class2.getSimpleName().contains("long") || class2.getSimpleName().contains("Long")) {
                    // 进行判空操作
                    if (!StringUtil.isNullOrEmpty(row.get(name1))) {
                        methods[i].invoke(object, Long.parseLong(row.get(name1).toString()));
                    }
                } else if (class2.getSimpleName().contains("Double") || class2.getSimpleName().contains("double")) {
                    // 进行判空操作
                    if (!StringUtil.isNullOrEmpty(row.get(name1))) {
                        methods[i].invoke(object, Double.parseDouble(row.get(name1).toString()));
                    }
                } else if (class2.getSimpleName().contains("String")) {
                    if (!StringUtil.isNullOrEmpty(row.get(name1))) {
                        methods[i].invoke(object, row.get(name1).toString());
                    }
                } else if (class2.getSimpleName().contains("Date")) {// model层需要是java.sql.Date
                    if (!StringUtil.isNullOrEmpty(row.get(name1))) {
                        if (row.get(name1).toString().indexOf("CST") > 0) {
                            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
                            Date date;
                            try {
                                date = sdf.parse(row.get(name1).toString());
                                java.sql.Date date2 = new java.sql.Date(date.getTime());
                                methods[i].invoke(object, date2);
                            } catch (ParseException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        } else if (row.get(name1).toString().indexOf("GMT+08:00") > 0) {//如果是格林威治时间。
                            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM ddHH:mm:ss 'GMT+08:00' yyyy", Locale.US);
                            Date date;
                            try {
                                date = sdf.parse(row.get(name1).toString());
                                java.sql.Date date2 = new java.sql.Date(date.getTime());
                                methods[i].invoke(object, date2);
                            } catch (ParseException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                        /*
                         * 将字符串转换为日期格式
                         */
                        else if ((row.get(name1).toString().indexOf("GMT") > 0) && (row.get(name1).toString().indexOf("GMT+") == -1)) {
                            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM ddHH:mm:ss 'GMT' yyyy", Locale.US);
                            Date date;
                            try {
                                date = sdf.parse(row.get(name1).toString());
                                java.sql.Date date2 = new java.sql.Date(date.getTime());
                                methods[i].invoke(object, date2);
                            } catch (ParseException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        } else if (row.get(name1).toString().indexOf(":") > 0) {//如果是utc，即本地时间
                            SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            try {
                                Date d = ss.parse(row.get(name1).toString());
                                methods[i].invoke(object, new java.sql.Date(d.getTime()));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        } else {
                            SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd");
                            try {
                                Date d = ss.parse(row.get(name1).toString());
                                methods[i].invoke(object, new java.sql.Date(d.getTime()));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else if (class2.getSimpleName().contains("int") || class2.getSimpleName().contains("Integer")) {// Integer和int
                    if (!StringUtil.isNullOrEmpty(row.get(name1))) {
                        methods[i].invoke(object, Integer.parseInt(row.get(name1).toString()));
                    }
                } else if (class2.getSimpleName().contains("float") || class2.getSimpleName().contains("Float")) {// Float和float
                    if (!StringUtil.isNullOrEmpty(row.get(name1))) {
                        methods[i].invoke(object, Float.parseFloat(row.get(name1).toString()));
                    }
                } else if (class2.getSimpleName().contains("byte") || class2.getSimpleName().contains("Byte")) {// Byte和byte
                    if (class2.getSimpleName().equals("byte[]") && (!StringUtil.isNullOrEmpty(row.get(name1)))) {//二进制流文件
                        methods[i].invoke(object, ObjectUtil.objectToByteArray(row.get(name1)));
                    } else if (!StringUtil.isNullOrEmpty(row.get(name1))) {
                        methods[i].invoke(object, Byte.parseByte(row.get(name1).toString()));
                    } else {
                    }
                } else if (class2.getSimpleName().contains("short") || class2.getSimpleName().contains("Short")) {
                    if (!StringUtil.isNullOrEmpty(row.get(name1))) {
                        methods[i].invoke(object, Short.parseShort(row.get(name1).toString()));
                    }
                } else if (class2.getSimpleName().contains("BigDecimal")) {// BigDecimal类型
                    if (!StringUtil.isNullOrEmpty(row.get(name1))) {
                        methods[i].invoke(object, BigDecimal.valueOf(Double.parseDouble(row.get(name1).toString())));
                    }
                } else if (class2.getSimpleName().contains("boolean") || class2.getSimpleName().contains("Boolean")) {//布尔类型
                    if (!StringUtil.isNullOrEmpty(row.get(name1))) {
                        methods[i].invoke(object, Boolean.parseBoolean(row.get(name1).toString()));
                    }
                } else {
                    methods[i].invoke(object, row.get(name1) == null ? "" : row.get(name1).toString());
                }
            } else
                continue;
        }
        return object;
    }
}
