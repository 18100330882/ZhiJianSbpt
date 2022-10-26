package com.yongjie.ZhiJianSbpt.dao;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcJgzy;

public interface ApiShiYsJyjcJgzyDao extends BaseDao<ApiShiYsJyjcJgzy> {

    String SERVICE_NAME = "com.yongjie.ZhiJianXzsp.module.zzrd.dao.impl.ApiShiYsJyjcJgzyDaoImpl";

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
