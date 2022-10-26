package com.yongjie.ZhiJianSbpt.startFlow.service;

import com.yongjie.ZhiJianSbpt.model.YjFlowInstLog;

public interface IYjFlowInstLogService {
    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.startFlow.service.impl.YjFlowInstLogService";

    void addYjFlowInstLog(YjFlowInstLog logModel);
}
