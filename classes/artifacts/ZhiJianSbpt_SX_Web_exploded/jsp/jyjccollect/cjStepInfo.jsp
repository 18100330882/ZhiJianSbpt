<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
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
    var zsbh = "";
    var jgmc = '';

    function SetData(data) {
        action = data.action;
        if (action == 'edit') {
            row = data.row;
            zsbh = row.ZZRDZSBH;
        }
        init();
    }

    function init() {
        $("#iframenr").height(document.body.offsetHeight - 200);
        ChangeTab("封面信息", 'jsp/jyjccollect/addcollect.jsp');
    }

    var hidStepnum = nui.getbyName("Hidstepflagnum");
    // 加载画布
    var stepListJson = loadMenu();
    //new一个工具类
    var StepTool = new Step_Tool_dc("test", "mycall");
    //使用工具对页面绘制相关流程步骤图形显示
    StepTool.drawStep(hidStepnum.getValue(), stepListJson);

    function loadMenu() {
        stepListJson = [

            {StepNum: 1, StepText: "采集添加", StepTitle: "采集添加", StepUrl: "1"},
            {StepNum: 2, StepText: "检验检测能力信息", StepTitle: "检验检测能力信息", StepUrl: "2"},
            {StepNum: 3, StepText: "签字人信息", StepTitle: "检验检测能力信息", StepUrl: "3"},
            {StepNum: 4, StepText: "人员信息", StepTitle: "人员信息", StepUrl: "4"},
            ];
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
            ChangeTab("采集添加", 'jsp/jyjccollect/addcollect.jsp');
        } else if (urlNum == 2) {
            ChangeTab("概况", 'jsp/jyjccollect/jyjcnlcj.jsp');
        } else if (urlNum == 3) {
            ChangeTab("申请类型", 'jsp/jyjccollect/jyjcqzrcj.jsp');
        } else if (urlNum == 4) {
            ChangeTab("机构资源", 'jsp/jyjccollect/jyjcrycj.jsp');
        }
    }

    function ChangeTab(title, src) {
        var param = "?action=" + action + "&zsbh=" + zsbh;
        var urlValue = encodeURI(encodeURI(src + param));
        $('#iframenr').attr('src', urlValue);
    }
</script>
</body>
</html>