package com.yongjie.ZhiJianSbpt.dao.flowBase.impl;

import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.dao.flowBase.IYjFlowNodesDao;
import com.yongjie.ZhiJianSbpt.model.Area;
import com.yongjie.ZhiJianSbpt.startFlow.model.YjFlowNodes;
import com.yongjie.ZhiJianSbpt.utilities.JdbcManipulation;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository(IYjFlowNodesDao.SERVICE_NAME)
public class YjFlowNodesDao extends BaseDaoImpl<Area> implements IYjFlowNodesDao {

    @Override
    public ArrayList getNodeMcByFlowInstId(long flowId, long flowInstId) {
        // TODO Auto-generated method stub
        String sql = "select n.* from yjFlowNodes n inner join yjNodeInst i on n.nodeId=i.nodeId where n.flowId=" + flowId + " and i.flowInstId=" + flowInstId + " and (i.currentState=0 or i.currentState=5 or i.currentState=6)";
        Connection connection = getSession().connection();
        ArrayList result = JdbcManipulation.executeSqlQueryToList(connection, sql);
        return result;
    }

    @Override
    public ArrayList getNodeMo(long flowId, long flowInstId) {
        // TODO Auto-generated method stub
        String sql = "select n.* from yjFlowNodes n inner join yjNodeInst i on n.nodeId=i.nodeId where n.flowId=" + flowId + " and i.flowInstId=" + flowInstId + " and (i.currentState=0 or i.currentState=5 or i.currentState=6)"
                + " and n.nodeweight>(select nodeweight from yjflownodes where nodeid='sppz' and flowid=69)";
        Connection connection = getSession().connection();
        ArrayList result = JdbcManipulation.executeSqlQueryToList(connection, sql);
        return result;
    }

    @Override
    public ArrayList getBizIdByflowId(long flowId) {
        // TODO Auto-generated method stub
        String sql = "select * from yjFlow where flowId=" + flowId;
        Connection connection = getSession().connection();
        ArrayList result = JdbcManipulation.executeSqlQueryToList(connection, sql);
        return result;
    }

    @Override
    public ArrayList getFlowNodesByNodeId(long flowId, String nodeId) {
        // TODO Auto-generated method stub
        String sql = "select * from yjFlowNodes where flowId=:flowId";
        if (!StringUtil.isNullOrEmpty(nodeId)) {
            sql += " and nodeId=:nodeId";
        }
        SQLQuery query = getSession().createSQLQuery(sql);
        query.setLong("flowId", flowId);
        if (!StringUtil.isNullOrEmpty(nodeId)) {
            query.setString("nodeId", nodeId);
        }
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return (ArrayList) query.list();
    }

    @Override
    public ArrayList isOptionEnable(long flowId, String nodeId, String type, String sortField, String sortOrder) {
        // TODO Auto-generated method stub
        String sql = " select * from yjFlowNodeOperations o where o.nodeId=:nodeId";
        if (!StringUtil.isNullOrEmpty(flowId)) {
            sql += " and o.flowId=:flowId";
        }
        if (!StringUtil.isNullOrEmpty(type)) {
            if (!type.equals("2")) {
                sql += " and o.opType=:type";
            }
        }
        if (!StringUtil.isNullOrEmpty(sortField)) {
            if (!sortOrder.equals("desc")) {
                sortOrder = "asc";
            }
            sql += " order by " + sortField + " " + sortOrder;
        } else {
            sql += " order by id desc ";
        }

        SQLQuery query = getSession().createSQLQuery(sql);
        query.setString("nodeId", nodeId);
        if (!StringUtil.isNullOrEmpty(flowId)) {
            query.setLong("flowId", flowId);
        }
        if (!StringUtil.isNullOrEmpty(type)) {
            if (!type.equals("2")) {
                query.setInteger("type", Integer.parseInt(type));
            }
        }
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return (ArrayList) query.list();
    }

