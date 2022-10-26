package com.yongjie.ZhiJianSbpt.startFlow.dao.impl;

import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.startFlow.dao.IYjNodeInstDao;
import com.yongjie.ZhiJianSbpt.startFlow.model.YjNodeInst;
import org.springframework.stereotype.Repository;

@Repository(IYjNodeInstDao.SERVICE_NAME)
public class YjNodeInstDao extends BaseDaoImpl<YjNodeInst> implements IYjNodeInstDao {
}
