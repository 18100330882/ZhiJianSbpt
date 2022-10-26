/**
 * @Author: 李杰
 * @Createtime: 2016年9月12日 上午11:36:53
 * @Copyright: Copyright (c) 2016
 * @Company: 北京永杰友信科技有限公司
 * @Version：1.0
 * @Description:
 */
package com.yongjie.ZhiJianSbpt.dao.flowBase;

import com.yongjie.ZhiJianSbpt.base.BaseDao;
import com.yongjie.ZhiJianSbpt.model.flowBase.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IYjFlowInstWenShuDao extends BaseDao<YjFlowInstWenShu> {
    public final static String SERVICE_NAME = "com.yongjie.ZhiJianSbpt.dao.flowBase.impl.YjFlowInstWenShuDao";

    public List<YjFlowInstWenShu> getWenShuByFlowIdFlowInsId(long flowId, long flowInsId, String fileTitle, String nodeId, String serialNumber);

    public List<YjFlowInstWenShu> getWenShuByFlowInsIdFileTitleWenHao(Long flowInsId, String fileTitle, String wenHao, String serialNumber);

    public int delWenShuByFlowInstIdFileTitleWenHao(long flowInstId, String filetitle, String wenHao);

    public int delWenShuById(long id);

    public int delTableByColumnName(String tableName, String accordingColName, String accordingColValue);

    /**
     * 获得文书列表
     * @param key
     */
    public HashMap getWenShuList(String key, long flowInstId, String nodeId, String wsName, int flag, int pageIndex, int pageSize, String sortField, String sortOrder) throws Exception;

    public HashMap getWenShuList2(String type, String newShxydm, int pageIndex, int pageSize, String sortField, String sortOrder) throws Exception;

    public Boolean checkExist(long fileTypeId, int flag, long flowInstId, String spjg);

    public ArrayList getFuJian(long flowInstId, String flag, String nodeId, String spjg);

    //杨青岭 12-21 查询文书表中所存放的 上传文件
    public ArrayList getFuJianByFILETYPEID(long flowInstId, int flag, String nodeId, String spjg, String fuJidStr);

    public ArrayList getFileByWenshu(String flowId, String flowInstId, String nodeId, String flag);

    //张谦根据sqsType和fileName得到附件
    public ArrayList getFuJianBySqsType(String sqsType, String fileName);

    //根据flowInstId,flag,wenHao获得文书表数据
    public ArrayList getWenShuByWenHao(long flowInstId, int flag, String wenHao);

    public ArrayList getFilePathByWenHao(String WenHao, String fileTitle, long flowInstId);

    //获得附件类型的filename
    public String getFilenameById(long fileTypeId);

    //获得附件类型的filename-用户端
    public String getFileNameByFlag(String flag);

    //根据nodeId和flowInstId删除文书
    public int deleteWenShu(String flowInstId, String nodeId) throws Exception;

    //根据nodeId和flowInstId查找文书数据
    public ArrayList getWenShuByNodeId(String flowInstId, String flag, String filePath) throws Exception;

    //根据nodeId和flowInstId查找文书数据(其他流程)
    public ArrayList getYjWenShuByNodeId(String flowInstId, String flag, String filePath) throws Exception;

    //根据flowInstId查找文书数据
    public List<YjFlowInstWenShu> getWenShuByFlowIdAndFlowInsId(long flowId, long flowInsId, String fileTitle1, String fileTitle2) throws Exception;

    public ArrayList getFuJianBycondTable(long flowInstId, int flag, String nodeId, String spjg, String condTable);

    //现场核查中的上传原件
    public ArrayList getFileTableListRow(long flowInstId, String fileTitleRow);

    public ArrayList getFileTableRawById(long id);

    public ArrayList getFlowXcPhotoById(long id);

    //获取抽样单附件列表
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
     * @param filetypeId
     * @return
     */
    public ArrayList getWenShuBySerialNumber(String serialNumber, long fileTypeId);

    public ArrayList getApiFileBySerialNumber(String serialNumber, String flag);

    public ArrayList getAllWenShuByFlowInstId(long flowInstId);

    public ArrayList getFuJianBanDuan(long flowInstId, String sqsType, String flag, String nodeId, String spjg);

    public ArrayList getPlxz(String sqsId);

    public ArrayList getWenShu(long flowId, long flowInstId, String nodeId, String fileTitle);

    public int delWenShuByFlowIdFlowInstIdNodeIdWenHao(long flowId, long flowInstId, String nodeId, String wenHao);

    /**
     * 根据流程实例id和文书名删除文书表记录，该操作并不会删除文件
     * @param flowInstIdArr
     * @param fileTitle
     */
    public void delWenShuByFlowInstIdsFileTitle(Long[] flowInstIdArr, String fileTitle);

    /**
     * 保存后刷新
     * @param model
     */
    public void saveFlush(Object model);

    Map<String, Object> getWenShuListBySerialNumber(String serialNumber);

    int updateWenShuId(String id, String newId);

    int updateWenShuFlag(String id, String flag);
}
