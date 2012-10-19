package com.wbs.tools.job.test;

import org.quartz.JobExecutionContext;

import com.wbs.tools.job.base.SimpleJob;

public class JobEntity extends SimpleJob{

	@Override
	public void executeJob(JobExecutionContext context) {
		System.out.println("执行了一次JobEntity");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("异常终止！");
		}
		System.out.println("执行完了！");
	}

}
