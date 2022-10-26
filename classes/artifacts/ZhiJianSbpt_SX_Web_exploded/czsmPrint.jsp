<%@ page language="java"
         import="java.util.*,
                 com.zhuozhengsoft.pageoffice.*,
                 com.zhuozhengsoft.pageoffice.wordwriter.*"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po" %>
<%@ page import="com.yongjie.ZhiJianSbpt.container.ServiceProvider" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.yongjie.ZhiJianSbpt.service.*" %>
<%@ page import="com.yongjie.ZhiJianSbpt.model.*" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.R" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.SysFinalRecource" %>
<%@ page import="com.alibaba.fastjson.JSONObject" %>
<%
    PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
    poCtrl1.setServerPage(request.getContextPath() + "/poserver.zz");
    WordDocument doc = new WordDocument();

    poCtrl1.setWriter(doc);
    //添加pageoffice保存按钮
    poCtrl1.addCustomToolButton("另存到本机", "SaveAs()", 1);

    // 设置文件保存之后执行的事件
    poCtrl1.setMenubar(false);//隐藏菜单栏
    poCtrl1.setOfficeToolbars(false);//隐藏工具栏
    //打开Word文件
    poCtrl1.webOpen("jsp/jyjc/rzrk/zzrd/doc/zzrdCzsm.doc", OpenModeType.docNormalEdit, "李杰");
    poCtrl1.setTagId("PageOfficeCtrl1"); //此行必须
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>检验检测资质认定操作说明</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <script src="<%=request.getContextPath()%>/nui/nui.js" type="text/javascript"></script>
</head>

<body>
<form id="form1">
    <div style="width: auto; height: 950px;">
        <po:PageOfficeCtrl id="PageOfficeCtrl1"/>
    </div>
</form>
<script type="text/javascript">
    nui.parse();

    function Save() {
        document.getElementById("PageOfficeCtrl1").WebSave();
    }

    //文档保存之后执行此方法
    function AfterDocumentSaved(param) {
        document.getElementById("PageOfficeCtrl1").Alert("保存成功！");
        CloseWindow();
    }

    function SaveAs() {
        document.getElementById("PageOfficeCtrl1").ShowDialog(2);
    }

    //关闭有父页面的窗口
    function CloseWindow(action) {
        window.close();
    }
</script>
</body>
</html>
