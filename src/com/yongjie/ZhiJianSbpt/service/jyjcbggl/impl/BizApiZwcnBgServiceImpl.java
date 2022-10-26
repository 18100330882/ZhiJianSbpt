package com.yongjie.ZhiJianSbpt.service.jyjcbggl.impl;

import com.yongjie.ZhiJianSbpt.dao.jyjcbggl.BizApiZwcnBgDao;
import com.yongjie.ZhiJianSbpt.model.jyjcbggl.BizApiZwcnBg;
import com.yongjie.ZhiJianSbpt.service.jyjcbggl.BizApiZwcnBgService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

@Transactional(readOnly = true)
@Service(BizApiZwcnBgService.SERVICE_NAME)
public class BizApiZwcnBgServiceImpl implements BizApiZwcnBgService {

    @Resource(name = BizApiZwcnBgDao.SERVICE_NAME)
    private BizApiZwcnBgDao dao;

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void save(BizApiZwcnBg entity) throws Exception {
        dao.save(entity);
    }

    @Override
    public BizApiZwcnBg queryById(Long id) throws Exception {
        return dao.getById(id);
    }

    @Override
    public BizApiZwcnBg queryBySerialNumber(String serialNumber) throws Exception {
        return dao.queryBySerialNumber(serialNumber);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void update(BizApiZwcnBg entity) throws Exception {
        dao.update(entity);
    }

    @Override
    public Map<String, Object> getList(Map<Object, Object> reqMap) throws Exception {
        return dao.getList(reqMap);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public int deleteBatchBySerialNumber(String serialNumber) throws Exception {
        return dao.deleteBatchBySerialNumber(serialNumber);
    }

    @Override
    public BizApiZwcnBg getBizApiZwcnBg(String serialNumber, String flag) throws Exception {
        return dao.getBizApiZwcnBg(serialNumber, flag);
    }

    @Override
    public BizApiZwcnBg getById(Long id) {
        return dao.getById(id);
    }
}
