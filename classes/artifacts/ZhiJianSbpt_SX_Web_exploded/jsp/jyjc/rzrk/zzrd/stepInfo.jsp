<%@ page import="com.yongjie.ZhiJianSbpt.utilities.StringUtil" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String sqlx = request.getParameter("sqlx");
    if (!StringUtil.isNullOrEmpty(sqlx)) {
        sqlx = URLDecoder.decode(sqlx, "utf-8");//解决中文乱码
    }
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>查看申请材料</title>
    <script type="text/javascript" src="<%=basePath %>nui/nui.js"></script>
    <script type="text/javascript" src="<%=basePath %>style/scripts/step-jquery-dc.js"></script>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" href="<%=basePath %>style/css/step-dc-style2.css"/>
    <style type="text/css">
        html, body {
            margin: 0;
            padding: 0;
            border: 0;
            width: 100%;
            height: 100%;
            overflow: hidden;
        }

        .step_context li {
            line-height: 15px;
        }

        .step_context li a {
            margin-top: 10px;
        }
    </style>
</head>

<body>
<div>
    <div>
        <input name="sqsId" class="nui-hidden"/>
        <input name="zslx" class="nui-hidden"/>
        <input name="isDcd" class="nui-hidden"/>
        <input name="Hidstepflagnum" class="nui-hidden"/>
    </div>
    <div class="step_context test">
    </div>
</div>
<div style="margin-top: 5px;">
    <iframe id="iframenr" name="leftfriame" width="100%" frameborder="0" scrolling="yes"></iframe>
</div>

