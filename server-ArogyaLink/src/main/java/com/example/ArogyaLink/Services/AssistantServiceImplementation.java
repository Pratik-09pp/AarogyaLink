package com.example.ArogyaLink.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ArogyaLink.Entity.Assistant;
import com.example.ArogyaLink.Entity.Doctor;
import com.example.ArogyaLink.Repository.AssistantRepository;
import com.example.ArogyaLink.config.JWTProvider;
import com.example.ArogyaLink.exceptions.UserException;

import java.util.Optional;

@Service
public class AssistantServiceImplementation implements AssistantService{

    @Autowired
    private AssistantRepository assistantRepository;

    @Autowired
    private JWTProvider jwtProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Assistant addAssistant(Assistant assistant) throws UserException {
        Assistant assistant1 = assistantRepository.findByEmail(assistant.getEmail());
        if(assistant1 != null){
            throw new UserException("Assistant Already Exists");
        }else{
            assistant.setRole("Assistant");
            assistant.setPassword(passwordEncoder.encode(assistant.getPassword()));
            return assistantRepository.save(assistant);
        }

    }

    @Override
    public Assistant findById(Long assistantId) throws UserException {
        Optional<Assistant> user = assistantRepository.findById(assistantId);

        if (user.isPresent()) {
            return user.get();
        }
        throw new UserException("User Not Found With Id -"+assistantId);
    }

    @Override
    public Assistant findUserProfileByJwt(String jwt) throws UserException {
        String email = jwtProvider.getEmailFromToken(jwt);

        Assistant assistant = assistantRepository.findByEmail(email);

        if (assistant == null) {
            throw new UserException("User Not Found With This Exception"+email);
        }
        System.out.println("Found User : "+assistant.toString());
        return assistant;
    }

    @Override
    public Assistant updateUser(String jwt, Assistant assistant) throws UserException {
        String email = jwtProvider.getEmailFromToken(jwt);
        Assistant existingAssistant = assistantRepository.findByEmail(email);

        if(existingAssistant == null){
            throw  new UserException("User Not Found With "+assistant.getEmail()+ "Email" );
        }else{
            existingAssistant.setFirst_name(assistant.getFirst_name());
            existingAssistant.setLast_name(assistant.getLast_name());
            existingAssistant.setGender(assistant.getGender());
            existingAssistant.setHospital(assistant.getHospital());
            existingAssistant.setMobile_No(assistant.getMobile_No());
            existingAssistant.setRole("Assistant");
        }
        return assistantRepository.save(existingAssistant);
    }

}
