package com.example.ArogyaLink.Controller;

import com.example.ArogyaLink.Entity.Assistant;
import com.example.ArogyaLink.Entity.Patient;
import com.example.ArogyaLink.Repository.PatientRepository;
import com.example.ArogyaLink.Request.LoginRequest;
import com.example.ArogyaLink.Responces.AuthResponse;
import com.example.ArogyaLink.Services.AssistantService;
import com.example.ArogyaLink.Services.CustomeAssistantServiceImplementation;
import com.example.ArogyaLink.Services.CustomePatientServiceImplementation;
import com.example.ArogyaLink.Services.PatientService;
import com.example.ArogyaLink.config.JWTProvider;
import com.example.ArogyaLink.exceptions.UserException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PatientService patientService;

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private CustomePatientServiceImplementation customePatientService;

    @PostMapping("/addPatient")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient){
        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
        patient.setRole("Patient");
        Patient savePatient = patientRepository.save(patient);
        return new ResponseEntity<Patient>(savePatient, HttpStatus.CREATED);
    }

    @GetMapping("/profile")
    public ResponseEntity<Patient> getUserProfileHandler(@RequestHeader("Authorization") String jwt)throws UserException {
        Patient user = patientService.findUserProfileByJwt(jwt);
        return new ResponseEntity<Patient>(user, HttpStatus.ACCEPTED);
    }

//    @PostMapping("/update")
//    public ResponseEntity<Patient> update(@RequestBody Patient patient){
//        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
//        patient.setRole("Patient");
//        Patient savePatient = patientRepository.save(patient);
//        return new ResponseEntity<Patient>(savePatient, HttpStatus.CREATED);
//    }



    @PostMapping("/getPatient")
    public ResponseEntity<Patient> getData(@RequestBody String email) throws UserException , Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(email);

        // Extract the email address
        String email1 = jsonNode.get("email").asText();
        System.out.println("Email: " + email1);
        Patient patientData = patientService.findByEmail(email1);

        return new ResponseEntity<Patient>(patientData,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Patient>> getAll(String email){
        List<Patient> patientData = patientService.findAll();
        return new ResponseEntity<>(patientData,HttpStatus.OK);
    }

}
