package com.yongjie.ZhiJianSbpt.bggl.action;

import com.alibaba.fastjson.JSONObject;
import com.yongjie.ZhiJianSbpt.action.base.BaseAction;
import com.yongjie.ZhiJianSbpt.bggl.model.*;
import com.yongjie.ZhiJianSbpt.bggl.service.*;
import com.yongjie.ZhiJianSbpt.utilities.HashmapAndEntityTransfer;
import com.yongjie.ZhiJianSbpt.utilities.R;
import com.yongjie.ZhiJianSbpt.utilities.RequestDataCheckUtil;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ChangeApplyInfoAction
 * @Date 2022/3/14 15:14
 * @Version 1.0
 */
@Controller("ChangeApplyInfoAction")
@Scope("prototype")
public class ChangeApplyInfoAction extends BaseAction<T> {

    @Resource(name = ChangeApplyInfoService.SERVICE_NAME)
    private ChangeApplyInfoService service;

    @Resource(name = StandardChangeDetailsService.SERVICE_NAME)
    private StandardChangeDetailsService standarDetailsService;

    @Resource(name = CancelNlDetailsService.SERVICE_NAME)
    private CancelNlDetailsService cancelNlDetailsService;

    @Resource(name = PersonChangeDetailsService.SERVICE_NAME)
    private PersonChangeDetailsService personChangeDetailsService;

    @Resource(name = SqqzrChangeDetailsService.SERVICE_NAME)
    private SqqzrChangeDetailsService sqqzrChangeDetailsService;

    public void changeApplyInfoInit() throws Exception {
        String resultJson;
        String serialNumber = req.getParameter("serialNumber");
        String changeType = req.getParameter("changeType");
        if (StringUtil.isNullOrEmpty(serialNumber)) {
            resultJson = R.error("查询失败！");
            response.getWriter().write(resultJson);
            return;
        }
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("serialNumber", serialNumber);
        reqMap.put("changeType", changeType);
        Map<String, Object> resultMap = service.queryBySerialNum(reqMap);
        resultJson = R.ok(resultMap);
        response.getWriter().write(resultJson);
    }

    public void changeApplyInfoSave() throws Exception {
        String resultJson;
        String serialNumber = req.getParameter("serialNumber");
        String reqJson = req.getParameter("data");

        if (StringUtil.isNullOrEmpty(serialNumber)) {
            resultJson = R.ok("保存失败，参数有误！");
            response.getWriter().write(resultJson);
            return;
        }

        ChangeApplyInfo entity = RequestDataCheckUtil.changeApplyInfoSave(reqJson);
        entity.setSerialNumber(serialNumber);
        entity.setCreateDate(new Date());
        entity.setApplyDate(new Date());
        entity.setUpdateDate(new Date());
        // 编辑、更新
        if (entity.getId() != null && entity.getId() > 0) {
            entity.setUpdateDate(new Date());
            service.update(entity);
        } else {
            service.save(entity);
        }

        resultJson = R.ok("操作成功！");
        response.getWriter().write(resultJson);
    }

    public void changeApplyInfoDetailsInit() throws Exception {
        String resultJson = "";
        String id = req.getParameter("id");
        String changeType = req.getParameter("changeType");
        if (StringUtil.isNullOrEmpty(id)) {
            resultJson = R.error("编辑失败！");
            response.getWriter().write(resultJson);
            return;
        }
        if ("11".equals(changeType)) {
            StandardChangeDetails entity = standarDetailsService.getById(Long.parseLong(id));
            resultJson = R.ok(entity);
        }

        if ("15".equals(changeType)) {
            CancelNlDetails entity = cancelNlDetailsService.getById(Long.parseLong(id));
            resultJson = R.ok(entity);
        }

        if ("16".equals(changeType)) {
            PersonChangeDetails entity = personChangeDetailsService.getById(Long.parseLong(id));
            resultJson = R.ok(entity);
        }

        if ("17".equals(changeType)) {
            SqqzrChangeDetails entity = sqqzrChangeDetailsService.getById(Long.parseLong(id));
            resultJson = R.ok(entity);
        }

        response.getWriter().write(resultJson);
    }

