package org.example.homework2.service;

import org.example.homework2.dto.*;
import org.example.homework2.entity.Schedule;
import org.example.homework2.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto saveSchedule(CreateScheduleRequestDto dto) {

        Schedule schedule = new Schedule(dto.getTask(),dto.getAuthor_name(),dto.getPassword());
        return scheduleRepository.saveSchedule(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findScheduleByUpdatedAtAndAuthorName(String updatedAt, String authorName) {
        return scheduleRepository.findScheduleByUpdatedAtAndAuthorName(updatedAt, authorName);
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {

        return scheduleRepository.findScheduleById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public ScheduleResponseDto updateSchedule(UpdateScheduleRequestDto dto) {

        String password = scheduleRepository.findPasswordById(dto.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (!password.equals(dto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        int updatedRow = scheduleRepository.updateSchedule(dto);

        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = "+ dto.getId());
        }

        return scheduleRepository.findScheduleById(dto.getId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void deleteSchedule(DeleteScheduleRequestDto dto) {

        String password = scheduleRepository.findPasswordById(dto.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (!password.equals(dto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        int deletedRow = scheduleRepository.deleteSchedule(dto.getId());

        if (deletedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = "+ dto.getId());
        }
    }
}
