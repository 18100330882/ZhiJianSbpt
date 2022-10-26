package com.yongjie.ZhiJianSbpt.service.common.impl;

import com.yongjie.ZhiJianSbpt.dao.common.CommonDao;
import com.yongjie.ZhiJianSbpt.service.common.CommonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

@Transactional(readOnly = true)
@Service(CommonService.SERVICE_NAME)
public class CommonServiceImpl implements CommonService {

    @Resource(name = CommonDao.SERVICE_NAME)
    private CommonDao dao;

    @Override
    public Map<String, Object> getShiysjyjcScType(Map<Object, Object> paramMap) throws Exception {
        return dao.getShiysjyjcScType(paramMap);
    }

    @Override
    public Map<String, Object> getSerialNumber() throws Exception {
        return dao.getSerialNumber();
    }

    @Override
    public Map<String, Object> getZgbmData() {
        return dao.getZgbmData();
    }
}
