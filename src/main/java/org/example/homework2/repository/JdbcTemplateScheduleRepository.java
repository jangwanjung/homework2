package org.example.homework2.repository;

import org.example.homework2.dto.CreateScheduleResponseDto;
import org.example.homework2.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Repository
public class JdbcTemplateScheduleRepository implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public CreateScheduleResponseDto saveSchedule(Schedule schedule) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("schedule")
                .usingGeneratedKeyColumns("id")
                .usingColumns("task", "author_name", "password");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("task", schedule.getTask());
        parameters.put("author_name", schedule.getAuthor_name());
        parameters.put("password", schedule.getPassword());
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        System.out.println(key.longValue());
        return new CreateScheduleResponseDto(key.longValue(),
                schedule.getTask(),
                schedule.getAuthor_name());
    }
}
