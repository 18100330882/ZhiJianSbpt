package com.yongjie.ZhiJianSbpt.service.jyjcJgzx;


import com.yongjie.ZhiJianSbpt.model.jyjcJgzx.BizApiZxsqb;

import java.util.Map;

public interface BizApiZxsqbService {

    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.service.jyjcJgzx.impl.BizApiZxsqbServiceImpl";

    /**
     * 保存
     */
    void save(BizApiZxsqb entity) throws Exception;

    /**
     * 查询
     *
     * @param id
     * @return
     * @throws Exception
     */
    BizApiZxsqb queryById(Long id) throws Exception;

    BizApiZxsqb queryBySerialNumber(String serialNumber) throws Exception;

    /**
     * 更新
     *
     * @param entity
     * @throws Exception
     */
    void update(BizApiZxsqb entity) throws Exception;

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
}
