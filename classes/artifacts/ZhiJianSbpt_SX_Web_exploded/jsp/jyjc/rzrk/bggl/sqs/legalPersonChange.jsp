<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>检验检测机构法人性质变更审批表</title>
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
    <h1 align="center" style="margin-top:10px">检验检测机构法人性质变更审批表</h1>
    <input id="id" name="id" class="nui-hidden"/>
    <input id="changeType" name="changeType" class="nui-hidden"/>
    <fieldset style="border: solid 1px #aaa; padding: 3px; width:100%;">
        <div style="width:100%;">
            <table style="margin-left: 20%">
                <tr>
                    <td align="right" style="">检验检测机构名称：<span style="color:red;">*</span></td>
                    <td colspan="3">
                        <input id="jgName" name="jgName" class="nui-textbox" style="width: 100%" allowInput="false" required="true"/>
                    </td>
                    <td align="right" style="">申请日期：<span style="color:red;">*</span></td>
                    <td colspan="2">
                        <input style="width: 100%" id="applyDate" name="applyDate" class="nui-datepicker" required="true" EmptyText="请选择日期" allowInput="false"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">法人性质变更 （适用于法人单位） ：<span style="color:red;">*</span></td>
                    <td colspan="3">
                        <input id="natureChange" name="natureChange" class="nui-textbox" style="width: 100%" allowInput="true" required="true"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">原法人性质：<span style="color:red;">*</span></td>
                    <td colspan="2">
                        <input id="natureOld" name="natureOld" class="nui-textbox" style="width: 100%" allowInput="true" required="true"/>
                    </td>
                    <td align="right" style="">变更后法人性质：<span style="color:red;">*</span></td>
                    <td colspan="2">
                        <input id="natureNew" name="natureNew" class="nui-textbox" style="width: 100%" allowInput="true" required="true"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">备注：<span style="color:red;">*</span></td>
                    <td colspan="5" style="height: 72px">
                        <input id="natureRemark" name="natureRemark" class="nui-textarea" style="width: 100%;height: 100%" allowInput="true" required="true"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">所在法人单位性质变更 ：<span style="color:red;">*</span></td>
                    <td colspan="3">
                        <input id="unitChange" name="unitChange" class="nui-textbox" style="width: 100%" allowInput="true" required="true"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">原法人单位性质：<span style="color:red;">*</span></td>
                    <td colspan="2">
                        <input id="unitOld" name="unitOld" class="nui-textbox" style="width: 100%" allowInput="true" required="true"/>
                    </td>
                    <td align="right" style="">变更后法人单位性质：<span style="color:red;">*</span></td>
                    <td colspan="2">
                        <input id="unitNew" name="unitNew" class="nui-textbox" style="width: 100%" allowInput="true" required="true"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">备注：<span style="color:red;">*</span></td>
                    <td colspan="5" style="height: 72px">
                        <input id="unitRemark" name="unitRemark" class="nui-textarea" style="width: 100%;height: 100%" allowInput="true" required="true"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">所在法人单位名称变更 ：<span style="color:red;">*</span></td>
                    <td colspan="3">
                        <input id="unitNameChange" name="unitNameChange" class="nui-textbox" style="width: 100%" allowInput="true" required="true"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">原法人单位名称：<span style="color:red;">*</span></td>
                    <td colspan="3">
                        <input id="unitNameOld" name="unitNameOld" class="nui-textbox" style="width: 100%" allowInput="true" required="true"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">变更后法人单位名称：<span style="color:red;">*</span></td>
                    <td colspan="3">
                        <input id="unitNameNew" name="unitNameNew" class="nui-textbox" style="width: 100%" allowInput="true" required="true"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">备注：<span style="color:red;">*</span></td>
                    <td colspan="5" style="height: 72px">
                        <input id="nuitNameRemark" name="nuitNameRemark" class="nui-textarea" style="width: 100%;height: 100%" allowInput="true" required="true"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">联系人：<span style="color:red;">*</span></td>
                    <td colspan="3">
                        <input id="lxrName" name="lxrName" class="nui-textbox" style="width: 50%" allowInput="true" required="true"/>
                    </td>
                    <td align="right" style="">手机：<span style="color:red;">*</span></td>
                    <td colspan="2">
                        <input id="lxrTel" name="lxrTel" class="nui-textbox" style="width: 100%" allowInput="true" required="true"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">通信地址：<span style="color:red;">*</span></td>
                    <td colspan="3">
                        <input id="address" name="address" class="nui-textbox" style="width: 100%" allowInput="true" required="true"/>
                    </td>
                    <td align="right" style="">邮箱：<span style="color:red;">*</span></td>
                    <td colspan="2">
                        <input style="width: 100%" id="email" name="email" class="nui-textbox" allowInput="true"
                               required="true"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">资质认定部门意见：</td>
                    <td height="52px" colspan="3">
                        <input id="deptOpinion" name="deptOpinion" class="nui-textarea" style="width:100%;height: 100%;"
                               allowInput="false"/>
                    </td>
                    <td align="right" style="">日期：</td>
                    <td colspan="2">
                        <input id="deptOpinionDate" name="deptOpinionDate" class="nui-datepicker" style="width:100%;"
                               allowInput="false"/>
                    </td>
                </tr>
            </table>
            <div style="margin-left: 30%"><span style="color: red">注：法人性质分为：行政单位、事业单位、企业、其他组织，其他组织需在备注中予以详细说明。</span>
            </div>
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

    changeApplyInfoInit()

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
                    nui.get("applyDate").setValue(data.sqrq);
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {//如果不能获得数据
                nui.alert(jqXHR.responseText);//弹出错误
                onCancel();
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