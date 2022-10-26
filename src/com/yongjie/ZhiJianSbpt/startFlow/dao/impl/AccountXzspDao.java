package com.yongjie.ZhiJianSbpt.startFlow.dao.impl;

import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.startFlow.dao.IAccountXzspDao;
import com.yongjie.ZhiJianSbpt.startFlow.model.AccountXzsp;
import com.yongjie.ZhiJianSbpt.utilities.HashmapAndEntityTransfer;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;


@Repository(IAccountXzspDao.SERVICE_NAME)
public class AccountXzspDao extends BaseDaoImpl<AccountXzsp> implements IAccountXzspDao {

    @Override
    public AccountXzsp getAccountById(long id) {
        String sql = "FROM AccountXzsp a where a.id=" + id;
        AccountXzsp model = null;
        List<AccountXzsp> lst = getSession().createQuery(sql).list();
        if (lst.size() > 0) {
            model = lst.get(0);
        }
        return model;
    }

    @Override
    public String getUserIds(String deptIds) {
        String[] deptIdS = deptIds.split(",");
        String userIds = "";
        for (int i = 0; i < deptIdS.length; i++) {
            String ids = "";
            String deptId = deptIdS[i];
            String sql = "select distinct * FROM AccountXzsp a where a.deptId=:deptId  and a.isLock=0";

            SQLQuery query = getSession().createSQLQuery(sql).addEntity(AccountXzsp.class);

            long longDeptId = 0L;
            try {
                longDeptId = Long.parseLong(deptId);
            } catch (Exception e) {
                longDeptId = 0L;
            }
            query.setLong("deptId", longDeptId);
            List<AccountXzsp> usersList = query.list();
            for (int j = 0; j < usersList.size(); j++) {
                ids = String.valueOf(usersList.get(j).getId()) + ",";
                if (!userIds.contains(ids)) {
                    userIds += ids;
                }
            }
        }
        if (userIds.length() > 0) {
            userIds = userIds.substring(0, userIds.length() - 1);
        }
        return userIds;
    }

    @Override
    public HashMap getUserModel(String userName) throws Exception {
        String sql = "select * from ACCOUNTXZSP a  WHERE a.userName=:userName ";
        SQLQuery query = getSession().createSQLQuery(sql).addEntity(AccountXzsp.class);
        query.setString("userName", userName);
        List<AccountXzsp> list = query.list();
        if (list.size() == 0) {
            return new HashMap<>();
        } else
            return HashmapAndEntityTransfer.entityTransferToHashmap(list.get(0));
    }
}