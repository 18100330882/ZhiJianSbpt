package com.yongjie.ZhiJianSbpt.service.jyjccj;

import com.yongjie.ZhiJianSbpt.model.JgObject;
import com.yongjie.ZhiJianSbpt.model.jyjccj.CjSqqzr;
import com.yongjie.ZhiJianSbpt.model.jyjccj.Collect;
import com.yongjie.ZhiJianSbpt.model.jyjccj.JgobjectJyjcRy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CollectService {

    String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.service.jyjccj.impl.CollectServiceImpl";

    public List<Collect> collectList(Map map);

    public void save(JgObject jgObject);

    public void update(JgObject jgObject);

    public JgObject getById(Long id);

    public void del(Long id);

    Map<String, Object> getNlFileList(HashMap<Object, Object> reqMap);

    Map<String, Object> getQzrFileList(HashMap<Object, Object> reqMap);

    Map<String, Object> getRyFileList(HashMap<Object, Object> reqMap);

    Map<String, Object> queryByZsbh(Map map);

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
