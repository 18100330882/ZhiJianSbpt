package com.yongjie.ZhiJianSbpt.startFlow.dao.impl;

import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.startFlow.dao.UserDao;
import com.yongjie.ZhiJianSbpt.startFlow.model.AccountXzsp;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

@Repository(UserDao.SERVICE_NAME)
public class UserDaoImpl extends BaseDaoImpl<AccountXzsp> implements UserDao {

    @Override
    public AccountXzsp getAccountXzspByUserName(String userName) throws Exception {
        StringBuffer sbf = new StringBuffer();
        sbf.append("select * from ACCOUNTXZSP ");
        // 如果用户名为空,则返回
        if (StringUtil.isEmpty(userName)) {
            return null;
        }
        sbf.append(" where userName=:userName ");

        SQLQuery sqlQuery = getSession().createSQLQuery(sbf.toString());
        sqlQuery.setString("userName", userName);
        sqlQuery.addEntity(AccountXzsp.class);
        return (AccountXzsp) sqlQuery.uniqueResult();
    }
}
