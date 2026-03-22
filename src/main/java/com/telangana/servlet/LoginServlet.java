package com.telangana.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import com.telangana.util.DBConnection;

public class LoginServlet extends HttpServlet {

protected void doPost(HttpServletRequest req, HttpServletResponse res)
throws ServletException, IOException {

try{

String username = req.getParameter("username");
String password = req.getParameter("password");

Connection con = DBConnection.getConnection();

PreparedStatement ps = con.prepareStatement(
"SELECT * FROM admin WHERE username=? AND password=?");

ps.setString(1, username);
ps.setString(2, password);

ResultSet rs = ps.executeQuery();

if(rs.next()){

HttpSession session = req.getSession();

session.setAttribute("admin", username);

res.sendRedirect("index.html");

}else{

res.getWriter().print("Invalid Credentials");

}

}
catch(Exception e){

e.printStackTrace();

}

}

}