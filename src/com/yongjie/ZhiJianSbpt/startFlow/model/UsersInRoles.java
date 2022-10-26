/**
 * @Author: 刘重洋
 * @Createtime: 2016年7月26日 下午4:54:33
 * @Copyright: Copyright (c) 2016
 * @Company: 北京永杰友信科技有限公司
 * @Version：1.0
 * @Description:
 */
package com.yongjie.ZhiJianSbpt.startFlow.model;

import java.util.Date;

public class UsersInRoles {

    /**
     *主键id
     * */
    private Long id;
    /**
     *用户id
     * */
    private Long userId;
    /**
     *角色id
     * */
    private Long roleId;
    /**
     *所属系统 1审批端 0行政端
     * */
    private Integer flag;
    /**
     *操作人
     * */
    private String caoR;
    /**
     *操作日期
     * */
    private Date caoDate;

    public UsersInRoles() {
        super();
    }

    public UsersInRoles(Long userId, Long roleId, Integer flag, String caoR, Date caoDate) {
        super();
        this.userId = userId;
        this.roleId = roleId;
        this.flag = flag;
        this.caoR = caoR;
        this.caoDate = caoDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getCaoR() {
        return caoR;
    }

    public void setCaoR(String caoR) {
        this.caoR = caoR;
    }

    public Date getCaoDate() {
        return caoDate;
    }

    public void setCaoDate(Date caoDate) {
        this.caoDate = caoDate;
    }
}
