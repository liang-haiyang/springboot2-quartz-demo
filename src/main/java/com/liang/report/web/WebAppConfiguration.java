package com.liang.report.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

/**
 * @author  lianghaiyang 2018/11/1 14:52
 */
@Configuration
public class WebAppConfiguration extends WebMvcConfigurationSupport {

    /**
     * 把我们的拦截器注入为bean
     */
    @Resource
    private IPInterceptor ipInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则, 这里拦截全部链接
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(ipInterceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
