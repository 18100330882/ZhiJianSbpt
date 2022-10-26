/**
 * @Author: 杨淑娟
 * @Createtime: 2017年1月9日 下午2:30:07
 * @Copyright: Copyright (c) 2016
 * @Company: 北京永杰友信科技有限公司
 * @Version：1.0
 * @Description:
 */
package com.yongjie.ZhiJianSbpt.service.flowBase.impl;

import com.yongjie.ZhiJianSbpt.dao.flowBase.IYjFlowFuJianListDao;
import com.yongjie.ZhiJianSbpt.service.flowBase.IYjFlowFuJianListService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;

@Transactional(readOnly = false)
@Service(IYjFlowFuJianListService.SERVICE_NAME)
public class YjFlowFuJianListService implements IYjFlowFuJianListService {
    @Resource(name = IYjFlowFuJianListDao.SERVICE_NAME)
    private IYjFlowFuJianListDao fuJiandao;

    @Override
    public ArrayList getYjFuJianList(long flowId, String sqsType) throws Exception {
        // TODO Auto-generated method stub
        return fuJiandao.getYjFuJianList(flowId, sqsType);
    }

    @Override
    public String getFuJianListId(long flowId, String sqsType, String fileName) throws Exception {
        // TODO Auto-generated method stub
        return fuJiandao.getFuJianListId(flowId, sqsType, fileName);
    }


}
