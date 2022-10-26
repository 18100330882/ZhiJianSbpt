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
    <h2 align="center" style="margin-top:10px">概况</h2>
    <input type="hidden" id="serialNumber" class="nui-hidden" name="SERIALNUMBER" value=""/>
    <input type="hidden" id="id" class="nui-hidden" name="id" value=""/>
    <fieldset style="border: solid 1px #aaa; padding: 3px; width:100%;">
        <legend><b>概况</b></legend>
        <div style="width:100%;">
            <fieldset style="border: solid 1px #aaa; padding: 3px; width:90%;">
                <table>
                    <legend><b>1.1</b></legend>
                    <tr>
                        <td align="right" style="width:190px;">检验检测机构名称：<span style="color:red;">*</span></td>
                        <td colspan="3">
                            <input id="jgmc" name="JGMC" class="nui-textbox" width="495px" allowInput="true"
                                   required="true" readonly="readonly"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" style="width:190px;">地址：<span style="color:red;">*</span></td>
                        <td colspan="3">
                            <input id="registerAdress" name="REGISTERADRESS" class="nui-textbox" width="495px"
                                   required="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" style="width:190px;">实验室地址：<span style="color:red;">*</span></td>
                        <td colspan="3">
                            <input name="ADRESS" class="nui-textbox" width="495px" required="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">邮编：<span style="color:red;">*</span></td>
                        <td>
                            <input id="postcode" name="POSTCODE" class="nui-textbox" width="180px"
                                   onvalidation="onYZBMValidation"/>
                        </td>
                        <td align="right">传真：</td>
                        <td>
                            <input name="FAX" class="nui-textbox" width="180px"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">E-mail：</td>
                        <td>
                            <input id="email" name="EMAIL" class="nui-textbox" vtype="email" width="180px"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">负责人：<span style="color:red;">*</span></td>
                        <td>
                            <input name="PRINCIPAL" class="nui-textbox" width="180px" required="true"/>
                        </td>
                        <td align="right">职务：<span style="color:red;">*</span></td>
                        <td>
                            <input name="POSITION" class="nui-textbox" width="180px" required="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">固定电话：</td>
                        <td>
                            <input name="TELEPHONE" class="nui-textbox" width="180px" onvalidation="onPhoneValidation"/>
                        <td align="right">手机：<span style="color:red;">*</span></td>
                        <td>
                            <input name="PHONE" class="nui-textbox" width="180px" onvalidation="onPhoneValidation"
                                   required="true"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right">联络人：<span style="color:red;">*</span></td>
                        <td>
                            <input id="contactPerson" name="CONTACTPERSON" class="nui-textbox" width="180px"
                                   required="true"/>
                        </td>
                        <td align="right">职务：</td>
                        <td>
                            <input name="CONTACTPOSITION" class="nui-textbox" width="180px"/>
                    </tr>
                    <tr>
                        <td align="right">固定电话：<span style="color:red;">*</span></td>
                        <td>
                            <input name="CONTACTTELEPHONE" class="nui-textbox" width="180px"
                                   onvalidation="onPhoneValidation" required="true"/>
                        </td>
                        <td align="right">手机：<span style="color:red;">*</span></td>
                        <td>
                            <input id="contactPhone" name="CONTACTPHONE" class="nui-textbox" width="180px"
                                   onvalidation="onPhoneValidation" required="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">社会信用代码：<span style="color:red;">*</span></td>
                        <td>
                            <input id="shxydm" name="SHXYDM" class="nui-textbox" width="180px"
                                   onvalidation="checkValue" readonly="readonly"/>
                        </td>
                        <td align="right">法定代表人：<span style="color:red;">*</span></td>
                        <td>
                            <input id="legalPerson" name="LEGALPERSON" class="nui-textbox" width="180px"
                                   required="true"/>
                        </td>
                    </tr>
                </table>
            </fieldset>
            <fieldset style="border: solid 1px #aaa; padding: 3px; width:90%;">
                <table>
                    <legend><b>1.2</b></legend>
                    <tr>
                        <td></td>
                        <td colspan="3" style="line-height: 20px; width: 360px; color: red; margin: 0px 9px">
                            (若检验检测机构是法人单位的不填此项)
                        </td>
                    </tr>
                    <tr>
                        <td align="right" style="width:190px;">所属法人单位名称：</td>
                        <td colspan="3">
                            <input id="legalunitName" name="LEGALUNITNAME" class="nui-textbox" width="495px"/>
                        </td>

                    </tr>
                    <tr>
                        <td align="right" style="width:190px;">所属法人单位地址：</td>
                        <td colspan="3">
                            <input id="legalunitAddress" onvalidation="onvalidationLegalunitName" name="LEGALUNITADRESS"
                                   class="nui-textbox" width="495px"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">负责人：</td>
                        <td>
                            <input id="legalPrincipal" name="LEGALPRINCIPAL" onvalidation="onvalidationLegalunitName"
                                   class="nui-textbox" width="180px"/>
                        </td>
                        <td align="right">职务：</td>
                        <td>
                            <input id="legalposition" name="LEGALPOSITION" onvalidation="onvalidationLegalunitName"
                                   class="nui-textbox" width="180px"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right">电话：</td>
                        <td>
                            <input id="legalphone" name="LEGALPHONE" onvalidation="onvalidationLegalunitName"
                                   class="nui-textbox" width="180px"
                                   onvalidation="onPhoneValidation"/>
                        </td>

                        <td align="right">社会信用代码：</td>
                        <td>
                            <input id="legalshxydm" name="LEGALSHXYDM" class="nui-textbox" width="180px"
                                   onvalidation="onvalidationLegalunitName"/>
                        </td>
                    </tr>
                </table>
            </fieldset>
            <fieldset style="border: solid 1px #aaa; padding: 3px; width:90%;">
                <table>
                    <legend><b>1.3</b></legend>
                    <tr>
                        <td></td>
                        <td colspan="3"
                            style="line-height: 20px; width: 360px; color: red; margin: 0px 9px">(若无主管部门的不填此项)
                        </td>
                    </tr>
                    <tr>
                        <td align="right">主管单位名称：</td>
                        <td colspan="3">
                            <input id="competentunitName" name="COMPETENTUNITNAME" class="nui-textbox" width="495px"/>
                        </td>

                    </tr>
                    <tr>
                        <td align="right">地址：</td>
                        <td colspan="3">
                            <input id="competentunitAdress" name="COMPETENTUNITADRESS" class="nui-textbox" width="495px"
                                   onvalidation="onvalidationCompetentunitName"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right">负责人：</td>
                        <td>
                            <input id="competentPrincipal" name="COMPETENTPRINCIPAL" class="nui-textbox" width="180px"
                                   onvalidation="onvalidationCompetentunitName"/>
                        </td>
                        <td align="right">职务：</td>
                        <td>
                            <input id="competentposition" name="COMPETENTPOSITION" class="nui-textbox" width="180px"
                                   onvalidation="onvalidationCompetentunitName"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">电话：</td>
                        <td>
                            <input name="COMPETENTPHONE" class="nui-textbox" width="180px" id="competentphone"
                                   onvalidation="onPhoneValidation" onvalidation="onvalidationCompetentunitName"/>
                        </td>
                    </tr>
                </table>
            </fieldset>
            <table>
                <tr>
                    <td align="right" style="width:190px;">检验检测机构设施特点：<span style="color:red;">*</span></td>
                    <td colspan="3">
                        <input name="CHARACTERISTIC" id="characteristic" class="nui-checkboxlist"
                               textField="text" width="600px" required="true" valueField="id"
                               data="[{'id':'0','text':'固定'},{'id':'1','text':'临时'},{'id':'2','text':'可移动'},{'id':'3','text':'多场所'}]"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">检验检测机构法人类型：<span style="color:red;">*</span></td>
                    <td colspan="3">
                        <input name="LEGALPERSONJGTYPE" id="legalPersonjgType" class="nui-radiobuttonlist"
                               valueField="id" textField="text"
                               data="[{'id':'0','text':'社团法人'},{'id':'1','text':'事业法人'},{'id':'2','text':'企业法人'},{'id':'3','text':'其他'}]"
                               valueField="text" textField="text" width="505px" repeatItems="2" required="true"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">独立法人检验检测机构 &nbsp;&nbsp;&nbsp;&nbsp;<br/>或检验检测机构所属法人：<span
                            style="color:red;">*</span></td>
                    <td colspan="3">
                        <input name="LEGALPERSONTYPE" id="legalPersonType" class="nui-radiobuttonlist" textField="text"
                               valueField="id"
                               data="[{'id':'0','text':'社团法人'},{'id':'1','text':'事业法人'},{'id':'2','text':'企业法人'},{'id':'3','text':'其他'}]"
                               valueField="text" required="true"/>
                    </td>
                </tr>
            </table>
        </div>
        <div>
            <a id="saveGk" class="nui-button" iconCls="icon-save" onclick="saveGk()"
               style="margin-left: 45%;margin-top:10%">保存</a>
        </div>
    </fieldset>
