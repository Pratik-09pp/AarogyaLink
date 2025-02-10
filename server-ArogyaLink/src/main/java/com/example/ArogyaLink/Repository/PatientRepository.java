package com.example.ArogyaLink.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ArogyaLink.Entity.Patient;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    public Patient findByEmail(String email);
}
