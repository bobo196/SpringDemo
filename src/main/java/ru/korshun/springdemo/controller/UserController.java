package ru.korshun.springdemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.korshun.springdemo.response.BaseResponse;
import ru.korshun.springdemo.security.CurrentUser;
import ru.korshun.springdemo.security.JwtAuthenticationEntryPoint;
import ru.korshun.springdemo.security.UserPrincipal;
import ru.korshun.springdemo.utils.Functions;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/user/me")
    public BaseResponse getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        Functions
                .getLogger(JwtAuthenticationEntryPoint.class)
                .info("Query /api/user/me from {}",  currentUser.getEmail());
        return new BaseResponse<>(HttpStatus.OK, null, currentUser);
    }

}