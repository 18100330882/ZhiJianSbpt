/**
 * @Author: 杨淑娟
 * @Createtime: 2017年1月9日 下午2:32:23
 * @Copyright: Copyright (c) 2016
 * @Company: 北京永杰友信科技有限公司
 * @Version：1.0
 * @Description:
 */
package com.yongjie.ZhiJianSbpt.dao.flowBase.impl;

import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.dao.flowBase.IYjFlowFuJianListDao;
import com.yongjie.ZhiJianSbpt.model.flowBase.YjFlow;
import com.yongjie.ZhiJianSbpt.utilities.JdbcManipulation;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import com.yongjie.ZhiJianSbpt.utilities.SysFinalRecource;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(IYjFlowFuJianListDao.SERVICE_NAME)
public class YjFlowFuJianListDao extends BaseDaoImpl<YjFlow> implements IYjFlowFuJianListDao {

    @Override
    public ArrayList getYjFuJianList(long flowId, String sqsType) throws Exception {
        String sql = "select * from yjFlowFuJianList where flowId= '4'"
                + " and sqsType = :sqsType order by paixu asc";
        SQLQuery query = getSession().createSQLQuery(sql);
        query.setString("sqsType", sqsType);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return (ArrayList) query.list();
    }


    @Override
    public String getFuJianListId(long flowId, String sqsType, String fileName) {
        String sql = "select * from yjFlowFuJianList where flowId=" + flowId;
        String id = "0";
        if (!StringUtil.isNullOrEmptyZf(sqsType)) {
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
        if (!StringUtil.isNullOrEmptyZf(fileName)) {
            sql += "  and fileName=:fileName ";
        }
        sql += " order by paixu ";
        SQLQuery query = getSession().createSQLQuery(sql);
        if (!StringUtil.isNullOrEmptyZf(sqsType)) {
            query.setString("sqsType", sqsType);
        }
        if (!StringUtil.isNullOrEmptyZf(fileName))
            query.setString("fileName", fileName);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> list = query.list();
        if (list != null && list.size() > 0) {
            return list.get(0).get("ID").toString();
        }
        return "0";

    }


}
