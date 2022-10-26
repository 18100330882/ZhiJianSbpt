package com.yongjie.ZhiJianSbpt.service.impl;

import com.yongjie.ZhiJianSbpt.dao.IRegistDao;
import com.yongjie.ZhiJianSbpt.model.AccountSbpt;
import com.yongjie.ZhiJianSbpt.model.JgObject;
import com.yongjie.ZhiJianSbpt.service.IRegistService;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Transactional(readOnly = false)
@Service(IRegistService.SERVICE_NAME)
public class RegistService implements IRegistService {

    @Resource(name = IRegistDao.SERVICE_NAME)
    private IRegistDao registDao;

    @Override
    public Boolean checkName(String name, String enterpriseName, long userid) {//查重
        return registDao.checkName(name, enterpriseName, userid);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void doRegist(AccountSbpt accountSbpt) {//注册保存
        registDao.save(accountSbpt);
    }

    public AccountSbpt getAccountSbptByUserName(String username) {
        return registDao.getAccountSbptByUserName(username);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void changeOwnData(AccountSbpt accountSbpt) {//登陆后可以修改注册信息
        registDao.update(accountSbpt);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public int changePsd(String psd, String username) {
        return registDao.changePsd(psd, username);
    }

    @Override
    public AccountSbpt checkHeNanUser(String username, String mobile) {
        // TODO Auto-generated method stub
        return registDao.checkHeNanUser(username, mobile);
    }

    @Override
    public int changeuserId(long id, String userId) {
        return registDao.changeuserId(id, userId);
    }

    @Override
    public ArrayList getMenusByGroup(int menuGroup) {
        return registDao.getMenusByGroup(menuGroup);
    }

    //校验社会信用代码是否已存在
    @Override
    public String validShxydm(String shxydm, String isDlfr, String qymc) {
        /*
         * 由于独立法人和非独立法人的校验规则不同，因此首先判断是否是独立法人
         * 如果是独立法人，则直接查询当前社会信用代码是否存在
         * 如果是非独立法人，则根据社会信用代码和企业名称判断是否存在
         */
        Number id;
        if ("0".equals(isDlfr)) {        //独立法人的校验
            id = registDao.validShxydm(shxydm);

        } else {    //非独立法人的校验
            id = registDao.validShxydm(shxydm, qymc);
        }

        //如果返回值为 -1，则证明当前 社会信用代码未存在
        if (id.longValue() == -1)
            return "not";
        else
            return id.longValue() + "";
    }

    //是否存在当前用户的信息
    @Override
    public boolean isUserNameExist(String userName) {
        Number count = registDao.getCountsByUserName(userName);
        if (count == null || count.intValue() < 1)
            return true;
        return false;
    }

    //获取 newShxydm
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public String getNewShxydm(AccountSbpt accountSbpt) {
        //获取是否是独立法人
        int isDlfr = accountSbpt.getIsDlf();

        String shxydm = accountSbpt.getZzjgdm();

        //存储从Jgobject表中查询到的newShxydm
        List<String> newShxydmList = null;

        if (isDlfr == 1) { //不是独立法人
            //根据社会信用代码和企业名获取Jgobject表符合条件的记录的newShxydm
            newShxydmList = registDao.getJgobjectNewShxydmList(shxydm, accountSbpt.getQymc());

        } else {        //其他值全默认为独立法人
            newShxydmList = registDao.getJgobjectNewShxydmList(shxydm);
        }

        if (newShxydmList != null && newShxydmList.size() > 0 && !StringUtil.isNullOrEmpty(newShxydmList.get(0)))    //如果查询到了，返回
            return newShxydmList.get(0);

        //如果没有查询到
        String newShxydm = this.genarateNewShxydm(shxydm);

        //将表单数据插入到 Jgobject表中
        accountSbpt.setNewShxydm(newShxydm);
        registDao.insertIntoJgobject(accountSbpt);

        return newShxydm;
    }

    //根据用户id来更新AccountSbpt，通过newShxydm来更新 Jgobject
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void updateByUserId(AccountSbpt accountSbpt) {
        //判断当前用户
        registDao.updateByUserId(accountSbpt);

        //TOOD 其字段的对应关心需进一步确认
        registDao.updateJgobjectByNewShxydm(accountSbpt);

    }

    /**
     * 根据传递进来的shxydm生成newShxydm
     * 当生成newShxydm后，判断Jgobject表中是否已经存在当前的值，如果存在，重新生成
     * 如果不存在，将当前生成的值返回
     *
     * @param shxydm
     * @return
     */
    private String genarateNewShxydm(String shxydm) {
        String uuid = null;
        ;

        String newShxydm = null;

        //去Jgobject表中查询当前newShxydm是否已存在，如果存在，则再次生成，否则返回
        while (true) {
            uuid = UUID.randomUUID().toString().substring(0, 4);
            newShxydm = shxydm + "-" + uuid;

            boolean isExist = registDao.isNewShxydmExistInJgobject(newShxydm);

            if (!isExist) {
                break;
            }
        }

        return newShxydm;
    }

    //判断当前用户信息中newShxydm是否为空
    @Override
    public boolean newShxydmISNull(String userName) {
        //根据userName获取用户的详细信息
        AccountSbpt user = registDao.getAccountSbptByUserName(userName);

        //如果用户为空，则肯定不存在
        if (user == null)
            return true;

        //由于当查询不到数据时，dao会创建一个空的对象，因此判断返回的用户的用户名是否为空，可分辨该返回用户是查询到的还是自己创建的
        if (user.getUserName() == null)
            return true;

        //如果确实根据用户名从数据库中查询到了用户，则判断其newShxydm是否为空
        if (StringUtil.isNullOrEmpty(user.getNewShxydm()))
            return true;

        return false;
    }

    // 根据监管社会信用代码、企业名称、是否独立法人获取监管对象
    @Override
    public JgObject getJgobject(String shxydm, String qymc, Integer isDlf) {
        List<JgObject> list = null;
        //只有当isDlf为 1 的时候，才认为是非独立法人，其他值均认为是独立法人
        if (isDlf != null && isDlf.intValue() == 1) {
            //非独立法人，根据社会信用代码和企业名称查
            list = registDao.getJgobject(shxydm, qymc);
        } else {
            list = registDao.getJgobject(shxydm);
        }

        if (list == null || list.size() < 1)
            return null;

        //查询到了，只返回第一条
        return list.get(0);
    }

    //将accountSbpt的相关信息插入到JgObject表中
    @Override
    public void insertIntoJgobject(AccountSbpt sbpt) {
        registDao.insertIntoJgobject(sbpt);
    }

    //根据社会信用代码返回一个在JgObject表中不存在的newShxydm
    @Override
    public String getNewShxydm2(String shxydm) {
        String uuid = null;
        String newShxydm = null;

        //为社会信用代码添加一个随机生成的后缀作为newShxydm，并去Jgobject表中查询当前newShxydm是否存在，如果存在，重新生成
        while (true) {
            uuid = UUID.randomUUID().toString().substring(0, 4);
            newShxydm = shxydm + "-" + uuid;

            boolean isExist = registDao.isNewShxydmExistInJgobject(newShxydm);

            if (!isExist)
                break;
        }
        return newShxydm;
    }

    // 根据社会信用代码返回一个在jgobject表中不存在的newShxydm
    @Override
    public String getNewShxydm3(String shxydm, int isDlf) throws Exception {
        String newShxydm = shxydm;
        if (isDlf == 1)
            newShxydm = shxydm + "-" + UUID.randomUUID().toString().substring(0, 4);

        while (true) {
            boolean isExist = registDao.isNewShxydmExistInJgobject(newShxydm);

            if (!isExist)
                break;
            newShxydm = shxydm + "-" + UUID.randomUUID().toString().substring(0, 4);
        }
        return newShxydm;
    }

    //更新监管对象的信息
    @Override
    public void updateJgobject(JgObject jgObject) {
        registDao.updateJgobject(jgObject);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public int updateAccountSbpt(String flag, String username) {
        // TODO Auto-generated method stub
        return registDao.updateAccountSbpt(flag, username);
    }

    @Override
    public String getNewShxydm4(String zzjgdm) {
        // TODO Auto-generated method stub
        String result = zzjgdm;
        ArrayList list = registDao.getAccountSbptByNewshxydm(zzjgdm);
        if (list.size() > 0) {
            result = zzjgdm + "-" + UUID.randomUUID().toString().substring(0, 4);
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> getShenHAreaId(String path, long id, int depth) throws Exception {
        return registDao.getShenHAreaId(path, id, depth);
    }

    @Override
    public List<AccountSbpt> checkHeNanUserByQymc(String qymc) {
        // TODO Auto-generated method stub
        return registDao.checkHeNanUserByQymc(qymc);
    }


    // 判断当前用户是否可以注册
    @Override
    public String ifRegist(AccountSbpt model) throws Exception {
        /*
         * 如果当前用户是独立法人或者个人，判断当前shxydm或身份证号是否在数据库中已经存在
         * 	如果存在
         * 		如果是独立法人，则提示注册非独立法人
         * 		如果是个人，则提示已经注册
         * 	如果不存在，允许注册
         * 如果当前用户注册非独立法人，判读所属法人是否已经在数据库中存在
         * 	如果存在，允许注册
         *  如果不存在，提示其注册独立法人
         */

        // 非独立法人的注册
        if (new Integer(1).equals(model.getIsDlf())) {
            List<AccountSbpt> list = registDao.getByZzjgdmAndIsDlf(model.getZzjgdm().trim(), 0, 0);
            if (list != null && list.size() > 0) {
                return "success";
            } else {
                return "系统中不存在当前社会信用代码的独立法人，请注册独立法人！";
            }
        } else {
            List<AccountSbpt> list = registDao.getByZzjgdmAndIsDlf(model.getZzjgdm().trim(), model.getIsDlf(), model.getFlag());
            if (list != null && list.size() > 0) {
                if (model.getFlag() == 1) {
                    return "系统中已存在当前身份证号对应的人员，不能再次注册！";
                } else {
                    return "系统中已经存在当前社会信用代码对应的独立法人，请注册非独立法人！";
                }
            } else {
                return "success";
            }
        }
    }

    @Override
    public ArrayList getMenugroupByItem(String itemcode) {
        // TODO Auto-generated method stub
        return registDao.getMenugroupByItem(itemcode);
    }

    @Override
    public void updateQymcByUsername(String newQymc, String username) {
        // TODO Auto-generated method stub
        AccountSbpt sbpt = registDao.getAccountSbptByUserName(username);
        String qymc = sbpt.getQymc();
        if (newQymc.equals(qymc))
            return;
        sbpt.setQymc(newQymc);
        registDao.update(sbpt);
    }
}
