package com.gulbagomedovich.youtubecloneservice.service.impl;

import com.gulbagomedovich.youtubecloneservice.dto.UserDto;
import com.gulbagomedovich.youtubecloneservice.model.User;
import com.gulbagomedovich.youtubecloneservice.repository.UserRepository;
import com.gulbagomedovich.youtubecloneservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDto getUser(String id) {
        User savedUser = getUserById(id);
        return UserDto.toUserDto(savedUser);
    }

    @Override
    public User getCurrentUser() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String sub = jwt.getClaim("sub");
        return userRepository.findBySub(sub)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find user with sub = " + sub));
    }

    @Override
    public boolean ifLikedVideo(String videoId) {
        return getCurrentUser().getLikedVideos().stream()
                .anyMatch(likedVideo -> likedVideo.equals(videoId));
    }

    @Override
    public boolean ifDislikedVideo(String videoId) {
        return getCurrentUser().getDislikedVideos().stream()
                .anyMatch(dislikedVideo -> dislikedVideo.equals(videoId));
    }

    @Override
    public void addLikedVideo(String videoId) {
        User currentUser = getCurrentUser();
        currentUser.addLikedVideo(videoId);

        userRepository.save(currentUser);
    }

    @Override
    public void removeLikedVideo(String videoId) {
        User currentUser = getCurrentUser();
        currentUser.removeLikedVideo(videoId);

        userRepository.save(currentUser);
    }

    @Override
    public void addDislikedVideo(String videoId) {
        User currentUser = getCurrentUser();
        currentUser.addDislikedVideo(videoId);

        userRepository.save(currentUser);
    }

    @Override
    public void removeDislikedVideo(String videoId) {
        User currentUser = getCurrentUser();
        currentUser.removeDislikedVideo(videoId);

        userRepository.save(currentUser);
    }

    @Override
    public void addVideoToHistory(String videoId) {
        User currentUser = getCurrentUser();
        currentUser.addVideoToHistory(videoId);

        userRepository.save(currentUser);
    }

    @Override
    public boolean checkSubscription(String userId) {
        User currentUser = getCurrentUser();
        return currentUser.getSubscriptions().contains(userId);
    }

    @Override
    public UserDto subscribe(String userId) {
        User currentUser = getCurrentUser();
        currentUser.addSubscription(userId);

        User targetUser = getUserById(userId);
        targetUser.addSubscriber(currentUser.getId());

        if (Objects.equals(currentUser.getId(), targetUser.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Самоподписка не разрешена");
        }

        userRepository.save(currentUser);
        userRepository.save(targetUser);

        return UserDto.toUserDto(targetUser);
    }

    @Override
    public UserDto unsubscribe(String userId) {
        User currentUser = getCurrentUser();
        currentUser.removeSubscription(userId);

        User targetUser = getUserById(userId);
        targetUser.removeSubscriber(currentUser.getId());

        userRepository.save(currentUser);
        userRepository.save(targetUser);

        return UserDto.toUserDto(targetUser);
    }

    @Override
    public Set<String> getSubscriptions(String userId) {
        User savedUser = getUserById(userId);
        return savedUser.getSubscriptions();
    }

    @Override
    public Set<String> getVideoHistory(String userId) {
        User savedUser = getUserById(userId);
        return savedUser.getVideoHistory();
    }

    @Override
    public Set<String> getLikedVideos(String userId) {
        User savedUser = getUserById(userId);
        return savedUser.getLikedVideos();
    }

    private User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find user with id = " + id));
    }
}
