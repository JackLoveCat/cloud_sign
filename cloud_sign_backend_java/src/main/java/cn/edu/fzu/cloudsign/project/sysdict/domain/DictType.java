package cn.edu.fzu.cloudsign.project.sysdict.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import cn.edu.fzu.cloudsign.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 字典类型对象 sys_dict_type
 * 
 * @author linkai
 * @date 2020-03-27
 */
@ApiModel("字典类型")
public class DictType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 字典类型主键 */
    @ApiModelProperty("字典类型主键")
    private Long dictTypeId;

    /** 字典类型名称 */
    @ApiModelProperty("字典类型名称")
    private String dictTypeName;

    /** 字典类型 */
    @ApiModelProperty("字典类型")
    private String dictType;

    /** 状态（0正常 1停用） */
    @ApiModelProperty("状态（0正常 1停用）")
    private String status;

    public void setDictTypeId(Long dictTypeId) 
    {
        this.dictTypeId = dictTypeId;
    }

    public Long getDictTypeId() 
    {
        return dictTypeId;
    }
    public void setDictTypeName(String dictTypeName) 
    {
        this.dictTypeName = dictTypeName;
    }

    public String getDictTypeName() 
    {
        return dictTypeName;
    }
    public void setDictType(String dictType) 
    {
        this.dictType = dictType;
    }

    public String getDictType() 
    {
        return dictType;
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
            .append("dictTypeId", getDictTypeId())
            .append("dictTypeName", getDictTypeName())
            .append("dictType", getDictType())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
