package com.web.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //TODO: check auth or replase it to oauth
        /*
            HttpEntity<Object> httpEntity = new HttpEntity<>(new Object(), request.getHeaders("Auth"));
            ResponseEntity<?> responseEntity = restTemplate.exchange("{host}/context/auth/checkAuth", HttpMethod.GET, httpEntity, ResponseEntity.class);
            if(responseEntity.getStatusCode().value() != 200) {
                throw new RuntimeException();
            }
         */
        filterChain.doFilter(request, response);
    }
}
