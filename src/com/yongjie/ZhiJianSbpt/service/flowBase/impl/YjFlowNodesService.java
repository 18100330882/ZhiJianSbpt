package com.yongjie.ZhiJianSbpt.service.flowBase.impl;

import com.yongjie.ZhiJianSbpt.dao.ICloginDao;
import com.yongjie.ZhiJianSbpt.dao.flowBase.IYjFlowNodesDao;
import com.yongjie.ZhiJianSbpt.service.flowBase.IYjFlowNodesService;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Transactional(readOnly = true)
@Service(IYjFlowNodesService.SERVICE_NAME)
public class YjFlowNodesService implements IYjFlowNodesService {
    @Resource(name = IYjFlowNodesDao.SERVICE_NAME)
    private IYjFlowNodesDao flowNodesdao;
    @Resource(name = ICloginDao.SERVICE_NAME)
    private ICloginDao cloginDao;

    @Override
    public ArrayList getNodeMcByFlowInstId(long flowId, long flowInstId) {
        // TODO Auto-generated method stub
        return flowNodesdao.getNodeMcByFlowInstId(flowId, flowInstId);
    }

    @Override
    public ArrayList getNodeMo(long flowId, long flowInstId) {
        // TODO Auto-generated method stub
        return flowNodesdao.getNodeMo(flowId, flowInstId);
    }

    @Override
    public ArrayList getBizIdByflowId(long flowId) {
        // TODO Auto-generated method stub
        return flowNodesdao.getBizIdByflowId(flowId);
    }

    @Override
    public ArrayList getFlowNodesByNodeId(long flowId, String nodeId) {
        // TODO Auto-generated method stub
        return flowNodesdao.getFlowNodesByNodeId(flowId, nodeId);
    }

    @Override
    public ArrayList isOptionEnable(long flowId, String nodeId, String type, String sortField, String sortOrder) {
        // TODO Auto-generated method stub
        return flowNodesdao.isOptionEnable(flowId, nodeId, type, sortField, sortOrder);
    }

