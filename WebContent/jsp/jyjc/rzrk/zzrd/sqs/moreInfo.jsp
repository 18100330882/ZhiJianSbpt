<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>相关信息下载</title>
    <script type="text/javascript" src="<%=basePath %>nui/nui.js"></script>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<body>
<div style="margin:auto; padding:5px;">
    <table width="100%">
        <tr height="40px">
            <td style="color: #005eff">关于印发能源节能检测项目等4个类别资质认定范围清单的通知.docx</td>
            <td width="10%"><a class="nui-button" iconCls="icon-download" onclick="downLoadInfo('fwqdtz')">下载</a></td>
        </tr>
        <tr height="40px">
            <td style="color: #005eff">关于中药材检验检测能力资质认定的补充通知.pdf</td>
            <td width="10%"><a class="nui-button" iconCls="icon-download" onclick="downLoadInfo('zzrdbctz')">下载</a></td>
        </tr>
        <tr height="40px">
            <td style="color: #005eff">药材检验检测能力资质认定范围清单.docx</td>
            <td width="10%"><a class="nui-button" iconCls="icon-download" onclick="downLoadInfo('zzrdfwqd')">下载</a></td>
        </tr>
        <tr height="40px">
            <td style="color: #005eff">豫市监办〔2021〕171号（检验检测处--主动公开）关于中药材检验检测能力资质认定的通知.pdf</td>
            <td width="10%"><a class="nui-button" iconCls="icon-download" onclick="downLoadInfo('zzrdtz')">下载</a></td>
        </tr>
    </table>
</div>
<script type="text/javascript">
    nui.parse();

    // 下载模版
    function downLoadInfo(fileName) {
        location.href = "<%=basePath%>jsp/jyjc/rzrk/zzrd/sqs/downLoadTemp.jsp?fileName=" + fileName;
    }
</script>
</body>
</html>
