package com.yongjie.ZhiJianSbpt.dao;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.model.ApplyEnd;

import java.util.HashMap;

public interface ApplyEndDao extends BaseDao<ApplyEnd> {
    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.dao.impl.ApplyEndDaoImpl";

    ApplyEnd queryBySerialNumber(String serialNumber);

    HashMap<String, Object> queryListBySerialNumber(HashMap reqMap);

    void deleteBySerialNum(String serialNum);
}
