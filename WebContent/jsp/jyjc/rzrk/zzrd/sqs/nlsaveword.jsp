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
<%@ page import="com.yongjie.ZhiJianSbpt.service.ApiShiYsJyjcNlSbptService" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.StringUtils" %>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po" %>
<%
    ApiShiYsJyjcNlSbptService apiShiYsJyjcNlSbptService = (ApiShiYsJyjcNlSbptService) ServiceProvider.getService(ApiShiYsJyjcNlSbptService.SERVICE_NAME);
    ShiYsJyjcXchcPsbgNlHzService psbgNlHzService = (ShiYsJyjcXchcPsbgNlHzService) ServiceProvider.getService(ShiYsJyjcXchcPsbgNlHzService.SERVICE_NAME);

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
        saveFilePageParam = "?action=nlsaveword&flowId=" + psbgEntity.getFlowId() + "&serialNumber=" + psbgEntity.getSerialNumber() + "&psbgId=" + psbgId;
        openUrl = basePath + "jsp/jyjc/rzrk/zzrd/doc/sbdjyjcnl.docx";

        HashMap<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("serialNumber", psbgEntity.getSerialNumber());
        reqMap.put("flag", psbgEntity.getNlOrSb());
        reqMap.put("typeName", psbgEntity.getIsSp());
        reqMap.put("siteName", psbgEntity.getCddz());
        Map<String, Object> nlDetailsMap = apiShiYsJyjcNlSbptService.queryList(reqMap);
        List nlList = (List) nlDetailsMap.get(R.DATA_NAME);
        DataRegion regTable = doc.openDataRegion("PO_jcnlTable");
        DataRegion PO_jgAddress = doc.openDataRegion("PO_jgAddress");
        PO_jgAddress.setValue(psbgEntity.getCddz());
        Table table = regTable.openTable(1);
        if (nlList != null && nlList.size() > 0) {
            int rowNum = 4;
            int index = 1;
            int yjflIndex = 1;
            int ejflIndex = 0;
            int cpxhIndex = 0;
            int sanjiStart = 0;
            String leibup = "";
            String ejflup = "";
            String sanjflup = "";
            String cpmcup = "";
            String cpxhup = "";

            for (int i = 0; i < nlList.size() - 1; i++) {
                Map<String, Object> nlMap = (Map<String, Object>) nlList.get(i);
                rowNum++;

                String leib = StringUtil.isNullOrEmpty(nlMap.get("YJFL")) ? "" : (String) nlMap.get("YJFL");
                String ejfl = StringUtil.isNullOrEmpty(nlMap.get("EJFL")) ? "" : (String) nlMap.get("EJFL");
                String sanjfl = StringUtil.isNullOrEmpty(nlMap.get("SANJFL")) ? "" : (String) nlMap.get("SANJFL");
                String cpmc = StringUtil.isNullOrEmpty(nlMap.get("SIJFL")) ? "" : (String) nlMap.get("SIJFL");
                String cpxh = StringUtil.isNullOrEmpty(nlMap.get("SORTINGNUMBER")) ? "" : (String) nlMap.get("SORTINGNUMBER");
                String yjbzmc = StringUtil.isNullOrEmpty(nlMap.get("YJBZ")) ? "" : (String) nlMap.get("YJBZ");
                String xzfw = StringUtil.isNullOrEmpty(nlMap.get("LIMITS")) ? "" : (String) nlMap.get("LIMITS");
                String sm = StringUtil.isNullOrEmpty(nlMap.get("INSTRUCTIONS")) ? "" : (String) nlMap.get("INSTRUCTIONS");
                table.insertRowAfter(table.openCellRC(rowNum, 0));

                // 设置一级分类
                if (StringUtil.isNullOrEmpty(leibup) || !leibup.equals(leib)) {
                    //合并table中的单元格(合并rowNum+1行,7列，从rowNum+1行，第2列开始)
                    table.openCellRC(rowNum, 7).mergeTo(rowNum, 2);
                    table.openCellRC(rowNum, 1).setValue(StringUtils.getNum(yjflIndex));
                    table.openCellRC(rowNum, 2).setValue(leib);
                    table.insertRowAfter(table.openCellRC(rowNum + 1, 0));
                    rowNum = rowNum + 1;
                    index = 1;
                    yjflIndex++;
                }

                table.openCellRC(rowNum, 1).setValue(String.valueOf(index));

                // 二级分类
                if (!StringUtil.isNullOrEmpty(ejfl) && ejfl.equals(ejflup) && leib.equals(leibup)) {
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
                // 产品序号
                if (!StringUtil.isNullOrEmpty(cpxh) && cpxhup.equals(cpxh)) {
                    if (cpxhIndex == 0) {
                        cpxhIndex = rowNum - 1;
                    }
                    //合并table中的单元格(合并rowNum行,第2列，sjrowStart，第2列开始)
                    table.openCellRC(rowNum, 3).mergeTo(cpxhIndex, 3);
                } else {
                    cpxhIndex = 0;
                    index++;
                }
                // 产品名称
                if (!StringUtil.isNullOrEmpty(sanjfl) && sanjflup.equals(sanjfl)) {
                    if (sanjiStart == 0) {
                        sanjiStart = rowNum - 1;
                    }
                    //合并table中的单元格(合并rowNum行,第2列，sjrowStart，第2列开始)
                    table.openCellRC(rowNum, 4).mergeTo(sanjiStart, 4);
                } else {
                    sanjiStart = 0;
                    index++;
                }

                table.openCellRC(rowNum, 2).setValue(ejfl);
                table.openCellRC(rowNum, 3).setValue(cpxh);
                table.openCellRC(rowNum, 4).setValue(sanjfl);
                table.openCellRC(rowNum, 5).setValue(yjbzmc);
                table.openCellRC(rowNum, 6).setValue(xzfw);
                table.openCellRC(rowNum, 7).setValue(sm);

                leibup = leib;
                ejflup = ejfl;
                sanjflup = sanjfl;
                cpxhup = cpxh;
            }
            table.removeRowAt(table.openCellRC(rowNum + 1, 0));
            table.removeRowAt(table.openCellRC(rowNum + 2, 0));
        }
    }

    //doc.insertPageBreak();
    poCtrl1.setWriter(doc);
    poCtrl1.webOpen(openUrl, OpenModeType.docNormalEdit, "");
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
    <title>检验检测能力申请书生成word</title>
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


    //关闭子窗口，刷新父窗体
    function CloseWindow(action) {
        if (window.CloseOwnerWindow) {
            return window.CloseOwnerWindow(action);
        } else {
            window.close();
        }
    }

    function onCancel(e) {
        CloseWindow("cancel");
    }

    function AfterDocumentOpened() {
        save();
    }
</script>