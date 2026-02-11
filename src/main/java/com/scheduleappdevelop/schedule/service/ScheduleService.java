package com.scheduleappdevelop.schedule.service;

import com.scheduleappdevelop.exception.ScheduleNotFoundException;
import com.scheduleappdevelop.exception.UserNotFoundException;
import com.scheduleappdevelop.schedule.dto.*;
import com.scheduleappdevelop.schedule.entity.Schedule;
import com.scheduleappdevelop.schedule.repository.ScheduleRepository;
import com.scheduleappdevelop.user.entity.User;
import com.scheduleappdevelop.user.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    // (POST 요청을 받은 Controller가 Service에게 넘겨서 실제로 로직이 실행되는 곳)

    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(
                () -> new UserNotFoundException("없는 유저입니다.")
        );
        Schedule schedule = new Schedule(
                user,
                request.getTitle(),
                request.getContent());
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new CreateScheduleResponse(savedSchedule);
    }

    @Transactional(readOnly = true)
    public List<GetAllScheduleResponse> findAll() {
        List<Schedule> schedules = scheduleRepository.findAll();
        List<GetAllScheduleResponse> dtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            GetAllScheduleResponse dto = new GetAllScheduleResponse(
                    schedule.getId(),
                    schedule.getUser().getId(),
                    schedule.getUser().getName(),
                    schedule.getTitle()
//                    schedule.getContent(),
//                    schedule.getCreatedAt(),
//                    schedule.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public GetOneScheduleResponse findOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException("없는 일정입니다.")
        );
        return new GetOneScheduleResponse(schedule);
    }

    @Transactional
    public UpdateScheduleResponse update(Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException("없는 일정입니다.")
        );
        schedule.update(request.getTitle(), request.getContent());
        return new UpdateScheduleResponse(schedule);
    }

    @Transactional
    public void deleteSchedule(Long scheduleId) {
        boolean existence = scheduleRepository.existsById(scheduleId);
        if (!existence) {
            throw new ScheduleNotFoundException("없는 일정입니다.");
        }
        scheduleRepository.deleteById(scheduleId);
    }
}
