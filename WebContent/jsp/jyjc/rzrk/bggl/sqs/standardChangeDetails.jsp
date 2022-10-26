<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>检验检测机构资质认定标准（方法）变更审批表</title>
    <script type="text/javascript" src="<%=basePath%>nui/nui.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>

<body>
<form id="form" target="id_iframe" style="padding-top:5px;margin:0 auto; width:100%; border:1px;">
    <h1 align="center" style="margin-top:10px">检验检测机构资质认定标准（方法）变更审批表</h1>
    <input id="id" name="id" class="nui-hidden"/>
    <div style="width:100%;">
        <table style="width: 100%;">
            <tr>
                <td align="right" style="width: 40%">序号：</td>
                <td>
                    <input id="sortNum" name="sortNum" class="nui-textbox" style="width:50%" required="true"
                           readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td align="right" style="">类别(产品/项目/参数)：</td>
                <td>
                    <input id="category" name="category" class="nui-textbox" style="width:90%" required="true"
                           readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td align="right" style="">已批准的标准（方法）名称、 编号（含年号） ：</td>
                <td>
                    <input id="standardNameAndNumOld" name="standardNameAndNumOld" class="nui-textarea"
                           style="width: 90%;height: 100%" required="true" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td align="right" style="">变更后的标准（方法）名称、编号（含年号）：<span style="color:red;">*</span></td>
                <td>
                    <input id="standardNameAndNumNew" name="standardNameAndNumNew" class="nui-textarea"
                           style="width: 90%;height: 100%" required="true"/>
                </td>
            </tr>
            <tr>
                <td align="right" style="">变更内容：<span style="color:red;">*</span></td>
                <td>
                    <input id="changeContent" name="changeContent" class="nui-textbox" width="50%" allowInput="true"
                           required="true"/>
                </td>
            </tr>
            <tr>
                <td align="right" style="">限制范围：<span style="color:red;">*</span></td>
                <td>
                    <input id="xzfw" name="limitRange" class="nui-textbox" width="50%" allowInput="true"
                           required="true"/>
                </td>
            </tr>
        </table>
        <div><span style="color: red">注：①“序号、资质认定项目名称”应与《证书附表》一致；</span></div>
        <div><span style="color: red;margin-left: 28px">②如标准（方法）仅为年号、编号变化，或变更的内容不涉及实际检验检测能力变化，可填写此表；</span></div>
        <div><span style="color: red;margin-left: 28px">③机构如选择自我承诺的方式，资质认定部门无需组织专业技术评价组织/专家审查，直接批准，在后续监督管理中对被审批单位承诺内容是否属实进行检查，发现承诺内容不实，资质认定部门将撤销审批决定，并将相关情况记入诚信档案。</span>
        </div>

    </div>
    <div>
        <a id="saveGk" class="nui-button" iconCls="icon-save" onclick="save()"
           style="margin-left: 45%;margin-top: 10px">保存</a>
    </div>
    <%--</fieldset>--%>
</form>

<script type="text/javascript">
    nui.parse();
    var form = new nui.Form("form");
    var changeType = "";

    function SetData(data) {
        var data = nui.clone(data);
        changeType = data.changeType;
        serialNumber = data.serialNumber;
        id = data.id;
        if (id != null && id != "") {
            $.ajax({
                url: "<%=basePath%>changeApplyInfo_changeApplyInfoDetailsInit.action",
                type: 'post',
                cache: 'false',
                async: false,
                data: {changeType: changeType, id: id},
                success: function (result) {
                    result = nui.decode(result);
                    if (result.code != 0) {
                        nui.alert(result.msg);
                        return;
                    }
                    form.setData(result.data);
                },
            });
        }
    }

    function save() {
        /* var sortNum = nui.get("sortNum").getValue();
         if (isNaN(sortNum)) {
             nui.alert("序号不是数字类型！", "提醒！");
             return;
         }*/

        var formData = form.getData();
        form.validate();
        if (form.isValid() == false) return;
        $.ajax({
            url: "<%=basePath%>changeApplyInfo_changeApplyInfoDetailsSave.action",
            type: 'post',
            cache: 'false',
            async: false,
            data: {changeType: changeType, serialNumber: serialNumber, row: nui.encode(formData)},
            success: function (result) {
                result = nui.decode(result);
                nui.alert(result.msg, "提醒!", function (e) {
                    CloseWindow();
                });
            },
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