package com.telangana.filter;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class AuthFilter implements Filter {

public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
throws IOException, ServletException {

HttpServletRequest req = (HttpServletRequest) request;
HttpServletResponse res = (HttpServletResponse) response;

HttpSession session = req.getSession(false);

if(session != null && session.getAttribute("admin") != null){

chain.doFilter(request, response);

}else{

res.sendRedirect("login.html");

}

}

}