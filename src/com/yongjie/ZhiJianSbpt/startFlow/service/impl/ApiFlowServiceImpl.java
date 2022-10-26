package com.yongjie.ZhiJianSbpt.startFlow.service.impl;

import com.yongjie.ZhiJianSbpt.startFlow.dao.ApiFlowDao;
import com.yongjie.ZhiJianSbpt.startFlow.model.ApiFlow;
import com.yongjie.ZhiJianSbpt.startFlow.service.ApiFlowService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Map;

@Transactional(readOnly = true)
@Service(ApiFlowService.SERVICE_NAME)
public class ApiFlowServiceImpl implements ApiFlowService {

    @Resource(name = ApiFlowDao.SERVICE_NAME)
    private ApiFlowDao apiFlowDao;

    @Override
    public ApiFlow getApiFlowByUnid(String uuid) {
        return apiFlowDao.getApiFlowByUnid(uuid);
    }
}
