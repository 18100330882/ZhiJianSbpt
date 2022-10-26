<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">
    <title>申请类型</title>
    <script type="text/javascript" src="<%=basePath %>nui/nui.js"></script>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>

<body>
<form id="form1" method="post">
    <h1 align="center" style="margin-top:10px">申请类型</h1>
    <input type="hidden" id="serialNumber" class="nui-hidden" name="serialNumber" value=""/>
    <div style="margin:auto; padding:5px;">
        <table align="center" border="0" width="100%">
            <tr height="40px">
                <td align="right" width="40%" style="width: 356px">资质认定：<span style="color:red;">*</span></td>
                <td>
                    <input id="applicationType" name="applicationType" class="nui-checkboxlist" textField="text"
                           valueField="text" width="505px" required="true" onvaluechanged="istype" readonly="readonly"/>
                </td>
            </tr>
            <tr height="40px">
                <td id="cmaNumber" align="right">CMA资质认定证书编号：<span style="color:red;">*</span></td>
                <td>
                    <input id="cmaPermitNumber" name="cmaPermitNumber" class="nui-textbox" style="width: 240px"
                           EmptyText="请输入证书编号" required="true" onvaluechanged="getJgobjectByZsbh()"/>
                </td>
            </tr>
            <tr height="40px">
                <td id="cmaDate" align="right">CMA证书有效日期：<span style="color:red;">*</span></td>
                <td>
                    <input id="cmaPermitEffectiveDate" name="cmaPermitEffectiveDate" class="nui-datepicker"
                           style="width: 240px" allowInput="false" required="true" EmptyText="请选择日期..."/>
                </td>
            </tr>
        </table>
        <div>
            <a id="saveSqlx" class="nui-button" iconCls="icon-save" onclick="saveSqlx()"
               style="margin-left: 45%;margin-top:10%">保存</a>
        </div>
    </div>
</form>
<script type="text/javascript">
    nui.parse();
    var form = new nui.Form("form1");
    // 流水号
    var serialNumber = '<%=request.getParameter("serialNumber")%>';
    var action = '<%=request.getParameter("action")%>';
    var detail = "<%=request.getParameter("detail")%>";

    nui.get("serialNumber").setValue(serialNumber);

    nui.get("applicationType").setData("[{'id':'1','text':'首次'},{'text':'扩项'},{'text':'地址变更'},{'text':'复查'},{'text':'其他'},{'text':'标准变更'},{'text':'授权签字人变更'}]");

    //详情查看
    if (detail == "detail") {
        setEnabled();
    }

    initCheck();

    function initLoad(serialNumber) {
        $.ajax({
            url: "<%=basePath%>apiShiYsJyjcSqlx_initLoad.action",
            type: 'post',
            data: {serialNumber: serialNumber},
            cache: false,
            success: function (text) {
                var map = nui.decode(text);
                if (map.code == 0) {
                    var data = map.data;
                    var applicationType = data.applicationType;
                    //如果是首次 隐藏证书编号填写
                    var typeMap = {"value": applicationType};
                    istype(typeMap);
                    form.setData(map.data);
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
                    nui.get("saveSqlx").setEnabled(false);
                    return;
                }
                // 编辑时 加载 初始化申请类型 数据
                initLoad(serialNumber);
            }
        });
    }

    function istype(e) {
        var result = e.value;
        $("#cmaPermitNumber").show();
        $("#cmaPermitEffectiveDate").show();
        $("#cmaNumber").show();
        $("#cmaDate").show();
        if (result.indexOf("首次") != -1 && result != null) {
            $("#cmaPermitNumber").hide();
            $("#cmaPermitEffectiveDate").hide();
            $("#cmaNumber").hide();
            $("#cmaDate").hide();
        }
    }


    function saveSqlx() {
        if (serialNumber == null || serialNumber == "") {
            nui.alert("生成流水号失败！请关闭刷新重试！", "提醒！");
            return;
        }
        var formData = form.getData();
        form.validate();
        if (form.isValid() == false) {
            return;
        }
        var reqJson = nui.encode(formData);
        $.ajax({
            url: "<%=basePath%>apiShiYsJyjcSqlx_save.action",
            type: 'post',
            cache: 'false',
            async: false,
            data: {data: reqJson},
            success: function (resultData) {
                resultData = nui.decode(resultData);
                nui.alert(resultData.msg, "提示!");
            }
        });
    }

    function setEnabled() {
        nui.get('saveSqlx').hide();
        nui.getbyName("applicationType").setEnabled(false);
        nui.getbyName("cmaPermitNumber").setEnabled(false);
        nui.getbyName("cmaPermitEffectiveDate").setEnabled(false);
    }

    function getJgobjectByZsbh() {
        var regexp_1 = /^\d{12}$/;
        zsbh = nui.get('cmaPermitNumber').getValue();
        var result = regexp_1.test(zsbh);
        if (!result) {
            $("#msg").hide();
            nui.alert('请输入12位纯数字的证书编号', '温馨提示');
            nui.get('saveSqlx').setEnabled(false);
            return;
        }
        nui.get('saveSqlx').setEnabled(true);
    }
</script>
</body>
</html>