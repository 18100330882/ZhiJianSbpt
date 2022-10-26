package com.yongjie.ZhiJianSbpt.service.shiYsJyjc;

import java.util.ArrayList;

public interface ApiFujianTypeService {

    public final static String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.service.shiYsJyjc.impl.ApiFujianTypeServiceImpl";


    //附件类型选择
    ArrayList getYjFuJianList(long flowId, String sqsType, String statue) throws Exception;

    //附件类型选择
    String getFuJianListId(long flowId, String sqsType, String fileName) throws Exception;


}
