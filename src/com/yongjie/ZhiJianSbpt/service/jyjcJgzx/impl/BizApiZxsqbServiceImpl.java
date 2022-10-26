package com.yongjie.ZhiJianSbpt.service.jyjcJgzx.impl;

import com.yongjie.ZhiJianSbpt.dao.jyjcJgzx.BizApiZxsqbDao;
import com.yongjie.ZhiJianSbpt.model.jyjcJgzx.BizApiZxsqb;
import com.yongjie.ZhiJianSbpt.service.jyjcJgzx.BizApiZxsqbService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

@Transactional(readOnly = true)
@Service(BizApiZxsqbService.SERVICE_NAME)
public class BizApiZxsqbServiceImpl implements BizApiZxsqbService {

    @Resource(name = BizApiZxsqbDao.SERVICE_NAME)
    private BizApiZxsqbDao dao;

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void save(BizApiZxsqb entity) throws Exception {
        dao.save(entity);
    }

    @Override
    public BizApiZxsqb queryById(Long id) throws Exception {
        return dao.getById(id);
    }

    @Override
    public BizApiZxsqb queryBySerialNumber(String serialNumber) throws Exception {
        return dao.queryBySerialNumber(serialNumber);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void update(BizApiZxsqb entity) throws Exception {
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
}
