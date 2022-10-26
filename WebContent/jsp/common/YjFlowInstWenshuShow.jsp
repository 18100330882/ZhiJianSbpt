<%@ page import="java.io.*" %>
<%@ page language="Java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.yongjie.ZhiJianSbpt.utilities.*" %>
<%@ page import="com.zhuozhengsoft.pageoffice.*" %>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";

    String nodeId = request.getParameter("nodeId");
    String flowId = request.getParameter("flowId");
    String flowInstId = request.getParameter("flowInstId");

    String ffClose = "";
    DesUtils des = new DesUtils();//自定义密钥
    String userName = (String) request.getSession().getAttribute("userName");
    String isButton = request.getParameter("isButton");
    String filePath = request.getParameter("filePath");
    //String fileTitle = java.net.URLDecoder.decode(request.getParameter("fileTitle"), "utf-8");
    String fileTitle = request.getParameter("fileTitle");
    //fileTitle = new String(fileTitle.getBytes("iso8859-1"),"utf-8");
    String extense = request.getParameter("extense");
    extense = extense.toLowerCase();
    //String fileAllPath=filePath;
    String fileAllPath = "";
    File filetemp = null;
    byte[] fileContent = null;
    for (int i = 0; i < 2; i++) {
        if (i == 0) {
            fileAllPath = SysFinalRecource.SECOND_LEVEL_DIRECTORY + filePath;
        } else {
            fileAllPath = SysFinalRecource.SECOND_LEVEL_DIRECTORY_W + filePath;
        }
        filetemp = new File(fileAllPath);
        if (filetemp.exists()) {
            fileContent = FileUtil.getBytes(filetemp);
            break;
        }
    }
    if (fileContent == null) {
        response.getWriter().write("文件未找到！");
        return;
    }

    PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);//声明对象
    poCtrl1.setServerPage(request.getContextPath() + "/poserver.zz"); //此行必须,设置服务器页面
    poCtrl1.setCaption(fileTitle + extense);//设置文档标题名称
    //if (!StringUtil.isNullOrEmpty(isButton) && (!isButton.equals("null"))) {
    if (1 == 1) {
        poCtrl1.addCustomToolButton("另存到本机", "SaveAs()", 1);
        poCtrl1.addCustomToolButton("页面设置", "ShowPageSetupDlg()", 5);
        poCtrl1.addCustomToolButton("打印", "ShowPrintDlg()", 6);
        poCtrl1.addCustomToolButton("打印预览", "PrintPreView()", 7);
    } else {
        poCtrl1.setCustomToolbar(false);//隐藏自定义工具栏
    }
    poCtrl1.setMenubar(false);//隐藏菜单栏
    poCtrl1.setOfficeToolbars(false);//隐藏工具栏

    //linux系统下需要前缀file:///才能被pageoffice识别
    if (SysFinalRecource.OS.equals("linux")) {
        fileAllPath = "file://" + fileAllPath;
    }
    //windows系统下需要修改路径
    if (SysFinalRecource.OS.equals("windows")) {
        fileAllPath = fileAllPath.replaceAll("/", "\\\\");
    }
    if (extense.equals(".xls") || extense.equals(".xlsx"))//打开的是系统路径
    {
        poCtrl1.webOpen(fileAllPath, OpenModeType.xlsReadOnly, userName);//
    } else if (extense.equals(".doc") || extense.equals(".docx")) {
        poCtrl1.webOpen(fileAllPath, OpenModeType.docNormalEdit, userName);//默认打开word 文档 可编辑的
    }
    poCtrl1.setTagId("PageOfficeCtrl1"); //此行必须
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>标题</title>
    <script type="text/javascript" src="<%=basePath%>nui/nui.js"></script>
    <script type="text/javascript" src="<%=basePath %>style/js/returnNodes.js"></script>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" href="<%=basePath %>style/css/return-nodes.css"/>
</head>
<!-- 
  - Author(s): 张谦
  - CreateTime: 2016年9月26日 上午10:08:02
  - Copyright: Copyright (c) 2016
  - Company: 北京永杰友信科技有限公司
  - Version：1.0
  - Description:查看实例文书页面
  -->
<body onbeforeunload="checkLeave()">
<!-- 定义page控件的显示大小  -->
<div id="pageOfficeDivId" style="width: 79%; height: 100%;display: block;float: left;">
    <po:PageOfficeCtrl id="PageOfficeCtrl1"/>
</div>

<!-- 固定右下角 退回信息记录-->
<div class="return-main-w" id="returnMainId">
    <div id="returnMainTopId" class="return-main-top" style="float: left;">退回原因</div>
    <div id="hiddenReturnId" class="hidden-open-div"><a href="javacscript:void(0);" onclick="hiddenReturnFile(1)">隐藏</a>
    </div>
    <div id="openReturnId" class="hidden-open-div hidden-return"><a href="javacscript:void(0);"
                                                                    onclick="hiddenReturnFile(0)">展开</a></div>
    <div class="return-main-center return-hidden">
        <textarea id="returnNodesId"></textarea>
    </div>
    <div class="return-main-bottom return-hidden">
        <a href="javascript:void(0);" onclick="saveNodes()">确定</a>
        <a id="returnNodesDelId" class="return-none" href="javascript:void(0);" onclick="delNodes()">删除</a>
    </div>
</div>

<script type="text/javascript">
    //对应直接打开新窗口的方式
    function checkLeave() {
        var path = "<%=ffClose%>";
        $.ajax({
            url: "<%=basePath%>jsp/common/closeFileTemp.jsp?path=" + path,
            async: false,
            success: function (text) {
            }
        });
        //响应事件
        //event.returnValue="确定离开当前页面吗？";
    }

    //关闭有父页面的窗口
    function CloseWindow(action) {
        if (action == "close" || action == "saveToClose") {
            ;
            var path = "<%=ffClose%>";
            $.ajax({
                url: "<%=basePath%>jsp/common/closeFileTemp.jsp?path=" + path,
                success: function (text) {
                },
                error: function () {
                    nui.alert("删除文件模板失败！");
                }
            });

        }
        if (window.CloseOwnerWindow)//关闭页面
            return window.CloseOwnerWindow(action);
        else
            window.close();
    }

    function SaveAs() {
        document.getElementById("PageOfficeCtrl1").ShowDialog(2);
    }

    //页面设置
    function ShowPageSetupDlg() {
        document.getElementById("PageOfficeCtrl1").ShowDialog(5);
    }

    //打印
    function ShowPrintDlg() {
        document.getElementById("PageOfficeCtrl1").ShowDialog(4);
    }

    //打印预览
    function PrintPreView() {
        document.getElementById("PageOfficeCtrl1").PrintPreview();
    }

    var closeReturn = '<%=request.getParameter("closeReturn")%>';
    if (closeReturn == 1) {
        $("#returnMainId").show();
        // 初始赋值
        getReturnNodes('<%=basePath%>', '<%=flowId%>', '<%=flowInstId%>', '<%=nodeId%>', '<%=fileTitle%>');

        // 删除
        function delNodes() {
            delReturnNodes('<%=basePath%>', '<%=flowId%>', '<%=flowInstId%>', '<%=nodeId%>', '<%=fileTitle%>');
        }

        // 保存
        function saveNodes() {
            saveReturnNodes('<%=basePath%>', '<%=flowId%>', '<%=flowInstId%>', '<%=nodeId%>', '<%=fileTitle%>');
        }
    } else {
        // 修改原有宽度为 100%
        $("#pageOfficeDivId").css("width", "100%");
    }
</script>
</body>
</html>