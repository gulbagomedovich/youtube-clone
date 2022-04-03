package com.gulbagomedovich.youtubecloneservice.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Document("users")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {
    @Id
    @Setter(AccessLevel.NONE)
    private String id;
    private String sub;
    private String firstName;
    private String lastName;
    private String picture;
    private String email;
    private Set<String> subscriptions = ConcurrentHashMap.newKeySet();
    private Set<String> subscribers = ConcurrentHashMap.newKeySet();
    private Set<String> videoHistory = ConcurrentHashMap.newKeySet();
    private Set<String> likedVideos = ConcurrentHashMap.newKeySet();
    private Set<String> dislikedVideos = ConcurrentHashMap.newKeySet();

    public User(String sub, String firstName, String lastName, String picture, String email, Set<String> subscriptions,
                Set<String> subscribers, Set<String> videoHistory, Set<String> likedVideos,
                Set<String> dislikedVideos) {
        this.sub = sub;
        this.firstName = firstName;
        this.lastName = lastName;
        this.picture = picture;
        this.email = email;
        this.subscriptions = subscriptions;
        this.subscribers = subscribers;
        this.videoHistory = videoHistory;
        this.likedVideos = likedVideos;
        this.dislikedVideos = dislikedVideos;
    }

    public void addSubscription(String userId) {
        subscriptions.add(userId);
    }

    public void removeSubscription(String userId) {
        subscriptions.remove(userId);
    }

    public void addSubscriber(String userId) {
        subscribers.add(userId);
    }

    public void removeSubscriber(String userId) {
        subscribers.remove(userId);
    }

    public void addVideoToHistory(String videoId) {
        videoHistory.add(videoId);
    }

    public void addLikedVideo(String videoId) {
        likedVideos.add(videoId);
    }

    public void removeLikedVideo(String videoId) {
        likedVideos.remove(videoId);
    }

    public void addDislikedVideo(String videoId) {
        dislikedVideos.add(videoId);
    }

    public void removeDislikedVideo(String videoId) {
        dislikedVideos.remove(videoId);
    }
}
