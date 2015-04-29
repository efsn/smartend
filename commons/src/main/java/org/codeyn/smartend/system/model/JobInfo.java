package org.codeyn.smartend.system.model;

import java.io.Serializable;

/**
 * 定时器信息表
 * 
 */
public class JobInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8779722466711301747L;

	/**
	 * 自增Id
	 */
	private Integer id;

	/**
	 * Job类型
	 */
	private Integer type;

	/**
	 * 执行点，记录job执行到哪一步
	 */
	private Integer jobPoint;

	/**
	 * 创建时间
	 */
	private Long createTime;

	/**
	 * 更新时间
	 */
	private Long updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getJobPoint() {
		return jobPoint;
	}

	public void setJobPoint(Integer jobPoint) {
		this.jobPoint = jobPoint;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

}
