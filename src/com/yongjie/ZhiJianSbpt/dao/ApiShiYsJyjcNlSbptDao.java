package com.yongjie.ZhiJianSbpt.dao;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcNl;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcNlSbpt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ApiShiYsJyjcNlSbptDao extends BaseDao<ApiShiYsJyjcNlSbpt> {

    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.dao.impl.ApiShiYsJyjcNlSbptDaoImpl";

    void deleteEvent(HashMap<String, Object> reqMap);

    Map<String, Object> getDetailList(HashMap<String, Object> reqMap);

    Map<String, Object> queryList(HashMap<String, Object> reqMap);

    ArrayList<ApiShiYsJyjcNlSbpt> queryListByTj(Map map) throws Exception;

    List queryListBySelect(Map map);

    List queryYjfl(Map map);
}
 
