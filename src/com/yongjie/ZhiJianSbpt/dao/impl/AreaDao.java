package com.yongjie.ZhiJianSbpt.dao.impl;

import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.dao.IAreaDao;
import com.yongjie.ZhiJianSbpt.model.Area;
import com.yongjie.ZhiJianSbpt.utilities.*;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(IAreaDao.SERVICE_NAME)
public class AreaDao extends BaseDaoImpl<Area> implements IAreaDao {
    /**
     * 获取区域列表
     */
    @Override
    public HashMap getArea(String key, int pageIndex, int pageSize, String sortField, String sortOrder, String pId, String areaId) throws Exception {
        // TODO Auto-generated method stub
        if (key == null)
            key = "";

        String sql = "FROM Area a" + " WHERE 1=1 and (a.areaName like '%" + key + "%' or a.areaNumber like '%" + key
                + "%' or a.abbreviation like '%" + key + "%')";
        if (!StringUtil.isNullOrEmpty(pId)) {
            sql += " and a.parentId=" + pId;
        }
        if (StringUtil.isNullOrEmpty(sortField) == false) {
            if ("desc".equals(sortOrder) == false)
                sortOrder = "asc";
            sql += " ORDER BY " + sortField + ",id " + sortOrder;
        } else {
            sql += " ORDER BY id DESC";
        }
        List<Area> dataAll = getSession().createQuery(sql).setFirstResult(0).setMaxResults(10000).list();// 取10000条数据
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

    /**
     * 获取区域树型
     */
    @Override
    public HashMap getTree(String areaId, String sortField, String sortOrder, String isEnabled) throws Exception {
        HashMap resultMap = new HashMap();
        String sql = "SELECT * FROM AREA WHERE 1=1 ";
        if (!StringUtil.isNullOrEmpty(areaId)) {
            sql += "and (id=" + areaId + " or parentPath like '%," + areaId + ",%' or  parentPath like '%," + areaId
                    + "')";
        }
        if (StringUtil.isNullOrEmpty(isEnabled) == false) {
            sql += " and isEnabled=0";
        }
        if (StringUtil.isNullOrEmpty(sortField) == false) {
            if ("desc".equals(sortOrder) == false)
                sortOrder = "asc";
            sql += " ORDER BY " + sortField + ",id " + sortOrder;
        } else {
            sql += " ORDER BY id DESC";
        }

        SQLQuery sqlQuery = getSession().createSQLQuery(sql);
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        resultMap.put(R.DATA_NAME, list);
        return resultMap;
    }

    /**
     * 根据areaId获取集合
     */
    @Override
    public List<Area> getData(long areaId) throws Exception {
        String sql = "FROM Area a" + " WHERE a.id= " + areaId;
        List<Area> data = getSession().createQuery(sql).list();
        return data;
    }

    /**
     * 根据areaId启用
     */
    @Override
    public int activeArea(long areaId) throws Exception {
        // TODO Auto-generated method stub
        String sql = "update Area area set area.isEnabled=0 where area.id=" + areaId
                + " or ','+area.parentPath+',' like '%," + areaId + ",%'";
        int ret = getSession().createQuery(sql).executeUpdate();
        return ret;
    }

    /**
     * 根据areaId禁用
     */
    @Override
    public int forbidArea(long areaId) throws Exception {
        // TODO Auto-generated method stub
        String sql = "update Area area set area.isEnabled=1 where area.id=" + areaId
                + " or ','+area.parentPath+',' like '%," + areaId + ",%'";
        return getSession().createQuery(sql).executeUpdate();
    }

    /**
     * 根据区域名称和区域代码判重
     */
    @Override
    public Boolean checkAreaNumber(String areaName, String areaNumber, String idStr) throws Exception {
        // TODO Auto-generated method stub
        String sql = "FROM Area a  WHERE 1=1";
        long id = 0;
        if (idStr != null && idStr != "") {
            id = Integer.parseInt(idStr);
        }
        int i = 0;
        if (!StringUtil.isNullOrEmpty(areaName) && !StringUtil.isNullOrEmpty(areaNumber)) {
            sql += " and a.areaName='" + areaName + "' and a.areaNumber ='" + areaNumber + "' and a.id<>" + id;
            List<Area> objList = getSession().createQuery(sql).list();
            for (Area o : objList) {
                i++;
            }
        }
        return i > 0;
    }

    @Override
    public String getAreaFormUser(String userName) throws Exception {
        // TODO Auto-generated method stub
        String sql = "select a.id from Area a inner join dept d on a.id=d.areaId inner join accountXzsp acc on d.id=acc.deptid "
                + " where acc.username=:userName";
        String areaId = getSession().createSQLQuery(sql).setString("userName", userName).uniqueResult() + "";
        return areaId;
    }

    @Override
    public ArrayList getAreaListByAreaId(long areaId) throws Exception {
        // TODO Auto-generated method stub
        String sql = "select new Area(a.id, a.areaName) From Area a where a.id=? or a.parentId=?";
        @SuppressWarnings("unchecked")
        List<Area> dataAll = getSession().createQuery(sql).setParameter(0, areaId).setParameter(1, areaId).list();
        ArrayList data = new ArrayList();
        for (int i = 0, l = dataAll.size(); i < l; i++)// 能获得数据
        {
            HashMap record = new HashMap();
            // 将对象转换成hashmap对象
            if (dataAll.get(i) == null)
                continue;
            // 将对象转换成hashMap
            record = HashmapAndEntityTransfer.entityTransferToHashmap(dataAll.get(i));
            // 获取需要返回的当前页记录
            data.add(record);
        }
        return data;
    }

    @Override
    public String getAllAreaNameById(long areaid) throws Exception {
        String sql = "select listagg(areaname, '') within group(order by id) as areaname from (select * from area start with id=" + areaid + " connect by prior parentId=id)";
        Connection connection = getSession().connection();
        ArrayList list = JdbcManipulation.executeSqlQueryToList(connection, sql);
        HashMap map = (HashMap) list.get(0);

        return map.get("AREANAME").toString();
    }

    @Override
    public ArrayList getAreaList(String isEnabled, String pid) throws Exception {
        // TODO lijie -generated 2016-08-04
        // 根据父id查询数据
        String sql = "FROM Area a" + " WHERE 1=1 ";
        if (!StringUtil.isNullOrEmpty(pid)) {
            sql += " and a.parentId=" + pid;
        }
        if (StringUtil.isNullOrEmpty(isEnabled) == false) {

            sql += "  and isEnabled='" + isEnabled + "'";
        }
        @SuppressWarnings("unchecked")
        List<Area> dataAll = getSession().createQuery(sql).setFirstResult(0).setMaxResults(10000).list();// 取10000条数据
        ArrayList data = new ArrayList();
        for (int i = 0, l = dataAll.size(); i < l; i++)// 能获得数据
        {
            HashMap record = new HashMap();
            // 将对象转换成hashmap对象
            if (dataAll.get(i) == null)
                continue;
            // 将对象转换成hashMap
            record = HashmapAndEntityTransfer.entityTransferToHashmap(dataAll.get(i));
            // 获取需要返回的当前页记录
            data.add(record);
        }
        return data;
    }

    /**
     * @return 根节点的id 用来刷tree
     * @author 杨清岭
     */
    @Override
    public Long getMinParentId() {
        String sql = "select m.id from area m where m.parentId=(select MIN(parentId) from area)";
        List list = getSession().createSQLQuery(sql).list();
        for (Object o : list) {
            return Long.parseLong(o.toString());
        }
        return 0L;
    }

    @Override
    public String getNodeNames(long areaid) {
        Connection connection = getSession().connection();
        String DBType = SysFinalRecource.USE_DB_NAME;
        String sql = "";
        switch (DBType) {
            case "DM":
                sql = "declare" + " str varchar(1000) :=(select parentPath from area where id=" + areaid + ");"
                        + "res varchar(1000) :=' ';" + "i integer := 1;  " + "begin "
                        + "for r in (select areaName from area where id in (select column_value from table(f_split(str,',')))) loop "
                        + "res := res||r.areaName || ',' ;" + "i:=i+1; "
                        + "if i > (select count(*) from table(f_split(str,','))) then " + "exit;" + "end if;"
                        + " end loop;res:=res|| (select areaName from area where id=" + areaid + ")||','||(select depth from area where id=" + areaid + ")||','||(select areaNumber from area where id=" + areaid + ");"
                        + "select res as nodeName;" + "end";
                break;
            case "SQLSERVER":
                sql = "declare @str nvarchar(max)set @str=(select parentPath from area where id=" + areaid + ") declare @res nvarchar(max) set @res='' "
                        + "select @res=@res+areaName+',' from area where id in (select value from dbo.F_Split(@str, ',')) or id=" + areaid + " "
                        + "select @res=@res+ CONVERT(VARCHAR, PARENTID) FROM AREA WHERE ID=" + areaid + " "
                        + "select @res=@res+','+AREANUMBER FROM AREA WHERE ID=" + areaid + " "
                        + "select @res as nodeName";
                break;
            case "ORACLE":
                String sql1 = "SELECT AREANAME,DEPTH, PARENTPATH,AREANUMBER FROM AREA WHERE ID=" + areaid;

                try {
                    ResultSet rs = null;
                    String parentPath = "";
                    String areaName = "";
                    String areaNumber = "";
                    String depth = "";
                    rs = connection.createStatement().executeQuery(sql1);
                    while (rs.next()) {
                        parentPath = rs.getString("PARENTPATH");
                        areaName = rs.getString("AREANAME");
                        areaNumber = rs.getString("AREANUMBER");
                        depth = rs.getString("DEPTH");
                    }
                    sql = "select areaName from area where id in (SELECT PARENTID from area where id = " + areaid + ")";
                    rs = connection.createStatement().executeQuery(sql);
                    String result = "";
                    while (rs.next()) {
                        result += rs.getString("AREANAME") + ",";
                    }
                    return result + areaName + "," + depth + "," + areaNumber;
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                break;
        }
        try {
            ResultSet rs = connection.createStatement().executeQuery(sql);
            while (rs.next()) {
                return rs.getString("nodeName");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public HashMap getAreas(String key) {
        if (key == null)
            key = "";
        String sql = "select * from area m where m.areaName like :key and m.isEnabled=0 order by areanumber ASC";
        SQLQuery query = getSession().createSQLQuery(sql);
        query.setString("key", "%" + key + "%");
        List<Area> dataAll = query.addEntity(Area.class).list();//
        ArrayList data = new ArrayList();
        for (int i = 0, l = dataAll.size(); i < l; i++)// 能获得数据
        {
            HashMap record = new HashMap();
            // 将对象转换成hashmap对象
            if (dataAll.get(i) == null)
                continue;
            // 将对象转换成hashMap
            record = HashmapAndEntityTransfer.entityTransferToHashmap(dataAll.get(i));
            data.add(record);
        }
        HashMap result = new HashMap();
        result.put("data", data);
        return result;
    }

    @Override
    public String getParentPathById(long areaid) {
        String sql = "select * from Area where id=" + areaid;
        Connection connection = getSession().connection();
        ArrayList list = JdbcManipulation.executeSqlQueryToList(connection, sql);
        HashMap map = (HashMap) list.get(0);
        String parentPath = map.get("PARENTPATH") == null ? "" : map.get("PARENTPATH").toString();
        return parentPath;
    }

    @Override
    public String getAreaNameByid(long areaid) {
        // TODO Auto-generated method stub
        String sql = "declare @str nvarchar(max)set @str=(select parentPath from area where id=" + areaid + ") declare @res nvarchar(max) set @res='' "
                + " select @res=@res+areaName from area where id in (select value from dbo.F_Split(@str, ',')) or id=" + areaid + " "
                + " select @res as AREANAME";
        Connection connection = getSession().connection();
        ArrayList list = JdbcManipulation.executeSqlQueryToList(connection, sql);
        HashMap map = (HashMap) list.get(0);

        return map.get("AREANAME").toString();
    }

    @Override
    public Area getAreaById(long areaid) {
        String sql = "select * from Area where id=(select parentid from Area where id=" + areaid + ")";
        @SuppressWarnings("unchecked")
        List<Area> dataAll = getSession().createSQLQuery(sql).addEntity(Area.class).list();
        return dataAll.get(0);
    }

    @Override
    public ArrayList getAreaListByAreaNumber(String areaNumber) throws Exception {
        // TODO Auto-generated method stub
        String sql = "select * from Area where AREANUMBER=:areaNumber";
        SQLQuery query = getSession().createSQLQuery(sql);
        query.setString("areaNumber", areaNumber);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return (ArrayList) query.list();
    }

    @Override
    public ArrayList getDept() {
        // TODO Auto-generated method stub
        String DBType = SysFinalRecource.USE_DB_NAME;
        String sql = "";
        switch (DBType) {
            case "ORACLE":
                sql = "select * from dept where INSTR(parentPath,'33')>0 or parentId=0";
                break;
            case "SQLSERVER":
                sql = "select * from dept where CHARINDEX('33',parentPath)>0 or parentId=0";
                break;
        }
        Connection conn = getSession().connection();
        return JdbcManipulation.executeSqlQueryToList(conn, sql);
    }

    //通过区域的名称，获取区域的id
    @Override
    public Number getAreaIdByName(String name) {
        String sql = "select ID from AREA where AREANAME=:name and ISENABLED=0";
        SQLQuery query = super.getSession().createSQLQuery(sql);
        query.setString("name", name);
        List list = query.list();

        if (list == null || list.size() < 1)
            return null;

        return (Number) list.get(0);
    }

    //通过区域的名称和父级区域的id获取区域的id
    @Override
    public Number getAreaIdByNameByParentId(String name, Number topid) {
        String sql = "select ID from AREA where AREANAME=:name and PARENTID=:pid and ISENABLED=0";
        SQLQuery query = super.getSession().createSQLQuery(sql);

        query.setString("name", name);
        query.setLong("pid", topid.longValue());

        List list = query.list();

        if (list == null || list.size() < 1)
            return null;

        return (Number) list.get(0);
    }

    //查询指定id下的所有的子级区域
    @Override
    public List<Area> getChildAreaListByParentId(Number secondid) {
        String sql = "from Area where parentId=:pid and isEnabled=0";

        Query query = super.getSession().createQuery(sql);
        query.setLong("pid", secondid.longValue());

        return query.list();

    }

    @Override
    public String getAreaName(long areaid) throws Exception {
        String sql = "select AREANAME from area where id=" + areaid;
        Connection connection = getSession().connection();
        ArrayList list = JdbcManipulation.executeSqlQueryToList(connection, sql);
        HashMap map = (HashMap) list.get(0);

        return map.get("AREANAME").toString();
    }

    @Override
    public Map<String, Object> areaTree(Map<String, Object> reqMap) {
        Map<String, Object> resultMap = new HashMap<>();
        String action = (String) reqMap.get("action");
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT * FROM AREA ");
        sql.append(" WHERE 1=1");
        String depth = "";
        // 显示 所有  省 市 县
        if (!StringUtil.isNullOrEmpty(action) && "allProvince".equals(action)) {
            depth = "3";
        }
        // 显示 所有 省 市
        if (!StringUtil.isNullOrEmpty(action) && "allCity".equals(action)) {
            depth = "2";
        }
        if (!StringUtil.isNullOrEmpty(depth)) {
            sql.append(" and depth <" + depth);
        }
        sql.append("  order by id ");
        SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString());
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        resultMap.put(R.DATA_NAME, list);
        return resultMap;
    }
}