<script type="text/javascript">
    nui.parse();
    // action:add 新增、 action：edit 编辑
    var action = "";
    var serialNumber = "";
    var flowId;
    var detail = '<%=request.getParameter("detail")%>';
    var jgmc = '';
    var sqlx = '<%=sqlx%>';

    function SetData(data) {
        action = data.action;
        serialNumber = data.serialNumber;
        flowId = data.flowId;
        jgmc = data.jgmc;
        init();
    }

    function init() {
        $("#iframenr").height(document.body.offsetHeight - 200);
        ChangeTab("封面信息", 'jsp/jyjc/rzrk/zzrd/sqs/apiBiz.jsp');
    }

    var hidStepnum = nui.getbyName("Hidstepflagnum");
    // 加载画布
    var stepListJson = loadMenu();
    //new一个工具类
    var StepTool = new Step_Tool_dc("test", "mycall");
    //使用工具对页面绘制相关流程步骤图形显示
    StepTool.drawStep(hidStepnum.getValue(), stepListJson);

    function loadMenu() {
        debugger
        stepListJson = [{StepNum: 1, StepText: "封面信息", StepTitle: "封面信息", StepUrl: "1"},
            {StepNum: 2, StepText: "申请类型", StepTitle: "申请类型", StepUrl: "2"},
            {StepNum: 3, StepText: "概况", StepTitle: "概况", StepUrl: "3"},
            {StepNum: 4, StepText: "机构资源", StepTitle: "机构资源", StepUrl: "4"},
            {StepNum: 5, StepText: "授权签字人信息", StepTitle: "授权签字人信息", StepUrl: "5"},
            {StepNum: 6, StepText: "检验检测人员", StepTitle: "检验检测人员", StepUrl: "6"},
            {StepNum: 7, StepText: "检验检测能力信息", StepTitle: "检验检测能力信息", StepUrl: "7"},
            {StepNum: 8, StepText: "仪器设备配置表", StepTitle: "仪器设备配置表", StepUrl: "8"}];
        if (detail == "detail") {
            stepListJson = [{StepNum: 1, StepText: "封面信息", StepTitle: "封面信息", StepUrl: "1"},
                {StepNum: 2, StepText: "申请类型", StepTitle: "申请类型", StepUrl: "2"},
                {StepNum: 3, StepText: "概况", StepTitle: "概况", StepUrl: "3"},
                {StepNum: 4, StepText: "机构资源", StepTitle: "机构资源", StepUrl: "4"},
                {StepNum: 5, StepText: "授权签字人信息", StepTitle: "授权签字人信息", StepUrl: "5"},
                {StepNum: 6, StepText: "检验检测人员", StepTitle: "检验检测人员", StepUrl: "6"},
                {StepNum: 7, StepText: "检验检测能力信息", StepTitle: "检验检测能力信息", StepUrl: "7"},
                {StepNum: 8, StepText: "仪器设备配置表", StepTitle: "仪器设备配置表", StepUrl: "8"},
                {StepNum: 9, StepText: "附件", StepTitle: "附件", StepUrl: "9"},
                {StepNum: 10, StepText: "审批附件", StepTitle: "审批附件", StepUrl: "10"}];
        } else if (sqlx.indexOf("授权签字人变更") != -1) {//包含授权签字人信息
            stepListJson = [{StepNum: 1, StepText: "封面信息", StepTitle: "封面信息", StepUrl: "1"},
                {StepNum: 2, StepText: "申请类型", StepTitle: "申请类型", StepUrl: "2"},
                {StepNum: 3, StepText: "概况", StepTitle: "概况", StepUrl: "3"},
                {StepNum: 4, StepText: "机构资源", StepTitle: "机构资源", StepUrl: "4"},
                {StepNum: 5, StepText: "授权签字人变更", StepTitle: "授权签字人信息", StepUrl: "11"},
                {StepNum: 6, StepText: "授权签字人信息", StepTitle: "授权签字人信息", StepUrl: "5"},
                {StepNum: 7, StepText: "检验检测人员", StepTitle: "检验检测人员", StepUrl: "6"},
                {StepNum: 8, StepText: "检验检测能力信息", StepTitle: "检验检测能力信息", StepUrl: "7"},
                {StepNum: 9, StepText: "仪器设备配置表", StepTitle: "仪器设备配置表", StepUrl: "8"}]
        }
        if (sqlx == "授权签字人变更") {
            stepListJson = [{StepNum: 1, StepText: "封面信息", StepTitle: "封面信息", StepUrl: "1"},
                {StepNum: 2, StepText: "申请类型", StepTitle: "申请类型", StepUrl: "2"},
                {StepNum: 3, StepText: "概况", StepTitle: "概况", StepUrl: "3"},
                {StepNum: 4, StepText: "机构资源", StepTitle: "机构资源", StepUrl: "4"},
                {StepNum: 5, StepText: "授权签字人变更", StepTitle: "授权签字人信息", StepUrl: "11"},
                {StepNum: 6, StepText: "授权签字人信息", StepTitle: "授权签字人信息", StepUrl: "5"}]
        }
        return stepListJson;
    }

    /* 点击画布,回调函数 */
    function mycall(result) {
        /* 重新渲染画布 */
        StepTool.drawStep(result.value, stepListJson);
        /* 第几步骤颜色 */
        hidStepnum.setValue(result.value);
        var urlNum = result.url;

        if (urlNum == 1) {
            ChangeTab("封面信息", 'jsp/jyjc/rzrk/zzrd/sqs/apiBiz.jsp');
        } else if (urlNum == 2) {
            ChangeTab("申请类型", 'jsp/jyjc/rzrk/zzrd/sqs/apiSqlx.jsp');
        } else if (urlNum == 3) {
            ChangeTab("概况", 'jsp/jyjc/rzrk/zzrd/sqs/apiSqs.jsp');
        } else if (urlNum == 4) {
            ChangeTab("机构资源", 'jsp/jyjc/rzrk/zzrd/sqs/apiJgzy.jsp');
        } else if (urlNum == 5) {
            ChangeTab("授权签字人信息", 'jsp/jyjc/rzrk/zzrd/sqs/apiQzr.jsp');
        } else if (urlNum == 6) {
            ChangeTab("检验检测人员", 'jsp/jyjc/rzrk/zzrd/sqs/apiRy.jsp');
        } else if (urlNum == 7) {
            ChangeTab("检验检测能力信息", 'jsp/jyjc/rzrk/zzrd/sqs/jyjcnlxx.jsp');
        } else if (urlNum == 8) {
            ChangeTab("仪器设备配置表", 'jsp/jyjc/rzrk/zzrd/sqs/yqsbpzb.jsp');
        } else if (urlNum == 9) {
            ChangeTab("附件", 'jsp/jyjc/rzrk/zzrd/sqs/apiFile.jsp');
        } else if (urlNum == 10) {
            ChangeTab("审批附件", 'jsp/jyjc/rzrk/zzrd/sqs/wenShuFile.jsp');
        } else if (urlNum == 11) {
            ChangeTab("授权签字人变更", 'jsp/jyjc/rzrk/zzrd/sqs/apiSqqzrBg.jsp');
        }
    }

    function ChangeTab(title, src) {
        var param = "?serialNumber=" + serialNumber + "&_=" + Math.random() + "&action=" + action + "&flowId=" + flowId + "&detail=" + detail + "&jgmc=" + jgmc;
        var urlValue = encodeURI(encodeURI(src + param));
        $('#iframenr').attr('src', urlValue);
    }

</script>
</body>
</html>