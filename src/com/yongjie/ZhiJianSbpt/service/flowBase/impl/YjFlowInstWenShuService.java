/**
 * @Author: 李杰
 * @Createtime: 2016年9月12日 上午11:25:05
 * @Copyright: Copyright (c) 2016
 * @Company: 北京永杰友信科技有限公司
 * @Version：1.0
 * @Description:
 */
package com.yongjie.ZhiJianSbpt.service.flowBase.impl;

import com.yongjie.ZhiJianSbpt.dao.flowBase.*;
import com.yongjie.ZhiJianSbpt.model.flowBase.*;
import com.yongjie.ZhiJianSbpt.service.flowBase.*;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import com.yongjie.ZhiJianSbpt.utilities.SysFinalRecource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(readOnly = false)
@Service(IYjFlowInstWenShuService.SERVICE_NAME)
public class YjFlowInstWenShuService implements IYjFlowInstWenShuService {
    @Resource(name = IYjFlowInstWenShuDao.SERVICE_NAME)
    private IYjFlowInstWenShuDao yjFlowInstWenShuDao;


    @Override
    public List<YjFlowInstWenShu> getWenShuByFlowIdFlowInsId(long flowId, long flowInsId, String fileTitle, String nodeId, String serialNumber) throws Exception {
        // TODO Auto-generated method stub
        return yjFlowInstWenShuDao.getWenShuByFlowIdFlowInsId(flowId, flowInsId, fileTitle, nodeId, serialNumber);
    }

