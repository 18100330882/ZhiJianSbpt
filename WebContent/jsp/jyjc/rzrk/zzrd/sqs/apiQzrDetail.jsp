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
    <h1 align="center" style="margin-top:10px">授权签字人详情页面</h1>
    <input type="hidden" id="serialNumber" class="nui-hidden" name="serialNumber" value=""/>
    <input type="hidden" id="id" class="nui-hidden" name="id" value=""/>
    <table border="0">
        <tr>
            <td align="right">姓名：<span style="color:red;">*</span></td>
            <td>
                <input id="name" name="name" class="nui-textbox" width="180px" required="true" EmptyText="请输入姓名"/>
            </td>
            <td align="right">性别：<span style="color:red;">*</span></td>
            <td>
                <div id="rblsex" name="sex" class="nui-radiobuttonlist" repeatItems="2" repeatLayout="table"
                     repeatDirection="horizontal" required="true"
                     textField="text" valueField="id" value="男">
                </div>
            </td>
        </tr>

        <tr>
            <td align="right">类型：<span style="color:red;">*</span></td>
            <td>
                <input id="peopleType" name="peopleType" class="nui-combobox" emptytext="请选择..." valueField="text"
                       width="180px" required="true"
                       data="[{id:'1',text:'食品'},{id:'2',text:'非食品'}]"/>
            </td>
        </tr>

        <tr>
            <td align="right">检验检测机构地址：<span style="color:red;">*</span></td>
            <td>
                <input id="address" name="address" class="nui-combobox" emptytext="请选择...(若无选项,请在机构资源添加申请地址)"
                       style="width:380px"
                       valueField="SITEADDRESS" required="true"
                       dataField="data" textField="SITEADDRESS"/>
            </td>
        </tr>

        <tr>
            <td align="right">身份证号：<span style="color:red;">*</span></td>
            <td>
                <input id="idCard" name="idCard" class="nui-textbox" width="180px" required="true"
                       onvalidation="onCardNoValidation" EmptyText="请输入身份证号码"/>
            </td>
            <td align="right">出生年月：<span style="color:red;">*</span></td>
            <td>
                <input id="birthday" name="birthday" class="nui-datepicker" width="180px" required="true"
                       EmptyText="请选择出生年月"/>
            </td>
        </tr>

        <tr>
            <td align="right">职务(岗位)：<span style="color:red;">*</span></td>
            <td>
                <input id="position" name="position" class="nui-textbox" width="180px" required="true"
                       EmptyText="请输入职务(岗位)"/>
            </td>
            <td align="right">职称：<span style="color:red;">*</span></td>
            <td>
                <input id="jobTitle" name="jobTitle" class="nui-textbox" width="180px" required="true"
                       EmptyText="请输入职称"/>
            </td>
        </tr>

        <tr>
            <td align="right">文化程度：<span style="color:red;">*</span></td>
            <td>
                <input id="education" name="education" class="nui-combobox" emptytext="请选择..." valueField="text"
                       width="180px" required="true"
                       data="[{id:'1',text:'博士研究生'},{id:'2',text:'硕士研究生'},{id:'3',text:'大学本科'},{id:'4',text:'大学专科'},{id:'5',text:'中等专科'},{id:'7',text:'职业高中'},{id:'8',text:'高中'},{id:'9',text:'初中'},{id:'10',text:'小学'},{id:'11',text:'无学历'}]"/>
            </td>
            <td align="right">专业：</td>
            <td>
                <input id="professional" name="professional" class="nui-textbox" width="180px"/>
            </td>

        </tr>

        <tr>
            <td align="right">毕业学校：</td>
            <td>
                <input id="school" name="school" class="nui-textbox" width="180px"/>
            </td>

        </tr>

        <tr>
            <td align="right">部门岗位：<span style="color:red;">*</span></td>
            <td>
                <input id="department" name="department" class="nui-textbox" width="180px" required="true"/>
            </td>

        </tr>

        <tr>
            <td align="right">电话：<span style="color:red;">*</span></td>
            <td>
                <input id="phone" name="phone" class="nui-textbox" width="180px" required="true"
                       onvalidation="onPhoneValidation"/>
            </td>
            <td align="right">传真：</td>
            <td>
                <input id="fax" name="fax" class="nui-textbox" width="180px"/>
            </td>
        </tr>

        <tr>
            <td align="right">电子邮箱：</td>
            <td>
                <input id="email" name="email" class="nui-textbox" width="180px" vtype="email"/>
            </td>
        </tr>

        <tr>
            <td align="right">申请签字的领域：<span style="color:red;">*</span></td>
            <td>
                <input id="signatureField" name="signatureField" class="nui-textarea" width="450px" height="50px"
                       required="true"/>
            </td>
        </tr>

        <tr>
            <td align="right">执业资格证书：</td>
            <td>
                <input id="jobCredentials" name="jobCredentials" class="nui-textbox" width="180px"/>
            </td>
        </tr>

    </table>

    <table>
        <tr>
            <td align="right">培训经历：</td>
            <td>
                <input id="train" name="train" class="nui-textarea" width="580px" EmptyText="请输入培训经历..."/>
            </td>
        </tr>

        <tr>
            <td align="right">从事检测机构工作的经历：</td>
            <td>
                <input id="jobUndergo" name="jobUndergo" class="nui-textarea" width="580px"
                       EmptyText="请输入从事检测机构工作的经历..."/>
            </td>
        </tr>

        <tr>
            <td align="right">相关说明：</td>
            <td>
                <input id="relevantExplanation" name="relevantExplanation" class="nui-textarea" width="580px"
                       EmptyText="请输入相关说明..."/>
            </td>
        </tr>
    </table>
    <div>
        <a id="save" class="nui-button" iconCls="icon-save" onclick="save()"
           style="margin-left: 45%;margin-top:10%">保存</a>
    </div>
