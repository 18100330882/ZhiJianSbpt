<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>检验检测机构资质认定标准（方法）变更审批表</title>
    <script type="text/javascript" src="<%=basePath%>nui/nui.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>

<body>
<form id="form" target="id_iframe" style="padding-top:5px;margin:0 auto; border:1px;width: 95%">
    <h1 align="center" style="margin-top:10px">检验检测机构资质认定标准（方法）变更审批表</h1>
    <input id="id" name="id" class="nui-hidden"/>
    <input id="changeType" name="changeType" class="nui-hidden"/>
    <fieldset style="border: solid 1px #aaa; padding: 3px; width:95%;">
        <table style="width:100%;">
            <tr>
                <td align="right">检验检测机构名称：</td>
                <td>
                    <input id="jgName" name="jgName" class="nui-textbox" style="width:100%" allowInput="false"/>
                </td>
                <td align="right" width="10%">申请日期：<span style="color:red;">*</span></td>
                <td>
                    <input id="applyDate" name="applyDate" class="nui-datepicker" required="true" EmptyText="请选择日期" allowInput="false" required="true" width="50%"/>
                </td>
            </tr>
            <tr>
                <td align="right" style="">联系人：<span style="color:red;">*</span></td>
                <td>
                    <input id="lxrName" name="lxrName" class="nui-textbox" width="50%" allowInput="true" required="true"/>
                </td>
                <td align="right" style="">电话：<span style="color:red;">*</span></td>
                <td>
                    <input id="lxrTel" name="lxrTel" class="nui-textbox" width="50%" allowInput="true" required="true"/>
                </td>
            </tr>
            <tr>
                <td align="right" style="">自我承诺：</td>
                <td colspan="3">本次变更不涉及实际能力变化，本机构承诺已具备新标准（方法）所需相应资质认定条件，并对承诺的真实性负责。</td>
            </tr>
            <tr>
                <td align="right" style="">本机构技术负责人审查意见：<span style="color:red;">*</span></td>
                <td height="52px">
                    <input id="fzrReviewOpinion" name="fzrReviewOpinion" class="nui-textarea" style="width: 100%;height: 100%" required="true"/>
                </td>
                <td align="right" style="">日期：<span style="color:red;">*</span></td>
                <td>
                    <input width="50%" id="fzrReviewOpinionDate" name="fzrReviewOpinionDate" class="nui-datepicker" required="true" EmptyText="请选择日期" allowInput="false"/>
                </td>
            </tr>
            <tr>
                <td align="right" style="">专业技术评价组织/专家审查意见：<span style="color:red;">*</span></td>
                <td height="52px">
                    <input id="zjReviewOpinion" name="zjReviewOpinion" class="nui-textarea" style="width: 100%;height: 100%" required="true"/>
                </td>
                <td align="right" style="">日期：<span style="color:red;">*</span></td>
                <td>
                    <input id="zjReviewOpinionDate" name="zjReviewOpinionDate" class="nui-datepicker" width="50%"
                           required="true" EmptyText="请选择日期" allowInput="false"/>
                </td>
            </tr>
            <tr>
                <td align="right" style="">资质认定部门意见：</td>
                <td height="52px">
                    <input id="deptOpinion" name="deptOpinion" class="nui-textarea" style="width:100%;height: 100%;"
                    />
                </td>
                <td align="right" style="">日期：</td>
                <td>
                    <input id="deptOpinionDate" name="deptOpinionDate" class="nui-datepicker" style="width:50%;"
                           allowInput="false"/>
                </td>
            </tr>
        </table>
        <div style="">
            <div class="nui-toolbar" style="border-bottom:0;padding:0px;">
                <table style="">
                    <tr>
                        <td style="">
                            <a id="add" class="nui-button" iconCls="icon-add" onclick="add()">选择</a>
                            <a id="edit" class="nui-button" iconCls="icon-edit" onclick="edit()">编辑</a>
                            <a id="remove" class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div id="listGrid" class="nui-datagrid" style="height: 300px" allowResize="false" idField="ID" multiSelect="true" sizeList="[50,100,500]" showPager="false" pageSize="50"
             allowAlternating="false">
            <div property="columns">
                <div field="check" type="checkcolumn" headerAlign="center"></div>
                <div field="SORTNUM" align="center" headerAlign="center" allowSort="true" width="3%">序号</div>
                <div field="" align="center" headeralign="center" width="200">
                    类别
                    <div property="columns" align="center">
                        <div field="CATEGORY" name="CATEGORY" width="50" headeralign="center" allowsort="true" align="center">
                            (产品/项目/参数)
                        </div>
                    </div>
                </div>
                <div field="" align="center" headeralign="center">
                    已批准的标准（方法）
                    <div property="columns" align="center">
                        <div field="STANDARDNAMEANDNUMOLD" name="STANDARDNAMEANDNUMOLD"  headeralign="center" allowsort="true" align="center">
                            名称、 编号（含年号）
                        </div>
                    </div>
                </div>
                <div field="" align="center" headeralign="center" >
                    变更后的标准（方法）
                    <div property="columns" align="center">
                        <div field="STANDARDNAMEANDNUMNEW" name="STANDARDNAMEANDNUMNEW"  headeralign="center" allowsort="true" align="center">
                            名称、 编号（含年号）
                        </div>
                    </div>
                </div>
                <div field="LIMITRANGE" align="center" headerAlign="center">限制范围</div>
                <div field="CHANGECONTENT" align="center" headerAlign="center">变更内容</div>
            </div>
        </div>
        <div><span style="color: red">注：①“序号、资质认定项目名称”应与《证书附表》一致；</span></div>
        <div><span style="color: red;margin-left: 28px">②如标准（方法）仅为年号、编号变化，或变更的内容不涉及实际检验检测能力变化，可填写此表；</span></div>
        <div><span style="color: red;margin-left: 28px">③机构如选择自我承诺的方式，资质认定部门无需组织专业技术评价组织/专家审查，直接批准，在后续监督管理中对被审批单位承诺内容是否属实进行检查，发现承诺内容不实，资质认定部门将撤销审批决定，并将相关情况记入诚信档案。</span></div>
    </fieldset>
    <div>
        <a id="save" class="nui-button" iconCls="icon-save" onclick="save()" style="margin-left: 45%;">保存</a>
    </div>
