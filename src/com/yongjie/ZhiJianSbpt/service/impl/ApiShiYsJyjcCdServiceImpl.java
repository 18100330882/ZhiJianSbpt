package com.yongjie.ZhiJianSbpt.service.impl;

import com.yongjie.ZhiJianSbpt.dao.ApiShiYsJyjcCdDao;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcCd;
import com.yongjie.ZhiJianSbpt.service.ApiShiYsJyjcCdService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(readOnly = true)
@Service(ApiShiYsJyjcCdService.SERVICE_NAME)
public class ApiShiYsJyjcCdServiceImpl implements ApiShiYsJyjcCdService {

    @Resource(name = ApiShiYsJyjcCdDao.SERVICE_NAME)
    private ApiShiYsJyjcCdDao dao;


    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void save(ApiShiYsJyjcCd entity) throws Exception {
        dao.save(entity);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void update(ApiShiYsJyjcCd entity) throws Exception {
        dao.update(entity);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteBatch(List<Object> idList) throws Exception {
        for (int i = 0; i < idList.size(); i++) {
            int id = (int) idList.get(i);
            dao.delete((long) id);
        }
    }

    @Override
    public ApiShiYsJyjcCd queryById(Long id) throws Exception {
        return dao.getById(id);
    }

    @Override
    public ApiShiYsJyjcCd queryBySerialNumber(String serialNumber) throws Exception {
        return dao.queryBySerialNumber(serialNumber);
    }

    @Override
    public Map<String, Object> queryListBySerialNumber(HashMap<Object, Object> reqMap) throws Exception {
        return dao.queryListBySerialNumber(reqMap);
    }

    @Override
    public Map<String, Object> getJgdzData(String serialNumber) throws Exception {
        return dao.getJgdzData(serialNumber);
    }
}
