package com.yongjie.ZhiJianSbpt.startFlow.service.impl;

import com.mysql.jdbc.StringUtils;
import com.yongjie.ZhiJianSbpt.dao.flowBase.IYjFlowNodesDao;
import com.yongjie.ZhiJianSbpt.model.YjFlowInstLog;
import com.yongjie.ZhiJianSbpt.startFlow.dao.*;
import com.yongjie.ZhiJianSbpt.startFlow.model.*;
import com.yongjie.ZhiJianSbpt.startFlow.service.*;
import com.yongjie.ZhiJianSbpt.utilities.HashmapAndEntityTransfer;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Transactional(readOnly = false)
@Service(IBpsService.SERVICE_NAME)
public class BpsService implements IBpsService {

    @Resource(name = IYjFlowInstService.SERVICE_NAME)
    private IYjFlowInstService yjFlowInstService;

    @Resource(name = IYjFlowNodesDao.SERVICE_NAME)
    private IYjFlowNodesDao yjFlowNodesDao;

    @Resource(name = IYjFlowInstDao.SERVICE_NAME)
    private IYjFlowInstDao flowInstdao;

    @Resource(name = IYjWorkItemService.SERVICE_NAME)
    private IYjWorkItemService yjwoItemService;

    @Resource(name = IYjNodeInstService.SERVICE_NAME)
    private IYjNodeInstService YjNodeInstService;

    @Resource(name = IAccountXzspDao.SERVICE_NAME)
    private IAccountXzspDao usersDao;

    @Resource(name = IYjFlowInstLogService.SERVICE_NAME)
    private IYjFlowInstLogService yjFlowInstLogService;

    @Resource(name = IYjFlowInstParticipantDao.SERVICE_NAME)
    private IYjFlowInstParticipantDao yjFlowInstPartiDao;

    @Resource(name = IUsersInRolesDao.SERVICE_NAME)
    private IUsersInRolesDao userInRoleDao;

    @Resource(name = IYjNodeInstDao.SERVICE_NAME)
    private IYjNodeInstDao nodeInstdao;

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public Long statrtNewYjflow(String flowName, Long flowId, Long sqsId, String currentUser, Long shenHeAreaId, String suggesTion, String slYesOrNo) throws Exception {
        YjFlowInst yjflowinst = new YjFlowInst();
        if (flowId == null || flowId <= 0) {
            throw new RuntimeException("???????????????????????????!,?????????flowId????????????");
        } else {
            yjflowinst.setFlowId(flowId);
        }
        // ?????????????????????
        Date date = new Date();
        yjflowinst.setCurrentState(0);// ?????????????????????0
        yjflowinst.setFlowInstName(flowName);
        yjflowinst.setCaoR(currentUser);
        yjflowinst.setCaoDate(date);
        yjFlowInstService.saveYjFlowInst(yjflowinst);

        // ????????????????????????nodeId
        YjFlowNodes nodeModel = getStartNode(flowId);
        String nodeMc = nodeModel.getNodeMc();
        String nodeId = nodeModel.getNodeId();
        // ?????????????????????
        YjNodeInst yjnodeinst = new YjNodeInst();
        yjnodeinst.setFlowId(flowId);
        yjnodeinst.setCurrentState(0);// ?????????0
        yjnodeinst.setFlowInstId(yjflowinst.getId());
        yjnodeinst.setNodeId(nodeId);
        yjnodeinst.setNodeInstName(nodeMc);
        yjnodeinst.setCaoR(currentUser);
        yjnodeinst.setCaoDate(date);
        YjNodeInstService.addYjNodeInst(yjnodeinst);
        /*-------------------???????????????????????????end----------------*/

        /*-------------------??????????????????begin------------------*/
        // ???????????????
        String userNames = getParticipantsStr(yjflowinst.getId(), nodeId, flowId, shenHeAreaId);

        String[] userNamelist = userNames.split(",");// ???????????????
        for (int j = 0; j < userNamelist.length; j++) {
            // ???????????????
            addWorkItem(flowId, yjflowinst.getId(), yjnodeinst.getId(), userNamelist[j], currentUser);
        }
        /*-------------------??????????????????end--------------------*/

        /*-------------------???????????????begin-------------------*/
        // ????????????????????????
        YjFlowInstLog logModel = new YjFlowInstLog();
        logModel.setFlowInstId(yjflowinst.getId());
        logModel.setNodeInstId(yjnodeinst.getId());
        logModel.setOperationName(nodeMc);
        logModel.setOperationMemo("????????????");
        if (!StringUtils.isNullOrEmpty(suggesTion)) {
            logModel.setSuggestion(suggesTion);
        } else {
            logModel.setSuggestion("??????");
        }
        logModel.setOpType(1);// ????????????
        // ????????????????????????????????????
        logModel.setTrueName(currentUser);
        logModel.setCaoR(currentUser);
        logModel.setCaoDate(date);
        logModel.setSlYesOrNo(slYesOrNo);
        yjFlowInstLogService.addYjFlowInstLog(logModel);
        /*-------------------???????????????end---------------------*/
        updateBiz(flowId, -1, nodeId, sqsId);
        // ??????????????????Id
        return yjflowinst.getId();
    }

