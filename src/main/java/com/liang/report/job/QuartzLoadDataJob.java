package com.liang.report.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author lianghaiyang
 * @date 2018/11/26
 */
@Component
@Slf4j
public class QuartzLoadDataJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) {
        // 获取当前的日期
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        log.info("开始执行QuartzLoadDataJob: 执行日期..." + today);
    }
}
