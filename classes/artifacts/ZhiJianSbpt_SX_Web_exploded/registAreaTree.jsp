<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
</head>

<body>
<div style="height:350px;">
    <div class="nui-toolbar" style="text-align: center; line-height: 30px; display: none"
         borderStyle="border-left:0;border-top:0;border-right:0;">
        <label>名称：</label>
        <input id="key" class="nui-textbox" style="width: 150px;" onenter="onKeyEnter"/>
        <a class="nui-button" style="width: 60px;" onclick="search()" iconCls="icon-search">查询</a>
    </div>
    <div class="nui-fit">
        <ul id="tree1" class="nui-tree" url="<%=basePath %>area_treeForRegist.action?isEnabled=0" style="width:100%;" onnodeclick="onNodeDblClick"
            expandOnLoad=0 sortField="PAIXU" showTreeIcon="true" textField="AREANAME" idField="ID" parentField="PARENTID" resultAsTree="false">
            <!-- url数据是否列表 -->
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

    function GetData() {
        var node = tree.getSelectedNode();
        var result = "";
        var node1 = tree.getAncestors(node);//拿到该节点的所有父节点
        for (var i = 0, size = node1.length; i < size; i++) {
            result += node1[i].AREANAME;
        }
        result += node.AREANAME + "," + node.ID;
        return result;
    }

    function onNodeDblClick(e) {
        onOk();
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
            //alert("不能选中父节点");
            return;
        }
        CloseWindow("ok");
    }

    function onCancel() {
        CloseWindow("cancel");
    }

    function search() {
        //对于nui控件需要下面方法获取值 而不能使用document.getElementById("key").value
        var text = nui.get("key").getValue();
        var msgid = nui.loading("数据查询中，请稍后......");
        /* ajax交互过程 */
        $.ajax({
            url: "<%=basePath%>regist_filterAreaTree.action",
            data: {
                name: text,
                isEnabled: 0
            },
            type: "post",
            success: function (text) {
                var data = nui.decode(text);
                tree.loadList(data);
                tree.expandAll();
                mini.hideMessageBox(msgid);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText);
            }
        });
    }

    function onKeyEnter(e) {
        search();
    }
</script>
</body>
</html>
