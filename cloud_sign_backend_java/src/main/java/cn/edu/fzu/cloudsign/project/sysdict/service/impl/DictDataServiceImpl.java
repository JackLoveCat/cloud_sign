package cn.edu.fzu.cloudsign.project.sysdict.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.fzu.cloudsign.common.utils.DateUtils;
import cn.edu.fzu.cloudsign.project.sysdict.domain.DictData;
import cn.edu.fzu.cloudsign.project.sysdict.mapper.DictDataMapper;
import cn.edu.fzu.cloudsign.project.sysdict.service.IDictDataService;

/**
 * 字典信息Service业务层处理
 * 
 * @author linkai
 * @date 2020-03-27
 */
@Service
public class DictDataServiceImpl implements IDictDataService 
{
    @Autowired
    private DictDataMapper dictDataMapper;

    /**
     * 查询字典信息
     * 
     * @param dictDataId 字典信息ID
     * @return 字典信息
     */
    @Override
    public DictData selectDictDataById(Long dictDataId)
    {
        return dictDataMapper.selectDictDataById(dictDataId);
    }

    /**
     * 查询字典信息列表
     * 
     * @param dictData 字典信息
     * @return 字典信息
     */
    @Override
    public List<DictData> selectDictDataList(DictData dictData)
    {
        return dictDataMapper.selectDictDataList(dictData);
    }

    /**
     * 新增字典信息
     * 
     * @param dictData 字典信息
     * @return 结果
     */
    @Override
    public int insertDictData(DictData dictData)
    {
        dictData.setCreateTime(DateUtils.getNowDate());
        return dictDataMapper.insertDictData(dictData);
    }

    /**
     * 修改字典信息
     * 
     * @param dictData 字典信息
     * @return 结果
     */
    @Override
    public int updateDictData(DictData dictData)
    {
        dictData.setUpdateTime(DateUtils.getNowDate());
        return dictDataMapper.updateDictData(dictData);
    }

    /**
     * 批量删除字典信息
     * 
     * @param dictDataIds 需要删除的字典信息ID
     * @return 结果
     */
    @Override
    public int deleteDictDataByIds(Long[] dictDataIds)
    {
        return dictDataMapper.deleteDictDataByIds(dictDataIds);
    }

    /**
     * 删除字典信息信息
     * 
     * @param dictDataId 字典信息ID
     * @return 结果
     */
    @Override
    public int deleteDictDataById(Long dictDataId)
    {
        return dictDataMapper.deleteDictDataById(dictDataId);
    }

	@Override
	public List<DictData> queryDictDataByType(String dictType) {
		return dictDataMapper.queryDictDataByType(dictType);
	}
}
