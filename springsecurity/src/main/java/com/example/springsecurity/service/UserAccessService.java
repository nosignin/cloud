package com.example.springsecurity.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface UserAccessService {
    boolean pass(HttpServletRequest request, Authentication authentication);
}
