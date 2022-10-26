package com.yongjie.ZhiJianSbpt.dao.impl;

import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.dao.ApiShiYsJyjcNlDao;
import com.yongjie.ZhiJianSbpt.dao.ApiShiYsJyjcNlSbptDao;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcNl;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcNlSbpt;
import com.yongjie.ZhiJianSbpt.utilities.R;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(ApiShiYsJyjcNlSbptDao.SERVICE_NAME)
public class ApiShiYsJyjcNlSbptDaoImpl extends BaseDaoImpl<ApiShiYsJyjcNlSbpt> implements ApiShiYsJyjcNlSbptDao {

    @Override
    public void deleteEvent(HashMap<String, Object> reqMap) {
        String serialNumber = (String) reqMap.get("serialNumber");
        String flag = String.valueOf(reqMap.get("flag"));
        String typeName = (String) reqMap.get("typeName");
        String siteName = (String) reqMap.get("siteName");
        StringBuffer sql = new StringBuffer();
        sql.append("DELETE FROM API_SHIYSJYJC_NLSBPT WHERE 1=1");
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
        sql.append("SELECT * FROM API_SHIYSJYJC_NLSBPT WHERE 1=1");
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
    public Map<String, Object> queryList(HashMap<String, Object> reqMap) {
        Map<String, Object> resultMap = new HashMap<>();
        StringBuffer sql = new StringBuffer();
        String serialNumber = (String) reqMap.get("serialNumber");
        String flag = String.valueOf(reqMap.get("flag"));
        String typeName = (String) reqMap.get("typeName");
        String siteName = (String) reqMap.get("siteName");
        String yjflName = (String) reqMap.get("yjflName");
        sql.append("SELECT * FROM API_SHIYSJYJC_NLSBPT WHERE 1=1");
        sql.append(" AND SERIALNUMBER='" + serialNumber + "'");
        sql.append(" AND FLAG='" + flag + "'");
        sql.append(" AND TYPENAME='" + typeName + "'");
        sql.append(" AND SITENAME='" + siteName + "'");
        if (StringUtil.isNullOrEmpty(yjflName)) {
            sql.append(" AND YJFL='" + yjflName + "'");
        }
//        sql.append(" ORDER BY to_number(SORTINGNUMBER) ASC,CREATEDATE DESC");
        sql.append(" ORDER BY ID ASC");
        SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString());

        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        resultMap.put(R.DATA_NAME, list);
        return resultMap;
    }

    @Override
    public List queryListBySelect(Map reqMap) {
        StringBuffer sql = new StringBuffer();
        String serialNumber = (String) reqMap.get("serialNumber");
        String flag = String.valueOf(reqMap.get("flag"));
        String typeName = (String) reqMap.get("typeName");
        String siteName = (String) reqMap.get("siteName");
        String selectTj = (String) reqMap.get("selectTj");//要查询的字段
        sql.append("SELECT DISTINCT " + selectTj + " FROM API_SHIYSJYJC_NLSBPT WHERE 1=1");
        sql.append(" AND SERIALNUMBER='" + serialNumber + "'");
        sql.append(" AND FLAG='" + flag + "'");
        sql.append(" AND TYPENAME='" + typeName + "'");
        sql.append(" AND SITENAME='" + siteName + "'");
        sql.append(" AND " + selectTj + " IS NOT NULL ");
        SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString());
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return sqlQuery.list();
    }

    @Override
    public ArrayList<ApiShiYsJyjcNlSbpt> queryListByTj(Map map) throws Exception {
        StringBuffer sql = new StringBuffer();
        String serialNumber = (String) map.get("serialNumber");
        String flag = String.valueOf(map.get("flag"));
        String typeName = (String) map.get("typeName");
        String siteName = (String) map.get("siteName");
        String yjflName = (String) map.get("yjflName");
        sql.append("SELECT * FROM API_SHIYSJYJC_NLSBPT WHERE 1=1");
        sql.append(" AND SERIALNUMBER='" + serialNumber + "'");
        sql.append(" AND FLAG='" + flag + "'");
        sql.append(" AND TYPENAME='" + typeName + "'");
        sql.append(" AND SITENAME='" + siteName + "'");
        if (!StringUtil.isNullOrEmpty(yjflName)) {
            sql.append(" AND YJFL='" + yjflName + "'");
        }
//        sql.append(" ORDER BY to_number(SORTINGNUMBER) ASC,CREATEDATE DESC");
        sql.append(" ORDER BY ID ASC");
        SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString()).addEntity(ApiShiYsJyjcNlSbpt.class);

        return (ArrayList<ApiShiYsJyjcNlSbpt>) sqlQuery.list();
    }

    @Override
    public List queryYjfl(Map reqMap) {
        StringBuffer sql = new StringBuffer();
        String serialNumber = (String) reqMap.get("serialNumber");
        String flag = String.valueOf(reqMap.get("flag"));
        String typeName = (String) reqMap.get("typeName");
        String siteName = (String) reqMap.get("siteName");
        sql.append(" SELECT YJFL FROM( ");
        sql.append(" SELECT DISTINCT YJFL,MAX(ID) ID FROM API_SHIYSJYJC_NLSBPT WHERE 1=1");
        sql.append(" AND SERIALNUMBER='" + serialNumber + "'");
        sql.append(" AND FLAG='" + flag + "'");
        sql.append(" AND TYPENAME='" + typeName + "'");
        sql.append(" AND SITENAME='" + siteName + "'");
        sql.append(" AND YJFL IS NOT NULL ");
        sql.append(" GROUP BY YJFL ORDER BY ID ASC ");
        sql.append(" )");
        SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString());
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return sqlQuery.list();
    }
}
