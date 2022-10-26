package com.yongjie.ZhiJianSbpt.startFlow.service.impl;

import com.yongjie.ZhiJianSbpt.model.YjFlowInstLog;
import com.yongjie.ZhiJianSbpt.startFlow.dao.IYjFlowInstLogDao;
import com.yongjie.ZhiJianSbpt.startFlow.service.IYjFlowInstLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Transactional(readOnly = false)
@Service(IYjFlowInstLogService.SERVICE_NAME)
public class YjFlowInstLogService implements IYjFlowInstLogService {

    @Resource(name = IYjFlowInstLogDao.SERVICE_NAME)
    private IYjFlowInstLogDao logdao;

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void addYjFlowInstLog(YjFlowInstLog flowInstLog) {
        logdao.save(flowInstLog);
    }
}
