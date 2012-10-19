package com.wbs.tools;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SchedulerUtil {

	static Logger log = LoggerFactory.getLogger(SchedulerUtil.class);

	public static boolean isDataTaskScheduled(String jobId) {
		return isJobScheduled(AppConstants.SCHEDULER, jobId,
				AppConstants.DATA_GROUP);
	}

	private static boolean isJobScheduled(Scheduler scheduler, String jobId,
			String groupId) {
		if (scheduler == null) {
			return false;
		}
		JobKey jobKey = new JobKey(jobId, groupId);
		boolean success = false;
		try {
			success = scheduler.checkExists(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

		return success;

	}



	
}
