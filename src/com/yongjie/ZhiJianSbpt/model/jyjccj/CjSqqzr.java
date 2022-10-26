package com.yongjie.ZhiJianSbpt.model.jyjccj;

import java.util.Date;

/**
 * 采集授权签字人表
 */
public class CjSqqzr {

    /**
     * 主键ID
     */
    private Long id;
    /**
     * 附件ID
     */
    private Long collectFujianId;
    /**
     * 证书编号
     */
    private String zsbh;
    /**
     * 签字人名字
     */
    private String qzrName;
    /**
     * 性别
     */
    private String sex;
    /**
     * 职务
     */
    private String zhiw;
    /**
     * 职称
     */
    private String zhic;
    /**
     * 文化程度
     */
    private String education;
    /**
     * 部门
     */
    private String dept;
    /**
     * 手机号码
     */
    private String telephone;
    /**
     * EMAIL邮箱
     */
    private String email;
    /**
     * 签字领域
     */
    private String qzly;
    /**
     * 身份证号
     */
    private String sfzh;
    /**
     * 批准日期
     */
    private Date pizhunDate;
    /**
     * 操作人
     */
    private String czr;
    /**
     * 操作日期
     */
    private Date czdate;

    private Date birthday;
    private String fax;
    private String bySchool;
    private String zhuanye;
    private String peixun;
    private String zgzs;
    private String gzjl;
    private String xgsm;
    private String zzjgdm;
    private Long jgclassifyId;
    private Long sqsId;
    private Integer jgstate;//检验检测机构基本信息上报中的用到的状态：-1：未上报；-9：已上报；-5：已退回；1：已审核；-6：之前导入的老数据
    private Integer cdid;//场地id
    private String isSp;//食品和非食品
    private String cddz;
    private String qzrpj;

    private Double paixu;//保存文档时的5.1 5.2 ...

    private String iszzpj;//1:组长评价;0组员评价

    private String xueli;//学历及职称情况
    private String khjg;//考核结果
    private String gzpxjl;//工作及培训经历

    private String signImg;//审查员签名图片路径

    private String originalSignatureArea; // 原授权签字领域
    private String deleteType;// 删除类型 0是未删除 1是已删除

    private String moreAddress;

    private String fileTitle;

    private String filePath;

    private String suffix;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCollectFujianId() {
        return collectFujianId;
    }

    public void setCollectFujianId(Long collectFujianId) {
        this.collectFujianId = collectFujianId;
    }

    public String getZsbh() {
        return zsbh;
    }

    public void setZsbh(String zsbh) {
        this.zsbh = zsbh;
    }

    public String getQzrName() {
        return qzrName;
    }

    public void setQzrName(String qzrName) {
        this.qzrName = qzrName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public Date getPizhunDate() {
        return pizhunDate;
    }

    public void setPizhunDate(Date pizhunDate) {
        this.pizhunDate = pizhunDate;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
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

    public String getDeleteType() {
        return deleteType;
    }

    public void setDeleteType(String deleteType) {
        this.deleteType = deleteType;
    }

    public String getMoreAddress() {
        return moreAddress;
    }

    public void setMoreAddress(String moreAddress) {
        this.moreAddress = moreAddress;
    }

    public String getFileTitle() {
        return fileTitle;
    }

    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getIsSp() {
        return isSp;
    }

    public void setIsSp(String isSp) {
        this.isSp = isSp;
    }
}
