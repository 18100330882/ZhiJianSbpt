package com.yongjie.ZhiJianSbpt.startFlow.action;

import com.alibaba.fastjson.JSONObject;
import com.yongjie.ZhiJianSbpt.action.base.BaseAction;
import com.yongjie.ZhiJianSbpt.model.BizApiShiYsJyjc;
import com.yongjie.ZhiJianSbpt.model.flowBase.YjFlow;
import com.yongjie.ZhiJianSbpt.module.util.R;
import com.yongjie.ZhiJianSbpt.service.BizApiShiysjyjcService;
import com.yongjie.ZhiJianSbpt.startFlow.model.ApiFlow;
import com.yongjie.ZhiJianSbpt.startFlow.service.ApiFlowService;
import com.yongjie.ZhiJianSbpt.startFlow.service.BpsFlowService;
import com.yongjie.ZhiJianSbpt.startFlow.util.BpsFlowDataCheckUtil;
import com.yongjie.ZhiJianSbpt.startFlow.util.BpsStatic;
import com.yongjie.ZhiJianSbpt.utilities.JSON;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
@Controller("BpsFlowAction")
@Scope("prototype")
public class BpsFlowAction extends BaseAction<YjFlow> {

    @Resource(name = BpsFlowService.SERVICE_NAME)
    private BpsFlowService bpsFlowService;

    /**
     * 流程参数配置表
     */
    @Resource(name = ApiFlowService.SERVICE_NAME)
    private ApiFlowService apiFlowService;

    /**
     * 实验室检验检测资质认定
     */
    @Resource(name = BizApiShiysjyjcService.SERVICE_NAME)
    private BizApiShiysjyjcService apiShiysjyjcService;

    public void startFlow() throws Exception {
        String resultJson = null;
        try {
            String json = req.getParameter("handleInfo");
            // 数据校验
            HashMap<String, Object> map = BpsFlowDataCheckUtil.check(json);
            String fileJson = req.getParameter("handleFile");
            HashMap<String, Object> fileMap = (HashMap<String, Object>) JSONObject.parseObject(fileJson, Map.class);

            ApiFlow dbApiFlow = apiFlowService.getApiFlowByUnid(map.get(BpsStatic.BPS_UUID) + "");

            if (StringUtil.isEmpty(dbApiFlow)) {
                throw new RuntimeException(BpsStatic.BPS_UUID + "值无效,请核准!");
            }
            if ("2".equals(dbApiFlow.getState())) {
                throw new RuntimeException("当前流程已停用!");
            }
            map.put(BpsStatic.BPS_FLOWID, dbApiFlow.getFlowId());

            Long flowInstId = bpsFlowService.zzrdStartFlow(map, fileMap);
            if ("4".equals(dbApiFlow.getFlowId() + "") || "6".equals(String.valueOf(dbApiFlow.getFlowId()))) {
                if (!StringUtil.isEmpty(flowInstId)) {
                    BizApiShiYsJyjc api = apiShiysjyjcService.getBizShiYsJyjcBySerialNumber(map.get(BpsStatic.BPS_SERIALNUMBER) + "");
                    api.setFlowInstId(flowInstId); // 设置流程实例ID
                    api.setFlag(2);
                    // 不予受理决定：set Flag = 6 and nodeId = end
                    if (!StringUtil.isEmpty(map.get("slYesOrNo")) && map.get("slYesOrNo").equals("1")) {
                        api.setFlag(6);
                        api.setNodeId("end");
                    }
                    apiShiysjyjcService.update(api);
                }
                resultJson = R.ok("上报受理成功，请耐心等待受理结果！");
            } else {
                resultJson = R.ok("上报受理异常!");
            }
        } catch (Exception e) {
            resultJson = R.error(e.getMessage());
        }
        response.getWriter().print(resultJson);
    }
}
