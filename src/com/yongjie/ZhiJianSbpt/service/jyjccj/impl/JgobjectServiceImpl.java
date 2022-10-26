package com.yongjie.ZhiJianSbpt.service.jyjccj.impl;

import com.yongjie.ZhiJianSbpt.dao.jyjccj.JgobjectDao;
import com.yongjie.ZhiJianSbpt.model.JgObject;
import com.yongjie.ZhiJianSbpt.service.jyjccj.JgobjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(JgobjectService.SERVICE_NAME)
@Transactional(readOnly = false)
public class JgobjectServiceImpl implements JgobjectService {
    @Resource(name = JgobjectDao.SERVICE_NAME)
    private JgobjectDao jgobjectDao;


    @Override
    public int saveBatch(List<JgObject> list) throws Exception {
        return jgobjectDao.saveBatch(list);
    }

    @Override
    public int saveBatch(List<JgObject> list, int size) throws Exception {
        return jgobjectDao.saveBatch(list, size);
    }

    @Override
    public void save(JgObject entity) {
        jgobjectDao.save(entity);
    }

    @Override
    public void update(JgObject entity) {
        jgobjectDao.update(entity);
    }

    @Override
    public Map getByMcAndShxydm(Map map) {
        return jgobjectDao.getByMcAndShxydm(map);
    }

    @Override
    public void delete(Long id) {
        jgobjectDao.delete(id);
    }

    @Override
    public JgObject getById(Long id) {
        return jgobjectDao.getById(id);
    }

    @Override
    public Map getByMcAndShxydm2(Map map) {
        return jgobjectDao.getByMcAndShxydm2(map);
    }


}
