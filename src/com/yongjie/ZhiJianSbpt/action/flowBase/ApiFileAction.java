package com.yongjie.ZhiJianSbpt.action.flowBase;

import com.yongjie.ZhiJianSbpt.action.base.BaseAction;
import com.yongjie.ZhiJianSbpt.model.flowBase.ApiFile;
import com.yongjie.ZhiJianSbpt.module.util.R;
import com.yongjie.ZhiJianSbpt.service.BizApiShiysjyjcService;
import com.yongjie.ZhiJianSbpt.service.flowBase.IYjFlowFuJianListService;
import com.yongjie.ZhiJianSbpt.service.shiYsJyjc.ApiFileService;
import com.yongjie.ZhiJianSbpt.utilities.JSON;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import com.yongjie.ZhiJianSbpt.utilities.SysFinalRecource;
import com.yongjie.ZhiJianSbpt.utilities.UuidUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
@Controller("ApiFileAction")
@Scope("prototype")
public class ApiFileAction extends BaseAction<ApiFile> {

    /**
     * 主表
     */
    @Resource(name = BizApiShiysjyjcService.SERVICE_NAME)
    private BizApiShiysjyjcService bizShiysjyjcService;

    /**
     * 附件
     */
    @Resource(name = ApiFileService.SERVICE_NAME)
    private ApiFileService apiFileService;

    @Resource(name = IYjFlowFuJianListService.SERVICE_NAME)
    private IYjFlowFuJianListService shiYsJyjcFuJianService;

    public static void main(String[] args) {
        ApiFile api = new ApiFile();

        /**
         * 附件名称
         */
        api.setFileName("申请书");

        /**
         * 附件类型
         */
        api.setFileType("doc");

        /**
         * 标识 1申请书 2检验能力 3仪器设备4组织机构图5附件
         */
        api.setFlag("1");

        /**
         * 文件存储路径
         */
        api.setFilePath("/2020/06/18/123456789.doc");

        /**
         * 排序
         */
        api.setOrderNum(1);


        /**
         * 流水号
         */
        api.setSerialNumber("202006180001");

        /**
         * 标识(主键,由接口调用方提供)
         */
        api.setUnid(UuidUtil.getSimpleUUID());

        String encode = JSON.Encode(api);

        System.out.println(encode);

    }

    /**
     * 查询
     *
     * @throws Exception
     */
    public void listPage() throws Exception {
        String resultJson = null;
        if (!StringUtil.isEmpty(userName)) {
            String serialNumber = req.getParameter("serialNumber");
            String flowIds = req.getParameter("flowId");
            String fileType = req.getParameter("fileType");
            String flag = req.getParameter("flag");
            Long flowId = null;
            if (!StringUtil.isEmpty(flowIds)) {
                flowId = Long.parseLong(flowIds);
            }
            try {
                Map<String, Object> listPage = apiFileService.listPage(flowId, fileType, serialNumber, flag, pageIndex, pageSize);
                resultJson = R.page(listPage);
            } catch (Exception e) {
                resultJson = R.error(e.getMessage(), "查询失败!");
            }
        } else {
            resultJson = R.error("用户认证已过期,请重新登录!");
        }
        response.getWriter().print(resultJson);
    }

    /**
     * 查询
     *
     * @throws Exception
     */
    public void wenShuList() throws Exception {
        String resultJson = null;
        if (!StringUtil.isEmpty(userName)) {
            String serialNumber = req.getParameter("serialNumber");
            String flowIds = req.getParameter("flowId");
            String flag = req.getParameter("flag");
            Long flowId = null;
            if (!StringUtil.isEmpty(flowIds)) {
                flowId = Long.parseLong(flowIds);
            }
            try {
                Map<String, Object> listPage = apiFileService.wenShuList(flowId, serialNumber, flag, pageIndex, pageSize);
                resultJson = R.page(listPage);
            } catch (Exception e) {
                resultJson = R.error(e.getMessage(), "查询失败!");
            }
        } else {
            resultJson = R.error("用户认证已过期,请重新登录!");
        }
        response.getWriter().print(resultJson);
    }

    /**
     * 查询具体附件信息
     *
     * @throws Exception
     */
    public void getApiFile() throws Exception {
        String resultJson = null;
        if (!StringUtil.isEmpty(userName)) {
            String serialNumber = req.getParameter("serialNumber") == null ? "" : req.getParameter("serialNumber");
            String unid = req.getParameter("unid") == null ? "" : req.getParameter("unid");
            try {
                ApiFile apiFile = apiFileService.getApiFile(unid, serialNumber);
                resultJson = R.ok(apiFile);
            } catch (Exception e) {
                resultJson = R.error(e.getMessage(), "查询失败!");
            }
        } else {
            resultJson = R.error("用户认证已过期,请重新登录!");
        }
        response.getWriter().print(resultJson);
    }

    public void getApiFileList() throws Exception {
        String resultJson = null;
        if (!StringUtil.isEmpty(userName)) {
            String serialNumber = req.getParameter("serialNumber") == null ? "" : req.getParameter("serialNumber");
            HashMap<Object, Object> reqMap = new HashMap<>();
            reqMap.put("serialNumber", serialNumber);
            try {
                HashMap<String, Object> resultMap = apiFileService.queryListBySerialNumber(reqMap);
                resultMap.put("code", 1);
                List<ApiFile> list = (List<ApiFile>) resultMap.get("data");
                if (list.size() != 0) {
                    resultMap.put("code", 0);
                }
                resultJson = R.page(resultMap);

            } catch (Exception e) {
                resultJson = R.error(e.getMessage(), "查询失败!");
            }
        } else {
            resultJson = R.error("用户认证已过期,请重新登录!");
        }
        response.getWriter().print(resultJson);
    }

    /**
     * 根据id删除附件
     *
     * @throws Exception
     */
    public void deleteApiFile() throws Exception {
        //获得参数
        String idStr = req.getParameter("idResult");
        //判空操作
        if (StringUtil.isNullOrEmpty(idStr)) {
            return;
        }
        String[] ids = idStr.split(",");
        for (int i = 0, l = ids.length; i < l; i++) {
            String id = ids[i];
            String filePath = apiFileService.getApiFileById(Long.parseLong(id)).getLocalFilePath();
            /****************文件操作****************/
            String fileAllPath = "";
            File filetemp = null;
            fileAllPath = SysFinalRecource.SECOND_LEVEL_DIRECTORY + filePath;
            //根据ID删除附件内容
            apiFileService.delete(Long.parseLong(id));
            //删除本地文件
            filetemp = new File(fileAllPath);
            if (filetemp.exists()) {
                filetemp.delete();
                break;
            }
            /****************文件操作****************/
            response.getWriter().write("删除成功");
        }
    }
}
