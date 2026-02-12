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
        Page<Schedule> schedules = scheduleRepository.findAll(pageable);

        // Page 객체는 map 메서드를 제공한다.
//        return schedules.map(schedule -> new GetAllScheduleWithPageResponse(schedule));
        // 람다 -> 메서드 참조
        return schedules.map(GetAllScheduleWithPageResponse::new);
    }

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
                () -> new ScheduleNotFoundException("없는 일정입니다.")
        );
        return new GetOneScheduleResponse(schedule);
    }

    @Transactional
    public UpdateScheduleResponse update(Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException("없는 일정입니다.")
        );
        // 수정시 비밀번호 일치한지 확인
        if (!schedule.getUser().getPassword().equals(request.getPassword())) {
            throw new LoginUnauthorizedException("비밀번호가 일치하지 않습니다.");
        }
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
