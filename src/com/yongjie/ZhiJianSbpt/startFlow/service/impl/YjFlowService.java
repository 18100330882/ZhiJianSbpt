package com.yongjie.ZhiJianSbpt.startFlow.service.impl;

import com.yongjie.ZhiJianSbpt.model.flowBase.YjFlow;
import com.yongjie.ZhiJianSbpt.startFlow.dao.IYjFlowDao;
import com.yongjie.ZhiJianSbpt.startFlow.service.IYjFlowService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional(readOnly = false)
@Service(IYjFlowService.SERVICE_NAME)
public class YjFlowService implements IYjFlowService {

    @Resource(name = IYjFlowDao.SERVICE_NAME)
    private IYjFlowDao flowDao;

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public YjFlow getFlowById(long id) {
        return flowDao.getById(id);
    }
}
