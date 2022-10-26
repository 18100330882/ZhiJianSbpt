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
    <script type="text/javascript" src="<%=basePath%>nui/nui.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>

<body>
<form id="form1" target="id_iframe" style="padding-top:5px;margin:0 auto; width:840px; border:1px;">
    <h1 align="center" style="margin-top:10px">本次申请地点</h1>
    <input type="hidden" id="serialNumber" class="nui-hidden" name="serialNumber" value=""/>
    <input type="hidden" id="id" class="nui-hidden" name="id" value=""/>
    <table border="0">
        <tr>
            <td align="right" style="width:230px;">请输入场地地址：</td>
            <td colspan="3">
                <input name="siteAddress" id="siteAddress" class="nui-textbox" width="547px" required="true"/>
            </td>
        </tr>
    </table>
    <div>
        <a id="saveCd" class="nui-button" iconCls="icon-save" onclick="saveCd()"
           style="margin-left: 45%;margin-top:10%">保存</a>
    </div>
</form>
<script type="text/javascript">
    nui.parse();
    var form = new nui.Form("form1");

    var row;
    var action;

    function SetData(data) {
        var data = nui.clone(data);
        serialNumber = data.serialNumber;
        nui.get("serialNumber").setValue(serialNumber);
        action = data.action;
        if (action == 'edit') {
            row = data.row;
            nui.get("id").setValue(row.id);
            nui.get("siteAddress").setValue(row.siteAddress);
        }
    }

    function saveCd() {
        if (serialNumber == null || serialNumber == "") {
            nui.alert("生成流水号失败！请关闭刷新重试！", "提醒！");
            return;
        }

        var formData = form.getData();
        form.validate();
        if (form.isValid() == false) {
            return;
        }
        var reqJson = nui.encode(formData);
        $.ajax({
            url: "<%=basePath%>apiShiYsJyjcCd_save.action",
            type: 'post',
            cache: 'false',
            async: false,
            data: {data: reqJson},
            success: function (resultData) {
                resultData = nui.decode(resultData);
                nui.alert(resultData.msg, "提示!", function () {
                    CloseWindow();
                });
            }
        });
    }

    function CloseWindow() {
        if (window.CloseOwnerWindow) {
            return window.CloseOwnerWindow("cancel");
        } else {
            window.close();
        }
    }
</script>
</body>
</html>