    @Override
    public ArrayList getNodeInstList(long flowInstId, String nodeId, String state) {
        // TODO Auto-generated method stub
        String sql = "select * from YJNODEINST where flowinstid=:flowInstId";
        if (!StringUtil.isNullOrEmpty(nodeId)) {
            sql += " and nodeId=:nodeId";
        }
        if (!StringUtil.isNullOrEmpty(state)) {
            sql += " and CURRENTSTATE=:state";
        }
        SQLQuery query = getSession().createSQLQuery(sql);
        query.setLong("flowInstId", flowInstId);
        query.setString("nodeId", nodeId);
        query.setInteger("state", Integer.parseInt(state));
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return (ArrayList) query.list();
    }

    @Override
    public int updateCurrentState(String nodeInstId, int state) {
        // TODO Auto-generated method stub
        String sql = "update yjNodeInst  set currentState=:state where id=:nodeInstId";
        SQLQuery query = getSession().createSQLQuery(sql);
        query.setInteger("state", state);
        query.setLong("nodeInstId", Long.parseLong(nodeInstId));
        return query.executeUpdate();
    }

    @Override
    public int addNodeInst(long flowId, long flowInstId, String nodeId, String nodeName, String currentUser, int state) {
        // TODO Auto-generated method stub
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sql = "insert into yjNodeInst(FLOWID,FLOWINSTID,NODEID,NODEINSTNAME,"
                + "CURRENTSTATE,CAOR,CAODATE) values(" + flowId + "," + flowInstId + "," + "'" + nodeId + "'" + "," + "'" + nodeName + "'" + "," + state + "," + "'" + currentUser + "'" + "," + "'" + sdf.format(new Date()) + "')";
        int result = getSession().createSQLQuery(sql).executeUpdate();
        return result;
    }

    @Override
    public String getflowInstParticipant(long flowId, long flowInstId, String nodeId, String type) {
        // TODO Auto-generated method stub
        String sql = "select PARTICIPANT from yjflowinstparticipant where flowId=:flowId";
        if (!StringUtil.isNullOrEmpty(flowInstId)) {
            sql += " and flowInstId=:flowInstId";
        }
        if (!StringUtil.isNullOrEmpty(nodeId)) {
            sql += " and nodeId=:nodeId";
        }
        if (!StringUtil.isNullOrEmpty(type)) {
            sql += " and participanttype=:type";
        }
        SQLQuery query = getSession().createSQLQuery(sql);
        query.setLong("flowId", flowId);
        if (!StringUtil.isNullOrEmpty(flowInstId)) {
            query.setLong("flowInstId", flowInstId);
        }
        if (!StringUtil.isNullOrEmpty(nodeId)) {
            query.setString("nodeId", nodeId);
        }
        if (!StringUtil.isNullOrEmpty(type)) {
            query.setInteger("type", Integer.parseInt(type));
        }
        String result = query.uniqueResult() + "";
        return result;
    }

    @Override
    public int addWorkItem(long flowId, long flowInstId, String nodeInstId, String participant, String currentUser) {
        // TODO Auto-generated method stub
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sql = "insert into yjworkitem(FLOWID,FLOWINSTID,NODEINSTID,PARTICIPANT,"
                + "CURRENTSTATE,CAOR,CAODATE) values(" + flowId + "," + flowInstId + "," + nodeInstId + "," + "'" + participant + "'" + "," + 0 + "," + "'" + currentUser + "'" + "," + "'" + sdf.format(new Date()) + "')";
        int result = getSession().createSQLQuery(sql).executeUpdate();
        return result;
    }

    @Override
    public ArrayList getParameterList(long flowInstId, String paraName) {
        // TODO Auto-generated method stub
        String sql = "select * from YJFLOWINSTPARA where  flowinstid=:flowInstId";
        if (!StringUtil.isNullOrEmpty(paraName)) {
            sql += " and paraName=:paraName";
        }
        SQLQuery query = getSession().createSQLQuery(sql);
        query.setLong("flowInstId", flowInstId);
        if (!StringUtil.isNullOrEmpty(paraName)) {
            query.setString("paraName", paraName);
        }
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return (ArrayList) query.list();
    }

    @Override
    public int insert_Parameter(long flowInstId, String paraname, String paravalue, String caor, Date caodate) {
        // TODO Auto-generated method stub
        String sql = "insert into YJFLOWINSTPARA(FLOWINSTID,PARANAME,PARAVALUE,"
                + "CAOR,CAODATE) values(" + flowInstId + ",'" + paraname + "','" + paravalue + "','" + caor + "'" + ",sysdate)";
        int result = getSession().createSQLQuery(sql).executeUpdate();
        return result;
    }

