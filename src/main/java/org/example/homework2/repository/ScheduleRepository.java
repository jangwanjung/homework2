package org.example.homework2.repository;

import org.example.homework2.dto.CreateScheduleResponseDto;
import org.example.homework2.dto.ScheduleResponseDto;
import org.example.homework2.entity.Schedule;

public interface ScheduleRepository {

    CreateScheduleResponseDto saveSchedule(Schedule schedule);
}
