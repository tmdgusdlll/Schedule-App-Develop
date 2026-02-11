package com.scheduleappdevelop.comment.entity;

import com.scheduleappdevelop.schedule.entity.BaseEntity;
import com.scheduleappdevelop.schedule.entity.Schedule;
import com.scheduleappdevelop.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    private String content;

    // schedule과 연관관계 매핑
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    // user와도 연관관계 매핑
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Comment(String content, Schedule schedule, User user) {
        this.content = content;
        this.schedule = schedule;
        this.user = user;
    }
}
