package com.yongjie.ZhiJianSbpt.utilities;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class R extends HashMap<String, Object> {

    /**
     * 状态码 : 用于区别错误信息类型
     * 默认0          成功,正常状态
     * 500 	异常
     * 1001 登录超时
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
     * 状态 :用于区别程序执行成功还是异常
     * true : 成功
     * false: 异常 || 错误
     */
    public static final String STATE_NAME = "state";  // 定义状态
    public static final int CODE_OK = 0;    // 正常
    public static final int CODE_LOG = 1001; // 登录超时
    public static final int CODE_ERROR = 500;// 异常
    public static final Boolean STATE_TRUE = Boolean.TRUE;    // 定义状态 true 查询没有异常,返回
    public static final Boolean STATE_FALSE = Boolean.FALSE; // 异常数据返回
    public static final String TOTAL_NAME = "total";
    private static final long serialVersionUID = 1L;

    public R() {
        this("");
    }

    public R(String msg) {
        this(msg, CODE_OK);
    }

    public R(String msg, int code) {
        this(msg, null, code);
    }

    public R(String msg, Object data, int code) {
        put(MSG_NAME, msg);
        put(DATA_NAME, data);
        put(CODE_NAME, code);
    }

    // 正常
    // > {"msg":"","data":"",code:0,state:true}
    public static String ok() {
        return ok("");
    }

    // 正常 > {"msg":msg,"data":"",code:0,state:true}
    public static String ok(String msg) {
        return ok("", msg);
    }

    // 正常 > {"msg":"","data":data,code:0,state:true}
    public static String ok(Object data) {
        return ok(data, "");
    }

    // 正常 > {"msg":msg,"data":data,code:0,state:true}
    public static String ok(Object data, String msg) {
        R r = new R(msg, data, CODE_OK).put(STATE_NAME, STATE_TRUE);
        return JSONArray.toJSONStringWithDateFormat(r, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat);
    }

    // 异常信息封装  {"msg":"","data":"",code:500,state:false,mess:""}
    public static String error() {
        return error("");
    }

    // 异常信息封装 {"msg":msg,"data":"",code:500,state:false,mess:""}
    public static String error(String msg) {
        return error(msg, null);
    }

    // 异常信息封装  {"msg":msg,"data":"",code:500,state:false,mess:mess}
    public static String error(String msg, Exception e) {
        R r = new R(msg, CODE_ERROR)
                .put(STATE_NAME, STATE_FALSE)
                .put(MESS_NAME, e == null ? "" : "异常打印:{" + e.getMessage() + "}");
        return JSONArray.toJSONString(r);
    }

    // 登录超时封装
    public static String logOut() {
        R r = new R("登录超时!", null, CODE_LOG).put(STATE_NAME, STATE_FALSE);
        return JSONArray.toJSONString(r);
    }

    public static String page(Object obj) {
        if (StringUtil.isNullOrEmpty(obj)) {
            return pageNull();
        }
        return JSON.Encode(obj);
    }

    public static String pageNull() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put(TOTAL_NAME, 0);
        resultMap.put(DATA_NAME, new ArrayList());
        return JSON.Encode(resultMap);
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