</form>
<script type="text/javascript">
    nui.parse();
    var form = new nui.Form("form1");
    //性别处理
    var rblsex = nui.get("rblsex");
    rblsex.load([{"id": "男", "text": "男"}, {"id": "女", "text": "女"}]);

    //获取流水号
    var serialNumber = '<%=request.getParameter("serialNumber")%>';
    var action = '<%=request.getParameter("action")%>';
    var id = '<%=request.getParameter("id")%>';
    nui.get("serialNumber").setValue(serialNumber);
    //数据ID
    nui.get("id").setValue(id);

    // 地址
    var address = nui.get("address");
    address.load("<%=basePath%>apiShiYsJyjcCd_getJgdzData.action?serialNumber=" + serialNumber);

    if (action == "lookDetails") {
        nui.get("save").hide();
    }

    function SetData(data) {
        row = data.row;
        if (row != null) {
            nui.get("name").setValue(row.NAME);
            nui.get("rblsex").setValue(row.SEX);
            nui.get("peopleType").setValue(row.PEOPLETYPE);
            nui.get("address").setValue(row.ADDRESS);
            nui.get("idCard").setValue(row.IDCARD);
            nui.get("birthday").setValue(row.BIRTHDAY);
            nui.get("position").setValue(row.POSITION);
            nui.get("jobTitle").setValue(row.JOBTITLE);
            nui.get("education").setValue(row.EDUCATION);
            nui.get("department").setValue(row.DEPARTMENT);
            nui.get("phone").setValue(row.PHONE);
            nui.get("fax").setValue(row.FAX);

            nui.get("email").setValue(row.EMAIL);
            nui.get("signatureField").setValue(row.SIGNATUREFIELD);
            nui.get("school").setValue(row.SCHOOL);
            nui.get("professional").setValue(row.PROFESSIONAL);
            nui.get("jobCredentials").setValue(row.JOBCREDENTIALS);
            nui.get("train").setValue(row.TRAIN);
            nui.get("jobUndergo").setValue(row.JOBUNDERGO);
            nui.get("relevantExplanation").setValue(row.RELEVANTEXPLANATION);

        }
    }

    function save() {
        //获取文本信息
        var formData = form.getData();
        form.validate();
        if (form.isValid() == false) {
            return;
        }
        var reqJson = nui.encode(formData);
        var urls = "<%=basePath%>apiShiYsJyjcQzr_saveQzr.action";
        $.ajax({
            url: urls,
            type: 'post',
            cache: 'false',
            async: false,
            data: {data: reqJson},
            success: function (resultData) {
                resultData = nui.decode(resultData);
                if (resultData.code == 0) {
                    var id = resultData.data;
                    window.open("<%=basePath%>jsp/jyjc/rzrk/zzrd/sqs/apiQzrDetailPrint.jsp?id=" + id);
                    CloseWindow();
                }
            }
        });
    }


    //关闭子窗口，刷新父窗体
    function CloseWindow(action) {
        if (window.CloseOwnerWindow) {
            return window.CloseOwnerWindow(action);
        } else {
            window.close();
        }
    }

    function onCancel(e) {
        CloseWindow("cancel");
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
</script>
</body>
</html>