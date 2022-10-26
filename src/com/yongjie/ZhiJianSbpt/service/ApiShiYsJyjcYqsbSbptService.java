package com.yongjie.ZhiJianSbpt.service;

import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcNlSbpt;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcYqsbSbpt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ApiShiYsJyjcYqsbSbptService {

    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.service.impl.ApiShiYsJyjcYqsbSbptServiceImpl";

    int saveBatch(List<ApiShiYsJyjcYqsbSbpt> list, int size) throws Exception;

    void deleteEvent(HashMap<String, Object> reqMap);

    Map<String, Object> getDetailList(HashMap<String, Object> reqMap);

    void deleteRow(long id);

    void updateRow(ApiShiYsJyjcYqsbSbpt entity);

    void addRow(ApiShiYsJyjcYqsbSbpt entity);

    List<ApiShiYsJyjcYqsbSbpt> reSetXh(HashMap<String, Object> reqMap) throws Exception;

    List<ApiShiYsJyjcYqsbSbpt> reSetXhYqsb(HashMap<String, Object> reqMap) throws Exception;

    void updateList(List<ApiShiYsJyjcYqsbSbpt> entityList);
}
 