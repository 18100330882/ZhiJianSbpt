<%@page import="org.apache.commons.io.FileUtils" %>
<%@page import="java.io.File" %>
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
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.StringUtil" %>
<%@ page import="com.yongjie.ZhiJianSbpt.service.shiYsJyjc.ApiFileService" %>
<%@ page import="com.yongjie.ZhiJianSbpt.model.flowBase.ApiFile" %>
<%@ page import="javax.swing.text.html.parser.Entity" %>
<%
    BizApiShiysjyjcService bizService = (BizApiShiysjyjcService) ServiceProvider.getService(BizApiShiysjyjcService.SERVICE_NAME);
    ApiShiYsJyjcSqsService sqsService = (ApiShiYsJyjcSqsService) ServiceProvider.getService(ApiShiYsJyjcSqsService.SERVICE_NAME);
    ApiShiYsJyjcSqlxService sqlxService = (ApiShiYsJyjcSqlxService) ServiceProvider.getService(ApiShiYsJyjcSqlxService.SERVICE_NAME);
    ApiShiYsJyjcJgzyService jgzyService = (ApiShiYsJyjcJgzyService) ServiceProvider.getService(ApiShiYsJyjcJgzyService.SERVICE_NAME);
    ApiShiYsJyjcCdService cdService = (ApiShiYsJyjcCdService) ServiceProvider.getService(ApiShiYsJyjcCdService.SERVICE_NAME);
    ApiShiYsJyjcQzrService qzrService = (ApiShiYsJyjcQzrService) ServiceProvider.getService(ApiShiYsJyjcQzrService.SERVICE_NAME);
    ApiShiYsJyjcRyService ryService = (ApiShiYsJyjcRyService) ServiceProvider.getService(ApiShiYsJyjcRyService.SERVICE_NAME);
    ApiFileService fileService = (ApiFileService) ServiceProvider.getService(ApiFileService.SERVICE_NAME);
    ShiYsJyjcXchcPsbgNlHzService nlService = (ShiYsJyjcXchcPsbgNlHzService) ServiceProvider.getService(ShiYsJyjcXchcPsbgNlHzService.SERVICE_NAME);
    PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
    poCtrl1.setServerPage(request.getContextPath() + "/poserver.zz");
    WordDocument doc = new WordDocument();

    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String serialNumber = request.getParameter("serialNumber");
    String flag = request.getParameter("flag");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    BizApiShiYsJyjc bizEntity = bizService.queryBySerialNumber(serialNumber);
    // set ????????????
    if (bizEntity != null) {
        String jgmc = bizEntity.getJgmc() + "";
        String zgbmName = bizEntity.getZgbmName() + "";
        String zylylbName = bizEntity.getZylylbName() + "";
        Date sqrq = bizEntity.getSqrq();
        Date xwpsrq = bizEntity.getXwpsrq();
        String sqrqStr = "";
        if (sqrq != null) {
            sqrqStr = sdf.format(sqrq);
        }
        DataRegion PO_jgmc = doc.openDataRegion("PO_jgmc");
        DataRegion PO_zgbmName = doc.openDataRegion("PO_zgbmName");
        DataRegion PO_sqrq = doc.openDataRegion("PO_sqrq");
        DataRegion PO_syszylb = doc.openDataRegion("PO_syszylb");
        DataRegion PO_start_year = doc.openDataRegion("PO_start_year");
        DataRegion PO_start_month = doc.openDataRegion("PO_start_month");
        DataRegion PO_start_day = doc.openDataRegion("PO_start_day");
        PO_jgmc.setValue(jgmc);
        PO_zgbmName.setValue(zgbmName);
        PO_sqrq.setValue(sqrqStr);
        PO_syszylb.setValue(zylylbName);
        if (xwpsrq != null) {
            PO_start_year.setValue(xwpsrq.getYear() + SysFinalRecource.ORGINAL_DATE + "");
            PO_start_month.setValue(xwpsrq.getMonth() + 1 + "");
            PO_start_day.setValue(xwpsrq.getDate() + "");
        }
    }
    // set ??????
    ApiShiYsJyjcSqs sqsEntity = sqsService.queryBySerialNumber(serialNumber);
    if (sqsEntity != null) {
        String gkJgmc = StringUtil.isNullOrEmpty(sqsEntity.getJgmc()) ? "" : sqsEntity.getJgmc();
        String address = StringUtil.isNullOrEmpty(sqsEntity.getAdress()) ? "" : sqsEntity.getAdress();
        String postCode = StringUtil.isNullOrEmpty(sqsEntity.getPostcode()) ? "" : sqsEntity.getPostcode();
        String fax = StringUtil.isNullOrEmpty(sqsEntity.getFax()) ? "" : sqsEntity.getFax();
        String email = StringUtil.isNullOrEmpty(sqsEntity.getEmail()) ? "" : sqsEntity.getEmail();
        String principal = StringUtil.isNullOrEmpty(sqsEntity.getPrincipal()) ? "" : sqsEntity.getPrincipal();
        String position = StringUtil.isNullOrEmpty(sqsEntity.getPosition()) ? "" : sqsEntity.getPosition();
        String telePhone = StringUtil.isNullOrEmpty(sqsEntity.getTelephone()) ? "" : sqsEntity.getTelephone();
        String phone = StringUtil.isNullOrEmpty(sqsEntity.getPhone()) ? "" : sqsEntity.getPhone();
        String contacPerson = StringUtil.isNullOrEmpty(sqsEntity.getContactPerson()) ? "" : sqsEntity.getContactPerson();
        String contactPosition = StringUtil.isNullOrEmpty(sqsEntity.getContactPosition()) ? "" : sqsEntity.getContactPosition();
        String contactTelephone = StringUtil.isNullOrEmpty(sqsEntity.getContactTelephone()) ? "" : sqsEntity.getContactTelephone();
        String contactPhone = StringUtil.isNullOrEmpty(sqsEntity.getContactPhone()) ? "" : sqsEntity.getContactPhone();
        String shxyDm = StringUtil.isNullOrEmpty(sqsEntity.getShxydm()) ? "" : sqsEntity.getShxydm();
        String legalPerson = StringUtil.isNullOrEmpty(sqsEntity.getLegalPerson()) ? "" : sqsEntity.getLegalPerson();
        String legalunitName = StringUtil.isNullOrEmpty(sqsEntity.getLegalunitName()) ? "" : sqsEntity.getLegalunitName();
        String legaUnitAddree = StringUtil.isNullOrEmpty(sqsEntity.getLegalunitAdress()) ? "" : sqsEntity.getLegalunitAdress();
        String legalPrincipal = StringUtil.isNullOrEmpty(sqsEntity.getLegalPrincipal()) ? "" : sqsEntity.getLegalPrincipal();
        String legalposition = StringUtil.isNullOrEmpty(sqsEntity.getLegalposition()) ? "" : sqsEntity.getLegalposition();
        String legalphone = StringUtil.isNullOrEmpty(sqsEntity.getLegalphone()) ? "" : sqsEntity.getLegalphone();
        String legalshxydm = StringUtil.isNullOrEmpty(sqsEntity.getLegalshxydm()) ? "" : sqsEntity.getLegalshxydm();
        String competentunitName = StringUtil.isNullOrEmpty(sqsEntity.getCompetentunitName()) ? "" : sqsEntity.getCompetentunitName();
        String competentunitAdress = StringUtil.isNullOrEmpty(sqsEntity.getCompetentunitAdress()) ? "" : sqsEntity.getCompetentunitAdress();
        String competentPrincipal = StringUtil.isNullOrEmpty(sqsEntity.getCompetentPrincipal()) ? "" : sqsEntity.getCompetentPrincipal();
        String competentposition = StringUtil.isNullOrEmpty(sqsEntity.getCompetentposition()) ? "" : sqsEntity.getCompetentposition();
        String competentphone = StringUtil.isNullOrEmpty(sqsEntity.getCompetentphone()) ? "" : sqsEntity.getCompetentphone();
        String characteristic = StringUtil.isNullOrEmpty(sqsEntity.getCharacteristic()) ? "" : sqsEntity.getCharacteristic();
        String legalPersonjgType = StringUtil.isNullOrEmpty(sqsEntity.getLegalPersonjgType()) ? "" : sqsEntity.getLegalPersonjgType();
        String legalPersonType = StringUtil.isNullOrEmpty(sqsEntity.getLegalPersonType()) ? "" : sqsEntity.getLegalPersonType();
        DataRegion PO_gkJgmc = doc.openDataRegion("PO_gkJgmc");
        DataRegion PO_gkJgdz = doc.openDataRegion("PO_gkJgdz");
        DataRegion PO_postcode = doc.openDataRegion("PO_postcode");
        DataRegion PO_fax = doc.openDataRegion("PO_fax");
        DataRegion PO_email = doc.openDataRegion("PO_email");
        DataRegion PO_principal = doc.openDataRegion("PO_principal");
        DataRegion PO_position = doc.openDataRegion("PO_position");
        DataRegion PO_telephone = doc.openDataRegion("PO_telephone");
        DataRegion PO_phone = doc.openDataRegion("PO_phone");
        DataRegion PO_contactPerson = doc.openDataRegion("PO_contactPerson");
        DataRegion PO_contactPosition = doc.openDataRegion("PO_contactPosition");
        DataRegion PO_contactTelephone = doc.openDataRegion("PO_contactTelephone");
        DataRegion PO_syslxrsj = doc.openDataRegion("PO_syslxrsj");
        DataRegion PO_JgShxydm = doc.openDataRegion("PO_JgShxydm");
        DataRegion PO_legalPerson = doc.openDataRegion("PO_legalPerson");
        DataRegion PO_ssfrdwmc = doc.openDataRegion("PO_ssfrdwmc");
        DataRegion PO_ssfrdwdz = doc.openDataRegion("PO_ssfrdwdz");
        DataRegion PO_ssfrdwfzr = doc.openDataRegion("PO_ssfrdwfzr");
        DataRegion PO_ssfrdwfzrzw = doc.openDataRegion("PO_ssfrdwfzrzw");
        DataRegion PO_ssfrdwfzrdh = doc.openDataRegion("PO_ssfrdwfzrdh");
        DataRegion PO_ssfrdwshxydm = doc.openDataRegion("PO_ssfrdwshxydm");
        DataRegion PO_zgdwmc = doc.openDataRegion("PO_zgdwmc");
        DataRegion PO_zgdwdz = doc.openDataRegion("PO_zgdwdz");
        DataRegion PO_zgdwfzr = doc.openDataRegion("PO_zgdwfzr");
        DataRegion PO_zgdwfzrzw = doc.openDataRegion("PO_zgdwfzrzw");
        DataRegion PO_zgdwfzrdh = doc.openDataRegion("PO_zgdwfzrdh");
        DataRegion PO_characteristic1 = doc.openDataRegion("PO_characteristic1");
        DataRegion PO_characteristic2 = doc.openDataRegion("PO_characteristic2");
        DataRegion PO_characteristic3 = doc.openDataRegion("PO_characteristic3");
        DataRegion PO_characteristic4 = doc.openDataRegion("PO_characteristic4");
        DataRegion PO_legalPersonjgType1 = doc.openDataRegion("PO_legalPersonjgType1");
        DataRegion PO_legalPersonjgType2 = doc.openDataRegion("PO_legalPersonjgType2");
        DataRegion PO_legalPersonType1 = doc.openDataRegion("PO_legalPersonType1");
        DataRegion PO_legalPersonType2 = doc.openDataRegion("PO_legalPersonType2");
        DataRegion PO_legalPersonType3 = doc.openDataRegion("PO_legalPersonType3");
        DataRegion PO_legalPersonType4 = doc.openDataRegion("PO_legalPersonType4");
        DataRegion PO_jgdz1 = doc.openDataRegion("PO_jgdz1");
        DataRegion PO_jgdz2 = doc.openDataRegion("PO_jgdz2");
        PO_gkJgmc.setValue(gkJgmc);
        PO_gkJgdz.setValue(address);
        PO_jgdz1.setValue(address);
        PO_jgdz2.setValue(address);
        PO_postcode.setValue(postCode);
        PO_fax.setValue(fax);
        PO_email.setValue(email);
        PO_principal.setValue(principal);
        PO_position.setValue(position);
        PO_telephone.setValue(telePhone);
        PO_phone.setValue(phone);
        PO_contactPerson.setValue(contacPerson);
        PO_contactPosition.setValue(contactPosition);
        PO_contactTelephone.setValue(contactTelephone);
        PO_syslxrsj.setValue(contactPhone);
        PO_JgShxydm.setValue(shxyDm);
        PO_legalPerson.setValue(legalPerson);
        PO_ssfrdwmc.setValue(legalunitName);
        PO_ssfrdwdz.setValue(legaUnitAddree);
        PO_ssfrdwfzr.setValue(legalPrincipal);
        PO_ssfrdwfzrzw.setValue(legalposition);
        PO_ssfrdwfzrdh.setValue(legalphone);
        PO_ssfrdwshxydm.setValue(legalshxydm);
        PO_zgdwmc.setValue(competentunitName);
        PO_zgdwdz.setValue(competentunitAdress);
        PO_zgdwfzr.setValue(competentPrincipal);
        PO_zgdwfzrzw.setValue(competentposition);
        PO_zgdwfzrdh.setValue(competentphone);

        if (characteristic.contains("??????")) {
            PO_characteristic1.setValue("???");
        } else {
            PO_characteristic1.setValue("???");
        }
        if (characteristic.contains("??????")) {
            PO_characteristic2.setValue("???");
        } else {
            PO_characteristic2.setValue("???");
        }
        if (characteristic.contains("?????????")) {
            PO_characteristic3.setValue("???");
        } else {
            PO_characteristic3.setValue("???");
        }
        if (characteristic.contains("?????????")) {
            PO_characteristic4.setValue("???");
        } else {
            PO_characteristic4.setValue("???");
        }

        if ("??????????????????????????????".equals(legalPersonjgType)) {
            PO_legalPersonjgType1.setValue("???");
        } else {
            PO_legalPersonjgType1.setValue("???");
        }
        if ("?????????????????????????????????".equals(legalPersonjgType)) {
            PO_legalPersonjgType2.setValue("???");
        } else {
            PO_legalPersonjgType2.setValue("???");
        }

        if (legalPersonType.contains("????????????")) {
            PO_legalPersonType1.setValue("???");
        } else {
            PO_legalPersonType1.setValue("???");
        }
        if (legalPersonType.contains("????????????")) {
            PO_legalPersonType2.setValue("???");
        } else {
            PO_legalPersonType2.setValue("???");
        }
        if (legalPersonType.contains("????????????")) {
            PO_legalPersonType3.setValue("???");
        } else {
            PO_legalPersonType3.setValue("???");
        }
        if (legalPersonType.contains("??????")) {
            PO_legalPersonType4.setValue("???");
        } else {
            PO_legalPersonType4.setValue("???");
        }
    }

    // ????????????
    ApiShiYsJyjcSqlx sqlxEntity = sqlxService.queryBySerialNumber(serialNumber);
    if (sqlxEntity != null) {
        String applicationType = sqlxEntity.getApplicationType() + "";
        String cmaPermitNumber = sqlxEntity.getCmaPermitNumber() + "";
        Date cmaPermitEffectiveDate = sqlxEntity.getCmaPermitEffectiveDate();
        String cmaPermitEffectiveDateStr = "";
        if (cmaPermitEffectiveDate != null) {
            cmaPermitEffectiveDateStr = sdf.format(cmaPermitEffectiveDate);
        }
        DataRegion PO_applicationType1 = doc.openDataRegion("PO_applicationType1");
        DataRegion PO_applicationType2 = doc.openDataRegion("PO_applicationType2");
        DataRegion PO_applicationType3 = doc.openDataRegion("PO_applicationType3");
        DataRegion PO_applicationType4 = doc.openDataRegion("PO_applicationType4");
        DataRegion PO_applicationType5 = doc.openDataRegion("PO_applicationType5");
        DataRegion PO_applicationType6 = doc.openDataRegion("PO_applicationType6");
        DataRegion PO_applicationType7 = doc.openDataRegion("PO_applicationType7");
        if (applicationType.contains("??????")) {
            PO_applicationType1.setValue("???");
        } else {
            PO_applicationType1.setValue("???");
        }
        if (applicationType.contains("??????")) {
            PO_applicationType2.setValue("???");
        } else {
            PO_applicationType2.setValue("???");
        }
        if (applicationType.contains("????????????")) {
            PO_applicationType3.setValue("???");
        } else {
            PO_applicationType3.setValue("???");
        }
        if (applicationType.contains("??????")) {
            PO_applicationType4.setValue("???");
        } else {
            PO_applicationType4.setValue("???");
        }
        if (applicationType.contains("??????")) {
            PO_applicationType5.setValue("???");
        } else {
            PO_applicationType5.setValue("???");
        }
        if (applicationType.contains("????????????")) {
            PO_applicationType6.setValue("???");
        } else {
            PO_applicationType6.setValue("???");
        }
        if (applicationType.contains("?????????????????????")) {
            PO_applicationType7.setValue("???");
        } else {
            PO_applicationType7.setValue("???");
        }
        DataRegion PO_jlrzzsbh = doc.openDataRegion("PO_jlrzzsbh");
        DataRegion PO_jlrzzsyxjzr = doc.openDataRegion("PO_jlrzzsyxjzr");
        PO_jlrzzsbh.setValue(cmaPermitNumber);
        PO_jlrzzsyxjzr.setValue(cmaPermitEffectiveDateStr);
    }

    // ????????????
    ApiShiYsJyjcJgzy jgzyEntity = jgzyService.queryBySerialNumber(serialNumber);
    if (jgzyEntity != null) {
        int allNumber = jgzyEntity.getAllNumber() > 0 ? jgzyEntity.getAllNumber() : 0;
        int hJobTitleNumber = jgzyEntity.gethJobTitleNumber() == null ? 0 : jgzyEntity.gethJobTitleNumber();
        int mJobTitleNumber = jgzyEntity.getmJobTitleNumber() == null ? 0 : jgzyEntity.getmJobTitleNumber();
        int pJobTitleNumber = jgzyEntity.getpJobTitleNumber() == null ? 0 : jgzyEntity.getpJobTitleNumber();
        int otherNumber = jgzyEntity.getOtherNumber() == null ? 0 : jgzyEntity.getOtherNumber();
        int equipmentNumber = jgzyEntity.getEquipmentNumber() == null ? 0 : jgzyEntity.getEquipmentNumber();
        String hRatioAll = StringUtil.isNullOrEmpty(jgzyEntity.gethRatioAll()) ? "" : jgzyEntity.gethRatioAll();
        String mRatioAll = StringUtil.isNullOrEmpty(jgzyEntity.gethRatioAll()) ? "" : jgzyEntity.gethRatioAll();
        String pRatioAll = StringUtil.isNullOrEmpty(jgzyEntity.getpRatioAll()) ? "" : jgzyEntity.getpRatioAll();
        String oRatioAll = StringUtil.isNullOrEmpty(jgzyEntity.getoRatioAll()) ? "" : jgzyEntity.getoRatioAll();
        String fixedAssets = StringUtil.isNullOrEmpty(jgzyEntity.getFixedAssets()) ? "" : jgzyEntity.getFixedAssets();
        String propertyRights = StringUtil.isNullOrEmpty(jgzyEntity.getPropertyRights()) ? "" : jgzyEntity.getPropertyRights();
        String propertyRightsValue = StringUtil.isNullOrEmpty(jgzyEntity.getPropertyRightsValue()) ? "" : jgzyEntity.getPropertyRightsValue();
        String institutionsArea = StringUtil.isNullOrEmpty(jgzyEntity.getInstitutionsArea()) ? "" : jgzyEntity.getInstitutionsArea();
        String siteArea = StringUtil.isNullOrEmpty(jgzyEntity.getSiteArea()) ? "" : jgzyEntity.getSiteArea();
        String whArea = StringUtil.isNullOrEmpty(jgzyEntity.getWhArea()) ? "" : jgzyEntity.getWhArea();
        String outdoorsiteArea = StringUtil.isNullOrEmpty(jgzyEntity.getOutdoorsiteArea()) ? "" : jgzyEntity.getOutdoorsiteArea();
        String sitePropertyRights = StringUtil.isNullOrEmpty(jgzyEntity.getSitePropertyRights()) ? "" : jgzyEntity.getSitePropertyRights();
        DataRegion PO_syszrs = doc.openDataRegion("PO_syszrs");
        DataRegion PO_gjzyjsrs = doc.openDataRegion("PO_gjzyjsrs");
        DataRegion PO_gc = doc.openDataRegion("PO_gc");
        DataRegion PO_zjzyjsrs = doc.openDataRegion("PO_zjzyjsrs");
        DataRegion PO_zc = doc.openDataRegion("PO_zc");
        DataRegion PO_cjzyjsrs = doc.openDataRegion("PO_cjzyjsrs");
        DataRegion PO_cc = doc.openDataRegion("PO_cc");
        DataRegion PO_qtrs = doc.openDataRegion("PO_qtrs");
        DataRegion PO_cq = doc.openDataRegion("PO_cq");
        DataRegion PO_gdzcz = doc.openDataRegion("PO_gdzcz");
        DataRegion PO_yqsbzs = doc.openDataRegion("PO_yqsbzs");
        DataRegion PO_propertyRights1 = doc.openDataRegion("PO_propertyRights1");
        DataRegion PO_propertyRightsValue1 = doc.openDataRegion("PO_propertyRightsValue1");
        DataRegion PO_propertyRightsValue2 = doc.openDataRegion("PO_propertyRightsValue2");
        DataRegion PO_propertyRightsValue3 = doc.openDataRegion("PO_propertyRightsValue3");
        DataRegion PO_propertyRights2 = doc.openDataRegion("PO_propertyRights2");
        DataRegion PO_propertyRights3 = doc.openDataRegion("PO_propertyRights3");
        DataRegion PO_syszmj = doc.openDataRegion("PO_syszmj");
        DataRegion PO_jcsmj = doc.openDataRegion("PO_jcsmj");
        DataRegion PO_hwmj = doc.openDataRegion("PO_hwmj");
        DataRegion PO_hwxjcdmj = doc.openDataRegion("PO_hwxjcdmj");
        DataRegion PO_sitePropertyRights1 = doc.openDataRegion("PO_sitePropertyRights1");
        DataRegion PO_sitePropertyRights2 = doc.openDataRegion("PO_sitePropertyRights2");
        DataRegion PO_sitePropertyRights3 = doc.openDataRegion("PO_sitePropertyRights3");
        DataRegion PO_sitePropertyRightsValue1 = doc.openDataRegion("PO_sitePropertyRightsValue1");
        DataRegion PO_sitePropertyRightsValue2 = doc.openDataRegion("PO_sitePropertyRightsValue2");
        DataRegion PO_sitePropertyRightsValue3 = doc.openDataRegion("PO_sitePropertyRightsValue3");
        PO_syszrs.setValue(String.valueOf(allNumber));
        PO_gjzyjsrs.setValue(String.valueOf(hJobTitleNumber));
        PO_gc.setValue(hRatioAll);
        PO_zjzyjsrs.setValue(String.valueOf(mJobTitleNumber));
        PO_zc.setValue(mRatioAll);
        PO_cjzyjsrs.setValue(String.valueOf(pJobTitleNumber));
        PO_cc.setValue(pRatioAll);
        PO_qtrs.setValue(String.valueOf(otherNumber));
        PO_cq.setValue(oRatioAll);
        PO_gdzcz.setValue(fixedAssets);
        PO_yqsbzs.setValue(String.valueOf(equipmentNumber));

        if (propertyRights.contains("??????")) {
            PO_propertyRights1.setValue("???");
            PO_propertyRightsValue1.setValue(propertyRightsValue);
        }
        if (propertyRights.contains("??????")) {
            PO_propertyRights2.setValue("???");
            PO_propertyRightsValue2.setValue(propertyRightsValue);
        }
        if (propertyRights.contains("??????")) {
            PO_propertyRights3.setValue("???");
            PO_propertyRightsValue3.setValue(propertyRightsValue);
        }
        PO_syszmj.setValue(institutionsArea);
        PO_jcsmj.setValue(siteArea);
        PO_hwmj.setValue(whArea);
        PO_hwxjcdmj.setValue(outdoorsiteArea);

        if (sitePropertyRights.contains("??????")) {
            PO_sitePropertyRights1.setValue("???");
            PO_sitePropertyRightsValue1.setValue(propertyRightsValue);
        }
        if (sitePropertyRights.contains("??????")) {
            PO_sitePropertyRights2.setValue("???");
            PO_sitePropertyRightsValue2.setValue(propertyRightsValue);
        }
        if (sitePropertyRights.contains("??????")) {
            PO_sitePropertyRights3.setValue("???");
            PO_sitePropertyRightsValue3.setValue(propertyRightsValue);
        }
    }

    // ??????
    HashMap reqMap = new HashMap();
    reqMap.put("siteType", "2");
    reqMap.put("serialNumber", serialNumber);
    reqMap.put("pageIndex", -1);
    reqMap.put("pageSize", -1);
    reqMap.put("sortField", "");
    reqMap.put("sortOrder", "");
    Map<String, Object> cdMap = cdService.queryListBySerialNumber(reqMap);
    List<ApiShiYsJyjcCd> cdList = (List<ApiShiYsJyjcCd>) cdMap.get(R.DATA_NAME);
    if (cdList != null && cdList.size() > 0) {
        DataRegion regTable = doc.openDataRegion("PO_xsqTable");
        Table table = regTable.openTable(1);
        //???????????????????????????2????????????
        if (cdList.size() > 2) {
            for (int i = 0; i < cdList.size() - 2; i++) {
                table.insertRowAfter(table.openCellRC(3 + i, 1));//????????????
            }
        }
        Iterator iteratorDcd = cdList.iterator();
        int iCounter = 0;
        while (iteratorDcd.hasNext()) {
            ApiShiYsJyjcCd cdEntity = (ApiShiYsJyjcCd) iteratorDcd.next();
            table.openCellRC(2 + iCounter, 1).setValue(cdEntity.getSiteAddress() + "");
            iCounter++;
        }
    }

    if (cdList != null) {
        String PO_qzrHzTable = "PO_qzrHzTable";
        String PO_table4 = "PO_ryTable";
        int index = 0;
        for (int i = 0; i < cdList.size(); i++) {
            DataRegion data = doc.createDataRegion(PO_qzrHzTable + i, DataRegionInsertType.After, PO_qzrHzTable);
            data.setValue("[word]doc/sqqzrTemplate.docx[/word]");//?????????????????????????????????                regTable.setValue("[word]doc/jyjcryTemplate.docx[/word]");//?????????????????????????????????
            if (i > 0) {
                data.selectStart();
                doc.insertPageBreak();
            }
            PO_qzrHzTable = PO_qzrHzTable + i;
            ApiShiYsJyjcCd apiShiYsJyjcCd = cdList.get(i);
            reqMap.put("address", apiShiYsJyjcCd.getSiteAddress());
            Map<String, Object> qzrMap = qzrService.getList(reqMap);
            List<Map<String, Object>> qzrListMap = (List<Map<String, Object>>) qzrMap.get(R.DATA_NAME);
            Table sqqzrTable = data.openTable(1);
            sqqzrTable.openCellRC(2, 1).setValue("???????????????????????????" + apiShiYsJyjcCd.getSiteAddress());
            for (int j = 0; j < qzrListMap.size(); j++) {
                if (j > 0) {
                    sqqzrTable.insertRowAfter(sqqzrTable.openCellRC(5 + j, 1));
                }
                sqqzrTable.openCellRC(5 + j, 1).setValue(j + 1 + "");
                sqqzrTable.openCellRC(5 + j, 2).setValue(qzrListMap.get(j).get("NAME") + "");
                sqqzrTable.openCellRC(5 + j, 4).setValue(qzrListMap.get(j).get("JOBTITLE") + "");
                sqqzrTable.openCellRC(5 + j, 5).setValue(qzrListMap.get(j).get("SIGNATUREFIELD") + "");

            }

            // ??????????????????
            DataRegion regTable = doc.createDataRegion(PO_table4 + i, DataRegionInsertType.After, PO_table4);
            regTable.setValue("[word]doc/jyjcryTemplate.docx[/word]");//?????????????????????????????????
            if (i > 0) {
                regTable.selectStart();
                doc.insertPageBreak();
            }
            PO_table4 = PO_table4 + i;

            Table table = regTable.openTable(1);
            Map<String, Object> ryMap = ryService.getList(reqMap);
            List<Map<String, Object>> ryListMap = (List<Map<String, Object>>) ryMap.get(R.DATA_NAME);
            if (ryListMap != null && ryListMap.size() > 0) {
                table.openCellRC(2, 1).setValue("???????????????????????????" + apiShiYsJyjcCd.getSiteAddress());
                for (int k = 0; k < ryListMap.size(); k++) {
                    if (k > 0) {
                        table.insertRowAfter(table.openCellRC(4 + k, 1));
                    }
                    table.openCellRC(4 + k, 1).setValue(k + 1 + "");
                    table.openCellRC(4 + k, 2).setValue(ryListMap.get(k).get("NAME") + "");
                    table.openCellRC(4 + k, 3).setValue(ryListMap.get(k).get("SEX") + "");
                    table.openCellRC(4 + k, 4).setValue(ryListMap.get(k).get("AGE") + "");
                    table.openCellRC(4 + k, 5).setValue(ryListMap.get(k).get("EDUCATION") + "");
                    table.openCellRC(4 + k, 6).setValue(ryListMap.get(k).get("POSITION") + "");
                    table.openCellRC(4 + k, 7).setValue(ryListMap.get(k).get("JOBTITLE") + "");
                    table.openCellRC(4 + k, 8).setValue(ryListMap.get(k).get("PROFESSIONAL") + "");
                    table.openCellRC(4 + k, 9).setValue(ryListMap.get(k).get("TECHNICALFIELDYEAR") + "");
                    table.openCellRC(4 + k, 10).setValue(ryListMap.get(k).get("DEPARTMENT") + "");
                }
            }
        }
    }


    // ?????????????????????
    reqMap.remove("address");
    Map<String, Object> qzrMap = qzrService.getList(reqMap);
    List<Map<String, Object>> qzrListMap = (List<Map<String, Object>>) qzrMap.get(R.DATA_NAME);
    if (qzrListMap != null && qzrListMap.size() > 0) {
        String PO_qzrTable = "PO_qzrTable";
        int index = 0;
        for (int i = 0; i < qzrListMap.size(); i++) {
            Map<String, Object> map = qzrListMap.get(i);
            String localfilepath = (String) map.get("LOCALFILEPATH");
            String fileAllPath = SysFinalRecource.SECOND_LEVEL_DIRECTORY + localfilepath;
            fileAllPath = fileAllPath.replace("/", "\\\\");
            File file = new File(fileAllPath);
            if (!file.exists()) {
                continue;
            }
            DataRegion data1 = doc.createDataRegion(PO_qzrTable + (index + 1), DataRegionInsertType.After, PO_qzrTable);
            data1.setValue("[word]" + fileAllPath + "[/word]");
            data1.selectStart();
            doc.insertPageBreak();
            index++;
        }
    }

    // ???????????????(???????????? ??????????????????113)
    Long flowId = bizEntity.getFlowId();
    String fileTypeFlag = "4";
    if (flowId == 6) {
        fileTypeFlag = "216";
    }
    List<ApiFile> fileList = fileService.getApiFileBySerialNumberFlag(serialNumber, fileTypeFlag);
    String PO_zzjgt = "PO_zzjgt";
    int index = 0;
    for (int i = 0; i < fileList.size(); i++) {
        ApiFile entity = fileList.get(i);
        String localfilepath = entity.getLocalFilePath();
        String fileType = entity.getFileType();
        String fileAllPath = SysFinalRecource.SECOND_LEVEL_DIRECTORY + localfilepath;
        fileAllPath = fileAllPath.replace("/", "\\\\");
        File file = new File(fileAllPath);
        if (!file.exists()) {
            continue;
        }
        DataRegion data1 = doc.createDataRegion(PO_zzjgt + (index + 1), DataRegionInsertType.After, PO_zzjgt);
        if (!StringUtil.isNullOrEmpty(fileType) && fileType.equalsIgnoreCase("doc") || fileType.equalsIgnoreCase("docx")) {
            data1.setValue("[word]" + fileAllPath + "[/word]");
        } else {
            data1.setValue("[image]" + fileAllPath + "[/image]");
        }

        data1.selectStart();
        doc.insertPageBreak();
        index++;
    }


    //???????????????????????????
    //?????????????????????????????????
    HashMap nlmap = new HashMap();
    nlmap.put("serialNumber", serialNumber);
    nlmap.put("nlorsb", "0");
    Map<String, Object> nlList = nlService.getNlFileListByDoc(nlmap);
    List<HashMap> nlEntity = (List<HashMap>) nlList.get("data");
    String PO_nlTable = "PO_nlTable";
    int index2 = 0;
    //??????:??????????????????
    if (nlEntity.size() > 0) {
        for (int i = 0; i < nlEntity.size(); i++) {
            Map mapEntity = nlEntity.get(i);
            String docfilepath = String.valueOf(mapEntity.get("FILEPATHWORD"));
            //??????:???????????????doc
            if (!StringUtil.isNullOrEmpty(docfilepath)) {
                //??????????????????
                String fileAllPath = SysFinalRecource.SECOND_LEVEL_DIRECTORY + docfilepath;
                fileAllPath = fileAllPath.replace("/", "\\\\");
                File file = new File(fileAllPath);
                if (!file.exists()) {
                    continue;
                }
                //??????????????????
                DataRegion data1 = doc.createDataRegion(PO_nlTable + (index2 + 1), DataRegionInsertType.After, PO_nlTable);
                data1.setValue("[word]" + fileAllPath + "[/word]");

                if (i > 0) {
                    data1.selectStart();
                    doc.insertPageBreak();
                }

                PO_nlTable = PO_nlTable + (index2 + 1);
                index2++;
            }
        }
    }

    //???????????????????????????
    //?????????????????????????????????
    HashMap yqsbmap = new HashMap();
    yqsbmap.put("serialNumber", serialNumber);
    yqsbmap.put("nlorsb", "1");
    Map<String, Object> yqsbList = nlService.getNlFileListByDoc(yqsbmap);
    List<HashMap> yqsbEntity = (List<HashMap>) yqsbList.get("data");
    String PO_yqsbTable = "PO_yqsbTable";
    //??????:??????????????????
    int index3 = 0;
    if (yqsbEntity.size() > 0) {
        for (int i = 0; i < yqsbEntity.size(); i++) {
            Map mapEntity = yqsbEntity.get(i);
            String docfilepath = String.valueOf(mapEntity.get("FILEPATHWORD"));
            //??????:???????????????doc
            if (!StringUtil.isNullOrEmpty(docfilepath)) {
                //??????????????????
                String fileAllPath = SysFinalRecource.SECOND_LEVEL_DIRECTORY + docfilepath;
                fileAllPath = fileAllPath.replace("/", "\\\\");
                File file = new File(fileAllPath);
                if (!file.exists()) {
                    continue;
                }
                //??????????????????
                DataRegion data1 = doc.createDataRegion(PO_yqsbTable + (index3 + 1), DataRegionInsertType.After, PO_yqsbTable);
                data1.setValue("[word]" + fileAllPath + "[/word]");
                data1.selectStart();
                doc.insertPageBreak();
                index3++;

            }
        }
    }

    poCtrl1.setWriter(doc);
    //??????pageoffice????????????
    poCtrl1.addCustomToolButton("??????", "Save()", 1);
    poCtrl1.setSaveFilePage("shiYsJyjcFuJian_fileWebSave.action?serialNumber=" + serialNumber + "&flag=" + flag);
    poCtrl1.addCustomToolButton("???????????????", "SaveAs()", 1);

    poCtrl1.addCustomToolButton("????????????", "ShowPageSetupDlg()", 5);
    poCtrl1.addCustomToolButton("??????", "ShowPrintDlg()", 6);
    poCtrl1.addCustomToolButton("????????????", "PrintPreView()", 7);

    // ???????????????????????????????????????
    poCtrl1.setJsFunction_AfterDocumentSaved("AfterDocumentSaved()");
    poCtrl1.setMenubar(false);//???????????????
    poCtrl1.setOfficeToolbars(false);//???????????????
    //??????Word??????
    poCtrl1.webOpen("doc/ShiYsJyjc.doc", OpenModeType.docNormalEdit, "??????");
    poCtrl1.setTagId("PageOfficeCtrl1"); //????????????
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>?????????????????????????????????</title>
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
    /*???????????????????????????0:popup??????1:????????????2:???????????????*/
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

    //????????????
    function ShowPageSetupDlg() {
        document.getElementById("PageOfficeCtrl1").ShowDialog(5);
    }

    //??????
    function ShowPrintDlg() {
        document.getElementById("PageOfficeCtrl1").ShowDialog(4);
    }

    //????????????
    function PrintPreView() {
        document.getElementById("PageOfficeCtrl1").PrintPreview();
    }


    //????????????????????????
    function BeforeDocumentSaved() {
        document.getElementById("PageOfficeCtrl1").Alert("???????????????????????????");
        return true;
    }

    //?????????????????????????????????
    function AfterDocumentSaved(IsSaved) {
        if (IsSaved) {
            document.getElementById("PageOfficeCtrl1").Alert("???????????????");
            if (openFlag == 2) {
                CloseWindow("saveToClose");
            } else if (openFlag == 1) {
                CloseWindow("redirect");
            } else {
                CloseWindow();
            }
        }
    }

    //???????????????????????????
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

    //??????
    function runBack() {
        window.location.href = "<%=basePath%>jsp/shiYsJyjcFlow/ShiYsJyjcZhunYujds.jsp";
    }
</script>
</body>
</html>
