package com.yongjie.ZhiJianSbpt.startFlow.service;


import com.yongjie.ZhiJianSbpt.model.flowBase.YjFlow;

import java.util.ArrayList;
import java.util.HashMap;

public interface IYjFlowService {
    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.startFlow.service.impl.YjFlowService";

    YjFlow getFlowById(long id);
}
