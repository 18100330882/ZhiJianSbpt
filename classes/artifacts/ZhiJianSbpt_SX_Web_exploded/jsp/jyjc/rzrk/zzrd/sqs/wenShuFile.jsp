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
    <title>检验检测机构审批附件页面</title>
    <script type="text/javascript" src="<%=basePath %>nui/nui.js"></script>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>

<body>
<div id="datagrid" style="height:100%;" class="nui-datagrid" allowResize="true" allowAlternating="true"
     multiSelect="true" expandOnLoad="1" dataField="data" ondrawcell="onDrawCell" sizeList="[100,500,1000]"
     pageSize="100">
    <div property="columns">
        <div type="indexcolumn" width="50" headerAlign="center" align="center">序号</div>
        <div name="fileName" field="FILETITLE">附件名称</div>
        <div field="EXTENSE" width="40" headerAlign="center" align="center" renderer="fileType">附件类型</div>
        <div field="ONDOWN" width="40" headerAlign="center" align="center">下载</div>
        <div field="ONYULAN" width="40" headerAlign="center" align="center">预览</div>
    </div>
</div>
<script type="text/javascript">

    nui.parse();
    var serialNumber = '<%=request.getParameter("serialNumber")%>';
    var grid = nui.get("datagrid");

    grid.load("<%=basePath%>apiFile_wenShuList.action?serialNumber=" + serialNumber);
    grid.reload();

    // 设置页面显示数据列 【下载】，【预览】
    function onDrawCell(e) {
        field = e.field;
        var record = e.record;
        if (field == "ONDOWN") {
            params = "FjID=" + record.ID + "&action=wenShu";
            e.cellHtml =
                '<a class="nui-button" style="text-decoration: none;color: blue;" target="_blank" href="<%=basePath%>jsp/jyjc/rzrk/zzrd/fuJian/JyjcWenShuDownload.jsp?' + params + '">下载</a>';
        }
        if (field == "ONYULAN") {
            params = "id=" + record.ID + "&action=wenShu";
            e.cellHtml =
                '<a class="nui-button" style="text-decoration: none;color: blue;" target="_blank" href="<%=basePath%>jsp/jyjc/rzrk/zzrd/fuJian/apiFileYuLan.jsp?' + params + '">预览</a>';
        }
    }

    function fileType(e) {
        var result = e.value;
        if (result.indexOf(".") != -1 && result != null) {
            result = result.replace(".", "");
        }
        return result;
    }

</script>
<style>
    .mini-popupedit {
        width: 80px !important;
    }
</style>
</body>
</html>