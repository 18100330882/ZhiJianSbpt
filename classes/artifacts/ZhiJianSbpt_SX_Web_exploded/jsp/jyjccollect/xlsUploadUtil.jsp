<%@page import="cn.hutool.poi.excel.ExcelReader"%>
<%@page import="cn.hutool.poi.excel.ExcelUtil"%>
<%@page import="org.apache.commons.io.FileUtils"%>
<%@page import="cn.hutool.core.io.FileUtil"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="java.io.File"%>
<%@ page language="Java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>
<%@ page import="com.mysql.jdbc.StringUtils" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.*" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.SessionUtil" %>
<%@ page import="com.yongjie.ZhiJianSbpt.container.ServiceProvider" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.SysFinalRecource" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.DateUtils" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.UuidUtil" %>
<%@ page import="com.yongjie.ZhiJianSbpt.model.JgObject" %>
<%@ page import="com.yongjie.ZhiJianSbpt.model.AccountSbpt" %>
<%@ page import="com.yongjie.ZhiJianSbpt.service.jyjccj.*" %>
<%@ page import="com.yongjie.ZhiJianSbpt.service.ICloginService" %>
<%@ page import="com.yongjie.ZhiJianSbpt.service.ApiShiYsJyjcNlService" %>
<%@ page import="com.yongjie.ZhiJianSbpt.model.ApiShiYsJyjcNl" %>
<%@ page import="com.yongjie.ZhiJianSbpt.model.jyjccj.*" %>
<%
    //采集
    CollectService collectService = (CollectService) ServiceProvider
            .getService(CollectService.SERVICE_NAME);
    //能力
    ApiShiYsJyjcNlService apiShiYsJyjcNlService = (ApiShiYsJyjcNlService) ServiceProvider
            .getService(ApiShiYsJyjcNlService.SERVICE_NAME);
    //授权签字人
    JgObjectJyjcQzrService jgObjectJyjcQzrService = (JgObjectJyjcQzrService) ServiceProvider
            .getService(JgObjectJyjcQzrService.SERVICE_NAME);
    //检测人员
    JgObjectJyjcryService jgObjectJyjcryService = (JgObjectJyjcryService) ServiceProvider
            .getService(JgObjectJyjcryService.SERVICE_NAME);
    //机构信息
    JgobjectService jgobjectService = (JgobjectService) ServiceProvider
            .getService(JgobjectService.SERVICE_NAME);
    //登录
    ICloginService cloginService = (ICloginService) ServiceProvider
            .getService(ICloginService.SERVICE_NAME);
    //登录
    CollectFujianService collectFujianService = (CollectFujianService) ServiceProvider
            .getService(CollectFujianService.SERVICE_NAME);

    // 状态1、能力2、签字人3、人员
    String status = request.getParameter("status");
    String zsbh = request.getParameter("zsbh");
    String siteAddress = request.getParameter("siteAddress");
    String isSp = request.getParameter("isSp");

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

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
    //03Sax读取方式
    //Excel03SaxReader reader = ExcelUtil.read03BySax(saveDirectory + uuidPath, 1, r.c);
    //reader.read(2, reader.getRowCount());
    //07Sax读取方式
    String username = new SessionUtil().getUserName(request);
    Map result = new HashMap();
    CollectFujian collectFujian = new CollectFujian();
    collectFujian.setFileTitle(fileName);
    collectFujian.setFilePath(uuidPath);
    collectFujian.setSuffiName(ext);
    collectFujian.setZsbh(zsbh);
    collectFujian.setCzDate(new Date());
    collectFujian.setCzr(request.getSession().getAttribute("userName").toString());
    collectFujian.setState(status);
    collectFujianService.save(collectFujian);
    if (status.equals("1")) {
        // 从第三行到最后一行读取
        List<List<Object>> read = reader.read(1, reader.getRowCount());
        List<ApiShiYsJyjcNl> list = new ArrayList<ApiShiYsJyjcNl>();
        ApiShiYsJyjcNl tempJyjc = new ApiShiYsJyjcNl();//遍历每次完成，将数据存入临时对象中，如果后边有字段值为空，说明与之前的重复
        int count = 0;
//        jgObjectJyjcnlService.deleteJyjcnlById(Long.valueOf(id));
        List<Object> oneRowInfo = read.get(0);
        Object[] oneInfo = oneRowInfo.toArray();
        if (!oneInfo[1].toString().equals(zsbh)) {
            result.put("code", 1);
            result.put("message", "文件中证书编号与概况中不一致,请认真核对后重试");
            result.put("success", false);
            dir.delete();
            collectFujianService.delete(collectFujian.getId());
            String json = com.alibaba.fastjson.JSON.toJSONString(result);
            response.getWriter().write(json);
            return;
        }
        try {
            for (int j = 0; j < read.size(); j++) {//所有行记录
                if (j < 3) {
                    continue;
                }
                ApiShiYsJyjcNl apiShiYsJyjcNl = new ApiShiYsJyjcNl();
                Object[] objArr = read.get(j).toArray();
                Object column0 = objArr[0];
                Object column1 = objArr[1];
                Object column2 = objArr[2];
                Object column3 = objArr[3];
                Object column4 = objArr[4];
                Object column5 = objArr[5];
                Object column6 = objArr[6];
                Object column7 = objArr[7];


                if (column0.toString().contains("注：")) {
                    continue;
                }

                // 确认 一级二级三级四级分类
                if (!StringUtil.isNullOrEmpty(column0) && !StringUtil.isNullOrEmpty(column1) && StringUtil.isNullOrEmpty(column2) && StringUtil.isNullOrEmpty(column3)
                        && StringUtil.isNullOrEmpty(column4) && StringUtil.isNullOrEmpty(column5) && StringUtil.isNullOrEmpty(column6)) {
                    tempJyjc.setYjfl(column1 + "");
                    tempJyjc.setBigNumber(column0 + "");
                    continue;
                }

                if (!StringUtil.isNullOrEmpty(column0) && !StringUtil.isNullOrEmpty(column1)) {
                    tempJyjc.setEjfl(column1 + "");
                    tempJyjc.setSortingNumber(String.valueOf(column0));
                }

                if (!StringUtil.isNullOrEmpty(column2) && !StringUtil.isNullOrEmpty(column3)) {
                    tempJyjc.setProductName(column3 + "");
                    tempJyjc.setSortNumber(String.valueOf(column2));
                }

                if (!StringUtil.isNullOrEmpty(column4)) {
                    tempJyjc.setYjbz(column4 + "");
                }
                if (!StringUtil.isNullOrEmpty(column7)) {
                    Date pizhun = sdf.parse(sdf.format(column7));
                    tempJyjc.setPizhunDate(pizhun);
                }
                Map parMap = new HashMap();
                parMap.put("zsbh", zsbh);
                Map<String, Object> map = collectService.queryByZsbh(parMap);
                System.out.println(map);
                //备注 第一列不为空其他都为空
                if (!StringUtil.isNullOrEmpty(column0) && StringUtil.isNullOrEmpty(column1) && StringUtil.isNullOrEmpty(column2) && StringUtil.isNullOrEmpty(column3)
                        && StringUtil.isNullOrEmpty(column4) && StringUtil.isNullOrEmpty(column5) && StringUtil.isNullOrEmpty(column6)
                        && !column0.toString().contains("检验检测能力”应依据国家、行业、地方、国际、区域标准。依据其他标准或方法的，应在“说明”中注明")) {
                    apiShiYsJyjcNl.setCreateDate(DateUtils.parse(DateUtils.format(new Date())));
                    apiShiYsJyjcNl.setIsSp(isSp);
                    apiShiYsJyjcNl.setFlag("0");
                    apiShiYsJyjcNl.setGsmc(String.valueOf(map.get("JGOBJECTNAME")));
                    apiShiYsJyjcNl.setZsbh(oneInfo[1].toString());
                    apiShiYsJyjcNl.setSiteName(oneInfo[3].toString());
                    apiShiYsJyjcNl.setPizhunDate(tempJyjc.getPizhunDate());
                    apiShiYsJyjcNl.setCollectFujianId(collectFujian.getId());
                    apiShiYsJyjcNl.setRemark(column0.toString());
                    list.add(apiShiYsJyjcNl);
                    continue;
                }
                //公共属性
//                apiShiYsJyjcNl.setCid(Long.valueOf(id));
                if (StringUtil.isNullOrEmpty(tempJyjc.getYjfl())) {
                    //如果一级分类为空把二级分类赋值给一级分类
                    apiShiYsJyjcNl.setYjfl(tempJyjc.getEjfl());
                } else {
                    apiShiYsJyjcNl.setYjfl(tempJyjc.getYjfl());
                }
                apiShiYsJyjcNl.setEjfl(tempJyjc.getEjfl());
                apiShiYsJyjcNl.setBigNumber(tempJyjc.getBigNumber());
                apiShiYsJyjcNl.setProductName(tempJyjc.getProductName());
                apiShiYsJyjcNl.setSortNumber(tempJyjc.getSortNumber());
                apiShiYsJyjcNl.setInstructions(column5.toString());
                apiShiYsJyjcNl.setLimits(column6.toString());
                apiShiYsJyjcNl.setPizhunDate(tempJyjc.getPizhunDate());
                apiShiYsJyjcNl.setYjbz(tempJyjc.getYjbz());
                apiShiYsJyjcNl.setSortingNumber(tempJyjc.getSortingNumber());
                apiShiYsJyjcNl.setFlag("0");
                apiShiYsJyjcNl.setGsmc(String.valueOf(map.get("JGOBJECTNAME")));
                apiShiYsJyjcNl.setCollectFujianId(collectFujian.getId());
                apiShiYsJyjcNl.setIsSp(isSp);
                apiShiYsJyjcNl.setZsbh(oneInfo[1].toString());
                apiShiYsJyjcNl.setSiteName(oneInfo[3].toString());
                apiShiYsJyjcNl.setCreateDate(DateUtils.parse(DateUtils.format(new Date())));
                list.add(apiShiYsJyjcNl);

            }

            if (list.size() > 0) {
                try {
                    apiShiYsJyjcNlService.saveBatch(list, list.size());
                    result.put("code", 0);
                    result.put("message", "成功");
                    result.put("success", false);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                result.put("code", 1);
                result.put("message", "文件格式有误,保存失败");
                result.put("success", false);
                String json = com.alibaba.fastjson.JSON.toJSONString(result);
                response.getWriter().write(json);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", 1);
            result.put("message", "第" + (count + 2) + "行有错误,请修改后重试");
            result.put("success", false);
        }
    } else if (status.equals("2")) {
        List<List<Object>> read = reader.read(2, reader.getRowCount());
        List<CjSqqzr> list = new ArrayList<CjSqqzr>();
        int count = 0;
//        jgObjectJyjcQzrService.deleteJyjcQzrById(Long.valueOf(id));
        try {
            for (int j = 0; j < read.size(); j++) {//所有行记录
                System.out.println(read.size());
                Object[] objArr = read.get(j).toArray();//单行记录：[一, 类别1, , , , , ] / [1, b, 产品1, 参数1, 编号1, 范围, 说明1] / [, , 产品2, 参数1, , , ],
                count = j;
                CjSqqzr cjSqqzr = new CjSqqzr();//实体类，填充数据
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
                Object columnA = objArr[10];

                if (column0.toString().contains("注：")) {
                    continue;
                }

                if (StringUtil.isNullOrEmpty(column0) || StringUtil.isNullOrEmpty(column1) || StringUtil.isNullOrEmpty(column2) || StringUtil.isNullOrEmpty(column3)
                        || StringUtil.isNullOrEmpty(column4) || StringUtil.isNullOrEmpty(column5) || StringUtil.isNullOrEmpty(column6) || StringUtil.isNullOrEmpty(column8)
                        || StringUtil.isNullOrEmpty(column9) || StringUtil.isNullOrEmpty(columnA)) {
                    response.getWriter().write(R.error("表中有信息未填，请完善信息！"));
                    return;
                }

                cjSqqzr.setQzrName(StringUtil.isNullOrEmpty(column0) ? null : column0 + "");
                cjSqqzr.setSex(StringUtil.isNullOrEmpty(column1) ? null : column1 + "");
                cjSqqzr.setEducation(StringUtil.isNullOrEmpty(column2) ? null : column2 + "");
                cjSqqzr.setZhic(StringUtil.isNullOrEmpty(column3) ? null : column3 + "");
                cjSqqzr.setZhiw(StringUtil.isNullOrEmpty(column4) ? null : column4 + "");
                cjSqqzr.setDept(StringUtil.isNullOrEmpty(column5) ? null : column5 + "");
                cjSqqzr.setTelephone(StringUtil.isNullOrEmpty(column6) ? null : column6 + "");
                cjSqqzr.setEmail(StringUtil.isNullOrEmpty(column7) ? null : column7 + "");
                cjSqqzr.setQzly(StringUtil.isNullOrEmpty(column8) ? null : column8 + "");
                cjSqqzr.setSfzh(StringUtil.isNullOrEmpty(column9) ? null : column9 + "");
                cjSqqzr.setPizhunDate(StringUtil.isNullOrEmpty(columnA) ? null : sdf.parse(sdf.format(columnA)));
                cjSqqzr.setZsbh(zsbh);
                cjSqqzr.setCzr(request.getSession().getAttribute("userName").toString());
                cjSqqzr.setCzdate(new Date());
                cjSqqzr.setCollectFujianId(collectFujian.getId());
                cjSqqzr.setIsSp(isSp);
                cjSqqzr.setCddz(siteAddress);
                list.add(cjSqqzr);

            }
            try {
                if (list != null && list.size() > 0) {
                    jgObjectJyjcQzrService.saveBatch(list, list.size());
                    result.put("code", 0);
                    result.put("message", "成功");
                    result.put("success", false);
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }


        } catch (Exception e) {
            e.printStackTrace();
            dir.delete();
            collectFujianService.delete(collectFujian.getId());
            result.put("code", 1);
            result.put("message", "第" + (count + 2) + "行有错误,请修改后重试");
            result.put("success", false);
        }
    } else if (status.equals("3")) {
        List<List<Object>> read = reader.read(2, reader.getRowCount());
        List<JgobjectJyjcRy> list = new ArrayList<JgobjectJyjcRy>();
        int count = 0;
        try {
            for (int j = 0; j < read.size(); j++) {//所有行记录

                System.out.println(read.size());
                Object[] objArr = read.get(j).toArray();//单行记录：[一, 类别1, , , , , ] / [1, b, 产品1, 参数1, 编号1, 范围, 说明1] / [, , 产品2, 参数1, , , ],
                count = j;
                JgobjectJyjcRy jgobjectJyjcRy = new JgobjectJyjcRy();//实体类，填充数据

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
                Object columnA = objArr[10];

                if (column0.toString().contains("注：")) {
                    continue;
                }

                if (StringUtil.isNullOrEmpty(column0) || StringUtil.isNullOrEmpty(column1) || StringUtil.isNullOrEmpty(column2) || StringUtil.isNullOrEmpty(column3)
                        || StringUtil.isNullOrEmpty(column4) || StringUtil.isNullOrEmpty(column5) || StringUtil.isNullOrEmpty(column6) || StringUtil.isNullOrEmpty(column8)
                        || StringUtil.isNullOrEmpty(column9) || StringUtil.isNullOrEmpty(columnA)) {
                    response.getWriter().write(R.error("表中有信息未填，请完善信息！"));
                    return;
                }

                jgobjectJyjcRy.setName(StringUtil.isNullOrEmpty(column0) ? null : column0 + "");
                jgobjectJyjcRy.setSex(StringUtil.isNullOrEmpty(column1) ? null : column1 + "");
                jgobjectJyjcRy.setAge(StringUtil.isNullOrEmpty(column2) ? null : Integer.valueOf(column2 + ""));
                jgobjectJyjcRy.setEducation(StringUtil.isNullOrEmpty(column3) ? null : column3 + "");
                jgobjectJyjcRy.setZhiw(StringUtil.isNullOrEmpty(column4) ? null : column4 + "");
                jgobjectJyjcRy.setZhic(StringUtil.isNullOrEmpty(column5) ? null : column5 + "");
                jgobjectJyjcRy.setZhuanye(StringUtil.isNullOrEmpty(column6) ? null : column6 + "");
                jgobjectJyjcRy.setYearOfService(StringUtil.isNullOrEmpty(column7) ? null : column7 + "");
                jgobjectJyjcRy.setDept(StringUtil.isNullOrEmpty(column8) ? null : column8 + "");
                jgobjectJyjcRy.setCardType(StringUtil.isNullOrEmpty(column9) ? null : column9 + "");
                jgobjectJyjcRy.setSfzh(StringUtil.isNullOrEmpty(columnA) ? null : columnA + "");
                jgobjectJyjcRy.setZsbh(zsbh);
                jgobjectJyjcRy.setCollectFujianId(collectFujian.getId());
                list.add(jgobjectJyjcRy);
            }
            try {
                if (list != null && list.size() > 0) {
                    jgObjectJyjcryService.saveBatch(list, list.size());
                    result.put("code", 0);
                    result.put("message", "成功");
                    result.put("success", false);
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }


        } catch (Exception e) {
            e.printStackTrace();
            dir.delete();
            collectFujianService.delete(collectFujian.getId());
            result.put("code", 1);
            result.put("message", "第" + (count + 2) + "行有错误,请修改后重试");
            result.put("success", false);
        }
    } else if (status.equals("4")) {
        List<List<Object>> read = reader.read(2, reader.getRowCount());
        List<JgObject> list = new ArrayList<JgObject>();
        int count = 0;
        // jgObjectJyjcryService.deleteJyjcRyById(Long.valueOf(id));
        try {
            for (int j = 0; j < read.size(); j++) {//所有行记录

                System.out.println(read.size());
                Object[] objArr = read.get(j).toArray();//单行记录：[一, 类别1, , , , , ] / [1, b, 产品1, 参数1, 编号1, 范围, 说明1] / [, , 产品2, 参数1, , , ],
                count = j;
                JgObject jgObject = new JgObject();//实体类，填充数据

                jgObject.setJgObjectName(objArr[0] == null ? "" : objArr[0] + "");
                jgObject.setShxydm(objArr[1] == null ? "" : objArr[1] + "");

                //证书编号
                jgObject.setZzrdzsbh(objArr[2] == null ? "" : objArr[2] + "");
                //法人
                jgObject.setFddbr(objArr[3] == null ? "" : objArr[3] + "");
                //联系人
                jgObject.setLxr(objArr[4] == null ? "" : objArr[4] + "");
                //联系电话
                jgObject.setLxdh(objArr[5] == null ? "" : objArr[5] + "");
                //地址
                jgObject.setZhusuo(objArr[6] == null ? "" : objArr[6] + "");
                //邮箱
                jgObject.setEmail(objArr[7] == null ? "" : objArr[7] + "");

                String userName = request.getSession().getAttribute("userName").toString();
                AccountSbpt accountSbpt = cloginService.getAccountSbptByUserName(userName);
                jgObject.setAreaId(Long.valueOf(accountSbpt.getAreaId()));
                jgObject.setIsDel("0");
                list.add(jgObject);
            }

            // 如果大于1000则进行保存
            if (list.size() >= 1000) {
                try {
                    jgobjectService.saveBatch(list, 1000);

                } catch (Exception e) {

                } finally {
                    list.clear();// 清空集合,释放内存
                }
            }

            if (!StringUtil.isEmpty(list + "") && list.size() > 0) {
                try {
                    jgobjectService.saveBatch(list, 100);
                } catch (Exception e) {

                } finally {
                    list.clear();// 清空集合,释放内存
                }
            }
            result.put("code", 0);
            result.put("message", "成功");
            result.put("success", false);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", 1);
            result.put("message", "第" + (count + 2) + "行有错误,请修改后重试");
            result.put("success", false);
        }
    }
    String json = com.alibaba.fastjson.JSON.toJSONString(result);
    response.getWriter().write(json);

%>
