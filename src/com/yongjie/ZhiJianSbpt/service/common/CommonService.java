package com.yongjie.ZhiJianSbpt.service.common;

import java.util.Map;

public interface CommonService {
    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.dao.impl.CommonServiceImpl";

    Map<String, Object> getShiysjyjcScType(Map<Object, Object> paramMap) throws Exception;

    Map<String, Object> getSerialNumber() throws Exception;

    Map<String, Object> getZgbmData();
}
