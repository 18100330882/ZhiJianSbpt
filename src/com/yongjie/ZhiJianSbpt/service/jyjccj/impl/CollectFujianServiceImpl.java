package com.yongjie.ZhiJianSbpt.service.jyjccj.impl;

import com.yongjie.ZhiJianSbpt.dao.jyjccj.CollectFujianDao;
import com.yongjie.ZhiJianSbpt.model.jyjccj.CollectFujian;
import com.yongjie.ZhiJianSbpt.service.jyjccj.CollectFujianService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

@Service(CollectFujianService.SERVICE_NAME)
@Transactional(readOnly = false)
public class CollectFujianServiceImpl implements CollectFujianService {
    @Resource(name = CollectFujianDao.SERVICE_NAME)
    private CollectFujianDao collectFujianDao;

    @Override
    public void save(CollectFujian collectFujian) {
        collectFujianDao.save(collectFujian);
    }

    @Override
    public void update(CollectFujian collectFujian) {
        collectFujianDao.update(collectFujian);
    }

    @Override
    public Map<String, Object> ckfj(Map<String, Object> map) {
        return collectFujianDao.ckfj(map);
    }

    @Override
    public CollectFujian getById(Long id) {
        return collectFujianDao.getById(id);
    }

    @Override
    public void delete(Long id) {
        collectFujianDao.delete(id);
    }

}
