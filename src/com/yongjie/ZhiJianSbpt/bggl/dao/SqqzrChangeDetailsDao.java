package com.yongjie.ZhiJianSbpt.bggl.dao;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.bggl.model.SqqzrChangeDetails;

import java.util.Map;

public interface SqqzrChangeDetailsDao extends BaseDao<SqqzrChangeDetails> {
    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.bggl.dao.impl.SqqzrChangeDetailsDaoImpl";

    Map<String, Object> queryListBySerialNum(Map<String, Object> reqMap);

    void deleteBySerialNum(String serialNumber);
}
