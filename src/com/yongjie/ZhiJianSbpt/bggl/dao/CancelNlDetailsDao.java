package com.yongjie.ZhiJianSbpt.bggl.dao;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.bggl.model.CancelNlDetails;

import java.util.Map;

/**
 * @ClassName CancelNlDetailsDao
 * @Date 2022/3/14 16:49
 * @Version 1.0
 */
public interface CancelNlDetailsDao extends BaseDao<CancelNlDetails> {
    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.bggl.dao.impl.CancelNlDetailsDaoImpl";

    Map<String, Object> queryListBySerialNum(Map<String, Object> reqMap);

    void deleteBySerialNum(String serialNumber);
}
