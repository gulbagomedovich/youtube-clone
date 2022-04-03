package com.gulbagomedovich.youtubecloneservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gulbagomedovich.youtubecloneservice.model.VideoStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.util.Set;

@Getter
@ToString
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateVideoRequest {
    private String videoTitle;
    private String videoDescription;
    private Set<String> videoTags;
    private VideoStatus videoStatus;
}
