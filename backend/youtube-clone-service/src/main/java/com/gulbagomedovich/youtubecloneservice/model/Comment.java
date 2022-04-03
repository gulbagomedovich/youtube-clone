package com.gulbagomedovich.youtubecloneservice.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Comment {
    @Id
    @Setter(AccessLevel.NONE)
    private String id;
    private String text;
    private String userId;
    private int likesCount;
    private int dislikesCount;

    public Comment(String text, String userId, int likesCount, int dislikesCount) {
        this.text = text;
        this.userId = userId;
        this.likesCount = likesCount;
        this.dislikesCount = dislikesCount;
    }
}
