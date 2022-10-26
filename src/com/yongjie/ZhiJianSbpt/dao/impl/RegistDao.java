package com.yongjie.ZhiJianSbpt.dao.impl;

import com.mysql.jdbc.StringUtils;
import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.dao.IRegistDao;
import com.yongjie.ZhiJianSbpt.model.AccountSbpt;
import com.yongjie.ZhiJianSbpt.model.JgObject;
import com.yongjie.ZhiJianSbpt.utilities.JdbcManipulation;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(IRegistDao.SERVICE_NAME)
public class RegistDao extends BaseDaoImpl<AccountSbpt> implements IRegistDao {
    @Override
    public void save(AccountSbpt entity) {// 注册保存
        super.save(entity);
    }

    @Override
    public void update(AccountSbpt entity) {
        super.update(entity);
    }

    @Override
    public Boolean checkName(String name, String qymc, long userid) {
        Boolean resultFlag = false;
        StringBuffer selectSql = new StringBuffer();
        selectSql.append("SELECT A.ID FROM ACCOUNTSBPT A WHERE A.USERNAME ='" + name + "' OR A.QYMC='" + qymc + "' AND A.ID<>" + userid + "");
        SQLQuery sqlQuery = getSession().createSQLQuery(selectSql.toString());
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List resultList = sqlQuery.list();
        if (resultList.size() > 0) {
            resultFlag = true;
        }
        return resultFlag;
    }

    @Override
    public AccountSbpt getAccountSbptByUserName(String username) {// 根据用户名查询
        String sql = "SELECT * from AccountSbpt a where a.userName =:username";
        SQLQuery query = getSession().createSQLQuery(sql).addEntity(AccountSbpt.class);
        query.setString("username", username);
        List<AccountSbpt> dataAll = query.list();// addEntity()将object对象转化成映射类型。
        AccountSbpt model = new AccountSbpt();
        if (dataAll.size() > 0) {// 说明已经有了
            model = dataAll.get(0);
        }
        return model;
    }

    @Override
    public int changePsd(String psd, String username) {// 执行更新密码
        String sql = "update AccountSbpt set password='" + psd + "' where userName='" + username + "'";
        int num = getSession().createSQLQuery(sql).executeUpdate();
        return num;
    }

    @Override
    public AccountSbpt checkHeNanUser(String username, String mobile) {
        String sql = "SELECT * from AccountSbpt a where a.userName =:username ";
        SQLQuery query = getSession().createSQLQuery(sql);
        query.setString("username", username);
        @SuppressWarnings("unchecked")
        List<AccountSbpt> dataAll = query.addEntity(AccountSbpt.class).list();// addEntity()将object对象转化成映射类型。
        AccountSbpt model = new AccountSbpt();
        if (dataAll.size() > 0) {// 说明已经有了
            model = dataAll.get(0);
        }
        return model;
    }

    @Override
    public int changeuserId(long id, String userId) {
        String sql = "update AccountSbpt set userId='" + userId + "' where id=" + id;
        int num = getSession().createSQLQuery(sql).executeUpdate();
        return num;
    }

    @Override
    public ArrayList getMenusByGroup(int menuGroup) {
        // TODO Auto-generated method stub
        String sql = "select * from menu where flag=0 and menuGroup=" + menuGroup + " order by paixu asc";
        Connection conn = getSession().connection();
        return JdbcManipulation.executeSqlQueryToList(conn, sql);
    }

    // 判断当前社会信用代码是否已存在
    @Override
    public Number validShxydm(String shxydm) {
        String sql = "select ID from ACCOUNTSBPT where Upper(ZZJGDM)=:shxydm";
        SQLQuery query = super.getSession().createSQLQuery(sql);

        query.setString("shxydm", shxydm.toUpperCase());

        List<Number> list = query.list();

        if (list == null || list.size() < 1) {
            return -1;
        }
        return list.get(0);
    }

    // 判断是否存在记录，其社会信用代码和企业名称为指定的值
    @Override
    public Number validShxydm(String shxydm, String qymc) {
        String sql = "select ID from ACCOUNTSBPT where Upper(ZZJGDM)=:shxydm and QYMC=:qymc";
        SQLQuery query = super.getSession().createSQLQuery(sql);

        query.setString("shxydm", shxydm.toUpperCase());
        query.setString("qymc", qymc);

        List<Number> list = query.list();

        if (list == null || list.size() < 1)
            return -1;

        return list.get(0);
    }

    // 获取当前用户的记录数
    @Override
    public Number getCountsByUserName(String userName) {
        String sql = "select count(*) from ACCOUNTSBPT where USERNAME=:userName";
        SQLQuery query = super.getSession().createSQLQuery(sql);
        query.setParameter("userName", userName);
        return (Number) query.uniqueResult();
    }

    // 根据社会信用代码从Jgobject表中获取对应的newShxydm
    @Override
    public List<String> getJgobjectNewShxydmList(String shxydm) {
        return getJgobjectNewShxydmList(shxydm, null);
    }

