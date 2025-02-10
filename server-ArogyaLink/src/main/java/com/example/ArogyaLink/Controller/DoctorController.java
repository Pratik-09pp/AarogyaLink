package com.example.ArogyaLink.Controller;

import com.example.ArogyaLink.Entity.Assistant;
import com.example.ArogyaLink.Entity.Doctor;
import com.example.ArogyaLink.Repository.AssistantRepository;
import com.example.ArogyaLink.Services.AssistantService;
import com.example.ArogyaLink.Services.DoctorService;
import com.example.ArogyaLink.exceptions.UserException;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AssistantService assistantService;



    @GetMapping("/profile")
    public ResponseEntity<Doctor> getUserProfileHandler(@RequestHeader("Authorization") String jwt)throws UserException {
        Doctor user = doctorService.findUserProfileByJwt(jwt);
        return new ResponseEntity<Doctor>(user, HttpStatus.ACCEPTED);
    }

    @PutMapping("/profile/update")
    public ResponseEntity<Doctor> updateUser(@RequestHeader("Authorization") String jwt,@RequestBody Doctor doctor)throws UserException {
        Doctor updatedProfile = doctorService.updateUser(jwt,doctor);
        return new ResponseEntity<Doctor>(updatedProfile,HttpStatus.ACCEPTED);
    }

    @PostMapping("/addassistant")
    public ResponseEntity<Assistant> addAssistant(@RequestBody Assistant assistant) throws UserException {
        Assistant newAssistant = assistantService.addAssistant(assistant);
        return new ResponseEntity<Assistant>(newAssistant, HttpStatus.CREATED);
    }

}
