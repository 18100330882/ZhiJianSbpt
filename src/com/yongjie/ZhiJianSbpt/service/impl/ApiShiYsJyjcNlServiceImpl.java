package com.yongjie.ZhiJianSbpt.service.impl;

import com.alibaba.fastjson.JSON;
import com.yongjie.ZhiJianSbpt.dao.ApiShiYsJyjcNlDao;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcNl;
import com.yongjie.ZhiJianSbpt.service.ApiShiYsJyjcNlService;
import com.yongjie.ZhiJianSbpt.utilities.R;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Transactional(readOnly = false)
@Service(ApiShiYsJyjcNlService.SERVICE_NAME)
public class ApiShiYsJyjcNlServiceImpl implements ApiShiYsJyjcNlService {

    @Resource(name = ApiShiYsJyjcNlDao.SERVICE_NAME)
    private ApiShiYsJyjcNlDao dao;

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public int saveBatch(List<ApiShiYsJyjcNl> list, int size) throws Exception {
        // 保存 能力、仪表数据 先删除 再 保存（同一个流水号-同一个食品-同一个场地）
        if (list != null && list.size() > 0) {
            ApiShiYsJyjcNl entity = list.get(0);
            HashMap<String, Object> reqMap = new HashMap();
            reqMap.put("serialNumber", entity.getSerialNumber());
            reqMap.put("flag", entity.getFlag());
            reqMap.put("typeName", entity.getTypeName());
            reqMap.put("siteName", entity.getSiteName());
            dao.deleteEvent(reqMap);
        }
        return dao.saveBatch(list, size);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteEvent(HashMap<String, Object> reqMap) {
        dao.deleteEvent(reqMap);
    }

    @Override
    public Map<String, Object> getDetailList(HashMap<String, Object> reqMap) {
        String flag = String.valueOf(reqMap.get("flag"));
        String fl = "EJFL";
        if ("0".equals(flag)) {
            fl = "YJFL";
        }
        Map<String, Object> resultMap = dao.getDetailList(reqMap);
        LinkedHashSet<String> set = new LinkedHashSet<>();
        Map<String, Object> resultMapNew = new HashMap<>();
        ArrayList result = new ArrayList();
        ArrayList addList = new ArrayList();
        ArrayList resultList = (ArrayList) resultMap.get(R.DATA_NAME);
        if (resultList != null && resultList.size() > 0) {
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, Object> map = (Map<String, Object>) resultList.get(i);
                String ejfl = (String) map.get(fl);
                set.add(ejfl);
            }
        }
        if (set != null && set.size() > 0) {
            int count = 1;
            for (String value : set) {
                Map<String, Object> addMap = new HashMap<>();
                addMap.put(fl, value);
                addMap.put("ID", count);
                addMap.put("PARENTID", -1);
                addList.add(addMap);
                count++;
            }
        }

        if (addList != null && addList.size() > 0) {
            for (int i = 0; i < addList.size(); i++) {
                Map map = (Map) addList.get(i);
                String ejfl = (String) map.get(fl);
                int parentId = (int) map.get("ID");
                for (int j = 0; j < resultList.size(); j++) {
                    Map map2 = (Map) resultList.get(j);
                    String ejfl1 = (String) map2.get(fl);
                    if (ejfl1.equals(ejfl)) {
                        map2.put("PARENTID", parentId);
                        result.add(map2);
                    }
                }
            }
        }
        result.addAll(addList);
        resultMapNew.put(R.DATA_NAME, result);
        return resultMapNew;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteRow(long id) {
        dao.delete(id);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void updateRow(ApiShiYsJyjcNl entity) {
        entity.setCreateDate(new Date());
        dao.update(entity);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void addRow(ApiShiYsJyjcNl entity) {
        // [检验检测能力] 新增 设置 序号
        if ("0".equals(entity.getFlag())) {
            String result = reSetxhAddRow(entity);
            entity.setSortingNumber(result);
        } else {
            String result = reSetxhAddRowYqsb(entity);
            entity.setSortingNumber(result);
        }
        entity.setCreateDate(new Date());
        dao.save(entity);
    }

    /**
     * 上报端 新增 能力数据  设置 序号
     * 1.一级分类一致 二级分类不一致
     * 2.一级分类一致 二级分类一致 产品名称一致
     * 3.一级分类一致 二级分类一致 产品名称不一致
     *
     * @param entity
     * @return
     */
    private String reSetxhAddRow(ApiShiYsJyjcNl entity) {
        String result = "";
        HashMap<String, Object> reqMap = new HashMap<>();
        reqMap.put("serialNumber", entity.getSerialNumber());
        reqMap.put("flag", entity.getFlag());
        reqMap.put("typeName", entity.getTypeName());
        reqMap.put("siteName", entity.getSiteName());
        Map<String, Object> resultMap = dao.getDetailList(reqMap);
        ArrayList resultList = (ArrayList) resultMap.get(R.DATA_NAME);
        if (resultList != null && resultList.size() > 0) {
            LinkedHashSet<String> yjflSet = new LinkedHashSet<>();
            LinkedHashSet<String> ejflSet = new LinkedHashSet<>();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, Object> map = (Map<String, Object>) resultList.get(i);
                yjflSet.add((String) map.get("YJFL"));
                ejflSet.add((String) map.get("EJFL"));
            }

            int ejflSize = ejflSet.size();
            String addYjfl = entity.getYjfl();
            String addEjfl = entity.getEjfl();
            // 新增数据 一级分类不存在
            if (!yjflSet.contains(addYjfl)) {
                result = (ejflSize + 1) + ".0";
                return result;
            }
            // 新增数据 一级分类存在
            Map<String, Object> flMap = new HashMap<>();
            if (yjflSet != null && yjflSet.size() > 0) {
                for (String value : yjflSet) {
                    List list = new ArrayList();
                    for (int i = 0; i < resultList.size(); i++) {
                        Map<String, Object> map = (Map<String, Object>) resultList.get(i);
                        String dbYjfl = (String) map.get("YJFL");
                        if (dbYjfl.equals(value)) {
                            list.add(map);
                        }
                    }
                    flMap.put(value, list);
                }
            }
            // 新增 一级分类存在 、 二级分类 不存在
            if (!ejflSet.contains(addEjfl)) {
                List list = (List) flMap.get(addYjfl);
                LinkedHashSet<String> set = new LinkedHashSet<>();
                for (int i = 0; i < list.size(); i++) {
                    Map<String, Object> map = (Map<String, Object>) list.get(i);
                    set.add((String) map.get("EJFL"));
                }
                result = (set.size() + 1) + ".0";
                return result;
            }

            // 新增 一级分类存在 、 二级分类 存在
            List list = (List) flMap.get(addYjfl);
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = (Map<String, Object>) list.get(i);
                String ejfl = (String) map.get("EJFL");
                if (ejfl.equals(addEjfl)) {
                    result = (String) map.get("SORTINGNUMBER");
                }
            }
        }
        return result;
    }

    /**
     * 重新设置序号
     * 1.一级分类List<>
     * 2.遍历一级分类List 生成 一级分类中 的 二级分类List
     * 3.遍历二级分类List 设置 sortNumber
     *
     * @param reqMap
     * @throws Exception
     */
    @Override
    public List<ApiShiYsJyjcNl> reSetXh(HashMap<String, Object> reqMap) throws Exception {
        Map<String, Object> detailMap = dao.getDetailList(reqMap);
        List<ApiShiYsJyjcNl> updateList = new ArrayList<>();
        if (detailMap != null) {
            List<Map<String, Object>> detailList = (List<Map<String, Object>>) detailMap.get(R.DATA_NAME);
            LinkedHashSet<String> setfl = new LinkedHashSet<>();
            List<Map<String, Object>> flList = new ArrayList<>();
            if (detailList != null && detailList.size() > 0) {
                for (int i = 0; i < detailList.size(); i++) {
                    Map<String, Object> map = detailList.get(i);
                    setfl.add((String) map.get("EJFL"));
                }
                if (setfl != null && setfl.size() > 0) {
                    int count = 1;
                    for (String value : setfl) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("EJFL", value);
                        map.put("NUMBER", count);
                        count++;
                        flList.add(map);
                    }
                }
            }
            if (flList != null && flList.size() > 0) {
                for (int i = 0; i < flList.size(); i++) {
                    Map<String, Object> map = flList.get(i);
                    String key = (String) map.get("EJFL");
                    int value = (int) map.get("NUMBER");
                    String upSijfl = "";
                    int countNum = 0;
                    for (int j = 0; j < detailList.size(); j++) {
                        Map<String, Object> dbMap = detailList.get(j);
                        String dbEjfl = (String) dbMap.get("EJFL");
                        String dbSijfl = (String) dbMap.get("SIJFL");
                        if (dbEjfl.equals(key)) {
                            if (!dbSijfl.equals(upSijfl) && !"".equals(upSijfl)) {
                                countNum++;
                            }
                            upSijfl = dbSijfl;
                            String newSortNum = value + "." + (countNum + 1);
                            dbMap.put("SORTINGNUMBER", newSortNum);
                            ApiShiYsJyjcNl entity = JSON.parseObject(JSON.toJSONString(dbMap), ApiShiYsJyjcNl.class);
                            updateList.add(entity);
                        }
                    }
                }
            }
        }
        return updateList;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void updateList(List<ApiShiYsJyjcNl> entityList) {
        if (entityList != null && entityList.size() > 0) {
            for (ApiShiYsJyjcNl entity : entityList) {
                dao.update(entity);
            }
        }
    }

    @Override
    public void delnl(String params) {
        dao.delnl(params);
    }

    @Override
    public void update(ApiShiYsJyjcNl apiShiYsJyjcNl) {
        dao.update(apiShiYsJyjcNl);
    }

    @Override
    public ApiShiYsJyjcNl getById(Long id) {
        return dao.getById(id);
    }

    /**
     * 重新设置序号
     * 1.一级分类List<>
     * 2.遍历一级分类List 生成 一级分类中 的 二级分类List
     * 3.遍历二级分类List 设置 sortNumber
     *
     * @param reqMap
     * @throws Exception
     */
    @Override
    public List<ApiShiYsJyjcNl> reSetXhYqsb(HashMap<String, Object> reqMap) throws Exception {
        Map<String, Object> detailMap = dao.getDetailList(reqMap);
        List<ApiShiYsJyjcNl> updateList = new ArrayList<>();
        if (detailMap != null) {
            List<Map<String, Object>> detailList = (List<Map<String, Object>>) detailMap.get(R.DATA_NAME);
            LinkedHashSet<String> setfl = new LinkedHashSet<>();
            LinkedHashSet<String> setEjfl = new LinkedHashSet<>();
            if (detailList != null && detailList.size() > 0) {
                for (int i = 0; i < detailList.size(); i++) {
                    Map<String, Object> map = detailList.get(i);
                    setEjfl.add((String) map.get("EJFL"));
                }

                for (String ejfl : setEjfl) {
                    for (int i = 0; i < detailList.size(); i++) {
                        Map<String, Object> map = detailList.get(i);
                        String dbEjfl = (String) map.get("EJFL");
                        if (dbEjfl.equals(ejfl)) {
                            setfl.add((String) map.get("PRODUCTNAME"));
                        }
                    }
                }

                int count = 1;
                List flList = new ArrayList();
                for (String value : setfl) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("PRODUCTNAME", value);
                    map.put("NUMBER", count);
                    count++;
                    flList.add(map);
                }

                if (flList != null && flList.size() > 0) {
                    for (int i = 0; i < flList.size(); i++) {
                        Map<String, Object> map1 = (Map<String, Object>) flList.get(i);
                        String key = (String) map1.get("PRODUCTNAME");
                        int value = (int) map1.get("NUMBER");
                        String upProductName = "";
                        int countNum = 0;
                        for (int j = 0; j < detailList.size(); j++) {
                            Map<String, Object> dbMap = detailList.get(j);
                            String dbProductName = (String) dbMap.get("PRODUCTNAME");
                            if (dbProductName.equals(key)) {
                                if (!dbProductName.equals(upProductName) && !"".equals(upProductName)) {
                                    countNum++;
                                }
                                upProductName = dbProductName;
                                String newSortNum = value + "." + (countNum + 1);
                                dbMap.put("SORTINGNUMBER", newSortNum);
                                ApiShiYsJyjcNl entity = JSON.parseObject(JSON.toJSONString(dbMap), ApiShiYsJyjcNl.class);
                                updateList.add(entity);
                            }
                        }
                    }
                }
            }
        }
        return updateList;
    }

