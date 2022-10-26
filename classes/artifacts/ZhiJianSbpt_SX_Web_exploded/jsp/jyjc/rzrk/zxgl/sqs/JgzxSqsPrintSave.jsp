<%@ page language="java" import="java.util.*,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po" %>
<%@ page import="com.yongjie.ZhiJianSbpt.container.ServiceProvider" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.yongjie.ZhiJianSbpt.service.jyjcJgzx.BizApiZxsqbService" %>
<%@ page import="com.yongjie.ZhiJianSbpt.model.jyjcJgzx.BizApiZxsqb" %>
<%
    BizApiZxsqbService zxService = (BizApiZxsqbService) ServiceProvider.getService(BizApiZxsqbService.SERVICE_NAME);
    PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
    poCtrl1.setServerPage(request.getContextPath() + "/poserver.zz");
    WordDocument doc = new WordDocument();

    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String serialNumber = request.getParameter("serialNumber");
    String flag = request.getParameter("flag");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    BizApiZxsqb bizEntity = zxService.queryBySerialNumber(serialNumber);
    // set 封面信息
    if (bizEntity != null) {
        //机构名称
        String jgmc = bizEntity.getOrganizationName() == null ? "" : bizEntity.getOrganizationName();
        DataRegion PO_JGMC = doc.openDataRegion("PO_JGMC");
        PO_JGMC.setValue(jgmc);

        //机构地址
        String address = bizEntity.getOrganizationAddress() == null ? "" : bizEntity.getOrganizationAddress();
        DataRegion PO_JGDZ = doc.openDataRegion("PO_JGDZ");
        PO_JGDZ.setValue(address);
        //证书编号
        String zsbh = bizEntity.getCertificateNumber() == null ? "" : bizEntity.getCertificateNumber();
        DataRegion PO_ZSBH = doc.openDataRegion("PO_ZSBH");
        PO_ZSBH.setValue(zsbh);
        //有效日期开始
        Date sdate = bizEntity.getStartDate();
        String sdateStr = "";
        if (sdate != null) {
            sdateStr = sdf.format(sdate);
        }
        DataRegion PO_ZZS = doc.openDataRegion("PO_ZZS");
        PO_ZZS.setValue(sdateStr);
        //有效日期结束
        Date edate = bizEntity.getEndDate();
        String edateStr = "";
        if (edate != null) {
            edateStr = sdf.format(sdate);
        }
        DataRegion PO_ZZE = doc.openDataRegion("PO_ZZE");
        PO_ZZE.setValue(edateStr);
        //社会信用统一代码
        String shxydm = bizEntity.getShxydm() == null ? "" : bizEntity.getShxydm();
        //联系人
        String lxr = bizEntity.getContacts() == null ? "" : bizEntity.getContacts();
        DataRegion PO_LXR = doc.openDataRegion("PO_LXR");
        PO_LXR.setValue(lxr);
        //联系电话
        String lxdh = bizEntity.getContactsPhone() == null ? "" : bizEntity.getContactsPhone();
        DataRegion PO_LXRDH = doc.openDataRegion("PO_LXRDH");
        PO_LXRDH.setValue(lxdh);
        //负责人
        String fzr = bizEntity.getUnitPeople() == null ? "" : bizEntity.getUnitPeople();
        DataRegion PO_DWFZR = doc.openDataRegion("PO_DWFZR");
        PO_DWFZR.setValue(fzr);
        //负责人电话
        String fzrdh = bizEntity.getUnitPhone() == null ? "" : bizEntity.getUnitPhone();
        DataRegion PO_DWFZRDH = doc.openDataRegion("PO_DWFZRDH");
        PO_DWFZRDH.setValue(fzrdh);
        //申请注销时间
        Date sqzxDate = bizEntity.getOrganizationDate();
        String sqzxDateStr = "";
        if (sqzxDate != null) {
            sqzxDateStr = sdf.format(sdate);
        }
        DataRegion PO_SQZXDATE = doc.openDataRegion("PO_SQZXDATE");
        PO_SQZXDATE.setValue(sqzxDateStr);
        //机构名称后的日期
        DataRegion PO_JGDATE = doc.openDataRegion("PO_JGDATE");
        PO_JGDATE.setValue(sqzxDateStr);
        //申请注销原因
        String sqzxReason = bizEntity.getCancelReason() == null ? "" : bizEntity.getCancelReason();
        DataRegion PO_SQZXYX = doc.openDataRegion("PO_SQZXYX");
        PO_SQZXYX.setValue(sqzxReason);
    }

    poCtrl1.setWriter(doc);
    //添加pageoffice保存按钮
    poCtrl1.addCustomToolButton("保存", "Save()", 1);
    poCtrl1.setSaveFilePage("shiYsJyjcFuJian_fileWebSave.action?serialNumber=" + serialNumber + "&flag=" + flag);
    poCtrl1.addCustomToolButton("另存到本机", "SaveAs()", 1);
    poCtrl1.addCustomToolButton("页面设置", "ShowPageSetupDlg()", 5);
    poCtrl1.addCustomToolButton("打印", "ShowPrintDlg()", 6);
    poCtrl1.addCustomToolButton("打印预览", "PrintPreView()", 7);

    // 设置文件保存之后执行的事件
    poCtrl1.setJsFunction_AfterDocumentSaved("AfterDocumentSaved()");
    poCtrl1.setMenubar(false);//隐藏菜单栏
    poCtrl1.setOfficeToolbars(false);//隐藏工具栏
    //打开Word文件
    poCtrl1.webOpen("../doc/JgzxSqb.doc", OpenModeType.docNormalEdit, "李杰");
    poCtrl1.setTagId("PageOfficeCtrl1"); //此行必须
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>检验检测机构注销申请书</title>
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
    /*获取页面打开的方式0:popup弹出1:直接跳转2:新页面打开*/
    var openFlag =<%=request.getParameter("openFlag")%>;

    function InsertPageBreak() {
        document.getElementById("PageOfficeCtrl1").RunMacro("AddPage", "sub AddPage() \r\n Application.Selection.InsertBreak(7) \r\n End Sub");
    }

    function Save() {
        document.getElementById("PageOfficeCtrl1").WebSave();
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


    //保存之前做的操作
    function BeforeDocumentSaved() {
        document.getElementById("PageOfficeCtrl1").Alert("文件就要开始保存！");
        return true;
    }

    //文档保存之后执行此方法
    function AfterDocumentSaved(IsSaved) {
        if (IsSaved) {
            document.getElementById("PageOfficeCtrl1").Alert("保存成功！");
            if (openFlag == 2) {
                CloseWindow("saveToClose");
            } else if (openFlag == 1) {
                CloseWindow("redirect");
            } else {
                CloseWindow();
            }
        }
    }

    //关闭有父页面的窗口
    function CloseWindow(action) {
        if (action == "saveToClose") {
            window.close();
        }
        if (action == "redirect") {
            window.close();
        } else if (window.CloseOwnerWindow) {
            return window.CloseOwnerWindow(action);
        } else {
            window.close();
        }
    }

    //返回
    function runBack() {
        window.location.href = "<%=basePath%>jsp/shiYsJyjcFlow/ShiYsJyjcZhunYujds.jsp";
    }
</script>
</body>
</html>
