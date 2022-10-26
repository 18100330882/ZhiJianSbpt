package com.yongjie.ZhiJianSbpt.service.impl;

import com.yongjie.ZhiJianSbpt.dao.ApiShiYsJyjcRyDao;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcQzr;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcRy;
import com.yongjie.ZhiJianSbpt.service.ApiShiYsJyjcRyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Transactional(readOnly = false)
@Service(ApiShiYsJyjcRyService.SERVICE_NAME)
public class ApiShiYsJyjcRyServiceImpl implements ApiShiYsJyjcRyService {

    @Resource(name = ApiShiYsJyjcRyDao.SERVICE_NAME)
    private ApiShiYsJyjcRyDao apiShiYsJyjcRyDao;

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void save(ApiShiYsJyjcRy entity) throws Exception {
        apiShiYsJyjcRyDao.save(entity);
    }

    @Override
    public ApiShiYsJyjcRy queryById(Long id) throws Exception {
        return apiShiYsJyjcRyDao.getById(id);
    }

    @Override
    public ApiShiYsJyjcRy queryBySerialNumber(String serialNumber) throws Exception {
        return apiShiYsJyjcRyDao.queryBySerialNumber(serialNumber);
    }

    @Override
    public List<ApiShiYsJyjcRy> queryByBatchSerialNumber(String serialNumber) {
        return apiShiYsJyjcRyDao.queryByBatchSerialNumber(serialNumber);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void update(ApiShiYsJyjcRy entity) throws Exception {
        apiShiYsJyjcRyDao.update(entity);
    }

    @Override
    public Map<String, Object> getList(Map<Object, Object> reqMap) {
        return apiShiYsJyjcRyDao.getList(reqMap);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(Long id) {
        apiShiYsJyjcRyDao.delete(id);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public int saveBatch(List<ApiShiYsJyjcRy> list, int size) throws Exception {
        return apiShiYsJyjcRyDao.saveBatch(list, size);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public int deleteBatchBySerialNumber(String serialNumber) throws Exception {
        return apiShiYsJyjcRyDao.deleteBatchBySerialNumber(serialNumber);
    }
}
