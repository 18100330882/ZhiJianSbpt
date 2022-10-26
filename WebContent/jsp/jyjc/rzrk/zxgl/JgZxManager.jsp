<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.yongjie.ZhiJianSbpt.utilities.*" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String flowId = request.getParameter("flowId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>检验检测机构注销</title>
    <script type="text/javascript" src="<%=basePath%>nui/nui.js"></script>
    <script type="text/javascript" src="<%=basePath %>style/ca/secCtrl.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>

<body>
<div style="width:100%;">
    <div class="nui-toolbar" style="border-bottom:0;padding:0px;">
        <table style="width:100%;">
            <tr>
                <td style="width: 100%;">
                    <a id="detail" class="nui-button" iconCls="icon-search" onclick="detail()">查看申请信息</a>
                    <a id="add" class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
                    <a id="edit" class="nui-button" iconCls="icon-edit" onclick="edit()">编辑</a>
                    <a id="remove" class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
                    <a id="myfujian" class="nui-button" iconCls="icon-upload" onclick="addfile()">附件上传下载</a>
                    <a id="myprint" class="nui-button" iconCls="icon-print" onclick="saveSqs()">保存申请书</a>
                    <a id="shangBao" class="nui-button" iconCls="icon-ok" onclick="shangBao()">上报</a>
                    <a id="fkxx" class="nui-button " iconCls="icon-edit" onclick="fkxx()">反馈信息</a>
                    <a id="sqzz" class="nui-hidden " iconCls="icon-tip" onclick="sqzz()">申请终止</a>
                </td>
                <td style="white-space:nowrap;">
                    <input id="keyJgmc" class="nui-textbox" emptyText="请输入单位名称" style="width:150px;"
                           onenter="onKeyEnter"/>
                    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
                </td>
            </tr>
        </table>
    </div>
</div>

<div id="listGrid" class="nui-datagrid" style="width:100%;height:95%;" allowResize="false" idField="ID"
     multiSelect="true" sizeList="[50,100,500]"
     showPager="true" pageSize="50" allowAlternating="true" onselectionchanged="selectionChanged">
    <div property="columns">
        <div field="check" type="checkcolumn" headerAlign="center"></div>
        <div type="indexcolumn" headerAlign="center">序号</div>
        <div field="ORGANIZATIONNAME" align="center" headerAlign="center" allowSort="true">机构名称</div>
        <div field="ORGANIZATIONADDRESS" align="center" headerAlign="center" allowSort="true">机构地址</div>
        <div field="CERTIFICATENUMBER" align="center" headerAlign="center" allowSort="true">证书编号</div>
        <div field="CONTACTS" align="center" headerAlign="center" allowSort="true">联系人</div>
        <div field="CONTACTSPHONE" align="center" headerAlign="center" allowSort="true">联系人电话</div>
        <div field="SQRQ" align="center" headerAlign="center" dateFormat="yyyy-MM-dd" allowSort="true">申请日期</div>
        <div field="FLAG" align="center" renderer="onGenderFlag" headerAlign="center" allowSort="true">状态</div>
    </div>
</div>

