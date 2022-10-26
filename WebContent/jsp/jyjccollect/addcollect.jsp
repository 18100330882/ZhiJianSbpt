<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String userName = request.getSession().getAttribute("userName").toString();
    String zsbh = request.getParameter("zsbh");
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
    <style type="text/css">
        .mini-errorIcon {
            margin-top: 2px !important;;
        }

        table tr {
            white-space: nowrap;
        }
    </style>
</head>

<body>
<form id="form1" target="id_iframe" style="padding-top:5px;margin:0 auto; width:840px; border:1px;">
    <input id="id" name="ID" class="nui-hidden" required="true" width="300px" readonly="readonly"/>
    <input id="collectId" name="COLLECTID" class="nui-hidden" required="true" width="300px" readonly="readonly"/>
    <h2 align="center" style="margin-top:10px">概况</h2>
    <fieldset style="border: solid 1px #aaa; padding: 3px; width:100%;">
        <legend><b>概况</b></legend>
        <div style="width:100%;padding-left: 4%">
            <fieldset style="border: solid 1px #aaa; padding: 3px; width:90%;">
                <table>
                    <legend><b>1.1</b></legend>
                    <tr>
                        <td align="right">证书编号：<span style="color:red;">*</span></td>
                        <td>
                            <input id="zsbh" name="ZZRDZSBH" class="nui-textbox" width="250px"
                                   required="true" emptyText="请先输入CMA证书编号获取注册信息" onvaluechanged="getJgobjectByZsbh()"/>
                            <br>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" style="width:190px;">检验检测机构名称：<span style="color:red;">*</span></td>
                        <td colspan="3">
                            <input id="jgobjectname" name="JGOBJECTNAME" class="nui-textbox" width="495px"
                                   allowInput="true"
                                   required="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" style="width:190px;">地址：<span style="color:red;">*</span></td>
                        <td colspan="3">
                            <input id="zhusuo" name="ZHUSUO" class="nui-textbox" width="495px"
                                   required="true"/>
                        </td>
                    </tr>
                    <tr height="40px">
                        <td align="right">专业领域类别：<span style="color:red;">*</span></td>
                        <td>
                            <input id="zylylbIds" name="zylylbIds" emptyText="请选择专业领域类别" class="nui-buttonedit"
                                   width="187px" onbuttonclick="onButtonSclbEdit" required="true"/>
                        </td>
                        <td align="right">行政区域：<span style="color:red;">*</span></td>
                        <td>
                            <input id="qyIds" name="qyIds" emptyText="请选择行政区域" class="nui-buttonedit" width="220px"
                                   onbuttonclick="onButtonXzqyEdit" required="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">邮编：<span style="color:red;">*</span></td>
                        <td>
                            <input id="yzbm" name="YZBM" class="nui-textbox" width="180px"
                                   required="true"/>
                        </td>
                        <td align="right">传真：</td>
                        <td>
                            <input name="CZ" class="nui-textbox" width="180px"/>
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
                            <input id="jgfzr" name="JGFZR" class="nui-textbox" width="180px" required="true"/>
                        </td>
                        <td align="right">职务：<span style="color:red;">*</span></td>
                        <td>
                            <input name="JGFZRZW" class="nui-textbox" width="180px" required="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">固定电话：</td>
                        <td>
                            <input name="JGFZRDH" class="nui-textbox" width="180px" onvalidation="onPhoneValidation"/>
                        <td align="right">手机：<span style="color:red;">*</span></td>
                        <td>
                            <input id="jgfzrphone" name="JGFZRPHONE" class="nui-textbox" width="180px"
                                   onvalidation="onPhoneValidation" required="true"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right">联络人：<span style="color:red;">*</span></td>
                        <td>
                            <input id="jgllr" name="JGLLR" class="nui-textbox" width="180px"
                                   required="true"/>
                        </td>
                        <td align="right">职务：</td>
                        <td>
                            <input name="JGLLRZW" class="nui-textbox" width="180px"/>
                    </tr>
                    <tr>
                        <td align="right">固定电话：<span style="color:red;">*</span></td>
                        <td>
                            <input name="JGRLLRDH" class="nui-textbox" width="180px"
                                   onvalidation="onPhoneValidation" required="true"/>
                        </td>
                        <td align="right">手机：<span style="color:red;">*</span></td>
                        <td>
                            <input id="jgllrphone" name="JGLLRPHONE" class="nui-textbox" width="180px"
                                   onvalidation="onPhoneValidation" required="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">社会信用代码：<span style="color:red;">*</span></td>
                        <td>
                            <input id="shxydm" name="SHXYDM" class="nui-textbox" width="180px"
                                   onvalidation="checkValue">
                        </td>
                    </tr>
                    <tr>
                        <td align="right">发证日期：<span style="color:red;">*</span></td>
                        <td>
                            <input id="zsfzdate" name="ZSFZDATE" class="nui-datepicker" width="180px"
                                   required="true"/>
                        </td>
                        <td align="right">有效日期：<span style="color:red;">*</span></td>
                        <td>
                            <input id="zsjzdate" name="ZSJZDATE" class="nui-datepicker" width="180px"
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
                            <input id="ssfrdwmc" name="SSFRDWMC" class="nui-textbox" width="495px"/>
                        </td>

                    </tr>
                    <tr>
                        <td align="right" style="width:190px;">所属法人单位地址：</td>
                        <td colspan="3">
                            <input id="ssfrdz" onvalidation="onvalidationLegalunitName" name="SSFRDZ"
                                   class="nui-textbox" width="495px"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">负责人：</td>
                        <td>
                            <input id="lxr" name="LXR" onvalidation="onvalidationLegalunitName" class="nui-textbox"
                                   width="180px"/>
                        </td>
                        <td align="right">职务：</td>
                        <td>
                            <input id="fddbrzw" name="FDDBRZW" onvalidation="onvalidationLegalunitName"
                                   class="nui-textbox" width="180px"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right">电话：</td>
                        <td>
                            <input id="lxdh" name="LXDH" class="nui-textbox" width="180px"
                                   onvalidation="onPhoneValidation" onvalidation="onvalidationLegalunitName"/>
                        </td>

                        <td align="right">社会信用代码：</td>
                        <td>
                            <input id="ssfrshxydm" name="SSFRSHXYDM" class="nui-textbox" width="180px"
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
                            <input id="zgbm" name="ZGBM" class="nui-textbox" width="495px"/>
                        </td>

                    </tr>
                    <tr>
                        <td align="right">地址：</td>
                        <td colspan="3">
                            <input id="zgbmdz" name="ZGBMDZ" class="nui-textbox" width="495px"
                                   onvalidation="onvalidationCompetentunitName"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right">负责人：</td>
                        <td>
                            <input id="zgbmlxr" name="ZGBMLXR" class="nui-textbox" width="180px"
                                   onvalidation="onvalidationCompetentunitName"/>
                        </td>
                        <td align="right">职务：</td>
                        <td>
                            <input id="zgbmzw" name="ZGBMZW" class="nui-textbox" width="180px"
                                   onvalidation="onvalidationCompetentunitName"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">电话：</td>
                        <td>
                            <input id="zgbmdh" name="ZGBMDH" class="nui-textbox" width="180px" id="competentphone"
                                   onvalidation="onPhoneValidation" onvalidation="onvalidationCompetentunitName"/>
                        </td>
                    </tr>
                </table>
            </fieldset>
            <fieldset style="border: solid 1px #aaa; padding: 3px; width:90%;">
                <table style="height: 150px" align="center" id="buttonts">
                    <legend><b>1.4</b></legend>
                    <tr>
                        <td align="center" style="width:190px;">检验检测机构设施特点：<span style="color:red;">*</span></td>
                        <td colspan="3">
                            <input id="jgtd" name="JGSSTD" class="nui-checkboxlist" textField="text" valueField="id"
                                   data="[{'id':'0','text':'固定'},{'id':'1','text':'临时'},{'id':'2','text':'可移动'},{'id':'3','text':'多场所'}]"
                                   required="true"></span>
                        </td>
                    </tr>
                    <tr style="margin-top: 20px">
                        <td align="center"><span>检验检测机构所属法人：</span><br><span style="font-size: 12px;color: red">（非独立法人检验检测机构填此项）</span>
                        </td>
                        <td colspan="3">
                            <input id="jyjcjgssfr" name="DLJGFRLB" class="nui-checkboxlist" textField="text"
                                   valueField="id"
                                   data="[{'id':'0','text':'社团法人'},{'id':'1','text':'事业法人'},{'id':'2','text':'企业法人'},{'id':'3','text':'其他'}]"
                                   required="false"></span>
                        </td>
                    </tr>
                    <tr style="margin-top: 20px">
                        <td align="center">独立法人检验检测机构 &nbsp;&nbsp;&nbsp;&nbsp;<br/>或检验检测机构所属法人：<span style="color:red;">*</span>
                        </td>
                        <td colspan="3">
                            <input id="dlfrjyjcjg" name="FDLJGFRLB" class="nui-checkboxlist" textField="text"
                                   valueField="id"
                                   data="[{'id':'0','text':'社团法人'},{'id':'1','text':'事业法人'},{'id':'2','text':'企业法人'},{'id':'3','text':'其他'}]"
                                   required="true"></span>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </div>
        <div>
            <a id="saveGk" class="nui-button" iconCls="icon-save" onclick="SaveData()"
               style="margin-left: 45%;margin-top:3%">保存</a>
        </div>
    </fieldset>
