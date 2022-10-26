package com.yongjie.ZhiJianSbpt.startFlow.service.impl;

import com.yongjie.ZhiJianSbpt.startFlow.dao.IYjWorkItemDao;
import com.yongjie.ZhiJianSbpt.startFlow.model.YjWorkItem;
import com.yongjie.ZhiJianSbpt.startFlow.service.IYjWorkItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional(readOnly = false)
@Service(IYjWorkItemService.SERVICE_NAME)
public class YjWorkItemService implements IYjWorkItemService {
    @Resource(name = IYjWorkItemDao.SERVICE_NAME)
    private IYjWorkItemDao workItemdao;

    @Override
    public void add(YjWorkItem workModel) {
        workItemdao.save(workModel);
    }
}
