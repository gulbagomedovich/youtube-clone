package com.gulbagomedovich.youtubecloneservice.controller;

import com.gulbagomedovich.youtubecloneservice.dto.AddVideoResponse;
import com.gulbagomedovich.youtubecloneservice.dto.CommentDto;
import com.gulbagomedovich.youtubecloneservice.dto.UpdateVideoRequest;
import com.gulbagomedovich.youtubecloneservice.dto.VideoDto;
import com.gulbagomedovich.youtubecloneservice.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;

    @PostMapping
    public ResponseEntity<AddVideoResponse> addVideo(@RequestParam("video") MultipartFile videoFile) {
        AddVideoResponse addVideoResponse = videoService.addVideo(videoFile);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addVideoResponse.getVideoId())
                .toUri();
        return ResponseEntity.created(location).body(addVideoResponse);
    }

    @GetMapping
    public List<VideoDto> getVideos() {
        return videoService.getVideos();
    }

    @GetMapping("/{id}")
    public VideoDto getVideo(@PathVariable String id) {
        return videoService.getVideo(id);
    }

    @PatchMapping("/{id}")
    public void updateVideo(@PathVariable String id,
                            @RequestBody UpdateVideoRequest updateVideoRequest) {
        videoService.updateVideo(id, updateVideoRequest);
    }

    @PatchMapping
    public void updatePreview(@RequestParam String videoId,
                              @RequestParam("preview") MultipartFile previewFile) {
        videoService.updatePreview(videoId, previewFile);
    }

    @PostMapping("/{videoId}/like")
    public VideoDto likeVideo(@PathVariable String videoId) {
        return videoService.likeVideo(videoId);
    }

    @PostMapping("/{videoId}/dislike")
    public VideoDto dislikeVideo(@PathVariable String videoId) {
        return videoService.dislikeVideo(videoId);
    }

    @PostMapping("/{videoId}/comment")
    public void addComment(@PathVariable String videoId, @RequestBody CommentDto commentDto) {
        videoService.addComment(videoId, commentDto);
    }

    @GetMapping("/{videoId}/comments")
    public List<CommentDto> getComments(@PathVariable String videoId) {
        return videoService.getComments(videoId);
    }

    @PostMapping("/cast-ids-to-videos")
    public List<VideoDto> castIdsToVideos(@RequestBody List<String> ids) {
        return videoService.getVideosByIds(ids);
    }

    @PostMapping("/by-subscriptions")
    public List<VideoDto> getVideosBySubscriptions(@RequestBody Set<String> subscriptions) {
        return videoService.getVideosBySubscriptions(subscriptions);
    }
}
