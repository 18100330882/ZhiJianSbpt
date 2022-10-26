package com.yongjie.ZhiJianSbpt.service.impl;

import com.yongjie.ZhiJianSbpt.dao.BizApiShiysjyjcDao;
import com.yongjie.ZhiJianSbpt.model.BizApiShiYsJyjc;
import com.yongjie.ZhiJianSbpt.service.BizApiShiysjyjcService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

@Transactional(readOnly = true)
@Service(BizApiShiysjyjcService.SERVICE_NAME)
public class BizApiShiysjyjcServiceImpl implements BizApiShiysjyjcService {

    @Resource(name = BizApiShiysjyjcDao.SERVICE_NAME)
    private BizApiShiysjyjcDao bizShiysjyjcDao;

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void save(BizApiShiYsJyjc entity) throws Exception {
        bizShiysjyjcDao.save(entity);
    }

    @Override
    public BizApiShiYsJyjc queryById(Long id) throws Exception {
        return bizShiysjyjcDao.getById(id);
    }

    @Override
    public BizApiShiYsJyjc queryBySerialNumber(String serialNumber) throws Exception {
        return bizShiysjyjcDao.queryBySerialNumber(serialNumber);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void update(BizApiShiYsJyjc entity) throws Exception {
        bizShiysjyjcDao.update(entity);
    }

    @Override
    public Map<String, Object> getList(Map<Object, Object> reqMap) throws Exception {
        return bizShiysjyjcDao.getList(reqMap);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(Long id) {
        bizShiysjyjcDao.delete(id);
    }

    @Override
    public BizApiShiYsJyjc getBizShiYsJyjcBySerialNumber(String serialNumber) throws Exception {
        return bizShiysjyjcDao.getBizShiYsJyjcBySerialNumber(serialNumber);
    }
}
