<%@ page language="Java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    Object logOnUserName = request.getSession().getAttribute("userName");
    if (logOnUserName == null) {
        response.sendRedirect("login.jsp");
    }
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <link rel="shortcut icon" href="<%=basePath%>style/images/login/xzsp.png" type="image/x-icon"/>
    <title>陕西省检验检测网上申报系统</title>
    <script type="text/javascript" src="<%=basePath%>nui/nui.js"></script>
    <script Language="JavaScript" type="text/javascript" src="<%=basePath%>style/scripts/topOrdown.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>style/css/mainFrame.css"/>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="食品安全,质量监督,综合监管">
    <meta http-equiv="description" content="站点主页面">
</head>
<body>
<!--Layout-->
<div id="layout1" class="nui-layout" style="width:100%;height:100%;">
    <div class="header" region="north" height="122" showSplit="false" showHeader="false">
        <div class="main_top">
            <div class="main_banner">
                <div class="main_tool">
                    <a href=""><img src="<%=basePath %>style/images/mainFrame/kj_menu.png" width="22"
                                    height="22"/>快捷</a>
                    <a href="mainFrame.jsp"><img src="<%=basePath %>style/images/mainFrame/sy_menu.png" width="22"
                                                 height="22"/>首页</a>
                    <a href="javascript:void(0);"><img src="<%=basePath %>style/images/mainFrame/xx_menu.png" width="22"
                                                       height="22"/>消息</a>
                    <a href="javascript:onLogOffClick();"><img src="<%=basePath %>style/images/mainFrame/zx_menu.png"
                                                               width="22" height="22"/>注销</a>
                    <a href="javascript:onCloseClick();"><img src="<%=basePath %>style/images/mainFrame/tc_menu.png"
                                                              width="22" height="22"/>关闭</a>
                </div>
            </div>
            <div class="main_user">
                <div class="main_user_nr">
                    <span>当前用户：<%=(logOnUserName == null ? "" : logOnUserName.toString())%></span>
                    <img src="<%=basePath %>style/images/mainFrame/usr_ico.png" width="22" height="22" class="left"/>
                </div>
            </div>
        </div>
    </div>
    <div title="south" region="south" showSplit="false" showHeader="false" height="30">
        <div style="line-height:28px;text-align:center;cursor:default">Copyright © 版权所有:陕西省市场监督管理局 技术支持:北京永杰友信科技有限公司
        </div>
    </div>
    <div title="center" region="center" style="border:0;" bodyStyle="overflow:hidden;">
        <!--Splitter-->
        <div class="nui-splitter" style="width:100%;height:100%;" borderStyle="border:0;">
            <div size="15%" maxSize="250" minSize="100" showCollapseButton="true" style="border:0;">
                <!--OutlookTree-->
                <div id="leftTree" class="nui-outlooktree" url="<%=basePath%>mainFrame_getMenus.action"
                     onnodeclick="onNodeSelect"
                     textField="MENUNAME" idField="ID" parentField="PARENTID" showTreeLines="false" expandOnLoad="0"
                >
                </div>
            </div>
            <div showCollapseButton="true" style="border:0;">
                <!--Tabs refreshOnClick点击tab时刷新-->
                <div id="mainTabs" class="nui-tabs" activeIndex="0" style="width:100%;height:100%;"
                     plain="false" onactivechanged="onTabsActiveChanged" contextMenu="#tabsMenu">
                </div>
                <ul id="tabsMenu" class="nui-contextmenu" onbeforeopen="onBeforeOpen">
                    <li iconCls='icon-collapse' onclick="refreshTab">刷新</li>
                    <li iconCls='icon-collapse' onclick="closeTab">关闭</li>
                    <li iconCls='icon-collapse' onclick="closeAllBut">关闭其他</li>
                    <li iconCls='icon-collapse' onclick="closeAll">关闭所有</li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div id="floatpoint"
     style="position: absolute; visibility: visible; z-index: 100; border: 0px solid #000; margin-right: 30px;">
    <div style="width: 80px; height: 30px; font-size: 14px; font-weight: bold; text-align: right; cursor: hand; margin-right: 10px; margin-top: 5px;"
         onClick="closead();">
        <img src="style/images/mainFrame/close2.png">
    </div>
    <!-- 这个窗口可以关闭噢。可以放漂浮广告哦！<br> -->
    <a href="javascript:void(0);" onClick="onDianClick()">
        <img src="style/images/firstPage/kuai_05.png">
    </a>
