<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.zhuozhengsoft.pageoffice.OpenModeType" %>
<%@page import="com.zhuozhengsoft.pageoffice.PageOfficeCtrl" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.zhuozhengsoft.pageoffice.wordwriter.*" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.StringUtil" %>
<%@ page import="com.yongjie.ZhiJianSbpt.model.ShiYsJyjcXchcPsbgNlHz" %>
<%@ page import="com.yongjie.ZhiJianSbpt.service.ApiShiYsJyjcNlService" %>
<%@ page import="com.yongjie.ZhiJianSbpt.container.ServiceProvider" %>
<%@ page import="com.yongjie.ZhiJianSbpt.service.ShiYsJyjcXchcPsbgNlHzService" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.R" %>
<%@ page import="java.util.*" %>
<%@ page import="com.yongjie.ZhiJianSbpt.service.ApiShiYsJyjcYqsbSbptService" %>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po" %>
<%
    ApiShiYsJyjcYqsbSbptService apiShiYsJyjcYqsbSbptService = (ApiShiYsJyjcYqsbSbptService) ServiceProvider.getService(ApiShiYsJyjcYqsbSbptService.SERVICE_NAME);
    ShiYsJyjcXchcPsbgNlHzService psbgNlHzService = (ShiYsJyjcXchcPsbgNlHzService) ServiceProvider.getService(ShiYsJyjcXchcPsbgNlHzService.SERVICE_NAME);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
    poCtrl1.setServerPage(request.getContextPath() + "/poserver.zz");
    WordDocument doc = new WordDocument();
    String psbgId = request.getParameter("psbgId");
    String openUrl = "";
    String saveFilePageParam = "";
    if (!StringUtil.isNullOrEmpty(psbgId)) {
        // 生成证书能力附表-写入 word数据
        ShiYsJyjcXchcPsbgNlHz psbgEntity = psbgNlHzService.queryById(Long.valueOf(psbgId));
        saveFilePageParam = "?action=yqsbsaveword&flowId=" + psbgEntity.getFlowId() + "&serialNumber=" + psbgEntity.getSerialNumber() + "&psbgId=" + psbgId;
        openUrl = basePath + "jsp/jyjc/rzrk/zzrd/doc/sbdyqsb.docx";

        HashMap<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("serialNumber", psbgEntity.getSerialNumber());
        reqMap.put("flag", psbgEntity.getNlOrSb());
        reqMap.put("isSp", psbgEntity.getIsSp());
        reqMap.put("cddz", psbgEntity.getCddz());
        reqMap.put("status", "0");
        Map<String, Object> nlDetailsMap = apiShiYsJyjcYqsbSbptService.getDetailList(reqMap);
        List nlList = (List) nlDetailsMap.get(R.DATA_NAME);
        DataRegion regTable = doc.openDataRegion("PO_jcnlTable");
        DataRegion PO_jgAddress = doc.openDataRegion("PO_jgAddress");
        PO_jgAddress.setValue(psbgEntity.getCddz());
        Table table = regTable.openTable(1);
        if (nlList != null && nlList.size() > 0) {
            int rowNum = 4;//当前四行
            int index = 1;
            int ejflIndex = 1;
            int yjflIndex = 1;
            int sjrowStart = 0;
            int cpmcStart = 1;
            String leibup = "";
            String ejflup = "";
            String sanjflup = "";
            String cpmcup = "";

            for (int i = 0; i < nlList.size() - 1; i++) {
                Map<String, Object> nlMap = (Map<String, Object>) nlList.get(i);
                rowNum++;//从第五行开始

                String ejfl = StringUtil.isNullOrEmpty(nlMap.get("EJFL")) ? "" : (String) nlMap.get("EJFL");
                String cpmc = StringUtil.isNullOrEmpty(nlMap.get("PRODUCTNAME")) ? "" : (String) nlMap.get("PRODUCTNAME");
                String cpxh = StringUtil.isNullOrEmpty(nlMap.get("SORTINGNUMBER")) ? "" : (String) nlMap.get("SORTINGNUMBER");
                String yjbzmc = StringUtil.isNullOrEmpty(nlMap.get("YJBZ")) ? "" : (String) nlMap.get("YJBZ");
                String bzwzName = StringUtil.isNullOrEmpty(nlMap.get("BZWZNAME")) ? "" : (String) nlMap.get("BZWZNAME");
                String identifier = StringUtil.isNullOrEmpty(nlMap.get("IDENTIFIER")) ? "" : (String) nlMap.get("IDENTIFIER");
                String bzwzClfw = StringUtil.isNullOrEmpty(nlMap.get("BZWZCLFW")) ? "" : (String) nlMap.get("BZWZCLFW");
                String syfs = StringUtil.isNullOrEmpty(nlMap.get("SYFS")) ? "" : (String) nlMap.get("SYFS");
                String yqsbYxrq = StringUtil.isNullOrEmpty(nlMap.get("YQSBYXRQ")) ? "" : sdf.format(nlMap.get("YQSBYXRQ"));
                String qrjg = StringUtil.isNullOrEmpty(nlMap.get("QRJG")) ? "" : (String) nlMap.get("QRJG");

                table.insertRowAfter(table.openCellRC(rowNum, 0));
                table.openCellRC(rowNum, 1).setValue(String.valueOf(index));
                System.out.println(index);
                // 二级分类
                if (!StringUtil.isNullOrEmpty(ejfl) && ejfl.equals(ejflup)) {
                    if (ejflIndex == 0) {
                        ejflIndex = rowNum - 1;
                    }
                    //合并table中的单元格(合并rowNum行,第2列，sjrowStart，第2列开始)
                    table.openCellRC(rowNum, 2).mergeTo(ejflIndex, 2);
                    // 第1列 序号 合并
                    table.openCellRC(rowNum, 1).mergeTo(ejflIndex, 1);
                } else {
                    ejflIndex = 0;
                    index++;
                }

                // 二级分类
                if (!StringUtil.isNullOrEmpty(cpxh) && cpmc.equals(cpmcup)) {
                    if (cpmcStart == 0) {
                        cpmcStart = rowNum - 1;
                    }
                    //合并table中的单元格(合并rowNum行,第2列，sjrowStart，第2列开始)
                    table.openCellRC(rowNum, 3).mergeTo(cpmcStart, 3);
                    // 第1列 序号 合并
                    table.openCellRC(rowNum, 4).mergeTo(cpmcStart, 4);
                } else {
                    cpmcStart = 0;
                    index++;
                }

                table.openCellRC(rowNum, 2).setValue(ejfl);
                table.openCellRC(rowNum, 3).setValue(cpxh);
                table.openCellRC(rowNum, 4).setValue(cpmc);
                table.openCellRC(rowNum, 5).setValue(yjbzmc);
                table.openCellRC(rowNum, 6).setValue(bzwzName);
                table.openCellRC(rowNum, 7).setValue(identifier);
                table.openCellRC(rowNum, 8).setValue(bzwzClfw);
                table.openCellRC(rowNum, 9).setValue(syfs);
                table.openCellRC(rowNum, 10).setValue(yqsbYxrq);
                table.openCellRC(rowNum, 11).setValue(qrjg);
                ejflup = ejfl;
                cpmcup = cpmc;
            }
            table.removeRowAt(table.openCellRC(rowNum + 1, 0));
            table.removeRowAt(table.openCellRC(rowNum + 2, 0));
        }
    }

    poCtrl1.setWriter(doc);
    poCtrl1.webOpen(openUrl, OpenModeType.docNormalEdit, "王景仟");
    poCtrl1.addCustomToolButton("保存", "save()", 1);
    poCtrl1.setSaveFilePage("common_fileSave.action" + saveFilePageParam);
    // 设置文件保存之后执行的事件
    poCtrl1.setJsFunction_AfterDocumentSaved("AfterDocumentSaved()");
    poCtrl1.setJsFunction_AfterDocumentOpened("AfterDocumentOpened()");
    poCtrl1.setMenubar(true);//隐藏菜单栏
    poCtrl1.setOfficeToolbars(true);//隐藏工具栏
    poCtrl1.setTagId("PageOfficeCtrl1"); //此行必须 */
%>
<!DOCTYPE HTML>
<head>
    <base href="<%=basePath%>">
    <title>检验检测仪器设备word</title>
    <script type="text/javascript" src="<%=basePath%>nui/nui.js"></script>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<body>
</body>
<form id="form1">
    <div style="width: auto; height: 950px;">
        <po:PageOfficeCtrl id="PageOfficeCtrl1"/>
    </div>
</form>
<script type="text/javascript">
    nui.parse();

    //保存
    function save() {
        document.getElementById("PageOfficeCtrl1").WebSave();
    }

    function AfterDocumentSaved(IsSaved) {
        document.getElementById("PageOfficeCtrl1").Alert("保存成功");
        // CloseWindow();
    }


    function CloseWindow(action) {
        if (window.CloseOwnerWindow) {
            return window.CloseOwnerWindow(action);
        } else {
            window.close();
        }
    }

    function AfterDocumentOpened() {
        save();
    }
</script>