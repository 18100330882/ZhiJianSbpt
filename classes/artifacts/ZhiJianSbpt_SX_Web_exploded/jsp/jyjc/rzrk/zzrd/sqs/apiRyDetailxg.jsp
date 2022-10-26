<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>人员信息编辑页面</title>
    <script type="text/javascript" src="<%=basePath%>nui/nui.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>

<body>

<form id="form1" target="id_iframe" style="padding-top:5px;margin:0 auto; width:720px; border:1px;">
    <h1 align="center" style="margin-top:10px">检验检测机构人员信息</h1>
    <input type="hidden" id="serialNumber" class="nui-hidden" name="serialNumber" value=""/>
    <fieldset style="width:90%;height: 60%;">
        <legend><label style="font-size: 18px;">基本信息</label></legend>
        <table border="0">
            <tr>
                <td align="right">姓名：<span style="color:red;">*</span></td>
                <td>
                    <input id="name" name="name" class="nui-textbox" width="180px" required="true"/>
                </td>
                <td align="right">性别：<span style="color:red;">*</span></td>
                <td>
                    <div id="rblsex" name="sex" class="nui-radiobuttonlist" repeatItems="2" repeatLayout="table" repeatDirection="horizontal" textField="text" valueField="id" value="男">
                    </div>
                </td>
            </tr>
            <tr>
                <td align="right">年龄：<span style="color:red;">*</span></td>
                <td>
                    <input id="age" name="age" class="nui-textbox" width="180px" required="true" vtype="int"/>
                </td>
                <td align="right">文化程度：<span style="color:red;">*</span></td>
                <td>
                    <input id="education" name="education" class="nui-textbox" width="180px" required="true"/>
                </td>
            </tr>
            <tr>
                <td align="right">证件类型：<span style="color:red;">*</span></td>
                <td>
                    <input id="documentType" name="documentType" class="nui-textbox" width="180px" required="true"/>
                </td>
                <td align="right">身份证号：<span style="color:red;">*</span></td>
                <td>
                    <input id="idCard" name="idCard" class="nui-textbox" width="180px" required="true" onvalidation="onCardNoValidation"/>
                </td>
            </tr>
            <tr>
                <td align="right">职务：<span style="color:red;">*</span></td>
                <td>
                    <input id="position" name="position" class="nui-textbox" width="180px" required="true"/>
                </td>
                <td align="right">职称：<span style="color:red;">*</span></td>
                <td>
                    <input id="jobTitle" name="jobTitle" class="nui-textbox" width="180px" required="true"/>
                </td>
            </tr>

            <tr>
                <td align="right">专业：<span style="color:red;">*</span></td>
                <td>
                    <input id="professional" name="professional" class="nui-textbox" width="180px" required="true"/>
                </td>
            </tr>
            <tr>
                <td align="right">从事本技术领域年限：<span style="color:red;">*</span></td>
                <td>
                    <input id="technicalFieldYear" name="technicalFieldYear" class="nui-textbox" width="180px" required="true" EmptyText="请输入年限(数字)" vtype="float"/>
                </td>
            </tr>
            <tr>
                <td align="right">部门岗位：<span style="color:red;">*</span></td>
                <td>
                    <input id="department" name="department" class="nui-textbox" width="180px" required="true"/>
                </td>
            </tr>
            <tr>
                <td align="right">检验检测机构地址：<span style="color:red;">*</span></td>
                <td colspan="4">
                    <input id="address" name="address" class="nui-combobox" emptytext="请选择..." style="width:100%" valueField="SITEADDRESS" required="true" dataField="data" textField="SITEADDRESS"/>
                </td>
            </tr>
        </table>
        <div>
            <a id="save" class="nui-button" iconCls="icon-save" onclick="save()" style="margin-left: 45%;margin-top:10%">保存</a>
        </div>
    </fieldset>
</form>

