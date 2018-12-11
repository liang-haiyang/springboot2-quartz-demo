package com.liang.report.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author lianghaiyang 2018/10/30 11:27
 */
@Component
@ConfigurationProperties(prefix = "app")
@Getter
@Setter
@ToString
@Slf4j
public class AppConfig {
    private List<String> allowOrigins;
}
