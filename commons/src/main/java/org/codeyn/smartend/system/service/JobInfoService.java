package org.codeyn.smartend.system.service;

import org.codeyn.smartend.system.model.JobInfo;

/**
 * 
 * JobInfo Service 接口
 * 
 */
public interface JobInfoService {

	/**
	 * 根据 JobInfo type 获取 JobInfo
	 * 
	 * @param type
	 * @return
	 */
	public JobInfo getJobInfoByType(Integer type);

	/**
	 * 新增 JobInfo
	 * 
	 * @param JobInfo
	 */
	public void addJobInfo(JobInfo jobInfo);

	/**
	 * 更新 JobInfo
	 * 
	 * @param JobInfo
	 */
	public void updateJobInfo(JobInfo jobInfo);
}
