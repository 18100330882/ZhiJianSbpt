package com.yongjie.ZhiJianSbpt.service;

import java.util.HashMap;

public interface YjFlowInstLogService {

    public final static String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.service.impl.YjFlowInstLogServiceImpl";

    /**
     * 流转信息
     *
     * @param nameFilter
     * @param pageIndex
     * @param pageSize
     * @param sortField
     * @param sortOrder
     * @param flowInstId
     * @return
     * @throws Exception
     */
    HashMap getFlowInstLogList(String nameFilter, String serialNumber, int pageIndex, int pageSize, String sortField, String sortOrder, long flowInstId) throws Exception;

}
