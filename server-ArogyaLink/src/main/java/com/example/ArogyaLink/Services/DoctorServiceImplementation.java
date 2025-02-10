package com.example.ArogyaLink.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ArogyaLink.Entity.Doctor;
import com.example.ArogyaLink.Repository.DoctorRepository;
import com.example.ArogyaLink.config.JWTProvider;
import com.example.ArogyaLink.exceptions.UserException;

import java.util.Optional;

@Service
public class DoctorServiceImplementation implements DoctorService {

    private DoctorRepository doctorRepository;
    private JWTProvider jwtProvider;

    @Autowired
    public DoctorServiceImplementation(DoctorRepository doctorRepository, JWTProvider jwtProvider) {
        this.doctorRepository = doctorRepository;
        this.jwtProvider = jwtProvider;
    }


    @Override
    public Doctor findById(Long doctorId) throws UserException {
        Optional<Doctor> user = doctorRepository.findById(doctorId);

        if (user.isPresent()) {
            return user.get();
        }
        throw new UserException("User Not Found With Id -"+doctorId);
    }

    @Override
    public Doctor findUserProfileByJwt(String jwt) throws UserException {
        String email = jwtProvider.getEmailFromToken(jwt);

        Doctor doctor = doctorRepository.findByEmail(email);

        if (doctor == null) {
            throw new UserException("User Not Found With This Exception"+email);
        }
        System.out.println("Found User : "+doctor.toString());
        return doctor;
    }

    @Override
    public Doctor updateUser(String jwt, Doctor doctor) throws UserException {
        String email = jwtProvider.getEmailFromToken(jwt);
        Doctor existingDoctor = doctorRepository.findByEmail(email);

        if(existingDoctor == null){
            throw  new UserException("User Not Found With "+doctor.getEmail()+ "Email" );
        }else{
            existingDoctor.setFirst_name(doctor.getFirst_name());
            existingDoctor.setLast_name(doctor.getLast_name());
            existingDoctor.setGender(doctor.getGender());
            existingDoctor.setMobile_no(doctor.getMobile_no());
            existingDoctor.setQualification(doctor.getQualification());
            existingDoctor.setRole("Doctor");
        }
        return doctorRepository.save(existingDoctor);
    }
}
