<%@ page language="Java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         import="java.util.*,
                 java.lang.reflect.*" %>
<%@ page import="com.yongjie.ZhiJianSbpt.container.ServiceProvider" %>
<%@ page import="com.alibaba.fastjson.JSONObject" %>
<%@ page import="com.yongjie.ZhiJianSbpt.model.JgObject" %>
<%@ page import="com.yongjie.ZhiJianSbpt.service.IRegistService" %>
<%@ page import="com.yongjie.ZhiJianSbpt.model.AccountSbpt" %>
<%@ page import="com.yongjie.ZhiJianSbpt.service.jyjccj.*" %>
<%@ page import="java.io.File" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.*" %>
<%@ page import="java.nio.MappedByteBuffer" %>
<%@ page import="com.yongjie.ZhiJianSbpt.model.jyjccj.*" %>
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
    //采集service
    CollectService collectService = (CollectService) ServiceProvider
            .getService(CollectService.SERVICE_NAME);
    //机构service
    JgobjectService jgobjectService = (JgobjectService) ServiceProvider
            .getService(JgobjectService.SERVICE_NAME);
    //注册service
    IRegistService registService = (IRegistService) ServiceProvider
            .getService(IRegistService.SERVICE_NAME);
    //能力/签字人/人员service
    CollectFujianService collectFujianService = (CollectFujianService) ServiceProvider
            .getService(CollectFujianService.SERVICE_NAME);


    //权限管理
    protected void BeforeInvoke(String methodName) {
    }

    protected void AfterInvoke(String methodName) {
    }

    private String saveDirectory = SysFinalRecource.SECOND_LEVEL_DIRECTORY;


    public void collectList(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String zzrdzsbh = request.getParameter("zzrdzsbh");
        Map map = new HashMap();
        map.put("zzrdzsbh", zzrdzsbh);
        List<Collect> collects = collectService.collectList(map);
        //将对象封装json语句
        String json = JSON.Encode(collects);
        //向页面输出json语句
        response.getWriter().write(json);

    }


    public void sb(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        JgObject jgObject = collectService.getById(Long.valueOf(id));
        jgObject.setFlag(1);
        collectService.update(jgObject);
        response.getWriter().print("1");
    }

    public void del(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long id = request.getParameter("id") == null ? null : Long.valueOf(request.getParameter("id"));
        if (null == id) {
            response.getWriter().print(R.error("删除异常！"));
            return;
        }
        JgObject jgObject = jgobjectService.getById(id);
        jgobjectService.delete(id);
        collectService.delByZsbh(jgObject.getZzrdzsbh());
        response.getWriter().print("1");
    }

    public void delnl(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        String zsbh = request.getParameter("zsbh");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("zsbh", zsbh);
        map.put("id", id);
        CollectFujian collectFujian = collectFujianService.getById(Long.valueOf(id));
        collectService.delnl(map);
        response.getWriter().print("1");
    }

    public void delqzr(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        String zsbh = request.getParameter("zsbh");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("zsbh", zsbh);
        map.put("id", id);
        CollectFujian collectFujian = collectFujianService.getById(Long.valueOf(id));
        File file = new File(saveDirectory + collectFujian.getFilePath());
        if (file.exists()) {
            file.delete();
        }
        collectService.delqzr(map);
        response.getWriter().print("1");
    }

    public void delry(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        String zsbh = request.getParameter("zsbh");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("zsbh", zsbh);
        map.put("id", id);
        CollectFujian collectFujian = collectFujianService.getById(Long.valueOf(id));
        File file = new File(saveDirectory + collectFujian.getFilePath());
        if (file.exists()) {
            boolean delete = file.delete();
            System.out.println(delete);
        }
        collectService.delry(map);
        response.getWriter().print("1");
    }

    public void getNlFileList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String resultJson = "";
        String zsbh = request.getParameter("zsbh");
        String id = request.getParameter("id");
        String sortField = request.getParameter("sortField");
        String sortOrder = request.getParameter("sortOrder");
        HashMap<Object, Object> reqMap = new HashMap<Object, Object>();
        reqMap.put("zsbh", zsbh);
        reqMap.put("id", id);
        reqMap.put("sortField", sortField);
        reqMap.put("sortOrder", sortOrder);
        Map<String, Object> nlFileList = collectService.getNlFileList(reqMap);
        resultJson = R.page(nlFileList);
        response.getWriter().write(resultJson);
    }

    public void getQzrFileList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String resultJson = "";
        String zsbh = request.getParameter("zsbh");
        String id = request.getParameter("id");
        HashMap<Object, Object> reqMap = new HashMap<Object, Object>();
        reqMap.put("zsbh", zsbh);
        reqMap.put("id", id);
        Map<String, Object> nlFileList = collectService.getQzrFileList(reqMap);
        resultJson = R.page(nlFileList);
        response.getWriter().write(resultJson);
    }

    public void getRyFileList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String resultJson = "";
        String zsbh = request.getParameter("zsbh");
        String id = request.getParameter("id");
        HashMap<Object, Object> reqMap = new HashMap<Object, Object>();
        reqMap.put("zsbh", zsbh);
        reqMap.put("id", id);
        Map<String, Object> nlFileList = collectService.getRyFileList(reqMap);
        resultJson = R.page(nlFileList);
        response.getWriter().write(resultJson);
    }


    public void queryByZsbh(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String resultJson = "";
        Map<String, Object> map = new HashMap<String, Object>();
        String zsbh = request.getParameter("zsbh");
        map.put("zsbh", zsbh);
        Map<String, Object> map1 = collectService.queryByZsbh(map);
        resultJson = R.ok(map1);
        response.getWriter().write(resultJson);
    }

    public void jgobjectSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String json = request.getParameter("data");
        String str = request.getParameter("str");
        String strN = request.getParameter("strN");
        String qyId = request.getParameter("qyId");
        String qyText = request.getParameter("qyText");
        HashMap<String, Object> map = JSONObject.parseObject(json, HashMap.class);
        Map parMap = new HashMap<String, Object>();
        parMap.put("zsbh", map.get("ZZRDZSBH").toString());
        Map<String, Object> result = collectService.queryByZsbh(parMap);
        if (StringUtil.isNullOrEmpty(result)) {
            JgObject jgObject = (JgObject) HashmapAndEntityTransfer.hashmapTransferToEntity2(new JgObject(), map);
            jgObject.setZylbId(Long.parseLong(str));
            jgObject.setZylbName(strN);
            jgObject.setAreaId(Long.valueOf(qyId));
            jgObject.setAreaName(qyText);
            jgObject.setCollectDate(new Date());
            jgObject.setFlag(0);
            jgobjectService.save(jgObject);
        } else {
            JgObject oldEntity = jgobjectService.getById(Long.valueOf(result.get("ID").toString()));
            oldEntity = (JgObject) HashmapAndEntityTransfer.hashmapTransferToEntity2(oldEntity, map);
            oldEntity.setZylbId(Long.parseLong(str));
            oldEntity.setZylbName(strN);
            oldEntity.setAreaId(Long.valueOf(qyId));
            oldEntity.setAreaName(qyText);
            oldEntity.setCollectDate(new Date());
            jgobjectService.update(oldEntity);
        }
        response.getWriter().write(R.ok("保存成功！"));
    }

    public void queryByUserName(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userName = request.getSession().getAttribute("userName").toString();
        AccountSbpt accountSbpt = registService.getAccountSbptByUserName(userName);
        Map map = new HashMap();
        map.put("qymc", accountSbpt.getQymc());
        map.put("shxydm", accountSbpt.getZzjgdm());
        Map result = jgobjectService.getByMcAndShxydm(map);
        response.getWriter().write(JSON.Encode(result));
    }

    public void queryByUserName2(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userName = request.getSession().getAttribute("userName").toString();
        AccountSbpt accountSbpt = registService.getAccountSbptByUserName(userName);
        Map map = new HashMap();
        map.put("qymc", accountSbpt.getQymc());
        map.put("shxydm", accountSbpt.getZzjgdm());
        Map result = jgobjectService.getByMcAndShxydm2(map);
        response.getWriter().write(JSON.Encode(result));
    }

    public void ckfj(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String state = request.getParameter("state");
        if (!"1".equals(state) && !"2".equals(state) && !"3".equals(state)) {
            response.getWriter().write("文件类型有误！");
            return;
        }
        String resultJson = "";
        String zsbh = request.getParameter("zsbh");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", state);
        map.put("zsbh", zsbh);
        Map<String, Object> collectFujians = collectService.ckfj(map);
        resultJson = R.page(collectFujians);
        response.getWriter().write(resultJson);
    }

    public void cknl(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String resultJson = "";
        String zzrdzsbh = request.getParameter("zzrdzsbh");
        String pageIndex = request.getParameter("pageIndex");
        String pageSize = request.getParameter("pageSize");
        String cp = request.getParameter("cp");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("zzrdzsbh", zzrdzsbh);
        map.put("cp", cp);
        map.put("pageIndex", pageIndex);
        map.put("pageSize", pageSize);
        Map result = collectService.cknl(map);
        resultJson = R.page(result);
        response.getWriter().write(resultJson);
    }

    public void queryQzrBySfzh(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sfzh = request.getParameter("sfzh");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sfzh", sfzh);
        CjSqqzr cjSqqzr = collectService.queryQzrBySfzh(map);
        String s = JSON.Encode(cjSqqzr);
        response.getWriter().write(s);
    }

    public void queryRyBySfzh(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sfzh = request.getParameter("sfzh");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sfzh", sfzh);
        JgobjectJyjcRy jgobjectJyjcRy = collectService.queryRyBySfzh(map);
        String s = JSON.Encode(jgobjectJyjcRy);
        response.getWriter().write(s);
    }

    public void queryRyListByZsbh(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String zsbh = request.getParameter("zsbh");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("zsbh", zsbh);
        List list = collectService.queryRyListByZsbh(map);
        String s = JSON.Encode(list);
        response.getWriter().write(s);
    }

    public void checkFlagByZsbh(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String zsbh = request.getParameter("zsbh");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("zsbh", zsbh);
        int count = collectService.checkFlagByZsbh(map);
        String encode = JSON.Encode(count);
        response.getWriter().write(encode);
    }

    public void querySfzhByName(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("name");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", name);
        Map result = collectService.querySfzhByName(map);
        String encode = JSON.Encode(result);
        response.getWriter().write(encode);
    }

    public void queryQzrSfzhByName(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("name");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", name);
        Map result = collectService.queryQzrSfzhByName(map);
        String encode = JSON.Encode(result);
        response.getWriter().write(encode);
    }

    public void queryQzrByZsbh(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String zsbh = request.getParameter("zsbh");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("zsbh", zsbh);
        List list = collectService.queryQzrByZsbh(map);
        String s = JSON.Encode(list);
        response.getWriter().write(s);
    }

%>
