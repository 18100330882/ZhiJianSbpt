/**
 * @Author: 李杰
 * @Createtime: 2016年9月12日 上午10:57:49
 * @Copyright: Copyright (c) 2016
 * @Company: 北京永杰友信科技有限公司
 * @Version：1.0
 * @Description:
 */
package com.yongjie.ZhiJianSbpt.service.flowBase;

import com.yongjie.ZhiJianSbpt.model.flowBase.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IYjFlowInstWenShuService {
    public final static String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.service.flowBase.impl.YjFlowInstWenShuService";

    /**
     * 根据业务id,流程实例id,文件名获得文书对象
     * @param flowInsId 流程实例id
     * @param fileTitle 文件名
     * @param serialNumber 流水号
     * @author
     */
    public List<YjFlowInstWenShu> getWenShuByFlowIdFlowInsId(long flowId, long flowInsId, String fileTitle, String nodeId, String serialNumber) throws Exception;

    /**
     * 根据业务id,流程实例id,文件名获得文书对象
     * @param flowInsId 流程实例id
     * @param fileTitle 文件名
     * @param wenHao 文号
     * @author lijie 2016-10=08
     */
    public List<YjFlowInstWenShu> getWenShuByFlowInsIdFileTitleWenHao(Long flowInsId, String fileTitle, String wenHao, String serialNumber);

    /**
     * 根据 id查询数据
     * @param id 流程实例文书表表主键
     * @author lijie 2016-10=09
     */
    public YjFlowInstWenShu getYjFlowInstWenShuById(long id);

    /**
     * 更新流程实例文书表
     * @author lijie 2016-10=08
     */
    public void updateYjFlowInstWenShu(YjFlowInstWenShu wenShu);

    /**
     * 往流程实例文书表插数据
     * @author lijie 2016-10=08
     */
    public void addYjFlowInstWenShu(YjFlowInstWenShu wenShu);

    /**
     * 根据流程实例 id，filetitle删除数据
     * @param flowInstId 流程实例 id
     * @param filetitle 文件名
     * @param wenHao 文号
     * @author lijie 2016-10=10 增加参数文号  20161205
     */
    public int delWenShuByFlowInstIdFileTitleWenHao(long flowInstId, String filetitle, String wenHao);

    /**
     * 根据文书表 id删除数据
     * @param id 文书表id
     * @author 刘晨源 2020-10-19
     */
    public int delWenShuById(String filePath, long id);

    /**
     * 根据流程实例 id，filetitle删除数据
     * @param flowInstId 流程实例 id
     * @param wenHao 文号
     * @author lijie 2016-10=10 增加参数文号  20161205
     */
    public int delWenShuByFlowIdFlowInstIdNodeIdWenHao(long flowId, long flowInstId, String nodeId, String wenHao);

    /**
     * 根据表名和主键删除对应文书表的数据
     * @param tableName 表名
     * @author lijie  20161205
     */
    public int delTableByColumnName(String tableName, String accordingColName, String accordingColValue);

    /**
     * 获得文书列表
     * @param key
     * @param flowInstId
     * @param nodeId
     * @param pageIndex
     * @param pageSize
     * @param sortField
     * @param sortOrder
     * @return
     * @throws Exception
     */
    public HashMap getWenShuList(String key, long flowInstId, String nodeId, String wsName, int flag, int pageIndex, int pageSize, String sortField, String sortOrder) throws Exception;

    public HashMap getWenShuList2(String type, String newShxdym, int pageIndex, int pageSize, String sortField, String sortOrder) throws Exception;

    //根据id删除
    public void deleteWenS(Long id) throws Exception;

    //根据流程实例id,文件名称id,flag查询文书数据
    public Boolean checkExist(long fileTypeId, int flag, long flowInstId, String spjg);

    //根据流程实例id,状态（0：现场核查  1：材料汇总）得到文书列表
    public ArrayList getFuJian(long flowInstId, String flag, String nodeId, String spjg);

    public ArrayList getFuJianByFILETYPEID(long flowInstId, int flag, String nodeId, String spjg, String fuJidStr);

    //得到现场核查和材料汇总的必填附件列表

    //杨青岭 12-21 查询文书表中所存放的 上传文件 flag:（0：现场核查  1：材料汇总）
    public ArrayList getFileByWenshu(String flowId, String flowInstId, String nodeId, String flag);

    //张谦根据sqsType和fileName得到附件
    public ArrayList getFuJianBySqsType(String sqsType, String fileName);

    //根据flowInstId,flag,wenHao获得文书表数据
    public ArrayList getWenShuByWenHao(long flowInstId, int flag, String wenHao);

    //获得附件类型的filename
    public String getFilenameById(long fileTypeId);

    //获得附件类型的filename-用户端
    public String getFileNameByFlag(String flag);

    //根据nodeId和flowInstId删除文书
    public int deleteWenShu(String flowInstId, String nodeId) throws Exception;

    //根据nodeId和flowInstId查找文书数据（gongyp）
    public ArrayList getWenShuByNodeId(String flowInstId, String flag, String filePath) throws Exception;

    //根据nodeId和flowInstId查找文书数据(其他流程)
    public ArrayList getYjWenShuByNodeId(String flowInstId, String flag, String filePath) throws Exception;

    //根据flowInstId查找文书数据
    public List<YjFlowInstWenShu> getWenShuByFlowIdAndFlowInsId(long flowId, long flowInsId, String fileTitle1, String fileTitle2) throws Exception;

    //根据流程实例id,状态（0：现场核查  1：材料汇总）得到文书列表
    public ArrayList getFuJianBycondTable(long flowInstId, int flag, String nodeId, String spjg, String condTable);

    //现场核查中的上传原件
    public ArrayList getFileTableListRow(long flowInstId, String fileTitleRow);

    public ArrayList getFileTableRawById(long id);

    public ArrayList getFlowXcPhotoById(long id);

    /**
     * 获取抽样单上传附件列表
     * @param flowInstId
     * @param cydName
     * @return
     */
    public ArrayList getCydList(long flowInstId, String cydName);

    //获得文书列表
    public ArrayList getWenShuByFileTietle(long flowInstId, String fileTitle, long flag, String serialNumber);

    /**
     * 当前记录下 filetypeid下的数据
     * @param flowInstId
     * @param flag
     * @param filetypeId
     * @return
     */
    public ArrayList getWenShuByfiletypeId(long flowInstId, int flag, long filetypeId);

    public ArrayList getWenShuByFileTitleAndFlowInstId(long flowInstId, String fileTitle);

    public ArrayList getWenShuByFileTypeId(long flowInstId, long fileTypeId);

    /**
     * 根据流水号拿到附件信息
     * @param serialNumber 流水号
     * @return
     */
    public ArrayList getWenShuBySerialNumber(String serialNumber, long fileTypeId);

    public ArrayList getApiFileBySerialNumber(String serialNumber, String flag);

    public ArrayList getAllWenShuByFlowInstId(long flowInstId);

    public ArrayList getFuJianBanDuan(long flowInstId, String sqsType, String flag, String nodeId, String spjg);

    public ArrayList getPlxz(String sqsId);

    public ArrayList getWenShu(long flowId, long flowInstId, String nodeId, String fileTitle);

    /**
     * 批量向文书表插入数据
     * 插入之前会先删除流程实例id对应的记录，但不会删除文件
     * 然后再执行新纪录的保存，以及相关参数的插入
     * @param model
     * @param flowInstIds
     * @param operId
     * @param paramValue
     * @param userName
     * @throws Exception
     */
    void deleteAddWenShuMulti(YjFlowInstWenShu model, String flowInstIds, long operId,
                              int paramValue, String userName) throws Exception;

    Map<String, Object> getWenShuListBySerialNumber(String serialNumber);

    int updateWenShuId(String id, String newId);

    void deleteWenShuById2(String id);

    int updateWenShuFlag(String id, String flag);
}
