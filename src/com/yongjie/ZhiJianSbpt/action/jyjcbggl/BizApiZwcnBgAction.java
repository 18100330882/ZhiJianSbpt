package com.yongjie.ZhiJianSbpt.action.jyjcbggl;

import com.alibaba.fastjson.JSONObject;
import com.yongjie.ZhiJianSbpt.action.base.BaseAction;
import com.yongjie.ZhiJianSbpt.bggl.model.ChangeApplyInfo;
import com.yongjie.ZhiJianSbpt.bggl.service.*;
import com.yongjie.ZhiJianSbpt.model.Area;
import com.yongjie.ZhiJianSbpt.model.flowBase.YjFlow;
import com.yongjie.ZhiJianSbpt.model.jyjcbggl.BizApiZwcnBg;
import com.yongjie.ZhiJianSbpt.service.*;
import com.yongjie.ZhiJianSbpt.service.jyjcbggl.BizApiZwcnBgService;
import com.yongjie.ZhiJianSbpt.service.shiYsJyjc.ApiFileService;
import com.yongjie.ZhiJianSbpt.startFlow.model.AccountXzsp;
import com.yongjie.ZhiJianSbpt.startFlow.service.IBpsService;
import com.yongjie.ZhiJianSbpt.startFlow.service.IYjFlowService;
import com.yongjie.ZhiJianSbpt.utilities.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("BizApiZwcnBgAction")
@Scope("prototype")
public class BizApiZwcnBgAction extends BaseAction<BizApiZwcnBg> {

    @Resource(name = BizApiZwcnBgService.SERVICE_NAME)
    private BizApiZwcnBgService service;

    @Resource(name = ApiFileService.SERVICE_NAME)
    private ApiFileService fileService;

    @Resource(name = IAreaService.SERVICE_NAME)
    private IAreaService areaService;

    @Resource(name = ChangeApplyInfoService.SERVICE_NAME)
    private ChangeApplyInfoService changeApplyInfoService;

    @Resource(name = CancelNlDetailsService.SERVICE_NAME)
    private CancelNlDetailsService cancelNlDetailsService;

    @Resource(name = PersonChangeDetailsService.SERVICE_NAME)
    private PersonChangeDetailsService personChangeDetailsService;

    @Resource(name = SqqzrChangeDetailsService.SERVICE_NAME)
    private SqqzrChangeDetailsService sqqzrChangeDetailsService;

    @Resource(name = StandardChangeDetailsService.SERVICE_NAME)
    private StandardChangeDetailsService standardChangeDetailsService;

    @Resource(name = IYjFlowService.SERVICE_NAME)
    private IYjFlowService yjFlowService;

    @Resource(name = IBpsService.SERVICE_NAME)
    private IBpsService bpsService;

