package com.scheduleappdevelop.schedule.dto;

import lombok.Getter;

@Getter
public class UpdateScheduleRequest {

    private String author;
    private String title;
    private String content;
}
