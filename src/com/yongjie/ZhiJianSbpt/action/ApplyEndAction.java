package com.yongjie.ZhiJianSbpt.action;

import com.yongjie.ZhiJianSbpt.action.base.BaseAction;
import com.yongjie.ZhiJianSbpt.model.ApplyEnd;
import com.yongjie.ZhiJianSbpt.model.BizApiShiYsJyjc;
import com.yongjie.ZhiJianSbpt.model.jyjcJgzx.BizApiZxsqb;
import com.yongjie.ZhiJianSbpt.model.jyjcbggl.BizApiZwcnBg;
import com.yongjie.ZhiJianSbpt.service.ApplyEndService;
import com.yongjie.ZhiJianSbpt.service.BizApiShiysjyjcService;
import com.yongjie.ZhiJianSbpt.service.jyjcJgzx.BizApiZxsqbService;
import com.yongjie.ZhiJianSbpt.service.jyjcbggl.BizApiZwcnBgService;
import com.yongjie.ZhiJianSbpt.utilities.R;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * @ClassName ApplyEndAction
 * @Description TODO
 * @Date 2021/12/29 9:37
 * @Version 1.0
 */
@Controller("ApplyEndAction")
@Scope("prototype")
public class ApplyEndAction extends BaseAction<T> {

    @Resource(name = ApplyEndService.SERVICE_NAME)
    private ApplyEndService service;

    @Resource(name = BizApiShiysjyjcService.SERVICE_NAME)
    private BizApiShiysjyjcService bizService;

    @Resource(name = BizApiZwcnBgService.SERVICE_NAME)
    private BizApiZwcnBgService zwcnBgService;

    @Resource(name = BizApiZxsqbService.SERVICE_NAME)
    private BizApiZxsqbService zxsqbService;

