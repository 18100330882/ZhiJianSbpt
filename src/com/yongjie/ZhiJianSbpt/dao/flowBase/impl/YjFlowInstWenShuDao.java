/**
 * @Author: 李杰
 * @Createtime: 2016年9月12日 上午11:42:02
 * @Copyright: Copyright (c) 2016
 * @Company: 北京永杰友信科技有限公司
 * @Version：1.0
 * @Description:
 */
package com.yongjie.ZhiJianSbpt.dao.flowBase.impl;

import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.dao.flowBase.*;
import com.yongjie.ZhiJianSbpt.model.flowBase.*;
import com.yongjie.ZhiJianSbpt.module.util.R;
import com.yongjie.ZhiJianSbpt.utilities.*;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
@Repository(IYjFlowInstWenShuDao.SERVICE_NAME)
public class YjFlowInstWenShuDao extends BaseDaoImpl<YjFlowInstWenShu> implements IYjFlowInstWenShuDao {

    @Override
    public List<YjFlowInstWenShu> getWenShuByFlowIdFlowInsId(long flowId, long flowInsId, String fileTitle, String nodeId, String serialNumber) {
        String sql = "select * from yjFlowInstWenShu d where 1=1";
        if (flowId != 0 && !StringUtil.isNullOrEmptyZf(flowId)) {
            sql += " and d.flowId=" + flowId;
        }
        if (flowInsId != 0 && !StringUtil.isNullOrEmptyZf(flowInsId)) {
            sql += " and d.flowInstId=" + flowInsId;
        }
        if (!StringUtil.isNullOrEmptyZf(serialNumber)) {
            sql += " and d.serialNumber=" + serialNumber;
        }
        if (!StringUtil.isNullOrEmpty(fileTitle)) {
            //sql+=" and d.fileTitle like '%"+fileTitle+"%'";
            sql += " and d.fileTitle like :fileTitle";
        }
        if (!StringUtil.isNullOrEmpty(nodeId)) {
            // sql+=" and d.nodeId='"+nodeId+"'";
            sql += " and d.nodeId=:nodeId";
        }

        SQLQuery query = getSession().createSQLQuery(sql).addEntity(YjFlowInstWenShu.class);

        if (!StringUtil.isNullOrEmpty(fileTitle))
            query.setString("fileTitle", "%" + fileTitle + "%");
        if (!StringUtil.isNullOrEmpty(nodeId))
            query.setString("nodeId", nodeId);

        List<YjFlowInstWenShu> wenShu = query.list();
        /*System.out.println(wenShu.get(0));*/
        return wenShu;
    }

    @Override
    public List<YjFlowInstWenShu> getWenShuByFlowInsIdFileTitleWenHao(Long flowInsId, String fileTitle, String wenHao, String serialNumber) {
        // TODO Auto-generated method stub
        String sql = "select * from yjFlowInstWenShu d where 1=1";

        if (!StringUtil.isNullOrEmptyZf(flowInsId) && flowInsId != 0) {
            sql += " and d.flowInstId=" + flowInsId;
        }
        if (!StringUtil.isNullOrEmptyZf(fileTitle)) {
            sql += " and d.fileTitle like '%" + fileTitle + "%'";
        }
        if (!StringUtil.isNullOrEmpty(wenHao)) {
            sql += " and d.wenHao ='" + wenHao + "'";
        }
        if (!StringUtil.isNullOrEmpty(serialNumber)) {
            sql += " and d.serialNumber ='" + serialNumber + "'";
        }
        List<YjFlowInstWenShu> wenShu = getSession().createSQLQuery(sql).addEntity(YjFlowInstWenShu.class).list();
        return wenShu;
    }

    @Override
    public int delWenShuByFlowInstIdFileTitleWenHao(long flowInstId, String filetitle, String wenHao) {
        String sql = "delete from yjFlowInstWenShu where flowInstId=" + flowInstId + " and fileTitle='" + filetitle + "'";
        if (!StringUtil.isNullOrEmpty(wenHao)) {
            sql += " and wenHao='" + wenHao + "'";
        }
        int result = getSession().createSQLQuery(sql).executeUpdate();
        return result;
    }

