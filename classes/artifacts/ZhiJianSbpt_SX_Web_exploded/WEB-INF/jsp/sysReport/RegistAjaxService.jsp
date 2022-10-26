<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         import="java.util.*,
                 java.lang.reflect.*,
                 com.yongjie.ZhiJianSbpt.service.*,
                 com.yongjie.ZhiJianSbpt.container.*,
                 com.yongjie.ZhiJianSbpt.utilities.*" %>
<%@ page import="com.yongjie.ZhiJianSbpt.model.AccountSbpt" %>
<%@ page import="com.yongjie.ZhiJianSbpt.model.JgObject" %>
<%@ page import="com.alibaba.fastjson.JSONArray" %>
<%@ page import="com.alibaba.fastjson.JSONObject" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
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
        e.printStackTrace();
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
    IAreaService areaService = (IAreaService) ServiceProvider.getService(IAreaService.SERVICE_NAME);
    IRegistService registService = (IRegistService) ServiceProvider.getService(IRegistService.SERVICE_NAME);

    //权限管理
    protected void BeforeInvoke(String methodName) {
    }

    //日志管理
    protected void AfterInvoke(String methodName) {
    }


    public void filterAreaTree(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ArrayList data = new ArrayList();
        //获取查询参数
        String text = request.getParameter("name").toLowerCase();
        //获取整个树数据
        String areaId = areaService.getMinParentId().toString();

        //字段排序
        String isEnabled = "";
        String sortField = request.getParameter("sortField");
        String sortOrder = request.getParameter("sortOrder");
        isEnabled = request.getParameter("isEnabled");
        HashMap rows = areaService.getTree(areaId, sortField, sortOrder, isEnabled);
        String json = JSON.Encode(rows);
        json = json.substring(json.indexOf("["), json.length() - 1);
        ArrayList nodes = (ArrayList) new JSON().Decode(json);
        //找出符合查询条件的节点
        if (text.equals("null")) {
            for (int j = 0; j < nodes.size(); j++) {
                HashMap node0 = (HashMap) nodes.get(j);
                data.add(node0);
                //加入父级所有节点
                String pid = node0.get("parentId").toString();
                if (!pid.equals("0")) {
                    ArrayList dataleaf = SearchParentNodeArea(pid, nodes);
                    data.addAll(dataleaf);
                }
            }
        } else {
            for (int i = 0; i < nodes.size(); i++) {
                HashMap node = (HashMap) nodes.get(i);
                String name = node.get("areaName") == null ? "" : node.get("areaName").toString().toLowerCase();
                if (name.indexOf(text) > -1) {
                    data.add(node);
                    //加入父级所有节点
                    String pid = node.get("parentId").toString();
                    if (!pid.equals("0")) {
                        ArrayList data2 = SearchParentNodeArea(pid, nodes);
                        data.addAll(data2);
                        String id = node.get("id").toString();
                        ArrayList childList = areaService.getAreaList(isEnabled, id);
                        if (childList.size() > 0) {
                            data.addAll(childList);
                        }
                    }
                    //加入所有子节点
                }
            }
        }
        //去除重复节点
        HashMap idMaps = new HashMap();
        for (int i = data.size() - 1; i >= 0; i--) {
            HashMap node = (HashMap) data.get(i);
            String id = node.get("id").toString();
            if (idMaps.get(id) == null) {
                idMaps.put(id, node);
            } else {
                data.remove(i);
            }
        }
        //将对象封装json语句
        String jsonStr = JSON.Encode(data);
        //向页面输出json语句
        response.getWriter().write(jsonStr);
    }

    //查询父节点
    public ArrayList SearchParentNodeArea(String pid, ArrayList nodes) throws Exception {
        ArrayList data = new ArrayList();
        for (int i = 0; i < nodes.size(); i++) {
            HashMap node = (HashMap) nodes.get(i);
            if (node.get("id").toString().equals(pid)) {
                data.add(node);
                if (!node.get("parentId").toString().equals("0")) {
                    ArrayList data2 = SearchParentNodeArea(node.get("parentId").toString(), nodes);
                    data.addAll(data2);
                }
            }
        }
        return data;
    }

    //注册保存
    public void doRegist(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //这里是保存所有的注册信息
        String requestJson = request.getParameter("data");
        if (requestJson == null || "".equals(requestJson)) {
            response.getWriter().write("数据为空！");
            return;
        }
        String flag = request.getParameter("flag");
        HashMap row = com.alibaba.fastjson.JSON.parseObject(requestJson, HashMap.class);
        String id = row.get("id") != null ? row.get("id").toString() : "";
        if (id.equals("") || id == null) {//说明新增
            String psd = row.get("password") != null ? row.get("password").toString() : "";
            String password = MD5EncryptHelper.getEncryption(psd);//加密
            AccountSbpt accountSbpt = (AccountSbpt) HashmapAndEntityTransfer.hashmapTransferToEntity(new AccountSbpt(), row);
            accountSbpt.setCaoDate(new java.sql.Date(System.currentTimeMillis()));//后台赋操作时间
            accountSbpt.setIsAction(1);
            accountSbpt.setFlag(Integer.parseInt(flag));
            accountSbpt.setIsDlf(1);
            accountSbpt.setPassword(password);
            JgObject model = registService.getJgobject(accountSbpt.getZzjgdm(), accountSbpt.getQymc(), accountSbpt.getIsDlf());
            String newShxydm = "";
            /*
             * 如果根据社会信用代码、企业名称、是否是独立法人未查询到数据，则生成生成一个社会信用代码，并将数据插入到Jgobject中
             * 如果查询到了数据，但是数据的新社会信用代码为空，则生成一个新的新社会信用代码，并更新Jgobject
             * 如果查询到了数据，且数据不为空，则将当前AccountSbpt的newShxydm设置为model的newShxydm
             */
            if (model == null) {
                if (accountSbpt.getIsDlf().intValue() == 1) {
                    newShxydm = registService.getNewShxydm2(accountSbpt.getZzjgdm());
                } else {
                    newShxydm = accountSbpt.getZzjgdm();
                }
                accountSbpt.setNewShxydm(newShxydm);
                registService.insertIntoJgobject(accountSbpt);
            } else if (StringUtil.isNullOrEmpty(model.getNewShxydm())) {
                if (accountSbpt.getIsDlf().intValue() == 1) {
                    newShxydm = registService.getNewShxydm2(accountSbpt.getZzjgdm());
                } else {
                    newShxydm = accountSbpt.getZzjgdm();
                }
                accountSbpt.setNewShxydm(newShxydm);
                model.setNewShxydm(newShxydm);
                //只更新newShxydm字段
                registService.updateJgobject(model);
            } else {
                newShxydm = model.getNewShxydm();
                accountSbpt.setNewShxydm(newShxydm);
            }
            registService.doRegist(accountSbpt);
        } else {
            //更新
            AccountSbpt accountSbpt2 = (AccountSbpt) HashmapAndEntityTransfer.hashmapTransferToEntity(new AccountSbpt(), row);
            accountSbpt2.setCaoDate(new java.sql.Date(System.currentTimeMillis()));//后台赋操作时间
            //根据社会信用代码、企业名、是否是独立法人去监管对象中查询相应的对象
            JgObject model = registService.getJgobject(accountSbpt2.getZzjgdm(), accountSbpt2.getQymc(), accountSbpt2.getIsDlf());
            String newShxydm = "";
            /*
             * 如果根据社会信用代码、企业名称、是否是独立法人未查询到数据，则生成生成一个社会信用代码，并将数据插入到Jgobject中
             * 如果查询到了数据，但是数据的新社会信用代码为空，则生成一个新的新社会信用代码，并更新Jgobject
             * 如果查询到了数据，且数据不为空，则将当前AccountSbpt的newShxydm设置为model的newShxydm
             */

            if (model == null) {
                if (accountSbpt2.getIsDlf().intValue() == 1) {
                    newShxydm = registService.getNewShxydm2(accountSbpt2.getZzjgdm());
                } else {
                    newShxydm = accountSbpt2.getZzjgdm();
                }
                accountSbpt2.setNewShxydm(newShxydm);
                registService.insertIntoJgobject(accountSbpt2);

            } else if (StringUtil.isNullOrEmpty(model.getNewShxydm())) {
                if (accountSbpt2.getIsDlf().intValue() == 1) {
                    newShxydm = registService.getNewShxydm2(accountSbpt2.getZzjgdm());
                } else {
                    newShxydm = accountSbpt2.getZzjgdm();
                }
                accountSbpt2.setNewShxydm(newShxydm);
                model.setNewShxydm(newShxydm);
                //只更新newShxydm字段
                registService.updateJgobject(model);

            } else {
                newShxydm = model.getNewShxydm();
                accountSbpt2.setNewShxydm(newShxydm);
            }

            //registService.changeOwnData(accountSbpt2);
            registService.updateByUserId(accountSbpt2);
        }
        response.getWriter().write("success");//向前台传递信息 注册成功
    }

    //查重
    public void check(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //查询新注册的用户是否重复
        String name = request.getParameter("name");
        String qymc = request.getParameter("qymc");
        String userid = request.getParameter("userid");
        boolean flag;
        if (StringUtil.isNullOrEmpty(userid)) {
            flag = registService.checkName(name, qymc, 0l);
        } else {
            flag = registService.checkName(name, qymc, Long.parseLong(userid));
        }
        //说明重复了
        if (flag == true) {
            response.getWriter().write("error");
        }
    }

    //重设密码
    public void changePsd(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userName = (String) request.getSession().getAttribute("userName");
        String psd = request.getParameter("pass");
        //将取到的新密码加密 然后再更新
        String password = MD5EncryptHelper.getEncryption(psd);
        int num = registService.changePsd(password, userName);
        if (num > 0) {
            response.getWriter().write("success");
        }
    }

    //根据用户名查询数据
    public void getInfoByUserName(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String logOnUserName = (String) request.getSession().getAttribute("userName");
        AccountSbpt model = registService.getAccountSbptByUserName(logOnUserName);
        String nodeNames = areaService.getNodeNames(model.getAreaId());
        //将area对象转成hashMap形式
        HashMap record = HashmapAndEntityTransfer.entityTransferToHashmap(model);
        //将节点名装进hashmap
        record.put("nodeNames", nodeNames);
        String json = JSON.Encode(record);
        response.getWriter().write(json);
    }

    //判断老密码是否输入正确
    public void checkOldPsd(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String psd = request.getParameter("pass");
        //将取到的老密码加密 然后再比较
        String password = MD5EncryptHelper.getEncryption(psd);
        String userName = (String) request.getSession().getAttribute("userName");
        AccountSbpt model = registService.getAccountSbptByUserName(userName);
        String oldPsd = model.getPassword();
        if (!password.equals(oldPsd)) {
            response.getWriter().write("error");
        }
    }
%>