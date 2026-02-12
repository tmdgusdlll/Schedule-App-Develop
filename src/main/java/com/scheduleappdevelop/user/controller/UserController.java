package com.scheduleappdevelop.user.controller;

import com.scheduleappdevelop.user.dto.*;
import com.scheduleappdevelop.user.entity.User;
import com.scheduleappdevelop.user.repository.UserRepository;
import com.scheduleappdevelop.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.hibernate.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // Lv.2 유저 CRUD
    // 생성 => 회원가입
    @PostMapping("/register")
    public ResponseEntity<CreateUserResponse> register(
            @Valid @RequestBody CreateUserRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(request));
    }

    // 로그인 (이메일 + 비밀번호)
    @PostMapping("/login")
    public ResponseEntity<Void> login(
            @Valid @RequestBody LoginRequest request, HttpSession session
    ) {
        SessionUser sessionUser = userService.login(request);
        session.setAttribute("login_user", sessionUser);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 전체조회
    @GetMapping("/users")
    public ResponseEntity<List<GetAllUserResponse>> getAllUser() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    // 단건조회
    @GetMapping("/users/{userId}")
    public ResponseEntity<GetOneUserResponse> getOneUser(
            @PathVariable Long userId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findOne(userId));
    }

    // 수정
    @PatchMapping("/users/{userId}")
    public ResponseEntity<UpdateUserResponse> updateUser(
            @Valid @PathVariable Long userId,
            @RequestBody UpdateUserRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(userId, request));
    }

    // 삭제
    // TODO: 게시글이나 댓글을 단 유저는 삭제가 안 됨. cascade??, soft delete 해보자
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long userId
    ) {
        userService.delete(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
