package cn.edu.fzu.cloudsign.project.sysdictdata.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.edu.fzu.cloudsign.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 字典信息对象 sys_dict_data
 * 
 * @author linkai
 * @date 2020-03-27
 */
@ApiModel("字典信息")
public class DictData extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 字典信息主键 */
    @ApiModelProperty("字典信息主键")
    private Long dictDataId;

    /** 字典信息排序 */
    @ApiModelProperty("字典信息排序")
    private Long dictDataSort;

    /** 字典信息标签 */
    @ApiModelProperty("字典信息标签")
    private String dictLabel;

    /** 字典信息键值 */
    @ApiModelProperty("字典信息键值")
    private String dictValue;

    /** 字典类型 */
    @ApiModelProperty("字典类型")
    private String dictType;

    /** 表格回显样式 */
    @ApiModelProperty("表格回显样式")
    private String listClass;

    /** 是否默认（Y是 N否） */
    @ApiModelProperty("是否默认（Y是 N否）")
    private String isDefault;

    /** 状态（0正常 1停用） */
    @ApiModelProperty("状态（0正常 1停用）")
    private String status;

    public void setDictDataId(Long dictDataId) 
    {
        this.dictDataId = dictDataId;
    }

    public Long getDictDataId() 
    {
        return dictDataId;
    }
    public void setDictDataSort(Long dictDataSort) 
    {
        this.dictDataSort = dictDataSort;
    }

    public Long getDictDataSort() 
    {
        return dictDataSort;
    }
    public void setDictLabel(String dictLabel) 
    {
        this.dictLabel = dictLabel;
    }

    public String getDictLabel() 
    {
        return dictLabel;
    }
    public void setDictValue(String dictValue) 
    {
        this.dictValue = dictValue;
    }

    public String getDictValue() 
    {
        return dictValue;
    }
    public void setDictType(String dictType) 
    {
        this.dictType = dictType;
    }

    public String getDictType() 
    {
        return dictType;
    }
    public void setListClass(String listClass) 
    {
        this.listClass = listClass;
    }

    public String getListClass() 
    {
        return listClass;
    }
    public void setIsDefault(String isDefault) 
    {
        this.isDefault = isDefault;
    }

    public String getIsDefault() 
    {
        return isDefault;
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
            .append("dictDataId", getDictDataId())
            .append("dictDataSort", getDictDataSort())
            .append("dictLabel", getDictLabel())
            .append("dictValue", getDictValue())
            .append("dictType", getDictType())
            .append("listClass", getListClass())
            .append("isDefault", getIsDefault())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
