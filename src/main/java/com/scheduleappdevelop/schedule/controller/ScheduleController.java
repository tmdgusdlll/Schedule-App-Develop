package com.scheduleappdevelop.schedule.controller;

import com.scheduleappdevelop.schedule.dto.CreateScheduleRequest;
import com.scheduleappdevelop.schedule.dto.CreateScheduleResponse;
import com.scheduleappdevelop.schedule.dto.GetAllScheduleResponse;
import com.scheduleappdevelop.schedule.dto.GetOneScheduleResponse;
import com.scheduleappdevelop.schedule.service.ScheduleService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // Lv.1 일정 CRUD
    // 생성
    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponse> createSchedule(
            @RequestBody CreateScheduleRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(request));
    }

    // 전체 조회
    @GetMapping("/schedules")
    public ResponseEntity<List<GetAllScheduleResponse>> getAllSchedule() {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAll());
    }

    // 단 건 조회
    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<GetOneScheduleResponse> getOneSchedule(
            @PathVariable Long scheduleId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findOne(scheduleId));
    }
}
