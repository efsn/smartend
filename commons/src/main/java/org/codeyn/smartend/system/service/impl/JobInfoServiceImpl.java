package org.codeyn.smartend.system.service.impl;

import javax.annotation.Resource;

import org.codeyn.smartend.system.dao.JobInfoMapper;
import org.codeyn.smartend.system.model.JobInfo;
import org.codeyn.smartend.system.service.JobInfoService;
import org.springframework.stereotype.Service;

/**
 * JobInfo Service 实现类
 */
@Service("jobInfoService")
public class JobInfoServiceImpl implements JobInfoService {

	@Resource
	private JobInfoMapper jobInfoMapper;

	@Override
	public JobInfo getJobInfoByType(Integer type) {
		JobInfo jobInfo = jobInfoMapper.getJobInfoByType(type);

		if (jobInfo == null) {
			jobInfo = new JobInfo();
			jobInfo.setType(type);
			jobInfo.setJobPoint(0);
			jobInfo.setCreateTime(System.currentTimeMillis());
			jobInfo.setUpdateTime(System.currentTimeMillis());
			this.addJobInfo(jobInfo);
		}
		return jobInfo;
	}

	@Override
	public void addJobInfo(JobInfo jobInfo) {
		jobInfoMapper.addJobInfo(jobInfo);
	}

	@Override
	public void updateJobInfo(JobInfo jobInfo) {
		jobInfoMapper.updateJobInfo(jobInfo);
	}
}
