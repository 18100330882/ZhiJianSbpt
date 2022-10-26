<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>申请书保存页面</title>
    <script type="text/javascript" src="<%=basePath %>nui/nui.js"></script>
    <script type="text/javascript" src="<%=basePath %>style/scripts/fn.js"></script>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<body>
<div id="hid" style="color:red;" align="center"><a><b>注意事项：申请书打印时请按显示顺序打印装订</b></a></div>
<div id="datagrid1" class="nui-datagrid" style="width:100%;height:95%;" allowAlternating="true" allowResize="true"
     idField="id" multiSelect="true"
     showPager="false" ondrawcell="onDrawCell">
    <div property="columns">
        <div type="indexcolumn" width="3%">序号</div>
        <div field="NAME" headerAlign="center" allowSort="true" width="50%" align="center">申请书附表名称</div>
        <div field="ISSAVE" headerAlign="center" align="center" width="10%" renderer="isSave">是否保存</div>
        <div field="CAOZUO" align="center" headerAlign="center" allowSort="true" width="37%">操作</div>
    </div>
</div>
<script type="text/javascript">
    nui.parse();
    var grid = nui.get("datagrid1");
    var flag = "";
    var serialNumber;

    function SetData(data) {
        var data = nui.clone(data);
        flag = data.flag;
        row = data.row;
        serialNumber = row.SERIALNUMBER;
        grid.load("<%=basePath %>shiYsJyjcFuJian_initLoad.action?flag=" + flag + "&serialNumber=" + serialNumber);
        grid.reload();
    }

    function isSave(e) {
        var record = e.record;
        var isSave = record.ISSAVE;
        var result = "❌";
        if (isSave == "1") {
            result = "√";
        }
        return result;
    }

    function onDrawCell(e) {
        field = e.field;
        var record = e.record;
        var id = record.ID;
        var isSave = record.ISSAVE;
        var lookHtml = '<a id="lookSqs" class="nui-button mini-button-c1" style="cursor: pointer" onclick="lookSqs(' + id + ')">查看</a>';
        var saveHtml = '<a id="saveSqs" class="nui-button mini-button-c1" style="cursor: pointer" onclick="saveSqs()">保存</a>';
        var downLoadHtml = '<a id="downLoadSqs" class="nui-button mini-button-c1" style="cursor: pointer" onclick="downLoadSqs(' + id + ')">下载</a>';
        if (field == "CAOZUO") {
            if (id != null && id != "") {
                // 未保存
                if (isSave != "1") {
                    e.cellHtml = saveHtml;
                } else {
                    e.cellHtml = lookHtml + saveHtml + downLoadHtml;
                }
            } else {
                e.cellHtml = saveHtml;
            }
        }
    }

    //下载申请书
    function downLoadSqs(sysFjId) {
        location.href = "<%=basePath%>jsp/jyjc/rzrk/zzrd/fuJian/JyjcSqsFujianDownload.jsp?FjID=" + sysFjId
    }

    //保存申请书
    function saveSqs() {
        window.open("<%=basePath%>jsp/jyjc/rzrk/zxgl/sqs/JgzxSqsPrintSave.jsp?serialNumber=" + serialNumber + "&flag=" + flag);
        CloseWindow();
    }

    //查看申请书
    function lookSqs(sysFjId) {
        nui.ajax({
            url: "<%=basePath%>shiYsJyjcFuJian_getJyjcFuJianById.action",
            data: {id: sysFjId},
            type: "post",
            async: false,
            success: function (text) {
                var data = nui.decode(text);
                if (data != 1) {
                    var filePath = data.filePath, // 本地路径
                        fileType = data.fileExtension, // 附件类型
                        serialNumber = data.serialNumber,// 流水号
                        fileName = data.fileTitle;// 文件名
                }
                var param = "filePath=" + filePath + "&fileType=" + fileType + "&serialNumber=" + serialNumber + "&fileName=" + fileName
                window.open("<%=basePath%>jsp/common/showDoc.jsp?" + param)
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
</script>
</body>
</html>