    public void save() throws Exception {
        String resultJson;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtil.isNullOrEmpty(userName)) {
            resultJson = R.error("用户认证已过期,请重新登录!");
            response.getWriter().print(resultJson);
            return;
        }
        try {
            // 获取传递参数
            String applyReason = req.getParameter("applyReason");
            String applyDate = req.getParameter("applyDate");
            String serialNumber = req.getParameter("serialNumber");

            if (StringUtil.isNullOrEmpty(serialNumber)) {
                resultJson = R.error("数据获取异常！!");
                response.getWriter().print(resultJson);
                return;
            }

            Date parseApplyDate = new Date();
            if (applyDate != null && applyDate != "") {
                parseApplyDate = sdf.parse(applyDate);
            }

            BizApiShiYsJyjc bizEntity = bizService.queryBySerialNumber(serialNumber);
            if (2 == bizEntity.getFlag()) {
                resultJson = R.error("该业务正在办理当中，不支持撤回!");
                response.getWriter().print(resultJson);
                return;
            }
            ApplyEnd entity = new ApplyEnd();
            entity.setApplyUserName(userName);
            entity.setCreateDate(new Date());
            entity.setUpdateDate(new Date());
            entity.setApplyReason(applyReason);
            entity.setApplyDate(parseApplyDate);
            entity.setSerialNumber(serialNumber);
            entity.setFlowId(bizEntity.getFlowId());
            entity.setFlowInstId(bizEntity.getFlowInstId());
            service.deleteBySerialNum(serialNumber);
            service.save(entity);
            bizEntity.setZzch_flag("1");
            bizEntity.setZzch_date(new Date());
            bizEntity.setZzch_reason(applyReason);
            bizEntity.setZzch_suggestion("");
            bizEntity.setZzch_isagree("");
            bizEntity.setFlag(8);
            bizService.update(bizEntity);
            resultJson = R.ok("申请成功！");
        } catch (Exception e) {
            resultJson = R.error(e.getMessage());
        }
        response.getWriter().print(resultJson);
    }

    public void bgsave() throws Exception {
        String resultJson;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtil.isNullOrEmpty(userName)) {
            resultJson = R.error("用户认证已过期,请重新登录!");
            response.getWriter().print(resultJson);
            return;
        }
        try {
            // 获取传递参数
            String applyReason = req.getParameter("applyReason");
            String applyDate = req.getParameter("applyDate");
            String serialNumber = req.getParameter("serialNumber");

            if (StringUtil.isNullOrEmpty(serialNumber)) {
                resultJson = R.error("数据获取异常！!");
                response.getWriter().print(resultJson);
                return;
            }

            Date parseApplyDate = new Date();
            if (applyDate != null && applyDate != "") {
                parseApplyDate = sdf.parse(applyDate);
            }

            BizApiZwcnBg bizEntity = zwcnBgService.queryBySerialNumber(serialNumber);
            ApplyEnd entity = new ApplyEnd();
            entity.setApplyUserName(userName);
            entity.setCreateDate(new Date());
            entity.setUpdateDate(new Date());
            entity.setApplyReason(applyReason);
            entity.setApplyDate(parseApplyDate);
            entity.setSerialNumber(serialNumber);
            entity.setFlowId(bizEntity.getFlowId());
            entity.setFlowInstId(bizEntity.getFlowInstId());
            service.save(entity);
//            bizEntity.setZzch_flag("1");
            bizEntity.setFlag("8");
            zwcnBgService.update(bizEntity);
            resultJson = R.ok("申请成功！");
        } catch (Exception e) {
            resultJson = R.error(e.getMessage());
        }
        response.getWriter().print(resultJson);
    }


    public void zxsave() throws Exception {
        String resultJson;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtil.isNullOrEmpty(userName)) {
            resultJson = R.error("用户认证已过期,请重新登录!");
            response.getWriter().print(resultJson);
            return;
        }
        try {
            // 获取传递参数
            String applyReason = req.getParameter("applyReason");
            String applyDate = req.getParameter("applyDate");
            String serialNumber = req.getParameter("serialNumber");

            if (StringUtil.isNullOrEmpty(serialNumber)) {
                resultJson = R.error("数据获取异常！!");
                response.getWriter().print(resultJson);
                return;
            }

            Date parseApplyDate = new Date();
            if (applyDate != null && applyDate != "") {
                parseApplyDate = sdf.parse(applyDate);
            }

            BizApiZxsqb bizEntity = zxsqbService.queryBySerialNumber(serialNumber);
            ApplyEnd entity = new ApplyEnd();
            entity.setApplyUserName(userName);
            entity.setCreateDate(new Date());
            entity.setUpdateDate(new Date());
            entity.setApplyReason(applyReason);
            entity.setApplyDate(parseApplyDate);
            entity.setSerialNumber(serialNumber);
            entity.setFlowId(bizEntity.getFlowId());
            entity.setFlowInstId(bizEntity.getFlowInstId());
            service.save(entity);
//            bizEntity.setZzch_flag("1");
            bizEntity.setFlag("8");
            zxsqbService.update(bizEntity);
            resultJson = R.ok("申请成功！");
        } catch (Exception e) {
            resultJson = R.error(e.getMessage());
        }
        response.getWriter().print(resultJson);
    }


    public void getList() throws Exception {
        String resultJson = "";
        if (StringUtil.isNullOrEmpty(userName)) {
            resultJson = R.error("用户认证已过期,请重新登录!");
            response.getWriter().print(resultJson);
            return;
        }

        String serialNumber = req.getParameter("serialNumber");
        if (!StringUtil.isNullOrEmpty(serialNumber)) {
            HashMap reqMap = new HashMap();
            reqMap.put("serialNumber", serialNumber);
            HashMap resultMap = service.queryListBySerialNumber(reqMap);
            resultJson = R.page(resultMap);
        }

        response.getWriter().print(resultJson);
    }

    public void initLoad() throws Exception {
        String resultJson = R.error();
        if (StringUtil.isNullOrEmpty(userName)) {
            resultJson = R.error("用户认证已过期,请重新登录!");
            response.getWriter().print(resultJson);
            return;
        }

        String serialNumber = req.getParameter("serialNumber");
        ApplyEnd entity = service.queryBySerialNumber(serialNumber);
        if (entity != null) {
            String ApplyReason = entity.getApplyReason();
            if (StringUtil.isNullOrEmpty(ApplyReason)) {
                resultJson = R.ok("终止申请已提交！");
            }

        }
        response.getWriter().print(resultJson);
    }
}
