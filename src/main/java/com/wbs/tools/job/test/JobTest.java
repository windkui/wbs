package com.wbs.tools.job.test;

import org.junit.Test;

import com.wbs.unit.BaseSpringTest;

public class JobTest extends BaseSpringTest {
	
	@Test
	public void testJob(){
		JobScheduler.startJob();
	}
}
