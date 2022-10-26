<%@ page language="Java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         import="java.util.*,
                 java.io.*,
                 org.apache.commons.io.FileUtils,
                 com.yongjie.ZhiJianSbpt.container.*,
                 com.yongjie.ZhiJianSbpt.utilities.*,
                 com.yongjie.ZhiJianSbpt.model.flowBase.*,
                 com.yongjie.ZhiJianSbpt.service.flowBase.*,
                 com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.yongjie.ZhiJianSbpt.service.shiYsJyjc.ApiFileService" %>
<%
    IYjFlowInstWenShuService wenShuService = (IYjFlowInstWenShuService) ServiceProvider.getService(IYjFlowInstWenShuService.SERVICE_NAME);
    ApiFileService apiFileService = (ApiFileService) ServiceProvider.getService(ApiFileService.SERVICE_NAME);
    String flowIds = request.getParameter("flowId");
    String serialNumber = request.getParameter("serialNumber") == null ? "" : request.getParameter("serialNumber");
    if (serialNumber.equals("undefined")) {
        serialNumber = "";
    }
    String flagStr = request.getParameter("flag") == null ? "" : request.getParameter("flag");
    String fileTypeId = request.getParameter("fileTypeId") == null ? "" : request.getParameter("fileTypeId");
    String paixu = request.getParameter("paixu") == null ? "" : request.getParameter("paixu");
    String FlowId = request.getParameter("flowId") == null ? "" : request.getParameter("flowId");

    //文件唯一标识符
    String uuid = String.valueOf(UUID.randomUUID()).substring(0, 12);

//先拿到当前日期 ,日期也文件路径的一部分
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

    MultipartRequest multi = new MultipartRequest(request, saveDirectory + d1, 1 * 100 * 1024 * 1024, "UTF-8");
//如果有上传文件, 则保存到数据内,//传回所有文件输入类型的名称 
    Enumeration files = multi.getFileNames();
    while (files.hasMoreElements()) {
        //Name指文件输入类型的名称
        String name = (String) files.nextElement();
        File f = multi.getFile(name);
        if (f != null) {

            //读取上传后的项目文件, 导入保存到数据中
            String fileName = multi.getFilesystemName(name);

            //得到附件类型名
            String fileTypeName = "";
            if (fileTypeId.length() > 0) {
                fileTypeName = wenShuService.getFileNameByFlag(flagStr);
            }
            //重设文件名
            String fileAllName = fileTypeName + "-" + fileName.substring(0, fileName.lastIndexOf('.'));

            int i = fileName.lastIndexOf(".");//原名称里倒数第一个"."在哪里
            String ext = fileName.substring(i + 1);//取得后缀，及"."后面的字符 就是扩展名
            String exts = fileName.substring(i);//取得后缀，后面的字符 就是扩展名
            //保存到数据库的名字
            String fileNewPath = d1 + "/" + UUID.randomUUID().toString() + exts;
            //要用全部名字 生产文件
            File dest = new File(saveDirectory + fileNewPath); //利用文件名,创建文件
            FileUtils.copyFile(f, dest);//存到目标路径,生成文件
            long fileSize = f.length();
            f.delete();

            ApiFile apiFile = new ApiFile();
            //流程ID
            apiFile.setFlowId(Long.valueOf(FlowId));
            //文件名
            apiFile.setFileName(fileAllName);
            //文件类型
            apiFile.setFileType(ext);
            //文件地址
            apiFile.setLocalFilePath(fileNewPath);
            //流水号
            apiFile.setSerialNumber(serialNumber);
            //文件类型flag(查询api_fujiantype表)
            apiFile.setFlag(flagStr);
            //文件唯一unid
            apiFile.setUnid(uuid);
            apiFile.setFilePath(UUID.randomUUID().toString());
            //排序
            apiFile.setPaiXu(Integer.valueOf(paixu));
            //操作人
            apiFile.setCzr(request.getSession().getAttribute("userName").toString());
            //操作时间
            apiFile.setCreateDate(new java.util.Date(System.currentTimeMillis()));

            try {
                //添加数据
                apiFileService.addApiFile(apiFile);
                response.getWriter().write("上传成功！");
            } catch (IOException e) {
                e.printStackTrace();
                response.getWriter().write("上传失败！");
            }
        }
    }
%>
