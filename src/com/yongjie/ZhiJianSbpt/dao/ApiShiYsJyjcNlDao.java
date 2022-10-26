package com.yongjie.ZhiJianSbpt.dao;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcNl;

import java.util.HashMap;
import java.util.Map;

public interface ApiShiYsJyjcNlDao extends BaseDao<ApiShiYsJyjcNl> {

    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.dao.impl.ApiShiYsJyjcNlDaoImpl";

    void deleteEvent(HashMap<String, Object> reqMap);

    Map<String, Object> getDetailList(HashMap<String, Object> reqMap);

    void delnl(String params);
}
 
