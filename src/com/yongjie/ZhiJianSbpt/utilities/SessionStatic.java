/**
 * @Author: 朱卫士
 * @Createtime: 2020年6月13日 下午5:04:04
 * @Copyright: Copyright (c) 2017
 * @Company: 北京永杰友信科技有限公司
 * @Version：1.0
 * @Description: 用户session 静态变量名称
 */
package com.yongjie.ZhiJianSbpt.utilities;

public class SessionStatic {

    /**
     * 用户名||账号
     */
    public final static String SYSTEM_SESSION_NAME = "userName";

    /**
     * 真实姓名
     */
    public final static String SYSTEM_SESSION_TRUE_NAME = "USER_TRUE_NAME";

    /**
     * 部门id
     */
    public final static String SYSTEM_SESSION_DEPT_ID = "USER_DEPT_ID";

    /**
     * 部门名称
     */
    public final static String SYSTEM_SESSION_DEPT_NAME = "USER_DEPT_NAME";

    /**
     * 区域ID
     */
    public final static String SYSTEM_SESSION_AREA_ID = "USER_AREA_ID";

    /**
     * 用户标识
     * 1推送过来的用户  2超级管理员 3是审查员(查询时候用于排除区域限制)
     */
    public final static String SYSTEM_SESSION_FLAG = "USER_FLAG";

    /**
     * 区域名称
     */
    public final static String SYSTEM_SESSION_AREA_NAME = "USER_AREA_NAME";

}
