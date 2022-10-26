<%@ page language="Java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String userName = request.getSession().getAttribute("userName").toString();
%>
<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">
    <title>最近评审记录</title>
    <script type="text/javascript" src="<%=basePath%>nui/nui.js"></script>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<body>
<div class="status" style="min-height: 350px;">
    <table width="100%">
        <tr>
            <td width="160">
                <img src="<%=basePath%>style/images/user_his.png" class="img1"/>
                <b style="color: rgb(17, 68, 119); font-size: 1.17em;">最近审批记录</b>
            </td>
        </tr>
    </table>
</div>
<script type="text/javascript">
    nui.parse();
    var grid = nui.get("datagrid1");

    //flag行绑定
    function onGenderFlag(e) {
        var a = e.value;
        var record = e.record;
        var flag = "";
        if (a == 0) {
            flag = "草稿";
        }
        if (a == 1) {
            flag = "提交待审核";
        }
        if (a == 2 || a == 5) {
            nui.ajax({
                url: "<%=basePath%>flowNodes_getNodeMcByFlowInstId.action",
                async: false,
                type: 'POST',
                data: {
                    flowInstId: record.FLOWINSTID,
                    flowId: record.FLOWID
                },
                success: function (text) {

                    if (text.trim() != "0") {
                        var result = nui.decode(text.trim());
                        flag = result.NODEMC;
                    }
                }
            });
        }
        if (a == 3) {
            flag = "已打回";
        }
        if (a == 4) {
            flag = "流程中的打回";
        }
        if (a == 6) {
            flag = "已办结";
        }
        return flag;
    }

    var tableName = "";

    //获取表名
    function onGenderTable(e) {
        tableName = e.value;
        if (tableName == "bizShiYsJyjc") {
            return "检验检测";
        }
    }

    //申请类别
    function onGenderSqlb(e) {
        var type = e.value;
        var sqlb = "";

        if (tableName == "bizShiYsJyjc") {
            if (type.substr(0, 1) == "1") {
                sqlb = "首次 ";
            } else {
                if (type.substr(1, 1) == "1") {
                    if (sqlb == null || sqlb == undefined || sqlb == "") {
                        sqlb += "扩项 ";
                    } else {
                        sqlb += ",扩项";
                    }
                }
                if (type.substr(2, 1) == "1") {
                    if (sqlb == null || sqlb == undefined || sqlb == '') {
                        sqlb += "地址变更";
                    } else {
                        sqlb += ",地址变更";
                    }
                }
                if (type.substr(3, 1) == "1") {
                    if (sqlb == null || sqlb == undefined || sqlb == '') {
                        sqlb += "复查";
                    } else {
                        sqlb += ",复查";
                    }
                }
                if (type.substr(4, 1) == "1") {
                    if (sqlb == null || sqlb == undefined || sqlb == '') {
                        sqlb += "其他";
                    } else {
                        sqlb += ",其他";
                    }
                }
            }
            return sqlb;
        }
    }
</script>
</body>
</html>
