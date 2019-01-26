package com.example.demo.common.auth;


import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.common.HttpCode;
import com.example.demo.common.HttpException;
import com.example.demo.common.HttpResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Order(1)
@WebFilter(filterName = "ShiroExceptionFilter",urlPatterns = {"/*"})
public class ShiroExceptionFilter extends OncePerRequestFilter {

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (Exception e) {

            // 自定义异常的类，用户返回给客户端相应的JSON格式的信息
            response.setContentType("application/json; charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            // 如果不是自定义的异常默认是shiro认证失败的异常
            String rspJson=(e instanceof HttpException)?convertObjectToJson(HttpResponse.error((HttpException)e)):
            	convertObjectToJson(HttpResponse.error(new HttpException(HttpCode.AUTH_FAIL)));
            
            OutputStream out = response.getOutputStream();
            out.write(rspJson.getBytes("UTF-8"));
            out.flush();

            
        }
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

}
