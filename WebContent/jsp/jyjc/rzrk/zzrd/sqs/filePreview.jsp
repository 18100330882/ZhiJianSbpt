<%@ page import="java.io.File" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.SessionUtil" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.StringUtil" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.SysFinalRecource" %>
<%@ page import="com.yongjie.ZhiJianSbpt.model.ShiYsJyjcXchcPsbgNlHz" %>
<%@ page import="com.yongjie.ZhiJianSbpt.service.ShiYsJyjcXchcPsbgNlHzService" %>
<%@ page import="com.yongjie.ZhiJianSbpt.container.ServiceProvider" %>
<%@ page import="com.yongjie.ZhiJianSbpt.service.jyjccj.CollectFujianService" %>
<%@ page import="com.yongjie.ZhiJianSbpt.model.jyjccj.CollectFujian" %>
<%@ page import="jxl.biff.EncodedURLHelper" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    ShiYsJyjcXchcPsbgNlHzService psbgNlHzService = (ShiYsJyjcXchcPsbgNlHzService) ServiceProvider.getService(ShiYsJyjcXchcPsbgNlHzService.SERVICE_NAME);
    CollectFujianService collectFujianService = (CollectFujianService) ServiceProvider.getService(CollectFujianService.SERVICE_NAME);

    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String fileType = "";
    String filePath = "";
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
    if ("JYJC_COLLECTFUJIAN".equals(table)) {
        CollectFujian entity = collectFujianService.getById(Long.valueOf(id));
        filePath = entity.getFilePath();
        fileType = entity.getSuffiName().substring(1);
        fileDownName = entity.getFileTitle();
    }

    String fileAllPath = SysFinalRecource.SECOND_LEVEL_DIRECTORY + filePath;
    File file = new File(fileAllPath);
    if (!file.exists()) {
        response.getWriter().println("文件不存在!");
        return;
    }
    if ("JPG".equalsIgnoreCase(fileType) || "PNG".equalsIgnoreCase(fileType) || "JPEG".equalsIgnoreCase(fileType) || "BMP".equalsIgnoreCase(fileType) || "GIF".equalsIgnoreCase(fileType)) {
        response.sendRedirect(basePath + "jsp/jyjc/rzrk/common/showDoc.jsp?filePath=" + filePath);

    } else if ("DOC".equalsIgnoreCase(fileType) || "DOCX".equalsIgnoreCase(fileType) || "XLS".equalsIgnoreCase(fileType) || "XLSX".equalsIgnoreCase(fileType)) {
        response.sendRedirect(basePath + "jsp/jyjc/rzrk/common/showDoc.jsp?filePath=" + filePath + "&fileType=" + fileType + "&fileName=" + URLEncoder.encode(fileDownName, "utf-8"));
    } else if ("PDF".equalsIgnoreCase(fileType)) {
        response.sendRedirect(basePath + "jsp/jyjc/rzrk/common/showPdf.jsp?filePath=" + filePath);
    }
%>