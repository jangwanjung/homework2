package org.example.homework2.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class UpdateScheduleRequestDto {

    @Setter
    private Long id;
    private String task;
    private String author_name;
    private String password;
}
