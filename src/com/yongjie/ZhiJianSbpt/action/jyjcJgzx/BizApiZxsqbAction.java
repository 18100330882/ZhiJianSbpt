package com.yongjie.ZhiJianSbpt.action.jyjcJgzx;

import com.yongjie.ZhiJianSbpt.action.base.BaseAction;
import com.yongjie.ZhiJianSbpt.model.Area;
import com.yongjie.ZhiJianSbpt.model.jyjcJgzx.BizApiZxsqb;
import com.yongjie.ZhiJianSbpt.service.*;
import com.yongjie.ZhiJianSbpt.service.jyjcJgzx.BizApiZxsqbService;
import com.yongjie.ZhiJianSbpt.service.shiYsJyjc.ApiFileService;
import com.yongjie.ZhiJianSbpt.utilities.R;
import com.yongjie.ZhiJianSbpt.utilities.RequestDataCheckUtil;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller("BizApiZxsqbAction")
@Scope("prototype")
public class BizApiZxsqbAction extends BaseAction<BizApiZxsqb> {

    @Resource(name = BizApiZxsqbService.SERVICE_NAME)
    private BizApiZxsqbService service;

    @Resource(name = ShiYsJyjcFuJianService.SERVICE_NAME)
    private ShiYsJyjcFuJianService sqsService;

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
        if (StringUtil.isNullOrEmpty(reqJson)) {
            throw new RuntimeException("数据不能为空!");
        }
        try {
            BizApiZxsqb entity = RequestDataCheckUtil.checkZxsqb(reqJson);
            String serialNumber = entity.getSerialNumber();
            Long flowId = entity.getFlowId();
            Long areaId = entity.getAreaId();
            BizApiZxsqb dbEntity = service.queryBySerialNumber(serialNumber);
            Area areaEntity = areaService.getById(areaId);
            Area checkAreaEntity = areaService.getById(entity.getCheckAreaId());
            String areaName = areaEntity.getAreaName();
            // 新增 or  更新
            if (dbEntity != null && dbEntity.getId() > 0) {
                dbEntity.setOrganizationName(entity.getOrganizationName());
                dbEntity.setOrganizationAddress(entity.getOrganizationAddress());
                dbEntity.setCertificateNumber(entity.getCertificateNumber());
                dbEntity.setShxydm(entity.getShxydm());
                dbEntity.setStartDate(entity.getStartDate());
                dbEntity.setEndDate(entity.getEndDate());
                dbEntity.setContacts(entity.getContacts());
                dbEntity.setContactsPhone(entity.getContactsPhone());
                dbEntity.setUnitPeople(entity.getUnitPeople());
                dbEntity.setUnitPhone(entity.getUnitPhone());
                dbEntity.setOrganizationDate(entity.getOrganizationDate());
                dbEntity.setCancelReason(entity.getCancelReason());
                dbEntity.setUpdateDate(new Date());
                dbEntity.setAreaId(entity.getAreaId());
                dbEntity.setCheckAreaId(entity.getCheckAreaId());
                dbEntity.setAreaName(areaName);
                dbEntity.setCheckAreaName(checkAreaEntity.getAreaName());
                service.update(dbEntity);
            } else {
                entity.setFlowId(flowId);
                entity.setFlag("0");
                entity.setSqrq(new Date());
                entity.setOrganizationDate(new Date());
                entity.setCreateDate(new Date());
                entity.setUpdateDate(new Date());
                entity.setCreateUser(userName);
                if (!StringUtil.isNullOrEmpty(areaId)) {
                    entity.setAreaId(areaId);
                    entity.setCheckAreaId(areaId);
                    entity.setAreaName(areaName);
                    entity.setCheckAreaName(checkAreaEntity.getAreaName());
                }
                service.save(entity);
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
            BizApiZxsqb entity = service.queryBySerialNumber(serialNumber);
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

            /*删除附件表*/
            fileService.deleteBatchBySerialNumber(serialNumber);

            /*删除申请书*/
            sqsService.deleteBatchBySerialNumber(serialNumber);

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

            BizApiZxsqb entity = service.queryBySerialNumber(serialNumber);
            if (!StringUtil.isNullOrEmpty(entity)) {
                if (entity.getFlag().equals("4")) {
                    flag = "5";
                }
                if (entity.getFlag().equals("3")) {
                    flag = "1";
                }
                entity.setFlag(flag);

                service.update(entity);
            }
            resultJson = R.error("用户认证已过期,请重新登录!");

            response.getWriter().print(resultJson);
        }
    }
}
