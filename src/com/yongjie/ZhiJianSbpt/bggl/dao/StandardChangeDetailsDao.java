package com.yongjie.ZhiJianSbpt.bggl.dao;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.bggl.model.StandardChangeDetails;

import java.util.Map;

public interface StandardChangeDetailsDao extends BaseDao<StandardChangeDetails> {
    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.bggl.dao.impl.StandardChangeDetailsDaoImpl";

    Map<String, Object> queryListBySerialNum(Map<String, Object> reqMap);

    void deleteBySerialNum(String serialNumber);
}
