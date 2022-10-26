package com.yongjie.ZhiJianSbpt.dao.impl;

import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.dao.ApiShiYsJyjcSqlxDao;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcSqlx;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

@Repository(ApiShiYsJyjcSqlxDao.SERVICE_NAME)
public class ApiShiYsJyjcSqlxDaoImpl extends BaseDaoImpl<ApiShiYsJyjcSqlx> implements ApiShiYsJyjcSqlxDao {


    @Override
    public ApiShiYsJyjcSqlx queryBySerialNumber(String serialNumber) throws Exception {
        if (StringUtil.isNullOrEmpty(serialNumber)) {
            return null;
        }
        StringBuffer sbf = new StringBuffer();
        sbf.append("SELECT * FROM API_SHIYSJYJC_SQLX WHERE SERIALNUMBER='" + serialNumber + "'");
        SQLQuery sqlQuery = getSession().createSQLQuery(sbf.toString()).addEntity(ApiShiYsJyjcSqlx.class);
        return (ApiShiYsJyjcSqlx) sqlQuery.uniqueResult();
    }

    @Override
    public int deleteBatchBySerialNumber(String serialNumber) throws Exception {

        if (StringUtil.isEmpty(serialNumber)) {
            return 0;
        }
        StringBuffer selectSql = new StringBuffer();
        selectSql.append("delete from API_SHIYSJYJC_SQLX where serialNumber=:serialNumber ");

        SQLQuery selectQuery = getSession().createSQLQuery(selectSql.toString());
        selectQuery.setString("serialNumber", serialNumber);

        return selectQuery.executeUpdate();
    }
}
