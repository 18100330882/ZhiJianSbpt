package com.yongjie.ZhiJianSbpt.action;

import com.alibaba.fastjson.JSONObject;
import com.yongjie.ZhiJianSbpt.action.base.BaseAction;
import com.yongjie.ZhiJianSbpt.model.Area;
import com.yongjie.ZhiJianSbpt.model.BizApiShiYsJyjc;
import com.yongjie.ZhiJianSbpt.service.*;
import com.yongjie.ZhiJianSbpt.service.shiYsJyjc.ApiFileService;
import com.yongjie.ZhiJianSbpt.utilities.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller("BizApiShiysjyjcAction")
@Scope("prototype")
public class BizApiShiysjyjcAction extends BaseAction<BizApiShiYsJyjc> {

    @Resource(name = BizApiShiysjyjcService.SERVICE_NAME)
    private BizApiShiysjyjcService service;

    @Resource(name = ApiShiYsJyjcSqsService.SERVICE_NAME)
    private ApiShiYsJyjcSqsService sqsService;

    @Resource(name = ApiShiYsJyjcQzrService.SERVICE_NAME)
    private ApiShiYsJyjcQzrService qzrService;

    @Resource(name = ApiShiYsJyjcRyService.SERVICE_NAME)
    private ApiShiYsJyjcRyService ryService;

    @Resource(name = ApiShiYsJyjcSqlxService.SERVICE_NAME)
    private ApiShiYsJyjcSqlxService sqlxService;

    @Resource(name = ApiShiYsJyjcJgzyService.SERVICE_NAME)
    private ApiShiYsJyjcJgzyService jgzyService;

    @Resource(name = ApiFileService.SERVICE_NAME)
    private ApiFileService fileService;

    @Resource(name = IAreaService.SERVICE_NAME)
    private IAreaService areaService;