    /**
     * ??????????????????
     */
    public void save() throws Exception {
        String resultJson = "";
        String reqJson = req.getParameter("data");
        Map parMap = new HashMap();
        if (StringUtil.isNullOrEmpty(reqJson)) {
            throw new RuntimeException("??????????????????!");
        }
        if (StringUtil.isEmpty(userName)) {
            resultJson = R.error("?????????????????????,???????????????!");
            response.getWriter().print(resultJson);
            return;
        }
        try {
            HashMap<String, Object> map = JSONObject.parseObject(reqJson, HashMap.class);
            if (StringUtil.isNullOrEmpty(map)) {
                return;
            }
            BizApiZwcnBg oldEntity = service.queryBySerialNumber(map.get("serialNumber") == null ? null : map.get("serialNumber").toString());
            if (StringUtil.isNullOrEmpty(oldEntity)) {
                BizApiZwcnBg entity = (BizApiZwcnBg) HashmapAndEntityTransfer.hashmapTransferToEntity(new BizApiZwcnBg(), map);
                Area areaEntity = areaService.getById(entity.getAreaId());
                Area checkAreaEntity = areaService.getById(entity.getCheckAreaId());
                String areaName = areaEntity.getAreaName();
                if (!StringUtil.isNullOrEmpty(entity.getAreaId())) {
                    entity.setAreaName(areaName);
                    entity.setCheckAreaName(checkAreaEntity.getAreaName());
                }
                if (!StringUtil.isNullOrEmpty(entity.getCheckAreaId())) {
                    entity.setCheckAreaName(checkAreaEntity.getAreaName());
                }
                entity.setFlowId(Long.valueOf("28"));
                entity.setFlag("0");
                entity.setSqrq(new Date());
                entity.setCreateDate(new Date());
                entity.setUpdateDate(new Date());
                entity.setCreateUser(userName);
                parMap.put("serialNumber", entity.getSerialNumber());
                parMap.put("changeType", entity.getState());
                service.save(entity);
            } else {
                oldEntity = (BizApiZwcnBg) HashmapAndEntityTransfer.hashmapTransferToEntity(oldEntity, map);
                parMap.put("serialNumber", oldEntity.getSerialNumber());
                parMap.put("changeType", oldEntity.getState());
                service.update(oldEntity);
            }
            Map<String, Object> changeInfoMap = changeApplyInfoService.queryBySerialNum(parMap);
            if (Integer.valueOf(changeInfoMap.get("total").toString()) > 0) {
                ChangeApplyInfo changeApplyInfo = (ChangeApplyInfo) changeInfoMap.get(R.DATA_NAME);
                changeApplyInfo.setChangeType(parMap.get("changeType").toString());
                changeApplyInfoService.update(changeApplyInfo);
            }
            resultJson = R.ok("???????????????");
        } catch (Exception e) {
            resultJson = R.error(e.getMessage());
        }
        response.getWriter().print(resultJson);
    }

    /**
     * ???????????? ??????
     *
     * @throws Exception
     */
    public void getList() throws Exception {
        String resultJson = "";
        if (StringUtil.isEmpty(userName)) {
            resultJson = R.error("?????????????????????,???????????????!");
            response.getWriter().print(resultJson);
            return;
        }
        // ??????
        String jgmc = req.getParameter("keyJgmc");
        String flowId = req.getParameter("flowId");
        String sortField = req.getParameter("sortField");
        String sortOrder = req.getParameter("sortOrder");
        Map<Object, Object> reqMap = new HashMap<>();
        reqMap.put("pageIndex", pageIndex);
        reqMap.put("pageSize", pageSize);
        reqMap.put("sortField", sortField);
        reqMap.put("sortOrder", sortOrder);

        reqMap.put("jgmc", jgmc);
        reqMap.put("flowId", flowId);
        reqMap.put("userName", userName);


        try {
            Map<String, Object> listPage = service.getList(reqMap);
            resultJson = R.page(listPage);
        } catch (Exception e) {
            resultJson = R.error("????????????", e);
        }
        response.getWriter().print(resultJson);
    }

    /**
     * ???????????????
     *
     * @throws Exception
     */
    public void initLoad() throws Exception {
        String resultJson = "";
        if (StringUtil.isEmpty(userName)) {
            resultJson = R.error("?????????????????????,???????????????!");
            response.getWriter().print(resultJson);
            return;
        }
        String serialNumber = req.getParameter("serialNumber");
        if (StringUtil.isNullOrEmpty(serialNumber)) {
            resultJson = R.error("????????????!????????????????????????");
            response.getWriter().print(resultJson);
            return;
        }
        try {
            BizApiZwcnBg entity = service.queryBySerialNumber(serialNumber);
            if (entity == null) {
                resultJson = R.error("??????????????????????????????????????????????????????????????????????????????????????????????????????");
            } else {
                resultJson = R.ok(entity);
            }
        } catch (Exception e) {
            resultJson = R.error("????????????!????????????????????????", e);
        }
        response.getWriter().print(resultJson);
    }


