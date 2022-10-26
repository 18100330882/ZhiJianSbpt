/**
 * @Author: 杨青岭
 * @Createtime: 2016年8月8日 上午11:13:58
 * @Copyright: Copyright (c) 2016
 * @Company: 北京永杰友信科技有限公司
 * @Version：1.0
 * @Description:
 */
package com.yongjie.ZhiJianSbpt.dao;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.model.AccountSbpt;

import java.util.ArrayList;
import java.util.List;

public interface ICloginDao extends BaseDao<AccountSbpt> {
    public final static String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.dao.impl.CloginDao";

    //如果用户名和密码都对在判断状态是否是激活
    public List<AccountSbpt> doLogin(String username, String password);

    //通过用户名查询激活状态
    public List<AccountSbpt> checkAction(String username);

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

    public ArrayList getAccountXzsp(String userId);

    public ArrayList getAccountSbptByName(String username);

    AccountSbpt getAccountSbptByUserName(String userName) throws Exception;
}
