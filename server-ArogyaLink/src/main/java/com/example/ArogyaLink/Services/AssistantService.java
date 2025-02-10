package com.example.ArogyaLink.Services;

import com.example.ArogyaLink.Entity.Assistant;
import com.example.ArogyaLink.Entity.Doctor;
import com.example.ArogyaLink.exceptions.UserException;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

public interface AssistantService {
    public Assistant addAssistant(Assistant assistant) throws UserException;

    public Assistant findById(Long assistantId) throws UserException;

    public Assistant findUserProfileByJwt(String jwt) throws UserException;

    public Assistant updateUser(String jwt,Assistant assistant) throws UserException;
}
