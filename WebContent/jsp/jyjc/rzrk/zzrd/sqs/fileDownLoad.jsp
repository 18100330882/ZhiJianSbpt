<%@page import="java.net.URLEncoder" %>
<%@page import="java.io.OutputStream" %>
<%@page import="java.io.FileInputStream" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.SessionUtil" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.StringUtil" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.SysFinalRecource" %>
<%@ page import="com.yongjie.ZhiJianSbpt.container.ServiceProvider" %>
<%@ page import="com.yongjie.ZhiJianSbpt.service.ShiYsJyjcXchcPsbgNlHzService" %>
<%@ page import="com.yongjie.ZhiJianSbpt.model.ShiYsJyjcXchcPsbgNlHz" %>
<%@ page import="java.io.File" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.FileUtil" %>
<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    ShiYsJyjcXchcPsbgNlHzService psbgNlHzService = (ShiYsJyjcXchcPsbgNlHzService) ServiceProvider.getService(ShiYsJyjcXchcPsbgNlHzService.SERVICE_NAME);

    String filePath = "";
    String fileType = "";
    String fileDownName = "";

    try {
        new SessionUtil().getUserName(request);
    } catch (Exception e) {
        response.getWriter().println("登录超时,请重新登录!");
        return;
    }

    //获取参数
    String id = request.getParameter("id");
    String table = request.getParameter("table");
    if (StringUtil.isEmpty(id) || StringUtil.isNullOrEmpty(table)) {
        response.getWriter().println("请求参数有误!");
        return;
    }

    if ("SHIYSJYJCXCHCPSBGNLHZ".equals(table)) {
        ShiYsJyjcXchcPsbgNlHz entity = psbgNlHzService.queryById(Long.parseLong(id));
        filePath = entity.getFilePathXchc();
        fileType = entity.getFileExtension();
        fileDownName = entity.getFileTitle();
    }


    String fileAllPath = SysFinalRecource.SECOND_LEVEL_DIRECTORY + filePath;
    // 如果文件为空则,则说明下载有问题
    if (fileAllPath == null || "".equals(fileAllPath)) {
        response.getWriter().println("文件不存在!");
        return;
    }

    File f = new File(fileAllPath);
    byte[] data = FileUtil.getBytes(f);
    response.setHeader("Content-Type", "application/octet-stream");
    response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileDownName.getBytes("utf-8"), "iso-8859-1") + "." + fileType);
    response.getOutputStream().write(data, 0, data.length);
    response.getOutputStream().flush();
    response.getOutputStream().close();
    out.clear();
%>