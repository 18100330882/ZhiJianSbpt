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
    <title>获取二进制流图片</title>
    <script type="text/javascript" src="<%=basePath %>nui/nui.js"></script>
    <script src="<%=basePath %>style/scripts/viewer.min.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>style/css/viewer.min.css"/>

</head>
<!--
- Author(s): 李杰
- CreateTime: 2016年12月8日 上午11:40:10
- Copyright: Copyright (c) 2016
- Company: 北京永杰友信科技有限公司
- Version：1.0
- Description:
-->
<body style="padding-top:70px">
<div id="TuPianXX">
    <img id="TuPian"
         src="<%=basePath%>jsp/common/ImageRead.jsp?id=<%=request.getParameter("id")%>&filePath=<%=request.getParameter("filePath") %>">
</div>

<!--显示图片-->
<div id='block1' style='position: absolute; left: 40px; top: 5px;' class="dragAble">
    <img id="images1" align="middle"/>
</div>
<script type="text/javascript">

    //获取值
    var url = "<%=request.getParameter("Url")%>";
    var FileTitle = "<%=request.getParameter("FileTitle")%>";
    var filepath = "<%=request.getParameter("filePath") %>";
    var fileTitle = "<%=request.getParameter("fileTitle") %>";
    var flag = "<%=request.getParameter("flag") %>";

    if (flag != "333") {
        Lodown();
    }

    //加载图片
    $(function () {
        //隐藏图片地板
        $("#TuPianXX").hide();

        viewer = new Viewer(document.getElementById('TuPianXX'), {
            keyboard: true,
            button: false,
            navbar: false,
        });
        //直接全屏
        viewer.show();
    });


    function Lodown() {
        location.href = "<%=basePath%>jsp/common/JyjcWenShuDownloadXER.jsp?filepath=" + filepath + "&fileTitle=" + fileTitle;

    }
</script>
</body>
</html>