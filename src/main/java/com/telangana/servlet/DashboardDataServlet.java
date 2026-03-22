package com.telangana.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import com.telangana.util.DBConnection;

public class DashboardDataServlet extends HttpServlet {

protected void doGet(HttpServletRequest req, HttpServletResponse res)
throws ServletException, IOException {

res.setContentType("application/json");

PrintWriter out = res.getWriter();

try{

Connection con = DBConnection.getConnection();

Statement st = con.createStatement();

/* TOTAL MLA */

ResultSet total = st.executeQuery("SELECT COUNT(*) total FROM mla");
total.next();

int totalMla = total.getInt("total");

/* PARTY DISTRIBUTION */

ResultSet rs = st.executeQuery(
"SELECT party, COUNT(*) cnt FROM mla GROUP BY party");

String labels="";
String data="";

while(rs.next()){

labels += "\""+rs.getString("party")+"\",";
data += rs.getInt("cnt")+",";

}

if(labels.length()>0){
labels = labels.substring(0,labels.length()-1);
data = data.substring(0,data.length()-1);
}

String json = "{";

json += "\"totalMla\":"+totalMla+",";

json += "\"labels\":["+labels+"],";

json += "\"data\":["+data+"]";

json += "}";

out.print(json);

}catch(Exception e){
e.printStackTrace();
}

}
}