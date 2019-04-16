package ru.korshun.springdemo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class Functions {

    public static int getHttpCodeFromDesc(HttpStatus status) {
        String error = status.toString();
        HttpStatus httpStatus = HttpStatus.valueOf(error.substring(error.indexOf(" ") + 1));
        return httpStatus.value();
    }

    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

}
