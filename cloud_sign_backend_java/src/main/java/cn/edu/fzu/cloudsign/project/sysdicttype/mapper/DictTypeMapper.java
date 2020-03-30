package cn.edu.fzu.cloudsign.project.sysdicttype.mapper;

import java.util.List;
import cn.edu.fzu.cloudsign.project.sysdicttype.domain.DictType;

/**
 * 字典类型Mapper接口
 * 
 * @author linkai
 * @date 2020-03-27
 */
public interface DictTypeMapper 
{
    /**
     * 查询字典类型
     * 
     * @param dictTypeId 字典类型ID
     * @return 字典类型
     */
    public DictType selectDictTypeById(Long dictTypeId);

    /**
     * 查询字典类型列表
     * 
     * @param dictType 字典类型
     * @return 字典类型集合
     */
    public List<DictType> selectDictTypeList(DictType dictType);

    /**
     * 新增字典类型
     * 
     * @param dictType 字典类型
     * @return 结果
     */
    public int insertDictType(DictType dictType);

    /**
     * 修改字典类型
     * 
     * @param dictType 字典类型
     * @return 结果
     */
    public int updateDictType(DictType dictType);

    /**
     * 删除字典类型
     * 
     * @param dictTypeId 字典类型ID
     * @return 结果
     */
    public int deleteDictTypeById(Long dictTypeId);

    /**
     * 批量删除字典类型
     * 
     * @param dictTypeIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteDictTypeByIds(Long[] dictTypeIds);
}
