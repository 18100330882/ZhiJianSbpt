package com.yongjie.ZhiJianSbpt.model;

import java.util.Date;

public class ShiYsJyjcXchcPsbgNlHz {
    private long id;
    private long flowId;
    private long flowInstId;
    private String fileTitle;
    private String fileExtension;
    private String fileSize;
    private Integer type;//1：食品 4：非食品 7:检测人员 8：签字人
    private String cdId;//场地地址Id
    private String cddz;//场地地址
    private String czr;
    private Date czDate;
    private String isSave;//是否完成保存
    private String filePathXchc;//上传核准的excle后的路径
    private String filePathWord;//保存为word的物理路径
    private String calOrCma;//CMA或者CAL
    private Long wenShuId;//证书附表文书Id
    private Integer nlOrSb;//能力的附件还是设备的附件   0能力   1设备

    private String serialNumber;

    private String isSp;//食品、非食品

    /**
     * 来源 企业端、审批端
     */
    private String comeFrom;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFlowId() {
        return flowId;
    }

    public void setFlowId(long flowId) {
        this.flowId = flowId;
    }

    public long getFlowInstId() {
        return flowInstId;
    }

    public void setFlowInstId(long flowInstId) {
        this.flowInstId = flowInstId;
    }

    public String getFileTitle() {
        return fileTitle;
    }

    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCdId() {
        return cdId;
    }

    public void setCdId(String cdId) {
        this.cdId = cdId;
    }

    public String getCddz() {
        return cddz;
    }

    public void setCddz(String cddz) {
        this.cddz = cddz;
    }

    public String getCzr() {
        return czr;
    }

    public void setCzr(String czr) {
        this.czr = czr;
    }

    public Date getCzDate() {
        return czDate;
    }

    public void setCzDate(Date czDate) {
        this.czDate = czDate;
    }

    public String getIsSave() {
        return isSave;
    }

    public void setIsSave(String isSave) {
        this.isSave = isSave;
    }

    public String getFilePathXchc() {
        return filePathXchc;
    }

    public void setFilePathXchc(String filePathXchc) {
        this.filePathXchc = filePathXchc;
    }

    public String getFilePathWord() {
        return filePathWord;
    }

    public void setFilePathWord(String filePathWord) {
        this.filePathWord = filePathWord;
    }

    public String getCalOrCma() {
        return calOrCma;
    }

    public void setCalOrCma(String calOrCma) {
        this.calOrCma = calOrCma;
    }

    public Long getWenShuId() {
        return wenShuId;
    }

    public void setWenShuId(Long wenShuId) {
        this.wenShuId = wenShuId;
    }

    public Integer getNlOrSb() {
        return nlOrSb;
    }

    public void setNlOrSb(Integer nlOrSb) {
        this.nlOrSb = nlOrSb;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getIsSp() {
        return isSp;
    }

    public void setIsSp(String isSp) {
        this.isSp = isSp;
    }

    public String getComeFrom() {
        return comeFrom;
    }

    public void setComeFrom(String comeFrom) {
        this.comeFrom = comeFrom;
    }
}
