package com.dtinone.datashare.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dtinone.datashare.entity.DictType;

@Mapper
public interface DictTypeMapper {

	/**
	 * 新增目录
	 * @param param
	 * @return 新增数据的id
	 */
	int addItem(DictType param);
	
	/**
	 * 删除目录
	 * @param idKeys
	 * @return 操作成功的数目
	 * 真删
	 * @提示:使用该方法时,应当先查询其子目录结构再做删除,否则无法删除子目录
	 */
	int delItem(@Param("idKeys") List<Integer> idKeys);
	
	/**
	 * 删除目录
	 * @param idKeys
	 * @param isOpen true 表示开启remove 反之关闭
	 * @return 操作成功的数目
	 * 假删
	 * @提示:使用该方法时,应当先查询其子目录结构再做修改,否则无法修改子目录
	 * 改变remove状态 0：可见 -1：不可见
	 */
	int changeRemove(@Param("idKeys") List<Integer> idKeys,@Param("isOpen") boolean isOpen);
	
	/**
	 * 修改目录-相关属性
	 * @param param
	 * @return 数据改变的数量
	 */
	int updateItem(DictType param);
	
	/**
	 *查询目录 
	 * @param condition
	 * @return 数据集合
	 */
	List<DictType> queryInfo(DictType condition);
	
	/**
	 *查询目录 
	 * @param condition
	 * @return 数据集合
	 */
	List<DictType> queryInfoByName(@Param("condition") List<DictType> condition);
}
