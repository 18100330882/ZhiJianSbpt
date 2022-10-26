package com.yongjie.ZhiJianSbpt.dao.impl;

import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.dao.YjFlowInstLogDao;
import com.yongjie.ZhiJianSbpt.model.YjFlowInstLog;
import com.yongjie.ZhiJianSbpt.utilities.JdbcManipulation;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;


@Repository(YjFlowInstLogDao.SERVICE_NAME)
public class YjFlowInstLogDaoImpl extends BaseDaoImpl<YjFlowInstLog>
        implements YjFlowInstLogDao {

    @Override
    public HashMap getFlowInstLogList(long flowInstId, String serialNumber, String opType, String sortField, String sortOrder, int pageIndex, int pageSize) throws Exception {
        Connection connection = getSession().connection();
        opType = StringUtil.replaceSQLInjectChar(opType, true);
        String sql = "select * from( select t.* from （select c.id, c.OPTYPE,c.CAOR as CAOR,c.TRUENAME as BANLIR,c.SUGGESTION,c.CAODATE as BANLIDATE,"
                + "c.FLOWINSTID,d.TRUENAME as DAIBANR,c.OPERATIONMEMO as BANLIOPERATION,d.OPERATIONMEMO DAIBANOPERATION," +
                "CASE WHEN d.OPERATIONMEMO IS NULL THEN(SELECT listagg ( ac.TRUENAME, ',' ) within GROUP ( ORDER BY ac.TRUENAME ) " +
                "FROM ACCOUNTXZSP ac INNER JOIN yjworkitem yj ON yj.NODEINSTID = " +
                "( SELECT n.id FROM yjNodeInst n WHERE n.flowinstid = " + flowInstId + " AND n.currentState IN ( 0, 5, 6, 9 ) and n.id =(SELECT max(id) FROM yjNodeInst n WHERE n.flowinstid = " + flowInstId + " AND n.currentState IN ( 0, 5, 6, 9 ) )) " +
                "AND ac.username = yj.PARTICIPANT) END PARTICIPANTS," +
                "CASE WHEN d.OPERATIONMEMO IS NULL THEN(" +
                "SELECT n.NODEINSTNAME FROM yjNodeInst n WHERE n.flowinstid = " + flowInstId + " AND n.currentState IN ( 0, 5, 6, 9 ) and n.id =(SELECT max(id) FROM yjNodeInst n WHERE n.flowinstid = " + flowInstId + " AND n.currentState IN ( 0, 5, 6, 9 ) )) ELSE d.OPERATIONMEMO END DAIBANOPERATION2," +
                " de.DEPTNAME HANDLINGDEPARTMENT "
                + "from ( select ROW_NUMBER() OVER (ORDER BY a.id ASC) AS xh, a.* from yjFlowInstLog a "
                + "where flowInstId=" + flowInstId + " and opType in( " + opType + ")) c "
                + "left join (select ROW_NUMBER() OVER (ORDER BY a.id ASC) AS xh, a.* from yjFlowInstLog a "
                + "where flowInstId=" + flowInstId + " and opType in (" + opType + ") and OPERATIONMEMO !='已归档退回') d on d.xh=(c.xh+1) left join ACCOUNTXZSP ac on ac.username = c.caor left join dept de on de.id = ac.deptid )t"
                + " UNION all SELECT id, 0 as OPTYPE,CAOR,lxr AS BANLIR, REASON as SUGGESTION,CAODATE as BANLIDATE,FLOWINSTID,caor as DAIBANR,'退回企业修改' as BANLIOPERATION,'' as DAIBANOPERATION,'' as PARTICIPANT,'' as DAIBANOPERATION2, lxr AS HANDLINGDEPARTMENT  from  YJFLOWINSTFKXX  where  serialNumber = '" + serialNumber + "')s";
        if (!StringUtil.isNullOrEmpty(sortField)) {
            if (!sortOrder.equals("desc")) {
                sortOrder = "asc";
            }
            sortField = StringUtil.replaceSQLInjectChar(sortField, true);
            sql += " order by s." + sortField + " " + sortOrder;
        } else {
            sql += " s.BANLIDATE desc ";
        }

        ArrayList list = JdbcManipulation.executeSqlQueryToList(connection, sql);
        HashMap result = new HashMap<>();
        result.put("data", list);
        result.put("total", list.size());
        return result;
    }
}
