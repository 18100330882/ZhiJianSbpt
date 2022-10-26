package com.yongjie.ZhiJianSbpt.bggl.model;

import java.util.Date;

/**
 * @ClassName StandardMethodChangeDetails 检验检测机构资质认定标准（方法）变更审批表
 * @Date 2022/3/11 15:35
 * @Version 1.0
 */
public class StandardChangeDetails {
    private Long id;

    /**
     * 流水号
     */
    private String serialNumber;

    /**
     * 序号
     */
    private String sortNum;

    /**
     * 类别(产品/项目/参数)
     */
    private String category;


    /**
     * 已批准的标准（方法）名称、编号（含年号）
     */
    private String standardNameAndNumOld;

    /**
     * 变更后的标准（方法）名称、编号（含年号）
     */
    private String standardNameAndNumNew;

    /**
     * 变更内容
     */
    private String changeContent;

    private Date createDate;
    private Date updateDate;

    private String sortNumbg;

    private String zsbh;

    private Long jgobjectnlId;

    private String limitRange;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSortNum() {
        return sortNum;
    }

    public void setSortNum(String sortNum) {
        this.sortNum = sortNum;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStandardNameAndNumOld() {
        return standardNameAndNumOld;
    }

    public void setStandardNameAndNumOld(String standardNameAndNumOld) {
        this.standardNameAndNumOld = standardNameAndNumOld;
    }

    public String getStandardNameAndNumNew() {
        return standardNameAndNumNew;
    }

    public void setStandardNameAndNumNew(String standardNameAndNumNew) {
        this.standardNameAndNumNew = standardNameAndNumNew;
    }

    public String getChangeContent() {
        return changeContent;
    }

    public void setChangeContent(String changeContent) {
        this.changeContent = changeContent;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getSortNumbg() {
        return sortNumbg;
    }

    public void setSortNumbg(String sortNumbg) {
        this.sortNumbg = sortNumbg;
    }

    public String getZsbh() {
        return zsbh;
    }

    public void setZsbh(String zsbh) {
        this.zsbh = zsbh;
    }

    public Long getJgobjectnlId() {
        return jgobjectnlId;
    }

    public void setJgobjectnlId(Long jgobjectnlId) {
        this.jgobjectnlId = jgobjectnlId;
    }

    public String getLimitRange() {
        return limitRange;
    }

    public void setLimitRange(String limitRange) {
        this.limitRange = limitRange;
    }
}
