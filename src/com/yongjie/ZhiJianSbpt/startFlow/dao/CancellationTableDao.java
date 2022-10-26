package com.yongjie.ZhiJianSbpt.startFlow.dao;


import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.startFlow.model.CancellationTable;

public interface CancellationTableDao extends BaseDao<CancellationTable> {

    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.startFlow.dao.impl.CancellationTableDaoImpl";

    CancellationTable getCancellationTable(String serialNumber);
}
