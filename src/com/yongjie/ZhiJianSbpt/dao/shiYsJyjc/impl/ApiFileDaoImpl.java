package com.yongjie.ZhiJianSbpt.dao.shiYsJyjc.impl;

import com.yongjie.ZhiJianSbpt.base.BaseDaoImpl;
import com.yongjie.ZhiJianSbpt.dao.shiYsJyjc.ApiFileDao;
import com.yongjie.ZhiJianSbpt.model.flowBase.ApiFile;
import com.yongjie.ZhiJianSbpt.module.util.R;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(ApiFileDao.SERVICE_NAME)
public class ApiFileDaoImpl extends BaseDaoImpl<ApiFile> implements ApiFileDao {

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> listPage(Long flowId, String fileType, String serialNumber, String flag, int pageIndex, int pageSize) throws Exception {
        StringBuffer selectSql = new StringBuffer();
        StringBuffer countSql = new StringBuffer();

        selectSql.append("select af1.*,af2.name fileTypeName from API_FILE af1 LEFT JOIN API_FUJIANTYPE af2 ON af2.FLAG = af1.FLAG  where 1=1 ");
        countSql.append("select count(*) from API_FILE   where 1=1 ");

        if (!StringUtil.isEmpty(String.valueOf(flowId))) {
            selectSql.append(" and af1.flowId=:flowId ");
            countSql.append(" and flowId=:flowId ");
        }
        if (!StringUtil.isEmpty(fileType)) {
            selectSql.append(" and af1.fileType =:fileType ");
            countSql.append(" and fileType =:fileType ");
        }
        if (!StringUtil.isEmpty(serialNumber)) {
            selectSql.append(" and af1.serialNumber =:serialNumber ");
            countSql.append(" and serialNumber =:serialNumber ");
        }
        if (!StringUtil.isEmpty(flag)) {
            selectSql.append(" and af1.flag =:flag ");
            countSql.append(" and flag =:flag ");
        }
        if (StringUtil.isEmpty(flag)) {
            selectSql.append(" and af1.flag IS NOT NULL ");
            countSql.append(" and flag IS NOT NULL ");
        }
        selectSql.append(" ORDER BY af1.flag asc");

        //		SQLQuery selectQuery = getSession().createSQLQuery(selectSql.toString()).addEntity(ApiFile.class);
        SQLQuery selectQuery = getSession().createSQLQuery(selectSql.toString());
        SQLQuery counQuery = getSession().createSQLQuery(countSql.toString());

        if (!StringUtil.isEmpty(String.valueOf(flowId))) {
            selectQuery.setLong("flowId", flowId);
            counQuery.setLong("flowId", flowId);
        }
        if (!StringUtil.isEmpty(fileType)) {
            selectQuery.setString("fileType", fileType);
            counQuery.setString("fileType", fileType);
        }
        if (!StringUtil.isEmpty(serialNumber)) {
            selectQuery.setString("serialNumber", serialNumber);
            counQuery.setString("serialNumber", serialNumber);
        }
        if (!StringUtil.isEmpty(flag)) {
            selectQuery.setString("flag", flag);
            counQuery.setString("flag", flag);
        }

        if (pageIndex != -1 && pageSize != -1) {
            selectQuery.setFirstResult(pageIndex * pageSize);
            selectQuery.setMaxResults(pageSize);
        }
        selectQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> list = selectQuery.list();
        Number count = (Number) counQuery.uniqueResult();
        HashMap<String, Object> result = new HashMap<>();
        result.put(R.TOTAL_NAME, count == null ? 0 : count.intValue());
        result.put(R.DATA_NAME, list);
        return result;
    }

