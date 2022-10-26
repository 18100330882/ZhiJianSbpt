/**
 * @Author: 杨青岭
 * @Createtime: 2016年8月8日 上午11:35:12
 * @Copyright: Copyright (c) 2016
 * @Company: 北京永杰友信科技有限公司
 * @Version：1.0
 * @Description:
 */
package com.yongjie.ZhiJianSbpt.service;

import com.yongjie.ZhiJianSbpt.model.AccountSbpt;

import java.util.ArrayList;
import java.util.List;

public interface ICloginService {
    public final static String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.service.impl.CloginService";

    public List<AccountSbpt> doLogin(String username, String password);

    public List<AccountSbpt> checkAction(String username);

    public ArrayList getAccountSbptByName(String username);

    /**
     *
     * @param logOnUserName 登录用户名
     * @param sysFlag 系统标号，0代表企业上报端，1代表行政审批端
     * @return tree的数据源arrayList
     * @author 黄煜豪
     */
    @SuppressWarnings("rawtypes")
    public ArrayList getMenusByUsername(String logOnUserName, String sysFlag);

    //首页最近审批记录查询
    public ArrayList getScrollList(String userName);

    //根据userName查询TrueName
    public ArrayList getTrueName(String userName);

    AccountSbpt getAccountSbptByUserName(String userName) throws Exception;
}
