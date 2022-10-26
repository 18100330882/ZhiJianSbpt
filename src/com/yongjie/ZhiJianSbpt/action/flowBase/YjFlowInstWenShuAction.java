/**
 * @Author: 杨淑娟
 * @Createtime: 2016年10月13日 下午3:03:58
 * @Copyright: Copyright (c) 2016
 * @Company: 北京永杰友信科技有限公司
 * @Version：1.0
 * @Description:
 */
package com.yongjie.ZhiJianSbpt.action.flowBase;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yongjie.ZhiJianSbpt.model.flowBase.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@SuppressWarnings("serial")
@Controller("YjFlowInstWenShuAction")
@Scope("prototype")//每次访问此action都会创建实例，确保数据安全
public class YjFlowInstWenShuAction extends ActionSupport implements ModelDriven<YjFlowInstWenShu> {
    private YjFlowInstWenShu Model = new YjFlowInstWenShu();

    @Override
    public YjFlowInstWenShu getModel() {
        // TODO Auto-generated method stub
        return Model;
    }

    @Override
    public String execute() throws Exception {
        // TODO Auto-generated method stub
        return super.execute();
    }

    //	删除附件
    public String deleteWenShu() throws Exception {
        return "deleteWenShu";
    }

    //  查询附件
    public String getFuJianJyjcXchc() throws Exception {
        return "getFuJianJyjcXchc";
    }

    //  查看附件
    public String getSwitchUrl() throws Exception {
        return "getSwitchUrl";
    }

    //  查看附件
    public String getApiFile() throws Exception {
        return "getApiFile";
    }
}
