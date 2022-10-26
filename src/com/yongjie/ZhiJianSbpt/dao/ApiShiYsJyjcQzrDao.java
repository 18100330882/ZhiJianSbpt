package com.yongjie.ZhiJianSbpt.dao;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcQzr;

import java.util.List;
import java.util.Map;

public interface ApiShiYsJyjcQzrDao extends BaseDao<ApiShiYsJyjcQzr> {

    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.dao.impl.ApiShiYsJyjcQzrDaoImpl";

    Map<String, Object> getList(Map<Object, Object> reqMap);

    ApiShiYsJyjcQzr queryBySerialNumber(String serialNumber);

    List<ApiShiYsJyjcQzr> queryByBatchSerialNumber(String serialNumber);

    /**
     * 根据流水号删除
     *
     * @param serialNumber
     * @return
     * @throws Exception
     */
    int deleteBatchBySerialNumber(String serialNumber) throws Exception;
}
