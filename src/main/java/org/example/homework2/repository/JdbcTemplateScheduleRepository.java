package org.example.homework2.repository;

import org.example.homework2.dto.CreateScheduleResponseDto;
import org.example.homework2.dto.ScheduleResponseDto;
import org.example.homework2.dto.UpdateScheduleRequestDto;
import org.example.homework2.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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

    @Override
    public List<ScheduleResponseDto> findScheduleByUpdatedAtAndAuthorName(String updatedAt, String authorName) {
        List<ScheduleResponseDto> result = jdbcTemplate.query("select * from schedule where date(updated_at) = ? or author_name = ? order by updated_at desc",
                scheduleMapper(), updatedAt, authorName);
        return result;
    }

    @Override
    public Optional<ScheduleResponseDto> findScheduleById(Long id) {
        List<ScheduleResponseDto> result = jdbcTemplate.query("select * from schedule where id = ?", scheduleMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public int updateSchedule(UpdateScheduleRequestDto dto) {
        return jdbcTemplate.update("update schedule set task = ?, author_name = ? where id = ?", dto.getTask(), dto.getAuthor_name(),dto.getId());
    }

    @Override
    public Optional<String> findPasswordById(Long id) {
        List<String> result = jdbcTemplate.query("select password from schedule where id = ?", passwordMapper(), id);
        return result.stream().findAny();
    }

    private RowMapper<String> passwordMapper() {
        return new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("password");
            }
        };
    }

    private RowMapper<ScheduleResponseDto> scheduleMapper() {
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("task"),
                        rs.getString("author_name"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );
            }
        };
    }


}
