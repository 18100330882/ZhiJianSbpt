package com.yongjie.ZhiJianSbpt.action;

import com.alibaba.fastjson.JSONObject;
import com.yongjie.ZhiJianSbpt.action.base.BaseAction;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcSqlx;
import com.yongjie.ZhiJianSbpt.service.ApiShiYsJyjcSqlxService;
import com.yongjie.ZhiJianSbpt.utilities.JSON;
import com.yongjie.ZhiJianSbpt.utilities.R;
import com.yongjie.ZhiJianSbpt.utilities.RequestDataCheckUtil;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Date;

@Controller("ApiShiYsJyjcSqlxAction")
@Scope("prototype")
public class ApiShiYsJyjcSqlxAction extends BaseAction<ApiShiYsJyjcSqlx> {

    @Resource(name = ApiShiYsJyjcSqlxService.SERVICE_NAME)
    private ApiShiYsJyjcSqlxService service;


    /**
     * 保存、更新 申请类型
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
            ApiShiYsJyjcSqlx entity = RequestDataCheckUtil.checkSqlx(reqJson);
            String serialNumber = entity.getSerialNumber();
            ApiShiYsJyjcSqlx dbEntity = service.queryBySerialNumber(serialNumber);
            // 新增 or  更新
            if (dbEntity != null && dbEntity.getId() > 0) {
                dbEntity.setApplicationType(entity.getApplicationType());
                dbEntity.setCmaPermitNumber(entity.getCmaPermitNumber());
                dbEntity.setCmaPermitEffectiveDate(entity.getCmaPermitEffectiveDate());
                dbEntity.setUpdateDate(new Date());
                service.update(dbEntity);
            } else {
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

    //只保存申请类型方法
    public void saveSqlx() throws Exception {
        String resultJson = "";
        String reqJson = req.getParameter("data");
        if (StringUtil.isNullOrEmpty(reqJson)) {
            throw new RuntimeException("数据不能为空!");
        }

        try {
            ApiShiYsJyjcSqlx entity = JSONObject.parseObject(reqJson, ApiShiYsJyjcSqlx.class);
            String serialNumber = entity.getSerialNumber();
            ApiShiYsJyjcSqlx dbEntity = service.queryBySerialNumber(serialNumber);
            // 新增 or  更新
            if (dbEntity != null && dbEntity.getId() > 0) {
                dbEntity.setApplicationType(entity.getApplicationType());
                dbEntity.setUpdateDate(new Date());
                service.update(dbEntity);
            } else {
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
            ApiShiYsJyjcSqlx entity = service.queryBySerialNumber(serialNumber);
            if (entity == null) {
                resultJson = R.error("未查询到申请类型相关数据！");
            } else {
                resultJson = R.ok(entity);
            }
        } catch (Exception e) {
            resultJson = R.error("请求有误!请关闭刷新重试！", e);
        }
        response.getWriter().print(resultJson);
    }

}
