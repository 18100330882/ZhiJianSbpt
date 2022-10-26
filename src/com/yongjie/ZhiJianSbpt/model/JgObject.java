package com.yongjie.ZhiJianSbpt.model;

import java.util.Date;

public class JgObject {
    private Long id;//主键Id
    private String shxydm;//社会信用代码 √√√√√√√

    private String fddbrzw;//法定代表人职务

    private String collectId;//社会信用代码 √√√√√√√

    private String jgObjectName;//检验检测机构名称  √√√√
    private String zhusuo;//检验检测机构地址  √√√√√√√
    private String yzbm; //检验检测机构邮政编码 √√√√√√√
    private String email;//检验检测机构邮箱 √√√√√√√
    private String cz; //检验检测机构传真 √√√√√√√
    private String wz;    //检验检测机构负责人 √√√√√√√
    private String jgfzr;//检验检测机构负责人电话 √√√√√√√
    //检验检测机构负责人职务 √√√√√√√
    private String jgfzrzw;
    //检验检测机构负责人手机 √√√√√√√
    private String jgfzrPhone;
    //检验检测机构联络人 √√√√√√√
    private String jgllr;
    //检验检测机构联络人职务 √√√√√√√
    private String jgllrzw;
    //检验检测机构联络人电话 √√√√√√√
    private String jgrllrdh;
    //检验检测机构联络人手机 √√√√√√√
    private String jgllrPhone;

    private String fddbr;//法定代表人 √√√√√√√
    private String fddbrdh; //法定代表人电话 √√√√√√√
    private String fddbrIdCard;//法定代表人身份证号 √√√√√√√
    //所属法人单位名称（若检验检测机构是法人单位的此项不填） √√√√√√√
    private String frdwmc;
    //法人地址 √√√√√√√
    private String frdz;
    //法人邮编 √√√√√√√
    private String fryb;
    //法人传真 √√√√√√√
    private String frFax;
    //法人邮箱 √√√√√√√
    private String frEmail;
    //法人负责人 √√√√√√√
    private String frfzr;
    //法人负责人职务 √√√√√√√
    private String frfzrzw;
    //法人负责人电话 √√√√√√√
    private String frfzrPhone;
    //检验检测机构设施特点 √√√√√√√
    //固定□        临时□          可移动□       多场所□
    private String jgsstd;
    private Integer isDlf;        //是否是独立法人   0：是     1：不是 √√√√√√
    //(法人类别) ->独立法人检验检测机构 √√√√√√√
    //社团法人□      事业法人□     企业法人□       其他□
    private String dljgfrlb;
    //(法人类别) ->检验检测机构所属法人（非独立法人检验检测机构填此项） √√√√√√√
    //社团法人□      事业法人□     企业法人□       其他□
    private String fdljgfrlb;
    //资质认定证书编号(检验检测机构现有证书情况) √√√√√√√
    private String zzrdzsbh;
    //证书有效截止日(检验检测机构现有证书情况) √√√√√√√
    private Date zsjzDate;//证书有效期
    private Date zsfzDate;//证书发证期
    //专业类别 (SHIYSJYJC_ZYTYPE)
    //1 食品; 2 建筑工程; 3 建材; 4 卫生计生; 5 农牧渔业;
    //6 机动车安检; 7 公安刑事技术; 8 司法鉴定; 9 机械;
    //10 电子信息; 11 轻工;12 纺织服装; 13 环境与环保;
    //14 水质;15 化工;16 医疗器械; 17 采矿冶金; 18 能源;
    //19 医学;20 生物安全;21 综合;22 其他;23 安检;24 环检;
    //25 综检
    //专业类别名称(传编号对应的名称)
    private String zylbName;
    //专业类别代码(传名称对应的编号)
    private Long zylbId;
    //区域(AREA)
    //2 咸阳市;3 铜川市;4 商洛市;5 宝鸡市; 6 渭南市; 7 西安市;
    //8 杨凌示范区; 9 榆林市; 10 安康市; 11 汉中市; 12 西咸新区;
    //13 延安市;14 韩城市
    //区域代码(传名称对应的编号)
    private Long areaId;
    //区域名称 (传编号对应的名字)
    private String areaName;


