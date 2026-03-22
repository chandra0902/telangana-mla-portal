package com.telangana.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import com.telangana.dao.MlaDAO;

public class DeleteMlaServlet extends HttpServlet {

protected void doGet(HttpServletRequest req, HttpServletResponse res)
throws ServletException, IOException {

try{

int id = Integer.parseInt(req.getParameter("id"));

MlaDAO dao = new MlaDAO();

dao.deleteMla(id);

res.getWriter().print("deleted");

}
catch(Exception e){

e.printStackTrace();

res.getWriter().print("error");

}

}

}