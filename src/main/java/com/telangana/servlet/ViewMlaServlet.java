package com.telangana.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import com.telangana.util.DBConnection;

public class ViewMlaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {

response.setContentType("application/json");

PrintWriter out = response.getWriter();

try{

Connection con = DBConnection.getConnection();

Statement st = con.createStatement();

ResultSet rs = st.executeQuery("SELECT * FROM mla");

StringBuilder json = new StringBuilder();
json.append("[");

boolean first=true;

while(rs.next()){

if(!first){
json.append(",");
}

String photo = rs.getString("photo");

if(photo==null){
photo="";
}

json.append("{");
json.append("\"id\":").append(rs.getInt("id")).append(",");
json.append("\"name\":\"").append(rs.getString("name")).append("\",");
json.append("\"party\":\"").append(rs.getString("party")).append("\",");
json.append("\"constituency\":\"").append(rs.getString("constituency")).append("\",");

/* ✅ ADD THIS LINE */
json.append("\"district\":\"").append(rs.getString("district")).append("\",");

json.append("\"photo\":\"").append(photo).append("\"");

json.append("}");

first=false;

}

json.append("]");

out.print(json.toString());

}catch(Exception e){
e.printStackTrace();
}

}
}