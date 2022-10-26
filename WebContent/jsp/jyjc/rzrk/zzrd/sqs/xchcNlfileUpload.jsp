<%@page import="cn.hutool.poi.excel.ExcelReader" %>
<%@page import="cn.hutool.poi.excel.ExcelUtil" %>
<%@page import="org.apache.commons.io.FileUtils" %>
<%@page import="cn.hutool.core.io.FileUtil" %>
<%@page import="com.oreilly.servlet.MultipartRequest" %>
<%@page import="java.io.File" %>
<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ page import="com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcCd" %>
<%@ page import="com.yongjie.ZhiJianSbpt.service.ApiShiYsJyjcCdService" %>
<%@ page import="com.yongjie.ZhiJianSbpt.container.ServiceProvider" %>
<%@ page import="com.yongjie.ZhiJianSbpt.service.ShiYsJyjcXchcPsbgNlHzService" %>
<%@ page import="com.yongjie.ZhiJianSbpt.model.ShiYsJyjcXchcPsbgNlHz" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.*" %>
<%@ page import="com.yongjie.ZhiJianSbpt.service.ApiShiYsJyjcNlSbptService" %>
<%@ page import="com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcNlSbpt" %>
<%
    //检验检测能力
    ShiYsJyjcXchcPsbgNlHzService nlhzService = (ShiYsJyjcXchcPsbgNlHzService) ServiceProvider.getService(ShiYsJyjcXchcPsbgNlHzService.SERVICE_NAME);
    ApiShiYsJyjcCdService cdService = (ApiShiYsJyjcCdService) ServiceProvider.getService(ApiShiYsJyjcCdService.SERVICE_NAME);
    ApiShiYsJyjcNlSbptService nlService = (ApiShiYsJyjcNlSbptService) ServiceProvider.getService(ApiShiYsJyjcNlSbptService.SERVICE_NAME);

    String flowId = request.getParameter("flowId");
    String flowInstId = request.getParameter("flowInstId");
    String isSp = request.getParameter("isSp");
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
    String type = request.getParameter("type");
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
    System.out.println(reader);
    // 从第三行到最后一行读取
    List<List<Object>> read = reader.read(2, reader.getRowCount());
    System.out.println(read);

    Date date = new Date();//日期
    List<ApiShiYsJyjcNlSbpt> nlList = new ArrayList<ApiShiYsJyjcNlSbpt>();

    ApiShiYsJyjcNlSbpt tempJyjc = new ApiShiYsJyjcNlSbpt();//遍历每次完成，将数据存入临时对象中，如果后边有字段值为空，说明与之前的重复
    int count = 0;
    String bigLeiXh = "";//记录上一个大类别序号
    for (int j = 0; j < read.size(); j++) {//所有行记录

        System.out.println(read.get(j));
        Object[] objArr = read.get(j).toArray();//单行记录：[一, 类别1, , , , , ] / [1, b, 产品1, 参数1, 编号1, 范围, 说明1] / [, , 产品2, 参数1, , , ],
        Object firstColumn = objArr[0];//第一列:一二三四、1234、""
        count = j + 3;
        ApiShiYsJyjcNlSbpt nlModel = new ApiShiYsJyjcNlSbpt();//实体类，填充数据


        //大类别、小类别处理
        if (StringUtil.isEmpty(firstColumn + "")) {//第一列为空，说明进行过合并，同时也不是第一列
            nlModel.setYjfl(tempJyjc.getYjfl());    // 大类别名称
            if (tempJyjc.getEjfl() != null && (!"".equals(tempJyjc.getEjfl()))) {//有小类别
                if (StringUtil.isEmpty(objArr[1] + "")) {//这次第二列为空，说明和上边的合并了，获取临时变量的值
                    nlModel.setEjfl(tempJyjc.getEjfl() == null ? "" : tempJyjc.getEjfl());
                } else {//这次第二列不为空，写自己的数据
                    nlModel.setEjfl(objArr[1] + "");
                }
            }
        } else {//第一列不为空
            if (!firstColumn.toString().matches("[0-9]+")) {//一二三四
                nlModel.setYjfl(objArr[1] + "");//此大类别
                if ((!StringUtil.isNullOrEmpty(objArr[2]) || !StringUtil.isNullOrEmpty(objArr[3]) || !StringUtil.isNullOrEmpty(objArr[4])) && bigLeiXh.equals(objArr[0])) {
                    Map result = new HashMap();
                    result.put("code", 1);
                    result.put("message", "请注意第" + count + "行序号需要使用阿拉伯数字,请修改后重试");
                    result.put("success", false);
                    String json = com.alibaba.fastjson.JSON.toJSONString(result);
                    response.getWriter().write(json);
                    return;
                }

                if (StringUtil.isNullOrEmpty(objArr[2]) && StringUtil.isNullOrEmpty(objArr[3]) && StringUtil.isNullOrEmpty(objArr[4])) {
                    bigLeiXh = (String) objArr[0];
                }
            } else if (firstColumn.toString().matches("[0-9]+")) {//1234，大类别行下边的数据列表，第二列为小类别
                String yjfl = tempJyjc.getYjfl();
                if (StringUtil.isNullOrEmpty(yjfl)) {//如果没有大类别 把小类别赋值给大类别
                    yjfl = objArr[1] + "";
                }
                nlModel.setYjfl(yjfl);//之前存入的大类别
                nlModel.setEjfl(objArr[1] + "");//此小类别
            }
            if (count != 3) {
                if (objArr[1].toString().equals("") && tempJyjc.getYjfl().equals("")) {
                    Map result = new HashMap();
                    result.put("code", 1);
                    result.put("message", "第" + count + "行类别不可为空,请修改后重试");
                    result.put("success", false);
                    String json = com.alibaba.fastjson.JSON.toJSONString(result);
                    response.getWriter().write(json);
                    return;
                }
            }

            //3-7列都为空值，说明是大类别行 ？  只将大小类别写到临时对象中，不存于数据库
            if ("".equals(objArr[2] + "") && "".equals(objArr[3] + "") && "".equals(objArr[4] + "") && "".equals(objArr[5] + "") && "".equals(objArr[6] + "")) {
                //2-7列都为空值，说明是下方额外内容
                if ("".equals(objArr[1] + "")) {
                    nlModel.setCreateDate(date);    // 日期
                    nlModel.setSerialNumber(serialNumber); // 流水号
                    nlModel.setSiteName(cddz);// 地址
                    nlModel.setStatus("0");
                    nlModel.setFlag("0");
                    nlModel.setTypeName(isSpText);
                    if (!firstColumn.toString().contains("注： ①“检验检测能力”应依据国家、行业、地方、国际、区域标准。依据其他标准或方法的，应在“说明”中注明")) {
                        nlModel.setRemark(firstColumn.toString());
                        nlList.add(nlModel);
                    }
                } else {
                    tempJyjc = nlModel;
                }
                continue;
            }
        }

        //其余字段处理：如果值为空，说明与之前的合并了，获取临时变量的值；如果值不为空，直接写入
        if (StringUtil.isEmpty(objArr[2] + "")) {
            nlModel.setSortingNumber(tempJyjc.getSortingNumber());
        } else {
            String xh = objArr[2].toString();
            nlModel.setSortingNumber(xh);
        }
        System.out.println("序号         " + objArr[2] + "");

        if (count != 3) {
            if (objArr[2].toString().equals("") && tempJyjc.getSortingNumber().equals("")) {
                Map result = new HashMap();
                result.put("code", 1);
                result.put("message", "第" + count + "行序号不可为空,请修改后重试");
                result.put("success", false);
                String json = com.alibaba.fastjson.JSON.toJSONString(result);
                response.getWriter().write(json);
                return;
            }
        }

        if (StringUtil.isEmpty(objArr[3] + "")) {
            nlModel.setSanjfl(tempJyjc.getSanjfl());
        } else {
            nlModel.setSanjfl(objArr[3] + "");
        }
        System.out.println("产品名称" + objArr[3] + "");
        if (count != 3) {
            if (objArr[3].toString().equals("") && tempJyjc.getSanjfl().equals("")) {
                Map result = new HashMap();
                result.put("code", 1);
                result.put("message", "第" + count + "行产品名称不可为空,请修改后重试");
                result.put("success", false);
                String json = com.alibaba.fastjson.JSON.toJSONString(result);
                response.getWriter().write(json);
                return;
            }
        }

        if (StringUtil.isEmpty(objArr[4] + "") && (!StringUtil.isEmpty(tempJyjc.getYjbz())) && (!StringUtil.isEmpty(nlModel.getYjbz()))) {
            if (nlModel.getYjbz().equals(tempJyjc.getYjbz())) {//此类别与上一个类别相同，说明是上下合并
                nlModel.setYjbz(tempJyjc.getYjbz());
            } else {
                nlModel.setYjbz(objArr[4] + "");
            }
        } else {
            nlModel.setYjbz(objArr[4] + "");
        }

        //范围
        if (StringUtil.isEmpty(objArr[5] + "")) {
            nlModel.setLimits("");
        } else {
            nlModel.setLimits(objArr[5] + "");
        }

        //说明
        if (StringUtil.isEmpty(objArr[6] + "")) {
            nlModel.setInstructions("");
        } else {
            nlModel.setInstructions(objArr[6] + "");
        }

        //公共属性
        nlModel.setCreateDate(date);    // 日期
        nlModel.setSerialNumber(serialNumber); // 流水号
        nlModel.setSiteName(cddz);// 地址
        nlModel.setStatus("0");
        nlModel.setFlag(type);
        nlModel.setTypeName(isSpText);

        if (!StringUtil.isEmpty(nlModel.getYjfl())) {
            nlList.add(nlModel);
        }
        tempJyjc = nlModel;//此次生成的对象存入临时对象中
    }

    // 如果大于1000则进行保存
    if (nlList.size() >= 1000) {
        try {
            nlService.saveBatch(nlList, 1000);
        } catch (Exception e) {

        } finally {
            nlList.clear();// 清空集合,释放内存
        }
    } else if (!StringUtil.isEmpty(nlList) && nlList.size() > 0) {
        try {
            nlService.saveBatch(nlList, 100);
        } catch (Exception e) {

        } finally {
            nlList.clear();// 清空集合,释放内存
        }
    } else {
        Map result = new HashMap();
        result.put("code", 1);
        result.put("message", "文件格式有误,保存失败");
        result.put("success", false);
        String json = com.alibaba.fastjson.JSON.toJSONString(result);
        response.getWriter().write(json);
        return;
    }

    //保存附件信息
    ArrayList listPsbgNl = nlhzService.getListByFlowInstId(serialNumber, cdId, "", String.valueOf(spType), "0", isSpText);
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
        entity.setComeFrom("qysb");
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
        entity.setNlOrSb(Integer.parseInt(type));
        entity.setComeFrom("qysb");
        try {
            nlhzService.add(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Map result = new HashMap();
    result.put("code", 0);
    result.put("message", "成功");
    result.put("success", false);
    String json = com.alibaba.fastjson.JSON.toJSONString(result);
    response.getWriter().write(json);
%>
