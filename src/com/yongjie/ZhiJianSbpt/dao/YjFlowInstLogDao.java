package com.yongjie.ZhiJianSbpt.dao;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.model.YjFlowInstLog;

import java.util.HashMap;

public interface YjFlowInstLogDao extends BaseDao<YjFlowInstLog> {

    public final static String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.dao.impl.YjFlowInstLogDaoImpl";

    /**
     * 流转信息
     *
     * @param pageIndex
     * @param pageSize
     * @param sortField
     * @param sortOrder
     * @param flowInstId
     * @return
     * @throws Exception
     */
    HashMap getFlowInstLogList(long flowInstId, String serialNumber, String opType, String sortField, String sortOrder, int pageIndex, int pageSize) throws Exception;


}
