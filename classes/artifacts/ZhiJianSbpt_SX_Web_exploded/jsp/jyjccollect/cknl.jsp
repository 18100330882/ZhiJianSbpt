<%@ page language="Java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>标题</title>
    <script type="text/javascript" src="<%=basePath%>nui/nui.js"></script>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <style type="text/css">
    </style>
</head>
<body>
<div style="width:100%;">
    <div class="nui-toolbar" style="border-bottom:0;padding:0px;">
        <table style="width:100%;">
            <tr>
                <td style="width: 100px;white-space:nowrap">
                    <a id="delnl" class="nui-button" iconCls="icon-remove" onclick="delnl()">删除能力信息</a>
                    <a id="updatenl" class="nui-button" iconCls="icon-edit" onclick="updatenl()">能力修改</a>
                </td>
                <td id="query" style="white-space:nowrap">
                    <input id="cp" class="nui-textbox" emptyText="请输入项目名称" style="width:150px;"/>
                    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
                </td>
            </tr>
        </table>
    </div>
</div>
<div id="datagrid" class="nui-datagrid" style="width:100%;height:90%;" showPager="true" showHGridLines="true"
     allowAlternating="true" sizeList="[50,100,500]" pageSize="50"
     idField="ID" multiSelect="true">
    <div property="columns">
        <div type="checkcolumn" headerAlign="center"></div>
        <div type="indexcolumn" headerAlign="center">序号</div>
        <!-- allSort是否支持排序 -->
        <div field="YJFL" headerAlign="center" allowSort="true">一级分类</div>
        <div field="EJFL" headerAlign="center" allowSort="true">二级分类</div>
        <div field="PRODUCTNAME" headerAlign="center" allowSort="true">项目名称</div>
        <div field="YJBZ" headerAlign="center" allowSort="true">标准</div>
        <div field="LIMITS" headerAlign="center" allowSort="true">限制范围</div>
        <div field="INSTRUCTIONS" headerAlign="center" allowSort="true">说明</div>
        <div field="PIZHUNDATE" align="center" headerAlign="center" dateFormat="yyyy-MM-dd" allowSort="true">批准日期</div>
    </div>
</div>

<script type="text/javascript">
    nui.parse();
    var zzrdzsbh = '<%=request.getParameter("zzrdzsbh")%>';
    var type = '<%=request.getParameter("type")%>';
    var grid = nui.get("datagrid");
    var url = "<%=basePath%>collect_cknl.action?zzrdzsbh=" + zzrdzsbh;
    grid.load(url);
    grid.reload();

    function delnl() {
        var rows = grid.getSelecteds();
        if (rows.length > 0) {
            nui.confirm('确认删除吗', '提示', function (action) {
                if (action == 'ok') {
                    var ids = '';
                    for (var i = 0; i < rows.length; i++) {
                        ids += ',' + rows[i].ID;
                    }
                    var idslice = ids.slice(1);
                    $.ajax({
                        url: "<%=basePath%>apiShiYsJyjcNl_delnl.action",
                        type: 'post',
                        cache: 'false',
                        async: false,
                        data: {params: idslice},
                        success: function (result) {
                            nui.alert('删除成功', "提示!");
                            grid.reload();
                        }
                    });
                }
            })
        }
    }


    function ckfj() {
        var rows = grid.getSelecteds();

        if (rows.length > 1) {
            nui.alert('至多只能同时查看一个附件');
            return
        }
        if (rows.length < 1) {
            nui.alert('请选中一个附件');
            return
        }
        row = rows[0];
        nui.open({
            url: "<%=basePath%>jsp/postSupervise/ckFujian.jsp",
            title: "文件查看", width: 1200, height: 800,
            onload: function () {
                var iframe = this.getIFrameEl();
                var data = {row: row, type: type};
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {
                grid.reload();
            }
        });
    }

    function search() {
        var cp = nui.get("cp").getValue();
        grid.load({zzrdzsbh: zzrdzsbh, cp: cp});
        grid.reload();
    }

    function updatenl() {
        var row = grid.getSelected();
        if (undefined == row) {
            nui.alert('请选择一条数据', '系统提示');
            return;
        }
        nui.open({
            url: "<%=basePath%>jsp/jyjccollect/pageNl.jsp",
            title: "修改能力信息", width: 1200, height: 700,
            onload: function () {
                var iframe = this.getIFrameEl();
                var data = {row: row};
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
