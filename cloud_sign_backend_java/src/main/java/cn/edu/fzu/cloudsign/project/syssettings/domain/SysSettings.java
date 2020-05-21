package cn.edu.fzu.cloudsign.project.syssettings.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.edu.fzu.cloudsign.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 系统参数对象 sys_settings
 * 
 * @author linkai
 * @date 2020-05-06
 */
@ApiModel("系统参数")
public class SysSettings extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 系统设置ID */
    @ApiModelProperty("系统设置ID")
    private Long sysSettingsId;

    /** 每次签到经验 */
    @ApiModelProperty("每次签到经验")
    private Long eachSignExp;

    /** 每次签到时间 */
    @ApiModelProperty("每次签到时间")
    private Long eachSignTime;

    /** 状态（0正常 1停用） */
    @ApiModelProperty("状态（0正常 1停用）")
    private String status;

    public void setSysSettingsId(Long sysSettingsId) 
    {
        this.sysSettingsId = sysSettingsId;
    }

    public Long getSysSettingsId() 
    {
        return sysSettingsId;
    }
    public void setEachSignExp(Long eachSignExp) 
    {
        this.eachSignExp = eachSignExp;
    }

    public Long getEachSignExp() 
    {
        return eachSignExp;
    }
    public void setEachSignTime(Long eachSignTime) 
    {
        this.eachSignTime = eachSignTime;
    }

    public Long getEachSignTime() 
    {
        return eachSignTime;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sysSettingsId", getSysSettingsId())
            .append("eachSignExp", getEachSignExp())
            .append("eachSignTime", getEachSignTime())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
