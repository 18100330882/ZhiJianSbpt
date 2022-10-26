package com.yongjie.ZhiJianSbpt.bggl.model;

import java.util.Date;

/**
 * @ClassName CancelNlDetails 取消检验检测能力审批表
 * @Date 2022/8/18 15:22
 * @Version 1.0
 */
public class CancelNlDetails {
    private Long id;
    /**
     * 机构名称
     */
    private String jgName;

    /**
     * 流水号
     */
    private String serialNumber;

    /**
     * 证书编号
     */
    private String cmaPermitNum;

    /**
     * 能力 序号
     */
    private String sortNum;

    /**
     * 类别(产品/项目/参数)
     */
    private String category;

    /**
     * 序号(产品/项目/参数)
     */
    private String productSortNum;

    /**
     * 名称（产品/项目/参数）
     */
    private String productName;

    /**
     * 依据的标准（方法）名称及编号（含年号）
     */
    private String standardNameAndNum;

    /**
     * 所在实验场所
     */
    private String laboratoryPlace;

    private Date createDate;
    private Date updateDate;

    private String sortNumbg;

    private Long jgobjectnlId;

    private String limitRange;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJgName() {
        return jgName;
    }

    public void setJgName(String jgName) {
        this.jgName = jgName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getCmaPermitNum() {
        return cmaPermitNum;
    }

    public void setCmaPermitNum(String cmaPermitNum) {
        this.cmaPermitNum = cmaPermitNum;
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

    public String getProductSortNum() {
        return productSortNum;
    }

    public void setProductSortNum(String productSortNum) {
        this.productSortNum = productSortNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getStandardNameAndNum() {
        return standardNameAndNum;
    }

    public void setStandardNameAndNum(String standardNameAndNum) {
        this.standardNameAndNum = standardNameAndNum;
    }

    public String getLaboratoryPlace() {
        return laboratoryPlace;
    }

    public void setLaboratoryPlace(String laboratoryPlace) {
        this.laboratoryPlace = laboratoryPlace;
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
