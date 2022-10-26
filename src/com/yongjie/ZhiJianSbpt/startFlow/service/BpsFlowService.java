package com.yongjie.ZhiJianSbpt.startFlow.service;

import java.util.Map;

public interface BpsFlowService {

    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.startFlow.service.impl.BpsFlowServiceImpl";

    /**
     * 资质认定流程启动
     */
    Long zzrdStartFlow(Map<String, Object> map, Map<String, Object> fileMap) throws Exception;
}
