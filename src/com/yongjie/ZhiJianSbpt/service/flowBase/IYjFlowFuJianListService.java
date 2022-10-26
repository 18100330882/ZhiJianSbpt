/**
 * @Author: 杨淑娟
 * @Createtime: 2017年1月9日 下午2:29:07
 * @Copyright: Copyright (c) 2016
 * @Company: 北京永杰友信科技有限公司
 * @Version：1.0
 * @Description:
 */
package com.yongjie.ZhiJianSbpt.service.flowBase;

import java.util.ArrayList;

public interface IYjFlowFuJianListService {
    public final static String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.service.flowBase.impl.YjFlowFuJianListService";

    //附件类型选择
    public ArrayList getYjFuJianList(long flowId, String sqsType) throws Exception;

    //附件类型选择
    public String getFuJianListId(long flowId, String sqsType, String fileName) throws Exception;

}
