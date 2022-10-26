/**
 * @Author: 杨青岭
 * @Createtime: 2016年8月8日 上午11:36:22
 * @Copyright: Copyright (c) 2016
 * @Company: 北京永杰友信科技有限公司
 * @Version：1.0
 * @Description:
 */
package com.yongjie.ZhiJianSbpt.service.impl;

import com.yongjie.ZhiJianSbpt.dao.ICloginDao;
import com.yongjie.ZhiJianSbpt.model.AccountSbpt;
import com.yongjie.ZhiJianSbpt.service.ICloginService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = false)
@Service(ICloginService.SERVICE_NAME)
public class CloginService implements ICloginService {
    @Resource(name = ICloginDao.SERVICE_NAME)
    private ICloginDao cloginDao;

    @Override
    public List<AccountSbpt> doLogin(String username, String password) {
        return cloginDao.doLogin(username, password);
    }

    @Override
    public List<AccountSbpt> checkAction(String username) {
        return cloginDao.checkAction(username);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public ArrayList getMenusByUsername(String logOnUserName, String sysFlag) {
        return cloginDao.getMenusByUsername(logOnUserName, sysFlag);
    }

    @Override
    public ArrayList getScrollList(String userName) {
        return cloginDao.getScrollList(userName);
    }

    @Override
    public ArrayList getTrueName(String userName) {
        // TODO Auto-generated method stub
        return cloginDao.getTrueName(userName);
    }

    @Override
    public ArrayList getAccountSbptByName(String username) {
        // TODO Auto-generated method stub
        return cloginDao.getAccountSbptByName(username);
    }

    @Override
    public AccountSbpt getAccountSbptByUserName(String userName) throws Exception {
        return cloginDao.getAccountSbptByUserName(userName);
    }
}