    public int deleteYjFlowInstPara(long flowInstId, String paraName, String paraValue) {
        String sql = "delete from yjFlowInstPara where flowInstId=:flowInstId and paraName=:paraName ";
        if (!StringUtil.isNullOrEmptyZf(paraValue)) {
            sql += " and  paravalue=:paraValue ";
        }

        SQLQuery query = getSession().createSQLQuery(sql);

        query.setLong("flowInstId", flowInstId);
        query.setString("paraName", paraName);
        if (!StringUtil.isNullOrEmptyZf(paraValue))
            query.setString("paraValue", paraValue);

        int result = query.executeUpdate();
        return result;
    }

    @Override
    public ArrayList getNodeMcByFlowInstIdOnEnd(long flowId, long flowInstId, String state) {
        // TODO Auto-generated method stub
        String sql = "select * from (select n.* from yjFlowNodes n inner join yjNodeInst i on n.nodeId=i.nodeId where n.flowId=" + flowId + " and i.flowInstId=" + flowInstId;
        if (!StringUtil.isNullOrEmpty(state) && state.equals("null") == false) {
            sql += " and i.currentState in(" + state + ")";
        }
        sql += " and STARTOREND=2 order by i.id desc) where rownum = 1";
        Connection connection = getSession().connection();
        ArrayList result = JdbcManipulation.executeSqlQueryToList(connection, sql);
        return result;
    }

    @Override
    public ArrayList getYjFlow(String flowId) {
        // TODO Auto-generated method stub
        String sql = "select * from YJFLOW where flowId=:flowId";
        SQLQuery query = getSession().createSQLQuery(sql);
        query.setLong("flowId", Long.parseLong(flowId));
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return (ArrayList) query.list();
    }

