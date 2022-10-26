package com.yongjie.ZhiJianSbpt.action;


import com.yongjie.ZhiJianSbpt.action.base.BaseAction;
import com.yongjie.ZhiJianSbpt.model.ShiYsJyjcFuJian;
import com.yongjie.ZhiJianSbpt.model.flowBase.ApiFujianType;
import com.yongjie.ZhiJianSbpt.service.ShiYsJyjcFuJianService;
import com.yongjie.ZhiJianSbpt.utilities.R;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import com.yongjie.ZhiJianSbpt.utilities.SysFinalRecource;
import com.zhuozhengsoft.pageoffice.FileSaver;
import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.swing.text.html.parser.Entity;
import java.io.File;
import java.util.*;

@Controller("ShiYsJyjcFuJianAction")
@Scope("prototype")
public class ShiYsJyjcFuJianAction extends BaseAction<ShiYsJyjcFuJian> {

    @Resource(name = ShiYsJyjcFuJianService.SERVICE_NAME)
    private ShiYsJyjcFuJianService service;

    public void initLoad() throws Exception {
        String resultJson;
        if (StringUtil.isNullOrEmpty(userName)) {
            resultJson = R.error("用户认证已过期,请重新登录!");
            response.getWriter().print(resultJson);
            return;
        }

        HashMap<Object, Object> reqMap = new HashMap<>();
        String serialNumber = req.getParameter("serialNumber");
        String flag = req.getParameter("flag");
        reqMap.put("serialNumber", serialNumber);
        reqMap.put("flag", flag);
        try {
            Map<String, Object> resultMap = service.queryListBySerialNumber(reqMap);
            resultMap.put("code", 1);
            resultJson = R.page(resultMap);
        } catch (Exception e) {
            resultJson = R.error(e.getMessage());
        }
        response.getWriter().print(resultJson);
    }

    public void fileWebSave() throws Exception {
        String flag = req.getParameter("flag");
        String serialNumber = req.getParameter("serialNumber");

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

            if (flag.equals("403")) {
                fileTemp = new File(req.getSession().getServletContext().getRealPath("jsp/jyjc/rzrk/zxgl/doc") + "/" + fs.getFileName());
            }
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
            ShiYsJyjcFuJian entity = new ShiYsJyjcFuJian();
            ApiFujianType fuJianTypeEntity = service.queryFuJIanTypeByFlag(flag);
            ShiYsJyjcFuJian dbEntity = service.queryBySerialNumber(serialNumber);
            if (dbEntity != null) {
                dbEntity.setSqsId(fuJianTypeEntity.getId());
                dbEntity.setFileTypeId(fuJianTypeEntity.getFlag());
                dbEntity.setFilePath(fileNewPath);
                dbEntity.setFileExtension(ext);
                dbEntity.setFileTitle(fuJianTypeEntity.getName());
                dbEntity.setCzr(userName);
                dbEntity.setCzDate(new Date());
                dbEntity.setIsSave("1");
                dbEntity.setSerialNumber(serialNumber);
                service.update(dbEntity);
            } else {
                entity.setSqsId(fuJianTypeEntity.getId());
                entity.setFileTypeId(fuJianTypeEntity.getFlag());
                entity.setFilePath(fileNewPath);
                entity.setFileExtension(ext);
                entity.setFileTitle(fuJianTypeEntity.getName());
                entity.setCzr(userName);
                entity.setCzDate(new Date());
                entity.setIsSave("1");
                entity.setSerialNumber(serialNumber);
                service.save(entity);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void getJyjcFuJianById() throws Exception {
        String resultJson;
        if (StringUtil.isNullOrEmpty(userName)) {
            resultJson = R.error("用户认证已过期,请重新登录!");
            response.getWriter().print(resultJson);
            return;
        }

        HashMap<Object, Object> reqMap = new HashMap<>();
        String id = req.getParameter("id");
        try {
            ShiYsJyjcFuJian entity = service.getJyjcFuJianById(Long.valueOf(id));
            resultJson = R.page(entity);
        } catch (Exception e) {
            resultJson = R.error(e.getMessage());
        }
        response.getWriter().print(resultJson);
    }

    /**
     * 查询具体附件信息
     *
     * @throws Exception
     */
    public void getSqsFujian() throws Exception {
        String resultJson = null;
        if (!StringUtil.isEmpty(userName)) {
            String serialNumber = req.getParameter("serialNumber") == null ? "" : req.getParameter("serialNumber");
            try {
                ShiYsJyjcFuJian entity = service.getSqsFujian(serialNumber);
                resultJson = com.yongjie.ZhiJianSbpt.module.util.R.ok(entity);
            } catch (Exception e) {
                resultJson = com.yongjie.ZhiJianSbpt.module.util.R.error(e.getMessage(), "查询失败");
            }
        } else {
            resultJson = com.yongjie.ZhiJianSbpt.module.util.R.error("用户认证已过期,请重新登录!");
        }
        response.getWriter().print(resultJson);
    }

}
