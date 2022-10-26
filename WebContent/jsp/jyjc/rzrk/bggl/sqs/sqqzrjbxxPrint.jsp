<%@ page language="java" import="java.util.*,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po" %>
<%@ page import="com.yongjie.ZhiJianSbpt.container.ServiceProvider" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.StringUtil" %>
<%@ page import="com.yongjie.ZhiJianSbpt.bggl.service.SqqzrChangeDetailsService" %>
<%@ page import="com.yongjie.ZhiJianSbpt.bggl.model.SqqzrChangeDetails" %>
<%
    SqqzrChangeDetailsService sqqzrChangeDetailsService = (SqqzrChangeDetailsService) ServiceProvider.getService(SqqzrChangeDetailsService.SERVICE_NAME);
    PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
    poCtrl1.setServerPage(request.getContextPath() + "/poserver.zz");
    WordDocument doc = new WordDocument();

    String id = request.getParameter("id");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    if (StringUtil.isNullOrEmpty(id)) {
        response.getWriter().println("请求参数有误!");
        return;
    }
    SqqzrChangeDetails dbEntity = sqqzrChangeDetailsService.getById(Long.parseLong(id));
    String name = dbEntity.getQzrName() == null ? "" : dbEntity.getQzrName();
    String sex = "";
    if ("0".equals(dbEntity.getSex())) {
        sex = "男";
    } else if ("1".equals(dbEntity.getSex())) {
        sex = "女";
    } else {
        sex = "未知";
    }
    Date birthday = dbEntity.getBirthday();
    String position = dbEntity.getZhiw() == null ? "" : dbEntity.getZhiw();
    String jobTitle = dbEntity.getZhic() == null ? "" : dbEntity.getZhic();
    String department = dbEntity.getDept() == null ? "" : dbEntity.getDept();
    String phone = dbEntity.getTelephone() == null ? "" : dbEntity.getTelephone();
    String fax = dbEntity.getFax() == null ? "" : dbEntity.getFax();
    String email = dbEntity.getEmail() == null ? "" : dbEntity.getEmail();
    String signatureField = dbEntity.getQzly() == null ? "" : dbEntity.getQzly();
    String jobUndergo = dbEntity.getPeixun() == null ? "" : dbEntity.getPeixun();
    String education = dbEntity.getEducation() == null ? "" : dbEntity.getEducation();
    String train = dbEntity.getGzjl() == null ? "" : dbEntity.getGzjl();
    String relevantExplanation = dbEntity.getXgsm() == null ? "" : dbEntity.getXgsm();


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
    PO_fax.setValue(fax);
    PO_email.setValue(email);
    PO_signatureField.setValue(signatureField);
    PO_jobUndergo.setValue(jobUndergo);
    PO_education.setValue(education);
    PO_train.setValue(train);
    PO_relevantExplanation.setValue(relevantExplanation);


    poCtrl1.setWriter(doc);
    //添加pageoffice保存按钮
    poCtrl1.addCustomToolButton("保存", "Save()", 1);
    poCtrl1.setSaveFilePage("common_justSaveFile.action?tableName=SQQZRCHANGEDETAILS&id=" + id);

    // 设置文件保存之后执行的事件
    poCtrl1.setJsFunction_AfterDocumentOpened("AfterDocumentOpened()");
    poCtrl1.setJsFunction_AfterDocumentSaved("AfterDocumentSaved()");
    poCtrl1.setMenubar(false);//隐藏菜单栏
    poCtrl1.setOfficeToolbars(false);//隐藏工具栏
    //打开Word文件
    poCtrl1.webOpen("temp/sqqzrjbxx.doc", OpenModeType.docNormalEdit, "李杰");
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
        // CloseWindow();
    }

    //关闭有父页面的窗口
    function CloseWindow(action) {
        window.close();
    }

    function AfterDocumentOpened() {
        Save();
    }
</script>
</body>
</html>
