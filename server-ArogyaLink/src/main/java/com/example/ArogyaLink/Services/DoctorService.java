package com.example.ArogyaLink.Services;

import com.example.ArogyaLink.Entity.Doctor;
import com.example.ArogyaLink.exceptions.UserException;

public interface DoctorService {
    public Doctor findById(Long doctorId) throws UserException;

    public Doctor findUserProfileByJwt(String jwt) throws UserException;

    public Doctor updateUser(String jwt,Doctor doctor) throws UserException;
}
