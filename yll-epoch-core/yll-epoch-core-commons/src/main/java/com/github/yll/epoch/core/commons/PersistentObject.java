package com.github.yll.epoch.core.commons;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * 持久化基类
 *
 * @author luliang_yu
 * @date 2018/11/21
 */
public abstract class PersistentObject implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/** 创建时间  */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTimestamp;
	/** 创建人ID  */
	private Integer createUserId;
	/** 修改时间  */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date lastChangeTimestamp;
	/** 修改人ID  */
	private Integer changeUserId;

	public abstract Object getId();

	public Date getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Date createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Date getLastChangeTimestamp() {
		return lastChangeTimestamp;
	}

	public void setLastChangeTimestamp(Date lastChangeTimestamp) {
		this.lastChangeTimestamp = lastChangeTimestamp;
	}

	public Integer getChangeUserId() {
		return changeUserId;
	}

	public void setChangeUserId(Integer changeUserId) {
		this.changeUserId = changeUserId;
	}
}