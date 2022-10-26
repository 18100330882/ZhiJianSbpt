package com.yongjie.ZhiJianSbpt.service;

import com.yongjie.ZhiJianSbpt.model.ShiYsJyjcFuJian;
import com.yongjie.ZhiJianSbpt.model.flowBase.ApiFile;
import com.yongjie.ZhiJianSbpt.model.flowBase.ApiFujianType;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName ShiYsJyjcFuJianService
 * @Description TODO
 * @Date 2021/12/20 11:22
 * @Version 1.0
 */
public interface ShiYsJyjcFuJianService {
    String SERVICE_NAME = "com.yongjie.ZhiJianXzsp.module.zzrd.service.impl.ShiYsJyjcFuJianServiceImpl";

    void save(ShiYsJyjcFuJian entity) throws Exception;

    void update(ShiYsJyjcFuJian entity) throws Exception;

    void delete(Long id) throws Exception;

    HashMap<String, Object> queryListBySerialNumber(HashMap<Object, Object> reqMap) throws Exception;

    ApiFujianType queryFuJIanTypeByFlag(String flag);

    ShiYsJyjcFuJian queryBySerialNumber(String serialNumber);

    List<ShiYsJyjcFuJian> queryByBatchSerialNumber(String serialNumber, String flag);

    ShiYsJyjcFuJian getJyjcFuJianById(Long id) throws Exception;

    ShiYsJyjcFuJian getSqsFujian(String serialNumber);

    /**
     * 根据流水号批量删除
     *
     * @param serialNumber
     * @return
     * @throws Exception
     */
    int deleteBatchBySerialNumber(String serialNumber) throws Exception;
}
