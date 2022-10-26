<%@ page language="Java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.yongjie.ZhiJianSbpt.utilities.*" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String currentUser = (String) request.getSession().getAttribute("userName");
    String json = request.getParameter("json");
    String data = "";
    if (!StringUtil.isNullOrEmpty(json)) {
        DesUtils des = new DesUtils();//自定义密钥
        data = des.decrypt(json);
    }
    //String sqsType=SysFinalRecource.JYJC_XCHC_SQSTYPE;
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
        .divtitle {
            font-size: 8px;
            font-weight: bold;
            font-family: 宋体;
            line-height: 5px;
            color: red;
        }
    </style>
</head>

<body>
<div align="center">
    <div id="treegrid1" class="nui-treegrid" style="width:90%;height:90%;" showTreeIcon="true" treeColumn="name"
         idField="ID" parentField="PARENTID" resultAsTree="false" ondrawcell="onDrawCell"
         expandOnLoad="2" allowResize="true">
        <div property="columns">
            <div name="name" field="NAME" width="300">附件名称</div>
            <div field="FILETYPE" width="100px">附件类型</div>
            <div field="CREATEDATE" width="150px" dateFormat="yyyy-mm-dd">操作日期</div>
            <div field="CAOZUO" width="200px">排序数/操作</div>
        </div>
    </div>
    <div id="panelcz" align="center">
        <a id="baocun" class="nui-button" iconCls="icon-save" onclick="save()">关闭窗口</a>
    </div>
</div>
<script type="text/javascript">
    nui.parse();
    var flowId = "<%=request.getParameter("flowId")%>";
    var flowInstId = "";
    var flag;
    var nodeId = "";
    var sqsType = "<%=request.getParameter("sqsType")%>";
    var statue = "<%=request.getParameter("statue")%>";
    var grid = nui.get("treegrid1");
    var serialNumber = "<%=request.getParameter("serialNumber")%>";
    grid.load("<%=basePath%>apifujiantype_getApiFujianType.action?serialNumber=" + serialNumber + "&flowId=" + flowId + "&sqsType=" + sqsType + "&statue=" + statue);
    grid.reload();

    function onDrawCell(e) {
        var node = e.node,
            field = e.field;
        if (field == "CAOZUO") {
            if (grid.getLevel(node) == 2) {
                e.cellHtml = '<input class="nui-textbox" value="' + e.node.PAIXU + '" disabled="disabled"  style="width:30px" vtype="int"/>';
                e.cellHtml +=
                    '<a class="nui-button mini-button-c1" href="javascript:void(0)" onclick="javascript:removeFuJian(\'' + e.node.KID + '\')" >删除</a>' +
                    '<a class="nui-button mini-button-c1" href="javascript:void(0)" onclick="javascript:lookSqs(' + e.node.KID + ')">查看</a>' +
                    '<a class="nui-button mini-button-c1" href="javascript:void(0)" onclick="javascript:down(\'' + e.node.KID + '\')" >下载</a>';
            } else if (grid.getLevel(node) == 1) {
                e.cellHtml = '<input id="paixu' + e.node.ID + '" class="nui-textbox" value="1" style="width:30px" vtype="int"/>';
                e.cellHtml += '<a class="nui-button mini-button-c1" href="javascript:void(0)" onclick="javascript:update(\'' + e.node.ID + '\',\'' + e.node.FILEEXTENSE + '\',\'' + e.node.FLAG + '\')" onCls="icon-upload">上传</a>';
            }
        }
    }

    //上传
    function update(e, b, c) {
        var id = e;
        flag = c;
        var paixu = $("#paixu" + id).val();
        if (isRealNum(paixu)) {
            if (paixu < 1 || paixu > 99) {
                nui.alert("请输入1~99之间的数字");
                return;
            }
        } else {
            nui.alert("请输入1~99之间的数字");
            return;
        }

        var extense = b;
        var nodes = grid.findNodes(function (node) {//搜索到节点
            if (node.id == id)
                return true;
        });
        nui.open({
            url: "<%=basePath%>jsp/jyjc/rzrk/zzrd/fuJian/XchcFuJianUpload.jsp?flowId=" + flowId + "&nodeId=" + nodeId + "&flowInstId=" + flowInstId + "&serialNumber=" + serialNumber + "&flag=" + flag + "&id=" + id + "&extense=" + extense + "&paixu=" + paixu,
            title: "添加附件",
            width: 800,
            height: 600,
            ondestroy: function (action) {
                grid.reload();
                grid.expandNode(nodes[0]);
            }
        });
    }

    function lookSqs(e) {
        var id = e;
        window.open("<%=basePath%>jsp/jyjc/rzrk/zzrd/fuJian/apiFileYuLan.jsp?id=" + id);
    }

    function isRealNum(val) {
        // isNaN()函数 把空串 空格 以及NUll 按照0来处理 所以先去除
        if (val === "" || val == null) {
            return false;
        }
        if (!isNaN(val)) {
            return true;
        } else {
            return false;
        }
    }

    //删除
    function removeFuJian(e) {
        nui.confirm("确定删除选中记录？", "系统提示",
            function (action) {
                if (action == "ok") {
                    var id = e;
                    $.ajax({
                        url: "<%=basePath%>apiFile_deleteApiFile.action?idResult=" + id,
                        type: "post",
                        success: function (text) {
                            nui.alert("删除成功！", "提醒");
                            grid.reload();
                        },
                        error: function () {
                        }
                    });
                }
            });
    }

    //下载
    function down(e) {
        var id = e;
        location.href = "<%=basePath%>jsp/jyjc/rzrk/zzrd/fuJian/JyjcWenShuDownload.jsp?FjID="
            + id;
    }

    //判断上传是否完成
    function save() {
        CloseWindow();
    }

    function CloseWindow(action) {
        if (action == "close" && form.isChanged()) {
            if (confirm("数据被修改了，是否先保存？")) {
                return false;
            }
        }
        if (window.CloseOwnerWindow)
            return window.CloseOwnerWindow(action);
        else
            window.close();
    }
</script>
</body>
</html>