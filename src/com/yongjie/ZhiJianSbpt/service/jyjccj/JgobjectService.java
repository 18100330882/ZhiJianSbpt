package com.yongjie.ZhiJianSbpt.service.jyjccj;

import com.yongjie.ZhiJianSbpt.model.JgObject;

import java.util.List;
import java.util.Map;

public interface JgobjectService {

    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.service.jyjccj.impl.JgobjectServiceImpl";

    int saveBatch(List<JgObject> list) throws Exception;

    int saveBatch(List<JgObject> list, int size) throws Exception;

    void save(JgObject entity);

    void update(JgObject entity);


    Map getByMcAndShxydm(Map map);

    void delete(Long id);

    JgObject getById(Long id);

    Map getByMcAndShxydm2(Map map);
}
