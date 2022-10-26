<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>授权签字人</title>
    <script type="text/javascript" src="<%=basePath %>nui/nui.js"></script>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <style>
        .mini-popupedit {
            width: 80px !important;
        }
    </style>
</head>

<body>
<div style="margin: 0 auto;width:320px; text-align:center;">
    <h1><span>授权签字人</span></h1>
    <input type="hidden" id="serialNumber" class="nui-hidden" name="serialNumber" value=""/>
</div>
<div>
    <a id="addQzr" class="nui-button" iconCls="icon-add" onclick="addQzr()">新增</a>
    <a id="editQzr" class="nui-button" iconCls="icon-edit" onclick="editQzr()">编辑</a>
    <a id="deleteQzr" class="nui-button" iconCls="icon-remove" onclick="deleteQzr()">删除</a>
    <a id="uploadTemp" class="nui-button" iconCls="icon-upload" onclick="uploadQzr()" visible="false">上传模板</a>
    <a id="downLoadTemp" class="nui-button" iconCls="icon-download" onclick="downQzr()" visible="false">下载模板</a>
    <a id="detail" class="nui-button" iconCls="icon-search" onclick="detail()">详情</a>
</div>
<!-- 生成work界面 -->
<div id="dataGrid" style="height:80%;" class="nui-datagrid"
     allowResize="true" sizeList="[50,100,200]" pageSize="50" allowAlternating="true">
    <div property="columns">
        <div type="indexcolumn" width="50" headerAlign="center" align="center">序号</div>
        <div field="NAME" width="100" headerAlign="center" align="center" allowSort="true">姓名</div>
        <div field="SEX" width="100" headerAlign="center" align="center" allowSort="true">性别</div>
        <div visible="false" field="PEOPLETYPE" width="100" headerAlign="center" align="center" allowSort="true">类型
        </div>
        <div field="ADDRESS" width="100" headerAlign="center" align="center" allowSort="true">检验检测机构地址</div>
        <div field="IDCARD" width="100" headerAlign="center" align="center" allowSort="true">身份证号</div>
        <div field="BIRTHDAY" width="100" headerAlign="center" align="center" allowSort="true" dateFormat="yyyy-MM-dd">
            出生年月
        </div>
        <div visible="false" field="POSITION" width="100" headerAlign="center" align="center" allowSort="true">职务</div>
        <div visible="false" field="JOBTITLE" width="100" headerAlign="center" align="center" allowSort="true">职称</div>
        <div visible="false" field="EDUCATION" width="100" headerAlign="center" align="center" allowSort="true">文化程度
        </div>
        <div visible="false" field="DEPARTMENT" width="100" headerAlign="center" align="center" allowSort="true">部门岗位
        </div>
        <div visible="false" field="PHONE" width="100" headerAlign="center" align="center" allowSort="true">电话</div>
        <div visible="false" field="FAX" width="100" headerAlign="center" align="center" allowSort="true">传真</div>
        <div visible="false" field="EMAIL" width="100" headerAlign="center" align="center" allowSort="true">电子邮件</div>
        <div visible="false" field="SIGNATUREFIELD" width="100" headerAlign="center" align="center" allowSort="true">
            申请签字的领域
        </div>
        <div visible="false" field="SCHOOL" width="100" headerAlign="center" align="center" allowSort="true">毕业学校</div>
        <div visible="false" field="PROFESSIONAL" width="100" headerAlign="center" align="center" allowSort="true">专业
        </div>
        <div visible="false" field="JOBCREDENTIALS" width="100" headerAlign="center" align="center" allowSort="true">
            执业资格证书
        </div>
        <div visible="false" field="TRAIN" width="100" headerAlign="center" align="center" allowSort="true">培训</div>
        <div visible="false" field="JOBUNDERGO" width="100" headerAlign="center" align="center" allowSort="true">
            从事检测机构工作的经历
        </div>
        <div visible="false" field="RELEVANTEXPLANATION" width="100" headerAlign="center" align="center"
             allowSort="true">相关说明
        </div>
    </div>
