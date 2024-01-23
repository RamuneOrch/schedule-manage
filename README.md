# 일정 api

## use case

![image](https://github.com/RamuneOrch/schedule-manage/assets/65538799/b8a31c1b-7916-4055-9cb1-2075105383dc)


## api 명세서

- 일정 작성 ( POST )
- 일정을 작성합니다.

![image](https://github.com/RamuneOrch/schedule-manage/assets/65538799/fee73ce2-27e1-4d48-b619-a0678554a06a)

- 선택한 일정 조회 ( GET )
- uri parameter로 넘어온 값을 기준으로 일정을 조회합니다.

![image](https://github.com/RamuneOrch/schedule-manage/assets/65538799/88fccfd2-ebd0-45ef-8701-552ee94f8e78)

- 전체 일정 조회 ( GET )
- 작성된 전체 일정을 조회합니다.

![image](https://github.com/RamuneOrch/schedule-manage/assets/65538799/e67c156f-2cdd-4506-b563-c2e0b34a03aa)

- 일정 수정 ( PUT )
- uri parameter로 넘어온 값과 request 값으로 선택된 일정을 수정합니다.

![image](https://github.com/RamuneOrch/schedule-manage/assets/65538799/a35d135c-7e2e-4f07-a785-9ead13966026)

- 선택한 일정 삭제 ( DELETE )
- uri parameter로 넘어온 값을 기준으로 일정을 삭제합니다.

![image](https://github.com/RamuneOrch/schedule-manage/assets/65538799/5ca28fa2-c423-467d-8dba-f60f38dfbec4)



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
