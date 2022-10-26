package com.yongjie.ZhiJianSbpt.dao;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.model.Area;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IAreaDao extends BaseDao<Area> {
    public final static String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.dao.impl.AreaDao";

    //加载数据列表
    public HashMap getArea(String nameFilter, int pageIndex, int pageSize, String sortField, String sortOrder, String id, String areaId) throws Exception;

    //根据区域id查找该区域及以下的区域
    public HashMap getTree(String areaId, String sortField, String sortOrder, String isEnabled) throws Exception;

    public ArrayList getDept();

    ////根据区域id获得区域信息
    public List<Area> getData(long areaId) throws Exception;

    //根据id启用
    public int activeArea(long areaId) throws Exception;

    //根据id 禁用
    public int forbidArea(long areaId) throws Exception;

    //根据当前用户的区域代码和id判重
    public Boolean checkAreaNumber(String areaName, String areaNumber, String idStr) throws Exception;

    public String getAreaFormUser(String userName) throws Exception;

    public ArrayList getAreaListByAreaId(long areaId) throws Exception;

    // 根据区域id获得区域全称    lijie  2016-08-03
    public String getAllAreaNameById(long areaid) throws Exception;

    //查询上报部门地市名  非常全称
    public String getAreaName(long areaid) throws Exception;

    //根据pid返回子节点
    public ArrayList getAreaList(String isEnabled, String pid) throws Exception;

    /**
     * @return 表中拥有最小父id的记录的id
     * @author 杨清岭
     */
    public Long getMinParentId();//如果最小的父id为0 那么以0为父id的子节点 会有多个吗 暂定为一个  返回一个id 之后 这个id被定为根节点

    /**
     * @param areaid 区域id
     * @return 返回从根节点到被选中的子节点的拼接后的节点名 如(江西省)
     */
    public String getNodeNames(long areaid);

    /**
     * 获得地区tree列表，并查询
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

    /**
     * 通过区域的名称获取区域的id
     *
     * @return
     */
    public Number getAreaIdByName(String name);

    /**
     * 通过区域的名称和父级区域的id获取区域的id
     *
     * @param string
     * @param topid
     * @return
     */
    public Number getAreaIdByNameByParentId(String string, Number topid);

    /**
     * 查询指定id下的所有的子级区域
     *
     * @param secondid
     * @return
     */
    public List<Area> getChildAreaListByParentId(Number secondid);

    Map<String, Object> areaTree(Map<String, Object> reqMap);
}
