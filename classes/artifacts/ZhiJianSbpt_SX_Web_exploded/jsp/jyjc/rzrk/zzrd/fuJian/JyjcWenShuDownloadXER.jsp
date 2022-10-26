<%@ page import="java.io.File" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.SysFinalRecource" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.FileUtil" %>
<%@ page language="Java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
 <%
 String path = request.getContextPath();
 String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";


//将申请书的二进制流转换成字节

String filePath=request.getParameter("filepath");
String fileTitle=request.getParameter("fileTitle");
//String ext = scy.getExtense();
	
	/****************轮询两台服务器，去查文件****************/
    String fileAllPath = "";
	File filetemp = null;
	byte[] fileContent = null;
	for (int i = 0; i < 2; i++) {
		if (i == 0) {
			fileAllPath = SysFinalRecource.SECOND_LEVEL_DIRECTORY + filePath;
		} else {
			fileAllPath = SysFinalRecource.SECOND_LEVEL_DIRECTORY_W + filePath;
		}
		filetemp = new File(fileAllPath);
		if(filetemp.exists()){
			fileContent = FileUtil.getBytes(filetemp);
			break;
		}
	}
	if(fileContent==null){
		response.getWriter().write("文件未找到！");
		return;
	} 
	/****************轮询两台服务器，去查文件****************/

	 //获得fileName
	 String fileName=fileTitle;
	 int index = filePath.indexOf(".");//获取第一个"_"的位置
	 String hz = filePath.substring(index);
//	 String hz = filePath.substring(".", );
	 response.setHeader("Content-Disposition", "attachment; filename="+new String(fileName.getBytes("utf-8"),"iso-8859-1")+hz);
	 response.getOutputStream().write(fileContent, 0, fileContent.length);
	 response.getOutputStream().flush();
	 response.getOutputStream().close();	
	 out.clear();
	 out = pageContext.pushBody();
 %>
 