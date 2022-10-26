package com.yongjie.ZhiJianSbpt.model;

import java.util.Date;

public class ApiShiYsJyjcNl {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 流水号
     */
    private String serialNumber;


    /**
     * 大序号 例如 一、 二     > 库中未用 由查询时候,生成
     */
    private String bigNumber;

    /**
     * 类别 例如 ：水泥   大类别  > 1
     */
    private String typeName;

    /**
     * 排序号 小序号 产品名称序号 1.2.3 > 库中未用
     */
    private String sortingNumber;

    /**
     * 用于查询排序和 分配人员时,新增数据,进行排序用
     */
    private String sortNumber;

    /**
     * 状态  ,用户分配人员修改  默认0 可用  1删除
     */
    private String status;

    /**
     * 参数  > 3
     */
    private String parameter;

    /**
     * 依据标准（方法）名称及编号（含年号） >4
     */
    private String standardName;

    /**
     * 依据标准及编号（含年号） GB/T17671-1999  > 未启用
     */
    private String standardNo;


    /**
     * 标识(主键,由接口调用方提供)
     */
    private String unid;

    /**
     * 父ID 一级则为空0  > 已弃用
     */
    private String pid;

    /**
     * 与仪器设备对应编号
     */
    private String identifier;

    private String catalogue;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 更新日期
     */
    private Date updateDate;

    private String yjfl;//一级分类
    private String ejfl; //二级分类
    private String sanjfl;//三级分类
    private String sijfl;//四级分类
    private String yjbz;//依据标准
    private Date pizhunDate;//批准日期
    private String zsbh;//证书编号
    private String gsmc;//公司名称

    private String limits; //限制范围
    private String productName;  //项目名称
    private String instructions;//说明
    private String siteName;//场所地址


    /**
     * 仪器设备（标准物质）名称
     */
    private String bzwzName;

    /**
     * 仪器设备（标准物质）测量范围
     */
    private String bzwzClfw;

    /**
     * 仪器设备  溯源方式
     */
    private String syfs;

    /**
     * 仪器设备 有效日期
     */
    private Date yqsbYxrq;

    /**
     * 仪器设备  确认结果
     */
    private String qrjg;

    /**
     * 0： 能力 1： 仪器设备
     */
    private String flag;

    /**
     * 注：****
     */
    private String remark;

    private Long collectFujianId;

    private String isSp;//食品（1）非食品（2）

    public ApiShiYsJyjcNl() {
        super();
    }

    public String getYjfl() {
        return yjfl;
    }

    public void setYjfl(String yjfl) {
        this.yjfl = yjfl;
    }

    public String getEjfl() {
        return ejfl;
    }

    public void setEjfl(String ejfl) {
        this.ejfl = ejfl;
    }

    public String getSanjfl() {
        return sanjfl;
    }

    public void setSanjfl(String sanjfl) {
        this.sanjfl = sanjfl;
    }

    public String getSijfl() {
        return sijfl;
    }

    public void setSijfl(String sijfl) {
        this.sijfl = sijfl;
    }

    public String getYjbz() {
        return yjbz;
    }

    public void setYjbz(String yjbz) {
        this.yjbz = yjbz;
    }

    public Date getPizhunDate() {
        return pizhunDate;
    }

    public void setPizhunDate(Date pizhunDate) {
        this.pizhunDate = pizhunDate;
    }

    public String getZsbh() {
        return zsbh;
    }

    public void setZsbh(String zsbh) {
        this.zsbh = zsbh;
    }

    public String getGsmc() {
        return gsmc;
    }

    public void setGsmc(String gsmc) {
        this.gsmc = gsmc;
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

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getBigNumber() {
        return bigNumber;
    }

    public void setBigNumber(String bigNumber) {
        this.bigNumber = bigNumber;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getSortingNumber() {
        return sortingNumber;
    }

    public void setSortingNumber(String sortingNumber) {
        this.sortingNumber = sortingNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }

    public String getStandardNo() {
        return standardNo;
    }

    public void setStandardNo(String standardNo) {
        this.standardNo = standardNo;
    }

    public String getLimits() {
        return limits;
    }

    public void setLimits(String limits) {
        this.limits = limits;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getUnid() {
        return unid;
    }

    public void setUnid(String unid) {
        this.unid = unid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
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

    public String getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(String sortNumber) {
        this.sortNumber = sortNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getCatalogue() {
        return catalogue;
    }

    public void setCatalogue(String catalogue) {
        this.catalogue = catalogue;
    }

    public String getBzwzName() {
        return bzwzName;
    }

    public void setBzwzName(String bzwzName) {
        this.bzwzName = bzwzName;
    }

    public String getBzwzClfw() {
        return bzwzClfw;
    }

    public void setBzwzClfw(String bzwzClfw) {
        this.bzwzClfw = bzwzClfw;
    }

    public String getSyfs() {
        return syfs;
    }

    public void setSyfs(String syfs) {
        this.syfs = syfs;
    }

    public String getQrjg() {
        return qrjg;
    }

    public void setQrjg(String qrjg) {
        this.qrjg = qrjg;
    }

    public Date getYqsbYxrq() {
        return yqsbYxrq;
    }

    public void setYqsbYxrq(Date yqsbYxrq) {
        this.yqsbYxrq = yqsbYxrq;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCollectFujianId() {
        return collectFujianId;
    }

    public void setCollectFujianId(Long collectFujianId) {
        this.collectFujianId = collectFujianId;
    }

    public String getIsSp() {
        return isSp;
    }

    public void setIsSp(String isSp) {
        this.isSp = isSp;
    }
}
