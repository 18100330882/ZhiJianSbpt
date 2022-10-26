<%@ page language="Java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <link rel="shortcut icon" href="<%=basePath%>style/images/login/xzsp.png" type="image/x-icon"/>
    <title>陕西省行政审批网上申报系统</title>
    <script type="text/javascript" src="<%=basePath %>nui/nui.js"></script>
    <link rel="stylesheet" href="<%=basePath %>style/css/registerPage.css"/>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <style type="text/css">
        .mini-buttonedit-input {
            text-align: right;
        }
    </style>
</head>
<!--
- Author(s): 杨青岭
- CreateTime: 2016年8月8日 上午10:51:31
- Copyright: Copyright (c) 2016
- Company: 北京永杰友信科技有限公司
- Version：1.0
- Description:注册页面
-->
<body>
<div class="logo">
    <div style=" width:378px; height:78px;background:url(style/images/registerPage/banner.jpg) no-repeat; margin-left:10%;"></div>
</div>
<div class="big">
    <div class="banner">
        <p>>>已经有账号，请<a id="link1" href="<%=basePath %>login.jsp">点击这里登录</a></p>
        <span>请认真填写一下信息，严肃的商业信息有助于您获得别人的信任，结交潜在的商业伙伴，获得更多商业机会！<span style="color:red;">* 	</span>为必填项</span>
    </div>
    <div class="ZC_xinxi">
        <div>
            <form id="registerForm">
                <div class="ZC_top">
                    <input name="id" class="nui-hidden"/>
                    <input name="caoDate" class="nui-hidden"/>
                    <p>注册账号信息</p><br/>
                    <table border="0px;">
                        <tr>
                            <td class="btxt">
                                <span>注册账号<span style="color:red;">*</span></span>
                            </td>
                            <td>
                                <input id="username" style="width: 260px;text-align:left" name="userName"
                                       class="nui-textbox" required="true"
                                       onvalidation="checkname"/>
                            </td>
                            <td>
                                <div id="userError1">账号由6-18位字母,数字,汉字或下划线组成。</div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <span>登录密码<span style="color:red;">*</span></span>
                            </td>
                            <td>
                                <input id="pwd" style="width: 260px;" name="password" class="nui-password"
                                       required="true"
                                       onvalidation="onPwdOnvalidation"/>
                            </td>
                            <td>
                                <div>请使用字母和数字设置密码,最少6个字符。</div>
                            </td>
                        </tr>
                        <tr>
                            <td><span>确认密码<span style="color:red;">*</span></span>
                            </td>
                            <td>
                                <input id="confirmpwd" style="width: 260px" name="password2" class="nui-password"
                                       required="true"
                                       onvalidation="onRepeatPwdOnvalidation"/>
                            </td>
                            <td>
                                <div>重复上面的密码</div>
                            </td>
                        </tr>
                        <tr>
                            <td><span>验证码　<span style="color:red;">*</span></span></td>
                            <td style="widht:400px; margin-left:5px;">
                                <input id="veryCode" name="veryCode" onblur="isRightCode" requiredErrorText="验证码不能为空"
                                       class="nui-textbox"
                                       onenter="onKeyEnter" required="true"
                                       style="width:120px; margin-left:5px; float:left; display:inline-block;"/>

                                <img id="imgObj" alt="一张图片" src="<%=basePath%>VerifyCodeServlet"
                                     style="margin-top:3px;display:inline-block;"/>
                                <a href="javascript:void(0)" onclick="changeImg()" style="margin-top:3px;">换一张</a>

                            </td>
                            <td>
                                <div id="info" style="font-size:16px;color:red"></div>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="ZC_top">
                    <p>所属法人信息</p><br/>
                    <table border="0px;">
                        <tr style="display:none;">
                            <td><span>申请人类型<span style="color:red;">*</span></span></td>
                            <td>
                                <label><input id="enterprise" name="flag" checked="checked" type="radio" disabled
                                              value="0"
                                              style="width:14px; height:14px;margin-left:10px;margin-right:0px !important;"/>
                                    企业 </label>
                                <label><input id="personal" name="flag" type="radio" value="1" disabled
                                              style="width:14px; height:14px;margin-left:20px;margin-right:0px !important;"/>
                                    个人 </label>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><span>所属法人统一社会信用代码<span style="color:red;">*</span></span></td>
                            <td>
                                <input id="zzjg2" name="zzjgdm" style="width: 260px" onvalidation="checkValue"
                                       class="nui-textbox"/>
                            </td>
                            <td>
                                <div><span class="reg_tips">请填写统一社会信用代码。</span></div>
                            </td>
                        </tr>

                        <tr>
                            <td id="fr" class="btxt1"><span>所属法人法定代表人<span style="color:red;">*</span></span></td>
                            <td>
                                <input id="fr2" name="fddbr" style="width: 260px" class="nui-textbox" required="true"/>
                            </td>
                            <td>
                                <div><span id="z2" class="reg_tips">请填写（所属法人）法定代表人。</span></div>
                            </td>
                        </tr>
                        <tr>
                            <td id="frdh" class="btxt1"><span>所属法人法定代表人电话<span style="color:red;">*</span></span></td>
                            <td>
                                <input id="frdh2" name="fddbrdh" style="width: 260px" class="nui-textbox"
                                       onvalidation="onPhoneOnvalidation2"
                                       required="true"/>
                            </td>
                            <td>
                                <div><span id="z3" class="reg_tips">请填写（所属法人）法定代表人电话。</span></div>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="ZC_top">
                    <p>本企业信息</p><br/>
                    <table border="0px;">
                        <tr id="eIsDlfr">
                            <td><span>法人类型<span style="color:red;">*</span></span></td>
                            <td colspan="2">
                                <label><input id="enterprise1" name="isDlf" type="radio" value="0"
                                              style="width:14px; height:14px;margin-left:10px;margin-right:0px !important;"/>
                                    独立法人 </label>
                                <label><input id="personal1" name="isDlf" checked="checked" type="radio" value="1"
                                              style="width:14px; height:14px;margin-left:20px;margin-right:0px !important;"/>
                                    非独立法人 </label>
                            </td>
                        </tr>
                        <tr>
                            <td><span>企业名称<span style="color:red;">*</span></span></td>
                            <td><input id="qymc" name="qymc" style="width: 260px" class="nui-textbox" required="true"
                                       onvalidation="checkqymc"/></td>
                            <td>
                                <div><span id="nametips" class="reg_tips">请填写营业执照上或批准文件的企业名称。</span>
                                    <span id="nameError" style="color:#CC0000"></span>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td><span>申请人地址<span style="color:red;">*</span></span></td>
                            <td><input name="zcdz" style="width: 260px" class="nui-textbox" required="true"/></td>
                            <td>
                                <div><span class="reg_tips">请填写营业执照上或批准文件的企业地址。</span></div>
                            </td>
                        </tr>
                        <tr>
                            <td><span>联系人<span style="color:red;">*</span></span></td>
                            <td><input name="lxr" style="width: 260px" class="nui-textbox" required="true"/></td>
                            <td>
                                <div><span class="reg_tips">请填写联系人姓名。</span></div>
                            </td>
                        </tr>
                        <tr>
                            <td class="btxt1"><span>联系电话<span style="color:red;">*</span></span></td>
                            <td>
                                <input id="lxdh" name="lxdh" style="width: 260px" class="nui-textbox"
                                       onvalidation="onPhoneOnvalidation"
                                       required="true"/>
                            </td>
                            <td>
                                <div><span id="s6" class="reg_tips">请填写联系电话。</span></div>
                            </td>
                        </tr>
                        <tr>
                            <td class=""><span>行政区域<span style="color:red;">*</span></span></td>
                            <td>
                                <input name="areaId" style="width: 255px;text-align:right" class="nui-buttonedit"
                                       onbuttonclick="onButtonEdit"
                                       required="true"/>
                            </td>
                            <td>
                                <div><span class="reg_tips">请选择企业所在行政区号。</span></div>
                            </td>
                        </tr>
                        <tr>
                            <td class="btxt1"><span>电子邮件<span style="color:red;">*</span></span></td>
                            <td><input name="email" style="width: 260px" class="nui-textbox" vtype="email"
                                       required="true"/></td>
                            <td>
                                <div><span class="reg_tips">请输入邮箱地址。</span></div>
                            </td>
                        </tr>
                        <tr>
                            <td class="btxt1"><span>邮编<span style="color:red;">*</span></span></td>
                            <td><input name="yzbm" style="width: 260px" class="nui-textbox" required="true"
                                       onvalidation="onYZBMValidation"/></td>
                            <td>
                                <div><span class="reg_tips">请填写单位所在地邮编。</span></div>
                            </td>
                        </tr>
                    </table>
                </div>
            </form>
            <div style="padding:5px; margin: 0 auto;text-align: center;">
                <a id="regbtn" class="nui-button" onclick="register" iconCls="icon-save" enabled="false">立即注册</a>
                <div id="checkbox" class="nui-checkbox" text="我已阅读并同意" checked="false"
                     oncheckedchanged="onValueChanged"></div>
                <a href="javascript:void(0);" onclick="onyhxy()">用户协议</a>
            </div>
        </div>
    </div>
    <div style="padding:5px; margin: 0 auto;text-align: center;">技术支持：北京永杰友信科技有限公司&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
