package com.yongjie.ZhiJianSbpt.startFlow.service;

import com.yongjie.ZhiJianSbpt.startFlow.model.AccountXzsp;

public interface UserService {

    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.startFlow.service.impl.UserServiceImpl";

    AccountXzsp getAccountXzspByUserName(String userName) throws Exception;
}
