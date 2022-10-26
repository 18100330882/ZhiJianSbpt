/**
 * @Author: 黄煜豪
 * @Createtime: 2017年9月20日 下午4:46:18
 * @Copyright: Copyright (c) 2016
 * @Company: 北京永杰友信科技有限公司
 * @Version：1.0
 * @Description:
 */
package com.yongjie.ZhiJianSbpt.dao.jyjccj.impl;


import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.dao.jyjccj.JgObjectJyjcryDao;
import com.yongjie.ZhiJianSbpt.model.jyjccj.JgobjectJyjcRy;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;


@Repository(JgObjectJyjcryDao.SERVICE_NAME)
public class JgObjectJyjcryDaoImpl extends BaseDaoImpl<JgobjectJyjcRy> implements JgObjectJyjcryDao {


    @Override
    public void deleteJyjcRyById(Long id) {
        StringBuffer sb = new StringBuffer("DELETE FROM JGOBJECT_JYJCRY WHERE CID=:id");
        SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString());
        sqlQuery.setLong("id", id);
        sqlQuery.executeUpdate();
    }
}
