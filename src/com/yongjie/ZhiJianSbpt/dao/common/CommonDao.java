package com.yongjie.ZhiJianSbpt.dao.common;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import org.apache.poi.ss.formula.functions.T;

import java.util.Map;

public interface CommonDao extends BaseDao<T> {
    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.dao.impl.CommonDaoImpl";

    /**
     * @param paramMap
     * @return
     * @throws Exception
     */
    Map<String, Object> getShiysjyjcScType(Map<Object, Object> paramMap) throws Exception;

    Map<String, Object> getSerialNumber() throws Exception;

    Map<String, Object> getZgbmData();
}
