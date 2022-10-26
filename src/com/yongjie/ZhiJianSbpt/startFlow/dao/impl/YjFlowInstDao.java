package com.yongjie.ZhiJianSbpt.startFlow.dao.impl;

import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.startFlow.dao.IYjFlowInstDao;
import com.yongjie.ZhiJianSbpt.startFlow.model.YjFlowInst;
import org.springframework.stereotype.Repository;

@Repository(IYjFlowInstDao.SERVICE_NAME)
public class YjFlowInstDao extends BaseDaoImpl<YjFlowInst> implements IYjFlowInstDao {

    @Override
    public int updateBiz(String tabName, long flowInstId, String nodeId, long sqsId) {
        String sql;
        if (sqsId > 0) {
            sql = "update " + tabName + " set nodeId='" + nodeId + "',nodedate=sysdate where id=" + sqsId;
        } else {
            sql = "update " + tabName + " set nodeId='" + nodeId + "',nodedate=sysdate where flowInstId=" + flowInstId;
        }
        int result = getSession().createSQLQuery(sql).executeUpdate();
        return result;
    }
}
