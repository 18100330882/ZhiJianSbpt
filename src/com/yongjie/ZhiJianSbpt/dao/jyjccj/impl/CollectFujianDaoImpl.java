package com.yongjie.ZhiJianSbpt.dao.jyjccj.impl;

import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.dao.jyjccj.CollectFujianDao;
import com.yongjie.ZhiJianSbpt.model.jyjccj.CollectFujian;
import com.yongjie.ZhiJianSbpt.module.util.R;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository(CollectFujianDao.SERVICE_NAME)
public class CollectFujianDaoImpl extends BaseDaoImpl<CollectFujian> implements CollectFujianDao {


    @Override
    public Map<String, Object> ckfj(Map<String, Object> map) {
        Map<String, Object> resultMap = new HashMap<>();
        StringBuffer sb = new StringBuffer();
        sb.append(" select * from JYJC_COLLECTFUJIAN f where f.SERIALNUMBER in (select SERIALNUMBER from JYJC_COLLECT where FLAG =0) and f.ZSBH=:zsbh and f.SERIALNUMBER=:serialNumber and f.STATE=:state  ");
        String state = map.get("state") == null ? null : map.get("state").toString();
        String serialNumber = map.get("serialNumber") == null ? null : map.get("serialNumber").toString();
        String zsbh = map.get("zsbh") == null ? null : map.get("zsbh").toString();
        SQLQuery sqlQuery = getSession().createSQLQuery(sb.toString());
        sqlQuery.setString("state", state);
        sqlQuery.setString("serialNumber", serialNumber);
        sqlQuery.setString("zsbh", zsbh);
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        resultMap.put(R.DATA_NAME, list);
        return resultMap;
    }

}
