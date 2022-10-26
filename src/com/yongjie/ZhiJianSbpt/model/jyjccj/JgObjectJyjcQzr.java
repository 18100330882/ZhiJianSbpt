package com.yongjie.ZhiJianSbpt.model.jyjccj;

import java.util.Date;

public class JgObjectJyjcQzr {

    private long id;
    private String qzrName;
    private String sex;
    private Date birthday;
    private String zhiw;
    private String zhic;
    private String education;
    private String dept;
    private String fax;
    private String email;
    private String qzly;
    private String telephone;
    private String bySchool;
    private String zhuanye;
    private String peixun;
    private String zgzs;
    private String gzjl;
    private String xgsm;
    private String zzjgdm;
    private Long jgclassifyId;
    private String czr;
    private Date czdate;
    private Date pizhunDate;
    private Long sqsId;
    private Integer jgstate;//检验检测机构基本信息上报中的用到的状态：-1：未上报；-9：已上报；-5：已退回；1：已审核；-6：之前导入的老数据
    private Integer cdid;//场地id
    private Integer sporfspid;//食品和非食品
    private String sfzh;
    private String cddz;
    private String qzrpj;

    private String zsbh;

    private Double paixu;//保存文档时的5.1 5.2 ...

    private String serialNumber;//流水号
    private String iszzpj;//1:组长评价;0组员评价

    private String xueli;//学历及职称情况
    private String khjg;//考核结果
    private String gzpxjl;//工作及培训经历

    private String signImg;//审查员签名图片路径

    private String originalSignatureArea; // 原授权签字领域
    private String changeType;// 变更类型
    private String deleteType;// 删除类型 0是未删除 1是已删除

    private Long collectFujianId;


    public String getDeleteType() {
        return deleteType;
    }

    public void setDeleteType(String deleteType) {
        this.deleteType = deleteType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQzrName() {
        return qzrName;
    }

    public void setQzrName(String qzrName) {
        this.qzrName = qzrName;
    }


    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getZhiw() {
        return zhiw;
    }

    public void setZhiw(String zhiw) {
        this.zhiw = zhiw;
    }

    public String getZhic() {
        return zhic;
    }

    public void setZhic(String zhic) {
        this.zhic = zhic;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
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

    public String getQzly() {
        return qzly;
    }

    public void setQzly(String qzly) {
        this.qzly = qzly;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getBySchool() {
        return bySchool;
    }

    public void setBySchool(String bySchool) {
        this.bySchool = bySchool;
    }

    public String getZhuanye() {
        return zhuanye;
    }

    public void setZhuanye(String zhuanye) {
        this.zhuanye = zhuanye;
    }

    public String getPeixun() {
        return peixun;
    }

    public void setPeixun(String peixun) {
        this.peixun = peixun;
    }

    public String getZgzs() {
        return zgzs;
    }

    public void setZgzs(String zgzs) {
        this.zgzs = zgzs;
    }

    public String getGzjl() {
        return gzjl;
    }

    public void setGzjl(String gzjl) {
        this.gzjl = gzjl;
    }

    public String getXgsm() {
        return xgsm;
    }

    public void setXgsm(String xgsm) {
        this.xgsm = xgsm;
    }

    public String getZzjgdm() {
        return zzjgdm;
    }

    public void setZzjgdm(String zzjgdm) {
        this.zzjgdm = zzjgdm;
    }

    public Long getJgclassifyId() {
        return jgclassifyId;
    }

    public void setJgclassifyId(Long jgclassifyId) {
        this.jgclassifyId = jgclassifyId;
    }

    public String getCzr() {
        return czr;
    }

    public void setCzr(String czr) {
        this.czr = czr;
    }

    public Date getCzdate() {
        return czdate;
    }

    public void setCzdate(Date czdate) {
        this.czdate = czdate;
    }

    public Long getSqsId() {
        return sqsId;
    }

    public void setSqsId(Long sqsId) {
        this.sqsId = sqsId;
    }

    public Integer getJgstate() {
        return jgstate;
    }

    public void setJgstate(Integer jgstate) {
        this.jgstate = jgstate;
    }

    public Integer getCdid() {
        return cdid;
    }

    public void setCdid(Integer cdid) {
        this.cdid = cdid;
    }

    public Integer getSporfspid() {
        return sporfspid;
    }

    public void setSporfspid(Integer sporfspid) {
        this.sporfspid = sporfspid;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getCddz() {
        return cddz;
    }

    public void setCddz(String cddz) {
        this.cddz = cddz;
    }

    public String getQzrpj() {
        return qzrpj;
    }

    public void setQzrpj(String qzrpj) {
        this.qzrpj = qzrpj;
    }

    public Double getPaixu() {
        return paixu;
    }

    public void setPaixu(Double paixu) {
        this.paixu = paixu;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getIszzpj() {
        return iszzpj;
    }

    public void setIszzpj(String iszzpj) {
        this.iszzpj = iszzpj;
    }

    public String getXueli() {
        return xueli;
    }

    public void setXueli(String xueli) {
        this.xueli = xueli;
    }

    public String getKhjg() {
        return khjg;
    }

    public void setKhjg(String khjg) {
        this.khjg = khjg;
    }

    public String getGzpxjl() {
        return gzpxjl;
    }

    public void setGzpxjl(String gzpxjl) {
        this.gzpxjl = gzpxjl;
    }

    public String getSignImg() {
        return signImg;
    }

    public void setSignImg(String signImg) {
        this.signImg = signImg;
    }

    public String getOriginalSignatureArea() {
        return originalSignatureArea;
    }

    public void setOriginalSignatureArea(String originalSignatureArea) {
        this.originalSignatureArea = originalSignatureArea;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
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

    public Date getPizhunDate() {
        return pizhunDate;
    }

    public void setPizhunDate(Date pizhunDate) {
        this.pizhunDate = pizhunDate;
    }

    public Long getCollectFujianId() {
        return collectFujianId;
    }

    public void setCollectFujianId(Long collectFujianId) {
        this.collectFujianId = collectFujianId;
    }
}