    @Override
    public long startYjFlow(String flowInstName, long flowId, long sqsId, String currentUser, long shenHeAreaId)
            throws Exception {
        /*------------???????????????????????????---------------------*/
        String sql = "insert into YJFLOWINST (FLOWID,CURRENTSTATE,FLOWINSTNAME,CAOR,CAODATE) values(" + flowId + "," + 0 + ",'" + flowInstName + "','" + currentUser + "','" + new java.sql.Date(System.currentTimeMillis()) + "')";
        Connection conn = getSession().connection();
        JdbcManipulation.executeSqlUpdate(conn, sql);
        /*-------------------???????????????????????????begin----------------*/
        String sql1 = "select * from  YJFLOWINST where FLOWINSTNAME='" + flowInstName + "' and flowId=" + flowId + " and CURRENTSTATE=0 order by id desc";
        Connection conn1 = getSession().connection();
        ArrayList listYjFlowInst = JdbcManipulation.executeSqlQueryToList(conn1, sql1);
        // ??????????????????Id
        String flowInstId = "";
        if (listYjFlowInst.size() > 0) {
            HashMap mapYjFlowInst = (HashMap) listYjFlowInst.get(0);
            flowInstId = mapYjFlowInst.get("ID") == null ? "" : mapYjFlowInst.get("ID").toString();
        }
        //?????????????????????nodeId
        String sql2 = "select * from yjFlowNodes where flowId=" + flowId + " and startOrEnd=1";
        Connection conn2 = getSession().connection();
        ArrayList listNodeInst = JdbcManipulation.executeSqlQueryToList(conn2, sql2);
        // ??????????????????Id
        String nodeId = "";
        String nodemc = "";
        String participantTypes = "";
        String participant = "";
        String isareafilter = "";
        if (listNodeInst.size() > 0) {
            HashMap mapNodeInst = (HashMap) listNodeInst.get(0);
            nodeId = mapNodeInst.get("NODEID") == null ? "" : mapNodeInst.get("NODEID").toString();
            nodemc = mapNodeInst.get("NODEMC") == null ? "" : mapNodeInst.get("NODEMC").toString();
            participantTypes = mapNodeInst.get("PARTICIPANTTYPE") == null ? "" : mapNodeInst.get("PARTICIPANTTYPE").toString();
            participant = mapNodeInst.get("PARTICIPANT") == null ? "" : mapNodeInst.get("PARTICIPANT").toString();
            isareafilter = mapNodeInst.get("ISAREAFILTER") == null ? "" : mapNodeInst.get("ISAREAFILTER").toString();
        }
        // ?????????????????????
        String sql3 = "insert into YJNODEINST(FLOWID,CURRENTSTATE,FLOWINSTID,NODEID,NODEINSTNAME,CAOR,CAODATE) "
                + "values(" + flowId + "," + 0 + "," + flowInstId + ",'" + nodeId + "','" + nodemc + "','" + currentUser + "','" + new java.sql.Date(System.currentTimeMillis()) + "')";
        Connection conn3 = getSession().connection();
        JdbcManipulation.executeSqlUpdate(conn3, sql3);

        //?????????????????????nodeInstId
        String sql6 = "select * from YJNODEINST where flowId=" + flowId + " and FLOWINSTID=" + flowInstId + " and nodeId='" + nodeId + "'";
        Connection conn6 = getSession().connection();
        ArrayList listNode = JdbcManipulation.executeSqlQueryToList(conn6, sql6);
        String nodeInstId = "";
        if (listNode.size() > 0) {
            HashMap mapNodeInst = (HashMap) listNode.get(0);
            nodeInstId = mapNodeInst.get("ID") == null ? "" : mapNodeInst.get("ID").toString();
        }

        /*-------------------??????????????????begin------------------*/
        //???????????????????????????
        int participantType = Integer.parseInt(participantTypes);
        String usersStrs = "";
        if (participantType == 0) {// ??????
            // ??????roleId????????????id
            String userIds = getUserId(participant, shenHeAreaId, Integer.parseInt(isareafilter));// ??????Id
            usersStrs += userIds;
        } else if (participantType == 1) {// ??????
            // ????????????id ??????userId;
            String userIds = getUserIds(participant);
            usersStrs += userIds;
        } else if (participantType == 2) {// ??????
            usersStrs = participant;
        } else if (participantType == 3) {// ?????? ??????????????????????????????????????????
            usersStrs = getUserIdsByModel("YjFlowNodes", participant, flowId + "", participantType, flowInstId, shenHeAreaId, isareafilter);// ??????
        } else if (participantType == 4) {// ??????
            // ?????????????????????????????????
            usersStrs = getUserIdsByModel("YjFlowNodeOperations", participant, flowId + "", participantType, flowInstId,
                    shenHeAreaId, isareafilter);// ??????
        } else if (participantType == -1) {
            usersStrs = getUserIdsByModel("", participant, flowId + "", participantType, flowInstId, shenHeAreaId, isareafilter);// ??????
        }

        String[] userList = usersStrs.split(",");
        String participants = "";// ????????????
        if (userList.length > 0) {
            for (int i = 0; i < userList.length; i++) {
                if (!StringUtil.isNullOrEmpty(userList[i])) {
                    ArrayList modelList = getAccountById(Long.parseLong(userList[i]));
                    if (modelList.size() > 0) {
                        HashMap mapUser = (HashMap) modelList.get(0);
                        String userName = mapUser.get("USERNAME") == null ? "" : mapUser.get("USERNAME").toString();
                        participants += userName + ",";
                    }
                }
            }
        }
        if (participants.length() > 0) {
            participants = participants.substring(0, participants.length() - 1);
        }

        String[] userNamelist = participants.split(",");// ???????????????
        for (int j = 0; j < userNamelist.length; j++) {
            // ???????????????
            addWorkItem(flowId, flowInstId, nodeInstId, userNamelist[j], currentUser);
        }
        /*---------???????????????------------*/
        // ????????????????????????
        addFlowInstLog(flowId, flowInstId, nodeInstId, nodemc, currentUser);

        return Long.parseLong(flowInstId);
    }

