package com.yongjie.ZhiJianSbpt.startFlow.service;


import com.yongjie.ZhiJianSbpt.startFlow.model.ApiFlow;

import java.util.ArrayList;
import java.util.Map;

public interface ApiFlowService {

    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.startFlow.service.impl.ApiFlowServiceImpl";

    ApiFlow getApiFlowByUnid(String s);
}
