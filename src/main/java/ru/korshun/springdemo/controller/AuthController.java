package ru.korshun.springdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.korshun.springdemo.repository.UserRepository;
import ru.korshun.springdemo.request.LoginRequest;
import ru.korshun.springdemo.response.BaseResponse;
import ru.korshun.springdemo.response.data.LoginData;
import ru.korshun.springdemo.security.JwtAuthenticationEntryPoint;
import ru.korshun.springdemo.security.JwtTokenProvider;
import ru.korshun.springdemo.utils.Functions;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public BaseResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Functions
                .getLogger(JwtAuthenticationEntryPoint.class)
                .info("Query /auth/signin with login {}, and pass {}",
                        loginRequest.getLogin(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getLogin(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Functions
                .getLogger(JwtAuthenticationEntryPoint.class)
                .info("User {} login successfully", loginRequest.getLogin());

        return new BaseResponse<>(HttpStatus.OK, null,
                new LoginData(tokenProvider.generateToken(authentication)));
    }

}