package com.example.demo.datasourceconfig;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


/**
 *  定义定时任务的线程池和普通的线程池
 */

@Configuration
@EnableConfigurationProperties(TaskSchedulerConfiguration.TaskSchedulerProperties.class)
public class TaskSchedulerConfiguration {

	@ConfigurationProperties(prefix="thread.pool")
	@Data
	public static class TaskSchedulerProperties{
		/**
		 * spring启动定时的线程池大小,默认10
		 */
		private int timerPoolSize=10;
		/**
		 * 所有定时要执行的代码都放在一个线程池里执行,线程池的大小,默认50
		 */
		private int taskPoolSize=50;
	}
	@Autowired
	private TaskSchedulerProperties taskSchedulerProperties;

	//定义定时任务的线程池
	@Bean
	public TaskScheduler taskScheduler(){
		return new ConcurrentTaskScheduler(Executors.newScheduledThreadPool(taskSchedulerProperties.getTimerPoolSize()));
	}


	//定义线程池
	@Bean  
    public Executor myAsync() {  
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();  
        executor.setCorePoolSize(taskSchedulerProperties.getTaskPoolSize());  
        executor.setMaxPoolSize(taskSchedulerProperties.getTaskPoolSize());  
        executor.setQueueCapacity(taskSchedulerProperties.getTaskPoolSize());  
        executor.setThreadNamePrefix("MyExecutor-");  
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());  
        executor.initialize();  
        return executor;  
    }  
}
