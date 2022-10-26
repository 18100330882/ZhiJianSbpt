package com.yongjie.ZhiJianSbpt.action.flowBase;

import com.yongjie.ZhiJianSbpt.action.base.BaseAction;
import com.yongjie.ZhiJianSbpt.model.flowBase.ApiFujianType;
import com.yongjie.ZhiJianSbpt.service.flowBase.IYjFlowInstWenShuService;
import com.yongjie.ZhiJianSbpt.service.shiYsJyjc.ApiFujianTypeService;
import com.yongjie.ZhiJianSbpt.utilities.JSON;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import com.yongjie.ZhiJianSbpt.utilities.SysFinalRecource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("serial")
@Controller("ApiFujianTypeAction")
@Scope("prototype")
public class ApiFujianTypeAction extends BaseAction<ApiFujianType> {

    /**
     * 附件
     */
    @Resource(name = ApiFujianTypeService.SERVICE_NAME)
    private ApiFujianTypeService service;
    @Resource(name = IYjFlowInstWenShuService.SERVICE_NAME)
    private IYjFlowInstWenShuService yjWenShuService;


    //加载数据列表
    public void getApiFujianType() throws Exception {
        //查询条件
        String flowIdStr = req.getParameter("flowId");//从控件传的值，若没有则为null
        String serialNumber = req.getParameter("serialNumber");
        String sqsType = req.getParameter("sqsType");
        String statue = req.getParameter("statue");
        long flowId = 0l;
        if (!StringUtil.isNullOrEmpty(flowIdStr)) {
            flowId = Long.parseLong(flowIdStr);
        }
        ArrayList dataFlowFuJian = service.getYjFuJianList(flowId, sqsType, statue);
        int count = dataFlowFuJian.size();
        if (dataFlowFuJian != null && dataFlowFuJian.size() > 0) {
            String requiredFuJ = SysFinalRecource.JLBZKH_REQUIREDFJ;//必传的附件
            String notRequiredFuJ = SysFinalRecource.JLBZKH_NOTREQUIREDFJ;//非必传附件
            //查询必填附件，选填附件对应的id
            String btFuJid = service.getFuJianListId(flowId, sqsType, requiredFuJ);
            String fbtFuJid = service.getFuJianListId(flowId, sqsType, notRequiredFuJ);
            for (int j = 0; j < count; j++) {
                HashMap map1 = (HashMap) dataFlowFuJian.get(j);
                String fileName = map1.get("NAME") != null ? map1.get("NAME").toString() : "";
                String flagStr = map1.get("FLAG") != null ? map1.get("FLAG").toString() : "";
                String groupId = map1.get("GROUPID") != null ? map1.get("GROUPID").toString() : "";
                if ("0".equals(groupId)) {
                    map1.put("PARENTID", btFuJid);
                } else if ("9".equals(groupId)) {
                    map1.put("PARENTID", fbtFuJid);
                }
                map1.put("NAME", fileName);
                //拿到已经上传的附件
                ArrayList dataFuJian = yjWenShuService.getApiFileBySerialNumber(serialNumber, flagStr);
                for (int i = 0; i < dataFuJian.size(); i++) {
                    HashMap map = (HashMap) dataFuJian.get(i);
                    String fileTitle = map.get("FILENAME") != null ? map.get("FILENAME").toString() : "";
                    String fileTypeId = map.get("FLAG") != null ? map.get("FLAG").toString() : "";
                    String id = map.get("ID") != null ? map.get("ID").toString() : "";
                    String filetype = map.get("FILETYPE") != null ? map.get("FILETYPE").toString() : "";
                    map.put("NAME", fileTitle);
                    map.put("PARENTID", fileTypeId);//设置parentId,其值为fileTypeId
                    map.put("FILETYPE", filetype);//设置parentId,其值为fileTypeId
                    map.put("KID", id);//为了避免两张表id的冲突，所以将附件表的id,用kid表示
                    map.remove("ID");//移除id
                    dataFlowFuJian.add(map);//将两个ArrayList集合添加到一个ArrayList里面
                }

            }
        }
        //向页面输出json语句
        String json = JSON.Encode(dataFlowFuJian);
        response.getWriter().write(json);
    }

}
