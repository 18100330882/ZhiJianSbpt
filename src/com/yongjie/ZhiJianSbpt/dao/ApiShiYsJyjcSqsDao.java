package com.yongjie.ZhiJianSbpt.dao;


import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcSqs;

import java.util.Map;

public interface ApiShiYsJyjcSqsDao extends BaseDao<ApiShiYsJyjcSqs> {

    String SERVICE_NAME = "com.yongjie.ZhiJianXzsp.module.zzrd.dao.impl.ApiShiYsJyjcSqsDaoImpl";


    ApiShiYsJyjcSqs queryBySerialNumber(String serialNumber) throws Exception;

    /**
     * 根据流水号删除
     *
     * @param serialNumber
     * @return
     * @throws Exception
     */
    int deleteBatchBySerialNumber(String serialNumber) throws Exception;

    Map deleteBatchBySerialNumber2(String serialNumber);
}
