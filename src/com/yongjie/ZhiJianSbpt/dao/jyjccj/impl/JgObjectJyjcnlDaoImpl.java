/**
 * @Author: 黄煜豪
 * @Createtime: 2017年9月20日 下午4:46:18
 * @Copyright: Copyright (c) 2016
 * @Company: 北京永杰友信科技有限公司
 * @Version：1.0
 * @Description:
 */
package com.yongjie.ZhiJianSbpt.dao.jyjccj.impl;


import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.dao.jyjccj.JgObjectJyjcnlDao;
import com.yongjie.ZhiJianSbpt.model.jyjccj.JgObjectJyjcnl;
import com.yongjie.ZhiJianSbpt.utilities.JdbcManipulation;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository(JgObjectJyjcnlDao.SERVICE_NAME)
public class JgObjectJyjcnlDaoImpl extends BaseDaoImpl<JgObjectJyjcnl> implements JgObjectJyjcnlDao {

    @Override
    public ArrayList checkNl(String leib, String cpxh, String yjbz, String cpmc, String shxydm, String sqsId) {
        String sql = "select * from JGOBJECT_JYJCNL where leib='" + leib + "' or cpmc='" + cpmc + "' or cpxh='" + cpxh + "' or yjbzmc='" + yjbz + "' or sqsId=" + sqsId + "' or zzjgdm='" + shxydm + "'";
        Connection conn = getSession().connection();
        ArrayList list = JdbcManipulation.executeSqlQueryToList(conn, sql);
        return list;
    }

    @Override
    public int deleteJgTable(String serialNumber, String type, String cddz, String calorcma, String tableName) {
        String sql = "delete " + tableName + " where serialNumber=" + serialNumber;
        if (!StringUtil.isNullOrEmpty(type) && !"null".equals(type)) {
            sql += " and type=" + type;
        }
        if (!StringUtil.isNullOrEmpty(cddz) && !"null".equals(cddz)) {
            if ("JGOBJECT_JYJCNL".equals(tableName)) {
                sql += " and DIZHI='" + cddz + "'";
            } else if ("JGOBJECT_JYJCSB".equals(tableName)) {
                sql += " and CDDZ='" + cddz + "'";
            }
        }
        if (!StringUtil.isNullOrEmptyZf(calorcma)) {
            sql += " and calorcma='" + calorcma + "'";
        }
        Connection conn = getSession().connection();
        return JdbcManipulation.executeSqlUpdate(conn, sql);
    }

    @Override
    public ArrayList getNlList(String serialNumber, String type, String cddz, String calOrCMa, String status) {
        String sql = "select * from JGOBJECT_JYJCNL where serialNumber=" + serialNumber + " and (LEIB is not null  or  replace(LEIB,'　','') !='' or  DXUHAO is not null or  replace(DXUHAO,'　','') !=''  or  YJBZMC is not null  or  replace(YJBZMC,'　','') !='')";
        if (!StringUtil.isNullOrEmptyZf(type) && !"null".equals(type)) {
            sql += " and type=" + type;
        }
        if (!StringUtil.isNullOrEmptyZf(cddz) && !"null".equals(cddz)) {
            sql += " and DIZHI='" + cddz + "'";
        }
        if (!StringUtil.isNullOrEmptyZf(calOrCMa)) {
            sql += " and CALORCMA='" + calOrCMa + "'";
        }
        if (!StringUtil.isNullOrEmptyZf(status)) {
            sql += " and status='" + status + "'";
        }
        sql += " order by id ";
        Connection conn = getSession().connection();
        return JdbcManipulation.executeSqlQueryToList(conn, sql);
    }

