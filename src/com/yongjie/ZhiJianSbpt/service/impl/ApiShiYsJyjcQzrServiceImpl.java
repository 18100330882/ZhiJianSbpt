package com.yongjie.ZhiJianSbpt.service.impl;

import com.yongjie.ZhiJianSbpt.dao.ApiShiYsJyjcQzrDao;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcQzr;
import com.yongjie.ZhiJianSbpt.model.flowBase.ApiFile;
import com.yongjie.ZhiJianSbpt.service.ApiShiYsJyjcQzrService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Transactional(readOnly = true)
@Service(ApiShiYsJyjcQzrService.SERVICE_NAME)
public class ApiShiYsJyjcQzrServiceImpl implements ApiShiYsJyjcQzrService {

    @Resource(name = ApiShiYsJyjcQzrDao.SERVICE_NAME)
    private ApiShiYsJyjcQzrDao apiShiYsJyjcQzrDao;

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void save(ApiShiYsJyjcQzr entity) throws Exception {
        apiShiYsJyjcQzrDao.save(entity);
    }

    @Override
    public ApiShiYsJyjcQzr queryById(Long id) throws Exception {
        return apiShiYsJyjcQzrDao.getById(id);
    }

    @Override
    public ApiShiYsJyjcQzr queryBySerialNumber(String serialNumber) throws Exception {
        return apiShiYsJyjcQzrDao.queryBySerialNumber(serialNumber);
    }

    @Override
    public List<ApiShiYsJyjcQzr> queryByBatchSerialNumber(String serialNumber) {
        return apiShiYsJyjcQzrDao.queryByBatchSerialNumber(serialNumber);
    }


    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void update(ApiShiYsJyjcQzr entity) throws Exception {
        apiShiYsJyjcQzrDao.update(entity);
    }

    @Override
    public Map<String, Object> getList(Map<Object, Object> reqMap) {
        return apiShiYsJyjcQzrDao.getList(reqMap);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(Long id) {
        apiShiYsJyjcQzrDao.delete(id);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public int saveBatch(List<ApiShiYsJyjcQzr> list, int size) throws Exception {
        return apiShiYsJyjcQzrDao.saveBatch(list, size);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public int deleteBatchBySerialNumber(String serialNumber) throws Exception {
        return apiShiYsJyjcQzrDao.deleteBatchBySerialNumber(serialNumber);
    }
}
