package com.yongjie.ZhiJianSbpt.service;


import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcSqlx;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcSqs;

public interface ApiShiYsJyjcSqlxService {

    String SERVICE_NAME = "com.yongjie.ZhiJianXzsp.module.zzrd.service.impl.ApiShiYsJyjcSqlxServiceImpl";

    /**
     * 保存
     */
    void save(ApiShiYsJyjcSqlx entity) throws Exception;

    /**
     * 更新
     */
    void update(ApiShiYsJyjcSqlx entity) throws Exception;

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     * @throws Exception
     */
    ApiShiYsJyjcSqlx queryById(Long id) throws Exception;

    /**
     * 概况信息
     *
     * @param serialNumber 流水号
     * @return
     */
    ApiShiYsJyjcSqlx queryBySerialNumber(String serialNumber) throws Exception;

    /**
     * 根据流水号删除
     *
     * @param serialNumber
     * @return
     * @throws Exception
     */
    int deleteBatchBySerialNumber(String serialNumber) throws Exception;
}
