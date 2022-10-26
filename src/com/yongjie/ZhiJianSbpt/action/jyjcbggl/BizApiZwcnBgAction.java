package com.yongjie.ZhiJianSbpt.action.jyjcbggl;

import com.alibaba.fastjson.JSONObject;
import com.yongjie.ZhiJianSbpt.action.base.BaseAction;
import com.yongjie.ZhiJianSbpt.bggl.model.ChangeApplyInfo;
import com.yongjie.ZhiJianSbpt.bggl.service.*;
import com.yongjie.ZhiJianSbpt.model.Area;
import com.yongjie.ZhiJianSbpt.model.flowBase.YjFlow;
import com.yongjie.ZhiJianSbpt.model.jyjcbggl.BizApiZwcnBg;
import com.yongjie.ZhiJianSbpt.service.*;
import com.yongjie.ZhiJianSbpt.service.jyjcbggl.BizApiZwcnBgService;
import com.yongjie.ZhiJianSbpt.service.shiYsJyjc.ApiFileService;
import com.yongjie.ZhiJianSbpt.startFlow.model.AccountXzsp;
import com.yongjie.ZhiJianSbpt.startFlow.service.IBpsService;
import com.yongjie.ZhiJianSbpt.startFlow.service.IYjFlowService;
import com.yongjie.ZhiJianSbpt.utilities.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("BizApiZwcnBgAction")
@Scope("prototype")
public class BizApiZwcnBgAction extends BaseAction<BizApiZwcnBg> {

    @Resource(name = BizApiZwcnBgService.SERVICE_NAME)
    private BizApiZwcnBgService service;

    @Resource(name = ApiFileService.SERVICE_NAME)
    private ApiFileService fileService;

    @Resource(name = IAreaService.SERVICE_NAME)
    private IAreaService areaService;

    @Resource(name = ChangeApplyInfoService.SERVICE_NAME)
    private ChangeApplyInfoService changeApplyInfoService;

    @Resource(name = CancelNlDetailsService.SERVICE_NAME)
    private CancelNlDetailsService cancelNlDetailsService;

    @Resource(name = PersonChangeDetailsService.SERVICE_NAME)
    private PersonChangeDetailsService personChangeDetailsService;

    @Resource(name = SqqzrChangeDetailsService.SERVICE_NAME)
    private SqqzrChangeDetailsService sqqzrChangeDetailsService;

    @Resource(name = StandardChangeDetailsService.SERVICE_NAME)
    private StandardChangeDetailsService standardChangeDetailsService;

    @Resource(name = IYjFlowService.SERVICE_NAME)
    private IYjFlowService yjFlowService;

    @Resource(name = IBpsService.SERVICE_NAME)
    private IBpsService bpsService;

