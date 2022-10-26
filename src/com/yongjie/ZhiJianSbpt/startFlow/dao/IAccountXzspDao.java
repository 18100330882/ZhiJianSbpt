package com.yongjie.ZhiJianSbpt.startFlow.dao;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.startFlow.model.AccountXzsp;

import java.util.HashMap;

public interface IAccountXzspDao extends BaseDao<AccountXzsp> {
    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.startFlow.dao.impl.AccountXzspDao";

    AccountXzsp getAccountById(long parseLong);

    String getUserIds(String value);

    HashMap getUserModel(String currentUser) throws Exception;
}
