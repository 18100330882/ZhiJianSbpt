package com.yongjie.ZhiJianSbpt.dao;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcRy;

import java.util.List;
import java.util.Map;

public interface ApiShiYsJyjcRyDao extends BaseDao<ApiShiYsJyjcRy> {

    String SERVICE_NAME = "src.com.yongjie.ZhiJianSbpt.dao.impl.ApiShiYsJyjcQzrDaoImpl";

    Map<String, Object> getList(Map<Object, Object> reqMap);

    ApiShiYsJyjcRy queryBySerialNumber(String serialNumber);

    List<ApiShiYsJyjcRy> queryByBatchSerialNumber(String serialNumber);


    /**
     * 根据流水号删除
     *
     * @param serialNumber
     * @return
     * @throws Exception
     */
    int deleteBatchBySerialNumber(String serialNumber) throws Exception;
}
