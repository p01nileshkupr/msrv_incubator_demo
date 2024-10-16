package com.nileshprajapati.incubator_demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableCaching
@EnableAsync
public class IncubatorDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(IncubatorDemoApplication.class, args);
    }

    @Value("${thread.core.pool.size}")
    private int threadCorePoolSize;

    @Value("${thread.max.pool.size}")
    private int threadMaximumPoolSize;

    @Value("${thread.queue.capacity}")
    private int threadQueueCapacity;

    @Primary
    @Bean(name = "taskExecutorDefault")
    public ThreadPoolTaskExecutor taskExecutorDefault() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(this.threadCorePoolSize);
        executor.setMaxPoolSize(this.threadMaximumPoolSize);
        executor.setQueueCapacity(this.threadQueueCapacity);
        executor.setThreadNamePrefix("Async1-");
        executor.initialize();
        return executor;
    }

    @Bean(name = "taskExecutorForHeavyTasks")
    public ThreadPoolTaskExecutor taskExecutorRegistration() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(this.threadCorePoolSize);
        executor.setMaxPoolSize(this.threadMaximumPoolSize);
        executor.setQueueCapacity(this.threadQueueCapacity);
        executor.setThreadNamePrefix("Async2-");
        executor.initialize();
        return executor;
    }
}
