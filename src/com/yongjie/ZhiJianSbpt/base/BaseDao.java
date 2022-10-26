package com.yongjie.ZhiJianSbpt.base;

import java.util.List;

public interface BaseDao<T> {
    /**
     * 保存对象
     *
     * @param entity
     */
    Long saveL(T entity);

    /**
     * 保存对象
     *
     * @param entity
     */
    void save(T entity);

    /**
     * 通过id删除对象
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 更新对象
     *
     * @param entity
     */
    void update(T entity);

    /**
     * 根据id，获取对象
     *
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     * 根据id数据，获取响应对象
     *
     * @param ids
     * @return
     */
    List<T> getByIds(Long[] ids);

    /**
     * 获取所有对象
     *
     * @return
     */
    List<T> findAll();

    /**
     * 批量保存
     *
     * @param list 对象集合 默认一次1000条
     * @return
     * @throws Exception
     * @author zws
     */
    int saveBatch(List<T> list) throws Exception;

    /**
     * 批量保存
     *
     * @param size 一次保存的数量
     * @return
     * @throws Exception
     * @author zws
     */
    int saveBatch(List<T> list, int size) throws Exception;

}
