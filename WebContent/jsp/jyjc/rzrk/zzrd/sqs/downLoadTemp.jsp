<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.io.*" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.FileUtil" %>
<%
    String fileName = request.getParameter("fileName");
    String downLoadFileName = "";
    if ("nlTemp".equals(fileName)) {
        fileName = "nlTemp.xlsx";
        downLoadFileName = "检验检测能力信息模板";
    }
    if ("fwqdtz".equals(fileName)) {
        fileName = "fwqdtz.docx";
        downLoadFileName = "关于印发能源节能检测项目等4个类别资质认定范围清单的通知";
    }
    if ("zzrdbctz".equals(fileName)) {
        fileName = "zzrdbctz.pdf";
        downLoadFileName = "关于中药材检验检测能力资质认定的补充通知";
    }
    if ("zzrdfwqd".equals(fileName)) {
        fileName = "zzrdfwqd.docx";
        downLoadFileName = "药材检验检测能力资质认定范围清单";
    }
    if ("zzrdtz".equals(fileName)) {
        fileName = "zzrdtz.pdf";
        downLoadFileName = "豫市监办〔2021〕171号（检验检测处--主动公开）关于中药材检验检测能力资质认定的通知";
    }
    if ("yqsbTemp".equals(fileName)) {
        fileName = "yqsbTemp.xlsx";
        downLoadFileName = "仪器设备配置表信息模板";
    }

    String url = request.getSession().getServletContext().getRealPath("jsp/jyjc/rzrk/zzrd/sqs/template/" + fileName);
    //拿到excel文件
    File f = new File(url);
    int i = fileName.lastIndexOf(".");
    String fileExtension = fileName.substring(i);
    byte[] data = FileUtil.getBytes(f);
    response.setHeader("Content-Type", "application/octet-stream");
    response.setHeader("Content-Disposition", "attachment; filename=" + new String(downLoadFileName.getBytes("utf-8"), "iso-8859-1") + fileExtension);
    response.getOutputStream().write(data, 0, data.length);
    response.getOutputStream().flush();
    response.getOutputStream().close();
    out.clear();
%>