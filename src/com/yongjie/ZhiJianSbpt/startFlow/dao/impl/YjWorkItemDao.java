package com.yongjie.ZhiJianSbpt.startFlow.dao.impl;

import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.startFlow.dao.IYjWorkItemDao;
import com.yongjie.ZhiJianSbpt.startFlow.model.YjWorkItem;
import org.springframework.stereotype.Repository;

@Repository(IYjWorkItemDao.SERVICE_NAME)
public class YjWorkItemDao extends BaseDaoImpl<YjWorkItem> implements IYjWorkItemDao {
}
