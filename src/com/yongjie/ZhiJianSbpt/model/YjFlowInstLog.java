package com.yongjie.ZhiJianSbpt.model;

import java.util.Date;

public class YjFlowInstLog {

    private long id;
    private Long flowInstId;
    private Long nodeInstId;
    private String operationName;
    private String operationMemo;
    private Integer opType;
    private String suggestion;
    private String trueName;
    private String caoR;
    private Date caoDate;
    private String serialNumber;//流水号
    private String startTime;//环节开始日期
    private String isAgree;

    // 0受理 同意，1不同意（不予受理）
    private String slYesOrNo;

    public YjFlowInstLog(long id, Long flowInstId, Long nodeInstId, String operationName, String operationMemo,
                         Integer opType, String suggestion, String trueName, String caoR, Date caoDate, String serialNumber,
                         String startTime) {
        super();
        this.id = id;
        this.flowInstId = flowInstId;
        this.nodeInstId = nodeInstId;
        this.operationName = operationName;
        this.operationMemo = operationMemo;
        this.opType = opType;
        this.suggestion = suggestion;
        this.trueName = trueName;
        this.caoR = caoR;
        this.caoDate = caoDate;
        this.serialNumber = serialNumber;
        this.startTime = startTime;
    }

    public YjFlowInstLog() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getFlowInstId() {
        return flowInstId;
    }

    public void setFlowInstId(Long flowInstId) {
        this.flowInstId = flowInstId;
    }

    public Long getNodeInstId() {
        return nodeInstId;
    }

    public void setNodeInstId(Long nodeInstId) {
        this.nodeInstId = nodeInstId;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getOperationMemo() {
        return operationMemo;
    }

    public void setOperationMemo(String operationMemo) {
        this.operationMemo = operationMemo;
    }

    public Integer getOpType() {
        return opType;
    }

    public void setOpType(Integer opType) {
        this.opType = opType;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getCaoR() {
        return caoR;
    }

    public void setCaoR(String caoR) {
        this.caoR = caoR;
    }

    public Date getCaoDate() {
        return caoDate;
    }

    public void setCaoDate(Date caoDate) {
        this.caoDate = caoDate;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getIsAgree() {
        return isAgree;
    }

    public void setIsAgree(String isAgree) {
        this.isAgree = isAgree;
    }

    public String getSlYesOrNo() {
        return slYesOrNo;
    }

    public void setSlYesOrNo(String slYesOrNo) {
        this.slYesOrNo = slYesOrNo;
    }
}
