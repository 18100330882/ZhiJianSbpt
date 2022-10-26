package com.yongjie.ZhiJianSbpt.dao.impl;

import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.dao.ApiShiYsJyjcYqsbSbptDao;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcYqsbSbpt;
import com.yongjie.ZhiJianSbpt.utilities.R;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(ApiShiYsJyjcYqsbSbptDao.SERVICE_NAME)
public class ApiShiYsJyjcYqsbSbptDaoImpl extends BaseDaoImpl<ApiShiYsJyjcYqsbSbpt> implements ApiShiYsJyjcYqsbSbptDao {

    @Override
    public void deleteEvent(HashMap<String, Object> reqMap) {
        String serialNumber = (String) reqMap.get("serialNumber");
        String flag = String.valueOf(reqMap.get("flag"));
        String typeName = (String) reqMap.get("typeName");
        String siteName = (String) reqMap.get("siteName");
        StringBuffer sql = new StringBuffer();
        sql.append("DELETE FROM API_SHIYSJYJC_YQSB_SBPT WHERE 1=1");
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
        String isSp = (String) reqMap.get("isSp");
        String cddz = (String) reqMap.get("cddz");
        String status = (String) reqMap.get("status");
        sql.append("SELECT * FROM API_SHIYSJYJC_YQSB_SBPT WHERE 1=1");
        sql.append(" AND SERIALNUMBER='" + serialNumber + "'");
        sql.append(" AND FLAG='" + flag + "'");
        sql.append(" AND TYPENAME='" + isSp + "'");
        sql.append(" AND SITENAME='" + cddz + "'");
        sql.append(" AND status='" + status + "' ");
        sql.append(" ORDER BY to_number(SORTINGNUMBER) ASC,CREATEDATE DESC");
        SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString());

        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        resultMap.put(R.DATA_NAME, list);
        return resultMap;
    }
}
