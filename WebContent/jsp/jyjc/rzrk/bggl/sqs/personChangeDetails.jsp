<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>检验检测机构人员变更备案审批表</title>
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
    <h1 align="center" style="margin-top:10px">检验检测机构人员变更备案审批表</h1>
    <input id="id" name="id" class="nui-hidden"/>
    <input id="jyjcryId" name="jyjcryId" class="nui-hidden"/>
    <input id="zsbh" name="zsbh" class="nui-hidden"/>
    <fieldset style="border: solid 1px #aaa; padding: 3px; width:100%;">
        <div style="width:100%;">
            <table width="100%" align="center">
                <tr>
                    <td align="right" width="25%">变更类型：</td>
                    <td width="20%">
                        <input name="changeType" class="nui-combobox" emptytext="请选择..." valueField="text"
                               style="width: 100%"
                               data="[{id:'1',text:'新增'},{id:'2',text:'替换'},{id:'3',text:'撤销'}]"
                               onValueChanged="changeTypeValueChange"/></td>
                </tr>
                <tr>
                    <td align="right">姓名：</td>
                    <td id="nameTd">
                        <input name="name" id="name" class="nui-textbox" allowInput="true" required="true"
                               style="width: 100%"/>
                        <%--<input id="selectName" class="nui-combobox" emptytext="请选择..." valueField="NAME" style="width: 50%"/>--%>
                        <input id="selectName" class="nui-combobox" style="width: 100%"
                               required="true" emptyText="请选择姓名" valueField="NAME" textField="NAME" allowinput="false"
                               onvaluechanged="selectNameValueChange"/>
                    </td>
                    <td align="right" style="">身份证号：</td>
                    <td>
                        <input id="textSfzh" name="sfzh" class="nui-textbox" allowInput="true" required="true"
                               style="width: 50%" onvaluechanged="queryMessage"/>
                        <input id="selectSfzh" class="nui-combobox" valueField="SFZH" textField="SFZH"
                               style="width: 50%"
                               required="true" emptyText="请选择身份证号码"/>
                    </td>
                </tr>
                <tr id="after">
                    <td align="right">变更后姓名：</td>
                    <td>
                        <input name="nameAfter" class="nui-textbox" allowInput="true" style="width: 50%"/>
                    </td>
                    <td align="right">变更后身份证号：</td>
                    <td>
                        <input name="sfzhAfter" class="nui-textbox" allowInput="true" style="width: 50%"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">年龄：</td>
                    <td>
                        <input name="age" class="nui-textbox" allowInput="true" required="true" style="width: 50%"/>
                    </td>
                    <td align="right">文化程度：</td>
                    <td>
                        <input name="education" class="nui-combobox" emptytext="请选择..." valueField="text"
                               style="width: 50%"
                               data="[{id:'1',text:'博士研究生'},{id:'2',text:'硕士研究生'},{id:'3',text:'大学本科'},{id:'4',text:'大学专科'},{id:'5',text:'中等专科'},{id:'7',text:'职业高中'},{id:'8',text:'高中'},{id:'9',text:'初中'},{id:'10',text:'小学'},{id:'11',text:'无学历'}]"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="15%">性别：</td>
                    <td>
                        <input name="sex" class="nui-radiobuttonlist" valueField="text"
                               data="[{id:'0',text:'男'},{id:'1',text:'女'}]" value=""/>
                    </td>
                    <td align="right" style="">职务（岗位）：</td>
                    <td>
                        <input name="zhiw" class="nui-textbox" allowInput="true" required="true" style="width: 50%"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">职称：</td>
                    <td>
                        <input name="zhic" class="nui-textbox" allowInput="true" required="true" style="width: 100%"/>
                    </td>
                    <td align="right" style="">所学专业：</td>
                    <td>
                        <input name="zhuanye" class="nui-textbox" allowInput="true" required="true" style="width: 50%"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">从事技术领域年限：</td>
                    <td>
                        <input name="yearOfService" class="nui-textbox" allowInput="true" required="true"
                               style="width: 100%"/>
                    </td>
                    <td align="right" style="">检验检测机构地址：</td>
                    <td>
                        <input name="jgAddress" class="nui-textbox" allowInput="true" required="true"
                               style="width: 50%"/>
                    </td>
                </tr>
            </table>
            <div><span style="color: red">注：①此表一式二份，检验检测机构和资质认定部门分别留存；</span></div>
            <div><span style="color: red;margin-left: 28px">②职务类型包括最高管理者、技术负责人、授权签字人，变更类型包括：替换、新增、撤销；</span></div>
            <div><span style="color: red;margin-left: 28px">③最高管理者变更时，需同时提供相关任命文件及法人授权书，无需批准，直接备案；</span></div>
            <div><span style="color: red;margin-left: 28px">⑤授权签字人变更时，需同时提供授权签字人申请表，经批准后，可签发检验检测报告或证书。</span></div>
        </div>
    </fieldset>
    <div>
        <a id="saveGk" class="nui-button" iconCls="icon-save" onclick="save()"
           style="margin-left: 45%;margin-top: 10px">保存</a>
    </div>
