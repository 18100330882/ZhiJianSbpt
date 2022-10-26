/**
 * @Author: 黄煜豪
 * @Createtime: 2017年9月20日 下午4:44:51
 * @Copyright: Copyright (c) 2016
 * @Company: 北京永杰友信科技有限公司
 * @Version：1.0
 * @Description:
 */
package com.yongjie.ZhiJianSbpt.dao.jyjccj;


import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.model.jyjccj.JgobjectJyjcRy;

public interface JgObjectJyjcryDao extends BaseDao<JgobjectJyjcRy> {
    public final static String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.dao.jyjccj.impl.JgObjectJyjcryDaoImpl";

    void deleteJyjcRyById(Long id);

}
