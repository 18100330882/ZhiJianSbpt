<%@page import="com.yongjie.ZhiJianSbpt.model.flowBase.YjFlowInstWenShu" %>
<%@page import="com.yongjie.ZhiJianSbpt.service.flowBase.IYjFlowInstWenShuService" %>
<%@ page language="Java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*,
         							 com.yongjie.ZhiJianSbpt.service.shiYsJyjc.*,
         							 com.yongjie.ZhiJianSbpt.container.*,
         							 com.yongjie.ZhiJianSbpt.utilities.*,
         							 java.io.File" %>
<%@ page import="com.yongjie.ZhiJianSbpt.model.flowBase.ApiFile" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    //判断是否是文书
    String action = request.getParameter("action")==null?"":request.getParameter("action");
    //获得附件service
    ApiFileService apiFileService = (ApiFileService) ServiceProvider.getService(ApiFileService.SERVICE_NAME);
    IYjFlowInstWenShuService wenShuService = (IYjFlowInstWenShuService) ServiceProvider.getService(IYjFlowInstWenShuService.SERVICE_NAME);
    if (request.getParameter("FjID") != null) {
        request.getParameter("FjID");
    }
    long id = Long.parseLong(request.getParameter("FjID"));
    //上报段附件
    ApiFile apiFile = apiFileService.getApiFileById(id);
    //审批段附件
    YjFlowInstWenShu wenShuEntity = wenShuService.getYjFlowInstWenShuById(id);

    String filePath;
    String ext;
    String fileName ;
    if (action.equals("wenShu")&&!StringUtil.isNullOrEmpty(action)){
        filePath = wenShuEntity.getFilePath();
        ext = wenShuEntity.getExtense();
        fileName = wenShuEntity.getFileTitle();
    }else {
        filePath = apiFile.getLocalFilePath();
        ext = apiFile.getFileType();
        fileName = apiFile.getFileName();
    }
    //附件类型加上"."
    if (!ext.contains(".")){
        ext = "."+ext;
    }
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

    response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "iso-8859-1") + ext);
    response.getOutputStream().write(fileContent, 0, fileContent.length);
    response.getOutputStream().flush();
    response.getOutputStream().close();
    out.clear();
    out = pageContext.pushBody();
%>
 