package com.yongjie.ZhiJianSbpt.service.flowBase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IYjFlowNodesService {
    public final static String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.service.flowBase.impl.YjFlowNodesService";

    public ArrayList getNodeMcByFlowInstId(long flowId, long flowInstId);

    public ArrayList getNodeMo(long flowId, long flowInstId);

    public ArrayList getNodeMcByFlowInstIdOnEnd(long flowId, long flowInstId, String state);

    public ArrayList getBizIdByflowId(long flowId);

    /**
     * 获得某节点的信息
     *
     * @param flowId
     * @param nodeId
     * @return
     */
    public ArrayList getFlowNodesByNodeId(long flowId, String nodeId);

    //获得操作
    public ArrayList isOptionEnable(long flowId, String nodeId, String type, String sortField, String sortOrder);

    //获得活动实例Id
    public ArrayList getNodeInstList(long flowInstId, String nodeId, String state);

    //完成提交
    public void finishCurrentNode(long flowId, long flowInstId, String nodeInstId, String nextNodeId, String currentUser);

    public ArrayList getParameterList(long flowInstId, String paraName);

    public int insert_Parameter(long flowInstId, String paraname, String paravalue, String caor, Date caodate);

    public int deleteYjflowinstpara(long flowInstId, String paraname, String paravalue);

    public ArrayList getYjFlow(String flowId);

    public long startYjFlow(String flowInstName, long flowId, long sqsId, String currentUser, long shenHeAreaId) throws Exception;

    public int addPara(String flowInstId, String paraName, String paraValue, String currentUser);

    public int addYjflowNodeinst(String flowInstId, String nodeInstId, String flowId, String userName);

    public int addYjflowNodeinstZzps(String flowInstId, String nodeInstId, String flowId, String userName);

    public int addWorkItem(long flowId, String flowInstId, String nodeInstId, String userName, String currentUser);

    public String getUserName(String userId);

    public String getflowInstParticipant(long flowId, long flowInstId, String nodeId, String type);

    /**
     * 根据流程ID,查询流程所有节点 》 在用
     *
     * @param flowId
     */
    List<Map<String, Object>> getYjflownodesByFlowId(String flowId) throws Exception;
}
