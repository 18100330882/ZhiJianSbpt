
package com.yongjie.ZhiJianSbpt.dao.shiYsJyjc;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.model.flowBase.ApiFujianType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ApiFujianTypeDao extends BaseDao<ApiFujianType> {

    public final static String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.module.zzrd.dao.impl.ApiFujianTypeDaoImpl";

    ArrayList getYjFuJianList(long flowId, String sqsType, String statue) throws Exception;

    //查询附件列表id
    String getFuJianListId(long flowId, String sqsType, String fileName);

}
