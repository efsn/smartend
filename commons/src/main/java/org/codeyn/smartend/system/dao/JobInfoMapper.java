package org.codeyn.smartend.system.dao;

import org.codeyn.smartend.system.model.JobInfo;

/**
 * JobInfoMapper 定时器信息表
 */
public interface JobInfoMapper {

    /**
     * 根据 JobInfo type 获取 JobInfo
     */
    public JobInfo getJobInfoByType(Integer type);

    /**
     * 新增 JobInfo
     */
    public void addJobInfo(JobInfo jobInfo);

    /**
     * 更新 JobInfo
     */
    public void updateJobInfo(JobInfo jobInfo);
}
