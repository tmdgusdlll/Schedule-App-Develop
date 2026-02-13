package com.scheduleappdevelop.user.entity;

import com.scheduleappdevelop.schedule.entity.BaseEntity;
import com.scheduleappdevelop.schedule.entity.Schedule;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.sql.ast.Clause;

import java.util.Optional;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE users SET is_deleted = true WHERE id = ?") // softdelete (삭제가 업데이트로)
@SQLRestriction("is_deleted = false")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    // Lv.3 회원가입 (비밀번호 추가) / 8글자 이상
    @Column(nullable = false)
    private String password;
    // softdelete 논리형 추가
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void update(String email) {
        this.email = email;
    }

    public void delete() {
        this.isDeleted = true;
    }
}
