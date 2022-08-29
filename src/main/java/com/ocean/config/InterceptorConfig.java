package com.ocean.config;

import com.ocean.config.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private static final String FILE_PATH = "/usr/springboot/files/**";

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/**")     // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
                .excludePathPatterns("/user/login","/user/register","/**/import","/**/export","/**/file/**",FILE_PATH);

        //TODO 文件可上传存储，但是无法下载

        //注册TestInterceptor拦截器
//        InterceptorRegistration registration = registry.addInterceptor(jwtInterceptor());
//        registration.addPathPatterns("/**");                      //添加拦截路径
//        registration.excludePathPatterns(                         //添加不拦截路径
//            "/**/*.html",            //html静态资源
//            "/**/*.js",              //js静态资源
//            "/**/*.css",             //css静态资源
//            "/**/*.woff",
//            "/**/*.ttf",
//            "/swagger-ui.html"
//        );
    }

    @Bean
    public JWTInterceptor jwtInterceptor() {
        return new JWTInterceptor();
    }

}
