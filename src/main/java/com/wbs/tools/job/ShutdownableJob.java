package com.wbs.tools.job;

import org.quartz.Job;

public interface ShutdownableJob extends Job {

	void shutdown();
}
