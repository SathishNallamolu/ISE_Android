package com.example.foodforall;

public class givefoodformat {

    String fullname, email, mobile_no, city, date;

    public givefoodformat() {
    }

    public givefoodformat(String fullname, String email, String mobile_no, String city, String date) {
        this.fullname = fullname;
        this.email = email;
        this.mobile_no = mobile_no;
        this.city = city;
        this.date = date;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

