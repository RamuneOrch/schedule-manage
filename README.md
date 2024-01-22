# 일정 api

## use case

![image](https://github.com/RamuneOrch/schedule-manage/assets/65538799/b8a31c1b-7916-4055-9cb1-2075105383dc)


## api 명세서

![image](https://github.com/RamuneOrch/schedule-manage/assets/65538799/b7e47e62-fc56-49b5-935c-70b35aa78e33)


## ERD

![image](https://github.com/RamuneOrch/schedule-manage/assets/65538799/6ddca627-ef7b-4ce0-8b74-9d7c0647f21a)

## 비밀번호에 대한 예외 처리

```
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SchedulePasswordErrorException extends RuntimeException{
    public SchedulePasswordErrorException(String message){
        super(message);
    }
}
```

- 위와 같이 비밀번호가 일치하지 않으면 응답으로 400을 보내주는 예외클래스 작성

```
public String validatePasswordDelete(Schedule schedule, Schedule beforeSchedule){
    if(schedule.getPassword().equals(beforeSchedule.getPassword())){
        scheduleRepository.delete(beforeSchedule);
        return "삭제되었습니다.";
    }else{
        throw new ScheduleNotFoundException("비밀번호가 일치하지 않습니다");
    }
}
```

- message로 "비밀번호가 일치하지 않습니다" 반환하기
