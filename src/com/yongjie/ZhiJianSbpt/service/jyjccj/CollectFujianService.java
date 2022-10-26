package com.yongjie.ZhiJianSbpt.service.jyjccj;

import com.yongjie.ZhiJianSbpt.model.jyjccj.CollectFujian;

import java.util.Map;

public interface CollectFujianService {

    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.service.jyjccj.impl.CollectFujianServiceImpl";

    public void save(CollectFujian collectFujian);

    public void update(CollectFujian collectFujian);

    Map<String, Object> ckfj(Map<String, Object> map);


    CollectFujian getById(Long valueOf);

    void delete(Long id);
}
