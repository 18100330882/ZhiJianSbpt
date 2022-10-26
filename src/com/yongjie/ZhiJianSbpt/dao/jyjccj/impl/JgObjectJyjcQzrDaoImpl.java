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
import com.yongjie.ZhiJianSbpt.dao.jyjccj.JgObjectJyjcQzrDao;
import com.yongjie.ZhiJianSbpt.model.jyjccj.CjSqqzr;
import com.yongjie.ZhiJianSbpt.model.jyjccj.JgObjectJyjcQzr;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;


@Repository(JgObjectJyjcQzrDao.SERVICE_NAME)
public class JgObjectJyjcQzrDaoImpl extends BaseDaoImpl<CjSqqzr> implements JgObjectJyjcQzrDao {


    @Override
    public void deleteJyjcQzrById(Long id) {
        StringBuffer sb = new StringBuffer("DELETE FROM CJ_SQQZR WHERE CID=:id");
        SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString());
        sqlQuery.setLong("id", id);
        sqlQuery.executeUpdate();
    }
}
