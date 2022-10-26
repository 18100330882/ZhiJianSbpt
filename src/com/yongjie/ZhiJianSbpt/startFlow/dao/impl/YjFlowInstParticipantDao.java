package com.yongjie.ZhiJianSbpt.startFlow.dao.impl;

import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.startFlow.dao.IYjFlowInstParticipantDao;
import com.yongjie.ZhiJianSbpt.startFlow.model.YjFlowInstParticipant;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Repository(IYjFlowInstParticipantDao.SERVICE_NAME)
public class YjFlowInstParticipantDao extends BaseDaoImpl<YjFlowInstParticipant> implements IYjFlowInstParticipantDao {

    @Override
    public List<YjFlowInstParticipant> getFlowInstParticipantList(long flowId, long flowInstId, String nodeId) {
        String sql = "select * from YJFLOWINSTPARTICIPANT y where y.flowInstId=:flowInstId ";
        if (flowId != 0) {
            //sql+=" and y.flowId="+flowId+"";
            sql += " and y.flowId=:flowId ";
        }
        if (!StringUtil.isNullOrEmpty(nodeId)) {
            sql += " and y.nodeId=:nodeId ";
        }

        SQLQuery query = getSession().createSQLQuery(sql).addEntity(YjFlowInstParticipant.class);

        query.setLong("flowInstId", flowInstId);
        if (flowId != 0)
            query.setLong("flowId", flowId);

        if (!StringUtil.isNullOrEmpty(nodeId))
            query.setString("nodeId", nodeId);

        List<YjFlowInstParticipant> flowInstParList = query.list();
        return flowInstParList;
    }
}