</div>


<script type="text/javascript">
    nui.parse();
    var tree = nui.get("leftTree");
    var tabs = nui.get("mainTabs");
    var currentTab = null;
    var node = nui.decode("{'id':'0','text':'首页'}");
    showTab(node, "HomePage.jsp");
    var xx = "";

    /*function onDianClick() {
        window.open({
            url: "<%=basePath%>czsmPrint.jsp",
            title: "检验检测资质认定操作说明", width: '70%', height: '90%',
            onload: function () {

            },
        });
    }*/

    function onDianClick() {
        window.open("<%=basePath%>czsmPrint.jsp");
    }

    function AA() {
        var userName = '<%=session.getAttribute("userName")%>';
        var qymc = '';
        var newshxydm = "";
        getQymcByUserName();

        function getQymcByUserName() {
            //查询注册表的企业名称
            $.ajax({
                url: "<%=basePath%>bizTzsbzzdw_getQymc.action",
                type: 'post',
                data: {userName: userName},
                cache: true,
                async: false,
                success: function (text) {//成功获得数据时
                    var data = nui.decode(text);//后台传过来了json格式的数据(其实也是完整的model了),到了这儿转化成对象,再去赋值.
                    qymc = data.qymc;
                    newshxydm = data.newShxydm;
                    getIfInJyjcjgxxBuHeGe();
                }
            });
        }

        //判断检验检测变更是否存在档案库，不存在先进行信息采集
        function getIfInJyjcjgxxBuHeGe() {
            nui.ajax({
                url: "<%=basePath%>jyjcjgxx_getIfInJyjcjgxxBuHeGe.action",
                type: "post",
                data: {newshxydm: newshxydm, jgflId: "38"},
                async: false,
                success: function (text) {
                    if (text.trim() == 0) {
                        xx = 1;
                        <%-- var data = nui.decode(text);
                        window.location.href="<%=basePath%>RegistJyjcjgxxcj.jsp";  --%>
                    }
                    <%-- window.location.href="<%=basePath%>RegistJyjcjgxxcj.jsp"; --%>
                }
            });
        }
    }


    function showTab(node, url) {
        var id = "tab$" + node.ID + "";
        var tab = tabs.getTab(id);
        if (!tab) {
            tab = {};
            tab._nodeid = node.ID + "";
            tab.name = id;
            tab.title = node.text;
            tab.showCloseButton = true;
            //这里拼接了url，实际项目，应该从后台直接获得完整的url地址
            tab.url = "<%=basePath%>" + url;
            tabs.addTab(tab);
        }
        //激活此tab页
        tabs.activeTab(tab);
    }

    function onNodeSelect(e) {
        var node = e.node;
        var isLeaf = e.isLeaf;
        if (isLeaf) {
            showTab(node, node.MENULINK);
        }
    }

    function onClick(e) {
        window.location.href = "mainFrame.jsp?userName=<%=logOnUserName%>";
    }

    //点击退出按钮
    function onLogOffClick(e) {
        nui.confirm("确定要退出系统吗？", "系统提示",
            function (action) {
                if (action == "ok") {
                    window.location.href = "login.jsp";
                }
            });
    }

    //点击系统关闭按钮
    function onCloseClick(e) {
        nui.confirm("确定要关闭该系统吗？", "系统提示",
            function (action) {
                if (action == "ok") {
                    window.opener = null;
                    window.open('', '_self', '');
                    window.close();
                }
            });
    }

    //切换tab
    function onTabsActiveChanged(e) {
        var tabs = e.sender;
        var tab = tabs.getActiveTab();
        if (tab && tab._nodeid) {

            var node = tree.getNode(tab._nodeid);
            if (node && !tree.isSelectedNode(node)) {
                tree.selectNode(node);
            }
        }

        tabs.reloadTab(tab);//重新加载此tab
    }

    //给tab添加右键菜单
    function onBeforeOpen(e) {
        currentTab = tabs.getTabByEvent(e.htmlEvent);
        if (!currentTab) {
            e.cancel = true;
        }
    }

    //关闭本标签
    function closeTab() {
        tabs.removeTab(currentTab);
    }

    //关闭其他标签
    function closeAllBut() {
        tabs.removeAll(currentTab);
    }

    //关闭所有标签
    function closeAll() {
        tabs.removeAll();
    }

    function refreshTab() {
        tabs.reloadTab(currentTab);
    }

    function closead() {
        $("#floatpoint").hide();
    }

</script>

</body>
</html>