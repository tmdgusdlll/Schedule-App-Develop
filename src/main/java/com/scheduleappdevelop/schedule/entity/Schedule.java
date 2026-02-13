package com.scheduleappdevelop.schedule.entity;

import com.scheduleappdevelop.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE schedules SET is_deleted = true WHERE id = ?") // softdelete (삭제가 업데이트로)
@SQLRestriction("is_deleted = false")
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String title;
    @Column(length = 200, nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false) // 일정은 유저없이는 존재할 수 없다.
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 유저 고유 식별자 가짐.

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    public Schedule(User user, String title, String content) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void delete() {
        this.isDeleted = true;
    }
}