    @Override
    public int delWenShuById(long id) {
        String sql = "delete from yjFlowInstWenShu where ID=" + id;
        int result = getSession().createSQLQuery(sql).executeUpdate();
        return result;
    }

    @Override
    public int delTableByColumnName(String tableName, String accordingColName, String accordingColValue) {
        String sql = JdbcManipulation.getSQLString_deleteBySingleCond(tableName, accordingColName, accordingColValue);
        Connection connection = getSession().connection();
        int result = JdbcManipulation.executeSqlUpdate(connection, sql);
        return result;
    }

    @Override
    public HashMap getWenShuList(String key, long flowInstId, String nodeId, String wsName, int flag, int pageIndex, int pageSize,
                                 String sortField, String sortOrder) throws Exception {
        // TODO Auto-generated method stub
        if (key == null) {
            key = "";
        }
        String sql = "select * from yjFlowInstWenShu where  flowInstId=" + flowInstId;
        if (!StringUtil.isNullOrEmpty(nodeId)) {
            sql += " and nodeId='" + nodeId + "'";
        }
        if (!StringUtil.isNullOrEmpty(wsName)) {
            sql += " and fileTitle like '" + wsName + "_%'";
        }
        if (flag != -1) {
            sql += " and flag=" + flag;
        }
        if (StringUtil.isNullOrEmpty(sortField) == false) {
            if ("desc".equals(sortOrder) == false)
                sortOrder = "asc";
            sql += " ORDER BY " + sortField + " " + sortOrder;
        } else {
            sql += " ORDER BY id DESC";
        }

        List<YjFlowInstWenShu> wenShulist = getSession().createSQLQuery(sql).addEntity(YjFlowInstWenShu.class).list();
        ArrayList data = new ArrayList();
        int start = pageIndex * pageSize, end = start + pageSize;
        for (int i = 0, l = wenShulist.size(); i < l; i++)// 能获得数据
        {
            HashMap record = new HashMap();
            // 将对象转换成hashmap对象
            if (wenShulist.get(i) == null)
                continue;
            // 将对象转换成hashMap
            record = HashmapAndEntityTransfer.entityTransferToHashmap(wenShulist.get(i));
            // 获取需要返回的当前页记录
            if (start <= i && i < end) {
                data.add(record);
            }
        }
        HashMap result = new HashMap();
        result.put("data", data);
        result.put("total", wenShulist.size());
        return result;
    }

    @Override
    public HashMap getWenShuList2(String type, String shiysjyjcpermitid, int pageIndex, int pageSize,
                                  String sortField, String sortOrder) throws Exception {

        return null;
    }

    @Override
    public Boolean checkExist(long fileTypeId, int flag, long flowInstId, String spjg) {
        // TODO Auto-generated method stub
        String sql = "select count(ID)  "
                + " from yjflowinstwenshu "
                + " where flowInstid=" + flowInstId + " and flag=" + flag + "  and fileTypeId=" + fileTypeId;
        if (!StringUtil.isNullOrEmpty(spjg)) {
            sql += " and wenHao='" + spjg + "'";
        }
        String result = getSession().createSQLQuery(sql).uniqueResult().toString();
        int num = Integer.parseInt(result);
        Boolean isExist = false;
        if (num > 0) {
            isExist = true;
        }
        return isExist;
    }

    @Override
    public ArrayList getFuJian(long flowInstId, String flag, String nodeId, String spjg) {
        // TODO Auto-generated method stub
        String sql = "select ID,FLOWINSTID,FLOWID,FILETITLE,EXTENSE,FILESIZE,CZDATE,FILETYPEID,CZR,FILEPATH"
                + " from yjflowinstwenshu"
                + " where  flag in (:flag) and flowInstid=:flowInstId ";
        //				+ " where  flag in ("+flag+") and flowInstid="+flowInstId;
        if (!StringUtil.isNullOrEmptyZf(nodeId)) {
            sql += " and nodeId=:nodeId ";
            //			sql+=" and nodeId='"+nodeId+"'";
        }
        if (!StringUtil.isNullOrEmptyZf(spjg)) {
            sql += " and wenHao=:spjg";
            //			sql+=" and wenHao='"+spjg+"'";
        }
        SQLQuery query = getSession().createSQLQuery(sql);
        String[] flags = flag.replace("'", "").split(",");
        Integer[] flagArr = new Integer[flags.length];
        for (int i = 0; i < flags.length; i++)
            flagArr[i] = Integer.parseInt(flags[i].trim());
        query.setParameterList("flag", flagArr);
        query.setLong("flowInstId", flowInstId);
        if (!StringUtil.isNullOrEmptyZf(nodeId))
            query.setString("nodeId", nodeId);
        if (!StringUtil.isNullOrEmptyZf(spjg))
            query.setString("spjg", spjg);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return (ArrayList) query.list();
         /*@SuppressWarnings({ "unchecked", "deprecation" })
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
         return null;*/
    }

