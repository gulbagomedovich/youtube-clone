package com.gulbagomedovich.youtubecloneservice.controller;

import com.gulbagomedovich.youtubecloneservice.dto.UserDto;
import com.gulbagomedovich.youtubecloneservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable String id) {
        return userService.getUser(id);
    }

    @GetMapping("/{userId}/check-subscription")
    public boolean checkSubscription(@PathVariable String userId) {
        return userService.checkSubscription(userId);
    }

    @PostMapping("/{userId}/subscribe")
    public UserDto subscribe(@PathVariable String userId) {
        return userService.subscribe(userId);
    }

    @PostMapping("/{userId}/unsubscribe")
    public UserDto unsubscribe(@PathVariable String userId) {
        return userService.unsubscribe(userId);
    }

    @GetMapping("/{userId}/subscriptions")
    public Set<String> getSubscriptions(@PathVariable String userId) {
        return userService.getSubscriptions(userId);
    }

    @GetMapping("/{userId}/video-history")
    public Set<String> getVideoHistory(@PathVariable String userId) {
        return userService.getVideoHistory(userId);
    }

    @GetMapping("/{userId}/liked-videos")
    public Set<String> getLikedVideos(@PathVariable String userId) {
        return userService.getLikedVideos(userId);
    }
}
