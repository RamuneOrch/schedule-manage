package com.sparta.schedulemanage.service;

import com.sparta.schedulemanage.dto.ScheduleRequestDto;
import com.sparta.schedulemanage.dto.ScheduleResponseDto;
import com.sparta.schedulemanage.entity.Schedule;
import com.sparta.schedulemanage.repository.ScheduleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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

    public List<ScheduleResponseDto> getSchedule() {
        return scheduleRepository.findAllByOrderByCreatedAtDesc().stream().map(ScheduleResponseDto::new).toList();
    }

    public ScheduleResponseDto getScheduleById(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 일정은 존재하지 않습니다.")
        );
        return new ScheduleResponseDto(schedule);
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto);
        Schedule beforeSchedule = scheduleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("선택한 일정은 존재하지 않습니다."));
        return validatePassword(schedule, beforeSchedule, requestDto);
    }

    public ScheduleResponseDto validatePassword(Schedule schedule, Schedule beforeSchedule, ScheduleRequestDto requestDto){
        if(schedule.getPassword().equals(beforeSchedule.getPassword())){
            beforeSchedule.update(requestDto);
            return new ScheduleResponseDto(beforeSchedule);
        }else{
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }
    }

    public String removeSchedule(Long id, ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto);
        Schedule beforeSchedule = scheduleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("선택한 일정은 존재하지 않습니다."));
        return validatePasswordDelete(schedule,beforeSchedule,requestDto);
    }

    public String validatePasswordDelete(Schedule schedule, Schedule beforeSchedule, ScheduleRequestDto requestDto){
        if(schedule.getPassword().equals(beforeSchedule.getPassword())){
            scheduleRepository.delete(beforeSchedule);
            return "삭제되었습니다.";
        }else{
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }
    }
}
