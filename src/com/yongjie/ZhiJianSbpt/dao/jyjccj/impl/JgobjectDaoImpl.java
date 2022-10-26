package com.yongjie.ZhiJianSbpt.dao.jyjccj.impl;

import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.dao.jyjccj.JgobjectDao;
import com.yongjie.ZhiJianSbpt.model.JgObject;
import com.yongjie.ZhiJianSbpt.utilities.R;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository(JgobjectDao.SERVICE_NAME)
public class JgobjectDaoImpl extends BaseDaoImpl<JgObject> implements JgobjectDao {

    @Override
    public Map getByMcAndShxydm(Map map) {
        Map res = new HashMap<>();
        String qymc = StringUtil.isNullOrEmpty(map.get("qymc")) ? null : map.get("qymc").toString();
        String shxydm = StringUtil.isNullOrEmpty(map.get("shxydm")) ? null : map.get("shxydm").toString();
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM JGOBJECT WHERE JGOBJECTNAME=:qymc AND SHXYDM=:shxydm");
        SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString());
        sqlQuery.setString("qymc", qymc);
        sqlQuery.setString("shxydm", shxydm);
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        if (list == null || list.size() < 1) {
            res.put(R.TOTAL_NAME, 0);
            res.put(R.DATA_NAME, null);
        } else {
            res.put(R.TOTAL_NAME, 1);
            res.put(R.DATA_NAME, list.get(0));
        }
        return res;
    }

    public Map getByMcAndShxydm2(Map map) {
        Map res = new HashMap<>();
        String qymc = StringUtil.isNullOrEmpty(map.get("qymc")) ? null : map.get("qymc").toString();
        String shxydm = StringUtil.isNullOrEmpty(map.get("shxydm")) ? null : map.get("shxydm").toString();
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT \n" +
                "JGOBJECTNAME as  jgmc,\n" +
                "ZHUSUO as  registeradress,\n" +
                "YZBM as  postcode,\n" +
                "CZ as  fax,\n" +
                "EMAIL as  email,\n" +
                "JGFZR as  principal,\n" +
                "JGFZRZW as position,\n" +
                "JGFZRDH as telephone,\n" +
                "JGFZRPHONE as phone,\n" +
                "JGLLR as contactPerson,\n" +
                "JGLLRZW as contactPosition,\n" +
                "JGRLLRDH as contactTelephone,\n" +
                "JGLLRPHONE as contactPhone,\n" +
                "SHXYDM as shxydm,\n" +
                "SSFRDWMC as legalunitName,\n" +
                "SSFRDZ as legalunitAdress,\n" +
                "LXR as legalPrincipal,\n" +
                "FDDBRZW as legalposition,\n" +
                "LXDH as legalphone,\n" +
                "SSFRSHXYDM as legalshxydm,\n" +
                "ZGBM as competentunitName,\n" +
                "ZGBMDZ as competentunitAdress,\n" +
                "ZGBMLXR as competentPrincipal,\n" +
                "ZGBMZW as competentposition,\n" +
                "ZGBMDH as competentphone,\n" +
                "JGSSTD as CHARACTERISTIC,\n" +
                "DLJGFRLB as LEGALPERSONJGTYPE,\n" +
                "FDLJGFRLB as LEGALPERSONTYPE\n" +
                "FROM JGOBJECT  WHERE JGOBJECTNAME=:qymc AND SHXYDM=:shxydm");
        SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString());
        sqlQuery.setString("qymc", qymc);
        sqlQuery.setString("shxydm", shxydm);
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        if (list == null || list.size() < 1) {
            res.put(R.TOTAL_NAME, 0);
            res.put(R.DATA_NAME, null);
        } else {
            res.put(R.TOTAL_NAME, 1);
            res.put(R.DATA_NAME, list.get(0));
        }
        return res;
    }

}
