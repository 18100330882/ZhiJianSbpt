/**
 * @Author: 潘元飞
 * @Createtime: 2020年6月16日 上午8:50:22
 * @Copyright: Copyright (c) 2017
 * @Company: 北京永杰友信科技有限公司
 * @Version：1.0
 * @Description:
 */
package com.yongjie.ZhiJianSbpt.dao.shiYsJyjc;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.model.flowBase.ApiFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ApiFileDao extends BaseDao<ApiFile> {

    public final static String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.module.zzrd.dao.impl.ApiFileDaoImpl";

    /**
     * 删除附件
     * @param flowId 流程Id
     * @param unid 上报端唯一标志
     * @param serialNumber 流水号
     * @return
     */
    void deleApiFile(Long flowId, String unid, String serialNumber);


    /**
     * 附件查询
     * @param flowId 流程id
     * @param fileType 附件类型
     * @param serialNumber 流水号
     * @param flag 标识 1申请书 2检验能力 3仪器设备4组织机构图5附件
     * @return
     * @throws Exception
     */
    Map<String, Object> listPage(Long flowId, String fileType, String serialNumber, String flag, int pageIndex, int pageSize) throws Exception;

    /**
     * 文书查询
     * @param flowId 流程id
     * @param serialNumber 流水号
     * @param flag 标识 1申请书 2检验能力 3仪器设备4组织机构图5附件
     * @return
     * @throws Exception
     */
    Map<String, Object> wenShuList(Long flowId, String serialNumber, String flag, int pageIndex, int pageSize) throws Exception;


    /**
     * 查询具体附件信息
     * @param unid 上报端唯一标志
     * @param serialNumber 流水号
     * @return
     */
    ApiFile getApiFile(String unid, String serialNumber);

    List<ApiFile> getApiFileBySerialNumberFlag(String serialNumber, String flag);

    HashMap<String, Object> queryListBySerialNumber(HashMap<Object, Object> reqMap);

    /**
     * 查询上传的附件信信息
     * @param flowId  流程Id
     * @param yjFlowFuJianId  YjFlowFuJianList的id
     * @param serialNumber 流水号
     * @return
     */
    ArrayList<ApiFile> apiFuJianTreeGridSp(Long flowId, String yjFlowFuJianId, String flag, String serialNumber, String siteAddress);

    /**
     * 根据流水号删除
     * @param serialNumber
     * @return
     * @throws Exception
     */
    int deleteBatchBySerialNumber(String serialNumber) throws Exception;

    public List<Map<String, Object>> getApiFileAndWenshu(String serialNumber) throws Exception;

}
