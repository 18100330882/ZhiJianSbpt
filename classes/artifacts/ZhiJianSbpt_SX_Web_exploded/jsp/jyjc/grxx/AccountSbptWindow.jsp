<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>修改注册信息</title>
    <script type="text/javascript" src="<%=basePath %>nui/nui.js"></script>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <style type="text/css">
        .red {
            color: red
        }

        .reg_tips {
            color: red
        }
    </style>
    <style type="text/css">
        .fdlfr {
            display: none;
        }
    </style>
</head>

<body>
<div id="reg_zcnr">
    <form id="registerForm">
        <input id="myid" name="id" class="nui-hidden"/>
        <input name="userName" class="nui-hidden"/>
        <input name="userId" class="nui-hidden"/>
        <input name="password" class="nui-hidden"/>
        <input name="isAction" class="nui-hidden"/>
        <input name="flag" class="nui-hidden">
        <input name="newShxydm" class="nui-hidden">
        <fieldset>
            <legend>基本信息</legend>
            <div>
                <table id="reg_table1">
                    <tr>
                        <td class="btxt1" style="width: 25%;"><span class="red">*</span>申请人类型：</td>
                        <td style="width: 75%;">
                            <div id="rbl" name="flag" class="nui-radiobuttonlist" valueField="id" repeatItems="0" textField="text"
                                 data="[{'id':'0','text':'企业'},{'id':'1','text':'个人'}]" value="0"></div>
                        </td>
                    </tr>
                    <tr id="eIsDlfr">
                        <td class="btxt1"><span class="red">*</span>法人类型：</td>
                        <td style="width:200px;">
                            <input class="nui-radiobuttonlist" id="isDlfr" name="isDlf" required="true" textField="text" valueField="id" value="0"
                                   data="[{'id':'0','text':'独立法人'},{'id':'1','text':'非独立法人'}]"/>
                        </td>
                    </tr>
                    <tr>
                        <td id="zzjg" class="btxt1" style="width: 230px"><span class="red">*</span><span class="fdlfr">所属法人</span>统一社会信用代码：</td>
                        <td id="sfz" class="btxt1"><span class="red">*</span>身份证号：</td>
                        <td style="width:200px;">
                            <input id="zzjg2" name="zzjgdm" style="width: 260px" onvalidation="checkValue"
                                   class="nui-textbox"/>&nbsp;&nbsp;&nbsp;<span id="z1" class="reg_tips">请填写统一社会信用代码。</span>
                            <span id="s1" class="reg_tips">请填写身份证号。</span>
                        </td>
                    </tr>
                    <tr>
                        <td id="qymc" class="btxt1"><span class="red">*</span>企业名称：</td>
                        <td id="grxm" class="btxt1"><span class="red">*</span>个人姓名：</td>
                        <td style="width:800px;">
                            <input id="sqr" name="qymc" style="width: 260px" class="nui-textbox" onvalidation="checkEnterName" required="true"/>&nbsp;&nbsp;
                            <span id="nametips" class="reg_tips">请填写营业执照上的企业名称。</span><span id="nameError" style="color:#CC0000"></span>
                            <span id="nametips1" class="reg_tips">请填写个人姓名。</span><span id="nameError" style="color:#CC0000"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="btxt1"><span class="red">*</span>联系人：</td>
                        <td style="width:200px;">
                            <input name="lxr" style="width: 260px" class="nui-textbox" required="true"/>&nbsp;&nbsp;&nbsp;<span class="reg_tips">请填写联系人姓名。</span>
                        </td>
                    </tr>
                    <tr>
                        <td class="btxt1"><span class="red">*</span>联系电话：</td>
                        <td style="width:200px;">
                            <input id="lxdh" name="lxdh" style="width: 260px" class="nui-textbox" onvalidation="onPhoneOnvalidation" required="true"/>&nbsp;&nbsp;&nbsp;<span
                                class="reg_tips">请填写联系电话。</span>
                        </td>
                    </tr>
                    <tr>
                        <td class="btxt1"><span class="red">*</span><span class="qyClass">注册</span><span class="grClass">通信</span>地址：</td>
                        <td style="width:200px;">
                            <input name="zcdz" style="width: 260px" class="nui-textbox" required="true"/>&nbsp;&nbsp;&nbsp;<span
                                class="reg_tips">请填写<span class="qyClass">营业执照上的注册</span><span class="grClass">通信</span>地址。</span>
                        </td>
                    </tr>
                    <tr>
                        <td class="btxt1"><span class="red">*</span>是否属本省：</td>
                        <td>
                            <input id="sfbs" name="sfbs" class="nui-combobox" style="width:250px;" textField="text" valueField="id" required="true"
                                   allowInput="true" data="[{'id':'0','text':'是'},{'id':'1','text':'否'}]" value="0" onvaluechanged="sfbsChange"
                                   enabled="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="btxt1"><span class="red">*</span>行政区划：</td>
                        <td style="width:200px;">
                            <input id="area" name="areaId" style="width: 260px" class="nui-buttonedit" onbuttonclick="onButtonEdit" required="true"/>
                            <input id="wsXzqh" name="wsXzqh" style="width: 260px; display: none;" emptyText="格式： XX省XX市XX县(区)" class="nui-textbox"
                                   onbuttonclick="onButtonEdit" required="true"/>&nbsp;&nbsp;&nbsp;
                            <span class="reg_tips">请选择<span class="qyClass">企业</span>所在行政区划。</span>
                        </td>
                    </tr>
                    <tr>
                        <td id="fr" class="btxt1"><span class="red">*</span><span class="fdlfr">所属法人</span>法定代表人:</td>
                        <td id="fzr" class="btxt1"><span class="red">*</span>负责人：</td>
                        <td style="width:200px;">
                            <input id="fr2" name="fddbr" style="width: 260px" class="nui-textbox" required="true"/>&nbsp;&nbsp;&nbsp;
                            <span id="s2" class="reg_tips">请填写负责人。</span>
                        </td>
                    </tr>
                    <tr>
                        <td id="frdh" class="btxt1"><span class="red">*</span><span class="fdlfr">所属法人</span>法定代表人电话:</td>
                        <td id="fzrdh" class="btxt1"><span class="red">*</span>负责人电话：</td>
                        <td style="width:200px;">
                            <input id="frdh2" name="fddbrdh" style="width: 260px" class="nui-textbox" onvalidation="onPhoneOnvalidation2"
                                   required="true"/>&nbsp;&nbsp;&nbsp;<span id="z3" class="reg_tips">请填写（所属法人）法定代表人电话。</span>
                            <span id="s3" class="reg_tips">请填写负责人电话。</span>
                        </td>
                    </tr>
                    <tr>
                        <td class="btxt1"><span class="red">*</span>电子邮件：</td>
                        <td style="width:200px;">
                            <input name="email" style="width: 260px" class="nui-textbox" vtype="email" required="true"/>&nbsp;&nbsp;&nbsp;
                            <span class="reg_tips">请输入邮箱地址。</span>
                        </td>
                    </tr>
                    <tr>
                        <td class="btxt1">邮编：</td>
                        <td style="width:200px;">
                            <input name="yzbm" style="width: 260px" class="nui-textbox" onvalidation="onYZBMValidation"/>&nbsp;&nbsp;&nbsp;
                            <span class="reg_tips">请填写<span class="qyClass">单位</span>所在地邮编。</span>
                        </td>
                    </tr>
                </table>
            </div>
        </fieldset>

    </form>
    <div style="padding:5px; margin: 0 auto;text-align: center;">
        <a class="nui-button" onclick="register" iconCls="icon-save" style="width: 60px; margin-right: 20px;">保存</a>
        <a class="nui-button" onclick="cancel" iconCls="icon-cancel" style="width: 60px; margin-right: 20px;">取消</a>
    </div>
