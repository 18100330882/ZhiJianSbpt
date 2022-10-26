package com.yongjie.ZhiJianSbpt.model.sysBase;

import java.util.Date;

/**
 * Dept generated by hbm2java
 */
public class Dept {
    private long id;
    private String deptName;
    private String deptLxr;
    private String deptPhone;
    private Long areaId;
    private Long parentId;
    private String parentPath;
    private Integer depth;
    private Integer paiXu;
    private String address;
    private String scfw;
    private String caoR;
    private Date caoDate;

    public Dept() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDeptName() {
        return this.deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptLxr() {
        return this.deptLxr;
    }

    public void setDeptLxr(String deptLxr) {
        this.deptLxr = deptLxr;
    }

    public String getDeptPhone() {
        return this.deptPhone;
    }

    public void setDeptPhone(String deptPhone) {
        this.deptPhone = deptPhone;
    }

    public Long getAreaId() {
        return this.areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Long getParentId() {
        return this.parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentPath() {
        return this.parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    public Integer getDepth() {
        return this.depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public Integer getPaiXu() {
        return this.paiXu;
    }

    public void setPaiXu(Integer paiXu) {
        this.paiXu = paiXu;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getScfw() {
        return scfw;
    }

    public void setScfw(String scfw) {
        this.scfw = scfw;
    }

    public String getCaoR() {
        return this.caoR;
    }

    public void setCaoR(String caoR) {
        this.caoR = caoR;
    }

    public Date getCaoDate() {
        return this.caoDate;
    }

    public void setCaoDate(Date caoDate) {
        this.caoDate = caoDate;
    }

}