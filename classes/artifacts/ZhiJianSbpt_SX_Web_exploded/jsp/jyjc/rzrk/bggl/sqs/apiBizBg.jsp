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
<form id="form1" target="id_iframe" style="padding-top:5px;margin:0 auto; border:1px;width: 95%">
    <h1 align="center" style="margin-top:10px">封面信息</h1>
    <input type="hidden" id="serialNumber" class="nui-hidden" name="serialNumber"/>
    <input type="hidden" id="id" class="nui-hidden" name="id"/>
    <fieldset style="border: solid 1px #aaa; padding: 3px; width:65%; margin: 0 auto">
        <legend><label style="font-size: 18px;">基本信息</label></legend>
        <div style="margin:auto; padding:5px;">
            <table style="margin: auto;padding-right: 8%" align="center" border="0">
                <tr>
                    <td align="right" style="width:30%">机构名称：<span style="color:red;">*</span></td>
                    <td colspan="3">
                        <input id="jgmc" name="jgmc" class="nui-textbox" width="97%" EmptyText="请输入机构名称"
                               required="true"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">负责人：<span style="color:red;">*</span></td>
                    <td>
                        <input id="principal" name="principal" class="nui-textbox" width="180px" required="true" EmptyText="请输入负责人"/>
                    </td>
                    <td align="right">负责人电话：<span style="color:red;">*</span></td>
                    <td>
                        <input id="phone" name="phone" class="nui-textbox" width="180px" required="true" EmptyText="请输入负责人电话" onvalidation="onPhoneValidation"/>
                    </td>
                </tr>

                <tr>
                    <td align="right">社会信用代码：<span style="color:red;">*</span></td>
                    <td>
                        <input id="shxydm" name="shxydm" class="nui-textbox" width="180px" EmptyText="请输入社会信用代码" required="true" onvalidation="checkValue"/>
                    </td>
                    <td align="right">住所：<span style="color:red;">*</span></td>
                    <td>
                        <input id="registerAdress" name="registerAdress" class="nui-textbox" width="180px" required="true" EmptyText="请输入住所"/>
                    </td>
                </tr>

                <tr>
                    <td align="right"> 联络人：<span style="color:red;">*</span></td>
                    <td>
                        <input id="contactPerson" name="contactPerson" class="nui-textbox" width="180px" required="true" EmptyText="请输入联络人"/>
                    </td>
                    <td align="right"> 联络人职位：<span style="color:red;">*</span></td>
                    <td>
                        <input id="contactPosition" name="contactPosition" class="nui-textbox" width="180px" required="true" EmptyText="请输入联络人职位"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">联络人固定电话：<span style="color:red;">*</span></td>
                    <td>
                        <input id="contactTelephone" name="contactTelephone" class="nui-textbox" width="180px" required="true" onvalidation="onPhoneValidation"/>
                    </td>
                    <td align="right">联络人手机：<span style="color:red;">*</span></td>
                    <td>
                        <input id="contactPhone" name="contactPhone" class="nui-textbox" width="180px" required="true"
                               onvalidation="onPhoneValidation" EmptyText="请输入联络人手机"/>
                    </td>

                </tr>
                <tr>
                    <td align="right">cma证书编号：<span style="color:red;">*</span></td>
                    <td>
                        <input id="cmaPermitNumber" name="cmaPermitNumber" class="nui-textbox" width="180px"
                               required="true" EmptyText="请输入证书编号"
                               onvaluechanged="getJgobjectByZsbh()"/>
                    </td>
                </tr>

                <tr>
                    <td align="right">cma发证日期：<span style="color:red;">*</span></td>
                    <td>
                        <input id="cmaDateoficcue" name="cmaDateoficcue" class="nui-datepicker" style="width: 180px"
                               EmptyText="请选择日期" required="true" allowInput="false"/>
                    </td>
                    <td align="right">cma有效日期：<span style="color:red;">*</span></td>
                    <td>
                        <input id="cmaPermitEffectiveDate" name="cmaPermitEffectiveDate" class="nui-datepicker" style="width: 180px" required="true" EmptyText="请选择日期" allowInput="false"/>
                    </td>
                </tr>

                <tr>
                    <td align="right">变更类型：<span style="color:red;">*</span></td>
                    <td>
                        <input id="state" name="state" class="nui-combobox" emptytext="请选择..." valueField="id" width="180" required="true"
                               data="[{id:'11',text:'标准（方法）变更'},{id:'12',text:'机构名称变更'},{id:'13',text:'机构地址名称变更'},{id:'14',text:'机构法人单位变更'},{id:'15',text:'机构取消检测检测能力'},{id:'16',text:'检验检测机构人员变更'},{id:'17',text:'授权签字人变更'}]"
                        />
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
            </table>
        </div>
    </fieldset>
    <div>
        <a id="saveFmxx" class="nui-button" iconCls="icon-save" onclick="saveFmxx()" style="margin-left: 47%;margin-top:20px">保存</a>
    </div>
