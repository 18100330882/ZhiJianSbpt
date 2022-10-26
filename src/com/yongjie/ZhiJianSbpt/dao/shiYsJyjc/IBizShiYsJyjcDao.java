package com.yongjie.ZhiJianSbpt.dao.shiYsJyjc;


import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.model.AccountSbpt;
import com.yongjie.ZhiJianSbpt.model.JgObject;
import com.yongjie.ZhiJianSbpt.model.shiYsJyjc.BizShiYsJyjc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface IBizShiYsJyjcDao extends BaseDao<BizShiYsJyjc> {
    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.dao.shiYsJyjc.impl.BizShiYsJyjcDao";

    /**
     * 查询主表数据显示在申请书列表(检验检测机构资质)
     *
     * @param name
     * @param pageIndex
     * @param pageSize
     * @param sortField
     * @param sortOrder
     * @return
     */
    public HashMap getShiYsJyjcSqsToList(String name, int pageIndex, int pageSize, String sortField, String sortOrder, String admin);

    /**
     * 查询主表数据显示在申请书列表(检验检测机构资质)
     *
     * @param name
     * @param pageIndex
     * @param pageSize
     * @param sortField
     * @param sortOrder
     * @return
     */
    public HashMap getShiYsJyjcSqsToList2(String name, int pageIndex, int pageSize, String sortField, String sortOrder, String admin, String flowId);

    /**
     * 根据申请书id查询主表数据
     *
     * @param sqsId
     * @return
     */
    public HashMap getBizShiYsJyjcOrsqsId(long sqsId);

    /**
     * 根据申请书id查询主表数据集合
     *
     * @return
     */
    public ArrayList getBizShiYsJyById(long sqsId);

    /**
     * 根据id删除申请书所有数据
     *
     * @param id
     * @return
     */
    public int delBizShiYsJy(long id);

    /**
     * 修改状态
     *
     * @param id
     * @param flag
     * @return
     */
    public int updateFlag(long id, String flag);

    //获得审核区域id
    public ArrayList getShenHAreaId();

    //获得数据根据czr
    public ArrayList getListByCzr(String czr);

    //获得数据根据czr
    public ArrayList getListByCzr2(String czr, String flowId);

    public ArrayList getListCanSb(String czr, String flowId, String cmaSqlx);

    public ArrayList getListCanSb(String czr, String flowId, String cmaSqlx, String sqsId);

    public ArrayList getShiysjyjcPremitByZsbh(String zsbh);

    public ArrayList getShiysjyjcPremitByZsbh2(String zsbh);

    //终止评审
    public void addZzps(long sqsId, long flowId, long flowInstId, String nodeId, String zzrdName, String zzxkdate, String zsNumber, String yxDate, String reason, String lxr, String lxdh, String txaddress, String faxx);

    public void updataZzps(String id, long sqsId, long flowId, long flowInstId, String nodeId, String zzrdName, String zzxkdate, String zsNumber, String yxDate, String reason, String lxr, String lxdh, String txaddress, String faxx);

    public ArrayList getzzpsSelect(long sqsId, long flowId, long flowInstId);

    //申报端检验检测档案机构查询
    public HashMap<String, Object> getQyCxList(String jgflIds, String shxydm, int pageIndex, int pageSize,
                                               String sortField, String sortOrder);

    public JgObject getJgObjectnewShxydm(String newShxydm, String isdel);

    public List<Object> getSqsxxByShxydm(String newShxydm, String jgstate);

    public List<Object> getCd(String newShxydm, String type);

    public List<Object> getZsxx(String newShxydm, String sortField, String sortOrder, Integer pageIndex,
                                Integer pageSize);

    public HashMap getAllJyjcZyTypes(String key);

    public HashMap getJgObjectJyjcjgRyList(String nameFilter, int pageIndex, int pageSize, String sortField, String sortOrder, long jgflId, String shxydm, String jgstate, String sqsId);

    public HashMap getJgObjectQzrList(String nameFilter, int pageIndex, int pageSize, String sortField, String sortOrder, long jgflId, String shxydm, String jgstate, String sqsId);

    public HashMap getJgObjectJyjcJynlList2(int pageIndex, int pageSize, String sortField, String sortOrder,
                                            long jgflId, String shxydm, String jgstate, String sqsId, String dizhi, String sporfspid, String leib,
                                            String cpmc, String cmaorcal);

    public ArrayList getZsxxList2(String newShxydm);

    public ArrayList getJdcXx(String sqsId, String zzjgdm, String jgState);

    public List<Number> getNullYqsbCount(long jgflId, String shxydm, String jgstate, String sqsId);

    public HashMap getJgobjectYqsbXxListFromNl(int pageIndex, int pageSize, String sortField, String sortOrder,
                                               long jgflId, String shxydm, String jgstate, String sqsId, String dizhi, String sporfspid);

    public HashMap getJgObjectYqsbList(int pageIndex, int pageSize, String sortField, String sortOrder, long jgflId, String shxydm, String jgstate, String sqsId, String dizhi, String sporfspid);

    public HashMap getJgObjectFuJianList(String nameFilter, int pageIndex, int pageSize, String sortField,
                                         String sortOrder, long jgflId, String shxydm, String propertyId);

    public ArrayList getObjectCd1(String shxydm, String type);

    //反馈信息   评审信息
    public ArrayList getPsxxListBySqsId(String sqsId, String flowId, String flowInstId);

    public ArrayList getPsry(String flowId, String flowInstId);
}
