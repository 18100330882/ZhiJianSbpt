package com.yongjie.ZhiJianSbpt.bggl.service;

import com.yongjie.ZhiJianSbpt.bggl.model.PersonChangeDetails;

import java.util.Map;

/**
 * @ClassName AddressChangeService
 * @Date 2022/3/11 16:55
 * @Version 1.0
 */
public interface PersonChangeDetailsService {
    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.bggl.service.impl.PersonChangeDetailsServiceImpl";

    void save(PersonChangeDetails entity);

    void update(PersonChangeDetails entity);

    void delete(Long id);

    void deleteBySerialNum(String serialNumber);

    PersonChangeDetails getById(Long id);

    Map<String, Object> queryListBySerialNum(Map<String, Object> reqMap);
}