</div>

<script type="text/javascript">
    nui.parse();
    var form = new nui.Form("#registerForm");//得到整个form对象
    var areaName = nui.getbyName("areaId");//行政区域
    var regt = nui.get("regbtn");//注册按钮
    var checkbox = nui.get("checkbox");//选择框

    function checkValue(e) {//判断组织机构代码和信用代码是否合法
        var value = e.value;
        //是企业时要填写9位组织机构代码或18位社会信用代码
        if (value.length == 9 || value.length == 18) {
            e.isValid = true;
        } else {
            e.errorText = "请填写组织机构代码9位，社会信用代码18位.";
            e.isValid = false;
        }
    }


    //验证用户名填写规则,和查重
    function checkname(e) {
        if (e.isValid) {
            if (e.value.length < 6 || e.value.length > 18 || isregUserName(e.value) == false) {
                e.errorText = "帐号由6-18位的字母、数字、汉字或下划线组成";
                e.isValid = false;
            } else {
                var userName = nui.get("#username").getValue();

                nui.ajax({
                    url: "<%=basePath%>regist_check.action",//到后台查重写查重方法
                    data: {"name": userName},
                    type: 'POST',
                    success: function (str) {

                        if (str.trim() == "error") {
                            nui.alert("用户已经存在,不能重复注册!", "提醒");
                            nui.get("username").setValue("");
                        }
                    }
                });
            }
        }
    }

    function checkqymc(e) {
        if (e.isValid) {
            var qymc = nui.get("#qymc").getValue();
            nui.ajax({
                url: "<%=basePath%>regist_check.action",//到后台查重写查重方法
                data: {qymc: qymc},
                type: 'POST',
                success: function (str) {
                    if (str.trim() == "error") {
                        nui.alert("该企业信息已存在!请联系工作人员!", "提醒");
                        nui.get("qymc").setValue("");
                    }
                }
            });
        }
    }

    //设置用户名格式
    function isregUserName(v) {//用户名的书写格式(字母 数字 下划线)
        var re = new RegExp("^[0-9a-zA-Z\_\u4e00-\u9fa5]+$");
        if (re.test(v)) return true;
        return false;
    }

    //验证密码
    function onPwdOnvalidation(e) {
        if (e.isValid) {
            if (e.value.length < 6 || e.value.length > 18 || isPwd(e.value) == false) {
                e.errorText = "帐号由6-18位的字母、数字或下划线组成";
                e.isValid = false;
            }
        }
    }

    //密码填写规则 由字母、数字或下划线组成
    function isPwd(v) {
        var re = new RegExp("^[0-9a-zA-Z\_]+$");
        if (re.test(v)) return true;
        return false;
    }

    //判断密码两次输入是否一致
    function onRepeatPwdOnvalidation(e) {
        var pwd = nui.get("#pwd").getValue();
        if (pwd != e.value) {
            e.errorText = "密码不一致！";
            e.isValid = false;
        }
    }


    //控制号码长度
    function onPhoneOnvalidation(e) {
        if (e.value == "") return;
        if (telephone(e.value) == false && mobilephone(e.value) == false) {
            e.isValid = false;
            $("#s6").html("1.手机,请输入11位数字,以13,15,18开头.2.固话,使用'区号-号码'的方式.");
            nui.get("#lxdh").focus();
        } else {
            e.isValid = true;
            $("#s6").html("请填写联系电话。");
        }
    }

    //控制号码长度2
    function onPhoneOnvalidation2(e) {
        if (e.value == "") return;

        if ($("input[name=flag]:checked").attr("id") == "enterprise") {//是企业时
            if (telephone(e.value) == false && mobilephone(e.value) == false) {
                e.isValid = false;
                $("#z3").html("1.手机,请输入11位数字,以13,15,18开头.2.固话,使用'区号-号码'的方式.");
                nui.get("#frdh2").focus();
            } else {
                $("#z3").html("请填写单位法人电话");
                e.isValid = true;
            }
        } else {//是个人时
            if (telephone(e.value) == false && mobilephone(e.value) == false) {
                e.isValid = false;
                $("#z3").html("1.手机,请输入11位数字,以13,15,18开头.2.固话,使用'区号-号码'的方式.");
                nui.get("#frdh2").focus();
            } else {
                $("#z3").html("请填写负责人电话");
                e.isValid = true;
            }
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

    //用户协议
    function onyhxy() {//要写一个页面
        nui.open({
            url: "<%=basePath%>Agreement.jsp",
            title: "用户协议",
            width: 740, height: 480,
            showModal: true,
            ondestroy: function (action) {
                checkbox.setChecked("true");
                regt.enable();//启动注册按钮
            }
        });
    }

    //当选择框被选择时
    function onValueChanged(e) {
        var checked = this.getChecked();

        if (checked) {
            regt.enable();//启动注册按钮
        } else {
            regt.disable();//禁用按钮
        }
    }

    //行政区域选择
    function onButtonEdit(e) {
        var btnEdit = this;
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
        //怎么获得flag
        if ($("input[name=flag]:checked").attr("id") == "enterprise") {
            flag = 0;
        } else {
            flag = 1;
        }
        var o = form.getData();
        form.validate();
        if (form.isValid() == false) {
            return;
        }
        var json = nui.encode(o);
        nui.ajax({
            url: "<%=basePath%>regist_doRegist.action",
            data: {data: json, flag: flag},//这里的方式?
            type: 'POST',
            success: function (str) {
                if (str.trim() == "success") {
                    nui.alert("注册成功", "提醒", function () {
                        nui.loading("正在跳转登录页面", "注册成功");
                        setTimeout(function () {
                            window.location = "<%=basePath%>login.jsp";//注册成功后转到登录页面
                        }, 2000);
                    });
                } else {
                    nui.alert("注册失败");
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText);
            }
        });
    }

    //更换验证码
    function changeImg() {
        var verycode = nui.get("#veryCode");//得到验证码文本框对象
        verycode.setValue("");
        var imgSrc = $("#imgObj"); //得到验证框对象
        var src = imgSrc.attr("src");
        imgSrc.attr("src", chgUrl(src)); //点击"换一张"时 就改变一次请求图片的路径 或者叫时间戳
    }

    //时间戳
    //为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
    function chgUrl(url) {
        var timestamp = (new Date()).valueOf();
        var urlurl = url.substring(0, 17);
        if ((url.indexOf("&") >= 0)) {
            urlurl = url + "×tamp=" + timestamp;
        } else {
            urlurl = url + "?timestamp=" + timestamp;
        }
        $("#info").html("");
        return urlurl;
    }

    //自己输入的验证字符 被提交到servlet进行比对 并返回提示语句
    function isRightCode() {
        var verycode = nui.get("#veryCode");//得到验证码文本框对象
        code = "c=" + verycode.value;
        $.ajax({
            type: "POST",
            url: "<%=basePath%>ResultServlet",
            data: code,
            async: false,
            success: callback = function (data) {
                if (data.length > 0) {//data不为空说明有提示语句 故要结束方法
                    $("#info").html(data);
                } else {
                    $("#info").html("");
                }
            }
        });
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
</script>
</body>
</html>