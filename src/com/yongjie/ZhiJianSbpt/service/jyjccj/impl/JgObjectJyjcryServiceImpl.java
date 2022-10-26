/**
 * @Author: 黄煜豪
 * @Createtime: 2017年9月20日 下午4:13:03
 * @Copyright: Copyright (c) 2016
 * @Company: 北京永杰友信科技有限公司
 * @Version：1.0
 * @Description:
 */
package com.yongjie.ZhiJianSbpt.service.jyjccj.impl;


import com.yongjie.ZhiJianSbpt.dao.jyjccj.JgObjectJyjcryDao;
import com.yongjie.ZhiJianSbpt.model.jyjccj.JgobjectJyjcRy;
import com.yongjie.ZhiJianSbpt.service.jyjccj.JgObjectJyjcryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional(readOnly = false)
@Service(JgObjectJyjcryService.SERVICE_NAME)
public class JgObjectJyjcryServiceImpl implements JgObjectJyjcryService {

    @Resource(name = JgObjectJyjcryDao.SERVICE_NAME)
    private JgObjectJyjcryDao jgObjectJyjcryDao;

    @Override
    public int saveBatch(List<JgobjectJyjcRy> list) throws Exception {
        return jgObjectJyjcryDao.saveBatch(list);
    }

    @Override
    public int saveBatch(List<JgobjectJyjcRy> list, int size) throws Exception {
        return jgObjectJyjcryDao.saveBatch(list, size);
    }

    @Override
    public void deleteJyjcRyById(Long id) {
        jgObjectJyjcryDao.deleteJyjcRyById(id);
    }
}
