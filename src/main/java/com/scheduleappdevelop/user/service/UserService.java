package com.scheduleappdevelop.user.service;

import com.scheduleappdevelop.schedule.entity.Schedule;
import com.scheduleappdevelop.schedule.repository.ScheduleRepository;
import com.scheduleappdevelop.user.dto.CreateUserRequest;
import com.scheduleappdevelop.user.dto.CreateUserResponse;
import com.scheduleappdevelop.user.dto.GetAllUserResponse;
import com.scheduleappdevelop.user.dto.GetOneUserResponse;
import com.scheduleappdevelop.user.entity.User;
import com.scheduleappdevelop.user.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public CreateUserResponse save(CreateUserRequest request) {
        User user = new User(request.getName(), request.getEmail());
        User savedUser = userRepository.save(user);
        return new CreateUserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getCreatedAt(),
                savedUser.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<GetAllUserResponse> findAll() {
        List<User> users = userRepository.findAll();
        List<GetAllUserResponse> dtos = new ArrayList<>();
        for (User user : users) {
            GetAllUserResponse dto = new GetAllUserResponse(
                    user.getId(),
                    user.getName()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public GetOneUserResponse findOne(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("없는 유저입니다.")
        );
        return new GetOneUserResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}


