package com.yongjie.ZhiJianSbpt.startFlow.dao;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.startFlow.model.ApiFlow;

import java.util.ArrayList;
import java.util.Map;

public interface ApiFlowDao extends BaseDao<ApiFlow> {

    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.startFlow.dao.impl.ApiFlowDaoImpl";

    ApiFlow getApiFlowByUnid(String uuid);
}
