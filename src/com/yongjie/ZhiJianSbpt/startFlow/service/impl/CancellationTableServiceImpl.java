package com.yongjie.ZhiJianSbpt.startFlow.service.impl;

import com.yongjie.ZhiJianSbpt.startFlow.dao.CancellationTableDao;
import com.yongjie.ZhiJianSbpt.startFlow.model.CancellationTable;
import com.yongjie.ZhiJianSbpt.startFlow.service.CancellationTableService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional(readOnly = true)
@Service(CancellationTableService.SERVICE_NAME)
public class CancellationTableServiceImpl implements CancellationTableService {

    @Resource(name = CancellationTableDao.SERVICE_NAME)
    private CancellationTableDao dao;

    @Override
    public CancellationTable getCancellationTable(String serialNumber) {
        return dao.getCancellationTable(serialNumber);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void update(CancellationTable entity) {
        dao.update(entity);
    }

}
