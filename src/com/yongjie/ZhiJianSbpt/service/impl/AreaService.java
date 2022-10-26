package com.yongjie.ZhiJianSbpt.service.impl;

import com.yongjie.ZhiJianSbpt.dao.IAreaDao;
import com.yongjie.ZhiJianSbpt.model.Area;
import com.yongjie.ZhiJianSbpt.service.IAreaService;
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
@Service(IAreaService.SERVICE_NAME)
public class AreaService implements IAreaService {
    @Resource(name = IAreaDao.SERVICE_NAME)
    private IAreaDao areadao;

    //加载数据列表
    @Override
    public HashMap getArea(String nameFilter, int pageIndex, int pageSize, String sortField, String sortOrder, String id, String areaId)
            throws Exception {
        // TODO Auto-generated method stub
        return areadao.getArea(nameFilter, pageIndex, pageSize, sortField, sortOrder, id, areaId);
    }

    //根据区域id查找该区域及以下的区域
    @Override
    public HashMap getTree(String areaId, String sortField, String sortOrder, String isEnabled) throws Exception {
        // TODO Auto-generated method stub
        return areadao.getTree(areaId, sortField, sortOrder, isEnabled);
    }

    //根据id查询数据
    @Override
    public List<Area> getData(long areaID) throws Exception {
        // TODO Auto-generated method stub
        List<Area> model = areadao.getData(areaID);
        //System.out.println("model:"+model);
        return model;
    }

    //添加区域
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void addArea(Area area) {
        // TODO Auto-generated method stub
        areadao.save(area);
    }

    //编辑区域
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void editArea(Area area) {
        // TODO Auto-generated method stub
        areadao.update(area);
    }

    //启用
    @Override
    public int activeArea(long areaId) throws Exception {
        // TODO Auto-generated method stub
        return areadao.activeArea(areaId);
    }

    //禁用
    @Override
    public int forbidArea(long areaId) throws Exception {
        // TODO Auto-generated method stub
        return areadao.forbidArea(areaId);
    }

    @Override
    public Boolean checkAreaNumber(String areaName, String areaNumber, String idStr) throws Exception {
        // TODO Auto-generated method stub
        return areadao.checkAreaNumber(areaName, areaNumber, idStr);
    }

    @Override
    public String getAreaFormUser(String userName) throws Exception {
        // TODO Auto-generated method stub
        return areadao.getAreaFormUser(userName);
    }

    @Override
    public String getAllAreaNameById(long areaid) throws Exception {
        //根据id获得区域全称
        // TODO  lijie 部门信息调用
        return areadao.getAllAreaNameById(areaid);
    }

    @Override
    public ArrayList getAreaList(String isEnabled, String areaId) throws Exception {
        // TODO Auto-generated method stub
        return areadao.getAreaList(isEnabled, areaId);
    }

    /**
     * @return 根节点id
     * @author 杨清岭
     */
    @Override
    public Long getMinParentId() {
        // TODO Auto-generated method stub
        return areadao.getMinParentId();
    }

    /**
     * @return 拼接后的节点名如(江西省)
     * @author 杨清岭
     */
    @Override
    public String getNodeNames(long areaid) {
        return areadao.getNodeNames(areaid);
    }

    @Override
    public HashMap getAreas(String key) {
        // TODO Auto-generated method stub
        return areadao.getAreas(key);
    }

    @Override
    public String getParentPathById(long areaid) {
        // TODO Auto-generated method stub
        return areadao.getParentPathById(areaid);
    }

    @Override
    public String getAreaNameByid(long areaid) {
        // TODO Auto-generated method stub
        return areadao.getAreaNameByid(areaid);
    }

    @Override
    public Area getAreaById(long areaid) {
        return areadao.getAreaById(areaid);
    }

    @Override
    public ArrayList getAreaListByAreaNumber(String areaNumber) throws Exception {
        // TODO Auto-generated method stub
        return areadao.getAreaListByAreaNumber(areaNumber);
    }

    @Override
    public ArrayList getDept() {
        // TODO Auto-generated method stub
        return areadao.getDept();
    }

    @Override
    public Area getById(long id) {
        // TODO Auto-generated method stub
        return areadao.getById(id);
    }

    //通过地域层级名称，获取最后一级地域的id
    @Override
    public Number getAreaIdByNames(String[] names) {
        Number topid = areadao.getAreaIdByName(names[0]);

        if (topid == null)
            return null;

        Number secondid = areadao.getAreaIdByNameByParentId(names[1], topid);

        if (secondid == null)
            return null;

        //如果数组的长度为2，判断气下面还有没有子节点，如果有，返回null，否则返回其id
        List<Area> list = areadao.getChildAreaListByParentId(secondid);
        if (names.length == 2) {
            if (list == null || list.size() < 1)
                return secondid;
            else
                return null;
        }

        for (Area area : list) {
            if (names[2].equals(area.getAreaName()))
                return area.getId();
        }
        return null;
    }

    @Override
    public Map<String, Object> areaTree(Map<String, Object> reqMap) {
        return areadao.areaTree(reqMap);
    }

    //查询上报部门地市名  非常全称
    @Override
    public String getAreaName(long areaid) throws Exception {
        return areadao.getAreaName(areaid);
    }
}