    // 根据社会信用代码和企业名称从Jgobject表中获取对应的newShxydm
    @Override
    public List<String> getJgobjectNewShxydmList(String shxydm, String qymc) {
        String sql = "select NEWSHXYDM from JGOBJECT where SHXYDM=:shxydm";

        if (qymc != null)
            sql += "  and Upper(JGOBJECTNAME)=:qymc";

        SQLQuery query = super.getSession().createSQLQuery(sql);
        query.setString("shxydm", shxydm);

        if (qymc != null)
            query.setString("qymc", qymc.toUpperCase());

        return query.list();
    }

    // 将当前对象的信息插入到 Jgobject表中 TODO
    @Override
    public void insertIntoJgobject(AccountSbpt accountSbpt) {
        String sql = "insert into JGOBJECT(ID,JGOBJECTNAME,ZHUSUO,AREAID,SHXYDM,FDDBR,FDDBRDH,LXR,LXDH,EMAIL,YZBM,ISDLF,NEWSHXYDM) "
                + "values(SEQ_JGOBJECT.nextval,:qymc,:zcdz,:areaId,:zzjgdm,:fddbr,:fddbrdh,:lxr,:lxdh,:email,:yzbm,:isDlf,:newShxydm)";

        SQLQuery query = super.getSession().createSQLQuery(sql);

        query.setString("qymc", accountSbpt.getQymc());
        query.setString("zcdz", accountSbpt.getZcdz());
        query.setInteger("areaId", accountSbpt.getAreaId());
        query.setString("zzjgdm", accountSbpt.getZzjgdm());
        query.setString("fddbr", accountSbpt.getFddbr());
        query.setString("fddbrdh", accountSbpt.getFddbrdh());
        query.setString("lxr", accountSbpt.getLxr());
        query.setString("lxdh", accountSbpt.getLxdh());
        query.setString("email", accountSbpt.getEmail());
        query.setString("yzbm", accountSbpt.getYzbm());
        query.setInteger("isDlf", accountSbpt.getIsDlf());
        query.setString("newShxydm", accountSbpt.getNewShxydm());

        query.executeUpdate();
    }

    // 通过当前用户id来更新
    @Override
    public void updateByUserId(AccountSbpt accountSbpt) {
        String sql = "update ACCOUNTSBPT set "
                + "USERNAME=:userName, PASSWORD=:password,FLAG=:flag,QYMC=:qymc,ZCDZ=:zcdz,"
                + "AREAID=:areaId,ZZJGDM=:zzjgdm,FDDBR=:fddbr,FDDBRDH=:fddbrdh,LXR=:lxr,"
                + "LXDH=:lxdh,EMAIL=:email,YZBM=:yzbm,ISACTION=:isAction,CAODATE=:caoDate,"
                + "ISDLF=:isDlf,NEWSHXYDM=:newShxydm,SFBS=:sfbs,WSXZQH=:wsxzqh where ID=:id";

        SQLQuery query = super.getSession().createSQLQuery(sql);

        query.setString("userName", accountSbpt.getUserName());
        query.setString("password", accountSbpt.getPassword());
        query.setInteger("flag", accountSbpt.getFlag());
        query.setString("qymc", accountSbpt.getQymc());
        query.setString("zcdz", accountSbpt.getZcdz());
        query.setInteger("areaId", accountSbpt.getAreaId());
        query.setString("zzjgdm", accountSbpt.getZzjgdm());
        query.setString("fddbr", accountSbpt.getFddbr());
        query.setString("fddbrdh", accountSbpt.getFddbrdh());
        query.setString("lxr", accountSbpt.getLxr());
        query.setString("lxdh", accountSbpt.getLxdh());
        query.setString("email", accountSbpt.getEmail());
        query.setString("yzbm", accountSbpt.getYzbm());
        query.setInteger("isAction", accountSbpt.getIsAction());
        query.setDate("caoDate", accountSbpt.getCaoDate());
        query.setInteger("isDlf", accountSbpt.getIsDlf());
        query.setString("newShxydm", accountSbpt.getNewShxydm());
        query.setLong("id", accountSbpt.getId());
        query.setInteger("sfbs", accountSbpt.getSfbs());
        query.setString("wsxzqh", accountSbpt.getWsXzqh());

        query.executeUpdate();

    }

