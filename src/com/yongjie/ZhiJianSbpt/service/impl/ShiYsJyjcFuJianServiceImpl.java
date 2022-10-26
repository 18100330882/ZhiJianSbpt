package com.yongjie.ZhiJianSbpt.service.impl;

import com.yongjie.ZhiJianSbpt.dao.ShiYsJyjcFuJianDao;
import com.yongjie.ZhiJianSbpt.model.ShiYsJyjcFuJian;
import com.yongjie.ZhiJianSbpt.model.flowBase.ApiFile;
import com.yongjie.ZhiJianSbpt.model.flowBase.ApiFujianType;
import com.yongjie.ZhiJianSbpt.service.ShiYsJyjcFuJianService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Transactional(readOnly = true)
@Service(ShiYsJyjcFuJianService.SERVICE_NAME)
public class ShiYsJyjcFuJianServiceImpl implements ShiYsJyjcFuJianService {
    @Resource(name = ShiYsJyjcFuJianDao.SERVICE_NAME)
    private ShiYsJyjcFuJianDao dao;

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void save(ShiYsJyjcFuJian entity) throws Exception {
        dao.save(entity);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void update(ShiYsJyjcFuJian entity) throws Exception {
        dao.update(entity);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(Long id) throws Exception {
        dao.delete(id);
    }

    @Override
    public ShiYsJyjcFuJian getJyjcFuJianById(Long id) throws Exception {
        return dao.getById(id);
    }

    @Override
    public ShiYsJyjcFuJian getSqsFujian(String serialNumber) {
        return dao.getSqsFujian(serialNumber);
    }

    @Override
    public HashMap<String, Object> queryListBySerialNumber(HashMap<Object, Object> reqMap) throws Exception {
        return dao.queryListBySerialNumber(reqMap);
    }

    @Override
    public ApiFujianType queryFuJIanTypeByFlag(String flag) {
        return dao.queryFuJIanTypeByFlag(flag);
    }

    @Override
    public ShiYsJyjcFuJian queryBySerialNumber(String serialNumber) {
        return dao.queryBySerialNumber(serialNumber);
    }

    @Override
    public List<ShiYsJyjcFuJian> queryByBatchSerialNumber(String serialNumber, String flag) {
        return dao.queryByBatchSerialNumber(serialNumber, flag);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public int deleteBatchBySerialNumber(String serialNumber) throws Exception {
        return dao.deleteBatchBySerialNumber(serialNumber);
    }

}
