package com.yongjie.ZhiJianSbpt.bggl.service.impl;

import com.yongjie.ZhiJianSbpt.bggl.dao.StandardChangeDetailsDao;
import com.yongjie.ZhiJianSbpt.bggl.model.StandardChangeDetails;
import com.yongjie.ZhiJianSbpt.bggl.service.StandardChangeDetailsService;
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
@Service(StandardChangeDetailsService.SERVICE_NAME)
public class StandardChangeDetailsServiceImpl implements StandardChangeDetailsService {

    @Resource(name = StandardChangeDetailsDao.SERVICE_NAME)
    private StandardChangeDetailsDao dao;

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void save(StandardChangeDetails entity) {
        dao.save(entity);
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

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void update(StandardChangeDetails entity) {
        dao.update(entity);
    }

    @Override
    public StandardChangeDetails getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public Map<String, Object> queryListBySerialNum(Map<String, Object> reqMap) {
        return dao.queryListBySerialNum(reqMap);
    }
}
