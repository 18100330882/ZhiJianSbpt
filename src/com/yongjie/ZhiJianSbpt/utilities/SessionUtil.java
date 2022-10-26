package com.yongjie.ZhiJianSbpt.utilities;

import com.yongjie.ZhiJianSbpt.model.AccountSbpt;

import javax.servlet.http.HttpServletRequest;

/**
 * 存储 用户登录后在信息
 *
 * @author zws
 */
public class SessionUtil {
    // 用户名称
    public final static String SESSION_USER_NAME = "userName";
    // 企业名称
    public final static String SESSION_QYMC = "session_qymc";
    // 组织结构代码  社会统一信用代码
    public final static String SESSION_ZZJGDM = "session_zzjgdm";

    // 用户对象
    public final static String SESSION_USER = "session_user";

    // 验证码
    public final static String SESSION_VER_CODE = "session_ver_code";


    /**
     * 获取当前登录用户名||账号
     *
     * @return
     */
    public String getUserName(HttpServletRequest request) {
        try {
            return (String) request.getSession().getAttribute(SessionStatic.SYSTEM_SESSION_NAME);
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 为当前登录用户存储session用户名
     *
     * @param userName
     */
    public void setSessionUserName(String userName, HttpServletRequest request) {
        try {
            request.getSession().setAttribute(SessionStatic.SYSTEM_SESSION_NAME, userName);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * 获取当前登录用户真实姓
     *
     * @return
     */
    public String getUserTrueName(HttpServletRequest request) {
        try {
            return (String) request.getSession().getAttribute(SessionStatic.SYSTEM_SESSION_TRUE_NAME);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 为当前登录用户真实姓session
     *
     * @param userName
     */
    public void setSessionUserTrueName(String trueName, HttpServletRequest request) {
        try {
            request.getSession().setAttribute(SessionStatic.SYSTEM_SESSION_TRUE_NAME, trueName);
        } catch (Exception e) {
        }
    }

    /**
     * 获取部门ID
     */
    public Long getDeptId(HttpServletRequest request) {
        try {
            return (Long) request.getSession().getAttribute(SessionStatic.SYSTEM_SESSION_DEPT_ID);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 设置部门session
     */
    public void setSessionDeptId(Long deptId, HttpServletRequest request) {
        try {
            request.getSession().setAttribute(SessionStatic.SYSTEM_SESSION_DEPT_ID, deptId);
        } catch (Exception e) {
        }
    }

    /**
     * 获取区域ID
     */
    public Long getAreaId(HttpServletRequest request) {
        try {
            return (Long) request.getSession().getAttribute(SessionStatic.SYSTEM_SESSION_AREA_ID);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 设置区域session
     */
    public void setSessionAreaId(Long areaId, HttpServletRequest request) {
        try {
            request.getSession().setAttribute(SessionStatic.SYSTEM_SESSION_AREA_ID, areaId);
        } catch (Exception e) {
        }
    }

    /**
     * 设置flag标识用户区别用户类型
     * 1推送过来的用户  2超级管理员 3是审查员(查询时候用于排除区域限制)
     */
    public String getFlag(HttpServletRequest request) {
        try {
            return request.getSession().getAttribute(SessionStatic.SYSTEM_SESSION_FLAG) + "";
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 设置flag标识用户区别用户类型
     * 1推送过来的用户  2超级管理员 3是审查员(查询时候用于排除区域限制)
     */
    public void setSessionFlag(String flag, HttpServletRequest request) {
        try {
            request.getSession().setAttribute(SessionStatic.SYSTEM_SESSION_FLAG, flag);
        } catch (Exception e) {
        }
    }


}
