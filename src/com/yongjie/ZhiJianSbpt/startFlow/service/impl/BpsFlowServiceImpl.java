package com.yongjie.ZhiJianSbpt.startFlow.service.impl;


import com.yongjie.ZhiJianSbpt.model.BizApiShiYsJyjc;
import com.yongjie.ZhiJianSbpt.model.flowBase.YjFlow;
import com.yongjie.ZhiJianSbpt.service.BizApiShiysjyjcService;
import com.yongjie.ZhiJianSbpt.startFlow.service.BpsFlowService;
import com.yongjie.ZhiJianSbpt.startFlow.service.IBpsService;
import com.yongjie.ZhiJianSbpt.startFlow.service.IYjFlowService;
import com.yongjie.ZhiJianSbpt.startFlow.util.BpsStatic;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

@Transactional(readOnly = true)
@Service(BpsFlowService.SERVICE_NAME)
public class BpsFlowServiceImpl implements BpsFlowService {

    @Resource(name = BizApiShiysjyjcService.SERVICE_NAME)
    private BizApiShiysjyjcService bizApiService;

    @Resource(name = IYjFlowService.SERVICE_NAME)
    private IYjFlowService yjFlowService;

    @Resource(name = IBpsService.SERVICE_NAME)
    private IBpsService bpsService;

    public static void flag(String flag) throws Exception {
        if ("1".equals(flag)) {
            throw new RuntimeException("材料预审核尚未通过!");
        }
        if ("2".equals(flag)) {
            throw new RuntimeException("当前数据在业务受理中,您没有操作权限!");
        }
        if ("3".equals(flag) || "4".equals(flag)) {
            throw new RuntimeException("当前在退回环节,请通过上报方式受理!");
        }
        if ("5".equals(flag)) {
            throw new RuntimeException("当前数据在挂起环节,待激活!");
        }
        if ("6".equals(flag)) {
            throw new RuntimeException("当前数据已办结!");
        }
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public Long zzrdStartFlow(Map<String, Object> map, Map<String, Object> fileMap) throws Exception {
        BizApiShiYsJyjc api = bizApiService.getBizShiYsJyjcBySerialNumber(map.get(BpsStatic.BPS_SERIALNUMBER) + "");
        if (StringUtil.isEmpty(api)) {
            throw new RuntimeException("未发现流水号对应的相关数据!");
        }
        // 状态 1预审核 2环节 3预审核退回4环节退回 5待激活 6归档7上报端受理环节
        // 判断流程状态
        flag(api.getFlag() + "");

        YjFlow dbFlow = yjFlowService.getFlowById((Long) map.get(BpsStatic.BPS_FLOWID));
        if (StringUtil.isEmpty(dbFlow)) {
            throw new RuntimeException("未发现相关流程!");
        }

        Long flowInstId;
        // 办理意见 1同意 -1 不同意
        String suggesTion = map.get(BpsStatic.BPS_SUGGESTION) + "";
        String slYesOrNo = (String) map.get("slYesOrNo");
        try {
            flowInstId = bpsService.statrtNewYjflow(dbFlow.getFlowName(), dbFlow.getFlowId(), api.getId(), map.get(BpsStatic.BPS_QYMC) + "", api.getCheckAreaId(), suggesTion, slYesOrNo);
        } catch (Exception e) {
            throw new RuntimeException("流程启动失败!");
        }
        return flowInstId;
    }
}
