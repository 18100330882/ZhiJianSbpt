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
<div id="dataGrid" class="nui-datagrid" style="width:100%;height:95%;" allowResize="false" idField="ID"
     multiSelect="true" sizeList="[50,100,500]" showPager="false" pageSize="50"
     allowAlternating="true">
    <div property="columns">
        <div field="check" type="checkcolumn" headerAlign="center"></div>
        <div type="indexcolumn" width="30px" headerAlign="center" align="center">序号</div>
        <div field="NAME" headerAlign="center" align="center">名称</div>
        <div field="ZHIW" headerAlign="center" align="center">职位</div>
        <div field="ZHUANYE" headerAlign="center" align="center">专业</div>
        <div field="CARDTYPE" headerAlign="center" align="center">证件类型</div>
        <div field="SFZH" headerAlign="center" align="center">证件号码</div>
        <div field="YEAROFSERVICE" headerAlign="center" align="center">工作年限</div>
        <div field="EDUCATION" headerAlign="center" align="center">文化水平</div>
    </div>
</div>
<script type="text/javascript">

    nui.parse();
    var zsbh = "";
    var grid = nui.get("dataGrid");

    function SetData(data) {
        var zsbh = data.zsbh;
        var id = data.id;
        grid.load("<%=basePath%>collect_getRyFileList.action?zsbh=" + zsbh + "&id=" + id);
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

    function onDrawCell(e) {
        field = e.field;
        var record = e.record;
        var id = record.ID;
        var fileTitle = record.FILETITLE;
        var filePath = record.FILEPATH;
        var suffiName = record.SUFFINAME;
        if (field == 'CAOZUO') {
            params = "fileTitle=" + encodeURI(encodeURI(fileTitle)) + "&filePath=" + filePath + "&suffiName=" + suffiName + "&id=" + id + "&table=" + "JYJC_COLLECTFUJIAN";
            e.cellHtml =
                '<a class="nui-button" style="text-decoration: none;color: blue;" target="_blank" href="<%=basePath%>jsp/jyjc/rzrk/zzrd/sqs/filePreview.jsp?' + params + '">预览文件</a>' +
                '<a class="nui-button mini-button-c1" id="deleteId" onclick="cksj(\'' + record.ID + '\')" style="cursor: pointer" href="javascript:void(0)">查看数据</a>' +
                '<a class="nui-button mini-button-c1" id="deleteId" onclick="del(\'' + record.ID + '\')" style="cursor: pointer" href="javascript:void(0)">删除</a>';
        }
    }

    function cksj(id) {
        nui.open({
            url: "<%=basePath%>jsp/jyjccollect/nlcksj.jsp",
            title: "数据查看", width: 1000, height: 500,
            onload: function () {
                var iframe = this.getIFrameEl();
                var data = {zsbh: zsbh, id: id};
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {
                grid.reload();
            }
        });
    }

    function del(id) {
        nui.confirm("如果删除Excel文件，需要重新上传Excel文件!", "提醒", function (action) {
            if (action == "ok") {
                $.ajax({
                    url: "<%=basePath%>collect_delry.action?id=" + id + "&zsbh=" + zsbh,
                    type: "post",
                    success: function (text) {
                        nui.alert("删除成功！", "提醒");
                        grid.reload();
                    },
                    error: function () {
                    }
                });
            }
        })
    }


</script>
</body>
</html>