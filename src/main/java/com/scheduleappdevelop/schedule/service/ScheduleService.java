package com.scheduleappdevelop.schedule.service;

import com.scheduleappdevelop.schedule.repository.ScheduleRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
}
