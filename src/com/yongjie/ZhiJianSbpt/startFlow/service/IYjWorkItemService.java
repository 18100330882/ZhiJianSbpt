package com.yongjie.ZhiJianSbpt.startFlow.service;


import com.yongjie.ZhiJianSbpt.startFlow.model.YjWorkItem;

public interface IYjWorkItemService {
    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.startFlow.service.impl.YjWorkItemService";

    void add(YjWorkItem workModel);
}
