package com.yongjie.ZhiJianSbpt.service;


import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcQzr;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcRy;

import java.util.List;
import java.util.Map;

public interface ApiShiYsJyjcRyService {

    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.service.impl.ApiShiYsJyjcRyServiceImpl";

    /**
     * 保存
     */
    void save(ApiShiYsJyjcRy entity) throws Exception;

    /**
     * 查询
     *
     * @param id
     * @return
     * @throws Exception
     */
    ApiShiYsJyjcRy queryById(Long id) throws Exception;

    ApiShiYsJyjcRy queryBySerialNumber(String serialNumber) throws Exception;

    List<ApiShiYsJyjcRy> queryByBatchSerialNumber(String serialNumber);

    /**
     * 更新
     *
     * @param entity
     * @throws Exception
     */
    void update(ApiShiYsJyjcRy entity) throws Exception;

    Map<String, Object> getList(Map<Object, Object> reqMap);

    /**
     * 根据id删除人员信息
     *
     * @return
     */
    void delete(Long id);

    /**
     * 批量保存
     *
     * @param list 保存的对象
     * @param size 一次保存的条数
     * @return
     * @throws Exception
     */
    int saveBatch(List<ApiShiYsJyjcRy> list, int size) throws Exception;

    /**
     * 根据流水号批量删除
     *
     * @param serialNumber
     * @return
     * @throws Exception
     */
    int deleteBatchBySerialNumber(String serialNumber) throws Exception;


}
