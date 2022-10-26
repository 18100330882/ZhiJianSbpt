<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>仪器设备配置表</title>
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
                       onclick="downLoadTemp()">下载仪器设备配置表模板</a>
                    <a id="uploadInfo" class="nui-button" iconCls="icon-upload" onclick="uploadInfo()">上传仪器设备配置表信息</a>
                    <a id="scword" class="nui-button" iconCls="icon-upload" onclick="scword()">生成Word</a>
                </td>
            </tr>
        </table>
    </div>
</div>
<div id="dataGrid" class="nui-datagrid" style="width:100%;height:95%;" allowResize="false" idField="ID"
     multiSelect="true" sizeList="[50,100,500]" showPager="false" pageSize="50"
     allowAlternating="true" ondrawcell="ondrawcellEvent">
    <div property="columns">
        <div field="check" type="checkcolumn" headerAlign="center"></div>
        <div type="indexcolumn" width="10px" headerAlign="center" align="center">序号</div>
        <div field="FILETITLE" headerAlign="center">附件名称</div>
        <div field="CDDZ" headerAlign="center" align="center">场地地址</div>
        <div field="ISSP" width="25px" headerAlign="center" align="center">食品/非食品</div>
        <div field="FILEEXTENSION" width="20px" headerAlign="center" align="center">附件类型</div>
        <div field="DOWNLOADFILE" width="15px" headerAlign="center" align="center">下载</div>
        <div field="LOOKFILE" width="15px" headerAlign="center" align="center">预览</div>
        <%--<div field="EDITFILE" width="15px" headerAlign="center" align="center">编辑</div>--%>
        <div field="DELETEFILE" width="15px" headerAlign="center" align="center">删除</div>
    </div>
</div>
<script type="text/javascript">

    nui.parse();
    var grid = nui.get("dataGrid");
    var serialNumber = '<%=request.getParameter("serialNumber")%>';
    var action = "<%=request.getParameter("detail")%>";

    grid.load("<%=basePath%>apiShiYsJyjcNl_getNlFileList.action?nlorSb=1&serialNumber=" + serialNumber);
    grid.reload();

    // 下载模版
    function downLoadTemp() {
        var fileName = "yqsbTemp";
        location.href = "<%=basePath%>jsp/jyjc/rzrk/zzrd/sqs/downLoadTemp.jsp?fileName=" + fileName;
    }

    function moreInfo() {
        nui.open({
            url: "<%=basePath%>/jsp/jyjc/rzrk/zzrd/sqs/moreInfo.jsp",
            title: "相关信息下载",
            width: '50%',
            height: '35%',
            onload: function () {

            },
            ondestroy: function (action) {
                grid.reload();
            }
        });
    }

    function uploadInfo() {
        nui.open({
            url: "<%=basePath%>jsp/jyjc/rzrk/zzrd/sqs/uploadFile.jsp",
            title: "上传检验检测能力信息",
            width: 800,
            height: 500,
            onload: function () {
                //拿到新窗口对象
                var iframe = this.getIFrameEl();
                //新窗口传递json数据
                var data = {serialNumber: serialNumber, flowId: "4", type: "1"};
                //调用新窗口的SetData方法。
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {
                grid.reload();
            }
        });
    }

    /* function scword() {
         var rows = grid.getSelecteds();
         if (rows.length == 0) {
             nui.alert("请选中一条记录！");
             return;
         }
         if (rows.length > 1) {
             nui.alert("不可选择多条记录！");
             return;
         }
         var row = grid.getSelected();
         var id = row.ID;
         window.open("<%=basePath%>jsp/jyjc/rzrk/zzrd/sqs/yqsbsaveword.jsp?psbgId=" + id);
    }*/

    function scword() {
        var rows = grid.getSelecteds();
        if (rows.length == 0) {
            nui.alert("请选中一条记录！");
            return;
        }
        if (rows.length > 1) {
            nui.alert("不可选择多条记录！");
            return;
        }
        var row = grid.getSelected();
        var cddz = row.CDDZ;
        var issp = row.ISSP;
        var id = row.ID;
        $.ajax({
            url: "<%=basePath%>apiShiYsJyjcNl_scyqsbword.action",
            type: 'post',
            cache: false,
            data: {serialNumber: serialNumber, cddz: cddz, isSp: issp, id: id},
            success: function (text) {
                window.open("<%=basePath%>" + text + "&fileName=" + encodeURI(encodeURI(row.FILETITLE)));
            }
        });
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

    function editEvent(param) {
        window.open("<%=basePath%>jsp/jyjc/rzrk/zzrd/sqs/editYqsb.jsp?" + param);
    }

    // 设置 预览 下载
    function ondrawcellEvent(e) {
        var field = e.field;
        var record = e.record;
        var param = "table=SHIYSJYJCXCHCPSBGNLHZ&id=" + record.ID;
        if ("DOWNLOADFILE" == field) {
            e.cellHtml =
                '<a class="nui-button mini-button-c1" style="cursor: pointer" href="<%=basePath%>jsp/jyjc/rzrk/zzrd/sqs/fileDownLoad.jsp?' + param + '">下载</a>';
        }
        if ("LOOKFILE" == field) {
            e.cellHtml =
                '<a class="nui-button mini-button-c1" onclick="previewEvent(\'' + param + '\')" href="javascript:void(0)">预览</a>';
        }
        /*  if ("EDITFILE" == field) {
              e.cellHtml =
                  '<a class="nui-button mini-button-c1" onclick="editEvent(\'' + param + '\')" style="cursor: pointer" href="javascript:void(0)">编辑</a>';
              if (action == "detail") {
                  e.cellHtml =
                      '<a class="nui-button mini-button-c1" style="color: #7D808D" href="javascript:void(0)">编辑</a>';
              }
          }*/
        if ("DELETEFILE" == field) {
            e.cellHtml =
                '<a class="nui-button mini-button-c1" onclick="deleteEvent(\'' + record.ID + '\')" href="javascript:void(0)"">删除</a>';
            if (action == "detail") {
                e.cellHtml =
                    '<a class="nui-button mini-button-c1" style="color: #7D808D" href="javascript:void(0)">删除</a>';
            }
        }
    }

    if (action == "detail") {
        nui.get('uploadInfo').hide();
        nui.get('scword').setEnabled(false);
    }

    function deleteEvent(id) {
        nui.confirm("如果删除Excel文件，需要重新上传Excel文件!", "提醒", function (action) {
            if ("ok" == action) {
                $.ajax({
                    url: "<%=basePath%>apiShiYsJyjcNl_deleteYqsbSbptEvent.action",
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

    initCheck();

    function initCheck() {
        $.ajax({
            url: "<%=basePath%>bizApiShiysjyjc_initLoad.action",
            type: 'post',
            cache: 'false',
            async: false,
            data: {serialNumber: serialNumber},
            success: function (resultData) {
                resultData = nui.decode(resultData);
                // 没有查询到数据
                if (resultData.code != 0) {
                    nui.alert(resultData.msg);
                    nui.get("uploadInfo").setEnabled(false);
                    return;
                }
            }
        });
    }
</script>
</body>
</html>