    // 全表更新备注
    @Override
    public int updatebeiZhu() {
        /*
         * 获取检验检测能力表数据
         * 遍历， 判断其备注是否为空
         * 如果备注为空，则插入数据
         * 	   插入的数据内容为根据 zzjgdm 关联  JGOBJECT_JYJCLCPSXX shxydm字段 查询的数据的 评审日期的部分内容加上评审结论的部分内容
         *  	如果有多条对应数据，使用jgstate为1的记录
         *  	如果有多条为1的记录，使用根据id倒叙后的第一条
         */
        String sql = "select distinct jgobject_jyjcnl.zzjgdm from shiysjyjc_sqs "
                + "inner join bizshiysjyjc on bizshiysjyjc.id = shiysjyjc_sqs.sqsid "
                + "inner join jgobject_jyjcnl on shiysjyjc_sqs.newshxydm=jgobject_jyjcnl.zzjgdm "
                + "inner join JGOBJECT_JYJCLCPSXX on JGOBJECT_JYJCLCPSXX.shxydm=shiysjyjc_sqs.newshxydm "
                + "where bizshiysjyjc.flowinstid in ("
                + "select distinct a.FLOWINSTID  from yjNodeInst a where a.flowId=3 "
                + "and a.nodeId in (select b.nodeId from YJFLOWNODES b "
                + "where b.flowId=3 "
                + "and b.nodeweight>=(select j.nodeweight from YJFLOWNODES j "
                + "where j.flowId=3 and j.nodeId='zz')))";

        SQLQuery query = super.getSession().createSQLQuery(sql);

        List<String> list = query.list();

        int updateCount = 0;

        // 遍历
        for (String zzjgdm : list) {
            // 根据当前组织机构代码获取 JGOBJECT_JYJCLCPSXX 表数据
            List<Map<String, Object>> psxxList = this.getPsxxList(zzjgdm);

            if (psxxList != null && psxxList.size() > 0) {
                String beizhu = "";
                // 只查询到了一条
                String psrq = (String) psxxList.get(0).get("PSRQ");        // 获取评审日期
                if (psrq == null || psrq.length() < 1)
                    continue;
                if (psrq.length() < 10)
                    psrq = psrq.substring(0);
                else
                    psrq = psrq.substring(0, 10);

                // 获取评审类型
                String pslx = (String) psxxList.get(0).get("PSLX");
                if (pslx != null && pslx.length() > 0)
                    pslx = pslx.substring(pslx.indexOf("("), pslx.indexOf(")") + 1);

                beizhu = psrq + pslx;
                System.out.println("beizhu: " + beizhu);
                int count = this.updateBeizhuByzzjgdm(zzjgdm, beizhu);
                updateCount += count;
            }
        }
        return updateCount;
    }

    // 根据社会信用代码获取 JGOBJECT_JYJCLCPSXX 表数据
    private List<Map<String, Object>> getPsxxList(String shxydm) {
        String sql = "select ID, PSRQ, PSLX, JGSTATE from JGOBJECT_JYJCLCPSXX where SHXYDM=:shxydm "
                + "order by JGSTATE desc, ID DESC";
        SQLQuery query = super.getSession().createSQLQuery(sql);

        query.setString("shxydm", shxydm);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        return query.list();
    }

    // 更新zzjgdm为当前值且备注为空的记录的备注
    private int updateBeizhuByzzjgdm(String zzjgdm, String beiZhu) {
        String sql = "update JGOBJECT_JYJCNL set BEIZHU=:beiZhu where ZZJGDM=:zzjgdm and BEIZHU is null";
        SQLQuery query = super.getSession().createSQLQuery(sql);
        query.setString("beiZhu", beiZhu);
        query.setString("zzjgdm", zzjgdm);
        return query.executeUpdate();
    }

    @Override
    public ArrayList getNlList2(String sqsId, String type, String cddz, String calOrCMa) {
        String sql = "select * from JGOBJECT_JYJCNL where sqsId=" + sqsId + " and (YJBZMC<> '' or YJBZMC is null or LEIB <> '' or LEIB is null or DXUHAO<>'' or DXUHAO is null)";
        if (!StringUtil.isNullOrEmpty(type) && !"null".equals(type)) {
            sql += " and type in (" + type + ")";
        }
        if (!StringUtil.isNullOrEmpty(cddz) && !"null".equals(cddz)) {
            sql += " and DIZHI='" + cddz + "'";
        }
        if (!StringUtil.isNullOrEmpty(calOrCMa)) {
            sql += " and CALORCMA='" + calOrCMa + "'";
        }
        sql += " order by id ";
        Connection conn = getSession().connection();
        return JdbcManipulation.executeSqlQueryToList(conn, sql);
    }

    @Override
    public ArrayList getNlList4(String shxydm, String type, String cddz, String calOrCMa, String jgstate) {
        String sql = "select * from JGOBJECT_JYJCNL where zzjgdm='" + shxydm + "' and jgstate in(" + jgstate + ") ";
        if (!StringUtil.isNullOrEmpty(type) && !"null".equals(type)) {
            sql += " and type in (" + type + ")";
        }
        if (!StringUtil.isNullOrEmpty(cddz) && !"null".equals(cddz)) {
            sql += " and DIZHI='" + cddz + "'";
        }
        if (!StringUtil.isNullOrEmpty(calOrCMa)) {
            sql += " and CALORCMA='" + calOrCMa + "'";
        }
        sql += " order by id ";
        Connection conn = getSession().connection();
        return JdbcManipulation.executeSqlQueryToList(conn, sql);
    }

    @Override
    public ArrayList getJyjcNlListByShxydm(String shxydm) {
        String sql = "select * from JGOBJECT_JYJCNL where ZZJGDM='" + shxydm + "' and JGSTATE=1 order by id asc";
        Connection conn = getSession().connection();
        return JdbcManipulation.executeSqlQueryToList(conn, sql);
    }

