package com.scheduleappdevelop.schedule.dto;

import lombok.Getter;

@Getter
public class UpdateScheduleRequest {

    private String userId;
    private String title;
    private String content;
}
