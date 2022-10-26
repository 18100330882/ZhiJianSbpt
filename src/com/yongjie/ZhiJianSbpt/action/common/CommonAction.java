package com.yongjie.ZhiJianSbpt.action.common;

import com.yongjie.ZhiJianSbpt.action.base.BaseAction;
import com.yongjie.ZhiJianSbpt.bggl.model.SqqzrChangeDetails;
import com.yongjie.ZhiJianSbpt.bggl.service.SqqzrChangeDetailsService;
import com.yongjie.ZhiJianSbpt.model.ShiYsJyjcXchcPsbgNlHz;
import com.yongjie.ZhiJianSbpt.module.util.R;
import com.yongjie.ZhiJianSbpt.service.ShiYsJyjcXchcPsbgNlHzService;
import com.yongjie.ZhiJianSbpt.service.common.CommonService;
import com.yongjie.ZhiJianSbpt.utilities.JSON;
import com.yongjie.ZhiJianSbpt.utilities.SysFinalRecource;
import com.zhuozhengsoft.pageoffice.FileSaver;
import dm.jdbc.util.StringUtil;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;

@Controller("CommonAction")
@Scope("prototype")
public class CommonAction extends BaseAction<T> {

    @Resource(name = CommonService.SERVICE_NAME)
    private CommonService service;

    @Resource(name = ShiYsJyjcXchcPsbgNlHzService.SERVICE_NAME)
    private ShiYsJyjcXchcPsbgNlHzService psbgNlHzService;

    @Resource(name = SqqzrChangeDetailsService.SERVICE_NAME)
    private SqqzrChangeDetailsService sqqzrChangeDetailsService;

    public void getShiysjyjcScType() throws Exception {
        String resultJson = "";
        Map<Object, Object> paramMap = new HashMap<>();
        String keyName = req.getParameter("keyName");
        paramMap.put("paramMap", keyName);
        try {
            Map<String, Object> resultMap = service.getShiysjyjcScType(paramMap);
            resultJson = JSON.Encode(resultMap.get("data"));
        } catch (Exception e) {
            resultJson = R.error(e.getMessage());
            e.printStackTrace();
        }
        response.getWriter().print(resultJson);
    }

    /**
     * 获取 主管部门数据
     *
     * @throws Exception
     */
    public void getZgbmData() throws Exception {
        String resultJson = "";
        if (!StringUtil.isEmpty(userName)) {
            try {
                Map<String, Object> resultMap = service.getZgbmData();
                resultJson = JSON.Encode(resultMap.get("data"));
            } catch (Exception e) {
                resultJson = R.error(e.getMessage(), "查询失败!");
            }
        } else {
            resultJson = R.error("用户认证已过期,请重新登录!");
        }
        response.getWriter().print(resultJson);
    }

    /**
     * 获取流水号
     *
     * @throws Exception
     */
    public void getSerialNumber() throws Exception {
        String resultJson = "";
        if (StringUtil.isEmpty(userName)) {
            resultJson = R.error("用户认证已过期,请重新登录!");
            response.getWriter().print(resultJson);
            return;
        }
        try {
            Map<String, Object> resultMap = service.getSerialNumber();
            String serialNum = (String) resultMap.get("data");
            String substring = String.valueOf(new Date().getTime()).substring(3, 12);
            resultMap.put(R.DATA_NAME, substring + serialNum);
            resultJson = JSON.Encode(resultMap);
        } catch (Exception e) {
            resultJson = R.error(e.getMessage(), "获取失败！");
        }
        response.getWriter().print(resultJson);
    }

    public void fileSave() throws Exception {
        String flowId = String.valueOf(req.getParameter("flowId"));
        String serialNumber = String.valueOf(req.getParameter("serialNumber"));
        String action = String.valueOf(req.getParameter("action"));
        String psbgId = String.valueOf(req.getParameter("psbgId"));
        Map<String, Object> map = fileWebSaveCommon();
        String fileName = (String) map.get("fileName");
        String filePath = (String) map.get("filePath");
        Long fileSize = (Long) map.get("fileSize");
        String fileType = (String) map.get("fileType");

        if ("4".equals(flowId) || "6".equals(flowId)) {
            // 能力-保存生成word
            if ("nlsaveword".equals(action)) {
                if (!com.yongjie.ZhiJianSbpt.utilities.StringUtil.isNullOrEmpty(psbgId)) {
                    ShiYsJyjcXchcPsbgNlHz psbghzEntity = psbgNlHzService.queryById(Long.valueOf(psbgId));
                    psbghzEntity.setFilePathWord(filePath);
                    psbghzEntity.setIsSave("1");
                    psbgNlHzService.edit(psbghzEntity);
                }
            }

            // 仪器设备-保存生成word
            if ("yqsbsaveword".equals(action)) {
                if (!com.yongjie.ZhiJianSbpt.utilities.StringUtil.isNullOrEmpty(psbgId)) {
                    ShiYsJyjcXchcPsbgNlHz psbghzEntity = psbgNlHzService.queryById(Long.valueOf(psbgId));
                    psbghzEntity.setFilePathWord(filePath);
                    psbghzEntity.setIsSave("1");
                    psbgNlHzService.edit(psbghzEntity);
                }
            }
        }
    }

