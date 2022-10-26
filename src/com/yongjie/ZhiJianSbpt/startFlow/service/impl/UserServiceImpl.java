package com.yongjie.ZhiJianSbpt.startFlow.service.impl;

import com.yongjie.ZhiJianSbpt.startFlow.dao.UserDao;
import com.yongjie.ZhiJianSbpt.startFlow.model.AccountXzsp;
import com.yongjie.ZhiJianSbpt.startFlow.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional(readOnly = true)
@Service(UserService.SERVICE_NAME)
public class UserServiceImpl implements UserService {

    @Resource(name = UserDao.SERVICE_NAME)
    private UserDao userDao;

    @Override
    public AccountXzsp getAccountXzspByUserName(String userName) throws Exception {
        return userDao.getAccountXzspByUserName(userName);
    }
}
