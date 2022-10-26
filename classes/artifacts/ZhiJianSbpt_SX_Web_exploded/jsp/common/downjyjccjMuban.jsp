<%@ page language="Java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="

         							 com.yongjie.ZhiJianSbpt.utilities.*,
                                      java.io.File" %>
<%
    String status = request.getParameter("status");
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String filePath = null;
    String fileType = null;
    if (status.equals("1")) {
        fileType = "能力";
        filePath = request.getSession().getServletContext().getRealPath("muban/nl.xlsx");
    } else if (status.equals("2")) {
        fileType = "签字人";
        filePath = request.getSession().getServletContext().getRealPath("muban/qzr.xls");
    } else if (status.equals("3")) {
        fileType = "检测人员";
        filePath = request.getSession().getServletContext().getRealPath("muban/ry.xls");
    } else if (status.equals("4")) {
        fileType = "机构";
        filePath = request.getSession().getServletContext().getRealPath("muban/jg.xls");
    } else {
        response.getWriter().print("没有找到该类型模板！");
        return;
    }
    File f = new File(filePath);
    int i = filePath.lastIndexOf(".");
    String fileExtension = filePath.substring(i);
    byte[] data = FileUtil.getBytes(f);
    response.setHeader("Content-Type", "application/octet-stream");
    response.setHeader("Content-Disposition", "attachment; filename=" + new String((fileType + "模板").getBytes("utf-8"), "iso-8859-1") + fileExtension);
    response.getOutputStream().write(data, 0, data.length);
    response.getOutputStream().flush();
    response.getOutputStream().close();
    out.clear();

%>

</html>