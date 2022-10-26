package com.yongjie.ZhiJianSbpt.dao.jyjccj;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.model.jyjccj.CjSqqzr;
import com.yongjie.ZhiJianSbpt.model.jyjccj.JgObjectJyjcQzr;

public interface JgObjectJyjcQzrDao extends BaseDao<CjSqqzr> {

    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.dao.jyjccj.impl.JgObjectJyjcQzrDaoImpl";

    void deleteJyjcQzrById(Long id);


}
