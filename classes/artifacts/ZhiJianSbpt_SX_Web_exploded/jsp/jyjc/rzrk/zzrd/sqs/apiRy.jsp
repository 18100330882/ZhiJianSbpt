<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>检验检测人员</title>
    <script type="text/javascript" src="<%=basePath %>nui/nui.js"></script>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <style type="text/css">
        .myrowred {
            color: #FF0000; /* 红色 */
        }

        .mini-popupedit {
            width: 80px !important;
        }
    </style>
</head>

<body>
<div style="margin: 0 auto;width:320px;text-align:center;">
    <h1><span>检验检测机构人员信息</span></h1>
</div>
<div>
    <a id="add" class="nui-button" iconCls="icon-add" onclick="add()">新增</a>
    <a id="edit" class="nui-button" iconCls="icon-edit" onclick="edit()">编辑</a>
    <a id="delete" class="nui-button" iconCls="icon-remove" onclick="deleteRy()">删除</a>
    <a id="uploadRy" class="nui-button" iconCls="icon-upload" onclick="uploadRy()">上传模板</a>
    <a id="down" class="nui-button" iconCls="icon-download" onclick="downRy()">下载模板</a>
    <a id="search" class="nui-button" iconCls="icon-search" onclick="detail()">详情</a>
</div>
<!-- 生成work界面 -->
<div id="datagrid" style=" height:69%;" class="nui-datagrid" allowResize="true" sizeList="[100,500,1000]"
     pageSize="100">
    <input type="hidden" id="serialNumber" class="nui-hidden" name="serialNumber" value=""/>
    <div property="columns">
        <div type="indexcolumn" width="50" headerAlign="center" align="center">序号</div>
        <div field="NAME" width="100" headerAlign="center" align="center" allowSort="true" renderer="isRed">姓名</div>
        <div field="SEX" width="100" headerAlign="center" align="center" allowSort="true">性别</div>
        <div field="AGE" width="100" headerAlign="center" align="center" allowSort="true">年龄</div>
        <div field="EDUCATION" width="100" headerAlign="center" align="center" allowSort="true">文化程度</div>
        <div field="PROFESSIONAL" width="100" headerAlign="center" align="center" allowSort="true">所学专业</div>
        <div visible="false" field="documentType" width="100" headerAlign="center" align="center">证件类型</div>
        <div visible="false" field="idCard" width="100" headerAlign="center" align="center" allowSort="true">证件编号</div>
        <div visible="false" field="position" width="100" headerAlign="center" align="center" allowSort="true">职务</div>
        <div visible="false" field="jobTitle" width="100" headerAlign="center" align="center" allowSort="true">职称</div>
        <div visible="false" field="technicalFieldYear" width="100" headerAlign="center" align="center"
             allowSort="true">从事本技术领域年限
        </div>
        <div visible="false" field="department" width="100" headerAlign="center" align="center" allowSort="true">部门岗位
        </div>
        <div visible="false" width="100" headerAlign="center" align="center" allowSort="true">检验检测机构地址</div>
    </div>
