package com.scheduleappdevelop.comment.repository;

import com.scheduleappdevelop.comment.dto.GetCommentResponse;
import com.scheduleappdevelop.comment.entity.Comment;
import com.scheduleappdevelop.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> user(User user);

    List<Comment> findAllByUserId(Long userId);

    List<Comment> findAllCommentsByScheduleId(Long scheduleId);
}