    @Override
    public List<YjFlowInstWenShu> getWenShuByFlowInsIdFileTitleWenHao(Long flowInsId, String fileTitle, String wenHao, String serialNumber) {

        return yjFlowInstWenShuDao.getWenShuByFlowInsIdFileTitleWenHao(flowInsId, fileTitle, wenHao, serialNumber);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void updateYjFlowInstWenShu(YjFlowInstWenShu wenShu) {
        // TODO Auto-generated method stub
        yjFlowInstWenShuDao.update(wenShu);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void addYjFlowInstWenShu(YjFlowInstWenShu wenShu) {
        // TODO Auto-generated method stub
        yjFlowInstWenShuDao.save(wenShu);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public YjFlowInstWenShu getYjFlowInstWenShuById(long id) {
        // 根据id查数据
        return yjFlowInstWenShuDao.getById(id);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public int delWenShuByFlowInstIdFileTitleWenHao(long flowInstId, String filetitle, String wenHao) {
        // TODO Auto-generated method stubs
        ArrayList list = yjFlowInstWenShuDao.getFilePathByWenHao(wenHao, filetitle, flowInstId);
        try {
            if (list.size() > 0) {
                HashMap map = (HashMap) list.get(0);
                String filePath = map.get("filePath") + "";
                String realFilePath = SysFinalRecource.SECOND_LEVEL_DIRECTORY + filePath;
                File file = new File(realFilePath);
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return yjFlowInstWenShuDao.delWenShuByFlowInstIdFileTitleWenHao(flowInstId, filetitle, wenHao);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public int delWenShuById(String filePath, long id) {

        try {
            String realFilePath = SysFinalRecource.SECOND_LEVEL_DIRECTORY + filePath;
            File file = new File(realFilePath);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return yjFlowInstWenShuDao.delWenShuById(id);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public int delTableByColumnName(String tableName, String accordingColName, String accordingColValue) {
        return yjFlowInstWenShuDao.delTableByColumnName(tableName, accordingColName, accordingColValue);
    }

    @Override
    public HashMap getWenShuList(String key, long flowInstId, String nodeId, String wsName, int flag, int pageIndex, int pageSize,
                                 String sortField, String sortOrder) throws Exception {
        // TODO Auto-generated method stub
        return yjFlowInstWenShuDao.getWenShuList(key, flowInstId, nodeId, wsName, flag, pageIndex, pageSize, sortField, sortOrder);
    }

    @Override
    public HashMap getWenShuList2(String type, String newShxdym, int pageIndex, int pageSize,
                                  String sortField, String sortOrder) throws Exception {
        // TODO Auto-generated method stub
        return yjFlowInstWenShuDao.getWenShuList2(type, newShxdym, pageIndex, pageSize, sortField, sortOrder);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void deleteWenS(Long id) throws Exception {
        // TODO Auto-generated method stub
        yjFlowInstWenShuDao.delete(id);
    }

    @Override
    public Boolean checkExist(long fileTypeId, int flag, long flowInstId, String spjg) {
        return yjFlowInstWenShuDao.checkExist(fileTypeId, flag, flowInstId, spjg);
    }

    @Override
    public ArrayList getFuJian(long flowInstId, String flag, String nodeId, String spjg) {
        return yjFlowInstWenShuDao.getFuJian(flowInstId, flag, nodeId, spjg);
    }

    @Override
    public ArrayList getFileByWenshu(String flowId, String flowInstId, String nodeId, String flag) {
        return yjFlowInstWenShuDao.getFileByWenshu(flowId, flowInstId, nodeId, flag);
    }

    @Override
    public ArrayList getFuJianBySqsType(String sqsType, String fileName) {
        // TODO Auto-generated method stub
        return yjFlowInstWenShuDao.getFuJianBySqsType(sqsType, fileName);
    }

    @Override
    public ArrayList getWenShuByWenHao(long flowInstId, int flag, String wenHao) {
        // TODO Auto-generated method stub
        return yjFlowInstWenShuDao.getWenShuByWenHao(flowInstId, flag, wenHao);
    }

    @Override
    public String getFilenameById(long fileTypeId) {
        // TODO Auto-generated method stub
        return yjFlowInstWenShuDao.getFilenameById(fileTypeId);
    }

    @Override
    public String getFileNameByFlag(String flag) {
        // TODO Auto-generated method stub
        return yjFlowInstWenShuDao.getFileNameByFlag(flag);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public int deleteWenShu(String flowInstId, String nodeId) throws Exception {
        // TODO Auto-generated method stub
        return yjFlowInstWenShuDao.deleteWenShu(flowInstId, nodeId);
    }

    @Override
    public ArrayList getWenShuByNodeId(String flowInstId, String flag, String filePath) throws Exception {
        // TODO Auto-generated method stub
        return yjFlowInstWenShuDao.getWenShuByNodeId(flowInstId, flag, filePath);
    }

    @Override
    public ArrayList getYjWenShuByNodeId(String flowInstId, String flag, String filePath) throws Exception {
        // TODO Auto-generated method stub
        return yjFlowInstWenShuDao.getYjWenShuByNodeId(flowInstId, flag, filePath);
    }

    @Override
    public List<YjFlowInstWenShu> getWenShuByFlowIdAndFlowInsId(long flowId, long flowInsId, String fileTitle1,
                                                                String fileTitle2) throws Exception {
        // TODO Auto-generated method stub
        return yjFlowInstWenShuDao.getWenShuByFlowIdAndFlowInsId(flowId, flowInsId, fileTitle1, fileTitle2);
    }

    @Override
    public ArrayList getFuJianBycondTable(long flowInstId, int flag, String nodeId, String spjg, String condTable) {
        // TODO Auto-generated method stub
        return yjFlowInstWenShuDao.getFuJianBycondTable(flowInstId, flag, nodeId, spjg, condTable);
    }

    @Override
    public ArrayList getFileTableListRow(long flowInstId, String fileTitleRow) {
        return yjFlowInstWenShuDao.getFileTableListRow(flowInstId, fileTitleRow);
    }

    @Override
    public ArrayList getFileTableRawById(long id) {
        return yjFlowInstWenShuDao.getFileTableRawById(id);
    }

    @Override
    public ArrayList getFlowXcPhotoById(long id) {
        return yjFlowInstWenShuDao.getFlowXcPhotoById(id);
    }

    @Override
    public ArrayList getCydList(long flowInstId, String cydName) {
        return yjFlowInstWenShuDao.getCydList(flowInstId, cydName);
    }

    @Override
    public ArrayList getWenShuByFileTietle(long flowInstId, String fileTitle, long flag, String serialNumber) {
        return yjFlowInstWenShuDao.getWenShuByFileTietle(flowInstId, fileTitle, flag, serialNumber);
    }

    @Override
    public ArrayList getWenShuByfiletypeId(long flowInstId, int flag, long filetypeId) {
        return yjFlowInstWenShuDao.getWenShuByfiletypeId(flowInstId, flag, filetypeId);
    }

    @Override
    public ArrayList getWenShuByFileTitleAndFlowInstId(long flowInstId, String fileTitle) {
        return yjFlowInstWenShuDao.getWenShuByFileTitleAndFlowInstId(flowInstId, fileTitle);
    }

    @Override
    public ArrayList getWenShuByFileTypeId(long flowInstId, long fileTypeId) {
        return yjFlowInstWenShuDao.getWenShuByFileTypeId(flowInstId, fileTypeId);
    }

    @Override
    public ArrayList getWenShuBySerialNumber(String serialNumber, long fileTypeId) {
        return yjFlowInstWenShuDao.getWenShuBySerialNumber(serialNumber, fileTypeId);
    }

    @Override
    public ArrayList getApiFileBySerialNumber(String serialNumber, String flag) {
        return yjFlowInstWenShuDao.getApiFileBySerialNumber(serialNumber, flag);
    }

    @Override
    public ArrayList getAllWenShuByFlowInstId(long flowInstId) {
        return yjFlowInstWenShuDao.getAllWenShuByFlowInstId(flowInstId);
    }

    @Override
    public ArrayList getFuJianByFILETYPEID(long flowInstId, int flag, String nodeId, String spjg, String fuJidStr) {
        return yjFlowInstWenShuDao.getFuJianByFILETYPEID(flowInstId, flag, nodeId, spjg, fuJidStr);
    }

    @Override
    public ArrayList getFuJianBanDuan(long flowInstId, String sqsType, String flag, String nodeId, String spjg) {
        return yjFlowInstWenShuDao.getFuJianBanDuan(flowInstId, sqsType, flag, nodeId, spjg);
    }

    @Override
    public ArrayList getPlxz(String sqsId) {
        return yjFlowInstWenShuDao.getPlxz(sqsId);
    }

    @Override
    public ArrayList getWenShu(long flowId, long flowInstId, String nodeId, String fileTitle) {
        return yjFlowInstWenShuDao.getWenShu(flowId, flowInstId, nodeId, fileTitle);
    }

    @Override
    public int delWenShuByFlowIdFlowInstIdNodeIdWenHao(long flowId, long flowInstId, String nodeId, String wenHao) {
        return yjFlowInstWenShuDao.delWenShuByFlowIdFlowInstIdNodeIdWenHao(flowId, flowInstId, nodeId, wenHao);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteAddWenShuMulti(YjFlowInstWenShu model, String flowInstIds, long operId, int paramValue,
                                     String userName) throws Exception {
        Long[] flowInstIdArr = strToArr(flowInstIds);
        // 删除原来的
        yjFlowInstWenShuDao.delWenShuByFlowInstIdsFileTitle(flowInstIdArr, model.getFileTitle());

        // 插入新的
        for (Long flowInstId : flowInstIdArr) {
            model.setFlowInstId(flowInstId);
            yjFlowInstWenShuDao.saveFlush(model);
            //bpsService.addOrUpdateFlowInstPara(operId, flowInstId, 1, userName);
        }
    }

    @Override
    public Map<String, Object> getWenShuListBySerialNumber(String serialNumber) {
        return yjFlowInstWenShuDao.getWenShuListBySerialNumber(serialNumber);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public int updateWenShuId(String id, String newId) {
        return yjFlowInstWenShuDao.updateWenShuId(id, newId);
    }

    @Override
    public void deleteWenShuById2(String id) {
        yjFlowInstWenShuDao.delete(Long.parseLong(id));
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public int updateWenShuFlag(String id, String flag) {
        return yjFlowInstWenShuDao.updateWenShuFlag(id, flag);
    }

    private Long[] strToArr(String str) {
        if (StringUtil.isNullOrEmptyZf(str))
            return new Long[0];
        String[] arr = str.split(",");
        Long[] result = new Long[arr.length];
        for (int i = 0; i < arr.length; i++)
            result[i] = Long.parseLong(arr[i]);
        return result;
    }
}