    @Override
    public int updatejyjcnlData(String ziduan, String id) {
        String sql = "update JGOBJECT_JYJCNL set " + ziduan + "=' ' where id=" + id;
        Connection conn = getSession().connection();
        return JdbcManipulation.executeSqlUpdate(conn, sql);
    }

    @Override
    public ArrayList getNlList3(String cddz, String shxydm) {
        String sql = "select * from JGOBJECT_JYJCNL where ZZJGDM='" + shxydm + "' and JGSTATE=1 and dizhi='" + cddz + "' order by id asc";
        Connection conn = getSession().connection();
        return JdbcManipulation.executeSqlQueryToList(conn, sql);
    }

    @Override
    public HashMap getJyjcnlDzByType(String zzjgdm, String jgstate, int pageIndex, int pageSize, String sortField,
                                     String sortOrder) {
        String sql = "select DISTINCT j.DIZHI,j.TYPE,j.CALORCMA,y.ID as WENSHUID,y.FILEPATH,y.EXTENSE,y.FILETITLE from JGOBJECT_JYJCNL j left join YJFLOWINSTWENSHU y "
                + "on j.zzjgdm=y.wenhao "
                + " and y.filetitle like '%'||j.dizhi||'%'"
                + " and y.filetitle like '%'||j.CALORCMA||'%' "
                + " and y.filetitle like '%'||(case  when j.type=1 then '食品' when j.type=4 then '非食品'end)||'%'"
                + "where j.zzjgdm='" + zzjgdm + "' and j.jgstate in(" + jgstate + ") ";

        Connection conn = getSession().connection();
        ArrayList list = JdbcManipulation.executeSqlQueryToList(conn, sql);
        HashMap result = new HashMap<>();
        /*------------------数据分页---------------------*/
        List data = new ArrayList<>();
        int start = pageIndex * pageSize;
        int end = start + pageSize;
        for (int i = 0, j = list.size(); i < j; i++) {
            if (null == list.get(i)) {
                continue;
            }
            if (i >= start && i < end) {
                data.add(list.get(i));
            }
        }
        result.put("data", data);
        result.put("total", list.size());
        return result;
    }

    @Override
    public int saveBatch(List<JgObjectJyjcnl> list) throws Exception {
        return saveBatch(list, 1000);
    }

    @Override
    public int saveBatch(List<JgObjectJyjcnl> list, int size) throws Exception {
        if (StringUtil.isEmpty(list)) {
            return 0;
        }
        if (StringUtil.isEmpty(size)
                || size <= 0) {
            return 0;
        }
        Session session = getSession();
        for (int i = 0, len = list.size(); i < len; i++) {
            session.save(list.get(i));
            if (i % size == 0) {
                session.flush();
                session.clear();
            }
        }
        return 1;
    }

    @Override
    public HashMap getJgObjectJyjcNlList(String zsbh, String lb, String cpmc, String bzmc, int pageIndex, int pageSize, String sortField, String sortOrder) {

        String sql = "SELECT * FROM JGOBJECT_JYJCNL WHERE 1=1 AND ZZRDZSBH like '%" + zsbh + "%' ";

        if (!StringUtil.isNullOrEmpty(lb) && !lb.equals("null")) {
            sql += " AND LEIB like '%" + lb + "%'";
        }
        if (!StringUtil.isNullOrEmpty(cpmc) && !cpmc.equals("null")) {
            sql += " AND CPMC like '%" + cpmc + "%'";
        }
        if (!StringUtil.isNullOrEmpty(bzmc) && !bzmc.equals("null")) {
            sql += " AND YJBZMC like '%" + bzmc + "%'";
        }
        // 排序
        if (!StringUtil.isNullOrEmptyZf(sortField)) {
            if (!"desc".equals(sortOrder))
                sortOrder = "asc";
            sql += " ORDER BY " + sortField + " " + sortOrder;
        } else {
            sql += " ORDER BY CPXH ASC";
        }

        Connection con = getSession().connection();
        ArrayList list = JdbcManipulation.executeSqlQueryToList(con, sql);

        HashMap result = new HashMap();
        result.put("data", list);
        result.put("total", list.size());
        return result;
    }

    @Override
    public void deleteJyjcnlById(Long id) {
        StringBuffer sb = new StringBuffer("DELETE FROM JGOBJECT_JYJCNL WHERE CID=:id");
        SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString());
        sqlQuery.setLong("id", id);
        sqlQuery.executeUpdate();
    }

    @Override
    public Long selectMaxSotrNum() {
        StringBuffer sb = new StringBuffer("SELECT MAX(SORTNUM) FROM JGOBJECT_JYJCNL");
        SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString());
        String result = (String) sqlQuery.uniqueResult();
        sqlQuery.executeUpdate();
        return Long.valueOf(result);
    }
}
