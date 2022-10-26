<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.yongjie.ZhiJianSbpt.utilities.*" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    DesUtils des = new DesUtils();//自定义密钥
    String bglxs = request.getParameter("bglx");
    String bglx = des.encrypt(bglxs);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>检验检测机构法人变更管理</title>
    <script type="text/javascript" src="<%=basePath%>nui/nui.js"></script>
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
                    <a id="mylook" class="nui-button" iconCls="icon-search" onclick="look()">查看</a>
                    <a id="add" class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
                    <a id="edit" class="nui-button" iconCls="icon-edit" onclick="edit()">编辑</a>
                    <a id="remove" class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
                    <a id="myfujian" class="nui-button" iconCls="icon-upload" onclick="addfile()">附件</a>
                    <a id="myshangbao" class="nui-button" iconCls="icon-ok" onclick="shangBao()">上报</a>
                    <a id="fkxx" class="nui-button " iconCls="icon-edit" onclick="fkxx()">退回信息</a>
                </td>
                <td style="white-space:nowrap;">
                    状态：
                    <input id="key" class="nui-combobox" textField="text" valueField="id" allowInput="true"
                           data="[{'id':'0','text':'草稿'},{'id':'1','text':'审核中'},{'id':'5','text':'已退回'},{'id':'9','text':'已办结'}]"/>
                    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a></td>
            </tr>
        </table>
    </div>
</div>
<div id="datagrid1" class="nui-datagrid" style="width:100%;height:93%;" allowAlternating="true" allowResize="true"
     url="<%=basePath%>bizJyjcbggl_getJyjcbgglSqsToList.action?bglx=<%=bglxs %>" onselectionchanged="selectionChanged"
     idField="id" multiSelect="true"
     sizeList="[10,20,30,50]" pageSize="10">
    <div property="columns">
        <div type="checkcolumn" headerAlign="center"></div>
        <div field="jgmc" align="center" headerAlign="center" allowSort="true">企业名称</div>
        <div field="bglx" align="center" headerAlign="center" allowSort="true">变更类型</div>
        <div field="lxr" align="center" headerAlign="center" allowSort="true">联系人</div>
        <div field="lxdh" align="center" headerAlign="center" allowSort="true">联系电话</div>
        <div field="sqrq" align="center" headerAlign="center" dateFormat="yyyy-MM-dd" allowSort="true">申请日期</div>
        <div field="flag" align="center" renderer="onGenderFlag" headerAlign="center" allowSort="true">状态</div>
    </div>
