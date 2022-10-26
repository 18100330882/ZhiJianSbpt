package com.yongjie.ZhiJianSbpt.bggl.dao;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.bggl.model.ChangeApplyInfo;

import java.util.Map;

public interface ChangeApplyInfoDao extends BaseDao<ChangeApplyInfo> {
    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.bggl.dao.impl.ChangeApplyInfoDaoImpl";

    Map<String, Object> queryBySerialNum(Map<String, Object> reqMap);

    void deleteBySerialNum(String serialNumber);

    Map<String, Object> nlDetailsByzsbh(Map<String, Object> map) throws Exception;

    Map<String, Object> personDetailsByzsbh(Map<String, Object> map) throws Exception;


}
