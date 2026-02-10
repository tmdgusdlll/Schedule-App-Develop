package com.scheduleappdevelop.user.controller;

import com.scheduleappdevelop.user.dto.CreateUserRequest;
import com.scheduleappdevelop.user.dto.CreateUserResponse;
import com.scheduleappdevelop.user.dto.GetAllUserResponse;
import com.scheduleappdevelop.user.dto.GetOneUserResponse;
import com.scheduleappdevelop.user.repository.UserRepository;
import com.scheduleappdevelop.user.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // Lv.2 유저 CRUD
    // 생성
    @PostMapping("/users")
    public ResponseEntity<CreateUserResponse> create(
            @RequestBody CreateUserRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(request));
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
}
