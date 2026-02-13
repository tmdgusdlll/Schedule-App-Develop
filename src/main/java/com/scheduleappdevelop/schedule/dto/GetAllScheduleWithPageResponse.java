package com.scheduleappdevelop.schedule.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.scheduleappdevelop.comment.entity.Comment;
import com.scheduleappdevelop.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonPropertyOrder({"title", "content", "commentCount", "createdAt", "modifiedAt", "username" })
public class GetAllScheduleWithPageResponse {

    // TODO: 댓글개수 구현 진행중... 어떻게 해야하지..
    private final String title;
    private final String content;
    private final Long commentCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final String username;

    public GetAllScheduleWithPageResponse(String title, String content, Long commentCount,
                                          LocalDateTime createdAt, LocalDateTime modifiedAt, String username) {
        this.title = title;
        this.content = content;
        this.commentCount = commentCount;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.username = username;
    }
}
