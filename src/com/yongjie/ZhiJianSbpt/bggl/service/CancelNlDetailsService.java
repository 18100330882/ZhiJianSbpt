package com.yongjie.ZhiJianSbpt.bggl.service;

import com.yongjie.ZhiJianSbpt.bggl.model.CancelNlDetails;

import java.util.Map;

/**
 * @ClassName AddressChangeService
 * @Date 2022/3/11 16:55
 * @Version 1.0
 */
public interface CancelNlDetailsService {
    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.bggl.service.impl.CancelNlDetailsServiceImpl";

    void save(CancelNlDetails entity);

    void update(CancelNlDetails entity);

    void delete(Long id);

    void deleteBySerialNum(String serialNumber);

    CancelNlDetails getById(Long id);

    Map<String, Object> queryListBySerialNum(Map<String, Object> reqMap);
}
