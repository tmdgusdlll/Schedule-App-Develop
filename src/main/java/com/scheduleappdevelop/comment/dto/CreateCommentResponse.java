package com.scheduleappdevelop.comment.dto;

import com.scheduleappdevelop.comment.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateCommentResponse {

    private final Long scheduleId;
    private final Long commentId;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CreateCommentResponse(Comment comment) {
        this.scheduleId = comment.getSchedule().getId();
        this.commentId = comment.getId();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
