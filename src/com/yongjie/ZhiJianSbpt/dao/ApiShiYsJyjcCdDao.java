package com.yongjie.ZhiJianSbpt.dao;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcCd;

import java.util.HashMap;
import java.util.Map;

public interface ApiShiYsJyjcCdDao extends BaseDao<ApiShiYsJyjcCd> {

    String SERVICE_NAME = "com.yongjie.ZhiJianXzsp.module.zzrd.dao.impl.ApiShiYsJyjcCdDaoImpl";

    ApiShiYsJyjcCd queryBySerialNumber(String serialNumber) throws Exception;

    Map<String, Object> queryListBySerialNumber(HashMap<Object, Object> reqMap) throws Exception;

    Map<String, Object> getJgdzData(String serialNumber) throws Exception;

}
