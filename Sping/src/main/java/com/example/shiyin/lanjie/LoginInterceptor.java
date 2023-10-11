package com.example.shiyin.lanjie;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object token = request.getSession().getAttribute("token");
        MakeToken makeToken = new MakeToken();
        if(token==null){
            return false;
        }else if(makeToken.verify(token.toString())){
            return false;
        }else{
            return true;
        }
    }

}