    private String lxr;//联系人
    private String lxdh;//联系电话
    private Long jgAreaId;//监管行政区划，区域表主键
    private Integer state;//状态（正常，停产，注销）枚举表
    private Integer jgObjectType;//类型（生产、流通）枚举表
    private Date createDate;//发证日期
    private String guimo;//规模(微型，小型，中型，大型)枚举表
    private String isDel;//是否删除（0未删， 1已删,2 注销） 密码
    private String x;//企业位置经度
    private String y;//企业位置经度
    private String czr; //操作人
    private Date czDate; //有效期
    private Integer jgflId;
    private String scdz;//发证机关
    private Integer sbxxid;//设备信息id
    private String tsaqgly;
    private String tsaqglyIdCard;
    private String szSheng;//用于第三方机构管理中
    private String szShi;


    private String jgfzrdh;
    private String pslxr; //陪审联系人
    private String pslxrdh;//陪审联系人电话
    private String jgzrs;
    private String psyzrs;
    private String jsfzr;
    private String jsfzrdh;
    private String createYear;
    private String dwdh;

    private Integer flag;//上报状态  0未上报1已上报2已归档-1已退回
    private String newShxydm;    //新社会信用代码     社会信用代码-uuid
    private String password;
    private String jcxx;  //检查信息

    private String serialNumber;  //流水号
    private String sbflag;  //是否上报

    // 机构地址 经纬度
    private String lngLat;

    //公式平台-报告鉴别联系人jg
    private String bgContact;
    //公式平台-报告鉴别联系电话
    private String bgPhone;
    //自查机构登录电话
    private String loginPhone;

    private Date collectDate;


    private String ssfrdwmc;//所属法人单位名称
    private String ssfrdz;//所属法人地址
    private String ssfrshxydm;//所属法人地址
    private String zgbm;//主管部门
    private String zgbmzw;//主管部门
    private String zgbmlxr;//主管部门联系人
    private String zgbmdh;//主管部门电话
    private String zgbmdz;//主管部门地址
    // 县级区 ID
    private String countyAreaId;

    public JgObject() {
        super();
    }

    public String getSsfrdwmc() {
        return ssfrdwmc;
    }

    public void setSsfrdwmc(String ssfrdwmc) {
        this.ssfrdwmc = ssfrdwmc;
    }

    public String getSsfrdz() {
        return ssfrdz;
    }

    public void setSsfrdz(String ssfrdz) {
        this.ssfrdz = ssfrdz;
    }

    public String getSsfrshxydm() {
        return ssfrshxydm;
    }

    public void setSsfrshxydm(String ssfrshxydm) {
        this.ssfrshxydm = ssfrshxydm;
    }

    public String getZgbm() {
        return zgbm;
    }

    public void setZgbm(String zgbm) {
        this.zgbm = zgbm;
    }

    public String getZgbmzw() {
        return zgbmzw;
    }

    public void setZgbmzw(String zgbmzw) {
        this.zgbmzw = zgbmzw;
    }

    public String getZgbmlxr() {
        return zgbmlxr;
    }

    public void setZgbmlxr(String zgbmlxr) {
        this.zgbmlxr = zgbmlxr;
    }

    public String getZgbmdh() {
        return zgbmdh;
    }

    public void setZgbmdh(String zgbmdh) {
        this.zgbmdh = zgbmdh;
    }

    public String getZgbmdz() {
        return zgbmdz;
    }

    public void setZgbmdz(String zgbmdz) {
        this.zgbmdz = zgbmdz;
    }

    public String getLoginPhone() {
        return loginPhone;
    }

    public void setLoginPhone(String loginPhone) {
        this.loginPhone = loginPhone;
    }

    public String getSbflag() {
        return sbflag;
    }