    public YjFlowNodes getStartNode(long flowId) throws Exception {
        // ???????????????startOrEnd???1
        return yjFlowNodesDao.selectFlowNodesByflowId(flowId, 1);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void updateBiz(long flowId, long flowInstId, String nodeId, long sqsId) throws Exception {
        String tabName = getSearchTab(flowId);
        flowInstdao.updateBiz(tabName, flowInstId, nodeId, sqsId);
    }

    // ??????flowId???????????????????????????
    public String getSearchTab(long flowId) {
        String bizName = "";
        if (flowId == 4 || flowId == 6) {
            bizName = "BIZ_API_SHIYSJYJC";
        } else if (flowId == 28) {
            bizName = "BIZ_API_ZWCNBG";
        } else if (flowId == 60) {
            bizName = "BIZ_API_ZXSQB";
        } else {
            bizName = "???????????????????????????!";
        }
        return bizName;
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void addWorkItem(long flowId, long flowInstId, long nodeInstId, String participant, String currentUser) throws Exception {
        YjWorkItem workModel = new YjWorkItem();
        workModel.setFlowId(flowId);
        workModel.setFlowInstId(flowInstId);
        workModel.setNodeInstId(nodeInstId);
        // ?????????
        workModel.setParticipant(participant);
        workModel.setCurrentState(0);
        workModel.setCaoR(currentUser);
        Date d = new Date();
        workModel.setCaoDate(d);
        yjwoItemService.add(workModel);
    }

    public String getParticipantsStr(long flowInstId, String nodeId, long flowId, long shenHeAreaId) throws Exception {
        String userIds = getParticipantUserIds(flowInstId, nodeId, flowId, shenHeAreaId);
        String[] userList = userIds.split(",");
        String participants = "";// ????????????
        if (userList.length > 0) {
            for (int i = 0; i < userList.length; i++) {
                if (!StringUtil.isNullOrEmpty(userList[i])) {
                    AccountXzsp model = usersDao.getAccountById(Long.parseLong(userList[i]));
                    if (model != null) {
                        participants += model.getUserName() + ",";
                    }
                }
            }
        }
        if (participants.length() > 0) {
            participants = participants.substring(0, participants.length() - 1);
        }
        return participants;
    }

    public String getParticipantUserIds(long flowInstId, String nodeId, long flowId, long shenHeAreaId) throws Exception {
        // ???yjFlowInstParticipant?????????????????????????????????
        List<YjFlowInstParticipant> partiList = yjFlowInstPartiDao.getFlowInstParticipantList(flowId, flowInstId,
                nodeId);
        String usersStrs = "";
        // ?????????????????????
        if (partiList.size() > 0) {
            for (int i = 0; i < partiList.size(); i++) {
                YjFlowInstParticipant model = partiList.get(i);
                usersStrs = getParticipantByTypeandValue(model.getParticipantType(), model.getParticipant(), flowId, flowInstId, shenHeAreaId, -1);
            }
        } else {
            YjFlowNodes model = yjFlowNodesDao.getYjFlowNodesByNodeId(nodeId, flowId);
            usersStrs = getParticipantByTypeandValue(model.getParticipantType(), model.getParticipant(), flowId, flowInstId, shenHeAreaId, model.getIsAreaFilter());
        }
        return usersStrs;
    }

    public String getParticipantByTypeandValue(int participantType, String value, long flowId, long flowInstId, long shenHeAreaId, Integer isAreaFilter) throws Exception {
        /**
         * ??????participantType == 0??????ISAREAFILTER???0 ????????????shenHeAreaId?????????????????????????????????shenHeAreaId??????
         * userInRoleDao.getUserIds() ????????????ISAREAFILTER
         */
        String usersStrs = "";
        if (participantType == 0) {// ??????
            // ??????roleId????????????id
            String userIds = userInRoleDao.getUserIds(value, shenHeAreaId, isAreaFilter);// ??????Id
            usersStrs += userIds;
        } else if (participantType == 1) {// ??????
            // ????????????id ??????userId;
            String userIds = usersDao.getUserIds(value);
            usersStrs += userIds;
        } else if (participantType == 2) {// ??????
            usersStrs = value;
        } else if (participantType == 3) {// ?????? ??????????????????????????????????????????
            usersStrs = getUserIdsByModel("YjFlowNodes", value, flowId, participantType, flowInstId, shenHeAreaId, isAreaFilter);// ??????
        } else if (participantType == 4) {// ??????
            // ?????????????????????????????????
            usersStrs = getUserIdsByModel("YjFlowNodeOperations", value, flowId, participantType, flowInstId,
                    shenHeAreaId, isAreaFilter);// ??????
        } else if (participantType == -1) {
            usersStrs = getUserIdsByModel("", value, flowId, participantType, flowInstId, shenHeAreaId, isAreaFilter);// ??????
        }
        return usersStrs;
    }

    public String getUserIdsByModel(String model, String ids, long flowId, int participantType, long flowInstId,
                                    long shenHeAreaId, Integer isAreaFilter) throws Exception {
        String usersStrs = "";
        if (participantType == 0)// ??????
        {
            String roleIds = yjFlowNodesDao.getParticipants(model, ids, flowId);
            // ??????roleId????????????id
            String userIds = userInRoleDao.getUserIds(roleIds, shenHeAreaId, isAreaFilter);
            usersStrs += userIds;
        } else if (participantType == 1)// ??????
        {
            String deptIds = yjFlowNodesDao.getParticipants(model, ids, flowId);// ??????Id
            // ????????????id ??????userId;
            String userIds = usersDao.getUserIds(deptIds);
            usersStrs += userIds;
        } else if (participantType == 2)// ??????
        {
            usersStrs += yjFlowNodesDao.getParticipants(model, ids, flowId);// ??????Id
        } else if (participantType == 3)// ??????
        {

            String type = yjFlowNodesDao.getPartiType(model, ids, flowId);
            String participant = yjFlowNodesDao.getParticipants(model, ids, flowId);// ??????Id.????????????nodeId??????????????????type=""
            if (Integer.parseInt(type) == 0 || Integer.parseInt(type) == 1 || Integer.parseInt(type) == 2) {
                usersStrs += getUserIdsByModel(model, ids, flowId, Integer.parseInt(type), flowInstId, shenHeAreaId, isAreaFilter);// ??????
            } else if (Integer.parseInt(type) == 3) {
                usersStrs += getUserIdsByModel("YjFlowNodes", participant, flowId, Integer.parseInt(type), flowInstId,
                        shenHeAreaId, isAreaFilter);// ??????
            } else if (Integer.parseInt(type) == 4) {
                usersStrs += getUserIdsByModel("YjFlowNodeOperations", participant, flowId, Integer.parseInt(type),
                        flowInstId, shenHeAreaId, isAreaFilter);// ??????
            } else if (Integer.parseInt(type) == -1) {
                // ???????????????????????????
                List<YjFlowInstParticipant> partiList = yjFlowInstPartiDao.getFlowInstParticipantList(flowId,
                        flowInstId, ids);
                usersStrs += getUserIdsByModel("YjFlowInstParticipant", String.valueOf(partiList.get(0).getId()),
                        flowId, partiList.get(0).getParticipantType(), flowInstId, shenHeAreaId, isAreaFilter);
            }
        } else if (participantType == 4)// ??????
        {
            String type = yjFlowNodesDao.getPartiType(model, ids, flowId);
            String participant = yjFlowNodesDao.getParticipants(model, ids, flowId);
            if (Integer.parseInt(type) == 0 || Integer.parseInt(type) == 1 || Integer.parseInt(type) == 2) {
                usersStrs += getUserIdsByModel(model, ids, flowId, Integer.parseInt(type), flowInstId, shenHeAreaId, isAreaFilter);// ??????
            } else if (Integer.parseInt(type) == 3) {
                usersStrs += getUserIdsByModel("YjFlowNodes", participant, flowId, Integer.parseInt(type), flowInstId,
                        shenHeAreaId, isAreaFilter);
            } else {// type?????????4???
                usersStrs += getUserIdsByModel("YjFlowNodeOperations", participant, flowId, Integer.parseInt(type),
                        flowInstId, shenHeAreaId, isAreaFilter);// ??????
            }
        } else if (participantType == -1) {
            // ???????????????????????????
            if (!StringUtil.isNullOrEmpty(ids)) {
                List<YjFlowInstParticipant> partiList = yjFlowInstPartiDao.getFlowInstParticipantList(flowId, flowInstId,
                        ids);
                if (partiList.size() > 0) {
                    usersStrs += getUserIdsByModel("YjFlowInstParticipant", String.valueOf(partiList.get(0).getId()),
                            flowId, partiList.get(0).getParticipantType(), flowInstId, shenHeAreaId, isAreaFilter);
                } else {
                    System.out.println(
                            "????????????:??????????????????-???????????????????????????????????????(??????:BpsService.java, ?????????:getUserIdsByModel,??????:com.yongjie.ZhiJianXzsp.service.flowBase.impl.BpsService)");
                }
            }
        }
        return usersStrs;
    }

    // ???????????? - ?????????-2016-09-05-11:23
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public long startYjFlow(String flowInstName, long flowId, long sqsId, String currentUser, long shenHeAreaId) throws Exception {
        /*---------------------???????????????????????????begin----------------*/
        YjFlowInst yjflowinst = new YjFlowInst();
        if (flowId == 0) {
            System.out.println("???????????????????????????flowId??????!");
        } else {
            yjflowinst.setFlowId(flowId);
        }
        yjflowinst.setCurrentState(0);// ?????????????????????0
        yjflowinst.setFlowInstName(flowInstName);
        yjflowinst.setCaoR(currentUser);
        yjflowinst.setCaoDate(new java.sql.Date(System.currentTimeMillis()));
        flowInstdao.save(yjflowinst);
        System.out.println("??????????????????" + HashmapAndEntityTransfer.entityTransferToHashmap(yjflowinst) + "yjflowinstcuo");
        /*-------------------???????????????????????????end----------------*/

        /*-------------------???????????????????????????begin----------------*/
        // ??????????????????Id
        Long flowInstId = yjflowinst.getId();
        System.out.println("??????????????????" + flowInstId);
        // ????????????????????????nodeId
        YjFlowNodes nodeModel = getStartNode(flowId);
        String nodeMc = nodeModel.getNodeMc();
        String nodeId = nodeModel.getNodeId();
        // ?????????????????????
        YjNodeInst yjnodeinst = new YjNodeInst();
        yjnodeinst.setFlowId(flowId);
        yjnodeinst.setCurrentState(0);// ?????????0
        yjnodeinst.setFlowInstId(flowInstId);
        yjnodeinst.setNodeId(nodeId);
        yjnodeinst.setNodeInstName(nodeMc);
        yjnodeinst.setCaoR(currentUser);
        yjnodeinst.setCaoDate(new java.sql.Date(System.currentTimeMillis()));
        nodeInstdao.save(yjnodeinst);
        System.out.println("??????????????????" + HashmapAndEntityTransfer.entityTransferToHashmap(yjnodeinst));
        /*-------------------???????????????????????????end----------------*/

        /*-------------------??????????????????begin------------------*/
        // ???????????????
        String userNames = getParticipantsStr(flowInstId, nodeId, flowId, shenHeAreaId);
        String[] userNamelist = userNames.split(",");// ???????????????
        for (int j = 0; j < userNamelist.length; j++) {
            // ???????????????
            addWorkItem(flowId, flowInstId, yjnodeinst.getId(), userNamelist[j], currentUser);
        }
        /*-------------------??????????????????end--------------------*/

        /*-------------------???????????????begin-------------------*/
        // ????????????????????????
        YjFlowInstLog logModel = new YjFlowInstLog();
        logModel.setFlowInstId(yjflowinst.getId());
        logModel.setNodeInstId(yjnodeinst.getId());
        logModel.setOperationName(nodeMc);
        logModel.setOperationMemo("????????????");
        logModel.setSuggestion("??????");
        logModel.setOpType(1);// ????????????
        // ????????????????????????????????????
        logModel.setTrueName(currentUser);
        logModel.setCaoR(currentUser);
        logModel.setCaoDate(new Date());
        yjFlowInstLogService.addYjFlowInstLog(logModel);
        /*-------------------???????????????end---------------------*/
        updateBiz(flowId, -1, nodeId, sqsId);
        return flowInstId;
    }
}
