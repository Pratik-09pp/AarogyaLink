package com.example.ArogyaLink.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ArogyaLink.Entity.Doctor;
import com.example.ArogyaLink.Repository.DoctorRepository;
import com.example.ArogyaLink.Request.LoginRequest;
import com.example.ArogyaLink.Responces.AuthResponse;
import com.example.ArogyaLink.Services.CustomUserServiceImplementation;
import com.example.ArogyaLink.config.JWTProvider;
import com.example.ArogyaLink.exceptions.UserException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private DoctorRepository doctorRepository;
    private JWTProvider jwtProvider;
    private PasswordEncoder passwordEncoder;
    private CustomUserServiceImplementation customUserService;

    public AuthController() {
    }

    @Autowired
    public AuthController(DoctorRepository doctorRepository, JWTProvider jwtProvider, PasswordEncoder passwordEncoder, CustomUserServiceImplementation customUserService) {
        this.doctorRepository = doctorRepository;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
        this.customUserService = customUserService;

    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody Doctor doctor) throws UserException {
        String email = doctor.getEmail();
        String firstName = doctor.getFirst_name();
        String lastName = doctor.getLast_name();
        String mobile = doctor.getMobile_no();
        String gender = doctor.getGender();
        String hospitalName = doctor.getHospitalName();
        String password = doctor.getPassword();
        String qualification = doctor.getQualification();

        Doctor isEmailExist = doctorRepository.findByEmail(email);
        System.out.println(doctor.getFirst_name());
        if (isEmailExist != null) {
            throw new UserException("Email already exist for another user");
        }
        Doctor createDoctor = new Doctor();
        createDoctor.setEmail(doctor.getEmail());

        createDoctor.setFirst_name(firstName);
        createDoctor.setLast_name(lastName);
        createDoctor.setMobile_no(mobile);
        createDoctor.setGender(gender);
        createDoctor.setHospitalName(hospitalName);
        createDoctor.setPassword(passwordEncoder.encode(password));
        createDoctor.setQualification(qualification);

        createDoctor.setRole("Doctor");

        Doctor savedDoctor = doctorRepository.save(createDoctor);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedDoctor.getEmail(),savedDoctor.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();

        authResponse.setJwt(token);

        authResponse.setMessage("Signup Successfully");

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }
    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest) throws UserException{

        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = Authentication(email,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("SignIn Successfull");

        return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
    }
    private Authentication Authentication(String email, String password) {
        UserDetails userDetails = customUserService.loadUserByUsername(email);

        if (userDetails==null) {
            throw new BadCredentialsException("Invalid Email");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid Password");
        }
        System.out.println("Authorities:"+userDetails.getAuthorities());
        return new UsernamePasswordAuthenticationToken(email, null,userDetails.getAuthorities());
    }
}
