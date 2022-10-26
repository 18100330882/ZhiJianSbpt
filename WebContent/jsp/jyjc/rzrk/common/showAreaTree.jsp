<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>行政区域</title>
    <script type="text/javascript" src="<%=basePath %>nui/nui.js"></script>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>

<body>
<div style="height:350px;">
    <div class="nui-fit">
        <ul id="areaTree" class="nui-tree" style="width:100%;" expandOnLoad=0 sortField="PAIXU" showTreeIcon="true"
            textField="AREANAME" idField="ID"
            parentField="PARENTID" resultAsTree="false">
            <!-- url数据是否列表 -->
        </ul>
    </div>
    <div class="nui-toolbar" style="text-align: center; padding-top: 8px; padding-bottom: 8px;"
         borderStyle="border-left:0;border-bottom:0;border-right:0;">
        <a class="nui-button" style="width: 60px;" onclick="onOk()" iconCls="icon-save">确定</a>
        <a class="nui-button" style="width: 60px;margin-left: 20px" onclick="onCancel()" iconCls="icon-cancel">取消</a>
    </div>
</div>

<script type="text/javascript">
    nui.parse();
    var areaTree = nui.get("areaTree")
    var action = "";

    function SetData(data) {
        action = data.action;
        actionUrl = "<%=basePath %>area_areaTree.action?action=" + action;
        areaTree.load(actionUrl);
        areaTree.reload();
    }

    function GetData() {
        var node = areaTree.getSelectedNode();
        if (node.PARENTID == 0) {
            nui.alert("请选择下级行政区域！", "提醒！");
            return;
        }
        var result = "";
        //拿到该节点的所有父节点
        var node1 = areaTree.getAncestors(node);
        var upAreaName = "";
        var upAreaId = "";
        for (var i = 0, size = node1.length; i < size; i++) {
            result += node1[i].AREANAME;
            if (node1[i].PARENTID == 1) {
                upAreaName = node1[i].AREANAME;
                upAreaId = node1[i].ID;
            }
        }

        if (node.PARENTID == 1) {
            upAreaName = node.AREANAME;
            upAreaId = node.ID;
        }

        result += node.AREANAME + "," + node.ID + "," + upAreaName + "," + upAreaId;
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
        CloseWindow("ok");
    }

    function onCancel() {
        CloseWindow("cancel");
    }
</script>
</body>
</html>
