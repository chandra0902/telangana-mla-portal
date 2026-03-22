package com.telangana.servlet;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;

import com.telangana.dao.MlaDAO;
import com.telangana.model.Mla;

@MultipartConfig
public class UpdateMlaServlet extends HttpServlet {

protected void doPost(HttpServletRequest req, HttpServletResponse res)
throws ServletException, IOException {

try{

MlaDAO dao = new MlaDAO();

String idParam = req.getParameter("id");

if(idParam == null || idParam.isEmpty()){
    res.getWriter().print("Invalid MLA ID");
    return;
}

int id = Integer.parseInt(idParam);

String action = req.getParameter("action");

/* ================= BASIC UPDATE ================= */

if("basic".equals(action)){

String name = req.getParameter("name");
int age = Integer.parseInt(req.getParameter("age"));
String party = req.getParameter("party");
String constituency = req.getParameter("constituency");

Mla mla = new Mla();

mla.setId(id);
mla.setName(name);
mla.setAge(age);
mla.setParty(party);
mla.setConstituency(constituency);

dao.updateMla(mla);

res.getWriter().print("basic-updated");

return;

}

/* ================= DETAILS UPDATE ================= */

String bio = req.getParameter("bio");
String contact = req.getParameter("contact");
String email = req.getParameter("email");
String twitter = req.getParameter("twitter");

/* ================= FILE UPLOAD ================= */

String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";

File uploadDir = new File(uploadPath);

if(!uploadDir.exists()) uploadDir.mkdir();

/* ================= PHOTO ================= */

String photoPath = null;

Part photoPart = req.getPart("photo");

if(photoPart != null && photoPart.getSize() > 0){

String original = photoPart.getSubmittedFileName();

String ext = original.substring(original.lastIndexOf("."));

String unique = UUID.randomUUID().toString()+ext;

photoPart.write(uploadPath + File.separator + unique);

photoPath = "uploads/" + unique;

}

/* ================= PARTY LOGO ================= */

String logoPath = null;

Part logoPart = req.getPart("partyLogo");

if(logoPart != null && logoPart.getSize() > 0){

String original = logoPart.getSubmittedFileName();

String ext = original.substring(original.lastIndexOf("."));

String unique = UUID.randomUUID().toString()+ext;

logoPart.write(uploadPath + File.separator + unique);

logoPath = "uploads/" + unique;

}

/* ================= UPDATE MLA DETAILS ================= */

Mla mla = new Mla();

mla.setId(id);
mla.setBio(bio);
mla.setContact(contact);
mla.setEmail(email);
mla.setTwitter(twitter);
mla.setPhoto(photoPath);
mla.setPartyLogo(logoPath);

dao.updateMla(mla);

res.getWriter().print("details-updated");

}
catch(Exception e){

e.printStackTrace();

res.getWriter().print("error");

}

}

}