    public void changeApplyInfoDetailsSave() throws Exception {
        String resultJson;
        Long resultId = 0L;
        String serialNumber = req.getParameter("serialNumber");
        String reqJson = req.getParameter("row");
        String changeType = req.getParameter("changeType");

        if (StringUtil.isNullOrEmpty(serialNumber)) {
            resultJson = R.ok("保存失败，参数有误！");
            response.getWriter().write(resultJson);
            return;
        }

        if ("11".equals(changeType)) {
            StandardChangeDetails entity = RequestDataCheckUtil.standarDetailsSave(reqJson);
            entity.setSerialNumber(serialNumber);
            entity.setCreateDate(new Date());
            entity.setUpdateDate(new Date());
            // 编辑、更新
            if (entity.getId() != null && entity.getId() > 0) {
                StandardChangeDetails oldEntity = standarDetailsService.getById(entity.getId());
                HashMap<String, Object> map = JSONObject.parseObject(reqJson, HashMap.class);
                oldEntity = (StandardChangeDetails) HashmapAndEntityTransfer.hashmapTransferToEntity(oldEntity, map);
                entity.setUpdateDate(new Date());
                standarDetailsService.update(oldEntity);
            } else {
                standarDetailsService.save(entity);
            }
        }
        if ("15".equals(changeType)) {
            CancelNlDetails entity = RequestDataCheckUtil.cancelNlDetailsSave(reqJson);
            entity.setSerialNumber(serialNumber);
            entity.setCreateDate(new Date());
            entity.setUpdateDate(new Date());
            // 编辑、更新
            if (entity.getId() != null && entity.getId() > 0) {
                CancelNlDetails oldEntity = cancelNlDetailsService.getById(entity.getId());
                HashMap<String, Object> map = JSONObject.parseObject(reqJson, HashMap.class);
                oldEntity = (CancelNlDetails) HashmapAndEntityTransfer.hashmapTransferToEntity(oldEntity, map);
                entity.setUpdateDate(new Date());
                cancelNlDetailsService.update(oldEntity);
            } else {
                cancelNlDetailsService.save(entity);
            }
        }

        if ("16".equals(changeType)) {
            PersonChangeDetails entity = RequestDataCheckUtil.personChangeDetailsSave(reqJson);
            entity.setSerialNumber(serialNumber);
            entity.setCreateDate(new Date());
            entity.setUpdateDate(new Date());
            // 编辑、更新
            if (entity.getId() != null && entity.getId() > 0) {
                PersonChangeDetails oldEntity = personChangeDetailsService.getById(entity.getId());
                HashMap<String, Object> map = JSONObject.parseObject(reqJson, HashMap.class);
                oldEntity = (PersonChangeDetails) HashmapAndEntityTransfer.hashmapTransferToEntity(oldEntity, map);
                entity.setUpdateDate(new Date());
                personChangeDetailsService.update(oldEntity);
            } else {
                personChangeDetailsService.save(entity);
            }
        }

        if ("17".equals(changeType)) {
            SqqzrChangeDetails entity = RequestDataCheckUtil.sqqzrChangeDetailsSave(reqJson);
            entity.setSerialNumber(serialNumber);
            entity.setCreateDate(new Date());
            entity.setUpdateDate(new Date());
            // 编辑、更新
            if (entity.getId() != null && entity.getId() > 0) {
                SqqzrChangeDetails oldEntity = sqqzrChangeDetailsService.getById(entity.getId());
                HashMap<String, Object> map = JSONObject.parseObject(reqJson, HashMap.class);
                oldEntity = (SqqzrChangeDetails) HashmapAndEntityTransfer.hashmapTransferToEntity(oldEntity, map);
                oldEntity.setXgsm(entity.getXgsm());
                oldEntity.setGzjl(entity.getGzjl());
                oldEntity.setGzjl(entity.getGzjl());
                oldEntity.setPeixun(entity.getPeixun());
                oldEntity.setFax(entity.getFax());
                oldEntity.setEmail(entity.getEmail());
                oldEntity.setUpdateDate(new Date());
                sqqzrChangeDetailsService.update(oldEntity);
            } else {
                sqqzrChangeDetailsService.save(entity);
            }
            resultId = entity.getId();
        }

        resultJson = R.ok(resultId, "操作成功！");
        response.getWriter().write(resultJson);
    }

    public void changeApplyInfoDetailsList() throws Exception {
        String resultJson;
        String serialNumber = req.getParameter("serialNumber");
        String changeType = req.getParameter("changeType");
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("serialNumber", serialNumber);
        Map<String, Object> result = new HashMap<>();
        if ("11".equals(changeType)) {
            result = standarDetailsService.queryListBySerialNum(reqMap);
        }
        if ("15".equals(changeType)) {
            result = cancelNlDetailsService.queryListBySerialNum(reqMap);
        }

        if ("16".equals(changeType)) {
            result = personChangeDetailsService.queryListBySerialNum(reqMap);
        }

        if ("17".equals(changeType)) {
            result = sqqzrChangeDetailsService.queryListBySerialNum(reqMap);
        }
        resultJson = R.page(result);
        response.getWriter().write(resultJson);
    }

