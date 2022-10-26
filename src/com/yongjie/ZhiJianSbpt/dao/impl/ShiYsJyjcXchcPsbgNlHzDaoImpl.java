package com.yongjie.ZhiJianSbpt.dao.impl;


import com.mysql.jdbc.StringUtils;
import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.dao.ShiYsJyjcXchcPsbgNlHzDao;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcNl;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcNlSbpt;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcYqsbSbpt;
import com.yongjie.ZhiJianSbpt.model.ShiYsJyjcXchcPsbgNlHz;
import com.yongjie.ZhiJianSbpt.utilities.JdbcManipulation;
import com.yongjie.ZhiJianSbpt.utilities.R;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(ShiYsJyjcXchcPsbgNlHzDao.SERVICE_NAME)
public class ShiYsJyjcXchcPsbgNlHzDaoImpl extends BaseDaoImpl<ShiYsJyjcXchcPsbgNlHz> implements ShiYsJyjcXchcPsbgNlHzDao {

    @Override
    public ArrayList getListByFlowInstId(String serialNumber, String cdId, String calOrCma, String type, String nlOrSb, String isSp) throws Exception {
        String sql = "select * from ShiYsJyjcXchcPsbgNlHz where serialNumber='" + serialNumber + "'";
        if (!StringUtil.isNullOrEmptyZf(cdId)) {
            sql += " and cdId=" + cdId;
        }
        if (!StringUtil.isNullOrEmptyZf(calOrCma)) {
            sql += " and calOrCma='" + calOrCma + "'";
        }
        if (!StringUtil.isNullOrEmptyZf(type)) {
            sql += " and type=" + type;
        }
        if (!StringUtil.isNullOrEmptyZf(nlOrSb)) {
            if (nlOrSb.equals("0")) {
                sql += " and (nlOrSb=0 or nlOrSb is null or nlOrSb='' ) ";
            } else {
                sql += " and nlOrSb in (" + nlOrSb + ")";
            }
        }
        if (!StringUtil.isNullOrEmptyZf(isSp)) {
            sql += " and isSp='" + isSp + "'";
        }
        SQLQuery sqlQuery = getSession().createSQLQuery(sql);
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        ArrayList resultList = (ArrayList) sqlQuery.list();
        return resultList;
    }

    @Override
    public Map<String, Object> getNlFileList(HashMap<Object, Object> reqMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        String serialNumber = (String) reqMap.get("serialNumber");
        String nlorSb = (String) reqMap.get("nlorSb");
        StringBuffer selectSql = new StringBuffer();
        if (StringUtils.isNullOrEmpty(serialNumber)) {
            return null;
        }
        selectSql.append("SELECT * FROM SHIYSJYJCXCHCPSBGNLHZ WHERE 1=1");
        selectSql.append(" AND NLORSB = '" + nlorSb + "'");
        selectSql.append(" AND SERIALNUMBER ='" + serialNumber + "'");
        selectSql.append(" AND (ISSP = '食品' OR ISSP = '非食品')");
        SQLQuery sqlQuery = getSession().createSQLQuery(selectSql.toString());
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        resultMap.put(R.DATA_NAME, list);
        return resultMap;
    }

    @Override
    public Map<String, Object> getNlFileListByDoc(HashMap<Object, Object> reqMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        String serialNumber = (String) reqMap.get("serialNumber");
        String nlorsb = (String) reqMap.get("nlorsb");
        StringBuffer selectSql = new StringBuffer();
        if (StringUtils.isNullOrEmpty(serialNumber)) {
            return null;
        }
        selectSql.append("SELECT * FROM SHIYSJYJCXCHCPSBGNLHZ WHERE 1=1");
        selectSql.append(" AND SERIALNUMBER ='" + serialNumber + "'");
        selectSql.append(" AND NLORSB ='" + nlorsb + "'");
        selectSql.append(" AND COMEFROM = 'qysb'");
        SQLQuery sqlQuery = getSession().createSQLQuery(selectSql.toString());
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        resultMap.put(R.DATA_NAME, list);
        return resultMap;
    }

    @Override
    public ArrayList<Map<String, String>> listLeib(String serialNumber, String SITENAME, String status, String TYPENAME) {
        StringBuffer sbf = new StringBuffer();
        sbf.append("SELECT yjfl,max( SORTINGNUMBER ) SORTINGNUMBER FROM API_SHIYSJYJC_NLSBPT where 1=1");
        sbf.append(" AND flag = 0  AND yjfl IS NOT NULL ");
        sbf.append(" and SERIALNUMBER = '" + serialNumber + "'");
        sbf.append(" AND SITENAME = '" + SITENAME + "'");
        sbf.append(" AND status = '" + status + "'");
        sbf.append(" AND TYPENAME = '" + TYPENAME + "'");
        sbf.append(" GROUP BY yjfl ORDER BY TO_NUMBER( SORTINGNUMBER )");

        SQLQuery sqlQuery = getSession().createSQLQuery(sbf.toString());

        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return (ArrayList<Map<String, String>>) sqlQuery.list();
    }