    public String getUserId(String roleIds, long shenHeAreaId, Integer isAreaFilter) {
        String[] roleIdList = roleIds.split(",");
        String userIds = "";
        for (int i = 0; i < roleIdList.length; i++) {
            String ids = "";
            String roleId = roleIdList[i];
            String sql = "select distinct u.* from UsersInRoles u inner join accountXzsp a "
                    + " on a.id=u.userId where u.roleId=" + roleId + " and a.isLock=0";
            if (isAreaFilter == 0) {// 0?????????????????????
                sql += " and a.areaId=" + shenHeAreaId;
            }
            Connection conn = getSession().connection();
            ArrayList usersList = JdbcManipulation.executeSqlQueryToList(conn, sql);
            for (int j = 0; j < usersList.size(); j++) {
                HashMap userMap = (HashMap) usersList.get(j);
                String idUser = userMap.get("USERID") == null ? "" : userMap.get("USERID").toString();
                ids = idUser + ",";// ??????id
                if (!(',' + userIds).contains(',' + ids)) {
                    userIds += ids;
                }
            }
        }
        if (userIds.length() > 0) {
            userIds = userIds.substring(0, userIds.length() - 1);
        }
        return userIds;
    }

    public String getUserIds(String roleIds) {
        String[] deptIdS = roleIds.split(",");
        String userIds = "";
        for (int i = 0; i < deptIdS.length; i++) {
            String ids = "";
            String deptId = deptIdS[i];
            String sql = "select distinct * FROM AccountXzsp a where a.deptId=" + deptId + " and a.isLock=0";

            Connection conn = getSession().connection();
            ArrayList usersList = JdbcManipulation.executeSqlQueryToList(conn, sql);
            for (int j = 0; j < usersList.size(); j++) {
                HashMap userMap = (HashMap) usersList.get(j);
                String idUser = userMap.get("USERID") == null ? "" : userMap.get("USERID").toString();
                ids = idUser + ",";//??????id
                if (!userIds.contains(ids)) {
                    userIds += ids;
                }
            }
        }
        if (userIds.length() > 0) {
            userIds = userIds.substring(0, userIds.length() - 1);
        }
        return userIds;
    }


    public String getUserIdsByModel(String model, String ids, String flowId, int participantType, String flowInstId, long shenHeAreaId, String isAreaFilter) {
        String usersStrs = "";
        if (participantType == 0)// ??????
        {
            String roleIds = getParticipants(model, ids, flowId);
            // ??????roleId????????????id
            String userIds = getUserIds(roleIds, shenHeAreaId, Integer.parseInt(isAreaFilter));
            usersStrs += userIds;
        } else if (participantType == 1)// ??????
        {
            String deptIds = getParticipants(model, ids, flowId);// ??????Id
            // ????????????id ??????userId;
            String userIds = getUserIds(deptIds);
            usersStrs += userIds;
        } else if (participantType == 2)// ??????
        {
            usersStrs += getParticipants(model, ids, flowId);// ??????Id
        } else if (participantType == 3)// ??????
        {

            String type = getPartiType(model, ids, flowId);
            String participant = getParticipants(model, ids, flowId);// ??????Id.????????????nodeId??????????????????type=""
            if (Integer.parseInt(type) == 0 || Integer.parseInt(type) == 1 || Integer.parseInt(type) == 2) {
                usersStrs += getUserIdsByModel(model, ids, flowId, Integer.parseInt(type), flowInstId, shenHeAreaId, isAreaFilter);// ??????
            } else if (Integer.parseInt(type) == 3) {
                usersStrs += getUserIdsByModel("YjFlowNodes", participant, flowId, Integer.parseInt(type), flowInstId,
                        shenHeAreaId, isAreaFilter);// ??????
            } else if (Integer.parseInt(type) == 4) {
                usersStrs += getUserIdsByModel("YjFlowNodeOperations", participant, flowId, Integer.parseInt(type),
                        flowInstId, shenHeAreaId, isAreaFilter);// ??????
            } else if (Integer.parseInt(type) == -1) {
                // ???????????????????????????
                ArrayList partiList = getFlowInstParticipantList(Long.parseLong(flowId),
                        flowInstId, ids);
                if (partiList.size() > 0) {
                    HashMap mapPart = (HashMap) partiList.get(0);
                    String partiId = mapPart.get("ID") == null ? "" : mapPart.get("ID").toString();
                    String ParticipantType = mapPart.get("PARTICIPANTTYPE") == null ? "" : mapPart.get("PARTICIPANTTYPE").toString();
                    usersStrs += getUserIdsByModel("YjFlowInstParticipant", partiId,
                            flowId, Integer.parseInt(ParticipantType), flowInstId, shenHeAreaId, isAreaFilter);
                }
            }
        } else if (participantType == 4)// ??????
        {
            String type = getPartiType(model, ids, flowId);
            String participant = getParticipants(model, ids, flowId);
            if (Integer.parseInt(type) == 0 || Integer.parseInt(type) == 1 || Integer.parseInt(type) == 2) {
                usersStrs += getUserIdsByModel(model, ids, flowId, Integer.parseInt(type), flowInstId, shenHeAreaId, isAreaFilter);// ??????
            } else if (Integer.parseInt(type) == 3) {
                usersStrs += getUserIdsByModel("YjFlowNodes", participant, flowId, Integer.parseInt(type), flowInstId,
                        shenHeAreaId, isAreaFilter);
            } else {// type?????????4???
                usersStrs += getUserIdsByModel("YjFlowNodeOperations", participant, flowId, Integer.parseInt(type),
                        flowInstId, shenHeAreaId, isAreaFilter);// ??????
            }
        } else if (participantType == -1) {
            // ???????????????????????????
            if (!StringUtil.isNullOrEmpty(ids)) {
                // ???????????????????????????
                ArrayList partiList = getFlowInstParticipantList(Long.parseLong(flowId),
                        flowInstId, ids);
                if (partiList.size() > 0) {
                    HashMap mapPart = (HashMap) partiList.get(0);
                    String partiId = mapPart.get("ID") == null ? "" : mapPart.get("ID").toString();
                    String ParticipantType = mapPart.get("PARTICIPANTTYPE") == null ? "" : mapPart.get("PARTICIPANTTYPE").toString();
                    usersStrs += getUserIdsByModel("YjFlowInstParticipant", partiId,
                            flowId, Integer.parseInt(ParticipantType), flowInstId, shenHeAreaId, isAreaFilter);
                } else {
                    System.out.println(
                            "????????????:??????????????????-???????????????????????????????????????(??????:BpsService.java, ?????????:getUserIdsByModel,??????:com.yongjie.ZhiJianXzsp.service.flowBase.impl.BpsService)");
                }
            }
        }
        return usersStrs;
    }


