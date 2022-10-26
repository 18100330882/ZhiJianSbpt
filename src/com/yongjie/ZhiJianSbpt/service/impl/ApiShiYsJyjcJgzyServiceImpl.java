package com.yongjie.ZhiJianSbpt.service.impl;

import com.yongjie.ZhiJianSbpt.dao.ApiShiYsJyjcJgzyDao;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcJgzy;
import com.yongjie.ZhiJianSbpt.service.ApiShiYsJyjcJgzyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional(readOnly = true)
@Service(ApiShiYsJyjcJgzyService.SERVICE_NAME)
public class ApiShiYsJyjcJgzyServiceImpl implements ApiShiYsJyjcJgzyService {

    @Resource(name = ApiShiYsJyjcJgzyDao.SERVICE_NAME)
    private ApiShiYsJyjcJgzyDao dao;

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void save(ApiShiYsJyjcJgzy entity) throws Exception {
        dao.save(entity);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void update(ApiShiYsJyjcJgzy entity) throws Exception {
        dao.update(entity);
    }

    @Override
    public ApiShiYsJyjcJgzy queryBySerialNumber(String serialNumber) throws Exception {
        return dao.queryBySerialNumber(serialNumber);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public int deleteBatchBySerialNumber(String serialNumber) throws Exception {
        return dao.deleteBatchBySerialNumber(serialNumber);
    }
}
