package com.yongjie.ZhiJianSbpt.dao.jyjcJgzx;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.model.jyjcJgzx.BizApiZxsqb;

import java.util.Map;

public interface BizApiZxsqbDao extends BaseDao<BizApiZxsqb> {

    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.dao.jyjcJgzx.impl.BizApiZxsqbDaoImpl";

    Map<String, Object> getList(Map<Object, Object> reqMap) throws Exception;

    BizApiZxsqb queryBySerialNumber(String serialNumber) throws Exception;

    /**
     * 根据流水号删除
     *
     * @param serialNumber
     * @return
     * @throws Exception
     */
    int deleteBatchBySerialNumber(String serialNumber) throws Exception;
}
