<%@ page language="Java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
<style>
    #cancelReason {
        height: 80px;
    }
</style>
<form id="form1" target="id_iframe" style="padding-top:5px;margin:0 auto; width:840px; border:1px;">
    <h1 align="center" style="margin-top:10px">注销申请表信息</h1>
    <input type="hidden" id="serialNumber" class="nui-hidden" name="serialNumber" value=""/>
    <input type="hidden" id="flowId" class="nui-hidden" name="flowId" value=""/>
    <fieldset style="width:97%;height: 60%;">
        <legend><label style="font-size: 18px;">基本信息</label></legend>
        <table border="0">
            <tr>
                <td align="right" style="width:230px;">机构名称：<span style="color:red;">*</span></td>
                <td colspan="3">
                    <input id="organizationName" name="organizationName" class="nui-textbox" width="557px" required="true"/>
                </td>
            </tr>
            <tr>
                <td align="right" style="width:230px;">机构地址：<span style="color:red;">*</span></td>
                <td colspan="3">
                    <input id="organizationAddress" name="organizationAddress" class="nui-textbox" width="557px" required="true"/>
                </td>
            </tr>
            <tr>
                <td align="right">证书编号：<span style="color:red;">*</span></td>
                <td>
                    <input name="certificateNumber" class="nui-textbox" width="200px" required="true"/>
                </td>
                <td align="right">社会信用代码：<span style="color:red;">*</span></td>
                <td>
                    <input id="shxydm" name="shxydm" class="nui-textbox" width="200px" required="true" onvalidation="checkValue"/>
                </td>
            </tr>
            <tr>
                <td align="right">发证日期：<span style="color:red;">*</span></td>
                <td>
                    <input name="startDate" class="nui-datepicker" style="width: 202px" required="true" allowInput="false"/>
                </td>
                <td align="right">有效日期：<span style="color:red;">*</span></td>
                <td>
                    <input name="endDate" class="nui-datepicker" style="width: 202px" required="true" allowInput="false"/>
                </td>
            </tr>
            <tr>
                <td align="right">联系人：<span style="color:red;">*</span></td>
                <td>
                    <input id="contacts" name="contacts" class="nui-textbox" width="200px" required="true"/>
                </td>
                <td align="right">联系手机：<span style="color:red;">*</span></td>
                <td>
                    <input id="contactsPhone" name="contactsPhone" class="nui-textbox" width="200px" required="true" onvalidation="onPhoneValidation"/>
                </td>
            </tr>
            <tr>
                <td align="right">单位负责人：<span style="color:red;">*</span></td>
                <td>
                    <input name="unitPeople" class="nui-textbox" width="200px" required="true"/>
                </td>
                <td align="right">负责人手机：<span style="color:red;">*</span></td>
                <td>
                    <input name="unitPhone" class="nui-textbox" width="200px" required="true" onvalidation="onPhoneValidation"/>
                </td>
            </tr>
            <tr>
                <td align="right">
                    行政区域：<span style="color:red;">*</span>
                </td>
                <td>
                    <input name="areaId" id="areaId" style="width: 180px;" class="nui-buttonedit" onbuttonclick="areaClick" required="true"/>
                </td>
                <td align="right">
                    上报机关：<span style="color:red;">*</span>
                </td>
                <td>
                    <input name="checkAreaId" id="checkAreaId" style="width: 180px;" class="nui-buttonedit" onbuttonclick="areaClick" required="true"/>
                </td>
            </tr>

            <tr style="display: none">
                <td align="right" style="width:230px;">申请注销日期：</td>
                <td colspan="3">
                    <input name="organizationDate" class="nui-datepicker" width="202px" required="true"/>
                </td>
            </tr>
            <tr>
                <td align="right" style="width:230px;">申请注销的详细原因：<span style="color:red;">*</span></td>
                <td colspan="3">
                    <input id="cancelReason" name="cancelReason" class="nui-textarea" width="557px" required="true"/>
                </td>
            </tr>
        </table>
        <div>
            <a id="saveFmxx" class="nui-button" iconCls="icon-save" onclick="saveFmxx()" style="margin-left: 45%;margin-top:10%">保存</a>
        </div>
    </fieldset>
