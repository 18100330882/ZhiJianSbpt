package com.yongjie.ZhiJianSbpt.action.flowBase;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yongjie.ZhiJianSbpt.model.Area;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@SuppressWarnings("serial")
@Controller("YjFlowNodesAction")
@Scope("prototype")//每次访问此action都会创建实例，确保数据安全
public class YjFlowNodesAction extends ActionSupport implements ModelDriven<Area> {
    @Override
    public String execute() throws Exception {
        // TODO Auto-generated method stub
        return super.execute();
    }

    @Override
    public Area getModel() {
        // TODO Auto-generated method stub
        return null;
    }

    public String getNodeMcByFlowInstId() throws Exception {
        return "getNodeMcByFlowInstId";
    }

    public String getBizIdByflowId() throws Exception {
        return "getBizIdByflowId";
    }

    public String getFlowNodesByNodeId() throws Exception {
        return "getFlowNodesByNodeId";
    }

    public String getNodeMcByFlowInstIdOnEnd() throws Exception {
        return "getNodeMcByFlowInstIdOnEnd";
    }

    public String getNodeMo() throws Exception {
        return "getNodeMo";
    }
}
