<%@ page language="Java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         import="java.util.*,
                 java.lang.reflect.*" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.JSON" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.StringUtil" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.SysFinalRecource" %>
<%@ page import="com.yongjie.ZhiJianSbpt.container.ServiceProvider" %>
<%@ page import="com.yongjie.ZhiJianSbpt.service.flowBase.IYjFlowFuJianListService" %>
<%@ page import="com.yongjie.ZhiJianSbpt.service.flowBase.IYjFlowInstWenShuService" %>
<%@ page import="java.io.File" %>
<%@ page import="com.yongjie.ZhiJianSbpt.service.shiYsJyjc.ApiFileService" %>
<%@ page import="com.yongjie.ZhiJianSbpt.model.flowBase.ApiFile" %>
<%
    //设置响应编码和请求编码为UTF-8
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    String methodName = request.getParameter("method");
    try {
        Class[] argsClass = new Class[2];
        argsClass[0] = HttpServletRequest.class;
        argsClass[1] = HttpServletResponse.class;
        Class cls = this.getClass();
        Method method = cls.getMethod(methodName, argsClass);
        Object[] args = new Object[2];
        args[0] = request;
        args[1] = response;
        BeforeInvoke(methodName);
        method.invoke(this, args);
    } catch (Exception e) {
        //封装hashmap对象
        HashMap result = new HashMap();
        result.put("error", -1);
        result.put("message", e.getMessage());
        result.put("stackTrace", e.getStackTrace());
        //将hashmap对象封装成json
        String json = JSON.Encode(result);
        response.reset();
        response.getWriter().write(json);
    } finally {
        AfterInvoke(methodName);
    }
