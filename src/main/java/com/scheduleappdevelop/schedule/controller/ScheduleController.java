package com.scheduleappdevelop.schedule.controller;

import com.scheduleappdevelop.exception.LoginUnauthorizedException;
import com.scheduleappdevelop.schedule.dto.*;
import com.scheduleappdevelop.schedule.entity.Schedule;
import com.scheduleappdevelop.schedule.service.ScheduleService;
import com.scheduleappdevelop.user.dto.GetAllUserResponse;
import com.scheduleappdevelop.user.dto.SessionUser;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
            @Valid @RequestBody CreateScheduleRequest request,
            @SessionAttribute(name = "login_user", required = false) SessionUser sessionUser
    ) {
        // 세션이 없으면(로그인 안 되었으면) 일정 생성 불가능
        if (sessionUser == null) {
            throw new LoginUnauthorizedException("로그인을 해주세요.");
        } // 세션이 있으면 (로그인 되었으면) 일정 생성
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(request, sessionUser.getUserId()));
    }

    // 페이징 조회
    @GetMapping("/schedulesPage")
    public ResponseEntity<Page<GetAllScheduleWithPageResponse>> getSchedules(
            // page = 0, size = 10이 디폴트 값
            @PageableDefault(size = 10, sort = "modifiedAt", direction = Sort.Direction.DESC)
            Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAllWithPage(pageable));
    }

    // 전체 조회
    @GetMapping("schedules")
    public ResponseEntity<List<GetAllScheduleResponse>> getAllSchedule() {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAll());
    }

    // 유저의 전체 일정 조회
    @GetMapping("/users/{userId}/schedules")
    public ResponseEntity<List<GetAllScheduleResponse>> getAllSchedulseByUser(
            @PathVariable Long userId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAllByUserId(userId));
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
            @Valid @PathVariable Long scheduleId,
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

// TODO: 유저의 모든 일정 조회할 수 있도록 구현하기. OK

// TODO: 스탠다드에서 배운 응답 dto 규격화? 해보기.