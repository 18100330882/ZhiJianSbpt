package com.yongjie.ZhiJianSbpt.startFlow.dao;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.startFlow.model.YjFlowInst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IYjFlowInstDao extends BaseDao<YjFlowInst> {
    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.startFlow.dao.impl.YjFlowInstDao";

    int updateBiz(String tabName, long flowInstId, String nodeId, long sqsId);
}
