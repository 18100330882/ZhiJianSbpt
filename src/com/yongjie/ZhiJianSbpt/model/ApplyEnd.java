package com.yongjie.ZhiJianSbpt.model;

import java.util.Date;

/**
 * @ClassName ApplyEnd
 * @Description 终止申请 自主撤回
 * @Date 2021/12/29 9:08
 * @Version 1.0
 */
public class ApplyEnd {

    private Long id;

    private String serialNumber;

    private Long flowId;

    /**
     * 流程实例ID
     */
    private Long flowInstId;

    /**
     * 申请 原因
     */
    private String applyReason;

    /**
     * 0 : 同意、1： 不同意
     */
    private String isAgree;
    /**
     * 返回原因
     */
    private String backReason;

    /**
     * 申请 人
     */
    private String applyUserName;

    /**
     * 受理人
     */
    private String backUserName;

    /**
     * 申请时间
     */
    private Date applyDate;

    /**
     * 返回时间
     */
    private Date backDate;

    private Date createDate;

    private Date updateDate;

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

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public String getBackReason() {
        return backReason;
    }

    public void setBackReason(String backReason) {
        this.backReason = backReason;
    }

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    public String getBackUserName() {
        return backUserName;
    }

    public void setBackUserName(String backUserName) {
        this.backUserName = backUserName;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Date getBackDate() {
        return backDate;
    }

    public void setBackDate(Date backDate) {
        this.backDate = backDate;
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

    public String getIsAgree() {
        return isAgree;
    }

    public void setIsAgree(String isAgree) {
        this.isAgree = isAgree;
    }
}
