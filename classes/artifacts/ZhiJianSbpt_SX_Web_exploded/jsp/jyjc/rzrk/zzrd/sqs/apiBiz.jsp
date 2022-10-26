<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>封面信息</title>
    <script type="text/javascript" src="<%=basePath%>nui/nui.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<body>
<form id="form1" method="post">
    <h1 align="center" style="margin-top:10px">封面信息</h1>
    <input type="hidden" id="serialNumber" class="nui-hidden" name="serialNumber" value=""/>
    <input type="hidden" id="flowId" class="nui-hidden" name="flowId" value=""/>
    <input type="hidden" id="id" class="nui-hidden" name="id" value=""/>
    <div style="margin:auto; padding:5px;">
        <table style="margin: auto" align="center" border="0" width="100%">
            <tr height="40px">
                <td align="right" style="width: 300px">机构名称：<span style="color:red;">*</span>
                </td>
                <td colspan=4>
                    <input name="jgmc" id="jgmc" class="nui-textbox" style="width: 600px" allowInput="true"
                           emptyText="请输入机构名称" required="true"/>
                </td>
            </tr>
            <tr height="40px">
                <td align="right">专业领域类别：<span style="color:red;">*</span></td>
                <td colspan=4>
                    <input id="zylylbIds" name="zylylbIds" emptyText="请选择专业领域类别" class="nui-buttonedit" width="187px"
                           onbuttonclick="onButtonSclbEdit" required="true"/>
                </td>
            </tr>
            <tr height="40px">
                <td align="right">主管部门：<span style="color:red;">*</span></td>
                <td colspan=4>
                    <input id="zgbmDm" name="zgbmDm" class="nui-combobox" style="width:602px" valueField="ZGBMDM"
                           required="true" emptyText="请选择主管部门" dataField="data" textField="ZGBMNAME"/>
                    <input type="hidden" id="zgbmName" name="zgbmName" class="nui-hidden"/>
                </td>
            </tr>
            <tr height="40px">
                <td align="right" colspan=4>
                    申请日期：<span style="color:red;">*</span>
                </td>
                <td>
                    <input name="sqrq" id="sqrq" class="nui-datepicker" style="width: 232px" EmptyText="请选择申请日期" allowInput="false" required="true"/>
                    希望评审时间：
                    <input name="xwpsrq" id="xwpsrq" class="nui-datepicker" style="width: 246px" EmptyText="请选择希望评审日期" allowInput="false" required="true"/>
                </td>
            </tr>
            <tr height="40px">
                <td align="right">社会信用代码：<span style="color:red;">*</span></td>
                <td colspan=4>
                    <input name="shxydm" id="shxydm" class="nui-textbox" style="width: 600px" allowInput="true" onvalidation="checkValue" EmptyText="请输入18位社会信用代码" required="true"/>
                </td>
            </tr>
            <tr height="40px">
                <td align="right" colspan=4>
                    行政区域：<span style="color:red;">*</span>
                </td>
                <td>
                    <input name="areaId" id="areaId" style="width: 232px;" class="nui-buttonedit" onbuttonclick="areaClick" required="true"/>
                    上报机关：<span style="color:red;">*</span>
                    <input name="checkAreaId" id="checkAreaId" style="width: 232px;" class="nui-buttonedit" onbuttonclick="areaClick" required="true"/>
                </td>
            </tr>
            <tr height="40px">
                <td id="sheji" align="right">是否首次申请或检验检测项目设计<br/>强制性标准技术规范：<span style="color:red;">*</span></td>
                <td colspan=4>
                    <div id="isFirstOrOther" name="isFirstOrOther" class="nui-radiobuttonlist" repeatItems="2" repeatLayout="table" repeatDirection="horizontal" textField="text" valueField="id"></div>
                </td>
            </tr>
        </table>

    </div>
    <div>
        <a id="saveFmxx" class="nui-button" iconCls="icon-save" onclick="saveFmxx()"
           style="margin-left: 45%;margin-top:10%">保存</a>
    </div>
    <br/>
