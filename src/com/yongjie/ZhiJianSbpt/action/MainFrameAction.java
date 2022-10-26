/**
 * @Author: 黄煜豪
 * @Createtime: 2016年7月25日 上午8:23:23
 * @Copyright: Copyright (c) 2016
 * @Company: 北京永杰友信科技有限公司
 * @Version：1.0
 * @Description:
 */
package com.yongjie.ZhiJianSbpt.action;

import com.opensymphony.xwork2.ActionSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@SuppressWarnings("serial")
@Controller("MainFrameAction")
@Scope("prototype")//每次访问此action都会创建实例，确保数据安全
public class MainFrameAction extends ActionSupport {
    @Override
    public String execute() throws Exception {
        // TODO Auto-generated method stub
        return super.execute();
    }

    //根据用户名获取菜单
    public String getMenus() throws Exception {
        return "getMenus";
    }

    public String getMenus8() {
        return "getMenus8";
    }
}
