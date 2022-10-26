package com.yongjie.ZhiJianSbpt.service.shiYsJyjc.impl;

import com.yongjie.ZhiJianSbpt.dao.shiYsJyjc.IBizShiYsJyjcDao;
import com.yongjie.ZhiJianSbpt.model.AccountSbpt;
import com.yongjie.ZhiJianSbpt.model.JgObject;
import com.yongjie.ZhiJianSbpt.model.shiYsJyjc.BizShiYsJyjc;
import com.yongjie.ZhiJianSbpt.service.shiYsJyjc.IBizShiYsJyjcService;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(readOnly = true)
@Service(IBizShiYsJyjcService.SERVICE_NAME)
public class BizShiYsJyjcService implements IBizShiYsJyjcService {
    @Resource(name = IBizShiYsJyjcDao.SERVICE_NAME)
    private IBizShiYsJyjcDao bizShiYsJyjcdao;

    @Override
    public HashMap getShiYsJyjcSqsToList(String name, int pageIndex, int pageSize, String sortField, String sortOrder, String admin) {
        // TODO Auto-generated method stub
        return bizShiYsJyjcdao.getShiYsJyjcSqsToList(name, pageIndex, pageSize, sortField, sortOrder, admin);
    }

    @Override
    public HashMap getShiYsJyjcSqsToList2(String name, int pageIndex, int pageSize, String sortField, String sortOrder, String admin, String flowId) {
        // TODO Auto-generated method stub
        return bizShiYsJyjcdao.getShiYsJyjcSqsToList2(name, pageIndex, pageSize, sortField, sortOrder, admin, flowId);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void saveJyjc(BizShiYsJyjc bizShiYsJyjc) {
        // TODO Auto-generated method stub
        bizShiYsJyjcdao.save(bizShiYsJyjc);
    }


    @Override
    public BizShiYsJyjc getBizShiYsJyjcById(long id) {
        // TODO Auto-generated method stub
        return bizShiYsJyjcdao.getById(id);
    }

    @Override
    public ArrayList getListCanSb(String czr, String flowId, String cmaSqlx) throws Exception {
        // TODO Auto-generated method stub
        return bizShiYsJyjcdao.getListCanSb(czr, flowId, cmaSqlx);
    }

    @Override
    public ArrayList getListCanSb(String czr, String flowId, String cmaSqlx, String sqsId) throws Exception {
        return bizShiYsJyjcdao.getListCanSb(czr, flowId, cmaSqlx, sqsId);
    }

    @Override
    public HashMap getBizShiYsJyjcOrsqsId(long sqsId) {
        // TODO Auto-generated method stub
        return bizShiYsJyjcdao.getBizShiYsJyjcOrsqsId(sqsId);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void updateJyjc(BizShiYsJyjc bizShiYsJyjc) {
        // TODO Auto-generated method stub
        bizShiYsJyjcdao.update(bizShiYsJyjc);
    }

    @Override
    public ArrayList getBizShiYsJyById(long sqsId) {
        // TODO Auto-generated method stub
        return bizShiYsJyjcdao.getBizShiYsJyById(sqsId);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public int delShiYsJyjc(long id) {
        // TODO Auto-generated method stub
        return bizShiYsJyjcdao.delBizShiYsJy(id);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public int updateFlag(long id, String flag) {
        // TODO Auto-generated method stub
        return bizShiYsJyjcdao.updateFlag(id, flag);
    }

    @Override
    public BizShiYsJyjc getById(Long id) {
        // TODO Auto-generated method stub
        return bizShiYsJyjcdao.getById(id);
    }

    @Override
    public ArrayList getShenHAreaId() {
        return bizShiYsJyjcdao.getShenHAreaId();
    }

    @Override
    public ArrayList getListByCzr(String czr) throws Exception {
        // TODO Auto-generated method stub
        return bizShiYsJyjcdao.getListByCzr(czr);
    }

    @Override
    public ArrayList getListByCzr2(String czr, String flowId) throws Exception {
        // TODO Auto-generated method stub
        return bizShiYsJyjcdao.getListByCzr2(czr, flowId);
    }

    @Override
    public ArrayList getShiysjyjcPremitByZsbh(String zsbh) {
        // TODO Auto-generated method stub
        return bizShiYsJyjcdao.getShiysjyjcPremitByZsbh(zsbh);
    }

    @Override
    public ArrayList getShiysjyjcPremitByZsbh2(String zsbh) {
        // TODO Auto-generated method stub
        return bizShiYsJyjcdao.getShiysjyjcPremitByZsbh2(zsbh);
    }

    //终止评审
    @Override
    public void addZzps(long sqsId, long flowId, long flowInstId, String nodeId, String zzrdName, String zzxkdate,
                        String zsNumber, String yxDate, String reason, String lxr, String lxdh, String txaddress, String faxx) {
        // TODO Auto-generated method stub
        bizShiYsJyjcdao.addZzps(sqsId, flowId, flowInstId, nodeId, zzrdName, zzxkdate, zsNumber, yxDate, reason, lxr, lxdh, txaddress, faxx);

    }

    @Override
    public void updataZzps(String id, long sqsId, long flowId, long flowInstId, String nodeId, String zzrdName,
                           String zzxkdate, String zsNumber, String yxDate, String reason, String lxr, String lxdh, String txaddress,
                           String faxx) {
        // TODO Auto-generated method stub
        bizShiYsJyjcdao.updataZzps(id, sqsId, flowId, flowInstId, nodeId, zzrdName, zzxkdate, zsNumber, yxDate, reason, lxr, lxdh, txaddress, faxx);
    }

    @Override
    public ArrayList getzzpsSelect(long sqsId, long flowId, long flowInstId) {
        // TODO Auto-generated method stub
        return bizShiYsJyjcdao.getzzpsSelect(sqsId, flowId, flowInstId);
    }


    //申报端检验检测档案机构查询
    @Override
    public HashMap<String, Object> getQyCxList(String jgflIds, String shxydm, int pageIndex, int pageSize,
                                               String sortField, String sortOrder) {
        // TODO Auto-generated method stub
        return bizShiYsJyjcdao.getQyCxList(jgflIds, shxydm, pageIndex, pageSize,
                sortField, sortOrder);
    }

    @Override
    public JgObject getJgObjectnewShxydm(String newShxydm, String isdel) {
        // TODO Auto-generated method stub
        return bizShiYsJyjcdao.getJgObjectnewShxydm(newShxydm, isdel);
    }

    @Override
    public List<Object> getSqsxxByShxydm(String newShxydm, String jgstate) {
        // TODO Auto-generated method stub
        return bizShiYsJyjcdao.getSqsxxByShxydm(newShxydm, jgstate);
    }

    @Override
    public List<Object> getCd(String newShxydm, String type) {
        // TODO Auto-generated method stub
        return bizShiYsJyjcdao.getCd(newShxydm, type);
    }

    @Override
    public Map<String, Object> getZsxx(String newShxydm, String sortField, String sortOrder, Integer pageIndex,
                                       Integer pageSize) {
        // 获取记录总数
        //Number count = shiYsJyjcPermitDao.getZsxxCount(newShxydm);
        // 获取当页记录
        List<Object> list = bizShiYsJyjcdao.getZsxx(newShxydm, sortField, sortOrder, pageIndex, pageSize);
        // 转存返回
        Map<String, Object> result = new HashMap<>();
        result.put("total", 2);
        // 页面要分来显示CMA和CAL证书，因此要对数据进行拆分，
        List<Map<String, Object>> list2 = new ArrayList<>();
        // 存储CMA证书信息
        Map<String, Object> CMAPermit = null;
        // 存储CAL证书信息
        Map<String, Object> CALPermit = null;
        if (list != null && list.size() > 0) {
            //ShiYsJyjcPermit permit = list.get(0);
            Map<String, Object> permit = (Map<String, Object>) list.get(0);
            String calPermitNumber = permit.get("CAL") == null ? "" : permit.get("CAL").toString();
            CMAPermit = getCmaOrCalItem(permit, "CMA");
            if (!StringUtil.isNullOrEmptyZf(calPermitNumber)) {
                CALPermit = getCmaOrCalItem(permit, "CAL");
            }
        } else {
            // 未查询到证书情况时，封装两条空数据，但这两条空数据应包含一些公共信息，以便于修改
            CMAPermit = getCmaOrCalItem("CMA", newShxydm);
            CALPermit = getCmaOrCalItem("CAL", newShxydm);
        }
        list2.add(CMAPermit);
        if (CALPermit != null) {
            list2.add(CALPermit);
        }
        result.put("data", list2);
        return result;
    }

    // 当证书为空时，返回各字段为空的记录
    public Map<String, Object> getCmaOrCalItem(String permitType, String newShxydm) {
        Map<String, Object> permitMap = new HashMap<>();
        permitMap.put("id", 0);
        permitMap.put("newShxydm", newShxydm);
        permitMap.put("permitType", permitType);

        permitMap.put("zzjgdm", "");
        permitMap.put("dz", "");
        permitMap.put("mc", "");
        permitMap.put("sqzylb", "");
        permitMap.put("permitNumber", "");
        permitMap.put("fzrq", null);
        permitMap.put("yxrq", null);

        return permitMap;
    }

    // 提取证书中CMA或者CAL的相关信息，并返回
    public Map<String, Object> getCmaOrCalItem(Map<String, Object> permit, String permitType) {
        if (permit == null)
            return null;

        Map<String, Object> permitMap = new HashMap<>();
        // 公共信息
        permitMap.put("id", permit.get("ID") == null ? "" : permit.get("ID").toString());
        permitMap.put("zzjgdm", permit.get("ZZJGDM") == null ? "" : permit.get("ZZJGDM").toString());
        permitMap.put("dz", permit.get("DZ") == null ? "" : permit.get("DZ").toString());
        permitMap.put("mc", permit.get("MC") == null ? "" : permit.get("MC").toString());
        permitMap.put("sqzylb", permit.get("SQZYLB") == null ? "" : permit.get("SQZYLB").toString());
        permitMap.put("newShxydm", permit.get("NEWSHXYDM") == null ? "" : permit.get("NEWSHXYDM").toString());

        // 每种类别的不同信息
        if ("CMA".equals(permitType)) {
            permitMap.put("permitType", permitType);
            permitMap.put("permitNumber", permit.get("CMA") == null ? "" : permit.get("CMA").toString());
            permitMap.put("fzrq", permit.get("FZRQ") == null ? "" : permit.get("FZRQ").toString());
            permitMap.put("yxrq", permit.get("YXRQ") == null ? "" : permit.get("YXRQ").toString());

        } else if ("CAL".equals(permitType)) {
            permitMap.put("permitType", permitType);
            permitMap.put("permitNumber", permit.get("CAL") == null ? "" : permit.get("CAL").toString());
            permitMap.put("fzrq", permit.get("FZRQ") == null ? "" : permit.get("FZRQ").toString());
            permitMap.put("yxrq", permit.get("YXRQ") == null ? "" : permit.get("YXRQ").toString());

        } else
            return null;

        return permitMap;
    }

    @Override
    public HashMap getAllJyjcZyTypes(String key) {
        // TODO Auto-generated method stub
        return bizShiYsJyjcdao.getAllJyjcZyTypes(key);
    }

    @Override
    public HashMap getJgObjectJyjcjgRyList(String nameFilter, int pageIndex, int pageSize, String sortField,
                                           String sortOrder, long jgflId, String shxydm, String jgstate, String sqsId) {
        if (!StringUtil.isNullOrEmpty(sqsId) && !sqsId.equals("null")) {
            jgstate = "";//若是sqsid有值则说明是从历史评审记录中的查看过来的，说无值则是从企业档案过来。
        }
        return bizShiYsJyjcdao.getJgObjectJyjcjgRyList(nameFilter, pageIndex, pageSize, sortField, sortOrder, jgflId, shxydm, jgstate, sqsId);
    }

    @Override
    public HashMap getJgObjectQzrList(String nameFilter, int pageIndex, int pageSize, String sortField,
                                      String sortOrder, long jgflId, String shxydm, String jgstate, String sqsId) {
        if (!StringUtil.isNullOrEmpty(sqsId) && !sqsId.equals("null")) {
            jgstate = "";//若是sqsid有值则说明是从历史评审记录中的查看过来的，说无值则是从企业档案过来。
        }
        return bizShiYsJyjcdao.getJgObjectQzrList(nameFilter, pageIndex, pageSize, sortField, sortOrder, jgflId, shxydm, jgstate, sqsId);
    }

    @Override
    public HashMap getJgObjectJyjcJynlList2(int pageIndex, int pageSize, String sortField, String sortOrder,
                                            long jgflId, String shxydm, String jgstate, String sqsId, String dizhi, String sporfspid, String leib,
                                            String cpmc, String cmaorcal) {
        if (!StringUtil.isNullOrEmpty(sqsId) && !sqsId.equals("null")) {
            jgstate = "";//若是sqsid有值则说明是从历史评审记录中的查看过来的，说无值则是从企业档案过来。
        }
        return bizShiYsJyjcdao.getJgObjectJyjcJynlList2(pageIndex, pageSize, sortField, sortOrder, jgflId, shxydm, jgstate, sqsId, dizhi, sporfspid, leib, cpmc, cmaorcal);
    }

    @Override
    public ArrayList getZsxxList2(String newShxydm) {
        // TODO Auto-generated method stub
        return bizShiYsJyjcdao.getZsxxList2(newShxydm);
    }

    @Override
    public ArrayList getJdcXx(String sqsId, String zzjgdm, String jgState) {
        return bizShiYsJyjcdao.getJdcXx(sqsId, zzjgdm, jgState);
    }

    @Override
    public HashMap getJgObjectYqsbXxList(int pageIndex, int pageSize, String sortField, String sortOrder,
                                         long jgflId, String shxydm, String jgstate, String sqsId, String dizhi, String sporfspid) {
        if (!StringUtil.isNullOrEmpty(sqsId) && !sqsId.equals("null")) {
            jgstate = "";//若是sqsid有值则说明是从历史评审记录中的查看过来的，说无值则是从企业档案过来。
        }
        // 首先查询能力表，获取能力表中当前组织结构对应的 记录的 总数 和设备信息全为空的记录的总数，如果全为空的记录的总数大于0并且大于等于 记录总数，则去设备表中查询，否则查询能力表

        // 获取档期 机构在 能力表中对应的记录总数，已经设备信息全为空的记录的总数
        List<Number> count = bizShiYsJyjcdao.getNullYqsbCount(jgflId, shxydm, jgstate, sqsId);

        if (count != null && count.size() > 1) {
            if (count.get(1).intValue() < count.get(0).intValue()) {
                HashMap map = bizShiYsJyjcdao.getJgobjectYqsbXxListFromNl(pageIndex, pageSize, sortField, sortOrder, jgflId, shxydm, jgstate, sqsId, dizhi, sporfspid);
                /*HashMap map = objectJyjcYqsbDao.getJgobjectYqsbListFromNl(pageIndex, pageSize, sortField, sortOrder, jgflId, shxydm,jgstate,sqsId,dizhi,sporfspid);*/
                /*
                 * ArrayList list=(ArrayList)map.get("data"); if(list.size()>0){ for(int
                 * i=0;i<list.size();i++){ HashMap map1=(HashMap)list.get(i); String
                 * cpmc1=map1.get("CPMC")==null?"":map1.get("CPMC").toString();
                 * if(!StringUtil.isNullOrEmpty(cpmc1)){ ClobToString cts=new ClobToString();
                 * Clob clob= (Clob)map1.get("CPMC"); String cpmc = ""; try { cpmc =
                 * cts.getCloToString(clob); } catch (SQLException | IOException e) {
                 * e.printStackTrace(); } map1.remove("CPMC"); map1.put("CPMC", cpmc); } } }
                 */
                return map;
            }
        } else if (count.size() == 1 && count.get(0).intValue() == 0) {//能力表为空值
            HashMap map = bizShiYsJyjcdao.getJgobjectYqsbXxListFromNl(pageIndex, pageSize, sortField, sortOrder, jgflId, shxydm, jgstate, sqsId, dizhi, sporfspid);
            /*HashMap map = objectJyjcYqsbDao.getJgobjectYqsbListFromNl(pageIndex, pageSize, sortField, sortOrder, jgflId, shxydm,jgstate,sqsId,dizhi,sporfspid);*/
            ArrayList list = (ArrayList) map.get("data");
            /*
             * if(list.size()>0){ for(int i=0;i<list.size();i++){ HashMap
             * map1=(HashMap)list.get(i); String
             * cpmc1=map1.get("CPMC")==null?"":map1.get("CPMC").toString();
             * if(!StringUtil.isNullOrEmpty(cpmc1)){ ClobToString cts=new ClobToString();
             * Clob clob= (Clob)map1.get("CPMC"); String cpmc = ""; try { cpmc =
             * cts.getCloToString(clob); } catch (SQLException | IOException e) {
             * e.printStackTrace(); } map1.remove("CPMC"); map1.put("CPMC", cpmc); } } }
             */
            return map;
        }
        return bizShiYsJyjcdao.getJgObjectYqsbList(pageIndex, pageSize, sortField, sortOrder, jgflId, shxydm, jgstate, sqsId, dizhi, sporfspid);
    }

    @Override
    public HashMap getJgObjectFuJianList(String nameFilter, int pageIndex, int pageSize, String sortField,
                                         String sortOrder, long jgflId, String shxydm, String propertyId) {
        return bizShiYsJyjcdao.getJgObjectFuJianList(nameFilter, pageIndex, pageSize, sortField, sortOrder, jgflId, shxydm, propertyId);
    }

    @Override
    public ArrayList getObjectCd1(String shxydm, String type) {
        // TODO Auto-generated method stub
        return bizShiYsJyjcdao.getObjectCd1(shxydm, type);
    }

    //反馈信息   评审信息
    @Override
    public ArrayList getPsxxListBySqsId(String sqsId, String flowId, String flowInstId) {
        // TODO Auto-generated method stub
        return bizShiYsJyjcdao.getPsxxListBySqsId(sqsId, flowId, flowInstId);
    }

    @Override
    public ArrayList getPsry(String flowId, String flowInstId) {
        // TODO Auto-generated method stub
        return bizShiYsJyjcdao.getPsry(flowId, flowInstId);
    }
}
