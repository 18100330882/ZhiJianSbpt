package com.yongjie.ZhiJianSbpt.utilities;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        // 这是刚生成的验证码
        String validateC = (String) request.getSession().getAttribute("validateCode");
        // 这是前台传过来的文本框中的验证码
        String veryCode = request.getParameter("c");
        PrintWriter out = response.getWriter();
        if (veryCode != "" && veryCode.length() != 0) {
            if (!validateC.equalsIgnoreCase(veryCode)) {
                // 验证码输入错误要用语句提醒 正确就不提示了
                out.print("验证码错误!");
            }
        }
        out.flush();
        out.close();
        super.init();
    }
}
