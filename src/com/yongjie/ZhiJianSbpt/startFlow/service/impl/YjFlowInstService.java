package com.yongjie.ZhiJianSbpt.startFlow.service.impl;

import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.startFlow.dao.IYjFlowInstDao;
import com.yongjie.ZhiJianSbpt.startFlow.model.YjFlowInst;
import com.yongjie.ZhiJianSbpt.startFlow.service.IYjFlowInstService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional(readOnly = false)
@Service(IYjFlowInstService.SERVICE_NAME)
public class YjFlowInstService extends BaseDaoImpl<YjFlowInst> implements IYjFlowInstService {

    @Resource(name = IYjFlowInstDao.SERVICE_NAME)
    private IYjFlowInstDao yjFlowInstdao;

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void saveYjFlowInst(YjFlowInst t) throws Exception {
        yjFlowInstdao.save(t);
    }
}
