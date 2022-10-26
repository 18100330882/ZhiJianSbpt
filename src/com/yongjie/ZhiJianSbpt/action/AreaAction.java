package com.yongjie.ZhiJianSbpt.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yongjie.ZhiJianSbpt.model.Area;
import com.yongjie.ZhiJianSbpt.service.IAreaService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@SuppressWarnings("serial")
@Controller("AreaAction")
@Scope("prototype")//每次访问此action都会创建实例，确保数据安全
public class AreaAction extends ActionSupport implements ModelDriven<Area> {
    private Area Model = new Area();

    @Override
    public Area getModel() {
        // TODO Auto-generated method stub
        return Model;
    }

    @Override
    public String execute() throws Exception {
        // TODO Auto-generated method stub
        return super.execute();
    }

    public String getArea() throws Exception {
        return "getArea";
    }

    public String getTree() throws Exception {
        return "getTree";
    }

    public String getData() throws Exception {
        return "getData";
    }

    public String addArea() throws Exception {
        return "addArea";
    }

    public String addPage() throws Exception {
        return "addPage";
    }

    public String activeArea() throws Exception {
        return "activeArea";
    }

    public String forbidArea() throws Exception {
        return "forbidArea";
    }

    public String checkAreaNumber() throws Exception {
        return "checkAreaNumber";
    }

    public String getPaiXu() throws Exception {

        return "getPaiXu";
    }

    public String getAllAreaNameById() throws Exception {

        return "getAllAreaNameById";
    }

    //杨清岭 2016-08-10 注册时弹出关于区域的选择树
    public String treeForRegist() throws Exception {
        return "treeForRegist";
    }

    public String getAreas() throws Exception {
        return "getAreas";

    }

    public String getNodeNames() {
        return "getNodeNames";
    }

    public String areaTree() {
        return "areaTree";
    }
}