    public void setSbflag(String sbflag) {
        this.sbflag = sbflag;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getBgContact() {
        return bgContact;
    }

    public void setBgContact(String bgContact) {
        this.bgContact = bgContact;
    }

    public String getBgPhone() {
        return bgPhone;
    }

    public void setBgPhone(String bgPhone) {
        this.bgPhone = bgPhone;
    }

    public String getJcxx() {
        return jcxx;
    }

    public void setJcxx(String jcxx) {
        this.jcxx = jcxx;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getDwdh() {
        return dwdh;
    }

    public void setDwdh(String dwdh) {
        this.dwdh = dwdh;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJgObjectName() {
        return jgObjectName;
    }

    public void setJgObjectName(String jgObjectName) {
        this.jgObjectName = jgObjectName;
    }

    public String getShxydm() {
        return shxydm;
    }

    public void setShxydm(String shxydm) {
        this.shxydm = shxydm;
    }

    public String getFddbr() {
        return fddbr;
    }

    public void setFddbr(String fddbr) {
        this.fddbr = fddbr;
    }

    public String getFddbrIdCard() {
        return fddbrIdCard;
    }

    public void setFddbrIdCard(String fddbrIdCard) {
        this.fddbrIdCard = fddbrIdCard;
    }

    public String getLxr() {
        return lxr;
    }

    public void setLxr(String lxr) {
        this.lxr = lxr;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getZhusuo() {
        return zhusuo;
    }

    public void setZhusuo(String zhusuo) {
        this.zhusuo = zhusuo;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getJgAreaId() {
        return jgAreaId;
    }

    public void setJgAreaId(Long jgAreaId) {
        this.jgAreaId = jgAreaId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getJgObjectType() {
        return jgObjectType;
    }

    public void setJgObjectType(Integer jgObjectType) {
        this.jgObjectType = jgObjectType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getGuimo() {
        return guimo;
    }

    public void setGuimo(String guimo) {
        this.guimo = guimo;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
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

    public Integer getJgflId() {
        return jgflId;
    }

    public void setJgflId(Integer jgflId) {
        this.jgflId = jgflId;
    }

    public String getScdz() {
        return scdz;
    }

    public void setScdz(String scdz) {
        this.scdz = scdz;
    }

    public Integer getSbxxid() {
        return sbxxid;
    }

    public void setSbxxid(Integer sbxxid) {
        this.sbxxid = sbxxid;
    }

    public String getTsaqgly() {
        return tsaqgly;
    }

    public void setTsaqgly(String tsaqgly) {
        this.tsaqgly = tsaqgly;
    }

    public String getTsaqglyIdCard() {
        return tsaqglyIdCard;
    }

    public void setTsaqglyIdCard(String tsaqglyIdCard) {
        this.tsaqglyIdCard = tsaqglyIdCard;
    }

    public String getSzSheng() {
        return szSheng;
    }

    public void setSzSheng(String szSheng) {
        this.szSheng = szSheng;
    }

    public String getSzShi() {
        return szShi;
    }

    public void setSzShi(String szShi) {
        this.szShi = szShi;
    }

    public String getYzbm() {
        return yzbm;
    }

    public void setYzbm(String yzbm) {
        this.yzbm = yzbm;
    }

    public String getCz() {
        return cz;
    }

    public void setCz(String cz) {
        this.cz = cz;
    }

    public String getWz() {
        return wz;
    }

    public void setWz(String wz) {
        this.wz = wz;
    }

    public String getFddbrdh() {
        return fddbrdh;
    }

    public void setFddbrdh(String fddbrdh) {
        this.fddbrdh = fddbrdh;
    }

    public String getJgfzr() {
        return jgfzr;
    }

    public void setJgfzr(String jgfzr) {
        this.jgfzr = jgfzr;
    }

    public String getJgfzrdh() {
        return jgfzrdh;
    }

    public void setJgfzrdh(String jgfzrdh) {
        this.jgfzrdh = jgfzrdh;
    }

    public String getPslxr() {
        return pslxr;
    }

    public void setPslxr(String pslxr) {
        this.pslxr = pslxr;
    }

    public String getPslxrdh() {
        return pslxrdh;
    }

    public void setPslxrdh(String pslxrdh) {
        this.pslxrdh = pslxrdh;
    }

    public String getJgzrs() {
        return jgzrs;
    }

    public void setJgzrs(String jgzrs) {
        this.jgzrs = jgzrs;
    }

    public String getPsyzrs() {
        return psyzrs;
    }

    public void setPsyzrs(String psyzrs) {
        this.psyzrs = psyzrs;
    }

    public String getJsfzr() {
        return jsfzr;
    }

    public void setJsfzr(String jsfzr) {
        this.jsfzr = jsfzr;
    }

    public String getJsfzrdh() {
        return jsfzrdh;
    }

    public void setJsfzrdh(String jsfzrdh) {
        this.jsfzrdh = jsfzrdh;
    }

    public String getCreateYear() {
        return createYear;
    }

    public void setCreateYear(String createYear) {
        this.createYear = createYear;
    }

    public Integer getIsDlf() {
        return isDlf;
    }

    public void setIsDlf(Integer isDlf) {
        this.isDlf = isDlf;
    }

    public String getNewShxydm() {
        return newShxydm;
    }

    public void setNewShxydm(String newShxydm) {
        this.newShxydm = newShxydm;
    }

    public String getJgfzrzw() {
        return jgfzrzw;
    }

    public void setJgfzrzw(String jgfzrzw) {
        this.jgfzrzw = jgfzrzw;
    }

    public String getJgfzrPhone() {
        return jgfzrPhone;
    }

    public void setJgfzrPhone(String jgfzrPhone) {
        this.jgfzrPhone = jgfzrPhone;
    }

    public String getJgllr() {
        return jgllr;
    }

    public void setJgllr(String jgllr) {
        this.jgllr = jgllr;
    }

    public String getJgllrzw() {
        return jgllrzw;
    }

    public void setJgllrzw(String jgllrzw) {
        this.jgllrzw = jgllrzw;
    }

    public String getJgrllrdh() {
        return jgrllrdh;
    }

    public void setJgrllrdh(String jgrllrdh) {
        this.jgrllrdh = jgrllrdh;
    }

    public String getJgllrPhone() {
        return jgllrPhone;
    }

    public void setJgllrPhone(String jgllrPhone) {
        this.jgllrPhone = jgllrPhone;
    }

    public String getFrdwmc() {
        return frdwmc;
    }

    public void setFrdwmc(String frdwmc) {
        this.frdwmc = frdwmc;
    }

    public String getFrdz() {
        return frdz;
    }

    public void setFrdz(String frdz) {
        this.frdz = frdz;
    }

    public String getFryb() {
        return fryb;
    }

    public void setFryb(String fryb) {
        this.fryb = fryb;
    }

    public String getFrFax() {
        return frFax;
    }

    public void setFrFax(String frFax) {
        this.frFax = frFax;
    }

    public String getFrEmail() {
        return frEmail;
    }

    public void setFrEmail(String frEmail) {
        this.frEmail = frEmail;
    }

    public String getFrfzr() {
        return frfzr;
    }

    public void setFrfzr(String frfzr) {
        this.frfzr = frfzr;
    }

    public String getFrfzrzw() {
        return frfzrzw;
    }

    public void setFrfzrzw(String frfzrzw) {
        this.frfzrzw = frfzrzw;
    }

    public String getFrfzrPhone() {
        return frfzrPhone;
    }

    public void setFrfzrPhone(String frfzrPhone) {
        this.frfzrPhone = frfzrPhone;
    }

    public String getJgsstd() {
        return jgsstd;
    }

    public void setJgsstd(String jgsstd) {
        this.jgsstd = jgsstd;
    }

    public String getDljgfrlb() {
        return dljgfrlb;
    }

    public void setDljgfrlb(String dljgfrlb) {
        this.dljgfrlb = dljgfrlb;
    }

    public String getFdljgfrlb() {
        return fdljgfrlb;
    }

    public void setFdljgfrlb(String fdljgfrlb) {
        this.fdljgfrlb = fdljgfrlb;
    }

    public String getZzrdzsbh() {
        return zzrdzsbh;
    }

    public void setZzrdzsbh(String zzrdzsbh) {
        this.zzrdzsbh = zzrdzsbh;
    }

    public String getZylbName() {
        return zylbName;
    }

    public void setZylbName(String zylbName) {
        this.zylbName = zylbName;
    }

    public Long getZylbId() {
        return zylbId;
    }

    public void setZylbId(Long zylbId) {
        this.zylbId = zylbId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCountyAreaId() {
        return countyAreaId;
    }

    public void setCountyAreaId(String countyAreaId) {
        this.countyAreaId = countyAreaId;
    }

    public Date getZsjzDate() {
        return zsjzDate;
    }

    public void setZsjzDate(Date zsjzDate) {
        this.zsjzDate = zsjzDate;
    }

    public Date getZsfzDate() {
        return zsfzDate;
    }

    public void setZsfzDate(Date zsfzDate) {
        this.zsfzDate = zsfzDate;
    }

    public String getLngLat() {
        return lngLat;
    }

    public void setLngLat(String lngLat) {
        this.lngLat = lngLat;
    }

    public String getCollectId() {
        return collectId;
    }

    public void setCollectId(String collectId) {
        this.collectId = collectId;
    }

    public Date getCollectDate() {
        return collectDate;
    }

    public void setCollectDate(Date collectDate) {
        this.collectDate = collectDate;
    }

    public String getFddbrzw() {
        return fddbrzw;
    }

    public void setFddbrzw(String fddbrzw) {
        this.fddbrzw = fddbrzw;
    }
}
