package com.scheduleappdevelop.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateScheduleRequest {

    private Long userId;
    @NotBlank(message = "제목을 입력해주세요.")
    @Size(max = 30, message = "제목은 30자 이내로 작성해주세요.")
    private  String title;
    @NotBlank(message = "내용을 입력해주세요.")
    private  String content;
}
