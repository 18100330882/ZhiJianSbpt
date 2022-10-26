package com.yongjie.ZhiJianSbpt.service.impl;

import com.yongjie.ZhiJianSbpt.dao.YjFlowInstLogDao;
import com.yongjie.ZhiJianSbpt.service.YjFlowInstLogService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;

@Repository(YjFlowInstLogService.SERVICE_NAME)
public class YjFlowInstLogServiceImpl implements YjFlowInstLogService {

    @Resource(name = YjFlowInstLogDao.SERVICE_NAME)
    private YjFlowInstLogDao dao;

    @SuppressWarnings("rawtypes")
    @Override
    public HashMap getFlowInstLogList(String nameFilter, String serialNumber, int pageIndex, int pageSize, String sortField,
                                      String sortOrder, long flowInstId) throws Exception {
        HashMap daiban = dao.getFlowInstLogList(flowInstId, serialNumber, "1,2,3,4", sortField, sortOrder,
                pageIndex, pageSize);
        return daiban;
    }

}
