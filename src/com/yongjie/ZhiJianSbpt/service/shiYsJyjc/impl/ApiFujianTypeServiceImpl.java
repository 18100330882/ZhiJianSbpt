package com.yongjie.ZhiJianSbpt.service.shiYsJyjc.impl;

import com.yongjie.ZhiJianSbpt.dao.shiYsJyjc.ApiFujianTypeDao;
import com.yongjie.ZhiJianSbpt.service.shiYsJyjc.ApiFujianTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;


@Transactional(readOnly = true)
@Service(ApiFujianTypeService.SERVICE_NAME)
public class ApiFujianTypeServiceImpl implements ApiFujianTypeService {

    @Resource(name = ApiFujianTypeDao.SERVICE_NAME)
    private ApiFujianTypeDao dao;

    @Override
    public ArrayList getYjFuJianList(long flowId, String sqsType, String statue) throws Exception {
        return dao.getYjFuJianList(flowId, sqsType, statue);
    }

    @Override
    public String getFuJianListId(long flowId, String sqsType, String fileName) throws Exception {
        return dao.getFuJianListId(flowId, sqsType, fileName);
    }

}
