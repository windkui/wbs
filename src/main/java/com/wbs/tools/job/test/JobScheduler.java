package com.wbs.tools.job.test;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;

import com.wbs.tools.job.base.SchedulerManager;


public class JobScheduler {

	public static void startJob(){
		String key = "test";
		JobKey jobKey = new JobKey(key, key);
		
		JobDetail job = JobBuilder.newJob(JobEntity.class).withIdentity(jobKey).build();
		String cronExpress = "0 * * * * ?";
		
		SchedulerManager.startJob(job, cronExpress);
		
	}
}
