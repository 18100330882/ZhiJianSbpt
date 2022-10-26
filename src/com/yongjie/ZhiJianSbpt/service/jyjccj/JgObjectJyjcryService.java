/**
 * @Author: 黄煜豪
 * @Createtime: 2017年9月20日 下午4:40:59
 * @Copyright: Copyright (c) 2016
 * @Company: 北京永杰友信科技有限公司
 * @Version：1.0
 * @Description:
 */
package com.yongjie.ZhiJianSbpt.service.jyjccj;

import com.yongjie.ZhiJianSbpt.model.jyjccj.JgobjectJyjcRy;

import java.util.List;

public interface JgObjectJyjcryService {
    public final static String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.service.jyjccj.impl.JgObjectJyjcryServiceImpl";


    /**
     * 批量保存
     *
     * @param list 对象集合 默认一次1000条
     * @return
     * @throws Exception
     * @author zws
     */
    int saveBatch(List<JgobjectJyjcRy> list) throws Exception;

    /**
     * 批量保存
     *
     * @param size 一次保存的数量
     * @return
     * @throws Exception
     * @author zws
     */
    int saveBatch(List<JgobjectJyjcRy> list, int size) throws Exception;

    void deleteJyjcRyById(Long id);


}
