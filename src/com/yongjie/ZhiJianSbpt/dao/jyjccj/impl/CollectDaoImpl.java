package com.yongjie.ZhiJianSbpt.dao.jyjccj.impl;

import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.dao.jyjccj.CollectDao;
import com.yongjie.ZhiJianSbpt.model.JgObject;
import com.yongjie.ZhiJianSbpt.model.jyjccj.CjSqqzr;
import com.yongjie.ZhiJianSbpt.model.jyjccj.Collect;
import com.yongjie.ZhiJianSbpt.model.jyjccj.JgobjectJyjcRy;
import com.yongjie.ZhiJianSbpt.utilities.R;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(CollectDao.SERVICE_NAME)
public class CollectDaoImpl extends BaseDaoImpl<JgObject> implements CollectDao {

    @Override
    public List<Collect> collectList(Map map) {
        String zzrdzsbh = map.get("zzrdzsbh").toString();
        StringBuffer sb = new StringBuffer("SELECT * FROM JGOBJECT where ZZRDZSBH=:zzrdzsbh");
        SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString());
        sqlQuery.setString("zzrdzsbh", zzrdzsbh);
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        return list;
    }

    @Override
    public Map<String, Object> getNlFileList(HashMap<Object, Object> reqMap) {
        Map<String, Object> resultMap = new HashMap<>();
        String zsbh = (String) reqMap.get("zsbh");
        String id = (String) reqMap.get("id");
        String sortField = (String) reqMap.get("sortField");
        String sortOrder = (String) reqMap.get("sortOrder");
        StringBuffer selectSql = new StringBuffer("select * from API_SHIYSJYJC_NL where ZSBH=:zsbh AND COLLECTFUJIANID=:id");
        if (StringUtil.isNullOrEmpty(sortField) == false) {
            if ("desc".equals(sortOrder) == false)
                sortOrder = "asc";
            selectSql.append(" ORDER BY " + sortField + "  " + sortOrder);
        } else {
            selectSql.append(" ORDER BY ID ASC");
        }
        SQLQuery sqlQuery = getSession().createSQLQuery(selectSql.toString());
        sqlQuery.setString("zsbh", zsbh);
        sqlQuery.setString("id", id);
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        resultMap.put(R.DATA_NAME, list);
        return resultMap;
    }

    @Override
    public Map<String, Object> getQzrFileList(HashMap<Object, Object> reqMap) {
        Map<String, Object> resultMap = new HashMap<>();
        String zsbh = (String) reqMap.get("zsbh");
        String id = (String) reqMap.get("id");
        StringBuffer selectSql = new StringBuffer("select * from CJ_SQQZR where ZSBH=:zsbh AND COLLECTFUJIANID=:id");
        SQLQuery sqlQuery = getSession().createSQLQuery(selectSql.toString());
        sqlQuery.setString("zsbh", zsbh);
        sqlQuery.setString("id", id);
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        resultMap.put(R.DATA_NAME, list);
        return resultMap;
    }

    @Override
    public Map<String, Object> getRyFileList(HashMap<Object, Object> reqMap) {
        Map<String, Object> resultMap = new HashMap<>();
        String zsbh = (String) reqMap.get("zsbh");
        String id = (String) reqMap.get("id");
        StringBuffer selectSql = new StringBuffer("select * from JGOBJECT_JYJCRY where ZSBH=:zsbh AND COLLECTFUJIANID=:id");
        SQLQuery sqlQuery = getSession().createSQLQuery(selectSql.toString());
        sqlQuery.setString("zsbh", zsbh);
        sqlQuery.setString("id", id);
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        resultMap.put(R.DATA_NAME, list);
        return resultMap;
    }

    @Override
    public Map<String, Object> queryByZsbh(Map<String, Object> map) {
        String zsbh = (String) map.get("zsbh");
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM JGOBJECT where ZZRDZSBH=:zsbh");
        SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString());
        sqlQuery.setString("zsbh", zsbh);
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        if (StringUtil.isNullOrEmpty(list) || list.size() < 1) {
            return null;
        }
        return (Map) list.get(0);
    }


    @Override
    public void delnl(Map<String, Object> map) {
        StringBuffer sb = new StringBuffer("delete from JYJC_COLLECTFUJIAN where id =:id");
        StringBuffer sb2 = new StringBuffer("delete from API_SHIYSJYJC_NL where COLLECTFUJIANID=:id AND ZSBH=:zsbh");
        SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString());
        SQLQuery sqlQuery2 = getSession().createSQLQuery(sb2.toString());
        sqlQuery.setLong("id", Long.valueOf(map.get("id").toString()));
        sqlQuery2.setLong("id", Long.valueOf(map.get("id").toString()));
        sqlQuery2.setString("zsbh", map.get("zsbh").toString());
        sqlQuery.executeUpdate();
        sqlQuery2.executeUpdate();
    }

    @Override
    public void delqzr(Map<String, Object> map) {
        StringBuffer sb = new StringBuffer("delete from JYJC_COLLECTFUJIAN where id =:id");
        StringBuffer sb2 = new StringBuffer("delete from CJ_SQQZR where COLLECTFUJIANID=:id AND ZSBH=:zsbh");
        SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString());
        SQLQuery sqlQuery2 = getSession().createSQLQuery(sb2.toString());
        sqlQuery.setLong("id", Long.valueOf(map.get("id").toString()));
        sqlQuery2.setLong("id", Long.valueOf(map.get("id").toString()));
        sqlQuery2.setString("zsbh", map.get("zsbh").toString());
        sqlQuery.executeUpdate();
        sqlQuery2.executeUpdate();
    }

    @Override
    public void delry(Map<String, Object> map) {
        StringBuffer sb = new StringBuffer("delete from JYJC_COLLECTFUJIAN where id =:id");
        StringBuffer sb2 = new StringBuffer("delete from JGOBJECT_JYJCRY where COLLECTFUJIANID=:id AND ZSBH=:zsbh");
        SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString());
        SQLQuery sqlQuery2 = getSession().createSQLQuery(sb2.toString());
        sqlQuery.setLong("id", Long.valueOf(map.get("id").toString()));
        sqlQuery2.setLong("id", Long.valueOf(map.get("id").toString()));
        sqlQuery2.setString("zsbh", map.get("zsbh").toString());
        sqlQuery.executeUpdate();
        sqlQuery2.executeUpdate();
    }

    @Override
    public Collect getByIdSerialNumber(Map paramMap) {
        String zsbh = (String) paramMap.get("zsbh");
        StringBuffer sb = new StringBuffer("SELECT * FROM JGOBJECT where ZSBH=:ZSBH");
        SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString());
        sqlQuery.setString("ZSBH", zsbh);
        Collect o = (Collect) sqlQuery.addEntity(Collect.class).list().get(0);
        return o;
    }

    @Override
    public void delByZsbh(String zsbh) {
        StringBuffer sb1 = new StringBuffer("DELETE FROM API_SHIYSJYJC_NL where ZSBH=:zsbh");
        StringBuffer sb2 = new StringBuffer("DELETE FROM CJ_SQQZR where ZSBH=:zsbh");
        StringBuffer sb3 = new StringBuffer("DELETE FROM JGOBJECT_JYJCRY where ZSBH=:zsbh");
        StringBuffer sb4 = new StringBuffer("DELETE FROM JYJC_COLLECTFUJIAN where ZSBH=:zsbh");
        SQLQuery sqlQuery1 = getSession().createSQLQuery(sb1.toString());
        SQLQuery sqlQuery2 = getSession().createSQLQuery(sb2.toString());
        SQLQuery sqlQuery3 = getSession().createSQLQuery(sb3.toString());
        SQLQuery sqlQuery4 = getSession().createSQLQuery(sb4.toString());
        sqlQuery1.setString("zsbh", zsbh);
        sqlQuery2.setString("zsbh", zsbh);
        sqlQuery3.setString("zsbh", zsbh);
        sqlQuery4.setString("zsbh", zsbh);
        sqlQuery1.executeUpdate();
        sqlQuery2.executeUpdate();
        sqlQuery3.executeUpdate();
        sqlQuery4.executeUpdate();
    }

    @Override
    public Map<String, Object> ckfj(Map<String, Object> map) {
        Map<String, Object> resultMap = new HashMap<>();
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT\n" +
                "\t* \n" +
                "FROM\n" +
                "\tJYJC_COLLECTFUJIAN f \n" +
                "WHERE\n" +
                "\tf.ZSBH IN ( SELECT ZSBH FROM JGOBJECT ) \n" +
                "\tAND f.ZSBH =:zsbh \t\n" +
                "\tAND f.STATE =:state  ");
        String state = map.get("state") == null ? null : map.get("state").toString();
        String zsbh = map.get("zsbh") == null ? null : map.get("zsbh").toString();
        SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString());
        sqlQuery.setString("state", state);
        sqlQuery.setString("zsbh", zsbh);
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        resultMap.put(com.yongjie.ZhiJianSbpt.module.util.R.DATA_NAME, list);
        return resultMap;
    }

    @Override
    public Map cknl(Map<String, Object> map) {
        StringBuffer sb = new StringBuffer();
        Map result = new HashMap<>();
        String zzrdzsbh = map.get("zzrdzsbh") == null ? null : map.get("zzrdzsbh").toString();
        int pageIndex = map.get("pageIndex") == null ? 0 : Integer.valueOf(map.get("pageIndex").toString());
        int pageSize = map.get("pageSize") == null ? 50 : Integer.valueOf(map.get("pageSize").toString());
        String cp = map.get("cp") == null ? null : map.get("cp").toString();
        sb.append(" select * from API_SHIYSJYJC_NL where ZSBH =:zzrdzsbh ");
        if (!StringUtil.isNullOrEmpty(cp)) {
            sb.append(" and PRODUCTNAME like :cp ");
        }
        SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString());
        sqlQuery.setString("zzrdzsbh", zzrdzsbh);
        if (!StringUtil.isNullOrEmpty(cp)) {
            sqlQuery.setString("cp", "%" + cp + "%");
        }
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        List data = new ArrayList();
        int start = pageIndex * pageSize, end = start + pageSize;
        for (int i = 0, l = list.size(); i < l; i++)// 能获得数据
        {
            Map record = new HashMap();
            // 将对象转换成hashmap对象
            if (list.get(i) == null)
                continue;
            // 将对象转换成hashMap
            record = (Map) list.get(i);
            // 获取需要返回的当前页记录
            if (start <= i && i < end) {// 前台显示 现在不要分页
                data.add(record);
            }
        }
        result.put(R.DATA_NAME, data);
        result.put(R.TOTAL_NAME, list.size());
        return result;
    }

    @Override
    public CjSqqzr queryQzrBySfzh(Map<String, Object> map) {
        StringBuffer sb = new StringBuffer();
        String sfzh = map.get("sfzh") == null ? null : map.get("sfzh").toString();
        sb.append(" select * from CJ_SQQZR where SFZH =:sfzh ");
        SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString());
        sqlQuery.setString("sfzh", sfzh);
        if (sqlQuery.list() == null || sqlQuery.list().size() < 1) {
            return null;
        }
        return (CjSqqzr) sqlQuery.addEntity(CjSqqzr.class).list().get(0);
    }

    @Override
    public JgobjectJyjcRy queryRyBySfzh(Map<String, Object> map) {
        StringBuffer sb = new StringBuffer();
        String sfzh = map.get("sfzh") == null ? null : map.get("sfzh").toString();
        sb.append(" select * from JGOBJECT_JYJCRY where SFZH =:sfzh ");
        SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString());
        sqlQuery.setString("sfzh", sfzh);
        if (sqlQuery.list() == null || sqlQuery.list().size() < 1) {
            return null;
        }
        return (JgobjectJyjcRy) sqlQuery.addEntity(JgobjectJyjcRy.class).list().get(0);
    }

    @Override
    public List queryRyListByZsbh(Map<String, Object> map) {
        StringBuffer sb = new StringBuffer();
        String zsbh = map.get("zsbh") == null ? null : map.get("zsbh").toString();
        sb.append(" select * from JGOBJECT_JYJCRY where ZSBH =:zsbh ");
        SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString());
        sqlQuery.setString("zsbh", zsbh);
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return sqlQuery.list();
    }

    @Override
    public int checkFlagByZsbh(Map<String, Object> map) {
        StringBuffer sb = new StringBuffer();
        String zsbh = map.get("zsbh") == null ? null : map.get("zsbh").toString();
        sb.append(" select * from JGOBJECT where ZSBH =:zsbh AND FLAG=1  ");
        SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString());
        sqlQuery.setString("zsbh", zsbh);
        List list = sqlQuery.list();
        if (list == null || list.size() < 1) {
            return 0;
        }
        return list.size();
    }

    @Override
    public Map querySfzhByName(Map<String, Object> map) {
        StringBuffer sb = new StringBuffer();
        Map result = new HashMap<String, Object>();
        String name = map.get("name") == null ? null : map.get("name").toString();
        sb.append(" select * from JGOBJECT_JYJCRY where NAME =:name ");
        SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString());
        sqlQuery.setString("name", name);
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        if (list == null || list.size() < 1) {
            return null;
        }
        result.put(R.DATA_NAME, list);
        return result;
    }

    @Override
    public List queryQzrByZsbh(Map<String, Object> map) {
        StringBuffer sb = new StringBuffer();
        String zsbh = map.get("zsbh") == null ? null : map.get("zsbh").toString();
        sb.append(" select * from CJ_SQQZR where ZSBH =:zsbh ");
        SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString());
        sqlQuery.setString("zsbh", zsbh);
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return sqlQuery.list();
    }

    @Override
    public Map queryQzrSfzhByName(Map<String, Object> map) {
        StringBuffer sb = new StringBuffer();
        Map result = new HashMap<String, Object>();
        String name = map.get("name") == null ? null : map.get("name").toString();
        sb.append(" select * from CJ_SQQZR where QZRNAME =:name ");
        SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString());
        sqlQuery.setString("name", name);
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        if (list == null || list.size() < 1) {
            return null;
        }
        result.put(R.DATA_NAME, list);
        return result;
    }
}
