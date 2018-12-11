package com.liang.report;

import com.liang.report.core.ApplicationContextHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author lianghaiyang 2018/10/30 11:27
 */
@EnableScheduling
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class QuartzApplication {

    @Resource
    private KafkaProducerListener kafkaProducerListener;
    @Resource
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(QuartzApplication.class, args);
    }

    @PostConstruct
    public void listen() {
        ApplicationContextHelper.setApplicationContext(applicationContext);
        KafkaMsgBackupDispatcher dispatcher = new KafkaMsgBackupDispatcher(kafkaProducerListener.getBlockingDeque());
        dispatcher.setName("BackupData");
        dispatcher.start();
    }
}
