package com.yongjie.ZhiJianSbpt.dao.shiYsJyjc.impl;

import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.dao.shiYsJyjc.IBizShiYsJyjcDao;
import com.yongjie.ZhiJianSbpt.model.AccountSbpt;
import com.yongjie.ZhiJianSbpt.model.JgObject;
import com.yongjie.ZhiJianSbpt.model.shiYsJyjc.BizShiYsJyjc;
import com.yongjie.ZhiJianSbpt.utilities.*;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

@Repository(IBizShiYsJyjcDao.SERVICE_NAME)
public class BizShiYsJyjcDao extends BaseDaoImpl<BizShiYsJyjc> implements IBizShiYsJyjcDao {

    @Override
    public BizShiYsJyjc getById(Long id) {
        return super.getById(id);
    }

    @Override
    public HashMap getShiYsJyjcSqsToList(String name, int pageIndex, int pageSize, String sortField, String sortOrder, String admin) {
        if (name == null)
            name = "";
        String sql = "select * from bizShiYsJyjc where czr=" + "'" + admin + "'";
        if (!StringUtil.isNullOrEmpty(name)) {
            sql += " and sysMc like '%" + name + "%'";
        }
        if (StringUtil.isNullOrEmpty(sortField) == false) {
            if ("desc".equals(sortOrder) == false)
                sortOrder = "asc";
            sql += " ORDER BY " + sortField + " " + sortOrder;
        } else {
            sql += " ORDER BY id DESC";
        }
        // addEntity()将object对象转化成映射类型
        List<BizShiYsJyjc> dataAll = getSession().createSQLQuery(sql).addEntity(BizShiYsJyjc.class).list();
        ArrayList data = new ArrayList();
        int start = pageIndex * pageSize, end = start + pageSize;
        for (int i = 0, l = dataAll.size(); i < l; i++)// 能获得数据
        {
            HashMap record = new HashMap();
            // 将对象转换成hashmap对象
            if (dataAll.get(i) == null)
                continue;
            // 将对象转换成hashMap
            record = HashmapAndEntityTransfer.entityTransferToHashmap(dataAll.get(i));
            if (start <= i && i < end) {
                data.add(record);
            }
        }
        HashMap result = new HashMap();
        result.put("data", data);
        result.put("total", dataAll.size());
        return result;
    }

    @Override
    public HashMap getShiYsJyjcSqsToList2(String name, int pageIndex, int pageSize, String sortField, String sortOrder, String admin, String flowId) {
        if (name == null)
            name = "";
        //String sql = "select * from bizShiYsJyjc where czr="+"'"+admin+"'";
        String sql = "select b.*,d.deptname from bizShiYsJyjc b left join dept d on b.SHRENAREAID = d.AREAID  where  b.czr=:admin ";
        if (!StringUtil.isNullOrEmpty(name)) {
            sql += " and b.sysMc like :name ";
            //			sql+=" and sysMc like '%"+name+"%'";
        }

        if (!StringUtil.isNullOrEmpty(flowId)) {
            if ("3".equals(flowId) || "71".equals(flowId)) {
                sql += " and (b.flowId = 71  or b.flowId = 3) ";
            } else {
                sql += " and b.flowId = " + Long.parseLong(flowId);
            }
        }
        if (StringUtil.isNullOrEmpty(sortField) == false) {
            if ("desc".equals(sortOrder) == false)
                sortOrder = "asc";
            sql += " ORDER BY  b." + sortField + " " + sortOrder;
        } else {
            sql += " ORDER BY b.id DESC";
        }
        SQLQuery query = getSession().createSQLQuery(sql.toString());
        query.setString("admin", admin);
        if (!StringUtil.isNullOrEmpty(name))
            query.setString("name", "%" + name + "%");

        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> list = query.list();
        HashMap result = new HashMap();
        result.put("data", list);
        result.put("total", list.size());
        return result;
    }

    @Override
    public HashMap getBizShiYsJyjcOrsqsId(long sqsId) {
        String sql = "select * from bizShiYsJyjc where id=" + sqsId;
        List dataAll = getSession().createSQLQuery(sql).addEntity(BizShiYsJyjc.class).list();
        if (dataAll.size() > 0) {
            HashMap result = HashmapAndEntityTransfer.entityTransferToHashmap(dataAll.get(0));
            return result;
        }
        return new HashMap<>();
    }

