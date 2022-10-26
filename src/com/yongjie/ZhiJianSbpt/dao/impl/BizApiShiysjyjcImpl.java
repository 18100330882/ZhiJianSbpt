package com.yongjie.ZhiJianSbpt.dao.impl;

import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.dao.BizApiShiysjyjcDao;
import com.yongjie.ZhiJianSbpt.model.BizApiShiYsJyjc;
import com.yongjie.ZhiJianSbpt.module.util.R;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(BizApiShiysjyjcDao.SERVICE_NAME)
public class BizApiShiysjyjcImpl extends BaseDaoImpl<BizApiShiYsJyjc> implements BizApiShiysjyjcDao {

    @Override
    public Map<String, Object> getList(Map<Object, Object> reqMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        StringBuffer sql = new StringBuffer();
        StringBuffer countSql = new StringBuffer();
        int pageIndex = (int) reqMap.get("pageIndex");
        int pageSize = (int) reqMap.get("pageSize");
        String sortField = (String) reqMap.get("sortField");
        String sortOrder = (String) reqMap.get("sortOrder");
        String jgmc = (String) reqMap.get("jgmc");
        String flowId = (String) reqMap.get("flowId");
        String userName = (String) reqMap.get("userName");


        sql.append("SELECT T.* FROM BIZ_API_SHIYSJYJC T WHERE 1=1 AND T.FLOWID =" + flowId + " AND CREATEUSER= '" + userName + "'");
        countSql.append("SELECT COUNT(ID) FROM BIZ_API_SHIYSJYJC T WHERE 1=1 AND T.FLOWID =" + flowId + " AND CREATEUSER= '" + userName + "'");
        if (!StringUtil.isNullOrEmpty(jgmc)) {
            sql.append(" AND T.JGMC LIKE '%" + jgmc + "%'");
            countSql.append(" AND T.JGMC LIKE '%" + jgmc + "%'");
        }

        if (StringUtil.isNullOrEmpty(sortField)) {
            sortField = "ID";
        }
        if (StringUtil.isNullOrEmpty(sortOrder)) {
            sortOrder = " DESC";
        }

        sql.append("ORDER BY T." + sortField + " " + sortOrder);

        SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString());
        SQLQuery counQuery = getSession().createSQLQuery(countSql.toString());

        if (pageIndex != -1 && pageSize != -1) {
            sqlQuery.setFirstResult(pageIndex * pageSize);
            sqlQuery.setMaxResults(pageSize);
        }
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> list = sqlQuery.list();
        Number count = (Number) counQuery.uniqueResult();
        resultMap.put(R.TOTAL_NAME, count == null ? 0 : count.intValue());
        resultMap.put(R.DATA_NAME, list);
        return resultMap;
    }

    @Override
    public BizApiShiYsJyjc queryBySerialNumber(String serialNumber) throws Exception {
        if (StringUtil.isNullOrEmpty(serialNumber)) {
            return null;
        }
        StringBuffer sbf = new StringBuffer();
        sbf.append("SELECT * FROM BIZ_API_SHIYSJYJC WHERE SERIALNUMBER='" + serialNumber + "'");
        SQLQuery sqlQuery = getSession().createSQLQuery(sbf.toString()).addEntity(BizApiShiYsJyjc.class);
        return (BizApiShiYsJyjc) sqlQuery.uniqueResult();
    }

    @Override
    public BizApiShiYsJyjc getBizShiYsJyjcBySerialNumber(String serialNumber) throws Exception {
        if (StringUtil.isEmpty(serialNumber)) {
            return null;
        }
        StringBuffer sbf = new StringBuffer();
        sbf.append("SELECT * FROM BIZ_API_SHIYSJYJC WHERE SERIALNUMBER=:SERIALNUMBER ");

        SQLQuery sqlQuery = getSession().createSQLQuery(sbf.toString()).addEntity(BizApiShiYsJyjc.class);
        sqlQuery.setString("SERIALNUMBER", serialNumber);

        return (BizApiShiYsJyjc) sqlQuery.uniqueResult();
    }
}
