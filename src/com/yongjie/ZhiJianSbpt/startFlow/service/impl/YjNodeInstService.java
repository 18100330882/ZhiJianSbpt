package com.yongjie.ZhiJianSbpt.startFlow.service.impl;

import com.yongjie.ZhiJianSbpt.startFlow.dao.IYjNodeInstDao;
import com.yongjie.ZhiJianSbpt.startFlow.model.YjNodeInst;
import com.yongjie.ZhiJianSbpt.startFlow.service.IYjNodeInstService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional(readOnly = false)
@Service(IYjNodeInstService.SERVICE_NAME)
public class YjNodeInstService implements IYjNodeInstService {

    @Resource(name = IYjNodeInstDao.SERVICE_NAME)
    private IYjNodeInstDao yjNodeInstDao;

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void addYjNodeInst(YjNodeInst yjNodeInst) throws Exception {
        yjNodeInstDao.save(yjNodeInst);
    }
}
