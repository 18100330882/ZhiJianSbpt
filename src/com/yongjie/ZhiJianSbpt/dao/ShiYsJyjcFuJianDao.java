package com.yongjie.ZhiJianSbpt.dao;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcCd;
import com.yongjie.ZhiJianSbpt.model.ShiYsJyjcFuJian;
import com.yongjie.ZhiJianSbpt.model.flowBase.ApiFile;
import com.yongjie.ZhiJianSbpt.model.flowBase.ApiFujianType;

import java.util.HashMap;
import java.util.List;

public interface ShiYsJyjcFuJianDao extends BaseDao<ShiYsJyjcFuJian> {
    String SERVICE_NAME = "com.yongjie.ZhiJianXzsp.module.zzrd.dao.impl.ShiYsJyjcFuJianDaoImpl";

    HashMap<String, Object> queryListBySerialNumber(HashMap<Object, Object> reqMap);

    ShiYsJyjcFuJian getSqsFujian(String serialNumber);

    ApiFujianType queryFuJIanTypeByFlag(String flag);

    ShiYsJyjcFuJian queryBySerialNumber(String serialNumber);

    List<ShiYsJyjcFuJian> queryByBatchSerialNumber(String serialNumber, String flag);

    /**
     * 根据流水号批量删除
     *
     * @param serialNumber
     * @return
     * @throws Exception
     */
    int deleteBatchBySerialNumber(String serialNumber) throws Exception;

}
