package com.yongjie.ZhiJianSbpt.dao.impl;

import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.dao.ApiShiYsJyjcRyDao;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcQzr;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcRy;
import com.yongjie.ZhiJianSbpt.module.util.R;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(ApiShiYsJyjcRyDao.SERVICE_NAME)
public class ApiShiYsJyjcRyDaoImpl extends BaseDaoImpl<ApiShiYsJyjcRy> implements ApiShiYsJyjcRyDao {

    @Override
    public Map<String, Object> getList(Map<Object, Object> reqMap) {
        Map<String, Object> resultMap = new HashMap<>();
        StringBuffer sql = new StringBuffer();
        StringBuffer countSql = new StringBuffer();
        int pageIndex = (int) reqMap.get("pageIndex");
        int pageSize = (int) reqMap.get("pageSize");
        String sortField = (String) reqMap.get("sortField");
        String sortOrder = (String) reqMap.get("sortOrder");
        String serialNumber = (String) reqMap.get("serialNumber");
        String address = (String) reqMap.get("address");

        sql.append("SELECT T.* FROM API_SHIYSJYJC_RY T WHERE 1=1");
        countSql.append("SELECT COUNT(ID) FROM API_SHIYSJYJC_RY T WHERE 1=1");
        if (!StringUtil.isNullOrEmpty(serialNumber)) {
            sql.append(" AND T.serialNumber = '" + serialNumber + "'");
            countSql.append(" AND T.serialNumber = '" + serialNumber + "'");
        }
        if (!StringUtil.isNullOrEmpty(address)) {
            sql.append(" AND T.address = '" + address + "'");
            countSql.append(" AND T.address = '" + address + "'");
        }
        if (StringUtil.isNullOrEmpty(sortField)) {
            sortField = "ID";
        }
        if (StringUtil.isNullOrEmpty(sortOrder)) {
            sortOrder = " ASC";
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
    public ApiShiYsJyjcRy queryBySerialNumber(String serialNumber) {
        if (StringUtil.isNullOrEmpty(serialNumber)) {
            return null;
        }
        StringBuffer sbf = new StringBuffer();
        sbf.append("SELECT * FROM API_SHIYSJYJC_RY WHERE SERIALNUMBER='" + serialNumber + "'");
        SQLQuery sqlQuery = getSession().createSQLQuery(sbf.toString()).addEntity(ApiShiYsJyjcRy.class);
        return (ApiShiYsJyjcRy) sqlQuery.uniqueResult();
    }

    @Override
    public List<ApiShiYsJyjcRy> queryByBatchSerialNumber(String serialNumber) {
        StringBuffer selectSql = new StringBuffer();

        selectSql.append("select * from API_SHIYSJYJC_RY where serialNumber = :serialNumber  ");

        SQLQuery selectQuery = getSession().createSQLQuery(selectSql.toString()).addEntity(ApiShiYsJyjcRy.class);

        if (!StringUtil.isEmpty(serialNumber)) {
            selectQuery.setString("serialNumber", serialNumber);
        }
        return (List<ApiShiYsJyjcRy>) selectQuery.list();
    }


    @Override
    public int deleteBatchBySerialNumber(String serialNumber) throws Exception {

        if (StringUtil.isEmpty(serialNumber)) {
            return 0;
        }
        StringBuffer selectSql = new StringBuffer();
        selectSql.append("delete from API_SHIYSJYJC_RY where serialNumber=:serialNumber ");

        SQLQuery selectQuery = getSession().createSQLQuery(selectSql.toString());
        selectQuery.setString("serialNumber", serialNumber);

        return selectQuery.executeUpdate();
    }
}
