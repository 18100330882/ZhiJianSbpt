package com.yongjie.ZhiJianSbpt.model.jyjcbggl;

import java.util.Date;

public class BizApiZwcnBg {
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 流水号(你们自己业务的流水号,标识)
     */
    private String serialNumber;
    /**
     * 状态 1预审核 2环节 3预审核退回4环节退回 5待激活 6归档
     */
    private String flag;
    /**
     * 标识11标准（方法）变更12机构名称变更13机构地址名称变更14机构法人单位变更15机构取消检测检测能力16检验检测机构人员变更17授权签字人变更
     */
    private String state;
    /**
     * 机构名称
     */
    private String jgmc;
    /**
     * 负责人
     */
    private String principal;
    /**
     * 手机
     */
    private String phone;
    /**
     * 申请日期     格式yyyy-MM-dd HH:mm:ss
     */
    private Date sqrq;
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
     * 住所
     */
    private String registerAdress;
    /**
     * 联络人
     */
    private String contactPerson;
    /**
     * 联络人职位
     */
    private String contactPosition;
    /**
     * 联络人固定电话
     */
    private String contactTelephone;
    /**
     * 联络人手机
     */
    private String contactPhone;
    /**
     * cma证书
     */
    private String cmaPermitNumber;
    /**
     * cma有效日期
     */
    private Date cmaPermitEffectiveDate;
    /**
     * cma发证日期
     */
    private Date cmaDateoficcue;
    /**
     * 操作用户
     */
    private String createUser;
    /**
     * 环节状态 0正常 1环节退回
     */
    private Integer rflag;

    /**
     * 上报 区域名称
     */
    private String checkAreaName;

    public BizApiZwcnBg() {
        super();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getJgmc() {
        return jgmc;
    }

    public void setJgmc(String jgmc) {
        this.jgmc = jgmc;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getSqrq() {
        return sqrq;
    }

    public void setSqrq(Date sqrq) {
        this.sqrq = sqrq;
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

    public String getRegisterAdress() {
        return registerAdress;
    }

    public void setRegisterAdress(String registerAdress) {
        this.registerAdress = registerAdress;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPosition() {
        return contactPosition;
    }

    public void setContactPosition(String contactPosition) {
        this.contactPosition = contactPosition;
    }

    public String getContactTelephone() {
        return contactTelephone;
    }

    public void setContactTelephone(String contactTelephone) {
        this.contactTelephone = contactTelephone;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
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

    public Date getCmaDateoficcue() {
        return cmaDateoficcue;
    }

    public void setCmaDateoficcue(Date cmaDateoficcue) {
        this.cmaDateoficcue = cmaDateoficcue;
    }

    public Integer getRflag() {
        return rflag;
    }

    public void setRflag(Integer rflag) {
        this.rflag = rflag;
    }

    public String getCheckAreaName() {
        return checkAreaName;
    }

    public void setCheckAreaName(String checkAreaName) {
        this.checkAreaName = checkAreaName;
    }
}