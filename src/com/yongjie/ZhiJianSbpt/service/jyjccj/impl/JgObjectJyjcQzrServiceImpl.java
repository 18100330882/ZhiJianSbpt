package com.yongjie.ZhiJianSbpt.service.jyjccj.impl;

import com.yongjie.ZhiJianSbpt.dao.jyjccj.JgObjectJyjcQzrDao;
import com.yongjie.ZhiJianSbpt.model.jyjccj.CjSqqzr;
import com.yongjie.ZhiJianSbpt.model.jyjccj.JgObjectJyjcQzr;
import com.yongjie.ZhiJianSbpt.service.jyjccj.JgObjectJyjcQzrService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service(JgObjectJyjcQzrService.SERVICE_NAME)
@Transactional(readOnly = false)
public class JgObjectJyjcQzrServiceImpl implements JgObjectJyjcQzrService {

    @Resource(name = JgObjectJyjcQzrDao.SERVICE_NAME)
    private JgObjectJyjcQzrDao jgObjectJyjcQzrDao;

    @Override
    public int saveBatch(List<CjSqqzr> list) throws Exception {
        return jgObjectJyjcQzrDao.saveBatch(list);
    }

    @Override
    public int saveBatch(List<CjSqqzr> list, int size) throws Exception {
        return jgObjectJyjcQzrDao.saveBatch(list, size);
    }

    @Override
    public void deleteJyjcQzrById(Long id) {
        jgObjectJyjcQzrDao.deleteJyjcQzrById(id);
    }
}
