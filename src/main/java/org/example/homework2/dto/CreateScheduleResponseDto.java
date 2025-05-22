package org.example.homework2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
public class CreateScheduleResponseDto {

    private Long id;
    private String task;
    private String author_name;

}