    /**
     * 封面信息保存
     */
    public void save() throws Exception {
        String resultJson = "";
        String reqJson = req.getParameter("data");
        Map parMap = new HashMap();
        if (StringUtil.isNullOrEmpty(reqJson)) {
            throw new RuntimeException("数据不能为空!");
        }
        if (StringUtil.isEmpty(userName)) {
            resultJson = R.error("用户认证已过期,请重新登录!");
            response.getWriter().print(resultJson);
            return;
        }
        try {
            HashMap<String, Object> map = JSONObject.parseObject(reqJson, HashMap.class);
            if (StringUtil.isNullOrEmpty(map)) {
                return;
            }
            BizApiZwcnBg oldEntity = service.queryBySerialNumber(map.get("serialNumber") == null ? null : map.get("serialNumber").toString());
            if (StringUtil.isNullOrEmpty(oldEntity)) {
                BizApiZwcnBg entity = (BizApiZwcnBg) HashmapAndEntityTransfer.hashmapTransferToEntity(new BizApiZwcnBg(), map);
                Area areaEntity = areaService.getById(entity.getAreaId());
                Area checkAreaEntity = areaService.getById(entity.getCheckAreaId());
                String areaName = areaEntity.getAreaName();
                if (!StringUtil.isNullOrEmpty(entity.getAreaId())) {
                    entity.setAreaName(areaName);
                    entity.setCheckAreaName(checkAreaEntity.getAreaName());
                }
                if (!StringUtil.isNullOrEmpty(entity.getCheckAreaId())) {
                    entity.setCheckAreaName(checkAreaEntity.getAreaName());
                }
                entity.setFlowId(Long.valueOf("28"));
                entity.setFlag("0");
                entity.setSqrq(new Date());
                entity.setCreateDate(new Date());
                entity.setUpdateDate(new Date());
                entity.setCreateUser(userName);
                parMap.put("serialNumber", entity.getSerialNumber());
                parMap.put("changeType", entity.getState());
                service.save(entity);
            } else {
                oldEntity = (BizApiZwcnBg) HashmapAndEntityTransfer.hashmapTransferToEntity(oldEntity, map);
                parMap.put("serialNumber", oldEntity.getSerialNumber());
                parMap.put("changeType", oldEntity.getState());
                service.update(oldEntity);
            }
            Map<String, Object> changeInfoMap = changeApplyInfoService.queryBySerialNum(parMap);
            if (Integer.valueOf(changeInfoMap.get("total").toString()) > 0) {
                ChangeApplyInfo changeApplyInfo = (ChangeApplyInfo) changeInfoMap.get(R.DATA_NAME);
                changeApplyInfo.setChangeType(parMap.get("changeType").toString());
                changeApplyInfoService.update(changeApplyInfo);
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
            BizApiZwcnBg entity = service.queryBySerialNumber(serialNumber);
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

            /*删除封面信息*/
            String id = ids[i];
            BizApiZwcnBg entity = service.queryById(Long.parseLong(id));
            // 删除 审批类型 审批表
            changeApplyInfoService.deleteBySerialNum(entity.getSerialNumber());
            if ("11".equals(entity.getState())) {
                standardChangeDetailsService.deleteBySerialNum(entity.getSerialNumber());
            }
            if ("15".equals(entity.getState())) {
                cancelNlDetailsService.deleteBySerialNum(entity.getSerialNumber());
            }
            if ("17".equals(entity.getState())) {
                personChangeDetailsService.deleteBySerialNum(entity.getSerialNumber());
            }
            if ("18".equals(entity.getState())) {
                sqqzrChangeDetailsService.deleteBySerialNum(entity.getSerialNumber());
            }
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
            BizApiZwcnBg entity = service.queryBySerialNumber(serialNumber);
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

    public void confirmAcceptance() throws Exception {
        String resultJson = null;
        String sqsIdStr = req.getParameter("id");
        String serialNumber = req.getParameter("serialNumber");
        String flowIdStr = req.getParameter("flowId");
        String shRenAreaId = req.getParameter("shRenAreaId");
        String qymc = req.getParameter("qymc");

        if (StringUtil.isEmpty(sqsIdStr)) {
            resultJson = R.error("上报受理失败!");
            response.getWriter().print(resultJson);
            return;
        } else {
            String flowName = "";
            YjFlow yjflow = yjFlowService.getFlowById(Long.parseLong(flowIdStr));
            if (yjflow != null) {
                flowName = yjflow.getFlowName();
            }
            //启动流程
            long flowInstId = bpsService.startYjFlow(qymc + "(" + flowName + ")", Long.parseLong(flowIdStr), Long.parseLong(sqsIdStr), userName, Long.parseLong(shRenAreaId));
            System.out.println(flowInstId + "---------------------------------flowInstId-----------------------");

            //修改申请书状态
            if (flowInstId > 0) {
                BizApiZwcnBg bizModel = service.getBizApiZwcnBg(serialNumber, "");//根据流水号查询，可以不用传flag
                if (!StringUtil.isEmpty(bizModel)) {
                    bizModel.setFlag("2"); // 状态修改为 2
                    bizModel.setUpdateDate(new Date()); // 更新时间
                    bizModel.setNodeDate(new Date());// 环节时间
                    bizModel.setFlowInstId(flowInstId);
                    bizModel.setFlowId(28L);
                    service.update(bizModel);
                    resultJson = R.ok("上报成功！请耐心等待受理结果!");
                }
            } else {
                resultJson = R.error("通过审核失败!");
            }
        }
        response.getWriter().print(resultJson);
    }

    /**
     * 变更 上报-审批
     */
    public void report() throws Exception {
        String resultJson = R.error("数据异常,请重新登录!");
        String serialNumber = req.getParameter("serialNumber");
        if (StringUtil.isNullOrEmptyZf(userName) || StringUtil.isNullOrEmptyZf(serialNumber)) {
            response.getWriter().print(resultJson);
            return;
        }

        HashMap<Object, Object> reqMap = new HashMap<>();
        reqMap.put("serialNumber", serialNumber);
        HashMap<String, Object> fileMap = fileService.queryListBySerialNumber(reqMap);
        List fileList = (List) fileMap.get(R.DATA_NAME);
        if (fileList == null || fileList.size() == 0) {
            resultJson = R.error("请上传必要的附件");
            response.getWriter().print(resultJson);
            return;
        }

        String flag = "2";
        BizApiZwcnBg bgEntity = service.queryBySerialNumber(serialNumber);
        if (StringUtil.isNullOrEmptyZf(bgEntity) || StringUtil.isNullOrEmptyZf(bgEntity.getId()) || bgEntity.getId() == 0) {
            resultJson = R.error("数据不存在");
            response.getWriter().print(resultJson);
            return;
        }
        if ("4".equals(bgEntity.getFlag())) {
            flag = "5";
        }
        if ("3".equals(bgEntity.getFlag())) {
            flag = "2";
        }

        // 更 变更审批表 中 申请时间 - 当前时间
        Map<String, Object> map = new HashMap<>();
        map.put("serialNumber", serialNumber);
        map.put("changeType", bgEntity.getState());
        Map<String, Object> applyInfoMap = changeApplyInfoService.queryBySerialNum(map);
        ChangeApplyInfo applyInfo = (ChangeApplyInfo) applyInfoMap.get(R.DATA_NAME);
        if (StringUtil.isNullOrEmptyZf(applyInfo) || StringUtil.isNullOrEmptyZf(applyInfo.getId()) || applyInfo.getId() == 0) {
            resultJson = R.error("数据不存在");
            response.getWriter().print(resultJson);
            return;
        }
        // 更新 变更申请表
        applyInfo.setApplyDate(new Date());
        changeApplyInfoService.update(applyInfo);

        // 主表 flag ！= 0   ！= 8  不需 启动流程
        long flowInstId = 0L;
        if (StringUtil.isNullOrEmptyZf(bgEntity.getFlag()) || "0".equals(bgEntity.getFlag()) || "8".equals(bgEntity.getFlag())) {
            // 材料与审核----> 受理
            YjFlow yjflow = yjFlowService.getFlowById(bgEntity.getFlowId());
            if (StringUtil.isNullOrEmptyZf(yjflow)) {
                resultJson = R.error("流程不存在");
                response.getWriter().print(resultJson);
                return;
            }
            // 启动流程
            flowInstId = bpsService.startYjFlow(bgEntity.getJgmc() + "(" + yjflow.getFlowName() + ")", bgEntity.getFlowId(), bgEntity.getId(), userName, bgEntity.getCheckAreaId());
            bgEntity = service.queryById(bgEntity.getId());
            if (flowInstId > 0) {
                bgEntity.setFlowInstId(flowInstId);
            }
        }

        //修改申请书状态
        bgEntity.setFlag(flag);
        bgEntity.setSqrq(new Date());
        bgEntity.setUpdateDate(new Date());
        bgEntity.setNodeDate(new Date());
        service.update(bgEntity);
        String areaName = areaService.getAreaName(bgEntity.getAreaId());
        resultJson = R.ok("已上报至" + areaName + "市场监督管理局受理！请耐心等待受理结果!");

        response.getWriter().print(resultJson);
    }
}
