package com.example.ArogyaLink.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ArogyaLink.Entity.Doctor;
import com.example.ArogyaLink.Repository.DoctorRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserServiceImplementation implements UserDetailsService {

    private DoctorRepository doctorRepository;


    @Autowired
    public CustomUserServiceImplementation(DoctorRepository doctorRepository) {
        this.doctorRepository=doctorRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Doctor doctor = doctorRepository.findByEmail(username);

        if (doctor == null) {
            throw new UsernameNotFoundException("User Not Found With Email"+username);
        }

        List<GrantedAuthority> authorities;
        authorities = Arrays.stream(doctor.getRole().split(","))
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(doctor.getEmail(),doctor.getPassword(),authorities);
    }
}
