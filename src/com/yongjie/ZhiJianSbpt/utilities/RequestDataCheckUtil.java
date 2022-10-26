package com.yongjie.ZhiJianSbpt.utilities;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yongjie.ZhiJianSbpt.bggl.model.*;
import com.yongjie.ZhiJianSbpt.model.*;
import com.yongjie.ZhiJianSbpt.model.jyjcJgzx.BizApiZxsqb;
import com.yongjie.ZhiJianSbpt.model.jyjcbggl.BizApiZwcnBg;
import org.apache.poi.ss.formula.functions.T;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class RequestDataCheckUtil {
    public static BizApiShiYsJyjc checkFmxx(String reqJson) {
        BizApiShiYsJyjc entity = new BizApiShiYsJyjc();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtil.isNullOrEmpty(reqJson)) {
            throw new RuntimeException("数据不能为空!");
        }
        Map<String, Object> reqMap = JSONObject.parseObject(reqJson, Map.class);
        String zylylbNames = "";
        String zylylbIds = "";
        String areaId = String.valueOf(reqMap.get("areaId"));
        String serialNumber = (String) reqMap.get("serialNumber");
        String flowId = String.valueOf(reqMap.get("flowId"));
        if (StringUtil.isNullOrEmpty(serialNumber)) {
            throw new RuntimeException("获取流水号异常!");
        }
        entity.setSerialNumber(serialNumber);
        entity.setFlowId(Long.valueOf(flowId));

        try {
            entity.setAreaId(Long.valueOf(areaId));
        } catch (Exception e) {
            throw new RuntimeException("上报机关有误,请重新选择专业领域类别!");
        }
        entity.setZylylbName(zylylbNames);
        entity.setZylylbDm(zylylbIds);
        if (!StringUtil.isNullOrEmpty(reqMap.get("id"))) {
            entity.setId(Long.valueOf(reqMap.get("id").toString()));
        }
        entity.setJgmc((String) reqMap.get("jgmc"));
        entity.setZgbmName((String) reqMap.get("zgbmName"));
        entity.setZgbmDm((String) reqMap.get("zgbmDm"));
        entity.setShxydm((String) reqMap.get("shxydm"));
        if (!StringUtil.isNullOrEmpty(reqMap.get("areaId"))) {
            entity.setAreaId(Long.valueOf(String.valueOf(reqMap.get("areaId"))));
        }
        if (!StringUtil.isNullOrEmpty(reqMap.get("checkAreaId"))) {
            entity.setCheckAreaId((Long.valueOf(String.valueOf(reqMap.get("checkAreaId")))));
        }
        String sqrq = (String) reqMap.get("sqrq");
        String xwpsrq = (String) reqMap.get("xwpsrq");
        String isFirstOrOther = (String) reqMap.get("isFirstOrOther");
        if (!StringUtil.isNullOrEmpty(isFirstOrOther)) {
            entity.setIsFirstOrOther(isFirstOrOther);
        }
        try {
            if (!StringUtil.isNullOrEmpty(sqrq)) {
                entity.setSqrq(sdf.parse(sqrq));
            }

            if (!StringUtil.isNullOrEmpty(xwpsrq)) {
                entity.setXwpsrq(sdf.parse(xwpsrq));
            }
        } catch (Exception e) {
            throw new RuntimeException("批准日期格式不正确（yyyy-MM-dd）!");
        }
        // 默认设置 flag = 0 未上报
        entity.setFlag(0);
        return entity;
    }

    public static ApiShiYsJyjcSqs checkGk(String reqJson) {
        if (StringUtil.isEmpty(reqJson)) {
            throw new RuntimeException("数据不能为空!");
        }
        ApiShiYsJyjcSqs entity;
        try {
            entity = (ApiShiYsJyjcSqs) JSON.Decode(reqJson);
        } catch (Exception e) {
            try {
                entity = JSONObject.parseObject(reqJson, ApiShiYsJyjcSqs.class);
            } catch (Exception e2) {
                throw new RuntimeException("json数据格式有误!");
            }
        }
        if (StringUtil.isEmpty(entity.getJgmc())) {
            throw new RuntimeException("机构名称不能为空!");
        }
        if (StringUtil.isEmpty(entity.getRegisterAdress())) {
            throw new RuntimeException("注册地址地址不能为空!");
        }
        if (StringUtil.isEmpty(entity.getAdress())) {
            throw new RuntimeException("实验室地址不能为空!");
        }

        if (StringUtil.isEmpty(entity.getContactPerson())) {
            throw new RuntimeException("联络人不能为空!");
        }

        if (StringUtil.isEmpty(entity.getContactPhone())) {
            throw new RuntimeException("联络人手机不能为空!");
        }
        if (StringUtil.isEmpty(entity.getShxydm())) {
            throw new RuntimeException("社会信用代码不能为空!");
        }

        if (StringUtil.isEmpty(entity.getLegalPerson())) {
            throw new
                    RuntimeException("法定代表人不能为空!");
        }

        if (StringUtil.isEmpty(entity.getSerialNumber())) {
            throw new RuntimeException("流水号不能为空!");
        }
        return entity;
    }

    public static ApiShiYsJyjcSqlx checkSqlx(String reqJson) {
        if (StringUtil.isEmpty(reqJson)) {
            throw new RuntimeException("数据不能为空!");
        }
        ApiShiYsJyjcSqlx apiShiYsJyjcSqlx;
        try {
            apiShiYsJyjcSqlx = (ApiShiYsJyjcSqlx) JSON.Decode(reqJson);
        } catch (Exception e) {
            try {
                apiShiYsJyjcSqlx = JSONObject.parseObject(reqJson, ApiShiYsJyjcSqlx.class);
            } catch (Exception e2) {
                throw new RuntimeException("json数据格式有误!");
            }
        }
        if (StringUtil.isEmpty(apiShiYsJyjcSqlx.getApplicationType())) {
            throw new RuntimeException("申请类型不能为空!");

        }
        if (StringUtil.isEmpty(apiShiYsJyjcSqlx.getSerialNumber())) {
            throw new RuntimeException("流水号不能为空!");
        }
        if (!"首次".equals(apiShiYsJyjcSqlx.getApplicationType())) {
            if (StringUtil.isEmpty(apiShiYsJyjcSqlx.getCmaPermitNumber())) {
                throw new RuntimeException("CMA资质认定证书编号不能为空!");
            }
            if (StringUtil.isEmpty(apiShiYsJyjcSqlx.getCmaPermitEffectiveDate())) {
                throw new RuntimeException("CMA证书有效日期不能为空!");
            }
        }
        return apiShiYsJyjcSqlx;
    }

    public static ApiShiYsJyjcJgzy checkJgzy(String reqJson) {
        if (StringUtil.isEmpty(reqJson)) {
            throw new RuntimeException("数据不能为空!");
        }
        ApiShiYsJyjcJgzy apiShiYsJyjcJgzy;
        try {
            apiShiYsJyjcJgzy = (ApiShiYsJyjcJgzy) JSON.Decode(reqJson);
        } catch (Exception e) {
            try {
                apiShiYsJyjcJgzy = JSONObject.parseObject(reqJson, ApiShiYsJyjcJgzy.class);
            } catch (Exception e2) {
                throw new RuntimeException("json数据格式有误!");
            }
        }
        return apiShiYsJyjcJgzy;
    }

    public static ApiShiYsJyjcCd saveCd(String json) {
        if (StringUtil.isEmpty(json)) {
            throw new RuntimeException("数据不能为空!");
        }
        ApiShiYsJyjcCd entity;
        try {
            entity = JSONObject.parseObject(json, ApiShiYsJyjcCd.class);
        } catch (Exception e) {
            throw new RuntimeException("json数据格式有误!");
        }
        return entity;
    }

    public static ApiShiYsJyjcRy checkRyxx(String reqJson) {
        if (StringUtil.isEmpty(reqJson)) {
            throw new RuntimeException("数据不能为空!");
        }
        ApiShiYsJyjcRy entity;
        try {
            entity = JSONObject.parseObject(reqJson, ApiShiYsJyjcRy.class);
            if (StringUtil.isEmpty(entity.getName())) {
                throw new RuntimeException("姓名不能为空!");
            }
            if (StringUtil.isEmpty(entity.getSex())) {
                throw new RuntimeException("性别不能为空!");
            }
            if (StringUtil.isEmpty(entity.getAge())) {
                throw new RuntimeException("年龄不能为空!");
            }
            if (StringUtil.isEmpty(entity.getEducation())) {
                throw new RuntimeException("文化程度不能为空!");
            }
            if (StringUtil.isEmpty(entity.getDocumentType())) {
                throw new RuntimeException("证件类型不能为空!");
            }
            if (StringUtil.isEmpty(entity.getIdCard())) {
                throw new RuntimeException("身份证号不能为空!");
            }
            if (StringUtil.isEmpty(entity.getPosition())) {
                throw new RuntimeException("职务不能为空!");
            }
            if (StringUtil.isEmpty(entity.getProfessional())) {
                throw new RuntimeException("专业不能为空!");
            }
            if (StringUtil.isEmpty(entity.getTechnicalFieldYear())) {
                throw new RuntimeException("从事本技术领域年限不能为空!");
            }
            if (StringUtil.isEmpty(entity.getDepartment())) {
                throw new RuntimeException("现在部门岗位不能为空!");
            }
            if (StringUtil.isEmpty(entity.getAddress())) {
                throw new RuntimeException("检验检测机构地址不能为空!");
            }
            if (StringUtil.isEmpty(entity.getSerialNumber())) {
                throw new RuntimeException("流水号不能为空!");
            }
        } catch (Exception e2) {
            throw new RuntimeException("json数据格式有误!");
        }
        return entity;
    }

    public static ApiShiYsJyjcQzr checkQzr(String reqJson) {
        if (StringUtil.isEmpty(reqJson)) {
            throw new RuntimeException("数据不能为空!");
        }
        ApiShiYsJyjcQzr entity;
        try {
            entity = JSONObject.parseObject(reqJson, ApiShiYsJyjcQzr.class);
            if (StringUtil.isEmpty(entity.getSerialNumber())) {
                throw new RuntimeException("流水号不能为空!");
            }

        } catch (Exception e2) {
            throw new RuntimeException("json数据格式有误!");
        }
        return entity;
    }

    public static BizApiZwcnBg checkZwcnBg(String reqJson) {
        if (StringUtil.isEmpty(reqJson)) {
            throw new RuntimeException("数据不能为空!");
        }
        BizApiZwcnBg entity;
        try {
            entity = JSONObject.parseObject(reqJson, BizApiZwcnBg.class);
            if (StringUtil.isEmpty(entity.getSerialNumber())) {
                throw new RuntimeException("流水号不能为空!");
            }
        } catch (Exception e2) {
            throw new RuntimeException("json数据格式有误!");
        }
        return entity;
    }

    public static BizApiZxsqb checkZxsqb(String reqJson) {
        if (StringUtil.isEmpty(reqJson)) {
            throw new RuntimeException("数据不能为空!");
        }
        BizApiZxsqb entity;
        try {
            entity = JSONObject.parseObject(reqJson, BizApiZxsqb.class);
            if (StringUtil.isEmpty(entity.getSerialNumber())) {
                throw new RuntimeException("流水号不能为空!");
            }
        } catch (Exception e2) {
            throw new RuntimeException("json数据格式有误!");
        }
        return entity;
    }

    public static ApiShiYsJyjcNl saveRow(String json) {
        if (StringUtil.isEmpty(json)) {
            throw new RuntimeException("数据不能为空!");
        }
        ApiShiYsJyjcNl entity;
        try {
            entity = JSONObject.parseObject(json, ApiShiYsJyjcNl.class);
        } catch (Exception e) {
            throw new RuntimeException("json数据格式有误!");
        }
        return entity;
    }

    public static ChangeApplyInfo changeApplyInfoSave(String json) {
        if (StringUtil.isEmpty(json)) {
            throw new RuntimeException("数据不能为空!");
        }
        ChangeApplyInfo entity;
        try {
            entity = JSONObject.parseObject(json, ChangeApplyInfo.class);
        } catch (Exception e) {
            throw new RuntimeException("json数据格式有误!");
        }
        return entity;
    }

    public static StandardChangeDetails standarDetailsSave(String json) {
        if (StringUtil.isEmpty(json)) {
            throw new RuntimeException("数据不能为空!");
        }
        StandardChangeDetails entity;
        try {
            entity = JSONObject.parseObject(json, StandardChangeDetails.class);
        } catch (Exception e) {
            throw new RuntimeException("json数据格式有误!");
        }
        return entity;
    }

    public static CancelNlDetails cancelNlDetailsSave(String json) {
        if (StringUtil.isEmpty(json)) {
            throw new RuntimeException("数据不能为空!");
        }
        CancelNlDetails entity;
        try {
            entity = JSONObject.parseObject(json, CancelNlDetails.class);
        } catch (Exception e) {
            throw new RuntimeException("json数据格式有误!");
        }
        return entity;
    }

    public static PersonChangeDetails personChangeDetailsSave(String json) {
        if (StringUtil.isEmpty(json)) {
            throw new RuntimeException("数据不能为空!");
        }
        PersonChangeDetails entity;
        try {
            entity = JSONObject.parseObject(json, PersonChangeDetails.class);
        } catch (Exception e) {
            throw new RuntimeException("json数据格式有误!");
        }
        return entity;
    }

    public static SqqzrChangeDetails sqqzrChangeDetailsSave(String json) {
        if (StringUtil.isEmpty(json)) {
            throw new RuntimeException("数据不能为空!");
        }
        SqqzrChangeDetails entity;
        try {
            entity = JSONObject.parseObject(json, SqqzrChangeDetails.class);
        } catch (Exception e) {
            throw new RuntimeException("json数据格式有误!");
        }
        return entity;
    }
}
