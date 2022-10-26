package com.yongjie.ZhiJianSbpt.action;

import com.yongjie.ZhiJianSbpt.action.base.BaseAction;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcQzr;
import com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcQzr;
import com.yongjie.ZhiJianSbpt.service.ApiShiYsJyjcQzrService;
import com.yongjie.ZhiJianSbpt.utilities.R;
import com.yongjie.ZhiJianSbpt.utilities.RequestDataCheckUtil;
import com.yongjie.ZhiJianSbpt.utilities.StringUtil;
import com.yongjie.ZhiJianSbpt.utilities.SysFinalRecource;
import com.zhuozhengsoft.pageoffice.FileSaver;
import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;

@Controller("ApiShiYsJyjcQzrAction")
@Scope("prototype")
public class ApiShiYsJyjcQzrAction extends BaseAction<ApiShiYsJyjcQzr> {


    @Resource(name = ApiShiYsJyjcQzrService.SERVICE_NAME)
    private ApiShiYsJyjcQzrService service;

    /**
     * 获取主表 数据
     *
     * @throws Exception
     */
    public void getList() throws Exception {
        String resultJson = "";
        if (StringUtil.isEmpty(userName)) {
            resultJson = R.error("用户认证已过期,请重新登录!");
            response.getWriter().print(resultJson);
            return;
        }
        // 参数
        String serialNumber = req.getParameter("serialNumber");
        String sortField = req.getParameter("sortField");
        String sortOrder = req.getParameter("sortOrder");
        Map<Object, Object> reqMap = new HashMap<>();
        reqMap.put("pageIndex", pageIndex);
        reqMap.put("pageSize", pageSize);
        reqMap.put("sortField", sortField);
        reqMap.put("sortOrder", sortOrder);
        reqMap.put("serialNumber", serialNumber);

        try {
            Map<String, Object> listPage = service.getList(reqMap);
            resultJson = R.page(listPage);
        } catch (Exception e) {
            resultJson = R.error("查询失败", e);
        }
        response.getWriter().print(resultJson);
    }

    /**
     * 封面信息保存
     */
    public void saveQzr() throws Exception {
        String resultJson = "";
        String reqJson = req.getParameter("data");
        String resultId = "";
        //增加或修改
        if (StringUtil.isNullOrEmpty(reqJson)) {
            throw new RuntimeException("数据不能为空!");
        }
        try {
            ApiShiYsJyjcQzr entity = RequestDataCheckUtil.checkQzr(reqJson);
            // 新增 or  更新
            if (StringUtil.isNullOrEmpty(entity.getId())) {
                entity.setCreateDate(new Date());
                entity.setUpdateDate(new Date());
                service.save(entity);
                resultId = String.valueOf(entity.getId());
            } else {
                ApiShiYsJyjcQzr dbEntity = service.queryById(entity.getId());
                if (dbEntity != null) {
                    dbEntity.setName(entity.getName());
                    dbEntity.setSex(entity.getSex());
                    dbEntity.setPeopleType(entity.getPeopleType());
                    dbEntity.setIdCard(entity.getIdCard());
                    dbEntity.setAddress(entity.getAddress());
                    dbEntity.setBirthday(entity.getBirthday());
                    dbEntity.setPosition(entity.getPosition());
                    dbEntity.setJobTitle(entity.getJobTitle());
                    dbEntity.setEducation(entity.getEducation());
                    dbEntity.setDepartment(entity.getDepartment());
                    dbEntity.setPhone(entity.getPhone());
                    dbEntity.setFax(entity.getFax());
                    dbEntity.setEmail(entity.getEmail());
                    dbEntity.setSignatureField(entity.getSignatureField());
                    dbEntity.setSchool(entity.getSchool());
                    dbEntity.setProfessional(entity.getProfessional());
                    dbEntity.setJobCredentials(entity.getJobCredentials());
                    dbEntity.setTrain(entity.getTrain());
                    dbEntity.setJobUndergo(entity.getJobUndergo());
                    dbEntity.setRelevantExplanation(entity.getRelevantExplanation());
                    dbEntity.setSerialNumber(entity.getSerialNumber());
                    dbEntity.setUpdateDate(new Date());
                    service.update(dbEntity);
                    resultId = String.valueOf(dbEntity.getId());
                }
            }
            resultJson = R.ok(resultId, "保存成功！");
        } catch (Exception e) {
            resultJson = R.error(e.getMessage());
        }
        response.getWriter().print(resultJson);
    }

    /**
     * 初始化数据
     *
     * @throws Exception
     */
    public void initLoad() throws Exception {
        String resultJson = "";
        if (StringUtil.isEmpty(userName)) {
            resultJson = R.error("用户认证已过期,请重新登录!");
            response.getWriter().print(resultJson);
            return;
        }
        String serialNumber = req.getParameter("serialNumber");
        if (StringUtil.isNullOrEmpty(serialNumber)) {
            resultJson = R.error("请求有误!请关闭刷新重试！");
            response.getWriter().print(resultJson);
            return;
        }
        try {
            List<ApiShiYsJyjcQzr> entity = service.queryByBatchSerialNumber(serialNumber);
            if (entity.size() == 0) {
                resultJson = R.error("未查询到授权签字人员相关数据！");
            } else {
                resultJson = R.ok(entity);
            }
        } catch (Exception e) {
            resultJson = R.error("请求有误!请关闭刷新重试！", e);
        }
        response.getWriter().print(resultJson);
    }


    //根据id删除人员信息
    public void deleteApiQzr() throws Exception {
        //获得参数
        String idStr = req.getParameter("idResult");
        //判空操作
        if (StringUtil.isNullOrEmpty(idStr)) {
            return;
        }
        String[] ids = idStr.split(",");
        for (int i = 0, l = ids.length; i < l; i++) {
            String id = ids[i];
            //根据ID删除内容
            service.delete(Long.parseLong(id));
            response.getWriter().write("删除成功");
        }
    }

    /**
     * 新增授权签字人 保存 文件
     *
     * @throws Exception
     */
    public void fileWebSave() throws Exception {
        String id = req.getParameter("id");
        if (StringUtil.isNullOrEmpty(id)) {
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
            File fileTemp = new File(req.getSession().getServletContext().getRealPath("jsp/jyjc/rzrk/zzrd/doc") + "/" + fs.getFileName());
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
            ApiShiYsJyjcQzr dbEntity = service.queryById(Long.parseLong(id));
            dbEntity.setLocalFilePath(fileNewPath);
            dbEntity.setUpdateDate(new Date());
            service.update(dbEntity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
