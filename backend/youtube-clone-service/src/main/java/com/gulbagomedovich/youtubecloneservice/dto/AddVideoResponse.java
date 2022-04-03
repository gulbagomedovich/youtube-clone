package com.gulbagomedovich.youtubecloneservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gulbagomedovich.youtubecloneservice.model.VideoStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Getter
@ToString
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddVideoResponse {
    private String videoId;
    private String videoTitle;
    private String videoUrl;
    private VideoStatus videoStatus;
}
