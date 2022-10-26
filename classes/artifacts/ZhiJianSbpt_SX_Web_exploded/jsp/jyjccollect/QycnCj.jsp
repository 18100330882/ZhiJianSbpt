<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">
    <title>企业承诺</title>
    <script type="text/javascript" src="<%=basePath %>nui/nui.js"></script>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>

<body>
<div id="form1" method="post">
    <table align="center" style="font-size:15px;" border="0">
        <tr height="50px">
            <td colspan="2">
                &nbsp;&nbsp;&nbsp;&nbsp;一、所填写申请的相关信息真实、准确；
            </td>
        </tr>
        <tr height="40px">
            <td colspan="2">
                &nbsp;&nbsp;&nbsp;&nbsp;二、已经阅读和知悉资质认定部门告知的全部内容；
            </td>
        </tr>
        <tr height="40px">
            <td colspan="2">
                &nbsp;&nbsp;&nbsp;&nbsp;三、本机构能够按照“公正、科学、准确、诚信”的原则开展采集活动；
            </td>
        </tr>
        <tr height="40px">
            <td colspan="2">
                &nbsp;&nbsp;&nbsp;&nbsp;四、所作承诺是本机构的真实意思表示。
            </td>
        </tr>
        <tr height="40px">
            <td colspan="2">
                &nbsp;&nbsp;&nbsp;&nbsp;如提供虚假材料或者隐瞒有关情况，所造成的一切后果由本单位承担。
            </td>
        </tr>
        <tr>
            <td align="right">
                <div id="checkbox" class="nui-checkbox" text="我已阅读并同意" checked="false" oncheckedchanged="onValueChanged"></div>
            </td>
            <td width="50%">
                <a id="regbtn" class="nui-button" onclick="ok" iconCls="icon-save" enabled="false">确定</a>
            <td>
        </tr>
    </table>
</div>
<script type="text/javascript">
    nui.parse()
    var regt = nui.get("regbtn");//注册按钮
    var form = new nui.Form("form1");

    //当选择框被选择时
    function onValueChanged(e) {
        var checked = this.getChecked();
        if (checked) {
            regt.enable();//启动注册按钮
        } else {
            regt.disable();//禁用按钮
        }
    }

    function GetData() {
        return nui.get("checkbox").getValue();
    }

    function ok() {
        CloseWindow("ok");
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