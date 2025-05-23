package org.example.homework2.repository;

import org.example.homework2.dto.CreateScheduleResponseDto;
import org.example.homework2.dto.DeleteScheduleRequestDto;
import org.example.homework2.dto.ScheduleResponseDto;
import org.example.homework2.dto.UpdateScheduleRequestDto;
import org.example.homework2.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    CreateScheduleResponseDto saveSchedule(Schedule schedule);

    List <ScheduleResponseDto> findScheduleByUpdatedAtAndAuthorName(String updatedAt, String authorName);

    Optional<ScheduleResponseDto> findScheduleById(Long id);

    int updateSchedule(UpdateScheduleRequestDto dto);

    Optional<String> findPasswordById(Long id);

    int deleteSchedule(Long id);
}
