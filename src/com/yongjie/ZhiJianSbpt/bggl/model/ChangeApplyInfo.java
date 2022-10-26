package com.yongjie.ZhiJianSbpt.bggl.model;

import java.util.Date;

/**
 * @ClassName ChangeTypeApplyInfo
 * @Date 2022/3/14 14:58
 * @Version 1.0
 */
public class ChangeApplyInfo {
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
     * 变更类型
     */
    private String changeType;

    /**
     * 联系人
     */
    private String lxrName;

    /**
     * 联系人电话
     */
    private String lxrTel;

    /**
     * 通信地址
     */
    private String address;

    /**
     * 邮编、邮箱
     */
    private String email;

    /**
     * 传真
     */
    private String fax;

    /**
     * 资质认定部门意见
     */
    private String deptOpinion;

    /**
     * 日期（部门意见 日期）
     */
    private Date deptOpinionDate;

    private Date createDate;
    private Date updateDate;

    /**
     * 日期(申请)
     */
    private Date applyDate;

    /**
     * 证书编号
     */
    private String cmaPermitNum;

    /**
     * 证书有效日期
     */
    private Date certificateEfficientDate;

    /**
     * 原地址名称
     */
    private String addressOld;

    /**
     * 拟变更的地址名称
     */
    private String addressNew;

    /**
     * 地址名称变更原因
     */
    private String addressChangeReason;

    /**
     * 法人性质变更
     * （适用于法人单位）
     */
    private String natureChange;

    /**
     * 原法人性质
     */
    private String natureOld;

    /**
     * 变更后法人性质
     */
    private String natureNew;

    /**
     * 备注（法人性质）
     */
    private String natureRemark;

    /**
     * 所在法人单位性质变更
     */
    private String unitChange;

    /**
     * 原法人单位性质
     */
    private String unitOld;
    /**
     * 变更后法人单位性质
     */
    private String unitNew;

    /**
     * 备注（所在法人单位性质变更）
     */
    private String unitRemark;

    /**
     * 所在法人单位名称变更
     */
    private String unitNameChange;

    /**
     * 原法人单位名称
     */
    private String unitNameOld;

    /**
     * 变更后法人单位名称
     */
    private String unitNameNew;

    /**
     * 备注（所在法人单位名称变更）
     */
    private String nuitNameRemark;

    /**
     * 原资质认定获证名称
     */
    private String nameOld;

    /**
     * 拟变更的名称
     */
    private String nameNew;

    /**
     * 更名原因
     */
    private String nameChangeReason;

    /**
     * 检验检测机构所属上级部门意见
     */
    private String superiorDeptOpinion;

    /**
     * 检验检测机构所属上级部门意见 日期
     */
    private Date superiorDeptOpinionDate;


    /**
     * 本机构技术负责人审查意见：
     */
    private String fzrReviewOpinion;

    /**
     * 本机构技术负责人审查意见：日期
     */
    private Date fzrReviewOpinionDate;

    /**
     * 专业技术评价组织/专家审查意见：
     */
    private String zjReviewOpinion;

    /**
     * 专业技术评价组织/专家审查意见：日期
     */
    private Date zjReviewOpinionDate;

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

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public String getLxrName() {
        return lxrName;
    }

    public void setLxrName(String lxrName) {
        this.lxrName = lxrName;
    }

    public String getLxrTel() {
        return lxrTel;
    }

