package org.example.homework2.service;

import org.example.homework2.dto.CreateScheduleRequestDto;
import org.example.homework2.dto.CreateScheduleResponseDto;
import org.example.homework2.dto.ScheduleResponseDto;

public interface ScheduleService {

    CreateScheduleResponseDto saveSchedule(CreateScheduleRequestDto dto);
}
