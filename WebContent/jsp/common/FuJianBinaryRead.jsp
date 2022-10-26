<%@ page language="Java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.SysFinalRecource" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.FileUtil" %>
<%@ page import="java.io.File" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String filePath = request.getParameter("filePath");
    String fileTitle = request.getParameter("fileTitle");
    String extense = request.getParameter("extense");

    String fileAllPath = "";
    File filetemp = null;
    byte[] fileContent = null;
    for (int i = 0; i < 2; i++) {
        if (i == 0) {
            fileAllPath = SysFinalRecource.SECOND_LEVEL_DIRECTORY + filePath;
        } else {
            fileAllPath = SysFinalRecource.SECOND_LEVEL_DIRECTORY_W + filePath;
        }
        filetemp = new File(fileAllPath);
        if (filetemp.exists()) {
            fileContent = FileUtil.getBytes(filetemp);
            break;
        }
    }
    if (fileContent == null) {
        response.getWriter().write("文件未找到！");
        return;
    }
    String fileName = fileTitle;
    String fileExtension = extense;
    response.setHeader("Content-Disposition",
            "attachment; filename=" + new String(fileName.getBytes("utf-8"), "iso-8859-1") + fileExtension);
    response.getOutputStream().write(fileContent, 0, fileContent.length);
    response.getOutputStream().flush();
    response.getOutputStream().close();
    out.clear();
    out = pageContext.pushBody();
%>
