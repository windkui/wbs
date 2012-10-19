package com.wbs.tools.job;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.List;

import org.quartz.CronTrigger;
import org.quartz.InterruptableJob;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerKey;
import org.quartz.UnableToInterruptJobException;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wbs.tools.AppConstants;

public class SendMailJob implements Job {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private static SchedulerFactory sf = new StdSchedulerFactory();
	private static Scheduler scheduler;
	public void execute(JobExecutionContext jobexecutioncontext)
			throws JobExecutionException {
		System.out.println("任务开始自行了");
		
//		try {
//			Thread.sleep(5000);
//			JobDataMap dataMap = jobexecutioncontext.getMergedJobDataMap();
//			
//			scheduler.interrupt(dataMap.getString("jobKey"));
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			System.out.println("任务异常终止");
//		} catch (UnableToInterruptJobException e) {
//			e.printStackTrace();
//		}
		
		System.out.println("执行完毕！");
	}

	public static void unScheduleMailJob(Job j) {
		Scheduler scheduler = AppConstants.SCHEDULER;
		String name = "";
		String group = "";
		JobKey jobKey = new JobKey(name, group);
		try {
			if (scheduler.checkExists(jobKey)) {
				scheduler.deleteJob(jobKey);
				List<JobExecutionContext> executionContexts = scheduler
						.getCurrentlyExecutingJobs();
				for (JobExecutionContext execution : executionContexts) {
					JobKey exeJobKey = execution.getJobDetail().getKey();
					if (group.equals(exeJobKey.getGroup())
							&& name.equals(exeJobKey.getName())) {
						Job exeJob = execution.getJobInstance();
						if (ShutdownableJob.class.isAssignableFrom(exeJob
								.getClass())) {
							((ShutdownableJob) exeJob).shutdown();
						}
					}
				}
				System.out.println("停止任务:");
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	public static void scheduleMailJob(Job j)
			throws SchedulerException {
		// 配置未激活，返回
//		if (j==null) {
//			return;
//		}
		String key = "123";
		scheduler = sf.getScheduler();
		
		JobKey jobKey = new JobKey(key, key);
		if (!scheduler.checkExists(jobKey)) {
			JobDataMap dataMap = new JobDataMap();
			dataMap.put("jobKey", jobKey);
			JobDetail job = JobBuilder.newJob(SendMailJob.class)
					.usingJobData(dataMap).withIdentity(jobKey).build();
			String timeExpress = "0 * * * * ?";
			String cronExpress = timeExpress;

			TriggerKey triggerKey = new TriggerKey("key", "key");

			CronTrigger trigger = newTrigger().withIdentity(triggerKey)
					.withSchedule(cronSchedule(cronExpress)).forJob(job)
					.build();
			scheduler.scheduleJob(job, trigger);
			scheduler.start();
			System.out.println("激活成功");
		}
	}

}
