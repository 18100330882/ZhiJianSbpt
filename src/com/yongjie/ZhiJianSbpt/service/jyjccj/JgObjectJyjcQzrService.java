package com.yongjie.ZhiJianSbpt.service.jyjccj;

import com.yongjie.ZhiJianSbpt.model.jyjccj.CjSqqzr;
import com.yongjie.ZhiJianSbpt.model.jyjccj.JgObjectJyjcQzr;

import java.util.List;

public interface JgObjectJyjcQzrService {

    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.service.jyjccj.impl.JgObjectJyjcQzrServiceImpl";

    /**
     * 批量保存
     *
     * @param list 对象集合 默认一次1000条
     * @return
     * @throws Exception
     * @author zws
     */
    int saveBatch(List<CjSqqzr> list) throws Exception;

    /**
     * 批量保存
     *
     * @param size 一次保存的数量
     * @return
     * @throws Exception
     * @author zws
     */
    int saveBatch(List<CjSqqzr> list, int size) throws Exception;

    void deleteJyjcQzrById(Long id);

}
