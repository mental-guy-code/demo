package com.example.demo.config;

import com.example.demo.common.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {


    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {;
        response.setStatus(HttpServletResponse.SC_OK);
        Map<String, Object> data = new HashMap<>();
        data.put("code", 200);
        data.put("message", "Login successful");
//        data.put("message", "Login successful");
        data.put("details", authentication.getDetails()); // 或者添加其他你希望返回的信息
        response.getOutputStream().println(objectMapper.writeValueAsString(data));
    }
}
