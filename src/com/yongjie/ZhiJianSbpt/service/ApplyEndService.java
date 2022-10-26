package com.yongjie.ZhiJianSbpt.service;

import com.yongjie.ZhiJianSbpt.model.ApplyEnd;

import java.util.HashMap;

public interface ApplyEndService {
    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.dao.impl.ApplyEndServiceImpl";

    void save(ApplyEnd entity);

    ApplyEnd queryBySerialNumber(String serialNumber);

    HashMap queryListBySerialNumber(HashMap reqMap);

    void deleteBySerialNum(String serialNum);
}
