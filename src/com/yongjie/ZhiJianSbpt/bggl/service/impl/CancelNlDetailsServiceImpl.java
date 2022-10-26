package com.yongjie.ZhiJianSbpt.bggl.service.impl;

import com.yongjie.ZhiJianSbpt.bggl.dao.CancelNlDetailsDao;
import com.yongjie.ZhiJianSbpt.bggl.model.CancelNlDetails;
import com.yongjie.ZhiJianSbpt.bggl.service.CancelNlDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassName AddressChangeServiceImpl
 * @Date 2022/3/11 16:55
 * @Version 1.0
 */
@Transactional(readOnly = true)
@Service(CancelNlDetailsService.SERVICE_NAME)
public class CancelNlDetailsServiceImpl implements CancelNlDetailsService {

    @Resource(name = CancelNlDetailsDao.SERVICE_NAME)
    private CancelNlDetailsDao dao;

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void save(CancelNlDetails entity) {
        dao.save(entity);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void update(CancelNlDetails entity) {
        dao.update(entity);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void deleteBySerialNum(String serialNumber) {
        dao.deleteBySerialNum(serialNumber);
    }

    @Override
    public CancelNlDetails getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public Map<String, Object> queryListBySerialNum(Map<String, Object> reqMap) {
        return dao.queryListBySerialNum(reqMap);
    }
}
