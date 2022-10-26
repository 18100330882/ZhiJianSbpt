package com.yongjie.ZhiJianSbpt.startFlow.service;


import com.yongjie.ZhiJianSbpt.startFlow.model.CancellationTable;

public interface CancellationTableService {
    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.startFlow.service.impl.CancellationTableServiceImpl";

    CancellationTable getCancellationTable(String serialNumber);

    void update(CancellationTable entity);
}
