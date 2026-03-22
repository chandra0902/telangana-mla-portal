package com.telangana.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;

import com.telangana.util.DBConnection;

@MultipartConfig
public class AddMlaServlet extends HttpServlet {

protected void doPost(HttpServletRequest req, HttpServletResponse res)
throws ServletException, IOException {

try{

String name = req.getParameter("name");
String ageStr = req.getParameter("age");
String party = req.getParameter("party");
String constituency = req.getParameter("constituency");

if(name==null || ageStr==null || party==null || constituency==null){

res.getWriter().print("invalid");
return;

}

int age = Integer.parseInt(ageStr);

/* UPLOAD FOLDER */

String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";

File uploadDir = new File(uploadPath);

if(!uploadDir.exists()){
uploadDir.mkdir();
}

/* PHOTO */

Part photoPart = req.getPart("photo");

String photoPath = null;

if(photoPart!=null && photoPart.getSize()>0){

String ext = photoPart.getSubmittedFileName()
.substring(photoPart.getSubmittedFileName().lastIndexOf("."));

String fileName = UUID.randomUUID().toString()+ext;

photoPart.write(uploadPath + File.separator + fileName);

photoPath = "uploads/" + fileName;

}

/* PARTY LOGO */

Part logoPart = req.getPart("partyLogo");

String logoPath = null;

if(logoPart!=null && logoPart.getSize()>0){

String ext = logoPart.getSubmittedFileName()
.substring(logoPart.getSubmittedFileName().lastIndexOf("."));

String fileName = UUID.randomUUID().toString()+ext;

logoPart.write(uploadPath + File.separator + fileName);

logoPath = "uploads/" + fileName;

}

/* DATABASE INSERT */

Connection con = DBConnection.getConnection();

PreparedStatement ps = con.prepareStatement(

"INSERT INTO mla(name,age,party,constituency,photo,party_logo) VALUES (?,?,?,?,?,?)"

);

ps.setString(1,name);
ps.setInt(2,age);
ps.setString(3,party);
ps.setString(4,constituency);
ps.setString(5,photoPath);
ps.setString(6,logoPath);

ps.executeUpdate();

res.getWriter().print("success");

}catch(Exception e){

e.printStackTrace();

res.getWriter().print("error");

}

}

}