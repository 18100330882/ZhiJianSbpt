<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="java.io.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.zhuozhengsoft.pageoffice.*" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.SysFinalRecource" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>附件查看</title>
</head>
<%
    String filePath = request.getParameter("filePath");
    String fileAllPath = "";
    out.clear();
    out = pageContext.pushBody();
    response.setContentType("application/pdf");
    fileAllPath = SysFinalRecource.SECOND_LEVEL_DIRECTORY + filePath;
    try {
        if (SysFinalRecource.OS.equals("windows")) {
            fileAllPath = fileAllPath.replaceAll("/", "\\\\");
        }

        File outputFile = new File(fileAllPath);

        if (outputFile.getParentFile().exists()) {
            System.out.print(outputFile);
            //判断该路径下的文件是否存在
            DataOutputStream temps = new DataOutputStream(response.getOutputStream());
            DataInputStream in = new DataInputStream(new FileInputStream(outputFile));

            byte[] b = new byte[2048];
            while ((in.read(b)) != -1) {
                temps.write(b);
                temps.flush();
            }
            in.close();
            temps.close();
        } else {
            out.print(outputFile + " 文件不存在!");
        }

    } catch (Exception e) {
        out.println(e.getMessage());
    }
%>
<body>
</body>
</html>