<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%
 String path = request.getContextPath();
 String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 %>
 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
 <html>
   <head>
     <base href="<%=basePath%>">    
     <title>图片预览</title>     
     <script type="text/javascript" src="<%=basePath %>nui/nui.js"></script>          
     <script src="<%=basePath %>style/scripts/viewer.min.js" type="text/javascript"></script> 
     <link rel="stylesheet" type="text/css" href="<%=basePath %>style/css/viewer.min.css"/>   
  </head>
  <body  style="padding-top:70px">
  <div id="TuPianXX">
		<img id="TuPian"   src="<%=basePath%>jsp/common/ImageRead.jsp?filePath=<%=request.getParameter("filePath") %>" >
	</div>
	 
   <!--显示图片-->
    <div id='block1' style='position: absolute; left: 40px; top: 5px;' class="dragAble">
       <img id="images1"  align="middle" /> 
    </div> 
    	<script type="text/javascript">
    	//获取值
		var url = "<%=request.getParameter("Url")%>";
    	var FileTitle = "<%=request.getParameter("FileTitle")%>";
		//加载图片
		$(function(){
			//隐藏图片地板
			$("#TuPianXX").hide();
			viewer = new Viewer(document.getElementById('TuPianXX'),{
				keyboard: true,
				button: false,
				navbar: false,
			});
			//直接全屏
			viewer.show();
		});
    </script>
  </body>
 </html>