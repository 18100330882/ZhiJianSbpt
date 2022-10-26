<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>检验检测机构人员变更备案审批表</title>
    <script type="text/javascript" src="<%=basePath%>nui/nui.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>

<body>
<form id="form" target="id_iframe" style="padding-top:5px;margin:0 auto; width:95%; border:1px;">
    <h1 align="center" style="margin-top:10px">检验检测机构人员变更备案审批表</h1>
    <input id="id" name="id" class="nui-hidden"/>
    <input id="changeType" name="changeType" class="nui-hidden"/>
    <fieldset style="border: solid 1px #aaa; padding: 3px; width:95%;">
        <div style="width:100%;">
            <table>
                <tr>
                    <td align="right" style="">检验检测机构名称：<span style="color:red;">*</span></td>
                    <td>
                        <input id="jgName" name="jgName" class="nui-textbox" width="100%" allowInput="true"
                               required="true"/>
                    </td>
                    <td align="right" width="10%">申请日期：<span style="color:red;">*</span></td>
                    <td>
                        <input id="applyDate" name="applyDate" class="nui-datepicker" width="50%" required="true"
                               EmptyText="请选择日期" allowInput="false"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">自我声明（适用于替换、新增技术负责人和授权签字人时）：</td>
                    <td align="left" colspan="4">
                        本机构自我声明，变更后的技术负责人和授权签字人符合《检验检测机构资质认定评审准则》的要求，并对真实性负责。
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">联系人：<span style="color:red;">*</span></td>
                    <td>
                        <input id="lxrName" name="lxrName" class="nui-textbox" width="50%" allowInput="true"
                               required="true"/>
                    </td>
                    <td align="right" style="">手机：<span style="color:red;">*</span></td>
                    <td>
                        <input id="lxrTel" name="lxrTel" class="nui-textbox" width="50%" allowInput="true"
                               required="true"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">通信地址：<span style="color:red;">*</span></td>
                    <td>
                        <input id="address" name="address" class="nui-textbox" width="100%" allowInput="true"
                               required="true"/>
                    </td>
                    <td align="right" style="">邮编：<span style="color:red;">*</span></td>
                    <td>
                        <input id="email" name="email" class="nui-textbox" width="50%" allowInput="true"
                               required="true"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="">资质认定部门意见：</td>
                    <td height="52px">
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
            <fieldset style="border: solid 1px #aaa; padding: 3px;">
                <div style="">
                    <div class="nui-toolbar" style="border-bottom:0;padding:0px;">
                        <table style="">
                            <tr>
                                <td style="">
                                    <a id="add" class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
                                    <a id="edit" class="nui-button" iconCls="icon-edit" onclick="edit()">编辑</a>
                                    <a id="remove" class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div id="listGrid" class="nui-datagrid" style="height: 50%" allowResize="false" idField="ID"
                     multiSelect="true" sizeList="[50,100,500]" showPager="false" pageSize="50"
                     allowAlternating="false">
                    <div property="columns">
                        <div field="check" type="checkcolumn" headerAlign="center"></div>
                        <div type="indexcolumn" headerAlign="center">序号</div>
                        <div field="ZHIC" align="center" headerAlign="center" allowSort="true">职称</div>
                        <div field="ZHIW" align="center" headerAlign="center" allowSort="true">职务</div>
                        <div field="NAME" align="center" headerAlign="center" allowSort="true">变更前人员姓名</div>
                        <div field="NAMEAFTER" align="center" headerAlign="center" allowSort="true">变更后人员姓名</div>
                        <div field="CHANGETYPE" align="center" headerAlign="center" allowSort="true">变更类型</div>
                    </div>
                </div>
            </fieldset>
            <div><span style="color: red">注：①此表一式二份，检验检测机构和资质认定部门分别留存；</span></div>
            <div><span style="color: red;margin-left: 28px">②职务类型包括最高管理者、技术负责人、授权签字人，变更类型包括：替换、新增、撤销；</span></div>
            <div><span style="color: red;margin-left: 28px">③最高管理者变更时，需同时提供相关任命文件及法人授权书，无需批准，直接备案；</span></div>
            <div><span style="color: red;margin-left: 28px">⑤授权签字人变更时，需同时提供授权签字人申请表，经批准后，可签发检验检测报告或证书。</span></div>

        </div>
    </fieldset>
    <div>
        <a id="save" class="nui-button" iconCls="icon-save" onclick="save()" style="margin-left: 45%;margin-top: 10px">保存</a>
    </div>
</form>

<script type="text/javascript">
    nui.parse();
    var form = new nui.Form("form");
    var changeType = "<%=request.getParameter("changeType")%>";
    var grid = nui.get("listGrid");
    var serialNumber = "<%=request.getParameter("serialNumber")%>";
    var action = "<%=request.getParameter("action")%>";
    var zsbh = "<%=request.getParameter("zsbh")%>";
    if ("detail" == action) {
        nui.get("save").hide();
        nui.get("add").hide();
        nui.get("edit").hide();
        nui.get("remove").hide();
    }
    changeApplyInfoInit();

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

                    zsbh = data.cmaPermitNumber;
                    nui.get("applyDate").setValue(data.sqrq);
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
                nui.alert(result.msg, "提醒！");
            },
        });
    }

    function add() {
        nui.open({
            url: "<%=basePath%>jsp/jyjc/rzrk/bggl/sqs/personChangeDetails.jsp",
            title: "检验检测机构人员变更备案审批表",
            width: '80%',
            height: '100%',
            onload: function () {
                //拿到新窗口对象
                var iframe = this.getIFrameEl();
                //新窗口传递json数据
                var data = {changeType: changeType, serialNumber: serialNumber, zsbh: zsbh};
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
            url: "<%=basePath%>jsp/jyjc/rzrk/bggl/sqs/personChangeDetails.jsp",
            title: "检验检测机构人员变更备案审批表",
            width: '80%',
            height: '100%',
            onload: function () {
                //拿到新窗口对象
                var iframe = this.getIFrameEl();
                //新窗口传递json数据
                var data = {changeType: changeType, serialNumber: serialNumber, id: rows[0].ID, zsbh: zsbh};
                //调用新窗口的SetData方法。
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {
                grid.reload();
            }
        });
    }
</script>
</body>
</html>