package com.yongjie.ZhiJianSbpt.service.impl;

import com.yongjie.ZhiJianSbpt.dao.ApiShiYsJyjcSqlxDao;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcSqlx;
import com.yongjie.ZhiJianSbpt.service.ApiShiYsJyjcSqlxService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional(readOnly = true)
@Service(ApiShiYsJyjcSqlxService.SERVICE_NAME)
public class ApiShiYsJyjcSqlxServiceImpl implements ApiShiYsJyjcSqlxService {

    @Resource(name = ApiShiYsJyjcSqlxDao.SERVICE_NAME)
    private ApiShiYsJyjcSqlxDao dao;


    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void save(ApiShiYsJyjcSqlx entity) throws Exception {
        dao.save(entity);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void update(ApiShiYsJyjcSqlx entity) throws Exception {
        dao.update(entity);
    }

    @Override
    public ApiShiYsJyjcSqlx queryById(Long id) throws Exception {
        return dao.getById(id);
    }

    @Override
    public ApiShiYsJyjcSqlx queryBySerialNumber(String serialNumber) throws Exception {
        return dao.queryBySerialNumber(serialNumber);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public int deleteBatchBySerialNumber(String serialNumber) throws Exception {
        return dao.deleteBatchBySerialNumber(serialNumber);
    }
}
