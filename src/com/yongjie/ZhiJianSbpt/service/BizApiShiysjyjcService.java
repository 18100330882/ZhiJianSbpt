package com.yongjie.ZhiJianSbpt.service;

import com.yongjie.ZhiJianSbpt.model.BizApiShiYsJyjc;

import java.util.Map;

public interface BizApiShiysjyjcService {

    String SERVICE_NAME = "com.yongjie.ZhiJianXzsp.module.zzrd.service.impl.BizApiShiysjyjcServiceImpl";

    /**
     * 保存
     */
    void save(BizApiShiYsJyjc entity) throws Exception;

    /**
     * 查询
     *
     * @param id
     * @return
     * @throws Exception
     */
    BizApiShiYsJyjc queryById(Long id) throws Exception;

    BizApiShiYsJyjc queryBySerialNumber(String serialNumber) throws Exception;

    /**
     * 更新
     *
     * @param entity
     * @throws Exception
     */
    void update(BizApiShiYsJyjc entity) throws Exception;

    Map<String, Object> getList(Map<Object, Object> reqMap) throws Exception;

    /**
     * 删除封面及相关信息
     *
     * @return
     */
    void delete(Long id);

    BizApiShiYsJyjc getBizShiYsJyjcBySerialNumber(String serialNumber) throws Exception;
}
