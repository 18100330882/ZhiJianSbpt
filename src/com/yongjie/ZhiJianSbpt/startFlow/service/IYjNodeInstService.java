package com.yongjie.ZhiJianSbpt.startFlow.service;


import com.yongjie.ZhiJianSbpt.startFlow.model.YjNodeInst;

import java.util.ArrayList;
import java.util.HashMap;

public interface IYjNodeInstService {
    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.startFlow.service.impl.YjNodeInstService";

    void addYjNodeInst(YjNodeInst yjNodeInst) throws Exception;

}
