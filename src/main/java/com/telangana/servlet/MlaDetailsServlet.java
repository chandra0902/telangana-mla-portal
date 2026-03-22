package com.telangana.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import com.telangana.util.DBConnection;

public class MlaDetailsServlet extends HttpServlet {

protected void doGet(HttpServletRequest req, HttpServletResponse res)
throws ServletException, IOException {

res.setContentType("text/html");
PrintWriter out = res.getWriter();

try{

String idParam = req.getParameter("id");

if(idParam == null || idParam.isEmpty()){
out.println("<h2>Invalid MLA ID</h2>");
return;
}

int id = Integer.parseInt(idParam);

Connection con = DBConnection.getConnection();

/* MLA DATA */

PreparedStatement ps = con.prepareStatement("SELECT * FROM mla WHERE id=?");
ps.setInt(1,id);
ResultSet rs = ps.executeQuery();

/* DEFAULT VALUES */

int attendance = 0;
double funds = 0;
int questions = 0;
double rating = 0;

/* PERFORMANCE */

try{
PreparedStatement ps2 = con.prepareStatement(
"SELECT * FROM mla_performance WHERE mla_id=?");

ps2.setInt(1,id);
ResultSet perf = ps2.executeQuery();

if(perf.next()){
attendance = perf.getInt("attendance");
funds = perf.getDouble("development_funds");
questions = perf.getInt("questions_raised");
rating = perf.getDouble("public_rating");
}
}catch(Exception e){
System.out.println("Performance missing");
}

/* MLA FOUND */

if(rs.next()){

String name = rs.getString("name");
String party = rs.getString("party");
String constituency = rs.getString("constituency");
String district = rs.getString("district");
String photo = rs.getString("photo");
String contact = rs.getString("contact");
String email = rs.getString("email");
String twitter = rs.getString("twitter");
String bio = rs.getString("bio");
int age = rs.getInt("age");

/* NULL SAFETY */

if(photo == null || photo.equals("")) photo = "uploads/default.png";
if(district == null) district = "Not Available";
if(contact == null) contact = "Not Available";
if(email == null) email = "#";
if(twitter == null) twitter = "#";
if(bio == null) bio = "No biography available";

/* PARTY LOGO */

String logo="uploads/default.png";
if(party.equals("BRS")) logo="uploads/brs.png";
if(party.equals("INC")) logo="uploads/congress.png";
if(party.equals("BJP")) logo="uploads/bjp.png";
if(party.equals("AIMIM")) logo="uploads/aimim.png";

/* SCORE */

double score = (attendance + questions + rating*20)/3;

String status="Average";
if(score > 75) status="Excellent";
else if(score > 50) status="Good";
else status="Needs Improvement";

/* HTML */

out.println("<html><head><title>MLA Profile</title>");

out.println("<link href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css' rel='stylesheet'>");

out.println("<style>");

out.println("body{font-family:Poppins;background:linear-gradient(135deg,#141e30,#243b55);margin:0;color:white;}");

out.println(".container{max-width:1100px;margin:auto;padding:40px;}");

out.println(".card{display:flex;gap:30px;background:rgba(255,255,255,0.1);padding:30px;border-radius:20px;}");

out.println(".photo{width:220px;height:220px;border-radius:20px;object-fit:cover;}");

out.println(".badge{background:#00c6ff;padding:5px 10px;border-radius:10px;margin:5px;display:inline-block;}");

out.println(".box{background:rgba(255,255,255,0.1);padding:20px;margin-top:20px;border-radius:15px;}");

out.println(".progress{background:#333;height:10px;border-radius:10px;}");

out.println(".progress div{background:#00c6ff;height:10px;border-radius:10px;}");

out.println(".back{display:inline-block;margin-top:20px;padding:10px 20px;background:white;color:#1e3c72;border-radius:20px;text-decoration:none;}");

out.println("</style>");

out.println("</head><body>");

out.println("<div class='container'>");

/* PROFILE */

out.println("<div class='card'>");

String img = "<img class='photo' src='"+photo+"' onerror=\"this.onerror=null;this.src='uploads/default.png'\">";
out.println(img);

out.println("<div>");

out.println("<h2>"+name+"</h2>");
out.println("<img src='"+logo+"' width='60'>");

out.println("<div class='badge'>"+party+"</div>");
out.println("<div class='badge'>"+constituency+"</div>");
out.println("<div class='badge'>"+district+"</div>");

out.println("<p><b>Age:</b> "+age+"</p>");
out.println("<p>"+bio+"</p>");

out.println("<p>");
out.println("<i class='fa fa-phone'></i> "+contact+" | ");
out.println("<i class='fa fa-envelope'></i> "+email+" | ");
out.println("<a href='"+twitter+"' target='_blank' style='color:#00c6ff'><i class='fa fa-twitter'></i></a>");
out.println("</p>");

out.println("</div></div>");

/* SCORE */

out.println("<div class='box'>");

out.println("<h3>🏆 Overall Score: "+(int)score+" / 100</h3>");
out.println("<p>Status: "+status+"</p>");

out.println("</div>");

/* RATING */

out.println("<div class='box'>");

out.println("<h3>⭐ Rating</h3>");

int full = (int) rating;
for(int i=0;i<full;i++) out.print("⭐");
for(int i=full;i<5;i++) out.print("☆");

out.println("<p>"+rating+" / 5</p>");

out.println("</div>");

/* PERFORMANCE */

out.println("<div class='box'>");

out.println("<h3>📊 Performance</h3>");

out.println("<p>Attendance: "+attendance+"%</p>");
out.println("<div class='progress'><div style='width:"+attendance+"%'></div></div>");

out.println("<p>Questions: "+questions+"</p>");
out.println("<div class='progress'><div style='width:"+(questions*2)+"%'></div></div>");

out.println("<p>Funds: ₹"+funds+"</p>");
out.println("<div class='progress'><div style='width:70%'></div></div>");

out.println("</div>");

/* MAP */

out.println("<div class='box'>");

out.println("<h3>📍 Location</h3>");
out.println("<iframe width='100%' height='200' src='https://maps.google.com/maps?q="+constituency+"&output=embed'></iframe>");

out.println("</div>");

out.println("<a class='back' href='public.html'>⬅ Back</a>");

out.println("</div></body></html>");

}else{
out.println("<h2>No MLA Found</h2>");
}

}catch(Exception e){
e.printStackTrace();
out.println("<h2>Server Error</h2>");
}

}
}
