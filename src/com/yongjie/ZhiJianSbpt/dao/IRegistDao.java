/**
 * @Author: 杨青岭
 * @Createtime: 2016年8月9日 下午2:01:48
 * @Copyright: Copyright (c) 2016
 * @Company: 北京永杰友信科技有限公司
 * @Version：1.0
 * @Description:
 */
package com.yongjie.ZhiJianSbpt.dao;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.model.AccountSbpt;
import com.yongjie.ZhiJianSbpt.model.JgObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IRegistDao extends BaseDao<AccountSbpt> {

    public final static String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.dao.impl.RegistDao";

    public Boolean checkName(String name, String qymc, long userid);//注册查重用户名

    public AccountSbpt getAccountSbptByUserName(String username);//根据用户名查询数据

    public int changePsd(String psd, String username);//修改密码

    public AccountSbpt checkHeNanUser(String username, String mobile);//根据用户名和联系电话查询数据

    //更新云政用户的userID 到库中
    public int changeuserId(long id, String userId);//执行更新密码

    //菜单表中筛选shiys的菜单
    public ArrayList getMenusByGroup(int menuGroup);

    /**
     * 判断该社会信用代码是否已存在
     * @param shxydm   要校验的社会信用代码
     * @return 社会信用代码为当前的记录的id，如果不存在，则返回-1
     * @author cyk
     */
    public Number validShxydm(String shxydm);

    /**
     * 判断是否存在数据的社会信用代码和企业名称等于当前值
     * @param shxydm
     * @param qymc
     * @return 社会信用代码为当前的记录的id，如果不存在则返回-1
     * @author cyk
     */
    public Number validShxydm(String shxydm, String qymc);

    /**
     * 根据用户名查询当前用户在数据库中的记录数
     * @param userName        用户名
     * @return 当前用户的记录数
     * @author cyk
     */
    public Number getCountsByUserName(String userName);

    /**
     * 根据社会信用代码从Jgobject表中获取符合条件的记录的newShxydm
     * @param shxydm
     * @return
     */
    public List<String> getJgobjectNewShxydmList(String shxydm);

    /**
     * 根据社会信用代码和企业名称从Jgobject表中获取符合条件的记录的newShxydm
     * @param shxydm
     * @param qymc
     * @return
     */
    public List<String> getJgobjectNewShxydmList(String shxydm, String qymc);

    /**
     * 将当前对象的信息插入到 Jgobject表中
     * @param accountSbpt
     */
    public void insertIntoJgobject(AccountSbpt accountSbpt);

    /**
     * 通过用户id来更新当前数据值
     * @param accountSbpt
     */
    public void updateByUserId(AccountSbpt accountSbpt);

    /**
     * 根据newShxydm来更新Jgobject的部分字段内容
     * @param accountSbpt
     */
    public void updateJgobjectByNewShxydm(AccountSbpt accountSbpt);

    /**
     * 查询当前 newShxydm是否在Jgobject表中存在
     * @param newShxydm
     * @return
     */
    public boolean isNewShxydmExistInJgobject(String newShxydm);

    /**
     * 根据社会信用代码和企业名称查询数据
     * @param shxydm
     * @param qymc
     * @return
     */
    public List<JgObject> getJgobject(String shxydm, String qymc);

    /**
     * 根据社会信用代码查询数据
     * @param shxydm
     * @return
     */
    public List<JgObject> getJgobject(String shxydm);

    /**
     * 更新监管对象的信息
     * @param jgObject
     */
    public void updateJgobject(JgObject jgObject);

    public int updateAccountSbpt(String flag, String username);

    public ArrayList getAccountSbptByNewshxydm(String zzjgdm);

    public List<Map<String, Object>> getShenHAreaId(String path, long id, int depth);

    public List<AccountSbpt> checkHeNanUserByQymc(String qymc);

    public List<AccountSbpt> getByZzjgdmAndIsDlf(String zzjgdm, Integer isDlf, int flag);

    public AccountSbpt getAccountSbptById(long id);

    public ArrayList getMenugroupByItem(String itemcode);


}
