<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
    <base href="<%=basePath%>">
    <title>选择内容</title>
    <script type="text/javascript" src="<%=basePath %>nui/nui.js"></script>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<body>
<div>
    <a class="nui-button mini-button-c1" onclick="saveSelected()" iconCls="icon-save">确认选择</a>
</div>
<div id="dataGrid" class="nui-datagrid" style="width:100%;height:95%;" allowResize="false" idField="ID"
     multiSelect="true" sizeList="[50,100,500]"
     showPager="true" pageSize="50" allowAlternating="true">
    <div property="columns">
        <div field="check" type="checkcolumn" headerAlign="center"></div>
        <div field="BIGNUMBER" width="30px" headeralign="center">序号</div>
<%--        <div name="yjfl" field="YJFL" width="80px" headeralign="center">一级分类</div>--%>
        <div field="" align="center" headeralign="center" width="200">
            类别
            <div property="columns" align="center">
                <div field="EJFL" width="100" headeralign="center" allowsort="true" align="center">
                    (产品/项目/参数)
                </div>
            </div>
        </div>
        <div field="" align="center" headeralign="center" width="150">
            产品/项目/参数
            <div property="columns" align="center">
                <div field="SORTINGNUMBER" name="" width="50" headeralign="center" allowsort="true" align="center">
                    序号
                </div>
                <div field="PRODUCTNAME" name="" width="100" headeralign="center" allowsort="true" align="center">
                    名称
                </div>
            </div>
        </div>

        <div field="YJBZ" name="" width="20%" headeralign="center" allowsort="true" align="center">
            依据的标准名称
        </div>

        <div name="" field="LIMITS" width="80px" align="center" headerAlign="center">限制范围</div>
        <div name="" field="SITENAME" width="80px" align="center" headerAlign="center">场所地址</div>
        <div name="" field="PIZHUNDATE" width="80px" align="center" headerAlign="center" dateFormat="yyyy-MM-dd">批准日期</div>
    </div>
</div>
</body>
<script type="text/javascript">
    nui.parse();
    var grid = nui.get("dataGrid");
    var changeType = "";
    var serialNumber = "";

    function SetData(data) {
        changeType = data.changeType;
        serialNumber = data.serialNumber;
        $.ajax({
            url: "<%=basePath%>bizApiZwcnBg_initLoad.action",
            type: 'post',
            data: {serialNumber: serialNumber},
            cache: false,
            success: function (text) {
                var map = nui.decode(text);
                var data = map.data;
                var zzrdzsbh = data.cmaPermitNumber;
                grid.load("<%=basePath%>changeApplyInfo_nlDetailsByzsbh.action?zzrdzsbh=" + zzrdzsbh + "&changeType=" + changeType);
                grid.reload();
            }
        });
    }

    function saveSelected() {
        var rows = grid.getSelecteds();
        if (rows == null || rows.length < 1) {
            nui.alert("请至少选择一条记录！", "提醒");
            return
        }

        $.ajax({
            url: "<%=basePath%>changeApplyInfo_saveStandardchangeCancelnlDetails.action",
            type: 'post',
            data: {data: nui.encode(rows), changeType: changeType, serialNumber: serialNumber},
            cache: false,
            success: function (result) {
                result = nui.decode(result);
                nui.alert(result.msg, "提醒!", function (e) {
                    CloseWindow();
                });
            }
        });
    }

    function CloseWindow() {
        if (window.CloseOwnerWindow) {
            return window.CloseOwnerWindow("cancel");
        } else {
            window.close();
        }
    }
</script>