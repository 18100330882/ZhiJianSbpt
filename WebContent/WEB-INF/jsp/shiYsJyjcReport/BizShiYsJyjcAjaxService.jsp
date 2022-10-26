<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         import="java.util.*,
                 java.lang.reflect.*,
                 com.yongjie.ZhiJianSbpt.service.*,
                 com.yongjie.ZhiJianSbpt.container.*,
                 com.yongjie.ZhiJianSbpt.model.shiYsJyjc.*,
                 com.yongjie.ZhiJianSbpt.model.*,
                 com.yongjie.ZhiJianSbpt.utilities.*" %>
<%@ page import="com.yongjie.ZhiJianSbpt.service.shiYsJyjc.IBizShiYsJyjcService" %>
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
        //利用反射技术获取方法对象（万物皆对象）
        Method method = cls.getMethod(methodName, argsClass);
        Object[] args = new Object[2];
        args[0] = request;
        args[1] = response;

        BeforeInvoke(methodName);
        method.invoke(this, args);
    } catch (Exception e) {
        e.printStackTrace();
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
    IBizShiYsJyjcService bizShiYsJyjcService = (IBizShiYsJyjcService) ServiceProvider.getService(IBizShiYsJyjcService.SERVICE_NAME);
    IRegistService registService = (IRegistService) ServiceProvider.getService(IRegistService.SERVICE_NAME);
    IAreaService areaService = (IAreaService) ServiceProvider.getService(IAreaService.SERVICE_NAME);
    //区域名称
    IAreaService iareaService = (IAreaService) ServiceProvider.getService(IAreaService.SERVICE_NAME);

    //权限管理
    protected void BeforeInvoke(String methodName) {
    }

    //日志管理
    protected void AfterInvoke(String methodName) {
    }

    //主表grid显示
    public void getShiYsJyjcSqsToList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String admin = request.getSession().getAttribute("userName").toString();
        String pageIndex = request.getParameter("pageIndex");
        String pageSize = request.getParameter("pageSize");
        String flowId = request.getParameter("flowId");
        String name = request.getParameter("key");
        //字段排序
        String sortField = request.getParameter("sortField");
        String sortOrder = request.getParameter("sortOrder");
        //从后台获得书记列表（hashmap形式）
        HashMap result = bizShiYsJyjcService.getShiYsJyjcSqsToList2(name, Integer.parseInt(pageIndex),
                Integer.parseInt(pageSize), sortField, sortOrder, admin, flowId);
        //将对象封装json语句
        String json = JSON.Encode(result);
        //向页面输出json语句
        response.getWriter().write(json);
    }

    //查询上报部门地市名
    public void getShangBaoBuMen(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String admin = request.getSession().getAttribute("userName").toString();
        String shRenAreaId = request.getParameter("shRenAreaId");
        //查询上报部门地市名  非常全称
        String result = iareaService.getAreaName(Long.parseLong(shRenAreaId));
        if (!StringUtil.isNullOrEmpty(result)) {
            response.getWriter().write(result);
        } else {
            response.getWriter().write("");
        }
    }


    //根据id获取主表数据
    public void getBizShiYsJyjcOrsqsId(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sqsId = request.getParameter("sqsId");
        HashMap result = bizShiYsJyjcService.getBizShiYsJyjcOrsqsId(Long.parseLong(sqsId));
        result.put("daima", result.get("sqZylb"));
        //将对象封装json语句
        String json = JSON.Encode(result);
        //向页面输出json语句
        response.getWriter().write(json);
    }

    //根据id获得主表数据ArrayList
    public void getBizShiYsJyjcById(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sqsId = request.getParameter("sqsId");
        ArrayList result = bizShiYsJyjcService.getBizShiYsJyById(Long.parseLong(sqsId));
        //将对象封装json语句
        String json = JSON.Encode(result);
        //向页面输出json语句
        response.getWriter().write(json);
    }

    //保存数据
    public void saveJyjc(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sqsId = request.getParameter("sqsId");//申请书id
        String json = request.getParameter("data");
        String flowId = request.getParameter("flowId");//拿到流程id
        String sqZylb = request.getParameter("sqZylb");//申请资质行业类别
        String frlx = request.getParameter("frlx");//法人类别
        HashMap row = (HashMap) JSON.Decode(json);
        //String sqType = row.get("sqType") != null ? row.get("sqType").toString() : "";
        BizShiYsJyjc jyjcModel = (BizShiYsJyjc) HashmapAndEntityTransfer.hashmapTransferToEntity(new BizShiYsJyjc(),
                row);
        if (sqsId == null || sqsId.equals("null")) {
            if (!StringUtil.isNullOrEmpty(flowId)) {
                jyjcModel.setFlowId(Long.parseLong(flowId));
            } else {
                jyjcModel.setFlowId(3l);
            }
            jyjcModel.setSqType(0);
            jyjcModel.setSqlb("资质认证");
            jyjcModel.setFlag(0);
            jyjcModel.setIsxz("0");
            jyjcModel.setCzr(request.getSession().getAttribute("userName").toString());
            jyjcModel.setCzDate(new java.util.Date(System.currentTimeMillis()));
            //把注册表的信息插入申请书表
            bizShiYsJyjcService.saveJyjc(jyjcModel);
        } else {
            BizShiYsJyjc model = bizShiYsJyjcService.getById(jyjcModel.getId());
            //编辑
            if (!flowId.equals("null")) {
                //jyjcModel.setFlowId(Long.parseLong(flowId));
                model.setFlowId(Long.parseLong(flowId));
            } else {
                //jyjcModel.setFlowId(3l);
                model.setFlowId(3l);
            }
            //jyjcModel.setSqlb("资质认证");
            model.setSqlb("资质认证");
            model.setCmaSqlx(jyjcModel.getCmaSqlx());
            model.setSqZylb(jyjcModel.getSqZylb());
            model.setSysMc(jyjcModel.getSysMc());
            model.setZgdwmc(jyjcModel.getZgdwmc());
            model.setZgdwdm(jyjcModel.getZgdwdm());
            model.setSqType(0);
            model.setSqrq(jyjcModel.getSqrq());
            model.setStartPsDate(jyjcModel.getStartPsDate());
            model.setCzr(request.getSession().getAttribute("userName").toString());
            model.setCzDate(new java.util.Date(System.currentTimeMillis()));
            //把注册表的信息插入申请书表
            bizShiYsJyjcService.updateJyjc(model);
        }
        //判断企业名称是否修改，若修改则吧注册表中的企业名称也修改
        AccountSbpt sbptModel = registService
                .getAccountSbptByUserName(request.getSession().getAttribute("userName").toString());
        if (sbptModel.getQymc().equals(jyjcModel.getSysMc()) == false) {//企业名称不同
            sbptModel.setQymc(jyjcModel.getSysMc());
            registService.updateByUserId(sbptModel);
        }

        String id = String.valueOf(jyjcModel.getId());
        String isxz = jyjcModel.getIsxz() == null ? "" : jyjcModel.getIsxz();
        if (!"0".equals(isxz)) {
            isxz = "1";
        }
        response.getWriter().write(id + "," + isxz);
    }


    public void getShiysjyjcPremitByZsbh(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String zsbh = request.getParameter("zsbh");
        if (StringUtil.isNullOrEmpty(zsbh) || zsbh.equals("null")) {
            return;
        }
        ArrayList list = bizShiYsJyjcService.getShiysjyjcPremitByZsbh(zsbh);
        if (list.size() > 0) {
            String json = JSON.Encode(list);
            response.getWriter().write(json);
        } else {
            response.getWriter().write("0");
        }
    }

    public void getShiysjyjcPremitByZsbh2(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String zsbh = request.getParameter("zsbh");
        if (StringUtil.isNullOrEmpty(zsbh) || zsbh.equals("null")) {
            return;
        }
        ArrayList list = bizShiYsJyjcService.getShiysjyjcPremitByZsbh2(zsbh);
        if (list.size() > 0) {
            String json = JSON.Encode(list);
            response.getWriter().write(json);
        } else {
            response.getWriter().write("0");
        }
    }


    public void isCanSb(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> resultMap = new HashMap();
        resultMap.put("state", Boolean.FALSE);
        try {
            String userName = request.getSession().getAttribute("userName").toString();
            try {
                String flowId = request.getParameter("flowId");
                String cmaSqlx = request.getParameter("cmaSqlx");
                String sqsId = request.getParameter("sqsId");
                ArrayList list = new ArrayList();
                if (StringUtil.isNullOrEmptyZf(sqsId)) {
                    list = bizShiYsJyjcService.getListCanSb(userName, flowId, cmaSqlx);
                } else {
                    list = bizShiYsJyjcService.getListCanSb(userName, flowId, cmaSqlx, sqsId);
                }
                if (list.size() > 0) {
                    resultMap.put("msg", "当前数据中有未办结!");
                } else {
                    resultMap.put("state", Boolean.TRUE);
                }
            } catch (Exception e) {
                resultMap.put("msg", "参数异常!");
            }
        } catch (Exception e) {
            resultMap.put("msg", "登录超时!");
        }
        String json = JSON.Encode(resultMap);
        response.getWriter().write(json);
    }

    //保存终止评审数据
    public void zzpsAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String sqsIds = request.getParameter("sqsId");//申请书id
        long sqsId = Long.parseLong(sqsIds);
			
			/* String json = request.getParameter("data");
			HashMap row = (HashMap) JSON.Decode(json);
			*/
        String flowIds = request.getParameter("flowId") + "";
        long flowId = Long.parseLong(flowIds);
        String flowInstIds = request.getParameter("flowInstId") + "";
        long flowInstId = 0;
        if (!("".equals(flowInstIds) || "null".equals(flowInstIds))) {
            flowInstId = Long.parseLong(flowInstIds);
        }
        String nodeId = request.getParameter("nodeId") + "";
        String reason = request.getParameter("reason") + "";
        String zzxkdate = request.getParameter("zzxkdate") + "";
        String lxr = request.getParameter("lxr") + "";
        String lxdh = request.getParameter("lxdh") + "";
        String zzrdName = request.getParameter("zzrdName") + "";
        String zsNumber = request.getParameter("zsNumber") + "";
        String yxDate = request.getParameter("yxDate") + "";
        String txaddress = request.getParameter("txaddress") + "";
        String faxx = request.getParameter("faxx") + "";
        String id = request.getParameter("id") != null ? request.getParameter("id").toString() : "";
        if ("".equals(id)) {
            bizShiYsJyjcService.addZzps(sqsId, flowId, flowInstId, nodeId, zzrdName, zzxkdate, zsNumber, yxDate, reason, lxr, lxdh, txaddress, faxx);
            response.getWriter().write("1");
        } else {
            bizShiYsJyjcService.updataZzps(id, sqsId, flowId, flowInstId, nodeId, zzrdName, zzxkdate, zsNumber, yxDate, reason, lxr, lxdh, txaddress, faxx);
            response.getWriter().write("1");
        }
    }

    //查询
    public void zzpsSelect(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String sqsIds = request.getParameter("sqsId");//申请书id
        long sqsId = Long.parseLong(sqsIds);

        String flowIds = request.getParameter("flowId") + "";
        long flowId = Long.parseLong(flowIds);
        String flowInstIds = request.getParameter("flowInstId") + "";
        long flowInstId = 0;
        if (!("".equals(flowInstIds) || "null".equals(flowInstIds))) {
            flowInstId = Long.parseLong(flowInstIds);
        }
        ArrayList result = bizShiYsJyjcService.getzzpsSelect(sqsId, flowId, flowInstId);
        //将对象封装json语句
        String json = JSON.Encode(result);
        //向页面输出json语句
        response.getWriter().write(json);

    }

    //查询证书编号及有效期
    public void zzpszsNumber(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String zsbh = request.getParameter("zzrdzsbh");
        ArrayList list = new ArrayList();
        if (!(StringUtil.isNullOrEmpty(zsbh) || zsbh.equals("null"))) {
            list = bizShiYsJyjcService.getShiysjyjcPremitByZsbh(zsbh);
        }
        if (list.size() > 0) {
            String json = JSON.Encode(list);
            response.getWriter().write(json);
        } else {
            response.getWriter().write("0");
        }

    }

    public void zzpstxaddress(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String logOnUserName = (String) request.getSession().getAttribute("userName");
        //String logOnUserName ="admin";
        AccountSbpt model = registService.getAccountSbptByUserName(logOnUserName);
        String zcdz = model.getZcdz();

        response.getWriter().write(zcdz);


    }


    //申报端检验检测档案机构查询 
    public void searchQyxxList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userName = request.getSession().getAttribute("userName").toString();
        AccountSbpt registModel = registService.getAccountSbptByUserName(userName);
        String shxydm = registModel.getNewShxydm();
        String jgflIds = request.getParameter("jgflId");
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        String sortField = request.getParameter("sortField");
        String sortOrder = request.getParameter("sortOrder");

        HashMap result = bizShiYsJyjcService.getQyCxList(jgflIds, shxydm, pageIndex, pageSize, null, sortOrder);
		/* if (result.size() > 0) {
			ArrayList datalist = (ArrayList) result.get("data"); 
			if (datalist != null && datalist.size() > 0) {
				for (int i = 0, length = datalist.size(); i < length; i++) {
					HashMap map = (HashMap) datalist.get(i);
					String areaIds = map.get("AREAID") + "";
					String xzqh = areaService.getAllAreaNameById(Long.parseLong(areaIds));
					map.put("XZQH", xzqh);
				}
			}
		} */
        String json = JSON.Encode(result);
        response.getWriter().write(json);
    }

    public void getJgObjectnewShxydm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String newShxydm = request.getParameter("newShxydm");
        if (StringUtil.isNullOrEmpty(newShxydm)) {
            return;
        }
        JgObject model = bizShiYsJyjcService.getJgObjectnewShxydm(newShxydm, "");
        //long areaId = model.getAreaId();
        //String nodeName = areaService.getAllAreaNameById(areaId);
        HashMap map = (HashMap) HashmapAndEntityTransfer.entityTransferToHashmap(model);
        //map.put("nodeName", nodeName);
        String json = JSON.Encode(map);
        response.getWriter().write(json);
    }

    public void getSqsxxByShxydm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String newShxydm = request.getParameter("newShxydm");
        String jgstate = request.getParameter("jgstate");
        List<Object> list = bizShiYsJyjcService.getSqsxxByShxydm(newShxydm, jgstate);
        String json = JSON.Encode(list);
        response.getWriter().write(json);
    }

    public void getCddzFromCd(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String newShxydm = request.getParameter("newShxydm");
        String type = request.getParameter("type");
        List<Object> cdList = bizShiYsJyjcService.getCd(newShxydm, type);
        String cdSum = "";
        if (cdList.size() > 0) {
            for (int i = 0; i < cdList.size(); i++) {
                HashMap map = (HashMap) cdList.get(i);
                String cddz = map.get("CDDZ") == null ? "" : map.get("CDDZ").toString();
                cdSum = cdSum + cddz + ",";
            }
            cdSum = cdSum.substring(0, cdSum.lastIndexOf(","));
            response.getWriter().write(cdSum);
        }
    }

    // 获取证书信息列表
    public void getZsxxList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String newShxydm = request.getParameter("newShxydm");
        if (StringUtil.isNullOrEmptyZf(newShxydm))
            return;
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex")); // 当前页
        int pageSize = Integer.parseInt(request.getParameter("pageSize")); // 当前页条数

        // 获取数据
        Map<String, Object> result = bizShiYsJyjcService.getZsxx(newShxydm, "", "", pageIndex, pageSize);
        String json = JSON.Encode(result);
        response.getWriter().write(json);
    }

    public void getZylbList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HashMap result = new HashMap();
        result = bizShiYsJyjcService.getAllJyjcZyTypes("");
        //将对象封装json语句
        String json = JSON.Encode(result);
        json = json.substring(json.indexOf("["), json.length() - 1);
        //向页面输出json语句
        response.getWriter().write(json);
    }

    public void getJgObjectJyjcjgRyList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String shxydm = request.getParameter("newShxydm");
        String jgstate = request.getParameter("jgstate");
        String sqsId = request.getParameter("sqsId");
        String jgflId = request.getParameter("jgclassifyId");//监管分类Id
        long jgflIdl = 0;
        if (StringUtil.isNullOrEmpty(jgflId) || jgflId.equals("null")) {
        } else {
            jgflIdl = Long.parseLong(jgflId);
        }
        if (StringUtil.isNullOrEmpty(shxydm)) {
            return;
        }
        //查询条件
        String nameFilter = request.getParameter("key");
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        //字段排序
        String sortField = request.getParameter("sortField");
        String sortOrder = request.getParameter("sortOrder");
        HashMap result = bizShiYsJyjcService.getJgObjectJyjcjgRyList(nameFilter, pageIndex, pageSize, sortField,
                sortOrder, jgflIdl, shxydm, jgstate, sqsId);
        String json = JSON.Encode(result);
        response.getWriter().write(json);
    }

    public void getJgObjectQzrList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String shxydm = request.getParameter("shxydm");
        String jgstate = request.getParameter("jgstate");
        String jgflId = request.getParameter("jgclassifyId");//监管分类Id
        String sqsId = request.getParameter("sqsId");
        long jgflIdL = 0;
        if (StringUtil.isNullOrEmpty(jgflId) || jgflId.equals("null")) {
            //return;
        } else {
            jgflIdL = Long.parseLong(jgflId);
        }
        String userName = (String) request.getSession().getAttribute("userName");
        //查询条件
        String nameFilter = request.getParameter("key");
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        //字段排序
        String sortField = request.getParameter("sortField");
        String sortOrder = request.getParameter("sortOrder");
        HashMap result = bizShiYsJyjcService.getJgObjectQzrList(nameFilter, pageIndex, pageSize, sortField, sortOrder, jgflIdL,
                shxydm, jgstate, sqsId);
        String json = JSON.Encode(result);
        response.getWriter().write(json);
    }

    public void getJynlList(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String shxydm = request.getParameter("shxydm");
        String jgflId = request.getParameter("jgflId");
        String jgstate = request.getParameter("jgstate");
        String sqsId = request.getParameter("sqsId");
        //20180830
        String leibStr = request.getParameter("leib");
        String cpmcStr = request.getParameter("cpmc");
        String cmaorcal = request.getParameter("cmaorcal");

        String dizhi = request.getParameter("key");
        if (!StringUtil.isNullOrEmpty(dizhi)) {
            dizhi = java.net.URLDecoder.decode(dizhi, "utf-8");
        }
        String sporfspid = request.getParameter("sporfspid");
        if (StringUtil.isNullOrEmpty(sporfspid) || "null".equals(sporfspid)) {
            sporfspid = "";
        }
        long jgflIdl = 0;
        if (!StringUtil.isNullOrEmpty(jgflId) && jgflId.equals("null") == false) {
            jgflIdl = Long.parseLong(jgflId);
        }
        //分页
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        //字段排序
        String sortField = request.getParameter("sortField");
        String sortOrder = request.getParameter("sortOrder");
        HashMap map = bizShiYsJyjcService.getJgObjectJyjcJynlList2(pageIndex, pageSize, sortField, sortOrder, jgflIdl, shxydm, jgstate, sqsId, dizhi, sporfspid, leibStr, cpmcStr, cmaorcal);
        ArrayList list = (ArrayList) map.get("data");
		/* if(list.size()>0){
			for(int i=0;i<list.size();i++){ 
				HashMap map1=(HashMap)list.get(i);
				String cpmc1=map1.get("CPMC")==null?"":map1.get("CPMC").toString();
				if(!StringUtil.isNullOrEmpty(cpmc1)){
				ClobToString cts=new ClobToString();
				Clob clob= (Clob)map1.get("CPMC");
				String cpmc=cts.getCloToString(clob);
				map1.remove("CPMC");
				map1.put("CPMC", cpmc);
				}
			}
		} */

        String json = JSON.Encode(map);
        response.getWriter().write(json);
    }

    public void getZsxxList2(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String newShxydm = request.getParameter("newShxydm");
        if (StringUtil.isNullOrEmptyZf(newShxydm)) {
            return;
        }
        ArrayList result = bizShiYsJyjcService.getZsxxList2(newShxydm);
        String json = JSON.Encode(result);
        response.getWriter().write(json);
    }

    public void getJdcXx(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sqsId = request.getParameter("sqsId");
        String zzjgdm = request.getParameter("newShxydm");
        String jgstate = request.getParameter("jgstate");
        ArrayList list = bizShiYsJyjcService.getJdcXx(sqsId, zzjgdm, jgstate);
        String json = JSON.Encode(list);
        response.getWriter().write(json);
    }

    public void getYqsbList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String shxydm = request.getParameter("shxydm");
        String jgflId = request.getParameter("jgflId");
        String jgstate = request.getParameter("jgstate");
        String sqsId = request.getParameter("sqsId");
        String dizhi = request.getParameter("key");
        if (!StringUtil.isNullOrEmpty(dizhi)) {
            dizhi = java.net.URLDecoder.decode(dizhi, "utf-8");
        }
        String sporfspid = request.getParameter("sporfspid");
        //分页
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        //字段排序
        String sortField = request.getParameter("sortField");
        String sortOrder = request.getParameter("sortOrder");
        HashMap map = bizShiYsJyjcService.getJgObjectYqsbXxList(pageIndex, pageSize, sortField, sortOrder, 0l, shxydm, jgstate, sqsId, dizhi, sporfspid);
        String json = JSON.Encode(map);
        response.getWriter().write(json);
    }

    public void getJgObjectFuJianList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String shxydm = request.getParameter("shxydm");
        String jgflId = request.getParameter("jgclassifyId");//监管分类Id
        String propertyId = request.getParameter("propertyId");
        if (StringUtil.isNullOrEmpty(jgflId) || jgflId.equals("null")) {
            return;
        }
        String userName = (String) request.getSession().getAttribute("userName");
        //查询条件
        String nameFilter = request.getParameter("key");
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        //字段排序
        String sortField = request.getParameter("sortField");
        String sortOrder = request.getParameter("sortOrder");
        HashMap result = bizShiYsJyjcService.getJgObjectFuJianList(nameFilter, pageIndex, pageSize, sortField, sortOrder, Long.parseLong(jgflId), shxydm, propertyId);
        String json = JSON.Encode(result);
        response.getWriter().write(json);
    }

    public void getJgObjectCd1(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String shxydm = request.getParameter("shxydm");
        String type = request.getParameter("type");
        ArrayList map = bizShiYsJyjcService.getObjectCd1(shxydm, type);
        String json = JSON.Encode(map);
        response.getWriter().write(json);
    }

    //反馈信息   评审信息
    public void getPsxx(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sqsId = request.getParameter("sqsId");
        String flowId = request.getParameter("flowId");
        String flowInstId = request.getParameter("flowInstId");
        if (StringUtil.isNullOrEmpty(sqsId) && StringUtil.isNullOrEmpty(flowInstId)) {
            return;
        }
        ArrayList list = bizShiYsJyjcService.getPsxxListBySqsId(sqsId, flowId, flowInstId);
        if (list.size() > 0) {
            String json = JSON.Encode(list);
            response.getWriter().write(json);
        } else {
            response.getWriter().write("0");
        }
    }

    public void getPsry(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sqsId = request.getParameter("sqsId");
        String flowId = request.getParameter("flowId");
        String flowInstId = request.getParameter("flowInstId");
        if (StringUtil.isNullOrEmpty(sqsId) && StringUtil.isNullOrEmpty(flowInstId)) {
            return;
        }
        ArrayList list = bizShiYsJyjcService.getPsry(flowId, flowInstId);

        HashMap result = new HashMap();
        String zuyuan = "";
        String zuzhang = "";
        for (int i = 0, l = list.size(); i < l; i++) {
            HashMap record = (HashMap) list.get(i);
            String isheader = record.get("ISHEADER") + "";
            if ("1".equals(isheader)) {
                zuzhang = record.get("TRUENAME") + "";
            }

            HashMap record2 = (HashMap) list.get(list.size() - 1);
            String isheader2 = record2.get("ISHEADER") + "";
            if ("1".equals(isheader2)) {
                if (i < list.size() - 2) {
                    if ("0".equals(isheader)) {
                        zuyuan += record.get("TRUENAME") + ",";
                    }
                    if ("3".equals(isheader)) {
                        zuyuan += record.get("TRUENAME") + ",";
                    }
                } else {
                    if ("0".equals(isheader)) {
                        zuyuan += record.get("TRUENAME") + "";
                    }
                    if ("3".equals(isheader)) {
                        zuyuan += record.get("TRUENAME") + "";
                    }
                }
            } else {
                if (i < list.size() - 1) {
                    if ("0".equals(isheader)) {
                        zuyuan += record.get("TRUENAME") + ",";
                    }
                    if ("3".equals(isheader)) {
                        zuyuan += record.get("TRUENAME") + ",";
                    }
                } else {
                    if ("0".equals(isheader)) {
                        zuyuan += record.get("TRUENAME") + "";
                    }
                    if ("3".equals(isheader)) {
                        zuyuan += record.get("TRUENAME") + "";
                    }
                }

            }
        }
        result.put("zuyuan", zuyuan);
        result.put("zuzhang", zuzhang);

        if (list.size() > 0) {
            String json = JSON.Encode(result);
            response.getWriter().write(json);
        } else {
            response.getWriter().write("0");
        }
    }
%>