</div>
<script type="text/javascript">
    nui.parse();
    var serialNumber = '<%=request.getParameter("serialNumber")%>';
    var action = '<%=request.getParameter("action")%>';
    var jgmc = '';
    var grid = nui.get("datagrid");
    var details = "<%=request.getParameter("detail")%>";


    grid.load("<%=basePath%>apiShiYsJyjcRy_getList.action?serialNumber=" + serialNumber);
    grid.reload();

    //详情查看
    if (details == "detail") {
        nui.get('add').hide();
        nui.get('edit').hide();
        nui.get('delete').hide();
        nui.get('uploadRy').hide();
        nui.get('down').hide();
    }
    //显示
    function isRed(e) {
        var record = e.record;
        return record.NAME;
    }

    function detail() {
        var rows = grid.getSelecteds();
        if (rows.length > 1) {
            nui.alert("不可选择多行记录！", "提醒");
            return;
        }
        if (rows.length == 0) {
            nui.alert("请选中一条记录");
            return;
        }
        var row = grid.getSelected();
        if (row == null || row == "") {
            nui.alert("请选中一条记录");
            return;
        }
        nui.open({
            url: "<%=basePath%>jsp/jyjc/rzrk/zzrd/sqs/apiRyDetailxg.jsp?serialNumber=" + serialNumber + "&zt=look" + "&id=" + row.ID,
            title: "人员详情",
            onload: function () {
                var iframe = this.getIFrameEl();
                var data = {row: row, action: "lookDetails", serialNumber: serialNumber};//id传递的是本节点
                iframe.contentWindow.SetData(data);
            },
            width: '50%',
            height: 600,
            ondestroy: function (action) {
                grid.reload();//刷新
            }
        });
    }

    // 初始化 验证 封面信息数据
    initCheck();

    function initCheck() {
        $.ajax({
            url: "<%=basePath%>bizApiShiysjyjc_initLoad.action",
            type: 'post',
            cache: 'false',
            async: false,
            data: {serialNumber: serialNumber},
            success: function (resultData) {
                resultData = nui.decode(resultData);
                // 没有查询到数据
                if (resultData.code != 0) {
                    nui.alert(resultData.msg);
                    nui.get("add").setEnabled(false);
                    nui.get("edit").setEnabled(false);
                    nui.get("delete").setEnabled(false);
                    nui.get("uploadRy").setEnabled(false);
                    nui.get("down").setEnabled(false);
                    nui.get("search").setEnabled(false);
                    return;
                }
                // 封面信息 存在，加载 初始化概况 数据
                initLoad(serialNumber);
            }
        });
    }

    function initLoad(serialNumber) {
        $.ajax({
            url: "<%=basePath%>apiShiYsJyjcRy_initLoad.action",
            type: 'post',
            data: {serialNumber: serialNumber},
            cache: false,
            success: function (text) {
                var map = nui.decode(text);
                if (map.code == 0) {
                    //form.setData(map.data);
                    return;
                }
                if ("edit" == action) {
                    nui.alert(map.msg, "提示!");
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {//如果不能获得数据
                nui.alert(jqXHR.responseText);//弹出错误
                CloseWindow();
            }
        });
    }

    //新增
    function add() {
        nui.open({
            url: "<%=basePath%>jsp/jyjc/rzrk/zzrd/sqs/apiRyDetail.jsp?serialNumber=" + serialNumber + "&zt=save",
            title: "附件管理",
            width: '50%',
            height: 600,
            ondestroy: function (action) {
                grid.reload();//刷新
            }
        });
    }

    //编辑
    function edit() {
        var rows = grid.getSelecteds();
        if (rows.length > 1) {
            nui.alert("不可选择多行记录进行编辑！", "提醒");
        } else {
            var row = grid.getSelected();//拿到选中的行
            if (row) {
                nui.open({
                    url: "<%=basePath%>jsp/jyjc/rzrk/zzrd/sqs/apiRyDetailxg.jsp?serialNumber=" + serialNumber + "&zt=update" + "&id=" + row.ID,
                    title: "附件管理",
                    onload: function () {
                        var iframe = this.getIFrameEl();
                        var data = {row: row, id: row.ID};//id传递的是本节点
                        iframe.contentWindow.SetData(data);
                    },
                    width: '50%',
                    height: 600,
                    ondestroy: function (action) {
                        grid.reload();//刷新
                    }
                });

            } else {
                nui.alert("请选中一条记录");
            }
        }
    }

    //删除
    function deleteRy(e) {

        var rows = grid.getSelecteds();
        if (!rows.length > 0) {
            nui.alert("请选中一条记录", "提醒");
            return;
        }
        nui.confirm("确定删除选中记录？", "系统提示",
            function (action) {
                if (action == "ok") {
                    var roleIds = [];
                    for (var i = 0; i < rows.length; i++) {
                        roleIds.push(rows[i].ID);
                    }
                    roleIds.join(",");
                    $.ajax({
                        url: "<%=basePath%>apiShiYsJyjcRy_deleteApiRy.action?idResult=" + roleIds,
                        type: "post",
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

    //下载
    function downRy() {
        //100是检验检测人员模板
        /*var id = 100;
        location.href = "<%=basePath%>jsp/jyjc/rzrk/zzrd/fuJian/JyjcWenShuDownload.jsp?FjID=" + id;*/
        var title = "检验检测机构人员信息"
        title=decodeURI(decodeURI(title));
        location.href ="<%=basePath%>jsp/jyjc/rzrk/zzrd/fuJian/JgryDownLoadTemp.jsp?title="+encodeURI(encodeURI(title))

    }

    //上传附件
    function uploadRy() {
        nui.open({
            url: "<%=basePath%>jsp/jyjc/rzrk/zzrd/fuJian/JyjcExcel.jsp?serialNumber=" + serialNumber + "&action=jyjcRy",
            title: "上传",
            width: '60%',
            height: 600,
            ondestroy: function (action) {
                grid.reload();//刷新
            }
        });
    }
</script>
</body>
</html>