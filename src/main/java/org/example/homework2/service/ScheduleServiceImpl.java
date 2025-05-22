package org.example.homework2.service;

import org.example.homework2.dto.CreateScheduleRequestDto;
import org.example.homework2.dto.CreateScheduleResponseDto;
import org.example.homework2.dto.ScheduleResponseDto;
import org.example.homework2.entity.Schedule;
import org.example.homework2.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public CreateScheduleResponseDto saveSchedule(CreateScheduleRequestDto dto) {

        Schedule schedule = new Schedule(dto.getTask(),dto.getAuthor_name(),dto.getPassword());
        return scheduleRepository.saveSchedule(schedule);
    }
}
