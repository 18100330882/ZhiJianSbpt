package com.yongjie.ZhiJianSbpt.startFlow.dao;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.startFlow.model.YjFlowInstParticipant;

import java.util.ArrayList;
import java.util.List;

public interface IYjFlowInstParticipantDao extends BaseDao<YjFlowInstParticipant> {
    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.startFlow.dao.impl.YjFlowInstParticipantDao";

    List<YjFlowInstParticipant> getFlowInstParticipantList(long flowId, long flowInstId, String nodeId);
}
