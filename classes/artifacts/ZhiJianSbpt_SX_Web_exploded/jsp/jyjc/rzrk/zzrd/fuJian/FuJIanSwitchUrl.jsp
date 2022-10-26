<%@ page language="Java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>标题</title>
    <script type="text/javascript" src="<%=basePath %>nui/nui.js"></script>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <style type="text/css">
        html, body {
            margin: 0;
            padding: 0;
            border: 0;
            width: 100%;
            height: 100%;
            overflow: hidden;
        }
    </style>
</head>
<!--
- Author(s): 李杰
- CreateTime: 2016年12月8日 上午10:14:58
- Copyright: Copyright (c) 2016
- Company: 北京永杰友信科技有限公司
- Version：1.0
- Description:
-->
<body>
<div style="height:470px;">
    <div style="margin-top: 5px;">
        <form id="form1" action="" target="target1" method="post">
            <input id="tableName" name="tableName" type="hidden"/>
            <input id="filePath" name="filePath" type="hidden"/>
            <input id="fileTitle" name="fileTitle" type="hidden"/>
            <input id="extense" name="extense" type="hidden"/>
            <input id="isButton" name="isButton" type="hidden"/>
            <input id="flowId" name="flowId" type="hidden"/>
            <input id="flowInstId" name="flowInstId" type="hidden"/>
            <input id="nodeId" name="nodeId" type="hidden"/>
            <input id="closeReturn" name="closeReturn" type="hidden"/>
        </form>
        <iframe id="iframenr" name="target1" width="100%" frameborder="0" scrolling="yes"></iframe>
    </div>

</div>
<script type="text/javascript">
    nui.parse();
    var id =<%=request.getParameter("id")%>;
    var tableName = '<%=request.getParameter("tableName")%>';
    var filePath = decodeURI(<%=request.getParameter("filePath")%>);
    var extense =<%=request.getParameter("extense")%>;
    var fileTitle = decodeURI(<%=request.getParameter("fileTitle")%>);
    var isButton = '<%=request.getParameter("isButton")%>';

    var nodeId = '<%=request.getParameter("nodeId")%>';
    var flowInstId = '<%=request.getParameter("flowInstId")%>';
    if (flowInstId == null || flowInstId == 'null') {
        flowInstId = '';
    }
    var flowId = '<%=request.getParameter("flowId")%>';
    if (flowInstId == null || flowId == 'null') {
        flowId = '';
    }
    var closeReturn = '<%=request.getParameter("closeReturn")%>';

    getUrl();

    function getUrl() {
        nui.ajax({
            url: "<%=basePath%>flowWenShu_getSwitchUrl.action",
            data: {
                id: id,
                tableName: tableName,
                filePath: filePath,
                extense: extense,
                fileTitle: fileTitle,
                isButton: isButton,
                nodeId: nodeId,
                flowInstId: flowInstId,
                flowId: flowId
            },
            type: "post",
            async: false,
            success: function (text) {
                $("#iframenr").height(document.body.offsetHeight - 20);
                //$("#iframenr").attr("src",text);
                var data = nui.decode(text);
                if (data.netCode == 1) { // 受理通知书
                    var unid = data.netWorkPath,	// 对方路径
                        localFilePath = data.filePath, // 本地路径
                        fileType = data.extense, // 附件类型
                        serialNumber = data.serialNumber;// 流水号
                    // 参数
                    var params = "unid=" + unid + "&localFilePath=" + localFilePath + "&serialNumber=" + serialNumber + "&fileType=" + fileType + "&tzs=01&tzsId=" + id;

                    $('#form1').attr('action', '<%=basePath%>jsp/jyjc/rzrk/zzrd/fuJian/apiFileYuLan.jsp?' + params);
                    $('#form1').submit();
                } else {
                    $("#tableName").val(data.tableName);
                    $("#filePath").val(data.filePath);
                    $("#fileTitle").val(data.fileTitle);
                    $("#extense").val(data.extense);
                    $("#isButton").val(data.isButton);
                    $("#flowId").val(data.flowId);
                    $("#flowInstId").val(data.flowInstId);
                    $("#nodeId").val(data.nodeId);
                    $("#closeReturn").val(closeReturn);
                    $('#form1').attr('action', data.ReturnUrl);
                    $('#form1').submit();
                }
            }
        });
    }

</script>
</body>
</html>