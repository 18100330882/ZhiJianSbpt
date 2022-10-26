<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>申请材料</title>
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
    var jgmc = '';
    var zsbh = '';

    function SetData(data) {
        action = data.action;
        serialNumber = data.serialNumber;
        flowId = data.flowId;
        jgmc = data.jgmc;
        changeType = data.changeType;
        init();
    }

    function init() {
        $("#iframenr").height(document.body.offsetHeight - 200);
        ChangeTab("封面信息", 'jsp/jyjc/rzrk/bggl/sqs/apiBizBg.jsp');
    }

    var hidStepnum = nui.getbyName("Hidstepflagnum");
    // 加载画布
    var stepListJson = loadMenu();
    //new一个工具类
    var StepTool = new Step_Tool_dc("test", "mycall");
    //使用工具对页面绘制相关流程步骤图形显示
    StepTool.drawStep(hidStepnum.getValue(), stepListJson);

    function loadMenu() {
        stepListJson = [{StepNum: 1, StepText: "封面信息", StepTitle: "封面信息", StepUrl: "1"},
            {StepNum: 2, StepText: "变更审批表", StepTitle: "概况", StepUrl: "2"}];
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
            ChangeTab("封面信息", 'jsp/jyjc/rzrk/bggl/sqs/apiBizBg.jsp');
            return;
        }
        // 画布：通过流水号 获取 数据信息 设置 变更审批表 url - 渲染 画布
        $.ajax({
            url: "<%=basePath%>bizApiZwcnBg_initLoad.action",
            type: 'post',
            data: {serialNumber: serialNumber},
            cache: false,
            success: function (result) {
                var result = nui.decode(result);
                if (result.code == 500) {
                    nui.alert(result.msg);
                    return;
                }

                var entity = result.data;
                changeType = entity.state;
                zsbh = entity.cmaPermitNumber;
                var urlNum2 = "";
                var changeTabName2 = "";
                if ("11" == changeType) {
                    changeTabName2 = "检验检测机构资质认定标准（方法）变更审批表";
                    urlNum2 = "jsp/jyjc/rzrk/bggl/sqs/standardChange.jsp";
                }
                if ("12" == changeType) {
                    changeTabName2 = "检验检测机构资质认定名称变更审批表";
                    urlNum2 = "jsp/jyjc/rzrk/bggl/sqs/nameChange.jsp";
                }
                if ("13" == changeType) {
                    changeTabName2 = "检验检测机构资质认定地址名称变更审批表";
                    urlNum2 = "jsp/jyjc/rzrk/bggl/sqs/addressChange.jsp";
                }
                if ("14" == changeType) {
                    changeTabName2 = "检验检测机构法人性质变更审批表";
                    urlNum2 = "jsp/jyjc/rzrk/bggl/sqs/legalPersonChange.jsp";
                }
                if ("15" == changeType) {
                    changeTabName2 = "取消检验检测能力审批表";
                    urlNum2 = "jsp/jyjc/rzrk/bggl/sqs/cancelNl.jsp";
                }
                if ("16" == changeType) {
                    changeTabName2 = "检验检测机构人员变更备案审批表";
                    urlNum2 = "jsp/jyjc/rzrk/bggl/sqs/personChange.jsp";
                }
                if ("17" == changeType) {
                    changeTabName2 = "检验检测机构资质认定授权签字人变更审批表";
                    urlNum2 = "jsp/jyjc/rzrk/bggl/sqs/sqqzrChange.jsp";
                }

                if (urlNum == 2) {
                    ChangeTab(changeTabName2, urlNum2);
                }
            }
        });
    }

    function ChangeTab(title, src) {
        var param = "?serialNumber=" + serialNumber + "&_=" + Math.random() + "&action=" + action + "&flowId=" + flowId + "&changeType=" + changeType + "&zsbh=" + zsbh;
        var urlValue = encodeURI(encodeURI(src + param));
        $('#iframenr').attr('src', urlValue);
    }

</script>
</body>
</html>