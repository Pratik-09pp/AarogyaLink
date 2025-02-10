package com.example.ArogyaLink.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class MedicalTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicalTestId;
    private String test_Name;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "id")
    private Patient patient;

    public MedicalTest(String test_Name, Date date, Patient patient) {
        this.test_Name = test_Name;
        this.date = date;
        this.patient = patient;
    }

    public Long getMedicalTestId() {
        return medicalTestId;
    }

    public void setMedicalTestId(Long medicalTestId) {
        this.medicalTestId = medicalTestId;
    }

    public String getTest_Name() {
        return test_Name;
    }

    public void setTest_Name(String test_Name) {
        this.test_Name = test_Name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    // Getters and setters
}
