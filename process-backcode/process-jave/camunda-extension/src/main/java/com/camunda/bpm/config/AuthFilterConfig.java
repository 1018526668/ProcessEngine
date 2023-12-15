package com.camunda.bpm.config;

import org.camunda.bpm.engine.rest.security.auth.ProcessEngineAuthenticationFilter;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@Configuration
public class AuthFilterConfig implements ServletContextInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        FilterRegistration.Dynamic authFilter = servletContext.addFilter("camunda-auth", ProcessEngineAuthenticationFilter.class);
        authFilter.setAsyncSupported(true);
        authFilter.setInitParameter("authentication-provider","org.camunda.bpm.engine.rest.security.auth.impl.HttpBasicAuthenticationProvider");
//        客户端需要鉴权，比如外部任务的客户端，不能直接连接，需要一个身份
        authFilter.addMappingForUrlPatterns(null,true,"/engine-rest/*");
    }
}
