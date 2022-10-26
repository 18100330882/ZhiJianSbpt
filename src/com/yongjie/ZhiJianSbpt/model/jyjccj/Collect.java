package com.yongjie.ZhiJianSbpt.model.jyjccj;

import java.util.Date;

public class Collect {
    private Long id;
    private String name;
    private String type;
    private String zsbh;
    private String collectPath;
    private String czr;
    private Date collectDate;
    private Date createDate;
    private Date updateDate;
    private Date pizhunDate;
    private String flag; //0未上报 //1已上报 //2审核通过
    private String serialNumber;
    private String Yuliu2;
    private String Yuliu3;
    private String Yuliu4;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCollectPath() {
        return collectPath;
    }

    public void setCollectPath(String collectPath) {
        this.collectPath = collectPath;
    }

    public Date getCollectDate() {
        return collectDate;
    }

    public void setCollectDate(Date collectDate) {
        this.collectDate = collectDate;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }


    public String getYuliu2() {
        return Yuliu2;
    }

    public void setYuliu2(String yuliu2) {
        Yuliu2 = yuliu2;
    }

    public String getYuliu3() {
        return Yuliu3;
    }

    public void setYuliu3(String yuliu3) {
        Yuliu3 = yuliu3;
    }

    public String getYuliu4() {
        return Yuliu4;
    }

    public void setYuliu4(String yuliu4) {
        Yuliu4 = yuliu4;
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

    public String getCzr() {
        return czr;
    }

    public void setCzr(String czr) {
        this.czr = czr;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
