package com.telangana.model;

public class Mla {

    private int id;
    private String name;
    private int age;
    private String party;
    private String constituency;
    private String photo;
    private String partyLogo;
    private String bio;
    private String contact;
    private String email;
    private String twitter;

    // ⭐ NEW FIELD (ADDED)
    private double rating;

    public Mla() {
    }

    public Mla(int id, String name, int age, String party, String constituency,
               String photo, String partyLogo, String bio,
               String contact, String email, String twitter) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.party = party;
        this.constituency = constituency;
        this.photo = photo;
        this.partyLogo = partyLogo;
        this.bio = bio;
        this.contact = contact;
        this.email = email;
        this.twitter = twitter;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getParty() { return party; }
    public void setParty(String party) { this.party = party; }

    public String getConstituency() { return constituency; }
    public void setConstituency(String constituency) { this.constituency = constituency; }

    public String getPhoto() { return photo; }
    public void setPhoto(String photo) { this.photo = photo; }

    public String getPartyLogo() { return partyLogo; }
    public void setPartyLogo(String partyLogo) { this.partyLogo = partyLogo; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTwitter() { return twitter; }
    public void setTwitter(String twitter) { this.twitter = twitter; }

    // ⭐ NEW GETTER/SETTER
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
}