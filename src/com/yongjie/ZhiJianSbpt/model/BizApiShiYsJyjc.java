package com.yongjie.ZhiJianSbpt.model;

import java.util.Date;

public class BizApiShiYsJyjc {

    /**
     * 主键ID
     */
    private Long id;
    /**
     * 流程ID
     */
    private Long flowId;

    /**
     * 流程实例ID
     */
    private Long flowInstId;

    /**
     * 流水号
     */
    private String serialNumber;

    /**
     * CMA / CAL申请类型
     * CMA || CMA+验收 || CMA+授权 || CAL验收 || CAL授权
     */
    private String cmaCalType;

    /**
     * 专业领域类别代码
     */
    private String zylylbDm;
    /**
     * 专业领域类别名称
     */
    private String zylylbName;

    /**
     * 机构名称
     */
    private String jgmc;

    /**
     * 主管部门代码
     */
    private String zgbmDm;

    /**
     * 主管部门名称
     */
    private String zgbmName;

    /**
     * 申请日期
     */
    private Date sqrq;

    /**
     * 希望评审日期
     */
    private Date xwpsrq;

    /**
     * 统一社会信用代码
     */
    private String shxydm;

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 区域代码
     */
    private String areaNumber;

    /**
     * 区域ID
     */
    private Long areaId;

    /**
     * 审核区域ID
     */
    private Long checkAreaId;

    /**
     * 状态 1预审核 2环节 3预审核退回4环节退回 5待激活 6归档7上报端受理环节
     */
    private Integer flag;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 更新日期
     */
    private Date updateDate;

    /**
     * 条码
     */
    private String tiaoMa;

    /**
     * 环节状态 0正常 1环节退回
     */
    private Integer rflag;

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
     * 文审 0 指派评审员1
     * 指派类型
     */
    private String designateType;

    private String clyshCzr;//材料预审核操作人

    private String wsjl;//文审结论

    private String xcpsjl;//现场评审结论

    private String jhzt;//文审结论，0是代表激活了

    private String status;//0提交 1未提交
    private String bxflag;//0提交 1未提交

    private Date gdDate;

    /**
     * 首次申请或检验检测项目设计强制性标准技术规范
     */
    private String isFirstOrOther;

    /**
     * 自主撤回原因
     */
    private String zzch_reason;

    /**
     * 自主撤回 时间
     */
    private Date zzch_date;

    /**
     * 自主撤回 数据 flag
     */
    private String zzch_flag;

    /**
     * 操作用户
     */
    private String createUser;

    /**
     * 上报 区域 名称
     */
    private String checkAreaName;

    /**
     * 自主撤回是否同意
     */
    private String zzch_isagree;
    /**
     * 自主撤回 意见
     */
    private String zzch_suggestion;

    public BizApiShiYsJyjc() {
        super();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getBxflag() {
        return bxflag;
    }

    public void setBxflag(String bxflag) {
        this.bxflag = bxflag;
    }

    public Date getGdDate() {
        return gdDate;
    }

    public void setGdDate(Date gdDate) {
        this.gdDate = gdDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getCmaCalType() {
        return cmaCalType;
    }

    public void setCmaCalType(String cmaCalType) {
        this.cmaCalType = cmaCalType;
    }

    public String getZylylbDm() {
        return zylylbDm;
    }

    public void setZylylbDm(String zylylbDm) {
        this.zylylbDm = zylylbDm;
    }

    public String getZylylbName() {
        return zylylbName;
    }

    public void setZylylbName(String zylylbName) {
        this.zylylbName = zylylbName;
    }

    public String getJgmc() {
        return jgmc;
    }

    public void setJgmc(String jgmc) {
        this.jgmc = jgmc;
    }

    public String getZgbmDm() {
        return zgbmDm;
    }

    public void setZgbmDm(String zgbmDm) {
        this.zgbmDm = zgbmDm;
    }

    public String getZgbmName() {
        return zgbmName;
    }

    public void setZgbmName(String zgbmName) {
        this.zgbmName = zgbmName;
    }

    public Date getSqrq() {
        return sqrq;
    }

    public void setSqrq(Date sqrq) {
        this.sqrq = sqrq;
    }

    public Date getXwpsrq() {
        return xwpsrq;
    }

    public void setXwpsrq(Date xwpsrq) {
        this.xwpsrq = xwpsrq;
    }

    public String getShxydm() {
        return shxydm;
    }

    public void setShxydm(String shxydm) {
        this.shxydm = shxydm;
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

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
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

    public String getTiaoMa() {
        return tiaoMa;
    }

    public void setTiaoMa(String tiaoMa) {
        this.tiaoMa = tiaoMa;
    }

    public Integer getRflag() {
        return rflag;
    }

    public void setRflag(Integer rflag) {
        this.rflag = rflag;
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

    public String getAreaNumber() {
        return areaNumber;
    }

    public void setAreaNumber(String areaNumber) {
        this.areaNumber = areaNumber;
    }

    public String getDesignateType() {
        return designateType;
    }

    public void setDesignateType(String designateType) {
        this.designateType = designateType;
    }

    public String getClyshCzr() {
        return clyshCzr;
    }

    public void setClyshCzr(String clyshCzr) {
        this.clyshCzr = clyshCzr;
    }

    public String getWsjl() {
        return wsjl;
    }

    public void setWsjl(String wsjl) {
        this.wsjl = wsjl;
    }

    public String getXcpsjl() {
        return xcpsjl;
    }

    public void setXcpsjl(String xcpsjl) {
        this.xcpsjl = xcpsjl;
    }

    public String getJhzt() {
        return jhzt;
    }

    public void setJhzt(String jhzt) {
        this.jhzt = jhzt;
    }

    public String getIsFirstOrOther() {
        return isFirstOrOther;
    }

    public void setIsFirstOrOther(String isFirstOrOther) {
        this.isFirstOrOther = isFirstOrOther;
    }

    public String getZzch_reason() {
        return zzch_reason;
    }

    public void setZzch_reason(String zzch_reason) {
        this.zzch_reason = zzch_reason;
    }

    public Date getZzch_date() {
        return zzch_date;
    }

    public void setZzch_date(Date zzch_date) {
        this.zzch_date = zzch_date;
    }

    public String getZzch_flag() {
        return zzch_flag;
    }

    public void setZzch_flag(String zzch_flag) {
        this.zzch_flag = zzch_flag;
    }

    public String getCheckAreaName() {
        return checkAreaName;
    }

    public void setCheckAreaName(String checkAreaName) {
        this.checkAreaName = checkAreaName;
    }

    public String getZzch_suggestion() {
        return zzch_suggestion;
    }

    public void setZzch_suggestion(String zzch_suggestion) {
        this.zzch_suggestion = zzch_suggestion;
    }

    public String getZzch_isagree() {
        return zzch_isagree;
    }

    public void setZzch_isagree(String zzch_isagree) {
        this.zzch_isagree = zzch_isagree;
    }
}
