package com.yongjie.ZhiJianSbpt.bggl.service;

import com.yongjie.ZhiJianSbpt.bggl.model.StandardChangeDetails;

import java.util.Map;

/**
 * @ClassName AddressChangeService
 * @Date 2022/3/11 16:55
 * @Version 1.0
 */
public interface StandardChangeDetailsService {
    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.bggl.service.impl.StandardChangeDetailsServiceImpl";

    void save(StandardChangeDetails entity);

    void delete(Long id);

    void deleteBySerialNum(String serialNumber);

    void update(StandardChangeDetails entity);

    StandardChangeDetails getById(Long id);

    Map<String, Object> queryListBySerialNum(Map<String, Object> reqMap);
}
