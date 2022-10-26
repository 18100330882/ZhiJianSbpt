package com.yongjie.ZhiJianSbpt.bggl.model;

import java.util.Date;

/**
 * @ClassName PersonChangeDetails 检验检测机构人员变更备案审批表
 * @Date 2022/3/11 15:42
 * @Version 1.0
 */
public class PersonChangeDetails {
    private Long id;

    /**
     * 变更类型
     */
    private String changeType;

    private Long jyjcryId;

    /**
     * 流水号
     */
    private String serialNumber;

    private String name;

    private String nameAfter;

    private String zsbh;

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
    private String yearOfService;

    private String telephone;

    private String dept;
    //身份证号码
    private String sfzh;

    private String sfzhAfter;

    //证件类型
    private String cardType;
    //状态
    private String flag;

    private String jgAddress;

    private String moreAddress;

    private Date createDate;

    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public Long getJyjcryId() {
        return jyjcryId;
    }

    public void setJyjcryId(Long jyjcryId) {
        this.jyjcryId = jyjcryId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAfter() {
        return nameAfter;
    }

    public void setNameAfter(String nameAfter) {
        this.nameAfter = nameAfter;
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

    public String getZhiw() {
        return zhiw;
    }

    public void setZhiw(String zhiw) {
        this.zhiw = zhiw;
    }

    public String getZhuanye() {
        return zhuanye;
    }

    public void setZhuanye(String zhuanye) {
        this.zhuanye = zhuanye;
    }

    public String getYearOfService() {
        return yearOfService;
    }

    public void setYearOfService(String yearOfService) {
        this.yearOfService = yearOfService;
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

    public String getSfzhAfter() {
        return sfzhAfter;
    }

    public void setSfzhAfter(String sfzhAfter) {
        this.sfzhAfter = sfzhAfter;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
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

    public String getJgAddress() {
        return jgAddress;
    }

    public void setJgAddress(String jgAddress) {
        this.jgAddress = jgAddress;
    }

    public String getMoreAddress() {
        return moreAddress;
    }

    public void setMoreAddress(String moreAddress) {
        this.moreAddress = moreAddress;
    }
}
