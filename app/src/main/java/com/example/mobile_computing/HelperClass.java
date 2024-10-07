package com.example.mobile_computing;

public class HelperClass {
    String username;
    String email;
    String password;
    String phone;
    String province;
    String status;
    String gender;
    String birthdate;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }



    public HelperClass(String username, String email, String password, String phone, String province, String status, String gender, String birthdate) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.province = province;
        this.status = status;
        this.gender = gender;
        this.birthdate = birthdate;
    }

    public HelperClass() {

    }
}
