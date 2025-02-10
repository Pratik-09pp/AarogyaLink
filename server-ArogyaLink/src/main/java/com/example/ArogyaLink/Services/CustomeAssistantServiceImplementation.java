package com.example.ArogyaLink.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ArogyaLink.Entity.Assistant;
import com.example.ArogyaLink.Entity.Doctor;
import com.example.ArogyaLink.Repository.AssistantRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomeAssistantServiceImplementation implements UserDetailsService {

    private AssistantRepository assistantRepository;

    @Autowired
    public CustomeAssistantServiceImplementation(AssistantRepository assistantRepository) {
        this.assistantRepository = assistantRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Assistant assistant = assistantRepository.findByEmail(username);

        if (assistant == null) {
            throw new UsernameNotFoundException("User Not Found With Email"+username);
        }

        List<GrantedAuthority> authorities;
        authorities = Arrays.stream(assistant.getRole().split(","))
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(assistant.getEmail(),assistant.getPassword(),authorities);
    }
}
