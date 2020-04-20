package cn.edu.fzu.cloudsign.framework.web.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

/**
 * Entity基类
 * 
 */
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 搜索值 */
	@ApiModelProperty(hidden = true)
	@JsonIgnore
	private String searchValue;

	/** 创建者 */
	@ApiModelProperty(hidden = true)
	private String createBy;

	/** 创建时间 */
	@ApiModelProperty(hidden = true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/** 更新者 */
	@ApiModelProperty(hidden = true)
	private String updateBy;

	/** 更新时间 */
	@ApiModelProperty(hidden = true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	/** 备注 */
	@ApiModelProperty("备注")
	private String remark;

	/** 数据权限 */
	@ApiModelProperty(hidden = true)
	@JsonIgnore
	private String dataScope;

	/** 开始时间 */
	@ApiModelProperty(hidden = true)
	@JsonIgnore
	private String beginTime;

	/** 结束时间 */
	@ApiModelProperty(hidden = true)
	@JsonIgnore
	private String endTime;

	/** 请求参数 */
	@ApiModelProperty(hidden = true)
	@JsonIgnore
	private Map<String, Object> params;

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDataScope() {
		return dataScope;
	}

	public void setDataScope(String dataScope) {
		this.dataScope = dataScope;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Map<String, Object> getParams() {
		if (params == null) {
			params = new HashMap<>();
		}
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
}
