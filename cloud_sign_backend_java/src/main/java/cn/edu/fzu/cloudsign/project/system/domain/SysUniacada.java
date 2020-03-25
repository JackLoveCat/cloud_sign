package cn.edu.fzu.cloudsign.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.edu.fzu.cloudsign.framework.web.domain.BaseEntity;

/**
 * 学校院系对象 sys_uniacada
 * 
 * @author fanxuenan
 * @date 2020-03-21
 */
public class SysUniacada extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学校院系ID */
    private Long uniacadaId;

    /** 学校名称 */

    private String universityName;

    /** 院系名称 */

    private String academyName;

    /** 显示顺序 */

    private Long orderNum;

    public void setUniacadaId(Long uniacadaId) 
    {
        this.uniacadaId = uniacadaId;
    }

    public Long getUniacadaId() 
    {
        return uniacadaId;
    }
    public void setUniversityName(String universityName) 
    {
        this.universityName = universityName;
    }

    public String getUniversityName() 
    {
        return universityName;
    }
    public void setAcademyName(String academyName) 
    {
        this.academyName = academyName;
    }

    public String getAcademyName() 
    {
        return academyName;
    }
    public void setOrderNum(Long orderNum) 
    {
        this.orderNum = orderNum;
    }

    public Long getOrderNum() 
    {
        return orderNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("uniacadaId", getUniacadaId())
            .append("universityName", getUniversityName())
            .append("academyName", getAcademyName())
            .append("orderNum", getOrderNum())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
