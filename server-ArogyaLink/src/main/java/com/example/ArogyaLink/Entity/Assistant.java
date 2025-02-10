package com.example.ArogyaLink.Entity;

import jakarta.persistence.*;

@Entity
public class Assistant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first_name;

    private String last_name;
    private String email;
    private String mobile_No;
    private String gender;

    private String password;

    private String hospital;

    private String role;

    public Assistant(){

    }
    public Assistant(String first_name, String last_name, String email, String mobile_No, String gender, String password, String hospital, String role) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.mobile_No = mobile_No;
        this.gender = gender;
        this.password = password;
        this.hospital = hospital;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile_No() {
        return mobile_No;
    }

    public void setMobile_No(String mobile_No) {
        this.mobile_No = mobile_No;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
