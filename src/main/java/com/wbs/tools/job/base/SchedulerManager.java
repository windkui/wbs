package com.wbs.tools.job.base;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.List;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 任务调度控制器
 * @author windkui
 *
 */
public abstract class SchedulerManager {

	Logger log = LoggerFactory.getLogger(SchedulerManager.class);
	private static Scheduler scheduler;
	
	private SchedulerManager(){
		
	}
	
	/**
	 * 初始化scheduler调度器
	 */
	static {
		if(scheduler == null){
			SchedulerFactory sf = new StdSchedulerFactory();
			try {
				scheduler = sf.getScheduler();
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 判断任务是否已存在
	 * @param job
	 * @return
	 */
	public static boolean isExistJob(JobDetail job){
		if(job == null){
			return false;
		}
		JobKey jobKey = job.getKey();
		try {
			return scheduler.checkExists(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 停止一个任务
	 * @param jobKey
	 * @return
	 */
	public static boolean stopJob(JobKey jobKey){
		try {
			if (scheduler.checkExists(jobKey)) {
				scheduler.deleteJob(jobKey);
				scheduler.interrupt(jobKey);
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * 启动一个任务
	 * @param job 任务：包括任务所属类，任务主键
	 * @param dataMap 启动任务时，需要传递的参数
	 * @param cronExpress 任务执行规则
	 * @return 启动成功
	 * @throws SchedulerException
	 */
	public static boolean startJob(JobDetail job,String cronExpress){
		JobKey jobKey = job.getKey();
		String key = jobKey.getName();
		String group = jobKey.getGroup();
		
		try {
			if (!scheduler.checkExists(jobKey)) {
				TriggerKey triggerKey = new TriggerKey(key, group);

				CronTrigger trigger = newTrigger().withIdentity(triggerKey)
						.withSchedule(cronSchedule(cronExpress)).forJob(job)
						.build();
				scheduler.scheduleJob(job, trigger);
				scheduler.start();
			}
			return true;
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}
