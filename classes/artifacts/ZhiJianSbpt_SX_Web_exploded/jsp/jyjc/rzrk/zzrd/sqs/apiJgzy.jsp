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
    <h1 align="center" style="margin-top:10px">检验检测机构资源</h1>
    <input type="hidden" id="serialNumber" class="nui-hidden" name="serialNumber" value=""/>
    <table border="0">
        <tr>
            <td align="right" style="width:230px;">检验检测机构总人数（名）：</td>
            <td colspan="3">
                <input id="allNumber" name="allNumber" type="number" class="nui-textbox" width="547px" required="true" EmptyText="此项无需填写.." readonly="readonly"/>
            </td>
        </tr>
        <tr>
            <td align="right">高级专业技术职称（名）：<span style="color:red;">*</span></td>
            <td>
                <input id="hJobTitleNumber" name="hJobTitleNumber" class="nui-textbox" width="180px" required="true" onvaluechanged="valueChangeEvent()" vtype="int" EmptyText="请输入正整数.."/>
            </td>
            <td align="right">占总人数比例（%）：</td>
            <td>
                <input id="hRatioAll" name="hRatioAll" class="nui-textbox" width="180px" required="true" EmptyText="此项无需填写.." readonly="readonly"/>
            </td>
        </tr>

        <tr>
            <td align="right">中级专业技术职称（名）：<span style="color:red;">*</span></td>
            <td>
                <input id="mJobTitleNumber" name="mJobTitleNumber" class="nui-textbox" width="180px" required="true" onvaluechanged="valueChangeEvent()" vtype="int" EmptyText="请输入正整数.."/>
            </td>
            <td align="right">占总人数比例（%）：</td>
            <td>
                <input id="mRatioAll" name="mRatioAll" class="nui-textbox" width="180px" required="true" EmptyText="此项无需填写.." readonly="readonly"/>
            </td>
        </tr>

        <tr>
            <td align="right">初级专业技术职称（名）：<span style="color:red;">*</span></td>
            <td>
                <input id="pJobTitleNumber" name="pJobTitleNumber" class="nui-textbox" width="180px" required="true" onvaluechanged="valueChangeEvent()" vtype="int" EmptyText="请输入正整数.."/>
            </td>
            <td align="right">占总人数比例（%）：</td>
            <td>
                <input id="pRatioAll" name="pRatioAll" class="nui-textbox" width="180px" required="true" EmptyText="此项无需填写.." readonly="readonly"/>
            </td>
        </tr>
        <tr>
            <td align="right">其他人数（名）：<span style="color:red;">*</span></td>
            <td>
                <input id="otherNumber" name="otherNumber" class="nui-textbox" width="180px" required="true" onvaluechanged="valueChangeEvent()" vtype="int" EmptyText="请输入正整数.."/>
            </td>
            <td align="right">占总人数比例（%）：</td>
            <td>
                <input id="oRatioAll" name="oRatioAll" class="nui-textbox" width="180px" required="true" EmptyText="此项无需填写.." readonly="readonly"/>
            </td>

        </tr>
        <tr>
            <td align="right">固定资产原值(万元)：<span style="color:red;">*</span></td>
            <td>
                <input name="fixedAssets" class="nui-textbox" width="180px" required="true"/>
            </td>
            <td align="right">仪器设备总数(台、套)：<span style="color:red;">*</span></td>
            <td>
                <input name="equipmentNumber" class="nui-textbox" width="180px" required="true"/>
            </td>

        </tr>

        <tr>
            <td align="right">产权状况：<span style="color:red;">*</span></td>
            <td>
                <input id="propertyRights" name="propertyRights" class="nui-combobox" style="width:180px" valueField="text" required="true" dataField="data" textField="text" required="true"/>
            </td>
            <td align="right">产权值（%）：<span style="color:red;">*</span></td>
            <td>
                <input name="propertyRightsValue" class="nui-textbox" width="180px" required="true"/>
            </td>
        </tr>

        <tr>
            <td align="right">检验检测机构总面积（㎡）：<span style="color:red;">*</span></td>
            <td colspan="3">
                <input id="institutionsArea" name="institutionsArea" class="nui-textbox" width="547px" required="true" EmptyText="此项无需填写.." readonly="readonly"/>
            </td>
        </tr>

        <tr>
            <td align="right">检验检测场地面积（㎡）：<span style="color:red;">*</span></td>
            <td>
                <input id="siteArea" name="siteArea" class="nui-textbox" width="180px" required="true" onvaluechanged="valueChangeEvent2()" vtype="float" EmptyText="请输入数字.."/>
            </td>
            <td align="right">温恒面积（㎡）：<span style="color:red;">*</span></td>
            <td>
                <input id="whArea" name="whArea" class="nui-textbox" width="180px" required="true" onvaluechanged="valueChangeEvent2()" vtype="float" EmptyText="请输入数字.."/>
            </td>
        </tr>
        <tr>
            <td align="right">户外检验检测场地面积（㎡）：<span style="color:red;">*</span></td>
            <td colspan="3">
                <input id="outdoorsiteArea" name="outdoorsiteArea" class="nui-textbox" width="547px" required="true" onvaluechanged="valueChangeEvent2()" vtype="float" EmptyText="请输入数字.."/>
            </td>
        </tr>
        <tr>
            <td align="right">场地产权状况：<span style="color:red;">*</span></td>
            <td>
                <input id="sitePropertyRights" name="sitePropertyRights" class="nui-combobox" style="width:180px" valueField="text" required="true" dataField="data" textField="text" required="true"/>
            </td>
            <td align="right">场地产权状况值（%）：<span style="color:red;">*</span></td>
            <td>
                <input name="sitePropertyRightsValue" class="nui-textbox" width="180px" required="true"/>
            </td>
        </tr>
    </table>
    <div>
        <a id="saveJgzy" class="nui-button" iconCls="icon-save" onclick="saveJgzy()" style="margin-left: 45%;">保存</a>
    </div>
    <div>
        <fieldset style="border: solid 1px #aaa; padding: 3px;width:93%;">
            <legend><b>本次申请的地点</b><span style="color:red;">*</span></legend>
            <a id="addCd" class="nui-button" iconCls="icon-add" onclick="addCd()" style="">新增</a>
            <a id="editCd" class="nui-button" iconCls="icon-edit" onclick="editCd()" style="">编辑</a>
            <a id="deleteCd" class="nui-button" iconCls="icon-remove" onclick="deleteCd()" style="">删除</a>
            <div id="dataGridAddress" class="nui-datagrid" style="width:100%;height:120px;" multiSelect="true" idField="sqsId" showPager="false">
                <div property="columns">
                    <div type="checkcolumn"></div>
                    <div type="indexcolumn" align="center" headerAlign="center">序号</div>
                    <div field="siteAddress" width="120" align="center" headerAlign="center">地址</div>
                </div>
            </div>
        </fieldset>
    </div>
