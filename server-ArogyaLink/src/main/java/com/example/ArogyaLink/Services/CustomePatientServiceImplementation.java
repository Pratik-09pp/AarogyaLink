package com.example.ArogyaLink.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ArogyaLink.Entity.Assistant;
import com.example.ArogyaLink.Entity.Patient;
import com.example.ArogyaLink.Repository.AssistantRepository;
import com.example.ArogyaLink.Repository.PatientRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomePatientServiceImplementation implements UserDetailsService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    public CustomePatientServiceImplementation(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Patient patient = patientRepository.findByEmail(username);

        if (patient == null) {
            throw new UsernameNotFoundException("User Not Found With Email"+username);
        }

        List<GrantedAuthority> authorities;
        authorities = Arrays.stream(patient.getRole().split(","))
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(patient.getEmail(),patient.getPassword(),authorities);
    }
}
