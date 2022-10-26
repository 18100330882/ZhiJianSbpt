package com.yongjie.ZhiJianSbpt.service.jyjccj.impl;

import com.yongjie.ZhiJianSbpt.dao.jyjccj.CollectDao;
import com.yongjie.ZhiJianSbpt.model.JgObject;
import com.yongjie.ZhiJianSbpt.model.jyjccj.CjSqqzr;
import com.yongjie.ZhiJianSbpt.model.jyjccj.Collect;
import com.yongjie.ZhiJianSbpt.model.jyjccj.JgobjectJyjcRy;
import com.yongjie.ZhiJianSbpt.service.jyjccj.CollectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(CollectService.SERVICE_NAME)
@Transactional(readOnly = false)
public class CollectServiceImpl implements CollectService {
    @Resource(name = CollectDao.SERVICE_NAME)
    private CollectDao collectDao;


    @Override
    public List<Collect> collectList(Map map) {
        return collectDao.collectList(map);
    }

    @Override
    public void save(JgObject jgObject) {
        collectDao.save(jgObject);
    }

    @Override
    public void update(JgObject jgObject) {
        collectDao.update(jgObject);
    }

    @Override
    public JgObject getById(Long id) {
        return collectDao.getById(id);
    }

    @Override
    public void del(Long id) {
        collectDao.delete(id);
    }

    @Override
    public Map<String, Object> getNlFileList(HashMap<Object, Object> reqMap) {
        return collectDao.getNlFileList(reqMap);
    }

    @Override
    public Map<String, Object> getQzrFileList(HashMap<Object, Object> reqMap) {
        return collectDao.getQzrFileList(reqMap);
    }

    @Override
    public Map<String, Object> getRyFileList(HashMap<Object, Object> reqMap) {
        return collectDao.getRyFileList(reqMap);
    }

    @Override
    public Map<String, Object> queryByZsbh(Map map) {
        return collectDao.queryByZsbh(map);
    }

    @Override
    public void delnl(Map<String, Object> map) {
        collectDao.delnl(map);
    }

    @Override
    public void delqzr(Map<String, Object> map) {
        collectDao.delqzr(map);
    }

    @Override
    public void delry(Map<String, Object> map) {
        collectDao.delry(map);
    }

    @Override
    public Collect getByIdSerialNumber(Map paramMap) {
        return collectDao.getByIdSerialNumber(paramMap);
    }

    @Override
    public void delByZsbh(String zsbh) {
        collectDao.delByZsbh(zsbh);
    }

    @Override
    public Map<String, Object> ckfj(Map<String, Object> map) {
        return collectDao.ckfj(map);
    }

    @Override
    public Map cknl(Map<String, Object> map) {
        return collectDao.cknl(map);
    }

    @Override
    public CjSqqzr queryQzrBySfzh(Map<String, Object> map) {
        return collectDao.queryQzrBySfzh(map);
    }

    @Override
    public JgobjectJyjcRy queryRyBySfzh(Map<String, Object> map) {
        return collectDao.queryRyBySfzh(map);
    }

    @Override
    public List queryRyListByZsbh(Map<String, Object> map) {
        return collectDao.queryRyListByZsbh(map);
    }

    @Override
    public int checkFlagByZsbh(Map<String, Object> map) {
        return collectDao.checkFlagByZsbh(map);
    }

    @Override
    public Map querySfzhByName(Map<String, Object> map) {
        return collectDao.querySfzhByName(map);
    }

    @Override
    public List queryQzrByZsbh(Map<String, Object> map) {
        return collectDao.queryQzrByZsbh(map);
    }

    @Override
    public Map queryQzrSfzhByName(Map<String, Object> map) {
        return collectDao.queryQzrSfzhByName(map);

    }

}