    /**
     * 封面信息保存
     */
    public void save() throws Exception {
        String resultJson = "";
        String reqJson = req.getParameter("data");
        String str = req.getParameter("str");
        String strN = req.getParameter("strN");

        if (StringUtil.isEmpty(userName)) {
            resultJson = R.error("用户认证已过期,请重新登录!");
            response.getWriter().print(resultJson);
            return;
        }

        if (StringUtil.isNullOrEmpty(reqJson)) {
            throw new RuntimeException("数据不能为空!");
        }
        try {
            HashMap<String, Object> map = JSONObject.parseObject(reqJson, HashMap.class);
            BizApiShiYsJyjc oldEntity = service.queryBySerialNumber(map.get("serialNumber") == null ? null : map.get("serialNumber").toString());
            if (StringUtil.isNullOrEmpty(oldEntity)) {
                BizApiShiYsJyjc entity = (BizApiShiYsJyjc) HashmapAndEntityTransfer.hashmapTransferToEntity(new BizApiShiYsJyjc(), map);
                Long flowId = entity.getFlowId();
                Long areaId = entity.getAreaId();
                Long checkAreaId = entity.getCheckAreaId();
                Area areaEntity = areaService.getById(areaId);
                String jgmc = entity.getJgmc().replaceAll(" ", "");
                Area checkAreaEntity = areaService.getById(checkAreaId);
                if (!StringUtil.isNullOrEmpty(areaId)) {
                    entity.setAreaId(areaId);
                    entity.setCheckAreaId(areaId);
                    entity.setAreaName(areaEntity.getAreaName());
                }
                if (!StringUtil.isNullOrEmpty(checkAreaId)) {
                    entity.setCheckAreaId(checkAreaId);
                    entity.setCheckAreaName(checkAreaEntity.getAreaName());
                }
                entity.setZylylbDm(str);
                entity.setFlag(0);
                entity.setZylylbName(strN);
                entity.setFlowId(flowId);
                entity.setJgmc(jgmc);
                entity.setCreateDate(new Date());
                entity.setUpdateDate(new Date());
                entity.setCreateUser(userName);
                service.save(entity);
            } else {
                oldEntity = (BizApiShiYsJyjc) HashmapAndEntityTransfer.hashmapTransferToEntity(oldEntity, map);
                oldEntity.setZylylbDm(str);
                oldEntity.setJgmc(oldEntity.getJgmc().replaceAll(" ", ""));
                oldEntity.setZylylbName(strN);
                service.update(oldEntity);
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
        String jgmc = req.getParameter("keyJgmc");
        String flowId = req.getParameter("flowId");
        String sortField = req.getParameter("sortField");
        String sortOrder = req.getParameter("sortOrder");
        Map<Object, Object> reqMap = new HashMap<>();
        reqMap.put("pageIndex", pageIndex);
        reqMap.put("pageSize", pageSize);
        reqMap.put("sortField", sortField);
        reqMap.put("sortOrder", sortOrder);

        reqMap.put("jgmc", jgmc);
        reqMap.put("flowId", flowId);
        reqMap.put("userName", userName);

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
            BizApiShiYsJyjc entity = service.queryBySerialNumber(serialNumber);
            if (entity == null) {
                resultJson = R.error("没有查询到封面信息数据，页面无法进行保存操作！请先进行封面信息保存！");
            } else {
                resultJson = R.ok(entity);
            }
        } catch (Exception e) {
            resultJson = R.error("请求有误!请关闭刷新重试！", e);
        }
        response.getWriter().print(resultJson);
    }


    /**
     * 删除封面及相关信息
     *
     * @throws Exception
     */
    public void delete() throws Exception {
        //获得参数
        String idStr = req.getParameter("idResult");
        String serialNumber = req.getParameter("serialNumber");
        //判空操作
        if (StringUtil.isNullOrEmpty(idStr) || StringUtil.isNullOrEmpty(serialNumber)) {
            return;
        }
        String[] ids = idStr.split(",");
        for (int i = 0, l = ids.length; i < l; i++) {

            /*删除概况信息*/
            sqsService.deleteBatchBySerialNumber(serialNumber);

            /*删除签字人信息*/
            qzrService.deleteBatchBySerialNumber(serialNumber);

            /*删除申请类型信息*/
            sqlxService.deleteBatchBySerialNumber(serialNumber);

            /*删除机构资源信息*/
            jgzyService.deleteBatchBySerialNumber(serialNumber);

            /*删除机构人员信息*/
            ryService.deleteBatchBySerialNumber(serialNumber);

            /*删除附件表*/
            fileService.deleteBatchBySerialNumber(serialNumber);

            /*删除封面信息*/
            String id = ids[i];
            //根据ID删除内容
            service.delete(Long.parseLong(id));

        }
        response.getWriter().write("删除成功");

    }

    /**
     * 上报
     *
     * @throws Exception
     */
    public void shangBao() throws Exception {
        String resultJson = null;
        if (!StringUtil.isEmpty(userName)) {
            String serialNumber = req.getParameter("serialNumber");
            String flag = req.getParameter("flag") == null ? "" : req.getParameter("flag");
            BizApiShiYsJyjc entity = service.queryBySerialNumber(serialNumber);
            if (!StringUtil.isNullOrEmpty(entity)) {
                if (entity.getFlag() == 4) {
                    flag = "5";
                }
                if (entity.getFlag() == 3) {
                    flag = "1";
                }
                entity.setFlag(Integer.valueOf(flag));
                service.update(entity);
            }
        } else {
            resultJson = R.error("用户认证已过期,请重新登录!");
        }

        response.getWriter().print(resultJson);
    }

    public void clyshApproval() throws Exception {
        String resultJson = null;
        if (!StringUtil.isEmpty(userName)) {
            try {
                // 流水号
                String serialNumber = req.getParameter("serialNumber");
                BizApiShiYsJyjc bizModel = service.getBizShiYsJyjcBySerialNumber(serialNumber);
                if (!StringUtil.isEmpty(bizModel)) {
                    bizModel.setFlag(7); // 状态修改为 7
                    bizModel.setUpdateDate(new Date()); // 更新时间
                    bizModel.setNodeDate(new Date());// 环节时间
                    bizModel.setClyshCzr(userName);
                    service.update(bizModel);
                    resultJson = R.ok("预审核通过!");
                } else {
                    resultJson = R.error("预审核失败!");
                }
            } catch (Exception e) {
                resultJson = com.yongjie.ZhiJianSbpt.module.util.R.error(e.getMessage(), "预审核异常!");
            }
        } else {
            resultJson = R.error("用户认证已过期,请重新登录!");
        }
        response.getWriter().print(resultJson);
    }
}
