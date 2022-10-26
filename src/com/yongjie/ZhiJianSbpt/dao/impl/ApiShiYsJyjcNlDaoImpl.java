package com.yongjie.ZhiJianSbpt.dao.impl;

import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.dao.ApiShiYsJyjcNlDao;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcNl;
import com.yongjie.ZhiJianSbpt.utilities.R;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(ApiShiYsJyjcNlDao.SERVICE_NAME)
public class ApiShiYsJyjcNlDaoImpl extends BaseDaoImpl<ApiShiYsJyjcNl> implements ApiShiYsJyjcNlDao {

    @Override
    public void deleteEvent(HashMap<String, Object> reqMap) {
        String serialNumber = (String) reqMap.get("serialNumber");
        String flag = String.valueOf(reqMap.get("flag"));
        String typeName = (String) reqMap.get("typeName");
        String siteName = (String) reqMap.get("siteName");
        StringBuffer sql = new StringBuffer();
        sql.append("DELETE FROM API_SHIYSJYJC_NL WHERE 1=1");
        sql.append(" AND SERIALNUMBER='" + serialNumber + "'");
        sql.append(" AND FLAG='" + flag + "'");
        sql.append(" AND TYPENAME='" + typeName + "'");
        sql.append(" AND SITENAME='" + siteName + "'");
        getSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    @Override
    public Map<String, Object> getDetailList(HashMap<String, Object> reqMap) {
        Map<String, Object> resultMap = new HashMap<>();
        StringBuffer sql = new StringBuffer();
        String serialNumber = (String) reqMap.get("serialNumber");
        String flag = String.valueOf(reqMap.get("flag"));
        String typeName = (String) reqMap.get("typeName");
        String siteName = (String) reqMap.get("siteName");
        sql.append("SELECT * FROM API_SHIYSJYJC_NL WHERE 1=1");
        sql.append(" AND SERIALNUMBER='" + serialNumber + "'");
        sql.append(" AND FLAG='" + flag + "'");
        sql.append(" AND TYPENAME='" + typeName + "'");
        sql.append(" AND SITENAME='" + siteName + "'");
        sql.append(" ORDER BY to_number(SORTINGNUMBER) ASC,CREATEDATE DESC");
        SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString());

        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        resultMap.put(R.DATA_NAME, list);
        return resultMap;
    }

    @Override
    public void delnl(String params) {
        StringBuffer sql = new StringBuffer();
        sql.append("DELETE FROM API_SHIYSJYJC_NL WHERE ID IN (" + params + ") ");
        SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString());
        sqlQuery.executeUpdate();
    }
}
