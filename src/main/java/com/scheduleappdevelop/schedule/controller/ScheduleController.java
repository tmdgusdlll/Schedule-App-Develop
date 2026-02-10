package com.scheduleappdevelop.schedule.controller;

import com.scheduleappdevelop.schedule.service.ScheduleService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
}
