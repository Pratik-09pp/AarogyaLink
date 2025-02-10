package com.example.ArogyaLink.Services;

import java.util.List;

import com.example.ArogyaLink.Entity.Assistant;
import com.example.ArogyaLink.Entity.Patient;
import com.example.ArogyaLink.exceptions.UserException;

public interface PatientService {
    public Patient findByEmail(String email) throws UserException;

    public List<Patient> findAll();

    public Patient findById(Long assistantId) throws UserException;

    public Patient findUserProfileByJwt(String jwt) throws UserException;
}
