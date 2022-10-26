package com.yongjie.ZhiJianSbpt.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yongjie.ZhiJianSbpt.model.AccountSbpt;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("RegistAction")
@Scope("prototype") // 每次访问此action都会创建实例，确保数据安全
public class RegistAction extends ActionSupport implements ModelDriven<AccountSbpt> {

    AccountSbpt accountSbpt = new AccountSbpt();

    @Override
    public AccountSbpt getModel() {
        return accountSbpt;
    }

    @Override
    public String execute() throws Exception {
        return super.execute();
    }

    //区域树
    public String gotoTree() {
        return "gotoTree";
    }

    public String filterAreaTree() {
        return "filterAreaTree";
    }

    public String check() {
        return "check";
    }

    public String changePsd() {
        return "changePsd";
    }

    public String getInfoByUserName() {
        return "getInfoByUserName";
    }

    public String doRegist() {
        return "doRegist";
    }

    public String checkOldPsd() {
        return "checkOldPsd";
    }
}