</form>

<script type="text/javascript">
    nui.parse();
    var form = new nui.Form("form");
    var serialNumber = "<%=request.getParameter("serialNumber")%>";
    var action = "<%=request.getParameter("action")%>";
    var changeType = "<%=request.getParameter("changeType")%>";
    var grid = nui.get("listGrid");

    changeApplyInfoInit();

    if ("detail" == action) {
        nui.get("save").hide();
        nui.get("add").hide();
        nui.get("edit").hide();
        nui.get("remove").hide();
    }

    function changeApplyInfoInit() {
        grid.load("<%=basePath%>changeApplyInfo_changeApplyInfoDetailsList.action?serialNumber=" + serialNumber + "&changeType=" + changeType);
        grid.reload();

        $.ajax({
            url: "<%=basePath%>changeApplyInfo_changeApplyInfoInit.action",
            type: 'post',
            cache: 'false',
            async: false,
            data: {serialNumber: serialNumber, changeType: changeType},
            success: function (result) {
                result = nui.decode(result);
                var resultData = result.data;
                if (resultData.total == 0) {
                    fmxxInitLoad(serialNumber);
                    return;
                }
                form.setData(resultData.data);
            },
        });
    }

    function add() {
        nui.open({
            url: "<%=basePath%>jsp/jyjc/rzrk/bggl/sqs/selectednlDetails.jsp",
            title: "检验检测机构资质认定标准（方法）变更",
            width: '80%',
            height: '100%',
            onload: function () {
                //拿到新窗口对象
                var iframe = this.getIFrameEl();
                //新窗口传递json数据
                var data = {changeType: changeType, serialNumber: serialNumber};
                //调用新窗口的SetData方法。
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {
                grid.reload();
            }
        });
    }

    function remove() {
        var rows = grid.getSelecteds();
        if (rows.length == 0) {
            nui.alert("请选择一条记录删除！", "提醒！");
            return;
        }
        nui.confirm("确定删除选中记录？", "系统提示", function (action) {
            if (action == "ok") {
                var ids = "";
                for (var i = 0; i < rows.length; i++) {
                    ids += (rows[i].ID) + ",";
                }
                $.ajax({
                    url: "<%=basePath%>changeApplyInfo_changeApplyInfoDetailsDelete.action",
                    type: "post",
                    data: {changeType: changeType, ids: ids},
                    success: function (text) {
                        nui.alert("删除成功！", "提醒");
                        grid.reload();
                    },
                    error: function () {
                        nui.alert("删除失败！");
                    }
                });
            }
        });
    }

    function edit() {
        var rows = grid.getSelecteds();
        if (rows.length != 1) {
            nui.alert("请选择一条记录编辑！", "提醒！");
            return;
        }
        nui.open({
            url: "<%=basePath%>jsp/jyjc/rzrk/bggl/sqs/standardChangeDetails.jsp",
            title: "检验检测机构资质认定标准（方法）变更",
            width: '80%',
            height: '100%',
            onload: function () {
                //拿到新窗口对象
                var iframe = this.getIFrameEl();
                //新窗口传递json数据
                var data = {changeType: changeType, serialNumber: serialNumber, id: rows[0].ID};
                //调用新窗口的SetData方法。
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {
                grid.reload();
            }
        });
    }

    function fmxxInitLoad(serialNumber) {
        $.ajax({
            url: "<%=basePath%>bizApiZwcnBg_initLoad.action",
            type: 'post',
            data: {serialNumber: serialNumber},
            cache: false,
            success: function (text) {
                var map = nui.decode(text);
                if (map.code == 0) {
                    var data = map.data;
                    nui.get("jgName").setValue(data.jgmc);
                    nui.get("changeType").setValue(data.state);
                    nui.get("lxrName").setValue(data.principal);
                    nui.get("lxrTel").setValue(data.phone);
                    nui.get("applyDate").setValue(data.sqrq);
                }
            }
        });
    }

    function save() {
        var formData = form.getData();
        form.validate();
        if (form.isValid() == false) return;
        var reqJson = nui.encode(formData);
        $.ajax({
            url: "<%=basePath%>changeApplyInfo_changeApplyInfoSave.action",
            type: 'post',
            cache: 'false',
            async: false,
            data: {data: reqJson, serialNumber: serialNumber},
            success: function (result) {
                result = nui.decode(result);
                nui.alert(result.msg, "提醒！");
            },
        });
    }
</script>
</body>
</html>