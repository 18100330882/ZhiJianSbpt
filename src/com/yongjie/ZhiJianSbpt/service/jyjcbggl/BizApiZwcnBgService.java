package com.yongjie.ZhiJianSbpt.service.jyjcbggl;

import com.yongjie.ZhiJianSbpt.model.jyjcbggl.BizApiZwcnBg;

import java.util.Map;

public interface BizApiZwcnBgService {

    String SERVICE_NAME = "com.yongjie.ZhiJianXzsp.module.zzrd.service.jyjcbggl.impl.BizApiZwcnBgServiceImpl";

    /**
     * 保存
     */
    void save(BizApiZwcnBg entity) throws Exception;

    /**
     * 查询
     *
     * @param id
     * @return
     * @throws Exception
     */
    BizApiZwcnBg queryById(Long id) throws Exception;

    BizApiZwcnBg queryBySerialNumber(String serialNumber) throws Exception;

    /**
     * 更新
     *
     * @param entity
     * @throws Exception
     */
    void update(BizApiZwcnBg entity) throws Exception;

    Map<String, Object> getList(Map<Object, Object> reqMap) throws Exception;

    /**
     * 删除封面及相关信息
     *
     * @return
     */
    void delete(Long id);

    /**
     * 根据流水号批量删除
     *
     * @param serialNumber
     * @return
     * @throws Exception
     */
    int deleteBatchBySerialNumber(String serialNumber) throws Exception;

    BizApiZwcnBg getBizApiZwcnBg(String serialNumber, String s) throws Exception;

    BizApiZwcnBg getById(Long id);
}
