package com.yongjie.ZhiJianSbpt.dao.jyjcbggl;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.model.jyjcbggl.BizApiZwcnBg;

import java.util.Map;

public interface BizApiZwcnBgDao extends BaseDao<BizApiZwcnBg> {

    String SERVICE_NAME = "com.yongjie.ZhiJianXzsp.module.zzrd.dao.jyjcbggl.impl.BizApiZwcnBgDaoImpl";

    Map<String, Object> getList(Map<Object, Object> reqMap) throws Exception;

    BizApiZwcnBg queryBySerialNumber(String serialNumber) throws Exception;

    /**
     * 根据流水号删除
     *
     * @param serialNumber
     * @return
     * @throws Exception
     */
    int deleteBatchBySerialNumber(String serialNumber) throws Exception;

    BizApiZwcnBg getBizApiZwcnBg(String serialNumber, String flag) throws Exception;
}