</form>
<script type="text/javascript">
    nui.parse();
    var action;
    var serialNumber;
    var flowId;

    function SetData(data) {
        action = data.action;
        serialNumber = data.serialNumber;
        flowId = data.flowId;
        detail = data.detail;
        nui.get("serialNumber").setValue(serialNumber);
        nui.get("flowId").setValue(flowId);
        // 页面数据 初始化
        if (action == 'edit') {
            initLoad(serialNumber);
        }
        if (detail == 'detail') {
            setEnabled();
        }
    }

    //初始化页面
    $(function () {
        $.ajax({
            url: "<%=basePath%>regist_getInfoByUserName.action",
            type: 'post',
            cache: false,
            success: function (text) {
                //成功获得数据时
                var data = nui.decode(text);
                var shxydm = nui.get("shxydm").getValue();
                if (data.zzjgdm != null && (shxydm == null || shxydm == "")) {
                    nui.get("shxydm").setValue(data.zzjgdm);
                }
                var organizationName = nui.get("organizationName").getValue();
                if (data.qymc != null && (organizationName == null || organizationName == "")) {
                    nui.get("organizationName").setValue(data.qymc);
                }
                var contacts = nui.get("contacts").getValue();
                if (data.lxr != null && (contacts == null || contacts == "")) {
                    nui.get("contacts").setValue(data.lxr);
                }
                var contactsPhone = nui.get("contactsPhone").getValue();
                if (data.lxdh != null && (contactsPhone == null || contactsPhone == "")) {
                    nui.get("contactsPhone").setValue(data.lxdh);
                }
                var organizationAddress = nui.get("organizationAddress").getValue();
                if (data.zcdz != null && (organizationAddress == null || organizationAddress == "")) {
                    nui.get("organizationAddress").setValue(data.zcdz);
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {//如果不能获得数据
                nui.alert(jqXHR.responseText);//弹出错误
            }
        })
    });

    var form = new nui.Form("form1");

    function saveFmxx() {
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
            url: "<%=basePath%>bizApiZxsqb_save.action",
            type: 'post',
            cache: 'false',
            async: false,
            data: {data: reqJson},
            success: function (resultData) {
                resultData = nui.decode(resultData);
                nui.alert(resultData.msg, "提示!", function () {
                    CloseWindow();
                });
            }
        });
    }

    function initLoad(serialNumber) {
        $.ajax({
            url: "<%=basePath%>bizApiZxsqb_initLoad.action",
            type: 'post',
            data: {serialNumber: serialNumber},
            cache: false,
            success: function (text) {
                var map = nui.decode(text);
                if (map.code == 0) {
                    var data = map.data;
                    areaId.setText(data.areaName);
                    areaId.setValue(data.areaId);
                    checkAreaId.setText(data.checkAreaName);
                    checkAreaId.setValue(data.chcekAreaId);
                    form.setData(map.data);
                    return;
                }
                if ("edit" == action) {
                    nui.alert(map.msg, "提示!");
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {//如果不能获得数据
                nui.alert(jqXHR.responseText);//弹出错误
                onCancel();
            }
        });
    }

    //关闭子窗口，刷新父窗体
    function CloseWindow(action) {
        if (window.CloseOwnerWindow)
            return window.CloseOwnerWindow(action);
        else
            window.close();
    }

    function onCancel(e) {
        CloseWindow("cancel");
    }

    function checkValue(e) {//判断组织机构代码和信用代码是否合法
        var value = e.value;
        //18位社会信用代码
        if (value.length == 18) {
            e.isValid = true;
        } else {
            e.errorText = "请填写组织机构代码9位，社会信用代码18位.";
            e.isValid = false;
        }
    }

    /** 手机和固话验证 */
    function onPhoneValidation(e) {
        if (e.value == "") return;
        if (telephone(e.value) == false && mobilephone(e.value) == false) {
            e.isValid = false;
            e.errorText = e.source.requiredErrorText;
        } else {
            e.isValid = true;
        }
    }

    function telephone(v) {//固话验证
        var re = /^0\d{2,3}-?\d{7,8}$/;
        if (re.test(v)) return true;
        return false;
    }

    function mobilephone(v) {//手机验证
        var re = /^1(3|5|8)\d{9}$/;
        if (re.test(v)) return true;
        return false;
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
                    console.log(data)
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

    function setEnabled() {
        nui.get("saveFmxx").hide();
        nui.getbyName("organizationName").setEnabled(false);
        nui.getbyName("organizationAddress").setEnabled(false);
        nui.getbyName("certificateNumber").setEnabled(false);
        nui.getbyName("shxydm").setEnabled(false);
        nui.getbyName("startDate").setEnabled(false);
        nui.getbyName("endDate").setEnabled(false);
        nui.getbyName("contacts").setEnabled(false);
        nui.getbyName("contactsPhone").setEnabled(false);
        nui.getbyName("unitPeople").setEnabled(false);
        nui.getbyName("unitPhone").setEnabled(false);
        nui.getbyName("areaId").setEnabled(false);
        nui.getbyName("checkAreaId").setEnabled(false);
        nui.getbyName("organizationDate").setEnabled(false);
        nui.getbyName("cancelReason").setEnabled(false);
    }
</script>
</body>
</html>