    public void fileSaveNew() throws Exception {
        String flowId = String.valueOf(req.getParameter("flowId"));
        String filePath = String.valueOf(req.getParameter("filePath"));
        String action = String.valueOf(req.getParameter("action"));
        String psbgId = String.valueOf(req.getParameter("psbgId"));

        if ("4".equals(flowId) || "6".equals(flowId)) {
            // 能力-保存生成word
            if ("nlsaveword".equals(action)) {
                if (!com.yongjie.ZhiJianSbpt.utilities.StringUtil.isNullOrEmpty(psbgId)) {
                    ShiYsJyjcXchcPsbgNlHz psbghzEntity = psbgNlHzService.queryById(Long.valueOf(psbgId));
                    psbghzEntity.setFilePathWord(filePath);
                    psbghzEntity.setIsSave("1");
                    psbgNlHzService.edit(psbghzEntity);
                }
            }

            // 仪器设备-保存生成word
            if ("yqsbsaveword".equals(action)) {
                if (!com.yongjie.ZhiJianSbpt.utilities.StringUtil.isNullOrEmpty(psbgId)) {
                    ShiYsJyjcXchcPsbgNlHz psbghzEntity = psbgNlHzService.queryById(Long.valueOf(psbgId));
                    psbghzEntity.setFilePathWord(filePath);
                    psbghzEntity.setIsSave("1");
                    psbgNlHzService.edit(psbghzEntity);
                }
            }
        }
    }


    private Map<String, Object> fileWebSaveCommon() {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, 0);
            int year1 = calendar.get(Calendar.YEAR);// 月份
            int month1 = calendar.get(Calendar.MONTH) + 1;// 日期
            int date1 = calendar.get(Calendar.DATE);
            String d1 = year1 + "-" + month1 + "-" + date1;//2017-2-4
            //拿到 事先定义好的静态变量 就是 所谓的二级目录 也是路径的一部分
            String saveDirectory = SysFinalRecource.SECOND_LEVEL_DIRECTORY;
            //拼接路径 并且 创建文件夹
            File dir = new File(saveDirectory + d1);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            /** *******生成目标文件,并填充内容**********/
            FileSaver fs = new FileSaver(req, response);
            File fileTemp = new File(req.getSession().getServletContext().getRealPath("jsp/jyjc/rzrk/zzrd/doc") + "/" + fs.getFileName());
            //拿到Tomcat中的模版的名字
            long fileSize = fileTemp.length();
            String fileName = fileTemp.getName();
            int i = fileName.lastIndexOf(".");//原名称里倒数第一个"."在哪里
            String fileType = fileName.substring(i);//取得后缀，及"."后面的字符 就是扩展名
            //换掉原模版的名字 用时间+随机数+后缀名的格式 之后把新名字保存到数据库里
            String filePath = d1 + "/" + UUID.randomUUID().toString() + fileType;
            //要用全部名字 生产文件
            File dest = new File(saveDirectory + filePath);
            FileUtils.copyFile(fileTemp, dest);//存到目标路径,生成文件
            fs.saveToFile(saveDirectory + filePath);
            fs.close();

            resultMap.put("fileName", fileName);
            resultMap.put("filePath", filePath);
            resultMap.put("fileSize", fileSize);
            resultMap.put("fileType", fileType);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resultMap;
    }

    public void justSaveFile() throws Exception {
        String id = req.getParameter("id");
        String tableName = req.getParameter("tableName");
        if (com.yongjie.ZhiJianSbpt.utilities.StringUtil.isNullOrEmpty(id)) {
            throw new RuntimeException();
        }
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, 0);
            int year1 = calendar.get(Calendar.YEAR);// 月份
            int month1 = calendar.get(Calendar.MONTH) + 1;// 日期
            int date1 = calendar.get(Calendar.DATE);
            String d1 = year1 + "-" + month1 + "-" + date1;//2017-2-4
            //拿到 事先定义好的静态变量 就是 所谓的二级目录 也是路径的一部分
            String saveDirectory = SysFinalRecource.SECOND_LEVEL_DIRECTORY;
            //拼接路径 并且 创建文件夹
            File dir = new File(saveDirectory + d1); //D:/sxzjUpload/files/2017-2-4
            if (!dir.exists()) {
                dir.mkdirs();
            }
            /** *******生成目标文件,并填充内容**********/
            FileSaver fs = new FileSaver(req, response);
            File fileTemp = new File(req.getSession().getServletContext().getRealPath("jsp/jyjc/rzrk/bggl/sqs/temp") + "/" + fs.getFileName());
            //拿到Tomcat中的模版的名字
            long fileSize = fileTemp.length();
            String filename = fileTemp.getName();
            int i = filename.lastIndexOf(".");//原名称里倒数第一个"."在哪里
            String ext = filename.substring(i);//取得后缀，及"."后面的字符 就是扩展名
            //换掉原模版的名字 用时间+随机数+后缀名的格式 之后把新名字保存到数据库里
            String fileNewPath = d1 + "/" + UUID.randomUUID().toString() + ext;
            //要用全部名字 生产文件
            File dest = new File(saveDirectory + fileNewPath);
            FileUtils.copyFile(fileTemp, dest);//存到目标路径,生成文件
            fs.saveToFile(saveDirectory + fileNewPath);
            fs.close();
            // 更新数据库 路径

            if ("SQQZRCHANGEDETAILS".equals(tableName)) {
                SqqzrChangeDetails entity = sqqzrChangeDetailsService.getById(Long.valueOf(id));
                entity.setFilePath(fileNewPath);
                entity.setFileTitle(filename);
                entity.setSuffix(ext);
                sqqzrChangeDetailsService.update(entity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
