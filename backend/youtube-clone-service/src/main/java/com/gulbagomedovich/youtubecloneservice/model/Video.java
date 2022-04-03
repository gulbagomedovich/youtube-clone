package com.gulbagomedovich.youtubecloneservice.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Document("videos")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Video {
    @Id
    @Setter(AccessLevel.NONE)
    private String id;
    private String title;
    private String description;
    private String userId;
    private AtomicInteger likesCount = new AtomicInteger(0);
    private AtomicInteger dislikesCount = new AtomicInteger(0);
    private Set<String> tags;
    private String videoUrl;
    private VideoStatus status;
    private AtomicInteger viewsCount = new AtomicInteger(0);
    private String previewUrl;
    private List<Comment> comments = new CopyOnWriteArrayList<>();
    private LocalDate videoUploadDate;

    public Video(String videoTitle, String description, String userId, AtomicInteger likesCount,
                 AtomicInteger dislikesCount, Set<String> tags, String videoUrl, VideoStatus status,
                 AtomicInteger viewsCount, String previewUrl, List<Comment> comments, LocalDate videoUploadDate) {
        this.title = videoTitle;
        this.description = description;
        this.userId = userId;
        this.likesCount = likesCount;
        this.dislikesCount = dislikesCount;
        this.tags = tags;
        this.videoUrl = videoUrl;
        this.status = status;
        this.viewsCount = viewsCount;
        this.previewUrl = previewUrl;
        this.comments = comments;
        this.videoUploadDate = videoUploadDate;
    }

    public void incrementLikes() {
        likesCount.incrementAndGet();
    }

    public void decrementLikes() {
        likesCount.decrementAndGet();
    }

    public void incrementDislikes() {
        dislikesCount.incrementAndGet();
    }

    public void decrementDislikes() {
        dislikesCount.decrementAndGet();
    }

    public void incrementViews() {
        viewsCount.incrementAndGet();
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }
}
