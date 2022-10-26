package com.yongjie.ZhiJianSbpt.startFlow.service;

import com.yongjie.ZhiJianSbpt.startFlow.model.YjFlowInst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface IYjFlowInstService {
    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.startFlow.service.impl.YjFlowInstService";

    void saveYjFlowInst(YjFlowInst t) throws Exception;
}
