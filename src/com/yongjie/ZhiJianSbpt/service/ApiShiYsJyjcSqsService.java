package com.yongjie.ZhiJianSbpt.service;


import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcSqs;

import java.util.Map;

public interface ApiShiYsJyjcSqsService {

    String SERVICE_NAME = "com.yongjie.ZhiJianXzsp.module.zzrd.service.impl.ApiShiYsJyjcSqsServiceImpl";

    /**
     * 保存
     */
    void save(ApiShiYsJyjcSqs entity) throws Exception;

    /**
     * 更新
     */
    void update(ApiShiYsJyjcSqs entity) throws Exception;

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     * @throws Exception
     */
    ApiShiYsJyjcSqs queryById(Long id) throws Exception;

    /**
     * 概况信息
     *
     * @param serialNumber 流水号
     * @return
     */
    ApiShiYsJyjcSqs queryBySerialNumber(String serialNumber) throws Exception;

    /**
     * 根据流水号批量删除
     *
     * @param serialNumber
     * @return
     * @throws Exception
     */
    int deleteBatchBySerialNumber(String serialNumber) throws Exception;

    Map queryBySerialNumber2(String serialNumber);
}
