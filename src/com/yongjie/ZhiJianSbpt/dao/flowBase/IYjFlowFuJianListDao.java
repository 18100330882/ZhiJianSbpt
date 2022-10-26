/**
 * @Author: 杨淑娟
 * @Createtime: 2017年1月9日 下午2:31:30
 * @Copyright: Copyright (c) 2016
 * @Company: 北京永杰友信科技有限公司
 * @Version：1.0
 * @Description:
 */
package com.yongjie.ZhiJianSbpt.dao.flowBase;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.model.flowBase.YjFlow;

import java.util.ArrayList;

public interface IYjFlowFuJianListDao extends BaseDao<YjFlow> {
    public final static String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.dao.flowBase.impl.YjFlowFuJianListDao";

    public ArrayList getYjFuJianList(long flowId, String sqsType) throws Exception;

    //查询附件列表id
    public String getFuJianListId(long flowId, String sqsType, String fileName);

}
