package com.yongjie.ZhiJianSbpt.service;

import com.yongjie.ZhiJianSbpt.model.AccountSbpt;
import com.yongjie.ZhiJianSbpt.model.JgObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public interface IRegistService {
    public final static String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.service.impl.RegistService";

    public Boolean checkName(String name, String enterpriseName, long userid);//查重

    public void doRegist(AccountSbpt accountSbpt);//注册保存

    public AccountSbpt getAccountSbptByUserName(String username);

    public void changeOwnData(AccountSbpt accountSbpt);

    public int changePsd(String psd, String username);//重设密码

    public AccountSbpt checkHeNanUser(String username, String mobile);//根据用户名和联系电话查询数据

    public List<AccountSbpt> checkHeNanUserByQymc(String qymc);//根据企业名称

    //更新云政用户的userID 到库中
    public int changeuserId(long id, String userId);//执行更新密码

    //菜单表中筛选shiys的菜单
    public ArrayList getMenusByGroup(int menuGroup);

    /**
     * 校验社会信用代码是否已存在
     *
     * @param shxydm 待校验的社会信用代码
     * @param isDlfr 是否是独立法人，独立法人和非独立法人的校验规则不同
     * @param qymc   企业名称，非独立法人时使用
     * @return not：不存在     其他值： 符合的值得id
     * @author cyk
     */
    public String validShxydm(String shxydm, String isDlfr, String qymc);

    /**
     * 判断是否存在当前用户的相关信息
     *
     * @param userName 用户名
     * @return true 存在      false  不存在
     * @author cyk
     */
    public boolean isUserNameExist(String userName);

    /**
     * 获取newShxydm
     * 首先根据获取当前对象是否是独立法人
     * 如果是，根据当前对象的社会信用代码去Jgobject表中当前社会信用代码查询是否存在
     * 如果存在，获取到当前记录的newShxydm
     * 否则，生成一个newShxydm，并将当前对象的相关信息插入到Jgobject表中
     * <p>
     * 如果不是，则根据当前对象的社会信用代码和企业名称去Jgobject表中查询是否存在符合条件的记录，存在或不存在时执行逻辑同是独立法人时
     *
     * @param accountSbpt
     * @return newShxydm
     * @author cyk
     */
    public String getNewShxydm(AccountSbpt accountSbpt);

    /**
     * 根据当前数据的userid来更新
     *
     * @param accountSbpt
     */
    public void updateByUserId(AccountSbpt accountSbpt);

    /**
     * 校验当前用户的信息中newShxydm是否为空
     *
     * @param userName
     * @return true: 为空   false：不为空
     */
    public boolean newShxydmISNull(String userName);

    /**
     * 根据社会信用代码、企业名称、是否独立法人查询监管对象信息
     *
     * @param shxydm
     * @param qymc
     * @param isDlf
     * @return
     */
    public JgObject getJgobject(String shxydm, String qymc, Integer isDlf);

    /**
     * 将accountsbpt的信息插入到JgObject中
     *
     * @param sbpt
     */
    public void insertIntoJgobject(AccountSbpt sbpt);

    /**
     * 根据社会信用代码，返回一个在Jgobject表中不存在的newShxydm
     *
     * @param shxydm
     * @return
     */
    public String getNewShxydm2(String shxydm);

    /**
     * 根据社会信用代码和是否是独立法人，返回 在jgobject 表中不存在的 newShxydm
     *
     * @param shxydm
     * @param isDlf
     * @return
     * @throws Exception
     */
    public String getNewShxydm3(String shxydm, int isDlf) throws Exception;

    /**
     * 修改监管对象的信息
     *
     * @param jgObject
     */
    public void updateJgobject(JgObject jgObject);

    public int updateAccountSbpt(String flag, String username);

    public String getNewShxydm4(String zzjgdm);

    /**
     * 获取 监管区域id
     *
     * @param path
     * @param id
     * @param depth
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getShenHAreaId(String path, long id, int depth) throws Exception;

    /**
     * 判断当前用户是否可以注册
     *
     * @param model
     * @return
     * @throws Exception
     */
    public String ifRegist(AccountSbpt model) throws Exception;

    public ArrayList getMenugroupByItem(String itemcode);

    public void updateQymcByUsername(String newQymc, String username);
}
