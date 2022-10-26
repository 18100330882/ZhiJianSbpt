/**
 * @Author: 朱卫士
 * @Createtime: 2020年6月15日 下午10:56:29
 * @Copyright: Copyright (c) 2017
 * @Company: 北京永杰友信科技有限公司
 * @Version：1.0
 * @Description: 附件
 */
package com.yongjie.ZhiJianSbpt.model.flowBase;

import java.util.Date;

public class ApiFile {

    /**
     * 主键
     */
    private Long id;
    /**
     * 流程ID
     */
    private Long flowId;

    /**
     * 附件名称
     */
    private String fileName;

    /**
     * 附件类型
     */
    private String fileType;

    /**
     * 标识 1申请书 2检验能力 3仪器设备4组织机构图5附件（10.检验检测变更审批时上传的附件）
     */
    private String flag;

    /**
     * 文件存储路径
     */
    private String filePath;

    /**
     * 本地文件存储路径
     */
    private String localFilePath;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 文件base64
     */
    private String fileBase64;

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
     * 状态 0是文件没有下载(本地没有文件) 1文件已经从上报端下载
     * 当filePath 改变,则更新为0
     */
    private Integer state;

    /**
     * 标识(主键,由接口调用方提供)
     */
    private String unid;
    /**
     * YjFlowFuJianList 表id 用于区分（必传的附件类型/选传的附件类型）
     */
    private Long yjFlowFuJianId;

    /**
     * siteAddress 场地名称
     */
    private String siteAddress;

    /**
     * paiXu
     */
    private Integer paiXu;

    /**
     * 操作人
     */
    private String czr;


    public ApiFile() {
        super();
    }

    public String getCzr() {
        return czr;
    }

    public void setCzr(String czr) {
        this.czr = czr;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getLocalFilePath() {
        return localFilePath;
    }

    public void setLocalFilePath(String localFilePath) {
        this.localFilePath = localFilePath;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getFileBase64() {
        return fileBase64;
    }

    public void setFileBase64(String fileBase64) {
        this.fileBase64 = fileBase64;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getUnid() {
        return unid;
    }

    public void setUnid(String unid) {
        this.unid = unid;
    }

    public Long getYjFlowFuJianId() {
        return yjFlowFuJianId;
    }

    public void setYjFlowFuJianId(Long yjFlowFuJianId) {
        this.yjFlowFuJianId = yjFlowFuJianId;
    }

    public String getSiteAddress() {
        return siteAddress;
    }

    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }

    public Integer getPaiXu() {
        return paiXu;
    }

    public void setPaiXu(Integer paiXu) {
        this.paiXu = paiXu;
    }
}
