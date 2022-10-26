package com.yongjie.ZhiJianSbpt.dao.impl;

import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.dao.ApiShiYsJyjcJgzyDao;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcJgzy;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

@Repository(ApiShiYsJyjcJgzyDao.SERVICE_NAME)
public class ApiShiYsJyjcJgzyDaoImpl extends BaseDaoImpl<ApiShiYsJyjcJgzy> implements ApiShiYsJyjcJgzyDao {

    @Override
    public ApiShiYsJyjcJgzy queryBySerialNumber(String serialNumber) throws Exception {
        if (StringUtil.isNullOrEmpty(serialNumber)) {
            return null;
        }
        StringBuffer sbf = new StringBuffer();
        sbf.append("SELECT * FROM API_SHIYSJYJC_JGZY WHERE SERIALNUMBER='" + serialNumber + "'");
        SQLQuery sqlQuery = getSession().createSQLQuery(sbf.toString()).addEntity(ApiShiYsJyjcJgzy.class);
        return (ApiShiYsJyjcJgzy) sqlQuery.uniqueResult();
    }

    @Override
    public int deleteBatchBySerialNumber(String serialNumber) throws Exception {

        if (StringUtil.isEmpty(serialNumber)) {
            return 0;
        }
        StringBuffer selectSql = new StringBuffer();
        selectSql.append("delete from API_SHIYSJYJC_JGZY where serialNumber=:serialNumber ");

        SQLQuery selectQuery = getSession().createSQLQuery(selectSql.toString());
        selectQuery.setString("serialNumber", serialNumber);

        return selectQuery.executeUpdate();
    }
}
