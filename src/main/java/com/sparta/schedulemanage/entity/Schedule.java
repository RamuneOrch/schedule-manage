package com.sparta.schedulemanage.entity;

import com.sparta.schedulemanage.dto.ScheduleRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "schedule")
public class Schedule extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="contents")
    private String contents;

    @Column(name = "manager")
    private String manager;

    @Column(name = "password", nullable = false)
    private String password;

    public Schedule(ScheduleRequestDto scheduleRequestDto){
        this.title = scheduleRequestDto.getTitle();
        this.contents = scheduleRequestDto.getContents();
        this.manager = scheduleRequestDto.getManager();
        this.password = scheduleRequestDto.getPassword();
    }

    public void update(ScheduleRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.manager = requestDto.getManager();
    }
}
