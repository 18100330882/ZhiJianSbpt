package com.yongjie.ZhiJianSbpt.model.shiYsJyjc;

import com.yongjie.ZhiJianSbpt.base.ReturnBaseModel;

import java.util.Date;

public class BizShiYsJyjc extends ReturnBaseModel {

    private long id;
    private Long flowId;
    private Long flowInstId;
    private Long bizId;
    private Date sqrq;
    private Date startPsDate;
    private String sqZylb;
    private String sqlb;
    private String zslx;
    private Integer isDcd;
    private String tiaoMa;
    private Integer flag;
    private Date sbrq;
    private Date fzrq;
    private String sysMc;
    private String sysDz;
    private String sysEmail;
    private String sysZip;
    private String sysFax;
    private String lxr;
    private String lxrDh;
    private Long shRenAreaId;
    private String czr;
    private Date czDate;
    private String zgdwdm;//主管单位代码
    private String zgdwmc; //主管单位名称
    private String cmaSqlx;//CMA/CAL申请类型
    private String zzrdzsbh;//资质认定证书编号
    private Date zsyxrq;

    private String zzrdzsbhcal;//资质认定证书编号
    private Date zsyxrqcal;

    private String isFoodType;
    private String scsqlb;
    private String pslx;
    private String bjbh;
    private Date ssdate;
    private String zzjgdm;
    private Date ysFinishTime;
    private String isshangbao;
    private String isxz;//判断该申请书是不是新增
    private String isWs;//现场还是文审
    private Integer sqType;    // 申请类型      0：普通申请       1：CA申请
    private String receiveNum;//一窗式服务平台获取的流水号
    private String receivePassword;
    private String receiveResult;//调用接口结果保存
    private String yearHao;
    private String year;
    private String hao;

    private String nodeId; // 当前数据对应的环节
    private Date nodeBlrq;    //环节办理时间

    private Date psdate;//派审通知单里面的评审时间
    private String psdateStr;// 派审通知单里面的评审时间  > 替换上面的日期

    public BizShiYsJyjc() {
        super();
    }

    public BizShiYsJyjc(long id, Long flowId, Long flowInstId, Long bizId, Date sqrq, Date startPsDate, String sqZylb,
                        String sqlb, String zslx, Integer isDcd, String tiaoMa, Integer flag, Date sbrq, Date fzrq, String sysMc,
                        String sysDz, String sysEmail, String sysZip, String sysFax, String lxr, String lxrDh, Long shRenAreaId,
                        String czr, Date czDate, String zgdwdm, String zgdwmc, String cmaSqlx, String zzrdzsbh, Date zsyxrq,
                        String zzrdzsbhcal, Date zsyxrqcal, String isFoodType, String scsqlb, String pslx, String bjbh, Date ssdate,
                        String zzjgdm, Date ysFinishTime, String isshangbao, String isxz, String isWs, Integer sqType,
                        String receiveNum, String receivePassword, String yearHao, String year, String hao, String nodeId,
                        Date nodeBlrq, Date psdate) {
        super();
        this.id = id;
        this.flowId = flowId;
        this.flowInstId = flowInstId;
        this.bizId = bizId;
        this.sqrq = sqrq;
        this.startPsDate = startPsDate;
        this.sqZylb = sqZylb;
        this.sqlb = sqlb;
        this.zslx = zslx;
        this.isDcd = isDcd;
        this.tiaoMa = tiaoMa;
        this.flag = flag;
        this.sbrq = sbrq;
        this.fzrq = fzrq;
        this.sysMc = sysMc;
        this.sysDz = sysDz;
        this.sysEmail = sysEmail;
        this.sysZip = sysZip;
        this.sysFax = sysFax;
        this.lxr = lxr;
        this.lxrDh = lxrDh;
        this.shRenAreaId = shRenAreaId;
        this.czr = czr;
        this.czDate = czDate;
        this.zgdwdm = zgdwdm;
        this.zgdwmc = zgdwmc;
        this.cmaSqlx = cmaSqlx;
        this.zzrdzsbh = zzrdzsbh;
        this.zsyxrq = zsyxrq;
        this.zzrdzsbhcal = zzrdzsbhcal;
        this.zsyxrqcal = zsyxrqcal;
        this.isFoodType = isFoodType;
        this.scsqlb = scsqlb;
        this.pslx = pslx;
        this.bjbh = bjbh;
        this.ssdate = ssdate;
        this.zzjgdm = zzjgdm;
        this.ysFinishTime = ysFinishTime;
        this.isshangbao = isshangbao;
        this.isxz = isxz;
        this.isWs = isWs;
        this.sqType = sqType;
        this.receiveNum = receiveNum;
        this.receivePassword = receivePassword;
        this.yearHao = yearHao;
        this.year = year;
        this.hao = hao;
        this.nodeId = nodeId;
        this.nodeBlrq = nodeBlrq;
        this.psdate = psdate;
    }

