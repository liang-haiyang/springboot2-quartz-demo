package com.liang.report.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

/**
 * @author lianghaiyang
 * @date 2018/11/23
 */
@Slf4j
@Component
public class QuartzResendFailedJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) {
        log.info("开始执行QuartzResendFailedJob: 查找发送失败数据..." + LocalDateTime.now());
    }
}
