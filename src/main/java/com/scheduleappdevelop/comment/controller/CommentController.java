package com.scheduleappdevelop.comment.controller;

import com.scheduleappdevelop.comment.dto.CreateCommentRequest;
import com.scheduleappdevelop.comment.dto.CreateCommentResponse;
import com.scheduleappdevelop.comment.dto.GetCommentResponse;
import com.scheduleappdevelop.comment.service.CommentService;
import com.scheduleappdevelop.user.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;


    // 댓글 생성(저장)
    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<CreateCommentResponse> create(
            @PathVariable Long scheduleId,
            @RequestBody CreateCommentRequest request,
            @SessionAttribute(name = "login_user") SessionUser sessionUser
            ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commentService.save(scheduleId, sessionUser.getUserId(), request));
    }

    // 댓글 전체 조회
    @GetMapping("/comments")
    public ResponseEntity<List<GetCommentResponse>> getAllComments() {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.findAll());
    }

    // TODO: 한 일정의 모든 댓글 조회 만들어보기.ok
    @GetMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<List<GetCommentResponse>> getAllComments(
            @PathVariable Long scheduleId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.findAllCommentBySchedule(scheduleId));
    }
}
