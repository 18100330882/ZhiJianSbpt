package com.yongjie.ZhiJianSbpt.model;

import java.util.Date;


public class ShiYsJyjcFuJian {

    private long id;
    private long sqsId;
    private String serialNumber;
    private String fileTypeId;
    private String fileTitle;
    private String fileExtension;
    private String fileSize;
    private String filePath;
    private Integer type;
    private String cdId;
    private String czr;
    private Date czDate;
    private String isSave;
    private String filePathXchc;
    private String filePathWord;
    private String calOrCma;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSqsId() {
        return sqsId;
    }

    public void setSqsId(long sqsId) {
        this.sqsId = sqsId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getFileTypeId() {
        return fileTypeId;
    }

    public void setFileTypeId(String fileTypeId) {
        this.fileTypeId = fileTypeId;
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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
}
