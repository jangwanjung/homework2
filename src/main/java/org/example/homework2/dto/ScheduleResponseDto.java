package org.example.homework2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
public class ScheduleResponseDto {

    private Long id;
    private String task;
    private String author_name;
    private Timestamp created_at;
    private Timestamp updated_at;
}
