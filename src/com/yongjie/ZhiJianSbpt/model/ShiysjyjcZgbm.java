package com.yongjie.ZhiJianSbpt.model;

import java.util.Date;

/**
 * @ClassName Zgbm
 * @Description TODO
 * @Date 2021/12/16 8:48
 * @Version 1.0
 */
public class ShiysjyjcZgbm {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 主管部门 名称
     */
    private String zgbmName;

    /**
     * 主管部门代码
     */
    private String zgbmDm;

    /**
     * 是否 有效
     */
    private String isEnabled;

    /**
     * 更新时间
     */
    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZgbmName() {
        return zgbmName;
    }

    public void setZgbmName(String zgbmName) {
        this.zgbmName = zgbmName;
    }

    public String getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getZgbmDm() {
        return zgbmDm;
    }

    public void setZgbmDm(String zgbmDm) {
        this.zgbmDm = zgbmDm;
    }
}
