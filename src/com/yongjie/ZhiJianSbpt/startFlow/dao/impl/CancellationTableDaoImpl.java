package com.yongjie.ZhiJianSbpt.startFlow.dao.impl;

import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.startFlow.dao.CancellationTableDao;
import com.yongjie.ZhiJianSbpt.startFlow.model.CancellationTable;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(CancellationTableDao.SERVICE_NAME)
public class CancellationTableDaoImpl extends BaseDaoImpl<CancellationTable> implements CancellationTableDao {

    @Override
    public CancellationTable getCancellationTable(String serialNumber) {
        StringBuffer selectSql = new StringBuffer();

        selectSql.append("select * from BIZ_API_ZXSQB where 1=1 ");

        if (!StringUtil.isEmpty(serialNumber)) {
            selectSql.append(" and serialNumber=:serialNumber ");
        }
        SQLQuery selectQuery = getSession().createSQLQuery(selectSql.toString()).addEntity(CancellationTable.class);
        if (!StringUtil.isEmpty(serialNumber)) {
            selectQuery.setString("serialNumber", serialNumber);
        }

        return (CancellationTable) selectQuery.uniqueResult();
    }
}
