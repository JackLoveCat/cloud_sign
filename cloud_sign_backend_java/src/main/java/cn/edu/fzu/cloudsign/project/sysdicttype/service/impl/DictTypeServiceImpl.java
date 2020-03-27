package cn.edu.fzu.cloudsign.project.sysdicttype.service.impl;

import java.util.List;
import cn.edu.fzu.cloudsign.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.fzu.cloudsign.project.sysdicttype.mapper.DictTypeMapper;
import cn.edu.fzu.cloudsign.project.sysdicttype.domain.DictType;
import cn.edu.fzu.cloudsign.project.sysdicttype.service.IDictTypeService;

/**
 * 字典类型Service业务层处理
 * 
 * @author linkai
 * @date 2020-03-27
 */
@Service
public class DictTypeServiceImpl implements IDictTypeService 
{
    @Autowired
    private DictTypeMapper dictTypeMapper;

    /**
     * 查询字典类型
     * 
     * @param dictTypeId 字典类型ID
     * @return 字典类型
     */
    @Override
    public DictType selectDictTypeById(Long dictTypeId)
    {
        return dictTypeMapper.selectDictTypeById(dictTypeId);
    }

    /**
     * 查询字典类型列表
     * 
     * @param dictType 字典类型
     * @return 字典类型
     */
    @Override
    public List<DictType> selectDictTypeList(DictType dictType)
    {
        return dictTypeMapper.selectDictTypeList(dictType);
    }

    /**
     * 新增字典类型
     * 
     * @param dictType 字典类型
     * @return 结果
     */
    @Override
    public int insertDictType(DictType dictType)
    {
        dictType.setCreateTime(DateUtils.getNowDate());
        return dictTypeMapper.insertDictType(dictType);
    }

    /**
     * 修改字典类型
     * 
     * @param dictType 字典类型
     * @return 结果
     */
    @Override
    public int updateDictType(DictType dictType)
    {
        dictType.setUpdateTime(DateUtils.getNowDate());
        return dictTypeMapper.updateDictType(dictType);
    }

    /**
     * 批量删除字典类型
     * 
     * @param dictTypeIds 需要删除的字典类型ID
     * @return 结果
     */
    @Override
    public int deleteDictTypeByIds(Long[] dictTypeIds)
    {
        return dictTypeMapper.deleteDictTypeByIds(dictTypeIds);
    }

    /**
     * 删除字典类型信息
     * 
     * @param dictTypeId 字典类型ID
     * @return 结果
     */
    @Override
    public int deleteDictTypeById(Long dictTypeId)
    {
        return dictTypeMapper.deleteDictTypeById(dictTypeId);
    }
}
