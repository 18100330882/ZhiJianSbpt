package com.yongjie.ZhiJianSbpt.dao;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.model.BizApiShiYsJyjc;

import java.util.Map;

public interface BizApiShiysjyjcDao extends BaseDao<BizApiShiYsJyjc> {

    String SERVICE_NAME = "com.yongjie.ZhiJianXzsp.module.zzrd.dao.impl.BizApiShiysjyjcImpl";

    Map<String, Object> getList(Map<Object, Object> reqMap) throws Exception;

    BizApiShiYsJyjc queryBySerialNumber(String serialNumber) throws Exception;

    BizApiShiYsJyjc getBizShiYsJyjcBySerialNumber(String serialNumber) throws Exception;
}
