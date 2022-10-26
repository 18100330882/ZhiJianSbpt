package com.yongjie.ZhiJianSbpt.service;

import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcCd;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcJgzy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ApiShiYsJyjcCdService {

    String SERVICE_NAME = "com.yongjie.ZhiJianXzsp.module.zzrd.service.impl.ApiShiYsJyjcCdServiceImpl";

    void save(ApiShiYsJyjcCd entity) throws Exception;

    void update(ApiShiYsJyjcCd entity) throws Exception;

    void deleteBatch(List<Object> idList) throws Exception;

    ApiShiYsJyjcCd queryById(Long id) throws Exception;

    ApiShiYsJyjcCd queryBySerialNumber(String serialNumber) throws Exception;

    Map<String, Object> queryListBySerialNumber(HashMap<Object, Object> reqMap) throws Exception;

    Map<String, Object> getJgdzData(String serialNumber) throws Exception;

}
