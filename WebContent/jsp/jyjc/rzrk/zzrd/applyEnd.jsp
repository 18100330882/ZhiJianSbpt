<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>自主撤回办理</title>
    <script type="text/javascript" src="<%=basePath %>nui/nui.js"></script>
    <script type="text/javascript" src="<%=basePath %>style/js/bizRflag.js"></script>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <style type="text/css">
        #textarea2 {
            border: 1px solid #AED0EA;
            width: 253px;
            height: 105px;
        }

        #suggestionText {
            margin-left: 62px !important;
        }
    </style>
</head>
<body>
<form id="form" method="post" checkType="blur">
    <h1 align="center" style="margin-top:10px">终止申请</h1>
    <div id="div" style="padding: 10px;margin:10px;">
        <table cellpadding="1" cellspacing="2" style="width:100%;">
            <tr>
                <td>终止申请时间：
                    <input id="applyDate" class="nui-datepicker" dateFormat="yyyy-MM-dd" readonly/>
                </td>
            </tr>
            <tr>
                <td>终止申请原因：
                    <input id="applyReason" class="nui-textarea" style="width: 85%;height: 10%"/>
                </td>
            </tr>
        </table>
    </div>

    <div id="dataGrid" class="nui-datagrid" style="height: 200px" allowResize="false" idField="ID" multiSelect="false"
         showPager="false" pageSize="15" allowAlternating="true">
        <div property="columns">
            <div type="indexcolumn" headerAlign="center">序号</div>
            <div field="APPLYDATE" headerAlign="center" dateFormat="yyyy-MM-dd">终止申请时间</div>
            <div field="APPLYREASON" headerAlign="center">终止申请原因</div>
            <div field="APPLYUSERNAME" headerAlign="center">申请人</div>
            <div field="ZZCH_ISAGREE" headerAlign="center" renderer="isAgreeRender">办理结果</div>
            <div field="ZZCH_SUGGESTION" headerAlign="center">办理意见</div>
        </div>
    </div>
    <div style="text-align: center;margin-top: 50px">
        <a id="save" class="nui-button" onclick="save()" iconCls="icon-save">保存申请</a>
        <a id="resultAndRecord" class="nui-button" onclick="resultAndRecord()" iconCls="icon-edit">处理结果/记录</a>
        <a id="recordHide" class="nui-button" onclick="recordHide()" iconCls="icon-edit">隐藏</a>
    </div>
</form>

<script type="text/javascript">
    nui.parse();
    var grid = nui.get("dataGrid");
    var serialNumber;

    function SetData(data) {
        var row = data.row;
        row = nui.decode(row);
        serialNumber = row.SERIALNUMBER;
        var currentDate = getYearMonthDay();
        nui.get("applyDate").setValue(currentDate);
        nui.get("dataGrid").hide();
        nui.get("recordHide").hide();
        initLoad(serialNumber);
    }

    function initLoad(serialNumber) {
        $.ajax({
            url: "<%=basePath%>applyEnd_initLoad.action",
            type: 'post',
            cache: 'false',
            async: false,
            data: {serialNumber: serialNumber},
            success: function (result) {
                result = nui.decode(result);
                // 有申请数据 没有办理 不能 申请
                if (result.code == 0) {
                    nui.get("save").setEnabled(false);
                    nui.alert(result.msg, "提示！");
                }
            },
        });
    }

    function save() {
        var applyReason = nui.get("applyReason").getValue();
        var applyDate = nui.get("applyDate").getValue();
        if (applyReason == null || applyReason == "") {
            nui.alert("请输入终止申请原因！");
            return;
        }

        nui.confirm("确定此记录要终止申请处理？", "提醒！", function (action) {
            if ("ok" == action) {
                $.ajax({
                    url: "<%=basePath%>applyEnd_save.action",
                    type: 'post',
                    cache: 'false',
                    async: false,
                    data: {applyReason: applyReason, applyDate: applyDate, serialNumber: serialNumber},
                    success: function (result) {
                        result = nui.decode(result);
                        if (result.code != 0) {
                            nui.alert(result.msg, "提示!");
                            return;
                        }
                        nui.alert(result.msg, "提示!", function () {
                            CloseWindow();
                        });
                    },
                });
            }
        });
    }

    function resultAndRecord() {
        nui.get("dataGrid").show();
        nui.get("recordHide").show();
        grid.load("<%=basePath%>applyEnd_getList.action?serialNumber=" + serialNumber);
        grid.reload();
    }

    function recordHide() {
        nui.get("dataGrid").hide();
        nui.get("recordHide").hide();
    }

    function isAgreeRender(e) {
        var record = e.record;
        var result = "";
        var isAgree = record.ZZCH_ISAGREE;
        if (isAgree == 0) {
            result = "同意";
        }
        if (isAgree == 1) {
            result = "不同意";
        }
        return result;
    }

    function getYearMonthDay() {
        var result = '';
        var currentDate = new Date();
        result += currentDate.getFullYear() + '-';
        result += currentDate.getMonth() + 1 + '-';
        result += currentDate.getDate() + '';
        return result;
    }

    //关闭本窗体
    function CloseWindow() {
        if (window.CloseOwnerWindow) {
            return window.CloseOwnerWindow("cancel");
        }
        window.close();
    }
</script>
</body>
</html>