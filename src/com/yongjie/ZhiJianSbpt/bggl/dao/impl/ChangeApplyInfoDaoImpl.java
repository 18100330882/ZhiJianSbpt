package com.yongjie.ZhiJianSbpt.bggl.dao.impl;

import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.bggl.dao.ChangeApplyInfoDao;
import com.yongjie.ZhiJianSbpt.bggl.model.ChangeApplyInfo;
import com.yongjie.ZhiJianSbpt.utilities.R;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AddressChangeDaoImpl
 * @Date 2022/3/11 16:53
 * @Version 1.0
 */
@Repository(ChangeApplyInfoDao.SERVICE_NAME)
public class ChangeApplyInfoDaoImpl extends BaseDaoImpl<ChangeApplyInfo> implements ChangeApplyInfoDao {
    @Override
    public Map<String, Object> queryBySerialNum(Map<String, Object> reqMap) {
        String serialNumber = (String) reqMap.get("serialNumber");
        Map<String, Object> result = new HashMap<>();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT s.* FROM");
        sql.append(" CHANGEAPPLYINFO s");
        sql.append(" where 1=1");
        sql.append(" and s.SERIALNUMBER = '" + serialNumber + "'");
        SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString()).addEntity(ChangeApplyInfo.class);
        List list = sqlQuery.list();
        if (list == null || list.size() < 1) {
            result.put("total", 0);
            return result;
        }
        result.put(R.DATA_NAME, (ChangeApplyInfo) list.get(0));
        result.put("total", list.size());
        return result;
    }

    @Override
    public void deleteBySerialNum(String serialNumber) {
        StringBuffer deleteSql = new StringBuffer();
        deleteSql.append("DELETE FROM CHANGEAPPLYINFO JJ");
        deleteSql.append("  WHERE JJ.SERIALNUMBER = '");
        deleteSql.append(serialNumber);
        deleteSql.append("'");
        getSession().createSQLQuery(deleteSql.toString()).executeUpdate();
    }

    @Override
    public Map<String, Object> nlDetailsByzsbh(Map<String, Object> map) {
        StringBuffer sql = new StringBuffer();
        String zzrdzsbh = (String) map.get("zzrdzsbh");
        String changeType = (String) map.get("changeType");
        sql.append(" SELECT * FROM API_SHIYSJYJC_NL where 1=1");
        sql.append(" and (flag = 0 or flag is not null)");
        sql.append(" and zsbh = '" + zzrdzsbh + "'");
        if (!StringUtil.isNullOrEmpty(changeType)) {
            if ("11".equals(changeType)) {
                sql.append(" and id NOT IN ( SELECT JGOBJECTNLID FROM STANDARDCHANGEDETAILS WHERE  ZSBH = '" + zzrdzsbh + "' AND JGOBJECTNLID IS NOT NULL ) ");
            } else if ("15".equals(changeType)) {
                sql.append(" and id NOT IN ( SELECT JGOBJECTNLID FROM CANCELNLDETAILS WHERE  CMAPERMITNUM = '" + zzrdzsbh + "' AND JGOBJECTNLID IS NOT NULL ) ");
            }
        }
        sql.append(" ORDER BY ID asc");

        SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString());
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put(R.DATA_NAME, list);
        return resultMap;
    }

    @Override
    public Map<String, Object> personDetailsByzsbh(Map<String, Object> map) {
        StringBuffer sql = new StringBuffer();
        String zzrdzsbh = (String) map.get("zzrdzsbh");
        sql.append(" SELECT * FROM JGOBJECT_JYJCRY where 1=1");
        sql.append(" and flag = 2");
        sql.append(" and zsbh = '" + zzrdzsbh + "'");
        sql.append(" ORDER BY ID ASC");

        SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString());
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put(R.DATA_NAME, list);
        return resultMap;
    }
}
