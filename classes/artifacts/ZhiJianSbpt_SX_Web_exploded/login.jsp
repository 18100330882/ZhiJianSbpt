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
    <title>陕西省网上申报系统</title>
    <script type="text/javascript" src="<%=basePath %>nui/nui.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>style/css/login.css"/>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<!--
- Author(s): 杨青岭
- CreateTime: 2016年8月8日 上午8:52:10
- Copyright: Copyright (c) 2016
- Company: 北京永杰友信科技有限公司
- Version：1.0
- Description:
-->
<body id="body">

<div id="loginWindow" class="login_con">
    <div class="login_wz">
        <div class="dlcon_nr">
            <div class="user_con">
                <label for="username$text">帐<span style="color:#f9f9fb">一</span>号</label>
                <input id="username" name="username" class="yj-textbox-user" <%-- onblur="javascript:chechAction()"--%> />
            </div>
            <div class="user_con">
                <label for="pwd$text">密<span style="color:#f9f9fb">一</span>码</label>
                <input id="pwd" type="password" name="pwd" class="yj-textbox-pwd"/>
            </div>
            <div class="user_con">
                <label for="veryCode$text">验证码</label>
                <input id="veryCode" name="veryCode" class="yj-textbox-verycode"/>

                <img id="imgObj" alt="一张图片" width="80px" height="34px" src="<%=basePath%>VerifyCodeServlet"/>
                <img src="style/images/login/reload.png" width="20" height="20" onclick="changeImg()"/>
            </div>
            <div id="info"></div>
            <div class="btn_con">
                <input name="" type="button" value="登 录" class="user_btn" onclick="onLoginClick()"/>
                <input name="" type="button" value="重 置" class="user_btn" onclick="onResetClick()"/>
            </div>
            <a href="#" onclick="gotoRegist()" class="dl_zc" style="margin-left: 50%;margin-top: 5px;">立即注册</a>
        </div>
    </div>
    <div class="login_bot">
        版权所有：陕西省市场监督管理局
    </div>
</div>
<script type="text/javascript">
    nui.parse();
    //注册回车事件
    $('#veryCode,#username,#pwd').bind('keypress', function (event) {
        if (event.keyCode == "13") {
            //需要处理的事情
            onLoginClick();
        }
    });

    function onLoginClick(e) {//提交数据
        var strusername = $("#username").val();
        var strpwd = $("#pwd").val();
        var strveryCode = $("#veryCode").val();
        if ($("#info").html() == "账号还没激活" || $("#info").html() == "此账号还没注册") {
            return;
        }
        if (strusername.length < 2 || strpwd.length < 3 || strveryCode.length < 4) {
            $("#info").html("请输入正确的用户名或密码");
            changeImg();
            return;
        }
        isRightCode();
        if (dataR > 0) {
            return;
        } else {
            var aMessage = nui.showMessageBox({
                title: "温馨提示",
                showCloseButton: false,
                minWidth: 250,
                message: "正在登录中...",
                buttons: [],
                iconCls: "mini-messagebox-waiting"
            });
            var name = $("#username").val();
            var pwd = $("#pwd").val();
            $.ajax({
                type: "post",
                url: "<%=basePath%>login_doLogin.action",
                data: {"name": name, "pwd": pwd},
                success: function (str) {
                    if (str.trim() == "ok") {//登录成功时
                        window.location = "<%=basePath %>mainFrame.jsp";
                    } else if (str.trim() == "error") {
                        nui.hideMessageBox(aMessage);
                        $("#info").html("用户名或密码不正确");
                        changeImg();
                    }
                }
            });
        }
    }

    //重置
    function onResetClick(e) {
        $("#username").val("");
        $("#pwd").val("");
        $("#veryCode").val("");
        $("#info").html("");
        changeImg();//点击重置时自动更换验证码
    }

    //更换验证码
    function changeImg() {
        var verycode = $("#veryCode");//得到验证码文本框对象
        verycode.val("");

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
        return urlurl;
    }

    //自己输入的验证字符 被提交到servlet进行比对 并返回提示语句
    function isRightCode() {
        var verycode = $("#veryCode");//得到验证码文本框对象
        code = "c=" + verycode.val();
        $.ajax({
            type: "POST",
            url: "<%=basePath%>ResultServlet",
            data: code,
            async: false,
            success: callback = function (data) {
                dataR = data.length;
                if (data.length > 0) {//data不为空说明有提示语句 故要结束方法
                    $("#info").html(data);
                    changeImg();
                }
            }
        });
    }

    function gotoRegist() {
        window.location = "<%=basePath %>regist.jsp";
    }
</script>
</body>
</html>