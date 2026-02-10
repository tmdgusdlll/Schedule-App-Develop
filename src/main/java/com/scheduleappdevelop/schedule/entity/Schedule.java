package com.scheduleappdevelop.schedule.entity;

import com.scheduleappdevelop.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private String author;
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Schedule(User user, String title, String content) {
//        this.author = author;
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void update(String title, String content) {
//        this.author = author;
        this.title = title;
        this.content = content;
    }
}
