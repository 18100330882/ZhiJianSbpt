package com.yongjie.ZhiJianSbpt.dao.shiYsJyjc.impl;

import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.dao.shiYsJyjc.ApiFujianTypeDao;
import com.yongjie.ZhiJianSbpt.model.flowBase.ApiFujianType;
import com.yongjie.ZhiJianSbpt.module.util.R;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import com.yongjie.ZhiJianSbpt.utilities.SysFinalRecource;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(ApiFujianTypeDao.SERVICE_NAME)
public class ApiFujianTypeDaoImpl extends BaseDaoImpl<ApiFujianType> implements ApiFujianTypeDao {

    @Override
    public ArrayList getYjFuJianList(long flowId, String sqsType, String statue) throws Exception {
        String sql = "SELECT * FROM API_FUJIANTYPE WHERE FLOWID= " + flowId;
        sql += " AND SQSTYPE = :sqsType ";
        if (!StringUtil.isNullOrEmptyZf(statue)) {
            sql += " AND (STATUE= :statue OR STATUE IS NULL) ";
        }
        sql += " ORDER BY PAIXU ASC ";
        SQLQuery query = getSession().createSQLQuery(sql);
        query.setString("sqsType", sqsType);
        if (!StringUtil.isNullOrEmptyZf(statue)) {
            query.setString("statue", statue);
        }
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return (ArrayList) query.list();
    }


    @Override
    public String getFuJianListId(long flowId, String sqsType, String fileName) {
        String sql = "select * from API_FUJIANTYPE where flowId=" + flowId;
        if (!com.yongjie.ZhiJianSbpt.utilities.StringUtil.isNullOrEmptyZf(sqsType)) {
            switch (SysFinalRecource.USE_DB_NAME) {
                case "SQLSERVER":
                    sql += " and charindex(:sqsType,sqsType)>0";
                    break;
                case "DM":
                case "ORACLE":
                    sql += " and instr(sqsType,:sqsType)>0";
                    break;
                default:
                    break;
            }
        }
        if (!com.yongjie.ZhiJianSbpt.utilities.StringUtil.isNullOrEmptyZf(fileName)) {
            sql += "  and Name=:Name ";
        }
        sql += " order by paixu ";
        SQLQuery query = getSession().createSQLQuery(sql);
        if (!com.yongjie.ZhiJianSbpt.utilities.StringUtil.isNullOrEmptyZf(sqsType)) {
            query.setString("sqsType", sqsType);
        }
        if (!StringUtil.isNullOrEmptyZf(fileName))
            query.setString("Name", fileName);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> list = query.list();
        if (list != null && list.size() > 0) {
            return list.get(0).get("ID").toString();
        }
        return "0";

    }

}
