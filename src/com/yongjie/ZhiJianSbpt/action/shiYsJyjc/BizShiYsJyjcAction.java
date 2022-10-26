package com.yongjie.ZhiJianSbpt.action.shiYsJyjc;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yongjie.ZhiJianSbpt.model.shiYsJyjc.BizShiYsJyjc;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@SuppressWarnings("serial")
@Controller("BizShiYsJyjcAction")
@Scope("prototype")//每次访问此action都会创建实例，确保数据安全
public class BizShiYsJyjcAction extends ActionSupport implements ModelDriven<BizShiYsJyjc> {

    @Override
    public BizShiYsJyjc getModel() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String execute() throws Exception {
        // TODO Auto-generated method stub
        return super.execute();
    }

    //查询数据 grid显示
    public String getShiYsJyjcSqsToList() throws Exception {
        return "getShiYsJyjcSqsToList";
    }

    //保存数据到主表
    public String saveJyjc() throws Exception {
        return "saveJyjc";
    }

    //更新数据到主表
    public String updateJyjc() throws Exception {
        return "updateJyjc";
    }

    //根据id获取主表数据
    public String getBizShiYsJyjcOrsqsId() throws Exception {
        return "getBizShiYsJyjcOrsqsId";
    }

    public String getBizShiYsJyjcById() throws Exception {
        return "getBizShiYsJyjcById";

    }

    //根据id删除申请书(所有关联的都删除)
    public String delShiYsJyjcOrId() throws Exception {
        return "delShiYsJyjcOrId";
    }

    //申请书打印的判断
    public String isCompleteBysqsId() throws Exception {
        return "isCompleteBysqsId";
    }

    //上报 更新状态
    public String submitSqs() throws Exception {
        return "submitSqs";
    }

    public String getShiYsJyjcListByCzr() throws Exception {
        return "getShiYsJyjcListByCzr";
    }

    public String toCnsUpload() throws Exception {
        return "toCnsUpload";
    }

    public String updateBizShiYsJyjc() {
        return "updateBizShiYsJyjc";
    }

    public String getShiysjyjcPremitByZsbh() {
        return "getShiysjyjcPremitByZsbh";
    }

    public String getShiysjyjcPremitByZsbh2() {
        return "getShiysjyjcPremitByZsbh2";
    }

    public String getIfInJyjcjgxxBuHeGe() {
        return "getIfInJyjcjgxxBuHeGe";
    }

    public String isCanSb() {
        return "isCanSb";
    }

    public String getShangBaoBuMen() {
        return "getShangBaoBuMen";
    }

    public String zzpsAdd() {
        return "zzpsAdd";
    }

    public String zzpsSelect() {
        return "zzpsSelect";
    }

    public String zzpszsNumber() {
        return "zzpszsNumber";
    }

    public String zzpstxaddress() {
        return "zzpstxaddress";
    }

    //申报端检验检测档案机构查询
    public String searchQyxxList() {
        return "searchQyxxList";
    }

    public String getJgObjectnewShxydm() {
        return "getJgObjectnewShxydm";
    }

    public String getSqsxxByShxydm() {
        return "getSqsxxByShxydm";
    }

    public String getCddzFromCd() {
        return "getCddzFromCd";
    }

    public String getZsxxList() {
        return "getZsxxList";
    }

    public String getZylbList() {
        return "getZylbList";
    }

    public String getJgObjectJyjcjgRyList() {
        return "getJgObjectJyjcjgRyList";
    }

    public String getJgObjectQzrList() {
        return "getJgObjectQzrList";
    }

    public String getJynlList() {
        return "getJynlList";
    }

    public String getZsxxList2() {
        return "getZsxxList2";
    }

    public String getJdcXx() {
        return "getJdcXx";
    }

    public String getYqsbList() {
        return "getYqsbList";
    }

    public String getJgObjectFuJianList() {
        return "getJgObjectFuJianList";
    }

    public String getJgObjectCd1() {
        return "getJgObjectCd1";
    }

    public String getPsxx() {
        return "getPsxx";
    }

    public String addFkxx() {
        return "addFkxx";
    }

    public String getFkxx() {
        return "getFkxx";
    }

    public String getPsry() {
        return "getPsry";
    }

}
