package com.yongjie.ZhiJianSbpt.dao.flowBase;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.model.Area;
import com.yongjie.ZhiJianSbpt.startFlow.model.YjFlowNodes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IYjFlowNodesDao extends BaseDao<Area> {
    public final static String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.dao.impl.YjFlowNodesDao";

    public ArrayList getNodeMcByFlowInstId(long flowId, long flowInstId);

    public ArrayList getNodeMo(long flowId, long flowInstId);

    public ArrayList getNodeMcByFlowInstIdOnEnd(long flowId, long flowInstId, String state);

    public ArrayList getBizIdByflowId(long flowId);

    public ArrayList getFlowNodesByNodeId(long flowId, String nodeId);

    //获得操作
    public ArrayList isOptionEnable(long flowId, String nodeId, String type, String sortField, String sortOrder);

    //获得活动实例Id
    public ArrayList getNodeInstList(long flowInstId, String nodeId, String state);

    public int updateCurrentState(String nodeInstId, int state);

    public int addNodeInst(long flowId, long flowInstId, String nodeId, String nodeName, String currentUser, int state);

    /**
     * 获取流程实例参与者
     *
     * @param flowId
     * @param flowInstId
     * @param nodeId
     * @param type
     * @return
     */
    public String getflowInstParticipant(long flowId, long flowInstId, String nodeId, String type);

    //增加工作项
    public int addWorkItem(long flowId, long flowInstId, String nodeInstId, String participant, String currentUser);

    public ArrayList getParameterList(long flowInstId, String paraName);

    public int insert_Parameter(long flowInstId, String paraname, String paravalue, String caor, Date caodate);

    public int deleteYjFlowInstPara(long flowInstId, String paraname, String paravalue);

    public ArrayList getYjFlow(String flowId);

    public long startYjFlow(String flowInstName, long flowId, long sqsId, String currentUser, long shenHeAreaId) throws Exception;

    public int addPara(String flowInstId, String paraName, String paraValue, String currentUser);

    public String getUserName(String userId);

    public int updateWorkItem(String flowInstId, String nodeInstId);

    public int addYjFlowInstLogWtqr(String flowInstId, String nodeInstId, String oprationName, String optype, String userName, String trueName);

    /**
     * 根据流程ID,查询流程所有节点 》 在用
     *
     * @param flowId
     */
    List<Map<String, Object>> getYjflownodesByFlowId(String flowId) throws Exception;

    YjFlowNodes selectFlowNodesByflowId(Long flowId, int startOrEnd);

    YjFlowNodes getYjFlowNodesByNodeId(String nodeId, long flowId);

    String getParticipants(String model, String ids, long flowId);

    String getPartiType(String model, String ids, long flowId);
}
