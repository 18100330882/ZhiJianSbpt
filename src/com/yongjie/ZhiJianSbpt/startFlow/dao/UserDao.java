package com.yongjie.ZhiJianSbpt.startFlow.dao;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.startFlow.model.AccountXzsp;

public interface UserDao extends BaseDao<AccountXzsp> {

    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.startFlow.dao.impl.UserDaoImpl";

    AccountXzsp getAccountXzspByUserName(String userName) throws Exception;
}
