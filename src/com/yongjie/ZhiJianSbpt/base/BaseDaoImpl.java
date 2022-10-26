package com.yongjie.ZhiJianSbpt.base;

import java.lang.reflect.ParameterizedType;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import dm.jdbc.util.StringUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional//此类的所有方法使用事务
@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T> implements BaseDao<T> {
    @Resource
    private SessionFactory sessionFactory;
    private Class<T> clazz;

    public BaseDaoImpl() {
        // 获取T的真实类型
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass(); // 获取泛型父类
        this.clazz = (Class<T>) pt.getActualTypeArguments()[0]; // 获取第一个参数的真实类型
    }

    /**
     * 获取连接session
     *
     * @return
     */
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public Long saveL(T entity) {
        return (Long) getSession().save(entity);
    }

    public void save(T entity) {
        getSession().save(entity);
    }

    public void update(T entity) {
        getSession().update(entity);
    }

    public void delete(Long id) {
        Object obj = getById(id);
        if (obj != null) {
            getSession().delete(obj);
        }
    }

    public T getById(Long id) {
        if (id == null) {
            return null;
        } else {
            return (T) getSession().get(clazz, id);
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> getByIds(Long[] ids) {
        if (ids == null || ids.length == 0) {
            return Collections.EMPTY_LIST;
        } else {
            return getSession()
                    .createQuery(//
                            "FROM " + clazz.getSimpleName() + " WHERE id IN (:ids)")//
                    .setParameterList("ids", ids)//
                    .list();
        }
    }

    public List<T> findAll() {
        return getSession()
                .createQuery(//
                        "FROM " + clazz.getSimpleName())//
                .list();
    }

    @Override
    public int saveBatch(List<T> list) throws Exception {
        return this.saveBatch(list, 1000);
    }

    @Override
    public int saveBatch(List<T> list, int size) throws Exception {
        if (StringUtil.isEmpty(String.valueOf(list))) {
            return 0;
        }
        if (StringUtil.isEmpty(String.valueOf(size)) || size <= 0) {
            return 0;
        }
        Session session = getSession();
        for (int i = 0, len = list.size(); i < len; i++) {
            session.save(list.get(i));
            if (i % size == 0) {
                session.flush();
                session.clear();
            }
        }
        return 1;
    }

}
