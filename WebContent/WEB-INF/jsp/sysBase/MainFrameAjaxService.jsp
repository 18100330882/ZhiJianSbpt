<%@page import="org.apache.naming.java.javaURLContextFactory" %>
<%@page import="java.sql.Date" %>
<%@ page language="Java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         import="java.util.*,
                 java.lang.reflect.*,
                 com.yongjie.ZhiJianSbpt.service.*,
                 com.yongjie.ZhiJianSbpt.container.*,
                 com.yongjie.ZhiJianSbpt.model.*,
                 com.yongjie.ZhiJianSbpt.utilities.*" %>
<%
    //设置响应编码和请求编码为UTF-8
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");

    String methodName = request.getParameter("method");

    try {
        Class[] argsClass = new Class[2];
        argsClass[0] = HttpServletRequest.class;
        argsClass[1] = HttpServletResponse.class;

        Class cls = this.getClass();
        //利用反射技术获取方法对象（万物皆对象）
        Method method = cls.getMethod(methodName, argsClass);

        Object[] args = new Object[2];
        args[0] = request;
        args[1] = response;

        BeforeInvoke(methodName);
        method.invoke(this, args);
    } catch (Exception e) {
        //封装hashmap对象
        HashMap result = new HashMap();
        result.put("error", -1);
        result.put("message", e.getMessage());
        result.put("stackTrace", e.getStackTrace());
        //将hashmap对象封装成json
        String json = JSON.Encode(result);
        response.reset();
        response.getWriter().write(json);
    } finally {
        AfterInvoke(methodName);
    }
%>
<%!
    ICloginService menuService = (ICloginService) ServiceProvider.getService(ICloginService.SERVICE_NAME);

    //权限管理
    protected void BeforeInvoke(String methodName) {

    }

    //日志管理
    protected void AfterInvoke(String methodName) {

    }

    //根据当前用户获取显示菜单
    public void getMenus(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sysFlag = WebPropsInfo.getSystemFlag();
        //从session中获取当前用户名
        Object logOnUserName = request.getSession().getAttribute("userName");
        if (logOnUserName == null) {
            RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");//并未实现跳转页面Login.jsp
            rd.forward(request, response);
            return;
        } else {
            //申报端暂不根据权限列菜单 黄煜豪
            ArrayList menus = menuService.getMenusByUsername(logOnUserName.toString(), sysFlag);
            String json = JSON.Encode(menus);
            response.getWriter().write(json);
        }
    }

    public void getMenus8(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String flag = request.getParameter("flag");
        Object logOnUserName = request.getSession().getAttribute("userName");
        if (logOnUserName == null) {
            RequestDispatcher rd = request.getRequestDispatcher("Login8.jsp");
            rd.forward(request, response);
            return;
        } else {
            //申报端暂不根据权限列菜单 黄煜豪
            ArrayList menus = menuService.getMenusByUsername(logOnUserName.toString(), flag);
            String json = JSON.Encode(menus);
            response.getWriter().write(json);
        }
    }
%>
