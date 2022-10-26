<%@ page language="Java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.yongjie.ZhiJianSbpt.utilities.*" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String serialNumber = request.getParameter("serialNumber") == null ? "" : request.getParameter("serialNumber");
    String action = request.getParameter("action") == null ? "" : request.getParameter("action");

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>标题</title>
    <script type="text/javascript" src="<%=basePath %>nui/nui.js"></script>
    <script type="text/javascript" src="<%=basePath %>nui/fileupload/swfupload/swfupload.js"></script>
    <script type="text/javascript" src="<%=basePath %>nui/FuJianCaiLiao/Js/jquery-1.11.3.min.js"></script>
    <link href="<%=basePath %>nui/FuJianCaiLiao/Css/FuJianUpload.css" rel="stylesheet" type="text/css"/>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<!--
- Author(s): 王景仟
- CreateTime: 2016年10月21日 上午12:20:17
- Copyright: Copyright (c) 2016
- Company: 北京永杰友信科技有限公司
- Version：1.0
- Description:
-->
<body>

<form width="100%" method="post" enctype="multipart/form-data">
    <div align="center" class="fileBox">
        <table>
            <tr>
                <td>
                    <p style="margin-top:0;" class="fileInputP vm">
                        <i>选择文件</i>
                        <input id="fileInput" type="file" multiple="multiple" value="选择文件" class="XuanZe"
                               accept=".xls,.xlsx"/>
                    </p>
                </td>

                <td style="margin-top:0;float:left;margin-left:30px;">
                    <input id="fileBtn" type="button" value="上传" style="width:55px;"/>
                </td>
                <td></td>
                <td style="margin-top:0;float:left;">
                    <input type="button" value="取消" class="quxiao" onClick="QuXiao()" style="width:55px;"/>
                </td>
            </tr>
        </table>
    </div>
    <table bordercolor="#4169E1" cellpadding='0' cellspacing='0' style="margin-left:12px;" width="97%" border="1"
           class="fileList_parent">
        <thead>
        <tr style="width:100%;">
            <th width="30%">文件名</th>
            <th width="15%">类型</th>
            <th width="15%">大小</th>
            <th width="25%">进度</th>
            <th width="15%">操作</th>
        </tr>
        </thead>
        <tbody class="fileList">
        </tbody>
    </table>
</form>

<br/>
<div style="color:red;" align="center">
    <a><b>提示：上传文件不能超过50M！</b></a><br/>
