<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">
    <title>申请类型</title>
    <script type="text/javascript" src="<%=basePath %>nui/nui.js"></script>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <style type="text/css">
        .mini-errorIcon{
            margin-top: 0px !important;
        }
    </style>
</head>

<body>
<form id="form1" method="post">
    <input type="hidden" id="serialNumber" class="nui-hidden" name="serialNumber" value=""/>
    <h1 align="center" style="margin-top:10px">申请类型</h1>
    <div style="margin:auto; padding:5px;">
        <table align="center" border="0" width="100%">
            <tr height="40px">
                <td align="right" style="width: 140px">资质认定：<span style="color:red;">*</span></td>
                <td>
                    <input id="applicationType" name="applicationType" class="nui-checkboxlist" textField="text"
                           valueField="text" width="100%" required="true" />
                </td>
            </tr>
        </table>
        <div>
            <a id="saveSqlx" class="nui-button" iconCls="icon-save" onclick="ok()" style="margin-left: 45%;margin-top:10%">确定</a>
        </div>
    </div>
</form>
<script type="text/javascript">
    nui.parse();
    var form = new nui.Form("form1");
    //流水号
    var serialNumber = '<%=request.getParameter("serialNumber")%>';
    nui.get("serialNumber").setValue(serialNumber);
    nui.get("applicationType").setData("[{'text':'首次'},{'text':'扩项'},{'text':'地址变更'},{'text':'复查'},{'text':'其他'},{'text':'标准变更'},{'text':'授权签字人变更'}]");

    //回写
    initLoad();
    function initLoad() {
        $.ajax({
            url: "<%=basePath%>apiShiYsJyjcSqlx_initLoad.action",
            type: 'post',
            data: {serialNumber: serialNumber},
            cache: false,
            success: function (text) {
                var map = nui.decode(text);
                if (map.code == 0) {
                    var data = map.data;
                    var applicationType = data.applicationType;
                    nui.get("applicationType").setValue(applicationType);
                    return;
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {//如果不能获得数据
                nui.alert(jqXHR.responseText);//弹出错误
                CloseWindow();
            }
        });
    }

    function ok() {
        if (serialNumber == null || serialNumber == "") {
            nui.alert("生成流水号失败！请关闭刷新重试！", "提醒！");
            return;
        }
        form.validate();
        if (form.isValid() == false) return;
        var applicationType = nui.get("applicationType").getValue();
        var formData = form.getData();
        var reqJson = nui.encode(formData);
        $.ajax({
            url: "<%=basePath%>apiShiYsJyjcSqlx_saveSqlx.action",
            type: 'post',
            cache: 'false',
            async: false,
            data: {data: reqJson},
            success: function (resultData) {
                resultData = nui.decode(resultData);
                nui.alert(resultData.msg, "提示!",function () {
                    CloseWindow(applicationType);
                });
            }
        });
    }

    function CloseWindow(action) {
        if (window.CloseOwnerWindow)
            return window.CloseOwnerWindow(action);
        else
            window.close();
    }


</script>
</body>
</html>