package com.yongjie.ZhiJianSbpt.startFlow.dao;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.startFlow.model.UsersInRoles;

public interface IUsersInRolesDao extends BaseDao<UsersInRoles> {
    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.startFlow.dao.impl.UsersInRolesDao";

    String getUserIds(String roleIds, long shenHeAreaId, Integer isAreaFilter) throws Exception;


}
