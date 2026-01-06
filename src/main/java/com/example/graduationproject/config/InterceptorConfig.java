package com.example.graduationproject.config;

import com.example.graduationproject.Interceptor.JWTInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
//    @Autowired
//    private HttpInterceptor httpInterceptor;
    @Autowired
    private JWTInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(httpInterceptor).addPathPatterns("/**");

        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")//对所有路径进行拦截
                .excludePathPatterns("/menu/login/status","/product/**","/menu/rgin");//这些路径放行

    }


}

