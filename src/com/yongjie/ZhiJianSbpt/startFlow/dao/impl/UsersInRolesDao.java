package com.yongjie.ZhiJianSbpt.startFlow.dao.impl;

import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.startFlow.dao.IUsersInRolesDao;
import com.yongjie.ZhiJianSbpt.startFlow.model.UsersInRoles;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(IUsersInRolesDao.SERVICE_NAME)
public class UsersInRolesDao extends BaseDaoImpl<UsersInRoles> implements IUsersInRolesDao {

    @Override
    public String getUserIds(String roleIds, long shenHeAreaId, Integer isAreaFilter) throws Exception {
        String[] roleIdList = roleIds.split(",");
        String userIds = "";
        for (int i = 0; i < roleIdList.length; i++) {
            String ids = "";
            String roleId = roleIdList[i];
            String sql = "select distinct u.* from UsersInRoles u inner join accountXzsp a "
                    //+ " on a.id=u.userId where u.roleId="+roleId+" and a.isLock=0";
                    + " on a.id=u.userId where u.roleId=:roleId and a.isLock=0";
            if (isAreaFilter == 0) {//0，依据区域过滤
                sql += " and a.areaId=" + shenHeAreaId;
            }
            @SuppressWarnings("unchecked")
            SQLQuery query = getSession().createSQLQuery(sql).addEntity(UsersInRoles.class);
            query.setLong("roleId", Long.parseLong(roleId));
            List<UsersInRoles> usersList = query.list();
            for (int j = 0; j < usersList.size(); j++) {
                ids = String.valueOf(usersList.get(j).getUserId()) + ",";//用户id
                if (!(',' + userIds).contains(',' + ids)) {
                    userIds += ids;
                }
            }
        }
        if (userIds.length() > 0) {
            userIds = userIds.substring(0, userIds.length() - 1);
        }
        return userIds;
    }
}
