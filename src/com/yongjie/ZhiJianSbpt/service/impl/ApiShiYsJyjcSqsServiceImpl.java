package com.yongjie.ZhiJianSbpt.service.impl;


import com.yongjie.ZhiJianSbpt.dao.ApiShiYsJyjcSqsDao;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcSqs;
import com.yongjie.ZhiJianSbpt.service.ApiShiYsJyjcSqsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

@Transactional(readOnly = true)
@Service(ApiShiYsJyjcSqsService.SERVICE_NAME)
public class ApiShiYsJyjcSqsServiceImpl implements ApiShiYsJyjcSqsService {

    @Resource(name = ApiShiYsJyjcSqsDao.SERVICE_NAME)
    private ApiShiYsJyjcSqsDao dao;

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void save(ApiShiYsJyjcSqs entity) throws Exception {
        dao.save(entity);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void update(ApiShiYsJyjcSqs entity) throws Exception {
        dao.update(entity);
    }

    @Override
    public ApiShiYsJyjcSqs queryById(Long id) throws Exception {
        return dao.getById(id);
    }

    @Override
    public ApiShiYsJyjcSqs queryBySerialNumber(String serialNumber) throws Exception {
        return dao.queryBySerialNumber(serialNumber);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public int deleteBatchBySerialNumber(String serialNumber) throws Exception {
        return dao.deleteBatchBySerialNumber(serialNumber);
    }

    @Override
    public Map queryBySerialNumber2(String serialNumber) {
        return dao.deleteBatchBySerialNumber2(serialNumber);
    }
}