</form>

<script type="text/javascript">
    nui.parse();
    var form = new nui.Form("form1");

    var userName = '<%=userName%>'
    var zsbh = '<%=zsbh%>'
    var action = '<%=request.getParameter("action")%>';

    var zyNames = loadZy();
    if ('edit' == action) {
        $.ajax({
            url: "<%=basePath%>collect_queryByZsbh.action",
            type: 'post',
            data: {zsbh: zsbh, action: 'edit'},
            cache: false,
            success: function (text) {
                var result = nui.decode(text);
                var map = result.data;
                form.setData(map);
                nui.get('zylylbIds').setValue(map.ZYLBID);
                nui.get('zylylbIds').setText(map.ZYLBNAME);
                nui.get('qyIds').setText(map.AREANAME);
                nui.get('qyIds').setValue(map.AREAID);
            }
        });
    } else if ('add' == action) {
        $.ajax({
            url: "<%=basePath%>collect_queryByUserName.action",
            type: 'post',
            cache: false,
            success: function (text) {
                var result = nui.decode(text);
                if (result.total == 0) {
                    return;
                }
                var map = result.data;
                form.setData(map);
                nui.get('zylylbIds').setValue(map.ZYLBID);
                nui.get('zylylbIds').setText(map.ZYLBNAME);
                nui.get('qyIds').setText(map.AREANAME);
                nui.get('qyIds').setValue(map.AREAID);
            }
        });
    }


    function SaveData() {
        var str = "";
        var zylylbIds = nui.get("zylylbIds").getValue();
        var strN = nui.get("zylylbIds").getText();
        var qyId = nui.get("qyIds").getValue();
        var qyText = nui.get("qyIds").getText();
        if (typeof zylylbIds != 'string' && zylylbIds.length > 0) {
            for (var i = 0; i < zylylbIds.length; i++) {
                str += zylylbIds[i] + ","
            }
            str = str.substring(0, str.lastIndexOf(","));
        } else {
            str = zylylbIds;
        }
        if (!zyNames.includes(str)) {
            nui.alert('专业类别非法！', '系统提示');
            nui.get("zylylbIds").setValue();
            nui.get("zylylbIds").setText();
            return
        }
        var o = form.getData();
        form.validate();
        if (form.isValid() == false) {
            nui.alert('请完善信息', '系统提示');
            return
        }
        var json = nui.encode(o);
        $.ajax({
            url: "<%=basePath%>collect_jgobjectSave.action",
            type: 'post',
            data: {data: json, str: str, strN: strN, qyId: qyId, qyText: qyText, action: action},
            cache: false,
            success: function (text) {
                if (text.trim() != 0) {
                    nui.alert("保存成功！", "提醒", function () {
                        CloseWindow("save");
                    });
                }
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


    function onvalidationLegalunitName(l) {
        var ssfrdwmc = nui.get('ssfrdwmc').getValue();
        ssfrdwmc = ssfrdwmc.replace(/\s+/g, "");
        var ssfrdz = nui.get('ssfrdz');
        var lxr = nui.get('lxr');
        var fddbrzw = nui.get('fddbrzw');
        var lxdh = nui.get('lxdh');
        var ssfrshxydm = nui.get('ssfrshxydm');
        ssfrdz.required = false;
        lxr.required = false;
        fddbrzw.required = false;
        lxdh.required = false;
        ssfrshxydm.required = false;

        if (ssfrdwmc != null & ssfrdwmc != '') {
            ssfrdz.required = true;
            lxr.required = true;
            fddbrzw.required = true;
            lxdh.required = true;
            ssfrshxydm.required = true;
        } else {
            ssfrdz.setValue('');
            lxr.setValue('');
            fddbrzw.setValue('');
            lxdh.setValue('');
            ssfrshxydm.setValue('');
        }
    }

    function onvalidationCompetentunitName() {
        var zgbm = nui.get('zgbm').getValue();
        zgbm = zgbm.replace(/\s+/g, "");
        var zgbmdz = nui.get('zgbmdz');
        var zgbmlxr = nui.get('zgbmlxr');
        var zgbmzw = nui.get('zgbmzw');
        var zgbmdh = nui.get('zgbmdh');
        zgbmdz.required = false;
        zgbmlxr.required = false;
        zgbmzw.required = false;
        zgbmdh.required = false;
        if (zgbm != null & zgbm != '') {
            zgbmdz.required = true;
            zgbmlxr.required = true;
            zgbmzw.required = true;
            zgbmdh.required = true;
        } else {
            zgbmdz.setValue('');
            zgbmlxr.setValue('');
            zgbmzw.setValue('');
            zgbmdh.setValue('');
        }
    }

    // 选择专业领域类别
    function onButtonSclbEdit(e) {
        var zylb = this;
        nui.open({
            url: "<%=basePath%>jsp/jyjc/rzrk/other/SelectZyTypeTree.jsp",
            showMaxButton: false,
            title: "选择专业类型",
            width: 350,
            height: 700,
            ondestroy: function (action) {
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var resultData = iframe.contentWindow.GetData();
                    resultData = nui.decode(resultData);
                    if (resultData) {
                        zylb.setValue(resultData.dms);
                        zylb.setText(resultData.names);
                    }
                }
            }
        });
    }

    //行政区域选择
    function onButtonXzqyEdit(e) {
        nui.open({
            url: "<%=basePath%>regist_gotoTree.action",
            showMaxButton: false,
            title: "选择区域",
            width: 350,
            height: 480,
            ondestroy: function (action) {
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    var words = data.split(',')
                    if (data) {
                        nui.get('qyIds').setValue(words[1]);
                        nui.get("qyIds").setText(words[0] + "市场监督管理局");
                    }
                }
            }
        });
    }

    function getJgobjectByZsbh() {
        // var  regexp_1 = /\d{12}/;
        var regexp_1 = /^\d{12}$/;
        zsbh = nui.get('zsbh').getValue();
        var result = regexp_1.test(zsbh);
        if (!result) {
            $("#msg").hide();
            nui.alert('请输入12位纯数字的证书编号', '温馨提示');
            nui.get('saveGk').setEnabled(false);
            return;
        }
        nui.get('saveGk').setEnabled(true);
        getInfo();
    }

    function getInfo() {
        $.ajax({
            url: "<%=basePath%>regist_getInfoByUserName.action",
            type: 'post',
            cache: false,
            success: function (result) {//成功获得数据时
                var map = nui.decode(result);
                nui.get('jgobjectname').setValue(map.qymc);
                nui.get('zhusuo').setValue(map.zcdz);
                var nodeNames = map.nodeNames.split(",");
                nui.get('qyIds').setValue(map.areaId);
                nui.get('qyIds').setText(nodeNames[0] + nodeNames[1] + "市场监督管理局");
                nui.get('yzbm').setValue(map.yzbm);
                nui.get('email').setValue(map.email);
                nui.get('jgfzr').setValue(map.lxr);
                nui.get('jgfzrphone').setValue(map.lxdh);
                nui.get('jgllr').setValue(map.lxr);
                nui.get('shxydm').setValue(map.zzjgdm);
                nui.get('zsbh').setValue(zsbh);
            }
        });
    }

    function loadZy() {
        var zys = new Array();
        $.ajax({
            url: "<%=basePath%>common_getShiysjyjcScType.action",
            async: false,
            success: function (text) {
                text = nui.decode(text);
                for (var i = 0; i < text.length; i++) {
                    zys.push(text[i].DAIMA);
                }
            }
        });
        return zys;
    }


</script>

</body>
</html>