    @Override
    public ArrayList getNodeInstList(long flowInstId, String nodeId, String state) {
        // TODO Auto-generated method stub
        return flowNodesdao.getNodeInstList(flowInstId, nodeId, state);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void finishCurrentNode(long flowId, long flowInstId, String nodeInstId, String nextNodeId, String currentUser) {
        // TODO Auto-generated method stub

        int result = flowNodesdao.updateCurrentState(nodeInstId, 9);
        if (result > 0) {
            // 获取所有符合条件的提交操作
            String[] strNodeIds = nextNodeId.split(",");
            for (int i = 0; i < strNodeIds.length; i++) {
                ArrayList flownodes = flowNodesdao.getFlowNodesByNodeId(flowId, strNodeIds[i]);
                String nodeName = ((HashMap) flownodes.get(0)).get("NODEMC") == null ? "" : ((HashMap) flownodes.get(0)).get("NODEMC").toString();
                // 插入一条活动实例
                int insertNodeInstRes = flowNodesdao.addNodeInst(flowId, flowInstId, strNodeIds[i], nodeName, currentUser, 0);
                if (insertNodeInstRes > 0) {
                    //获取获得实例
                    ArrayList nextNodeInstList = flowNodesdao.getNodeInstList(flowInstId, strNodeIds[i], "0");
                    //查找下个环节的参与者
                    if (strNodeIds[i].equals("pscljshz")) {
                        if (flowId == 25) {
                            strNodeIds[i] = "xchc";
                        } else {
                            strNodeIds[i] = "xcps";
                        }
                    }
                    String userNames = flowNodesdao.getflowInstParticipant(flowId, flowInstId, strNodeIds[i], "2");
                    String[] userNamelist = userNames.split(",");// 参与者列表
                    if (userNamelist.length > 0) {
                        for (int j = 0; j < userNamelist.length; j++) {
                            if (!StringUtil.isNullOrEmpty(userNamelist[j])) {
                                //用户
                                ArrayList userList = cloginDao.getAccountXzsp(userNamelist[j]);
                                String userName = ((HashMap) userList.get(0)).get("USERNAME") == null ? "" : ((HashMap) userList.get(0)).get("USERNAME").toString();
                                // 插入一条工作项
                                // ，工作项中的nodeInstId是指新插入的活动实例的id.
                                int insertWorkItemRes = flowNodesdao.addWorkItem(flowId, flowInstId, ((HashMap) nextNodeInstList.get(0)).get("ID") == null ? "" : ((HashMap) nextNodeInstList.get(0)).get("ID").toString(), userName, currentUser);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public ArrayList getParameterList(long flowInstId, String paraName) {
        // TODO Auto-generated method stub
        return flowNodesdao.getParameterList(flowInstId, paraName);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public int insert_Parameter(long flowInstId, String paraname, String paravalue, String caor, Date caodate) {
        // TODO Auto-generated method stub
        return flowNodesdao.insert_Parameter(flowInstId, paraname, paravalue, caor, caodate);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public int deleteYjflowinstpara(long flowInstId, String paraname, String paravalue) {
        // TODO Auto-generated method stub
        return flowNodesdao.deleteYjFlowInstPara(flowInstId, paraname, paravalue);
    }

    @Override
    public ArrayList getNodeMcByFlowInstIdOnEnd(long flowId, long flowInstId, String state) {
        // TODO Auto-generated method stub
        return flowNodesdao.getNodeMcByFlowInstIdOnEnd(flowId, flowInstId, state);
    }

    @Override
    public ArrayList getYjFlow(String flowId) {
        // TODO Auto-generated method stub
        return flowNodesdao.getYjFlow(flowId);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public long startYjFlow(String flowInstName, long flowId, long sqsId, String currentUser, long shenHeAreaId)
            throws Exception {
        /*---------------------插入流程实例表数据begin----------------*/
        return flowNodesdao.startYjFlow(flowInstName, flowId, sqsId, currentUser, shenHeAreaId);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public int addPara(String flowInstId, String paraName, String paraValue, String currentUser) {
        // TODO Auto-generated method stub
        return flowNodesdao.addPara(flowInstId, paraName, paraValue, currentUser);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public int addYjflowNodeinst(String flowInstId, String nodeInstId, String flowId, String userName) {
        // TODO Auto-generated method stub
        flowNodesdao.updateCurrentState(nodeInstId, 1);
        flowNodesdao.addNodeInst(Long.parseLong(flowId), Long.parseLong(flowInstId), "xppsjg", "委托评审机构", userName, 0);
        flowNodesdao.addYjFlowInstLogWtqr(flowInstId, nodeInstId, "退回到委托评审机构", "2", userName, "");
        return 0;
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public int addWorkItem(long flowId, String flowInstId, String nodeInstId, String userName, String currentUser) {
        // TODO Auto-generated method stub
        return flowNodesdao.addWorkItem(flowId, Long.parseLong(flowInstId), nodeInstId, userName, currentUser);
    }

    @Override
    public String getUserName(String userId) {
        // TODO Auto-generated method stub
        return flowNodesdao.getUserName(userId);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public int addYjflowNodeinstZzps(String flowInstId, String nodeInstId, String flowId, String userName) {
        // TODO Auto-generated method stub
        flowNodesdao.updateCurrentState(nodeInstId, 9);
        flowNodesdao.addNodeInst(Long.parseLong(flowId), Long.parseLong(flowInstId), "zzps", "评审组织", userName, 0);
        flowNodesdao.updateWorkItem(flowInstId, nodeInstId);
        flowNodesdao.addYjFlowInstLogWtqr(flowInstId, nodeInstId, "提交到评审组织", "1", userName, "");
        return 0;
    }

    @Override
    public String getflowInstParticipant(long flowId, long flowInstId, String nodeId, String type) {
        // TODO Auto-generated method stub
        return flowNodesdao.getflowInstParticipant(flowId, flowInstId, nodeId, type);
    }

    @Override
    public List<Map<String, Object>> getYjflownodesByFlowId(String flowId) throws Exception {
        // TODO Auto-generated method stub
        return flowNodesdao.getYjflownodesByFlowId(flowId);
    }

}
