package com.example.shiyin.lanjie;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/hello","/loginByAdmin","/loginbyPassword","/loginByauto","/register","/sendsms","/forget",
                        "/question","/loginbySms","/loginbyUniver","/homepaint/*");
    }
}