    public String getPsdateStr() {
        return psdateStr;
    }

    public void setPsdateStr(String psdateStr) {
        this.psdateStr = psdateStr;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    public Long getFlowInstId() {
        return flowInstId;
    }

    public void setFlowInstId(Long flowInstId) {
        this.flowInstId = flowInstId;
    }

    public Long getBizId() {
        return bizId;
    }

    public void setBizId(Long bizId) {
        this.bizId = bizId;
    }

    public Date getSqrq() {
        return sqrq;
    }

    public void setSqrq(Date sqrq) {
        this.sqrq = sqrq;
    }

    public Date getStartPsDate() {
        return startPsDate;
    }

    public void setStartPsDate(Date startPsDate) {
        this.startPsDate = startPsDate;
    }

    public String getSqZylb() {
        return sqZylb;
    }

    public void setSqZylb(String sqZylb) {
        this.sqZylb = sqZylb;
    }

    public String getSqlb() {
        return sqlb;
    }

    public void setSqlb(String sqlb) {
        this.sqlb = sqlb;
    }

    public String getZslx() {
        return zslx;
    }

    public void setZslx(String zslx) {
        this.zslx = zslx;
    }

    public Integer getIsDcd() {
        return isDcd;
    }

    public void setIsDcd(Integer isDcd) {
        this.isDcd = isDcd;
    }

    public String getTiaoMa() {
        return tiaoMa;
    }

    public void setTiaoMa(String tiaoMa) {
        this.tiaoMa = tiaoMa;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Date getSbrq() {
        return sbrq;
    }

    public void setSbrq(Date sbrq) {
        this.sbrq = sbrq;
    }

    public Date getFzrq() {
        return fzrq;
    }

    public void setFzrq(Date fzrq) {
        this.fzrq = fzrq;
    }

    public String getSysMc() {
        return sysMc;
    }

    public void setSysMc(String sysMc) {
        this.sysMc = sysMc;
    }

    public String getSysDz() {
        return sysDz;
    }

    public void setSysDz(String sysDz) {
        this.sysDz = sysDz;
    }

    public String getSysEmail() {
        return sysEmail;
    }

    public void setSysEmail(String sysEmail) {
        this.sysEmail = sysEmail;
    }

    public String getSysZip() {
        return sysZip;
    }

    public void setSysZip(String sysZip) {
        this.sysZip = sysZip;
    }

    public String getSysFax() {
        return sysFax;
    }

    public void setSysFax(String sysFax) {
        this.sysFax = sysFax;
    }

    public String getLxr() {
        return lxr;
    }

    public void setLxr(String lxr) {
        this.lxr = lxr;
    }

    public String getLxrDh() {
        return lxrDh;
    }

    public void setLxrDh(String lxrDh) {
        this.lxrDh = lxrDh;
    }

    public Long getShRenAreaId() {
        return shRenAreaId;
    }

    public void setShRenAreaId(Long shRenAreaId) {
        this.shRenAreaId = shRenAreaId;
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

    public String getZgdwdm() {
        return zgdwdm;
    }

    public void setZgdwdm(String zgdwdm) {
        this.zgdwdm = zgdwdm;
    }

    public String getZgdwmc() {
        return zgdwmc;
    }

    public void setZgdwmc(String zgdwmc) {
        this.zgdwmc = zgdwmc;
    }

    public String getCmaSqlx() {
        return cmaSqlx;
    }

    public void setCmaSqlx(String cmaSqlx) {
        this.cmaSqlx = cmaSqlx;
    }

    public String getZzrdzsbh() {
        return zzrdzsbh;
    }

    public void setZzrdzsbh(String zzrdzsbh) {
        this.zzrdzsbh = zzrdzsbh;
    }

    public Date getZsyxrq() {
        return zsyxrq;
    }

    public void setZsyxrq(Date zsyxrq) {
        this.zsyxrq = zsyxrq;
    }

    public String getZzrdzsbhcal() {
        return zzrdzsbhcal;
    }

    public void setZzrdzsbhcal(String zzrdzsbhcal) {
        this.zzrdzsbhcal = zzrdzsbhcal;
    }

    public Date getZsyxrqcal() {
        return zsyxrqcal;
    }

    public void setZsyxrqcal(Date zsyxrqcal) {
        this.zsyxrqcal = zsyxrqcal;
    }

    public String getIsFoodType() {
        return isFoodType;
    }

    public void setIsFoodType(String isFoodType) {
        this.isFoodType = isFoodType;
    }

    public String getScsqlb() {
        return scsqlb;
    }

    public void setScsqlb(String scsqlb) {
        this.scsqlb = scsqlb;
    }

    public String getPslx() {
        return pslx;
    }

    public void setPslx(String pslx) {
        this.pslx = pslx;
    }

    public String getBjbh() {
        return bjbh;
    }

    public void setBjbh(String bjbh) {
        this.bjbh = bjbh;
    }

    public Date getSsdate() {
        return ssdate;
    }

    public void setSsdate(Date ssdate) {
        this.ssdate = ssdate;
    }

    public String getZzjgdm() {
        return zzjgdm;
    }

    public void setZzjgdm(String zzjgdm) {
        this.zzjgdm = zzjgdm;
    }

    public Date getYsFinishTime() {
        return ysFinishTime;
    }

    public void setYsFinishTime(Date ysFinishTime) {
        this.ysFinishTime = ysFinishTime;
    }

    public String getIsshangbao() {
        return isshangbao;
    }

    public void setIsshangbao(String isshangbao) {
        this.isshangbao = isshangbao;
    }

    public String getIsxz() {
        return isxz;
    }

    public void setIsxz(String isxz) {
        this.isxz = isxz;
    }

    public String getIsWs() {
        return isWs;
    }

    public void setIsWs(String isWs) {
        this.isWs = isWs;
    }

    public Integer getSqType() {
        return sqType;
    }

    public void setSqType(Integer sqType) {
        this.sqType = sqType;
    }

    public String getReceiveNum() {
        return receiveNum;
    }

    public void setReceiveNum(String receiveNum) {
        this.receiveNum = receiveNum;
    }

    public String getReceivePassword() {
        return receivePassword;
    }

    public void setReceivePassword(String receivePassword) {
        this.receivePassword = receivePassword;
    }

    public String getReceiveResult() {
        return receiveResult;
    }

    public void setReceiveResult(String receiveResult) {
        this.receiveResult = receiveResult;
    }

    public String getYearHao() {
        return yearHao;
    }

    public void setYearHao(String yearHao) {
        this.yearHao = yearHao;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getHao() {
        return hao;
    }

    public void setHao(String hao) {
        this.hao = hao;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public Date getNodeBlrq() {
        return nodeBlrq;
    }

    public void setNodeBlrq(Date nodeBlrq) {
        this.nodeBlrq = nodeBlrq;
    }

    public Date getPsdate() {
        return psdate;
    }

    public void setPsdate(Date psdate) {
        this.psdate = psdate;
    }

}
