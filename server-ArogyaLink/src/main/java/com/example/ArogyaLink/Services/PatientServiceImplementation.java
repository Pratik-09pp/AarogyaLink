package com.example.ArogyaLink.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ArogyaLink.Entity.Assistant;
import com.example.ArogyaLink.Entity.Patient;
import com.example.ArogyaLink.Repository.PatientRepository;
import com.example.ArogyaLink.config.JWTProvider;
import com.example.ArogyaLink.exceptions.UserException;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImplementation implements PatientService{

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    public PatientServiceImplementation(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient findByEmail(String email) throws UserException{
        Patient getPatient = patientRepository.findByEmail(email);
        if (getPatient == null){
            throw new UserException("Patient Not Found");
        }
        return getPatient;

    }

    @Override
    public List<Patient> findAll(){
        return patientRepository.findAll();
    }

    @Override
    public Patient findById(Long patientId) throws UserException {
        Optional<Patient> user = patientRepository.findById(patientId);

        if (user.isPresent()) {
            return user.get();
        }
        throw new UserException("User Not Found With Id -"+patientId);
    }

    @Override
    public Patient findUserProfileByJwt(String jwt) throws UserException {
        String email = jwtProvider.getEmailFromToken(jwt);

        Patient patient = patientRepository.findByEmail(email);

        if (patient == null) {
            throw new UserException("User Not Found With This Exception"+email);
        }
        System.out.println("Found User : "+patient.toString());
        return patient;
    }
}
