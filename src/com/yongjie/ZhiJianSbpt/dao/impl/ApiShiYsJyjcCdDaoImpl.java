package com.yongjie.ZhiJianSbpt.dao.impl;

import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.dao.ApiShiYsJyjcCdDao;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcCd;
import com.yongjie.ZhiJianSbpt.utilities.R;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(ApiShiYsJyjcCdDao.SERVICE_NAME)
public class ApiShiYsJyjcCdDaoImpl extends BaseDaoImpl<ApiShiYsJyjcCd> implements ApiShiYsJyjcCdDao {

    @Override
    public ApiShiYsJyjcCd queryBySerialNumber(String serialNumber) throws Exception {
        if (StringUtil.isNullOrEmpty(serialNumber)) {
            return null;
        }
        StringBuffer sbf = new StringBuffer();
        sbf.append("SELECT * FROM API_SHIYSJYJC_CD WHERE SERIALNUMBER='" + serialNumber + "'");
        SQLQuery sqlQuery = getSession().createSQLQuery(sbf.toString()).addEntity(ApiShiYsJyjcCd.class);
        return (ApiShiYsJyjcCd) sqlQuery.uniqueResult();
    }

    @Override
    public Map<String, Object> queryListBySerialNumber(HashMap<Object, Object> reqMap) throws Exception {
        String siteType = (String) reqMap.get("siteType");
        String serialNumber = (String) reqMap.get("serialNumber");

        StringBuffer selectSql = new StringBuffer();
        selectSql.append("SELECT * FROM API_SHIYSJYJC_CD WHERE 1=1 ");

        if (!StringUtil.isEmpty(siteType)) {
            selectSql.append(" AND SITETYPE='" + siteType + "' ");
        }

        if (!StringUtil.isEmpty(serialNumber)) {
            selectSql.append(" AND SERIALNUMBER ='" + serialNumber + "' ");
        }

        SQLQuery selectQuery = getSession().createSQLQuery(selectSql.toString()).addEntity(ApiShiYsJyjcCd.class);

        List<ApiShiYsJyjcCd> list = selectQuery.list();
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put(R.DATA_NAME, list);
        return resultMap;
    }

    @Override
    public Map<String, Object> getJgdzData(String serialNumber) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        StringBuffer selectSql = new StringBuffer();
        selectSql.append("SELECT T.* FROM API_SHIYSJYJC_CD T WHERE SERIALNUMBER='" + serialNumber + "'");
        SQLQuery sqlQuery = getSession().createSQLQuery(selectSql.toString());
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        result.put(R.DATA_NAME, list);
        return result;
    }
}
