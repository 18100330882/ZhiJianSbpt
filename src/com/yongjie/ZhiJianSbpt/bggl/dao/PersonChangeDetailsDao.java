package com.yongjie.ZhiJianSbpt.bggl.dao;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.bggl.model.PersonChangeDetails;

import java.util.Map;

public interface PersonChangeDetailsDao extends BaseDao<PersonChangeDetails> {
    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.bggl.dao.impl.PersonChangeDetailsDaoImpl";

    Map<String, Object> queryListBySerialNum(Map<String, Object> reqMap);

    void deleteBySerialNum(String serialNumber);
}
