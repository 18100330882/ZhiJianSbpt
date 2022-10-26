package com.yongjie.ZhiJianSbpt.bggl.service;

import com.yongjie.ZhiJianSbpt.bggl.model.ChangeApplyInfo;

import java.util.Map;

/**
 * @ClassName AddressChangeService
 * @Date 2022/3/11 16:55
 * @Version 1.0
 */
public interface ChangeApplyInfoService {
    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.bggl.service.impl.ChangeApplyInfoServiceImpl";

    void save(ChangeApplyInfo entity);

    void update(ChangeApplyInfo entity);

    void delete(Long id);

    void deleteBySerialNum(String serialNumber);

    ChangeApplyInfo getById(Long id);

    Map<String, Object> queryBySerialNum(Map<String, Object> reqMap);

    Map<String, Object> nlDetailsByzsbh(Map<String, Object> map) throws Exception;

    Map<String, Object> personDetailsByzsbh(Map<String, Object> map) throws Exception;
}
