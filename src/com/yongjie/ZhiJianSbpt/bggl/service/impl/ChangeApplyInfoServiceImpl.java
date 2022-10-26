package com.yongjie.ZhiJianSbpt.bggl.service.impl;

import com.yongjie.ZhiJianSbpt.bggl.dao.ChangeApplyInfoDao;
import com.yongjie.ZhiJianSbpt.bggl.model.ChangeApplyInfo;
import com.yongjie.ZhiJianSbpt.bggl.service.ChangeApplyInfoService;
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
@Service(ChangeApplyInfoService.SERVICE_NAME)
public class ChangeApplyInfoServiceImpl implements ChangeApplyInfoService {

    @Resource(name = ChangeApplyInfoDao.SERVICE_NAME)
    private ChangeApplyInfoDao dao;

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void save(ChangeApplyInfo entity) {
        dao.save(entity);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void update(ChangeApplyInfo entity) {
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
    public ChangeApplyInfo getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public Map<String, Object> queryBySerialNum(Map<String, Object> reqMap) {
        return dao.queryBySerialNum(reqMap);
    }

    @Override
    public Map<String, Object> nlDetailsByzsbh(Map<String, Object> map) throws Exception {
        return dao.nlDetailsByzsbh(map);
    }

    @Override
    public Map<String, Object> personDetailsByzsbh(Map<String, Object> map) throws Exception {
        return dao.personDetailsByzsbh(map);
    }
}
