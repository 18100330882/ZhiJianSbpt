package com.yongjie.ZhiJianSbpt.dao.impl;

import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.dao.ApplyEndDao;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcCd;
import com.yongjie.ZhiJianSbpt.model.ApplyEnd;
import com.yongjie.ZhiJianSbpt.utilities.R;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository(ApplyEndDao.SERVICE_NAME)
public class ApplyEndDaoImpl extends BaseDaoImpl<ApplyEnd> implements ApplyEndDao {

    @Override
    public ApplyEnd queryBySerialNumber(String serialNumber) {
        if (StringUtil.isNullOrEmpty(serialNumber)) {
            return null;
        }
        StringBuffer sbf = new StringBuffer();
        sbf.append("SELECT * FROM APPLYEND WHERE id = (SELECT max(id) FROM APPLYEND where SERIALNUMBER = '" + serialNumber + "')");
        sbf.append(" ORDER BY APPLYDATE,ID DESC");
        SQLQuery sqlQuery = getSession().createSQLQuery(sbf.toString()).addEntity(ApplyEnd.class);
        return (ApplyEnd) sqlQuery.uniqueResult();
    }

    @Override
    public HashMap<String, Object> queryListBySerialNumber(HashMap reqMap) {
        HashMap<String, Object> resultMap = new HashMap<>();
        StringBuffer sql = new StringBuffer();
        String serialNumber = (String) reqMap.get("serialNumber");
        sql.append("SELECT bas.zzch_suggestion,bas.zzch_isagree,a.*");
        sql.append(" FROM APPLYEND a");
        sql.append(" LEFT JOIN BIZ_API_SHIYSJYJC bas on bas.SERIALNUMBER = a.SERIALNUMBER ");
        sql.append(" WHERE 1 = 1 ");
        sql.append(" AND a.SERIALNUMBER = '" + serialNumber + "' ");
        sql.append(" ORDER BY a.APPLYDATE,a.ID DESC");
        SQLQuery query = getSession().createSQLQuery(sql.toString());
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        List list = query.list();
        if (list != null && list.size() > 0) {
            resultMap.put(R.DATA_NAME, list.get(0));
        }

        return resultMap;
    }

    @Override
    public void deleteBySerialNum(String serialNum) {
        StringBuffer sql = new StringBuffer();
        sql.append("DELETE FROM APPLYEND");
        sql.append(" where serialnumber = '" + serialNum + "'");
        getSession().createSQLQuery(sql.toString()).executeUpdate();
    }
}
