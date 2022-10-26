<%@ page language="java"
         import="java.util.*,
                 com.zhuozhengsoft.pageoffice.*,
                 com.zhuozhengsoft.pageoffice.wordwriter.*"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po" %>
<%@ page import="com.yongjie.ZhiJianSbpt.container.ServiceProvider" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.yongjie.ZhiJianSbpt.service.jyjcbggl.BizApiZwcnBgService" %>
<%@ page import="com.yongjie.ZhiJianSbpt.model.jyjcbggl.BizApiZwcnBg" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.R" %>
<%@ page import="com.yongjie.ZhiJianSbpt.bggl.model.ChangeApplyInfo" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.StringUtil" %>
<%@ page import="com.yongjie.ZhiJianSbpt.bggl.service.*" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.SysFinalRecource" %>
<%@ page import="java.io.File" %>
<%
    BizApiZwcnBgService service = (BizApiZwcnBgService) ServiceProvider.getService(BizApiZwcnBgService.SERVICE_NAME);

    ChangeApplyInfoService changeApplyInfoService = (ChangeApplyInfoService) ServiceProvider.getService(ChangeApplyInfoService.SERVICE_NAME);
    StandardChangeDetailsService standardChangeDetailsService = (StandardChangeDetailsService) ServiceProvider.getService(StandardChangeDetailsService.SERVICE_NAME);
    CancelNlDetailsService cancelNlDetailsService = (CancelNlDetailsService) ServiceProvider.getService(CancelNlDetailsService.SERVICE_NAME);
    PersonChangeDetailsService personChangeDetailsService = (PersonChangeDetailsService) ServiceProvider.getService(PersonChangeDetailsService.SERVICE_NAME);
    SqqzrChangeDetailsService sqqzrChangeDetailsService = (SqqzrChangeDetailsService) ServiceProvider.getService(SqqzrChangeDetailsService.SERVICE_NAME);
    PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
    poCtrl1.setServerPage(request.getContextPath() + "/poserver.zz");
    WordDocument doc = new WordDocument();

    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String serialNumber = request.getParameter("serialNumber");
    String changeType = request.getParameter("changeType");

    if (StringUtil.isNullOrEmpty(serialNumber) || StringUtil.isNullOrEmpty(changeType)) {
        response.sendRedirect("404.jsp");
        return;
    }

    String docPath = "";
    Map<String, Object> reqMap = new HashMap<String, Object>();
    reqMap.put("serialNumber", serialNumber);
    reqMap.put("changeType", changeType);

    BizApiZwcnBg entity = service.queryBySerialNumber(serialNumber);
    // set 公共信息
    if (entity != null) {
        String jgmc = StringUtil.isNullOrEmpty(entity.getJgmc()) ? "" : entity.getJgmc();
        DataRegion PO_jgmc = doc.openDataRegion("PO_jgName");
        PO_jgmc.setValue(jgmc);
    }

    // 申请表  信息
    Map<String, Object> map = changeApplyInfoService.queryBySerialNum(reqMap);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    ChangeApplyInfo applyInfoEntity = (ChangeApplyInfo) map.get(R.DATA_NAME);
    // 申请日期 年月日
    String applyDate = StringUtil.isNullOrEmpty(applyInfoEntity.getApplyDate()) ? null : sdf.format(applyInfoEntity.getApplyDate());

    // 联系人 ，电话
    String lxrName = StringUtil.isNullOrEmpty(applyInfoEntity.getLxrName()) ? "" : applyInfoEntity.getLxrName();
    String lxrTel = StringUtil.isNullOrEmpty(applyInfoEntity.getLxrTel()) ? "" : applyInfoEntity.getLxrTel();
    // 通信地址、邮编、传真
    String address = StringUtil.isNullOrEmpty(applyInfoEntity.getAddress()) ? "" : applyInfoEntity.getAddress();
    String email = StringUtil.isNullOrEmpty(applyInfoEntity.getEmail()) ? "" : applyInfoEntity.getEmail();
    String fax = StringUtil.isNullOrEmpty(applyInfoEntity.getFax()) ? "" : applyInfoEntity.getFax();
    // 本机构技术负责人审查意见 、本机构技术负责人审查意见：日期
    String fzrReviewOpinion = StringUtil.isNullOrEmpty(applyInfoEntity.getFzrReviewOpinion()) ? "" : applyInfoEntity.getFzrReviewOpinion();
    String fzrReviewOpinionDate = StringUtil.isNullOrEmpty(applyInfoEntity.getFzrReviewOpinionDate()) ? "年月日" : sdf.format(applyInfoEntity.getFzrReviewOpinionDate());

    // 专业技术评价组织/专家审查意见：、日期
    String zjReviewOpinion = StringUtil.isNullOrEmpty(applyInfoEntity.getZjReviewOpinion()) ? "" : applyInfoEntity.getZjReviewOpinion();
    String zjReviewOpinionDate = StringUtil.isNullOrEmpty(applyInfoEntity.getZjReviewOpinionDate()) ? "年月日" : sdf.format(applyInfoEntity.getZjReviewOpinionDate());

    // 原资质认定获证名称
    String nameOld = StringUtil.isNullOrEmpty(applyInfoEntity.getNameOld()) ? "" : applyInfoEntity.getNameOld();
    // 拟变更的名称
    String nameNew = StringUtil.isNullOrEmpty(applyInfoEntity.getNameNew()) ? "" : applyInfoEntity.getNameNew();
    // 证书编号
    String cmaPermitNum = StringUtil.isNullOrEmpty(applyInfoEntity.getCmaPermitNum()) ? "" : applyInfoEntity.getCmaPermitNum();
    // 证书 有效日期
    String certificateEfficientDate = StringUtil.isNullOrEmpty(applyInfoEntity.getCertificateEfficientDate()) ? null : sdf.format(applyInfoEntity.getCertificateEfficientDate());
    // 更名原因
    String nameChangeReason = StringUtil.isNullOrEmpty(applyInfoEntity.getNameChangeReason()) ? "" : applyInfoEntity.getNameChangeReason();
    // 检验检测机构所属上级部门意见、日期
    String superiorDeptOpinion = StringUtil.isNullOrEmpty(applyInfoEntity.getSuperiorDeptOpinion()) ? "" : applyInfoEntity.getSuperiorDeptOpinion();
    String superiorDeptOpinionDate = StringUtil.isNullOrEmpty(applyInfoEntity.getSuperiorDeptOpinionDate()) ? "年月日" : sdf.format(applyInfoEntity.getSuperiorDeptOpinionDate());

    // 原地址名称 、拟变更的地址名称
    String addressOld = StringUtil.isNullOrEmpty(applyInfoEntity.getAddressOld()) ? "" : applyInfoEntity.getAddressOld();
    String addressNew = StringUtil.isNullOrEmpty(applyInfoEntity.getAddressNew()) ? "" : applyInfoEntity.getAddressNew();
    // 地址名称变更原因
    String addressChangeReason = StringUtil.isNullOrEmpty(applyInfoEntity.getAddressChangeReason()) ? "" : applyInfoEntity.getAddressChangeReason();

    // 法人性质变更 适用于法人单位）
    String natureChange = StringUtil.isNullOrEmpty(applyInfoEntity.getNatureChange()) ? "" : applyInfoEntity.getNatureChange();
    // 原法人性质、变更后法人性质、备注（法人性质）
    String natureOld = StringUtil.isNullOrEmpty(applyInfoEntity.getNatureOld()) ? "" : applyInfoEntity.getNatureOld();
    String natureNew = StringUtil.isNullOrEmpty(applyInfoEntity.getNatureNew()) ? "" : applyInfoEntity.getNatureNew();
    String natureRemark = StringUtil.isNullOrEmpty(applyInfoEntity.getNatureRemark()) ? "" : applyInfoEntity.getNatureRemark();
    // 所在法人单位性质变更、原法人单位性质、变更后法人单位性质、备注（所在法人单位性质变更）
    String unitChange = StringUtil.isNullOrEmpty(applyInfoEntity.getUnitChange()) ? "" : applyInfoEntity.getUnitChange();
    String unitOld = StringUtil.isNullOrEmpty(applyInfoEntity.getUnitOld()) ? "" : applyInfoEntity.getUnitOld();
    String unitNew = StringUtil.isNullOrEmpty(applyInfoEntity.getUnitNew()) ? "" : applyInfoEntity.getUnitNew();
    String unitRemark = StringUtil.isNullOrEmpty(applyInfoEntity.getUnitRemark()) ? "" : applyInfoEntity.getUnitRemark();
    // 所在法人单位名称变更、原法人单位名称、变更后法人单位名称、备注（所在法人单位名称变更）
    String unitNameChange = StringUtil.isNullOrEmpty(applyInfoEntity.getUnitNameChange()) ? "" : applyInfoEntity.getUnitNameChange();
    String unitNameOld = StringUtil.isNullOrEmpty(applyInfoEntity.getUnitNameOld()) ? "" : applyInfoEntity.getUnitNameOld();
    String unitNameNew = StringUtil.isNullOrEmpty(applyInfoEntity.getUnitNameNew()) ? "" : applyInfoEntity.getUnitNameNew();
    String nuitNameRemark = StringUtil.isNullOrEmpty(applyInfoEntity.getNuitNameRemark()) ? "" : applyInfoEntity.getNuitNameRemark();

    DataRegion PO_fzrReviewOpinion = doc.openDataRegion("PO_fzrReviewOpinion");
    DataRegion PO_fzrReviewOpinionDate = doc.openDataRegion("PO_fzrReviewOpinionDate");
    DataRegion PO_zjReviewOpinion = doc.openDataRegion("PO_zjReviewOpinion");
    DataRegion PO_zjReviewOpinionDate = doc.openDataRegion("PO_zjReviewOpinionDate");
    DataRegion PO_cmaPermitNum = doc.openDataRegion("PO_cmaPermitNum");
    DataRegion PO_natureChange = doc.openDataRegion("PO_natureChange");
    DataRegion PO_natureOld = doc.openDataRegion("PO_natureOld");
    DataRegion PO_natureNew = doc.openDataRegion("PO_natureNew");
    DataRegion PO_natureRemark = doc.openDataRegion("PO_natureRemark");
    DataRegion PO_unitChange = doc.openDataRegion("PO_unitChange");
    DataRegion PO_unitOld = doc.openDataRegion("PO_unitOld");
    DataRegion PO_unitNew = doc.openDataRegion("PO_unitNew");
    DataRegion PO_unitRemark = doc.openDataRegion("PO_unitRemark");
    DataRegion PO_unitNameChange = doc.openDataRegion("PO_unitNameChange");
    DataRegion PO_unitNameOld = doc.openDataRegion("PO_unitNameOld");
    DataRegion PO_unitNameNew = doc.openDataRegion("PO_unitNameNew");
    DataRegion PO_nuitNameRemark = doc.openDataRegion("PO_nuitNameRemark");
    DataRegion PO_applyDateYear = doc.openDataRegion("PO_applyDateYear");
    DataRegion PO_applyDateMonth = doc.openDataRegion("PO_applyDateMonth");
    DataRegion PO_applyDateDay = doc.openDataRegion("PO_applyDateDay");
    DataRegion PO_lxrName = doc.openDataRegion("PO_lxrName");
    DataRegion PO_lxrTel = doc.openDataRegion("PO_lxrTel");
    DataRegion PO_address = doc.openDataRegion("PO_address");
    DataRegion PO_email = doc.openDataRegion("PO_email");
    DataRegion PO_fax = doc.openDataRegion("PO_fax");
    DataRegion PO_certificateEfficientDateYear = doc.openDataRegion("PO_certificateEfficientDateYear");
    DataRegion PO_certificateEfficientDateMonth = doc.openDataRegion("PO_certificateEfficientDateMonth");
    DataRegion PO_certificateEfficientDateDay = doc.openDataRegion("PO_certificateEfficientDateDay");
    DataRegion PO_addressOld = doc.openDataRegion("PO_addressOld");
    DataRegion PO_addressNew = doc.openDataRegion("PO_addressNew");
    DataRegion PO_addressChangeReason = doc.openDataRegion("PO_addressChangeReason");
    DataRegion PO_nameOld = doc.openDataRegion("PO_nameOld");
    DataRegion PO_nameNew = doc.openDataRegion("PO_nameNew");
    DataRegion PO_nameChangeReason = doc.openDataRegion("PO_nameChangeReason");
    DataRegion PO_superiorDeptOpinion = doc.openDataRegion("PO_superiorDeptOpinion");
    DataRegion PO_superiorDeptOpinionDate = doc.openDataRegion("PO_superiorDeptOpinionDate");
    DataRegion PO_YEAR = doc.openDataRegion("PO_YEAR");
    DataRegion PO_MONTH = doc.openDataRegion("PO_MONTH");
    DataRegion PO_DAY = doc.openDataRegion("PO_DAY");

    if (!fzrReviewOpinionDate.contains("年")) {
        String[] dates = fzrReviewOpinionDate.split("-");
        PO_fzrReviewOpinion.setValue(fzrReviewOpinion);
        PO_YEAR.setValue(dates[0]);
        PO_MONTH.setValue(dates[1]);
        PO_DAY.setValue(dates[2]);
    }
    PO_zjReviewOpinion.setValue(zjReviewOpinion);
    PO_zjReviewOpinionDate.setValue(zjReviewOpinionDate);
    PO_cmaPermitNum.setValue(cmaPermitNum);
    PO_natureChange.setValue(natureChange);
    PO_natureOld.setValue(natureOld);
    PO_natureNew.setValue(natureNew);
    PO_natureRemark.setValue(natureRemark);
    PO_unitChange.setValue(unitChange);
    PO_unitOld.setValue(unitOld);
    PO_unitNew.setValue(unitNew);
    PO_unitRemark.setValue(unitRemark);
    PO_unitNameChange.setValue(unitNameChange);
    PO_unitNameOld.setValue(unitNameOld);
    PO_unitNameNew.setValue(unitNameNew);
    PO_nuitNameRemark.setValue(nuitNameRemark);
    if (!StringUtil.isNullOrEmpty(applyDate)) {
        String[] dates = applyDate.split("-");
        PO_applyDateYear.setValue(dates[0]);
        PO_applyDateMonth.setValue(dates[1]);
        PO_applyDateDay.setValue(dates[2]);
    }
    PO_lxrName.setValue(lxrName);
    PO_lxrTel.setValue(lxrTel);
    PO_address.setValue(address);
    PO_email.setValue(email);
    PO_fax.setValue(fax);
    PO_nameOld.setValue(nameOld);
    PO_nameNew.setValue(nameNew);
    PO_nameChangeReason.setValue(nameChangeReason);
    if (!StringUtil.isNullOrEmpty(certificateEfficientDate)) {
        String[] datess = certificateEfficientDate.split("-");
        PO_certificateEfficientDateYear.setValue(datess[0]);
        PO_certificateEfficientDateMonth.setValue(datess[1]);
        PO_certificateEfficientDateDay.setValue(datess[2]);
    }
    PO_superiorDeptOpinion.setValue(superiorDeptOpinion);
    PO_superiorDeptOpinionDate.setValue(superiorDeptOpinionDate);
    PO_addressOld.setValue(addressOld);
    PO_addressNew.setValue(addressNew);
    PO_addressChangeReason.setValue(addressChangeReason);

    // 标准（方法）变更
    if ("11".equals(changeType)) {
        docPath = "temp/standardChange.docx";

        // 标准（方法）变更 list数据
        Map<String, Object> map11 = standardChangeDetailsService.queryListBySerialNum(reqMap);
        List<Map<String, Object>> listMap = (List<Map<String, Object>>) map11.get(R.DATA_NAME);
        if (listMap != null && listMap.size() > 0) {
            DataRegion regTable = doc.openDataRegion("PO_standartDetails");
            Table table = regTable.openTable(1);
            if (listMap != null) {
                //如果得到的数据多于1条则加行
                if (listMap.size() > 1) {
                    for (int i = 0; i < listMap.size() - 1; i++) {
                        table.insertRowAfter(table.openCellRC(3 + i, 3));
                    }
                }
                Iterator iteratorRy = listMap.iterator();
                int iCounter = 0;
                int xuhaoIndex = 0;
                int leibieIndex = 0;
                String xuhaoUp = "";
                String leibieUp = "";
                int rownum = 3;
                while (iteratorRy.hasNext()) {
                    rownum++;
                    HashMap detailsMap = (HashMap) iteratorRy.next();
                    String xuhao = detailsMap.get("SORTNUM") == null ? "" : detailsMap.get("SORTNUM").toString();
                    String leibie = detailsMap.get("CATEGORY") == null ? "" : detailsMap.get("CATEGORY").toString();
                    if (!StringUtil.isNullOrEmpty(xuhao) && xuhaoUp.equals(xuhao)) {
                        if (xuhaoIndex == 0) {
                            xuhaoIndex = rownum - 1;
                        }
                        table.openCellRC(rownum, 1).mergeTo(xuhaoIndex, 1);
                    } else {
                        xuhaoIndex = 0;
                    }
                    if (!StringUtil.isNullOrEmpty(leibie) && leibieUp.equals(leibie)) {
                        if (leibieIndex == 0) {
                            leibieIndex = rownum - 1;
                        }
                        table.openCellRC(rownum, 2).mergeTo(leibieIndex, 2);
                    } else {
                        leibieIndex = 0;
                    }
                    xuhaoUp = xuhao;
                    leibieUp = leibie;
                    table.openCellRC(4 + iCounter, 1).setValue(xuhao);
                    table.openCellRC(4 + iCounter, 2).setValue(leibie);
                    table.openCellRC(4 + iCounter, 3).setValue(detailsMap.get("STANDARDNAMEANDNUMOLD") == null ? "" : detailsMap.get("STANDARDNAMEANDNUMOLD").toString());
                    table.openCellRC(4 + iCounter, 4).setValue(detailsMap.get("STANDARDNAMEANDNUMNEW") == null ? "" : detailsMap.get("STANDARDNAMEANDNUMNEW").toString());
                    table.openCellRC(4 + iCounter, 5).setValue(detailsMap.get("CHANGECONTENT") == null ? "" : detailsMap.get("CHANGECONTENT").toString());
                    iCounter++;
                }
            }
        }
    }
    // 机构名称变更
    if ("12".equals(changeType)) {
        docPath = "temp/nameChange.docx";
    }
    // 机构地址名称变更
    if ("13".equals(changeType)) {
        docPath = "temp/addressChange.docx";
    }
    // 机构法人单位变更
    if ("14".equals(changeType)) {
        docPath = "temp/legalPersonChange.docx";
    }
    // 机构取消检测检测能力
    if ("15".equals(changeType)) {
        docPath = "temp/cancelNl.docx";
        // 机构取消检测检测能力 list数据
        Map<String, Object> map15 = cancelNlDetailsService.queryListBySerialNum(reqMap);
        List<Map<String, Object>> listMap = (List<Map<String, Object>>) map15.get(R.DATA_NAME);
        if (listMap != null && listMap.size() > 0) {
            DataRegion regTable = doc.openDataRegion("PO_cancelNlDetails");
            Table table = regTable.openTable(1);
            if (listMap != null) {
                //如果得到的数据多于1条则加行
                if (listMap.size() > 1) {
                    for (int i = 0; i < listMap.size() - 1; i++) {
                        table.insertRowAfter(table.openCellRC(5 + i, 3));
                    }
                }
                Iterator iteratorRy = listMap.iterator();
                int iCounter = 0;
                int xuhaoIndex = 0;
                int leibieIndex = 0;
                String xuhaoUp = "";
                String leibieUp = "";
                int rownum = 4;
                while (iteratorRy.hasNext()) {
                    rownum++;
                    HashMap detailsMap = (HashMap) iteratorRy.next();
                    String xuhao = detailsMap.get("PRODUCTSORTNUM") == null ? "" : detailsMap.get("PRODUCTSORTNUM").toString();
                    String leibie = detailsMap.get("CATEGORY") == null ? "" : detailsMap.get("CATEGORY").toString();
                    if (!StringUtil.isNullOrEmpty(xuhao) && xuhaoUp.equals(xuhao)) {
                        if (xuhaoIndex == 0) {
                            xuhaoIndex = rownum - 1;
                        }
                        table.openCellRC(rownum, 1).mergeTo(xuhaoIndex, 1);
                    } else {
                        xuhaoIndex = 0;
                    }
                    if (!StringUtil.isNullOrEmpty(leibie) && leibieUp.equals(leibie)) {
                        if (leibieIndex == 0) {
                            leibieIndex = rownum - 1;
                        }
                        table.openCellRC(rownum, 2).mergeTo(leibieIndex, 2);
                    } else {
                        leibieIndex = 0;
                    }
                    xuhaoUp = xuhao;
                    leibieUp = leibie;
                    table.openCellRC(5 + iCounter, 1).setValue(xuhao);
                    table.openCellRC(5 + iCounter, 2).setValue(leibie);
                    table.openCellRC(5 + iCounter, 3).setValue(detailsMap.get("SORTNUMBG") == null ? "" : detailsMap.get("SORTNUMBG").toString());
                    table.openCellRC(5 + iCounter, 4).setValue(detailsMap.get("PRODUCTNAME") == null ? "" : detailsMap.get("PRODUCTNAME").toString());
                    table.openCellRC(5 + iCounter, 5).setValue(detailsMap.get("STANDARDNAMEANDNUM") == null ? "" : detailsMap.get("STANDARDNAMEANDNUM").toString());
                    table.openCellRC(5 + iCounter, 6).setValue(detailsMap.get("LABORATORYPLACE") == null ? "" : detailsMap.get("LABORATORYPLACE").toString());
                    iCounter++;
                }
            }
        }
    }
    // 检验检测机构人员变更
    if ("16".equals(changeType)) {
        docPath = "temp/personChange.docx";

        // 机构取消检测检测能力 list数据
        Map<String, Object> map17 = personChangeDetailsService.queryListBySerialNum(reqMap);
        List<Map<String, Object>> listMap = (List<Map<String, Object>>) map17.get(R.DATA_NAME);
        if (listMap != null && listMap.size() > 0) {
            DataRegion regTable = doc.openDataRegion("PO_personChangeDetails");
            Table table = regTable.openTable(1);
            if (listMap != null) {
                //如果得到的数据多于1条则加行
                if (listMap.size() > 1) {
                    for (int i = 0; i < listMap.size() - 1; i++) {
                        table.insertRowAfter(table.openCellRC(3 + i, 1));
                    }
                }
                Iterator iteratorRy = listMap.iterator();
                int iCounter = 0;
                while (iteratorRy.hasNext()) {
                    HashMap detailsMap = (HashMap) iteratorRy.next();
                    table.openCellRC(3 + iCounter, 1).setValue(detailsMap.get("ZHIW") == null ? "" : detailsMap.get("ZHIW").toString());
                    table.openCellRC(3 + iCounter, 2).setValue(detailsMap.get("NAME") == null ? "" : detailsMap.get("NAME").toString());
                    table.openCellRC(3 + iCounter, 3).setValue(detailsMap.get("NAMEAFTER") == null ? "" : detailsMap.get("NAMEAFTER").toString());
                    table.openCellRC(3 + iCounter, 4).setValue(detailsMap.get("CHANGETYPE") == null ? "" : detailsMap.get("CHANGETYPE").toString());
                    iCounter++;
                }
            }
        }
    }
    // 授权签字人变更
    if ("17".equals(changeType)) {
        docPath = "temp/sqqzrChange.docx";
        String PO_qzrTable = "PO_qzrTable";

        // 机构取消检测检测能力 list数据
        Map<String, Object> map18 = sqqzrChangeDetailsService.queryListBySerialNum(reqMap);
        List<Map<String, Object>> listMap = (List<Map<String, Object>>) map18.get(R.DATA_NAME);
        if (listMap != null && listMap.size() > 0) {
            DataRegion regTable = doc.openDataRegion("PO_sqqzrChangeDetails");
            Table table = regTable.openTable(1);
            if (listMap != null) {
                //如果得到的数据多于1条则加行
                if (listMap.size() > 1) {
                    for (int i = 0; i < listMap.size() - 1; i++) {
                        table.insertRowAfter(table.openCellRC(3 + i, 1));
                    }
                }
                Iterator iteratorRy = listMap.iterator();
                int iCounter = 0;
                while (iteratorRy.hasNext()) {
                    HashMap detailsMap = (HashMap) iteratorRy.next();
                    table.openCellRC(3 + iCounter, 1).setValue(detailsMap.get("QZRNAME") == null ? "" : detailsMap.get("QZRNAME").toString());
                    table.openCellRC(3 + iCounter, 2).setValue(detailsMap.get("QZLY") == null ? "" : detailsMap.get("QZLY").toString());
                    table.openCellRC(3 + iCounter, 3).setValue(detailsMap.get("QZRAREANEW") == null ? "" : detailsMap.get("QZRAREANEW").toString());
                    table.openCellRC(3 + iCounter, 4).setValue(detailsMap.get("CHANGETYPE") == null ? "" : detailsMap.get("CHANGETYPE").toString());

                    String filePath = detailsMap.get("FILEPATH") == null ? "" : detailsMap.get("FILEPATH").toString();
                    if (!StringUtil.isNullOrEmpty(filePath)) {
                        filePath = SysFinalRecource.SECOND_LEVEL_DIRECTORY + filePath;
                        filePath = filePath.replace("/", "\\\\");
                        File file = new File(filePath);
                        if (!file.exists()) {
                            continue;
                        }
                        DataRegion data1 = doc.createDataRegion(PO_qzrTable + iCounter, DataRegionInsertType.After, PO_qzrTable);
                        data1.setValue("[word]" + filePath + "[/word]");
                        data1.selectStart();
                        doc.insertPageBreak();
                        iCounter++;
                    }
                }
            }
        }

    }

    poCtrl1.setWriter(doc);
    //添加pageoffice保存按钮
    poCtrl1.addCustomToolButton("另存到本机", "SaveAs()", 1);
    poCtrl1.addCustomToolButton("打印", "ShowPrintDlg()", 6);
    poCtrl1.addCustomToolButton("打印预览", "PrintPreView()", 7);

    // 设置文件保存之后执行的事件
    poCtrl1.setMenubar(false);//隐藏菜单栏
    poCtrl1.setOfficeToolbars(false);//隐藏工具栏
    //打开Word文件
    poCtrl1.webOpen(docPath, OpenModeType.docNormalEdit, "李杰");
    poCtrl1.setTagId("PageOfficeCtrl1"); //此行必须
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>检验检测变更申请审批表</title>
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

    function SaveAs() {
        document.getElementById("PageOfficeCtrl1").ShowDialog(2);
    }

    //打印
    function ShowPrintDlg() {
        document.getElementById("PageOfficeCtrl1").ShowDialog(4);
    }

    //打印预览
    function PrintPreView() {
        document.getElementById("PageOfficeCtrl1").PrintPreview();
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
