package com.scheduleappdevelop.schedule.repository;

import com.scheduleappdevelop.schedule.dto.GetAllScheduleWithPageResponse;
import com.scheduleappdevelop.schedule.entity.Schedule;
import com.scheduleappdevelop.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("""
        select new com.scheduleappdevelop.schedule.dto.GetAllScheduleWithPageResponse(
            s.title, s.content, count(c.id), s.createdAt, s.modifiedAt, u.name)
            from Schedule s
            join s.user u
            left join com.scheduleappdevelop.comment.entity.Comment c
            on c.schedule = s
            group by s.id, u.id""")
    Page<GetAllScheduleWithPageResponse> findScheduleByPage(Pageable pageable);

    List<Schedule> user(User user);

    List<Schedule> findAllByUserId(Long userId);
}
