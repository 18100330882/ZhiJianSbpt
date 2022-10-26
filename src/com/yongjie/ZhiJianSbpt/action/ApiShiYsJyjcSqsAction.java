package com.yongjie.ZhiJianSbpt.action;

import com.alibaba.fastjson.JSONObject;
import com.yongjie.ZhiJianSbpt.action.base.BaseAction;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcSqs;
import com.yongjie.ZhiJianSbpt.model.BizApiShiYsJyjc;
import com.yongjie.ZhiJianSbpt.service.ApiShiYsJyjcSqsService;
import com.yongjie.ZhiJianSbpt.service.BizApiShiysjyjcService;
import com.yongjie.ZhiJianSbpt.utilities.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller("ApiShiYsJyjcSqs")
@Scope("prototype")
public class ApiShiYsJyjcSqsAction extends BaseAction<ApiShiYsJyjcSqs> {

    /**
     * 主表
     */
    @Resource(name = BizApiShiysjyjcService.SERVICE_NAME)
    private BizApiShiysjyjcService bizShiysjyjcService;

    /**
     * 概况
     */
    @Resource(name = ApiShiYsJyjcSqsService.SERVICE_NAME)
    private ApiShiYsJyjcSqsService service;

    /**
     * 概况信息 保存
     *
     * @throws Exception
     */
    public void save() throws Exception {
        String resultJson = "";
        String reqJson = req.getParameter("data");
        if (StringUtil.isNullOrEmpty(reqJson)) {
            throw new RuntimeException("数据不能为空!");
        }
        try {
            HashMap map = JSONObject.parseObject(reqJson, HashMap.class);
            ApiShiYsJyjcSqs apiShiYsJyjcSqs = service.queryBySerialNumber(map.get("SERIALNUMBER") == null ? null : map.get("SERIALNUMBER").toString());
            // 新增 or  更新
            if (!StringUtil.isNullOrEmpty(apiShiYsJyjcSqs)) {
                apiShiYsJyjcSqs = (ApiShiYsJyjcSqs) HashmapAndEntityTransfer.hashmapTransferToEntity2(apiShiYsJyjcSqs, map);
                service.update(apiShiYsJyjcSqs);
            } else {
                ApiShiYsJyjcSqs entity = (ApiShiYsJyjcSqs) HashmapAndEntityTransfer.hashmapTransferToEntity2(new ApiShiYsJyjcSqs(), map);
                entity.setCreateDate(new Date());
                entity.setUpdateDate(new Date());
                service.save(entity);
            }
            resultJson = R.ok("保存成功！");
        } catch (Exception e) {
            resultJson = R.error(e.getMessage());
        }
        response.getWriter().print(resultJson);
    }

    /**
     * 初始化数据
     *
     * @throws Exception
     */
    public void initLoad() throws Exception {
        String resultJson = "";
        if (StringUtil.isEmpty(userName)) {
            resultJson = R.error("用户认证已过期,请重新登录!");
            response.getWriter().print(resultJson);
            return;
        }
        String serialNumber = req.getParameter("serialNumber");
        if (StringUtil.isNullOrEmpty(serialNumber)) {
            resultJson = R.error("请求有误!请关闭刷新重试！");
            response.getWriter().print(resultJson);
            return;
        }
        try {
            Map entity = service.queryBySerialNumber2(serialNumber);
            if (entity == null) {
                resultJson = R.error("未查询到概况相关数据！");
            } else {
                resultJson = JSON.Encode(entity);
            }
        } catch (Exception e) {
            resultJson = R.error("请求有误!请关闭刷新重试！", e);
        }
        response.getWriter().print(resultJson);
    }
}