    /**
     * ???????????????????????????
     *
     * @throws Exception
     */
    public void delete() throws Exception {
        //????????????
        String idStr = req.getParameter("idResult");
        String serialNumber = req.getParameter("serialNumber");
        //????????????
        if (StringUtil.isNullOrEmpty(idStr) || StringUtil.isNullOrEmpty(serialNumber)) {
            return;
        }
        String[] ids = idStr.split(",");
        for (int i = 0, l = ids.length; i < l; i++) {

            /*???????????????*/
            fileService.deleteBatchBySerialNumber(serialNumber);

            /*??????????????????*/
            String id = ids[i];
            BizApiZwcnBg entity = service.queryById(Long.parseLong(id));
            // ?????? ???????????? ?????????
            changeApplyInfoService.deleteBySerialNum(entity.getSerialNumber());
            if ("11".equals(entity.getState())) {
                standardChangeDetailsService.deleteBySerialNum(entity.getSerialNumber());
            }
            if ("15".equals(entity.getState())) {
                cancelNlDetailsService.deleteBySerialNum(entity.getSerialNumber());
            }
            if ("17".equals(entity.getState())) {
                personChangeDetailsService.deleteBySerialNum(entity.getSerialNumber());
            }
            if ("18".equals(entity.getState())) {
                sqqzrChangeDetailsService.deleteBySerialNum(entity.getSerialNumber());
            }
            //??????ID????????????
            service.delete(Long.parseLong(id));
        }
        response.getWriter().write("????????????");

    }

    /**
     * ??????
     *
     * @throws Exception
     */
    public void shangBao() throws Exception {
        String resultJson = null;
        if (!StringUtil.isEmpty(userName)) {
            String serialNumber = req.getParameter("serialNumber");
            String flag = req.getParameter("flag") == null ? "" : req.getParameter("flag");
            BizApiZwcnBg entity = service.queryBySerialNumber(serialNumber);
            if (!StringUtil.isNullOrEmpty(entity)) {
                if (entity.getFlag().equals("4")) {
                    flag = "5";
                }
                if (entity.getFlag().equals("3")) {
                    flag = "1";
                }
                entity.setFlag(flag);
                service.update(entity);
            }
            resultJson = R.error("?????????????????????,???????????????!");

            response.getWriter().print(resultJson);
        }
    }

    public void confirmAcceptance() throws Exception {
        String resultJson = null;
        String sqsIdStr = req.getParameter("id");
        String serialNumber = req.getParameter("serialNumber");
        String flowIdStr = req.getParameter("flowId");
        String shRenAreaId = req.getParameter("shRenAreaId");
        String qymc = req.getParameter("qymc");

        if (StringUtil.isEmpty(sqsIdStr)) {
            resultJson = R.error("??????????????????!");
            response.getWriter().print(resultJson);
            return;
        } else {
            String flowName = "";
            YjFlow yjflow = yjFlowService.getFlowById(Long.parseLong(flowIdStr));
            if (yjflow != null) {
                flowName = yjflow.getFlowName();
            }
            //????????????
            long flowInstId = bpsService.startYjFlow(qymc + "(" + flowName + ")", Long.parseLong(flowIdStr), Long.parseLong(sqsIdStr), userName, Long.parseLong(shRenAreaId));
            System.out.println(flowInstId + "---------------------------------flowInstId-----------------------");

            //?????????????????????
            if (flowInstId > 0) {
                BizApiZwcnBg bizModel = service.getBizApiZwcnBg(serialNumber, "");//???????????????????????????????????????flag
                if (!StringUtil.isEmpty(bizModel)) {
                    bizModel.setFlag("2"); // ??????????????? 2
                    bizModel.setUpdateDate(new Date()); // ????????????
                    bizModel.setNodeDate(new Date());// ????????????
                    bizModel.setFlowInstId(flowInstId);
                    bizModel.setFlowId(28L);
                    service.update(bizModel);
                    resultJson = R.ok("??????????????????????????????????????????!");
                }
            } else {
                resultJson = R.error("??????????????????!");
            }
        }
        response.getWriter().print(resultJson);
    }

