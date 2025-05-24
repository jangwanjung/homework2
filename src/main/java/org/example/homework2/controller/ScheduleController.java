package org.example.homework2.controller;

import org.apache.coyote.Response;
import org.example.homework2.dto.*;
import org.example.homework2.entity.Schedule;
import org.example.homework2.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody CreateScheduleRequestDto dto) {

        return new ResponseEntity<>(scheduleService.saveSchedule(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedules(
            @RequestParam(required = false) String updated_at,
            @RequestParam(required = false) String author_name) {
        return new ResponseEntity<>(scheduleService.findScheduleByUpdatedAtAndAuthorName(updated_at,author_name), HttpStatus.OK);

    }

    @GetMapping(params = "id")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@RequestParam Long id) {

        return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id, @RequestBody UpdateScheduleRequestDto dto) {
        dto.setId(id);
        return new ResponseEntity<>(scheduleService.updateSchedule(dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id, @RequestBody DeleteScheduleRequestDto dto) {
        dto.setId(id);
        scheduleService.deleteSchedule(dto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