    String getParticipants(String model, String ids, String flowId) {

        String sql = "select distinct participant from " + model + " where 1=1 and ";
        if (model == "YjFlowInstParticipant") {
            sql += "id=" + ids;// ???????????????
        } else if (model == "YjFlowNodes") {
            sql += "nodeId='" + ids + "' and flowId=" + flowId;
        } else if (model == "YjFlowNodeOperations") {
            sql += "id='" + ids + "'";
        }
        Connection conn = getSession().connection();
        ResultSet rs = null;
        try {
            rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                String participant = "";
                participant = rs.getString("participant");
                return participant;// ???????????????
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    public String getUserIds(String roleIds, long shenHeAreaId, int isAreaFilter) {
        String[] roleIdList = roleIds.split(",");
        String userIds = "";
        for (int i = 0; i < roleIdList.length; i++) {
            String ids = "";
            String roleId = roleIdList[i];
            String sql = "select distinct u.* from UsersInRoles u inner join accountXzsp a "
                    + " on a.id=u.userId where u.roleId=" + roleId + " and a.isLock=0";
            if (isAreaFilter == 0) {//0?????????????????????
                sql += " and a.areaId=" + shenHeAreaId;
            }
            Connection conn = getSession().connection();
            ArrayList usersList = JdbcManipulation.executeSqlQueryToList(conn, sql);
            for (int j = 0; j < usersList.size(); j++) {
                HashMap userMap = (HashMap) usersList.get(j);
                String idUser = userMap.get("USERID") == null ? "" : userMap.get("USERID").toString();
                ids = idUser + ",";//??????id
                if (!(',' + userIds).contains(',' + ids)) {
                    userIds += ids;
                }
            }
        }
        if (userIds.length() > 0) {
            userIds = userIds.substring(0, userIds.length() - 1);
        }
        return userIds;
    }

    public String getPartiType(String model, String ids, String flowId) {
        String sql = "select participantType from " + model + " where 1=1 and ";
        if (model == "YjFlowInstParticipant" || model == "YjFlowNodeOperations") {
            long id = Long.parseLong(ids);
            sql += "id=" + id;
        } else if (model == "YjFlowNodes") {
            sql += "nodeId='" + ids + "' and flowid=" + flowId;
        }

        Connection conn = getSession().connection();
        ResultSet rs = null;
        try {
            rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                String participantType = "";
                participantType = rs.getString("participantType");
                return participantType;// ?????????????????????
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    public ArrayList getFlowInstParticipantList(long flowId, String flowInstId, String nodeId) {
        String sql = "select * from YJFLOWINSTPARTICIPANT y where y.flowInstId=:flowInstId";
        if (flowId != 0) {
            sql += " and y.flowId=:flowId";
        }
        if (!StringUtil.isNullOrEmpty(nodeId)) {
            sql += " and y.nodeId=:nodeId";
        }
        SQLQuery query = getSession().createSQLQuery(sql);
        query.setLong("flowInstId", Long.parseLong(flowInstId));
        if (flowId != 0) {
            query.setLong("flowId", flowId);
        }
        if (!StringUtil.isNullOrEmpty(nodeId)) {
            query.setString("nodeId", nodeId);
        }
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return (ArrayList) query.list();
    }

    public ArrayList getAccountById(long id) {
        String sql = "select * FROM AccountXzsp a where a.id=" + id;
        Connection conn = getSession().connection();
        return JdbcManipulation.executeSqlQueryToList(conn, sql);
    }

    public int addWorkItem(long flowId, String flowInstId, String nodeInstId, String userName, String currentUser) {
        Date d = new Date();
        String sql = "insert into YJWORKITEM(FLOWID,FLOWINSTID,NODEINSTID,PARTICIPANT,CURRENTSTATE,CAOR,CAODATE)"
                + " values(" + flowId + "," + flowInstId + "," + nodeInstId + ",'" + userName + "','" + 0 + "','" + currentUser + "','" + new java.sql.Date(d.getTime()) + "')";
        Connection conn = getSession().connection();
        return JdbcManipulation.executeSqlUpdate(conn, sql);
    }

    public int addFlowInstLog(long flowId, String flowInstId, String nodeInstId, String nodeMc, String currentUser) {
        String sql1 = "select * from AccountXzsp where userName='" + currentUser + "'";
        Connection conn = getSession().connection();
        ArrayList listXzsp = JdbcManipulation.executeSqlQueryToList(conn, sql1);
        String trueName = "";
        if (listXzsp.size() > 0) {
            HashMap map = (HashMap) listXzsp.get(0);
            trueName = map.get("TRUENAME") == null ? "" : map.get("TRUENAME").toString();
        }
        Date d = new Date();
        String sql = "insert into YJFLOWINSTLOG(FLOWINSTID,NODEINSTID,OPERATIONNAME,OPERATIONMEMO,OPTYPE,SUGGESTION,TRUENAME,CAOR,CAODATE)"
                + " values(" + flowInstId + "," + nodeInstId + ",'" + nodeMc + "','????????????','1','??????','" + trueName + "','" + currentUser + "','" + new java.sql.Date(d.getTime()) + "')";
        Connection conn1 = getSession().connection();
        return JdbcManipulation.executeSqlUpdate(conn1, sql);
    }

    @Override
    public int addPara(String flowInstId, String paraName, String paraValue, String currentUser) {
        // TODO Auto-generated method stub
        Date d = new Date();
        String sql = "insert into YJFLOWINSTPARA(FLOWINSTID,PARANAME,PARAVALUE,CAOR,CAODATE)"
                + " values(" + flowInstId + ",'" + paraName + "'," + paraValue + ",'" + currentUser + "','" + new java.sql.Date(d.getTime()) + "')";
        Connection conn1 = getSession().connection();
        return JdbcManipulation.executeSqlUpdate(conn1, sql);
    }

    @Override
    public String getUserName(String userId) {
        String sql = "select * from ACCOUNTXZSP where id=:userId";
        SQLQuery query = getSession().createSQLQuery(sql);
        query.setInteger("userId", Integer.parseInt(userId));
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        ArrayList list = (ArrayList) query.list();
        String userName = "";
        if (list.size() > 0) {
            HashMap map = (HashMap) list.get(0);
            userName = map.get("USERNAME") == null ? "" : map.get("USERNAME").toString();
        }
        return userName;
    }

    @Override
    public int updateWorkItem(String flowInstId, String nodeInstId) {
        // TODO Auto-generated method stub
        String sql = "update YJWORKITEM  set currentState=9 where flowInstId=:flowInstId and nodeInstId=:nodeInstId";
        SQLQuery query = getSession().createSQLQuery(sql);
        query.setLong("flowInstId", Long.parseLong(flowInstId));
        query.setLong("nodeInstId", Long.parseLong(nodeInstId));
        return query.executeUpdate();
    }

    @Override
    public int addYjFlowInstLogWtqr(String flowInstId, String nodeInstId, String oprationName, String optype,
                                    String userName, String trueName) {
        // TODO Auto-generated method stub
        Date d = new Date();
        String sql = "insert into YJFLOWINSTLOG(FLOWINSTID,NODEINSTID,OPERATIONNAME,OPERATIONMEMO,OPTYPE,TRUENAME,CAOR,CAODATE)"
                + " values(" + flowInstId + "," + nodeInstId + ",'" + oprationName + "','??????????????????????????????','" + optype + "','" + trueName + "','" + userName + "','" + new java.sql.Date(d.getTime()) + "')";
        Connection conn1 = getSession().connection();
        return JdbcManipulation.executeSqlUpdate(conn1, sql);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> getYjflownodesByFlowId(String flowId) throws Exception {
        if (StringUtil.isNullOrEmpty(flowId)) {
            flowId = "-0";
        }
        String sql = "select nodeid,nodemc from yjFlowNodes where isEnable=0 and flowid =:flowId order by id asc";
        SQLQuery sqlQuery = getSession().createSQLQuery(sql);
        sqlQuery.setLong("flowId", Long.parseLong(flowId));
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return (List<Map<String, Object>>) sqlQuery.list();
    }

    @Override
    public YjFlowNodes selectFlowNodesByflowId(Long flowId, int startOrEnd) {
        String sql = "SELECT * from yjFlowNodes WHERE flowId=" + flowId + " and startOrEnd=" + startOrEnd;
        List<YjFlowNodes> list = getSession().createSQLQuery(sql).addEntity(YjFlowNodes.class).list();
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public YjFlowNodes getYjFlowNodesByNodeId(String nodeId, long flowId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT * FROM YJFLOWNODES y WHERE y.nodeId='" + nodeId + "' and y.flowId=" + flowId + "");
        SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString()).addEntity(YjFlowNodes.class);
        List<YjFlowNodes> nodelist = sqlQuery.list();
        if (nodelist.size() > 0) {
            return nodelist.get(0);
        }
        return null;
    }

    @Override
    public String getParticipants(String model, String ids, long flowId) {
        String sql = "SELECT DISTINCT participant FROM " + model + " WHERE 1=1 and ";
        if (model == "YjFlowInstParticipant") {
            sql += "id=" + ids;// ???????????????
        } else if (model == "YjFlowNodes") {
            sql += "nodeId='" + ids + "' and flowId=" + flowId;
        } else if (model == "YjFlowNodeOperations") {
            sql += "id='" + ids + "'";
        }
        Connection conn = getSession().connection();
        ResultSet rs = null;
        try {
            rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                String participant = "";
                participant = rs.getString("participant");
                return participant;// ???????????????
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    @Override
    public String getPartiType(String model, String ids, long flowId) {
        String sql = "SELECT participantType FROM " + model + " WHERE 1=1 and ";
        if (model == "YjFlowInstParticipant" || model == "YjFlowNodeOperations") {
            long id = Long.parseLong(ids);
            sql += "id=" + id;
        } else if (model == "YjFlowNodes") {
            sql += "nodeId='" + ids + "' and flowid=" + flowId;
        }

        Connection conn = getSession().connection();
        ResultSet rs = null;
        try {
            rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                String participantType = "";
                participantType = rs.getString("participantType");
                return participantType;// ?????????????????????
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return "";
    }
}
