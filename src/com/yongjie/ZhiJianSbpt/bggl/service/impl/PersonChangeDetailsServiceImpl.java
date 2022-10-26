package com.yongjie.ZhiJianSbpt.bggl.service.impl;

import com.yongjie.ZhiJianSbpt.bggl.dao.PersonChangeDetailsDao;
import com.yongjie.ZhiJianSbpt.bggl.model.PersonChangeDetails;
import com.yongjie.ZhiJianSbpt.bggl.service.PersonChangeDetailsService;
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
@Service(PersonChangeDetailsService.SERVICE_NAME)
public class PersonChangeDetailsServiceImpl implements PersonChangeDetailsService {

    @Resource(name = PersonChangeDetailsDao.SERVICE_NAME)
    private PersonChangeDetailsDao dao;

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void save(PersonChangeDetails entity) {
        dao.save(entity);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void update(PersonChangeDetails entity) {
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
    public PersonChangeDetails getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public Map<String, Object> queryListBySerialNum(Map<String, Object> reqMap) {
        return dao.queryListBySerialNum(reqMap);
    }
}