    @Override
    public Map<String, Object> wenShuList(Long flowId, String serialNumber, String flag, int pageIndex, int pageSize) throws Exception {
        StringBuffer selectSql = new StringBuffer();
        StringBuffer countSql = new StringBuffer();

        selectSql.append("SELECT * FROM YJFLOWINSTWENSHU  WHERE 1=1  AND NODEID != 'xchc' ");
        countSql.append("SELECT COUNT(*) FROM YJFLOWINSTWENSHU   WHERE 1=1 AND NODEID != 'xchc'");

        if (!StringUtil.isEmpty(String.valueOf(flowId))) {
            selectSql.append(" and flowId=:flowId ");
            countSql.append(" and flowId=:flowId ");
        }
        if (!StringUtil.isEmpty(serialNumber)) {
            selectSql.append(" and serialNumber =:serialNumber ");
            countSql.append(" and serialNumber =:serialNumber ");
        }
        if (!StringUtil.isEmpty(flag)) {
            selectSql.append(" and flag =:flag ");
            countSql.append(" and flag =:flag ");
        }
        selectSql.append(" ORDER BY ID asc");

        SQLQuery selectQuery = getSession().createSQLQuery(selectSql.toString());
        SQLQuery counQuery = getSession().createSQLQuery(countSql.toString());

        if (!StringUtil.isEmpty(String.valueOf(flowId))) {
            selectQuery.setLong("flowId", flowId);
            counQuery.setLong("flowId", flowId);
        }
        if (!StringUtil.isEmpty(serialNumber)) {
            selectQuery.setString("serialNumber", serialNumber);
            counQuery.setString("serialNumber", serialNumber);
        }
        if (!StringUtil.isEmpty(flag)) {
            selectQuery.setString("flag", flag);
            counQuery.setString("flag", flag);
        }

        if (pageIndex != -1 && pageSize != -1) {
            selectQuery.setFirstResult(pageIndex * pageSize);
            selectQuery.setMaxResults(pageSize);
        }
        selectQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> list = selectQuery.list();
        Number count = (Number) counQuery.uniqueResult();
        HashMap<String, Object> result = new HashMap<>();
        result.put(R.TOTAL_NAME, count == null ? 0 : count.intValue());
        result.put(R.DATA_NAME, list);
        return result;
    }

    @Override
    public ApiFile getApiFile(String unid, String serialNumber) {
        StringBuffer selectSql = new StringBuffer();

        selectSql.append("select * from API_FILE where 1=1 ");

        // unid 值传入到 filePath里面
        if (!StringUtil.isEmpty(unid)) {
            selectSql.append(" and filePath=:unid ");
        }
        if (!StringUtil.isEmpty(serialNumber)) {
            selectSql.append(" and serialNumber=:serialNumber ");
        }
        SQLQuery sqlQuery = getSession().createSQLQuery(selectSql.toString()).addEntity(ApiFile.class);

        if (!StringUtil.isEmpty(unid)) {
            sqlQuery.setString("unid", unid);
        }
        if (!StringUtil.isEmpty(serialNumber)) {
            sqlQuery.setString("serialNumber", serialNumber);
        }
        return (ApiFile) sqlQuery.uniqueResult();
    }

    @Override
    public List<ApiFile> getApiFileBySerialNumberFlag(String serialNumber, String flag) {
        StringBuffer selectSql = new StringBuffer();

        selectSql.append("select * from API_FILE where serialNumber = :serialNumber  ");
        if (!StringUtil.isEmpty(flag)) {
            selectSql.append(" and flag = :flag ");
        }
        SQLQuery selectQuery = getSession().createSQLQuery(selectSql.toString()).addEntity(ApiFile.class);

        if (!StringUtil.isEmpty(flag)) {
            selectQuery.setString("flag", flag);
        }
        if (!StringUtil.isEmpty(serialNumber)) {
            selectQuery.setString("serialNumber", serialNumber);
        }
        return (List<ApiFile>) selectQuery.list();
    }

    @Override
    public void deleApiFile(Long flowId, String unid, String serialNumber) {
        StringBuffer selectSql = new StringBuffer();

        selectSql.append("DELETE from API_FILE where 1=1 ");

        if (!StringUtil.isEmpty(unid)) {
            selectSql.append(" and unid=:unid ");
        }
        if (!StringUtil.isEmpty(serialNumber)) {
            selectSql.append(" and serialNumber=:serialNumber ");
        }
        SQLQuery selectQuery = getSession().createSQLQuery(selectSql.toString());
        if (!StringUtil.isEmpty(unid)) {
            selectQuery.setString("unid", unid);
        }
        if (!StringUtil.isEmpty(serialNumber)) {
            selectQuery.setString("serialNumber", serialNumber);
        }
        selectQuery.uniqueResult();
    }

