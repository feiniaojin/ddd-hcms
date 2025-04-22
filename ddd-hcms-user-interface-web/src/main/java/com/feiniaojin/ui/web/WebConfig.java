package com.feiniaojin.ui.web;

import com.feiniaojin.gracefulresponse.EnableGracefulResponse;
import com.feiniaojin.ui.web.annotation.RequestParamResolver;
import com.feiniaojin.ui.web.annotation.UrlRequestParamResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableGracefulResponse
public class WebConfig implements WebMvcConfigurer {

    private final UrlRequestParamResolver urlRequestParamResolver;

    @Autowired
    private RequestParamResolver requestParamResolver;



    public WebConfig(UrlRequestParamResolver urlRequestParamResolver) {
        this.urlRequestParamResolver = urlRequestParamResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        // 将自定义解析器添加到 Spring 的解析器列表中
        resolvers.add(requestParamResolver);
        resolvers.add(urlRequestParamResolver);
    }

}
