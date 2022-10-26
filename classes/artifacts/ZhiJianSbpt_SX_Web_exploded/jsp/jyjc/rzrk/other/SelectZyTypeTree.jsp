<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>专业领域类别</title>
    <script type="text/javascript" src="<%=basePath %>nui/nui.js"></script>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>

<body>
<div style="height:600px;">
    <div class="nui-fit">
        <ul id="tree1" class="nui-tree" style="width:100%;" showCheckBox="false"
            expandOnLoad=0 sortField="PAIXU" showTreeIcon="true" textField="TYPENAME" idField="ID" parentField="PARENTID" resultAsTree="false">
        </ul>
    </div>
    <div class="nui-toolbar" style="text-align: center; padding-top: 8px; padding-bottom: 8px;"
         borderStyle="border-left:0;border-bottom:0;border-right:0;">
        <a class="nui-button" style="width: 60px;" onclick="onOk()" iconCls="icon-save">确定</a>
        <span style="display: inline-block; width: 25px;"></span>
        <a class="nui-button" style="width: 60px;" onclick="onCancel()" iconCls="icon-cancel">取消</a>
    </div>
</div>

<script type="text/javascript">
    nui.parse();
    var tree = nui.get("tree1")
    tree.load("<%=basePath %>common_getShiysjyjcScType.action");

    function GetData() {
        var node = tree.getSelectedNode();
        if (node == null || node == "") {
            return "";
        }
        var result = {};
        result.names = node.TYPENAME;
        result.dms = node.DAIMA;
        return result;
    }

    function CloseWindow(action) {
        if (window.CloseOwnerWindow)
            return window.CloseOwnerWindow(action);
        else
            window.close();
    }

    //选取节点
    function onOk() {
        var node = tree.getSelectedNode();
        if (node && tree.isLeaf(node) == false) {
            return;
        }
        CloseWindow("ok");
    }

    function onCancel() {
        CloseWindow("cancel");
    }
</script>
</body>
</html>
