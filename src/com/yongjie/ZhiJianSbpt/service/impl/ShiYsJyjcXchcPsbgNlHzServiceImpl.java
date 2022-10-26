package com.yongjie.ZhiJianSbpt.service.impl;

import com.yongjie.ZhiJianSbpt.dao.ShiYsJyjcXchcPsbgNlHzDao;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcNl;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcNlSbpt;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcYqsbSbpt;
import com.yongjie.ZhiJianSbpt.model.ShiYsJyjcXchcPsbgNlHz;
import com.yongjie.ZhiJianSbpt.service.ShiYsJyjcXchcPsbgNlHzService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service(ShiYsJyjcXchcPsbgNlHzService.SERVICE_NAME)
public class ShiYsJyjcXchcPsbgNlHzServiceImpl implements ShiYsJyjcXchcPsbgNlHzService {

    @Resource(name = ShiYsJyjcXchcPsbgNlHzDao.SERVICE_NAME)
    private ShiYsJyjcXchcPsbgNlHzDao dao;

    @Override
    public ArrayList getListByFlowInstId(String serialNumber, String cdId, String calOrCma, String type, String nlOrSb, String isSp) throws Exception {
        return dao.getListByFlowInstId(serialNumber, cdId, calOrCma, type, nlOrSb, isSp);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void add(ShiYsJyjcXchcPsbgNlHz entity) {
        dao.save(entity);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void edit(ShiYsJyjcXchcPsbgNlHz shiYsJyjcXchcPsbgNlHz) {
        dao.update(shiYsJyjcXchcPsbgNlHz);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public ShiYsJyjcXchcPsbgNlHz queryById(Long id) {
        return dao.getById(id);
    }

    @Override
    public Map<String, Object> getNlFileList(HashMap<Object, Object> reqMap) throws Exception {
        return dao.getNlFileList(reqMap);
    }

    @Override
    public Map<String, Object> getNlFileListByDoc(HashMap<Object, Object> reqMap) throws Exception {
        return dao.getNlFileListByDoc(reqMap);
    }

    @Override
    public ArrayList<Map<String, String>> listLeib(String serialNumber, String cddz, String status, String isSp) {
        return dao.listLeib(serialNumber, cddz, status, isSp);
    }

    @Override
    public ArrayList<ApiShiYsJyjcNlSbpt> listCpmc1(String serialNumber, String leibName, String cddz, String status, String isSp) {
        return dao.listCpmc1(serialNumber, leibName, cddz, status, isSp);
    }

    @Override
    public ArrayList<ApiShiYsJyjcYqsbSbpt> yqsblist(String serialNumber, String cddz, String status, String isSp) {
        return dao.yqsblist(serialNumber, cddz, status, isSp);
    }

    @Override
    public ArrayList<ApiShiYsJyjcYqsbSbpt> yqsblistToBeizhu(Map map) {
        return dao.yqsblistToBeizhu(map);
    }
}
