package com.yongjie.ZhiJianSbpt.service;

import com.yongjie.ZhiJianSbpt.model.Area;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IAreaService {
    public final static String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.service.impl.AreaService";

    /**
     * 加载数据列表
     *
     * @param pageIndex
     * @param pageSize
     * @param sortField
     * @param sortOrder
     * @param id        父节点的id值  list显示的是子节点
     * @return
     * @throws Exception
     */
    public HashMap getArea(String nameFilter, int pageIndex, int pageSize, String sortField, String sortOrder, String id, String areaId) throws Exception;

    /**
     * 根据区域id查找该区域及以下的区域
     *
     * @param areaId
     * @return
     * @throws Exception
     */
    public HashMap getTree(String areaId, String sortField, String sortOrder, String isEnabled) throws Exception;

    public ArrayList getDept();

    /**
     * 根据用户名和区域名称查找管辖区域及以下的区域
     *
     * @param isEnabled 0：启用 1：禁用
     * @param pid       父id
     * @return
     * @throws Exception
     * @Author lijie
     */
    public ArrayList getAreaList(String isEnabled, String pid) throws Exception;

    /**
     * 根据区域id获得区域信息
     *
     * @param areaID
     * @return
     * @throws Exception
     */
    public List<Area> getData(long areaID) throws Exception;

    /**
     * 保存区域
     *
     * @param area 区域对象
     */
    public void addArea(Area area);

    /**
     * 修改区域
     *
     * @param area 区域对象
     */
    public void editArea(Area area);

    /**
     * 根据id启用
     *
     * @param areaId
     */
    public int activeArea(long areaId) throws Exception;

    /**
     * 根据id 禁用
     *
     * @param areaId
     * @return
     * @throws Exception
     */
    public int forbidArea(long areaId) throws Exception;

    /**
     * 根据当前用户的区域代码和id判重
     *
     * @param areaNumber
     * @param idStr      当前id值
     * @return
     * @throws Exception
     */
    public Boolean checkAreaNumber(String areaName, String areaNumber, String idStr) throws Exception;

    /**
     * 根据当前用户获得区域ID
     *
     * @param userName
     * @return
     * @throws Exception
     */
    public String getAreaFormUser(String userName) throws Exception;

    /**
     * 根据areaid获得区域全称
     *
     * @param areaid
     * @return 区域全称
     * @throws Exception
     */
    public String getAllAreaNameById(long areaid) throws Exception;

    //查询上报部门地市名  非常全称
    public String getAreaName(long areaid) throws Exception;

    /**
     * @return 根节点id
     * @author 杨清岭
     */
    public Long getMinParentId();

    /**
     * @return 拼接后的区域节点名 如(江西省)
     * @author 杨清岭
     */
    public String getNodeNames(long areaid);

    /**
     * 获得tree并查询
     * 张谦
     *
     * @param key
     * @return
     */
    public HashMap getAreas(String key);

    /**
     * 根据areaid获得parentPath
     *
     * @param areaid
     * @return 区域全称
     * @throws Exception
     */
    public String getParentPathById(long areaid);

    public String getAreaNameByid(long areaid);

    public Area getAreaById(long areaid);

    public ArrayList getAreaListByAreaNumber(String areaNumber) throws Exception;

    public Area getById(long id);

    /**
     * 通过区域的层级名称数组，获取最后一级地域的id
     * 如果所传递的层级数组的最后一级不是县区级单位，则返回null
     *
     * @param names
     * @return
     */
    public Number getAreaIdByNames(String[] names);

    Map<String, Object> areaTree(Map<String, Object> reqMap);
}
