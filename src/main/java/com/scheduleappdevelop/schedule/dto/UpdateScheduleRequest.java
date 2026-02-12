package com.scheduleappdevelop.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateScheduleRequest {

    @NotBlank(message = "제목을 입력해주세요.")
    private  String title;
    @NotBlank(message = "내용을 입력해주세요.")
    private  String content;
    private String password;
}
