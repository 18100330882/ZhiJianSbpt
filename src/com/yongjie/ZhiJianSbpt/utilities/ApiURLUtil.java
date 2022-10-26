/**
 * @Author: 朱卫士
 * @Createtime: 2020年6月24日 下午1:06:01
 * @Copyright: Copyright (c) 2017
 * @Company: 北京永杰友信科技有限公司
 * @Version：1.0
 * @Description:
 */
package com.yongjie.ZhiJianSbpt.utilities;

public class ApiURLUtil {

    // 测试环境
    //public static final String HTTP = "http://183.129.215.106:11119";

    // 正式环境
    //public static final String HTTP = "http://172.29.131.145:8888";
    //public static final String HTTP = "http://113.200.157.196:7126";
    //正式环境
    public static final String HTTP = "http://10.7.9.58:43332";

    // 西安市 用 附件 下载 预览 IP
    public static final String HTTP_XIAN = "http://scjgwsdj.xa.gov.cn:8086";
    // 西安市 数据 提交 请求 接口 IP
    public static final String HTTP_XIAN_TJ = "http://10.2.87.64:8055";


    //测试环境
    //public static final String HTTP = "http://fjrnge.natappfree.cc";

    //2021-8-4新增   3.2机构基本信息接口
    public static final String ZZRD_NODE_JG_URL = HTTP + "/api/cmapush/baseInfo";
    /**
     * 办理环节 推送换url
     */
    public static final String ZZRD_NODE_API_URL = HTTP + "/api/upload/flowInfo";


    /**
     * 专家评审信息接口 url
     */
    public static final String ZZRD_EXPERT_API_URL = HTTP + "/api/upload/expertcheckInfo";
    /**
     * 办件通知书接口 url
     */
    public static final String ZZRD_NOTICE_API_URL = HTTP + "/api/upload/noteInfo";
    /**
     * 办件通知书接口 最终审批为不准入许可时 url
     */
    public static final String ZZRD_NOTICENO_API_URL = HTTP + "/api/upload/noteInfo";
    /**
     * 许可证信息接口 url
     */
    public static final String ZZRD_PERMIT_API_URL = HTTP + "/api/upload/licenseInfo";
    /**
     * 3.1.6.办件材料信息接口 url
     */
    public static final String MATERIAL_NODE_API_URL = HTTP + "/api/upload/fileInfo";
    /**
     * 3.1.5.办件退回信息接口
     */
    public static final String RETURN_NODE_API_URL = HTTP + "/api/upload/returnInfo";

    /**
     * 3.1.6.办件材料（补证通知书,审批表）信息接口
     */
    public static final String FILE_INFO_API_URL = HTTP + "/api/upload/fileInfo";

    /**
     * 文件下载接口
     */
    public static final String DOWNLOAD_FILE_API_URL = HTTP + "/api/upload/downLoadFileByUuid";

    /**
     * 西安市  文件 下载接口
     */
    public static final String DOWNLOAD_FILE_API_URL_XIAN = HTTP_XIAN + "/api/upload/downLoadFileByUuid";

    /**
     * 检验检测仪器设备接口 url
     */
    public static final String ZZRD_JYJCYQSB_API_URL = HTTP + "/api/upload/abilityInfo";

    /**
     * 检验检测仪器设备接口 url
     */
    public static final String ZZRD_QZR_API_URL = HTTP + "/api/upload/sqqzry";

    //2021-8-4新增  3.2.3.检验能力信息接口
    public static final String ZZRD_JYJC_NL_URL = HTTP + "/api/cmapush/abilityInfo";

    /**
     * 3.3.1.	专家审核结果信息接口
     */
    public static final String ZZRD_ZJSH_JG_URL = HTTP_XIAN_TJ + "/api/upload/expertApprovalInfo";
}
