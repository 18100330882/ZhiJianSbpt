package com.yongjie.ZhiJianSbpt.service;

import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcQzr;
import com.yongjie.ZhiJianSbpt.model.flowBase.ApiFile;

import java.util.List;
import java.util.Map;

public interface ApiShiYsJyjcQzrService {

    String SERVICE_NAME = "com.yongjie.ZhiJianXzsp.module.zzrd.service.impl.ApiShiYsJyjcQzrServiceImpl";


    /**
     * 保存
     */
    void save(ApiShiYsJyjcQzr entity) throws Exception;

    /**
     * 查询
     *
     * @param id
     * @return
     * @throws Exception
     */
    ApiShiYsJyjcQzr queryById(Long id) throws Exception;


    /**
     * 根据流水号查询
     *
     * @param serialNumber
     * @return
     * @throws Exception
     */
    ApiShiYsJyjcQzr queryBySerialNumber(String serialNumber) throws Exception;


    List<ApiShiYsJyjcQzr> queryByBatchSerialNumber(String serialNumber);

    /**
     * 更新
     *
     * @param entity
     * @throws Exception
     */
    void update(ApiShiYsJyjcQzr entity) throws Exception;

    /**
     * 展示数据
     *
     * @param reqMap
     * @return
     */
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
    int saveBatch(List<ApiShiYsJyjcQzr> list, int size) throws Exception;

    /**
     * 根据流水号批量删除
     *
     * @param serialNumber
     * @return
     * @throws Exception
     */
    int deleteBatchBySerialNumber(String serialNumber) throws Exception;

}
