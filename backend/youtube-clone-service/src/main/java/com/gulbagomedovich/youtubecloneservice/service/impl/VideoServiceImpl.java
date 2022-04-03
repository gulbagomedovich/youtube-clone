package com.gulbagomedovich.youtubecloneservice.service.impl;

import com.gulbagomedovich.youtubecloneservice.dto.AddVideoResponse;
import com.gulbagomedovich.youtubecloneservice.dto.CommentDto;
import com.gulbagomedovich.youtubecloneservice.dto.UpdateVideoRequest;
import com.gulbagomedovich.youtubecloneservice.dto.VideoDto;
import com.gulbagomedovich.youtubecloneservice.model.Comment;
import com.gulbagomedovich.youtubecloneservice.model.User;
import com.gulbagomedovich.youtubecloneservice.model.Video;
import com.gulbagomedovich.youtubecloneservice.model.VideoStatus;
import com.gulbagomedovich.youtubecloneservice.repository.VideoRepository;
import com.gulbagomedovich.youtubecloneservice.service.FileService;
import com.gulbagomedovich.youtubecloneservice.service.UserService;
import com.gulbagomedovich.youtubecloneservice.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository;
    private final FileService amazonS3Service;
    private final UserService userService;

    @Override
    public AddVideoResponse addVideo(MultipartFile videoFile) {
        Video video = new Video();
        video.setTitle(FilenameUtils.removeExtension(videoFile.getOriginalFilename()));
        video.setStatus(VideoStatus.PRIVATE);
        video.setVideoUploadDate(LocalDate.now());

        User currentUser = userService.getCurrentUser();
        video.setUserId(currentUser.getId());

        String videoUrl = amazonS3Service.uploadFile(videoFile);
        video.setVideoUrl(videoUrl);

        Video savedVideo = videoRepository.save(video);
        return AddVideoResponse.builder()
                .videoId(savedVideo.getId())
                .videoTitle(savedVideo.getTitle())
                .videoUrl(savedVideo.getVideoUrl())
                .videoStatus(savedVideo.getStatus())
                .build();
    }

    @Override
    public List<VideoDto> getVideos() {
        List<Video> videos = videoRepository.findAll();
        return videos.stream()
                .filter(video -> video.getStatus().equals(VideoStatus.PUBLIC))
                .map(VideoDto::toVideoDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VideoDto> getVideosByIds(List<String> ids) {
        List<Video> videos = (List<Video>) videoRepository.findAllById(ids);
        return videos.stream()
                .map(VideoDto::toVideoDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VideoDto> getVideosBySubscriptions(Set<String> subscriptions) {
        List<Video> videos = videoRepository.findAllByUserIdIn(subscriptions);
        return videos.stream()
                .sorted((video1, video2) -> video2.getVideoUploadDate().compareTo(video1.getVideoUploadDate()))
                .map(VideoDto::toVideoDto)
                .collect(Collectors.toList());
    }

    @Override
    public VideoDto getVideo(String id) {
        Video savedVideo = getVideoById(id);
        savedVideo.incrementViews();

        videoRepository.save(savedVideo);
        userService.addVideoToHistory(savedVideo.getId());

        return VideoDto.toVideoDto(savedVideo);
    }

    @Override
    public void updateVideo(String id, UpdateVideoRequest updateVideoRequest) {
        Video savedVideo = getVideoById(id);
        savedVideo.setTitle(updateVideoRequest.getVideoTitle());
        savedVideo.setDescription(updateVideoRequest.getVideoDescription());
        savedVideo.setTags(updateVideoRequest.getVideoTags());
        savedVideo.setStatus(updateVideoRequest.getVideoStatus());

        videoRepository.save(savedVideo);
    }

    @Override
    public void updatePreview(String videoId, MultipartFile previewFile) {
        Video savedVideo = getVideoById(videoId);

        String previewUrl = amazonS3Service.uploadFile(previewFile);
        savedVideo.setPreviewUrl(previewUrl);

        videoRepository.save(savedVideo);
    }

    @Override
    public VideoDto likeVideo(String videoId) {
        Video savedVideo = getVideoById(videoId);

        if (userService.ifLikedVideo(videoId)) {
            savedVideo.decrementLikes();
            userService.removeLikedVideo(videoId);
        } else if (userService.ifDislikedVideo(videoId)) {
            savedVideo.decrementDislikes();
            userService.removeDislikedVideo(videoId);
            savedVideo.incrementLikes();
            userService.addLikedVideo(videoId);
        } else {
            savedVideo.incrementLikes();
            userService.addLikedVideo(videoId);
        }

        videoRepository.save(savedVideo);
        return VideoDto.toVideoDto(savedVideo);
    }

    @Override
    public VideoDto dislikeVideo(String videoId) {
        Video savedVideo = getVideoById(videoId);

        if (userService.ifLikedVideo(videoId)) {
            savedVideo.decrementLikes();
            userService.removeLikedVideo(videoId);
            savedVideo.incrementDislikes();
            userService.addDislikedVideo(videoId);
        } else if (userService.ifDislikedVideo(videoId)) {
            savedVideo.decrementDislikes();
            userService.removeDislikedVideo(videoId);
        } else {
            savedVideo.incrementDislikes();
            userService.addDislikedVideo(videoId);
        }

        videoRepository.save(savedVideo);
        return VideoDto.toVideoDto(savedVideo);
    }

    @Override
    public void addComment(String videoId, CommentDto commentDto) {
        Video savedVideo = getVideoById(videoId);

        Comment comment = new Comment();
        comment.setText(commentDto.getText());
        comment.setUserId(commentDto.getUserId());

        savedVideo.addComment(comment);
        videoRepository.save(savedVideo);
    }

    @Override
    public List<CommentDto> getComments(String videoId) {
        Video savedVideo = getVideoById(videoId);
        List<Comment> comments = savedVideo.getComments();
        return comments.stream()
                .map(comment -> CommentDto.builder()
                        .text(comment.getText())
                        .userId(comment.getUserId())
                        .build())
                .collect(Collectors.toList());
    }

    private Video getVideoById(String id) {
        return videoRepository.findById(id)
                //TODO Выбросить собственное исключение
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Video not found"));
    }
}
