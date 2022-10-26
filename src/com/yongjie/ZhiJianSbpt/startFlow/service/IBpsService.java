package com.yongjie.ZhiJianSbpt.startFlow.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface IBpsService {
    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.startFlow.service.impl.BpsService";

    Long statrtNewYjflow(String flowName, Long flowId, Long sqsId, String currentUser, Long shenHeAreaId, String suggesTion, String slYesOrNo) throws Exception;

    long startYjFlow(String s, long parseLong, long parseLong1, String userName, long parseLong2) throws Exception;
}
