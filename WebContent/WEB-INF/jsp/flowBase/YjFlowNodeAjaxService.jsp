<%@ page language="Java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         import="java.util.*,
                 java.lang.reflect.*,
                 com.yongjie.ZhiJianSbpt.container.*,
                 com.yongjie.ZhiJianSbpt.utilities.*" %>
<%@ page import="com.yongjie.ZhiJianSbpt.service.flowBase.IYjFlowNodesService" %>
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
    IYjFlowNodesService flowNodesService = (IYjFlowNodesService) ServiceProvider.getService(IYjFlowNodesService.SERVICE_NAME);

    //权限管理
    protected void BeforeInvoke(String methodName) {
    }

    protected void AfterInvoke(String methodName) {
    }

    public void getNodeMcByFlowInstId(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String flowInstId = request.getParameter("flowInstId");
        String flowId = request.getParameter("flowId");
        if (StringUtil.isNullOrEmpty(flowInstId) || StringUtil.isNullOrEmpty(flowId)) {
            return;
        }
        ArrayList result = flowNodesService.getNodeMcByFlowInstId(Long.parseLong(flowId), Long.parseLong(flowInstId));
        HashMap map = new HashMap();
        if (result.size() > 0) {
            map = (HashMap) result.get(0);//取其中的一条记录
            String json = JSON.Encode(map);
            response.getWriter().write(json);
        } else {
            response.getWriter().write("0");
        }

    }

    //69
    public void getNodeMo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String flowInstId = request.getParameter("flowInstId");
        String flowId = request.getParameter("flowId");
        if (StringUtil.isNullOrEmpty(flowInstId) || StringUtil.isNullOrEmpty(flowId)) {
            return;
        }
        ArrayList result = flowNodesService.getNodeMo(Long.parseLong(flowId), Long.parseLong(flowInstId));
        HashMap map = new HashMap();
        if (result.size() > 0) {
            map = (HashMap) result.get(0);//取其中的一条记录
            String json = JSON.Encode(map);
            response.getWriter().write(json);
        } else {
            response.getWriter().write("0");
        }

    }

    public void getNodeMcByFlowInstIdOnEnd(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String flowInstId = request.getParameter("flowInstId");
        String flowId = request.getParameter("flowId");
        String state = request.getParameter("state");

        if (StringUtil.isNullOrEmpty(flowInstId) || StringUtil.isNullOrEmpty(flowId)) {
            return;
        }
        ArrayList result = flowNodesService.getNodeMcByFlowInstIdOnEnd(Long.parseLong(flowId), Long.parseLong(flowInstId), state);
        HashMap map = new HashMap();
        if (result.size() > 0) {
            map = (HashMap) result.get(0);//取其中的一条记录
            String json = JSON.Encode(map);
            response.getWriter().write(json);
        } else {
            response.getWriter().write("0");
        }

    }

    //根据flowId获取这个yjFlow表   杨淑娟 20170612
    public void getBizIdByflowId(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String flowIds = request.getParameter("flowId");
        long flowId = 0;
        if (!StringUtil.isNullOrEmpty(flowIds)) {
            flowId = Long.parseLong(flowIds);
        }
        ArrayList result = flowNodesService.getBizIdByflowId(flowId);
        HashMap map = (HashMap) result.get(0);//flowId唯一
        String json = JSON.Encode(map);
        response.getWriter().write(json);
    }

    //根据nodeId查看节点信息
    public void getFlowNodesByNodeId(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String nodeId = request.getParameter("nodeId");
        String flowIds = request.getParameter("flowId");
        long flowId = 0;
        if (!StringUtil.isNullOrEmpty(flowIds)) {
            flowId = Long.parseLong(flowIds);
        }
        if (StringUtil.isNullOrEmpty(nodeId)) {
            return;
        }
        ArrayList result = flowNodesService.getFlowNodesByNodeId(flowId, nodeId);
        String json = JSON.Encode(result);
        response.getWriter().write(json);
    }
%>