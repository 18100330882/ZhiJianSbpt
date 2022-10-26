package com.yongjie.ZhiJianSbpt.startFlow.action;

import com.yongjie.ZhiJianSbpt.action.base.BaseAction;
import com.yongjie.ZhiJianSbpt.model.flowBase.YjFlow;
import com.yongjie.ZhiJianSbpt.startFlow.model.AccountXzsp;
import com.yongjie.ZhiJianSbpt.startFlow.model.CancellationTable;
import com.yongjie.ZhiJianSbpt.startFlow.service.CancellationTableService;
import com.yongjie.ZhiJianSbpt.startFlow.service.IBpsService;
import com.yongjie.ZhiJianSbpt.startFlow.service.IYjFlowService;
import com.yongjie.ZhiJianSbpt.startFlow.service.UserService;
import com.yongjie.ZhiJianSbpt.utilities.R;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import com.yongjie.ZhiJianSbpt.utilities.UuidUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@SuppressWarnings("serial")
@Controller("CancellationTableAction")
@Scope("prototype")
public class CancellationTableAction extends BaseAction<CancellationTable> {

    @Resource(name = CancellationTableService.SERVICE_NAME)
    private CancellationTableService service;

    @Resource(name = IYjFlowService.SERVICE_NAME)
    private IYjFlowService yjFlowService;

    @Resource(name = UserService.SERVICE_NAME)
    private UserService useService;

    @Resource(name = IBpsService.SERVICE_NAME)
    private IBpsService bpsService;

    /**
     * 材料预审核：预审通过
     *
     * @throws Exception
     */
    public void confirmAcceptance() throws Exception {
        String resultJson = null;
        String sqsIdStr = req.getParameter("id");
        String serialNumber = req.getParameter("serialNumber");
        String flowIdStr = req.getParameter("flowId");
        String shRenAreaId = req.getParameter("shRenAreaId");
        String qymc = req.getParameter("qymc");

        if (StringUtil.isEmpty(sqsIdStr)) {
            resultJson = R.error("上报受理失败!");
            response.getWriter().print(resultJson);
            return;
        }

        String flowName = "";
        YjFlow yjflow = yjFlowService.getFlowById(Long.parseLong(flowIdStr));
        if (yjflow != null) {
            flowName = yjflow.getFlowName();
        }
        //启动流程
        long flowInstId = bpsService.startYjFlow(qymc + "(" + flowName + ")", Long.parseLong(flowIdStr), Long.parseLong(sqsIdStr), userName, Long.parseLong(shRenAreaId));

        //修改申请书状态
        CancellationTable entity = service.getCancellationTable(serialNumber);//根据流水号查询，可以不用传flag
        if (entity != null) {
            entity.setFlag("2"); // 状态修改为 2
            entity.setUpdateDate(new Date()); // 更新时间
            entity.setNodeDate(new Date());// 环节时间
            entity.setFlowInstId(flowInstId);
            entity.setFlowId(60L);
            service.update(entity);
            resultJson = R.ok("上报成功！请耐心等待受理结果！");
        } else {
            resultJson = R.error("该数据不存在或已被更新!");
        }

        response.getWriter().print(resultJson);
    }
}
