package com.scheduleappdevelop.comment.dto;

import com.scheduleappdevelop.comment.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetCommentResponse {


    private final Long commentId;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public GetCommentResponse(Comment comment) {
        this.commentId = comment.getId();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
