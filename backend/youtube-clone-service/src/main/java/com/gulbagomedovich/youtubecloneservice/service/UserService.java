package com.gulbagomedovich.youtubecloneservice.service;

import com.gulbagomedovich.youtubecloneservice.dto.UserDto;
import com.gulbagomedovich.youtubecloneservice.model.User;

import java.util.Set;

public interface UserService {
    UserDto getUser(String id);

    User getCurrentUser();

    boolean ifLikedVideo(String videoId);

    boolean ifDislikedVideo(String videoId);

    void addLikedVideo(String videoId);

    void removeLikedVideo(String videoId);

    void addDislikedVideo(String videoId);

    void removeDislikedVideo(String videoId);

    void addVideoToHistory(String videoId);

    boolean checkSubscription(String userId);

    UserDto subscribe(String userId);

    UserDto unsubscribe(String userId);

    Set<String> getSubscriptions(String userId);

    Set<String> getVideoHistory(String userId);

    Set<String> getLikedVideos(String userId);
}
