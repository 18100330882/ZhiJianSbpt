package com.yongjie.ZhiJianSbpt.dao;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcNl;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcNlSbpt;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcYqsbSbpt;
import com.yongjie.ZhiJianSbpt.model.ShiYsJyjcXchcPsbgNlHz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface ShiYsJyjcXchcPsbgNlHzDao extends BaseDao<ShiYsJyjcXchcPsbgNlHz> {

    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.dao.impl.ShiYsJyjcXchcPsbgNlHzDaoImpl";

    ArrayList getListByFlowInstId(String serialNumber, String cdId, String calOrCma, String type, String nlOrSb, String isSp) throws Exception;

    Map<String, Object> getNlFileList(HashMap<Object, Object> reqMap) throws Exception;

    Map<String, Object> getNlFileListByDoc(HashMap<Object, Object> reqMap) throws Exception;

    ArrayList<Map<String, String>> listLeib(String serialNumber, String cddz, String status, String isSp);

    ArrayList<ApiShiYsJyjcNlSbpt> listCpmc1(String serialNumber, String leibName, String cddz, String status, String isSp);

    ArrayList<ApiShiYsJyjcYqsbSbpt> yqsblist(String serialNumber, String cddz, String status, String isSp);

    ArrayList<ApiShiYsJyjcYqsbSbpt> yqsblistToBeizhu(Map map);

}
