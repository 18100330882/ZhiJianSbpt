package com.yongjie.ZhiJianSbpt.bggl.service;

import com.yongjie.ZhiJianSbpt.bggl.model.SqqzrChangeDetails;

import java.util.Map;

/**
 * @ClassName AddressChangeService
 * @Date 2022/3/11 16:55
 * @Version 1.0
 */
public interface SqqzrChangeDetailsService {
    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.bggl.service.impl.SqqzrChangeDetailsServiceImpl";

    void save(SqqzrChangeDetails entity);

    void update(SqqzrChangeDetails entity);

    void delete(Long id);

    void deleteBySerialNum(String serialNumber);

    SqqzrChangeDetails getById(Long id);

    Map<String, Object> queryListBySerialNum(Map<String, Object> reqMap);
}
