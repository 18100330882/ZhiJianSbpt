<%@ page language="Java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@page import="cn.hutool.poi.excel.ExcelReader" %>
<%@page import="cn.hutool.poi.excel.ExcelUtil" %>
<%@page import="org.apache.commons.io.FileUtils" %>
<%@page import="cn.hutool.core.io.FileUtil" %>
<%@page import="com.oreilly.servlet.MultipartRequest" %>
<%@page import="java.io.File" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.*" %>
<%@ page import="com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcRy" %>
<%@ page import="com.yongjie.ZhiJianSbpt.service.ApiShiYsJyjcRyService" %>
<%@ page import="com.yongjie.ZhiJianSbpt.container.ServiceProvider" %>
<%@ page import="com.yongjie.ZhiJianSbpt.model.flowBase.ApiFile" %>
<%@ page import="java.util.*" %>
<%@ page import="com.yongjie.ZhiJianSbpt.service.shiYsJyjc.ApiFileService" %>
<%@ page import="com.yongjie.ZhiJianSbpt.service.ApiShiYsJyjcCdService" %>
<%@ page import="com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcCd" %>
<%@ page import="org.apache.tomcat.jni.Address" %>
<%
    ApiShiYsJyjcRyService RyService = (ApiShiYsJyjcRyService) ServiceProvider.getService(ApiShiYsJyjcRyService.SERVICE_NAME);
    ApiFileService apiFileService = (ApiFileService) ServiceProvider.getService(ApiFileService.SERVICE_NAME);
    ApiShiYsJyjcCdService jyjcCdService = (ApiShiYsJyjcCdService) ServiceProvider.getService(ApiShiYsJyjcCdService.SERVICE_NAME);
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
    List<ApiShiYsJyjcRy> nlList = new ArrayList();

    int count = 0;
    String message = null;
    try {
        for (int j = 0; j < read.size(); j++) {//所有行记录
            count = j + 2;
            System.out.println(read.get(j));
            Object[] objArr = read.get(j).toArray();//单行记录
            ApiShiYsJyjcRy nlModel = new ApiShiYsJyjcRy();//实体类，填充数据

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

            //第三列 年龄
            if (StringUtil.isEmpty(objArr[2] + "")) {
                nlModel.setAge(Integer.valueOf(""));
            } else {
                nlModel.setAge(Integer.valueOf(objArr[2] + ""));
            }

            //第四列 文化程度
            if (StringUtil.isEmpty(objArr[3] + "")) {
                nlModel.setEducation("");
            } else {
                nlModel.setEducation(objArr[3] + "");
            }

            //第五列 证件类型
            if (StringUtil.isEmpty(objArr[4] + "")) {
                nlModel.setDocumentType("");
            } else {
                nlModel.setDocumentType(objArr[4] + "");
            }

            //第六列 身份证号
            if (StringUtil.isEmpty(objArr[5] + "")) {
                nlModel.setIdCard("");
            } else {
                nlModel.setIdCard(objArr[5] + "");
            }

            //第七列 职称
            if (StringUtil.isEmpty(objArr[6] + "")) {
                nlModel.setJobTitle("");
            } else {
                nlModel.setJobTitle(objArr[6] + "");
            }

            //第八列 职务(岗位)
            if (StringUtil.isEmpty(objArr[7] + "")) {
                nlModel.setPosition("");
            } else {
                nlModel.setPosition(objArr[7] + "");
            }

            //第九列 所学专业
            if (StringUtil.isEmpty(objArr[8] + "")) {
                nlModel.setProfessional("");
            } else {
                nlModel.setProfessional(objArr[8] + "");
            }

            //第十列 从事本技术领域年限
            if (StringUtil.isEmpty(objArr[9] + "")) {
                nlModel.setTechnicalFieldYear("");
            } else {
                nlModel.setTechnicalFieldYear(objArr[9] + "");
            }

            //第十一列 现在部门岗位
            if (StringUtil.isEmpty(objArr[10] + "")) {
                nlModel.setDepartment("");
            } else {
                nlModel.setDepartment(objArr[10] + "");
            }

            //第十二列 检验检测机构地址
            Map<String, Object> jgdzMap = jyjcCdService.getJgdzData(serialNumber);
            List<HashMap> data = (List<HashMap>) jgdzMap.get("data");
            boolean flag = true;
            for (int i = 0; i < data.size(); i++) {
                String siteAddress = String.valueOf(data.get(i).get("SITEADDRESS"));
                if (!StringUtil.isNullOrEmpty(siteAddress)) {
                    if ((objArr[11] + "").equals(siteAddress)) {
                        nlModel.setAddress(objArr[11] + "");
                        flag = false;
                    }
                }
            }
            if (flag) {
                message = "第" + count + "行的机构地址有误,请确认是否与机构资源的申请地址一致";
                throw new RuntimeException("机构地址有误");
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

        //记录文件日志
        ApiFile apiFile = new ApiFile();
        apiFile.setFileType(ext);
        apiFile.setFileName(fileName);
        apiFile.setUnid(uuid);
        apiFile.setSerialNumber(serialNumber);
        apiFile.setCzr("人员信息");
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
        if (StringUtil.isNullOrEmpty(message)) {
            message = "第" + count + "行" + "" + "有错误,请修改后重试";
        }
        result.put("message", message);
        result.put("success", false);
        String json = com.alibaba.fastjson.JSON.toJSONString(result);
        response.getWriter().write(json);
    }
%>
