<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<head>
    <base href="<%=basePath%>">
    <title>检验检测机构资质认定授权签字人变更审批表</title>
    <script type="text/javascript" src="<%=basePath%>nui/nui.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<style>
    .mini-textbox {

        width: 100%;
    }
</style>
<body>
<form id="form" target="id_iframe" style="padding-top:5px;margin:0 auto; width:95%; border:1px;">
    <h1 align="center" style="margin-top:10px">检验检测机构资质认定授权签字人变更审批表</h1>
    <input id="id" name="id" class="nui-hidden"/>
    <fieldset style="border: solid 1px #aaa; padding: 3px; width:95%;">
        <div style="width:95%;">
            <table width="100%">
                <tr>
                    <td align="right" style="width: 28%">变更类型：<span style="color:red;">*</span></td>
                    <td>
                        <input name="changeType" class="nui-combobox" emptytext="请选择..." valueField="text" width="100%"
                               data="[{id:'1',text:'新增'},{id:'2',text:'撤销'},{id:'3',text:'授权签字领域调整'}]"
                               onValueChanged="changeTypeValueChanged"/>
                    </td>
                    <td align="right">姓名：<span style="color:red;" id="qzrNamespan">*</span></td>
                    <td><input id="textName" name="qzrName" class="nui-textbox" allowInput="true"/>
                        <input id="selectName" class="nui-combobox" style="width: 100%"
                               required="true" emptyText="请选择姓名" valueField="QZRNAME" textField="QZRNAME"
                               allowinput="false"
                               onvaluechanged="selectNameValueChange"/>
                    </td>
                    <td align="right" style="">身份证号：<span style="color:red;">*</span></td>
                    <td>
                        <input id="textSfzh" name="sfzh" class="nui-textbox" allowInput="true" required="true"
                               style="width: 100%"
                               onvaluechanged="queryMessage"/>
                        <input id="selectSfzh" class="nui-combobox" valueField="SFZH" textField="SFZH"
                               style="width: 100%"
                               required="true" emptyText="请选择身份证号码"/>
                    </td>


                </tr>
                <tr>
                    <td align="right">场地地址：<span style="color:red;" id="cddzspan">*</span></td>
                    <td>
                        <input name="cddz" class="nui-textbox" allowInput="true" style="width: 100%"/>
                    </td>
                    <td align="right">性别：<span style="color:red;" id="sexspan">*</span></td>
                    <td>
                        <input name="sex" class="nui-radiobuttonlist" valueField="id"
                               data="[{id:'0',text:'男'},{id:'1',text:'女'}]" value="1"/>
                    </td>
                    <td align="right">出生年月：<span style="color:red;" id="birthdayspan">*</span></td>
                    <td>
                        <input name="birthday" class="nui-datepicker" allowInput="false" dataFormat="yyyy-MM-dd"
                               style="width: 100%"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">职务：<span style="color:red;" id="zhiwspan">*</span></td>
                    <td><input name="zhiw" class="nui-textbox" allowInput="true"/></td>
                    <td align="right">职称：<span style="color:red;" id="zhicspan">*</span></td>
                    <td><input name="zhic" class="nui-textbox" allowInput="true"/></td>
                    <td align="right">文化程度：<span style="color:red;" id="educationspan">*</span></td>
                    <td>
                        <input name="education" class="nui-combobox" emptytext="请选择..." valueField="text"
                               style="width: 100%"
                               data="[{id:'1',text:'博士研究生'},{id:'2',text:'硕士研究生'},{id:'3',text:'大学本科'},{id:'4',text:'大学专科'},{id:'5',text:'中等专科'},{id:'7',text:'职业高中'},{id:'8',text:'高中'},{id:'9',text:'初中'},{id:'10',text:'小学'},{id:'11',text:'无学历'}]"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">部门：<span style="color:red;" id="deptspan">*</span></td>
                    <td><input name="dept" class="nui-textbox" allowInput="true"/></td>
                </tr>
                <tr>
                    <td align="right">电话：<span style="color:red;" id="telephonespan">*</span></td>
                    <td><input name="telephone" class="nui-textbox" allowInput="true"/></td>
                    <td align="right">传真：</td>
                    <td><input name="fax" class="nui-textbox" allowInput="true"/></td>
                    <td align="right">电子邮箱：</td>
                    <td><input name="email" class="nui-textbox" allowInput="true"/></td>
                </tr>
                <tr>
                    <td align="right" style="">何年毕业于何院校、何专业、受过何种培训：</td>
                    <td height="52px" colspan="5">
                        <input name="peixun" class="nui-textarea" height="100%" allowInput="true"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">从事检验检测工作的经历：</td>
                    <td height="52px" colspan="5">
                        <input name="gzjl" class="nui-textarea" height="100%" allowInput="true"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">相关说明（若授权领域又变更应予以说明）：</td>
                    <td height="52px" colspan="5">
                        <input name="xgsm" class="nui-textarea" height="100%" allowInput="true"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">原授权签字领域：<span style="color:red;" id="qzlyspan">*</span></td>
                    <td height="52px" colspan="5">
                        <input name="qzly" class="nui-textarea" height="100%" allowInput="true"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">变更后的授权签字领域：<span style="color:red;" id="qzrAreaNewspan">*</span></td>
                    <td height="52px" colspan="5">
                        <input name="qzrAreaNew" class="nui-textarea" height="100%" allowInput="true"/>
                    </td>
                </tr>
            </table>
            <div><span style="color: red">注：①此表一式二份，检验检测机构和资质认定部门分别留存；</span></div>
            <div><span
                    style="color: red;margin-left: 28px">②变更类型包括：新增、撤销、授权签字领域调整；新增时原授权签字领域可填“无”，撤销时变更后的授权签字领域可填“无”；</span>
            </div>
            <div><span style="color: red;margin-left: 28px">③授权签字人变更时，需同时提供申请书中的附表2-1授权签字人基本信息表，必要时，资质认定部门可派员现场考核，经批准后，可签发检验检测报告或证书。</span>
            </div>
            <div><span style="color: red;margin-left: 28px">④需一并提交本表的电子版。</span></div>
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
    var changeType = "";
    var zsbh = "";
    var serialNumber = "";
    var action = "";
    var id = "";
    var qzrName = "";
    var flowId = "";

    function SetData(result) {
        flowId = result.flowId;
        action = result.action;
        if (action == 'add') {
            var data = nui.clone(result);
            zsbh = data.zsbh;
            changeType = data.changeType;
            serialNumber = data.serialNumber;
            $("#textName").show(), $("#selectName").hide();
            $("#textSfzh").show(), $("#selectSfzh").hide();
        } else {
            var data = nui.clone(result.row);
            zsbh = result.zsbh;
            changeType = result.changeType;
            serialNumber = result.serialNumber;
            id = data.ID;
            $("#textName").hide(), $("#selectName").show();
            $("#textSfzh").hide(), $("#selectSfzh").show();
            nui.get('selectName').setValue(data.QZRNAME);
            nui.get('selectSfzh').setValue(data.SFZH);
            nui.get('selectName').load("<%=basePath%>collect_queryQzrByZsbh.action?zsbh=" + zsbh);
            nui.get('selectSfzh').load("<%=basePath%>collect_queryQzrSfzhByName.action?name=" + data.QZRNAME);
            nui.getbyName('birthday').setValue(data.BIRTHDAY);
        }


        // 编辑**
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
                    form.setData(resultData);
                    var changeType = resultData.changeType;
                    var e = {};
                    e.value = changeType;
                    changeTypeValueChanged(e);
                }
            });
        }


    }


    function save() {
        var formData = form.getData();
        form.validate();
        if (form.isValid() == false) {
            return;
        }
        $.ajax({
            url: "<%=basePath%>changeApplyInfo_changeApplyInfoDetailsSave.action",
            type: 'post',
            cache: 'false',
            async: false,
            data: {changeType: changeType, serialNumber: serialNumber, row: nui.encode(formData)},
            success: function (result) {
                result = nui.decode(result);
                nui.alert(result.msg, "提醒!", function (e) {
                    window.open("<%=basePath%>jsp/jyjc/rzrk/bggl/sqs/sqqzrjbxxPrint.jsp?id=" + result.data);
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

    function changeTypeValueChanged(e) {
        var value = e.value;
        if ("新增" == value) {
            show();
            $("#qzrAreaNewspan").hide();
            nui.getbyName("qzrAreaNew").setEnabled(false);
            nui.getbyName("qzrAreaNew").required = false;
            nui.getbyName("qzrAreaNew").setValue("");
            $("#textName").show(), $("#selectName").hide();
            $("#textSfzh").show(), $("#selectSfzh").hide();
            form.setData();
            nui.getbyName('changeType').setValue(value);

        }
        if ("授权签字领域调整" == value) {
            $("#qzrAreaNewspan").show();
            show();
            nui.getbyName("qzrAreaNew").required = true;
            nui.getbyName("qzrAreaNew").allowInput = true;
            nui.getbyName("qzrAreaNew").setEnabled(true);
            $("#textName").hide(), $("#selectName").show();
            $("#textSfzh").hide(), $("#selectSfzh").show();
            nui.get('selectName').load("<%=basePath%>collect_queryQzrByZsbh.action?zsbh=" + zsbh);
        }

        if ("撤销" == value) {
            hidden();
            show2();
            nui.getbyName("qzrAreaNew").setEnabled(false);
            nui.getbyName("qzrAreaNew").required = false;
            nui.getbyName("qzrAreaNew").allowInput = false;
            $("#textName").hide(), $("#selectName").show();
            $("#textSfzh").hide(), $("#selectSfzh").show();
            nui.get('selectName').load("<%=basePath%>collect_queryQzrByZsbh.action?zsbh=" + zsbh);
        }
    }

    function show() {
        $("#cddzspan").show();
        $("#sexspan").show();
        $("#birthdayspan").show();
        $("#zhiwspan").show();
        $("#zhicspan").show();
        $("#educationspan").show();
        $("#deptspan").show();
        $("#telephonespan").show();
        $("#qzrNamespan").show();
        $("#qzlyspan").show();

        nui.getbyName("cddz").required = true;
        nui.getbyName("sex").required = true;
        nui.getbyName("birthday").required = true;
        nui.getbyName("zhiw").required = true;
        nui.getbyName("zhic").required = true;
        nui.getbyName("education").required = true;
        nui.getbyName("dept").required = true;
        nui.getbyName("telephone").required = true;
        nui.getbyName("qzrName").required = true;
        nui.getbyName("qzly").required = true;
    }

    function show2() {

        $("#qzrNamespan").show();
        $("#sexspan").show();
        $("#birthdayspan").show();
        $("#zhicspan").show();
        $("#educationspan").show();
        $("#qzlyspan").show();
        $("#telephonespan").show();

        nui.getbyName("qzrName").required = true;
        nui.getbyName("sex").required = true;
        nui.getbyName("birthday").required = true;
        nui.getbyName("zhic").required = true;
        nui.getbyName("education").required = true;
        nui.getbyName("telephone").required = true;
        nui.getbyName("qzly").required = true;
    }

    hidden();

    function hidden() {
        $("#cddzspan").hide();
        $("#sexspan").hide();
        $("#birthdayspan").hide();
        $("#zhiwspan").hide();
        $("#zhicspan").hide();
        $("#educationspan").hide();
        $("#deptspan").hide();
        $("#telephonespan").hide();
        $("#qzrNamespan").hide();
        $("#qzlyspan").hide();
        $("#qzrAreaNewspan").hide();

        nui.getbyName("cddz").required = false;
        nui.getbyName("sex").required = false;
        nui.getbyName("birthday").required = false;
        nui.getbyName("zhiw").required = false;
        nui.getbyName("zhic").required = false;
        nui.getbyName("education").required = false;
        nui.getbyName("dept").required = false;
        nui.getbyName("telephone").required = false;
        nui.getbyName("qzrName").required = false;
        nui.getbyName("qzly").required = false;
    }

    function queryBysfzh() {
        var value = nui.get('selectSfzh').getValue();
        nui.get('textSfzh').setValue(value);
        var sfzh = nui.get('textSfzh').getValue();
        var changeType = nui.getbyName('changeType').getValue();
        nui.get('saveGk').setEnabled(true);
        if ("" == changeType || null == changeType) {
            return;
        }
        var result = loadBySfzh(sfzh);
        if (undefined == result.qzrName) {
            return;
        }
        form.setData(result);
        console.log(result);
        nui.get('id').setValue(id)
        nui.getbyName('changeType').setValue(changeType);
    }


    function loadBySfzh(sfzh) {
        var resultMap = {};
        $.ajax({
            url: "<%=basePath%>collect_queryQzrBySfzh.action",
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

    function selectNameValueChange(e) {
        var value = e.value;
        var valueArray = value.split(",");
        nui.get("textName").setValue(valueArray[0]);
        nui.get('selectSfzh').setValue();
        var url = "<%=basePath%>collect_queryQzrSfzhByName.action?name=" + value
        nui.get('selectSfzh').setUrl(url);
        nui.get('selectSfzh').select(0);
        queryBysfzh();
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