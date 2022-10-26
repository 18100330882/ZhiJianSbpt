package com.yongjie.ZhiJianSbpt.action;

import com.yongjie.ZhiJianSbpt.action.base.BaseAction;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcRy;
import com.yongjie.ZhiJianSbpt.service.ApiShiYsJyjcRyService;
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

@Controller("ApiShiYsJyjcRyAction")
@Scope("prototype")
public class ApiShiYsJyjcRyAction extends BaseAction<ApiShiYsJyjcRy> {

    @Resource(name = ApiShiYsJyjcRyService.SERVICE_NAME)
    private ApiShiYsJyjcRyService service;

    /**
     * 封面信息保存
     */
    public void save() throws Exception {
        String resultJson = "";
        String reqJson = req.getParameter("data");
        String ID = req.getParameter("id") == null ? "" : req.getParameter("id");
        //增加或修改
        String zt = req.getParameter("zt") == null ? "" : req.getParameter("zt");
        if (StringUtil.isNullOrEmpty(reqJson)) {
            throw new RuntimeException("数据不能为空!");
        }
        try {
            ApiShiYsJyjcRy entity = RequestDataCheckUtil.checkRyxx(reqJson);
            // 新增 or  更新
            if (zt.equals("save")) {
                entity.setCreateDate(new Date());
                entity.setUpdateDate(new Date());
                service.save(entity);
            } else {
                ApiShiYsJyjcRy apiShiYsJyjcRy = service.queryById(Long.valueOf(ID));
                if (apiShiYsJyjcRy != null) {
                    apiShiYsJyjcRy.setName(entity.getName());
                    apiShiYsJyjcRy.setSex(entity.getSex());
                    apiShiYsJyjcRy.setAge(entity.getAge());
                    apiShiYsJyjcRy.setEducation(entity.getEducation());
                    apiShiYsJyjcRy.setDocumentType(entity.getDocumentType());
                    apiShiYsJyjcRy.setIdCard(entity.getIdCard());
                    apiShiYsJyjcRy.setPosition(entity.getPosition());
                    apiShiYsJyjcRy.setProfessional(entity.getProfessional());
                    apiShiYsJyjcRy.setTechnicalFieldYear(entity.getTechnicalFieldYear());
                    apiShiYsJyjcRy.setDepartment(entity.getDepartment());
                    apiShiYsJyjcRy.setAddress(entity.getAddress());
                    apiShiYsJyjcRy.setSerialNumber(entity.getSerialNumber());
                    apiShiYsJyjcRy.setUpdateDate(new Date());
                    service.update(apiShiYsJyjcRy);
                }
            }
            resultJson = R.ok("保存成功！");
        } catch (Exception e) {
            resultJson = R.error(e.getMessage());
        }
        response.getWriter().print(resultJson);
    }

    /**
     * 获取主表 数据
     *
     * @throws Exception
     */
    public void getList() throws Exception {
        String resultJson = "";
        if (StringUtil.isEmpty(userName)) {
            resultJson = R.error("用户认证已过期,请重新登录!");
            response.getWriter().print(resultJson);
            return;
        }
        // 参数
        String serialNumber = req.getParameter("serialNumber");
        String sortField = req.getParameter("sortField");
        String sortOrder = req.getParameter("sortOrder");
        Map<Object, Object> reqMap = new HashMap<>();
        reqMap.put("pageIndex", pageIndex);
        reqMap.put("pageSize", pageSize);
        reqMap.put("sortField", sortField);
        reqMap.put("sortOrder", sortOrder);
        reqMap.put("serialNumber", serialNumber);

        try {
            Map<String, Object> listPage = service.getList(reqMap);
            resultJson = R.page(listPage);
        } catch (Exception e) {
            resultJson = R.error("查询失败", e);
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
            List<ApiShiYsJyjcRy> entity = service.queryByBatchSerialNumber(serialNumber);
            if (entity.size() == 0) {
                resultJson = R.error("未查询到人员相关数据！");
            } else {
                resultJson = R.ok(entity);
            }
        } catch (Exception e) {
            resultJson = R.error("请求有误!请关闭刷新重试！", e);
        }
        response.getWriter().print(resultJson);
    }


    //根据id删除人员信息
    public void deleteApiRy() throws Exception {
        //获得参数
        String idStr = req.getParameter("idResult");
        //判空操作
        if (StringUtil.isNullOrEmpty(idStr)) {
            return;
        }
        String[] ids = idStr.split(",");
        for (int i = 0, l = ids.length; i < l; i++) {
            String id = ids[i];
            //根据ID删除内容
            service.delete(Long.parseLong(id));
            response.getWriter().write("删除成功");
        }
    }
}