    @Override
    public ArrayList<ApiFile> apiFuJianTreeGridSp(Long flowId, String yjFlowFuJianId, String flag, String serialNumber, String siteAddress) {
        StringBuffer selectSql = new StringBuffer();
        StringBuffer countSql = new StringBuffer();
        selectSql.append("select * from API_FILE   where 1=1 ");

        if (!StringUtil.isEmpty(String.valueOf(flowId))) {
            selectSql.append(" and flowId=:flowId ");
        }
        if (!StringUtil.isEmpty(yjFlowFuJianId)) {
            selectSql.append(" and yjFlowFuJianId=:yjFlowFuJianId ");
        }
        if (!StringUtil.isEmpty(serialNumber)) {
            selectSql.append(" and serialNumber =:serialNumber ");
        }
         /*if(!StringUtil.isEmpty(flag)){
             selectSql.append(" and flag =:flag ");
         }*/
        if (!StringUtil.isEmpty(siteAddress)) {
            selectSql.append(" and siteAddress =:siteAddress ");
        }
        SQLQuery selectQuery = getSession().createSQLQuery(selectSql.toString()).addEntity(ApiFile.class);
        SQLQuery countQuery = getSession().createSQLQuery(countSql.toString());
        if (!StringUtil.isEmpty(String.valueOf(flowId))) {
            selectQuery.setLong("flowId", flowId);
        }
        if (!StringUtil.isEmpty(yjFlowFuJianId)) {
            selectQuery.setLong("yjFlowFuJianId", Long.parseLong(yjFlowFuJianId));
        }
        if (!StringUtil.isEmpty(serialNumber)) {
            selectQuery.setString("serialNumber", serialNumber);
        }
         /*if(!StringUtil.isEmpty(flag)){
             selectQuery.setString("flag",flag);
         }*/
        if (!StringUtil.isEmpty(siteAddress)) {
            selectQuery.setString("siteAddress", siteAddress);
        }
        ArrayList<ApiFile> list = (ArrayList) selectQuery.list();
        return list;
    }


    public List<Map<String, Object>> getApiFileAndWenshu(String serialNumber) throws Exception {
        StringBuffer sql = new StringBuffer();

        sql.append("select '企业自我承诺'  as WENSHU,a.FILEPATH,a.LOCALFILEPATH,a.SERIALNUMBER,a.FILETYPE, 0 as IDS from API_FILE a where a.SERIALNUMBER= :serialNumber and a.flag=31");
        sql.append(" union all ");
        sql.append(" select '补正告知书'as WENSHU, b.FILEPATH,' ' as LOCALFILEPATH,b.SERIALNUMBER,b.EXTENSE as FILETYPE,b.ID as IDS from YJFLOWINSTWENSHU b where b.SERIALNUMBER=:serialNumber and FILETITLE = '检验检测-补正告知通知书' ");
        sql.append(" union all ");
        sql.append(" select '退回机构材料'as WENSHU, c.FILEPATH,' ' as LOCALFILEPATH,c.SERIALNUMBER,c.EXTENSE as FILETYPE,c.ID as IDS from YJFLOWINSTWENSHU c where c.SERIALNUMBER=:serialNumber and FILETITLE like '%退回附件%' ");

        SQLQuery query = super.getSession().createSQLQuery(sql.toString());

        if (!StringUtil.isEmpty(serialNumber)) {
            query.setString("serialNumber", serialNumber);
        }

        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.list();
    }


    @Override
    public int deleteBatchBySerialNumber(String serialNumber) throws Exception {

        if (StringUtil.isEmpty(serialNumber)) {
            return 0;
        }
        StringBuffer selectSql = new StringBuffer();
        selectSql.append("delete from API_FILE where serialNumber=:serialNumber ");

        SQLQuery selectQuery = getSession().createSQLQuery(selectSql.toString());
        selectQuery.setString("serialNumber", serialNumber);

        return selectQuery.executeUpdate();
    }


    @Override
    public HashMap<String, Object> queryListBySerialNumber(HashMap<Object, Object> reqMap) {
        String serialNumber = (String) reqMap.get("serialNumber");

        StringBuffer selectSql = new StringBuffer();
        selectSql.append(" SELECT * FROM API_FILE ");
        selectSql.append(" WHERE SERIALNUMBER = '" + serialNumber + "'");

        SQLQuery sqlQuery = getSession().createSQLQuery(selectSql.toString());
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<ApiFile> list = sqlQuery.list();
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put(R.DATA_NAME, list);
        return resultMap;
    }


}
