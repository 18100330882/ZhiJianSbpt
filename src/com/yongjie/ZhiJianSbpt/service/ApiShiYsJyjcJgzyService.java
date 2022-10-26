package com.yongjie.ZhiJianSbpt.service;


import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcJgzy;

public interface ApiShiYsJyjcJgzyService {

    String SERVICE_NAME = "com.yongjie.ZhiJianXzsp.module.zzrd.service.impl.ApiShiYsJyjcJgzyServiceImpl";

    void save(ApiShiYsJyjcJgzy entity) throws Exception;

    void update(ApiShiYsJyjcJgzy entity) throws Exception;

    ApiShiYsJyjcJgzy queryBySerialNumber(String serialNumber) throws Exception;

    /**
     * 根据流水号删除
     *
     * @param serialNumber
     * @return
     * @throws Exception
     */
    int deleteBatchBySerialNumber(String serialNumber) throws Exception;
}
