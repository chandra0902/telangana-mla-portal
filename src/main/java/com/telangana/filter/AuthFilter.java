package com.telangana.filter;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class AuthFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();

        // ✅ Allow public pages
        if (uri.contains("login") || uri.contains("home") || uri.contains("viewMla")) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("admin") != null) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect("login.html");
        }
    }
}