<%@page import="cn.hutool.poi.excel.ExcelReader" %>
<%@page import="cn.hutool.poi.excel.ExcelUtil" %>
<%@page import="org.apache.commons.io.FileUtils" %>
<%@page import="cn.hutool.core.io.FileUtil" %>
<%@page import="com.oreilly.servlet.MultipartRequest" %>
<%@page import="java.io.File" %>
<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ page import="com.yongjie.ZhiJianSbpt.service.ApiShiYsJyjcCdService" %>
<%@ page import="com.yongjie.ZhiJianSbpt.container.ServiceProvider" %>
<%@ page import="com.yongjie.ZhiJianSbpt.service.ShiYsJyjcXchcPsbgNlHzService" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.*" %>
<%@ page import="com.yongjie.ZhiJianSbpt.service.ApiShiYsJyjcYqsbSbptService" %>
<%@ page import="com.yongjie.ZhiJianSbpt.model.*" %>
<%
    //检验检测能力
    ShiYsJyjcXchcPsbgNlHzService nlhzService = (ShiYsJyjcXchcPsbgNlHzService) ServiceProvider.getService(ShiYsJyjcXchcPsbgNlHzService.SERVICE_NAME);
    ApiShiYsJyjcCdService cdService = (ApiShiYsJyjcCdService) ServiceProvider.getService(ApiShiYsJyjcCdService.SERVICE_NAME);
    ApiShiYsJyjcYqsbSbptService nlService = (ApiShiYsJyjcYqsbSbptService) ServiceProvider.getService(ApiShiYsJyjcYqsbSbptService.SERVICE_NAME);

    String flowId = request.getParameter("flowId");
    String flowInstId = request.getParameter("flowInstId");
    String isSp = request.getParameter("isSp");
    String type = request.getParameter("type");
    String isSpText = "";
    int spType = 0;
    if ("1".equals(isSp)) {
        isSpText = "食品";
        spType = 1;
    } else if ("2".equals(isSp)) {
        isSpText = "非食品";
        spType = 4;
    }

    String serialNumber = request.getParameter("serialNumber");

    //场地
    String cdId = request.getParameter("cdId");
    ApiShiYsJyjcCd cdEntity = cdService.queryById(Long.parseLong(cdId));
    String cddz = cdEntity.getSiteAddress();
    // 文件存储路径
    String saveDirectory = SysFinalRecource.SECOND_LEVEL_DIRECTORY;
    String yyyyMMdd = DateUtils.format();

    String filePath = saveDirectory + yyyyMMdd;

    File dir = new File(filePath);
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

    //普通读取方式     这里从上传文件里面读取数据,存储到数据库中
    ExcelReader reader = ExcelUtil.getReader(FileUtil.file(saveDirectory + uuidPath));
    // 从第几行 开始 读取
    List<List<Object>> read = reader.read(4, reader.getRowCount());
    System.out.println(read);

    List<ApiShiYsJyjcYqsbSbpt> nlList = new ArrayList<ApiShiYsJyjcYqsbSbpt>();

    ApiShiYsJyjcYqsbSbpt upEntity = new ApiShiYsJyjcYqsbSbpt();//遍历每次完成，将数据存入临时对象中，如果后边有字段值为空，说明与之前的重复
    for (int j = 0; j < read.size(); j++) {//所有行记录
        ApiShiYsJyjcYqsbSbpt entity = new ApiShiYsJyjcYqsbSbpt();
        System.out.println(read.get(j));
        Object[] objArr = read.get(j).toArray();
        //第一列:类别(产品/项目/参数)
        Object column0 = objArr[0];
        Object column1 = objArr[1];
        Object column2 = objArr[2];
        Object column3 = objArr[3];
        Object column4 = objArr[4];
        Object column5 = objArr[5];
        Object column6 = objArr[6];
        Object column7 = objArr[7];
        Object column8 = objArr[8];
        Object column9 = objArr[9];
        Object column10 = objArr[10];
        if (!StringUtil.isNullOrEmpty(column0) && column0.toString().contains("注：")) {
            upEntity = new ApiShiYsJyjcYqsbSbpt();
            entity.setRemark(column0.toString());
            entity.setEjfl("此为备注");
        }
        if (!StringUtil.isNullOrEmpty(column1)) {
            entity.setEjfl(String.valueOf(column1));
        } else {
            if (!StringUtil.isNullOrEmpty(upEntity.getEjfl())) {
                entity.setEjfl(upEntity.getEjfl());
            }
        }

        if (!StringUtil.isNullOrEmpty(column2)) {
            entity.setSortingNumber(String.valueOf(column2));
        } else {
            if (!StringUtil.isNullOrEmpty(upEntity.getSortingNumber())) {
                entity.setSortingNumber(upEntity.getSortingNumber());
            }
        }

        if (!StringUtil.isNullOrEmpty(column3)) {
            entity.setProductName(String.valueOf(column3));
        } else {
            if (!StringUtil.isNullOrEmpty(upEntity.getProductName())) {
                entity.setProductName(upEntity.getProductName());
            }
        }

        if (!StringUtil.isNullOrEmpty(column4)) {
            entity.setYjbz((String) column4);
        } else {
            if (!StringUtil.isNullOrEmpty(upEntity.getYjbz())) {
                entity.setYjbz(upEntity.getYjbz());
            }
        }

        if (!StringUtil.isNullOrEmpty(column5)) {
            entity.setBzwzName((String) column5);
        } else {
            if (!StringUtil.isNullOrEmpty(upEntity.getBzwzName())) {
                entity.setBzwzName(upEntity.getBzwzName());
            }
        }

        if (!StringUtil.isNullOrEmpty(column6)) {
            entity.setIdentifier((String) column6);
        } else {
            if (!StringUtil.isNullOrEmpty(upEntity.getIdentifier())) {
                entity.setIdentifier(upEntity.getIdentifier());
            }
        }

        if (!StringUtil.isNullOrEmpty(column7)) {
            entity.setBzwzClfw((String) column7);
        } else {
            if (!StringUtil.isNullOrEmpty(upEntity.getBzwzClfw())) {
                entity.setBzwzClfw(upEntity.getBzwzClfw());
            }
        }

        if (!StringUtil.isNullOrEmpty(column8)) {
            entity.setSyfs((String) column8);
        } else {
            if (!StringUtil.isNullOrEmpty(upEntity.getSyfs())) {
                entity.setSyfs(upEntity.getSyfs());
            }
        }

        if (!StringUtil.isNullOrEmpty(column9)) {
            entity.setYqsbYxrq((Date) column9);
        } else {
            if (!StringUtil.isNullOrEmpty(upEntity.getYqsbYxrq())) {
                entity.setYqsbYxrq(upEntity.getYqsbYxrq());
            }
        }

        if (!StringUtil.isNullOrEmpty(column10)) {
            entity.setQrjg((String) column10);
        } else {
            if (!StringUtil.isNullOrEmpty(upEntity.getQrjg())) {
                entity.setQrjg(upEntity.getQrjg());
            }
        }

        //公共属性
        entity.setStatus("0");
        entity.setCreateDate(new Date());
        entity.setUpdateDate(new Date());
        entity.setSerialNumber(serialNumber);
        entity.setSiteName(cddz);// 地址
        entity.setFlag(type);
        entity.setTypeName(isSpText);
        if (!StringUtil.isEmpty(entity.getEjfl())) {
            nlList.add(entity);
        }
        upEntity = entity;
    }

    nlService.saveBatch(nlList, nlList.size());

    //保存附件信息
    ArrayList listPsbgNl = nlhzService.getListByFlowInstId(serialNumber, cdId, "", String.valueOf(spType), type, isSpText);
    String[] subFileName = fileName.split("\\.");
    if (listPsbgNl.size() > 0) {
        HashMap mapPsbgNl = (HashMap) listPsbgNl.get(0);
        ShiYsJyjcXchcPsbgNlHz entity = (ShiYsJyjcXchcPsbgNlHz) HashmapAndEntityTransfer.hashmapTransferToEntity2(new ShiYsJyjcXchcPsbgNlHz(), mapPsbgNl);
        //删除原文件
        String oldFilePath = entity.getFilePathXchc();
        String allFilepath = saveDirectory + oldFilePath;
        File fileOld = new File(allFilepath);
        fileOld.delete();
        //保存
        entity.setFilePathXchc(uuidPath);
        entity.setFileSize(fileSize + "");
        if (!StringUtil.isNullOrEmpty(ext)) {
            entity.setFileExtension(ext.substring(1));
        }
        entity.setFileTitle(subFileName[0]);
        entity.setCzr(request.getSession().getAttribute("userName").toString());
        entity.setCzDate(new Date(System.currentTimeMillis()));
        nlhzService.edit(entity);
    } else {
        ShiYsJyjcXchcPsbgNlHz entity = new ShiYsJyjcXchcPsbgNlHz();
        entity.setCalOrCma("");
        entity.setCddz(cddz);
        entity.setCdId(cdId);
        entity.setFilePathXchc(uuidPath);
        entity.setFileSize(fileSize + "");
        if (!StringUtil.isNullOrEmpty(ext)) {
            entity.setFileExtension(ext.substring(1));
        }

        entity.setFileTitle(subFileName[0]);
        entity.setCzr(request.getSession().getAttribute("userName").toString());
        entity.setCzDate(new Date(System.currentTimeMillis()));
        entity.setFlowId(Long.parseLong(flowId));
        entity.setFlowInstId(Long.parseLong(flowInstId));
        entity.setSerialNumber(serialNumber);
        entity.setIsSp(isSpText);
        entity.setType(spType);
        entity.setComeFrom("qysb");
        entity.setNlOrSb(Integer.parseInt(type));
        nlhzService.add(entity);
    }

    Map result = new HashMap();
    result.put("code", 0);
    result.put("message", "成功");
    result.put("success", false);
    String json = com.alibaba.fastjson.JSON.toJSONString(result);
    response.getWriter().write(json);
%>
