package org.example.homework2.service;

import org.example.homework2.dto.*;
import org.example.homework2.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {

    CreateScheduleResponseDto saveSchedule(CreateScheduleRequestDto dto);

    List<ScheduleResponseDto> findScheduleByUpdatedAtAndAuthorName(String updatedAt, String authorName);

    ScheduleResponseDto findScheduleById(Long id);

    ScheduleResponseDto updateSchedule(UpdateScheduleRequestDto dto);

    void deleteSchedule(DeleteScheduleRequestDto dto);
}
