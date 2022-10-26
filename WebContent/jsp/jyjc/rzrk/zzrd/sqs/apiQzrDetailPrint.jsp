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
    ApiShiYsJyjcQzrService qzrService = (ApiShiYsJyjcQzrService) ServiceProvider.getService(ApiShiYsJyjcQzrService.SERVICE_NAME);
    PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
    poCtrl1.setServerPage(request.getContextPath() + "/poserver.zz");
    WordDocument doc = new WordDocument();

    String path = request.getContextPath();
    String id = request.getParameter("id");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    ApiShiYsJyjcQzr qzrReqEntity = qzrService.queryById(Long.parseLong(id));
    String name = qzrReqEntity.getName() + "";
    String sex = qzrReqEntity.getSex() + "";
    Date birthday = qzrReqEntity.getBirthday();
    String position = qzrReqEntity.getPosition() + "";
    String jobTitle = qzrReqEntity.getJobTitle() + "";
    String department = qzrReqEntity.getDepartment() + "";
    String phone = qzrReqEntity.getPhone() + "";
    String fax = qzrReqEntity.getFax() + "";
    String email = qzrReqEntity.getEmail() + "";
    String signatureField = qzrReqEntity.getSignatureField() + "";
    String jobUndergo = qzrReqEntity.getJobUndergo() + "";
    String education = qzrReqEntity.getEducation() + "";
    String train = qzrReqEntity.getTrain() + "";
    String relevantExplanation = qzrReqEntity.getRelevantExplanation() + "";


    DataRegion PO_name = doc.openDataRegion("PO_name");
    DataRegion PO_sex = doc.openDataRegion("PO_sex");
    DataRegion PO_birthday = doc.openDataRegion("PO_birthday");
    DataRegion PO_position = doc.openDataRegion("PO_position");
    DataRegion PO_jobTitle = doc.openDataRegion("PO_jobTitle");
    DataRegion PO_education = doc.openDataRegion("PO_education");
    DataRegion PO_department = doc.openDataRegion("PO_department");
    DataRegion PO_phone = doc.openDataRegion("PO_phone");
    DataRegion PO_fax = doc.openDataRegion("PO_fax");
    DataRegion PO_email = doc.openDataRegion("PO_email");
    DataRegion PO_signatureField = doc.openDataRegion("PO_signatureField");
    DataRegion PO_jobUndergo = doc.openDataRegion("PO_jobUndergo");
    DataRegion PO_train = doc.openDataRegion("PO_train");
    DataRegion PO_relevantExplanation = doc.openDataRegion("PO_relevantExplanation");
    PO_name.setValue(name);
    PO_sex.setValue(sex);
    String birthdayStr = "";
    if (birthday != null) {
        birthdayStr = sdf.format(birthday);
    }
    PO_birthday.setValue(birthdayStr);
    PO_position.setValue(position);
    PO_jobTitle.setValue(jobTitle);
    PO_department.setValue(department);
    PO_phone.setValue(phone);
    PO_fax.setValue(fax == null | fax.equals("null") ? "" : fax);
    PO_email.setValue(email == null | email.equals("null") ? "" : email);
    PO_signatureField.setValue(signatureField);
    PO_jobUndergo.setValue(jobUndergo == null | jobUndergo.equals("null") ? "" : jobUndergo);
    PO_education.setValue(education == null | education.equals("null") ? "" : education);
    PO_train.setValue(train == null | train.equals("null") ? "" : train);
    PO_relevantExplanation.setValue(relevantExplanation == null | relevantExplanation.equals("null") ? "" : relevantExplanation);


    poCtrl1.setWriter(doc);
    //添加pageoffice保存按钮
    poCtrl1.addCustomToolButton("保存", "Save()", 1);
    poCtrl1.setSaveFilePage("apiShiYsJyjcQzr_fileWebSave.action?id=" + id);

    // 设置文件保存之后执行的事件
    poCtrl1.setJsFunction_AfterDocumentSaved("AfterDocumentSaved()");
    poCtrl1.setMenubar(false);//隐藏菜单栏
    poCtrl1.setOfficeToolbars(false);//隐藏工具栏
    //打开Word文件
    poCtrl1.webOpen("../doc/sqqzrjbxx.doc", OpenModeType.docNormalEdit, "李杰");
    poCtrl1.setTagId("PageOfficeCtrl1"); //此行必须
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>授权签字人基本信息</title>
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

    //关闭有父页面的窗口
    function CloseWindow(action) {
        window.close();
    }
</script>
</body>
</html>
