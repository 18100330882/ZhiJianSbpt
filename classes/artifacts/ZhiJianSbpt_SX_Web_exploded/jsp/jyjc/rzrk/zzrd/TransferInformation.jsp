<%@ page language="Java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String pincou1 = request.getParameter("QYMC");
    String pincou2 = request.getParameter("SQRQ");
    String pincou;
    if ((pincou1 == null && pincou2 == null) || (pincou1 == "" && pincou2 == "")) {
        pincou = "行政事项";
    } else {
        pincou = "企业名称：" + pincou1 + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;申请日期：" + pincou2;
    }
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>流转信息</title>
    <script type="text/javascript" src="<%=basePath %>nui/nui.js"></script>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" co ntent="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <style type="text/css">
        .myrow {
            color: #FF0000; /* 红色 */
        }

        .myrowblue {
            color: #0000ff; /* 蓝色 */
        }

        .myrowLightgreen {
            color: #7FFF00; /*浅绿色 */
        }
    </style>
</head>

<body>
<div id="div1" title="流转信息" style="width:100%;height:auto">
    <table style="margin-bottom: 8px; margin-top: 10px;" height="5%">
        <tbody>
        <tr>
            <td><font color="#ff0000">提示：</font> &nbsp;&nbsp;&nbsp;</td>
            <td style="background-color: #3C3C3C; width: 50px;"></td>
            <td style="width:150px;"><font style="font-size:14px;">提交信息(正常提交)</font>&nbsp;</td>
            <td style="background-color: #FF0000; width: 50px;"></td>
            <td><font style="font-size:14px;">退回信息(节点之间退回)</font>&nbsp;</td>
            <td style="background-color: #7FFF00; width: 50px;"></td>
            <td><font style="font-size:14px;">挂起信息(节点退回到企业)</font>&nbsp;</td>
            <td style="background-color: #0000ff; width: 50px;"></td>
            <td><font style="font-size:14px;">激活信息(企业提交后激活)</font>&nbsp;</td>
        </tr>
        </tbody>
    </table>
    <div id="datagrid1" class="nui-datagrid" style="width:100%;height:93%;" allowResize="false"
         idField="id" showPager="false">
        <div property="columns">
            <div header="<%=pincou %>" headerAlign="center">
                <div property="columns">
                    <div header="办理信息" headerAlign="center">
                        <div property="columns">
                            <div type="indexcolumn">步骤</div>
                            <div field="HANDLINGDEPARTMENT" headerAlign="center" allowSort="true">办理单位(部门)</div>
                            <div field="BANLIR" headerAlign="center" allowSort="true">办理人</div>
                            <div field="BANLIOPERATION" headerAlign="center" allowSort="true">办理动作</div>
                            <div field="SUGGESTION" headerAlign="center" allowSort="true">办理意见</div>
                            <div field="BANLIDATE" headerAlign="center" allowSort="true"
                                 dateFormat="yyyy-MM-dd HH:mm:ss">办理时间
                            </div>
                        </div>
                    </div>
                    <div header="待办信息" headerAlign="center">
                        <div property="columns">
                            <div field="DAIBANOPERATION" headerAlign="center" allowSort="true" renderer="onDaiBanState">
                                待办状态
                            </div>
                            <div field="DAIBANR" headerAlign="center" allowSort="true" renderer="onDaiBanR">待办人</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<script type="text/javascript">
    nui.parse();
    var grid = nui.get("datagrid1");
    var flowInstId;
    var flowId;
    var nodeId;
    var row;

    function SetData(data) {
        row = data.row;
        flowId = data.flowId;
        nodeId = data.nodeId;
        serialNumber = data.serialNumber;
        flowInstId = row.FLOWINSTID;
        if (flowInstId == null || flowInstId == "") {
            flowInstId = '0' ;
        }
        if (serialNumber == null || serialNumber == "") {
            return;
        }
        grid.load("<%=basePath%>flowLog_getFlowInstLogList.action?flowInstId=" + flowInstId + "&serialNumber=" +serialNumber);
        grid.sortBy("BANLIDATE", "desc");
    }

    //最后一行的待办状态
    function onDaiBanState(e) {
        var record = e.record;
        var value = record.DAIBANOPERATION;
        var opType = record.OPTYPE;
        // 2.退回操作  3.挂起操作 4.激活操作
        if (opType == 2) {
            e.rowCls = "myrow";
        }
        if (opType == 3) {
            e.rowCls = "myrowLightgreen";
        }
        if (opType == 4) {
            e.rowCls = "myrowblue";
        }

        if (value == null || value == "") {
            record.type = "dbr";
            return record.DAIBANOPERATION2;
        }
        return value;

    }

    //最后一行的待办人
    function onDaiBanR(e) {
        var record = e.record;
        if (record.type == "dbr") {
            return record.PARTICIPANTS;
        }
        return record.DAIBANR;
    }
</script>
</body>
</html>