    public void setLxrTel(String lxrTel) {
        this.lxrTel = lxrTel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getDeptOpinion() {
        return deptOpinion;
    }

    public void setDeptOpinion(String deptOpinion) {
        this.deptOpinion = deptOpinion;
    }

    public Date getDeptOpinionDate() {
        return deptOpinionDate;
    }

    public void setDeptOpinionDate(Date deptOpinionDate) {
        this.deptOpinionDate = deptOpinionDate;
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

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public String getCmaPermitNum() {
        return cmaPermitNum;
    }

    public void setCmaPermitNum(String cmaPermitNum) {
        this.cmaPermitNum = cmaPermitNum;
    }

    public Date getCertificateEfficientDate() {
        return certificateEfficientDate;
    }

    public void setCertificateEfficientDate(Date certificateEfficientDate) {
        this.certificateEfficientDate = certificateEfficientDate;
    }

    public String getAddressOld() {
        return addressOld;
    }

    public void setAddressOld(String addressOld) {
        this.addressOld = addressOld;
    }

    public String getAddressNew() {
        return addressNew;
    }

    public void setAddressNew(String addressNew) {
        this.addressNew = addressNew;
    }

    public String getAddressChangeReason() {
        return addressChangeReason;
    }

    public void setAddressChangeReason(String addressChangeReason) {
        this.addressChangeReason = addressChangeReason;
    }

    public String getNatureChange() {
        return natureChange;
    }

    public void setNatureChange(String natureChange) {
        this.natureChange = natureChange;
    }

    public String getNatureOld() {
        return natureOld;
    }

    public void setNatureOld(String natureOld) {
        this.natureOld = natureOld;
    }

    public String getNatureNew() {
        return natureNew;
    }

    public void setNatureNew(String natureNew) {
        this.natureNew = natureNew;
    }

    public String getNatureRemark() {
        return natureRemark;
    }

    public void setNatureRemark(String natureRemark) {
        this.natureRemark = natureRemark;
    }

    public String getUnitChange() {
        return unitChange;
    }

    public void setUnitChange(String unitChange) {
        this.unitChange = unitChange;
    }

    public String getUnitOld() {
        return unitOld;
    }

    public void setUnitOld(String unitOld) {
        this.unitOld = unitOld;
    }

    public String getUnitNew() {
        return unitNew;
    }

    public void setUnitNew(String unitNew) {
        this.unitNew = unitNew;
    }

    public String getUnitRemark() {
        return unitRemark;
    }

    public void setUnitRemark(String unitRemark) {
        this.unitRemark = unitRemark;
    }

    public String getUnitNameChange() {
        return unitNameChange;
    }

    public void setUnitNameChange(String unitNameChange) {
        this.unitNameChange = unitNameChange;
    }

    public String getUnitNameOld() {
        return unitNameOld;
    }

    public void setUnitNameOld(String unitNameOld) {
        this.unitNameOld = unitNameOld;
    }

    public String getUnitNameNew() {
        return unitNameNew;
    }

    public void setUnitNameNew(String unitNameNew) {
        this.unitNameNew = unitNameNew;
    }

    public String getNuitNameRemark() {
        return nuitNameRemark;
    }

    public void setNuitNameRemark(String nuitNameRemark) {
        this.nuitNameRemark = nuitNameRemark;
    }

    public String getNameOld() {
        return nameOld;
    }

    public void setNameOld(String nameOld) {
        this.nameOld = nameOld;
    }

    public String getNameNew() {
        return nameNew;
    }

    public void setNameNew(String nameNew) {
        this.nameNew = nameNew;
    }

    public String getNameChangeReason() {
        return nameChangeReason;
    }

    public void setNameChangeReason(String nameChangeReason) {
        this.nameChangeReason = nameChangeReason;
    }

    public String getFzrReviewOpinion() {
        return fzrReviewOpinion;
    }

    public void setFzrReviewOpinion(String fzrReviewOpinion) {
        this.fzrReviewOpinion = fzrReviewOpinion;
    }

    public Date getFzrReviewOpinionDate() {
        return fzrReviewOpinionDate;
    }

    public void setFzrReviewOpinionDate(Date fzrReviewOpinionDate) {
        this.fzrReviewOpinionDate = fzrReviewOpinionDate;
    }

    public String getZjReviewOpinion() {
        return zjReviewOpinion;
    }

    public void setZjReviewOpinion(String zjReviewOpinion) {
        this.zjReviewOpinion = zjReviewOpinion;
    }

    public Date getZjReviewOpinionDate() {
        return zjReviewOpinionDate;
    }

    public void setZjReviewOpinionDate(Date zjReviewOpinionDate) {
        this.zjReviewOpinionDate = zjReviewOpinionDate;
    }

    public String getSuperiorDeptOpinion() {
        return superiorDeptOpinion;
    }

    public void setSuperiorDeptOpinion(String superiorDeptOpinion) {
        this.superiorDeptOpinion = superiorDeptOpinion;
    }

    public Date getSuperiorDeptOpinionDate() {
        return superiorDeptOpinionDate;
    }

    public void setSuperiorDeptOpinionDate(Date superiorDeptOpinionDate) {
        this.superiorDeptOpinionDate = superiorDeptOpinionDate;
    }
}
