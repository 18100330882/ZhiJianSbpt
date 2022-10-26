package com.yongjie.ZhiJianSbpt.startFlow.dao.impl;


import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.model.flowBase.YjFlow;
import com.yongjie.ZhiJianSbpt.startFlow.dao.BpsFlowDao;
import org.springframework.stereotype.Repository;

@Repository(BpsFlowDao.SERVICE_NAME)
public class BpsFlowDaoImpl extends BaseDaoImpl<YjFlow> implements BpsFlowDao {
}
