package com.liang.report.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author lianghaiyang 2018/11/23 17:26
 */
@Getter
@Setter
@Configuration
public class QuartzConfig {
    @Value("${spring.quartz.cron}")
    private String cron;
    @Value("${spring.quartz.interval}")
    private Integer interval;
}
