package com.scheduleappdevelop.schedule.service;

import com.scheduleappdevelop.schedule.dto.CreateScheduleRequest;
import com.scheduleappdevelop.schedule.dto.CreateScheduleResponse;
import com.scheduleappdevelop.schedule.dto.GetAllScheduleResponse;
import com.scheduleappdevelop.schedule.dto.GetOneScheduleResponse;
import com.scheduleappdevelop.schedule.entity.Schedule;
import com.scheduleappdevelop.schedule.repository.ScheduleRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // 생성 (POST 요청을 받은 Controller가 Service에게 넘겨서 실제로 로직이 실행되는 곳)
    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(
                request.getAuthor(),
                request.getTitle(),
                request.getContent());
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new CreateScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getAuthor(),
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<GetAllScheduleResponse> findAll() {
        List<Schedule> schedules = scheduleRepository.findAll();
        List<GetAllScheduleResponse> dtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            GetAllScheduleResponse dto = new GetAllScheduleResponse(
                    schedule.getId(),
                    schedule.getAuthor(),
                    schedule.getTitle()
//                    schedule.getContent(),
//                    schedule.getCreatedAt(),
//                    schedule.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    public GetOneScheduleResponse findOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다.")
        );
        return new GetOneScheduleResponse(
                schedule.getId(),
                schedule.getAuthor(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }
}
