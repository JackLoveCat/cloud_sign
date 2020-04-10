package cn.edu.fzu.cloudsign.project.sysdictdata.mapper;

import java.util.List;
import cn.edu.fzu.cloudsign.project.sysdictdata.domain.DictData;

/**
 * 字典信息Mapper接口
 * 
 * @author linkai
 * @date 2020-03-27
 */
public interface DictDataMapper 
{
    /**
     * 查询字典信息
     * 
     * @param dictDataId 字典信息ID
     * @return 字典信息
     */
    public DictData selectDictDataById(Long dictDataId);

    /**
     * 查询字典信息列表
     * 
     * @param dictData 字典信息
     * @return 字典信息集合
     */
    public List<DictData> selectDictDataList(DictData dictData);

    /**
     * 新增字典信息
     * 
     * @param dictData 字典信息
     * @return 结果
     */
    public int insertDictData(DictData dictData);

    /**
     * 修改字典信息
     * 
     * @param dictData 字典信息
     * @return 结果
     */
    public int updateDictData(DictData dictData);

    /**
     * 删除字典信息
     * 
     * @param dictDataId 字典信息ID
     * @return 结果
     */
    public int deleteDictDataById(Long dictDataId);

    /**
     * 批量删除字典信息
     * 
     * @param dictDataIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteDictDataByIds(Long[] dictDataIds);
}
