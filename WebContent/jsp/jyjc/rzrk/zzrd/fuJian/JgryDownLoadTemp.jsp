<%@ page language="Java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import=" java.io.*" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.StringUtil" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.FileUtil" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String title = request.getParameter("title");
    if (!StringUtil.isNullOrEmpty(title) && title.equals("null") == false) {
        title = java.net.URLDecoder.decode(title, "utf-8");
    }
    String fileNames = "JyjcRy.xls";
    String url = request.getSession().getServletContext().getRealPath("jsp/jyjc/rzrk/zzrd/fuJian/flowBase/" + fileNames);
    //拿到excel文件
    File f = new File(url);
    int i = fileNames.lastIndexOf(".");//原名称里倒数第一个"."在哪里
    String fileExtension = fileNames.substring(i);//取得后缀，及"."后面的字符
    byte[] data = FileUtil.getBytes(f);
    response.setHeader("Content-Type", "application/octet-stream");
    response.setHeader("Content-Disposition", "attachment; filename=" + new String(title.getBytes("utf-8"), "iso-8859-1") + fileExtension);
    response.getOutputStream().write(data, 0, data.length);
    response.getOutputStream().flush();
    response.getOutputStream().close();
    out.clear();
    out = pageContext.pushBody();
%>