    /**
     * 上报端 新增 仪器设备  设置 序号
     * 1. 二级分类 不存在
     * 2.二级分类存在 名称不存在
     * 3.二级分类存在 名称存在
     *
     * @param entity
     * @return
     */
    private String reSetxhAddRowYqsb(ApiShiYsJyjcNl entity) {
        String result = "";
        HashMap<String, Object> reqMap = new HashMap<>();
        reqMap.put("serialNumber", entity.getSerialNumber());
        reqMap.put("flag", entity.getFlag());
        reqMap.put("typeName", entity.getTypeName());
        reqMap.put("siteName", entity.getSiteName());
        Map<String, Object> resultMap = dao.getDetailList(reqMap);
        ArrayList resultList = (ArrayList) resultMap.get(R.DATA_NAME);
        if (resultList != null && resultList.size() > 0) {
            LinkedHashSet<String> ejflSet = new LinkedHashSet<>();
            LinkedHashSet<String> productNameSet = new LinkedHashSet<>();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, Object> map = (Map<String, Object>) resultList.get(i);
                ejflSet.add((String) map.get("EJFL"));
                productNameSet.add((String) map.get("PRODUCTNAME"));
            }

            int productNameSize = productNameSet.size();
            String addEjfl = entity.getEjfl();
            String addProductName = entity.getProductName();
            // 新增数据 二级分类不存在
            if (!ejflSet.contains(addEjfl)) {
                result = (productNameSize + 1) + ".0";
                return result;
            }
            // 新增数据 二级分类存在
            Map<String, Object> flMap = new HashMap<>();
            if (ejflSet != null && ejflSet.size() > 0) {
                for (String value : ejflSet) {
                    List list = new ArrayList();
                    for (int i = 0; i < resultList.size(); i++) {
                        Map<String, Object> map = (Map<String, Object>) resultList.get(i);
                        String dbEjfl = (String) map.get("EJFL");
                        if (dbEjfl.equals(value)) {
                            list.add(map);
                        }
                    }
                    flMap.put(value, list);
                }
            }
            // 新增  名称 不存在
            if (!productNameSet.contains(addProductName)) {
                List list = (List) flMap.get(addEjfl);
                LinkedHashSet<String> set = new LinkedHashSet<>();
                for (int i = 0; i < list.size(); i++) {
                    Map<String, Object> map = (Map<String, Object>) list.get(i);
                    set.add((String) map.get("PRODUCTNAME"));
                }
                result = (set.size() + 1) + ".0";
                return result;
            }

            // 新增 二级分类存在 、 名称 存在
            List list = (List) flMap.get(addEjfl);
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = (Map<String, Object>) list.get(i);
                String productName = (String) map.get("PRODUCTNAME");
                if (productName.equals(addProductName)) {
                    result = (String) map.get("SORTINGNUMBER");
                }
            }
        }
        return result;
    }
}