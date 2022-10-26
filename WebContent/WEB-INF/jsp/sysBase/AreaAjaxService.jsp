<%@page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*,
                                      java.lang.reflect.*,
                                      com.yongjie.ZhiJianSbpt.service.*,
                                      java.text.*,
                                      com.yongjie.ZhiJianSbpt.container.*,
                                      com.yongjie.ZhiJianSbpt.model.*,
                                      com.yongjie.ZhiJianSbpt.utilities.*" %>
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

    //权限管理
    protected void BeforeInvoke(String methodName) {
    }

    //日志管理
    protected void AfterInvoke(String methodName) {
    }

    //加载数据列表
    public void getArea(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取的五大参数，由NUI封装 key pageIndex pageSize sortField sortOrder
        String userName = (String) request.getSession().getAttribute("userName");
        //查询条件
        String nameFilter = request.getParameter("key");
        String pId = request.getParameter("pId");
        //首次加载 获得区域ID
        String areaId = areaService.getAreaFormUser(userName);
        if (StringUtil.isNullOrEmpty(pId)) {
            pId = areaId;
        }
        //分页
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        //字段排序
        String sortField = request.getParameter("sortField");
        String sortOrder = request.getParameter("sortOrder");
        /*--------------------------------------------------------*/
        //从后台获得书记列表（hashmap形式）
        HashMap result = areaService.getArea(nameFilter, pageIndex, pageSize, sortField, sortOrder, pId, areaId);
        //将对象封装json语句
        String json = JSON.Encode(result);
        //向页面输出json语句
        response.getWriter().write(json);
    }

    //根据区域id查找该区域及以下的区域
    public void getTree(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userName = (String) request.getSession().getAttribute("userName");
        String areaId = areaService.getAreaFormUser(userName);
        //字段排序
        String isEnabled = "";
        String sortField = request.getParameter("sortField");
        String sortOrder = request.getParameter("sortOrder");
        isEnabled = request.getParameter("isEnabled");
        HashMap result = areaService.getTree(areaId, sortField, sortOrder, isEnabled);
        //将对象封装json语句
        String json = JSON.Encode(result);
        String json2 = json.substring(json.indexOf('['), json.indexOf(']') + 1);
        //向页面输出json语句
        response.getWriter().write(json2);
    }

    //根据区域id获得区域信息
    public void getData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("nodeId");
        List<Area> model = areaService.getData(Long.parseLong(id));
        Area area = model.get(0);
        //将area对象转成hashMap形式
        HashMap record = HashmapAndEntityTransfer.entityTransferToHashmap(area);
        String json = JSON.Encode(record);
        response.getWriter().write(json);
    }

    //增加和编辑区域
    public void addArea(HttpServletRequest request, HttpServletResponse response) throws Exception {
        /**
         *1.获得json数据
         *2.json数据解密
         *3.获取json数据，并转换成hashMap格式
         *4.执行操作数据库的方法
         */
        String userName = (String) request.getSession().getAttribute("userName");
        String idStr = request.getParameter("pId");//添加时：传过来的值是父节点的ID。
        String json = request.getParameter("data");
        ArrayList rows = (ArrayList) new JSON().Decode(json);
        for (int i = 0, l = rows.size(); i < l; i++) {
            HashMap row = (HashMap) rows.get(i);
            String id = row.get("id") != null ? row.get("id").toString() : "";
            String state = row.get("_state") != null ? row.get("_state").toString() : "";
            Area model = null;
            if (!StringUtil.isNullOrEmpty(idStr)) //传过来的值是父节点的ID
            {
                //根据父节点查询出实体类
                model = areaService.getData(Long.parseLong(idStr)).get(0);
            } else//否则用本节点的id 获得实体类
            {
                model = areaService.getData(Long.parseLong(id)).get(0);
            }
            //判断添加，编辑还是删除
            if (state.equals("added") || id.equals("")) {//添加情况
                //执行添加操作
                Area area = (Area) HashmapAndEntityTransfer.hashmapTransferToEntity(new Area(), row);
                if (model != null) {
                    //给新实体赋值
                    area.setParentId(model.getId());
                    area.setParentPath(model.getId() + "," + model.getParentPath());
                    area.setDepth(model.getDepth() + 1);
                    area.setIsEnabled(0);
                    area.setCaoR(userName);
                    Date d = new Date();
                    area.setCaoDate(new java.sql.Date(d.getTime()));
                }
                areaService.addArea(area);
            } else if (state.equals("") || state.equals("modified")) {//编辑情况执行
                Area area = (Area) HashmapAndEntityTransfer.hashmapTransferToEntity(new Area(), row);
                if (model != null) {
                    //给当前实体赋值
                    area.setParentId(model.getParentId());
                    area.setParentPath(model.getParentPath());
                    area.setDepth(model.getDepth());
                    area.setCaoR(userName);
                    Date d = new Date();
                    area.setCaoDate(new java.sql.Date(d.getTime()));
                }
                //执行修改操作
                areaService.editArea(area);
            }
        }
    }

    //根据ids启用
    public void activeArea(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获得参数
        String idStr = request.getParameter("idResult");
        //判空操作
        if (StringUtil.isNullOrEmpty(idStr)) {
            return;
        }
        String[] ids = idStr.split(",");
        for (int i = 0, l = ids.length; i < l; i++) {
            String id = ids[i];
            if (areaService.activeArea(Long.parseLong(id)) > 0) {
                response.getWriter().write("1");
            } else {
                response.getWriter().write("0");
            }
        }
    }

    //根据ids 禁用
    public void forbidArea(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String idStr = request.getParameter("idResult");
        if (StringUtil.isNullOrEmpty(idStr)) {
            return;
        }
        String[] ids = idStr.split(",");
        for (int i = 0, l = ids.length; i < l; i++) {
            String id = ids[i];
            if (areaService.forbidArea(Long.parseLong(id)) > 0) {
                response.getWriter().write("1");
            } else {
                response.getWriter().write("0");
            }
        }
    }

    //根据当前用户的区域代码和id判重
    public void checkAreaNumber(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String areaNumber = request.getParameter("areaNumber");
        String areaName = request.getParameter("areaName");
        String idStr = request.getParameter("id");//
        if ((!StringUtil.isNullOrEmpty(areaNumber)) && (!StringUtil.isNullOrEmpty(areaName))) {
            if (areaService.checkAreaNumber(areaName, areaNumber, idStr))
                response.getWriter().write("1");
            else
                response.getWriter().write("0");
        }
    }

    /* 根据areaid获得区域全称
    李杰     2016-06-03 */
    public void getAllAreaNameById(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //父节点的ID值
        String idStr = request.getParameter("id");
        String areaName = areaService.getAllAreaNameById(Long.parseLong(idStr));
        response.getWriter().write(areaName);
    }

    //杨清岭 2016-08-10 从根节点 开始往下刷数据
    public void treeForRegist(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String json2 = "";
        Long id = areaService.getMinParentId();
        if (id < 1) {
            response.getWriter().write(json2);
            return;
        }
        String areaId = id.toString();
        //字段排序
        String isEnabled = "";
        String sortField = request.getParameter("sortField");
        String sortOrder = request.getParameter("sortOrder");
        isEnabled = request.getParameter("isEnabled");
        HashMap result = areaService.getTree(areaId, sortField, sortOrder, isEnabled);
        //将对象封装json语句
        String json = JSON.Encode(result);
        json2 = json.substring(json.indexOf('['), json.indexOf(']') + 1);
        //向页面输出json语句
        response.getWriter().write(json2);
    }

    public void getAreas(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String key = request.getParameter("key");
        HashMap result = areaService.getAreas(key);
        //将对象封装json语句
        String json = JSON.Encode(result);
        String json2 = json.substring(json.indexOf('['), json.indexOf(']') + 1);

        //向页面输出json语句
        response.getWriter().write(json2);
    }

    public void getNodeNames(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String areaId = request.getParameter("areaId");
        if (StringUtil.isNullOrEmptyZf(areaId))
            return;
        response.getWriter().write(areaService.getNodeNames(Long.parseLong(areaId)));
    }

    public void areaTree(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String action = request.getParameter("action");
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("action", action);
        // 省 市 县
        Map<String, Object> resultMap = areaService.areaTree(reqMap);
        response.getWriter().write(JSON.Encode(resultMap.get(R.DATA_NAME)));
    }
%>