</form>

<script type="text/javascript">
    nui.parse();
    var form = new nui.Form("form");
    var selectName = nui.get("selectName");
    var changeType = "";
    var serialNumber = "";
    var zsbh = '';

    function SetData(data) {
        var data = nui.clone(data);
        changeType = data.changeType;
        serialNumber = data.serialNumber;
        zsbh = data.zsbh;
        id = data.id;
        $("#selectName").hide();
        $("#selectSfzh").hide();
        $("#textSfzh").show();
        selectName.load("<%=basePath%>collect_queryRyListByZsbh.action?zsbh=" + zsbh);

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
                    var resultData = result.data;
                    var changeType = resultData.changeType;
                    if (changeType != null && changeType != "") {
                        if (changeType == "替换") {
                            $("#after").show();
                            $("#name").hide();
                            $("#selectName").show();
                            $("#selectSfzh").show();
                            selectName.setValue(resultData.jyjcryId);
                        }
                    }
                    form.setData(result.data);
                    selectName.setText(resultData.name);
                },
            });
        }
    }

    function save() {

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

    $("#after").hide();

    function changeTypeValueChange(e) {

        $("#after").hide();
        var value = e.value;
        if (value == "新增") {
            $("#name").show();
            $("#selectName").hide();
            $("#textSfzh").show(), $("#selectSfzh").hide();
            form.setData();
            nui.getbyName("changeType").setValue(value);
        }
        if (value == "替换") {
            $("#after").show();
            $("#name").hide(), $("#selectName").show();
            $("#textSfzh").hide(), $("#selectSfzh").show();
            selectName.load("<%=basePath%>collect_queryRyListByZsbh.action?zsbh=" + zsbh);

        }
        if (value == "撤销") {
            $("#name").hide();
            $("#selectName").show();
            $("#after").hide();
            $("#selectSfzh").show(), $("#textSfzh").hide();
            selectName.load("<%=basePath%>collect_queryRyListByZsbh.action?zsbh=" + zsbh);
        }
    }


    function selectNameValueChange(e) {
        var value = e.value;
        var valueArray = value.split(",");
        nui.getbyName("name").setValue(valueArray[0]);
        nui.get('selectSfzh').setValue();
        var url = "<%=basePath%>collect_querySfzhByName.action?name=" + value
        nui.get('selectSfzh').setUrl(url);
        nui.get('selectSfzh').select(0);
        queryBysfzh();
    }

    function queryBysfzh() {
        var value = nui.get('selectSfzh').getValue();
        nui.getbyName('sfzh').setValue(value);
        var sfzh = nui.getbyName('sfzh').getValue();
        var changeType = nui.getbyName('changeType').getValue();
        nui.get('saveGk').setEnabled(true);
        if ("" == changeType || null == changeType) {
            return;
        }
        var result = loadBySfzh(sfzh);
        if (undefined == result.name) {
            return;
        }
        form.setData(result);
        nui.get('id').setValue();
        nui.getbyName('changeType').setValue(changeType);
    }


    function loadBySfzh(sfzh) {
        var resultMap = {};
        $.ajax({
            url: "<%=basePath%>collect_queryRyBySfzh.action",
            type: 'post',
            data: {sfzh: sfzh},
            cache: false,
            async: false,
            success: function (data) {
                var resultData = nui.decode(data);
                resultMap = resultData;
            }
        });
        return resultMap;
    }

    function queryMessage(e) {
        var result = loadBySfzh(e.value);
        if (result.sfzh != undefined) {
            nui.alert('该人员身份信息已存在', '温馨提示');
            nui.get('saveGk').setEnabled(false);
            return
        }
        nui.get('saveGk').setEnabled(true);
    }
</script>
</body>
</html>