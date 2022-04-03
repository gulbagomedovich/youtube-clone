package com.gulbagomedovich.youtubecloneservice.service.impl;

import com.gulbagomedovich.youtubecloneservice.dto.UserInfo;
import com.gulbagomedovich.youtubecloneservice.model.User;
import com.gulbagomedovich.youtubecloneservice.repository.UserRepository;
import com.gulbagomedovich.youtubecloneservice.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    @Setter(onMethod_ = @Value("${youtube-clone-service.auth0.user-info-uri}"))
    private String userInfoUri;

    @Override
    public String signup(String token) {
        UserInfo userInfo = getUserInfo(token);

        if (userInfo == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Exception occurred while registering user");
        }

        User user = new User();
        user.setSub(userInfo.getSub());
        user.setFirstName(userInfo.getGivenName());
        user.setLastName(userInfo.getFamilyName());
        user.setPicture(userInfo.getPicture());
        user.setEmail(userInfo.getEmail());

        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }

    @Override
    public boolean checkRegistration(String token) {
        UserInfo userInfo = getUserInfo(token);

        if (userInfo == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Exception occurred while checking registration");
        }

        return userRepository.findBySub(userInfo.getSub()).isPresent();
    }

    private UserInfo getUserInfo(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<UserInfo> responseEntity = restTemplate.exchange(userInfoUri, HttpMethod.GET,
                requestEntity, UserInfo.class);
        return responseEntity.getBody();
    }
}
