package com.yongjie.ZhiJianSbpt.action;

import com.alibaba.fastjson.JSONObject;
import com.yongjie.ZhiJianSbpt.action.base.BaseAction;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcCd;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcQzr;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcRy;
import com.yongjie.ZhiJianSbpt.service.ApiShiYsJyjcCdService;
import com.yongjie.ZhiJianSbpt.service.ApiShiYsJyjcQzrService;
import com.yongjie.ZhiJianSbpt.service.ApiShiYsJyjcRyService;
import com.yongjie.ZhiJianSbpt.utilities.JSON;
import com.yongjie.ZhiJianSbpt.utilities.R;
import com.yongjie.ZhiJianSbpt.utilities.RequestDataCheckUtil;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("ApiShiYsJyjcCdAction")
@Scope("prototype")
public class ApiShiYsJyjcCdAction extends BaseAction<ApiShiYsJyjcCd> {


    @Resource(name = ApiShiYsJyjcCdService.SERVICE_NAME)
    private ApiShiYsJyjcCdService service;
    @Resource(name = ApiShiYsJyjcQzrService.SERVICE_NAME)
    private ApiShiYsJyjcQzrService qzrService;
    @Resource(name = ApiShiYsJyjcRyService.SERVICE_NAME)
    private ApiShiYsJyjcRyService ryService;

    /**
     * 场地保存||更新
     */
    public void save() throws Exception {
        String resultJson;
        if (StringUtil.isNullOrEmpty(userName)) {
            resultJson = R.error("用户认证已过期,请重新登录!");
            response.getWriter().print(resultJson);
            return;
        }
        try {
            // 获取传递参数
            String json = req.getParameter("data");
            try {
                ApiShiYsJyjcCd entity = RequestDataCheckUtil.saveCd(json);
                if (StringUtil.isNullOrEmpty(entity.getId())) {
                    // 新增 保存场地 设置 默认值
                    entity.setCreateDate(new Date());
                    entity.setSiteAddress(entity.getSiteAddress().replaceAll(" ", ""));
                    entity.setUpdateDate(new Date());
                    entity.setSiteType("2");
                    entity.setUnid("1");
                    service.save(entity);
                    resultJson = R.ok("保存成功!");

                } else {
                    ApiShiYsJyjcCd dbEntity = service.queryById(entity.getId());
                    List<ApiShiYsJyjcQzr> qzrEntity = qzrService.queryByBatchSerialNumber(entity.getSerialNumber());
                    //授权签字人修改地址
                    for (int i = 0; i < qzrEntity.size(); i++) {
                        String qzrAddress = qzrEntity.get(i).getAddress();
                        if (qzrAddress.equals(dbEntity.getSiteAddress()) && !StringUtil.isNullOrEmpty(qzrAddress)) {
                            qzrEntity.get(i).setAddress(entity.getSiteAddress());
                            qzrService.update(qzrEntity.get(i));
                        }
                    }
                    //人员修改地址
                    List<ApiShiYsJyjcRy> ryEntity = ryService.queryByBatchSerialNumber(entity.getSerialNumber());
                    for (int i = 0; i < ryEntity.size(); i++) {
                        String ryAddress = ryEntity.get(i).getAddress();
                        if (ryAddress.equals(dbEntity.getSiteAddress()) && !StringUtil.isNullOrEmpty(ryAddress)) {
                            ryEntity.get(i).setAddress(entity.getSiteAddress());
                            ryService.update(ryEntity.get(i));
                        }
                    }

                    dbEntity.setUpdateDate(new Date());
                    dbEntity.setSiteAddress(entity.getSiteAddress());
                    service.update(dbEntity);
                    resultJson = R.ok("修改成功!,请重新保存授权签字人信息及申请表");
                }
            } catch (Exception e) {
                throw new Exception("保存失败!");
            }
        } catch (Exception e) {
            resultJson = R.error(e.getMessage());
        }
        response.getWriter().print(resultJson);
    }

    /**
     * 通过流水号 获取 场地 List
     *
     * @throws Exception
     */
    public void initLoad() throws Exception {
        String resultJson;
        if (StringUtil.isNullOrEmpty(userName)) {
            resultJson = R.error("用户认证已过期,请重新登录!");
            response.getWriter().print(resultJson);
            return;
        }

        HashMap<Object, Object> reqMap = new HashMap<>();
        String serialNumber = req.getParameter("serialNumber");
        String siteType = req.getParameter("siteType");
        reqMap.put("serialNumber", serialNumber);
        reqMap.put("siteType", siteType);
        try {
            Map<String, Object> resultMap = service.queryListBySerialNumber(reqMap);
            resultJson = R.page(resultMap);
        } catch (Exception e) {
            resultJson = R.error(e.getMessage());
        }

        response.getWriter().print(resultJson);
    }

    public void delete() throws Exception {
        String resultJson;
        if (StringUtil.isNullOrEmpty(userName)) {
            resultJson = R.error("用户认证已过期,请重新登录!");
            response.getWriter().print(resultJson);
            return;
        }
        try {
            // 获取传递参数
            String reqJson = req.getParameter("deleteIds");
            try {
                List<Object> idList = JSONObject.parseObject(reqJson, List.class);
                if (idList.size() > 0) {
                    service.deleteBatch(idList);
                    resultJson = R.ok("删除成功!");
                } else {
                    resultJson = R.ok("没有数据删除!");
                }
            } catch (Exception e) {
                throw new Exception("删除失败!");
            }
        } catch (Exception e) {
            resultJson = R.error(e.getMessage());
        }
        response.getWriter().print(resultJson);
    }

    /**
     * 获取 主管部门数据
     *
     * @throws Exception
     */
    public void getJgdzData() throws Exception {
        String resultJson = "";
        if (!StringUtil.isNullOrEmpty(userName)) {
            try {
                String serialNumber = req.getParameter("serialNumber");
                Map<String, Object> resultMap = service.getJgdzData(serialNumber);
                resultJson = JSON.Encode(resultMap.get("data"));
            } catch (Exception e) {
                resultJson = com.yongjie.ZhiJianSbpt.module.util.R.error(e.getMessage(), "查询失败!");
            }
        } else {
            resultJson = com.yongjie.ZhiJianSbpt.module.util.R.error("用户认证已过期,请重新登录!");
        }
        response.getWriter().print(resultJson);
    }
}