    @Override
    public ArrayList getBizShiYsJyById(long sqsId) {
        // TODO Auto-generated method stub
        String sql = "select * from bizShiYsJyjc where id=" + sqsId;
        @SuppressWarnings({"unchecked", "deprecation"})
        Connection connection = getSession().connection();
        try {
            PreparedStatement pStatement = connection.prepareStatement(sql);
            ResultSet rset = pStatement.executeQuery();
            ArrayList list = JdbcManipulation.ResultSetToList(rset);
            return list;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return null;

    }

    @Override
    public int delBizShiYsJy(long id) {
        Connection conn = null;
        Statement stmt = null;
        try {
            String sql1 = "delete bizShiYsJyjc where id=" + id;
            String sql2 = "delete shiYsJyjc_Sqs where sqsId=" + id;
            String sql3 = "delete shiYsJyjc_Cd where sqsId=" + id;
            String sql4 = "delete shiYsJyjc_Ry where sqsId=" + id;
            String sql5 = "delete shiYsJyjc_Qzr where sqsId=" + id;
            String sql6 = "delete shiYsJyjc_SqZz where sqsId=" + id;
            String sql7 = "delete shiYsJyjc_Yqsb where sqsId=" + id;
            /*String sql8 = "delete shiYsJyjc_Frxzbg where sqsId=" + id;*/
            String sql9 = "delete shiYsJyjc_Jgrybg where sqsId=" + id;
            String sql10 = "delete shiYsJyjc_Jgrybg_Add where sqsId=" + id;
            String sql11 = "delete shiYsJyjc_Zzbzbg where sqsId=" + id;
            String sql12 = "delete shiYsJyjc_Zzbzbg_Add where sqsId=" + id;
            String sql13 = "delete shiYsJyjc_Zzmcbg where sqsId=" + id;
            String sql14 = "delete shiYsJyjc_QxRz where sqsId=" + id;
            String sql15 = "delete shiYsJyjc_FuJian where sqsId=" + id;
            String sql16 = "delete shiYsJyjc_Qxspnl where sqsId=" + id;
            conn = getSession().connection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.addBatch(sql1);
            stmt.addBatch(sql2);
            stmt.addBatch(sql3);
            stmt.addBatch(sql4);
            stmt.addBatch(sql5);
            stmt.addBatch(sql6);
            stmt.addBatch(sql7);
            /*stmt.addBatch(sql8);*/
            stmt.addBatch(sql9);
            stmt.addBatch(sql10);
            stmt.addBatch(sql11);
            stmt.addBatch(sql12);
            stmt.addBatch(sql13);
            stmt.addBatch(sql14);
            stmt.addBatch(sql15);
            stmt.addBatch(sql16);
            int[] a = stmt.executeBatch();
            conn.commit();
            //conn.setAutoCommit(true);
            return a[0];
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            if (stmt != null)
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }

        return 0;
    }

    @Override
    public int updateFlag(long id, String flag) {
        String sql = "update bizShiYsJyjc set flag='" + flag + "' ";
        sql += " where id =" + id;
        int result = getSession().createSQLQuery(sql).executeUpdate();
        return result;
    }

    @Override
    public ArrayList getShenHAreaId() {
        // TODO Auto-generated method stub
        String type = SysFinalRecource.USE_DB_NAME;
        String sql = "";
        sql = "select * from area where parentId=0 and areaName like '江西省%'";
        Connection con = getSession().connection();
        ArrayList list = JdbcManipulation.executeSqlQueryToList(con, sql);
        return list;
    }

    @Override
    public ArrayList getListByCzr(String czr) {
        String sql = "select * from bizShiYsJyjc where czr=" + "'" + czr + "'";
        Connection con = getSession().connection();
        ArrayList list = JdbcManipulation.executeSqlQueryToList(con, sql);
        return list;
    }

    @Override
    public ArrayList getListByCzr2(String czr, String flowId) {
        String sql = "select * from bizShiYsJyjc where czr=" + "'" + czr + "' and flowId = " + Long.parseLong(flowId);
        Connection con = getSession().connection();
        ArrayList list = JdbcManipulation.executeSqlQueryToList(con, sql);
        return list;
    }

    @Override
    public ArrayList getShiysjyjcPremitByZsbh(String zsbh) {
        // TODO Auto-generated method stub
        String sql = "select * from SHIYSJYJC_PERMIT where  CMA='" + zsbh + "'";
        Connection conn = getSession().connection();
        return JdbcManipulation.executeSqlQueryToList(conn, sql);
    }

    @Override
    public ArrayList getShiysjyjcPremitByZsbh2(String zsbh) {
        String sql = "select * from SHIYSJYJC_PERMIT where  CAL = '" + zsbh + "'";
        Connection conn = getSession().connection();
        return JdbcManipulation.executeSqlQueryToList(conn, sql);
    }

    @Override
    public ArrayList getListCanSb(String czr, String flowId, String cmaSqlx) {
        String sql = "";
        if ("CMA+验收".equals(cmaSqlx) || "CMA+授权".equals(cmaSqlx)) {
            sql = "select * from bizShiYsJyjc where czr=" + "'" + czr + "' and flowId = '" + Long.parseLong(flowId) + "' and (CMASQLX like '%授权%' or CMASQLX like '%CMA%' or CMASQLX like '%验收%') and flag  not in (0,3,4,6)";
        } else if ("CAL验收".equals(cmaSqlx) || "CAL授权".equals(cmaSqlx)) {
            sql = "select * from bizShiYsJyjc where czr=" + "'" + czr + "' and flowId = '" + Long.parseLong(flowId) + "' and (CMASQLX like '%授权%' or CMASQLX like '%CMA+%' or CMASQLX like '%验收%') and flag  not in (0,3,4,6)";
        } else {
            sql = "select * from bizShiYsJyjc where czr=" + "'" + czr + "' and flowId = '" + Long.parseLong(flowId) + "' and (CMASQLX like '%" + cmaSqlx + "%' or CMASQLX like '%CMA+%') and flag  not in (0,3,4,6)";
        }
        Connection con = getSession().connection();
        ArrayList list = JdbcManipulation.executeSqlQueryToList(con, sql);
        return list;
    }

    @Override
    public ArrayList getListCanSb(String czr, String flowId, String cmaSqlx, String sqsId) {
        String sql = "";
        if ("计量认证+验收".equals(cmaSqlx) || "计量认证+授权".equals(cmaSqlx)) {
            if (!StringUtil.isNullOrEmptyZf(flowId)) {
                sql = "select * from bizShiYsJyjc where czr=" + "'" + czr + "' and flowId = '" + Long.parseLong(flowId) + "' and (CMASQLX like '%授权%' or CMASQLX like '%计量认证%' or CMASQLX like '%验收%') and flag not in (0,6) and id <>" + Long.parseLong(sqsId);
            } else {
                sql = "select * from bizShiYsJyjc where czr=" + "'" + czr + "' and (CMASQLX like '%授权%' or CMASQLX like '%计量认证%' or CMASQLX like '%验收%') and flag not in (0,6) and id <>" + Long.parseLong(sqsId);
            }
        } else if ("验收".equals(cmaSqlx) || "授权".equals(cmaSqlx)) {
            if (!StringUtil.isNullOrEmptyZf(flowId)) {
                sql = "select * from bizShiYsJyjc where czr=" + "'" + czr + "' and flowId = '" + Long.parseLong(flowId) + "' and (CMASQLX like '%授权%' or CMASQLX like '%计量认证+%' or CMASQLX like '%验收%') and flag not in (0,6) and id <>" + Long.parseLong(sqsId);
            } else {
                sql = "select * from bizShiYsJyjc where czr=" + "'" + czr + "' and (CMASQLX like '%授权%' or CMASQLX like '%计量认证+%' or CMASQLX like '%验收%') and flag not in (0,6) and id <>" + Long.parseLong(sqsId);
            }
        } else {
            if (!StringUtil.isNullOrEmptyZf(flowId)) {
                sql = "select * from bizShiYsJyjc where czr=" + "'" + czr + "' and flowId = '" + Long.parseLong(flowId) + "' and CMASQLX like '%" + cmaSqlx + "%' and flag not in (0,6) and id <>" + Long.parseLong(sqsId);
            } else {
                sql = "select * from bizShiYsJyjc where czr=" + "'" + czr + "' and CMASQLX like '%" + cmaSqlx + "%' and flag not in (0,6) and id <>" + Long.parseLong(sqsId);
            }
        }
        Connection con = getSession().connection();
        ArrayList list = JdbcManipulation.executeSqlQueryToList(con, sql);
        return list;
    }

    @Override
    public void addZzps(long sqsId, long flowId, long flowInstId, String nodeId, String zzrdName, String zzxkdate,
                        String zsNumber, String yxDate, String reason, String lxr, String lxdh, String txaddress, String faxx) {
        // TODO Auto-generated method stub
        String sql = "insert into SHIYSJYJC_ZZXKXX(ID,SQSID,FLOWID,FLOWINSTID,NODEID,ZZRDNAME,ZZXKDATE,ZSBUMBER,YXDATE,REASON,LXR,LXDH,TXADDRESS,FAXX) "
                + "values ("
                + "SEQ_SHIYSJYJC_ZZXKXX.nextval,"
                + "" + sqsId + ","
                + "" + flowId + ","
                + "" + flowInstId + ","
                + "'" + nodeId + "',"
                + "'" + zzrdName + "',"
                + "to_date('" + zzxkdate + "','yyyy-MM-dd HH24:mi:ss'),"
                + "'" + zsNumber + "',"
                + "to_date('" + yxDate + "','yyyy-MM-dd HH24:mi:ss'),"
                + "?,"
                //+ "'"+reason+"',"
                + "'" + lxr + "',"
                + "'" + lxdh + "',"
                + "'" + txaddress + "',"
                + "'" + faxx + "')";

        /*
         * Connection conn = getSession().connection();
         * JdbcManipulation.executeSqlUpdate(conn, sql);
         */
        PreparedStatement pst = null;
        try {
            Connection connection2 = getSession().connection();
            pst = connection2.prepareStatement(sql);
            pst.setString(1, reason);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updataZzps(String id, long sqsId, long flowId, long flowInstId, String nodeId, String zzrdName,
                           String zzxkdate, String zsNumber, String yxDate, String reason, String lxr, String lxdh, String txaddress,
                           String faxx) {
        // TODO Auto-generated method stub
        String sql = "update SHIYSJYJC_ZZXKXX  set sqsId=:sqsId,flowId=:flowId,flowInstId=:flowInstId,nodeId=:nodeId,zzrdName=:zzrdName,"
                + "zsBumber=:zsNumber,";
        if (!"".equals(yxDate)) {
            sql += "zzxkdate=:zzxkdate,";
        }
        if (!"".equals(zzxkdate)) {
            sql += "yxDate=:yxDate,";
        }
        sql += "reason=:reason,lxr=:lxr,lxdh=:lxdh,txaddress=:txaddress,faxx=:faxx where id=:id";
        SQLQuery query = getSession().createSQLQuery(sql);
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        query.setLong("sqsId", sqsId);
        query.setLong("flowId", flowId);
        query.setLong("flowInstId", flowInstId);
        query.setString("nodeId", nodeId);
        query.setString("zzrdName", zzrdName);
        query.setString("zsNumber", zsNumber);
        query.setString("reason", reason);
        query.setString("lxr", lxr);
        query.setString("lxdh", lxdh);
        query.setString("txaddress", txaddress);
        query.setString("faxx", faxx);
        query.setLong("id", Long.parseLong(id));

        Date date1 = new Date();
        Date date2 = new Date();
        try {
            if (!"".equals(yxDate)) {
                date2 = format1.parse(yxDate);
                query.setDate("yxDate", date2);
            }
            if (!"".equals(zzxkdate)) {
                date1 = format1.parse(zzxkdate);
                query.setDate("zzxkdate", date1);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        query.executeUpdate();

    }

    @Override
    public ArrayList getzzpsSelect(long sqsId, long flowId, long flowInstId) {
        // TODO Auto-generated method stub
        String sql = "select ID,FLOWINSTID,FLOWID,SQSID,LXR,REASON,LXDH,NODEID,CZR,to_char(ZZXKDATE,'yyyy-MM-dd HH:mi:ss') as ZZXKDATE,ZSBUMBER,FILEPATH,to_char(CZDATE,'yyyy-MM-dd HH:mi:ss') as CZDATE,to_char(YXDATE,'yyyy-MM-dd HH:mi:ss') as YXDATE,ZZRDNAME,TXADDRESS,FAXX from SHIYSJYJC_ZZXKXX where sqsId=" + sqsId + " and flowId=" + flowId + " and flowInstId=+" + flowInstId;
        Connection con = getSession().connection();
        ArrayList list = JdbcManipulation.executeSqlQueryToList(con, sql);
        return list;
    }

    @Override
    public HashMap<String, Object> getQyCxList(String jgflIds, String shxydm, int pageIndex, int pageSize,
                                               String sortField, String sortOrder) {
        String sql = "select distinct j.*,d.JGCLASSIFYID from JGOBJECT j "
                + "inner join JGOBJECT_JGFL d on j.newshxydm=d.shxydm and j.isdel=0 "
                + "inner join SHIYSJYJC_PERMIT p on j.newshxydm=p.newshxydm "
                + "and  p.id in (select max(id) from SHIYSJYJC_PERMIT  where  newshxydm=j.newshxydm   group by  newshxydm ) "
                + "where 1=1 ";
        if (!StringUtil.isNullOrEmpty(jgflIds) && jgflIds != "null") {
            sql += " and d.JGCLASSIFYID in (" + jgflIds + ")";
        }
        if (!StringUtil.isNullOrEmpty(shxydm) && shxydm != "null") {
            sql += " and j.newshxydm ='" + shxydm + "'";
        }

        if (StringUtil.isNullOrEmpty(sortField) == false) {
            if ("desc".equals(sortOrder) == false)
                sortOrder = "asc";
            sql += " ORDER BY " + sortField + "  " + sortOrder;
        } else {
            sql += " ORDER BY j.ID ASC";
        }
        Connection con = getSession().connection();
        ArrayList list = JdbcManipulation.executeSqlQueryToList(con, sql);
        ArrayList data = new ArrayList();
        int start = pageIndex * pageSize, end = start + pageSize;
        for (int i = 0, l = list.size(); i < l; i++) {
            HashMap record = (HashMap) list.get(i);
            if (record == null) {
                continue;
            }
            if (start <= i && i < end) {
                data.add(record);
            }
        }
        HashMap result = new HashMap();
        result.put("data", data);
        result.put("total", list.size());
        return result;
    }

    @Override
    public JgObject getJgObjectnewShxydm(String newShxydm, String isdel) {
        String sql = "select * from JGOBJECT where  NEWSHXYDM=:newShxydm ";
        if (!StringUtil.isNullOrEmptyZf(isdel))
            sql += " and isdel in(:isdel)";
        SQLQuery query = getSession().createSQLQuery(sql);
        query.setString("newShxydm", newShxydm);
        if (!StringUtil.isNullOrEmptyZf(isdel))
            query.setString("isdel", isdel);
        query.addEntity(JgObject.class);
        List<JgObject> list = query.list();
        if (list.size() > 0)
            return list.get(0);
        return null;
    }

    @Override
    public List<Object> getSqsxxByShxydm(String newShxydm, String jgstate) {
        String sql = "select * from SHIYSJYJC_SQS where newshxydm=:newShxydm ";
        if (!StringUtil.isNullOrEmpty(jgstate) && !jgstate.equals("null"))
            sql += " and jgstate=:jgstate ";
        SQLQuery query = getSession().createSQLQuery(sql);
        query.setString("newShxydm", newShxydm);
        if (!StringUtil.isNullOrEmptyZf(jgstate))
            query.setInteger("jgstate", Integer.parseInt(jgstate));
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.list();
    }

    @Override
    public List<Object> getCd(String newShxydm, String type) {
        String sql = "select ID as CDID,JGCLASSID,SHXYDM,CDMC,TYPE,CDDZ from JGOBJECT_JYJCCD where 1=1 ";
        if (!StringUtil.isNullOrEmpty(newShxydm))
            sql += " and shxydm=:newShxydm ";
        if (!StringUtil.isNullOrEmpty(type))
            sql += " and type=:type ";
        SQLQuery query = getSession().createSQLQuery(sql);
        if (!StringUtil.isNullOrEmptyZf(newShxydm))
            query.setString("newShxydm", newShxydm);
        if (!StringUtil.isNullOrEmptyZf(type))
            query.setInteger("type", Integer.parseInt(type));
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.list();
    }

    @Override
    public List<Object> getZsxx(String newShxydm, String sortField, String sortOrder, Integer pageIndex,
                                Integer pageSize) {
        // TODO Auto-generated method stub
        String sql = "select * from ShiYsJyjc_Permit where newShxydm=:newShxydm";
        sql += " order by fzrq  desc ";
        Query query = getSession().createSQLQuery(sql);
        query.setString("newShxydm", newShxydm);
        if (pageIndex != null && pageSize != null) {
            query.setFirstResult(pageIndex);
            query.setMaxResults(pageSize);
        }
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.list();
    }

    @Override
    public HashMap getAllJyjcZyTypes(String key) {
        if (key.equals("null")) {
            key = "";
        }
        String sql = "select * from ShiYsJyjc_ZyType where typeName  like '%" + key + "%'  ORDER BY paiXu,id ";
        Connection connection = getSession().connection();
        ArrayList dataAll = JdbcManipulation.executeSqlQueryToList(connection, sql);
        ArrayList data = new ArrayList();
        for (int i = 0, l = dataAll.size(); i < l; i++) {
            HashMap record = new HashMap();
            if (dataAll.get(i) == null)
                continue;
            record = (HashMap) dataAll.get(i);
            data.add(record);
        }
        HashMap result = new HashMap();
        result.put("data", data);
        result.put("total", dataAll.size());
        return result;
    }

    @Override
    public HashMap getJgObjectJyjcjgRyList(String nameFilter, int pageIndex, int pageSize, String sortField,
                                           String sortOrder, long jgflId, String shxydm, String jgstate, String sqsId) {
        if (nameFilter == null)
            nameFilter = "";
        String sql = "select * FROM JGOBJECT_JYJCJGRY WHERE 1=1 ";
        if (!StringUtil.isNullOrEmpty(shxydm) && !shxydm.equals("null")) {
            sql += " and ZZJGDM='" + shxydm + "'";
        }
        if (!StringUtil.isNullOrEmpty(nameFilter) && !nameFilter.equals("null")) {
            sql += " and name like '%" + nameFilter + "%'";
        }
        if (!StringUtil.isNullOrEmpty(jgstate) && !jgstate.equals("null")) {
            sql += " and jgstate=" + jgstate;
        }
        if (!StringUtil.isNullOrEmpty(sqsId) && !sqsId.equals("null")) {
            sql += " and sqsId=" + sqsId;
        }
        if (StringUtil.isNullOrEmpty(sortField) == false) {
            if ("desc".equals(sortOrder) == false)
                sortOrder = "asc";
            sql += " ORDER BY " + sortField + ",id " + sortOrder;
        } else {
            sql += " ORDER BY id DESC";
        }
        Connection conn = getSession().connection();
        ArrayList dataAll = JdbcManipulation.executeSqlQueryToList(conn, sql);
        ArrayList data = new ArrayList();
        int start = pageIndex * pageSize, end = start + pageSize;
        for (int i = 0, l = dataAll.size(); i < l; i++)// 能获得数据
        {
            HashMap record = new HashMap();
            // 将对象转换成hashmap对象
            if (dataAll.get(i) == null)
                continue;
            // 将对象转换成hashMap
            record = (HashMap) dataAll.get(i);
            // 获取需要返回的当前页记录
            if (start <= i && i < end) {// 前台显示 现在不要分页
                data.add(record);
            }
        }
        HashMap result = new HashMap();
        result.put("data", data);
        result.put("total", dataAll.size());
        return result;
    }

    @Override
    public HashMap getJgObjectQzrList(String nameFilter, int pageIndex, int pageSize, String sortField,
                                      String sortOrder, long jgflId, String shxydm, String jgstate, String sqsId) {
        if (nameFilter == null)
            nameFilter = "";

        String sql = "select * FROM JGOBJECT_JYJCQZR WHERE (QZRNAME like '%" + nameFilter + "%' )"
                /* + " and JGCLASSIFYID="+jgflId */;
        if (!StringUtil.isNullOrEmpty(shxydm) && !shxydm.equals("null")) {
            sql += " and ZZJGDM='" + shxydm + "'";
        }
        if (!StringUtil.isNullOrEmpty(jgstate) && !jgstate.equals("null")) {
            sql += " and jgstate=" + jgstate;
        }
        if (!StringUtil.isNullOrEmpty(sqsId) && !sqsId.equals("null")) {
            sql += " and sqsId=" + sqsId;
        }
        if (StringUtil.isNullOrEmpty(sortField) == false) {
            if ("desc".equals(sortOrder) == false)
                sortOrder = "asc";
            sql += " ORDER BY " + sortField + ",id " + sortOrder;
        } else {
            sql += " ORDER BY id DESC";
        }
        Connection conn = getSession().connection();
        ArrayList dataAll = JdbcManipulation.executeSqlQueryToList(conn, sql);
        ArrayList data = new ArrayList();
        int start = pageIndex * pageSize, end = start + pageSize;
        for (int i = 0, l = dataAll.size(); i < l; i++)// 能获得数据
        {
            HashMap record = new HashMap();
            // 将对象转换成hashmap对象
            if (dataAll.get(i) == null)
                continue;
            // 将对象转换成hashMap
            record = (HashMap) dataAll.get(i);
            // 获取需要返回的当前页记录
            if (start <= i && i < end) {// 现在不要分页
                data.add(record);
            }
        }
        HashMap result = new HashMap();
        result.put("data", data);
        result.put("total", dataAll.size());
        return result;
    }

    @Override
    public HashMap getJgObjectJyjcJynlList2(int pageIndex, int pageSize, String sortField, String sortOrder,
                                            long jgflId, String shxydm, String jgstate, String sqsId, String dizhi, String sporfspid, String leib,
                                            String cpmc, String cmaorcal) {
        String sql = "select ID,DXUHAO,LEIB,CPXH,CPMC,YJBZMC,XZFW,SM,BEIZHU,DIZHI FROM JGOBJECT_JYJCNL "
                + " where 1=1 and ((DXUHAO IS NOT NULL AND length(trim(DXUHAO))>0) OR (LEIB IS NOT NULL AND length(trim(LEIB))>0) "
                + " OR (CPXH IS NOT NULL AND length(trim(CPXH))>0) OR (CPMC IS NOT NULL AND length(trim(CPMC))>0) OR (YJBZMC IS NOT NULL AND length(trim(YJBZMC))>0) "
                + " OR (XZFW IS NOT NULL AND length(trim(XZFW))>0)) ";
        if (jgflId > 0) {
            sql += " and JGCLASSIFYID=" + jgflId;
        }
        if (!StringUtil.isNullOrEmpty(shxydm) && !shxydm.equals("null")) {
            sql += " and ZZJGDM='" + shxydm + "'";
        }

        if (!StringUtil.isNullOrEmpty(jgstate) && !jgstate.equals("null")) {
            sql += " and jgstate=" + jgstate;
        }
        if (!StringUtil.isNullOrEmpty(sqsId) && !sqsId.equals("null")) {
            sql += " and sqsId=" + sqsId;
        }
        if (!StringUtil.isNullOrEmpty(dizhi) && !dizhi.equals("null")) {
            sql += " and dizhi='" + dizhi + "'";
        }
        if (!StringUtil.isNullOrEmpty(sporfspid) && !sporfspid.equals("null")) {
            sql += " and type='" + sporfspid + "'";
        }

        if (!StringUtil.isNullOrEmpty(leib) && !leib.equals("null")) {
            sql += " and LEIB like '%" + leib + "%'  ";
        }
        if (!StringUtil.isNullOrEmpty(cpmc) && !cpmc.equals("null")) {
            sql += " and  CPMC like '%" + cpmc + "%'  ";
        }
        if (!StringUtil.isNullOrEmpty(cmaorcal) && !cmaorcal.equals("null")) {
            sql += " and  CALORCMA like '%" + cmaorcal + "%'  ";
        }

        if (StringUtil.isNullOrEmpty(sortField) == false) {
            if ("desc".equals(sortOrder) == false)
                sortOrder = "asc";
            sql += " ORDER BY " + sortField + ",id " + sortOrder;
        } else {
            sql += " ORDER BY id ";
        }
        Connection conn = getSession().connection();
        ArrayList dataAll = JdbcManipulation.executeSqlQueryToList(conn, sql);
        ArrayList data = new ArrayList();
        int start = pageIndex * pageSize, end = start + pageSize;
        for (int i = 0, l = dataAll.size(); i < l; i++)// 能获得数据
        {
            HashMap record = new HashMap();
            // 将对象转换成hashmap对象
            if (dataAll.get(i) == null)
                continue;
            // 将对象转换成hashMap
            record = (HashMap) dataAll.get(i);
            // 获取需要返回的当前页记录
            if (start <= i && i < end) {// 现在需要分页
                data.add(record);
            }
        }
        HashMap result = new HashMap();
        result.put("data", data);
        result.put("total", dataAll.size());
        return result;
    }

    @Override
    public ArrayList getZsxxList2(String newShxydm) {
        String sql = "select * from SHIYSJYJC_PERMIT where newshxydm=:newShxydm order by fzrq desc,id desc";
        Query query = super.getSession().createSQLQuery(sql);
        query.setString("newShxydm", newShxydm);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = query.list();
        return (ArrayList) list;
    }

    @Override
    public ArrayList getJdcXx(String sqsId, String zzjgdm, String jgState) {
        String sql = "select * from  SHIYSJYJC_JDCXX where 1=1 ";
        if (!StringUtil.isNullOrEmptyZf(sqsId)) {
            sql += " and sqsid=" + sqsId;
        }
        if (!StringUtil.isNullOrEmptyZf(zzjgdm)) {
            sql += " and ZZJGDM='" + zzjgdm + "'";
        }
        if (!StringUtil.isNullOrEmptyZf(jgState)) {
            sql += " and jgState=" + jgState;
        }
        Connection conn = getSession().connection();
        return JdbcManipulation.executeSqlQueryToList(conn, sql);
    }

    @Override
    public List<Number> getNullYqsbCount(long jgflId, String shxydm, String jgstate, String sqsId) {
        String sql = "select count(*) from jgobject_jyjcnl where 1=1 ";

        if (jgflId > 0) {
            sql += " and JGCLASSIFYID=" + jgflId;
        }
        if (!StringUtil.isNullOrEmpty(shxydm) && !shxydm.equals("null")) {
            sql += " and ZZJGDM='" + shxydm + "'";
        }

        if (!StringUtil.isNullOrEmpty(jgstate) && !jgstate.equals("null")) {
            sql += " and jgstate=" + jgstate;
        }
        if (!StringUtil.isNullOrEmpty(sqsId) && !sqsId.equals("null")) {
            sql += " and sqsId=" + sqsId;
        }

        sql += "union select count(*) from jgobject_jyjcnl where "
                + " yqsbmc is null and yqsbxh is  null and yqsbclfw is  null "
                + " and syfs is  null and yxrq is  null and qrjg is  null ";

        if (jgflId > 0) {
            sql += " and JGCLASSIFYID=" + jgflId;
        }
        if (!StringUtil.isNullOrEmpty(shxydm) && !shxydm.equals("null")) {
            sql += " and ZZJGDM='" + shxydm + "'";
        }

        if (!StringUtil.isNullOrEmpty(jgstate) && !jgstate.equals("null")) {
            sql += " and jgstate=" + jgstate;
        }
        if (!StringUtil.isNullOrEmpty(sqsId) && !sqsId.equals("null")) {
            sql += " and sqsId=" + sqsId;
        }
        SQLQuery query = getSession().createSQLQuery(sql);
        return query.list();
    }

    @Override
    public HashMap getJgobjectYqsbXxListFromNl(int pageIndex, int pageSize, String sortField, String sortOrder,
                                               long jgflId, String shxydm, String jgstate, String sqsId, String dizhi, String sporfspid) {
        String sql = "select ID,DXUHAO,LEIB,CPXH as XUHAO,CPMC,YJBZMC,YQSBMC,YQSBXH,YQSBCLFW,SYFS,YXRQ as YXJZRQ,QRJG FROM JGOBJECT_JYJCNL "
                + "where 1=1 ";// 若是不加这些限制条件，则会显示出检验检测能力没有数据但是在一起设备中有数据。
        if (jgflId > 0) {
            sql += " and JGCLASSIFYID=" + jgflId;
        }
        if (!StringUtil.isNullOrEmpty(shxydm) && !shxydm.equals("null")) {
            sql += " and ZZJGDM='" + shxydm + "'";
        }

        if (!StringUtil.isNullOrEmpty(jgstate) && !jgstate.equals("null")) {
            sql += " and jgstate=" + jgstate;
        }
        if (!StringUtil.isNullOrEmpty(sqsId) && !sqsId.equals("null")) {
            sql += " and sqsId=" + sqsId;
        }
        if (!StringUtil.isNullOrEmpty(dizhi) && !dizhi.equals("null")) {
            sql += " and dizhi='" + dizhi + "'";
        }
        if (!StringUtil.isNullOrEmpty(sporfspid) && !sporfspid.equals("null")) {
            sql += " and type='" + sporfspid + "'";
        }
        if (StringUtil.isNullOrEmpty(sortField) == false) {
            if ("desc".equals(sortOrder) == false)
                sortOrder = "asc";
            sql += " ORDER BY " + sortField + ",id " + sortOrder;
        } else {
            sql += " ORDER BY id ";
        }
        /*
         * Connection conn=getSession().connection(); ArrayList dataAll =
         * JdbcManipulation.executeSqlQueryToList(conn, sql);
         */
        SQLQuery query = getSession().createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List dataAll = query.list();
        ArrayList data = new ArrayList();
        int start = pageIndex * pageSize, end = start + pageSize;
        for (int i = 0, l = dataAll.size(); i < l; i++)// 能获得数据
        {
            HashMap record = new HashMap();
            // 将对象转换成hashmap对象
            if (dataAll.get(i) == null)
                continue;
            // 将对象转换成hashMap
            record = (HashMap) dataAll.get(i);
            // 获取需要返回的当前页记录
            if (start <= i && i < end) {// 现在需要分页
                data.add(record);
            }
        }
        HashMap result = new HashMap();
        result.put("data", data);
        result.put("total", dataAll.size());
        return result;
    }

    @Override
    public HashMap getJgObjectYqsbList(int pageIndex, int pageSize, String sortField, String sortOrder, long jgflId,
                                       String shxydm, String jgstate, String sqsId, String dizhi, String sporfspid) {
        String sql = "select * FROM JGOBJECT_JYJCSB  where 1=1";

        if (jgflId > 0) {
            sql += " and JGCLASSIFYID=" + jgflId;
        }
        if (!StringUtil.isNullOrEmpty(shxydm) && !shxydm.equals("null")) {
            sql += " and ZZJGDM='" + shxydm + "'";
        }
        if (!StringUtil.isNullOrEmpty(jgstate) && !jgstate.equals("null")) {
            sql += " and jgstate=" + jgstate;
        }
        if (!StringUtil.isNullOrEmpty(sqsId) && !sqsId.equals("null")) {
            sql += " and sqsId=" + sqsId;
        }
        if (!StringUtil.isNullOrEmpty(dizhi) && !dizhi.equals("null")) {
            sql += " and cddz='" + dizhi + "'";
        }
        if (!StringUtil.isNullOrEmpty(sporfspid) && !sporfspid.equals("null")) {
            sql += " and type='" + sporfspid + "'";
        }
        if (StringUtil.isNullOrEmpty(sortField) == false) {
            if ("desc".equals(sortOrder) == false)
                sortOrder = "asc";
            sql += " ORDER BY " + sortField + ",id " + sortOrder;
        } else {
            sql += " ORDER BY id ";
        }
        Connection conn = getSession().connection();
        ArrayList dataAll = JdbcManipulation.executeSqlQueryToList(conn, sql);
        ArrayList data = new ArrayList();
        int start = pageIndex * pageSize, end = start + pageSize;
        for (int i = 0, l = dataAll.size(); i < l; i++)// 能获得数据
        {
            HashMap record = new HashMap();
            // 将对象转换成hashmap对象
            if (dataAll.get(i) == null)
                continue;
            // 将对象转换成hashMap
            record = (HashMap) dataAll.get(i);
            // 获取需要返回的当前页记录
            if (start <= i && i < end) {// 现在需要分页
                data.add(record);
            }
        }
        HashMap result = new HashMap();
        result.put("data", data);
        result.put("total", dataAll.size());
        return result;
    }

    @Override
    public HashMap getJgObjectFuJianList(String nameFilter, int pageIndex, int pageSize, String sortField,
                                         String sortOrder, long jgflId, String shxydm, String propertyId) {
        if (nameFilter == null)
            nameFilter = "";

        String sql = "select * FROM JGOBJECT_FUJIAN WHERE (FILETITLE like '%" + nameFilter + "%')"
                + " and JGCLASSIFYID=" + jgflId;
        if (!StringUtil.isNullOrEmpty(shxydm) && !shxydm.equals("null")) {
            sql += " and SHXYDM='" + shxydm + "'";
        }
        if (!StringUtil.isNullOrEmpty(propertyId) && !propertyId.equals("null")) {
            sql += " and PROPERTYID='" + propertyId + "'";
        }
        if (StringUtil.isNullOrEmpty(sortField) == false) {
            if ("desc".equals(sortOrder) == false)
                sortOrder = "asc";
            sql += " ORDER BY " + sortField + ",id " + sortOrder;
        } else {
            sql += " ORDER BY id DESC";
        }
        Connection conn = getSession().connection();
        ArrayList dataAll = JdbcManipulation.executeSqlQueryToList(conn, sql);
        ArrayList data = new ArrayList();
        int start = pageIndex * pageSize, end = start + pageSize;
        for (int i = 0, l = dataAll.size(); i < l; i++)// 能获得数据
        {
            HashMap record = new HashMap();
            // 将对象转换成hashmap对象
            if (dataAll.get(i) == null)
                continue;
            // 将对象转换成hashMap
            record = (HashMap) dataAll.get(i);
            // 获取需要返回的当前页记录
            if (start <= i && i < end) {
                data.add(record);
            }
        }
        HashMap result = new HashMap();
        result.put("data", data);
        result.put("total", dataAll.size());
        return result;
    }

    @Override
    public ArrayList getObjectCd1(String shxydm, String type) {
        String sql = "select * from JGOBJECT_JYJCCD where shxydm=:shxydm and type=:type";
        SQLQuery query = getSession().createSQLQuery(sql);
        query.setString("shxydm", shxydm);
        query.setInteger("type", Integer.parseInt(type));
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return (ArrayList) query.list();
    }

    @Override
    public ArrayList getPsxxListBySqsId(String sqsId, String flowId, String flowInstId) {
        // TODO Auto-generated method stub
        String sql = "select * from JGOBJECT_JYJCLCPSXX where 1=1 ";
        if (!StringUtil.isNullOrEmpty(sqsId) && sqsId.equals("null") == false) {
            sql += " and sqsId=" + sqsId;
        }
        if (!StringUtil.isNullOrEmpty(flowId) && flowId.equals("null") == false) {
            sql += " and flowId=" + flowId;
        }
        if (!StringUtil.isNullOrEmpty(flowInstId) && flowInstId.equals("null") == false) {
            sql += " and flowInstId=" + flowInstId;
        }
        Connection conn = getSession().connection();
        return JdbcManipulation.executeSqlQueryToList(conn, sql);
    }

    @Override
    public ArrayList getPsry(String flowId, String flowInstId) {
        // TODO Auto-generated method stub
        String sql = "select * from SHIYSJYJC_XCHC where 1=1 ";
        if (!StringUtil.isNullOrEmpty(flowId) && flowId.equals("null") == false) {
            sql += " and flowId=" + flowId;
        }
        if (!StringUtil.isNullOrEmpty(flowInstId) && flowInstId.equals("null") == false) {
            sql += " and flowInstId=" + flowInstId;
        }
        sql += " and ISHEADER in(0,1,3)";
        Connection conn = getSession().connection();
        return JdbcManipulation.executeSqlQueryToList(conn, sql);
    }
}
