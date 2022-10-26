package com.yongjie.ZhiJianSbpt.service.impl;

import com.yongjie.ZhiJianSbpt.dao.ApplyEndDao;
import com.yongjie.ZhiJianSbpt.model.ApplyEnd;
import com.yongjie.ZhiJianSbpt.service.ApiShiYsJyjcSqsService;
import com.yongjie.ZhiJianSbpt.service.ApplyEndService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;

@Transactional(readOnly = true)
@Service(ApplyEndService.SERVICE_NAME)
public class ApplyEndServiceImpl implements ApplyEndService {
    @Resource(name = ApplyEndDao.SERVICE_NAME)
    private ApplyEndDao dao;

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void save(ApplyEnd entity) {
        dao.save(entity);
    }

    @Override
    public ApplyEnd queryBySerialNumber(String serialNumber) {
        return dao.queryBySerialNumber(serialNumber);
    }

    @Override
    public HashMap queryListBySerialNumber(HashMap reqMap) {
        return dao.queryListBySerialNumber(reqMap);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void deleteBySerialNum(String serialNum) {
        dao.deleteBySerialNum(serialNum);
    }
}
