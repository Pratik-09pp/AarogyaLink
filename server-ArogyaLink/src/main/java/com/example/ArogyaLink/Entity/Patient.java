package com.example.ArogyaLink.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String patient_Name;
    private String email;
    private Integer mobile_No;
    private String gender;
    private Integer age;
    private String address;
    private List<String> Allergy = new ArrayList<>();

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MedicalTest> tests = new ArrayList<>();

//    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<String> surgeries = new ArrayList<>(); // List of surgeries

    private List<String> Current_Medicine = new ArrayList<>();

    private String role;

    private String password;

    // No-argument constructor
    public Patient() {}

    public Patient( String patient_Name, String email, Integer mobile_No, String gender,Integer age, String address, List<String> allergy, List<MedicalTest> tests, List<String> surgeries, List<String> current_Medicine, String role, String password) {
        this.patient_Name = patient_Name;
        this.email = email;
        this.mobile_No = mobile_No;
        this.gender = gender;
        this.age = age;
        this.address = address;
        Allergy = allergy;
        this.tests = tests;
        this.surgeries = surgeries;
        Current_Medicine = current_Medicine;
        this.role = role;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatient_Name() {
        return patient_Name;
    }

    public void setPatient_Name(String patient_Name) {
        this.patient_Name = patient_Name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getMobile_No() {
        return mobile_No;
    }

    public void setMobile_No(Integer mobile_No) {
        this.mobile_No = mobile_No;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getAllergy() {
        return Allergy;
    }

    public void setAllergy(List<String> allergy) {
        Allergy = allergy;
    }

    public List<MedicalTest> getTests() {
        return tests;
    }

    public void setTests(List<MedicalTest> tests) {
        this.tests = tests;
    }

    public List<String> getSurgeries() {
        return surgeries;
    }

    public void setSurgeries(List<String> surgeries) {
        this.surgeries = surgeries;
    }

    public List<String> getCurrent_Medicine() {
        return Current_Medicine;
    }

    public void setCurrent_Medicine(List<String> current_Medicine) {
        Current_Medicine = current_Medicine;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
