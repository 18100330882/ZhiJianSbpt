<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>概况</title>
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
    <input id="id" name="ID" class="nui-hidden" required="true" width="300px" readonly="readonly"/>
    <table>
        <tr hidden="hidden">
            <td align="right" style="width:100px;">机构名称：<span style="color:red;">*</span></td>
            <td colspan="3">
                <input id="gsmc" name="GSMC" class="nui-textbox" width="150px"
                       allowInput="true"
                       required="true" readonly="readonly"/>
            </td>

        </tr>
        <tr hidden="hidden">
            <td align="right" style="width:100px;">证书编号：<span style="color:red;">*</span></td>
            <td colspan="3">
                <input id="zsbh" name="ZSBH" class="nui-textbox" width="150px"
                       allowInput="true"
                       required="true" readonly="readonly"/>
            </td>
        </tr>
        <tr>
            <td align="right" style="width:190px;">一级分类：<span style="color:red;">*</span></td>
            <td colspan="3">
                <input id="yjfl" name="YJFL" class="nui-textbox" width="500px"
                       allowInput="true"
                       required="true"/>
            </td>
        </tr>
        <tr>
            <td align="right" style="width:190px;">二级分类：<span style="color:red;">*</span></td>
            <td colspan="3">
                <input id="ejfl" name="EJFL" class="nui-textbox" width="500px"
                       required="true"/>
            </td>
        </tr>
        <tr>
            <td align="right">项目名称：<span style="color:red;">*</span></td>
            <td>
                <input id="productname" name="PRODUCTNAME" class="nui-textbox" width="500px"
                       required="true"/>
            </td>
        </tr>
        <tr>
            <td align="right">地址：<span style="color:red;">*</span></td>
            <td>
                <input id="sitename" name="SITENAME" class="nui-textbox" width="500px"
                       required="true"/>
            </td>
        </tr>
        <tr>
            <td align="right">限制范围：</td>
            <td>
                <input id="limits" name="LIMITS" class="nui-textbox" width="500px"/>
            </td>
        </tr>
        <tr>
            <td align="right">说明：</td>
            <td>
                <input id="instructions" name="INSTRUCTIONS" class="nui-textbox" width="500px"/>
            </td>
        </tr>

        <tr>
            <td align="right">标准：<span style="color:red;">*</span></td>
            <td>
                <input id="yjbz" name="YJBZ" class="nui-textarea" width="500px" height="150px"
                       required="true"/>
            </td>
        </tr>
        <tr>
            <td align="right">批准日期：<span style="color:red;">*</span></td>
            <td>
                <input id="pizhundate" name="PIZHUNDATE" class="nui-datepicker" width="180px"
                       required="true"/>
            </td>
        </tr>
    </table>
    <div>
        <a id="saveGk" class="nui-button" iconCls="icon-save" onclick="SaveData()"
           style="margin-left: 45%;margin-top:3%">保存</a>
    </div>
</form>

<script type="text/javascript">
    nui.parse();
    var form = new nui.Form("form1");

    function SaveData() {
        var o = form.getData();
        form.validate();
        if (form.isValid() == false) {
            nui.alert('请完善信息', '系统提示');
            return
        }
        var json = nui.encode(o);
        $.ajax({
            url: "<%=basePath%>apiShiYsJyjcNl_updateNl.action",
            type: 'post',
            data: {json: json},
            cache: false,
            success: function (text) {
                nui.alert("保存成功！", "提醒", function () {
                    CloseWindow("save");
                });
            }
        });
    }

    function CloseWindow(action) {
        if (action == "close" && form.isChanged()) {
            if (confirm("数据被修改了，是否先保存？")) {
                return false;
            }
        }
        if (window.CloseOwnerWindow) {
            return window.CloseOwnerWindow(action);
        } else {
            window.close();
        }
    }

    function onCancel() {
        CloseWindow();
    }

    function SetData(data) {

        var row = data.row;
        console.log(row)
        form.setData(row);
    }


</script>

</body>
</html>