package com.yongjie.ZhiJianSbpt.service.shiYsJyjc;

import com.yongjie.ZhiJianSbpt.model.JgObject;
import com.yongjie.ZhiJianSbpt.model.shiYsJyjc.BizShiYsJyjc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IBizShiYsJyjcService {
    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.service.shiYsJyjc.impl.BizShiYsJyjcService";

    /**
     * 查询主表数据显示在申请书列表(检验检测机构资质)
     *
     * @param name
     * @param pageIndex
     * @param pageSize
     * @param sortField
     * @param sortOrder
     * @param admin
     * @return
     */
    HashMap getShiYsJyjcSqsToList(String name, int pageIndex, int pageSize, String sortField, String sortOrder, String admin);

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
    HashMap getShiYsJyjcSqsToList2(String name, int pageIndex, int pageSize, String sortField, String sortOrder, String admin, String flowId);

    /**
     * 保存数据到主表(封面表)
     *
     * @param bizShiYsJyjc
     */
    void saveJyjc(BizShiYsJyjc bizShiYsJyjc);

    /**
     * 根据id获得实体
     * 张谦
     *
     * @param id
     * @return
     */
    BizShiYsJyjc getBizShiYsJyjcById(long id);

    /**
     * 更新数据到主表
     *
     * @param bizShiYsJyjc
     */
    void updateJyjc(BizShiYsJyjc bizShiYsJyjc);

    /**
     * 根据申请书id查询主表数据
     *
     * @param sqsId
     * @return
     */
    HashMap getBizShiYsJyjcOrsqsId(long sqsId);

    /**
     * 根据申请书id查询主表数据集合
     * 张谦
     *
     * @param sqsId
     * @return
     */
    ArrayList getBizShiYsJyById(long sqsId);

    /**
     * 根据id删除申请书所有数据
     *
     * @param id
     */
    int delShiYsJyjc(long id);

    /**
     * 修改状态
     *
     * @param id
     * @param flag
     * @return
     */
    int updateFlag(long id, String flag);


    BizShiYsJyjc getById(Long id);

    /**
     * 查找审核区域Id
     *
     * @return
     * @author 李杰  20161213
     */
    ArrayList getShenHAreaId();

    //获得数据根据czr
    ArrayList getListByCzr(String czr) throws Exception;

    //获得数据根据czr
    ArrayList getListByCzr2(String czr, String flowId) throws Exception;

    ArrayList getListCanSb(String czr, String flowId, String cmaSqlx) throws Exception;

    ArrayList getListCanSb(String czr, String flowId, String cmaSqlx, String sqsId) throws Exception;

    ArrayList getShiysjyjcPremitByZsbh(String zsbh);

    ArrayList getShiysjyjcPremitByZsbh2(String zsbh);

    //终止评审
    void addZzps(long sqsId, long flowId, long flowInstId, String nodeId, String zzrdName, String zzxkdate, String zsNumber, String yxDate, String reason, String lxr, String lxdh, String txaddress, String faxx);

    void updataZzps(String id, long sqsId, long flowId, long flowInstId, String nodeId, String zzrdName, String zzxkdate, String zsNumber, String yxDate, String reason, String lxr, String lxdh, String txaddress, String faxx);

    ArrayList getzzpsSelect(long sqsId, long flowId, long flowInstId);

    //申报端检验检测档案机构查询
    HashMap<String, Object> getQyCxList(String jgflIds, String shxydm, int pageIndex, int pageSize, String sortField, String sortOrder);

    JgObject getJgObjectnewShxydm(String newShxydm, String isdel);

    List<Object> getSqsxxByShxydm(String newShxydm, String jgstate);

    List<Object> getCd(String newShxydm, String type);

    Map<String, Object> getZsxx(String newShxydm, String sortField, String sortOrder, Integer pageIndex, Integer pageSize);

    HashMap getAllJyjcZyTypes(String key);

    HashMap getJgObjectJyjcjgRyList(String nameFilter, int pageIndex, int pageSize, String sortField, String sortOrder, long jgflId, String shxydm, String jgstate, String sqsId);

    HashMap getJgObjectQzrList(String nameFilter, int pageIndex, int pageSize, String sortField, String sortOrder, long jgflId, String shxydm, String jgstate, String sqsId);

    HashMap getJgObjectJyjcJynlList2(int pageIndex, int pageSize, String sortField, String sortOrder, long jgflId, String shxydm, String jgstate, String sqsId, String dizhi, String sporfspid, String leib, String cpmc, String cmaorcal);

    ArrayList getZsxxList2(String newShxydm);

    ArrayList getJdcXx(String sqsId, String zzjgdm, String jgState);

    HashMap getJgObjectYqsbXxList(int pageIndex, int pageSize, String sortField, String sortOrder, long jgflId, String shxydm, String jgstate, String sqsId, String dizhi, String sporfspid);

    HashMap getJgObjectFuJianList(String nameFilter, int pageIndex, int pageSize, String sortField, String sortOrder, long jgflId, String shxydm, String propertyId);

    ArrayList getObjectCd1(String shxydm, String type);

    //反馈信息   评审信息
    ArrayList getPsxxListBySqsId(String sqsId, String flowId, String flowInstId);

    ArrayList getPsry(String flowId, String flowInstId);
}
