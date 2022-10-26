package com.yongjie.ZhiJianSbpt.bggl.dao.impl;

import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.bggl.dao.SqqzrChangeDetailsDao;
import com.yongjie.ZhiJianSbpt.bggl.model.SqqzrChangeDetails;
import com.yongjie.ZhiJianSbpt.utilities.R;
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
@Repository(SqqzrChangeDetailsDao.SERVICE_NAME)
public class SqqzrChangeDetailsDaoImpl extends BaseDaoImpl<SqqzrChangeDetails> implements SqqzrChangeDetailsDao {

    @Override
    public Map<String, Object> queryListBySerialNum(Map<String, Object> reqMap) {
        String serialNumber = (String) reqMap.get("serialNumber");
        Map<String, Object> result = new HashMap<>();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT * FROM SQQZRCHANGEDETAILS where 1=1 ");
        sql.append(" and SERIALNUMBER = '" + serialNumber + "'");
        sql.append(" order by id");
        SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString());
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        result.put(R.DATA_NAME, list);
        return result;
    }

    @Override
    public void deleteBySerialNum(String serialNumber) {
        StringBuffer deleteSql = new StringBuffer();
        deleteSql.append("DELETE FROM SQQZRCHANGEDETAILS JJ");
        deleteSql.append("  WHERE JJ.SERIALNUMBER = '" + serialNumber + "'");
        getSession().createSQLQuery(deleteSql.toString()).executeUpdate();
    }
}
