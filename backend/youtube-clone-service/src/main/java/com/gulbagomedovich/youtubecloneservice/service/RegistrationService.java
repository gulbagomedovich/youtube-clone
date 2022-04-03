package com.gulbagomedovich.youtubecloneservice.service;

public interface RegistrationService {
    String signup(String token);

    boolean checkRegistration(String token);
}
