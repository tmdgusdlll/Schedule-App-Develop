package com.scheduleappdevelop.comment.entity;

import com.scheduleappdevelop.schedule.entity.BaseEntity;
import com.scheduleappdevelop.schedule.entity.Schedule;
import com.scheduleappdevelop.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.Optional;

@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE comments SET is_deleted = true WHERE id = ?") // softdelete (삭제가 업데이트로)
@SQLRestriction("is_deleted = false")
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

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    public Comment(String content, Schedule schedule, User user) {
        this.content = content;
        this.schedule = schedule;
        this.user = user;
    }

    public void delete() {
        this.isDeleted = true;
    }
}
