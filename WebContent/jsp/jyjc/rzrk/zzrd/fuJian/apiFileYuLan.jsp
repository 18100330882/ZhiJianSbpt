<%@page import="java.io.File" %>
<%@page import="com.yongjie.ZhiJianSbpt.model.flowBase.ApiFile" %>
<%@page import="com.yongjie.ZhiJianSbpt.container.ServiceProvider" %>
<%@page import="java.util.HashMap" %>
<%@ page import="com.yongjie.ZhiJianSbpt.service.shiYsJyjc.ApiFileService" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.*" %>
<%@ page import="com.yongjie.ZhiJianSbpt.service.flowBase.IYjFlowInstWenShuService" %>
<%@ page import="com.yongjie.ZhiJianSbpt.model.flowBase.YjFlowInstWenShu" %>
<%@ page language="Java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    ApiFileService apiFileService = (ApiFileService) ServiceProvider.getService(ApiFileService.SERVICE_NAME);
    IYjFlowInstWenShuService wenShuService = (IYjFlowInstWenShuService) ServiceProvider.getService(IYjFlowInstWenShuService.SERVICE_NAME);
    try {
        new SessionUtil().getUserName(request);
    } catch (Exception e) {
        response.getWriter().println("登录超时,请重新登录!");
        return;
    }

    //判断是否是文书
    String action = request.getParameter("action") == null ? "" : request.getParameter("action");
    //获取参数
    String fjId = request.getParameter("id"); // 对方文件路径

    if (StringUtil.isEmpty(fjId)) {
        response.getWriter().println("请求参数有误!");
        return;
    }
    ApiFile entity = apiFileService.getApiFileById(Long.parseLong(fjId));
    YjFlowInstWenShu wenShuEntity = wenShuService.getYjFlowInstWenShuById(Long.parseLong(fjId));
    String fileType;
    String localFilePath;
    String dbFileName;
    if (action.equals("wenShu") && !StringUtil.isNullOrEmpty(action)) {
        fileType = wenShuEntity.getExtense();
        localFilePath = wenShuEntity.getFilePath();
        dbFileName = wenShuEntity.getFileTitle();
    } else {
        fileType = entity.getFileType();
        localFilePath = entity.getLocalFilePath();
        dbFileName = entity.getFileName();
    }
    //附件类型删掉"."
    if (fileType.contains(".")) {
        fileType = fileType.replace(".", "");
    }

    if ("JPG".equalsIgnoreCase(fileType) || "PNG".equalsIgnoreCase(fileType) || "JPEG".equalsIgnoreCase(fileType) || "BMP".equalsIgnoreCase(fileType) || "GIF".equalsIgnoreCase(fileType)) {
        response.sendRedirect(basePath + "jsp/common/showImage.jsp?filePath=" + localFilePath);
    } else if ("DOC".equalsIgnoreCase(fileType) || "DOCX".equalsIgnoreCase(fileType) || "XLS".equalsIgnoreCase(fileType) || "XLSX".equalsIgnoreCase(fileType)) {
        response.sendRedirect(basePath + "jsp/common/showDoc.jsp?filePath=" + localFilePath + "&fileType=" + fileType + "&fileName=" + dbFileName);
    } else if ("PDF".equalsIgnoreCase(fileType)) {
        response.sendRedirect(basePath + "jsp/common/showPdf.jsp?filePath=" + localFilePath);
    }
%>