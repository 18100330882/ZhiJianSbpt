package com.yongjie.ZhiJianSbpt.service;

import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcNl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ApiShiYsJyjcNlService {

    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.service.impl.ApiShiYsJyjcNlServiceImpl";

    int saveBatch(List<ApiShiYsJyjcNl> list, int size) throws Exception;

    void deleteEvent(HashMap<String, Object> reqMap);

    Map<String, Object> getDetailList(HashMap<String, Object> reqMap);

    void deleteRow(long id);

    void updateRow(ApiShiYsJyjcNl entity);

    void addRow(ApiShiYsJyjcNl entity);

    List<ApiShiYsJyjcNl> reSetXh(HashMap<String, Object> reqMap) throws Exception;

    List<ApiShiYsJyjcNl> reSetXhYqsb(HashMap<String, Object> reqMap) throws Exception;

    void updateList(List<ApiShiYsJyjcNl> entityList);

    void delnl(String params);

    void update(ApiShiYsJyjcNl apiShiYsJyjcNl);

    ApiShiYsJyjcNl getById(Long id);
}
 