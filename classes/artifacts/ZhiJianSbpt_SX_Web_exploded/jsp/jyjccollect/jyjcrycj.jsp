<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>人员信息</title>
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
<div style="width:100%;">
    <div class="nui-toolbar" style="border-bottom:0;padding:0px;">
        <table style="width:100%;">
            <tr>
                <td style="width: 100%;">
                    <a id="downLoadTemp" class="nui-button" iconCls="icon-download"
                       onclick="downLoadTemp()">下载人员信息模板</a>
                    <a id="uploadInfo" class="nui-button" iconCls="icon-upload" onclick="uploadInfo()">上传人员信息</a>
                </td>
            </tr>
        </table>
    </div>
</div>
<div id="dataGrid" class="nui-datagrid" style="width:100%;height:94%;" allowResize="false" ondrawcell="onDrawCell"
     idField="ID" multiSelect="false" pageSize="50" allowAlternating="true">
    <div property="columns">
        <div field="check" type="checkcolumn" headerAlign="center"></div>
        <div type="indexcolumn" headerAlign="center">序号</div>
        <div field="FILETITLE" align="center" headerAlign="center" allowSort="true">文件名称</div>
        <div field="SUFFINAME" align="center" headerAlign="center" allowSort="true">文件类型</div>
        <div field="CZDATE" align="center" headerAlign="center" dateFormat="yyyy-MM-dd" allowSort="true">采集日期</div>
        <div field="CAOZUO" align="center" headerAlign="center" allowSort="true">操作</div>
    </div>
</div>

<script type="text/javascript">

    nui.parse();
    var zsbh = "";
    var grid = nui.get("dataGrid");


    // 下载模版
    function downLoadTemp() {
        var fileName = "ryTemp";
        location = "<%=basePath%>jsp/common/downjyjccjMuban.jsp?status=" + 3;

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


    function uploadInfo() {
        nui.open({
            url: "<%=basePath%>jsp/jyjccollect/fileupload.jsp?status=" + 3 + "&zsbh=" + zsbh,
            title: "添加附件",
            width: 800,
            height: 300,
            ondestroy: function (action) {
                grid.reload();
            }
        });
    }

    function onDrawCell(e) {
        field = e.field;
        var record = e.record;
        var id = record.ID;
        var fileTitle = record.FILETITLE;
        var filePath = record.FILEPATH;
        var suffiName = record.SUFFINAME;
        if (field == 'CAOZUO') {
            params = "id=" + id + "&table=" + "JYJC_COLLECTFUJIAN";
            e.cellHtml =
                '<a class="nui-button mini-button-c1" style="cursor: pointer" target="_blank" href="<%=basePath%>jsp/jyjc/rzrk/zzrd/sqs/filePreview.jsp?' + params + '">预览文件</a>' +
                '<a class="nui-button mini-button-c1" id="deleteId" onclick="cksj(\'' + record.ID + '\')" style="cursor: pointer" href="javascript:void(0)">查看数据</a>' +
                '<a class="nui-button mini-button-c1" id="deleteId" onclick="del(\'' + record.ID + '\')" style="cursor: pointer" href="javascript:void(0)">删除</a>';
        }
    }

    function cksj(id) {
        nui.open({
            url: "<%=basePath%>jsp/jyjccollect/rycksj.jsp",
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

    function editEvent(param) {
        window.open("<%=basePath%>jsp/jyjc/rzrk/zzrd/sqs/editJyjcNl.jsp?" + param);
    }

    function deleteEvent(id) {
        nui.confirm("如果删除Excel文件，需要重新上传Excel文件!", "提醒", function (action) {
            if ("ok" == action) {
                $.ajax({
                    url: "<%=basePath%>apiShiYsJyjcNl_deleteEvent.action",
                    type: 'post',
                    cache: 'false',
                    async: false,
                    data: {id: id},
                    success: function (result) {
                        result = nui.decode(result);
                        nui.alert(result.msg, "提示!");
                        grid.reload();
                    },
                });
            }
        })
    }

    function previewEvent(param) {
        window.open("<%=basePath%>jsp/jyjc/rzrk/zzrd/sqs/filePreview.jsp?" + param);
    }

    function delry(){
        var rows = grid.getSelecteds();
        if(rows.length>0){
            nui.confirm('确认删除吗','提示',function (action){
                if(action=='ok'){
                    var ids='';
                    for (var i = 0; i < rows.length; i++) {
                        ids+=','+rows[i].ID;
                    }
                    var idslice=ids.slice(1);
                    $.ajax({
                        url: "<%=basePath%>collect_delry.action",
                        type: 'post',
                        cache: 'false',
                        async: false,
                        data: {idslice: idslice},
                        success: function (result) {
                            result = nui.decode(result);
                            if(result=='1'){
                                nui.alert('删除成功', "提示!");
                                grid.reload();
                            }
                        },
                    });
                }
            })
        }
    }

    $(function () {
        $.ajax({
            url: "<%=basePath%>collect_queryByUserName.action",
            type: 'post',
            cache: false,
            success: function (result) {//成功获得数据时
                var map = nui.decode(result);
                if (map.total == 0) {
                    nui.get('uploadInfo').disable();
                    return
                }
                zsbh = map.data.ZZRDZSBH;
                grid.load("<%=basePath%>collect_ckfj.action?zsbh=" + zsbh + "&state=" + 3);
                grid.reload();
                nui.get('uploadInfo').enable();
            }
        });
    })
</script>
</body>
</html>