</form>
<script type="text/javascript">
    nui.parse();
    var form = new nui.Form("form1");
    var dataGridAddress = nui.get("dataGridAddress");
    nui.get("propertyRights").setData("[{'text':'自有'},{'text':'租用'},{'text':'合资'}]");
    nui.get("sitePropertyRights").setData("[{'text':'自有'},{'text':'租用'},{'text':'其他'}]");
    // 流水号
    var serialNumber = '<%=request.getParameter("serialNumber")%>';
    var action = '<%=request.getParameter("action")%>';
    var detail = "<%=request.getParameter("detail")%>";

    nui.get("serialNumber").setValue(serialNumber);

    //详情查看
    if (detail == "detail") {
        setEnabled();
    }

    // 初始化 获取场地数据
    dataGridAddress.load("<%=basePath%>apiShiYsJyjcCd_initLoad.action?serialNumber=" + serialNumber + "&siteType=2");
    dataGridAddress.reload();

    function saveJgzy() {
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
            url: "<%=basePath%>apiShiYsJyjcJgzy_save.action",
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

    function addCd() {
        nui.open({
            url: "<%=basePath%>jsp/jyjc/rzrk/zzrd/sqs/apiCd.jsp",
            title: "本次申请的地点",
            width: '70%',
            height: '50%',
            onload: function () {
                var iframe = this.getIFrameEl();
                var data = {serialNumber: serialNumber, action: "add"};
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {
                dataGridAddress.reload();
            }
        });
    }

    function editCd() {
        var rows = dataGridAddress.getSelecteds();
        if (rows == null || rows == "" || rows.length == 0) {
            nui.alert("请选择一条进行编辑！", "提醒！");
            return;
        }
        if (rows.length > 1) {
            nui.alert("只能选择一条进行编辑！", "提醒！");
            return;
        }

        var row = dataGridAddress.getSelected();
        nui.open({
            url: "<%=basePath%>jsp/jyjc/rzrk/zzrd/sqs/apiCd.jsp",
            title: "本次申请的地点",
            width: '70%',
            height: '50%',
            onload: function () {
                var iframe = this.getIFrameEl();
                var data = {serialNumber: serialNumber, action: "edit", row: row};
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {
                dataGridAddress.reload();
            }
        });
    }

    function deleteCd() {
        var rows = dataGridAddress.getSelecteds();
        if (rows == null || rows == "" || rows.length == 0) {
            nui.alert("请最少选择一条数据删除！", "提醒！");
            return;
        }
        var deleteIds = [];
        $.each(rows, function (i, data) {
            deleteIds.push(data.id);
        })
        deleteIds = nui.encode(deleteIds);
        $.ajax({
            url: "<%=basePath%>apiShiYsJyjcCd_delete.action",
            type: 'post',
            data: {deleteIds: deleteIds},
            cache: false,
            success: function (text) {
                var map = nui.decode(text);
                nui.alert(map.msg, "提示!");
                dataGridAddress.reload();
            },
            error: function (jqXHR, textStatus, errorThrown) {//如果不能获得数据
                nui.alert(jqXHR.responseText);//弹出错误
                CloseWindow();
            }
        });
    }

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
                    nui.get("saveJgzy").setEnabled(false);
                    nui.get("addCd").setEnabled(false);
                    nui.get("editCd").setEnabled(false);
                    nui.get("deleteCd").setEnabled(false);
                    return;
                }
                // 封面信息 存在，加载 初始化概况 数据
                initLoad(serialNumber);
                nui.get("addCd").setEnabled(true);
                nui.get("editCd").setEnabled(true);
                nui.get("deleteCd").setEnabled(true);
            }
        });
    }

    function initLoad(serialNumber) {
        $.ajax({
            url: "<%=basePath%>apiShiYsJyjcJgzy_initLoad.action",
            type: 'post',
            data: {serialNumber: serialNumber},
            cache: false,
            success: function (text) {
                var map = nui.decode(text);
                if (map.code == 0) {
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

    function valueChangeEvent() {
        var hJobTitleNumber = nui.get("hJobTitleNumber").getValue();
        hJobTitleNumber = Number(hJobTitleNumber);
        var mJobTitleNumber = nui.get("mJobTitleNumber").getValue();
        mJobTitleNumber = Number(mJobTitleNumber);
        var pJobTitleNumber = nui.get("pJobTitleNumber").getValue();
        pJobTitleNumber = Number(pJobTitleNumber);
        var otherNumber = nui.get("otherNumber").getValue();
        otherNumber = Number(otherNumber);
        //计算总和
        var allNumber = hJobTitleNumber + mJobTitleNumber + pJobTitleNumber + otherNumber;
        allNumber = toDecimal(allNumber);
        nui.get("allNumber").setValue(allNumber);

        //计算百分比
        if (allNumber == 0) {
            alert("请输入正整数")
            return;
        }
        var hRatioAll = hJobTitleNumber / allNumber;
        hRatioAll = toDecimal2(hRatioAll);
        nui.get("hRatioAll").setValue(hRatioAll);

        var mRatioAll = mJobTitleNumber / allNumber;
        mRatioAll = toDecimal2(mRatioAll);
        nui.get("mRatioAll").setValue(mRatioAll);

        var pRatioAll = pJobTitleNumber / allNumber;
        pRatioAll = toDecimal2(pRatioAll);
        nui.get("pRatioAll").setValue(pRatioAll);

        var oRatioAll = otherNumber / allNumber;
        oRatioAll = toDecimal2(oRatioAll);
        nui.get("oRatioAll").setValue(oRatioAll);
    }

    function toDecimal(x) {
        var f = parseFloat(x);
        if (isNaN(f)) {
            return "0";
        }
        var f = Math.round(x * 100) / 100;
        return f;
    }

    function toDecimal2(x) {
        var f = parseFloat(x);
        if (isNaN(f)) {
            return "0.00";
        }
        var f = Math.round(x * 10000) / 100;
        return f.toFixed(2);
    }

    function valueChangeEvent2() {
        //检验检测场地面积
        var siteArea = nui.get("siteArea").getValue();
        siteArea = Number(siteArea);
        //温恒面积
        var whArea = nui.get("whArea").getValue();
        whArea = Number(whArea);
        //户外检验检测场地面积
        var outdoorsiteArea = nui.get("outdoorsiteArea").getValue();
        outdoorsiteArea = Number(outdoorsiteArea);
        //总面积
        var institutionsArea = siteArea + whArea + outdoorsiteArea;
        institutionsArea = toDecimal(institutionsArea);
        nui.get("institutionsArea").setValue(institutionsArea)
    }

    function setEnabled() {
        nui.get('saveJgzy').hide();
        nui.get('addCd').hide();
        nui.get('editCd').hide();
        nui.get('deleteCd').hide();
        nui.getbyName("hJobTitleNumber").setEnabled(false);
        nui.getbyName("mJobTitleNumber").setEnabled(false);
        nui.getbyName("pJobTitleNumber").setEnabled(false);
        nui.getbyName("pRatioAll").setEnabled(false);
        nui.getbyName("otherNumber").setEnabled(false);
        nui.getbyName("fixedAssets").setEnabled(false);
        nui.getbyName("equipmentNumber").setEnabled(false);
        nui.getbyName("propertyRights").setEnabled(false);
        nui.getbyName("propertyRightsValue").setEnabled(false);
        nui.getbyName("siteArea").setEnabled(false);
        nui.getbyName("whArea").setEnabled(false);
        nui.getbyName("outdoorsiteArea").setEnabled(false);
        nui.getbyName("sitePropertyRights").setEnabled(false);
        nui.getbyName("sitePropertyRightsValue").setEnabled(false);
    }
</script>
</body>
</html>