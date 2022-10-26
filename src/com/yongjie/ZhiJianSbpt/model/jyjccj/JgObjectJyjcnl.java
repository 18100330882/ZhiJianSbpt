/**
 * @Author: 王景仟
 * @Createtime: 2017年11月3日 上午8:56:42
 * @Copyright: Copyright (c) 2017
 * @Company: 北京永杰友信科技有限公司
 * @Version：1.0
 * @Description:
 */
package com.yongjie.ZhiJianSbpt.model.jyjccj;

import java.util.Date;

public class JgObjectJyjcnl {

    private long id;
    private long cid;
    private String leib;//大類別 如：建筑材料
    private String cpleib;//小类别 如：水泥
    private String cpxh;
    //三级分类
    private String sanji;
    private String cpmc;//产品名称如 ：水泥强度
    private String yjbzmc;
    private String xzfw;
    private String zzrdZsbh;
    private String sm;
    private Date pizhunDate;
    //仪器的字段
    private String yqsbmc;
    private String yqsbxh;
    private String flag;
    private String yqsbclfw;
    private String syfs;
    private String yxrq;
    private String qrjg;
    private Long type;
    private String dxuhao;
    private String zzjgdm;
    private Long jgclassifyid;
    private Long sqsId;
    //-22:更新档案库时要删除的数据
    private Long jgstate;//检验检测机构基本信息上报中的用到的状态：-1：未上报；-9：已上报；-5：已退回；1：已审核；-6：之前导入的老数据
    private String parentId;
    private String czr;
    private Date czDate;
    private String diZhi;//场地地址
    private String fuJianId;
    private String beizhu;
    private String calOrCma;
    /**
     * 状态  ,用户分配人员修改  默认0 可用  1删除
     */
    private String status;

    private String serialNumber;
    private String sortNum;

    private String identifier;
    private String catalogue;

    private Long collectFujianId;


    private String isSp;//食品（1）非食品（2）


    public Date getPizhunDate() {
        return pizhunDate;
    }

    public void setPizhunDate(Date pizhunDate) {
        this.pizhunDate = pizhunDate;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLeib() {
        return leib;
    }

    public void setLeib(String leib) {
        this.leib = leib;
    }

    public String getCpxh() {
        return cpxh;
    }

    public void setCpxh(String cpxh) {
        this.cpxh = cpxh;
    }

    public String getCpmc() {
        return cpmc;
    }

    public void setCpmc(String cpmc) {
        this.cpmc = cpmc;
    }

    public String getYjbzmc() {
        return yjbzmc;
    }

    public void setYjbzmc(String yjbzmc) {
        this.yjbzmc = yjbzmc;
    }

    public String getXzfw() {
        return xzfw;
    }

    public void setXzfw(String xzfw) {
        this.xzfw = xzfw;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getDxuhao() {
        return dxuhao;
    }

    public void setDxuhao(String dxuhao) {
        this.dxuhao = dxuhao;
    }

    public String getZzjgdm() {
        return zzjgdm;
    }

    public void setZzjgdm(String zzjgdm) {
        this.zzjgdm = zzjgdm;
    }

    public Long getJgclassifyid() {
        return jgclassifyid;
    }

    public void setJgclassifyid(Long jgclassifyid) {
        this.jgclassifyid = jgclassifyid;
    }

    public Long getSqsId() {
        return sqsId;
    }

    public void setSqsId(Long sqsId) {
        this.sqsId = sqsId;
    }

    public Long getJgstate() {
        return jgstate;
    }

    public void setJgstate(Long jgstate) {
        this.jgstate = jgstate;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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

    public String getDiZhi() {
        return diZhi;
    }

    public void setDiZhi(String diZhi) {
        this.diZhi = diZhi;
    }

    public String getFuJianId() {
        return fuJianId;
    }

    public void setFuJianId(String fuJianId) {
        this.fuJianId = fuJianId;
    }

    public String getCalOrCma() {
        return calOrCma;
    }

    public void setCalOrCma(String calOrCma) {
        this.calOrCma = calOrCma;
    }

    public String getYqsbmc() {
        return yqsbmc;
    }

    public void setYqsbmc(String yqsbmc) {
        this.yqsbmc = yqsbmc;
    }

    public String getYqsbxh() {
        return yqsbxh;
    }

    public void setYqsbxh(String yqsbxh) {
        this.yqsbxh = yqsbxh;
    }

    public String getYqsbclfw() {
        return yqsbclfw;
    }

    public void setYqsbclfw(String yqsbclfw) {
        this.yqsbclfw = yqsbclfw;
    }

    public String getSyfs() {
        return syfs;
    }

    public void setSyfs(String syfs) {
        this.syfs = syfs;
    }

    public String getYxrq() {
        return yxrq;
    }

    public void setYxrq(String yxrq) {
        this.yxrq = yxrq;
    }

    public String getQrjg() {
        return qrjg;
    }

    public void setQrjg(String qrjg) {
        this.qrjg = qrjg;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getCpleib() {
        return cpleib;
    }

    public void setCpleib(String cpleib) {
        this.cpleib = cpleib;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCatalogue() {
        return catalogue;
    }

    public void setCatalogue(String catalogue) {
        this.catalogue = catalogue;
    }

    public String getIsSp() {
        return isSp;
    }

    public void setIsSp(String isSp) {
        this.isSp = isSp;
    }

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public String getSortNum() {
        return sortNum;
    }

    public void setSortNum(String sortNum) {
        this.sortNum = sortNum;
    }

    public String getZzrdZsbh() {
        return zzrdZsbh;
    }

    public void setZzrdZsbh(String zzrdZsbh) {
        this.zzrdZsbh = zzrdZsbh;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getSanji() {
        return sanji;
    }

    public void setSanji(String sanji) {
        this.sanji = sanji;
    }

    public Long getCollectFujianId() {
        return collectFujianId;
    }

    public void setCollectFujianId(Long collectFujianId) {
        this.collectFujianId = collectFujianId;
    }
}