    /**
     * ?????? ??????-??????
     */
    public void report() throws Exception {
        String resultJson = R.error("????????????,???????????????!");
        String serialNumber = req.getParameter("serialNumber");
        if (StringUtil.isNullOrEmptyZf(userName) || StringUtil.isNullOrEmptyZf(serialNumber)) {
            response.getWriter().print(resultJson);
            return;
        }

        HashMap<Object, Object> reqMap = new HashMap<>();
        reqMap.put("serialNumber", serialNumber);
        HashMap<String, Object> fileMap = fileService.queryListBySerialNumber(reqMap);
        List fileList = (List) fileMap.get(R.DATA_NAME);
        if (fileList == null || fileList.size() == 0) {
            resultJson = R.error("????????????????????????");
            response.getWriter().print(resultJson);
            return;
        }

        String flag = "2";
        BizApiZwcnBg bgEntity = service.queryBySerialNumber(serialNumber);
        if (StringUtil.isNullOrEmptyZf(bgEntity) || StringUtil.isNullOrEmptyZf(bgEntity.getId()) || bgEntity.getId() == 0) {
            resultJson = R.error("???????????????");
            response.getWriter().print(resultJson);
            return;
        }
        if ("4".equals(bgEntity.getFlag())) {
            flag = "5";
        }
        if ("3".equals(bgEntity.getFlag())) {
            flag = "2";
        }

        // ??? ??????????????? ??? ???????????? - ????????????
        Map<String, Object> map = new HashMap<>();
        map.put("serialNumber", serialNumber);
        map.put("changeType", bgEntity.getState());
        Map<String, Object> applyInfoMap = changeApplyInfoService.queryBySerialNum(map);
        ChangeApplyInfo applyInfo = (ChangeApplyInfo) applyInfoMap.get(R.DATA_NAME);
        if (StringUtil.isNullOrEmptyZf(applyInfo) || StringUtil.isNullOrEmptyZf(applyInfo.getId()) || applyInfo.getId() == 0) {
            resultJson = R.error("???????????????");
            response.getWriter().print(resultJson);
            return;
        }
        // ?????? ???????????????
        applyInfo.setApplyDate(new Date());
        changeApplyInfoService.update(applyInfo);

        // ?????? flag ???= 0   ???= 8  ?????? ????????????
        long flowInstId = 0L;
        if (StringUtil.isNullOrEmptyZf(bgEntity.getFlag()) || "0".equals(bgEntity.getFlag()) || "8".equals(bgEntity.getFlag())) {
            // ???????????????----> ??????
            YjFlow yjflow = yjFlowService.getFlowById(bgEntity.getFlowId());
            if (StringUtil.isNullOrEmptyZf(yjflow)) {
                resultJson = R.error("???????????????");
                response.getWriter().print(resultJson);
                return;
            }
            // ????????????
            flowInstId = bpsService.startYjFlow(bgEntity.getJgmc() + "(" + yjflow.getFlowName() + ")", bgEntity.getFlowId(), bgEntity.getId(), userName, bgEntity.getCheckAreaId());
            bgEntity = service.queryById(bgEntity.getId());
            if (flowInstId > 0) {
                bgEntity.setFlowInstId(flowInstId);
            }
        }

        //?????????????????????
        bgEntity.setFlag(flag);
        bgEntity.setSqrq(new Date());
        bgEntity.setUpdateDate(new Date());
        bgEntity.setNodeDate(new Date());
        service.update(bgEntity);
        String areaName = areaService.getAreaName(bgEntity.getAreaId());
        resultJson = R.ok("????????????" + areaName + "?????????????????????????????????????????????????????????!");

        response.getWriter().print(resultJson);
    }
}
