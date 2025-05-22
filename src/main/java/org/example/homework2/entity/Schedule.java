package org.example.homework2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
public class Schedule {

    private Long id;
    private String task;
    private String author_name;
    private String password;
    private Timestamp created_at;
    private Timestamp updated_at;

    public Schedule(String task, String author_name, String password){
        this.task = task;
        this.author_name = author_name;
        this.password = password;
    }
}
