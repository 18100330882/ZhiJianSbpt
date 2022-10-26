<%@page import="cn.hutool.poi.excel.ExcelReader" %>
<%@page import="cn.hutool.poi.excel.ExcelUtil" %>
<%@page import="org.apache.commons.io.FileUtils" %>
<%@page import="cn.hutool.core.io.FileUtil" %>
<%@page import="com.oreilly.servlet.MultipartRequest" %>
<%@page import="java.io.File" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.*" %>
<%@ page import="com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcQzr" %>
<%@ page import="com.yongjie.ZhiJianSbpt.service.ApiShiYsJyjcQzrService" %>
<%@ page import="com.yongjie.ZhiJianSbpt.container.ServiceProvider" %>
<%@ page import="com.yongjie.ZhiJianSbpt.model.flowBase.ApiFile" %>
<%@ page import="java.util.*" %>
<%@ page import="com.yongjie.ZhiJianSbpt.service.shiYsJyjc.ApiFileService" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="Java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    ApiShiYsJyjcQzrService RyService = (ApiShiYsJyjcQzrService) ServiceProvider.getService(ApiShiYsJyjcQzrService.SERVICE_NAME);
    ApiFileService apiFileService = (ApiFileService) ServiceProvider.getService(ApiFileService.SERVICE_NAME);
    String userName = (String) request.getSession().getAttribute("userName");

    // 流水号
    String serialNumber = request.getParameter("serialNumber") == null ? "" : request.getParameter("serialNumber");

    // 文件存储路径
    String saveDirectory = SysFinalRecource.SECOND_LEVEL_DIRECTORY;
    String yyyyMMdd = DateUtils.format();
    String filePath = saveDirectory + yyyyMMdd;
    File dir = new File(filePath); //D:/sxzjUpload/files/2017-2-4
    if (!dir.exists()) {
        dir.mkdirs();
    }
    // 记录存储文件的路径名称 2017-02-04/abcded.doc
    String uuidPath = null;
    // 文件后缀名称
    String ext = null;
    //文件大小
    long fileSize = 0;
    //文件名
    String fileName = "";

    // 文件上传
    MultipartRequest multi = new MultipartRequest(request, filePath, 1 * 100 * 1024 * 1024, "UTF-8");
    Enumeration files = multi.getFileNames();
    while (files.hasMoreElements()) {
        String name = files.nextElement() + "";
        // 获取上传的文件
        File f = multi.getFile(name);
        // 文件不为空
        if (f != null) {
            fileName = multi.getFilesystemName(name);
            // 获取最后一个点的下标
            int len = fileName.lastIndexOf(".");
            // 获取文件后缀名称
            ext = fileName.substring(len);
            // 生成新的文件名称
            String uuid = UuidUtil.getSimpleUUID();
            // 用于存储新的文件路径地址
            uuidPath = yyyyMMdd + "/" + uuid + ext;
            // 放入存储的路径
            File dest = new File(saveDirectory + uuidPath);
            fileSize = f.length();
            FileUtils.copyFile(f, dest);
            // 删除旧文件
            f.delete();
        }
    }


    //普通读取方式
    // 这里从上传文件里面读取数据,存储到数据库中
    ExcelReader reader = ExcelUtil.getReader(FileUtil.file(saveDirectory + uuidPath));
    System.out.println(reader);
    // 从第二行到最后一行读取
    List<List<Object>> read = reader.read(1, reader.getRowCount());
    Date date = new Date();//日期
    String username = new SessionUtil().getUserName(request);
    List<ApiShiYsJyjcQzr> nlList = new ArrayList();

    int count = 0;
    try {
        for (int j = 0; j < read.size(); j++) {//所有行记录
            count = j + 2;
            System.out.println(read.get(j));
            Object[] objArr = read.get(j).toArray();//单行记录
            ApiShiYsJyjcQzr nlModel = new ApiShiYsJyjcQzr();//实体类，填充数据

            //第一列 姓名
            if (StringUtil.isEmpty(objArr[0] + "")) {
                nlModel.setName("");
            } else {
                nlModel.setName(objArr[0] + "");
            }

            //第二列 性别
            if (StringUtil.isEmpty(objArr[1] + "")) {
                nlModel.setSex("");
            } else {
                nlModel.setSex(objArr[1] + "");
            }

            //第三列 类型
            if (StringUtil.isEmpty(objArr[2] + "")) {
                nlModel.setPeopleType((""));
            } else {
                nlModel.setPeopleType((objArr[2] + ""));
            }

            //第四列 身份证号
            if (StringUtil.isEmpty(objArr[3] + "")) {
                nlModel.setIdCard((""));
            } else {
                nlModel.setIdCard((objArr[3] + ""));
            }

            //第五列 出生日期
            if (!StringUtil.isNullOrEmpty(objArr[4] + "")) {
                Object o = objArr[4];
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date birth = sdf.parse(String.valueOf(o));
                nlModel.setBirthday(birth);
            }

            //第六列 职务(岗位)
            if (StringUtil.isEmpty(objArr[5] + "")) {
                nlModel.setPosition("");
            } else {
                nlModel.setPosition(objArr[5] + "");
            }

            //第七列 职称
            if (StringUtil.isEmpty(objArr[6] + "")) {
                nlModel.setJobTitle("");
            } else {
                nlModel.setJobTitle(objArr[6] + "");
            }

            //第八列 文化程度
            if (StringUtil.isEmpty(objArr[7] + "")) {
                nlModel.setEducation("");
            } else {
                nlModel.setEducation(objArr[7] + "");
            }

            //第九列 现在部门岗位
            if (StringUtil.isEmpty(objArr[8] + "")) {
                nlModel.setDepartment("");
            } else {
                nlModel.setDepartment(objArr[8] + "");
            }

            //第十列 电话
            if (StringUtil.isEmpty(objArr[9] + "")) {
                nlModel.setPhone("");
            } else {
                nlModel.setPhone(objArr[9] + "");
            }

            //第十一列 传真
            if (StringUtil.isEmpty(objArr[10] + "")) {
                nlModel.setFax("");
            } else {
                nlModel.setFax(objArr[10] + "");
            }

            //第十二列 电子邮箱
            if (StringUtil.isEmpty(objArr[11] + "")) {
                nlModel.setEmail("");
            } else {
                nlModel.setEmail(objArr[11] + "");
            }

            //第十三列 申请签字领域
            if (StringUtil.isEmpty(objArr[12] + "")) {
                nlModel.setSignatureField("");
            } else {
                nlModel.setSignatureField(objArr[12] + "");
            }

            //第十四列 毕业学校
            if (StringUtil.isEmpty(objArr[13] + "")) {
                nlModel.setSchool("");
            } else {
                nlModel.setSchool(objArr[13] + "");
            }

            //第十五列 所学专业
            if (StringUtil.isEmpty(objArr[14] + "")) {
                nlModel.setProfessional("");
            } else {
                nlModel.setProfessional(objArr[14] + "");
            }

            //第十六列 执业资格证书
            if (StringUtil.isEmpty(objArr[15] + "")) {
                nlModel.setJobCredentials("");
            } else {
                nlModel.setJobCredentials(objArr[15] + "");
            }

            //第十七列 培训经历
            if (StringUtil.isEmpty(objArr[16] + "")) {
                nlModel.setTrain("");
            } else {
                nlModel.setTrain(objArr[16] + "");
            }

            //第十八列 从事检测机构工作的经历
            if (StringUtil.isEmpty(objArr[17] + "")) {
                nlModel.setJobUndergo("");
            } else {
                nlModel.setJobUndergo(objArr[17] + "");
            }

            //第十九列 相关说明
            if (StringUtil.isEmpty(objArr[18] + "")) {
                nlModel.setRelevantExplanation("");
            } else {
                nlModel.setRelevantExplanation(objArr[18] + "");
            }

            //第二十列 检验检测机构地址
            if (StringUtil.isEmpty(objArr[19] + "")) {
                nlModel.setAddress("");
            } else {
                nlModel.setAddress(objArr[19] + "");
            }

            nlModel.setCreateDate(date);    // 日期
            nlModel.setSerialNumber(serialNumber); // 流水号
            nlList.add(nlModel);

        }


        // 如果大于1000则进行保存
        if (nlList.size() >= 1000) {
            try {
                RyService.saveBatch(nlList, 1000);

            } catch (Exception e) {

            } finally {
                nlList.clear();// 清空集合,释放内存
            }
        }

        if (!StringUtil.isEmpty(nlList) && nlList.size() > 0) {
            try {
                RyService.saveBatch(nlList, 100);
            } catch (Exception e) {

            } finally {
                nlList.clear();// 清空集合,释放内存
            }
        }

        //文件唯一标识符
        String uuid = String.valueOf(UUID.randomUUID()).substring(0, 12);

        //记录文件地址
        ApiFile apiFile = new ApiFile();
        apiFile.setFileType(ext);
        apiFile.setFileName(fileName);
        apiFile.setSerialNumber(serialNumber);
        apiFile.setUnid(uuid);
        apiFile.setCzr("授权签字人信息");
        apiFile.setLocalFilePath(uuidPath);
        apiFile.setCreateDate(new Date());
        apiFileService.save(apiFile);


        Map result = new HashMap();
        result.put("code", 0);
        result.put("message", "成功");
        result.put("success", false);
        String json = com.alibaba.fastjson.JSON.toJSONString(result);
        response.getWriter().write(json);
    } catch (Exception e) {
        e.printStackTrace();
        Map result = new HashMap();
        result.put("code", 1);
        result.put("message", "第" + count + "行有错误,请修改后重试");
        result.put("success", false);
        String json = com.alibaba.fastjson.JSON.toJSONString(result);
        response.getWriter().write(json);
    }
%>
