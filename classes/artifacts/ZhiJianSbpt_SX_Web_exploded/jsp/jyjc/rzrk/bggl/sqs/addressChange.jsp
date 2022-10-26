<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>检验检测机构资质认定地址名称变更审批表</title>
    <script type="text/javascript" src="<%=basePath%>nui/nui.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>

<body>
<form id="form" target="id_iframe" style="padding-top:5px;margin:0 auto; width:95%; border:1px;">
    <h1 align="center" style="margin-top:10px">检验检测机构资质认定地址名称变更审批表</h1>
    <input id="id" name="id" class="nui-hidden"/>
    <input id="changeType" name="changeType" class="nui-hidden"/>
    <fieldset style="border: solid 1px #aaa; padding: 3px; width:95%;">
            <table width="100%">
                <tr>
                    <td align="right">检验检测机构名称：<span style="color:red;">*</span></td>
                    <td width="40%">
                        <input id="jgName" name="jgName" class="nui-textbox" width="100%" allowInput="true"
                               required="true"/>
                    </td>
                    <td align="right" width="10%">申请日期：<span style="color:red;">*</span></td>
                    <td>
                        <input id="applyDate" name="applyDate" class="nui-datepicker" style="width:50%" required="true"
                               EmptyText="请选择日期" allowInput="false"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">证书编号：<span style="color:red;">*</span></td>
                    <td>
                        <input id="cmaPermitNum" name="cmaPermitNum" class="nui-textbox" width="50%" allowInput="true"
                               required="true" readonly="readonly"/>
                    </td>
                    <td align="right" style="">有效日期：<span style="color:red;">*</span></td>
                    <td>
                        <input id="certificateEfficientDate" name="certificateEfficientDate" width="50%"
                               class="nui-datepicker" required="true" EmptyText="请选择日期" allowInput="false"
                               readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">原地址名称：<span style="color:red;">*</span></td>
                    <td>
                        <input id="addressOld" name="addressOld" class="nui-textbox" style="width:100%"
                               allowInput="true" required="true"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">拟变更的地址名称：<span style="color:red;">*</span></td>
                    <td>
                        <input id="addressNew" name="addressNew" class="nui-textbox" style="width:100%" allowInput="true" required="true"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">地址名称变更原因：<span style="color:red;">*</span></td>
                    <td>
                        <input id="addressChangeReason" name="addressChangeReason" class="nui-textarea" style="width:100%;height: 100%;" allowInput="true" required="true"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">联系人：<span style="color:red;">*</span></td>
                    <td>
                        <input id="lxrName" name="lxrName" class="nui-textbox" style="width: 50%" allowInput="true" required="true"/>
                    </td>
                    <td align="right" style="">手机：<span style="color:red;">*</span></td>
                    <td>
                        <input id="lxrTel" name="lxrTel" class="nui-textbox" style="width:50%;" allowInput="true" required="true"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">通信地址：<span style="color:red;">*</span></td>
                    <td>
                        <input id="address" name="address" class="nui-textbox" style="width:100%;" allowInput="true" required="true"/>
                    </td>
                    <td align="right" style="">传真：<span style="color:red;">*</span></td>
                    <td>
                        <input id="fax" name="fax" class="nui-textbox" style="width:50%;" allowInput="true"
                               required="true"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">资质认定部门意见：</td>
                    <td height="52px">
                        <input id="deptOpinion" name="deptOpinion" class="nui-textarea" style="width:100%;height: 100%;"
                               allowInput="true"/>
                    </td>
                    <td align="right" style="">日期：</td>
                    <td>
                        <input id="deptOpinionDate" name="deptOpinionDate" class="nui-datepicker" style="width:50%;"
                               allowInput="false"/>
                    </td>
                </tr>
            </table>
        <div style="margin-left: 15%"><span style="color: red">注：①本表仅适用于机构实际地址不变，但地址名称发生变化的情况；若实际地址发生变更时，需提交申请书，由资质认定部门现场考核确认；</span>
        </div>
        <div style="margin-left: 15%"><span
                style="color: red;margin-left: 28px">②随本申请表提交的材料如下：需提供地址名称变更证明文件、原资质认定证书复印件；</span></div>
        <div style="margin-left: 15%"><span style="color: red;margin-left: 28px">③需一并提交地址名称变更后的新证书附表电子版。</span></div>
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

    if ("detail" == action) {
        nui.get("save").hide()
    }

    changeApplyInfoInit();

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
                    nui.get("applyDate").setValue(data.sqrq);
                    nui.get("cmaPermitNum").setValue(data.cmaPermitNumber);
                    nui.get("certificateEfficientDate").setValue(data.cmaPermitEffectiveDate);
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
                var Jgobject = quertyJgobjectByZsbh(zsbh);
                console.log(Jgobject)
                form.setData(resultData.data);
                nui.get('addressOld').setValue(Jgobject.ZHUSUO);
                nui.get("cmaPermitNum").setValue(zsbh);
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
                nui.alert(result.msg, "提醒！");
            },
        });
    }

    function quertyJgobjectByZsbh(zsbh) {
        var Jgobject = {};
        $.ajax({
            url: "<%=basePath%>collect_queryByZsbh.action",
            type: 'post',
            cache: 'false',
            async: false,
            data: {zsbh: zsbh},
            success: function (result) {
                result = nui.decode(result);
                if (result.data != undefined) {
                    Jgobject = result.data;
                }
            }
        });
        return Jgobject;
    }
</script>
</body>
</html>