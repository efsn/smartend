package org.codeyn.smartend.framework.base;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.TimerTask;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codeyn.smartend.framework.util.PropertyConfigurer;

/**
 * 计划任务抽象定时器
 * 
 * @author parcel
 * 
 * @date 2015年2月21日
 */
public abstract class AbstractScheduledJob extends TimerTask {

	/**
	 * 表示所有主机都执行job
	 */
	public static final String ALL = "all";

	protected static final Logger logger = Logger.getLogger(AbstractScheduledJob.class);

	public abstract String getJobName();

	public String getJobHost() {
		return PropertyConfigurer.get("job.execute.host");
	};

	public abstract boolean isSynData();

	@Override
	public void run() {

		if (!isSynData()) {
			logger.info(getJobName() + " execute forbidden....");
			return;
		}

		try {
			String localhost = InetAddress.getLocalHost().getHostName();
			String jobHost = getJobHost();

			// 假如指定了调度机器，但是当前机器不是指定的调度机器则不允许调度执行
			if (StringUtils.isNotBlank(jobHost) && !localhost.equalsIgnoreCase(jobHost)) {
				logger.debug(localhost + " cannot execute job " + getJobName() + ". jobHost = " + jobHost);
				return;
			}

			logger.debug(localhost + " is able to execute job " + getJobName() + ". jobHost = " + jobHost);
		} catch (UnknownHostException e) {
			logger.error("getHostName failed!", e);
		}

		try {
			logger.info(getJobName() + " begin execute....");
			execute();
		} catch (Throwable t) {
			logger.error(getJobName() + " execute failed!", t);
		} finally {
			logger.info(getJobName() + " execute complete...");
		}
	}

	public abstract void execute();
}
