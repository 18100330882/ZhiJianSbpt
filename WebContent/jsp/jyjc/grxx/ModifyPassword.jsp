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
    <title>标题</title>
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
</head>
<!--
- Author(s): 杨青岭
- CreateTime: 2016年8月22日 下午5:22:07
- Copyright: Copyright (c) 2016
- Company: 北京永杰友信科技有限公司
- Version：1.0
- Description:
-->
<body>
<form id="registerForm">
    <fieldset style="border:solid 1px #aaa;padding:3px;">
        <legend>重设密码</legend>
        <div>
            <input name="id" class="nui-hidden"/>
            <input name="caoDate" class="nui-hidden"/>
            <table id="reg_table">
                <tr>
                    <td class="btxt"><span class="red">*</span>旧密码：</td>
                    <td>
                        <input id="pwd" style="width: 260px;" name="password" class="nui-password" required="true"
                               onvalidation="checkOldPsd"/>&nbsp;&nbsp;&nbsp;<span
                            id="tips" class="reg_tips"></span>
                    </td>
                </tr>
                <tr>
                    <td class="btxt"><span class="red">*</span>新密码：</td>
                    <td>
                        <input id="confirmpwd" style="width: 260px" name="password2" class="nui-password"
                               required="true"
                               onvalidation="onPwdOnvalidation"/>&nbsp;&nbsp;&nbsp;<span id="newPass" class="reg_tips">密码长度至少有8个字符,至少包含以下四类字符中的三类：大写字母,小写字母,数字,以及键盘上的符号（如 !、@、#）</span>
                    </td>
                </tr>
                <tr>
                    <td class="btxt"><span class="red">*</span>再次输入新密码：</td>
                    <td>
                        <input id="confirmpwd3" style="width: 260px" name="password3" class="nui-password"
                               required="true"
                               onvalidation="onRepeatPwdOnvalidation"/>&nbsp;&nbsp;&nbsp;<span id="newPassTwo"
                                                                                               class="reg_tips">请重复上面输入的密码。</span>
                    </td>
                </tr>
                <tr>
                    <td class="btxt"><span class="red">*</span>验证码：</td>
                    <td>
                        <input id="veryCode" name="veryCode" onblur="isRightCode" requiredErrorText="验证码不能为空"
                               class="nui-textbox" onenter="onKeyEnter"
                               required="true" style="width:150px;"/>
                        <img id="imgObj" alt="一张图片" src="<%=basePath%>VerifyCodeServlet"/>
                        <a href="javascript:void(0)" onclick="changeImg()">换一张</a>
                        <div id="info" style="font-size:17px;color:red"></div>
                    </td>
                </tr>
            </table>
        </div>
    </fieldset>
</form>
<div style="padding:5px; margin: 0 auto;text-align: center;">
    <a class="nui-button" onclick="savePassword" iconCls="icon-save" style="width: 60px; margin-right: 20px;">保存</a>
    <a class="nui-button" onclick="cancel" iconCls="icon-cancel" style="width: 60px; margin-right: 20px;">取消</a></div>
<script type="text/javascript">
    nui.parse();
    var form = new nui.Form("#registerForm");
    var flag = true;//全局变量

    //保存修改后的密码
    function savePassword() {
        if (flag == false) {
            nui.alert("旧密码输入错误", "提醒", function tixing() {
                nui.get("#pwd").focus();
            });
            return;//当老密码验证不通过时 此方法被截断;
        }
        form.validate();
        if (form.isValid() == false) return;
        var psd = nui.get("#confirmpwd").getValue();//得到新密码了
        $.ajax({
            type: "POST",
            url: "<%=basePath%>regist_changePsd.action",
            data: {"pass": psd},
            success: function (text) {
                if (text.trim() == "success") {
                    nui.alert("重设密码成功", "提醒", function tixing() {
                        CloseWindow();
                    });
                } else {
                    nui.alert("重设密码失败", "提醒", function tixing() {
                        CloseWindow();
                    });
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

    //点击取消按钮时触发 ,关闭窗口.
    function cancel() {
        CloseWindow();
    }

    //要验证老密码是否输入正确
    function checkOldPsd(e) {
        var pass = e.value;
        $.ajax({
            type: "POST",
            url: "<%=basePath%>regist_checkOldPsd.action",
            data: {"pass": pass},
            success: function (text) {
                if (text.trim() == "error") {
                    document.getElementById("tips").innerHTML = "旧密码输入错误";
                    nui.get("#pwd").focus();
                    flag = false;
                } else {
                    document.getElementById("tips").innerHTML = "";
                    flag = true;
                }
            }
        });
    }

    //验证密码
    function onPwdOnvalidation(e) {

        if (e.isValid) {
            /*if (e.value.length < 6 || e.value.length > 18 || isPwd(e.value) == false) {
                e.errorText = "密码长度至少有8个字符,至少包含以下四类字符中的三类：大写字母,小写字母,数字,以及键盘上的符号（如 !、@、#）";
                e.isValid = false;
            }*/
            var level = getPwdStrength(e.value);
            //非强密码，提示
            if (level) {
                document.getElementById("newPass").innerHTML = "密码格式错误:密码长度至少有8个字符,至少包含以下四类字符中的三类：大写字母,小写字母,数字,以及键盘上的符号（如 !、@、#）";
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
        var pwd = nui.get("#confirmpwd").getValue();
        if (pwd != e.value) {
            document.getElementById("newPassTwo").innerHTML = "两次密码不一样,请重新输入!";
            e.errorText = "两次密码不一致！";
            e.isValid = false;
        } else {
            e.isValid = true;
        }
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

    var getPwdStrength = function (pwd) {
        var level = 1;
        if (pwd.length >= 8) {
            level += 5;
        }
        var m = [
            /[a-z]/,
            /[A-Z]/,
            /[\d]/,
            /[^\da-zA-Z]/
        ];
        for (var i = m.length - 1; i >= 0; i--) {
            if (null !== pwd.match(m[i])) {
                level += 1;
            }
        }
        if (level <= 8) {
            return true;
        } else {
            return false;
        }
    };
</script>
</body>
</html>