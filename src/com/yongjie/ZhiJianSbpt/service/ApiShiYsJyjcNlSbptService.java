package com.yongjie.ZhiJianSbpt.service;

import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcNlSbpt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ApiShiYsJyjcNlSbptService {

    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.service.impl.ApiShiYsJyjcNlSbptServiceImpl";

    int saveBatch(List<ApiShiYsJyjcNlSbpt> list, int size) throws Exception;

    void deleteEvent(HashMap<String, Object> reqMap);

    Map<String, Object> getDetailList(HashMap<String, Object> reqMap);

    void deleteRow(long id);

    void updateRow(ApiShiYsJyjcNlSbpt entity);

    void addRow(ApiShiYsJyjcNlSbpt entity);

    List<ApiShiYsJyjcNlSbpt> reSetXh(HashMap<String, Object> reqMap) throws Exception;

    List<ApiShiYsJyjcNlSbpt> reSetXhYqsb(HashMap<String, Object> reqMap) throws Exception;

    void updateList(List<ApiShiYsJyjcNlSbpt> entityList);

    Map<String, Object> queryList(HashMap<String, Object> reqMap);

    /**
     * 查询返回的字段
     *
     * @param map
     * @return
     */
    List queryListBySelect(Map map);

    /**
     * 查询一级分类
     *
     * @param map
     * @return
     */
    List queryYjfl(Map map);

    /**
     * 根据条件查询数据
     *
     * @param map
     * @return
     * @throws Exception
     */
    ArrayList<ApiShiYsJyjcNlSbpt> queryListByTj(Map map) throws Exception;

}
 