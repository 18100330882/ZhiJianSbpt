package com.yongjie.ZhiJianSbpt.model;

import java.util.Date;

public class ApiShiYsJyjcSqs {
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 机构名称
     */
    private String jgmc;
    /**
     * 注册地址地址
     */
    private String registerAdress;
    /**
     * 实验室地址
     */
    private String adress;
    /**
     * 邮编
     */
    private String postcode;
    /**
     * 传真
     */
    private String fax;
    /**
     * E-mail
     */
    private String email;
    /**
     * 负责人
     */
    private String principal;
    /**
     * 职务
     */
    private String position;
    /**
     * 固定电话
     */
    private String telephone;
    /**
     * 手机
     */
    private String phone;
    /**
     * 联络人
     */
    private String contactPerson;
    /**
     * 联络人职务
     */
    private String contactPosition;
    /**
     * 联络人固定电话
     */
    private String contactTelephone;
    /**
     * 联络人手机
     */
    private String contactPhone;
    /**
     * 社会信用代码
     */
    private String shxydm;
    /**
     * 法定代表人
     */
    private String legalPerson;
    /**
     * 所属法人单位名称
     */
    private String legalunitName;
    /**
     * 所属法人单位地址
     */
    private String legalunitAdress;
    /**
     * 所属法人单位负责人
     */
    private String legalPrincipal;
    /**
     * 所属法人单位负责人职务
     */
    private String legalposition;
    /**
     * 所属法人单位负责人电话
     */
    private String legalphone;
    /**
     * 所属法人单位社会信用代码
     */
    private String legalshxydm;
    /**
     * 主管单位名称
     */
    private String competentunitName;
    /**
     * 主管单位地址
     */
    private String competentunitAdress;
    /**
     * 主管单位负责人
     */
    private String competentPrincipal;
    /**
     * 主管单位负责人职务
     */
    private String competentposition;
    /**
     * 主管单位负责人电话
     */
    private String competentphone;
    /**
     * 检验检测机构设施特点:固定   临时   可移动   多场所
     */
    private String characteristic;
    /**
     * 检验检测机构法人类型:独立法人检验检测机构/非独立法人检验检测机构
     */
    private String legalPersonjgType;
    /**
     * 检验检测机构所属法人:社团法人/事业法人/企业法人/其他
     */
    private String legalPersonType;
    /**
     * 流水号
     */
    private String serialNumber;
    /**
     * 证书编号
     */
    private String zsbh;
    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 更新日期
     */
    private Date updateDate;


    /**
     * 新增字段
     */


    public ApiShiYsJyjcSqs() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJgmc() {
        return jgmc;
    }

    public void setJgmc(String jgmc) {
        this.jgmc = jgmc;
    }

    public String getRegisterAdress() {
        return registerAdress;
    }

    public void setRegisterAdress(String registerAdress) {
        this.registerAdress = registerAdress;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZsbh() {
        return zsbh;
    }

    public void setZsbh(String zsbh) {
        this.zsbh = zsbh;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPosition() {
        return contactPosition;
    }

    public void setContactPosition(String contactPosition) {
        this.contactPosition = contactPosition;
    }

    public String getContactTelephone() {
        return contactTelephone;
    }

    public void setContactTelephone(String contactTelephone) {
        this.contactTelephone = contactTelephone;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }


    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getLegalunitName() {
        return legalunitName;
    }

    public void setLegalunitName(String legalunitName) {
        this.legalunitName = legalunitName;
    }

    public String getLegalunitAdress() {
        return legalunitAdress;
    }

    public void setLegalunitAdress(String legalunitAdress) {
        this.legalunitAdress = legalunitAdress;
    }

    public String getLegalPrincipal() {
        return legalPrincipal;
    }

    public void setLegalPrincipal(String legalPrincipal) {
        this.legalPrincipal = legalPrincipal;
    }

    public String getLegalposition() {
        return legalposition;
    }

    public void setLegalposition(String legalposition) {
        this.legalposition = legalposition;
    }

    public String getLegalphone() {
        return legalphone;
    }

    public void setLegalphone(String legalphone) {
        this.legalphone = legalphone;
    }


    public String getCompetentunitName() {
        return competentunitName;
    }

    public void setCompetentunitName(String competentunitName) {
        this.competentunitName = competentunitName;
    }

    public String getCompetentunitAdress() {
        return competentunitAdress;
    }

    public void setCompetentunitAdress(String competentunitAdress) {
        this.competentunitAdress = competentunitAdress;
    }

    public String getCompetentPrincipal() {
        return competentPrincipal;
    }

    public void setCompetentPrincipal(String competentPrincipal) {
        this.competentPrincipal = competentPrincipal;
    }

    public String getCompetentposition() {
        return competentposition;
    }

    public void setCompetentposition(String competentposition) {
        this.competentposition = competentposition;
    }

    public String getCompetentphone() {
        return competentphone;
    }

    public void setCompetentphone(String competentphone) {
        this.competentphone = competentphone;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public String getLegalPersonjgType() {
        return legalPersonjgType;
    }

    public void setLegalPersonjgType(String legalPersonjgType) {
        this.legalPersonjgType = legalPersonjgType;
    }

    public String getLegalPersonType() {
        return legalPersonType;
    }

    public void setLegalPersonType(String legalPersonType) {
        this.legalPersonType = legalPersonType;
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

    public String getShxydm() {
        return shxydm;
    }

    public void setShxydm(String shxydm) {
        this.shxydm = shxydm;
    }

    public String getLegalshxydm() {
        return legalshxydm;
    }

    public void setLegalshxydm(String legalshxydm) {
        this.legalshxydm = legalshxydm;
    }

}