    public void changeApplyInfoDetailsDelete() throws Exception {
        String resultJson;
        String ids = req.getParameter("ids");
        String changeType = req.getParameter("changeType");
        if (!StringUtil.isNullOrEmpty(ids)) {
            String[] split = ids.split(",");
            for (String str : split) {
                if ("11".equals(changeType)) {
                    standarDetailsService.delete(Long.parseLong(str));
                }
                if ("15".equals(changeType)) {
                    cancelNlDetailsService.delete(Long.parseLong(str));
                }

                if ("16".equals(changeType)) {
                    personChangeDetailsService.delete(Long.parseLong(str));
                }

                if ("17".equals(changeType)) {
                    sqqzrChangeDetailsService.delete(Long.parseLong(str));
                }
            }
        }
        resultJson = R.ok();
        response.getWriter().write(resultJson);
    }

    public void nlDetailsByzsbh() throws Exception {
        String resultJson = "";
        String zzrdzsbh = req.getParameter("zzrdzsbh");
        String changeType = req.getParameter("changeType");
        if (!StringUtil.isNullOrEmpty(zzrdzsbh)) {
            Map<String, Object> map = new HashMap<>();
            map.put("zzrdzsbh", zzrdzsbh);
            map.put("changeType", changeType);
            Map<String, Object> resultMap = service.nlDetailsByzsbh(map);
            resultJson = R.ok(resultMap.get(R.DATA_NAME));
        }
        response.getWriter().write(resultJson);
    }

    public void saveStandardchangeCancelnlDetails() throws Exception {
        String resultJson = "";
        String changeType = req.getParameter("changeType");
        String serialNumber = req.getParameter("serialNumber");
        String data = req.getParameter("data");
        List list = JSONObject.parseObject(data, List.class);
        if (StringUtil.isNullOrEmpty(changeType)) {
            resultJson = R.ok("未获取到变更类型,请在封面信息重新保存！");
            response.getWriter().write(resultJson);
            return;
        }
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = (Map<String, Object>) list.get(i);
                if ("11".equals(changeType)) {
                    StandardChangeDetails standardEntity = new StandardChangeDetails();
                    standardEntity.setSortNumbg((String) map.get("SORTINGNUMBER"));
                    standardEntity.setJgobjectnlId(new BigDecimal(String.valueOf(map.get("ID"))).longValue());
                    standardEntity.setZsbh((String) map.get("ZSBH"));
                    standardEntity.setSerialNumber(serialNumber);
                    standardEntity.setSortNum((String) map.get("BIGNUMBER"));
                    standardEntity.setCategory((String) map.get("EJFL"));
                    standardEntity.setStandardNameAndNumOld((String) map.get("YJBZ"));
                    standardEntity.setLimitRange((String) map.get("LIMITS"));
                    standardEntity.setUpdateDate(new Date());
                    standardEntity.setCreateDate(new Date());

                    standarDetailsService.save(standardEntity);
                }

                if ("15".equals(changeType)) {
                    CancelNlDetails cancelNlDetails = new CancelNlDetails();
                    cancelNlDetails.setSortNumbg((String) map.get("SORTINGNUMBER"));
                    cancelNlDetails.setJgobjectnlId(new BigDecimal(String.valueOf(map.get("ID"))).longValue());
                    cancelNlDetails.setCmaPermitNum((String) map.get("ZSBH"));
                    cancelNlDetails.setSerialNumber(serialNumber);
                    cancelNlDetails.setProductSortNum((String) map.get("BIGNUMBER"));
                    cancelNlDetails.setCategory((String) map.get("EJFL"));
                    cancelNlDetails.setProductName((String) map.get("PRODUCTNAME"));
                    cancelNlDetails.setStandardNameAndNum((String) map.get("YJBZ"));
                    cancelNlDetails.setLaboratoryPlace((String) map.get("SITENAME"));
                    cancelNlDetails.setLimitRange((String) map.get("LIMITS"));
                    cancelNlDetails.setUpdateDate(new Date());
                    cancelNlDetails.setCreateDate(new Date());

                    cancelNlDetailsService.save(cancelNlDetails);
                }

                if ("16".equals(changeType)) {
                    PersonChangeDetails personEntity = new PersonChangeDetails();
                    personEntity.setJyjcryId(new BigDecimal(String.valueOf(map.get("ID"))).longValue());
                    personEntity.setSerialNumber(serialNumber);
                    personEntity.setSfzh((String) map.get("SFZH"));
                    personEntity.setZsbh((String) map.get("ZSBH"));
                    personEntity.setUpdateDate(new Date());
                    personEntity.setCreateDate(new Date());

                    personChangeDetailsService.save(personEntity);
                }
            }
            resultJson = R.ok("操作成功！");
        }
        response.getWriter().write(resultJson);
    }

}
