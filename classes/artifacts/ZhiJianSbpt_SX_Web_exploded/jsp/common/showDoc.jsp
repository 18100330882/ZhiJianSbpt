<%@ page import="java.io.*" %>
<%@ page language="Java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.zhuozhengsoft.pageoffice.*" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.DesUtils" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.SysFinalRecource" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.FileUtil" %>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";

    DesUtils des = new DesUtils();//自定义密钥
    String filePath = request.getParameter("filePath");
    String fileType = request.getParameter("fileType") + "";
    fileType = fileType.toLowerCase();
    String fileName = request.getParameter("fileName");

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
    poCtrl1.setCaption(fileName);//设置文档标题名称
    poCtrl1.addCustomToolButton("另存到本机", "SaveAs()", 1);
    //poCtrl1.addCustomToolButton("页面设置", "ShowPageSetupDlg()", 5);
    poCtrl1.addCustomToolButton("打印", "ShowPrintDlg()", 6);
    poCtrl1.addCustomToolButton("打印预览", "PrintPreView()", 7);
    //poCtrl1.setCustomToolbar(false);//隐藏自定义工具栏
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
    fileType = fileType.replace('.',' ').trim();
    if ("xls".contains(fileType) || "xlsx".contains(fileType)) {
        poCtrl1.webOpen(fileAllPath, OpenModeType.xlsReadOnly, "admin");//
    } else if ("doc".contains(fileType) || "docx".contains(fileType)) {
        poCtrl1.webOpen(fileAllPath, OpenModeType.docNormalEdit, "admin");//默认打开word 文档 可编辑的
    }
    poCtrl1.setTagId("PageOfficeCtrl1"); //此行必须
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>预览</title>
    <script type="text/javascript" src="<%=basePath%>nui/nui.js"></script>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" href="<%=basePath %>style/css/return-nodes.css"/>
</head>
<body onbeforeunload="checkLeave()">
<!-- 定义page控件的显示大小  -->
<div id="pageOfficeDivId" style="width: 100%; height: 100%;display: block;float: left;">
    <po:PageOfficeCtrl id="PageOfficeCtrl1"/>
</div>

<script type="text/javascript">

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
</script>
</body>
</html>