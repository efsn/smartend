package org.codeyn.smartend.job;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;

import org.codeyn.smartend.framework.base.AbstractJob;
import org.codeyn.smartend.system.model.JobInfo;
import org.codeyn.smartend.system.service.JobInfoService;
import org.springframework.stereotype.Service;

@Service
public class JobQuartz extends AbstractJob{

    private static String DEFAULT_SYNC_ON = "timer.job.sync.on";
    
    @Resource
    private JobInfoService jobInfoService;
    
    @Override
    public String getJobName(){
        return this.getClass().getName();
    }

    @Override
    public boolean isSyncData(){
        return true;
    }

    @Override
    public void execute(){
        logger.info("开始执行交易数据拉取调度：");
        try {
            //结束日期 
            long timeMillis = System.currentTimeMillis();
            // 如果使用初始同步时间
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            JobInfo info = jobInfoService.getJobInfoByType(1);
            // 获取时间戳
            Long updateTime = info.getUpdateTime();
            Timestamp timestamp = new Timestamp(updateTime);
            // 订单起始结束日期
            String startTime = sdf.format(timestamp);
            String endTime = sdf.format(timeMillis);
            
//            jobConsumptionService.executeJobService(startTime, endTime);
            
            //改变更新时间
            info.setUpdateTime(timeMillis);
            jobInfoService.updateJobInfo(info);
        } catch (Exception e) {
            logger.error("交易数据拉取异常", e);
        }
        logger.info("交易拉取数据结束");
    }
        
}
