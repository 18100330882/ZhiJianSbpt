package com.yongjie.ZhiJianSbpt.startFlow.dao.impl;

import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.module.util.R;
import com.yongjie.ZhiJianSbpt.startFlow.dao.ApiFlowDao;
import com.yongjie.ZhiJianSbpt.startFlow.model.ApiFlow;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(ApiFlowDao.SERVICE_NAME)
public class ApiFlowDaoImpl extends BaseDaoImpl<ApiFlow> implements ApiFlowDao {

    @Override
    public ApiFlow getApiFlowByUnid(String uuid) {
        StringBuffer selectSql = new StringBuffer();

        selectSql.append("select * from API_FLOW where 1=1 ");
        if (!StringUtil.isEmpty(uuid)) {
            selectSql.append(" and uuid=:uuid ");
        }
        SQLQuery selectQuery = getSession().createSQLQuery(selectSql.toString()).addEntity(ApiFlow.class);
        if (!StringUtil.isEmpty(uuid)) {
            selectQuery.setString("uuid", uuid);
        }
        return (ApiFlow) selectQuery.uniqueResult();
    }
}
