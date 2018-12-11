package com.liang.report.core;

import org.flywaydb.core.api.callback.Callback;
import org.flywaydb.core.api.callback.Context;
import org.flywaydb.core.api.callback.Event;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author lianghaiyang 2018/12/3 15:47
 * flyway migrate 之后进行
 */
@Component
public class QuartzInitializer implements Callback {
    private static final String AFTER_MIGRATE = "afterMigrate";
    @Resource
    private QuartzTaskScheduler quartzTaskScheduler;

    @Override
    public boolean supports(Event event, Context context) {
        return true;
    }

    @Override
    public boolean canHandleInTransaction(Event event, Context context) {
        return true;
    }


    @Override
    public void handle(Event event, Context context) {
        String eventId = event.getId();
        switch (eventId) {
            case AFTER_MIGRATE:
                quartzTaskScheduler.listenTrigger();
                break;
            default:
                break;
        }
    }
}