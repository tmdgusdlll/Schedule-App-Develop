package com.scheduleappdevelop.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

// POST로 들어온 요청에 대한 응답 dto
@Getter
public class CreateScheduleResponse {

    private final Long id;
    private final String author;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CreateScheduleResponse(Long id, String author, String title, String content,
                                  LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
