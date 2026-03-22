package com.telangana.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.telangana.model.Mla;
import com.telangana.util.DBConnection;

public class MlaDAO {

    /* ================= ADD MLA ================= */

    public void addMla(Mla mla) {

        String sql = "INSERT INTO mla(name,age,party,constituency,photo,party_logo,bio,contact,email,twitter) VALUES(?,?,?,?,?,?,?,?,?,?)";

        try {

            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, mla.getName());
            ps.setInt(2, mla.getAge());
            ps.setString(3, mla.getParty());
            ps.setString(4, mla.getConstituency());
            ps.setString(5, mla.getPhoto());
            ps.setString(6, mla.getPartyLogo());
            ps.setString(7, mla.getBio());
            ps.setString(8, mla.getContact());
            ps.setString(9, mla.getEmail());
            ps.setString(10, mla.getTwitter());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* ================= GET ALL MLAs ================= */

    public List<Mla> getAllMla() {

        List<Mla> list = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM mla");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Mla m = new Mla();

                m.setId(rs.getInt("id"));
                m.setName(rs.getString("name"));
                m.setAge(rs.getInt("age"));
                m.setParty(rs.getString("party"));
                m.setConstituency(rs.getString("constituency"));
                m.setPhoto(rs.getString("photo"));
                m.setPartyLogo(rs.getString("party_logo"));
                m.setBio(rs.getString("bio"));
                m.setContact(rs.getString("contact"));
                m.setEmail(rs.getString("email"));
                m.setTwitter(rs.getString("twitter"));

                list.add(m);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /* ================= DELETE MLA ================= */

    public void deleteMla(int id) {

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement("DELETE FROM mla WHERE id=?");

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* ================= GET MLA BY ID ================= */

    public Mla getMlaById(int id) {

        Mla m = null;

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM mla WHERE id=?");

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                m = new Mla();

                m.setId(rs.getInt("id"));
                m.setName(rs.getString("name"));
                m.setAge(rs.getInt("age"));
                m.setParty(rs.getString("party"));
                m.setConstituency(rs.getString("constituency"));
                m.setPhoto(rs.getString("photo"));
                m.setPartyLogo(rs.getString("party_logo"));
                m.setBio(rs.getString("bio"));
                m.setContact(rs.getString("contact"));
                m.setEmail(rs.getString("email"));
                m.setTwitter(rs.getString("twitter"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return m;
    }

    /* ================= UPDATE MLA ================= */

    public void updateMla(Mla mla) {

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(

                    "UPDATE mla SET name=?,age=?,party=?,constituency=?,bio=?,contact=?,email=?,twitter=? WHERE id=?");

            ps.setString(1, mla.getName());
            ps.setInt(2, mla.getAge());
            ps.setString(3, mla.getParty());
            ps.setString(4, mla.getConstituency());
            ps.setString(5, mla.getBio());
            ps.setString(6, mla.getContact());
            ps.setString(7, mla.getEmail());
            ps.setString(8, mla.getTwitter());
            ps.setInt(9, mla.getId());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}