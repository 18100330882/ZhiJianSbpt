package com.yongjie.ZhiJianSbpt.startFlow.dao.impl;

import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.model.flowBase.YjFlow;
import com.yongjie.ZhiJianSbpt.startFlow.dao.IYjFlowDao;
import org.springframework.stereotype.Repository;

@Repository(IYjFlowDao.SERVICE_NAME)
public class YjFlowDao extends BaseDaoImpl<YjFlow> implements IYjFlowDao {
}
