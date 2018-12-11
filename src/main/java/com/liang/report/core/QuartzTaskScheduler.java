package com.liang.report.core;

import com.liang.report.config.QuartzConfig;
import com.liang.report.job.QuartzLoadDataJob;
import com.liang.report.job.QuartzResendFailedJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author lianghaiyang
 * @date 2018/11/26
 */
@Component
@Slf4j
public class QuartzTaskScheduler {
    @Resource
    private QuartzConfig quartzConfig;
    @Resource
    private Scheduler scheduler;

    /**
     * 每次重启项目进行触发器监听
     */
    public void listenTrigger() {
        try {
            // 获取任务列表
            GroupMatcher<JobKey> groupMatcher = GroupMatcher.anyJobGroup();
            Set<JobKey> jobKeysSet = scheduler.getJobKeys(groupMatcher);
            List<JobKey> jobKeys = new ArrayList<>(jobKeysSet);
            // 获取触发器列表
            GroupMatcher<TriggerKey> triggerKeyGroupMatcher = GroupMatcher.anyTriggerGroup();
            Set<TriggerKey> triggerKeySet = scheduler.getTriggerKeys(triggerKeyGroupMatcher);
            ArrayList<TriggerKey> triggerKeys = new ArrayList<>(triggerKeySet);
            // 取消订阅触发器
            scheduler.unscheduleJobs(triggerKeys);
            // 删除所有任务
            scheduler.deleteJobs(jobKeys);
            // 开启任务
            this.loadDataTaskTrigger();
            this.resendFailedTaskTrigger();
        } catch (ObjectAlreadyExistsException e) {
            log.info("任务已经存在不需要再次添加");
        } catch (SchedulerException e) {
            log.error("定时任务出现问题");
            e.printStackTrace();
        }
    }

    /**
     * 上传需要再当天发送的数据
     */
    private void loadDataTaskTrigger() throws SchedulerException {
        //任务名称
        String taskName = QuartzLoadDataJob.class.getSimpleName();
        // 使用默认任务所属分组
        JobDetail jobDetail = JobBuilder.newJob(QuartzLoadDataJob.class).withIdentity(taskName).build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartzConfig.getCron());
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(taskName).withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    /**
     * 重新发送失败数据
     */
    private void resendFailedTaskTrigger() throws SchedulerException {
        //任务名称
        String taskName = QuartzResendFailedJob.class.getSimpleName();
        // 使用默认任务所属分组
        JobDetail jobDetail = JobBuilder.newJob(QuartzResendFailedJob.class).withIdentity(taskName).build();
        DailyTimeIntervalScheduleBuilder timeIntervalScheduleBuilder = DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule().withIntervalInMinutes(quartzConfig.getInterval());
        DailyTimeIntervalTrigger timeIntervalTrigger = TriggerBuilder.newTrigger()
                .withIdentity(taskName)
                .withSchedule(timeIntervalScheduleBuilder)
                .build();
        scheduler.scheduleJob(jobDetail, timeIntervalTrigger);
    }
}