    // 更近newShxydm来更新Jgobject表的部分字段
    @Override
    public void updateJgobjectByNewShxydm(AccountSbpt accountSbpt) {
        String sql = "update JGOBJECT set JGOBJECTNAME=:qymc, ZHUSUO=:zcdz,"
                + "AREAID=:areaId,SHXYDM=:zzjgdm,FDDBR=:fddbr,FDDBRDH=:fddbrdh,"
                + "LXR=:lxr,LXDH=:lxdh,EMAIL=:email,YZBM=:yzbm,ISDLF=:isDlf " + "where NEWSHXYDM=:newShxydm";

        SQLQuery query = super.getSession().createSQLQuery(sql);
        query.setString("qymc", accountSbpt.getQymc());
        query.setString("zcdz", accountSbpt.getZcdz());
        query.setInteger("areaId", accountSbpt.getAreaId());
        query.setString("zzjgdm", accountSbpt.getZzjgdm());
        query.setString("fddbr", accountSbpt.getFddbr());
        query.setString("fddbrdh", accountSbpt.getFddbrdh());
        query.setString("lxr", accountSbpt.getLxr());
        query.setString("lxdh", accountSbpt.getLxdh());
        query.setString("email", accountSbpt.getEmail());
        query.setString("yzbm", accountSbpt.getYzbm());
        query.setInteger("isDlf", accountSbpt.getIsDlf());
        query.setString("newShxydm", accountSbpt.getNewShxydm());

        query.executeUpdate();

    }

    // 查询Jgobject表中是否存在当前newShxydm
    @Override
    public boolean isNewShxydmExistInJgobject(String newShxydm) {
        String sql = "select count(*) from JGOBJECT where NEWSHXYDM=:newShxydm";
        SQLQuery query = super.getSession().createSQLQuery(sql);
        query.setString("newShxydm", newShxydm);
        Number count = (Number) query.uniqueResult();
        if (count == null || count.intValue() < 1)
            return false;
        return true;
    }

    // 根据社会信用代码和企业名称查询数据
    @Override
    public List<JgObject> getJgobject(String shxydm, String qymc) {
        try {
            String sql = "from JgObject j where j.shxydm=:shxydm ";
            if (qymc != null)
                sql += " and j.jgObjectName=:jgObjectName";

            Query query = super.getSession().createQuery(sql);

            query.setString("shxydm", shxydm);
            if (qymc != null)
                query.setString("jgObjectName", qymc);

            return query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 根据社会信用代码查询数据
    @Override
    public List<JgObject> getJgobject(String shxydm) {
        return this.getJgobject(shxydm, null);
    }

    // 更新监管对象信息
    @Override
    public void updateJgobject(JgObject jgObject) {
        super.getSession().update(jgObject);
    }

    @Override
    public int updateAccountSbpt(String flag, String username) {
        // TODO Auto-generated method stub
        String sql = "update accountSbpt set flag=" + flag + " where username='" + username + "'";
        Connection conn = getSession().connection();
        return JdbcManipulation.executeSqlUpdate(conn, sql);
    }

    @Override
    public ArrayList getAccountSbptByNewshxydm(String zzjgdm) {
        // TODO Auto-generated method stub
        String sql = "select * from AccountSbpt where newshxydm='" + zzjgdm + "'";
        Connection conn = getSession().connection();
        return JdbcManipulation.executeSqlQueryToList(conn, sql);
    }

    @Override
    public List<Map<String, Object>> getShenHAreaId(String path, long id, int depth) {
        String sql = "select a.*,d.deptName from area a  left join DEPT d on a.id =d.AREAID " + "where a.depth=" + depth
                + " and (a.id in(" + path + ") or a.id=" + id + ")";
        SQLQuery query = getSession().createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.list();
    }

    @Override
    public List<AccountSbpt> checkHeNanUserByQymc(String qymc) {
        String sql = "from AccountSbpt a where a.qymc=? ";
        List<AccountSbpt> list = getSession().createQuery(sql).setParameter(0, qymc).list();
        return list;
    }

    @Override
    public List<AccountSbpt> getByZzjgdmAndIsDlf(String zzjgdm, Integer isDlf, int flag) {
        String sql = "from AccountSbpt where zzjgdm=:zzjgdm ";
        // 当是个人注册的时候，不需要添加 是否独立法人条件
        if (flag != 1) {
            sql += " and isDlf=:isDlf";
        }
        Query query = getSession().createQuery(sql);
        query.setString("zzjgdm", zzjgdm);

        if (flag != 1)
            query.setInteger("isDlf", isDlf);
        return query.list();
    }

    @Override
    public AccountSbpt getAccountSbptById(long id) {
        // TODO Auto-generated method stub
        String sql = "select * from AccountSbpt where id=:id ";
        SQLQuery query = getSession().createSQLQuery(sql).addEntity(AccountSbpt.class);
        query.setLong("id", id);
        List<AccountSbpt> dataAll = query.list();
        if (dataAll.size() > 0) {
            return dataAll.get(0);
        }
        return null;
    }

    @Override
    public ArrayList getMenugroupByItem(String itemcode) {
        // TODO Auto-generated method stub
        String sql = "select * from itemtable where MENUGROUP is not null and ITEMCODE=:itemcode";
        SQLQuery query = getSession().createSQLQuery(sql);
        query.setString("itemcode", itemcode);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = query.list();
        return (ArrayList) list;
    }
}
