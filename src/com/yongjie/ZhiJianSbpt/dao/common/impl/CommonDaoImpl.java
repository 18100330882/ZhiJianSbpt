package com.yongjie.ZhiJianSbpt.dao.common.impl;

import com.mysql.jdbc.StringUtils;
import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.dao.common.CommonDao;
import com.yongjie.ZhiJianSbpt.utilities.R;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import org.apache.poi.ss.formula.functions.T;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(CommonDao.SERVICE_NAME)
public class CommonDaoImpl extends BaseDaoImpl<T> implements CommonDao {

    @Override
    public Map<String, Object> getShiysjyjcScType(Map<Object, Object> paramMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        StringBuffer sql = new StringBuffer();
        String keyName = (String) paramMap.get("keyName");
        sql.append("SELECT S.* FROM SHIYSJYJC_ZYTYPE S WHERE 1=1 ");
        if (!StringUtil.isNullOrEmpty(keyName)) {
            sql.append(" AND (S.TYPENAME LIKE '%" + keyName + "%')");
        }
        sql.append(" ORDER BY S.ID");
        SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString());
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        resultMap.put(R.DATA_NAME, list);
        return resultMap;
    }

    @Override
    public Map<String, Object> getSerialNumber() throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        StringBuffer sql = new StringBuffer("SELECT TO_CHAR(SEQ_SERIALNUMBERUSE.NEXTVAL) SERIALNUMBER FROM DUAL");
        SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString());
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        if (list.size() > 0) {
            HashMap map = (HashMap) list.get(0);
            resultMap.put(R.DATA_NAME, map.get("SERIALNUMBER"));
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> getZgbmData() {
        HashMap<String, Object> result = new HashMap<>();
        StringBuffer selectSql = new StringBuffer();
        selectSql.append("SELECT T.* FROM SHIYSJYJCZGBM T WHERE T.ISENABLED =0 ORDER BY ID ASC");
        SQLQuery sqlQuery = getSession().createSQLQuery(selectSql.toString());
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        result.put(R.DATA_NAME, list);
        return result;
    }
}
