package com.sparta.schedulemanage.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SchedulePasswordErrorException extends RuntimeException{
    public SchedulePasswordErrorException(String message){
        super(message);
    }
}
