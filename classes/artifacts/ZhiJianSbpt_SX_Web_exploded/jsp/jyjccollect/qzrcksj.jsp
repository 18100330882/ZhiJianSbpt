<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>检验检测签字人信息</title>
    <script type="text/javascript" src="<%=basePath %>nui/nui.js"></script>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <style>
        .mini-popupedit {
            width: 80px !important;
        }
    </style>
</head>
<body>
<div id="dataGrid" class="nui-datagrid" style="width:100%;height:92%;" allowResize="false" idField="ID"
     multiSelect="true" sizeList="[50,100,500]" showPager="false" pageSize="50"
     allowAlternating="true">
    <div property="columns">
        <div field="check" type="checkcolumn" headerAlign="center"></div>
        <div type="indexcolumn" width="30px" headerAlign="center" align="center">序号</div>
        <div field="QZRNAME" headerAlign="center" align="center">名称</div>
        <div field="ZHIW" headerAlign="center" align="center">职位</div>
        <div field="DEPT" headerAlign="center" align="center">部门</div>
        <div field="EDUCATION" headerAlign="center" align="center">文化程度</div>
        <div field="SFZH" headerAlign="center" align="center">身份证号码</div>
        <div field="TELEPHONE" headerAlign="center" align="center">电话</div>
        <div field="EMAIL" headerAlign="center" align="center">邮箱</div>
        <div field="PIZHUNDATE" align="center" headerAlign="center" dateFormat="yyyy-MM-dd" allowSort="true">批准日期</div>
    </div>
</div>
<script type="text/javascript">

    nui.parse();
    var zsbh = "";
    var grid = nui.get("dataGrid");

    function SetData(data) {
        var zsbh = data.zsbh;
        var id = data.id;
        grid.load("<%=basePath%>collect_getQzrFileList.action?zsbh=" + zsbh + "&id=" + id);
        grid.reload();
    }

    //关闭子窗口，刷新父窗体
    function CloseWindow(action) {
        if (window.CloseOwnerWindow)
            return window.CloseOwnerWindow(action);
        else
            window.close();
    }

    function onCancel(e) {
        CloseWindow("cancel");
    }


</script>
</body>
</html>