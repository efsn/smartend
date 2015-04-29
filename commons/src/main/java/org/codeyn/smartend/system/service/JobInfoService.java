package org.codeyn.smartend.system.service;

import org.codeyn.smartend.system.model.JobInfo;

/**
 * 
 * JobInfo Service 接口
 * 
 */
public interface JobInfoService {

	public JobInfo getJobInfoByType(Integer type);

	public void addJobInfo(JobInfo jobInfo);

	public void updateJobInfo(JobInfo jobInfo);
	
}