%>
<%!
    //检验检测流程service
    IYjFlowFuJianListService fuJianService = (IYjFlowFuJianListService) ServiceProvider
            .getService(IYjFlowFuJianListService.SERVICE_NAME);
    IYjFlowInstWenShuService yjWenShuService = (IYjFlowInstWenShuService) ServiceProvider
            .getService(IYjFlowInstWenShuService.SERVICE_NAME);
    ApiFileService apiFileService = (ApiFileService) ServiceProvider
            .getService(ApiFileService.SERVICE_NAME);

    //权限管理
    protected void BeforeInvoke(String methodName) {
    }

    protected void AfterInvoke(String methodName) {
    }

    //加载数据列表
    public void getFuJianJyjcXchc(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //查询条件
        String flowIdStr = request.getParameter("flowId") == null ? "" : request.getParameter("flowId");//从控件传的值，若没有则为null
        String serialNumber = request.getParameter("serialNumber") == null ? "" : request.getParameter("serialNumber");
        String sqsType = request.getParameter("sqsType") == null ? "" : request.getParameter("sqsType");
        long flowId = 0l;
        if (!StringUtil.isNullOrEmpty(flowIdStr)) {
            flowId = Long.parseLong(flowIdStr);
        }
        ArrayList dataFlowFuJian = fuJianService.getYjFuJianList(flowId, sqsType);
        int count = dataFlowFuJian.size();
        if (count > 0) {
            /* if (flag == 0||flag==9) { */
            String banL = SysFinalRecource.JLBZKH_BLLB;//办理列表
            String requiredFuJ = SysFinalRecource.JLBZKH_REQUIREDFJ;//必传的附件
            String notRequiredFuJ = SysFinalRecource.JLBZKH_NOTREQUIREDFJ;//非必传附件
            //查询必填附件，选填附件对应的id
            String banLId = fuJianService.getFuJianListId(flowId, sqsType, banL);
            String btFuJid = fuJianService.getFuJianListId(flowId, sqsType, requiredFuJ);
            String fbtFuJid = fuJianService.getFuJianListId(flowId, sqsType, notRequiredFuJ);
            for (int j = 0; j < count; j++) {
                HashMap map1 = (HashMap) dataFlowFuJian.get(j);
                String fileName = map1.get("FILENAME") != null ? map1.get("FILENAME").toString() : "";
                String fuJidStr = map1.get("ID") != null ? map1.get("ID").toString() : "";
                String groupId = map1.get("GROUPID") != null ? map1.get("GROUPID").toString() : "";
                if ("0".equals(groupId)) {
                    map1.put("PARENTID", btFuJid);
                } else if ("9".equals(groupId)) {
                    map1.put("PARENTID", fbtFuJid);
                }
                map1.put("NAME", fileName);
                //拿到已经上传的附件
                ArrayList dataFuJian = yjWenShuService.getWenShuBySerialNumber(serialNumber, Long.parseLong(fuJidStr));
                for (int i = 0; i < dataFuJian.size(); i++) {
                    HashMap map = (HashMap) dataFuJian.get(i);
                    String fileTitle = map.get("FILETITLE") != null ? map.get("FILETITLE").toString() : "";
                    String fileTypeId = map.get("FILETYPEID") != null ? map.get("FILETYPEID").toString() : "";
                    String id = map.get("ID") != null ? map.get("ID").toString() : "";
                    map.put("NAME", fileTitle);
                    map.put("PARENTID", fileTypeId);//设置parentId,其值为fileTypeId
                    map.put("KID", id);//为了避免两张表id的冲突，所以将附件表的id,用kid表示
                    map.remove("ID");//移除id
                    dataFlowFuJian.add(map);//将两个ArrayList集合添加到一个ArrayList里面
                }

            }
        }
        //向页面输出json语句
        String json = JSON.Encode(dataFlowFuJian);
        response.getWriter().write(json);
    }

    //加载数据列表
    public void getApiFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //查询条件
        String flowIdStr = request.getParameter("flowId") == null ? "" : request.getParameter("flowId");//从控件传的值，若没有则为null
        String serialNumber = request.getParameter("serialNumber") == null ? "" : request.getParameter("serialNumber");
        String sqsType = request.getParameter("sqsType") == null ? "" : request.getParameter("sqsType");
        long flowId = 0l;
        if (!StringUtil.isNullOrEmpty(flowIdStr)) {
            flowId = Long.parseLong(flowIdStr);
        }
        ArrayList dataFlowFuJian = fuJianService.getYjFuJianList(flowId, sqsType);
        int count = dataFlowFuJian.size();
        if (count > 0) {
            String banL = SysFinalRecource.JLBZKH_BLLB;//办理列表
            String requiredFuJ = SysFinalRecource.JLBZKH_REQUIREDFJ;//必传的附件
            String notRequiredFuJ = SysFinalRecource.JLBZKH_NOTREQUIREDFJ;//非必传附件
            //查询必填附件，选填附件对应的id
            String btFuJid = fuJianService.getFuJianListId(flowId, sqsType, requiredFuJ);
            String fbtFuJid = fuJianService.getFuJianListId(flowId, sqsType, notRequiredFuJ);
            for (int j = 0; j < count; j++) {
                HashMap map1 = (HashMap) dataFlowFuJian.get(j);
                String fileName = map1.get("FILENAME") != null ? map1.get("FILENAME").toString() : "";
                String flagStr = map1.get("FLAG") != null ? map1.get("FLAG").toString() : "";
                String fuJidStr = map1.get("ID") != null ? map1.get("ID").toString() : "";
                String groupId = map1.get("GROUPID") != null ? map1.get("GROUPID").toString() : "";
                if ("0".equals(groupId)) {
                    map1.put("PARENTID", btFuJid);
                } else if ("9".equals(groupId)) {
                    map1.put("PARENTID", fbtFuJid);
                }
                map1.put("NAME", fileName);
                //拿到已经上传的附件
                ArrayList dataFuJian = yjWenShuService.getApiFileBySerialNumber(serialNumber, flagStr);
                for (int i = 0; i < dataFuJian.size(); i++) {
                    HashMap map = (HashMap) dataFuJian.get(i);
                    String fileTitle = map.get("FILENAME") != null ? map.get("FILENAME").toString() : "";
                    String fileTypeId = map.get("FLAG") != null ? map.get("FLAG").toString() : "";
                    String id = map.get("ID") != null ? map.get("ID").toString() : "";
                    String createDate = map.get("createDate") != null ? map.get("createDate").toString() : "";
                    map.put("NAME", fileTitle);
                    map.put("PARENTID", fileTypeId);//设置parentId,其值为fileTypeId
                    map.put("KID", id);//为了避免两张表id的冲突，所以将附件表的id,用kid表示
                    map.remove("ID");//移除id
                    dataFlowFuJian.add(map);//将两个ArrayList集合添加到一个ArrayList里面
                }

            }
        }
        //向页面输出json语句
        String json = JSON.Encode(dataFlowFuJian);
        response.getWriter().write(json);
    }

    //根据id删除文书
    public void deleteWenShu(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获得参数
        String idStr = request.getParameter("idResult");
        String nlId = request.getParameter("nlId");
        //判空操作
        if (StringUtil.isNullOrEmpty(idStr)) {
            return;
        }
        String[] ids = idStr.split(",");
        for (int i = 0, l = ids.length; i < l; i++) {
            String id = ids[i];
            String filePath = yjWenShuService.getYjFlowInstWenShuById(Long.parseLong(id)).getFilePath();
            /****************文件操作****************/
            String fileAllPath = "";
            File filetemp = null;
            byte[] fileContent = null;
            fileAllPath = SysFinalRecource.SECOND_LEVEL_DIRECTORY + filePath;
            //根据ID删除文书表内容
            yjWenShuService.deleteWenS(Long.parseLong(id));
            //删除本地文件
            filetemp = new File(fileAllPath);
            if (filetemp.exists()) {
                filetemp.delete();
                break;
            }
            /****************文件操作****************/
            response.getWriter().write("1");
        }
    }

    //根据文件扩展名，得到跳转路径
    public void getSwitchUrl(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获得参数
        String idStr = request.getParameter("id");
        String tableName = request.getParameter("tableName");
        String isButton = request.getParameter("isButton");
        long id = 0l;
        if (!StringUtil.isNullOrEmpty(idStr)) {
            id = Long.parseLong(idStr);
        }

        String extension = "", fileTitle = "";
        String filePath = "";
        String netWorkPath = "";
        String netCode = "-1";
        String serialNumber = "";
        if (!StringUtil.isNullOrEmpty(tableName) && !tableName.equals("null")) {
            ApiFile apiFileById = apiFileService.getApiFileById(id);
            if (apiFileById != null) {
                extension = apiFileById.getFileType();
                filePath = apiFileById.getLocalFilePath();
                fileTitle = apiFileById.getFileName();
            }
        } else {//查看申请书中附件查看
            filePath = request.getParameter("filePath");
            fileTitle = request.getParameter("fileTitle");
            extension = request.getParameter("extense");
        }

        String ReturnUrl = "";
        String extense = extension.toLowerCase().trim();
        if (extense.equals(".jpg") || extense.equals(".gif") || extense.equals(".png") || extense.equals(".jpeg")
                || extense.equals(".bmp")) {
            ReturnUrl = "jsp/common/FuJianShow_Image.jsp";
        } else if (extense.equals(".doc") || extense.equals(".docx") || extense.equals(".xls")
                || extense.equals(".xlsx")) {
            ReturnUrl = "jsp/common/YjFlowInstWenshuShow.jsp";
        } else if (extense.equals(".pdf")) {
            ReturnUrl = "jsp/common/fujianShow_pdf.jsp";
        } else {
            ReturnUrl = "jsp/common/FuJianBinaryRead.jsp";
        }

        HashMap map = new HashMap();
        map.put("ReturnUrl", ReturnUrl);
        map.put("id", id);
        map.put("tableName", tableName);
        map.put("filePath", filePath);
        map.put("fileTitle", fileTitle);
        map.put("extense", extense);
        map.put("isButton", isButton);
        map.put("netWorkPath", netWorkPath);
        map.put("serialNumber", serialNumber);
        map.put("netCode", netCode);
        String resulte = JSON.Encode(map);

        response.getWriter().write(resulte);
    }
%>
