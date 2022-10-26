package com.yongjie.ZhiJianSbpt.dao.jyjccj;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.model.JgObject;
import com.yongjie.ZhiJianSbpt.model.jyjccj.CjSqqzr;
import com.yongjie.ZhiJianSbpt.model.jyjccj.Collect;
import com.yongjie.ZhiJianSbpt.model.jyjccj.JgobjectJyjcRy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CollectDao extends BaseDao<JgObject> {

    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.dao.jyjccj.impl.CollectDaoImpl";

    public List<Collect> collectList(Map map);

    Map<String, Object> getNlFileList(HashMap<Object, Object> reqMap);

    Map<String, Object> getQzrFileList(HashMap<Object, Object> reqMap);

    Map<String, Object> getRyFileList(HashMap<Object, Object> reqMap);

    Map<String, Object> queryByZsbh(Map<String, Object> map);

    public void delnl(Map<String, Object> map);

    public void delqzr(Map<String, Object> map);

    public void delry(Map<String, Object> map);

    Collect getByIdSerialNumber(Map paramMap);

    void delByZsbh(String zsbh);

    Map<String, Object> ckfj(Map<String, Object> map);

    Map cknl(Map<String, Object> map);

    CjSqqzr queryQzrBySfzh(Map<String, Object> map);

    JgobjectJyjcRy queryRyBySfzh(Map<String, Object> map);

    List queryRyListByZsbh(Map<String, Object> map);

    int checkFlagByZsbh(Map<String, Object> map);

    Map querySfzhByName(Map<String, Object> map);

    List queryQzrByZsbh(Map<String, Object> map);

    Map queryQzrSfzhByName(Map<String, Object> map);
}
