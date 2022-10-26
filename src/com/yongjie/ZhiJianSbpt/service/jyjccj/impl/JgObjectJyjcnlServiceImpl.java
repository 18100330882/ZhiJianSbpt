/**
 * @Author: 黄煜豪
 * @Createtime: 2017年9月20日 下午4:13:03
 * @Copyright: Copyright (c) 2016
 * @Company: 北京永杰友信科技有限公司
 * @Version：1.0
 * @Description:
 */
package com.yongjie.ZhiJianSbpt.service.jyjccj.impl;


import com.yongjie.ZhiJianSbpt.dao.jyjccj.JgObjectJyjcnlDao;
import com.yongjie.ZhiJianSbpt.model.jyjccj.JgObjectJyjcnl;
import com.yongjie.ZhiJianSbpt.service.jyjccj.JgObjectJyjcnlService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Transactional(readOnly = false)
@Service(JgObjectJyjcnlService.SERVICE_NAME)
public class JgObjectJyjcnlServiceImpl implements JgObjectJyjcnlService {

    @Resource(name = JgObjectJyjcnlDao.SERVICE_NAME)
    private JgObjectJyjcnlDao jgObjectJyjcnlDao;

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void addJgObjectJyjcnl(JgObjectJyjcnl model) {
        jgObjectJyjcnlDao.save(model);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void editJgObjectJyjcnl(JgObjectJyjcnl model) {
        jgObjectJyjcnlDao.update(model);
    }

    @Override
    public ArrayList checkNl(String leib, String cpxh, String yjbz, String cpmc, String shxydm, String sqsId) {
        return jgObjectJyjcnlDao.checkNl(leib, cpxh, yjbz, cpmc, shxydm, sqsId);
    }

    @Override
    public JgObjectJyjcnl getById(long id) {
        return jgObjectJyjcnlDao.getById(id);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public int deleteJgTable(String serialNumber, String type, String cddz, String calorcma, String tableName) {
        return jgObjectJyjcnlDao.deleteJgTable(serialNumber, type, cddz, calorcma, tableName);
    }

    @Override
    public ArrayList getNlList(String serialNumber, String type, String cddz, String calOrCMa, String status) {
        return jgObjectJyjcnlDao.getNlList(serialNumber, type, cddz, calOrCMa, status);
    }

    // 全表更新备注
    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public int updatebeiZhu() {
        return jgObjectJyjcnlDao.updatebeiZhu();
    }

    @Override
    public ArrayList getNlList2(String sqsId, String type, String cddz, String calOrCMa) {
        return jgObjectJyjcnlDao.getNlList2(sqsId, type, cddz, calOrCMa);
    }

    @Override
    public ArrayList getJyjcNlListByShxydm(String shxydm) {
        return jgObjectJyjcnlDao.getJyjcNlListByShxydm(shxydm);
    }

    @Override
    public ArrayList getNlList4(String shxydm, String type, String cddz, String calOrCMa, String jgstate) {
        return jgObjectJyjcnlDao.getNlList4(shxydm, type, cddz, calOrCMa, jgstate);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public int updatejyjcnlData(String ziduan, String id) {
        return jgObjectJyjcnlDao.updatejyjcnlData(ziduan, id);
    }

    @Override
    public ArrayList getNlList3(String cddz, String shxydm) {
        return jgObjectJyjcnlDao.getNlList3(cddz, shxydm);
    }

    @Override
    public HashMap getJyjcnlDzByType(String shxydm, String jgstate, int pageIndex, int pageSize, String sortField,
                                     String sortOrder) {
        return jgObjectJyjcnlDao.getJyjcnlDzByType(shxydm, jgstate, pageIndex, pageSize, sortField, sortOrder);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public int saveBatch(List<JgObjectJyjcnl> list) throws Exception {
        return jgObjectJyjcnlDao.saveBatch(list);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public int saveBatch(List<JgObjectJyjcnl> list, int size) throws Exception {
        return jgObjectJyjcnlDao.saveBatch(list, size);
    }

    @Override
    public HashMap getJgObjectJyjcNlList(String zsbh, String lb, String cpmc, String bzmc, int pageIndex, int pageSize, String sortField, String sortOrder) {
        return jgObjectJyjcnlDao.getJgObjectJyjcNlList(zsbh, lb, cpmc, bzmc, pageIndex, pageSize, sortField, sortOrder);
    }

    @Override
    public void deleteJyjcnlById(Long id) {
        jgObjectJyjcnlDao.deleteJyjcnlById(id);
    }

    @Override
    public Long selectMaxSotrNum() {
        return jgObjectJyjcnlDao.selectMaxSotrNum();
    }
}
