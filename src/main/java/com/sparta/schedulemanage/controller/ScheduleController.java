package com.sparta.schedulemanage.controller;

import com.sparta.schedulemanage.dto.ScheduleRequestDto;
import com.sparta.schedulemanage.dto.ScheduleResponseDto;
import com.sparta.schedulemanage.service.ScheduleService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }


    @PostMapping("/schedule")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.createSchedule(requestDto);
    }

    @GetMapping("/schedule")
    public List<ScheduleResponseDto> getSchedule() {
        return scheduleService.getSchedule();
    }


    @GetMapping("/schedule/{id}")
    public ScheduleResponseDto getScheduleById(@PathVariable Long id) {
        return scheduleService.getScheduleById(id);
    }


    @PutMapping("/schedule/{id}")
    public void updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        scheduleService.updateSchedule(id, requestDto);
    }

    @DeleteMapping("/schedule/{id}")
    public String removeSchedule(@PathVariable Long id,
            @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.removeSchedule(id, requestDto);
    }
}
