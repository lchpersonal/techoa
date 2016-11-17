package com.tech.oa.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccessFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter inited ~~");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rep = (HttpServletResponse) response;
        req.setCharacterEncoding("UTF-8");
        rep.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        String reqUri = req.getRequestURI();
        if (!reqUri.contains("login") && !reqUri.contains("static")) {
            Object isLogin = session.getAttribute("login");
            if (isLogin == null || !isLogin.equals("true")) {
                rep.sendRedirect("/loginPage");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
