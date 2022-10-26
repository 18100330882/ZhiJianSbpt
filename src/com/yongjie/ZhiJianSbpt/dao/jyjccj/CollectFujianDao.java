package com.yongjie.ZhiJianSbpt.dao.jyjccj;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.model.jyjccj.CollectFujian;

import java.util.Map;

public interface CollectFujianDao extends BaseDao<CollectFujian> {

    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.dao.jyjccj.impl.CollectFujianDaoImpl";


    Map<String, Object> ckfj(Map<String, Object> map);

}
