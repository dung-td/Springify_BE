package com.training.MusicPlayer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableAsync
@EnableScheduling
public class AppConfig {
    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);
    @Autowired
    private RestTemplate restTemplate;

    @Scheduled(fixedDelay = 840000)
    public void doSomething() {
       logger.info("Schedule task run to keep server up to date...");
       Object data = restTemplate.getForEntity("https://noter-jzlg.onrender.com/api/wakeup", Object.class);
       logger.info(data.toString());
    }
}