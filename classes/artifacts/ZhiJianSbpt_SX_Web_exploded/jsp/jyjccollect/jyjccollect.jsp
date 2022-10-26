<%@ page import="com.yongjie.ZhiJianSbpt.utilities.SysFinalRecource" %><%--
  Created by IntelliJ IDEA.
  User: 韩冰
  Date: 2022/6/28
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";

    String type = SysFinalRecource.JYJC_FJ_BIANGENGTYPE;


%>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=basePath%>nui/jquery/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>nui/nui.js"></script>
</head>
<body>
<div style="width:100%;">
    <div class="nui-toolbar" style="border-bottom:0;padding:0px;">
        <table style="width:100%;">
            <tr>
                <td style="width: 100%;">
                    <a id="addcj" class="nui-button" iconCls="icon-add" onclick="addcj()">新增机构信息</a>
                    <a id="editcj" class="nui-button" iconCls="icon-edit" onclick="editcj()">编辑机构信息</a>
                    <a id="delcj" class="nui-hidden" iconCls="icon-remove" onclick="delcj()">删除机构信息</a>
                    <a id="sb" class="nui-hidden" iconCls="icon-upload" onclick="sb()">上报</a>
                </td>
            </tr>
        </table>
    </div>
</div>
<div id="listGrid" class="nui-datagrid" style="width:100%;height:95%;" allowResize="false" idField="ID"
     multiSelect="false" pageSize="50" allowAlternating="true" ondrawcell="onDrawCell">
    <div property="columns">
        <div field="check" type="checkcolumn" headerAlign="center"></div>
        <div type="indexcolumn" headerAlign="center">序号</div>
        <div field="JGOBJECTNAME" align="center" headerAlign="center" allowSort="true">采集名称</div>
        <div field="SHXYDM" align="center" headerAlign="center" allowSort="true">社会信用代码</div>
        <div field="COLLECTDATE" align="center" headerAlign="center" dateFormat="yyyy-MM-dd" allowSort="true">采集日期</div>
    </div>
</div>
<script type="text/javascript">
    nui.parse();
    var type = '<%=type%>';
    var grid = nui.get('listGrid');
    var zzrdzsbh;

    //初始化页面
    load();

    function load() {
        $.ajax({
            url: "<%=basePath%>collect_queryByUserName.action",
            type: 'post',
            cache: false,
            success: function (result) {//成功获得数据时
                var map = nui.decode(result);

                if (map.total == 0) {
                    return
                }
                zzrdzsbh = map.data.ZZRDZSBH;
                grid.load("<%=basePath%>collect_collectList.action?zzrdzsbh=" + zzrdzsbh);
                grid.reload();
            }
        });

    }

    //有数据隐藏新增按钮
    function onDrawCell(e) {
        nui.get("addcj").enable();
        if (e != null || e != "") {
            nui.get("addcj").disable();
        }
    }

    function upload() {
        var row = grid.getSelecteds();

        if (row.length < 1) {
            nui.alert("请选择一条数据再上传！", "系统提示");
            return;
        } else if (row.length > 1) {
            nui.alert("只能选择一条数据！", "系统提示");
            return;
        }

        var status = row[0].TYPE;
        var id = row[0].ID;
        if (status == undefined | status == "") {
            nui.alert("请选择上传的数据类型！", "系统提示");
        }
        nui.open({
            url: "<%=basePath%>jsp/jyjccollect/fileupload.jsp?status=" + 4,
            title: "添加附件",
            width: 800,
            height: 600,
            ondestroy: function (action) {
                grid.reload();
            }
        });
    }


    function down() {
        location = "<%=basePath%>jsp/common/downjyjccjMuban.jsp?status=" + 4;
    }

    function addcj() {
        if (grid.data.length >= 1) {
            nui.alert('只能添加一条机构信息!', '系统提示');
            nui.get('addcj').disable();
            return
        }
        nui.open({
            url: "<%=basePath%>bizJyjcbggl_getQycnWindowCj.action",
            title: "添加封面信息", width: 600, height: 400,
            ondestroy: function (action) {
                if (action == "ok") {
                    // 获取 流水号 打开 新增页面
                    nui.open({
                        url: "<%=basePath%>jsp/jyjccollect/cjStepInfo.jsp",
                        title: "增加申请材料", width: 1200, height: 800,
                        onload: function () {
                            var iframe = this.getIFrameEl();
                            var data = {action: "add"};
                            iframe.contentWindow.SetData(data);
                        },
                        ondestroy: function (action) {
                            load();
                            //grid.reload();
                        }
                    });

                }
            }
        });


    }

    function editcj() {
        var row = grid.getSelected();
        if (undefined == row) {
            nui.alert('请选择一条数据', '系统提示');
            return;
        }
        nui.open({
            url: "<%=basePath%>jsp/jyjccollect/cjStepInfo.jsp",
            title: "增加申请材料", width: 1200, height: 800,
            onload: function () {
                var iframe = this.getIFrameEl();
                var data = {action: "edit", row: row};
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {
                grid.reload();
            }
        });
    }


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
                url: "<%=basePath%>jsp/jyjc/rzrk/zzrd/fuJian/apiFuJian.jsp?flowId=" + 100 + "&sqsType=" + type,
                title: "附件管理",
                width: '70%',
                height: 800,
                ondestroy: function (action) {
                    grid.reload();//刷新
                }
            });
        }
    }

    function delcj() {
        var rows = grid.getSelecteds();
        if (rows.length < 1) {
            nui.alert("请选择一条数据再上传！", "系统提示");
            return;
        } else if (rows.length > 1) {
            nui.alert("只能选择一条数据！", "系统提示");
            return;
        }
        var row = rows[0];
        nui.confirm('确认删除？', '系统提醒', function (action) {
            if (action == 'ok') {
                $.ajax({
                    url: "<%=basePath%>collect_del.action",
                    type: 'post',
                    data: {id: row.ID},
                    cache: false,
                    success: function (text) {
                        //成功获得数据时
                        if (text.trim() != 0) {
                            nui.alert("删除成功！", "提醒", function () {
                                nui.get('addcj').enable();
                                grid.reload();//刷新
                            });
                        }
                    }
                });
            }
        })
    }

    function selectionChanged() {
        console.log(grid);
        var row = grid.getSelected();
        var flag = row.FLAG;
        nui.get('editcj').enable();
        nui.get('delcj').enable();
        nui.get('sb').enable();
        if (flag == 1 | flag == 2) {
            nui.get('editcj').disable();
            nui.get('delcj').disable();
            nui.get('sb').disable();
        }

    }


</script>
</body>
</html>
