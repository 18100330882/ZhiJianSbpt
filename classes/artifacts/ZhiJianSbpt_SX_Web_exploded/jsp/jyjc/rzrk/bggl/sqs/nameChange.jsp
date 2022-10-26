<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>检验检测机构资质认定名称变更审批表</title>
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
    <h1 align="center" style="margin-top:10px">检验检测机构资质认定名称变更审批表</h1>
    <input id="id" name="id" class="nui-hidden"/>
    <input id="jgName" name="jgName" class="nui-hidden"/>
    <input id="changeType" name="changeType" class="nui-hidden"/>
    <fieldset style="border: solid 1px #aaa; padding: 3px;">
        <div style="width:100%;">
            <table style="margin-left: 12%">
                <tr>
                    <td align="right" width="40%">原资质认定获证名称 ：<span style="color:red;">*</span></td>
                    <td colspan="5">
                        <input id="nameOld" name="nameOld" class="nui-textbox" width="100%" required="true"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">证书编号：<span style="color:red;">*</span></td>
                    <td colspan="3">
                        <input id="cmaPermitNum" name="cmaPermitNum" class="nui-textbox" width="60%" required="true"
                               readonly="readonly"/>
                    </td>
                    <td align="right" style="">有效日期：<span style="color:red;">*</span></td>
                    <td>
                        <input id="certificateEfficientDate" name="certificateEfficientDate" class="nui-datepicker"
                               style="width:100%" required="true" EmptyText="请选择日期" allowInput="false"
                               readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">拟变更的名称：<span style="color:red;">*</span></td>
                    <td colspan="5">
                        <input id="nameNew" name="nameNew" class="nui-textbox" style="width:100%" allowInput="true"
                               required="true"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">更名原因：<span style="color:red;">*</span></td>
                    <td style="height: 70px" colspan="5">
                        <input id="nameChangeReason" name="nameChangeReason" class="nui-textarea" style="width:100%;height: 100%;" allowInput="true" required="true"/>
                    </td>
                </tr>

                <tr>
                    <td align="right" style="">联系人：<span style="color:red;">*</span></td>
                    <td>
                        <input id="lxrName" name="lxrName" class="nui-textbox" allowInput="true" required="true"/>
                    </td>
                    <td align="right" style="">手机：<span style="color:red;">*</span></td>
                    <td>
                        <input id="lxrTel" name="lxrTel" class="nui-textbox" allowInput="true" required="true"/>
                    </td>
                    <td align="right" style="">传真：<span style="color:red;">*</span></td>
                    <td>
                        <input id="fax" name="fax" class="nui-textbox" width="100%" allowInput="true" required="true"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">通信地址：<span style="color:red;">*</span></td>
                    <td colspan="3">
                        <input id="address" name="address" class="nui-textbox" style="width: 100%" allowInput="true" required="true"/>
                    </td>
                    <td align="right" style="">邮箱：<span style="color:red;">*</span></td>
                    <td>
                        <input id="email" name="email" class="nui-textbox" allowInput="true" required="true" width="100%"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">检验检测机构所属上级部门意见 ：<span style="color:red;">*</span></td>
                    <td height="52px" colspan="3">
                        <input id="superiorDeptOpinion" name="superiorDeptOpinion" class="nui-textarea" style="width:100%;height: 100%;" required="true"/>
                    </td>
                    <td align="right" style="">日期：<span style="color:red;">*</span></td>
                    <td colspan="2">
                        <input id="superiorDeptOpinionDate" name="superiorDeptOpinionDate" class="nui-datepicker"
                               required="true" EmptyText="请选择日期" allowInput="false" width="100%"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">资质认定部门意见：</td>
                    <td height="52px" colspan="3">
                        <input id="deptOpinion" name="deptOpinion" class="nui-textarea" style="width:100%;height: 100%;"
                               allowInput="true"/>
                    </td>
                    <td align="right" style="">日期：</td>
                    <td colspan="2">
                        <input id="deptOpinionDate" name="deptOpinionDate" class="nui-datepicker" style="width:100%;"
                               allowInput="false"/>
                    </td>
                </tr>
            </table>
            <div style="margin-left: 35%;margin-top: 2%"><span style="color: red">注：①如是独立法人机构，可不填上级机构意见；</span></div>
            <div><span style="color: red;margin-left: 35%">②随申请表提交的材料如下：需提供名称变更证明文件、原资质认定证书复印件。</span></div>
        </div>
    </fieldset>
    <div>
        <a id="save" class="nui-button" iconCls="icon-save" onclick="save()" style="margin-left: 45%;margin-top: 10px">保存</a>
    </div>
</form>

<script type="text/javascript">
    nui.parse();
    var form = new nui.Form("form");
    var serialNumber = "<%=request.getParameter("serialNumber")%>";
    var action = "<%=request.getParameter("action")%>";
    var changeType = "<%=request.getParameter("changeType")%>";
    var zsbh = "<%=request.getParameter("zsbh")%>";
    changeApplyInfoInit();

    if ("detail" == action) {
        nui.get("save").hide()
    }

    function fmxxInitLoad(serialNumber) {
        $.ajax({
            url: "<%=basePath%>bizApiZwcnBg_initLoad.action",
            type: 'post',
            data: {serialNumber: serialNumber},
            cache: false,
            success: function (text) {
                var map = nui.decode(text);
                if (map.code == 0) {
                    var data = map.data;
                    nui.get("jgName").setValue(data.jgmc);
                    nui.get("changeType").setValue(data.state);
                    nui.get("lxrName").setValue(data.principal);
                    nui.get("lxrTel").setValue(data.phone);
                    nui.get("cmaPermitNum").setValue(data.cmaPermitNumber);
                    nui.get("certificateEfficientDate").setValue(data.cmaPermitEffectiveDate);
                    nui.get("nameOld").setValue(data.jgmc);
                }
            }
        });
    }

    function changeApplyInfoInit() {
        $.ajax({
            url: "<%=basePath%>changeApplyInfo_changeApplyInfoInit.action",
            type: 'post',
            cache: 'false',
            async: false,
            data: {serialNumber: serialNumber, changeType: changeType},
            success: function (result) {
                result = nui.decode(result);
                var resultData = result.data;
                if (resultData.total == 0) {
                    fmxxInitLoad(serialNumber);
                    return;
                }
                form.setData(resultData.data);
                nui.get('cmaPermitNum').setValue(zsbh);
                nui.get('nameOld').setValue(resultData.data.jgName);
                nui.get('certificateEfficientDate').setValue(resultData.data.certificateEfficientDate);
            },
        });
    }

    function save() {
        var formData = form.getData();
        form.validate();
        if (form.isValid() == false) return;
        var reqJson = nui.encode(formData);
        $.ajax({
            url: "<%=basePath%>changeApplyInfo_changeApplyInfoSave.action",
            type: 'post',
            cache: 'false',
            async: false,
            data: {data: reqJson, serialNumber: serialNumber},
            success: function (result) {
                result = nui.decode(result);
                nui.alert(result.msg,"提醒！");
            },
        });
    }
</script>
</body>
</html>