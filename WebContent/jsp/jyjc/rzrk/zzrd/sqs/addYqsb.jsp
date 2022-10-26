<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>标题</title>
    <script type="text/javascript" src="<%=basePath%>nui/nui.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>

<body>
<form id="form1" target="id_iframe" style="padding-top:5px;margin:0 auto; width:840px; border:1px;">
    <h1 align="center" style="margin-top:10px">新增仪器设备配置</h1>
    <table border="0">
        <tr>
            <td align="left" style="">场地地址：</td>
            <td colspan="">
                <input id="cddz" name="cddz" class="nui-textbox" width="500px" required="true" readonly="readonly"/>
            </td>
        </tr>
        <tr>
            <td align="left" style="">食品/非食品：</td>
            <td>
                <input name="isSp" id="isSp" class="nui-radiobuttonlist" valueField="text" textField="text"
                       repeatItems="2" readonly="readonly"/>
            </td>
        </tr>
        <tr>
            <td align="left">类别(产品/项目/参数)：</td>
            <td>
                <input id="ejfl" name="ejfl" class="nui-textbox" width="500px" EmptyText="类别(产品/项目/参数)"/>
            </td>
        </tr>
        <tr>
            <td align="left">序号[产品/项目/参数]：</td>
            <td>
                <input id="sortingNumber" name="sortingNumber" class="nui-textbox" width="500px"
                       EmptyText="例：1.1、1.2..."/>
            </td>
        </tr>
        <tr>
            <td align="left">名称[产品/项目/参数]：</td>
            <td>
                <input id="productName" name="productName" class="nui-textbox" width="500px" EmptyText="名称[产品/项目/参数]"/>
            </td>
        </tr>
        <tr>
            <td align="left">依据的标准（方法）名称及编号（含年号）：</td>
            <td>
                <input id="yjbz" name="yjbz" class="nui-textbox" width="500px" EmptyText="依据的标准（方法）名称及编号（含年号）"/>
            </td>
        </tr>
        <tr>
            <td align="left">名称[仪器设备（标准物质）]：</td>
            <td>
                <input id="bzwzName" name="bzwzName" class="nui-textbox" width="500px" EmptyText="名称[仪器设备（标准物质）]"/>
            </td>
        </tr>
        <tr>
            <td align="left">型号/规格/等级[仪器设备（标准物质）]：</td>
            <td>
                <input id="identifier" name="identifier" class="nui-textbox" width="500px"
                       EmptyText="型号/规格/等级[仪器设备（标准物质）]"/>
            </td>
        </tr>
        <tr>
            <td align="left">型号/规格/等级[仪器设备（标准物质）]：</td>
            <td>
                <input id="bzwzClfw" name="bzwzClfw" class="nui-textbox" width="500px" EmptyText="测量范围[仪器设备（标准物质）]"/>
            </td>
        </tr>
        <tr>
            <td align="left">溯源方式：</td>
            <td>
                <input id="syfs" name="syfs" class="nui-textbox" width="500px" EmptyText="检定、校准、内部校准等"/>
            </td>
        </tr>
        <tr>
            <td align="left">有效日期：</td>
            <td>
                <input id="yqsbYxrq" name="yqsbYxrq" property="editor" class="nui-datepicker" allowInput="false"/>
            </td>
        </tr>
        <tr>
            <td align="left">确认结果：</td>
            <td>
                <input id="qrjg" name="qrjg" class="nui-textbox" width="500px" EmptyText="确认结果"/>
            </td>
        </tr>
    </table>
    <div>
        <a id="saveRow" class="nui-button" iconCls="icon-save" onclick="saveRow()" style="margin-left: 45%;">保存</a>
    </div>
    <table>
        <tr>
            <td align="left" colspan="2" style="color: #0022ff">
                注：①申请时，为了简化此表的填写，参数相同的不重复填写，序号可以不连续；
            </td>
        </tr>
        <tr>
            <td align="left" colspan="2" style="color: #0022ff">
                ②溯源方式填写：检定、校准、内部校准等；
            </td>
        </tr>
        <tr>
            <td align="left" colspan="2" style="color: #0022ff">
                ③多实验场所的检验检测机构，按不同实验场所分别填写；
            </td>
        </tr>
        <tr>
            <td align="left" colspan="2" style="color: #0022ff">
                ④确认意见分为“符合”和“不符合”两种，机构应对仪器设备检定校准的数据和结果进行分析，判断是否符合检验检测标准、技术规范、程序的要求。
            </td>
        </tr>
    </table>
</form>
<script type="text/javascript">
    nui.parse();
    nui.get("isSp").setData("[{'text':'食品'},{'text':'非食品'}]");

    var psbgId;

    function saveRow() {
        var sortingNumber = nui.get("sortingNumber").getValue();
        if (sortingNumber == null || sortingNumber == "") {
            nui.alert("序号不能为空！", "提醒");
            return;
        }
        var numberFlag = isNumber(sortingNumber);
        if (!numberFlag) {
            nui.alert("序号类型不是数字类型！", "提醒");
            return;
        }

        var form = new nui.Form("form1");
        var formData = form.getData();
        nui.confirm("确定保存记录？", "提醒!", function (action) {
            if (action == "ok") {
                $.ajax({
                    url: "<%=basePath%>apiShiYsJyjcNl_saveRow.action",
                    type: 'post',
                    cache: 'false',
                    async: false,
                    data: {id: "", psbgId: psbgId, row: nui.encode(formData)},
                    success: function (result) {
                        result = nui.decode(result);
                        nui.alert(result.msg, "提醒!", function (e) {
                            CloseWindow();
                        });
                    },
                });
            }
        });
    }

    function SetData(data) {
        psbgId = data.psbgId;
        initLoad(psbgId);
    }

    function initLoad(psbgId) {
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

    function CloseWindow() {
        if (window.CloseOwnerWindow) {
            return window.CloseOwnerWindow("cancel");
        } else {
            window.close();
        }
    }

    function isNumber(obj) {
        return !isNaN(parseFloat(obj)) && isFinite(obj);
    }
</script>
</body>
</html>