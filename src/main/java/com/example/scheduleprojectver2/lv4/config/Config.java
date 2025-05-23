package com.example.scheduleprojectver2.lv4.config;

import com.example.scheduleprojectver2.lv4.filter.LoginFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public FilterRegistrationBean loginFilter(){
        FilterRegistrationBean<Filter> RegistrationBean = new FilterRegistrationBean<>();
        RegistrationBean.setFilter(new LoginFilter());
        RegistrationBean.setOrder(1);
        RegistrationBean.addUrlPatterns("/*");
        return RegistrationBean;
    }
}
