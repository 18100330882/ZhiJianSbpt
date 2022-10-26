/**
 * @Author: 黄煜豪
 * @Createtime: 2017年9月20日 下午4:44:51
 * @Copyright: Copyright (c) 2016
 * @Company: 北京永杰友信科技有限公司
 * @Version：1.0
 * @Description:
 */
package com.yongjie.ZhiJianSbpt.dao.jyjccj;


import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.model.jyjccj.JgObjectJyjcnl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface JgObjectJyjcnlDao extends BaseDao<JgObjectJyjcnl> {
    public final static String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.dao.jyjccj.impl.JgObjectJyjcnlDaoImpl";

    //根据序号、类别、产品名称、依据标准名称 判重
    public ArrayList checkNl(String leib, String cpxh, String yjbz, String cpmc, String shxydm, String sqsId);

    public int deleteJgTable(String serialNumber, String type, String cddz, String calorcma, String tableName);

    public ArrayList getNlList(String serialNumber, String type, String cddz, String calOrCMa, String status);

    public ArrayList getNlList2(String sqsId, String type, String cddz, String calOrCMa);

    public ArrayList getNlList3(String cddz, String shxydm);

    public ArrayList getNlList4(String shxydm, String type, String cddz, String calOrCMa, String jgstate);

    public ArrayList getJyjcNlListByShxydm(String shxydm);

    public HashMap getJyjcnlDzByType(String shxydm, String jgstate, int pageIndex, int pageSize, String sortField,
                                     String sortOrder);

    public int updatejyjcnlData(String ziduan, String id);

    /**
     * 全表更新备注
     * @return
     */
    public int updatebeiZhu();

    /**
     * 批量保存
     * @param list 保存的对象,默认一次批量保存1000条
     * @return
     * @throws Exception
     */
    int saveBatch(List<JgObjectJyjcnl> list) throws Exception;

    /**
     * 批量保存
     * @param list 保存的对象
     * @param size 一次保存的条数
     * @return
     * @throws Exception
     */
    int saveBatch(List<JgObjectJyjcnl> list, int size) throws Exception;


    HashMap getJgObjectJyjcNlList(String zsbh, String lb, String cpmc, String bzmc, int pageIndex, int pageSize, String sortField, String sortOrder);

    void deleteJyjcnlById(Long id);

    Long selectMaxSotrNum();

}
