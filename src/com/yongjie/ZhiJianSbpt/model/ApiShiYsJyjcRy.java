package com.yongjie.ZhiJianSbpt.model;

import java.util.Date;

public class ApiShiYsJyjcRy {
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 文化程度
     */
    private String education;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 职务(岗位)
     */
    private String position;
    /**
     * 职称
     */
    private String jobTitle;
    /**
     * 所学专业
     */
    private String professional;
    /**
     * 从事本技术领域年限
     */
    private String technicalFieldYear;
    /**
     * 现在部门岗位
     */
    private String department;
    /**
     * 检验检测机构地址
     */
    private String address;
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
    private String unid;

    /**
     * 证件类型
     */
    private String documentType;
    /**
     * 学历
     */
    private String EducationZM;

    /**
     *
     */
    private String jgmc;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件 类型 名
     */
    private String fileTypeName;

    /**
     * 本地文件存储路径
     */
    private String localFilePath;

    public ApiShiYsJyjcRy() {
        super();
    }

    public String getUnid() {
        return unid;
    }

    public void setUnid(String unid) {
        this.unid = unid;
    }

    public String getEducationZM() {
        return EducationZM;
    }

    public void setEducationZM(String educationZM) {
        this.EducationZM = educationZM;
    }

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

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
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

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public String getTechnicalFieldYear() {
        return technicalFieldYear;
    }

    public void setTechnicalFieldYear(String technicalFieldYear) {
        this.technicalFieldYear = technicalFieldYear;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
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
