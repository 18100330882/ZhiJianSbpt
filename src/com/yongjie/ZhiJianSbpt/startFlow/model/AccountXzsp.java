package com.yongjie.ZhiJianSbpt.startFlow.model;

// Generated 2016-7-13 9:56:09 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * AccountXzsp generated by hbm2java
 */

public class AccountXzsp implements java.io.Serializable {


    private static final long serialVersionUID = 1L;
    private long id;
    private String userName;//用户名
    private String trueName;//真实姓名
    private String password;//密码
    private String sex;//性别 为String类型直接 填写 男 女
    private Date birthday;//出生日期
    private String telephone;//办公电话
    private String mobile;//手机
    private Long deptId;//所属部门编号
    private Long areaId;
    private String professional;//职称
    private String duties;//职务
    private String workDept;//工作部门
    private String cardNumber;//身份证号
    private String email;//邮箱
    private String address;//通讯地址
    private int isLock;//锁定 0未锁定，1：锁定   特种设备审查员申请、批准的关于这个字段的  2：申请 3：申请后提交 4：退回 0：批准
    private String caoR;//操作人
    private Date caoDate;//操作时间
    private String sclbIds;//审查类别id 多个id 使用逗号隔开

    private String workpj;//工作评价 
    private String shxydm;//社会信用代码,用于监管对象
    private String bank;//银行
    private String bankNumber;//银行卡号

    /**
     * 1推送过来的用户  2超级管理员 3是审查员(查询时候用于排除区域限制)
     */
    private String flag;

    private String hangye;//评审专家的行业

    // 页面显示状态（伪删除数据 flag）
    private String pageDisplayStatus;
    // 年龄字段
    private String age;

    public AccountXzsp(long id, String userName, String trueName, String password, String sex, Date birthday, String telephone, String mobile, Long deptId, Long areaId, String professional, String duties, String workDept, String cardNumber, String email, String address, int isLock, String caoR, Date caoDate, String sclbIds, String workpj, String shxydm, String bank, String bankNumber, String flag, String hangye, String pageDisplayStatus, String age) {
        this.id = id;
        this.userName = userName;
        this.trueName = trueName;
        this.password = password;
        this.sex = sex;
        this.birthday = birthday;
        this.telephone = telephone;
        this.mobile = mobile;
        this.deptId = deptId;
        this.areaId = areaId;
        this.professional = professional;
        this.duties = duties;
        this.workDept = workDept;
        this.cardNumber = cardNumber;
        this.email = email;
        this.address = address;
        this.isLock = isLock;
        this.caoR = caoR;
        this.caoDate = caoDate;
        this.sclbIds = sclbIds;
        this.workpj = workpj;
        this.shxydm = shxydm;
        this.bank = bank;
        this.bankNumber = bankNumber;
        this.flag = flag;
        this.hangye = hangye;
        this.pageDisplayStatus = pageDisplayStatus;
        this.age = age;
    }

    public AccountXzsp() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "AccountXzsp{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", trueName='" + trueName + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", telephone='" + telephone + '\'' +
                ", mobile='" + mobile + '\'' +
                ", deptId=" + deptId +
                ", areaId=" + areaId +
                ", professional='" + professional + '\'' +
                ", duties='" + duties + '\'' +
                ", workDept='" + workDept + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", isLock=" + isLock +
                ", caoR='" + caoR + '\'' +
                ", caoDate=" + caoDate +
                ", sclbIds='" + sclbIds + '\'' +
                ", workpj='" + workpj + '\'' +
                ", shxydm='" + shxydm + '\'' +
                ", bank='" + bank + '\'' +
                ", bankNumber='" + bankNumber + '\'' +
                ", flag='" + flag + '\'' +
                ", hangye='" + hangye + '\'' +
                ", pageDisplayStatus='" + pageDisplayStatus + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public String getDuties() {
        return duties;
    }

    public void setDuties(String duties) {
        this.duties = duties;
    }

    public String getWorkDept() {
        return workDept;
    }

    public void setWorkDept(String workDept) {
        this.workDept = workDept;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIsLock() {
        return isLock;
    }

    public void setIsLock(int isLock) {
        this.isLock = isLock;
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

    public String getSclbIds() {
        return sclbIds;
    }

    public void setSclbIds(String sclbIds) {
        this.sclbIds = sclbIds;
    }

    public String getWorkpj() {
        return workpj;
    }

    public void setWorkpj(String workpj) {
        this.workpj = workpj;
    }

    public String getShxydm() {
        return shxydm;
    }

    public void setShxydm(String shxydm) {
        this.shxydm = shxydm;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getHangye() {
        return hangye;
    }

    public void setHangye(String hangye) {
        this.hangye = hangye;
    }

    public String getPageDisplayStatus() {
        return pageDisplayStatus;
    }

    public void setPageDisplayStatus(String pageDisplayStatus) {
        this.pageDisplayStatus = pageDisplayStatus;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
