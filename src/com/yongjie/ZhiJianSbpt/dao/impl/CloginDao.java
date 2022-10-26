package com.yongjie.ZhiJianSbpt.dao.impl;

import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.dao.ICloginDao;
import com.yongjie.ZhiJianSbpt.model.AccountSbpt;
import com.yongjie.ZhiJianSbpt.utilities.JdbcManipulation;
import com.yongjie.ZhiJianSbpt.utilities.MD5EncryptHelper;
import com.yongjie.ZhiJianSbpt.utilities.SysFinalRecource;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository(ICloginDao.SERVICE_NAME)
public class CloginDao extends BaseDaoImpl<AccountSbpt> implements ICloginDao {
    @SuppressWarnings({"unchecked"})
    @Override
    public List<AccountSbpt> doLogin(String username, String password) {
        String sql = "select * from AccountSbpt where userName=? and password=?";
        SQLQuery sqlQuery = getSession().createSQLQuery(sql).addEntity(AccountSbpt.class);
        sqlQuery.setParameter(0, username);
        String psd = MD5EncryptHelper.getEncryption(password);// 加密处理
        sqlQuery.setParameter(1, psd);
        List<AccountSbpt> list = sqlQuery.list();
        return list;
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public List<AccountSbpt> checkAction(String username) {
        String sql = "select new AccountSbpt(isAction) from AccountSbpt where userName=?";
        Query query = getSession().createQuery(sql);
        query.setParameter(0, username);
        List<AccountSbpt> list = query.list();
        return list;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public ArrayList getMenusByUsername(String logOnUserName, String sysFlag) {

        String sql = "SELECT ID,MENUNAME,MENULINK,PARENTID FROM menu WHERE FLAG=:sysFlag ORDER BY PAIXU ASC";// 列出所有菜单,申报端无需根据权限.
        SQLQuery query = getSession().createSQLQuery(sql);
        query.setInteger("sysFlag", Integer.parseInt(sysFlag));
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return (ArrayList) query.list();
    }

    @Override
    public ArrayList getScrollList(String userName) {
        String DBType = SysFinalRecource.USE_DB_NAME;
        String sql = "";
        switch (DBType) {
            case "ORACLE":
                delete();
                creat();
                createTable(userName);
                sql = "select * FROM (select * from HOMESHENP order by sqrq desc) where ROWNUM < 10";
                break;
            case "DM":
                delete();
                creat();
                createTable(userName);
                sql = "select top 10 * from HOMESHENP  order by sqrq desc";
                break;
            case "SQLSERVER":
                createTable(userName);
                sql = "select top 10 * from HOMESHENP  order by sqrq desc";
                break;
            default:
                break;
        }
        Connection connection = getSession().connection();
        ArrayList list = null;
        list = JdbcManipulation.executeSqlQueryToList(connection, sql);
        return list;
    }

    public void delete() {
        String DBType = SysFinalRecource.USE_DB_NAME;
        String sql = "";
        switch (DBType) {
            case "DM":
                sql = "begin " + "if exists(select * from user_tables where table_name='HOMESHENP') then  "
                        + "execute immediate 'drop table HOMESHENP';  end if;end";
                break;
            case "ORACLE":
                sql = "declare num number; begin select count(1) into num from all_tables where TABLE_NAME = 'HOMESHENP'"
                        + " and OWNER='ZHIJIANXZSP'; if num=1 then execute immediate 'DROP trigger trig_homeshenp';"
                        + " execute immediate 'DROP sequence seq_homeshenp';"
                        + " execute immediate 'DROP table HOMESHENP';"
                        + " end if; end;";
                break;
            default:
                break;
        }
        Connection connection = getSession().connection();
        JdbcManipulation.executeSqlUpdate(connection, sql);
    }

    public void creat() {
        String DBType = SysFinalRecource.USE_DB_NAME;
        String sql = "";
        switch (DBType) {
            case "DM":
                sql = "create table HOMESHENP(id int IDENTITY (1,1) PRIMARY KEY,"
                        + "flowId bigint ,flowInstId bigint ,tableName varchar(20),sxlb varchar(50),sqlb varchar(50),sqdw varchar(150),sqrq datetime,flag int)";
                break;
            case "ORACLE":
                sql = "declare v_Sql varchar2(1000);begin "
                        + "v_Sql:='CREATE TABLE HOMESHENP (ID NUMBER(20) NOT NULL,FLOWID NUMBER(10) NULL ,FLOWINSTID NUMBER(10) NULL ,TABLENAME NVARCHAR2(20) NULL ,SXLB NVARCHAR2(50) NULL ,SQLB NVARCHAR2(50) NULL ,SQDW NVARCHAR2(50) NULL ,SQRQ DATE  NULL ,FLAG NUMBER(11) NULL )';"
                        + "execute immediate v_Sql;"
                        + "v_Sql:='create sequence seq_homeshenp start with 1';"
                        + "execute immediate v_Sql;"
                        + "v_Sql:='create or replace trigger trig_homeshenp before insert on HOMESHENP for each row begin select seq_homeshenp.nextval into :new.id from dual; end;';"
                        + "execute immediate v_Sql;end;";
                break;
            default:
                break;
        }
        Connection connection = getSession().connection();
        JdbcManipulation.executeSqlUpdate(connection, sql);
    }

    public void createTable(String userName) {
        // 用户名校验，用户名必须在数据库中存在
        boolean isUserNameExists = this.isUserNameExists(userName);
        if (!isUserNameExists)
            return;
        String sql = "";
        String DBType = SysFinalRecource.USE_DB_NAME;
        switch (DBType) {
            case "DM":
                sql = "INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,flag)"
                        + "select flowId,flowInstId,jgmc,sqrq,flag from bizFdjljd where czr='" + userName + "';"
                        + "update HOMESHENP set HOMESHENP.tableName='bizFdjljd' where flowId =21 ;"
                        + "INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,sqlb,flag) select flowId,flowInstId,qymc,sqrq,type,flag from bizGongyp where czr='"
                        + userName + "';" + "update HOMESHENP set HOMESHENP.tableName='bizGongyp' where flowId =2 ;"
/*					+ "INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,sqlb,flag) select flowId,flowInstId,sqdw,sqrq,sqxz,flag from bizqpcz_new where czr='"
					+ userName + "';" + "update HOMESHENP set HOMESHENP.tableName='bizqpcz_new' where flowId =51 ;"
*/

                        + "INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,flag) select flowId,flowInstId,sqdw,sqrq,flag from bizJlbzkh where czr='"
                        + userName + "';" + "update HOMESHENP set HOMESHENP.tableName='bizJlbzkh' where flowId =13 ;"
                        + "INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,sqlb,flag) select flowId,flowInstId,sqdw,sqrq,sqlb,flag from bizJlqjxspz where czr='"
                        + userName + "'; " + "update HOMESHENP set HOMESHENP.tableName='bizJlqjxspz' where flowId =20 ;"
                        + "INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,sqlb,flag) select flowId,flowInstId,sysMc,sqrq,zslx,flag from bizShiYsJyjc where caoR='"
                        + userName + "';" + "update HOMESHENP set HOMESHENP.tableName='bizShiYsJyjc' where flowId =3 ;"
                        + "INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,sqlb,flag) select flowId,flowInstId,sqdw,sqrq,sqlb,flag from bizTzsbAzgzwx where czr='"
                        + userName + "';" + "update HOMESHENP set HOMESHENP.tableName='bizTzsbAzgzwx' where flowId =16 ;"
                        + "INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,sqlb,flag) select flowId,flowInstId,sqdw,sqrq,sqlb,flag from bizTzsbJyjcJghz where czr='"
                        + userName + "';" + "update HOMESHENP set HOMESHENP.tableName='bizTzsbJyjcJghz' where flowId =19; "
                        + "INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,sqlb,flag) select flowId,flowInstId,sqdw,sqrq,sqlb,flag from bizTzsbsj where czr='"
                        + userName + "';" + "update HOMESHENP set HOMESHENP.tableName='bizTzsbsj' where flowId =18 ;"
                        + "INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,flag) select flowId,flowInstId,yjgmc,sqrq,flag from bizTzsbxkzbg where czr='"
                        + userName + "'; " + "update HOMESHENP set HOMESHENP.tableName='bizTzsbxkzbg' where flowId =17 ;"
                        + "INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,sqlb,flag) select flowId,flowInstId,sqdw,sqrq,sqlb,flag from bizTzsbzzdw where czr='"
                        + userName + "'; "
                        + "update HOMESHENP set HOMESHENP.tableName='bizTzsbzzdw' where flowId =14 ;INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,sqlb,flag)  select flowId,flowInstId,sqdw,sqrq,sqlb,flag from bizYdsylrq where czr='"
                        + userName + "'; "
                        + "update HOMESHENP set HOMESHENP.tableName='bizYdsylrq' where flowId =15 ;update HOMESHENP set HOMESHENP.sxlb=y.flowName from yjFlow y inner join HOMESHENP e on y.flowId=e.flowId; ";
                break;
            case "ORACLE":
                sql = "declare cursor c_job is select flowname,flowid from YJFLOW;c_row c_job%rowtype;"
                        + "begin "
                        + "execute immediate "
                        + "'INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,flag)"
                        + "select flowId,flowInstId,jgmc,sqrq,flag from bizFdjljd where czr=''" + userName + "''';"
                        + "execute immediate "
                        + "'update HOMESHENP set HOMESHENP.tableName=''bizFdjljd'' where flowId =21';"
                        + "execute immediate "
                        + "'INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,sqlb,flag) "
                        + "select flowId,flowInstId,qymc,sqrq,type,flag from bizGongyp where czr=''" + userName + "''';"
                        + "execute immediate "
                        + "'update HOMESHENP set HOMESHENP.tableName=''bizGongyp'' where flowId =2';"
                        + "execute immediate "
                        + "'INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,flag) "
                        + "select flowId,flowInstId,sqdw,sqrq,flag from bizJlbzkh where czr=''" + userName + "''';"
                        + "execute immediate "
                        + "'update HOMESHENP set HOMESHENP.tableName=''bizJlbzkh'' where flowId =13';"
                        + "execute immediate "
                        + "'INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,sqlb,flag) "
                        + "select flowId,flowInstId,sqdw,sqrq,sqlb,flag from bizJlqjxspz where czr=''" + userName + "'''; "
                        + "execute immediate "
                        + "'update HOMESHENP set HOMESHENP.tableName=''bizJlqjxspz'' where flowId =20';"
                        + "execute immediate "
                        + "'INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,sqlb,flag) "
                        + "select flowId,flowInstId,sysMc,sqrq,zslx,flag from bizShiYsJyjc where caoR=''" + userName + "''';"
                        + "execute immediate "
                        + "'update HOMESHENP set HOMESHENP.tableName=''bizShiYsJyjc'' where flowId =3';"
                        + "execute immediate "
                        + "'INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,sqlb,flag) "
                        + "select flowId,flowInstId,sqdw,sqrq,sqlb,flag from bizTzsbAzgzwx where czr=''" + userName + "''';"
                        + "execute immediate "
                        + "'update HOMESHENP set HOMESHENP.tableName=''bizTzsbAzgzwx'' where flowId =16';"
                        + "execute immediate "
                        + "'INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,sqlb,flag) "
                        + "select flowId,flowInstId,sqdw,sqrq,sqlb,flag from bizTzsbJyjcJghz where czr=''" + userName + "''';"
                        + "execute immediate "
                        + "'update HOMESHENP set HOMESHENP.tableName=''bizTzsbJyjcJghz'' where flowId =19';"
                        + "execute immediate "
                        + "'INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,sqlb,flag) "
                        + "select flowId,flowInstId,sqdw,sqrq,sqlb,flag from bizTzsbsj where czr=''" + userName + "''';"
                        + "execute immediate "
                        + "'update HOMESHENP set HOMESHENP.tableName=''bizTzsbsj'' where flowId =18';"
                        + "execute immediate "
                        + "'INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,flag) "
                        + "select flowId,flowInstId,yjgmc,sqrq,flag from bizTzsbxkzbg where czr=''" + userName + "'''; "
                        + "execute immediate "
                        + "'update HOMESHENP set HOMESHENP.tableName=''bizTzsbxkzbg'' where flowId =17';"
                        + "execute immediate "
                        + "'INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,sqlb,flag) "
                        + "select flowId,flowInstId,sqdw,sqrq,sqlb,flag from bizTzsbzzdw where czr=''" + userName + "''';"
                        + "execute immediate "
                        + "'update HOMESHENP set HOMESHENP.tableName=''bizTzsbzzdw'' where flowId =14';"
                        + "execute immediate "
                        + "'INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,sqlb,flag) "
                        + "select flowId,flowInstId,sqdw,sqrq,sqlb,flag from bizYdsylrq where czr=''" + userName + "''';"
                        + "execute immediate "
                        + "'update HOMESHENP set HOMESHENP.tableName=''bizYdsylrq'' where flowId =15';"
                        + "execute immediate "
                        + "'INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,flag)"
                        + "select flowId,flowInstId,JDCMC,sqrq,flag from BIZJDCJYJC where czr=''" + userName + "''';"
                        + "execute immediate "
                        + "'update HOMESHENP set HOMESHENP.tableName=''BIZJDCJYJC'' where flowId =23';"
                        + "execute immediate "
                        + "'INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,flag)"
                        + "select flowId,flowInstId,SQDW,sqrq,flag from BIZQPCZ where czr=''" + userName + "''';"
                        + "execute immediate "
                        + "'update HOMESHENP set HOMESHENP.tableName=''BIZQPCZ'' where flowId =25';"
                        + "execute immediate "
                        + "'INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,flag)"
                        + "select flowId,flowInstId,sqdw,sqrq,flag from BIZTZSBWXZGRD where czr=''" + userName + "''';"
                        + "execute immediate "
                        + "'update HOMESHENP set HOMESHENP.tableName=''BIZTZSBWXZGRD'' where flowId =26';"
                        + "execute immediate "
                        + "'INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,flag)"
                        + "select flowId,flowInstId,dwmc,sqrq,flag from BIZTZSBZYRYKH where czr=''" + userName + "''';"
                        + "execute immediate "
                        + "'update HOMESHENP set HOMESHENP.tableName=''BIZTZSBZYRYKH'' where flowId =27';"
                        + "execute immediate "
                        + "'INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,flag)"
                        + "select flowId,flowInstId,sqdw,sqrq,flag from BIZZZJLQJ where czr=''" + userName + "''';"
                        + "execute immediate "
                        + "'update HOMESHENP set HOMESHENP.tableName=''BIZZZJLQJ'' where flowId =24';"
                        + "for c_row in c_job loop "
                        + "update HOMESHENP set HOMESHENP.sxlb=c_row.flowname where HOMESHENP.FLOWID = c_row.flowid;"
                        + "end loop;"
                        + "end;";
                break;
            case "SQLSERVER":
                sql = "IF EXISTS(SELECT * FROM sys.Tables WHERE name='HOMESHENP') DROP TABLE HOMESHENP "
                        + "create table HOMESHENP" + "(" + "ID  int IDENTITY (1,1) not null," + "FLOWID bigint ,"
                        + "FLOWINSTID bigint ," + "TABLENAME varchar(20)," + "SXLB varchar(50)," + "SQLB varchar(50),"
                        + "SQDW varchar(150)," + "SQRQ datetime," + "FLAG int," + "primary key (ID)  " + ");"
                        + "INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,flag)"
                        + "select flowId,flowInstId,jgmc,sqrq,flag from bizFdjljd where czr='" + userName
                        + "' update HOMESHENP set HOMESHENP.tableName='bizFdjljd' where flowId =21 "

                        + "INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,sqlb,flag) "
                        + "select flowId,flowInstId,qymc,sqrq,type,flag from bizGongyp where czr='" + userName
                        + "' update HOMESHENP set HOMESHENP.tableName='bizGongyp' where flowId =2 "

                        + "INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,flag) "
                        + "select flowId,flowInstId,sqdw,sqrq,flag from bizJlbzkh where czr='" + userName
                        + "' update HOMESHENP set HOMESHENP.tableName='bizJlbzkh' where flowId =13 "

                        + "INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,sqlb,flag) "
                        + "select flowId,flowInstId,sqdw,sqrq,sqlb,flag from bizJlqjxspz where czr='" + userName
                        + "' update HOMESHENP set HOMESHENP.tableName='bizJlqjxspz' where flowId =20 or flowId=22"

                        + "INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,sqlb,flag) "
                        + "select flowId,flowInstId,sysMc,sqrq,zslx,flag from bizShiYsJyjc where caoR='" + userName
                        + "' update HOMESHENP set HOMESHENP.tableName='bizShiYsJyjc' where flowId =3 "

                        + "INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,sqlb,flag) "
                        + "select flowId,flowInstId,sqdw,sqrq,sqlb,flag from bizTzsbAzgzwx where czr='" + userName
                        + "' update HOMESHENP set HOMESHENP.tableName='bizTzsbAzgzwx' where flowId =16 "

                        + "INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,sqlb,flag) "
                        + "select flowId,flowInstId,sqdw,sqrq,sqlb,flag from bizTzsbJyjcJghz where czr='" + userName
                        + "' update HOMESHENP set HOMESHENP.tableName='bizTzsbJyjcJghz' where flowId =19 "

                        + "INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,sqlb,flag) "
                        + "select flowId,flowInstId,sqdw,sqrq,sqlb,flag from bizTzsbsj where czr='" + userName
                        + "' update HOMESHENP set HOMESHENP.tableName='bizTzsbsj' where flowId =18 "

                        + "INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,flag) "
                        + "select flowId,flowInstId,yjgmc,sqrq,flag from bizTzsbxkzbg where czr='" + userName
                        + "' update HOMESHENP set HOMESHENP.tableName='bizTzsbxkzbg' where flowId =17 "

                        + "INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,sqlb,flag) "
                        + "select flowId,flowInstId,sqdw,sqrq,sqlb,flag from bizTzsbzzdw where czr='" + userName
                        + "' update HOMESHENP set HOMESHENP.tableName='bizTzsbzzdw' where flowId =14 "

                        + "INSERT INTO HOMESHENP (flowId,flowInstId,sqdw,sqrq,sqlb,flag)  "
                        + "select flowId,flowInstId,sqdw,sqrq,sqlb,flag from bizYdsylrq where czr='" + userName
                        + "' update HOMESHENP set HOMESHENP.tableName='bizYdsylrq' where flowId =15"

                        + "update HOMESHENP set HOMESHENP.sxlb=y.flowName from yjFlow y inner join HOMESHENP e on y.flowId=e.flowId ";
                break;
            default:
                break;
        }
        Connection connection = getSession().connection();
        JdbcManipulation.executeSqlUpdate(connection, sql);// executeUpdate，执行后再有list集合
    }

    // 判断用户名是否在AccountSbpt表中存在
    private boolean isUserNameExists(String userName) {
        String sql = "select * from AccountSbpt where username=:username";
        SQLQuery query = getSession().createSQLQuery(sql);
        query.setString("username", userName);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> list = query.list();
        if (list == null || list.size() < 1)
            return false;
        return true;
    }

    @Override
    public ArrayList getTrueName(String userName) {
        String sql = "select * from AccountXzsp where userName =:userName";
        SQLQuery query = getSession().createSQLQuery(sql);
        query.setString("userName", userName);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return (ArrayList) query.list();
    }

    @Override
    public ArrayList getAccountXzsp(String userId) {
        // TODO Auto-generated method stub
        String sql = "select * from AccountXzsp where id=:userId";
        SQLQuery query = getSession().createSQLQuery(sql);
        query.setLong("userId", Long.parseLong(userId));
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return (ArrayList) query.list();
    }

    @Override
    public ArrayList getAccountSbptByName(String username) {
        // TODO Auto-generated method stub
        String sql = "select * from AccountSbpt where username=:username";
        SQLQuery query = getSession().createSQLQuery(sql);
        query.setString("username", username);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return (ArrayList) query.list();
    }

    @Override
    public AccountSbpt getAccountSbptByUserName(String userName) throws Exception {
        String sql = "select * from AccountSbpt where userName =:userName";
        SQLQuery query = getSession().createSQLQuery(sql).addEntity(AccountSbpt.class);
        query.setString("userName", userName);
        return (AccountSbpt) query.uniqueResult();
    }
}
