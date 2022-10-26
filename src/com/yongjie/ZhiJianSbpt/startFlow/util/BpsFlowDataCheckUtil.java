package com.yongjie.ZhiJianSbpt.startFlow.util;

import com.alibaba.fastjson.JSONObject;
import com.yongjie.ZhiJianSbpt.utilities.JSON;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;

import java.util.HashMap;
import java.util.Map;

public class BpsFlowDataCheckUtil {


    /**
     * bps 流程启动,数据校验
     *
     * @param json :传递参数
     */
    @SuppressWarnings("unchecked")
    public static HashMap<String, Object> check(String json) throws Exception {
        if (StringUtil.isEmpty(json)) {
            throw new RuntimeException("handleInfo数据不能为空!");
        }
        try {
            HashMap<String, Object> map = null;
            try {
                map = (HashMap<String, Object>) JSONObject.parseObject(json, Map.class);
            } catch (Exception e) {
                throw new RuntimeException("handleInfo>json数据格式错误!");
            }
            if (StringUtil.isEmpty(map.get(BpsStatic.BPS_SERIALNUMBER))) {
                throw new RuntimeException("流水号不能为空!");
            }
            if (StringUtil.isEmpty(map.get(BpsStatic.BPS_UUID))) {
                throw new RuntimeException(BpsStatic.BPS_UUID + "标识符不能为空!");
            }
            if (StringUtil.isEmpty(map.get(BpsStatic.BPS_TRUENAME))) {
                throw new RuntimeException("办理人姓名不能为空!");
            }
            if (StringUtil.isEmpty(map.get(BpsStatic.BPS_QYMC))) {
                throw new RuntimeException("机构名称不能为空!");
            }
            if (StringUtil.isEmpty(map.get(BpsStatic.BPS_SUGGESTION))) {
                throw new RuntimeException("办理意见不能为空!");
            }
            return map;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