    @Override
    public ArrayList getFileByWenshu(String flowId, String flowInstId, String nodeId, String flag) {
        String sql = "select id,flowInstId,flowId,fileTitle,extense,fileSize,czDate,fileTypeId,czr from yjflowinstwenshu where 1=1";
        if (!StringUtil.isNullOrEmpty(flowId) && flowId != "null") {
            sql += " and flowId='" + flowId + "'";
        }
        if (!StringUtil.isNullOrEmpty(flowInstId) && flowInstId != "null") {
            sql += " and flowInstId='" + flowInstId + "'";
        }
        if (!StringUtil.isNullOrEmpty(nodeId) && nodeId != "null") {
            sql += " and nodeId='" + nodeId + "'";
        }
        if (!StringUtil.isNullOrEmpty(flag) && flag != "null") {
            sql += " and flag='" + flag + "'";
        }
        Connection conn = getSession().connection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            ArrayList dataAll = JdbcManipulation.ResultSetToList(rs);
            return dataAll;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {//倒着关闭
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public ArrayList getFuJianBySqsType(String sqsType, String fileName) {
        String sql = "select * from yjFlowFuJianList where sqsType='" + sqsType + "' and fileName='" + fileName + "'";
        Connection connection = getSession().connection();
        ArrayList list = JdbcManipulation.executeSqlQueryToList(connection, sql);
        return list;
    }

    @Override
    public ArrayList getWenShuByWenHao(long flowInstId, int flag, String wenHao) {
        String sql = "select * from yjFlowInstWenShu where flowInstId=" + flowInstId + " and flag=3 and wenHao='" + wenHao + "'";
        Connection connection = getSession().connection();
        ArrayList list = JdbcManipulation.executeSqlQueryToList(connection, sql);
        return list;
    }

    @Override
    public ArrayList getFilePathByWenHao(String wenHao, String fileTitle, long flowInstId) {
        String sql = "";
        if (!StringUtil.isNullOrEmpty(wenHao)) {
            sql = "select filePath from yjFlowInstWenShu where flowInstId=" + flowInstId + " and fileTitle='" + fileTitle + "' and wenHao='" + wenHao + "'";
        } else {
            sql = "select filePath from yjFlowInstWenShu where flowInstId=" + flowInstId + " and fileTitle='" + fileTitle + "'";
        }
        Connection connection = getSession().connection();
        ArrayList list = JdbcManipulation.executeSqlQueryToList(connection, sql);
        return list;
    }

    @Override
    public String getFilenameById(long fileTypeId) {
        Connection conn = getSession().connection();
        String sql = "SELECT FILENAME FROM yjFlowFuJianList WHERE id=" + fileTypeId;
        ArrayList list = JdbcManipulation.executeSqlQueryToList(conn, sql);
        HashMap map = (HashMap) list.get(0);
        return map.get("FILENAME").toString();
    }

    @Override
    public String getFileNameByFlag(String flag) {
        Connection conn = getSession().connection();
        String sql = "SELECT NAME FROM API_FUJIANTYPE WHERE flag=" + flag;
        ArrayList list = JdbcManipulation.executeSqlQueryToList(conn, sql);
        HashMap map = (HashMap) list.get(0);
        return map.get("NAME").toString();
    }

    @Override
    public int deleteWenShu(String flowInstId, String nodeId) throws Exception {
        String fileTitle = SysFinalRecource.GONGYP_SDHCFKB;//企业实地核查工作反馈表
        String sql = "delete from yjFlowInstWenShu where flowInstId=" + flowInstId + " and nodeId='" + nodeId + "'";
        if (!StringUtil.isNullOrEmpty(fileTitle)) {
            sql += " and fileTitle!='" + fileTitle + "'";//工业品现场核查第二次上传离线文件时把nodeId='xcjc'的企业实地核查反馈表不能删除，所以在这除掉
        }
        Connection conn = getSession().connection();
        Statement statement = conn.createStatement();
        return JdbcManipulation.executeSqlUpdate(conn, sql);
    }

    @Override
    public ArrayList getWenShuByNodeId(String flowInstId, String flag, String filePath) throws Exception {
        String sql = "select * from XZXK_FileTable where XZXKID=" + flowInstId + " and flag=" + flag;
        Connection conn = ConnSqlite.getConn(filePath);
        ArrayList dataAll = JdbcManipulation.executeSqlQueryToList(conn, sql);
        return dataAll;
    }

    @Override
    public ArrayList getYjWenShuByNodeId(String flowInstId, String flag, String filePath) throws Exception {
        String sql = "select * from yjFlowInstWenShu where flowInstId=" + flowInstId + " and nodeId='" + flag + "'";
        Connection conn = ConnSqlite.getConn(filePath);
        ArrayList dataAll = JdbcManipulation.executeSqlQueryToList(conn, sql);
        return dataAll;
    }

    @Override
    public List<YjFlowInstWenShu> getWenShuByFlowIdAndFlowInsId(long flowId, long flowInsId, String fileTitle1, String fileTitle2
    ) throws Exception {

        String sql = "select * from yjFlowInstWenShu d where 1=1";
        if (flowId != 0) {
            sql += " and d.flowId=" + flowId;
        }
        if (flowInsId != 0) {
            sql += " and d.flowInstId=" + flowInsId;
        }
        if (!StringUtil.isNullOrEmpty(fileTitle1) && !StringUtil.isNullOrEmpty(fileTitle2)) {
            sql += "  and ( d.fileTitle = '" + fileTitle1 + "'  or d.fileTitle = '" + fileTitle2 + "' )";

        } else {
            if (!StringUtil.isNullOrEmpty(fileTitle2)) {
                sql += " and d.fileTitle = '" + fileTitle2 + "'";
            }
            if (!StringUtil.isNullOrEmpty(fileTitle1)) {
                sql += " and d.fileTitle = '" + fileTitle1 + "'";
            }
        }
        List<YjFlowInstWenShu> wenShu = getSession().createSQLQuery(sql).addEntity(YjFlowInstWenShu.class).list();
        /*System.out.println(wenShu.get(0));*/
        return wenShu;
    }

    @Override
    public ArrayList getFuJianBycondTable(long flowInstId, int flag, String nodeId, String spjg, String condTable) {
        // TODO Auto-generated method stub
        String sql = "select ID,FLOWINSTID,FLOWID,FILETITLE,EXTENSE,FILESIZE,CZDATE,CZR"
                + " from " + condTable
                + " where  flag=" + flag + " and flowInstid=" + flowInstId;
        if (!StringUtil.isNullOrEmpty(nodeId)) {
            // sql+=" and nodeId='"+nodeId+"'";
            sql += " and nodeId=:nodeId ";
        }
        if (!StringUtil.isNullOrEmpty(spjg)) {
            // sql+=" and wenHao='"+spjg+"'";
            sql += " and wenHao=:spjg ";
        }

        SQLQuery query = getSession().createSQLQuery(sql);

        if (!StringUtil.isNullOrEmpty(nodeId))
            query.setString("nodeId", nodeId);
        if (!StringUtil.isNullOrEmpty(spjg))
            query.setString("spjg", spjg);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        return (ArrayList) query.list();

         /*@SuppressWarnings({ "unchecked", "deprecation" })
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
         return null;*/
    }

    @Override
    public ArrayList getFileTableListRow(long flowInstId, String fileTitleRow) {
        String sql = "select * from yjFlowFileTableRaw where flowInstId=" + flowInstId;
        if (!StringUtil.isNullOrEmptyZf(fileTitleRow)) {
            //			sql+=" and fileTitleRaw='"+fileTitleRow+"'";
            sql += " and fileTitleRaw=:fileTitleRow ";
        } else {
            return null;
        }
        SQLQuery query = getSession().createSQLQuery(sql);
        if (!StringUtil.isNullOrEmptyZf(fileTitleRow))
            query.setString("fileTitleRow", fileTitleRow);

        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        return (ArrayList) query.list();
         /*Connection connection = getSession().connection();
         ArrayList filetable=JdbcManipulation.executeSqlQueryToList(connection, sql);
         return filetable;*/
    }

    @Override
    public ArrayList getFileTableRawById(long id) {
        // TODO Auto-generated method stub
        String sql = "select * from yjFlowFileTableRaw where id=" + id;
        Connection connection = getSession().connection();
        ArrayList filetableList = JdbcManipulation.executeSqlQueryToList(connection, sql);
        return filetableList;
    }

    @Override
    public ArrayList getFlowXcPhotoById(long id) {
        // TODO Auto-generated method stub
        String sql = "select * from yjFlowXcPhoto where id=" + id;
        Connection connection = getSession().connection();
        ArrayList filetableList = JdbcManipulation.executeSqlQueryToList(connection, sql);
        return filetableList;
    }

    @Override
    public ArrayList getCydList(long flowInstId, String cydName) {
        ArrayList list = new ArrayList();
        if (StringUtil.isNullOrEmptyZf(cydName)) {
            return list;
        }
        String sql = "select f.* from gongyp_CydFj f join gongyp_Cyd c on f.cydId=c.id "
                + "and f.flowInstId=c.flowInstId where c.cydName=:cydName  and f.flowInstId=:flowInstId ";
        SQLQuery query = getSession().createSQLQuery(sql);
        query.setString("cydName", cydName);
        query.setLong("flowInstId", flowInstId);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        return (ArrayList) query.list();
         /*String sql="select f.* from gongyp_CydFj f join gongyp_Cyd c on f.cydId=c.id and f.flowInstId=c.flowInstId where c.cydName='"+cydName+"' and f.flowInstId="+flowInstId;
         Connection connection = getSession().connection();
         list=JdbcManipulation.executeSqlQueryToList(connection, sql);
         return list;*/
    }

    @Override
    public ArrayList getWenShuByFileTietle(long flowInstId, String fileTitle, long flag, String serialNumber) {
        // TODO Auto-generated method stub
        String sql = "select f.filename as fileTitle2,d.* from yjFlowInstWenShu d left join YJFLOWFUJIANLIST f on d.FILETYPEID = f.id where 1=1 ";
        if (!StringUtil.isNullOrEmptyZf(flowInstId) && flowInstId > 0) {
            sql += " and d.flowInstId=" + flowInstId;
        }
        if (!StringUtil.isNullOrEmptyZf(fileTitle)) {
            sql += " and d.fileTitle='" + fileTitle + "'";
        }
        if (!StringUtil.isNullOrEmptyZf(flag)) {
            sql += " and d.flag=" + flag;
        }
        if (!StringUtil.isNullOrEmptyZf(serialNumber)) {
            sql += " and d.serialNumber=" + serialNumber;
        }
        Connection connection = getSession().connection();
        ArrayList list = JdbcManipulation.executeSqlQueryToList(connection, sql);
        return list;
    }

    @Override
    public ArrayList getWenShuByfiletypeId(long flowInstId, int flag, long filetypeId) {
        String sql = "select * from yjFlowInstWenShu where flowInstId=" + flowInstId + " and flag=" + flag;
        if (filetypeId > 0) {
            sql += " and filetypeId=" + filetypeId;
        }
        Connection connection = getSession().connection();
        ArrayList list = JdbcManipulation.executeSqlQueryToList(connection, sql);
        return list;
    }

    @Override
    public ArrayList getWenShuByFileTitleAndFlowInstId(long flowInstId, String fileTitle) {
        String sql = "select * from yjFlowInstWenShu d "
                + "where d.flowInstId=:flowInstId and d.fileTitle=:fileTitle";
        SQLQuery query = getSession().createSQLQuery(sql);
        query.setLong("flowInstId", flowInstId);
        query.setString("fileTitle", fileTitle);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return (ArrayList) query.list();
         /*String sql="select * from yjFlowInstWenShu d where d.flowInstId="+flowInstId+" and d.fileTitle='"+fileTitle+"'";
         Connection connection = getSession().connection();
         ArrayList list=JdbcManipulation.executeSqlQueryToList(connection, sql);
         return list;*/
    }

    @Override
    public ArrayList getWenShuByFileTypeId(long flowInstId, long fileTypeId) {
        String sql = "select * from yjFlowInstWenShu d where d.flowInstId=" + flowInstId + " and d.fileTypeId=" + fileTypeId;
        Connection connection = getSession().connection();
        ArrayList list = JdbcManipulation.executeSqlQueryToList(connection, sql);
        return list;
    }

    @Override
    public ArrayList getWenShuBySerialNumber(String serialNumber, long fileTypeId) {
        String sql = "select ID,NODEID,FLOWID,FLOWINSTID,BIZID,WENHAO,FILETITLE,FILEPATH,FILESIZE,EXTENSE,TIAOMA,ISGZ,FLAG,FILETYPEID,TYPE,PAIXU,CZR,LICENSE_CODE,AUTH_CODE,NETWORKPATH,SERIALNUMBER,LNG,LAT,IMGADDRESS,IMGTIME, TO_CHAR(CZDATE,'yyyy-mm-dd') CZDATE,BGLX,PZDATE,SQDATE,SQLX  from yjFlowInstWenShu d where d.serialNumber= '" + serialNumber + "' and d.fileTypeId=" + fileTypeId;
        Connection connection = getSession().connection();
        ArrayList list = JdbcManipulation.executeSqlQueryToList(connection, sql);
        return list;
    }

    @Override
    public ArrayList getApiFileBySerialNumber(String serialNumber, String flag) {
        String sql = "select ID,FLOWID,FILENAME,FILETYPE,FLAG,FILEPATH,LOCALFILEPATH,ORDERNUM,FILEBASE64,SERIALNUMBER,TO_CHAR(CREATEDATE,'yyyy-mm-dd') CREATEDATE,UPDATEDATE,UNID,STATE,YJFLOWFUJIANID,SITEADDRESS,PAIXU,CZR from API_FILE  where serialNumber= '" + serialNumber + "' and flag = '" + flag + "'";
        Connection connection = getSession().connection();
        ArrayList list = JdbcManipulation.executeSqlQueryToList(connection, sql);
        return list;
    }

    @Override
    public ArrayList getAllWenShuByFlowInstId(long flowInstId) {
        String sql = "select * from yjFlowInstWenShu where flowInstId=" + flowInstId;
        Connection connection = getSession().connection();
        ArrayList list = JdbcManipulation.executeSqlQueryToList(connection, sql);
        return list;
    }

    @Override
    public ArrayList getFuJianByFILETYPEID(long flowInstId, int flag, String nodeId, String spjg, String fuJidStr) {
        String sql = "select ID,FLOWINSTID,FLOWID,FILETITLE,EXTENSE,FILESIZE,CZDATE,FILETYPEID,CZR,FILEPATH"
                + " from yjflowinstwenshu"
                + " where  flag=" + flag + " and flowInstid=" + flowInstId;
        if (!StringUtil.isNullOrEmpty(nodeId)) {
            sql += " and nodeId='" + nodeId + "'";
        }
        if (!StringUtil.isNullOrEmpty(fuJidStr)) {
            sql += " and FILETYPEID='" + fuJidStr + "'";
        }
        if (!StringUtil.isNullOrEmpty(spjg) && !spjg.equals("null")) {
            sql += " and wenHao='" + spjg + "'";
        }
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
    public ArrayList getFuJianBanDuan(long flowInstId, String sqsType, String flag, String nodeId, String spjg) {
        String sql = "select ID,FLOWINSTID,FLOWID,FILETITLE,EXTENSE,FILESIZE,CZDATE,FILETYPEID,CZR,FILEPATH"
                + " from yjflowinstwenshu"
                + " where  flag in (:flag) and flowInstid=:flowInstId "
                + "and FILETYPEID in(select id from YJFLOWFUJIANLIST where sqsType=:sqsType)";
                 /*+ " where  flag in ("+flag+") and flowInstid="+flowInstId
                 +"and FILETYPEID in(select id from YJFLOWFUJIANLIST where sqsType='"+sqsType +"')" ;*/
        if (!StringUtil.isNullOrEmptyZf(nodeId)) {
            sql += " and nodeId=:nodeId ";
            //			sql+=" and nodeId='"+nodeId+"'";
        }
        if (!StringUtil.isNullOrEmptyZf(spjg)) {
            sql += " and wenHao=:spjg ";
            //			sql+=" and wenHao='"+spjg+"'";
        }
        SQLQuery query = getSession().createSQLQuery(sql);

        String[] flagArr = flag.replace("'", "").split(",");
        Integer[] flags = new Integer[flagArr.length];
        for (int i = 0; i < flagArr.length; i++)
            flags[i] = Integer.parseInt(flagArr[i].trim());
        query.setParameterList("flag", flags);

        query.setLong("flowInstId", flowInstId);

        query.setString("sqsType", sqsType);
        if (!StringUtil.isNullOrEmptyZf(nodeId))
            query.setString("nodeId", nodeId);
        if (!StringUtil.isNullOrEmptyZf(spjg))
            query.setString("spjg", spjg);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return (ArrayList) query.list();
         /*@SuppressWarnings({ "unchecked", "deprecation" })
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
         return null;*/
    }

    @Override
    public ArrayList getPlxz(String sqsId) {
        String sql = "select * from JLQJXSPZ_FUJIAN d where 1=1";
        if (!StringUtil.isNullOrEmpty(sqsId)) {
            sql += " and SQSID=" + sqsId;
        }
        Connection connection = getSession().connection();
        ArrayList list = JdbcManipulation.executeSqlQueryToList(connection, sql);
        return list;
    }

    @Override
    public ArrayList getWenShu(long flowId, long flowInstId, String nodeId, String fileTitle) {
        String sql = "select * from YJFLOWINSTWENSHU where flowId=" + flowId + " and flowInstId=" + flowInstId + " and nodeId='" + nodeId + "' and fileTitle='" + fileTitle + "'";
        Connection connection = getSession().connection();
        ArrayList list = JdbcManipulation.executeSqlQueryToList(connection, sql);
        return list;
    }

    @Override
    public int delWenShuByFlowIdFlowInstIdNodeIdWenHao(long flowId, long flowInstId, String nodeId, String wenHao) {
        String sql = "delete from yjFlowInstWenShu where flowId=" + flowId + " and flowInstId=" + flowInstId + " and nodeId = '" + nodeId + "' and wenHao='" + wenHao + "'";
        int result = getSession().createSQLQuery(sql).executeUpdate();
        return result;
    }

    @Override
    public void delWenShuByFlowInstIdsFileTitle(Long[] flowInstIdArr, String fileTitle) {
        String sql = "delete from yjFlowInstWenShu where flowInstId in (:flowInstIds) and fileTitle=:title";
        SQLQuery query = getSession().createSQLQuery(sql);
        query.setParameterList("flowInstIds", flowInstIdArr);
        query.setString("title", fileTitle);
        query.executeUpdate();
    }

    @Override
    public void saveFlush(Object model) {
        Session session = getSession();
        session.save(model);
        session.flush();
        session.clear();
    }

    @Override
    public Map<String, Object> getWenShuListBySerialNumber(String serialNumber) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT T.* FROM YJFLOWINSTWENSHU T WHERE T.SERIALNUMBER = '");
        sql.append(serialNumber);
        sql.append("'");
        SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString());
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> list = sqlQuery.list();
        HashMap<String, Object> result = new HashMap<>();
        result.put(R.DATA_NAME, list);
        return result;
    }

    @Override
    public int updateWenShuId(String id, String newId) {
        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE YJFLOWINSTWENSHU SET ID = '" + newId + "' WHERE ID = '" + id + "'");
        SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString());
        return sqlQuery.executeUpdate();
    }

    @Override
    public int updateWenShuFlag(String id, String flag) {
        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE YJFLOWINSTWENSHU SET FLAG = '" + flag + "' WHERE ID = '" + id + "'");
        SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString());
        return sqlQuery.executeUpdate();
    }
}