</form>
<script type="text/javascript">
    nui.parse();
    var action = "<%=request.getParameter("action")%>";
    var detail = "<%=request.getParameter("detail")%>";
    var serialNumber = '<%=request.getParameter("serialNumber")%>';
    var flowId = '<%=request.getParameter("flowId")%>';
    nui.get("serialNumber").setValue(serialNumber);
    nui.get("flowId").setValue(flowId);

    var form = new nui.Form("form1");

    // 主管部门
    var zgbmDm = nui.get("zgbmDm");
    zgbmDm.load("<%=basePath%>common_getZgbmData.action");
    var zyNames = loadZy();


    //详情查看
    if (detail == "detail") {
        setEnabled();
    }

    if (flowId != "6") {
        nui.get('isFirstOrOther').hide();
        $("#sheji").hide();
    }

    //首次申请或检验检测项目设计强制性标准技术规范
    var isFirstOrOther = nui.get("isFirstOrOther");
    isFirstOrOther.load([{"id": "1", "text": "是"}, {"id": "0", "text": "否"}]);

    // 页面数据 初始化
    initLoad();

    $(function () {
        $.ajax({
            url: "<%=basePath%>regist_getInfoByUserName.action",
            type: 'post',
            cache: false,
            success: function (text) {        //成功获得数据时
                var data = nui.decode(text);
                var shxydm = nui.get("shxydm").getValue();
                if (data.zzjgdm != null && (shxydm == null || shxydm == "")) {
                    nui.get("shxydm").setValue(data.zzjgdm);
                }
                var jgmc = nui.get("jgmc").getValue();
                if (data.qymc != null && (jgmc == null || jgmc == "")) {
                    nui.get("jgmc").setValue(data.qymc);
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {//如果不能获得数据
                nui.alert(jqXHR.responseText);//弹出错误
            }
        });
    });

    // 选择专业领域类别
    function onButtonSclbEdit(e) {
        var zylb = this;
        nui.open({
            url: "<%=basePath%>jsp/jyjc/rzrk/other/SelectZyTypeTrees.jsp",
            showMaxButton: false,
            title: "选择专业类型",
            width: 350,
            height: 700,
            ondestroy: function (action) {
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var resultData = iframe.contentWindow.GetData();
                    resultData = nui.decode(resultData);
                    if (resultData) {
                        zylb.setValue(resultData.dms);
                        zylb.setText(resultData.names);
                    }
                }
            }
        });
    }

    function saveFmxx() {

        if (serialNumber == null || serialNumber == "") {
            nui.alert("生成流水号失败！请关闭刷新重试！", "提醒！");
            return;
        }

        // 保存提交时：设置 主管部门、专业领域类别
        nui.get("zgbmName").setValue(nui.get("zgbmDm").text)
        var str = "";
        var zylylbIds = nui.get("zylylbIds").getValue();
        var strN = nui.get("zylylbIds").getText();
        if (typeof zylylbIds != 'string' && zylylbIds.length > 0) {
            for (var i = 0; i < zylylbIds.length; i++) {
                str += zylylbIds[i] + ","
            }
            str = str.substring(0, str.lastIndexOf(","));
        } else {
            str = zylylbIds;
        }
        /*if (!zyNames.includes(zylylbIds[i])) {
            nui.alert('专业类别非法！', '系统提示');
            nui.get("zylylbIds").setValue();
            nui.get("zylylbIds").setText();
            return
        }*/
        var formData = form.getData();
        form.validate();
        if (form.isValid() == false) {
            return;
        }
        var reqJson = nui.encode(formData);
        $.ajax({
            url: "<%=basePath%>bizApiShiysjyjc_save.action",
            type: 'post',
            cache: 'false',
            async: false,
            data: {data: reqJson, str: str, strN: strN},
            success: function (resultData) {
                resultData = nui.decode(resultData);
                nui.alert(resultData.msg, "提示!");
            }
        });
    }

    function initLoad() {
        $.ajax({
            url: "<%=basePath%>bizApiShiysjyjc_initLoad.action",
            type: 'post',
            cache: 'false',
            async: false,
            data: {serialNumber: serialNumber},
            success: function (resultData) {
                resultData = nui.decode(resultData);
                // 有数据
                if (resultData.code == 0) {
                    var data = resultData.data;
                    form.setData(data);
                    nui.get("zylylbIds").setValue(data.zylylbDm);
                    nui.get("zylylbIds").setText(data.zylylbName);
                    nui.get("areaId").setText(data.areaName);
                    nui.get("areaId").setValue(data.areaId);
                    nui.get("checkAreaId").setText(data.checkAreaName);
                    nui.get("checkAreaId").setValue(data.checkAreaId);
                }
            }
        });
    }

    function checkValue(e) {//判断组织机构代码和信用代码是否合法
        var value = e.value;
        //18位社会信用代码
        if (value.length == 18) {
            e.isValid = true;
        } else {
            e.errorText = "请填写社会信用代码18位.";
            e.isValid = false;
        }
    }

    var areaId = nui.getbyName("areaId");
    var checkAreaId = nui.getbyName("checkAreaId");

    //行政区域选择
    function areaClick(e) {
        var inputName = e.sender.name;
        var action = "";
        if (inputName == "areaId") {
            action = "allProvince";
        }
        if (inputName == "checkAreaId") {
            action = "allCity";
        }
        var btnEdit = this;
        nui.open({
            url: "<%=basePath%>jsp/jyjc/rzrk/common/showAreaTree.jsp",
            showMaxButton: false,
            title: "选择区域",
            width: 350,
            height: 480,
            onload: function () {
                var iframe = this.getIFrameEl();
                var data = {action: action};
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    var words = data.split(',')
                    if (data) {
                        if (inputName == "areaId") {
                            areaId.setText(words[0]);
                            areaId.setValue(words[1]);
                            checkAreaId.setText(words[2]);
                            checkAreaId.setValue(words[3]);
                        }
                        if (inputName == "checkAreaId") {
                            checkAreaId.setValue(words[1]);
                            checkAreaId.setText(words[0]);
                        }
                    }
                }
            }
        });
    }

    /**
     * 查看详情 设置 隐藏，不可编辑
     */
    function setEnabled() {
        nui.get('saveFmxx').hide();
        nui.get("jgmc").setEnabled(false);
        nui.get("sqrq").setEnabled(false);
        nui.get("xwpsrq").setEnabled(false);
        nui.get("shxydm").setEnabled(false);
        nui.get("zylylbIds").setEnabled(false);
        nui.get("zgbmDm").setEnabled(false);
        nui.get("areaId").setEnabled(false);
        nui.get("checkAreaId").setEnabled(false);
    }

    function loadZy() {
        var zys = new Array();
        $.ajax({
            url: "<%=basePath%>common_getShiysjyjcScType.action",
            async: false,
            success: function (text) {
                text = nui.decode(text);
                for (var i = 0; i < text.length; i++) {
                    zys.push(text[i].DAIMA);
                }
            }
        });
        return zys;
    }
</script>
</body>
</html>

