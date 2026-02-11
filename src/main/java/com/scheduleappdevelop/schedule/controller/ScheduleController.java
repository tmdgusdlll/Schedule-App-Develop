package com.scheduleappdevelop.schedule.controller;

import com.scheduleappdevelop.schedule.dto.*;
import com.scheduleappdevelop.schedule.service.ScheduleService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // Lv.1 일정 CRUD
    // TODO: URL을 자세히 적고 dto에서 경로를 알려주는 게 더 나은가..?
    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponse> createSchedule(
            @RequestBody CreateScheduleRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(request));
    }

    // 전체 조회
    @GetMapping("schedules")
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

    // 수정 (업데이트)
    @PatchMapping("/schedules/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponse> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody UpdateScheduleRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.update(scheduleId, request));
    }

    // 삭제
    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long scheduleId
    ) {
        scheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

// TODO: 유저의 모든 일정 조회할 수 있도록 구현하기.