package com.yongjie.ZhiJianSbpt.model;

import java.util.Date;

public class ApiShiYsJyjcJgzy {
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 检验检测机构总人数人数
     */
    private Integer allNumber;
    /**
     * 高级专业技术职称人数
     */
    private Integer hJobTitleNumber;
    /**
     * 高级专业技术职称人数占总人数比例
     */
    private String hRatioAll;

    /**
     * 中级专业技术职称人数
     */
    private Integer mJobTitleNumber;
    /**
     * 中级专业技术职称人数占总人数比例
     */
    private String mRatioAll;
    /**
     * 初级专业技术职称人数
     */
    private Integer pJobTitleNumber;
    /**
     * 初级专业技术职称人数占总人数比例
     */
    private String pRatioAll;

    /**
     * 其他人数
     */
    private Integer otherNumber;
    /**
     * 其他人数占总人数比例
     */
    private String oRatioAll;
    /**
     * 固定资产原值(万元)
     */
    private String fixedAssets;
    /**
     * 仪器设备总数
     */
    private Integer equipmentNumber;
    /**
     * 产权状况:自有/租用/合资
     */
    private String propertyRights;
    /**
     * 产权值百分比
     */
    private String propertyRightsValue;
    /**
     * 检验检测机构总面积
     */
    private String institutionsArea;
    /**
     * 检验检测场地面积
     */
    private String siteArea;
    /**
     * 户外检验检测场地面积
     */
    private String outdoorsiteArea;
    /**
     * 温恒面积
     */
    private String whArea;
    /**
     * 场地产权状况：自有/租用/合资
     */
    private String sitePropertyRights;
    /**
     * 场地产权状况值百分比
     */
    private String sitePropertyRightsValue;
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

    public ApiShiYsJyjcJgzy() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAllNumber() {
        return allNumber;
    }

    public void setAllNumber(Integer allNumber) {
        this.allNumber = allNumber;
    }

    public Integer gethJobTitleNumber() {
        return hJobTitleNumber;
    }

    public void sethJobTitleNumber(Integer hJobTitleNumber) {
        this.hJobTitleNumber = hJobTitleNumber;
    }

    public String gethRatioAll() {
        return hRatioAll;
    }

    public void sethRatioAll(String hRatioAll) {
        this.hRatioAll = hRatioAll;
    }

    public Integer getmJobTitleNumber() {
        return mJobTitleNumber;
    }

    public void setmJobTitleNumber(Integer mJobTitleNumber) {
        this.mJobTitleNumber = mJobTitleNumber;
    }

    public String getmRatioAll() {
        return mRatioAll;
    }

    public void setmRatioAll(String mRatioAll) {
        this.mRatioAll = mRatioAll;
    }

    public Integer getpJobTitleNumber() {
        return pJobTitleNumber;
    }

    public void setpJobTitleNumber(Integer pJobTitleNumber) {
        this.pJobTitleNumber = pJobTitleNumber;
    }

    public String getpRatioAll() {
        return pRatioAll;
    }

    public void setpRatioAll(String pRatioAll) {
        this.pRatioAll = pRatioAll;
    }

    public Integer getOtherNumber() {
        return otherNumber;
    }

    public void setOtherNumber(Integer otherNumber) {
        this.otherNumber = otherNumber;
    }

    public String getoRatioAll() {
        return oRatioAll;
    }

    public void setoRatioAll(String oRatioAll) {
        this.oRatioAll = oRatioAll;
    }

    public String getFixedAssets() {
        return fixedAssets;
    }

    public void setFixedAssets(String fixedAssets) {
        this.fixedAssets = fixedAssets;
    }

    public Integer getEquipmentNumber() {
        return equipmentNumber;
    }

    public void setEquipmentNumber(Integer equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }

    public String getPropertyRights() {
        return propertyRights;
    }

    public void setPropertyRights(String propertyRights) {
        this.propertyRights = propertyRights;
    }

    public String getPropertyRightsValue() {
        return propertyRightsValue;
    }

    public void setPropertyRightsValue(String propertyRightsValue) {
        this.propertyRightsValue = propertyRightsValue;
    }

    public String getInstitutionsArea() {
        return institutionsArea;
    }

    public void setInstitutionsArea(String institutionsArea) {
        this.institutionsArea = institutionsArea;
    }

    public String getSiteArea() {
        return siteArea;
    }

    public void setSiteArea(String siteArea) {
        this.siteArea = siteArea;
    }

    public String getOutdoorsiteArea() {
        return outdoorsiteArea;
    }

    public void setOutdoorsiteArea(String outdoorsiteArea) {
        this.outdoorsiteArea = outdoorsiteArea;
    }

    public String getWhArea() {
        return whArea;
    }

    public void setWhArea(String whArea) {
        this.whArea = whArea;
    }

    public String getSitePropertyRights() {
        return sitePropertyRights;
    }

    public void setSitePropertyRights(String sitePropertyRights) {
        this.sitePropertyRights = sitePropertyRights;
    }

    public String getSitePropertyRightsValue() {
        return sitePropertyRightsValue;
    }

    public void setSitePropertyRightsValue(String sitePropertyRightsValue) {
        this.sitePropertyRightsValue = sitePropertyRightsValue;
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


}
