package com.example.project.filters;


import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Order(1)
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest= (HttpServletRequest) request;
        System.out.println("Received a new Request");
        System.out.println("Request URI= "+httpRequest.getRequestURI());
        System.out.println("Request method= "+ httpRequest.getMethod());
        System.out.println("Request URL= "+ httpRequest.getRequestURL());

        chain.doFilter(request, response); //move to next filter
    }
}
