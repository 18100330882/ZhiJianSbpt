<%@ page import="java.io.File" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.SysFinalRecource" %>
<%@ page import="com.yongjie.ZhiJianSbpt.utilities.FileUtil" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.ByteArrayInputStream" %>
<%@ page language="Java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String filePath = request.getParameter("filePath");
	/*  String fileAllPath=filePath;//因为查看申请书下载和现场核查文书下载是不一样的，所以传过来的是全路径。
	 File file=new File(fileAllPath);
	 byte[] fileContent=FileUtil.(file); */

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
        if (filetemp.exists()) {
            fileContent = FileUtil.getBytes(filetemp);
            break;
        }
    }
    if (fileContent == null) {
        response.getWriter().write("文件未找到！");
        return;
    }
    InputStream in = new ByteArrayInputStream(fileContent);
    ServletOutputStream os = null;
    byte[] buf = new byte[1024 * 1024 * 5]; //定义你需要的缓存大小
    response.setContentType("image/*");//设置图片的类型.jpeg等类型，此处我用了*，也没报错，是否通配待验证。
    os = response.getOutputStream();
    int len;
    while ((len = in.read(buf)) != -1) {
        os.write(buf, 0, len);
    }
    os.flush();
    os.close();
    os = null;
    response.flushBuffer();
    out.clear();
    out = pageContext.pushBody();
%>