</div>
<script type="text/javascript">
    nui.parse();
    var flowId =<%=request.getParameter("flowId")%>;
    var sqsType;

    //查找人员变更的职务。
    function searchZhiwu(e) {
        nui.ajax({
            url: '<%=basePath%>jyjcBgglJgrybgAdd_getrybgAddList.action?sqsId=' + e,
            type: 'post',
            async: false,
            success: function (text) {
                ;
                sqsType = text.trim();
            }
        });
    }

    var zylb = "";
    var grid = nui.get("datagrid1");
    grid.sortBy("id", "desc");
    //getHidden();
    //添加按钮的隐藏操作
    function getHidden() {
        nui.ajax({
            url: "<%=basePath%>bizJyjcbggl_getJyjcbgglByCzr.action",
            type: "post",
            async: false,
            <%-- data:{bglx:"<%=bglxs%>"}, --%>
            success: function (text) {
                if (text.trim() > 0) {
                    nui.get("add").disable();
                } else {
                    nui.get("add").enable();
                }
            }
        });
    }

    //查看申请书
    function look() {
        var rows = grid.getSelecteds();//拿到选中的行
        if (rows.length == 0) {
            nui.alert("请选中一条记录！");
            return;
        } else if (rows.length > 1) {
            nui.alert("不可选中多条记录");
            return;
        } else {
            var row = grid.getSelected();//拿到选中的行
            searchZhiwu(row.id);
            //控制按钮 只有上报后才可以点击,否则 提醒 先上报
            nui.ajax({
                url: "<%=basePath%>bizJyjcbggl_getBizJyjcbgglById.action",
                async: false,
                type: 'POST',
                data: {sqsId: row.id},
                success: function (text) {
                    var ext = "";
                    if (row.filePathPdf != null && row.filePathPdf != "") {
                        ext = ".pdf";
                    }
                    nui.open({
                        url: "<%=basePath%>bizJyjcbggl_lookBgSqs.action?sqsId=" + row.id + "&flowId=" + flowId + "&bglx=" + '<%=bglx%>' + "&sqsType=" + sqsType + "&doc=rybg&ext=" + ext,
                        title: "查看申请信息", width: 1050, height: 700,
                        ondestroy: function (action) {//关闭窗口时执行刷新
                            grid.reload();
                        }
                    });
                }
            });
        }
    }

    //跳转到新增 封面信息
    function add() {
        nui.open({
            url: "<%=basePath%>bizJyjcbggl_getQycnWindow.action",
            title: "企业承诺", width: 650, height: 400,
            ondestroy: function (action) {//关闭窗口时执行刷新
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                        if (data == "true") {
                            nui.open({
                                url: "<%=basePath%>bizJyjcbggl_getJyjcRybgglWindow.action?flowId=" + flowId + "&bglx=" + '<%=bglx%>' + "&sqsType=" + sqsType,
                                title: "新增", width: 1050, height: 700,
                                onload: function () {//加载窗口时执行
                                    //拿到新窗口对象
                                    var iframe = this.getIFrameEl();
                                    //新窗口传递json数据
                                    var data = {action: "new"};
                                    //调用新窗口的SetData方法。
                                    iframe.contentWindow.SetData(data);
                                },
                                ondestroy: function (action) {//关闭窗口时执行刷新
                                    grid.reload();
                                }
                            });
                        }
                    }
                }
            }
        });
    }

    function edit() {
        var rows = grid.getSelecteds();
        if (rows.length > 1) {
            nui.alert("不可选中多条记录")
        } else {
            var row = grid.getSelected();//拿到选中的行
            if (row) {
                searchZhiwu(row.id);
                nui.open({//get传参 跳转到步骤控制页面
                    url: "<%=basePath %>bizJyjcbggl_getJyjcRybgglWindow.action?sqsId=" + row.id + "&flowId=" + flowId + "&bglx=" + '<%=bglx%>' + "&sqsType=" + sqsType + "&zzjgdm=" + encodeURI(encodeURI(row.zzjgdm)),
                    title: "编辑", width: 1300, height: 600,
                    onload: function () {

                    },
                    ondestroy: function (action) {
                        grid.reload();
                        //getHidden();
                    }
                });
            } else {
                nui.alert("请选中一条记录");
            }
        }
    }

    //行改变事件(删除按钮不可用的几种情况)
    function selectionChanged() {
        var row = grid.getSelected();
        if (row) {
            if (row.flag == 0) {
                nui.get("edit").enable();
                nui.get("remove").enable();
                nui.get("myshangbao").enable();
                nui.get("mylook").disable();
                nui.get("myfujian").enable();
                nui.get("fkxx").enable();
            }
            if (row.flag == 5) {
                nui.get("mylook").enable();
                nui.get("edit").enable();
                nui.get("remove").enable();
                nui.get("myshangbao").enable();
                nui.get("myfujian").enable();
                nui.get("fkxx").enable();
            }
            if (row.flag == 9) {
                nui.get("mylook").enable();
                nui.get("edit").disable();
                nui.get("remove").disable();
                nui.get("myshangbao").disable();
                nui.get("myfujian").disable();
                nui.get("fkxx").enable();
            }
            if (row.flag == 1) {
                nui.get("mylook").enable();
                nui.get("edit").enable();
                nui.get("remove").disable();
                nui.get("myshangbao").enable();
                nui.get("myfujian").enable();
                nui.get("fkxx").disable();
            }
        }
    }

    //flag行绑定
    function onGenderFlag(e) {
        var a = e.value;
        var record = e.record;
        var flag = "";
        //flag行绑定
        if (a == 0) {
            flag = "草稿";
        }
        if (a == 1) {
            flag = "审核中";
        }
        if (a == 5) {
            flag = "已退回";
        }
        if (a == 9) {
            flag = "已办结";
        }
        return flag;
    }

    //删除申请书信息
    function remove() {
        ;
        var rows = grid.getSelecteds();

        if (rows.length > 0) {
            var zzjgdm = rows[0].zzjgdm;
            nui.confirm("确定删除选中记录？", "系统提示",
                function (action) {
                    if (action == "ok") {
                        var ids = [];
                        for (var i = 0, l = rows.length; i < l; i++) {
                            var r = rows[i];
                            ids.push(r.id);
                            var id = ids.join(',');
                        }
                        grid.loading("操作中，请稍后......");
                        $.ajax({
                            url: "<%=basePath%>bizJyjcbggl_delJyjcRybgglById.action?idResult=" + id + "&zzjgdm=" + zzjgdm + "&jgState=-99",
                            success: function (text) {
                                nui.alert("删除成功！");
                                grid.reload();
                                //getHidden();
                            },
                            error: function () {
                                nui.alert("删除失败，请稍后再试！");
                            }
                        });
                    }
                });
        } else {
            nui.alert("请选中一条记录");
        }
    }

    //上报
    function shangBao() {
        var rows = grid.getSelecteds();
        if (rows.length == 0) {
            nui.alert("请选中一条记录！");
            return;
        } else if (rows.length > 1) {
            nui.alert("不可选中多条记录");
            return;
        } else {
            mini.confirm("申请材料经受理后不能做任何修改，如需修改只能在补正材料里提交相关补正材料！请确认提交前内容正确！", "温馨提示", function (action) {
                if (action == "ok") {
                    for (var i = 0; i < rows.length; i++) {
                        //提交时只修改当前数据的Flag
                        var row = rows[i];
                        searchZhiwu(row.id);
                        $.ajax({
                            url: "<%=basePath%>bizJyjcbggl_submitSqs.action",
                            data: {sqsId: row.id, flowId: flowId, sqsType: sqsType},
                            type: 'post',
                            cache: false,
                            async: false,
                            success: function (text) {
                                ;
                                text = text.trim();
                                if (text.trim() == "error1") {
                                    nui.alert("请保存申请书信息！", "提醒");
                                } else {
                                    //弹出 分两种情况 1.未上传的附件类型名 2."上报部门:"+areaName+"质量技术监督局大厅" 就看后台走那个逻辑了
                                    alert(text.trim());
                                    if (text.trim().indexOf("已经上报成功") != -1) {
                                        ;
                                        $.ajax({
                                            url: "<%=basePath%>fkxxSbpt_saveSomething.action",
                                            type: 'post',
                                            data: {sqsId: row.id, flowId: row.flowId, flowInstId: row.flowInstId},
                                            cache: false,
                                            async: false,
                                            success: function (text) {
                                            }
                                        });
                                    }
                                }
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
    }

    function onKeyEnter(e) {
        search();
    }

    //查询事件
    function search() {
        var key = nui.get("key").getValue();
        grid.load({key: key});
    }

    //反馈信息
    function fkxx() {
        var rows = grid.getSelecteds();
        if (rows.length == 0) {
            nui.alert("请选中一条记录！");
            return;
        } else if (rows.length > 1) {
            nui.alert("不可选择多条记录！");
            return;
        } else {
            nui.open({
                url: "<%=basePath %> fkxxSbpt_fkxxManager.action",
                title: "退回信息", width: 800, height: 450,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = {action: "", sqsIds: rows[0].id, flowId: rows[0].flowId, flowInstId: rows[0].flowInstId};//action:区别挂起和退回
                    iframe.contentWindow.SetData(data);
                }
            });
        }
    }

    //附件
    function addfile() {
        var rows = grid.getSelecteds();
        if (rows.length > 1) {
            nui.alert("不可选中多条记录")
        } else {
            var row = grid.getSelected();//拿到选中的行
            searchZhiwu(row.id);
            if (row) {
                var row = grid.getSelected();//拿到选中的行
                nui.open({
                    url: "<%=basePath%>bizJyjcbggl_toFuJianWindow.action?sqsId=" + row.id + "&flowId=" + flowId + "&bglx=" + '<%=bglx%>' + "&sqsType=" + sqsType,
                    title: "附件管理",
                    width: 800,
                    height: 650,
                    ondestroy: function (action) {
                        grid.reload();//刷新
                    }
                });
            } else {
                nui.alert("请选中一条记录");
            }
        }
    }

</script>
</body>
</html>