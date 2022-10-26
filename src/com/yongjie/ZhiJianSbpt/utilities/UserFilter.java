package com.yongjie.ZhiJianSbpt.utilities;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        //System.out.println("请求被处理时间是："+httpServletRequest.getContextPath()+"vvvvvvv");
        String user = (String) httpServletRequest.getSession().getAttribute("userName");
        //System.out.println("请求被处理时间是："+httpServletRequest.getSession().getAttribute("userName"));
        if (!isExcludePages(httpServletRequest.getRequestURI())) {
            if (StringUtil.isNullOrEmpty(user)) {
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.jsp");
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isExcludePages(String url) {
        return url.indexOf("login.dhtml") != -1
                || url.indexOf("logout.dhtml") != -1
                || url.indexOf("login.jsp") != -1
                || url.endsWith(".css")
                || url.endsWith(".js")
                || url.endsWith(".gif")
                || url.endsWith(".jpg")
                || url.endsWith(".png");
    }

    public void destroy() {
    }
}
