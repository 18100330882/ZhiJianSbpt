package com.yongjie.ZhiJianSbpt.dao.impl;

import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.dao.ApiShiYsJyjcSqsDao;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcSqs;
import com.yongjie.ZhiJianSbpt.utilities.R;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository(ApiShiYsJyjcSqsDao.SERVICE_NAME)
public class ApiShiYsJyjcSqsDaoImpl extends BaseDaoImpl<ApiShiYsJyjcSqs> implements ApiShiYsJyjcSqsDao {

    @Override
    public ApiShiYsJyjcSqs queryBySerialNumber(String serialNumber) throws Exception {
        if (StringUtil.isNullOrEmpty(serialNumber)) {
            return null;
        }
        StringBuffer sbf = new StringBuffer();
        sbf.append("SELECT * FROM API_SHIYSJYJC_SQS WHERE SERIALNUMBER='" + serialNumber + "'");
        SQLQuery sqlQuery = getSession().createSQLQuery(sbf.toString()).addEntity(ApiShiYsJyjcSqs.class);
        return (ApiShiYsJyjcSqs) sqlQuery.uniqueResult();
    }

    @Override
    public int deleteBatchBySerialNumber(String serialNumber) throws Exception {

        if (StringUtil.isEmpty(serialNumber)) {
            return 0;
        }
        StringBuffer selectSql = new StringBuffer();
        selectSql.append("delete from API_SHIYSJYJC_SQS where serialNumber=:serialNumber ");

        SQLQuery selectQuery = getSession().createSQLQuery(selectSql.toString());
        selectQuery.setString("serialNumber", serialNumber);

        return selectQuery.executeUpdate();
    }

    @Override
    public Map deleteBatchBySerialNumber2(String serialNumber) {
        if (StringUtil.isNullOrEmpty(serialNumber)) {
            return null;
        }
        StringBuffer sbf = new StringBuffer();
        sbf.append("SELECT * FROM API_SHIYSJYJC_SQS WHERE SERIALNUMBER='" + serialNumber + "'");
        SQLQuery sqlQuery = getSession().createSQLQuery(sbf.toString());
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        Map result = new HashMap<>();
        List list = sqlQuery.list();
        if (list == null || list.size() < 1) {
            result.put(R.DATA_NAME, null);
            result.put("code", 1);
            return result;
        }
        result.put(R.DATA_NAME, list.get(0));
        result.put("code", 0);
        return result;
    }

}
