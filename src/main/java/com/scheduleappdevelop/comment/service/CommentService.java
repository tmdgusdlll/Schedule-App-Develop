package com.scheduleappdevelop.comment.service;

import com.scheduleappdevelop.comment.dto.CreateCommentRequest;
import com.scheduleappdevelop.comment.dto.CreateCommentResponse;
import com.scheduleappdevelop.comment.dto.GetCommentResponse;
import com.scheduleappdevelop.comment.entity.Comment;
import com.scheduleappdevelop.comment.repository.CommentRepository;
import com.scheduleappdevelop.exception.ScheduleNotFoundException;
import com.scheduleappdevelop.exception.UserNotFoundException;
import com.scheduleappdevelop.schedule.entity.Schedule;
import com.scheduleappdevelop.schedule.repository.ScheduleRepository;
import com.scheduleappdevelop.user.entity.User;
import com.scheduleappdevelop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public CreateCommentResponse save(Long scheduleId, Long userId, CreateCommentRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException("없는 일정입니다.")
        );
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("없는 유저입니다.")
        );

        Comment comment = new Comment(
                request.getContent(),
                schedule,
                user
        );
        Comment savedComment = commentRepository.save(comment);
        return new CreateCommentResponse(savedComment);
    }

    public List<GetCommentResponse> findAll() {
        List<Comment> comments = commentRepository.findAll();
        List<GetCommentResponse> dtos = new ArrayList<>();

        // TODO: stream 으로 리팩토링 해보기
        for (Comment comment : comments) {
            GetCommentResponse dto = new GetCommentResponse(comment);
            dtos.add(dto);
        }
        return dtos;
    }
}
