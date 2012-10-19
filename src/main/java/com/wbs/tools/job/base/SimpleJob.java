package com.wbs.tools.job.base;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Job基本类，设置标志位判断job是否允许执行
 * @author windkui
 *
 */
public abstract class SimpleJob implements Job {

	//JOB是否中断的标志
	private boolean isInterrupt = false;
	
	/**
	 * 实现父类的execute任务方法，判断如果标志位为空，则终止执行JOB
	 */
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println(isInterrupt);
		
		if(!isInterrupt){
			executeJob(context);
		}
		
	}

	/**
	 * 子类实现方法
	 * @param arg0
	 */
	public abstract void executeJob(JobExecutionContext context);
	
	public void interrupt() {
		isInterrupt = true;
	}

}