</form>
<script type="text/javascript">
    nui.parse();
    var action = "<%=request.getParameter("action")%>";
    var serialNumber = "<%=request.getParameter("serialNumber")%>";

    nui.get("serialNumber").setValue(serialNumber);

    var form = new nui.Form("form1");

    //初始化页面
    function initValue() {
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
                var jgmc = nui.get("jgmc").getValue();
                if (data.qymc != null && (jgmc == null || jgmc == "")) {
                    nui.get("jgmc").setValue(data.qymc);
                }
                var contactPerson = nui.get("contactPerson").getValue();
                if (data.lxr != null && (contactPerson == null || contactPerson == "")) {
                    nui.get("contactPerson").setValue(data.lxr);
                }
                var contactPhone = nui.get("contactPhone").getValue();
                if (data.lxdh != null && (contactPhone == null || contactPhone == "")) {
                    nui.get("contactPhone").setValue(data.lxdh);
                }

                // nui.get("areaId").setText(data.areaName);
                // nui.get("areaId").setValue(data.areaId);
                // nui.get("checkAreaId").setText(data.checkAreaName);
                // nui.get("checkAreaId").setValue(data.checkAreaId);
            },
            error: function (jqXHR, textStatus, errorThrown) {//如果不能获得数据
                nui.alert(jqXHR.responseText);//弹出错误
            }
        })
    }

    // 页面数据 初始化
    initLoad(serialNumber);

    if (action == 'detail') {
        setEnabled();
    }

    function initLoad(serialNumber) {
        $.ajax({
            url: "<%=basePath%>bizApiZwcnBg_initLoad.action",
            type: 'post',
            data: {serialNumber: serialNumber},
            cache: false,
            success: function (text) {
                var map = nui.decode(text);
                if (map.code != 0) {
                    initValue();
                    return;
                }
                var data = map.data;
                areaId.setText(data.areaName);
                areaId.setValue(data.areaId);
                checkAreaId.setText(data.checkAreaName);
                checkAreaId.setValue(data.chcekAreaId);
                form.setData(map.data);
            }
        });
    }

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
            url: "<%=basePath%>bizApiZwcnBg_save.action",
            type: 'post',
            cache: 'false',
            async: false,
            data: {data: reqJson},
            success: function (resultData) {
                resultData = nui.decode(resultData);
                nui.alert(resultData.msg, "提示!");
                CloseWindow();
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
        //是企业时要填写9位组织机构代码或18位社会信用代码
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
        nui.getbyName("jgmc").setEnabled(false);
        nui.getbyName("principal").setEnabled(false);
        nui.getbyName("phone").setEnabled(false);
        nui.getbyName("shxydm").setEnabled(false);
        nui.getbyName("registerAdress").setEnabled(false);
        nui.getbyName("contactPerson").setEnabled(false);
        nui.getbyName("contactPosition").setEnabled(false);
        nui.getbyName("contactTelephone").setEnabled(false);
        nui.getbyName("contactPhone").setEnabled(false);
        nui.getbyName("cmaPermitNumber").setEnabled(false);
        nui.getbyName("cmaDateoficcue").setEnabled(false);
        nui.getbyName("cmaPermitEffectiveDate").setEnabled(false);
        nui.getbyName("state").setEnabled(false);
        nui.getbyName("areaId").setEnabled(false);
        nui.getbyName("checkAreaId").setEnabled(false);
    }

    function getJgobjectByZsbh() {
        var regexp_1 = /^\d{12}$/;
        var zsbh = nui.get('cmaPermitNumber').getValue();
        var result = regexp_1.test(zsbh);
        if (!result) {
            nui.alert('请输入12位纯数字的证书编号', '温馨提示');
            nui.get('saveFmxx').setEnabled(false);
            return;
        }
        nui.get('saveFmxx').setEnabled(true);
    }
</script>
</body>
</html>

