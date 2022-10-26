package com.yongjie.ZhiJianSbpt.model;

import java.util.Date;

public class ApiShiYsJyjcSqlx {
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 申请类型 :首次/扩项/地址变更/复查/其他
     */
    private String applicationType;
    /**
     * CMA资质认定证书编号
     */
    private String cmaPermitNumber;
    /**
     * CMA证书有效日期
     */
    private Date cmaPermitEffectiveDate;
    /**
     * 流水号
     */
    private String serialNumber;
    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 更新日期
     */
    private Date updateDate;
    /**
     * CAL资质认定证书编号
     */
    private String calPermitNumber;
    /**
     * CAL证书有效日期
     */
    private Date calPermitEffectiveDate;


    public ApiShiYsJyjcSqlx() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public String getCmaPermitNumber() {
        return cmaPermitNumber;
    }

    public void setCmaPermitNumber(String cmaPermitNumber) {
        this.cmaPermitNumber = cmaPermitNumber;
    }

    public Date getCmaPermitEffectiveDate() {
        return cmaPermitEffectiveDate;
    }

    public void setCmaPermitEffectiveDate(Date cmaPermitEffectiveDate) {
        this.cmaPermitEffectiveDate = cmaPermitEffectiveDate;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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

    public String getCalPermitNumber() {
        return calPermitNumber;
    }

    public void setCalPermitNumber(String calPermitNumber) {
        this.calPermitNumber = calPermitNumber;
    }

    public Date getCalPermitEffectiveDate() {
        return calPermitEffectiveDate;
    }

    public void setCalPermitEffectiveDate(Date calPermitEffectiveDate) {
        this.calPermitEffectiveDate = calPermitEffectiveDate;
    }

}
