package com.scheduleappdevelop.schedule.service;

import com.scheduleappdevelop.exception.LoginUnauthorizedException;
import com.scheduleappdevelop.exception.ScheduleNotFoundException;
import com.scheduleappdevelop.exception.UserNotFoundException;
import com.scheduleappdevelop.schedule.dto.*;
import com.scheduleappdevelop.schedule.entity.Schedule;
import com.scheduleappdevelop.schedule.repository.ScheduleRepository;
import com.scheduleappdevelop.user.entity.User;
import com.scheduleappdevelop.user.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    // pagination
    @Transactional(readOnly = true)
    public Page<GetAllScheduleWithPageResponse> findAllWithPage(Pageable pageable) {
        return scheduleRepository.findScheduleByPage(pageable);
    }

    // (POST 요청을 받은 Controller가 Service에게 넘겨서 실제로 로직이 실행되는 곳)

    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("존재하지 않는 유저입니다.")
        );
        Schedule schedule = new Schedule(
                user,
                request.getTitle(),
                request.getContent());
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new CreateScheduleResponse(savedSchedule);
    }

    // TODO: stream으로 바꿔보기
    @Transactional(readOnly = true)
    public List<GetAllScheduleResponse> findAll() {
//        List<Schedule> schedules = scheduleRepository.findAll();
//        List<GetAllScheduleResponse> dtos = new ArrayList<>();
//        for (Schedule schedule : schedules) {
//            GetAllScheduleResponse dto = new GetAllScheduleResponse(
//                    schedule.getId(),
//                    schedule.getUser().getId(),
//                    schedule.getUser().getName(),
//                    schedule.getTitle()
//            );
//            dtos.add(dto);
//        }
//        return dtos;
        return scheduleRepository.findAll()
                .stream()
//                .map(schedule -> new GetAllScheduleResponse(schedule));
                .map(GetAllScheduleResponse::new) // 람다 -> 메서드 참조
                .toList();
    }

    @Transactional(readOnly = true)
    public GetOneScheduleResponse findOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException("존재하지 않는 일정입니다.")
        );
        return new GetOneScheduleResponse(schedule);
    }

    @Transactional
    public UpdateScheduleResponse update(Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException("존재하지 않는 일정입니다.")
        );
        schedule.update(request.getTitle(), request.getContent());
        return new UpdateScheduleResponse(schedule);
    }

    @Transactional
    public void deleteSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException("존재하지 않는 일정입니다.")
        );
        scheduleRepository.delete(schedule);
    }

    @Transactional(readOnly = true)
    public List<GetAllScheduleResponse> findAllByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("존재하지 않는 유저입니다.");
        }
        return scheduleRepository.findAllByUserId(userId)
                .stream()
                .map(GetAllScheduleResponse::new)
                .toList();
    }
}
