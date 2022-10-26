package com.yongjie.ZhiJianSbpt.dao.impl;

import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.dao.ShiYsJyjcFuJianDao;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcQzr;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcSqlx;
import com.yongjie.ZhiJianSbpt.model.ShiYsJyjcFuJian;
import com.yongjie.ZhiJianSbpt.model.flowBase.ApiFile;
import com.yongjie.ZhiJianSbpt.model.flowBase.ApiFujianType;
import com.yongjie.ZhiJianSbpt.utilities.R;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;


@Repository(ShiYsJyjcFuJianDao.SERVICE_NAME)
public class ShiYsJyjcFuJianDaoImpl extends BaseDaoImpl<ShiYsJyjcFuJian> implements ShiYsJyjcFuJianDao {

    @Override
    public HashMap<String, Object> queryListBySerialNumber(HashMap<Object, Object> reqMap) {
        String serialNumber = (String) reqMap.get("serialNumber");
        String flag = (String) reqMap.get("flag");

        StringBuffer selectSql = new StringBuffer();
        selectSql.append("SELECT AF.NAME,AF.FLAG,S.* FROM API_FUJIANTYPE AF ");
        selectSql.append(" LEFT JOIN SHIYSJYJCFUJIAN S ON S.SERIALNUMBER = '" + serialNumber + "' AND S.SQSID = AF.ID ");
        selectSql.append(" WHERE AF.FLAG = '" + flag + "'");

        SQLQuery sqlQuery = getSession().createSQLQuery(selectSql.toString());
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<ShiYsJyjcFuJian> list = sqlQuery.list();
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put(R.DATA_NAME, list);
        return resultMap;
    }

    @Override
    public ShiYsJyjcFuJian getSqsFujian(String serialNumber) {
        StringBuffer selectSql = new StringBuffer();

        selectSql.append("select * from SHIYSJYJCFUJIAN where 1=1 ");


        if (!StringUtil.isEmpty(serialNumber)) {
            selectSql.append(" and serialNumber=:serialNumber ");
        }
        SQLQuery sqlQuery = getSession().createSQLQuery(selectSql.toString()).addEntity(ShiYsJyjcFuJian.class);

        if (!StringUtil.isEmpty(serialNumber)) {
            sqlQuery.setString("serialNumber", serialNumber);
        }
        return (ShiYsJyjcFuJian) sqlQuery.uniqueResult();
    }

    @Override
    public ApiFujianType queryFuJIanTypeByFlag(String flag) {
        if (StringUtil.isNullOrEmpty(flag)) {
            return null;
        }
        StringBuffer sbf = new StringBuffer();
        sbf.append("SELECT * FROM API_FUJIANTYPE WHERE flag='" + flag + "'");
        SQLQuery sqlQuery = getSession().createSQLQuery(sbf.toString()).addEntity(ApiFujianType.class);
        return (ApiFujianType) sqlQuery.uniqueResult();
    }

    @Override
    public ShiYsJyjcFuJian queryBySerialNumber(String serialNumber) {
        if (StringUtil.isNullOrEmpty(serialNumber)) {
            return null;
        }
        StringBuffer sbf = new StringBuffer();
        sbf.append("SELECT * FROM SHIYSJYJCFUJIAN WHERE serialNumber='" + serialNumber + "'");
        SQLQuery sqlQuery = getSession().createSQLQuery(sbf.toString()).addEntity(ShiYsJyjcFuJian.class);
        return (ShiYsJyjcFuJian) sqlQuery.uniqueResult();
    }

    @Override
    public List<ShiYsJyjcFuJian> queryByBatchSerialNumber(String serialNumber, String flag) {
        StringBuffer selectSql = new StringBuffer();

        selectSql.append("select * from SHIYSJYJCFUJIAN where serialNumber = :serialNumber  and flag =:flag ");

        SQLQuery selectQuery = getSession().createSQLQuery(selectSql.toString()).addEntity(ShiYsJyjcFuJian.class);

        if (!StringUtil.isEmpty(serialNumber)) {
            selectQuery.setString("serialNumber", serialNumber);
        }
        if (!StringUtil.isEmpty(flag)) {
            selectQuery.setString("flag", flag);
        }

        return (List<ShiYsJyjcFuJian>) selectQuery.list();
    }


    @Override
    public int deleteBatchBySerialNumber(String serialNumber) throws Exception {

        if (StringUtil.isEmpty(serialNumber)) {
            return 0;
        }
        StringBuffer selectSql = new StringBuffer();
        selectSql.append("delete from SHIYSJYJCFUJIAN where serialNumber=:serialNumber ");

        SQLQuery selectQuery = getSession().createSQLQuery(selectSql.toString());
        selectQuery.setString("serialNumber", serialNumber);

        return selectQuery.executeUpdate();
    }
}
