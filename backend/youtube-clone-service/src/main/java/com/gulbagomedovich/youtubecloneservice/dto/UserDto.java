package com.gulbagomedovich.youtubecloneservice.dto;

import com.gulbagomedovich.youtubecloneservice.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.util.Set;

@Getter
@ToString
@Builder
@Jacksonized
public class UserDto {
    private String id;
    private String sub;
    private String firstName;
    private String lastName;
    private String picture;
    private String email;
    private Set<String> subscriptions;
    private Set<String> subscribers;
    private Set<String> videoHistory;
    private Set<String> likedVideos;
    private Set<String> dislikedVideos;

    public static UserDto toUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .sub(user.getSub())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .picture(user.getPicture())
                .email(user.getEmail())
                .subscriptions(user.getSubscriptions())
                .subscribers(user.getSubscribers())
                .videoHistory(user.getVideoHistory())
                .likedVideos(user.getLikedVideos())
                .dislikedVideos(user.getDislikedVideos())
                .build();
    }
}
