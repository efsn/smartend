package org.codeyn.smartend.framework.base;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.TimerTask;

import org.apache.commons.lang.StringUtils;
import org.codeyn.smartend.framework.util.PropertyConfigurer;

import common.Logger;

public abstract class AbstractJob extends TimerTask{

    public static final String ALL = "all";
    public static final String HOST = "job.execute.host";
    
    protected final Logger logger = Logger.getLogger(this.getClass());
    
    public abstract String getJobName();
    
    public abstract boolean isSyncData();
    
    public abstract void execute();
    
    public String getJobHost(){
        return PropertyConfigurer.get(AbstractJob.HOST);
    }
    
    
    @Override
    public void run(){
        if(!isSyncData()){
            logger.info(getJobHost() + " execute forbidden.");
        }
        
        try{
            String localhost = InetAddress.getLocalHost().getHostName();
            String jobHost = getJobHost();
            
            if(StringUtils.isNotBlank(jobHost) && !localhost.equalsIgnoreCase(jobHost)){
                logger.debug(localhost.concat(" cannot execute job ").concat(getJobName()).concat(". jobHost = ").concat(jobHost));
                return;
            }
            logger.debug(localhost.concat(" is able to execute job ").concat(getJobName()).concat(". jobHost = ").concat(jobHost));
        } catch (UnknownHostException e){
            logger.error("getHostName failed!", e);
        }
        
        try{
            logger.info(getJobHost().concat(" begin execute."));
            execute();
        } catch (Throwable t){
            logger.error(getJobHost().concat(" execute failed!"));
        } finally {
            logger.info(getJobHost().concat(" execute complete."));
        }
    }
    
}
