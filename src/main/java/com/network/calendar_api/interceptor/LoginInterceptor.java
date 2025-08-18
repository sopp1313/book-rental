package com.network.calendar_api.interceptor;

import com.network.calendar_api.annotation.LoginRequired;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        LoginRequired loginrequired = handlerMethod.getMethodAnnotation(LoginRequired.class);

        if(loginrequired == null) {
            return true;
        }

        HttpSession session = request.getSession(false);
        if(session == null||session.getAttribute("loginId") == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("You are not logged in");
            response.setContentType("application/json");
            return false;
        }

        return true;
    }
}