</div>
<script type="text/javascript">
    nui.parse();

    var serialNumber = '<%=request.getParameter("serialNumber")%>';
    var action = '<%=request.getParameter("action")%>';
    var details = "<%=request.getParameter("detail")%>";

    //详情查看
    if (details == "detail") {
        nui.get('addQzr').hide();
        nui.get('editQzr').hide();
        nui.get('deleteQzr').hide();
    }

    nui.get("serialNumber").setValue(serialNumber);

    var grid = nui.get("dataGrid");

    grid.load("<%=basePath%>apiShiYsJyjcQzr_getList.action?serialNumber=" + serialNumber);
    grid.reload();

    function detail() {
        var rows = grid.getSelecteds();
        if (rows.length > 1) {
            nui.alert("不可选择多行记录进行编辑！", "提醒");
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
            url: "<%=basePath%>jsp/jyjc/rzrk/zzrd/sqs/apiQzrDetail.jsp?serialNumber=" + serialNumber + "&action=lookDetails" + "&id=" + row.ID,
            title: "附件管理",
            onload: function () {
                var iframe = this.getIFrameEl();
                var data = {row: row, id: row.ID};//id传递的是本节点
                iframe.contentWindow.SetData(data);
            },
            width: '70%',
            height: 800,
            ondestroy: function (action) {
                grid.reload();//刷新
            }
        });
    }

    //新增
    function addQzr() {
        nui.open({
            url: "<%=basePath%>jsp/jyjc/rzrk/zzrd/sqs/apiQzrDetail.jsp?serialNumber=" + serialNumber + "&action=save",
            title: "新增授权签字人",
            width: '70%',
            height: 800,
            ondestroy: function (action) {
                grid.reload();
            }
        });
    }

    //编辑
    function editQzr() {
        var rows = grid.getSelecteds();
        if (rows.length > 1) {
            nui.alert("不可选择多行记录进行编辑！", "提醒");
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
            url: "<%=basePath%>jsp/jyjc/rzrk/zzrd/sqs/apiQzrDetail.jsp?serialNumber=" + serialNumber + "&action=update" + "&id=" + row.ID,
            title: "附件管理",
            onload: function () {
                var iframe = this.getIFrameEl();
                var data = {row: row, id: row.ID};//id传递的是本节点
                iframe.contentWindow.SetData(data);
            },
            width: '70%',
            height: 800,
            ondestroy: function (action) {
                grid.reload();//刷新
            }
        });
    }

    //删除
    function deleteQzr() {
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
                        url: "<%=basePath%>apiShiYsJyjcQzr_deleteApiQzr.action?idResult=" + roleIds,
                        type: "post",
                        success: function (text) {
                            nui.alert("删除成功！", "提醒", function () {
                                CloseWindow();
                            });
                            grid.reload();
                        },
                        error: function () {
                            nui.alert("删除失败！", function () {
                                CloseWindow();
                            });
                        }
                    });
                }
            });
    }

    //下载
    function downQzr() {
        //101是检验检测授权签字人模板
        var id = 101;
        location.href = "<%=basePath%>jsp/jyjc/rzrk/zzrd/fuJian/JyjcWenShuDownload.jsp?FjID="
            + id;
    }

    //上传附件
    function uploadQzr() {
        nui.open({
            url: "<%=basePath%>jsp/jyjc/rzrk/zzrd/fuJian/JyjcExcel.jsp?serialNumber=" + serialNumber + "&action=jyjcQzr",
            title: "上传",
            width: '60%',
            height: 600,
            ondestroy: function (action) {
                grid.reload();//刷新
            }
        });
    }

    //关闭子窗口，刷新父窗体
    function CloseWindow(action) {
        if (window.CloseOwnerWindow)
            return window.CloseOwnerWindow(action);
        else
            window.close();
    }

    function onCancel(e) {
        CloseWindow("cancel");
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
                    nui.get("addQzr").setEnabled(false);
                    nui.get("editQzr").setEnabled(false);
                    nui.get("deleteQzr").setEnabled(false);
                    nui.get("downLoadTemp").setEnabled(false);
                    nui.get("uploadTemp").setEnabled(false);
                    nui.get("detail").setEnabled(false);
                    return;
                }
                // 封面信息 存在，加载 初始化概况 数据
                initLoad(serialNumber);
            }
        });
    }

    function initLoad(serialNumber) {
        $.ajax({
            url: "<%=basePath%>apiShiYsJyjcQzr_initLoad.action",
            type: 'post',
            data: {serialNumber: serialNumber},
            cache: false,
            success: function (text) {
                var map = nui.decode(text);
                if (map.code == 0) {
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
</script>
</body>
</html>