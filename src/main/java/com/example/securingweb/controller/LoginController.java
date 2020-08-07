package com.example.securingweb.controller;

import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginForm(HttpServletRequest request, HttpServletResponse response){
        RequestCache requestCache = new HttpSessionRequestCache();
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        try {
            request.getSession().setAttribute("prevPage", savedRequest.getRedirectUrl());
        } catch (NullPointerException e){
            request.getSession().setAttribute("prevPage", "/");
        }
        return "/login";
    }

}
