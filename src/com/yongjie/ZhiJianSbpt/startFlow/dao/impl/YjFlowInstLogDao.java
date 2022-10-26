package com.yongjie.ZhiJianSbpt.startFlow.dao.impl;

import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.model.YjFlowInstLog;
import com.yongjie.ZhiJianSbpt.startFlow.dao.IYjFlowInstLogDao;
import org.springframework.stereotype.Repository;


@Repository(IYjFlowInstLogDao.SERVICE_NAME)
public class YjFlowInstLogDao extends BaseDaoImpl<YjFlowInstLog> implements IYjFlowInstLogDao {
}