    @Override
    public ArrayList<ApiShiYsJyjcNlSbpt> listCpmc1(String serialNumber, String yjfl, String SITENAME, String status, String TYPENAME) {
        if (StringUtil.isEmpty(serialNumber)) {
            return null;
        }
        StringBuffer sbf = new StringBuffer();
        sbf.append("select * from API_SHIYSJYJC_NLSBPT ")
                .append(" where SERIALNUMBER =:serialNumber and flag=0 ");
        if (!StringUtil.isEmpty(yjfl)) {
            sbf.append(" and yjfl =:yjfl ");
        }

        if (!StringUtil.isEmpty(status)) {
            sbf.append(" and status =:status ");
        }
        if (!StringUtil.isEmpty(SITENAME)) {
            sbf.append(" and SITENAME =:SITENAME ");
        }
        if (!StringUtil.isEmpty(TYPENAME)) {
            sbf.append(" and TYPENAME = '" + TYPENAME + "' ");
        }
        sbf.append(" order by id asc ");

        SQLQuery sqlQuery = getSession().createSQLQuery(sbf.toString()).addEntity(ApiShiYsJyjcNlSbpt.class);
        sqlQuery.setString("serialNumber", serialNumber);
        if (!StringUtil.isEmpty(yjfl)) {
            sqlQuery.setString("yjfl", yjfl);
        }

        if (!StringUtil.isEmpty(status)) {
            sqlQuery.setString("status", status);
        }
        if (!StringUtil.isEmpty(SITENAME)) {
            sqlQuery.setString("SITENAME", SITENAME);
        }

        return (ArrayList<ApiShiYsJyjcNlSbpt>) sqlQuery.list();
    }

    @Override
    public ArrayList<ApiShiYsJyjcYqsbSbpt> yqsblist(String serialNumber, String SITENAME, String status, String TYPENAME) {
        if (StringUtil.isEmpty(serialNumber)) {
            return null;
        }
        StringBuffer sbf = new StringBuffer();
        sbf.append("select * from API_SHIYSJYJC_YQSB_SBPT ");
        sbf.append(" where SERIALNUMBER =:serialNumber  and flag=1");
        sbf.append(" AND EJFL IS NOT NULL ");
        if (!StringUtil.isEmpty(status)) {
            sbf.append(" and status =:status ");
        }
        if (!StringUtil.isEmpty(SITENAME)) {
            sbf.append(" and SITENAME =:SITENAME ");
        }
        if (!StringUtil.isEmpty(TYPENAME)) {
            sbf.append(" and TYPENAME = '" + TYPENAME + "' ");
        }
        sbf.append(" ORDER BY ID ASC");

        SQLQuery sqlQuery = getSession().createSQLQuery(sbf.toString()).addEntity(ApiShiYsJyjcYqsbSbpt.class);
        sqlQuery.setString("serialNumber", serialNumber);

        if (!StringUtil.isEmpty(status)) {
            sqlQuery.setString("status", status);
        }
        if (!StringUtil.isEmpty(SITENAME)) {
            sqlQuery.setString("SITENAME", SITENAME);
        }

        return (ArrayList<ApiShiYsJyjcYqsbSbpt>) sqlQuery.list();
    }

    @Override
    public ArrayList<ApiShiYsJyjcYqsbSbpt> yqsblistToBeizhu(Map map) {
        String serialNumber = (String) map.get("serialNumber");
        String status = (String) map.get("status");
        String siteName = (String) map.get("siteName");
        String typeName = (String) map.get("typeName");
        StringBuffer sbf = new StringBuffer();
        sbf.append("SELECT * FROM API_SHIYSJYJC_YQSB_SBPT ");
        sbf.append(" where SERIALNUMBER ='" + serialNumber + "'  and flag=1");
        sbf.append(" AND REMARK IS NOT NULL ");
        if (!StringUtil.isEmpty(status)) {
            sbf.append(" and STATUS = " + status);
        }
        if (!StringUtil.isEmpty(siteName)) {
            sbf.append(" and SITENAME = '" + siteName + "'");
        }
        if (!StringUtil.isEmpty(typeName)) {
            sbf.append(" and TYPENAME = '" + typeName + "' ");
        }

        SQLQuery sqlQuery = getSession().createSQLQuery(sbf.toString()).addEntity(ApiShiYsJyjcYqsbSbpt.class);

        return (ArrayList<ApiShiYsJyjcYqsbSbpt>) sqlQuery.list();
    }
}