<script type="text/javascript">
    nui.parse();
    var form = new nui.Form("form1");
    var rblsex = nui.get("rblsex");
    rblsex.load([{"id": "男", "text": "男"}, {"id": "女", "text": "女"}]);
    var serialNumber = '<%=request.getParameter("serialNumber")%>';
    var bjzt = '<%=request.getParameter("zt")%>';
    var ID = '<%=request.getParameter("id")%>';
    nui.get("serialNumber").setValue(serialNumber);

    //保存按钮消失
    if (bjzt == 'look') {
        nui.get("save").hide();
    }

    // 地址
    var address = nui.get("address");
    address.load("<%=basePath%>apiShiYsJyjcCd_getJgdzData.action?serialNumber=" + serialNumber);

    function save() {
        //获取文本信息
        var formData = form.getData();
        form.validate();
        if (form.isValid() == false) {
            return;
        }
        var reqJson = nui.encode(formData);
        $.ajax({
            url: "<%=basePath%>apiShiYsJyjcRy_save.action",
            type: 'post',
            cache: 'false',
            async: false,
            data: {data: reqJson, zt: bjzt, id: ID},
            success: function (resultData) {
                resultData = nui.decode(resultData);
                nui.alert(resultData.msg, "提示!", function () {
                    CloseWindow();
                });
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

    function SetData(data) {
        row = data.row;

        nui.get("name").setValue(row.NAME);
        nui.get("rblsex").setValue(row.SEX);
        nui.get("age").setValue(row.AGE);
        nui.get("education").setValue(row.EDUCATION);
        nui.get("documentType").setValue(row.DOCUMENTTYPE);
        nui.get("idCard").setValue(row.IDCARD);
        nui.get("position").setValue(row.POSITION);
        nui.get("jobTitle").setValue(row.JOBTITLE);
        nui.get("professional").setValue(row.PROFESSIONAL);
        nui.get("technicalFieldYear").setValue(row.TECHNICALFIELDYEAR);
        nui.get("department").setValue(row.DEPARTMENT);
        nui.get("address").setValue(row.ADDRESS);

    }

    /** 身份证验证 */
    function onCardNoValidation(e) {
        if (e.value == "") return;
        if (e.isValid) {
            var city = {
                11: "北京", 12: "天津", 13: "河北", 14: "山西", 15: "内蒙古", 21: "辽宁", 22: "吉林", 23: "黑龙江 ",
                31: "上海", 32: "江苏", 33: "浙江", 34: "安徽", 35: "福建", 36: "江西", 37: "山东", 41: "河南",
                42: "湖北 ", 43: "湖南", 44: "广东", 45: "广西", 46: "海南", 50: "重庆", 51: "四川", 52: "贵州",
                53: "云南", 54: "西藏 ", 61: "陕西", 62: "甘肃", 63: "青海", 64: "宁夏", 65: "新疆", 71: "台湾",
                81: "香港", 82: "澳门", 91: "国外 ",
            };
            if (!e.value || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[012])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(e.value)) {
                e.errorText = "身份证号输入错误!";
                e.isValid = false;
            } else if (!city[e.value.substr(0, 2)]) {
                e.errorText = "身份证号输入错误!";
                e.isValid = false;
            } else {
                //18位身份证需要验证最后一位校验位
                if (e.value.length == 18) {
                    //e.value = e.value.valuelit('');
                    //∑(ai×Wi)(mod 11)
                    //加权因子
                    var factor = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
                    //校验位
                    var parity = [1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2];
                    var sum = 0;
                    var ai = 0;
                    var wi = 0;
                    for (var i = 0; i < 17; i++) {
                        ai = e.value[i];
                        wi = factor[i];
                        sum += ai * wi;
                    }
                    var last = parity[sum % 11];

                    if (last != e.value[17].toUpperCase()) {
                        e.errorText = "身份证号输入错误!";
                        e.isValid = false;
                    }
                }
            }
        }
    }
</script>
</body>
</html>