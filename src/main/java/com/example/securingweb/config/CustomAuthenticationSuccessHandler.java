package com.example.securingweb.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.securingweb.dto.ResponseDataDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 로그인 성공시 핸들러
 */
@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    /**
     * 로그인이 성공하고나서 로직
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        RequestCache requestCache = new HttpSessionRequestCache();
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        try {
            request.getSession().setAttribute("prevPage", savedRequest.getRedirectUrl());
        } catch (NullPointerException e){
            request.getSession().setAttribute("prevPage", "/");
        }


        ObjectMapper mapper = new ObjectMapper();    //JSON 변경용

        ResponseDataDto responseDataDto = new ResponseDataDto();
        responseDataDto.setCode(ResponseDataCode.SUCCESS);
        responseDataDto.setStatus(ResponseDataStatus.SUCCESS);

        String prevPage = request.getSession().getAttribute("prevPage").toString();    //이전 페이지 가져오기

        Map<String, String> items = new HashMap<String, String>();
        items.put("url", prevPage);    // 이전 페이지 저장
        responseDataDto.setItem(items);

        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print(mapper.writeValueAsString(responseDataDto));
        response.getWriter().flush();
    }
}
