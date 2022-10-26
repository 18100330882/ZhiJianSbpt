package com.yongjie.ZhiJianSbpt.model;

import java.util.Date;

public class ApiShiYsJyjcQzr {
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 检验检测机构地址
     */
    private String address;
    /**
     * 类型：食品/非食品
     */
    private String peopleType;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 出生年月
     */
    private Date birthday;
    /**
     * 职务(岗位)
     */
    private String position;
    /**
     * 职称
     */
    private String jobTitle;
    /**
     * 文化程度
     */
    private String education;
    /**
     * 部门
     */
    private String department;
    /**
     * 电话
     */
    private String phone;
    /**
     * 传真
     */
    private String fax;
    /**
     * 电子邮件
     */
    private String email;
    /**
     * 申请签字的领域
     */
    private String signatureField;
    /**
     * 毕业学校
     */
    private String school;
    /**
     * 专业
     */
    private String professional;
    /**
     * 执业资格证书
     */
    private String jobCredentials;
    /**
     * 培训
     */
    private String train;
    /**
     * 从事检测机构工作的经历
     */
    private String jobUndergo;
    /**
     * 相关说明（若授权领域有变更更应予以说明）
     */
    private String relevantExplanation;
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
     * 标识(主键,由接口调用方提供)
     */
    private String unid; //学历的
    /**
     * 学历证明材料
     */
    private String EducationZM;
    /**
     * 食品/非食品
     */
    private String isSp;

    /**
     *
     */
    private String jgmc;

    /**
     * 文件类型
     */
    private String fileType;//学历的

    /**
     * 文件 类型 名
     */
    private String fileTypeName;//学历的

    /**
     * 本地文件存储路径
     */
    private String localFilePath;

    private String zcunid;//职称的
    private String zcfileType;//职称的
    private String zcfileTypeName;//职称的
    private String zclocalFilePath;//职称的

    public ApiShiYsJyjcQzr() {
        super();
    }

    public String getZclocalFilePath() {
        return zclocalFilePath;
    }

    public void setZclocalFilePath(String zclocalFilePath) {
        this.zclocalFilePath = zclocalFilePath;
    }

    public String getZcunid() {
        return zcunid;
    }

    public void setZcunid(String zcunid) {
        this.zcunid = zcunid;
    }

    public String getZcfileType() {
        return zcfileType;
    }

    public void setZcfileType(String zcfileType) {
        this.zcfileType = zcfileType;
    }

    public String getZcfileTypeName() {
        return zcfileTypeName;
    }

    public void setZcfileTypeName(String zcfileTypeName) {
        this.zcfileTypeName = zcfileTypeName;
    }

    public String getEducationZM() {
        return EducationZM;
    }

    public void setEducationZM(String educationZM) {
        this.EducationZM = educationZM;
    }

    public String getUnid() {
        return unid;
    }

    public void setUnid(String unid) {
        this.unid = unid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPeopleType() {
        return peopleType;
    }

    public void setPeopleType(String peopleType) {
        this.peopleType = peopleType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getSignatureField() {
        return signatureField;
    }

    public void setSignatureField(String signatureField) {
        this.signatureField = signatureField;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public String getJobCredentials() {
        return jobCredentials;
    }

    public void setJobCredentials(String jobCredentials) {
        this.jobCredentials = jobCredentials;
    }

    public String getTrain() {
        return train;
    }

    public void setTrain(String train) {
        this.train = train;
    }

    public String getJobUndergo() {
        return jobUndergo;
    }

    public void setJobUndergo(String jobUndergo) {
        this.jobUndergo = jobUndergo;
    }

    public String getRelevantExplanation() {
        return relevantExplanation;
    }

    public void setRelevantExplanation(String relevantExplanation) {
        this.relevantExplanation = relevantExplanation;
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

    public String getIsSp() {
        return isSp;
    }

    public void setIsSp(String isSp) {
        this.isSp = isSp;
    }

    public String getJgmc() {
        return jgmc;
    }

    public void setJgmc(String jgmc) {
        this.jgmc = jgmc;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileTypeName() {
        return fileTypeName;
    }

    public void setFileTypeName(String fileTypeName) {
        this.fileTypeName = fileTypeName;
    }

    public String getLocalFilePath() {
        return localFilePath;
    }

    public void setLocalFilePath(String localFilePath) {
        this.localFilePath = localFilePath;
    }
}
