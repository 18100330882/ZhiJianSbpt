<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>取消检验检测能力审批表</title>
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
    <h1 align="center" style="margin-top:10px">取消检验检测能力审批表</h1>
    <input id="id" name="id" class="nui-hidden"/>
    <input id="changeType" name="changeType" class="nui-hidden"/>
    <fieldset style="border: solid 1px #aaa; padding: 3px; width:100%;">
        <div style="width:100%;">
            <table width="100%">
                <tr>
                    <td align="right">检验检测机构名称：<span style="color:red;">*</span></td>
                    <td colspan="3">
                        <input id="jgName" name="jgName" class="nui-textbox" style="width: 100%" allowInput="false"
                               required="true"/>
                    </td>
                    <td align="right" width="10%">申请日期：<span style="color:red;">*</span></td>
                    <td colspan="2">
                        <input style="width: 50%" id="applyDate" name="applyDate" class="nui-datepicker" required="true"
                               EmptyText="请选择日期" allowInput="false"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">证书编号：<span style="color:red;">*</span></td>
                    <td colspan="3">
                        <input id="cmaPermitNum" name="cmaPermitNum" class="nui-textbox" style="width: 50%"
                               allowInput="true" required="true" readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">联系人：<span style="color:red;">*</span></td>
                    <td colspan="3">
                        <input id="lxrName" name="lxrName" class="nui-textbox" style="width: 50%" allowInput="true"
                               required="true"/>
                    </td>
                    <td align="right" style="">手机：<span style="color:red;">*</span></td>
                    <td colspan="2">
                        <input id="lxrTel" name="lxrTel" class="nui-textbox" style="width: 50%" allowInput="true"
                               required="true"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">通信地址：<span style="color:red;">*</span></td>
                    <td colspan="3">
                        <input id="address" name="address" class="nui-textbox" style="width: 100%" allowInput="true" required="true"/>
                    </td>
                    <td align="right" style="">邮编：<span style="color:red;">*</span></td>
                    <td >
                        <input id="email" name="email" class="nui-textbox" style="width: 50%" allowInput="true"
                               required="true"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">资质认定部门意见：</td>
                    <td colspan="3" height="52px">
                        <input id="deptOpinion" name="deptOpinion" class="nui-textarea" style="width:100%;height: 100%;"
                               allowInput="false"/>
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
                                <%--<a id="edit" class="nui-button" iconCls="icon-edit" onclick="edit()">编辑</a>--%>
                                <a id="remove" class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <div id="listGrid" class="nui-datagrid" style="height: 300px" allowResize="false" idField="ID"
                 multiSelect="true" sizeList="[50,100,500]" showPager="false" pageSize="50"
                 allowAlternating="false">
                <div property="columns">
                    <div field="check" type="checkcolumn" headerAlign="center"></div>
                    <div field="PRODUCTSORTNUM" align="center" headerAlign="center" allowSort="false" width="3%">序号
                    </div>
                    <div field="" align="center" headeralign="center" width="150">
                        类别
                        <div property="columns" align="center">
                            <div field="CATEGORY" name="CATEGORY" width="150" headeralign="center" allowsort="true"
                                 align="center">
                                产品/项目/参数
                            </div>
                        </div>
                    </div>
                    <div field="" align="center" headeralign="center" width="200">
                        产品/项目/参数
                        <div property="columns" align="center">
                            <div field="SORTNUMBG" name="PRODUCTSORTNUM" width="50" headeralign="center"
                                 allowsort="true" align="center">
                                序号
                            </div>
                            <div field="PRODUCTNAME" name="PRODUCTNAME" width="150" headeralign="center"
                                 allowsort="true" align="center">
                                名称
                            </div>
                        </div>
                    </div>
                    <div field="STANDARDNAMEANDNUM" align="center" headerAlign="center" allowSort="false">
                        依据的标准（方法）名称及编号（含年号）
                    </div>
                    <div field="LIMITRANGE" align="center" headerAlign="center">限制范围</div>
                    <div field="LABORATORYPLACE" align="center" headerAlign="center" allowSort="false">所在实验场所</div>
                </div>
            </div>
            <div>
                <a id="save" class="nui-button" iconCls="icon-save" onclick="save()" style="margin-left: 45%;margin-top: 10px">保存</a>
            </div>
            <div><span style="color: red">注：①序号应与原《证书附表》一致；</span></div>
            <div><span style="color: red;margin-left: 28px">②需一并提交取消能力后的新证书附表电子版。</span></div>
        </div>
    </fieldset>

</form>

<script type="text/javascript">
    nui.parse();
    var form = new nui.Form("form");
    var grid = nui.get("listGrid");
    var changeType = "";
    var serialNumber = "<%=request.getParameter("serialNumber")%>";
    var changeType = "<%=request.getParameter("changeType")%>";
    var action = "<%=request.getParameter("action")%>";
    var zsbh = "<%=request.getParameter("zsbh")%>";
    changeApplyInfoInit();

    if ("detail" == action) {
        nui.get("save").hide();
        nui.get("add").hide();
        nui.get("edit").hide();
        nui.get("remove").hide();
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
                    changeType = data.state;
                    nui.get("applyDate").setValue(data.sqrq);
                    nui.get("cmaPermitNum").setValue(data.cmaPermitNumber);
                    nui.get("lxrName").setValue(data.principal);
                    nui.get("lxrTel").setValue(data.phone);
                }
            }
        });
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
                nui.get("cmaPermitNum").setValue(zsbh);
            },
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
                nui.alert(result.msg,"提醒！");
            },
        });
    }

    function add() {
        nui.open({
            url: "<%=basePath%>jsp/jyjc/rzrk/bggl/sqs/selectednlDetails.jsp",
            title: "取消检验检测能力审批表",
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

    function edit() {
        var rows = grid.getSelecteds();
        if (rows.length != 1) {
            nui.alert("请选择一条记录编辑！", "提醒！");
            return;
        }
        nui.open({
            url: "<%=basePath%>jsp/jyjc/rzrk/bggl/sqs/cancelNlDetails.jsp",
            title: "取消检验检测能力审批表",
            width: '50%',
            height: '65%',
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
</script>
</body>
</html>