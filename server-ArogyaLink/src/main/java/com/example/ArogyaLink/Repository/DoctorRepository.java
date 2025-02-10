package com.example.ArogyaLink.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ArogyaLink.Entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    public Doctor findByEmail(String email);
}