</form>

<script type="text/javascript">
    nui.parse();
    // 流水号
    var serialNumber = '<%=request.getParameter("serialNumber")%>';
    var action = '<%=request.getParameter("action")%>';
    var detail = "<%=request.getParameter("detail")%>";
    var form = new nui.Form("form1");
    //详情查看
    if (detail == "detail") {
        nui.get('saveGk').hide();
    } else if ('add' == action) {
        initCheck();
    } else if ('edit' == action) {
        initCheck();
    }

    // 初始化 验证 封面信息数据


    function initCheck() {
        $.ajax({
            url: "<%=basePath%>bizApiShiysjyjc_initLoad.action",
            type: 'post',
            cache: 'false',
            async: false,
            data: {serialNumber: serialNumber},
            success: function (resultData) {
                resultData = nui.decode(resultData);
                var data = resultData.data;
                // 没有查询到数据
                if (resultData.code != 0) {
                    nui.alert(resultData.msg);
                    nui.get("saveGk").setEnabled(false);
                    return;
                }
                var zgbmName = data.zgbmName;
                if (zgbmName == "无") {
                    nui.getbyName("competentunitName").setEnabled(false);
                    nui.getbyName("competentunitAdress").setEnabled(false);
                    nui.getbyName("competentPrincipal").setEnabled(false);
                    nui.getbyName("competentposition").setEnabled(false);
                    nui.getbyName("competentphone").setEnabled(false);
                }
                // 编辑时  初始化概况 数据
                initLoad(serialNumber);
            }
        });
    }

    function initLoad(serialNumber) {
        $.ajax({
            url: "<%=basePath%>apiShiYsJyjcSqs_initLoad.action",
            type: 'post',
            data: {serialNumber: serialNumber},
            cache: false,
            success: function (text) {
                var map = nui.decode(text);
                if (map.code == 0) {
                    form.setData(map.data);
                    return;
                } else {
                    getSqlx();
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {//如果不能获得数据
                nui.alert(jqXHR.responseText);//弹出错误
                CloseWindow();
            }
        });

    }

    //初始化页面
    function getRegistInfo() {
        $.ajax({
            url: "<%=basePath%>regist_getInfoByUserName.action",
            type: 'post',
            cache: false,
            async: false,
            success: function (text) {
                //成功获得数据时
                var data = nui.decode(text);
                var registerAdress = nui.get("registerAdress").getValue();
                if (data.zcdz != null && (registerAdress == null || registerAdress == "")) {
                    nui.get("registerAdress").setValue(data.zcdz);
                }
                var email = nui.get("email").getValue()
                if (data.email != null && (email == null || email == "")) {
                    nui.get("email").setValue(data.email);
                }
                var postcode = nui.get("postcode").getValue();
                if (data.yzbm != null && (postcode == null || postcode == "")) {
                    nui.get("postcode").setValue(data.yzbm);
                }
                var fddbr = nui.get("legalPerson").getValue();
                if (data.fddbr != null && (fddbr == null || fddbr == "")) {
                    nui.get("legalPerson").setValue(data.fddbr);
                }
                var contactPerson = nui.get("contactPerson").getValue();
                if (data.lxr != null && (contactPerson == null || contactPerson == "")) {
                    nui.get("contactPerson").setValue(data.lxr);
                }
                var contactPhone = nui.get("contactPhone").getValue();
                if (data.lxdh != null && (contactPhone == null || contactPhone == "")) {
                    nui.get("contactPhone").setValue(data.lxdh);
                }
                nui.get('jgmc').setValue(data.qymc);
            },
            error: function (jqXHR, textStatus, errorThrown) {//如果不能获得数据
                nui.alert(jqXHR.responseText);//弹出错误
            }
        })
    }

    function saveGk() {
        nui.get("serialNumber").setValue(serialNumber);
        if (serialNumber == null || serialNumber == "") {
            nui.alert("生成流水号失败！请关闭刷新重试！", "提醒！");
            return;
        }

        var formData = form.getData();
        form.validate();
        if (form.isValid() == false) {
            nui.alert("有必填项未填！", "提醒！");
            return;
        }
        var reqJson = nui.encode(formData);
        $.ajax({
            url: "<%=basePath%>apiShiYsJyjcSqs_save.action",
            type: 'post',
            cache: 'false',
            async: false,
            data: {data: reqJson},
            success: function (resultData) {
                resultData = nui.decode(resultData);
                nui.alert(resultData.msg, "提示!");
            }
        });
    }

    /** 手机和固话验证 */
    function onPhoneValidation(e) {
        if (e.value == "") return;
        if (telephone(e.value) == false && mobilephone(e.value) == false) {
            e.isValid = false;
            e.errorText = e.source.requiredErrorText;
        } else {
            e.isValid = true;
        }
    }

    function telephone(v) {//固话验证
        var re = /^0\d{2,3}-?\d{7,8}$/;
        if (re.test(v)) return true;
        return false;
    }

    function mobilephone(v) {//手机验证
        var re = /^1(3|5|8)\d{9}$/;
        if (re.test(v)) return true;
        return false;
    }

    //邮政编码验证
    function onYZBMValidation(e) {
        if (e.isValid) {
            var pattern = /^[0-9]{6}$/;
            if (!pattern.test(e.value)) {
                e.errorText = "邮编格式不正确,需要6位数字";
                e.isValid = false;
            }
        }
    }

    function checkValue(e) {//判断组织机构代码和信用代码是否合法
        var value = e.value;
        //是企业时要填写9位组织机构代码或18位社会信用代码
        if (value.length == 18) {
            e.isValid = true;
        } else {
            e.errorText = "请填写组织机构代码9位，社会信用代码18位.";
            e.isValid = false;
        }
    }


    function getSqlx() {
        $.ajax({
            url: "<%=basePath%>apiShiYsJyjcSqlx_initLoad.action",
            type: 'post',
            data: {serialNumber: serialNumber},
            cache: false,
            success: function (text) {
                var map = nui.decode(text);
                if (map.code == 0) {
                    var data = map.data;
                    if (data.applicationType != '首次') {
                        getJgobjectByUserName();
                    } else {
                        getRegistInfo();
                    }
                }

            }
        });
    }

    function getJgobjectByUserName() {
        $.ajax({
            url: "<%=basePath%>collect_queryByUserName2.action",
            type: 'post',
            cache: false,
            async: false,
            success: function (text) {
                var result = nui.decode(text);
                if (result.total == 0) {
                    return;
                }
                var map = result.data;
                form.setData(map);
            }
        });
    }

    function onvalidationLegalunitName(l) {
        var legalunitName = nui.get('legalunitName').getValue();
        legalunitName = legalunitName.replace(/\s+/g, "");
        var legalunitAddress = nui.get('legalunitAddress');
        var legalPrincipal = nui.get('legalPrincipal');
        var legalposition = nui.get('legalposition');
        var legalphone = nui.get('legalphone');
        var legalshxydm = nui.get('legalshxydm');
        legalunitAddress.required = false;
        legalPrincipal.required = false;
        legalposition.required = false;
        legalphone.required = false;
        legalshxydm.required = false;

        if (legalunitName != null & legalunitName != '') {
            legalunitAddress.required = true;
            legalPrincipal.required = true;
            legalposition.required = true;
            legalphone.required = true;
            legalshxydm.required = true;
        } else {
            legalunitAddress.setValue('');
            legalPrincipal.setValue('');
            legalposition.setValue('');
            legalphone.setValue('');
            legalshxydm.setValue('');
        }
    }

    function onvalidationCompetentunitName() {
        var competentunitName = nui.get('competentunitName').getValue();
        competentunitName = competentunitName.replace(/\s+/g, "");
        var competentunitAdress = nui.get('competentunitAdress');
        var competentPrincipal = nui.get('competentPrincipal');
        var competentposition = nui.get('competentposition');
        var competentphone = nui.get('competentphone');
        competentunitAdress.required = false;
        competentPrincipal.required = false;
        competentposition.required = false;
        competentphone.required = false;
        if (competentunitName != null & competentunitName != '') {
            competentunitAdress.required = true;
            competentPrincipal.required = true;
            competentposition.required = true;
            competentphone.required = true;
        } else {
            competentunitAdress.setValue('');
            competentPrincipal.setValue('');
            competentposition.setValue('');
            competentphone.setValue('');
        }
    }

    function getSqsBySerialNumber(serialNumber) {
        $.ajax({
            url: "<%=basePath%>apiShiYsJyjcSqs_.action",
            type: 'post',
            cache: 'false',
            async: false,
            data: {serialNumber: serialNumber},
            success: function (resultData) {
                resultData = nui.decode(resultData);
                nui.alert(resultData.msg, "提示!");
            }
        });
    }
</script>
</body>
</html>