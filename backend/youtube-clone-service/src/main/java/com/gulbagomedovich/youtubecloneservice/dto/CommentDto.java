package com.gulbagomedovich.youtubecloneservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Getter
@ToString
@Builder
@Jacksonized
public class CommentDto {
    private String text;
    private String userId;
}
