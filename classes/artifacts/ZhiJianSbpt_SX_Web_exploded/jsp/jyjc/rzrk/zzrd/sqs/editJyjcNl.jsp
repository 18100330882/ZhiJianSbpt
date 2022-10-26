<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>编辑检验检测能力信息</title>
    <script type="text/javascript" src="<%=basePath %>nui/nui.js"></script>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<body>
<table border="0">
    <tr>
        <td align="left" style="">场地地址：</td>
        <td colspan="">
            <input id="cddz" name="cddz" class="nui-textbox" width="547px" required="true" readonly="readonly"/>
        </td>
        <td>
            <input name="isSp" id="isSp" class="nui-radiobuttonlist" valueField="text" textField="text" repeatItems="2" readonly="readonly"/>
        </td>
    </tr>
</table>
<div>
    <a class="nui-button mini-button-c1" href="javascript:addRow()" iconCls="icon-add">新增</a>
</div>
<div id="dataGrid" class="nui-treegrid" showTreeIcon="true"
     treeColumn="YJFL" idField="ID" parentField="PARENTID" resultAsTree="false"
     allowResize="true" expandOnLoad="true" style="height: 90%"
     allowCellEdit="true" allowCellSelect="true" frozenStartColumn="0" frozenEndColumn="1">
    <div property="columns">
        <div type="indexcolumn"></div>
        <div name="YJFL" field="YJFL" width="200px">一级分类（大分类）
            <input property="editor" class="nui-textbox" style="width:100%;"/>
        </div>
        <div name="EJFL" field="EJFL" width="100px">类别(产品/项目/参数)
            <input property="editor" class="nui-textbox" style="width:100%;"/>
        </div>
        <div name="SORTINGNUMBER" field="SORTINGNUMBER" width="50px" align="center" headerAlign="center">序号[产品/项目/参数]
            <input property="editor" class="nui-textbox" style="width:100%;"/>
        </div>
        <div name="SIJFL" field="SIJFL" width="150px">名称[产品/项目/参数]
            <input property="editor" class="nui-textbox" style="width:100%;"/>
        </div>
        <div name="yjbz" field="YJBZ" width="250px">依据的标准（方法）名称及编号（含年号）
            <input property="editor" class="nui-textbox" style="width:100%;"/>
        </div>
        <div name="LIMITS" field="LIMITS" width="80px" align="center" headerAlign="center">限制范围
            <input property="editor" class="nui-textbox" style="width:100%;"/></div>
        <div name="instructions" field="INSTRUCTIONS" width="80px" align="center" headerAlign="center">说明
            <input property="editor" class="nui-textbox" style="width:100%;"/>
        </div>
        <div width="10%" headerAlign="center" align="center" renderer="onActionRenderer" cellStyle="padding:0;">操作</div>
    </div>
</div>
<script type="text/javascript">
    nui.parse();
    nui.get("isSp").setData("[{'text':'食品'},{'text':'非食品'}]");
    var psbgId = '<%=request.getParameter("id")%>';
    var grid = nui.get("dataGrid");
    grid.load("<%=basePath%>apiShiYsJyjcNl_getDetailList.action?psbgId=" + psbgId);
    grid.reload();

    function onActionRenderer(e) {
        var record = e.record;
        var id = record.ID;
        var result = "";
        // 子节点
        if (e.isLeaf == true) {
            result = '<input class="nui-button mini-button" style="cursor: pointer;width:40px" type="button" value="保存" onclick="saveRow(\'' + id + '\')"/>' +
                '<input class="nui-button mini-button" style="cursor: pointer;margin-left: 15px;width:40px" type="button" value="删除" onclick="deleteRow(\'' + id + '\')"/>';
        }
        return result;
    }

    function addRow() {
        nui.open({
            url: "<%=basePath%>/jsp/jyjc/rzrk/zzrd/sqs/addJyjcNl.jsp",
            title: "新增检验检测能力信息",
            width: '60%',
            height: '70%',
            onload: function () {
                //拿到新窗口对象
                var iframe = this.getIFrameEl();
                //新窗口传递json数据
                var data = {action: "new", psbgId: psbgId};
                //调用新窗口的SetData方法。
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {
                grid.reload();
            }
        });
    }

    function saveRow(param) {
        var row = grid.getSelected();
        var sortNumber = row.SORTINGNUMBER;
        var numberFlag = isNumber(sortNumber);
        if (!numberFlag) {
            nui.alert("序号类型不是数字类型！", "提醒");
            return;
        }
        nui.confirm("确定保存记录？", "提醒!", function (action) {
            if (action == "ok") {
                $.ajax({
                    url: "<%=basePath%>apiShiYsJyjcNl_saveRow.action",
                    type: 'post',
                    cache: 'false',
                    async: false,
                    data: {id: param, psbgId: psbgId, row: nui.encode(row)},
                    success: function (result) {
                        result = nui.decode(result);
                        nui.alert(result.msg, "提醒!", function (e) {
                            location.reload();
                        });

                    },
                });
            }
        });
    }

    function deleteRow(param) {
        nui.confirm("确定删除此记录？", "提醒!", function (action) {
            if (action == "ok") {
                $.ajax({
                    url: "<%=basePath%>apiShiYsJyjcNl_deleteRow.action",
                    type: 'post',
                    cache: 'false',
                    async: false,
                    data: {id: param, psbgId: psbgId},
                    success: function (result) {
                        result = nui.decode(result);
                        nui.alert(result.msg, "提醒!");
                        grid.reload();
                    },
                });
            }
        });
    }

    initLoad();

    function initLoad() {
        $.ajax({
            url: "<%=basePath%>apiShiYsJyjcNl_initLoad.action",
            type: 'post',
            cache: 'false',
            async: false,
            data: {psbgId: psbgId},
            success: function (result) {
                result = nui.decode(result);
                nui.get("isSp").setValue(result.isSp);
                nui.get("cddz").setValue(result.cddz);
            },
        });
    }

    function isNumber(obj) {
        return !isNaN(parseFloat(obj)) && isFinite(obj);
    }
</script>
</body>
</html>