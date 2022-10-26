package com.yongjie.ZhiJianSbpt.module.util;

import com.yongjie.ZhiJianSbpt.utilities.JSON;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class R extends HashMap<String, Object> {

    /**
     * 状态码 : 用于区别错误信息类型
     * 默认0          成功,正常状态
     */
    public static final String CODE_NAME = "code";      // 状态码
    /**
     * 提示信息
     * 例如 nui.alert(map.msg,"提示!");
     */
    public static final String MSG_NAME = "msg";      // 提示信息
    /**
     * 异常数据信息 : 用于程序报错,可以通过前台打印错误信息
     * 用法 :前台打印  console.log(map.mess);
     */
    public static final String MESS_NAME = "mess";      // 异常数据
    /**
     * 返回数据封装
     * 前台获取数据
     * map.data
     */
    public static final String DATA_NAME = "data";      // 返回数据
    /**
     * 分页查询中分页条数
     */
    public static final String TOTAL_NAME = "total";          // 分页条数
    private static final long serialVersionUID = -6323563551292332813L;


    public R() {
        super();
    }

    public R(String mess, String msg) {
        this(msg, 1);
        put(MESS_NAME, mess);

    }

    public R(String msg, Integer code) {
        put(MSG_NAME, msg);
        put(CODE_NAME, code);
    }

    public R(Object obj) {
        this("查询成功", 0);
        put(DATA_NAME, obj);
    }

    /**
     * 分页查询
     *
     * @param obj
     * @return
     */
    public static String page(Object obj) {
        if (StringUtil.isEmpty(obj)) {
            return pageNull();
        }
        return JSON.Encode(obj);
    }

    @SuppressWarnings("rawtypes")
    public static String pageNull() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put(TOTAL_NAME, 0);
        resultMap.put(DATA_NAME, new ArrayList());
        return JSON.Encode(resultMap);
    }

    /**
     * 查询成功
     *
     * @return
     */
    public static String ok(Object obj) {
        R r = new R(obj);
        // 如果传入对象为空,则修改提示信息
        if (StringUtil.isEmpty(obj)) {
            r.put(MSG_NAME, "没有查询到相关数据!");
            r.put(CODE_NAME, 1);
        }
        return JSON.Encode(r);
    }

    /**
     * 返回list数据格式
     *
     * @param list
     * @return
     */
    public static String list(Object list) {
        return JSON.Encode(list);
    }

    /**
     * 返回R格式
     */
    public static R r(Object obj) {
        R r = new R(obj);
        // 如果传入对象为空,则修改提示信息
        if (StringUtil.isEmpty(obj)) {
            r.put(MSG_NAME, "没有查询到相关数据!");
            r.put(CODE_NAME, 1);
        }
        return r;
    }

    /**
     * 保存成功||更新成功
     *
     * @param msg
     * @return
     */
    public static String ok(String msg) {
        R r = new R(msg, 0);
        return JSON.Encode(r);
    }

    /**
     * 保存成功||更新成功
     *
     * @return
     */
    public static String ok() {
        return ok("保存成功!");
    }

    /**
     *
     */
    public static String error() {
        return error("保存失败!");
    }

    public static String error(String msg) {
        R r = new R(msg, 1);
        return JSON.Encode(r);
    }

    public static String error(String mess, String msg) {
        R r = new R(mess, StringUtil.isEmpty(msg) ? "异常!" : msg);
        return JSON.Encode(r);
    }


    public static void main(String[] args) {
        String ok = R.ok();
        System.out.println(ok);

        String ok2 = R.ok("保存成功!--更新成功!");
        System.out.println(ok2);

        String ok3 = R.ok(2);
        System.out.println(ok3);


        String error = R.error();
        System.out.println(error);

        String error2 = R.error("xxx");
        System.out.println(error2);

        String error3 = R.error("mess", null);
        System.out.println(error3);
    }
}
