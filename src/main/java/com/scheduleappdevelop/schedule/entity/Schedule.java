package com.scheduleappdevelop.schedule.entity;

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
    private String author;
    private String title;
    private String content;

    public Schedule(String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public void update(String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }
}
