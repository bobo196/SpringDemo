package ru.korshun.springdemo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import ru.korshun.springdemo.response.BaseResponse;
import ru.korshun.springdemo.utils.Functions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint
        implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException {

        Functions
                .getLogger(JwtAuthenticationEntryPoint.class)
                .error("Authorization error. Message - {}", e.getMessage());

        ObjectMapper mapper = new ObjectMapper();
        String responseMsg = mapper.writeValueAsString(
                new BaseResponse<>(HttpStatus.UNAUTHORIZED, e.getMessage(), null));
        httpServletResponse.getWriter().write(responseMsg);

    }
}
