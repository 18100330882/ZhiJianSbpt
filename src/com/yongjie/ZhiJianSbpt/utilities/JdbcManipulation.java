package com.yongjie.ZhiJianSbpt.utilities;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

public class JdbcManipulation {
    /**
     * 将对象数据填充到模板对象PreparedStatement中。
     *
     * @param jdbcConnection jdbc连接对象
     * @param sql            sql语句
     * @param objects        此数组存的是所有参数值，按顺序
     * @return 已经填充数据的PreparedStatement对象
     */
    public static PreparedStatement fillDataToPreparedStatement(Connection jdbcConnection, String sql,
                                                                Object[] objects) {
        PreparedStatement pst = null;
        try {
            pst = jdbcConnection.prepareStatement(sql);
            for (int i = 0, size = objects.length; i < size; i++) {
                pst.setObject(i + 1, objects[i]);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return pst;
    }

    //查询
    public static ResultSet getResultSetQuery(Statement statement, String sql) {
        if (statement != null) {
            try {
                ResultSet set = statement.executeQuery(sql);
                return set;
            } catch (SQLException e) {
                System.out.println("getResultSetQuery出现错误");
                e.printStackTrace();
            }
        }
        return null;
    }

    public static ArrayList ResultSetToList(ResultSet rs) throws Exception {
        ResultSetMetaData md = rs.getMetaData();
        int columnCount = md.getColumnCount();
        ArrayList list = new ArrayList();
        Map rowData;
        while (rs.next()) {
            rowData = new HashMap(columnCount);
            for (int i = 1; i <= columnCount; i++) {
                Object v = rs.getObject(i);

                if (v != null && (v.getClass() == Date.class || v.getClass() == java.sql.Date.class)) {
                    Timestamp ts = rs.getTimestamp(i);
                    v = new Date(ts.getTime());
                    //v = ts;
                } else if (v != null && v.getClass() == Clob.class) {
                    v = clob2String((Clob) v);
                }
                rowData.put(md.getColumnName(i), v);
            }
            list.add(rowData);
        }
        return list;
    }

    private static String clob2String(Clob clob) throws Exception {
        return (clob != null ? clob.getSubString(1, (int) clob.length()) : null);
    }

    /**
     * 根据单一条件拼凑sql删除语句
     *
     * @param tablename         表名
     * @param accordingColName  依据列名
     * @param accordingColValue 依据列值
     * @return 拼凑成更新的sql语句
     */
    public static String getSQLString_deleteBySingleCond(String tablename, String accordingColName, String accordingColValue) {
        StringBuffer sql = new StringBuffer("");
        sql.append("DELETE FROM ").append(tablename);
        if (!StringUtil.isNullOrEmpty(accordingColName) && !StringUtil.isNullOrEmpty(accordingColValue)) {
            sql.append("WHERE ").append(accordingColName).append("='").append(accordingColValue).append("'");
        }
        return sql.toString();
    }

    /**
     * 根据单一条件拼凑更新sql语句
     *
     * @param tablename         表名
     * @param accordingColName  依据列名
     * @param accordingColValue 依据列值
     * @param strs              需要更新的字段值(以colname-colValue等形式传递)
     * @return 拼凑成更新的sql语句
     */
    public static String getSQLString_updateBySingleCond(String tablename, String accordingColName, String accordingColValue, String... strs) {
        StringBuffer sql = new StringBuffer("");
        sql.append("UPDATE ").append(tablename);
        if (strs.length > 0) {
            sql.append(" set ");
        }
        for (int i = 0, j = strs.length, flag = 0; i < j; i++, flag++) {//拼凑set部分.
            if (0 == (flag % 2)) {
                sql.append(strs[i]).append("='");
            } else if (1 == (flag % 2) && (i <= j - 3)) {
                sql.append(strs[i]).append("', ");
            } else {
                sql.append(strs[i]).append("' ");
            }
        }
        if (!StringUtil.isNullOrEmpty(accordingColName) && !StringUtil.isNullOrEmpty(accordingColValue)) {
            sql.append("WHERE ").append(accordingColName).append("='").append(accordingColValue).append("'");
        }
        return sql.toString();
    }

    /**
     * 根据多条件拼凑更新sql语句
     *
     * @param tablename        表名
     * @param mMultiConditions map类型
     * @param multiValues      map类型
     * @return 拼凑成更新的sql语句
     */
    public static String getSQLString_updateByMultiConds(String tablename, Map mMultiConditions, Map multiValues) {
        StringBuffer sql = new StringBuffer("");
        sql.append("UPDATE ").append(tablename);
        if (multiValues.size() > 0) {
            sql.append(" set ");
        }
        for (Iterator iterator_multiValues = multiValues.entrySet().iterator(); iterator_multiValues.hasNext(); ) {//填充要更新的列
            Map.Entry entry = (Map.Entry) iterator_multiValues.next();
            if (iterator_multiValues.hasNext()) {
                sql.append(entry.getKey()).append("='").append(entry.getValue()).append("', ");
            } else {
                sql.append(entry.getKey()).append("='").append(entry.getValue()).append("' ");
            }
        }
        if (mMultiConditions.size() > 0) {
            sql.append("WHERE ");
        }
        for (Iterator iterator_multiConditions = mMultiConditions.entrySet().iterator(); iterator_multiConditions.hasNext(); ) {//填充条件
            Map.Entry entry = (Map.Entry) iterator_multiConditions.next();
            if (iterator_multiConditions.hasNext()) {
                sql.append(entry.getKey()).append("='").append(entry.getValue()).append("' AND ");
            } else {
                sql.append(entry.getKey()).append("='").append(entry.getValue()).append("' ");
            }
        }
        return sql.toString();
    }

    /**
     * 根据单个条件和排序字段返回指定列 --sql语句
     *
     * @param tablename         表名
     * @param accordingColName  依据列
     * @param accordingColValue 依据列值
     * @param sortField         排序字段
     * @param sortOrder         排序规律
     * @param returnCols        查询返回列
     * @return 拼凑而成的sql语句
     */
    public static String getSQLString_queryBySingleCond(String tablename, String accordingColName, String accordingColValue, String sortField, String sortOrder, String... returnCols) {
        StringBuffer sql = new StringBuffer("");
        if (returnCols.length == 0) {
            sql.append("SELECT * ");
        } else {
            sql.append("SELECT ");
        }
        for (int i = 0, j = returnCols.length; i < j; i++) { //添加返回列
            if (i < j - 1) {
                sql.append(returnCols[i]).append(", ");
            } else {
                sql.append(returnCols[i]).append(" ");
            }
        }
        sql.append("FROM ").append(tablename);
        if (!StringUtil.isNullOrEmpty(accordingColName) && !StringUtil.isNullOrEmpty(accordingColValue)) {
            sql.append(" WHERE ").append(accordingColName).append("='").append(accordingColValue)//添加后续语句
                    .append("' ");
        }
        if (!StringUtil.isNullOrEmpty(sortField)) {
            sql.append("ORDER By ").append(sortField).append(" ").append(sortOrder);
        }
        return sql.toString();
    }

    /**
     * 根据多个条件和排序字段返回指定列 --sql语句
     *
     * @param tablename        表名
     * @param mMultiConditions map结构,以colName-colValue形式组装
     * @param orders           map结构,以sortField-sortValue形式组装
     * @param returnCols       查询返回列
     * @return
     */
    public static String getSQLString_queryByMultiConds(String tablename, Map mMultiConditions, Map orders, String... returnCols) {
        StringBuffer sql = new StringBuffer("");
        if (returnCols.length == 0) {
            sql.append("SELECT * ");
        } else {
            sql.append("SELECT ");
        }
        for (int i = 0, j = returnCols.length; i < j; i++) { //添加返回列
            if (i < j - 1) {
                sql.append(returnCols[i]).append(", ");
            } else {
                sql.append(returnCols[i]).append(" ");
            }
        }
        sql.append("FROM ").append(tablename).append(" ");
        if (mMultiConditions.size() > 0) {
            sql.append("WHERE ");
        }
        for (Iterator iterator_multiConditions = mMultiConditions.entrySet().iterator(); iterator_multiConditions.hasNext(); ) {//填充条件
            Map.Entry entry = (Map.Entry) iterator_multiConditions.next();
            if (iterator_multiConditions.hasNext()) {
                sql.append(entry.getKey()).append("='").append(entry.getValue()).append("' AND ");
            } else {
                sql.append(entry.getKey()).append("='").append(entry.getValue()).append("' ");
            }
        }
        if (orders.size() > 0) {
            sql.append("ORDER BY ");
        }
        for (Iterator iterator_orders = orders.entrySet().iterator(); iterator_orders.hasNext(); ) {//填充排序策略
            Map.Entry entry = (Map.Entry) iterator_orders.next();
            if (iterator_orders.hasNext()) {
                sql.append(entry.getKey()).append(" ").append(entry.getValue()).append(", ");
            } else {
                sql.append(entry.getKey()).append(" ").append(entry.getValue()).append(" ");
            }
        }
        return sql.toString();
    }

    /**
     * 根据jdbc链接conn和拼凑好的查询sql语句 返回arrayList
     * 很好的处理了jdbc 链接关闭。
     *
     * @param connection jdbc连接
     * @param sql        不带模板参数的查询sql
     * @return arrayList数据集 如果没有记录，返回空arrayList（arrayList的size为0）
     */
    public static ArrayList executeSqlQueryToList(Connection connection, String sql) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = connection;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            return JdbcManipulation.ResultSetToList(rs);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

    public static int executeSqlUpdate(Connection connection, String sql) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = connection;
            stmt = conn.createStatement();
            int rows = stmt.executeUpdate(sql);
            return rows;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    private int ToInt(Object o) {
        if (o == null) return 0;
        double d = Double.parseDouble(o.toString());
        int i = 0;
        i -= d;
        return -i;
    }

    private String ToString(Object o) {
        if (o == null) return "";
        return o.toString();
    }

    public Timestamp ToDate(Object o) {
        try {
            if (o.getClass() == String.class) {


                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                o = format.parse(o.toString());
                return new Timestamp(((Date) o).getTime());
            }
            return o != null ? new Timestamp(((Date) o).getTime()) : null;
        } catch (Exception ex) {
            return null;
        }
    }
}
