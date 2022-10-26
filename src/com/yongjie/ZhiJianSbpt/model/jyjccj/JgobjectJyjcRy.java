package com.yongjie.ZhiJianSbpt.model.jyjccj;

import java.util.Date;

public class JgobjectJyjcRy {

    private Long id;

    private String name;

    private String zsbh;

    /**
     * 新增字段
     *
     * @return
     */
    private String sex;

    private Integer age;

    //文化程度
    private String education;
    //职称
    private String zhic;
    //职务
    private String zhiw;
    //专业
    private String zhuanye;
    //工作年限
    private String YearOfService;

    private String telephone;

    private String dept;
    //身份证号码
    private String sfzh;
    //采集id
    private Long cid;
    //证件类型
    private String cardType;
    //状态
    private String flag;
    //流水号
    private String serialNumber;

    private Date createDate;

    private Date updateDate;

    private Long collectFujianId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZsbh() {
        return zsbh;
    }

    public void setZsbh(String zsbh) {
        this.zsbh = zsbh;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getZhic() {
        return zhic;
    }

    public void setZhic(String zhic) {
        this.zhic = zhic;
    }

    public String getZhuanye() {
        return zhuanye;
    }

    public void setZhuanye(String zhuanye) {
        this.zhuanye = zhuanye;
    }

    public String getYearOfService() {
        return YearOfService;
    }

    public void setYearOfService(String yearOfService) {
        YearOfService = yearOfService;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getZhiw() {
        return zhiw;
    }

    public void setZhiw(String zhiw) {
        this.zhiw = zhiw;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
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

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Long getCollectFujianId() {
        return collectFujianId;
    }

    public void setCollectFujianId(Long collectFujianId) {
        this.collectFujianId = collectFujianId;
    }
}
