package com.gulbagomedovich.youtubecloneservice.dto;

import com.gulbagomedovich.youtubecloneservice.model.Video;
import com.gulbagomedovich.youtubecloneservice.model.VideoStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;
import java.util.Set;

@Getter
@ToString
@Builder
@Jacksonized
public class VideoDto {
    private String id;
    private String title;
    private String description;
    private Integer likesCount;
    private Integer dislikesCount;
    private String userId;
    private Set<String> tags;
    private String videoUrl;
    private VideoStatus status;
    private Integer viewsCount;
    private String previewUrl;
    private LocalDate videoUploadDate;

    public static VideoDto toVideoDto(Video video) {
        return VideoDto.builder()
                .id(video.getId())
                .title(video.getTitle())
                .description(video.getDescription())
                .userId(video.getUserId())
                .likesCount(video.getLikesCount().get())
                .dislikesCount(video.getDislikesCount().get())
                .tags(video.getTags())
                .videoUrl(video.getVideoUrl())
                .status(video.getStatus())
                .viewsCount(video.getViewsCount().get())
                .previewUrl(video.getPreviewUrl())
                .videoUploadDate(video.getVideoUploadDate())
                .build();
    }
}
