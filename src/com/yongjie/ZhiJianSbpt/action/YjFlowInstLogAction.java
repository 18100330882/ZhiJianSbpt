package com.yongjie.ZhiJianSbpt.action;

import com.yongjie.ZhiJianSbpt.action.base.BaseAction;
import com.yongjie.ZhiJianSbpt.model.YjFlowInstLog;
import com.yongjie.ZhiJianSbpt.service.YjFlowInstLogService;
import com.yongjie.ZhiJianSbpt.utilities.JSON;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;

@Controller("YjFlowInstLogAction")
@Scope("prototype")//每次访问此action都会创建实例，确保数据安全
public class YjFlowInstLogAction extends BaseAction<YjFlowInstLog> {

    @Resource(name = YjFlowInstLogService.SERVICE_NAME)
    private YjFlowInstLogService service;

    public void getFlowInstLogList() throws Exception {
        String flowInstIds = req.getParameter("flowInstId") == null ? "" : req.getParameter("flowInstId");
        String serialNumber = req.getParameter("serialNumber") == null ? "" : req.getParameter("serialNumber");
        long flowInstId = 0;
        if (!StringUtil.isNullOrEmpty(flowInstIds)) {
            flowInstId = Long.parseLong(flowInstIds);
        }
        //查询条件
        String nameFilter = req.getParameter("key");
        //分页
        int pageIndex = Integer.parseInt(req.getParameter("pageIndex"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        //字段排序
        String sortField = req.getParameter("sortField");
        String sortOrder = req.getParameter("sortOrder");
        HashMap list = service.getFlowInstLogList(nameFilter, serialNumber, pageIndex, pageSize, sortField, sortOrder, flowInstId);
        //将对象封装json语句
        String json = JSON.Encode(list);
        //向页面输出json语句
        response.getWriter().write(json);
    }


}
