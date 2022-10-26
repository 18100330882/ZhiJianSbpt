package com.yongjie.ZhiJianSbpt.action.jyjccj;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yongjie.ZhiJianSbpt.model.jyjccj.Collect;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@SuppressWarnings("serial")
@Controller("CollectAction")
@Scope("prototype")
public class CollectAction extends ActionSupport implements ModelDriven<Collect> {
    private Collect collect = new Collect();

    @Override
    public Collect getModel() {
        return collect;
    }

    @Override
    public String execute() throws Exception {
        // TODO Auto-generated method stub
        return super.execute();
    }

    public String collectList() {
        return "collectList";
    }

    public String queryByZsbh() {
        return "queryByZsbh";
    }

    public String save() {
        return "save";
    }

    public String sb() {
        return "sb";
    }

    public String del() {
        return "del";
    }

    public String delnl() {
        return "delnl";
    }

    public String delqzr() {
        return "delqzr";
    }

    public String delry() {
        return "delry";
    }

    public String ckfj() {
        return "ckfj";
    }

    public String getNlFileList() {
        return "getNlFileList";
    }

    public String getQzrFileList() {
        return "getQzrFileList";
    }

    public String getRyFileList() {
        return "getRyFileList";
    }

    public String jgobjectSave() {
        return "jgobjectSave";
    }

    public String queryByUserName() {
        return "queryByUserName";
    }

    public String cknl() {
        return "cknl";
    }

    public String queryQzrBySfzh() {
        return "queryQzrBySfzh";
    }

    public String queryRyBySfzh() {
        return "queryRyBySfzh";
    }

    public String queryRyListByZsbh() {
        return "queryRyListByZsbh";
    }

    public String checkFlagByZsbh() {
        return "checkFlagByZsbh";
    }

    public String querySfzhByName() {
        return "querySfzhByName";
    }

    public String queryQzrByZsbh() {
        return "queryQzrByZsbh";
    }

    public String queryQzrSfzhByName() {
        return "queryQzrSfzhByName";
    }

    public String queryByUserName2() {
        return "queryByUserName2";
    }


}