</div>
<script type="text/javascript">
    nui.parse();
    var form = new nui.Form("#registerForm");//得到整个form对象
    var nameError = document.getElementById("nameError");//填写企业名称/个人名称时的错误提示框
    var areaName = nui.getbyName("areaId");//行政区域
    //nui.get("#rbl").setEnabled();//设置单选框不可选
    var checkbox = nui.get("checkbox");//选择框
    var check_userName = true;//boolean类型变量

    //法人类型的值改变事件
    nui.get("isDlfr").on("valuechanged", function (e) {
        if (e.value == 1)
            $(".fdlfr").show();
        else
            $(".fdlfr").hide();
    });

    //页面加载时给页面刷数据
    $(function () {
        $.ajax({
            url: "<%=basePath%>regist_getInfoByUserName.action",
            type: 'post',
            cache: false,
            success: function (text) {//成功获得数据时
                ;
                var data = nui.decode(text);
                form.setData(data);
                ;
                if (data.nodeNames) {
                    // areaName.setText(data.nodeNames);
                    var spli = data.nodeNames.split(",");
                    if (spli.length == 5) {
                        areaName.setText(spli[0] + spli[1] + spli[2]);
                    }
                    if (spli.length == 4) {
                        areaName.setText(spli[0] + spli[1]);
                    }
                }
                if (data.sfbs == 1) {
                    nui.get("area").hide();
                    nui.get("wsXzqh").show();
                }
                hideOrShow(nui.get("rbl").getValue());
            },
            error: function (jqXHR, textStatus, errorThrown) {//如果不能获得数据
                nui.alert(jqXHR.responseText);//弹出错误
            }
        });
    });

    //点击取消按钮时触发 ,关闭窗口.
    function cancel() {
        CloseWindow();
    }

    function checkValue(e) {
        var value = e.value;
        if (nui.get("#rbl").getValue() == 0) {//是企业时要填写9位组织机构代码或18位社会信用代码
            if (value.length == 9 || value.length == 10 || value.length == 18) {
                e.isValid = true;
            } else {
                e.errorText = "请填写组织机构代码9位，社会信用代码18位.";
                e.isValid = false;
            }
        } else {//是个人时 填写18位身份证号
            if (value.length == 18) {
                e.isValid = true;
            } else {
                e.errorText = "请填写18位身份证号码";
                e.isValid = false;
            }
        }

    }

    //企业名称查重
    function checkEnterName(e) {
        nameError.innerHTML = "";
        var qymc = nui.get("#sqr").getValue();//申请人
        var id = nui.get("#myid").getValue()
        if (qymc == "" || qymc == null) return;
        nui.ajax({
            url: "<%=basePath%>regist_check.action",//到后台查重 写查重方法
            data: {"qymc": qymc, "userid": id},
            type: 'POST',
            success: function (str) {
                if (str.trim() == "error") {
                    $("#nametips").hide();
                    nameError.innerHTML = "名称已存在!";
                    check_userName = false;
                    e.isValid = false;
                } else {
                    check_userName = true;
                    e.isValid = true;
                }
            }
        });

    }

    //关闭窗口
    function CloseWindow(action) {
        if (window.CloseOwnerWindow)
            return window.CloseOwnerWindow(action);
        else window.close();
    }

    //申请人类型，企业：显示 法人 ,法人电话 ,组织机构代码；个人：显示负责人 ,电话 ,身份证
    var rbl = nui.get("rbl");
    rbl.on("valuechanged", function (e) {
        var va = this.getValue();
        hideOrShow(va);
    });

    function hideOrShow(va) {
        if (va == 0) {//是企业时
            $("#zzjg").show();//组织机构代码 和社会信用代码
            $("#fr").show();//法人
            $("#frdh").show();//法人电话
            $("#sfz").hide();//隐藏身份证
            $("#fzr").hide();//隐藏负责人
            $("#fzrdh").hide();//隐藏负责人电话
            $("#qymc").show();
            $("#s1").hide();//关于个人的提示语句隐藏
            $("#s2").hide();
            $("#s3").hide();
            $("#z1").show();//关于企业的提示语句显示
            $("#z2").show();
            $("#z3").show();
            $("#eIsDlfr").show();
            $("#grxm").hide();
            $("#nametips1").hide();
            $("#nametips").show();
            $(".grClass").hide();
            $(".qyClass").show();
        } else {//是个人时
            $("#sfz").show();//显示身份证
            $("#fzr").show();//显示负责人
            $("#fzrdh").show();//显示负责人电话
            $("#zzjg").hide();
            $("#fr").hide();
            $("#frdh").hide();
            $("#z1").hide();//关于企业的提示语句隐藏
            $("#z2").hide();
            $("#z3").hide();
            $("#s1").show();//关于个人的提示语句显示
            $("#s2").show();
            $("#s3").show();
            $("#eIsDlfr").hide();
            $("#grxm").show();
            $("#qymc").hide();
            $("#nametips1").show();
            $("#nametips").hide();
            $(".grClass").show();
            $(".qyClass").hide();
        }
    }

    //控制号码长度
    function onPhoneOnvalidation(e) {
        if (e.value == "") return;

        if (telephone(e.value) == false && mobilephone(e.value) == false) {
            e.isValid = false;
            nui.alert("1.手机号码,必须输入11位数字.2.固话,使用'区号 - 号码'的方式.", "提醒", function getMouse() {
                nui.get("#lxdh").focus();
            });
        }
        e.isValid = true;
    }

    //控制号码长度2
    function onPhoneOnvalidation2(e) {
        if (e.value == "") return;

        if (telephone(e.value) == false && mobilephone(e.value) == false) {
            e.isValid = false;
            nui.alert("1.手机号码,必须输入11位数字.2.固话,使用'区号 - 号码'的方式.", "提醒", function getMouse() {
                nui.get("#frdh2").focus();
            });
        }
        e.isValid = true;
    }

    function telephone(v) {//固话验证
        var re = /^0\d{2,3}-?\d{7,8}$/;
        if (re.test(v)) return true;
        return false;
    }

    function mobilephone(v) {//手机验证
        var re = /^\d{11}$/;
        if (re.test(v)) return true;
        return false;
    }

    function mobilephone2(v) {//手机验证
        var re = /^1(3|5|8)\d{9}$/;
        if (re.test(v)) return true;
        return false;
    }

    //行政区域选择
    function onButtonEdit(e) {
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
                        areaName.setValue(words[1]);
                        areaName.setText(words[0]);
                    }
                }
            }
        });
    }

    //注册
    function register() {
        var o = form.getData();
        form.validate();
        if (form.isValid() == false) return;
        var json = nui.encode(o);
        ;
        nui.ajax({
            url: "<%=basePath%>regist_doRegist.action",
            data: {data: json},
            type: 'POST',
            success: function (str) {
                if (str.trim() == "success") {
                    nui.alert("修改成功", "提醒");
                } else {
                    nui.alert("修改失败", "提醒");
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText);
            }
        });
    }

    //邮政编码验证
    function onYZBMValidation(e) {
        if (e.isValid) {
            var pattern = /^[0-9]{6}$/;
            if (!pattern.test(e.value)) {
                e.errorText = "邮编格式不正确";
                e.isValid = false;
            }
        }
    }


    function sfbsChange(e) {
        if (e.value == 1) {
            nui.get("area").hide();
            nui.get("wsXzqh").show();
        } else {
            nui.get("wsXzqh").hide();
            nui.get("area").show();
        }
    }
</script>
</body>
</html>