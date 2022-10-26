package com.yongjie.ZhiJianSbpt.startFlow.model;

import java.util.Date;

public class CancellationTable {
    //检验检测机构资质注销申请表
    private long id; //主键id
    private String serialNumber; //流水号
    private String organizationName; //机构名称
    private Date organizationDate;  //机构名称后日期
    private String organizationAddress;  //机构地址
    private String certificateNumber;  //证书编号
    private Date startDate;  //起始时间
    private Date endDate;  //结束时间
    private String contacts;  //联系人
    private String contactsPhone;  //联系手机
    private String unitPeople;  //单位负责人
    private String unitPhone;  //单位负责人手机
    private String cancelReason;  //申请注销的详细原因
    private Date cancelDate;  //申请注销的日期
    private String checkOpinion;  //市局审核部门意见
    private Date checkDate;  //市局审核部门意见的日期
    private String spbmOpinion;  //审批部门意见
    private Date spbmDate;  //审批部门意见的日期
    private Date sqrq;  //申请日期

    /**
     * 状态 1预审核 2环节 3预审核退回4环节退回 5待激活 6归档
     */
    private String flag;

    /**
     * 统一社会信用代码
     */
    private String shxydm;

    /**
     * 区域代码
     */
    private String areaNumber;

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 区域ID
     */
    private Long areaId;

    /**
     * 审核区域ID
     */
    private Long checkAreaId;

    /**
     * 流程ID
     */
    private Long flowId;
    /**
     * 流程实例ID
     */
    private Long flowInstId;

    /**
     * 当前环节
     */
    private String nodeId;

    /**
     * 环节办理日期
     */
    private Date nodeDate;

    /**
     * 年份
     */
    private Integer year;

    /**
     * 月
     */
    private Integer month;

    /**
     * 季度
     */
    private Integer quarter;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 更新日期
     */
    private Date updateDate;

    /**
     * 环节状态 0正常 1环节退回
     */
    private Integer rflag;

    public CancellationTable() {
    }

    public CancellationTable(long id, String serialNumber, String organizationName, Date organizationDate, String organizationAddress, String certificateNumber, Date startDate, Date endDate, String contacts, String contactsPhone, String unitPeople, String unitPhone, String cancelReason, Date cancelDate, String checkOpinion, Date checkDate, String spbmOpinion, Date spbmDate, Date sqrq, String flag, String shxydm, String areaNumber, String areaName, Long areaId, Long checkAreaId, Long flowId, Long flowInstId, String nodeId, Date nodeDate, Integer year, Integer month, Integer quarter, Date createDate, Date updateDate, Integer rflag) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.organizationName = organizationName;
        this.organizationDate = organizationDate;
        this.organizationAddress = organizationAddress;
        this.certificateNumber = certificateNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.contacts = contacts;
        this.contactsPhone = contactsPhone;
        this.unitPeople = unitPeople;
        this.unitPhone = unitPhone;
        this.cancelReason = cancelReason;
        this.cancelDate = cancelDate;
        this.checkOpinion = checkOpinion;
        this.checkDate = checkDate;
        this.spbmOpinion = spbmOpinion;
        this.spbmDate = spbmDate;
        this.sqrq = sqrq;
        this.flag = flag;
        this.shxydm = shxydm;
        this.areaNumber = areaNumber;
        this.areaName = areaName;
        this.areaId = areaId;
        this.checkAreaId = checkAreaId;
        this.flowId = flowId;
        this.flowInstId = flowInstId;
        this.nodeId = nodeId;
        this.nodeDate = nodeDate;
        this.year = year;
        this.month = month;
        this.quarter = quarter;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.rflag = rflag;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Date getOrganizationDate() {
        return organizationDate;
    }

    public void setOrganizationDate(Date organizationDate) {
        this.organizationDate = organizationDate;
    }

    public String getOrganizationAddress() {
        return organizationAddress;
    }

    public void setOrganizationAddress(String organizationAddress) {
        this.organizationAddress = organizationAddress;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }

    public String getUnitPeople() {
        return unitPeople;
    }

    public void setUnitPeople(String unitPeople) {
        this.unitPeople = unitPeople;
    }

    public String getUnitPhone() {
        return unitPhone;
    }

    public void setUnitPhone(String unitPhone) {
        this.unitPhone = unitPhone;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    public String getCheckOpinion() {
        return checkOpinion;
    }

    public void setCheckOpinion(String checkOpinion) {
        this.checkOpinion = checkOpinion;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getSpbmOpinion() {
        return spbmOpinion;
    }

    public void setSpbmOpinion(String spbmOpinion) {
        this.spbmOpinion = spbmOpinion;
    }

    public Date getSpbmDate() {
        return spbmDate;
    }

    public void setSpbmDate(Date spbmDate) {
        this.spbmDate = spbmDate;
    }

    public Date getSqrq() {
        return sqrq;
    }

    public void setSqrq(Date sqrq) {
        this.sqrq = sqrq;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getShxydm() {
        return shxydm;
    }

    public void setShxydm(String shxydm) {
        this.shxydm = shxydm;
    }

    public String getAreaNumber() {
        return areaNumber;
    }

    public void setAreaNumber(String areaNumber) {
        this.areaNumber = areaNumber;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Long getCheckAreaId() {
        return checkAreaId;
    }

    public void setCheckAreaId(Long checkAreaId) {
        this.checkAreaId = checkAreaId;
    }

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    public Long getFlowInstId() {
        return flowInstId;
    }

    public void setFlowInstId(Long flowInstId) {
        this.flowInstId = flowInstId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public Date getNodeDate() {
        return nodeDate;
    }

    public void setNodeDate(Date nodeDate) {
        this.nodeDate = nodeDate;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getQuarter() {
        return quarter;
    }

    public void setQuarter(Integer quarter) {
        this.quarter = quarter;
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

    public Integer getRflag() {
        return rflag;
    }

    public void setRflag(Integer rflag) {
        this.rflag = rflag;
    }
}
