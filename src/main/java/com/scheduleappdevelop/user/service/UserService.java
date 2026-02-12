package com.scheduleappdevelop.user.service;

import com.scheduleappdevelop.config.PasswordEncoder;
import com.scheduleappdevelop.exception.AlreadyExistEmailException;
import com.scheduleappdevelop.exception.LoginUnauthorizedException;
import com.scheduleappdevelop.exception.UserNotFoundException;
import com.scheduleappdevelop.user.dto.*;
import com.scheduleappdevelop.user.entity.User;
import com.scheduleappdevelop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // PasswordEncoder 주입
    private final PasswordEncoder passwordEncoder;

    // TODO: 로그인구현하면서 생성이 회원가입으로 바뀜. 따라서 리팩토링 필요/ 이미 존재하는 (회원)이메일인지 확인 할 것
    @Transactional
    public CreateUserResponse register(CreateUserRequest request) {
        // 이미 존재하는 회원인지 이메일로 확인
        boolean existence = userRepository.existsByEmail(request.getEmail());
        if (existence) {
            throw new AlreadyExistEmailException("이미 존재하는 이메일입니다.");
        }
        // PasswordEncode 호출, 암호화하기
        String encodePassword = passwordEncoder.encode(request.getPassword());
        User user = new User(request.getName(), request.getEmail(), encodePassword);
        User savedUser = userRepository.save(user);
        return new CreateUserResponse(savedUser);
    }

    // 로그인 로직
    @Transactional(readOnly = true)
    public SessionUser login(LoginRequest request) {
        // 이메일로 유효한지
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new LoginUnauthorizedException("유효하지 않은 이메일입니다."));
                // 비밀번호 대조
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new LoginUnauthorizedException("비밀번호가 일치하지 않습니다.");
        }
        return new SessionUser(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    @Transactional(readOnly = true)
    public List<GetAllUserResponse> findAll() {
//        List<User> users = userRepository.findAll();
//        List<GetAllUserResponse> dtos = new ArrayList<>();
//        for (User user : users) {
//            GetAllUserResponse dto = new GetAllUserResponse(user);
//            dtos.add(dto);
//        }
//        return dtos;
        return userRepository.findAll()
                .stream()
//                .map(user -> new GetAllUserResponse(user))
                .map(GetAllUserResponse::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public GetOneUserResponse findOne(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("없는 유저입니다.")
        );
        return new GetOneUserResponse(user);
    }

    @Transactional
    public UpdateUserResponse update(Long userId, UpdateUserRequest request) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("없는 유저입니다.")
        );
        user.update(request.getName(), request.getEmail());
        return new UpdateUserResponse(user.getId(),user.getName(),user.getEmail());
    }

    @Transactional
    public void delete(Long userId) {
        boolean existence = userRepository.existsById(userId);
        if (!existence) {
            throw new UserNotFoundException("없는 유저입니다.");
        }
        userRepository.deleteById(userId);
    }
}


