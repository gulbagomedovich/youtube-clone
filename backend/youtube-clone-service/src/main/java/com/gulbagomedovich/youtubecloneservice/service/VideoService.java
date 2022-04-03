package com.gulbagomedovich.youtubecloneservice.service;

import com.gulbagomedovich.youtubecloneservice.dto.AddVideoResponse;
import com.gulbagomedovich.youtubecloneservice.dto.CommentDto;
import com.gulbagomedovich.youtubecloneservice.dto.UpdateVideoRequest;
import com.gulbagomedovich.youtubecloneservice.dto.VideoDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface VideoService {
    AddVideoResponse addVideo(MultipartFile videoFile);

    List<VideoDto> getVideos();

    List<VideoDto> getVideosByIds(List<String> ids);

    List<VideoDto> getVideosBySubscriptions(Set<String> subscriptions);

    VideoDto getVideo(String id);

    void updateVideo(String id, UpdateVideoRequest updateVideoRequest);

    void updatePreview(String videoId, MultipartFile previewFile);

    VideoDto likeVideo(String videoId);

    VideoDto dislikeVideo(String videoId);

    void addComment(String videoId, CommentDto commentDto);

    List<CommentDto> getComments(String videoId);
}
