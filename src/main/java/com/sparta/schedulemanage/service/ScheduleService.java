package com.sparta.schedulemanage.service;

import com.sparta.schedulemanage.dto.ScheduleRequestDto;
import com.sparta.schedulemanage.dto.ScheduleResponseDto;
import com.sparta.schedulemanage.entity.Schedule;
import com.sparta.schedulemanage.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {

        Schedule schedule = new Schedule(requestDto);
        // DB 저장
        scheduleRepository.save(schedule);

        return new ScheduleResponseDto(schedule);
    }
}
