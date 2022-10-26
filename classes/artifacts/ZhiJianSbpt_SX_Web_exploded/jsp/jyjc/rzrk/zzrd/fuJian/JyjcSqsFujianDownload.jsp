<%@page import="com.yongjie.ZhiJianSbpt.model.flowBase.YjFlowInstWenShu" %>
<%@page import="com.yongjie.ZhiJianSbpt.service.flowBase.IYjFlowInstWenShuService" %>
<%@ page language="Java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*,
         							 com.yongjie.ZhiJianSbpt.service.shiYsJyjc.*,
         							 com.yongjie.ZhiJianSbpt.container.*,
         							 com.yongjie.ZhiJianSbpt.utilities.*,
         							 java.io.File" %>
<%@ page import="com.yongjie.ZhiJianSbpt.model.flowBase.ApiFile" %>
<%@ page import="com.yongjie.ZhiJianSbpt.service.ShiYsJyjcFuJianService" %>
<%@ page import="com.yongjie.ZhiJianSbpt.model.ShiYsJyjcFuJian" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    //获得附件service
    ShiYsJyjcFuJianService shiYsJyjcFuJianService = (ShiYsJyjcFuJianService) ServiceProvider.getService(ShiYsJyjcFuJianService.SERVICE_NAME);

    if (request.getParameter("FjID") != null) {
        request.getParameter("FjID");
    }
    long id = Long.parseLong(request.getParameter("FjID"));
//将申请书的二进制流转换成字节
    ShiYsJyjcFuJian jyjcFuJian = shiYsJyjcFuJianService.getJyjcFuJianById(id);
    String filePath = jyjcFuJian.getFilePath();
    String ext = jyjcFuJian.getFileExtension();

    /****************查文件****************/
    String fileAllPath = "";
    File filetemp = null;
    byte[] fileContent = null;
    fileAllPath = SysFinalRecource.SECOND_LEVEL_DIRECTORY + filePath;
    filetemp = new File(fileAllPath);
    if (filetemp.exists()) {
        fileContent = FileUtil.getBytes(filetemp);
    }
    if (fileContent == null) {
        response.getWriter().write("文件未找到！");
        return;
    }
    /****************查文件****************/

    //获得fileName
    String fileName = jyjcFuJian.getFileTitle();
    response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "iso-8859-1") + ext);
    response.getOutputStream().write(fileContent, 0, fileContent.length);
    response.getOutputStream().flush();
    response.getOutputStream().close();
    out.clear();
    out = pageContext.pushBody();
%>
 