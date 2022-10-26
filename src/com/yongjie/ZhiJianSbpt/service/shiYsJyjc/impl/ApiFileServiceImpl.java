/**
 * @Author: 潘元飞
 * @Createtime: 2020年6月16日 上午9:42:42
 * @Copyright: Copyright (c) 2017
 * @Company: 北京永杰友信科技有限公司
 * @Version：1.0
 * @Description:
 */
package com.yongjie.ZhiJianSbpt.service.shiYsJyjc.impl;

import com.yongjie.ZhiJianSbpt.dao.shiYsJyjc.ApiFileDao;
import com.yongjie.ZhiJianSbpt.model.flowBase.ApiFile;
import com.yongjie.ZhiJianSbpt.model.flowBase.YjFlowInstWenShu;
import com.yongjie.ZhiJianSbpt.service.shiYsJyjc.ApiFileService;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import com.yongjie.ZhiJianSbpt.utilities.SysFinalRecource;
import com.yongjie.ZhiJianSbpt.utilities.delFileUtil;

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

@Transactional(readOnly = true)
@Service(ApiFileService.SERVICE_NAME)
public class ApiFileServiceImpl implements ApiFileService {

    @Resource(name = ApiFileDao.SERVICE_NAME)
    private ApiFileDao apiFileDao;


    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void save(ApiFile t) throws Exception {
        apiFileDao.save(t);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void update(ApiFile t) throws Exception {
        apiFileDao.update(t);
    }

    @Override
    public ApiFile getApiFileById(Long id) {
        return apiFileDao.getById(id);
    }

    @Override
    public void deleApiFile(Long flowId, String unid, String serialNumber) {
        apiFileDao.deleApiFile(flowId, unid, serialNumber);
    }

    @Override
    public Map<String, Object> listPage(Long flowId, String fileType, String serialNumber, String flag
            , int pageIndex, int pageSize)
            throws Exception {
        return apiFileDao.listPage(flowId, fileType, serialNumber, flag, pageIndex, pageSize);
    }

    @Override
    public Map<String, Object> wenShuList(Long flowId, String serialNumber, String flag
            , int pageIndex, int pageSize)
            throws Exception {
        return apiFileDao.wenShuList(flowId, serialNumber, flag, pageIndex, pageSize);
    }

    @Override
    public ApiFile getApiFile(String unid, String serialNumber) {
        return apiFileDao.getApiFile(unid, serialNumber);
    }

    @Override
    public List<ApiFile> getApiFileBySerialNumberFlag(String serialNumber, String flag) {
        return apiFileDao.getApiFileBySerialNumberFlag(serialNumber, flag);
    }

    @Override
    public HashMap<String, Object> queryListBySerialNumber(HashMap<Object, Object> reqMap) throws Exception {
        return apiFileDao.queryListBySerialNumber(reqMap);
    }

    @Override
    public ArrayList<ApiFile> apiFuJianTreeGridSp(Long flowId, String yjFlowFuJianId, String flag, String serialNumber, String siteAddress) {
        return apiFileDao.apiFuJianTreeGridSp(flowId, yjFlowFuJianId, flag, serialNumber, siteAddress);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(Long id) {
        apiFileDao.delete(id);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public int deleteBatchBySerialNumber(String serialNumber) throws Exception {

        //判空操作
        if (StringUtil.isNullOrEmpty(serialNumber)) {
            return 0;
        }

        List<ApiFile> entity = this.getApiFileBySerialNumberFlag(serialNumber, null);
        for (int i = 0; i < entity.size(); i++) {
            String filePath = entity.get(i).getLocalFilePath();
            if (!StringUtil.isNullOrEmpty(filePath)) {
                /****************文件操作****************/
                String fileAllPath = "";
                File filetemp = null;
                fileAllPath = SysFinalRecource.SECOND_LEVEL_DIRECTORY + filePath;
                //删除本地文件
                filetemp = new File(fileAllPath);
                if (filetemp.exists()) {
                    filetemp.delete();
                    /****************文件操作****************/
                }
            }
        }

        return apiFileDao.deleteBatchBySerialNumber(serialNumber);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public int saveBatch(List<ApiFile> list) throws Exception {
        return apiFileDao.saveBatch(list);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public int saveBatch(List<ApiFile> list, int size) throws Exception {
        return apiFileDao.saveBatch(list, size);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public int delOldAndSaveBatch(List<ApiFile> list) throws Exception {

        if (!StringUtil.isEmpty(list) && list.size() > 0) {
            System.out.println("------list.size()-------" + list.size());

            for (int i = 0; i < list.size(); i++) {
                //根据流水号和flag状态进行查询原来数据
                ArrayList<ApiFile> dbList = apiFileDao.apiFuJianTreeGridSp(null, null, list.get(i).getFlag(), list.get(0).getSerialNumber(), list.get(0).getSiteAddress());

                //查询数据中不是第一个
                if (!StringUtil.isEmpty(dbList) && dbList.size() > 0) {

                    System.out.println("------list.size()-------" + list.size());

                    for (ApiFile apiFile : dbList) { // 本地文件不为空,则可以删除文件
                        if (!StringUtil.isEmpty(apiFile.getLocalFilePath())) { // 删除本地已经下载的文件
                            delFileUtil.delFile(apiFile.getLocalFilePath());
                        }
                    }

                    //1.删除原数据
                    deleteBatchBySerialNumber(list.get(i).getSerialNumber());
                }

            }
            return saveBatch(list, list.size());

            // 2.批量新增数据
        }
        return 0;
    }

    @Override
    public List<Map<String, Object>> getApiFileAndWenshu(String serialNumber) throws Exception {
        return apiFileDao.getApiFileAndWenshu(serialNumber);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void addApiFile(ApiFile apiFile) {
        // TODO Auto-generated method stub
        apiFileDao.save(apiFile);
    }

}
