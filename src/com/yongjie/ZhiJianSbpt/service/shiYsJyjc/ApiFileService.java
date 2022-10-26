package com.yongjie.ZhiJianSbpt.service.shiYsJyjc;

import com.yongjie.ZhiJianSbpt.model.flowBase.ApiFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ApiFileService {

    public final static String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.service.shiYsJyjc.impl.ApiFileServiceImpl";

    /**
     * 保存
     */
    void save(ApiFile t) throws Exception;

    /**
     * 更新
     */
    void update(ApiFile t) throws Exception;

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     * @throws Exception
     */
    ApiFile getApiFileById(Long id);

    /**
     * 删除附件
     *
     * @param unid         上报端唯一标志
     * @param serialNumber 流水号
     * @param flowId       流程id
     * @return
     */
    void deleApiFile(Long flowId, String unid, String serialNumber);


    /**
     * 查询附加
     *
     * @param flowId       流程id
     * @param fileType     附件类型
     * @param serialNumber 流水号
     * @param flag         标识 1申请书 2检验能力 3仪器设备4组织机构图5附件
     * @return
     * @throws Exception
     */
    Map<String, Object> listPage(Long flowId, String fileType, String serialNumber, String flag, int pageIndex, int pageSize) throws Exception;

    /**
     * 文书查询
     *
     * @param flowId       流程id
     * @param serialNumber 流水号
     * @param flag         标识 1申请书 2检验能力 3仪器设备4组织机构图5附件
     * @return
     * @throws Exception
     */
    Map<String, Object> wenShuList(Long flowId, String serialNumber, String flag, int pageIndex, int pageSize) throws Exception;

    /**
     * 查询具体附件信息
     *
     * @param unid         上报端唯一标志
     * @param serialNumber 流水号
     * @return
     */
    ApiFile getApiFile(String unid, String serialNumber);

    List<ApiFile> getApiFileBySerialNumberFlag(String serialNumber, String flag);

    HashMap<String, Object> queryListBySerialNumber(HashMap<Object, Object> reqMap) throws Exception;

    /**
     * 查询上传的附件信信息
     *
     * @param flowId       流程Id
     * @param serialNumber 流水号
     * @return
     */
    ArrayList<ApiFile> apiFuJianTreeGridSp(Long flowId, String yjFlowFuJianId, String flag, String serialNumber, String siteAddress);

    /**
     * 根据id删除附件
     *
     * @return
     */
    void delete(Long id);


    /**
     * 根据流水号批量删除
     *
     * @param serialNumber
     * @return
     * @throws Exception
     */
    int deleteBatchBySerialNumber(String serialNumber) throws Exception;

    /**
     * 批量保存
     *
     * @param list
     * @return
     * @throws Exception
     */
    int saveBatch(List<ApiFile> list) throws Exception;

    /**
     * 批量保存
     *
     * @param list
     * @param size
     * @return
     * @throws Exception
     */
    int saveBatch(List<ApiFile> list, int size) throws Exception;

    /**
     * 删除旧数据和批量增加数据 > 删除附件,需要同时删除下载的文件
     *
     * @return
     * @throws Exception
     */
    int delOldAndSaveBatch(List<ApiFile> list) throws Exception;

    public List<Map<String, Object>> getApiFileAndWenshu(String serialNumber) throws Exception;

    /**
     * 往apifile里面添加实例数据
     *
     * @param apiFile
     */
    void addApiFile(ApiFile apiFile);
}
