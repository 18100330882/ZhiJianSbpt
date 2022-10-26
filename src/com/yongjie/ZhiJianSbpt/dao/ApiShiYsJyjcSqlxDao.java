package com.yongjie.ZhiJianSbpt.dao;


import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcSqlx;

public interface ApiShiYsJyjcSqlxDao extends BaseDao<ApiShiYsJyjcSqlx> {

    String SERVICE_NAME = "com.yongjie.ZhiJianXzsp.module.zzrd.dao.impl.ApiShiYsJyjcSqlxDaoImpl";


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
