package com.gulbagomedovich.youtubecloneservice.controller;

import com.gulbagomedovich.youtubecloneservice.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String userId = registrationService.signup(jwt.getTokenValue());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userId)
                .toUri();
        return ResponseEntity.created(location).body(userId);
    }

    @GetMapping("/check-registration")
    public boolean checkRegistration(Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        return registrationService.checkRegistration(jwt.getTokenValue());
    }
}