<script type="text/javascript">
    nui.parse();
    var flowId =<%=request.getParameter("flowId")%>;

    var grid = nui.get("listGrid");
    grid.load("<%=basePath%>bizApiZxsqb_getList.action?flowId=" + flowId);
    grid.reload();

    var serialNumber;

    //跳转到新增 封面信息
    function add() {
        nui.open({
            url: "<%=basePath%>bizJyjcbggl_getQycnWindow.action",
            title: "添加封面信息", width: 1200, height: 800,
            ondestroy: function (action) {
                if (action == "ok") {
                    // 获取 流水号 打开 新增页面
                    $.ajax({
                        url: "<%=basePath%>common_getSerialNumber.action",
                        type: 'post',
                        cache: 'false',
                        async: false,
                        success: function (resultData) {
                            resultData = nui.decode(resultData);
                            var serialNumber = resultData.data;
                            if (serialNumber == null || serialNumber == "") {
                                nui.alert("请刷新页面重试！", "提醒！");
                                return;
                            }
                            nui.open({
                                url: "<%=basePath%>jsp/jyjc/rzrk/zxgl/sqs/apiBizZx.jsp",
                                title: "增加申请材料", width: 1000, height: 600,
                                onload: function () {
                                    var iframe = this.getIFrameEl();
                                    var data = {action: "add", serialNumber: serialNumber, flowId: flowId};
                                    iframe.contentWindow.SetData(data);
                                },
                                ondestroy: function (action) {
                                    grid.reload();
                                }
                            });
                        }
                    });
                }
            }
        });
    }

    function selectionChanged() {
        var rows = grid.getSelecteds();
        //获取flag值
        var flags = [];
        for (var i = 0; i < rows.length; i++) {
            flags.push(rows[i].FLAG);
        }
        flags = flags.join(",");
        nui.get("edit").enable();
        nui.get("remove").enable();
        nui.get("myfujian").enable();
        nui.get("myprint").enable();
        nui.get("shangBao").enable();
        nui.get("sqzz").disable();
        if (rows == null) {
            return;
        }
        //根据条件隐藏
        if (flags.indexOf("1") != -1 || flags.indexOf("2") != -1 || flags.indexOf("5") != -1 || flags.indexOf("6") != -1 && flags != null) {
            nui.get("edit").disable();
            nui.get("remove").disable();
            nui.get("myfujian").disable();
            nui.get("myprint").disable();
            nui.get("shangBao").disable();
            nui.get("sqzz").enable();
            if (flags.indexOf("6") != -1) {
                nui.get("sqzz").disable();
            }
        }
        if (flags.indexOf("3") != -1 || flags.indexOf("4") != -1) {
            nui.get("remove").disable();
        }
    }

    function edit() {
        var rows = grid.getSelecteds();
        if (rows == null || rows == "") {
            nui.alert("请选择一条记录！", "提醒！");
            return;
        }
        if (rows.length > 1) {
            nui.alert("不能选择多条记录编辑！", "提醒！");
            return;
        }
        var row = grid.getSelected();
        nui.open({
            url: "<%=basePath%>jsp/jyjc/rzrk/zxgl/sqs/apiBizZx.jsp",
            title: "增加申请材料", width: 1000, height: 600,
            onload: function () {
                var iframe = this.getIFrameEl();
                var data = {action: "edit", serialNumber: row.SERIALNUMBER};
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {
                grid.reload();
            }
        });
    }

    function detail() {
        var rows = grid.getSelecteds();
        if (rows == null || rows == "") {
            nui.alert("请选择一条记录！", "提醒！");
            return;
        }
        if (rows.length > 1) {
            nui.alert("不能选择多条记录编辑！", "提醒！");
            return;
        }
        var row = grid.getSelected();
        nui.open({
            url: "<%=basePath%>jsp/jyjc/rzrk/zxgl/sqs/apiBizZx.jsp",
            title: "增加申请材料", width: 1000, height: 600,
            onload: function () {
                var iframe = this.getIFrameEl();
                var data = {action: "edit", serialNumber: row.SERIALNUMBER, detail: "detail"};
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {
                grid.reload();
            }
        });
    }

    //保存申请书
    function saveSqs() {
        var rows = grid.getSelecteds();
        if (rows == null || rows == "" || rows.length == 0) {
            nui.alert("请选择一条进行编辑！", "提醒！");
            return;
        }
        if (rows.length > 1) {
            nui.alert("只能选择一条进行编辑！", "提醒！");
            return;
        }

        var row = grid.getSelected();

        nui.open({
            url: "<%=basePath%>jsp/jyjc/rzrk/zxgl/sqs/JgzxSqsPrint.jsp",
            title: "申请书打印管理",
            width: 1200,
            height: 800,
            onload: function () {
                var iframe = this.getIFrameEl();
                var data = {flag: "403", row: row};
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {
                grid.reload();//刷新
            }
        });

    }

    //flag行绑定
    function onGenderFlag(e) {
        var result = "";
        var flag = e.value;
        if (flag == 0) {
            result = "未上报";
        }
        if (flag == 1) {
            result = "预审核";
        }
        if (flag == 2) {
            result = "受理中";
        }
        if (flag == 3 || flag == 4) {
            result = "已退回";
        }
        if (flag == 5) {
            result = "待激活";
        }
        if (flag == 6) {
            result = "归档";
        }
        return result;
    }

    //附件
    function addfile() {
        var rows = grid.getSelecteds();
        if (rows == null || rows == "" || rows.length == 0) {
            nui.alert("请选择一条进行编辑！", "提醒！");
            return;
        }
        if (rows.length > 1) {
            nui.alert("只能选择一条进行编辑！", "提醒！");
            return;
        }

        var row = grid.getSelected();//拿到选中的行
        if (row) {
            nui.open({
                url: "<%=basePath%>jsp/jyjc/rzrk/zzrd/fuJian/apiFuJian.jsp?serialNumber=" + row.SERIALNUMBER + "&flowId=" + row.FLOWID + "&sqsType=机构注销",
                title: "附件管理",
                width: '70%',
                height: 800,
                ondestroy: function (action) {
                    grid.reload();//刷新
                }
            });
        }
    }

    function onKeyEnter(e) {
        search();
    }

    //查询事件
    function search() {
        var keyJgmc = nui.get("keyJgmc").getValue();
        grid.load({keyJgmc: keyJgmc});
    }


    function remove() {
        var rows = grid.getSelecteds();
        if (!rows.length > 0) {
            nui.alert("请选中一条记录", "提醒");
            return;
        }
        nui.confirm("确定删除选中记录？", "系统提示",
            function (action) {
                if (action == "ok") {
                    var roleIds = [];
                    var serialNumber = rows[0].SERIALNUMBER;
                    for (var i = 0; i < rows.length; i++) {
                        roleIds.push(rows[i].ID);
                    }
                    roleIds.join(",");
                    $.ajax({
                        url: "<%=basePath%>bizApiZxsqb_delete.action?idResult=" + roleIds + "&serialNumber=" + serialNumber,
                        type: "post",
                        success: function (text) {
                            nui.alert("删除成功！", "提醒");
                            grid.reload();
                        },
                        error: function () {
                            nui.alert("删除失败！");
                        }
                    });
                }
            });
    }

    //上报
    function shangBao() {
        var rows = grid.getSelecteds();
        if (rows.length == 0) {
            nui.alert("请选中一条记录！");
            return;
        }
        if (rows.length > 1) {
            nui.alert("不可选中多条记录");
            return;
        }

        nui.confirm("申请材料经受理后不能做任何修改，如需修改只能在补正材料里提交相关补正材料！请确认提交前内容正确！", "温馨提示", function (action) {
            if (action == "ok") {
                for (var i = 0; i < rows.length; i++) {
                    //提交时只修改当前数据的Flag
                    var row = rows[i];
                    $.ajax({
                        url: "<%=basePath%>apiFile_getApiFileList.action",
                        data: {serialNumber: row.SERIALNUMBER},
                        type: 'post',
                        cache: false,
                        async: false,
                        success: function (text) {
                            text = text.trim();
                            var map = nui.decode(text);
                            if (map.code != 0) {
                                nui.alert("请上传必要附件！", "提醒");
                                return;
                            }
                            $.ajax({
                                url: "<%=basePath%>shiYsJyjcFuJian_getSqsFujian.action",
                                type: 'post',
                                data: {serialNumber: row.SERIALNUMBER},
                                cache: false,
                                async: false,
                                success: function (text) {
                                    text = text.trim();
                                    var map = nui.decode(text);
                                    if (map.code != 0) {
                                        nui.alert("请保存申请书！", "提醒");
                                        return;
                                    }
                                    $.ajax({
                                        url: "<%=basePath%>bizApiZxsqb_shangBao.action",
                                        type: 'post',
                                        data: {serialNumber: row.SERIALNUMBER, flag: "1"},
                                        cache: false,
                                        async: false,
                                        success: function (text) {
                                            if (row.FLAG != 0) {
                                                nui.alert("上报成功！", "提醒！");
                                                return;
                                            }
                                            // 预审核通过-受理通过 ( 退回 不再  启动 row.FLAG == 0 )
                                            if (row.FLAG != 0 && row.FLAG != 8) {
                                                nui.alert("上报成功！", "提醒！");
                                                return;
                                            }
                                            // 注销-预审核通过
                                            $.ajax({
                                                url: "<%=basePath%>cancellation_confirmAcceptance.action",
                                                type: 'post',
                                                cache: 'false',
                                                async: false,
                                                //需要进行优化
                                                data: {
                                                    id: row.ID,
                                                    flowId: row.FLOWID,
                                                    shRenAreaId: row.CHECKAREAID,
                                                    qymc: row.ORGANIZATIONNAME,
                                                    serialNumber: row.SERIALNUMBER
                                                },
                                                success: function (result) {
                                                    var map = JSON.parse(result);
                                                    nui.alert(map.msg, "提示!", function () {
                                                        grid.reload();
                                                    });
                                                },
                                            });
                                        }
                                    });
                                }
                            });

                            grid.reload();
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            alert(jqXHR.responseText); //弹出错误
                        }
                    });
                }
            }
        });
    }


    //流转信息
    function fkxx() {
        var rows = grid.getSelecteds();
        if (rows.length == 0) {
            nui.alert("请选中一条记录！");
            return;
        }
        if (rows.length > 1) {
            nui.alert("不可选择多条记录！");
            return;
        }

        nui.open({
            url: "<%=basePath%>jsp/jyjc/rzrk/zzrd/TransferInformation.jsp",
            title: "流转记录", width: 1000, height: 600,
            onload: function () {
                var iframe = this.getIFrameEl();
                var data = {
                    action: "return",
                    sqsIds: rows[0].ID,
                    flowId: rows[0].FLOWID,
                    flowInstId: rows[0].FLOWINSTID,
                    serialNumber: rows[0].SERIALNUMBER,
                    nodeId: rows[0].NODEID,
                    row: rows[0]
                };//action:区别挂起和退回
                iframe.contentWindow.SetData(data);
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

</script>
</body>
</html>