</div>
<br/>
<script type="text/javascript">
    nui.parse();
    //上传模板类型 / 授权签字人:jyjcQzr / 人员:jyjcRy
    var action = '<%=action%>';

    var serialNumber = '<%=serialNumber%>';
    //选择文件父级盒子
    var oFileBox = $(".fileBox");
    //选择文件按钮
    var oFileInput = $("#fileInput");
    //表格
    var oFileList_parent = $(".fileList_parent");
    //表格tbody
    var oFileList = $(".fileList");
    //上传按钮
    var oFileBtn = $("#fileBtn");
    //数据，为一个复合数组
    var flieList = [];
    //存放每个文件大小的数组，用来比较去重
    var sizeObj = [];

    //点击选择文件按钮选文件
    oFileInput.on("change", function () {
        AnalysisList(this.files);
    });

    //解析列表函数
    function AnalysisList(obj) {
        //限制上传的文件数量
        if (obj.length > 1) {
            nui.alert("对不起，每次最多只能上传1个文件！", "系统提示");
            return false;
        }
        for (var i = 0; i < obj.length; i++) {
            //单个文件
            var fileObj = obj[i];
            //文件名
            var name = fileObj.name;
            //文件大小
            var size = fileObj.size;
            //文件类型，获取的是文件的后缀
            var type = fileType(name);
            //文件大于50M，就不上传
            if (size > 50 * 1024 * 1024 || size == 50) {
                nui.alert('“' + name + '”超过了50M，不能上传！', "系统提示");
                continue;
            }
            //禁止上传的文件类型
            var JinZhi = "mp3/mp4/avi/exe/wav/dll/java/css/jsp/html/xml/js/scc/run/tld/class/jar/swf/msi";
            if (JinZhi.indexOf(type) > 0) {
                nui.alert('“' + name + '”文件类型不支持上传！', "系统提示");
                continue;
            }
            //把文件大小放到一个数组中，然后再去比较，如果有比较上的，就认为重复了，不能上传
            if (sizeObj.indexOf(name) != -1) {
                nui.alert('“' + name + '”文件已经选择，不能重复上传！');
                continue;
            }
            //给json对象添加内容，得到选择的文件的数据
            var itemArr = [fileObj, name, size, type];//文件，文件名，文件大小，文件类型
            flieList.push(itemArr);
            //把这个文件的大小放进数组中
            sizeObj.push(size);
        }

        //生成列表
        createList();
        //表格显示
        oFileList_parent.show();
    }

    //生成列表
    function createList() {
        //先清空元素内容
        oFileList.empty();
        for (var i = 0; i < flieList.length; i++) {
            //flieList数组中的某一个
            var fileData = flieList[i];
            //文件名
            var name = fileData[1];
            //文件大小
            var size = fileData[2];
            //文件类型
            var type = fileData[3];
            //文件大小格式化
            var volume = bytesToSize(size);
            //文件大小赋值
            FileSize = volume;
            //创建标签
            var oTr = $("<tr></tr>");
            var str = '<td><cite title="' + name + '">' + name + '</cite></td>';
            str += '<td>' + type + '</td>';
            str += '<td>' + volume + '</td>';
            str += '<td>';
            str += '<div class="progressParent">';
            str += '<p class="progress"></p>';
            str += '<span class="progressNum">0%</span>';
            str += '</div>';
            str += '</td>';
            str += '<td><a href="javascript:;" class="operation">删除</a></td>';

            oTr.html(str);
            oTr.appendTo(oFileList);
        }
    }

    //删除表格行
    oFileList.on("click", "a.operation", function () {
        alert("删除列表！！");
        var oTr = $(this).parents("tr");
        var index = oTr.index();
        //删除这一行
        oTr.remove();
        //删除数据
        flieList.splice(index, 1);
        //删除文件大小数组中的项
        sizeObj.splice(index, 1);
    });

    //上传
    oFileBtn.on("click", function () {
        //获取所有tr列表
        var tr = oFileList.find("tr");
        if (tr.length < 1) {
            nui.alert("请选择一条文件后，再上传！", "系统提示");
            return;
        }
        if (tr.length > 1) {
            nui.alert("对不起，每次最多只能上传1个文件！", "系统提示");
            return;
        }

        //已上传成功的数目
        var successNum = 0;
        //关闭上传按钮功能
        oFileBtn.off();
        //取消删除事件
        oFileList.off();
        //隐藏输入框
        oFileBox.slideUp();
        oFileList.find("a.operation").text("等待上传");

        for (var i = 0; i < tr.length; i++) {
            //参数为当前项，下标
            uploadFn(tr.eq(i), i);
        }

        function uploadFn(obj, i) {
            var formData = new FormData();
            //获取数据数组的当前项
            var arrNow = flieList[i];
            //从当前项中获取上传文件，放到 formData对象里面
            var result = arrNow[0];//数据
            //上传的文件对象
            formData.append("File", result);
            //传值
            formData.append("FileSize", FileSize);
            //上传进度背景元素
            var progress = obj.find(".progress");
            //上传进度元素文字
            var progressNum = obj.find(".progressNum");
            //按钮
            var oOperation = obj.find("a.operation");
            //改变操作按钮
            oOperation.text("正在上传");
            oOperation.off();

            var uploadUrl = "";

            if (action == "jyjcRy") {
                uploadUrl = "<%=basePath%>jsp/jyjc/rzrk/zzrd/fuJian/JyjcRyUpload.jsp?serialNumber=" + serialNumber;
            } else if (action == "jyjcQzr") {
                uploadUrl = "<%=basePath%>jsp/jyjc/rzrk/zzrd/fuJian/JyjcQzrUpload.jsp?serialNumber=" + serialNumber;
            }

            var request = $.ajax({
                type: "POST",
                url: uploadUrl,
                data: formData,	//这里上传的数据使用了formData对象
                cache: false,
                processData: false,//必须false才会自动加上正确的Content-Type
                contentType: false,
                //这里我们先拿到jQuery产生的XMLHttpRequest对象，为其增加 progress 事件绑定，然后再返回交给ajax使用
                xhr: function () {
                    var xhr = $.ajaxSettings.xhr();
                    if (onprogress && xhr.upload) {
                        //进度条
                        xhr.upload.addEventListener("progress", onprogress, false);
                        return xhr;
                    }
                },

                //上传成功后回调
                success: function (text) {
                    ;

                    var ooo = nui.decode(text.trim());
                    if (ooo.code == 0) {
                        oOperation.text("成功");
                        successNum++
                        if (successNum == tr.length) {
                            nui.alert("上传成功！", "系统提示", function () {
                                CloseWindow("ok");
                            });
                        }
                    } else {
                        oOperation.text("重传");
                        oOperation.on("click", function () {
                            //终止本次
                            request.abort();
                            uploadFn(obj, i);
                        });
                        nui.alert(ooo.message);
                    }

                },

                //上传失败后回调
                error: function () {
                    oOperation.text("重传");
                    oOperation.on("click", function () {
                        //终止本次
                        request.abort();
                        uploadFn(obj, i);
                    });
                }
            });


            //侦查附件上传情况 ,这个方法大概0.05-0.1秒执行一次
            function onprogress(evt) {
                //已经上传大小情况
                var loaded = evt.loaded;
                //附件总大小
                var tot = evt.total;
                //已经上传的百分比
                var per = Math.floor(100 * loaded / tot);
                progressNum.html(per + "%");
                progress.css("width", per + "%");
            }
        }
    });

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

    //字节大小转换，参数为b
    function bytesToSize(bytes) {
        var sizes = ['B', 'KB', 'MB'];
        if (bytes == 0) return 'n/a';
        var i = parseInt(Math.floor(Math.log(bytes) / Math.log(1024)));
        return (bytes / Math.pow(1024, i)).toFixed(1) + ' ' + sizes[i];
    };

    //通过文件名，返回文件的后缀名
    function fileType(name) {
        var nameArr = name.split(".");
        return nameArr[nameArr.length - 1].toLowerCase();
    }

    //点击取消按钮
    function QuXiao(e) {
        CloseWindow("cancel");
    }
</script>
</body>
</html>