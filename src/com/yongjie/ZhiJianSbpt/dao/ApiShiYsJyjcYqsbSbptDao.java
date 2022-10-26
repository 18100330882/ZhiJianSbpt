package com.yongjie.ZhiJianSbpt.dao;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcYqsbSbpt;

import java.util.HashMap;
import java.util.Map;

public interface ApiShiYsJyjcYqsbSbptDao extends BaseDao<ApiShiYsJyjcYqsbSbpt> {

    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.dao.impl.ApiShiYsJyjcYqsbSbptDaoImpl";

    void deleteEvent(HashMap<String, Object> reqMap);

    Map<String, Object> getDetailList(HashMap<String, Object> reqMap);
}
 
