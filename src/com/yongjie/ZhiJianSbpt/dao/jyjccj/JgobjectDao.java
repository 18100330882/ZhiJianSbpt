package com.yongjie.ZhiJianSbpt.dao.jyjccj;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.model.JgObject;

import java.util.Map;

public interface JgobjectDao extends BaseDao<JgObject> {

    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.dao.jyjccj.impl.JgobjectDaoImpl";

    Map getByMcAndShxydm(Map map);


    Map getByMcAndShxydm2(Map map);
}
