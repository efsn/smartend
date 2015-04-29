package org.codeyn.smartend.system.dao;

import org.codeyn.smartend.system.model.JobInfo;

/**
 * JobInfoMapper 定时器信息表
 * 
 * @author parcel
 * 
 * @date 2014-08-29 17:10:52
 * 
 */
public interface JobInfoMapper {

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
