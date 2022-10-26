package com.yongjie.ZhiJianSbpt.action.jyjcbggl;

import com.opensymphony.xwork2.ActionSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@SuppressWarnings("serial")
@Controller("BizJyjcbgglAction")
@Scope("prototype")//每次访问此action都会创建实例，确保数据安全
public class BizJyjcbgglAction extends ActionSupport {

    //跳转到首页
    public String getJyjcbgglWindow() throws Exception {
        return "getJyjcbgglWindow";
    }

    public String saveJyjc() throws Exception {
        return "saveJyjc";
    }

    public String getBizJyjcbgglById() throws Exception {
        return "getBizJyjcbgglById";
    }

    public String gotoStepPage() throws Exception {
        return "gotoStepPage";
    }

    //查询数据 grid显示
    public String getJyjcbgglSqsToList() throws Exception {
        return "getJyjcbgglSqsToList";
    }

    //更新数据到主表
    public String updateJyjc() throws Exception {
        return "updateJyjc";
    }

    //根据id获取主表数据
    public String getBizJyjcbgglOrsqsId() throws Exception {
        return "getBizJyjcbgglOrsqsId";
    }


    //根据id删除申请书(所有关联的都删除)
    public String delJyjcbgglOrId() throws Exception {
        return "delJyjcbgglOrId";
    }

    //申请书打印的判断
    public String isCompleteBysqsId() throws Exception {
        return "isCompleteBysqsId";
    }

    //上报 更新状态
    public String submitSqs() throws Exception {
        return "submitSqs";
    }

    //上报 更新状态
    public String submitSqsNew() throws Exception {
        return "submitSqsNew";
    }

    //上报，只更新flag
    public String updateFlag() throws Exception {
        return "updateFlag";
    }

    public String getJyjcbgglListByCzr() throws Exception {
        return "getJyjcbgglListByCzr";
    }

    public String toCnsUpload() throws Exception {
        return "toCnsUpload";
    }

    public String updateBizJyjcbggl() {
        return "updateBizJyjcbggl";
    }

    public String getPrintPath() {
        return "getPrintPath";
    }

    public String IssqsDown() {
        return "IssqsDown";
    }

    public String getFrbgWindow() throws Exception {
        return "getFrbgWindow";
    }

    public String getJyjcMcbgglWindow() throws Exception {
        return "getJyjcMcbgglWindow";
    }

    public String getJyjcQxnlbgglWindow() throws Exception {
        return "getJyjcQxnlbgglWindow";
    }

    public String getJyjcRybgglWindow() throws Exception {
        return "getJyjcRybgglWindow";
    }

    public String delJyjcRybgglById() throws Exception {
        return "delJyjcRybgglById";
    }

    public String getJyjcDzbgglWindow() throws Exception {
        return "getJyjcDzbgglWindow";
    }

    public String getJyjcQzrbgglWindow() throws Exception {
        return "getJyjcQzrbgglWindow";
    }

    //名称变更
    public String gotoMcbgStepPage() throws Exception {
        return "gotoMcbgStepPage";
    }

    public String toFuJianWindow() throws Exception {
        return "toFuJianWindow";
    }

    public String lookBgSqs() throws Exception {
        return "lookBgSqs";
    }

    public String getPermit() throws Exception {
        return "getPermit";
    }

    public String getJyjcjgZzzxWindow() throws Exception {
        return "getJyjcjgZzzxWindow";
    }

    public String getQycnWindow() throws Exception {
        return "getQycnWindow";
    }

    public String getQycnWindowBg() throws Exception {
        return "getQycnWindowBg";
    }

    public String getQycnWindowCj() throws Exception {
        return "getQycnWindowCj";
    }

    public String getQycnWindowZx() throws Exception {
        return "getQycnWindowZx";
    }

    public String getQycnBZWindow() throws Exception {
        return "getQycnBZWindow";
    }

    public String getJyjcbgglByCzr() throws Exception {
        return "getJyjcbgglByCzr";
    }

    //变更指南
    public String getSqqzrBgznWindow() throws Exception {
        return "getSqqzrBgznWindow";
    }

    public String getMcBgznWindow() throws Exception {
        return "getMcBgznWindow";
    }

    public String getDzBgznWindow() throws Exception {
        return "getDzBgznWindow";
    }

    public String getZxsm() throws Exception {
        return "getZxsm";
    }

    public String removeSqs() throws Exception {
        return "removeSqs";
    }

    public String downloadSqs() throws Exception {
        return "downloadSqs";
    }

    public String downloadSqsTemp() throws Exception {
        return "downloadSqsTemp";
    }

    public String getQycnWindowCn() throws Exception {
        return "getQycnWindowCn";
    }
}
